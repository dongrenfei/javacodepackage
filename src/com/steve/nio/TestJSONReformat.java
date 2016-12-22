package com.steve.nio;

public class TestJSONReformat {

	public static void main(String[] args) {
		String path = "C:\\Users\\IBM_ADMIN\\Desktop\\ThirdpartyData\\metatopic-2016-12-15\\";
		String input = path + "Articles2016-12-15.metatopics.json";
		String otput = path + "Articles2016-12-15.metatopics.new.json";
		FileHandleBehavior js = new JsonFormat(input, otput);
		js.handle();
		
		input = path + "Articles2016-12-15.topics.json";
		otput = path + "Articles2016-12-15.topics.new.json";
		js = new JsonFormat(input, otput);
		js.handle();
		
		input = path + "Products2016-12-15.metatopics.json";
		otput = path + "Products2016-12-15.metatopics.new.json";
		js = new JsonFormat(input, otput);
		js.handle();
		
		input = path + "Products2016-12-15.topics.json";
		otput = path + "Products2016-12-15.topics.new.json";
		js = new JsonFormat(input, otput);
		js.handle();
		
		// combine
		String afile = path + "Articles2016-12-15.metatopics.new.json";
		String bfile = path + "Articles2016-12-15.topics.new.json";
		String cfile = path + "articles-2016-12-15.json";
		FileHandleBehavior combine = new CombineJSON(afile, bfile, cfile);
		combine.handle();
		
		afile = path + "Products2016-12-15.metatopics.new.json";
		bfile = path + "Products2016-12-15.topics.new.json";
		cfile = path + "products-2016-12-15.json";
		combine = new CombineJSON(afile, bfile, cfile);
		combine.handle();
		
	}

}
