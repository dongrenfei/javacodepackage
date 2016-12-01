package com.steve.nio;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;

import org.apache.commons.lang.StringUtils;

import com.steve.util.DashboardData;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class HandleJSON implements FileHandleBehavior {
	private String inPath;
	private JSONObject cloudant;
	
	public HandleJSON(String in) {
		this.inPath = in;
	}
	
	@Override
	public String handle() {
		StringBuffer sbf = null;
		String result = "";
		
		try {
			DashboardData db = new DashboardData();
			String cloudant = db.getDocById("meta_and_topic", "metatopicandtopic");
			this.cloudant = JSONObject.fromObject(cloudant);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			BufferedReader br = new BufferedReader(new FileReader(new File(this.inPath)));
			String temp = null;
			sbf = new StringBuffer();

			while ((temp = br.readLine()) != null) {
				sbf.append(temp);
			}

			br.close();
			
			JSONObject inJson = JSONObject.fromObject(sbf.toString());
			JSONArray ja = inJson.getJSONArray("assets");
			JSONObject it = null;
			for(int i=0; i<ja.size(); i++) {
				it = ja.getJSONObject(i);
				String url = it.getString("EXTERNAL_SDA_LINK");
				
				JSONArray mt = findMetatopicTopic(url, 0);
				JSONArray tp = findMetatopicTopic(url, 1);
				if(mt!=null && !mt.isEmpty()) {
					it.put("metatopic", mt);
				}
				
				if(tp!=null && !tp.isEmpty()) {
					it.put("topic", tp);
				}
				
				ja.set(i, it);
			}
			
			inJson.put("assets", ja);
			result = inJson.toString();
			System.out.println("handle json done.");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}

	/**
	 * find metatopic or topic json array by url
	 * @param url
	 * @param flag, 0 is metatopic and 1 is topic
	 * */
	@SuppressWarnings("unchecked")
	public JSONArray findMetatopicTopic(String url, int flag) {
		JSONArray mt = null;
		JSONArray rs = null;
		
		try {
			if(StringUtils.isNotEmpty(url)) {
				if(flag==0) {
					mt = this.cloudant.getJSONArray("metatopic");
				} else {
					mt = this.cloudant.getJSONArray("topic");
				}
				
				if(mt==null || mt.isEmpty()) {
					return null;
				} else {
					Iterator<JSONObject> it = mt.iterator();
					while(it.hasNext()) {
						JSONObject jo = it.next().getJSONObject("Data").getJSONArray("ResultSet").getJSONObject(0);
						if(jo.containsKey("Result")) {
							JSONArray j = jo.getJSONArray("Result");
							if(j==null || j.isEmpty()) {
								continue;
							} else {
								if(j.getJSONObject(0).containsValue(url)) {
									rs = j;
									break;
								}
							}
						}
						
					}
					
				}
				
			} else {
				return null;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return rs;
		
	}
	
}
