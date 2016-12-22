package com.steve.nio;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.steve.util.CSVUtils;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class JSONtoCSV {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void main(String[] args) {
		String csvPath = "C:\\Users\\IBM_ADMIN\\Desktop\\ThirdpartyData\\jsontocsv\\";
		String csvName = "Products 2016-12-05.csv";
		String jsonFile = csvPath + "Products 2016-12-05.txt";
		StringBuffer sbf = null;
		Map<String, String> fieldMap = new HashMap<String, String>();
		fieldMap.put("uuid", "uuid");
		fieldMap.put("productId", "productId");
		fieldMap.put("url", "url");
		fieldMap.put("name", "name");
		fieldMap.put("icon", "icon");
		fieldMap.put("naturalLanguageName", "naturalLanguageName");
		fieldMap.put("productPageName", "productPageName");
		fieldMap.put("overview", "overview");
		fieldMap.put("description", "description");
		fieldMap.put("longDescription", "longDescription");
		fieldMap.put("profileImageUrl", "profileImageUrl");
		fieldMap.put("demoUrl", "demoUrl");
		fieldMap.put("screenshotUrl", "screenshotUrl");
		fieldMap.put("category", "category");
		fieldMap.put("keywords", "keywords");
		fieldMap.put("serviceType", "serviceType");
		fieldMap.put("support", "support");
		fieldMap.put("provider", "provider");
		fieldMap.put("benefits", "benefits");
		fieldMap.put("resources", "resources");
		fieldMap.put("publishedOn", "publishedOn");
		fieldMap.put("lastModified", "lastModified");
		fieldMap.put("purchasePlans", "purchasePlans");
		fieldMap.put("topics", "topics");
		fieldMap.put("availableForLocales", "availableForLocales");
		
		try {
			BufferedReader br = new BufferedReader(new FileReader(new File(jsonFile)));
//			br = new BufferedReader(new InputStreamReader(new FileInputStream(jsonFile), "UTF-8"));
			String temp = null;
			sbf = new StringBuffer();

			while ((temp = br.readLine()) != null) {
				sbf.append(temp);
			}

			br.close();
			
			JSONObject jsonsrc = JSONObject.fromObject(sbf.toString());
			JSONArray data = jsonsrc.getJSONArray("data");
			
			List exportData = new ArrayList<Map>();
			Map row = null;
			JSONObject jo = null;
			int cnt = 0;
			for (Iterator dit = data.iterator(); dit.hasNext();) {
				cnt++;
				System.out.println("cnt="+cnt);
				jo = JSONObject.fromObject(dit.next());
				row = new LinkedHashMap<String, String>();
				row.put(fieldMap.get("uuid"), jo.getString(fieldMap.get("uuid")));
				if(jo.containsKey(fieldMap.get("productId"))) {
					row.put(fieldMap.get("productId"), jo.getString(fieldMap.get("productId")));
				} else {
					row.put(fieldMap.get("productId"), "");
				}
				row.put(fieldMap.get("url"), jo.getString(fieldMap.get("url")));
				row.put(fieldMap.get("name"), jo.getString(fieldMap.get("name")));
				if(jo.containsKey(fieldMap.get("icon"))) {
					row.put(fieldMap.get("icon"), jo.getString(fieldMap.get("icon")));
				} else {
					row.put(fieldMap.get("icon"), "");
				}
				row.put(fieldMap.get("naturalLanguageName"), jo.getString(fieldMap.get("naturalLanguageName")));
				row.put(fieldMap.get("productPageName"), jo.getString(fieldMap.get("productPageName")));
				if(jo.containsKey(fieldMap.get("overview"))) {
					row.put(fieldMap.get("overview"), CSVUtils.appendDQ(jo.getString(fieldMap.get("overview")).replaceAll("\"", "")));
				} else {
					row.put(fieldMap.get("overview"), "");
				}
				row.put(fieldMap.get("description"), CSVUtils.appendDQ(jo.getString(fieldMap.get("description")).replaceAll("\"", "")));
				row.put(fieldMap.get("longDescription"), CSVUtils.appendDQ(jo.getString(fieldMap.get("longDescription")).replaceAll("\"", "")));
				if(jo.containsKey(fieldMap.get("profileImageUrl"))) {
					row.put(fieldMap.get("profileImageUrl"), jo.getString(fieldMap.get("profileImageUrl")));
				} else {
					row.put(fieldMap.get("profileImageUrl"), "");
				}
				if(jo.containsKey(fieldMap.get("demoUrl"))) {
					row.put(fieldMap.get("demoUrl"), jo.getString(fieldMap.get("demoUrl")));
				} else {
					row.put(fieldMap.get("demoUrl"), "");
				}
				if(jo.containsKey(fieldMap.get("screenshotUrl"))) {
					row.put(fieldMap.get("screenshotUrl"), jo.getString(fieldMap.get("screenshotUrl")));
				} else {
					row.put(fieldMap.get("screenshotUrl"), "");
				}
				row.put(fieldMap.get("category"), CSVUtils.appendDQ(jo.getJSONArray(fieldMap.get("category")).toString().replaceAll("\"", "")));
				row.put(fieldMap.get("keywords"), CSVUtils.appendDQ(jo.getJSONArray(fieldMap.get("keywords")).toString().replaceAll("\"", "")));
				if(jo.containsKey(fieldMap.get("serviceType"))) {
					row.put(fieldMap.get("serviceType"), jo.getString(fieldMap.get("serviceType")));
				} else {
					row.put(fieldMap.get("serviceType"), "");
				}
				row.put(fieldMap.get("support"), CSVUtils.appendDQ(jo.getJSONObject(fieldMap.get("support")).toString().replaceAll("\"", "")));
				row.put(fieldMap.get("provider"), CSVUtils.appendDQ(jo.getJSONObject(fieldMap.get("provider")).toString().replaceAll("\"", "")));
				row.put(fieldMap.get("benefits"), CSVUtils.appendDQ(jo.getJSONArray(fieldMap.get("benefits")).toString().replaceAll("\"", "")));
				row.put(fieldMap.get("resources"), CSVUtils.appendDQ(jo.getJSONArray(fieldMap.get("resources")).toString().replaceAll("\"", "")));
				if(jo.containsKey(fieldMap.get("publishedOn"))) {
					row.put(fieldMap.get("publishedOn"), jo.getString(fieldMap.get("publishedOn")));
				} else {
					row.put(fieldMap.get("publishedOn"),"");
				}
				row.put(fieldMap.get("lastModified"), jo.getString(fieldMap.get("lastModified")));
				row.put(fieldMap.get("purchasePlans"), CSVUtils.appendDQ(jo.getJSONArray(fieldMap.get("purchasePlans")).toString().replaceAll("\"", "")));
				if(jo.containsKey(fieldMap.get("topics"))) {
					row.put(fieldMap.get("topics"), CSVUtils.appendDQ(jo.getJSONArray(fieldMap.get("topics")).toString().replaceAll("\"", "")));
				} else {
					row.put(fieldMap.get("topics"), "");
				}
				row.put(fieldMap.get("availableForLocales"), CSVUtils.appendDQ(jo.getJSONArray(fieldMap.get("availableForLocales")).toString().replaceAll("\"", "")));
				
				exportData.add(row);
			}
			
			// csv header, keep it empty or null if no header
			LinkedHashMap map = new LinkedHashMap();
			map.put(fieldMap.get("uuid"), fieldMap.get("uuid"));
			map.put(fieldMap.get("productId"), fieldMap.get("productId"));
			map.put(fieldMap.get("url"), fieldMap.get("url"));
			map.put(fieldMap.get("name"), fieldMap.get("name"));
			map.put(fieldMap.get("icon"), fieldMap.get("icon"));
			map.put(fieldMap.get("naturalLanguageName"), fieldMap.get("naturalLanguageName"));
			map.put(fieldMap.get("productPageName"), fieldMap.get("productPageName"));
			map.put(fieldMap.get("overview"), fieldMap.get("overview"));
			map.put(fieldMap.get("description"), fieldMap.get("description"));
			map.put(fieldMap.get("longDescription"), fieldMap.get("longDescription"));
			map.put(fieldMap.get("profileImageUrl"), fieldMap.get("profileImageUrl"));
			map.put(fieldMap.get("demoUrl"), fieldMap.get("demoUrl"));
			map.put(fieldMap.get("screenshotUrl"), fieldMap.get("screenshotUrl"));
			map.put(fieldMap.get("category"), fieldMap.get("category"));
			map.put(fieldMap.get("keywords"), fieldMap.get("keywords"));
			map.put(fieldMap.get("serviceType"), fieldMap.get("serviceType"));
			map.put(fieldMap.get("support"), fieldMap.get("support"));
			map.put(fieldMap.get("provider"), fieldMap.get("provider"));
			map.put(fieldMap.get("benefits"), fieldMap.get("benefits"));
			map.put(fieldMap.get("resources"), fieldMap.get("resources"));
			map.put(fieldMap.get("publishedOn"), fieldMap.get("publishedOn"));
			map.put(fieldMap.get("lastModified"), fieldMap.get("lastModified"));
			map.put(fieldMap.get("purchasePlans"), fieldMap.get("purchasePlans"));
			map.put(fieldMap.get("topics"), fieldMap.get("topics"));
			map.put(fieldMap.get("availableForLocales"), fieldMap.get("availableForLocales"));

			CSVUtils.createCSVFile(exportData, map, csvPath, csvName);

			System.out.println("CSV generated.");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
