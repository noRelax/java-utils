package Net;

import java.net.InetAddress;

public class NetTools {

	public static String getLocalHostIP(){
		String ip;
		try{
			InetAddress addr = InetAddress.getLocalHost();
			ip = addr.getHostAddress(); 
		}catch(Exception ex){
			ip = "";
		}
		return ip;
	}
 
	public static String getLocalHostName(){
		String hostName;
		try{
			InetAddress addr = InetAddress.getLocalHost();
			hostName = addr.getHostName();
		}catch(Exception ex){
			hostName = "";
		}
		return hostName;
	}
 
	public static String[] getAllLocalHostIP(){
		String[] ret = null;
		try{
			String hostName = getLocalHostName();
			if(hostName.length()>0){
				InetAddress[] addrs = InetAddress.getAllByName(hostName);
				if(addrs.length>0){
					ret = new String[addrs.length];
					for(int i=0 ; i< addrs.length ; i++){
						ret[i] = addrs[i].getHostAddress();
					}
				}
			}
		}catch(Exception ex){
			ret = null;
		}
		return ret;
	}

	public static String[] getAllHostIPByName(String hostName){
		String[] ret = null;
		try{
			if(hostName.length()>0){
				InetAddress[] addrs = InetAddress.getAllByName(hostName);
				if(addrs.length>0){
					ret = new String[addrs.length];
					for(int i=0 ; i< addrs.length ; i++){
						ret[i] = addrs[i].getHostAddress();
					}
				}
			}
		}catch(Exception ex){
			ret = null;
		}
		return ret;
	}
	
	public static String getIP(){
		String[] localIP = getAllLocalHostIP();
		String ip="";
		for(int i=0 ; i<localIP.length ; i++){
			ip=ip+localIP[i];
		}
		return ip;
	}

	public static String getName(){
		return getLocalHostName();
	}
	
	public static int getIpSect(){
		String sect="";
		String ip=getIP();
		int x=0;
		int y=0;
		for(int i=0;i<ip.length();i++){
				if(ip.substring(i,i+1).equals(".")){
					x++;	
				}
				if(y==1){
					sect=sect+ip.substring(i,i+1);
				}
				if(x==3){
					y=1;
				}
		}
		return Integer.parseInt(sect);
	}
}