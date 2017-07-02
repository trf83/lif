package br.com.lif;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import br.com.lif.exceptions.FormatActionInvalidException;
import br.com.lif.exceptions.ParameterNotInformedException;
import br.com.lif.util.Constants;
import br.com.lif.util.GsonUtils;
import br.com.lif.util.MessageUtils;
import br.com.lif.util.ReflectionUtils;

public class ControllerProcessor{

	public static String executeAction(HttpServletRequest request) {		

		String action = request.getParameter(Constants.ACTION);
		if(action.matches(Constants.VALIDACTION)){
			int indexParethesis  = action.indexOf(Constants.PARENTHESIS);
			String[] nameControlerMethod = action.substring(0, indexParethesis).split(Constants.DOT);
			String nameBeanControler = nameControlerMethod[0];
			String nameMethod = nameControlerMethod[1];
			Object[] parametersMethod = getParametersRequest(request, action, indexParethesis);		
			ParametersResolver parameters = new ParametersResolver(nameBeanControler, nameMethod, parametersMethod);		
			return (String) ReflectionUtils.invokeMethod(parameters.getObjectController(),
														 parameters.getParametersTypesMethod(), 
														 parameters.getParametersMethod(),
														 parameters.getNameMethod());
		}else{
			throw new FormatActionInvalidException(MessageUtils.getMessage(Constants.KEY_MSG_FORMAT_ACTION_INVALID));
		}
	}
	

	public static String executeUpload(HttpServletRequest request) {
		try {
			String action = request.getParameter(Constants.ACTION);
			if(action.matches(Constants.VALIDACTION)){
				int indexParethesis  = action.indexOf(Constants.PARENTHESIS);
				String[] nameControlerMethod = action.substring(0, indexParethesis).split(Constants.DOT);
				String nameBeanControler = nameControlerMethod[0];
				String nameMethod = nameControlerMethod[1];
				ParametersResolver parameters = new ParametersResolver(nameBeanControler, nameMethod, null);	
				return (String) ReflectionUtils.invokeMethod(parameters.getObjectController(),
						 									 new Class[]{Collection.class}, 
						 									 new Object[]{request.getParts()},
															 parameters.getNameMethod());
			}else{
				throw new FormatActionInvalidException(MessageUtils.getMessage(Constants.KEY_MSG_FORMAT_ACTION_INVALID));
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ServletException e) {
			e.printStackTrace();
		}
		return null;
	}

	private static Object[] getParametersRequest(HttpServletRequest request, String action, int indexParethesis) {
		String[] namesBeansParameters = action.substring(indexParethesis + 1, action.length() - 1).split(Constants.COMA);	
		Object[] parametersMethod  = new Object[0];
		if(!(namesBeansParameters.length == 1 && namesBeansParameters[0].equals(""))){
			parametersMethod  = new Object[namesBeansParameters.length];
		}		
		for (int index = 0; index < parametersMethod.length; index++) {
			if(request.getParameter(namesBeansParameters[index]) != null){
				parametersMethod[index] = GsonUtils.getInstance().transformJsonToObjectsArray(namesBeansParameters[index], request.getParameter(namesBeansParameters[index]));
			}else{				
				throw new ParameterNotInformedException(MessageUtils.getMessage(Constants.KEY_MSG_PARAMETER_NOT_INFORMED, namesBeansParameters[index]));
			}
		}
		return parametersMethod;
	}

}