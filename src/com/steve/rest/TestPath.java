package com.steve.rest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;

import com.steve.util.JsonSendUtil;

import net.sf.json.JSONObject;

@Path("/test")
public class TestPath {
	@POST
	public void watsonRec(@Context HttpServletRequest request, @Context HttpServletResponse response) {
//		JSONObject itemObject = new JSONObject();
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(request.getInputStream(), "utf-8"));
			String payload = "";
			String str = "";
			while ((str = reader.readLine()) != null) {
				payload += str;
			}
			System.out.println("POST data=" + payload);
			JSONObject pl = JSONObject.fromObject(payload);
			
//			itemObject.put("success", "Test success message.");
			JsonSendUtil.sendJsonData("success", "Test success message.", request, response);
		} catch (IOException ioe) {
			ioe.printStackTrace();
			System.out.println("IOException happened in TestPath.");
			try {
				JsonSendUtil.sendJsonData("error", "Test fail message.", request, response);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} finally {
			
		}
		
	}
}
