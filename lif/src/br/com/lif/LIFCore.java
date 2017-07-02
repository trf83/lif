package br.com.lif;

import java.lang.reflect.Method;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import br.com.interfaces.ILIFController;
import br.com.interfaces.ILIFParameter;
import br.com.lif.context.LIFContextLoaderListener;
import br.com.lif.util.Constants;
import br.com.lif.util.ReflectionUtils;

public class LIFCore{
	
	private static final Object[] ARG_LIST_METHOD_CURRENT_WEB_APP = new Object[0];
	private static final Class<?>[] PARAMETERS_TYPES_METHOD_BEAN = new Class[]{String.class};
	private static final Object OBJECT_NULL = null;
	
	private static Class<?> classCurrentIOC;
	private static Object currentIOCEJB;
	private static Method currentWebAppMethod;
	private static Object currentWebApp;
	private static Method currentGetBeanMethod;
	
	public static ILIFParameter getBeanParameterMethod(String nameBean) {		
		if(LIFContextLoaderListener.springParameters){
			return (ILIFParameter) getBeanUsingSpring(nameBean);
		}else{
			return (ILIFParameter) getBeanUsingEJB(nameBean);
		}
	}
	
	public static ILIFController getBeanController(String nameBean) {		
		if(LIFContextLoaderListener.springController){
			return (ILIFController) getBeanUsingSpring(nameBean);
		}else{
			return (ILIFController) getBeanUsingEJB(nameBean);
		}
	}
	
	private static Object getBeanUsingEJB(String nameEJB) {
		if(LIFContextLoaderListener.ejbRemote){
			return getEJBRemote(nameEJB);
		}else{
			return ReflectionUtils.invokeMethod(getCurrentIOCEJB(), 
					PARAMETERS_TYPES_METHOD_BEAN,
					new Object[]{nameEJB},
					Constants.GET_BEAN_METHOD_EJB);
		}
	}

	private static Object getCurrentIOCEJB() {
		if(currentIOCEJB == null){
			currentIOCEJB = ReflectionUtils.newInstance(Constants.CTX_LOADER_NAME_CLASS_EJB);
		}
		return currentIOCEJB;
	}

	public static Object getBeanUsingSpring(String nameBean) {
		return ReflectionUtils.invokeMethod(getCurrentGetBeanMethod(), getCurrentWebApp(), new Object[]{nameBean});
	}

	private static Class<?> getClassCurrentIOC() {
		if (classCurrentIOC == null) {
			classCurrentIOC = ReflectionUtils.getClass(Constants.CTX_LOADER_NAME_CLASS_SPRING);			
		}
		return classCurrentIOC;
	}

	private static Method getCurrentWebAppMethod() {
		if(currentWebAppMethod == null){
			currentWebAppMethod = ReflectionUtils.getMethod(getClassCurrentIOC(), 
															Constants.GET_CURRENT_WEBAPPLICATION_METHOD_SPRING, 
															Constants.NIVEL_SUPER_CLASS_CURRENT_WEB_APP);
		}
		return currentWebAppMethod;
	}

	private static Object getCurrentWebApp() {
		if(currentWebApp == null){
			currentWebApp = ReflectionUtils.invokeMethod(getCurrentWebAppMethod(), 
					                                     OBJECT_NULL, 
													     ARG_LIST_METHOD_CURRENT_WEB_APP);
		}
		return currentWebApp;
	}

	private static Method getCurrentGetBeanMethod() {
		if(currentGetBeanMethod == null){
			currentGetBeanMethod = ReflectionUtils.getMethod(getCurrentWebApp().getClass(), 
															 Constants.GET_BEAN_METHOD_SPRING, 
															 Constants.NIVEL_SUPER_CLASS_GET_BEAN, 
															 PARAMETERS_TYPES_METHOD_BEAN);
		}
		return currentGetBeanMethod;
	}
	
	private static Object getEJBRemote(String nameEJB){
		try {     
            Properties properties = new Properties();
            properties.put(Context.INITIAL_CONTEXT_FACTORY, "org.jnp.interfaces.NamingContextFactory");
            properties.put(Context.URL_PKG_PREFIXES, "org.jboss.naming rg.jnp.interfaces");
            properties.put(Context.PROVIDER_URL, "jnp://localhost:1099");
            properties.put(Context.SECURITY_CREDENTIALS, "");
            properties.put(Context.SECURITY_AUTHENTICATION, "");
            Context ctx = new InitialContext(properties);
            return ctx.lookup(nameEJB);
        }
        catch(NamingException ne) {           
            throw new RuntimeException(ne);
        }
	}
	
}