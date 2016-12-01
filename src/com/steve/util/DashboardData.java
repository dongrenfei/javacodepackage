package com.steve.util;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;

import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.util.EntityUtils;

import com.cloudant.client.api.Database;
import com.cloudant.client.api.model.Response;
import com.steve.util.CloudantUtil;
import com.steve.util.PropertiesReader;

import net.sf.json.JSONObject;

@Path("/cloudant")
public class DashboardData {
	
	// init client
	public static CloseableHttpClient getClient() {
		String username = PropertiesReader.getProperty("c_username");
		String password = PropertiesReader.getProperty("c_password");
		CredentialsProvider provider = new BasicCredentialsProvider();
		UsernamePasswordCredentials credentials = new UsernamePasswordCredentials(username, password);
		provider.setCredentials(AuthScope.ANY, credentials);
		SSLContextBuilder builder = new SSLContextBuilder();
		try {
			builder.loadTrustMaterial(null, new TrustSelfSignedStrategy());
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (KeyStoreException e) {
			e.printStackTrace();
		}

		SSLConnectionSocketFactory sslsf = null;
		try {
			sslsf = new SSLConnectionSocketFactory(builder.build());
		} catch (KeyManagementException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}

		return HttpClients.custom().setSSLSocketFactory(sslsf).setDefaultCredentialsProvider(provider).build();

	}

	@GET
	@Produces(value = MediaType.APPLICATION_JSON)
	@Path(value = "/{db}/{docid}")
	public String getDocById(@PathParam("db") String db, @PathParam("docid") String docid) {
		String responseString = null;
		String cloudantURL = PropertiesReader.getProperty("c_url");
		HttpGet httpGet = new HttpGet(cloudantURL + "/" + db + "/" + docid);
		System.out.println(cloudantURL + db + "/" + docid);
		HttpResponse response = null;
		CloseableHttpClient client = getClient();
		try {
			response = client.execute(httpGet);
			HttpEntity entity = response.getEntity();
			responseString = EntityUtils.toString(entity, "UTF-8");
			System.out.println(responseString);
			// int status = response.getStatusLine().getStatusCode();
			// Header[] headers = response.getAllHeaders();
			// client.close();
			// return httpResponse2JsonRespone(status, responseString, headers);

		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return responseString;

		// return Response.serverError().build();
	}

	@PUT
	@Produces(value = MediaType.APPLICATION_JSON)
	@Path(value = "/{db}/{id}")
	public Response updateDocById(@PathParam("db") String db, JSONObject json) {
//		String responseString = null;
		Response re = new Response();
		try {
			Database database = CloudantUtil.getDatabase(db);
			re = database.update(json);
//			responseString = "Update successfully.";
		} catch (Exception e) {
//			responseString = "Update failed.";
			e.printStackTrace();
		}
		
		return re;
	}

	/**	
	@PUT
	@Produces(value = MediaType.APPLICATION_JSON)
	@Path(value = "/{db}/{id}")
	public String updateDocById(@PathParam("db") String db, @QueryParam("rev") String rev, JSONObject json) {
		String responseString = null;
		String cloudantURL = PropertiesReader.getProperty("c_url");
		HttpPut httpPut = new HttpPut(cloudantURL +"/"+ db);
		System.out.println("save action json="+json);
		json.remove("_id");
		json.remove("_rev");
		System.out.println("after json="+json);
		rev="4-8bf0991beca5ba932502c1c7319919ab";
		CloseableHttpResponse response = null;
		CloseableHttpClient client = getClient();
		try {
			StringEntity entity = new StringEntity(json.toString());
			entity.setContentType("application/json");
			httpPut.setEntity(entity);
			httpPut.setHeader("Connection", "keep-alive");
			if (null != rev && !"".equals(rev)) {System.out.println("db="+db+"| rev="+rev);
				httpPut.setHeader("If-Match", rev);
			}
			response = client.execute(httpPut);
			HttpEntity ReEntity = response.getEntity();
			responseString = EntityUtils.toString(ReEntity, "UTF-8");
//			int status = response.getStatusLine().getStatusCode();
//			Header[] headers = response.getAllHeaders();
//			client.close();
//			return httpResponse2JsonRespone(status, responseString, headers);
			
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(responseString);
		return responseString;
//		return Response.serverError().build();
		
	}
*/
	
}
