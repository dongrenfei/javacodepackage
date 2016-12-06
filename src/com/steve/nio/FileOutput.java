package com.steve.nio;

public class FileOutput {

	public static void main(String[] args) {
		String path = "C:\\Users\\IBM_ADMIN\\Desktop\\ThirdpartyData\\assets-2016-12-05\\";
		String infile = path + "NHMprocessedAssetsFile_2016-12-05.json";
		String otfile = path + "NHM-product.json";
		String csvname = "NHM-product.csv";
		// products-cloudant-2016-12-5.json articles-cloudant-2016-12-5
		String cloudant = "C:\\Users\\IBM_ADMIN\\Desktop\\ThirdpartyData\\metatopic-2016-12-5\\products-cloudant-2016-12-5.json";
		
		// output JSON
		FileHandleBehavior fhb = new HandleJSON(infile, cloudant, otfile);
		fhb.handle();
		
		// output CSV
		fhb = new HandleCSV(otfile, path, csvname);
		fhb.handle();
		
		infile = path + "MKPprocessedAssetsFile_2016-12-05.json";
		otfile = path + "MKP-article.json";
		csvname = "MKP-article.csv";
		cloudant = "C:\\Users\\IBM_ADMIN\\Desktop\\ThirdpartyData\\metatopic-2016-12-5\\articles-cloudant-2016-12-5.json";
		
		// output JSON
		fhb = new HandleJSON(infile, cloudant, otfile);
		fhb.handle();
		
		// output CSV
		fhb = new HandleCSV(otfile, path, csvname);
		fhb.handle();
		
	}

}
