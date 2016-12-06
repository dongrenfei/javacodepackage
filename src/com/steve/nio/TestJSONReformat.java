package com.steve.nio;

public class TestJSONReformat {

	public static void main(String[] args) {
		String path = "C:\\Users\\IBM_ADMIN\\Desktop\\ThirdpartyData\\metatopic-2016-12-5\\";
		String input = path + "Articles2016-12-05.metatopics.json";
		String otput = path + "Articles2016-12-05.metatopics.new.json";
		FileHandleBehavior js = new JsonFormat(input, otput);
		js.handle();
		
		input = path + "Articles2016-12-05.topics.json";
		otput = path + "Articles2016-12-05.topics.new.json";
		js = new JsonFormat(input, otput);
		js.handle();
		
		input = path + "Products2016-12-05.metatopics.json";
		otput = path + "Products2016-12-05.metatopics.new.json";
		js = new JsonFormat(input, otput);
		js.handle();
		
		input = path + "Products2016-12-05.topics.json";
		otput = path + "Products2016-12-05.topics.new.json";
		js = new JsonFormat(input, otput);
		js.handle();
		
		// combine
		String afile = path + "Articles2016-12-05.metatopics.new.json";
		String bfile = path + "Articles2016-12-05.topics.new.json";
		String cfile = path + "articles-cloudant-2016-12-5.json";
		FileHandleBehavior combine = new CombineJSON(afile, bfile, cfile);
		combine.handle();
		
		afile = path + "Products2016-12-05.metatopics.new.json";
		bfile = path + "Products2016-12-05.topics.new.json";
		cfile = path + "products-cloudant-2016-12-5.json";
		combine = new CombineJSON(afile, bfile, cfile);
		combine.handle();
		
	}

}
