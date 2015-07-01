package honor.util;

import javax.servlet.http.HttpServletRequest;

public class BasePath {
	
	public static String get(HttpServletRequest req){
		if(req.getServerPort()==80){
		    return req.getScheme()+"://"+req.getServerName()+""+req.getContextPath();	  
		}else{
		    return req.getScheme()+"://"+req.getServerName()+":"+req.getServerPort()+req.getContextPath();	 
		}
	}

}
