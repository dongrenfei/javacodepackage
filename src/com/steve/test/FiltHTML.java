package com.steve.test;

import com.steve.util.ServicelinkEncoding;
import com.steve.util.XSSFilter;

public class FiltHTML {

	public static void main(String[] args) {
		String html = "1000\" onmouseover=prompt(1) bad=\"";
		XSSFilter filter = new XSSFilter();
		ServicelinkEncoding se = new ServicelinkEncoding();
		
		try {
			String res = filter.doFilter(html);
			System.out.println(res);
		} catch (Exception e) {
			e.printStackTrace();
		}
		String enstr = se.encodeForHTML(html);
		System.out.println("enstr="+enstr);
		String destr = se.decodeForHTML(enstr);
		System.out.println("destr="+destr);
	}

}
