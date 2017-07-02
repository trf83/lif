package br.com.lif.util;

public class Constants {

	public static final String KEY_MSG_FORMAT_ACTION_INVALID = "format_action_invalid";
	public static final String KEY_MSG_PARAMETER_NOT_INFORMED = "parameter_not_informed";
	public static final String KEY_MSG_METHOD_NOT_DECLARED = "method_not_declared";
	
	public static final String PARENTHESIS = "(";
	public static final String DOT = "\\.";
	public static final String COMA = ",";
	public static final String ACTION = "action";
	public static final String VALIDACTION = "\\w+\\.\\w+\\(\\w*([,][\\w+]+)*\\)";
	
	public static final String RESOURCES_MESSAGES_PT_BR = "br.com.lif.resources.Messages_pt_BR";
	public static final String RESOURCES_MESSAGES = "br.com.lif.resources.messages";
	
	public static final String CTX_LOADER_NAME_CLASS_SPRING = "org.springframework.web.context.ContextLoaderListener";
	public static final String GET_CURRENT_WEBAPPLICATION_METHOD_SPRING = "getCurrentWebApplicationContext";
	public static final String GET_BEAN_METHOD_SPRING = "getBean";	
	public static final int NIVEL_SUPER_CLASS_CURRENT_WEB_APP = 1;
	public static final int NIVEL_SUPER_CLASS_GET_BEAN = 4;
	
	public static final String CTX_LOADER_NAME_CLASS_EJB = "javax.naming.InitialContext";
	public static final String GET_BEAN_METHOD_EJB = "lookup";


}
