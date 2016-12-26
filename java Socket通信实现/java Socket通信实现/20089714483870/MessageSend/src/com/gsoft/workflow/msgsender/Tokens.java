package com.gsoft.workflow.msgsender;

import com.lotus.sametime.community.LoginEvent;
import com.lotus.sametime.community.LoginListener;
import com.lotus.sametime.community.ServerAppService;
import com.lotus.sametime.core.comparch.DuplicateObjectException;
import com.lotus.sametime.core.comparch.STSession;
import com.lotus.sametime.core.types.STUser;
import com.lotus.sametime.core.types.STUserInstance;
import com.lotus.sametime.core.util.connection.Connection;
import com.lotus.sametime.core.util.connection.SocketConnection;
import com.lotus.sametime.token.SATokenService;
import com.lotus.sametime.token.Token;
import com.lotus.sametime.token.TokenEvent;
import com.lotus.sametime.token.TokenServiceListener;

public class Tokens implements LoginListener, TokenServiceListener{
	
	private SATokenService m_tokenService;
	private STSession m_session;
	private ServerAppService m_saService;
	private Token m_token;
	private boolean success;
	private boolean debug=true;
	
	public Tokens(){}
	
	public void initSametime(String hostName){
		try {
			m_session = new STSession("" + this);
			String[] compNames = { "com.lotus.sametime.community.STBase",
			"com.lotus.sametime.token.SATokenComp" };
			
			m_session.loadComponents(compNames);
			m_session.start();
		} catch (DuplicateObjectException e) {
			e.printStackTrace();
		}

		m_saService = (ServerAppService) m_session.getCompApi(ServerAppService.COMP_NAME);
		m_tokenService = (SATokenService) m_session.getCompApi(SATokenService.COMP_NAME);
		m_tokenService.addTokenServiceListener(this);
		// Login to the server
		loginToServer(hostName);
		
		try {
			synchronized (this) {wait();}
		} catch (InterruptedException e) {
			
		}
	}
	
	void loginToServer(String serverName) {
		m_saService.addLoginListener(this);
		short loginType = STUserInstance.LT_SERVER_APP;
		Connection[] connections = { new SocketConnection(8082, 17000)};
		m_saService.setConnectivity(connections);
		m_saService.loginAsServerApp(serverName, loginType, "SametimeTokenGenerator",null);
	}

	
	public void loggedIn(LoginEvent arg0) {
		// TODO Auto-generated method stub
		success=true;
		printOut("Token Service 成功登录");
		synchronized (this) {
			printOut(">> wake up call");
			notify();
		}
		
	}

	public void loggedOut(LoginEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void generateTokenFailed(TokenEvent arg0) {
		// TODO Auto-generated method stub
		printOut("为 "+ arg0.getUser().getName() +"生成Token失败");
		synchronized (this) {
			notify();
		}
	}

	public void serviceAvailable(TokenEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void tokenGenerated(TokenEvent arg0) {
		// TODO Auto-generated method stub
		m_token = arg0.getToken();
		
		//m_imUsers.setToken(arg0.getUser().getName(), arg0.getToken());
		printOut("已为用户 "+arg0.getUser().getName()+ " 生成为Token= "+arg0.getToken().getTokenString());
		
		synchronized (this) {notify();}
		
	}
	
	public void generateToken(STUser stuser){
		m_tokenService.generateToken(stuser);
		printOut("为 " + stuser.getName() +" 生成Token");
		synchronized (this) {
			try {
				wait(10000);
			} catch (InterruptedException e) {
				
			}
		}
	}
	
	public void stop(){
		m_session.stop();
		m_session.unloadSession();
	}
	
	private void printOut(String str){
		if(debug==true)System.out.println(str);
	}
	
	private String convertToken(String str){
		String tmp;
		byte bt[] = str.getBytes();
		tmp=bt.toString();
		return tmp;
		
	}

	public Token getToken() {
		return m_token;
	}
	
	public void resetToken(){
		m_token = null;
	}
}
