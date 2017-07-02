package br.com.lif;

import br.com.interfaces.ILIFController;

@SuppressWarnings("rawtypes")
public class ParametersResolver{

	private String nameController;
	private String nameMethod;
	private Object[] parametersMethod;
	private Class[] parametersTypesMethod;
	private String[] namesBeansConstruct;
	
	public ParametersResolver(String nameController, String nameMethod, Object[] parametersMethod){
		this.nameController = nameController;
		this.nameMethod = nameMethod;
		this.parametersMethod = parametersMethod;
	}	

	public String getNameMethod() {
		return nameMethod;
	}

	public Object[] getParametersMethod() {
		return parametersMethod;
	}
	
	public Class[] getParametersTypesMethod() {	
		parametersTypesMethod = transformStringNameClassForClass(parametersMethod);
		return parametersTypesMethod;
	}	
	
	public String[] getNamesBeansConstruct() {
		return namesBeansConstruct;
	}
	
	private Class[] transformStringNameClassForClass(Object parameters[]){
		int length = parameters.length;
		Class[] parametersTypes = new Class[length];
		for (int index = 0; index < length; index++) { 
			parametersTypes[index] = parameters[index].getClass();
		}
		return parametersTypes;
	}

	public ILIFController getObjectController() {
		return LIFCore.getBeanController(nameController);
	}
}
