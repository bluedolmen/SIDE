package com.bluexml.side.framework.alfresco.webscriptExtension;

import java.io.UnsupportedEncodingException;

import org.alfresco.repo.jscript.BaseScopableProcessorExtension;
import org.alfresco.repo.site.script.ScriptSiteService;
import org.alfresco.service.ServiceRegistry;
import org.alfresco.service.cmr.site.SiteInfo;
import org.alfresco.service.cmr.repository.NodeRef;
import org.alfresco.service.cmr.site.SiteService;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import java.io.IOException;

import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Extend Alfresco Javascript API with rule service
 * siderule.getTasksDescriptionOfDocument(document) to get the workflow task on a document, including subprocess
 */
public class SiteScriptExtension extends BaseScopableProcessorExtension {

	private Logger logger = Logger.getLogger(getClass());

    private static final String HEADER_CONTENT_TYPE = "Content-Type";
    private static final String CONTENT_TYPE_TEXT_PLAIN = "text/plain";
    private static final String CONTENT_TYPE_JSON = "application/json";
    private static final String CONTENT_TYPE_FORM = "application/x-www-form-urlencoded";
    private static final String UTF_8 = "UTF-8";
    private static final String BASE_URL = "http://localhost:8080/share";
    private static final String LOGIN_URL = "/page/dologin";
    private static final String CREATE_SITE_URL = "/service/modules/create-site";


	/**
	 * Method to create a site
	 * The site is created through an http post call using a json structure as done by Share.
	 * The json structure is of the form:
	 * {
     * "visibility" : "PUBLIC",
     * "title" : "My Test Site100",
     * "shortName" : "mytestsite100",
     * "description" : "My Test Site created from command line 100",
     * "sitePreset" : "site-dashboard"
     * },

	 * 
	 * @param baseUrl     		the base url 
	 * @param alfrescoUsername 	the alfresco user login to use to send request
	 * @param alfrescoUsername 	the alfresco password to use to send request
	 * @param siteTitle			the site title
	 * @param siteShortname		the mandatory site short name
	 * @param siteDescription	the site description
	 * @param siteVisibility	the site visibility
	 * @param sitePreset		the site preset
	 *            
	 * @return nodeRef of the site; null if the site failed to be created; in the javascript, use site.getSite(siteShortname) to get the site.
	 */
	public NodeRef createSite(String baseUrl, String alfrescoUsername, String alfrescoPwd, 
			String siteTitle, String siteShortname, String siteDescription, String siteVisibility, String sitePreset) {
        if (logger.isDebugEnabled()) logger.debug("Create site "+siteTitle);
        NodeRef nodeRef = null;
        if (siteShortname != null) {
        	SiteInfo siteInfo = siteService.getSite(siteShortname);
        	if (siteInfo == null) {
        		if (siteTitle == null) siteTitle = siteShortname;
        		if (siteDescription == null) siteDescription = "";
        		if (sitePreset == null) sitePreset = "site-dashboard";
        		if (siteVisibility == null || !siteVisibility.equals(ScriptSiteService.PUBLIC_SITE) || !siteVisibility.equals(ScriptSiteService.MODERATED_SITE) || !siteVisibility.equals(ScriptSiteService.PRIVATE_SITE)) 
        			siteVisibility = ScriptSiteService.PUBLIC_SITE;
        		if (baseUrl == null) baseUrl = BASE_URL;
                
        		// Create Apache HTTP Client to use for both calls: login and create-site
                HttpClient httpClient = new HttpClient();

                // Login to Share to get a JSESSIONID setup in HTTP Client
                String loginData = "username=" + alfrescoUsername + "&password=" + alfrescoPwd;
                makePostCall(httpClient, baseUrl + LOGIN_URL, loginData, CONTENT_TYPE_FORM, "Login to Alfresco Share",
                        HttpStatus.SC_MOVED_TEMPORARILY, alfrescoUsername);
                
                // create body request using site parameters
                JSONObject json = new JSONObject();
                try {
					json.put("visibility", siteVisibility);
					json.put("title", siteTitle);
					json.put("shortName", siteShortname);
					json.put("description", siteDescription);
					json.put("sitePreset", sitePreset);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
               
                // send create-site request
                makePostCall(httpClient, CREATE_SITE_URL, json.toString(), CONTENT_TYPE_JSON,
                        "Create site with name: " + siteShortname, HttpStatus.SC_OK, alfrescoUsername);
                
                siteInfo = siteService.getSite(siteShortname);
            	if (siteInfo != null) {
            		nodeRef = siteInfo.getNodeRef();
            	} else {            		
    	        	// Site already exists, cannot create it, continue with next one
    	            logger.warn("Site (" + siteShortname + ") has not been created !!! - Check log.");
            	}
        	} else {
	        	// Site already exists, cannot create it, continue with next one
	            logger.warn("Site (" + siteShortname + ") already exists, cannot create it");
        	}
        } else {
        	// Site already exists, cannot create it, continue with next one
            logger.warn("Site shortName parameter is mandatory, cannot create site without shortName");        	
        }
		return nodeRef;
	}

	

    /**
     * Make a HTTP POST call to past in URL and with passed in Body content
     *
     * @param httpClient     the Apache HTTP client to use for the call, will have JSESSION ID set if cookie
     *                       was returned in previous call
     * @param url            the URL
     * @param data           the Data (for example JSON string)
     * @param dataType       the Data Type (for example application/json)
     * @param callName       the name of the POST call, for logging purpose
     * @param expectedStatus the expected HTTP status code when call returns
     */
    private void makePostCall(HttpClient httpClient, String url, String data, String dataType,
                              String callName, int expectedStatus, String alfrescoUsername) {
        PostMethod postMethod = null;
        try {
            postMethod = createPostMethod(url, data, dataType);
            int status = httpClient.executeMethod(postMethod);

            if (logger.isDebugEnabled()) {
                logger.debug(callName + " returned status: " + status);
            }

            if (status == expectedStatus) {
                if (logger.isDebugEnabled()) {
                    logger.debug(callName + " with user " + alfrescoUsername);
                }
            } else {
                logger.error("Could not " + callName + ", HTTP Status code : " + status);
            }
        } catch (HttpException he) {
            logger.error("Failed to " + callName, he);
        } catch (IOException ioe) {
            logger.error("Failed to " + callName, ioe);
        } finally {
            postMethod.releaseConnection();
        }
    }

    /**
     * Creates POST method with passed in body and content type
     *
     * @param url         URL for request
     * @param body        body of request
     * @param contentType content type of request
     * @return POST method
     * @throws java.io.UnsupportedEncodingException
     *
     */
    private PostMethod createPostMethod(String url, String body, String contentType)
            throws UnsupportedEncodingException {
        PostMethod postMethod = new PostMethod(url);
        postMethod.setRequestHeader(HEADER_CONTENT_TYPE, contentType);
        postMethod.setRequestEntity(new StringRequestEntity(body, CONTENT_TYPE_TEXT_PLAIN, UTF_8));

        return postMethod;
    }

	private ServiceRegistry serviceRegistry;
	private SiteService siteService;

	/**
	 * @return the serviceRegistry
	 */
	public ServiceRegistry getServiceRegistry() {
		return serviceRegistry;
	}

	/**
	 * @param serviceRegistry
	 *            the serviceRegistry to set
	 */
	public void setServiceRegistry(ServiceRegistry serviceRegistry) {
		this.serviceRegistry = serviceRegistry;
	}

	/**
	 * @return the siteService
	 */
	public SiteService getsiteService() {
		return siteService;
	}

	/**
	 * @param serviceRegistry
	 *            the serviceRegistry to set
	 */
	public void setsiteService(SiteService siteService) {
		this.siteService = siteService;
	}

}
