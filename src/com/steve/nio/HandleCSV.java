package com.steve.nio;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.steve.util.CSVUtils;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class HandleCSV implements FileHandleBehavior {
	private String afileFullPath;
	private String cfilePath;
	private String cfileName;

	public HandleCSV(String a, String cpath, String cname) {
		this.afileFullPath = a;
		this.cfilePath = cpath;
		this.cfileName = cname;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public String handle() {

		try {
			BufferedReader br = new BufferedReader(new FileReader(new File(this.afileFullPath)));
			String temp = null;
			StringBuffer sbf = new StringBuffer();

			while ((temp = br.readLine()) != null) {
				sbf.append(temp);
			}

			br.close();
			
			JSONObject inJson = JSONObject.fromObject(sbf.toString());
			
			// generate out.csv
			JSONObject json = new JSONObject();
			List exportData = new ArrayList<Map>();
			Map row = null;
			JSONArray assetArr = inJson.getJSONArray("assets");

			for (Iterator aait = assetArr.iterator(); aait.hasNext();) {
				json = JSONObject.fromObject(aait.next());

				row = new LinkedHashMap<String, String>();
				row.put("EXTERNAL_SDA_LINK", json.getString("EXTERNAL_SDA_LINK"));
				row.put("ECC-id", json.getString("ECC-id"));
				row.put("DOCID", json.getString("DOCID"));
				row.put("ABSTRACT", CSVUtils.appendDQ(json.getString("ABSTRACT").replaceAll("\"", "")));
				row.put("HAS_BEEN_DELETED", json.getString("HAS_BEEN_DELETED"));
				row.put("CLIENT_INDUSTRY", json.getString("CLIENT_INDUSTRY"));
				row.put("ALCHEMY_CONCEPTS", CSVUtils.appendDQ(
						json.get("ALCHEMY_CONCEPTS").toString().replaceAll("\"", "").replaceAll("[\\[\\]]", "")));
				row.put("LANGUAGE_SPECIFIC_TITLE", CSVUtils.appendDQ(json.getString("LANGUAGE_SPECIFIC_TITLE")));
				row.put("LAST_SIGNIFICANT_UPDATE_DATE", json.getString("LAST_SIGNIFICANT_UPDATE_DATE"));
				row.put("IMAGE_URL", json.getString("IMAGE_URL"));
				row.put("MINOR_BRAND", json.getString("MINOR_BRAND"));
				row.put("BRANDS", json.getString("BRANDS"));
				row.put("KEYWORDS",
						CSVUtils.appendDQ(json.getString("KEYWORDS").replaceAll("[\\[\\]]", "").replaceAll("\"", "")));
				row.put("DOCUMENT_TYPE", json.getString("DOCUMENT_TYPE"));
				row.put("PRODUCT_PAGE_NAME", json.getString("PRODUCT_PAGE_NAME"));
				row.put("MAJOR_BRAND", json.getString("MAJOR_BRAND"));
				row.put("ALCHEMY_CONCEPTS_SCORES", CSVUtils.appendDQ(json.get("ALCHEMY_CONCEPTS_SCORES").toString()
						.replaceAll("\"", "").replaceAll("[\\[\\]]", "")));

				row.put("DISQUS_METATOPICS", CSVUtils
						.appendDQ(json.getString("DISQUS_METATOPICS").replaceAll("\"", "").replaceAll("[\\[\\]]", "")));
				row.put("DISQUS_METATOPICS_SCORES", CSVUtils.appendDQ(
						json.getString("DISQUS_METATOPICS_SCORES").replaceAll("\"", "").replaceAll("[\\[\\]]", "")));
				row.put("DISQUS_TOPICS", CSVUtils
						.appendDQ(json.getString("DISQUS_TOPICS").replaceAll("\"", "").replaceAll("[\\[\\]]", "")));
				row.put("DISQUS_TOPICS_SCORES", CSVUtils.appendDQ(
						json.getString("DISQUS_TOPICS_SCORES").replaceAll("\"", "").replaceAll("[\\[\\]]", "")));

				exportData.add(row);
			}

			// csv header, keep it empty or null if no header
			LinkedHashMap map = new LinkedHashMap();
			map.put("EXTERNAL_SDA_LINK", "EXTERNAL_SDA_LINK");
			map.put("ECC-id", "ECC-id");
			map.put("DOCID", "DOCID");
			map.put("ABSTRACT", "ABSTRACT");
			map.put("HAS_BEEN_DELETED", "HAS_BEEN_DELETED");
			map.put("CLIENT_INDUSTRY", "CLIENT_INDUSTRY");
			map.put("ALCHEMY_CONCEPTS", "ALCHEMY_CONCEPTS");
			map.put("LANGUAGE_SPECIFIC_TITLE", "LANGUAGE_SPECIFIC_TITLE");
			map.put("LAST_SIGNIFICANT_UPDATE_DATE", "LAST_SIGNIFICANT_UPDATE_DATE");
			map.put("IMAGE_URL", "IMAGE_URL");
			map.put("MINOR_BRAND", "MINOR_BRAND");
			map.put("BRANDS", "BRANDS");
			map.put("KEYWORDS", "KEYWORDS");
			map.put("DOCUMENT_TYPE", "DOCUMENT_TYPE");
			map.put("PRODUCT_PAGE_NAME", "PRODUCT_PAGE_NAME");
			map.put("MAJOR_BRAND", "MAJOR_BRAND");
			map.put("ALCHEMY_CONCEPTS_SCORES", "ALCHEMY_CONCEPTS_SCORES");
			map.put("DISQUS_METATOPICS", "DISQUS_METATOPICS");
			map.put("DISQUS_METATOPICS_SCORES", "DISQUS_METATOPICS_SCORES");
			map.put("DISQUS_TOPICS", "DISQUS_TOPICS");
			map.put("DISQUS_TOPICS_SCORES", "DISQUS_TOPICS_SCORES");

			CSVUtils.createCSVFile(exportData, map, this.cfilePath, this.cfileName);

			System.out.println("CSV generated.");

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return this.cfilePath+this.cfileName;
	}

}
