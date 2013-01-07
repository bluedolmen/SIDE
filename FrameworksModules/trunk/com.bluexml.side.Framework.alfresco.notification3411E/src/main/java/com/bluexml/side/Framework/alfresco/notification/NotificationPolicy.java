package com.bluexml.side.Framework.alfresco.notification;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.alfresco.error.AlfrescoRuntimeException;
import org.alfresco.model.ContentModel;
import org.alfresco.repo.node.NodeServicePolicies.BeforeDeleteNodePolicy;
import org.alfresco.repo.node.NodeServicePolicies.OnCreateNodePolicy;
import org.alfresco.repo.node.NodeServicePolicies.OnUpdatePropertiesPolicy;
import org.alfresco.repo.policy.Behaviour.NotificationFrequency;
import org.alfresco.repo.policy.JavaBehaviour;
import org.alfresco.repo.policy.PolicyComponent;
import org.alfresco.repo.security.authentication.AuthenticationUtil;
import org.alfresco.repo.security.authentication.AuthenticationUtil.RunAsWork;
import org.alfresco.repo.site.SiteModel;
import org.alfresco.service.ServiceRegistry;
import org.alfresco.service.cmr.repository.ChildAssociationRef;
import org.alfresco.service.cmr.repository.NodeRef;
import org.alfresco.service.cmr.repository.Path;
import org.alfresco.service.cmr.repository.datatype.DefaultTypeConverter;
import org.alfresco.service.cmr.security.AccessStatus;
import org.alfresco.service.cmr.security.PermissionService;
import org.alfresco.service.namespace.QName;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author davidabad This Class aims to sending email when Document (cm:content)
 *         or Event are created modified or deleted User must be able to choose
 *         to subscribe or not. Email are sent to user according to : -
 *         subscribe profile - user language (stored in user profile, can be
 *         changed using langPicker dashlet)
 */
public class NotificationPolicy implements OnCreateNodePolicy, OnUpdatePropertiesPolicy, BeforeDeleteNodePolicy {

	private static Log logger = LogFactory.getLog(NotificationPolicy.class);

	private PolicyComponent policyComponent;
	private NotificationHelper notificationHelper;

	String timeBetweenUpdates = "";

	boolean enableCreateDocument = true;
	boolean enableUpdateDocument = true;
	boolean enableDeleteDocument = true;

	boolean enableCreateEvent = true;
	boolean enableUpdateEvent = true;
	boolean enableDeleteEvent = false;

	public String getTimeBetweenUpdates() {
		return timeBetweenUpdates;
	}

	public void setTimeBetweenUpdates(String timeBetweenUpdates) {
		this.timeBetweenUpdates = timeBetweenUpdates;
	}

	public NotificationHelper getNotificationHelper() {
		return notificationHelper;
	}

	public void setNotificationHelper(NotificationHelper notificationHelper) {
		this.notificationHelper = notificationHelper;
	}

	protected ServiceRegistry serviceRegistry;

	public ServiceRegistry getServiceRegistry() {
		return serviceRegistry;
	}

	public void setServiceRegistry(ServiceRegistry serviceRegistry) {
		this.serviceRegistry = serviceRegistry;
	}

	/**
	 * @return the policyComponent
	 */
	public PolicyComponent getPolicyComponent() {
		return policyComponent;
	}

	/**
	 * @param policyComponent
	 *            the policyComponent to set
	 */
	public void setPolicyComponent(PolicyComponent policyComponent) {
		this.policyComponent = policyComponent;
	}

	public void init() {
		logger.debug("[init] Initializing register policy Components");
		logger.debug("[init] config :");
		logger.debug("[init]\tenableCreateDocument=" + enableCreateDocument);
		logger.debug("[init]\tenableUpdateDocument=" + enableUpdateDocument);
		logger.debug("[init]\tenableDeleteDocument=" + enableDeleteDocument);

		logger.debug("[init]\tenableCreateEvent=" + enableCreateEvent);
		logger.debug("[init]\tenableUpdateEvent=" + enableUpdateEvent);
		logger.debug("[init]\tenableDeleteEvent=" + enableDeleteEvent);
		// Bind behaviours to node policies
		// RB: it is necessary to fire event creation after commit in order to
		// have to the event attributes in the email template.
		//policyComponent.bindClassBehaviour(OnCreateNodePolicy.QNAME, ContentModel.TYPE_CONTENT, new JavaBehaviour(this, "onCreateNode", NotificationFrequency.TRANSACTION_COMMIT));
		//policyComponent.bindClassBehaviour(OnUpdatePropertiesPolicy.QNAME, ContentModel.TYPE_CONTENT, new JavaBehaviour(this, "onUpdateProperties", NotificationFrequency.TRANSACTION_COMMIT));

		// to have access to the deleted node we need to work BEFORE node deletion and at first event because at transaction commit the node is deleted ...
		//policyComponent.bindClassBehaviour(BeforeDeleteNodePolicy.QNAME, ContentModel.TYPE_CONTENT, new JavaBehaviour(this, "beforeDeleteNode", NotificationFrequency.FIRST_EVENT));

	}

	public void onCreateNode(ChildAssociationRef assoNode) {
		logger.debug("############## NotificationPolicy.onCreateNode()");
		// test storeRef to avoid to work outside a valid context
		NodeRef childRef = assoNode.getChildRef();
		if (isNodeValide(childRef)) {
			String eventType = "create";
			NodeRef nodeToCreate = childRef;
			manage(nodeToCreate, assoNode, eventType);
		}
		String string = childRef.getStoreRef().toString();
		if (string.equals("archive://SpacesStore")) {
			// node archived
			logger.debug("node archived :" + childRef);
		}

	}

	public void onUpdateProperties(NodeRef node, Map<QName, Serializable> arg1, Map<QName, Serializable> arg2) {
		logger.debug("############## NotificationPolicy.onUpdateProperties()");
		// test storeRef to avoid to work outside a valid context
		if (isNodeValide(node)) {

			// do not send notification if a previous creation notification has
			// been sent
			// just some time before, let's say 5 mn
			Date createdDate = DefaultTypeConverter.INSTANCE.convert(Date.class, serviceRegistry.getNodeService().getProperty(node, ContentModel.PROP_CREATED));
			Date currentDate = new Date();
			if (currentDate.getTime() - createdDate.getTime() > Integer.parseInt(timeBetweenUpdates)) {
				String eventType = "update";
				QName type = serviceRegistry.getNodeService().getType(node);
				boolean isDocument = serviceRegistry.getDictionaryService().isSubClass(type, ContentModel.TYPE_CONTENT);
				ChildAssociationRef primaryParent = null;
				if (isDocument) {
					primaryParent = serviceRegistry.getNodeService().getPrimaryParent(node);

				}
				manage(node, primaryParent, eventType);
			} else {
				logger.debug("Do nothing because node has just been created");
			}
		}
	}

	public void beforeDeleteNode(NodeRef nodeRef) {
		logger.debug("############## NotificationPolicy.onDeleteNode()");
		// test storeRef to avoid to work outside a valid context

		String eventType = "delete";
		if (isNodeValide(nodeRef)) {
			NodeRef nodeToCreate = nodeRef;
			manage(nodeToCreate, null, eventType);
			return;
		}

	}

	private void manage(NodeRef node, ChildAssociationRef assoNode, String eventType) {
		QName type = serviceRegistry.getNodeService().getType(node);
		boolean isDocument = serviceRegistry.getDictionaryService().isSubClass(type, ContentModel.TYPE_CONTENT);
		logger.debug("manage type :" + type);
		try {
			// dispatch according to nodeType
			if (type.toString().equals(NotificationHelper.TYPE_CALANDAR_EVENT)) {
				logger.debug("is an Event");
				manageEvents(node, assoNode, eventType);
			} else if (isDocument) {
				// other document
				logger.debug("is a Document");
				manageDocuments(node, assoNode, eventType);
			} else if (type.toString().equals(ContentModel.TYPE_PERSON)) {
				logger.debug("is a Person");
				managePerson(node, eventType);
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new AlfrescoRuntimeException(e.getMessage(), e);
		}
	}

	private void managePerson(NodeRef node, String eventType) {
		// can be used to manage a cache so if user profile changed cache must
		// be updated
	}

	private void manageEvents(NodeRef event, ChildAssociationRef arg0, String eventType) throws Exception {
		QName type = serviceRegistry.getNodeService().getType(event);

		String profileNotificationEventNew = null;
		if (type.toString().equals(NotificationHelper.TYPE_CALANDAR_EVENT)) {
			// event notification
			logger.debug("NotificationPolicy.manageEvents() eventType :" + eventType);
			if (eventType.equals("create") && enableCreateEvent) {
				profileNotificationEventNew = NotificationHelper.profile_notification_event_new;
			} else if (eventType.equals("update") && enableUpdateEvent) {
				profileNotificationEventNew = NotificationHelper.profile_notification_event_change;
			} else if (eventType.equals("delete") && enableDeleteEvent) {
				profileNotificationEventNew = NotificationHelper.profile_notification_event_delete;
			}

			if (profileNotificationEventNew != null) {

				if (arg0 == null) {
					arg0 = serviceRegistry.getNodeService().getPrimaryParent(event);
				}
				NodeRef parent = arg0.getParentRef();
				String siteShortName = getSiteShortName(parent);
				List<String> list = getSiteMembers(siteShortName);
				if (list != null) {
					notifyNewDocumentSubscriber(list, profileNotificationEventNew, event, siteShortName);
				} else {
					logger.debug("Event is not in Site (notification disabled");
					// not in Site
				}
			} else {
				logger.debug("Policy configuration disable " + eventType + " events on Calandar Events");
			}
		}
	}

	private void manageDocuments(NodeRef document, ChildAssociationRef arg0, String eventType) throws Exception {
		// but need to check that document is in document library avoid notification about blog, forum, dataType ...

		String profileNotificationEventNew = null;
		// get the event key to let notifyNewDocumentSubscriber to choose the
		// right email template
		if (eventType.equals("create") && enableCreateDocument) {
			profileNotificationEventNew = NotificationHelper.profile_notification_document_new;
		} else if (eventType.equals("update") && enableUpdateDocument) {
			profileNotificationEventNew = NotificationHelper.profile_notification_document_change;
		} else if (eventType.equals("delete") && enableDeleteDocument) {
			profileNotificationEventNew = NotificationHelper.profile_notification_document_delete;
		}

		if (profileNotificationEventNew != null) {
			if (arg0 == null) {
				arg0 = serviceRegistry.getNodeService().getPrimaryParent(document);
			}

			NodeRef parent = arg0.getParentRef();
			String siteShortName = getSiteShortName(parent);
			if (siteShortName != null) {
				List<String> list = getSiteMembers(siteShortName);
				if (list != null) {
					notifyNewDocumentSubscriber(list, profileNotificationEventNew, document, siteShortName);
				}
			} else {
				logger.debug("Document is not in Site (notification disabled");
				// not in Site
			}
		} else {
			logger.debug("Policy configuration disable " + eventType + " events on Document");
		}

	}

	private List<String> getSiteMembers(String siteShortName) {
		List<String> authorities = null;

		if (siteShortName != null) {
			// get Authority site members (can be GROUP or USER)
			Map<String, String> list = serviceRegistry.getSiteService().listMembers(siteShortName, null, null, -1);
			authorities = new ArrayList<String>(list.keySet());
			logger.debug("Site (" + siteShortName + ") Members :" + authorities);
		}

		return authorities;
	}

	protected String getSiteShortName(NodeRef node) {
		Path path = serviceRegistry.getNodeService().getPath(node);

		logger.debug("Node path :" + path);
		String regExpSiteName = ".*" + Pattern.quote(SiteModel.TYPE_SITES.toString()) + "/\\{[^}]*\\}([^/]+)/.*";

		String pathString = path.toString();
		String siteShortName = null;
		if (Pattern.matches(regExpSiteName, pathString)) {
			// get site short name
			siteShortName = pathString.replaceFirst(regExpSiteName, "$1");
		}
		return siteShortName;
	}

	protected void manageUserPreferenceChange(NodeRef arg0, Map<QName, Serializable> oldProperties, Map<QName, Serializable> newProperties) {
		// if user change preference update cached email list (document in
		// dictionary ?)
		// test if Node is person and if property changed is preferences
		if (serviceRegistry.getNodeService().getType(arg0).equals(ContentModel.TYPE_PERSON)) {
			Serializable oldPreferences = oldProperties.get(ContentModel.PROP_PREFERENCE_VALUES);
			Serializable newPreferences = newProperties.get(ContentModel.PROP_PREFERENCE_VALUES);
			if (!oldPreferences.equals(newPreferences)) {
				// must update cached preferences
				// get xml/json document from repository and update it
				// TODO
				logger.warn("NOT YET IMPLEMENTED !!!");
			}
		}
	}

	protected void notifyNewDocumentSubscriber(List<String> userIds, String notify_key, final NodeRef document, final String siteShortName) throws Exception {
		// use Helper to sendMail
		// filter user according to preferences and group them by language

		List<String> userIds_filtered = notificationHelper.getPersonList(userIds);
		GroupByMap<String> gblanguage = new GroupByMap<String>();

		for (final String user : userIds_filtered) {
			NodeRef person = serviceRegistry.getPersonService().getPerson(user);
			logger.debug("notifyNewDocumentSubscriber Test permission for :" + user + " on document " + document);
			RunAsWork<Object> arg0 = new RunAsWork<Object>() {

				public Object doWork() throws Exception {
					logger.debug("current user :" + user);
					try {
						boolean readPermission = false;
						PermissionService permissionService = serviceRegistry.getPermissionService();
						AccessStatus hasPermission = permissionService.hasPermission(document, "Consumer");
						if (hasPermission != null) {
							readPermission = hasPermission.equals(AccessStatus.ALLOWED);
							logger.debug("check Consumer permission :" + readPermission);
						}
						return readPermission;
					} catch (Exception e) {
						logger.debug(e.getMessage(), e);
					}
					return false;
				}
			};
			Boolean runAs = (Boolean) AuthenticationUtil.runAs(arg0, user);
			if (runAs) {
				Boolean userPreference = (Boolean) notificationHelper.getUserPreference(person, notify_key);
				if (userPreference) {
					gblanguage.put(getUserLanguage(person), user);
				}
				logger.debug(user + " preference for " + notify_key + " :" + userPreference);
			} else {
				logger.debug("user :" + user + " do not have read permission on " + document);
			}

		}

		logger.debug("userGroup :" + gblanguage);

		for (Map.Entry<Object, List<String>> entry : gblanguage.getEntrySet()) {
			// send mail for users with language
			List<String> users = entry.getValue();
			String language = (String) entry.getKey();
			notificationHelper.sendMails(users, notify_key, document, language, null);
		}
	}

	protected String getUserLanguage(NodeRef user) throws Exception {
		return (String) notificationHelper.getUserPreference(user, NotificationHelper.profile_language);
	}

	protected boolean isNodeValide(NodeRef node) {
		// test if this policy can run on this node
		boolean valide = true;
		try {
			String string = node.getStoreRef().toString();
			if (!string.equals("workspace://SpacesStore")) {
				logger.debug("Do nothing because node in :" + string);
				return false;
			}
			if (!serviceRegistry.getNodeService().exists(node)) {
				logger.debug("Do nothing because node do not exist :" + node);
				return false;
			}
			if (serviceRegistry.getNodeService().getType(node).equals(ContentModel.TYPE_THUMBNAIL)) {
				logger.debug("Do nothing because Node is a thumbnail :" + node);
				return false;
			}
		} catch (Exception e) {
			return false;
		}

		return valide;
	}

}
