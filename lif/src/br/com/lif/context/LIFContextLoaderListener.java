package br.com.lif.context;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class LIFContextLoaderListener implements ServletContextListener {
	
	public static boolean springController;
	public static boolean springParameters;
	public static boolean ejbRemote;

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {

	}

	@Override
	public void contextInitialized(ServletContextEvent arg) {
		springController = (Boolean.parseBoolean((String) arg.getServletContext().getInitParameter("springController")));
		springParameters = (Boolean.parseBoolean((String) arg.getServletContext().getInitParameter("springParameters")));
		ejbRemote = (Boolean.parseBoolean((String) arg.getServletContext().getInitParameter("ejbRemote")));
	}
}
