package com.steve.test;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.MissingResourceException;

import com.steve.db.DBConUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class Test {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		
//		try {
//			DBConUtil.getDB2Conn();
//		} catch (MissingResourceException mre) {
//			mre.printStackTrace();
//			System.out.println("No such property key.");
//		} catch (ClassNotFoundException cnfe) {
//			cnfe.printStackTrace();
//			System.out.println("DB driver class not found.");
//		} catch (SQLException sqle) {
//			sqle.printStackTrace();
//			System.out.println("DB connect failed.");
//		} catch (Exception e) {
//			e.printStackTrace();
//			System.out.println("other exception.");
//		}
		
		String str = "{ \"assets\" : [{\"title\":\"t1\", \"categories\" : [588, 628]}, {\"title\":\"t2\",\"categories\" : [588, 628]}]}";
		String str2 = "{\"Data\":{\"ResultSet\":[{\"ObjectName\":\"topic\"}]},\"Return\":{\"Status\":3}}";
		JSONObject j2 = JSONObject.fromObject(str2);
		JSONObject jo2 = j2.getJSONObject("Data").getJSONArray("ResultSet").getJSONObject(0);
		System.out.println("result===" + jo2.containsKey("Result"));
		JSONObject assets = JSONObject.fromObject(str);
		JSONArray ja = assets.getJSONArray("assets");
		Iterator<JSONObject> it = ja.iterator();
		JSONObject jo = JSONObject.fromObject(ja.get(0));
		ja = JSONArray.fromObject(jo.get("categories"));
		// int i = (int)ja.get(0);
		String result = null;

		// System.out.println(i==588);
		while (it.hasNext()) {
			JSONObject j = it.next();
			System.out.println(j);
			if (j.containsValue(588)) {
				result = "found it";
				break;
			}
		}

		System.out.println(result);
		List<String> list = new ArrayList<String>();
//		list.add("l1");
//		list.add("l2");
//		list.add("l3");
		System.out.println("list==="+list.toString().replaceAll("[\\[\\]]", ""));
		String str0123 = "0123";
		System.out.println(str0123.substring(0,1));
	}

}
