package com.steve.resource;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Context;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@WebServlet(name = "UploadServlet", urlPatterns = { "/upload2Cloudant" })
public class ThridPartyData extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final String METATOPIC = "metatopic";
	public static final String TOPIC = "topic";
	
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		doPost(request, response);
	}
	
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		// request object
		FileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload dfu = new ServletFileUpload(factory);
		
		JSONObject json = null;
		JSONArray jsonArr = null;
		
		try {
			// request form field to set
			List<FileItem> fileItems = dfu.parseRequest(request);
			for (FileItem fileItem : fileItems) {
				if (fileItem.getName() != null && !"".equals(fileItem.getName())) {
					// local file
					File localFile = new File(fileItem.getName());
					System.out.println(fileItem.getContentType());
					
					String fName = localFile.getName().replaceAll(" ", "_");
					StringBuffer sb = new StringBuffer();
					InputStream in = fileItem.getInputStream();
					byte[] bt = new byte[in.available()];

					for (;;) {
						int len = in.read(bt);
						if (len == -1) {
							break;
						}
						sb.append(new String(bt));
					}

					if (in != null) {
						in.close();
					}

					if(fName.indexOf(METATOPIC) != -1) {
						// metatopic json
						System.out.println("metatopic json:\n");
						json.put("metatopic", JSONArray.fromObject(sb.toString()));
						
					} else {
						// topic json
						System.out.println("topic json:\n");
						json.put("topic", JSONArray.fromObject(sb.toString()));
					}
				}
			}
		} catch (FileUploadException e) {
			e.printStackTrace();
		} finally {
			
		}
	}
	
	public String upload(@Context HttpServletRequest request, @Context HttpServletResponse response) 
			throws Exception {
		
		response.setContentType("text/html");
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		// request object
		FileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload dfu = new ServletFileUpload(factory);

		// request form field to set
		List<FileItem> fileItems = dfu.parseRequest(request);
		for (FileItem fileItem : fileItems) {
			if (fileItem.getName() != null && !"".equals(fileItem.getName())) {
				// local file
				File localFile = new File(fileItem.getName());
				System.out.println(fileItem.getContentType()+" uploaded");
				
				String fName = localFile.getName().replaceAll(" ", "_");
				StringBuffer sb = new StringBuffer();
				InputStream in = fileItem.getInputStream();
				byte[] bt = new byte[in.available()];

				for (;;) {
					int len = in.read(bt);
					if (len == -1) {
						break;
					}
					sb.append(bt);
				}

				if (in != null) {
					in.close();
				}

				if(fName.indexOf(METATOPIC) != -1) {
					// metatopic json
					System.out.println("metatopic json:\n"+sb.toString());
				} else {
					// topic json
					System.out.println("topic json:\n"+sb.toString());
				}
			}
		}
		
		return "SUCCESS";
	}
	
	public static void main(String[] args) {

	}

}
