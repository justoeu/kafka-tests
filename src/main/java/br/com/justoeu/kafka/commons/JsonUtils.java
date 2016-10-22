package br.com.justoeu.kafka.commons;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.lang.reflect.Type;

public class JsonUtils<T> {

	public static String toJson(Object obj){
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		return gson.toJson(obj);
	}
	
	public T toObject(String json, Type objType){
		Gson gson = new GsonBuilder().create();
		return gson.fromJson(json, objType);
	}

}