/**
 * 
 */
package it.tsc.util;

import java.util.List;

import com.datastax.driver.core.Row;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

/**
 * @author astraservice
 *
 */
public class ConversionUtil {

	/**
	 * 
	 */
	public ConversionUtil() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * return array of JSON object
	 * 
	 * @param rows
	 * @return
	 */
	public static String returnJson(List<Row> rows) {
		JsonArray array = new JsonArray();
		for (Row row : rows) {
			JsonObject json = (JsonObject) new JsonParser().parse(row.getString("[json]"));
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
