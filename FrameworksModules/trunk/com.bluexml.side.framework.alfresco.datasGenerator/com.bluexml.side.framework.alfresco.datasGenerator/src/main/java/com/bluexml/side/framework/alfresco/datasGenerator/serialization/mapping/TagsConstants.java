/**
 * 
 */
package com.bluexml.side.framework.alfresco.datasGenerator.serialization.mapping;

import java.util.HashMap;
import java.util.Map;

import org.alfresco.model.BlogIntegrationModel;
import org.alfresco.model.ContentModel;
import org.alfresco.repo.invitation.WorkflowModelModeratedInvitation;
import org.alfresco.repo.invitation.WorkflowModelNominatedInvitation;
import org.alfresco.repo.module.ModuleComponentHelper;
import org.alfresco.repo.site.SiteModel;
import org.alfresco.repo.version.Version2Model;
import org.alfresco.service.ServiceRegistry;
import org.alfresco.service.namespace.NamespaceService;
import org.alfresco.service.namespace.QName;
import org.springframework.context.ApplicationContext;

import com.bluexml.side.framework.alfresco.datasGenerator.context.SpringContext;
import com.bluexml.side.framework.alfresco.datasGenerator.dictionary.AlfrescoModelDictionary;

/**
 * @author davidchevrier
 *
 */
public class TagsConstants {

	public static final String PRINCIPAL_TAG = "view";
	public static final String TAG_ARG_CHILDNAME = "childName";
	public static final String TAG_ARG_PATHREF = "pathref";
	public static final String TAG_ARG_ASSOCIATION = "reference";
	public static final String TAG_ARG_PROPERTIES = "properties";
	public static final String TAG_ARG_ASSOCIATIONS = "associations";
	public static final Object TAG_ARG_ACL = "acl";
	
	public static final String HEADER = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>";
	
	public static final String OPEN_TAG = "<";
	public static final String END_TAG = ">";
	public static final String OPEN_CLOSING_TAG = "</";
	public static final String DECLARATIVE_PREFIX_NAMESPACE = "xmlns";
	public static final String SEPARATOR = ":";
	public static final String BLANK_SEPARATOR = " ";
	public static final String DEFINITION_SEPARATOR  = "=";
	public static final String URI_BRAKET = "\"";
	public static final String BLANK_REPLACE_IN_PATHREF = "_x0020_";
	
	public static final QName[] SERVICES_QNAMES = new QName[]{
		ServiceRegistry.CMIS_SERVICE,
		ServiceRegistry.ACTION_SERVICE,
		ServiceRegistry.VERSION_SERVICE,
		ServiceRegistry.RULE_SERVICE,
		ServiceRegistry.IMAP_SERVICE,
		ServiceRegistry.REGISTRY_SERVICE,
		ServiceRegistry.SITE_SERVICE
	};
	
	public static final Map<String,String> NATIVE_ATTRIBUTES_FOR_OPEN_TAG = Methods.getNativeAttributesOpenTag();
	public static final Map<String,String> SERVICES_ATTRIBUTES_FOR_OPEN_TAG = Methods.getServiceAttributesOpenTag();
	public static final Map<String,String> OTHERS_ATTRIBUTES_FOR_OPEN_TAG = Methods.getOtherAttributesOpenTag();
	public static final Map<String,String> SIDE_ATTRIBUTES_FOR_OPEN_TAG = Methods.getSideAttributesOpenTag();
	
	//public static final Map<String,String> NATIVE_TAG_FOR_PROPERTIES = Methods.getNativeTagsForProperties();
	
	private static class Methods {
		
		private static Map<String,String> getNativeAttributesOpenTag(){
			Map<String,String> nativeAttributes = new HashMap<String, String>();
			nativeAttributes.put(NamespaceService.APP_MODEL_1_0_URI, NamespaceService.APP_MODEL_PREFIX);
			nativeAttributes.put(NamespaceService.WCM_MODEL_1_0_URI, NamespaceService.WCM_MODEL_PREFIX);
			nativeAttributes.put(NamespaceService.WCMWF_MODEL_1_0_URI, NamespaceService.WCMWF_MODEL);
			nativeAttributes.put(NamespaceService.FORUMS_MODEL_1_0_URI, NamespaceService.FORUMS_MODEL_PREFIX);
			nativeAttributes.put(NamespaceService.EMAILSERVER_MODEL_URI, NamespaceService.EMAILSERVER_MODEL_PREFIX);
			nativeAttributes.put(NamespaceService.REPOSITORY_VIEW_1_0_URI, NamespaceService.REPOSITORY_VIEW_PREFIX);
			nativeAttributes.put(NamespaceService.DICTIONARY_MODEL_1_0_URI, NamespaceService.DICTIONARY_MODEL_PREFIX);
			nativeAttributes.put(NamespaceService.ALFRESCO_URI, NamespaceService.ALFRESCO_PREFIX);
			nativeAttributes.put(NamespaceService.WORKFLOW_MODEL_1_0_URI, NamespaceService.WORKFLOW_MODEL_PREFIX);
			nativeAttributes.put(NamespaceService.CONTENT_MODEL_1_0_URI, NamespaceService.CONTENT_MODEL_PREFIX);
			nativeAttributes.put(NamespaceService.WCMAPP_MODEL_1_0_URI, NamespaceService.WCMAPP_MODEL_PREFIX);
			nativeAttributes.put(NamespaceService.SYSTEM_MODEL_1_0_URI, NamespaceService.SYSTEM_MODEL_PREFIX);
			nativeAttributes.put(NamespaceService.BPM_MODEL_1_0_URI, NamespaceService.BPM_MODEL_PREFIX);
			return nativeAttributes;
		}
		
		private static Map<String,String> getServiceAttributesOpenTag(){
			Map<String,String> serviceAttributes = new HashMap<String, String>();
			for (int indexQName = 0; indexQName < SERVICES_QNAMES.length; indexQName++){
				String prefix = SERVICES_QNAMES[indexQName].getPrefixString();
				String uri = SERVICES_QNAMES[indexQName].getNamespaceURI();
				serviceAttributes.put(uri, prefix);
			}
			return serviceAttributes;
		}
		
		private static Map<String,String> getOtherAttributesOpenTag(){
			Map<String,String> otherAttributes = new HashMap<String, String>();
			otherAttributes.put(ContentModel.USER_MODEL_URI, ContentModel.USER_MODEL_PREFIX);
			otherAttributes.put(BlogIntegrationModel.MODEL_URL, BlogIntegrationModel.MODEL_PREFIX);
			otherAttributes.put(WorkflowModelNominatedInvitation.NAMESPACE_URI, "inwf");
			otherAttributes.put(WorkflowModelModeratedInvitation.NAMESPACE_URI, "imwf");
			otherAttributes.put(SiteModel.SITE_CUSTOM_PROPERTY_URL, SiteModel.SITE_CUSTOM_PROPERTY_PREFIX);
			otherAttributes.put(ModuleComponentHelper.URI_MODULES_1_0, "module");
			otherAttributes.put(Version2Model.NAMESPACE_URI, "ver2");
			otherAttributes.put("http://www.alfresco.org/model/linksmodel/1.0","lnk");
			otherAttributes.put("http://www.alfresco.org/model/calendar","ia");
			otherAttributes.put("http://www.jcp.org/jcr/mix/1.0","mix");
			otherAttributes.put("http://www.jcp.org/jcr/1.0","jcr");
			otherAttributes.put("http://www.jcp.org/jcr/nt/1.0","nt");
			otherAttributes.put("http://www.jcp.org/jcr/sv/1.0","sv");
			otherAttributes.put("custom.model", "custom");
			return otherAttributes;
		}

		private static Map<String, String> getSideAttributesOpenTag() {
			Map<String,String> sideAttributes = new HashMap<String, String>();
			sideAttributes.put("http://www.bluexml.com/model/content/1.0", "bxcm");
			ApplicationContext ctx = SpringContext.getContext();
			AlfrescoModelDictionary dictionary = (AlfrescoModelDictionary) ctx.getBean("alfrescoModelDictionary");
			QName model = dictionary.getModel(dictionary.getQnameStringModel()).getName();
			String prefix = (model.getPrefixString().split(SEPARATOR))[0];
			sideAttributes.put(model.getNamespaceURI(),prefix);
			return sideAttributes;
		}

		//private static Map<String, String> getNativeTagsForProperties() {
			//Map<String,String> nativeTags = new HashMap<String,String>();
			//Collection<QNamePattern> nativeProperties = ((NativeAlfrescoModelStructure) getStructureBean()).getNativeMandatoryProperties();
			//for (QNamePattern nativeProperty : nativeProperties) {
				//String uri = ((QName) nativeProperty).getNamespaceURI();
				//String prefix = ((QName) nativeProperty).getPrefixString();
				//nativeTags.put(uri,prefix);
			//}
			//return nativeTags;
		//}
		
		//private static IStructure getStructureBean(){
			//ApplicationContext ctx = SpringContext.getContext();
			//return (IStructure) ctx.getBean("nativeAlfrescoModelStructure");
		//}
		
	}
	
}
