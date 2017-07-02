package br.com.lif.util;

import br.com.interfaces.ILIFParameter;
import br.com.lif.LIFCore;

import com.google.gson.Gson;

@SuppressWarnings({"unchecked", "rawtypes"})
public class GsonUtils{

	private Gson gson;
	private static GsonUtils gsonUtils;
	
	private GsonUtils(){
		gson = new Gson();
	}
	
	public static GsonUtils getInstance(){
		if (gsonUtils == null) {
			gsonUtils = new GsonUtils();			
		}
		return gsonUtils;
	}
	
	public String objectToJson(ILIFParameter object){
		return gson.toJson(object);
	}	

	public ILIFParameter jsonToObject(String json, Class clazz){
		return gson.fromJson(json, clazz);
	}
	
	public ILIFParameter transformJsonToObjectsArray(String nameBeanParameter, String json){
		return GsonUtils.getInstance().jsonToObject(json, LIFCore.getBeanParameterMethod(nameBeanParameter).getClass());
	}
	
}