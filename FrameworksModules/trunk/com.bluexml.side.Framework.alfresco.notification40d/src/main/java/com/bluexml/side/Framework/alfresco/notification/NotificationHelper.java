/*
    Copyright (C) 2007-20013  BlueXML - www.bluexml.com

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU Affero General Public License as
    published by the Free Software Foundation, either version 3 of the
    License, or (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU Affero General Public License for more details.

    You should have received a copy of the GNU Affero General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/
package com.bluexml.side.Framework.alfresco.notification;

import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.alfresco.model.ContentModel;
import org.alfresco.repo.action.executer.MailActionExecuter;
import org.alfresco.service.ServiceRegistry;
import org.alfresco.service.cmr.action.Action;
import org.alfresco.service.cmr.model.FileNotFoundException;
import org.alfresco.service.cmr.repository.ContentReader;
import org.alfresco.service.cmr.repository.NodeRef;
import org.alfresco.service.cmr.repository.StoreRef;
import org.alfresco.service.cmr.search.ResultSet;
import org.alfresco.service.cmr.search.SearchService;
import org.alfresco.service.cmr.security.AuthorityType;
import org.alfresco.service.cmr.security.PersonService;
import org.alfresco.service.cmr.site.SiteInfo;
import org.alfresco.service.namespace.QName;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.extensions.surf.util.I18NUtil;


public class NotificationHelper {
	static Log logger = LogFactory.getLog(NotificationHelper.class);

	// email sending action can be set to work in background
	boolean runInBackground = true;

	// true to enable server to reload properties without restart, useful if properties files are stored in alfresco repository
	boolean dynamicLoading = true;

	// use a fake mailer for debug purpose
	boolean usefakeEmailer = false;

	// force  recipients email advanced debug purpose
	protected String forcedEmail = null;

	public static String TYPE_CONTENT_PREFIX = "cm";
	protected static String PROPERTIES_PATH = "notification.properties";
	protected static String PROPERTIES_IN_DICTIONARY_PATH = "/app:company_home/app:dictionary/app:email_templates/app:notify_email_templates/";
	public static String TYPE_CALANDAR_NSURI = "http://www.alfresco.org/model/calendar";
	public static String TYPE_CALANDAR_PREFIX = "ia";
	public static String TYPE_CALANDAR_EVENT = "{http://www.alfresco.org/model/calendar}calendarEvent";
	public static String TYPE_CALANDAR_SPACE = "{http://www.alfresco.org/model/calendar}calendar";

	public static String ROOT_NOTIFICATION = "NOTIFICATION";

	public static String profile_notification_document_new = "profile_notification_document_new";
	public static String profile_notification_document_change = "profile_notification_document_change";
	public static String profile_notification_document_delete = "profile_notification_document_delete";
	public static String profile_notification_event_new = "profile_notification_event_new";
	public static String profile_notification_event_change = "profile_notification_event_change";
	public static String profile_notification_event_delete = "profile_notification_event_delete";
	public static String profile_notification_group = "profile_notification_group";

	// user language preference key
	public static String profile_language = "user_language";

	// defaults values
	public static boolean default_preferences = false;
	public static String default_language = "fr_FR";

	private Map<String, Properties> props;

	public boolean isRunInBackground() {
		return runInBackground;
	}

	public void setRunInBackground(boolean runInBackground) {
		this.runInBackground = runInBackground;
	}

	public boolean isDynamicLoading() {
		return dynamicLoading;
	}

	public void setDynamicLoading(boolean dynamicLoading) {
		this.dynamicLoading = dynamicLoading;
	}

	public boolean isUsefakeEmailer() {
		return usefakeEmailer;
	}

	public void setUsefakeEmailer(boolean usefakeEmailer) {
		this.usefakeEmailer = usefakeEmailer;
	}

	public String getForcedEmail() {
		return forcedEmail;
	}

	public void setForcedEmail(String forcedEmail) {
		this.forcedEmail = forcedEmail;
	}

	protected ServiceRegistry serviceRegistry;

	public ServiceRegistry getServiceRegistry() {
		return serviceRegistry;
	}

	public void setServiceRegistry(ServiceRegistry serviceRegistry) {
		this.serviceRegistry = serviceRegistry;
	}

	private String getUserPreferencesString(NodeRef userId) {
		// get preferences from Preson Node or cache
		String preferencesString = null;
		ContentReader reader = serviceRegistry.getContentService().getReader(userId, ContentModel.PROP_PREFERENCE_VALUES);
		if (reader != null) {
			preferencesString = reader.getContentString();
		}

		return preferencesString;
	}

	public Map<String, Object> getUserPreferences(NodeRef user) throws Exception {
		boolean useDefault = true;
		boolean useDefault_lang = true;
		Map<String, Object> userPreferencies = new HashMap<String, Object>();
		String pref = getUserPreferencesString(user);
		if (pref != null) {

			JSONObject root = new JSONObject(pref);
			if (!root.isNull(ROOT_NOTIFICATION)) {
				String prefsString = root.getString(ROOT_NOTIFICATION).replaceFirst("^\\(", "").replaceFirst("\\)$", "");
				JSONObject root_NOTIFICATION = new JSONObject(prefsString);

				// get configuration for Documents
				Boolean doc_new = getNotificationValueFor(root_NOTIFICATION, profile_notification_document_new);
				userPreferencies.put(profile_notification_document_new, doc_new);
				Boolean doc_change = getNotificationValueFor(root_NOTIFICATION, profile_notification_document_change);
				userPreferencies.put(profile_notification_document_change, doc_change);
				Boolean doc_delete = getNotificationValueFor(root_NOTIFICATION, profile_notification_document_delete);
				userPreferencies.put(profile_notification_document_delete, doc_delete);

				// get configuration for Events
				Boolean event_new = getNotificationValueFor(root_NOTIFICATION, profile_notification_event_new);
				userPreferencies.put(profile_notification_event_new, event_new);
				Boolean event_change = getNotificationValueFor(root_NOTIFICATION, profile_notification_event_change);
				userPreferencies.put(profile_notification_event_change, event_change);
				Boolean event_delete = getNotificationValueFor(root_NOTIFICATION, profile_notification_event_delete);
				userPreferencies.put(profile_notification_event_delete, event_delete);
				useDefault = false;
			}

			if (!root.isNull(profile_language)) {
				String language = root.getString(profile_language);
				userPreferencies.put(profile_language, language);
				useDefault_lang = false;
			} else {
				logger.debug("use default language (user_language not found in user profile)");
			}
		}

		if (useDefault) {
			// set default values
			userPreferencies.put(profile_notification_document_new, default_preferences);
			userPreferencies.put(profile_notification_document_change, default_preferences);
			userPreferencies.put(profile_notification_document_delete, default_preferences);
			userPreferencies.put(profile_notification_event_new, default_preferences);
			userPreferencies.put(profile_notification_event_change, default_preferences);
			userPreferencies.put(profile_notification_event_delete, default_preferences);

		}

		if (useDefault_lang) {
			userPreferencies.put(profile_language, default_language);
		}

		return userPreferencies;
	}

	private boolean getNotificationValueFor(JSONObject root_NOTIFICATION, String key) throws JSONException {
		return root_NOTIFICATION.has(key) ? root_NOTIFICATION.getBoolean(key) : false;
	}

	public Object getUserPreference(NodeRef user, String key) throws Exception {
		Object obj = getUserPreferences(user).get(key);
		if (obj == null) return true;
		else return obj;
	}

	public void sendMails(List<String> dests, String eventType, NodeRef document, String language, Map<String, Object> model) throws Exception {
		sendMails(dests, eventType, document, language, model, null, null);
	}

	public void sendMails(List<String> dests, String eventType, NodeRef document, Map<String, Object> model, String propertyFileName, ArrayList<String> resourceCustomPaths) throws Exception {
		if (logger.isDebugEnabled()) logger.debug("dests :" + dests+" - eventType :"+eventType+ " - document : "+document+" - model : "+model+" - resourceCustomPath : "+resourceCustomPaths);
		List<String> userIds_filtered = getPersonList(dests);
		GroupByMap<String> gblanguage = new GroupByMap<String>();

		for (String user : userIds_filtered) {
			NodeRef person = serviceRegistry.getPersonService().getPerson(user);
			Boolean userPreference = (Boolean) getUserPreference(person, eventType);
			if (userPreference) {
				gblanguage.put(getUserPreference(person, profile_language), user);
			}
			if (logger.isDebugEnabled()) logger.debug(user + " preference for " + eventType + " :" + userPreference);
		}

		if (logger.isDebugEnabled()) logger.debug("userGroup :" + gblanguage);

		for (Map.Entry<Object, List<String>> entry : gblanguage.getEntrySet()) {
			// send mail for users with language
			List<String> users = entry.getValue();
			String language = (String) entry.getKey();
			sendMails(users, eventType, document, language, model, propertyFileName, resourceCustomPaths);
		}
	}

	public void sendMails(List<String> dests, String eventType, NodeRef document, String language, Map<String, Object> model, String propertyFileName, ArrayList<String> resourceCustomPaths) throws Exception {
		EmailTest emailTest = new EmailTest(serviceRegistry);

		Properties propertiesFor = getPropertiesFor(language,propertyFileName, resourceCustomPaths);
		String forcedEamilFromProperty = propertiesFor.getProperty("forcedEmail");

		if (forcedEamilFromProperty != null) {
			setForcedEmail(forcedEamilFromProperty);
		}

		logger.debug("send mail for " + eventType + " to " + dests);
		Action ac = serviceRegistry.getActionService().createAction("mail");
		// run in background to avoid performance issues
		ac.setExecuteAsynchronously(Boolean.parseBoolean(propertiesFor.getProperty("runInBackground", Boolean.toString(runInBackground))));

		//getAttributs(site, docName,docType, docUrl, Isdocument, owner)
		String subjectKey = "mail.notification." + eventType + ".subject";
		Serializable subject = propertiesFor.getProperty(subjectKey);
		logger.debug("email subject " + subjectKey + "=" + subject);
		//evaluer le sujet

		// get nodeRef template
		String templateKey = "mail.notification." + eventType + ".templates";
		String t = propertiesFor.getProperty(templateKey);

		logger.debug("template path " + templateKey + "=" + t);
		NodeRef templateRef = getNodeRefFromPath(t);

		// set actions parameters
		Map<String, Serializable> parameterValues = new HashMap<String, Serializable>();
		

		if (forcedEmail != null) {
			logger.warn("BEWARE SendMail action used forced email adress :" + forcedEmail);
			logger.info("To whitch off this, edit/check :");
			logger.info("* alfresco/module/SIDE_notification3411E/context/service-context.xml");
			logger.info("* notification properties files in \"Data Dictionary > Email Templates > Notify Email Templates\"");
			parameterValues.put(MailActionExecuter.PARAM_TO, (Serializable) forcedEmail);
		} else {
			parameterValues.put(MailActionExecuter.PARAM_TO_MANY, (Serializable) dests);
		}

		/* manage case that template need alternative model
		String emailText = "default email body please to use templates";
		if (runInBackground && eventType.contains("delete")) {
			// instantiate the template before calling the action 
			// build the email template model
			model = emailTest.createEmailTemplateModel(document);
		}
		if (model == null) {
			// let emailAction to interpret template
			parameterValues.put(MailActionExecuter.PARAM_TEMPLATE, templateRef);
		} else {
			// beware this not work in background
			emailText = serviceRegistry.getTemplateService().processTemplate(templateRef.toString(), model);
		}*/
		Map<String, Object> modelAll = emailTest.createEmailTemplateModel(document);
		if (!serviceRegistry.getNodeService().getType(document).equals(QName.createQName("{http://www.alfresco.org/model/calendar}calendarEvent"))) {
			Map<String, Object> docAttributs = getDocAttributs(document);
			if (docAttributs != null) {
				modelAll.putAll(docAttributs);
			}
			modelAll.put("userInfo", getUserDetails());
		}
		if (model != null) modelAll.putAll(model);
		String emailText = serviceRegistry.getTemplateService().processTemplate(templateRef.toString(), modelAll);
		subject = serviceRegistry.getTemplateService().processTemplateString( null, (String) subject, modelAll);
		parameterValues.put(MailActionExecuter.PARAM_SUBJECT, subject);
				
		parameterValues.put(MailActionExecuter.PARAM_TEXT, emailText); 

		ac.setParameterValues(parameterValues);

		// schedule the email sending
		if (Boolean.parseBoolean(propertiesFor.getProperty("usefakeEmailer", Boolean.toString(usefakeEmailer)))) {

			emailTest.doAction(dests, (String) subject, document, templateRef, modelAll);
		} else {
			serviceRegistry.getActionService().executeAction(ac, document);
		}

		logger.debug("########### NotificationHelper.sendMails() END");
	}

	private Object getUserDetails() {
		String userName = serviceRegistry.getAuthenticationService().getCurrentUserName();
		NodeRef user = serviceRegistry.getPersonService().getPerson(userName);
		String result = "";
		String firstName = (String) serviceRegistry.getNodeService().getProperty(user, ContentModel.PROP_FIRSTNAME);
		String lastName = (String) serviceRegistry.getNodeService().getProperty(user, ContentModel.PROP_LASTNAME);
		String email = (String) serviceRegistry.getNodeService().getProperty(user, ContentModel.PROP_EMAIL);
		if (firstName != null) {
			result += firstName + " ";
		}
		if (lastName != null) {
			result += lastName + " ";
		}
		if (email != null) {
			result += email;
		}
		return result;
	}

	// get site, docName, docType, docUrl, isDocument, owner
	private Map<String, Object> getDocAttributs(NodeRef document) {
		Map<String, Object> result = new HashMap<String, Object>();
		
		//get docType and isDocument
		NodeRef parent = serviceRegistry.getNodeService().getPrimaryParent(document).getParentRef();
		do {
			if (serviceRegistry.getNodeService().getProperty(parent, ContentModel.PROP_NAME).equals("documentLibrary")) {
				if (serviceRegistry.getNodeService().getType(document).equals(QName.createQName("{http://www.alfresco.org/model/forum/1.0}post"))) {
					result.put("docType", "commentaire sur le document");
					result.put("isDocument", false);
				} else {
					result.put("docType", "document");
					result.put("isDocument", true);
				}
				break;
			} else  if (serviceRegistry.getNodeService().getProperty(parent, ContentModel.PROP_NAME).equals("wiki")) {
				if (serviceRegistry.getNodeService().getType(document).equals(QName.createQName("{http://www.alfresco.org/model/forum/1.0}post"))) {
					result.put("docType", "commentaire sur le lien");
					result.put("isDocument", false);
				} else {
					result.put("docType", "wiki");
					result.put("isDocument", false);
				}
				break;
			} else  if (serviceRegistry.getNodeService().getProperty(parent, ContentModel.PROP_NAME).equals("dataLists")) {
				result.put("docType", "document liste de donnée");
				result.put("isDocument", false);
				break;
			} else  if (serviceRegistry.getNodeService().getProperty(parent, ContentModel.PROP_NAME).equals("discussions")) {
				result.put("docType", "discussion");
				result.put("isDocument", false);
				break;
			} else  if (serviceRegistry.getNodeService().getProperty(parent, ContentModel.PROP_NAME).equals("blog")) {
				if (serviceRegistry.getNodeService().getType(document).equals(QName.createQName("{http://www.alfresco.org/model/forum/1.0}post"))) {
					result.put("docType", "commentaire sur le blog");
					result.put("isDocument", false);
				} else {
					result.put("docType", "blog");
					result.put("isDocument", false);
				}
				break;
			} else  if (serviceRegistry.getNodeService().getProperty(parent, ContentModel.PROP_NAME).equals("links")) {
				if (serviceRegistry.getNodeService().getType(document).equals(QName.createQName("{http://www.alfresco.org/model/forum/1.0}post"))) {
					result.put("docType", "commentaire sur le lien");
					result.put("isDocument", false);
				} else {
					result.put("docType", "lien");
					result.put("isDocument", false);
				}
				break;
			}
			parent = serviceRegistry.getNodeService().getPrimaryParent(parent).getParentRef();
		} while (parent != null);
		
		if (parent != null) {
		
			//Get document if it is a comment or a discussion
			NodeRef temp = null;
			try {
				if(result.get("docType").equals("discussion")) {
					temp = getDocument(QName.createQName("{http://www.alfresco.org/model/forum/1.0}topic"), document, result);
				} else if(result.get("docType").equals("commentaire sur le document") || result.get("docType").equals("commentaire sur le lien") || result.get("docType").equals("commentaire sur le blog") || result.get("docType").equals("commentaire sur le wiki")) {
					temp = getDocument(ContentModel.TYPE_CONTENT, document, result);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (temp != null) {
				document = temp;
			}
				
			//get document site title
			SiteInfo siteInfo = serviceRegistry.getSiteService().getSite(document);
			result.put("site", siteInfo.getShortName());
			
			//get docName
			String docName = (String) serviceRegistry.getNodeService().getProperty(document, ContentModel.PROP_NAME);
			result.put("docName", docName);
			
			//get docUrl
			result.put("docUrl", "document-details?nodeRef=" + document.getStoreRef() + "/" + document.getId());
			
			//get owner
			String owner = serviceRegistry.getOwnableService().getOwner(document);
			NodeRef onwerNodeRef = serviceRegistry.getPersonService().getPerson(owner);
			if (onwerNodeRef != null) {
				result.put("owner", onwerNodeRef);
			}
		
			return result;
		}
		return null;
	}
	
	protected NodeRef getDocument(QName parentType, NodeRef document, Map<String, Object> docAttributs) throws FileNotFoundException {
		NodeRef result = null;
		NodeRef parent = null;
		
		parent = serviceRegistry.getNodeService().getPrimaryParent(document).getParentRef();
		do {
			if (serviceRegistry.getNodeService().getType(parent).equals(parentType)) {
				result = parent;
				break;
			}
			parent = serviceRegistry.getNodeService().getPrimaryParent(parent).getParentRef();
		} while (serviceRegistry.getNodeService().getPrimaryParent(document) != null);
		if (result == null) {
			throw new FileNotFoundException("Parent document not found for comment or discussion"); 
		}
		return result;
	}

	private NodeRef getNodeRefFromPath(String t) throws FileNotFoundException {
		NodeRef companyHome = null;
		if (logger.isDebugEnabled()) {
			logger.debug("getNodeRefFromPath t=" + t);
		}
		ResultSet rs = serviceRegistry.getSearchService().query(StoreRef.STORE_REF_WORKSPACE_SPACESSTORE, SearchService.LANGUAGE_XPATH, t);

		try {
			if (rs.length() == 0) {
				throw new FileNotFoundException("Didn't find " + t);
			}
			companyHome = rs.getNodeRef(0);
		} finally {
			rs.close();
		}
		return companyHome;
	}

	/**
	 * This method loads the 'notification_<language>.properties' file from the Dictionary path of Alfresco repository as Properties
	 * @param language the language to load
	 * @return the set of properties for this language
	 * @throws Exception
	 */
	public Properties getPropertiesFor(String language) throws Exception {
		return getPropertiesFor(language, null, null);
	}
	
	/**
	 * This method loads the 'notification_<language>.properties' file from the Dictionary path of Alfresco repository and then overwrite the properties 
	 * whith the properties files successively contains in resourceCustomPaths[i]+"/cm:"+propertyFileName.basename+<language>+propertyFileName.extension
	 * @param language the language to load; if not null the propertyFileName is modified in propertyFileName.basename+<language>+propertyFileName.extension; if null the propertyFileName is not modified
	 * @param propertyFileName the property file name to load
	 * @param resourceCustomPaths a list of folder where the propertyFileName may be loaded (with the embedded language if not null)
	 * @return the set of properties for this language
	 * @throws Exception
	 */
	public Properties getPropertiesFor(String language, String propertyFileName, ArrayList<String> resourceCustomPaths) throws Exception {
		if (props == null) {
			props = new HashMap<String, Properties>();
		}
		if (language == null) {
			Locale loc = I18NUtil.getLocale();
			if (loc == null || loc.toString() == null) {
				language = "fr_FR";
			} else {
				language = loc.toString();
			}
		}
		logger.trace("language :" + language);
		Properties langueExist = props.get(language);
		if (langueExist == null || dynamicLoading) {
			// load properties
			Properties prop = getProperties(language, propertyFileName, resourceCustomPaths);
			props.put(language, prop);
		}
		// not dynamic so return cached object
		return props.get(language);
	}
	/**
	 * This method loads the propertyFileName file from the Dictionary path of Alfresco repository and then overwrite the properties 
	 * whith the properties files successively contains in resourceCustomPaths[i]+"/cm:"+propertyFileName
	 * @param propertyFileName the property file name to load; if null, set to "notification.properties"
	 * @param resourceCustomPaths a list of folder where the propertyFileName may be loade
	 * @return the set of properties
	 * @throws Exception
	 */
	public Properties getProperties(String propertyFileName, ArrayList<String> resourceCustomPaths) throws Exception {
		return getProperties(null, propertyFileName, resourceCustomPaths);
	}

	/**
	 * This method loads the propertyFileName file from the Dictionary path of Alfresco repository (and if not found from classpath) 
	 * and then overwrite the properties with the properties files successively contains in resourceCustomPaths[i]+"/cm:"+propertyFileName
	 * @param propertyFileName the property file name to load; if null, set to "notification.properties"
	 * @param resourceCustomPaths a list of folder where the propertyFileName may be loaded
	 * @return the set of properties
	 * @throws Exception
	 */
	public Properties getProperties(String language, String propertyFileName, ArrayList<String> resourceCustomPaths) throws Exception {
		// load properties
		Properties prop = new Properties();
		if (propertyFileName == null)  propertyFileName = PROPERTIES_PATH;
		// search in alfresco dictionary
		prop.load(getPropertiesInputStream(language,propertyFileName,PROPERTIES_IN_DICTIONARY_PATH,true));
		// custom properties
		if (resourceCustomPaths != null && !resourceCustomPaths.isEmpty()) {
			for (String resourceCustomPath : resourceCustomPaths) {
				Properties customProp = new Properties();
				InputStream s = getPropertiesInputStream(language, propertyFileName, resourceCustomPath, false);
				if (s != null) {
					customProp.load(s);
					prop.putAll(customProp);
				}
			}
		}
		logger.trace("Properties loaded :" + prop);
		return prop;
	}

	/**
	 * This method loads the 'notification_<language>.properties' file from the Dictionary path of Alfresco repository as InputStream
	 * @param language
	 * @return the inputstream 'notification_<language>.properties' content
	 */
	public InputStream getPropertiesInputStream(String language) {
		// search in alfresco dictionary
		return getPropertiesInputStream(language,PROPERTIES_PATH,PROPERTIES_IN_DICTIONARY_PATH,true);
	}

	/**
	 * This method loads the  file resourceCustomPaths+"/cm:"+propertyFileName.basename+<language>+propertyFileName.extension as InputStream
	 * @param language the language to load; if not null the propertyFileName is modified in propertyFileName.basename+<language>+propertyFileName.extension; if null the propertyFileName is not modified
	 * @param propertyFileName the property file name to load
	 * @param resourceCustomPath the folder where the propertyFileName may be loaded (with the embedded language if not null)
	 * @param classPath if true, the propertyFileName is loaded from the classpath (without resourceCustomPath)
	 * @return the inputstream resourceCustomPaths+"/cm:"+propertyFileName.basename+<language>+propertyFileName.extension content
	 */
	public InputStream getPropertiesInputStream(String language, String propertyFileName, String resourceCustomPath, boolean classPath) {
		InputStream s = null;
		// compute path with language
		String[] path_dictionary = propertyFileName.split("\\.");
		if (!resourceCustomPath.endsWith("/")) resourceCustomPath += "/";
		path_dictionary[0] = resourceCustomPath+TYPE_CONTENT_PREFIX+":"+path_dictionary[0];
		String resourcePath_dictionary = path_dictionary[0];
		if (language != null) resourcePath_dictionary+= "_" + language;
		resourcePath_dictionary+= "." + path_dictionary[1];

		if (logger.isDebugEnabled()) {
			logger.debug("getPropertiesInputStream path_dictionary=" + path_dictionary);
			logger.debug("getPropertiesInputStream language=" + language);
			logger.debug("getPropertiesInputStream resourcePath_dictionary=" + resourcePath_dictionary);
		}

		try {
			// try to get the template that match the language (country[variant]?)
			s = getInputStream(resourcePath_dictionary);
		} catch (FileNotFoundException e) {
			s = null;
			String country = null;
			if (language != null) {
				logger.debug("no perfect match try on country");
				// no perfect match found, try to get template for country (without variant)
				Locale parseLocale = I18NUtil.parseLocale(language);
				logger.debug("local country :" + parseLocale.getCountry());
				logger.debug("local variant :" + parseLocale.getVariant());
				country = parseLocale.getCountry().toLowerCase();
				if (!language.equals(country)) {
					// try on country ?
					resourcePath_dictionary = path_dictionary[0] + "_" + country + "." + path_dictionary[1];
					try {
						s = getInputStream(resourcePath_dictionary);
					} catch (FileNotFoundException e1) {
						logger.debug("no match on country");
					}
				}
			}
			if (s == null && classPath) {
				// file not exist so go ahead, and search for the default configuration for this country
				if (country != null) {
					language = country;
					logger.debug("Load default configuration from classPath");
					// search in classPath (for default values)
					String[] path = PROPERTIES_PATH.split("\\.");
					String resourcePath = path[0] + "_" + language + "." + path[1];
					s = this.getClass().getResourceAsStream(resourcePath);
				}
				if (s == null) {
					// not found so we load default file
					s = this.getClass().getResourceAsStream(PROPERTIES_PATH);
					logger.debug("configuration for language :" + language + " not found, so use default language");
				}
			}
		}
		return s;
	}

	private InputStream getInputStream(String resourcePath_dictionary) throws FileNotFoundException {
		NodeRef nodeProperties = getNodeRefFromPath(resourcePath_dictionary);
		ContentReader reader = serviceRegistry.getContentService().getReader(nodeProperties, ContentModel.PROP_CONTENT);
		logger.debug("Properties loaded from :" + resourcePath_dictionary);
		return reader.getContentInputStream();

	}

	public List<String> getPersonList(List<String> authorities) {
		PersonService personService = serviceRegistry.getPersonService();
		List<String> recipients = new ArrayList<String>(authorities.size());
		for (String authority : authorities) {
			AuthorityType authType = AuthorityType.getAuthorityType(authority);
			if (authType.equals(AuthorityType.USER)) {
				if (personService.personExists(authority) == true) {
					recipients.add(authority);
				}
			} else if (authType.equals(AuthorityType.GROUP)) {
				Set<String> users = serviceRegistry.getAuthorityService().getContainedAuthorities(AuthorityType.USER, authority, false);
				recipients.addAll(users);
			}
		}
		return recipients;
	}

}
