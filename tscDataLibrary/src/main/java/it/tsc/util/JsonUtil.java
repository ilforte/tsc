/**
 * 
 */
package it.tsc.util;

import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

/**
 * @author astraservice (JSON conversion utils)
 */
public class JsonUtil {

	/**
	 * 
	 */
	public JsonUtil() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * return array of JSON object
	 * 
	 * @param rows
	 * @return
	 */
	public static String returnJson(List<?> rows) {
		JsonArray array = new JsonArray();
		for (Object row : rows) {
			JsonObject json = (JsonObject) new JsonParser().parse(new Gson().toJson(row));
			array.add(json);
		}
		return array.toString();
	}

	/**
	 * return gson converter
	 * 
	 * @return
	 */
	public static Gson getGsonConverter() {
		Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
		return gson;
	}

}
