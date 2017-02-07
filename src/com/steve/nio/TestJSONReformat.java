package com.steve.nio;

import java.util.ArrayList;
import java.util.List;

import com.steve.util.CSVUtils;

/**
 * combine metatopics and topics data to one json file
 * 
 * */
public class TestJSONReformat {

	public static void main(String[] args) {
		// two ArrayList to store files to combine
		List<String> metatopic = new ArrayList<String>();
		List<String> topic = new ArrayList<String>();
				
		String path = "C:\\Users\\IBM_ADMIN\\Desktop\\ThirdpartyData\\metatopic-2017-01-10\\";
		String fileName = "Articles2017-01-10.metatopics";
		String suffix = ".json";
		String tmp = "-tmp";
		String input = path + fileName + suffix;
		String otput = path + fileName + tmp + suffix;
		metatopic.add(otput);
		
		FileHandleBehavior js = new JsonFormat(input, otput);
		js.handle();
		
		fileName = "Articles2017-01-10.topics";
		input = path + fileName + suffix;
		otput = path + fileName + tmp + suffix;
		topic.add(otput);
		js = new JsonFormat(input, otput);
		js.handle();
		
		fileName = "Products2017-01-10.metatopics";
		input = path + fileName + suffix;
		otput = path + fileName + tmp + suffix;
		metatopic.add(otput);
		js = new JsonFormat(input, otput);
		js.handle();
		
		fileName = "Products2017-01-10.topics";
		input = path + fileName + suffix;
		otput = path + fileName + tmp + suffix;
		metatopic.add(otput);
		topic.add(otput);
		js = new JsonFormat(input, otput);
		js.handle();
		
		// combine
		String afile = metatopic.get(0);
		String bfile = topic.get(0);
		String cfile = path + "articles-2017-01-10.json";
		FileHandleBehavior combine = new CombineJSON(afile, bfile, cfile);
		combine.handle();
		
		afile = metatopic.get(1);
		bfile = topic.get(1);
		cfile = path + "products-2017-01-10.json";
		combine = new CombineJSON(afile, bfile, cfile);
		combine.handle();
		
		// delete all temp files
		CSVUtils.deleteFileByCondition(path, tmp);
	}

}
