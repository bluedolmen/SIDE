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

	protected static String PROPERTIES_PATH = "notification.properties";
	protected static String PROPERTIES_IN_DICTIONARY_PATH = "/app:company_home/app:dictionary/app:email_templates/app:notify_email_templates/cm:notification.properties";
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
		return getUserPreferences(user).get(key);
	}

	public void sendMails(List<String> dests, String eventType, String language, NodeRef document, Map<String, Object> model) throws Exception {
		EmailTest emailTest = new EmailTest(serviceRegistry);

		Properties propertiesFor = getPropertiesFor(language);
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
		model = emailTest.createEmailTemplateModel(document);
		if (!serviceRegistry.getNodeService().getType(document).equals(QName.createQName("{http://www.alfresco.org/model/calendar}calendarEvent"))) {
			Map<String, Object> docAttributs = getDocAttributs(document);
			if (docAttributs != null) {
				model.putAll(docAttributs);
			}
			model.put("userInfo", getUserDetails());
		}
		String emailText = serviceRegistry.getTemplateService().processTemplate(templateRef.toString(), model);
		subject = serviceRegistry.getTemplateService().processTemplateString( null, (String) subject, model);
		parameterValues.put(MailActionExecuter.PARAM_SUBJECT, subject);
				
		parameterValues.put(MailActionExecuter.PARAM_TEXT, emailText); 

		ac.setParameterValues(parameterValues);

		// schedule the email sending
		if (Boolean.parseBoolean(propertiesFor.getProperty("usefakeEmailer", Boolean.toString(usefakeEmailer)))) {

			emailTest.doAction(dests, (String) subject, document, templateRef, model);
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
			String owner = (String) serviceRegistry.getNodeService().getProperty(document, ContentModel.PROP_OWNER);
			if (owner != null) {
				result.put("owner", owner);
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

	public Properties getPropertiesFor(String language) throws Exception {
		if (props == null) {
			props = new HashMap<String, Properties>();
		}
		if (language == null) {
			Locale loc = I18NUtil.getLocale();
			if (loc == null || loc.toString() == null) {
				language = "en";
			} else {
				language = loc.toString().toLowerCase();
			}
		}
		language = language.toLowerCase();
		if (props.get(language) == null || dynamicLoading) {
			// load properties
			Properties prop = new Properties();
			prop.load(getPropertiesInputStream(language));
			logger.trace("Properties loaded :" + prop);
			props.put(language, prop);
		}
		// not dynamic so return cached object
		return props.get(language);
	}

	private InputStream getPropertiesInputStream(String language) {
		InputStream s = null;
		// search in alfresco dictionary
		// compute path with language
		String[] path_dictionary = PROPERTIES_IN_DICTIONARY_PATH.split("\\.");
		String resourcePath_dictionary = path_dictionary[0] + "_" + language + "." + path_dictionary[1];

		if (logger.isDebugEnabled()) {
			logger.debug("getPropertiesInputStream path_dictionary=" + path_dictionary);
			logger.debug("getPropertiesInputStream language=" + language);
			logger.debug("getPropertiesInputStream resourcePath_dictionary=" + resourcePath_dictionary);
		}

		try {
			// try to get the template that match the language (country[variant]?)
			s = getInputStream(resourcePath_dictionary);
		} catch (FileNotFoundException e) {
			logger.debug("no perfect match try on country");
			// no perfect match found, try to get template for country (without variant)
			Locale parseLocale = I18NUtil.parseLocale(language);
			logger.debug("local country :" + parseLocale.getCountry());
			logger.debug("local variant :" + parseLocale.getVariant());
			String country = parseLocale.getCountry().toLowerCase();
			if (!language.equals(country)) {
				// try on country ?
				resourcePath_dictionary = path_dictionary[0] + "_" + country + "." + path_dictionary[1];
				try {
					s = getInputStream(resourcePath_dictionary);
				} catch (FileNotFoundException e1) {
					logger.debug("no match on country");
				}
			}
			if (s == null) {
				// file not exist so go ahead, and search for the default configuration for this country
				language = country;
				logger.debug("Load default configuration from classPath");
				// search in classPath (for default values)
				String[] path = PROPERTIES_PATH.split("\\.");
				String resourcePath = path[0] + "_" + language + "." + path[1];
				s = this.getClass().getResourceAsStream(resourcePath);
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
