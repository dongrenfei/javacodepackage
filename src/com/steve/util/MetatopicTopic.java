package com.steve.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * return JSONArray searched by url
 * 
 * */
public class MetatopicTopic {
	@SuppressWarnings("unchecked")
	public static JSONObject find(String url, JSONArray mt) {
		JSONObject result = new JSONObject();
		List<String> topic = new ArrayList<String>();
		List<String> score = new ArrayList<String>();
		String metaortopic = "";
//		System.out.println("url="+url);
		try {
			if(StringUtils.isNotEmpty(url)) {
				
				if(mt==null || mt.isEmpty()) {
					return null;
				} else {
					Iterator<JSONObject> it = mt.iterator();
					while(it.hasNext()) {
						JSONObject jo = it.next().getJSONObject("Data").getJSONArray("ResultSet").getJSONObject(0);
						metaortopic = jo.getString("ObjectName");
						if(jo.containsKey("Result")) {
							JSONArray j = jo.getJSONArray("Result");
							
							if(j==null || j.isEmpty()) {
								continue;
							} else {
								if(j.getJSONObject(0).containsValue(url)) {
									for(int i=0; i<j.size(); i++) {
										JSONObject r = j.getJSONObject(i);
										if(metaortopic.equals("metatopic")) {// metatopic
											topic.add(r.getString("MetaTopic"));
										} else {// topic
											topic.add(r.getString("Topic"));
										}
										score.add(r.getString("Score"));
									}
									break;
								}
							}
						}
						
					}
					
					if(metaortopic.equals("metatopic")) {// metatopic
//						result.put("disqus_metatopics", topic.toString().replaceAll("[\\[\\]]", ""));
//						result.put("disqus_metatopics_scores", score.toString().replaceAll("[\\[\\]]", ""));
						result.put("disqus_metatopics", topic.toArray());
						result.put("disqus_metatopics_scores", score.toArray());
					} else {// topic
//						result.put("disqus_topics", topic.toString().replaceAll("[\\[\\]]", ""));
//						result.put("disqus_topics_scores", score.toString().replaceAll("[\\[\\]]", ""));
						result.put("disqus_topics", topic.toArray());
						result.put("disqus_topics_scores", score.toArray());
					}
					
				}
				
			} else {
				return null;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
}
