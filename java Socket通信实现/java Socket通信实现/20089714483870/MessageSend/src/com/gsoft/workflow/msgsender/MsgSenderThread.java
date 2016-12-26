package com.gsoft.workflow.msgsender;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Hashtable;

import com.gsoft.workflow.msgsender.Tokens;
import com.lotus.sametime.awareness.AwarenessService;
import com.lotus.sametime.awareness.StatusEvent;
import com.lotus.sametime.awareness.StatusListener;
import com.lotus.sametime.awareness.WatchList;
import com.lotus.sametime.community.CommunityService;
import com.lotus.sametime.community.Login;
import com.lotus.sametime.community.LoginEvent;
import com.lotus.sametime.community.LoginListener;
import com.lotus.sametime.community.ServerAppService;
import com.lotus.sametime.community.ServiceEvent;
import com.lotus.sametime.community.ServiceListener;
import com.lotus.sametime.core.comparch.DuplicateObjectException;
import com.lotus.sametime.core.comparch.STSession;
import com.lotus.sametime.core.constants.EncLevel;
import com.lotus.sametime.core.constants.ImTypes;
import com.lotus.sametime.core.types.STUser;
import com.lotus.sametime.core.types.STUserInstance;
import com.lotus.sametime.core.util.connection.Connection;
import com.lotus.sametime.core.util.connection.SocketConnection;
import com.lotus.sametime.im.Im;
import com.lotus.sametime.im.ImEvent;
import com.lotus.sametime.im.ImListener;
import com.lotus.sametime.im.ImServiceListener;
import com.lotus.sametime.im.InstantMessagingService;
import com.lotus.sametime.lookup.LookupService;
import com.lotus.sametime.lookup.ResolveEvent;
import com.lotus.sametime.lookup.ResolveListener;
import com.lotus.sametime.lookup.Resolver;
import com.lotus.sametime.token.SATokenService;
import com.lotus.sametime.token.Token;
import com.lotus.sametime.token.TokenEvent;
import com.lotus.sametime.token.TokenServiceListener;

public class MsgSenderThread implements Runnable, LoginListener, ServiceListener,  
								ImServiceListener, ImListener,ResolveListener, 
								StatusListener,TokenServiceListener{
	String welcomeMessage = "Welcome to the GroupChatBot.";
	
	protected static boolean debug = true;
	protected boolean running = true;
	protected static String USERTOALERT = "<utw>";
	protected static final String STDRESPONSE = "I\'m sorry.  I don\'t take messages from users";
	protected static final String STATUSMSG = "The Alert Bot is online!";
	
	protected Thread engine;
	protected Token token;
	protected Tokens m_tokens = new Tokens();
	protected SATokenService tokenService;
	protected STSession session;
	protected ServerAppService m_saService;
	protected CommunityService service;
	protected InstantMessagingService imService;
	protected Login login;
	protected LookupService lookupService;
	protected Resolver resolver;
	protected AwarenessService awarenessService;
	protected WatchList watchList;
	protected Im im;
	protected STUser stuser;
	protected String userName;
	protected String sendTo;
	protected String text;
	public boolean isSent=false;
	
	private Hashtable unSentMsg = new Hashtable();
	
	public MsgSenderThread() throws DuplicateObjectException {
			session= new STSession("WebOA12");            
			session.loadSemanticComponents();
			session.start();
			loadComponents();
	}
  
	public void start(String server, String username, String password)
	{
	
		service = (CommunityService)session.getCompApi(CommunityService.COMP_NAME);
		m_saService = (ServerAppService)session.getCompApi(ServerAppService.COMP_NAME);
		service.addServiceListener(this);
		login(server, username, password);
		
		//init Token new
		m_tokens.initSametime(server);
		
	}
  
	public void stop()
	{	
		service.logout();
	}

	public void loggedIn(LoginEvent arg0) {
		
		
		//Register to listen for incoming messages
		imService = (InstantMessagingService) session.getCompApi(InstantMessagingService.COMP_NAME);
		imService.registerImType(ImTypes.IM_TYPE_CHAT);
		imService.addImServiceListener(this);

		//Get a handle to the Lookup Service and add a resolve listener
		lookupService = (LookupService) session.getCompApi(LookupService.COMP_NAME);
		resolver = lookupService.createResolver(false, false, true, false);
		resolver.addResolveListener(this);

		//Get a handle to the Awareness Service and create a WatchList
		awarenessService = (AwarenessService) session.getCompApi(AwarenessService.COMP_NAME);
		watchList = awarenessService.createWatchList();
		watchList.addStatusListener(this);
		
		//resolver.resolve(sendTo);

	}
	
	public boolean isLogged()  //ÅÐ¶ÏÊÇ·ñµÇÂ¼³É¹¦
	{
		if(session !=null)
			return session.isActive();
		else
			return false;
	}
	
	public void callrevsolve(String sendTo,String sendtime,String msgcontent,String msgtype){
		this.sendTo=sendTo;
		this.text=msgcontent;
		isSent=false;
		resolver.resolve(sendTo);
		
	}

	public void loggedOut(LoginEvent arg0) {
		int reason = arg0.getReason();
	    if (reason == 0)
	    	printOut("Successfully logged out.");
	    else 
	    	printOut("Failed to login.  Return Code =" + reason);
	    session.stop();
	    session.unloadSession();
	}

	public void serviceAvailable(ServiceEvent arg0) {}

	public void imReceived(ImEvent arg0) {
		printOut("IM Received");

	}

	public void dataReceived(ImEvent arg0) {
		printOut("Data is Received!");
	}

	public void imClosed(ImEvent arg0) {
		printOut("IM Closed from " + arg0.getIm().getPartner().getName());
		im = null;
		arg0.getIm().removeImListener(this);	
	}

	public void imOpened(ImEvent arg0) {
		printOut("IM Opened to " + arg0.getIm().getPartner().getName());
		if (arg0.getIm().isOpen()) {
			String str = arg0.getIm().getPartner().getName();
			printOut("Message sent to " + str);
			if(im==null)
				im = arg0.getIm();
			sendMessage();
		}

		
	}

	public void openImFailed(ImEvent arg0) {
		isSent=true;
		printOut("Open IM Failed");
	}

	public void textReceived(ImEvent arg0) {
		printOut("Text is Received! " + arg0.getText()+" from " + arg0.getIm().getPartner().getName());
	}

	public void resolveConflict(ResolveEvent arg0) {}

	public void resolveFailed(ResolveEvent arg0) {}

	public void resolved(ResolveEvent arg0) {
		
		if (arg0.getResolved() instanceof STUser) {
			STUser user = ((STUser) arg0.getResolved());
			//tokenService.generateToken(user);
			String userName = user.getName();
			printOut("Resolved " + userName);
			watchList.addItem(user);
			printOut("Added " + userName + " to WatchList");
			this.userName =userName; 
			
			//Generate Token String new
			m_tokens.generateToken(user);
			while(m_tokens.getToken()==null){
				try{
					Thread.sleep(100);
				}catch(Exception e){
					
				}
			}
			
			//Add Token String into message new
			String str = "";
			String tmp = text.toLowerCase();
			int begin = tmp.indexOf("<a");
			
			if(begin >= 0){
				Configuration rc = new Configuration("st.property");
				if(begin>1)str = tmp.substring(1, begin);
				begin = tmp.indexOf("=", begin);
				str = str + "<a href='http://"+rc.getValue("domain")+"/servlet/SSORedirect?LtpaToken="+m_tokens.getToken().getTokenString();
				str = str + "&RedirectTo=" + tmp.substring(begin+1);
				text=str;	
			}
			
			
			//Send message new
			if(im==null){
				stuser = user;
				im = imService.createIm(stuser, EncLevel.ENC_LEVEL_NONE, ImTypes.IM_TYPE_CHAT);
				im.addImListener(this);
				im.open();
			}else
			{
				sendMessage();
			}

		}
	}
	
	private void loadComponents() {
		String[] compNames = { "com.lotus.sametime.community.STBase",
				"com.lotus.sametime.token.SATokenComp" };

		session.loadComponents(compNames);

	}

	void loginToServer(String serverName) {
		m_saService.addLoginListener(this);
		
		short loginType = STUserInstance.LT_SERVER_APP;

		Connection[] connections = { new SocketConnection(8082, 17000)};
		m_saService.setConnectivity(connections);	
		m_saService.loginAsServerApp(serverName, loginType, "SametimeServlet",null);
	}

	public void groupCleared(StatusEvent arg0) {}

	private void login(String server, String username, String password)
	{
		service.addLoginListener(this); 
		service.loginByPassword(server,username, password);
	}
	
	public void run() {
		Thread myThread = Thread.currentThread();
		while (running) {
			try {
				Thread.sleep(1000);
			}
			catch (InterruptedException e) {
			}
		}

	}
	
	public void destroy() {
		if (service != null && service.isLoggedIn()) {
			try {
				service.logout();
				running = false;
				engine.interrupt();
			}
			catch (Exception exc) {
				printOut("Could not logout: " + exc.toString());
			}
		}
	}
	
	protected boolean sendMessage(){
		//if(stuser != null){
		
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			DataOutputStream dataStream = new DataOutputStream(baos);
			try
		    {
		        dataStream.writeUTF("data");
		        dataStream.writeUTF("richtext");
		        dataStream.write(new byte[] {-18});
		    }
		    catch(IOException e)
		    {
		        return false;
		    	//throw new AssertionError("sendDataMessage failed");
		        
		    }
		    
		    im.sendData(true, 27191, 0, baos.toByteArray());
		    String str =text;
		    im.sendText(true, str);
		    isSent=true;
		    //im.sendText(true, "hhhhhhhh");
		    printOut("Sent '"+text+"' to "+im.getPartner().getName());
		    
		    return true;
		//}
		//return false;
	}
	
		
	private void printOut(String str){
		if(debug==true)System.out.println(str);
	}

	public void userStatusChanged(StatusEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	public void generateTokenFailed(TokenEvent arg0) {
		// TODO Auto-generated method stub
		System.err.println(arg0.toString());
	}

	public void serviceAvailable(TokenEvent arg0) {
		// TODO Auto-generated method stub
		System.err.println(arg0.toString());
		
	}

	public void tokenGenerated(TokenEvent arg0) {
		// TODO Auto-generated method stub
		token = arg0.getToken();
		System.err.println(token.getTokenString());
		//isSent = true;
		
	}

	public void setIm(Im im) {
		this.im = im;
	}
}
