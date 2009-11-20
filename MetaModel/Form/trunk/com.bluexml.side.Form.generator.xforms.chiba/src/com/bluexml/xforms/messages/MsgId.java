/**
 * NE PAS FORMATTER CE FICHIER.
 */
package com.bluexml.xforms.messages;

/**
 * Classe d'indirection pour les messages indiquant les clés autorisées via une
 * énumération. Elle permet de découpler le nom choisi pour les clés de leur
 * utilisation dans le reste du code, en centralisant le texte ici pour une 
 * modification effective partout. La plupart de ces chaînes ne sont pas 
 * modifiées par l'utilisateur sauf celles préfixées par "MSG_" ou "CAPTION_".<p/>
 * Préfixes utilisés:
 * <ul>
 * <li>KEY: clés dans le fichier forms.properties.</li>
 * <li>MSG: messages dans le fichier messages.properties.</li>
 * <li>CAPTION: labels pour boutons usuels du formulaire.</li>
 * <li>INT: chaînes utilisées en interne, dans le code; objectif: centraliser.</li>
 * <li>PARAM: noms de paramètres admis dans l'url.</li>
 * </ul>
 *
 * @author Amenel
 *
 */
public enum MsgId {
	// keys for messages in the forms.properties file
	KEY_ALFRESCO_URL("alfresco.url"),
	/** The key for the complete path when uploading to file system.*/
	KEY_UPLOAD_DIRECTORY("upload.directory"),
	KEY_TEMP_DIRECTORY("temp.directory"),
	/** The key for the repository upload path.*/
	KEY_UPLOAD_REPOSITORY("upload.repository"),
	KEY_USER_NAME("user.name"),
	KEY_USER_PSWD("user.pswd"),
	KEY_MAX_RESULTS("max.results"),
	// keys for messages in the message.properties file; user-changeable
	//
	MSG_ASSOC_MANDATORY_PART1		("association.mandatory.part1"),
	MSG_ASSOC_MANDATORY_PART2		("association.mandatory.part2"),
	MSG_FIELD_MANDATORY_PART1		("field.mandatory.part1"),
	MSG_FIELD_MANDATORY_PART2		("field.mandatory.part2"),
	MSG_KEY_NOT_FOUND				("key.not.found"),
	MSG_LENGTH_BETWEEN_PART1		("length.between.part1"),
	MSG_LENGTH_BETWEEN_PART2		("length.between.part2"),
	MSG_LENGTH_MAXIMAL				("length.maximal"),
	MSG_LENGTH_MINIMAL				("length.minimal"),
	MSG_LENGTH_POST_INFO_PART1		("length.post.info.part1"),
	MSG_LENGTH_POST_INFO_PART2		("length.post.info.part2"),	
	MSG_SELECT_LIST_MANDATORY		("selection.list.label.mandatory"),
	MSG_SELECT_LIST_SHOW_ALL		("selection.list.show.all.results"),
	MSG_SESSION_TIMED_OUT			("session.timed.out"),
	MSG_STATUS_DELETE_SUCCESS		("status.message.delete.success"),
	MSG_STATUS_DELETE_FAILURE		("status.message.delete.failure"),
	MSG_STATUS_EDIT_SUCCESS			("status.message.edit.success"),
	MSG_STATUS_EDIT_FAILURE			("status.message.edit.failure"),
	MSG_STATUS_EMPTY				("status.message.empty"),
	MSG_STATUS_ITERATION_AFTER		("status.message.iteration.after"),
	MSG_STATUS_ITERATION_BEFORE		("status.message.iteration.before"),
	MSG_STATUS_SUBMIT_SUCCESS		("status.message.submit.success"),
	MSG_STATUS_SUBMIT_FAILURE		("status.message.submit.failure"),
	MSG_WKFLW_SECTION_LABEL			("workflow.section.label"),
	MSG_WKFLW_SEL_PAGE_TITLE		("workflow.selection.page.title"),
	MSG_WKFLW_GLOBAL_GROUP			("workflow.selection.global.group"),
	MSG_WKFLW_INSTANCE_GROUP		("workflow.selection.instance.group"),
	MSG_WKFLW_PROCESS_GROUP			("workflow.selection.process.group"),
	MSG_WKFLW_INSTANCE_WIDGET_TITLE	("workflow.instance.widget.title"),
	MSG_WKFLW_PROCESS_WIDGET_TITLE	("workflow.process.widget.title"),
	MSG_WKFLW_SUBMIT_DATA_FIRST		("workflow.error.submit.data"),
	MSG_WKFLW_START_FAILURE			("workflow.error.start.failure"),
	// captions for buttons; user-changeable
	//
	CAPTION_BUTTON_CANCEL			("caption.button.cancel"),
	// label for create in selection widget
	CAPTION_BUTTON_CREATE			("caption.button.create"),
	CAPTION_BUTTON_CREATE_CLASS		("caption.button.create.class"),
	CAPTION_BUTTON_CREATE_FORM		("caption.button.create.form"),
	CAPTION_BUTTON_DELETE			("caption.button.delete"),
	// label for modify (edit) in right side of selection widget
	CAPTION_BUTTON_EDIT				("caption.button.edit"),
	CAPTION_BUTTON_EDIT_CLASS		("caption.button.edit.class"),
	CAPTION_BUTTON_EDIT_FORM		("caption.button.edit.form"),
	CAPTION_BUTTON_SUBMIT			("caption.button.submit"),
	CAPTION_BUTTON_WORKFLOW_CANCEL	("caption.button.workflow.cancel"),
	CAPTION_BUTTON_WORKFLOW_SELECT	("caption.button.workflow.select"),
	CAPTION_BUTTON_WORKFLOW_START	("caption.button.workflow.start"),	
	// shortcuts for internal strings
	//
	INT_ACT_CODE_CANCEL					("cancel"),
	INT_ACT_CODE_CREATE_CLASS			("create"),
	INT_ACT_CODE_CREATE_FORM			("createForm"),
	INT_ACT_CODE_DELETE					("delete"),
	INT_ACT_CODE_EDIT_CLASS				("edit"),
	INT_ACT_CODE_EDIT_FORM				("editForm"),
	INT_ACT_CODE_ENUM					("enum"),
	INT_ACT_CODE_EXECUTE				("execute"),
	INT_ACT_CODE_GET_FORM				("get"),
	INT_ACT_CODE_GET_FORM_WKFLW			("wrkflwfrm-get"),
	INT_ACT_CODE_GET_WKFLW_SELECTION	("process-get"),
	INT_ACT_CODE_I18N					("i18n"),
	INT_ACT_CODE_LIST					("list"),
	INT_ACT_CODE_SETTYPE				("settype"),
	INT_ACT_CODE_SUBMIT					("submit"),
	INT_ACT_CODE_WRKFLW_INSTANCE_LIST	("instance-list"),
	INT_ACT_CODE_WRKFLW_PROCESS_LIST	("process-list"),
	INT_ACT_CODE_WRKFLW_START			("workflow-start"),
	INT_ACT_CODE_WRKFLW_TRANSITION		("transition"),
	INT_ACT_PARAM_LIST_FORMAT			("format"),
	INT_ACT_PARAM_LIST_MAXLENGTH		("maxLength"),
	INT_ACT_PARAM_LIST_TYPE				("type"),
	INT_BLUEXML_DEFAULT_STORE_PATH		("/app:company_home/app:dictionary/cm:SIDE DATA"),
	INT_ERR_NULL_WKFLW_INSTANCE_PATHS	("No paths for instance: "),
	INT_ERR_NULL_WKFLW_ACTIVE_PATHS		("No active path for instance: "),
	INT_EXC_ASSOCIATION_ENDS			("Illegal association: both ends must be classes."),
	INT_EXC_WKFLW_GET_INSTANCE			("Exception getting instance with id: "),
	INT_EXC_WKFLW_GET_INSTANCE_PATHS	("Exception getting paths for instance: "),
	INT_DIRECTORY_FORM_FORMS			("forms"),
	INT_DIRECTORY_FORM_LISTS			("lists"),
	INT_DIRECTORY_FORM_WKFLW			("workflow"),
	INT_DIRECTORY_WKFLW_SEL_FORM		("workflow"),
	INT_FORMTYPE_FORM					("form"),
	INT_FORMTYPE_LIST					("list"),
	INT_FORMTYPE_WKFLW					("wkflw"),
	INT_FORMTYPE_WKFLWSEL				("wkflwSel"),
	INT_GEN_DYN_ENUM_PREFIX				("$"),
	INT_GEN_DYN_ENUM_PREFIX_CONTEXT		("enumContext"),
	INT_GEN_DYN_ENUM_PREFIX_PARENT		("enumParent"),
	INT_GEN_ID_OBJECTMODEL				("objectmodel"),
	INT_GEN_PLACEHOLDER_SESSION_ID		("{@sessionid}"),
	INT_GEN_PLACEHOLDER_CONTEXT_PATH	("{@contextpath}"),
	INT_GEN_PREFIX_BIND_FORM			("bind"),
	INT_GEN_PREFIX_BIND_WKFLW			("wkflwbind"),
	INT_GEN_PREFIX_SUBMIT_FORM			("submit"),
	INT_GEN_PREFIX_SUBMIT_WKFLW			("wkflwsubmit"),
	INT_GEN_REDIRECT_ADD_PARAMS			("addParams"),
	INT_GEN_REDIRECT_AUTO_ADVANCE		("auto"),
	INT_GEN_REDIRECT_ENTRY				("entry"),
	INT_GEN_REDIRECT_NAME				("name"),
	INT_GEN_REDIRECT_URL				("url"),
	INT_INSTANCE_SIDE_DATATYPE			("SIDEDataType"),
	INT_INSTANCE_SIDE_EDITEDID			("SIDEEditedId"),
	INT_INSTANCE_SIDEID					("SIDEID"),
	INT_INSTANCE_SIDELABEL				("SIDELABEL"),
	INT_INSTANCE_SELECTEDID				("SELECTEDID"),
	INT_INSTANCE_SELECTEDLABEL			("SELECTEDLABEL"),
	INT_INSTANCE_SELECTEDMAX			("SELECTEDMAX"),
	INT_INSTANCE_SELECTEDNUMBER			("SELECTEDNB"),
	INT_CSS_STATUS_BAR_ID				("SIDE_XForms_Status"),
	INT_CSS_CLASS_LINE					("SIDE_XForms_Line"),
	INT_CSS_CLASS_BLUEXML_AUTOGEN		("bluexml-autogen"),
	INT_SERVLET_REDIRECTOR_PATH			("redirect"),
	INT_SUBMIT_BUTTONS_GROUP_ID			("submitButtons"),
	INT_URL_SCHEME_READER				("sidereader://{@sessionid}/"),
	INT_URL_SCHEME_WRITER				("sidewriter://{@sessionid}/"),
	INT_NAMESPACE_XHTML					("http://www.w3.org/1999/xhtml"),
	INT_NAMESPACE_XFORMS				("http://www.w3.org/2002/xforms"),
	INT_MSGPOOL_NO_MESSAGE_FILE			("INVALID MESSAGE FILE!"),
	INT_WEBSCRIPT_AUTHENTICATE			("auth"),
	INT_WEBSCRIPT_BATCH					("batch"),
	INT_WEBSCRIPT_DELETE				("delete"),
	INT_WEBSCRIPT_ENUM					("enum"),
	INT_WEBSCRIPT_HELP					("help"),
	INT_WEBSCRIPT_LABELS				("labels"),
	INT_WEBSCRIPT_LIST					("list"),
	INT_WEBSCRIPT_MKDIR					("mkdir"),
	INT_WEBSCRIPT_PACKAGE				("package"),
	INT_WEBSCRIPT_READ					("read"),
	INT_WEBSCRIPT_SERVICE				("service"),
	INT_WEBSCRIPT_UPLOAD				("upload"),
	INT_WEBSCRIPT_WORKFLOW				("workflow"),
	//
	// these are used when generating the workflow selection page
	INT_WKFLW_SEL_FORM_FILENAME			("ProcessSelection"),
	INT_WKFLW_INSTANCE_INSTANCE_NAME	("InstanceInstance"),
	INT_WKFLW_NODESET_PREFIX			("workflow"),
	INT_WKFLW_PROCESS_INSTANCE_NAME		("ProcessInstance"),
	//// caution: the nodeset prefix must be the actual prefix in all nodesets
	INT_WKFLW_PROCESS_NODESET			("process"),
	INT_WKFLW_INSTANCE_NODESET			("instance"),
	INT_WKFLW_PROCESS_WIDGET_NAME		("workflow_definitions"),
	INT_WKFLW_INSTANCE_WIDGET_NAME		("workflow_instances"),
	// must end with a trailing slash
	INT_WKFKW_BLUEXML_NAMESPACE			("http://www.bluexml.com/model/workflow/"),
	//
	// these are used when expanding xforms instances with wkflw data
	INT_WKFLW_INSTANCE_TAG_WKFLW		("workflow"),
	INT_WKFLW_INSTANCE_TAG_PROCESS_ID	("processId"),
	INT_WKFLW_INSTANCE_TAG_INSTANCE_ID	("pathId"),
	//
	// these are used when expanding xforms file templates with wkflw
	INT_WKFLW_TMPLT_WKFLW_GROUP_ID		("workflow_section"),
	// strings by which we accept parameters in the url
	//
	PARAM_SKIP_ADDITIONAL_INFO("skipAdditionalInfo"),
	/** the name of a Java class to be called */
	PARAM_ACTION_NAME ("actionName"),
	/** the address (protocol, host and port) to the Alfresco server*/
	PARAM_ALFRESCO_HOST("alfrescoHost"),
	/** whether non BlueXML definitions should also be listed*/
	PARAM_ALLOW_ALL_DEFS ("allowAllDefs"),
	/** Used when redirecting from a workflow form to continually progress along a workflow path*/
	PARAM_AUTO_ADVANCE ("autoAdvance"),
	/** the HTTP address of a CSS file to include in xhtml templates sent to clients*/
	PARAM_CSS_FILE("CSS"),
	PARAM_DATA_ID("id"),
	PARAM_DATA_TYPE("type"),
	PARAM_FORM_TYPE("formType"),
	/** specifies a dummy call whose returned result will be "success" or "failure"*/
	PARAM_INIT_CALL("init"),
	/** output param for redirection; indicates which workflow form the redirection comes from*/ 
	PARAM_LEAVING_FORM("leavingForm"),
	PARAM_LANGUAGE ("language"),
	PARAM_PAGE_SUCCESS ("successPage"),
	PARAM_PAGE_FAILURE ("failurePage"),
	/** filesystem path to the messages.properties file*/
	PARAM_MESSAGES_PROPERTIES_FILE ("messagesPropertiesFile"),
	/** filesystem path to the forms.properties file*/
	PARAM_FORMS_PROPERTIES_FILE ("formsPropertiesFile"),
	/** the URL the page being left was called with */
	@Deprecated
	PARAM_REDIRECT_FROM_URL("redirectFromUrl"),
	/** filesystem path to the redirect.xml file*/
	PARAM_REDIRECTOR_CONFIG_FILE("redirectXmlFile"),
	/** whether to reload properties and config files*/
	PARAM_RELOAD_PROPERTIES("reloadProperties"),
	/** whether to reload the mapping.xml file*/
	PARAM_RELOAD_MAPPING_FILE("dynamicReload"),
	PARAM_SHOW_SUBMITS("showSubmitButtons"),
	/** whether to avoid contacting the webscript on Alfresco*/
	PARAM_STANDALONE("standalone"),
	/** originally for passing the status msg to client URL, now also considered as an input*/
	PARAM_STATUS_MSG("statusMsg"),
	PARAM_USER_NAME("userName"),
	PARAM_USER_PSWD("userPswd"),
	PARAM_UPLOAD_DIRECTORY("uploadDirectory"),
	PARAM_UPLOAD_REPOSITORY("uploadRepository"),
	PARAM_WORKFLOW_INSTANCE_ID("workflowInstanceId"),
	PARAM_WORKFLOW_PROCESS_ID("workflowProcessId");

	private final String associatedKeyText;
	
	MsgId(String keyText) {
		this.associatedKeyText = keyText;
	}
	
	public String getText() {
		return associatedKeyText;
	}
	
	@Override
	public String toString() {
		return getText();
	}
}
