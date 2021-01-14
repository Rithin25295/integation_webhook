package com.indrasol.supervue.supervuewebhook.util;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class ObjectConversion {
	
	public JsonObject objecToJson(Object obj) {
		Gson gson = new Gson();
		String json = gson.toJson(obj);
        JsonObject jsonResponse = gson.fromJson(json,JsonObject.class);
        return jsonResponse;
	}
}
