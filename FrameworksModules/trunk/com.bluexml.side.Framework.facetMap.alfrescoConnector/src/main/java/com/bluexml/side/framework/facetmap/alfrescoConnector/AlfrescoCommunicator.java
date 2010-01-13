package com.bluexml.side.framework.facetmap.alfrescoConnector;
 
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.httpclient.Credentials;
import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.UsernamePasswordCredentials;
import org.apache.commons.httpclient.auth.AuthScope;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class AlfrescoCommunicator {
	private Logger logger = Logger.getLogger(getClass());
	HttpClient http;

	String alfrescoBaseURL;

	static String doclistURL;
	static String sitesMembershipValidationURL = "service/com/bluexml/side/facetMap/sitesMembershipValidation";
	static String ticketValidationURL = "service/com/bluexml/side/facetMap/ticketValidation";
	static String sitesCollectionURL = "service/api/sites";

	public final static String TICKET_KEY = "ticket";
	public final static String AUTHORITIES_KEY = "authorities";

	public AlfrescoCommunicator(Properties prop) {
		alfrescoBaseURL = prop.getProperty("ant.alfrescoURL", "http://localhost:8080/alfresco");

		http = new HttpClient();
		http.getParams().setAuthenticationPreemptive(true);
		Credentials defaultcreds = new UsernamePasswordCredentials(prop.getProperty("ant.usernameAlfresco", "admin"), prop.getProperty("ant.passwordAlfresco", "admin"));
		http.getState().setCredentials(new AuthScope("localhost", 8080, AuthScope.ANY_REALM), defaultcreds);
	}

	public Map<String, Object> checkUserTicketForSite(String userName, String ticket, String site) throws Exception {
		Map<String, String> params = new HashMap<String, String>();
		params.put("userName", userName);
		params.put("userTicket", ticket);
		params.put("siteName", site);
		String sitesMembershipValidationURL2 = getSitesMembershipValidationURL();
		logger.debug("**********************" + sitesMembershipValidationURL2);
		String jsonString = new String(buildGetMethodAndExecute(sitesMembershipValidationURL2, params));
		Map<String, Object> ticket_ = readJsonResponse_checkUserAccessRight(jsonString);
		return ticket_;
	}

	public Map<String, Object> checkUserAccessForSite(String userName, String pwd, String site) throws Exception {
		Map<String, String> params = new HashMap<String, String>();
		params.put("userName", userName);
		params.put("userpwd", pwd);
		params.put("siteName", site);
		String sitesMembershipValidationURL2 = getSitesMembershipValidationURL();
		logger.debug("*********************" + sitesMembershipValidationURL2);
		String jsonString = new String(buildGetMethodAndExecute(sitesMembershipValidationURL2, params));
		Map<String, Object> ticket = readJsonResponse_checkUserAccessRight(jsonString);
		return ticket;
	}
	
	
	public Map<String, Object> checkUserAccess(String userName, String pwd) throws Exception {
		Map<String, String> params = new HashMap<String, String>();
		params.put("userName", userName);
		params.put("userpwd", pwd);
		String sitesMembershipValidationURL2 = getTicketValidationURL();
		logger.debug("*********************" + sitesMembershipValidationURL2);
		String jsonString = new String(buildGetMethodAndExecute(sitesMembershipValidationURL2, params));
		Map<String, Object> ticket = readJsonResponse_checkUserAccessRight(jsonString);
		return ticket;
	}

	public List<String> getSitesCollection() throws Exception {
		List<String> ls = null;

		ls = readJsonResponse_SiteCollection(new String(buildGetMethodAndExecute(getSitesCollectionURL(), new HashMap<String, String>())));

		return ls;
	}

	private byte[] buildGetMethodAndExecute(String url, Map<String, String> params) {
		byte[] responseBody = {};
		GetMethod get = new GetMethod(url);
		get.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler(3, false));
		logger.debug("parameters :" + params);

		ArrayList<NameValuePair> pairs = new ArrayList<NameValuePair>();
		for (Map.Entry<String, String> entry : params.entrySet()) {
			pairs.add(new NameValuePair(entry.getKey(), entry.getValue()));
		}
		NameValuePair[] arr = new NameValuePair[pairs.size()];
		arr = pairs.toArray(arr);
		get.setQueryString(arr);
		// Execute the method.
		int statusCode;
		try {
			statusCode = http.executeMethod(get);

			if (statusCode != HttpStatus.SC_OK) {
				System.err.println("Method failed: " + get.getStatusLine());
			}

			// Read the response body.
			responseBody = get.getResponseBody();
		} catch (HttpException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			// Release the connection.
			get.releaseConnection();
		}
		return responseBody;
	}

	public List<String> readJsonResponse_SiteCollection(String jsonString) throws JSONException {
		logger.debug("SiteCollection :" + jsonString);

		List<String> ls = new ArrayList<String>();
		JSONArray json = new JSONArray(jsonString);

		for (int c = 0; c < json.length(); c++) {
			JSONObject site = json.getJSONObject(c);
//			String url = site.getString("url");
//			String sitePreset = site.getString("sitePreset");
//			
//			String title = site.getString("title");
//			String description = site.getString("description");
//			String node = site.getString("node");
//			String tagScope = site.getString("tagScope");
//			String isPublic = site.getString("isPublic");
//			String visibility = site.getString("visibility");
//			JSONArray sitesManagers = site.getJSONArray("sitesManagers");
//			for (int i = 0; c < sitesManagers.length(); i++) {
//				String username = sitesManagers.getString(i);
//			}
			String shortName = site.getString("shortName");
			ls.add(shortName);
		}

		return ls;
	}

	public String getSitesCollectionURL() {
		return alfrescoBaseURL + "/" + sitesCollectionURL;
	}

	public String getSitesMembershipValidationURL() {
		return alfrescoBaseURL + "/" + sitesMembershipValidationURL;
	}
	
	public String getTicketValidationURL() {
		return alfrescoBaseURL + "/" + ticketValidationURL;
	}

	public Map<String, Object> readJsonResponse_checkUserAccessRight(String jsonString) throws JSONException {
		Map<String, Object> accessRight = null;
		logger.debug("cheeck Access :" + jsonString);
		String ticket = null;
		JSONObject json = new JSONObject(jsonString);
		boolean ok = json.getBoolean("validity");
		if (ok) {
			accessRight = new HashMap<String, Object>();
			ticket = json.getString("ticket");
			JSONArray auth = json.getJSONArray("authorities");
			ArrayList<String> authList = new ArrayList<String>();
			for (int i = 0; i < auth.length(); i++) {
				String array_element = auth.getString(i);
				authList.add(array_element);
			}
			accessRight.put("ticket", ticket);
			accessRight.put("authorities", authList);
		}

		return accessRight;
	}
}
