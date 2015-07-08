package net.kos.servlet;

import javax.servlet.Servlet;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletHandler;

public class MyServer {
	public static void main (String [] args) throws Exception{
		
		Server serv = new Server(8090);
		ServletHandler handler = new ServletHandler();
		serv.setHandler(handler);
		handler.addServletWithMapping(HttpServletExample.class, "/*");
		serv.start();
		serv.join();
		
	}
	
	
	

}
