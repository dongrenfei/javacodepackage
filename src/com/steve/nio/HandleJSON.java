package com.steve.nio;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import com.steve.util.MetatopicTopic;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class HandleJSON implements FileHandleBehavior {
	private String afileFullPath;
	private String bfileFullPath;
	private String cfileFullPath;

	public HandleJSON(String a, String b, String c) {
		this.afileFullPath = a;
		this.bfileFullPath = b;
		this.cfileFullPath = c;
	}

	@Override
	public String handle() {
		StringBuffer sbf = null;

		try {
			// processedAsset
			BufferedReader br = new BufferedReader(new FileReader(new File(this.afileFullPath)));
			String temp = null;
			sbf = new StringBuffer();

			while ((temp = br.readLine()) != null) {
				sbf.append(temp);
			}

			br.close();

			JSONObject inJson = JSONObject.fromObject(sbf.toString());
			JSONArray ja = inJson.getJSONArray("assets");
			JSONObject it = null;

			// cloudant
			br = new BufferedReader(new FileReader(new File(this.bfileFullPath)));
			temp = null;
			sbf = new StringBuffer();
			while ((temp = br.readLine()) != null) {
				sbf.append(temp);
			}

			br.close();

			JSONObject cldJSON = JSONObject.fromObject(sbf.toString());

			for (int i = 0; i < ja.size(); i++) {
				it = ja.getJSONObject(i);
				String url = it.getString("EXTERNAL_SDA_LINK");

				JSONObject mt = MetatopicTopic.find(url, cldJSON.getJSONArray("metatopic"));
				JSONObject tp = MetatopicTopic.find(url, cldJSON.getJSONArray("topic"));
				if (mt != null && !mt.isEmpty()) {// System.out.println(url);
					it.put("DISQUS_METATOPICS", mt.getString("disqus_metatopics"));
					it.put("DISQUS_METATOPICS_SCORES", mt.getString("disqus_metatopics_scores"));
				}

				if (tp != null && !tp.isEmpty()) {// System.out.println(url);
					it.put("DISQUS_TOPICS", tp.getString("disqus_topics"));
					it.put("DISQUS_TOPICS_SCORES", tp.getString("disqus_topics_scores"));
				}

				ja.set(i, it);
			}

			inJson.put("assets", ja);

			// generate mixed json file
			BufferedWriter bw = new BufferedWriter(new FileWriter(new File(this.cfileFullPath)));
			temp = null;
			bw.write(inJson.toString());
			bw.close();
			System.out.println("JSON generated.");

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return afileFullPath;

	}

}
