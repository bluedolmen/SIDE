/**
 * PLEASE DO NOT FORMAT THIS FILE.
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
 * NOTES:
 * <ul>
 * <li>form types must be kept in sync with the forms.jsp</li>
 * </ul>
 *
 * @author Amenel
 *
 */
public enum MsgId {
	//
	//
	// keys for messages in the forms.properties file
	//
	KEY_ALFRESCO_URL("alfresco.url"),
	KEY_CHECK_MATCH_DATA_FORM("check.match.data.and.form"),
	KEY_MAX_RESULTS("selection.list.max.results"),
	KEY_TEMP_DIRECTORY("temp.directory"),
	/** The key for the complete path when uploading to file system.*/
	KEY_UPLOAD_DIRECTORY("upload.directory"),
	KEY_UPLOAD_DIR_PATH_DEPTH("upload.directory.random.path.depth"),
	/** The key for the repository upload path.*/
	KEY_UPLOAD_REPOSITORY("upload.repository"),
	KEY_UPLOAD_REPOSITORY_APPEND("upload.repository.append.suffix"),
	KEY_UPLOAD_REPOSITORY_FORMAT_INFO("upload.repository.format.info"),
	KEY_USER_NAME("user.name"),
	KEY_USER_PSWD("user.pswd"),
	//
	//
	// keys for messages in the messages.properties file; user-changeable
	//
	MSG_ASSOC_MANDATORY				("association.is.mandatory"),
	MSG_DEFAULT_ERROR_MSG			("default.error.msg"), 
	MSG_FIELD_LABEL_FORMAT			("field.label.format"),
	MSG_FIELD_MANDATORY				("field.is.mandatory"),
	MSG_FILE_FIELD_FILENAME_TEMP	("file.field.filename.temporary"),
	MSG_FILE_FIELD_LABEL			("file.field.label"),
	MSG_FILL_MANDATORY_FIELDS		("fill.mandatory.fields"),
	MSG_FORMAT_DATE_OUTPUT			("format.date.output"),
	MSG_FORMAT_TIME_OUTPUT			("format.time.output"),
	MSG_INTEGRITY_VIOLATION			("integrity.violation"),
	MSG_INVALID_REGEX_FORMAT		("invalid.regex.format"),
	MSG_KEY_NOT_FOUND				("key.not.found"),
	MSG_LENGTH_BETWEEN				("length.between.min.and.max"),
	MSG_LENGTH_MAXIMAL				("length.maximal"),
	MSG_LENGTH_MINIMAL				("length.minimal"),
	MSG_SEARCH_OPERATOR_ABOVE		("search.operator.above"),
	MSG_SEARCH_OPERATOR_AFTER		("search.operator.after"),
	MSG_SEARCH_OPERATOR_ALLBUT		("search.operator.allBut"),
	MSG_SEARCH_OPERATOR_BEFORE		("search.operator.before"),
	MSG_SEARCH_OPERATOR_BELOW		("search.operator.below"),
	MSG_SEARCH_OPERATOR_BETWEEN		("search.operator.between"),
	MSG_SEARCH_OPERATOR_CONTAINS	("search.operator.contains"),
	MSG_SEARCH_OPERATOR_CONTENTS	("search.operator.contents"),
	MSG_SEARCH_OPERATOR_EMPTY		("search.operator.empty"),
	MSG_SEARCH_OPERATOR_ENDSWITH	("search.operator.endsWith"),
	MSG_SEARCH_OPERATOR_EXACTLY		("search.operator.exactly"),
	MSG_SEARCH_OPERATOR_FILETYPE	("search.operator.fileType"),
	MSG_SEARCH_OPERATOR_ICONTAINS	("search.operator.icontains"),
	MSG_SEARCH_OPERATOR_IENDSWITH	("search.operator.iendsWith"),
	MSG_SEARCH_OPERATOR_IGNORE		("search.operator.ignore"),
	MSG_SEARCH_OPERATOR_IS			("search.operator.is"),
	MSG_SEARCH_OPERATOR_ISNOT		("search.operator.isNot"),
	MSG_SEARCH_OPERATOR_ISTARTSWITH	("search.operator.istartsWith"),
	MSG_SEARCH_OPERATOR_NONE		("search.operator.none"),
	MSG_SEARCH_OPERATOR_NOTBETWEEN	("search.operator.notBetween"),
	MSG_SEARCH_OPERATOR_ONEOF		("search.operator.oneOf"),
	MSG_SEARCH_OPERATOR_SIZE		("search.operator.size"),
	MSG_SEARCH_OPERATOR_STARTSWITH	("search.operator.startsWith"),
	MSG_SELECT_LIST_LABEL			("selection.list.label"),
	MSG_SELECT_LIST_SEARCH_LABEL	("selection.list.search.label"),
	MSG_SELECT_LIST_SHOW_ALL		("selection.list.show.all.results"),
	MSG_SESSION_TIMED_OUT			("session.timed.out"),
	MSG_STATUS_CREATE_FAILURE		("status.message.create.failure"),
	MSG_STATUS_CREATE_SUCCESS		("status.message.create.success"),
	MSG_STATUS_DELETE_FAILURE		("status.message.delete.failure"),
	MSG_STATUS_DELETE_SUCCESS		("status.message.delete.success"),
	MSG_STATUS_EDIT_FAILURE			("status.message.edit.failure"),
	MSG_STATUS_EDIT_SUCCESS			("status.message.edit.success"),
	MSG_STATUS_EMPTY				("status.message.empty"),
	MSG_STATUS_ITERATION			("status.message.iteration.postfix"),
	MSG_UPLOAD_CONTENT_FIELD_LABEL	("upload.node.content.field.label"),
	MSG_UPLOAD_CONTENT_GROUP_LABEL	("upload.node.content.group.label"),
	MSG_UPLOAD_CONTENT_NO_CONTENT	("upload.node.content.none"),
	MSG_UPLOAD_CONTENT_REPO_FORMAT	("upload.node.content.repository.format"),
	MSG_UPLOAD_CONTENT_REPO_INFO	("upload.node.content.repository.info"),
	MSG_UPLOAD_FAILED				("upload.to.repository.failure"),
	MSG_WKFLW_ERROR_START_FAILURE	("workflow.error.start.failure"),
	MSG_WKFLW_ERROR_SUBMIT_DATA		("workflow.error.submit.data"),
	MSG_WKFLW_GLOBAL_GROUP			("workflow.selection.global.group"),
	MSG_WKFLW_INSTANCE_GROUP		("workflow.selection.instance.group"),
	MSG_WKFLW_INSTANCE_WIDGET_TITLE	("workflow.instance.widget.title"),
	MSG_WKFLW_PROCESS_GROUP			("workflow.selection.process.group"),
	MSG_WKFLW_PROCESS_WIDGET_TITLE	("workflow.process.widget.title"),
	MSG_WKFLW_SECTION_LABEL			("workflow.section.label"),
	MSG_WKFLW_SEL_PAGE_TITLE		("workflow.selection.page.title"),
	// captions for buttons; user-changeable
	//
	CAPTION_BUTTON_CANCEL			("caption.button.cancel"),
	// label for create in selection widget
	CAPTION_BUTTON_CREATE			("caption.button.create"),
	CAPTION_BUTTON_DELETE			("caption.button.delete"),
	// label for 'Modify' (edit) on right side of selection widget
	CAPTION_BUTTON_EDIT				("caption.button.edit"),
	CAPTION_BUTTON_SEARCH			("caption.button.search"),
	CAPTION_BUTTON_SETTYPE			("caption.button.settype"),
	CAPTION_BUTTON_SUBMIT			("caption.button.submit"),
	CAPTION_BUTTON_WORKFLOW_CANCEL	("caption.button.workflow.cancel"),
	CAPTION_BUTTON_WORKFLOW_SELECT	("caption.button.workflow.select"),
	CAPTION_BUTTON_WORKFLOW_START	("caption.button.workflow.start"),
	//
	//
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
	INT_ACT_CODE_I18N					("i18n"),
	INT_ACT_CODE_LIST					("list"),
	INT_ACT_CODE_SETTYPE				("settype"),
	INT_ACT_CODE_SUBMIT					("submit"),
	INT_ACT_CODE_WRKFLW_TRANSITION		("transition"),
	// names of action parameters, internal to the actions hierarchy
	INT_ACT_PARAM_ANY_DATATYPE			("type"),
	INT_ACT_PARAM_ANY_ID				("id"),
	INT_ACT_PARAM_ANY_ASSOC			("assoc"),
	INT_ACT_PARAM_ENUM_FILTER_DATA		("filterData"),
	INT_ACT_PARAM_ENUM_FILTER_PARENT	("filterParent"),
	INT_ACT_PARAM_ENUM_LIMITED			("limited"),
	INT_ACT_PARAM_ENUM_RAWTYPE			("rawtype"),
	INT_ACT_PARAM_GET_FORMTYPE			("formType"),
	INT_ACT_PARAM_LIST_FORMAT			("format"),
	INT_ACT_PARAM_LIST_MAXLENGTH		("maxLength"),
	INT_ACT_PARAM_LIST_QUERY			("query"),
	INT_ACT_PARAM_LIST_SIZE				("size"),
	INT_ACT_SUFFIX_GET_FORM_CLASS		("class"),
	INT_ACT_SUFFIX_GET_FORM_FORM		("form"),
	INT_ACT_SUFFIX_GET_FORM_LIST		("list"),
	INT_ACT_SUFFIX_GET_FORM_SEARCH		("search"),
	INT_ACT_SUFFIX_GET_FORM_SELECTOR	("selector"),
	INT_ACT_SUFFIX_GET_FORM_WKFLW		("workflow"),
	INT_CSS_BLUEXML_AUTOGEN				("bluexml-autogen"),
	INT_CSS_HORIZ_LINE					("side_horizontal_line"),
	INT_CSS_NO_LABEL_FIELD				("side_no_label_field"),
	INT_CSS_RO_TEXTAREA					("side_ro_textarea"),
	INT_CSS_SEARCH_FIELD				("side_search_field"),
	INT_CSS_SEARCH_OPERATOR				("side_search_operator"),
	INT_CSS_SEARCH_VALUE				("side_search_value"),
	INT_CSS_SELECT_SEARCH_ZONE			("side_select_search_zone"),
	INT_CSS_SELECT_TRIGGER_IMG			("side_select_trigger_img"),
	INT_CSS_SELECT_WIDGET				("side_select_widget"),
	INT_CSS_STATIC_TEXT					("side_static_text"),
	INT_CSS_STATUS_BAR_ID				("side_status_bar"),
	INT_CSS_UPLOAD_FILENAME 			("side_upload_filename"),
	INT_CSS_UPLOAD_PREVIEW 				("side_upload_preview"),
	INT_CSS_UPLOAD_WIDGET 				("side_upload_widget"),
	// NOTE: all directories must be filled.
	INT_DIRECTORY_ENUMS					("enums"),
	INT_DIRECTORY_FORM_CLASSES			("defaults"),
	INT_DIRECTORY_FORM_FORMS			("forms"),
	INT_DIRECTORY_FORM_LISTS			("lists"),
	INT_DIRECTORY_FORM_READONLY			("readonly"),
	INT_DIRECTORY_FORM_SEARCH			("search"),
	INT_DIRECTORY_FORM_SELECTOR			("selectors"),
	INT_DIRECTORY_FORM_WKFLW			("workflows"),
	INT_ERR_NULL_WKFLW_ACTIVE_PATHS		("No active path for instance: "),
	INT_ERR_NULL_WKFLW_INSTANCE_PATHS	("No paths for instance: "),
	INT_EXC_ASSOCIATION_ENDS			("Illegal association: both ends must be classes."),
	INT_EXC_WKFLW_GET_INSTANCE			("Exception getting instance with id: "),
	INT_EXC_WKFLW_GET_INSTANCE_PATHS	("Exception getting paths for instance: "),
	INT_FILEFIELD_PREVIEW_IMAGE			("image"),
	INT_FILEFIELD_PREVIEW_NONE			(""),
	INT_FORMTYPE_FORM					("form"),
	INT_FORMTYPE_LIST					("list"),
	INT_FORMTYPE_SEARCH					("search"),
	INT_FORMTYPE_SELECTOR				("selector"),
	INT_FORMTYPE_WKFLW					("wkflw"),
	INT_GEN_DYN_ENUM_PREFIX				("$"),
	INT_GEN_DYN_ENUM_PREFIX_CONTEXT		("enumContext"),
	INT_GEN_DYN_ENUM_PREFIX_PARENT		("enumParent"),
	INT_GEN_ID_OBJECTMODEL				("objectmodel"),
	INT_GEN_PLACEHOLDER_CONTEXT_PATH	("{@contextpath}"),
	INT_GEN_PLACEHOLDER_SESSION_ID		("{@sessionid}"),
	INT_GEN_PREFIX_BIND_FORM			("bind"),
	INT_GEN_PREFIX_BIND_WKFLW			("wkflwbind"),
	INT_GEN_PREFIX_SUBMIT_FORM			("submit"),
	INT_GEN_PREFIX_SUBMIT_WKFLW			("wkflwsubmit"),
	INT_GEN_REDIRECT_ADD_PARAMS			("addParams"),
	INT_GEN_REDIRECT_AUTO_ADVANCE		("auto"),
	INT_GEN_REDIRECT_ENTRY				("entry"),
	INT_GEN_REDIRECT_NAME				("name"),
	INT_GEN_REDIRECT_URL				("url"),
	INT_GEN_REVERSED_TAG_KEY			("reversed"),
	INT_GEN_REVERSED_TAG_VAL_ALFRESCO	("alfresco"),
	INT_INSTANCE_SEARCH_OPCODE			("op"),
	INT_INSTANCE_SEARCH_VALUE			("val"),
	INT_INSTANCE_SEARCH_VALUE_HI		("hiValue"),
	INT_INSTANCE_SEARCH_VALUE_LO		("loValue"),
	INT_INSTANCE_SELECTEDID				("SELECTEDID"),
	INT_INSTANCE_SELECTEDLABEL			("SELECTEDLABEL"),
	INT_INSTANCE_SELECTEDMAX			("SELECTEDMAX"),
	INT_INSTANCE_SIDE_DATATYPE			("SIDEDataType"),
	INT_INSTANCE_SIDE_NODE_CONTENT		("SIDENodeContent"),
	INT_INSTANCE_SIDEEDIT				("SIDEEDIT"),
	INT_INSTANCE_SIDEID					("SIDEID"),
	INT_INSTANCE_SIDELABEL				("SIDELABEL"),
	INT_INSTANCE_WKFLW_NODESET			("workflow"),
	INT_MSGPOOL_NO_MESSAGE_FILE			("INVALID MESSAGE FILE!"),
	INT_NAMESPACE_BLUEXML_CLASS			("http://www.bluexml.com/model/content"),
	INT_NAMESPACE_BLUEXML_WORKFLOW		("http://www.bluexml.com/model/workflow"),
	INT_NAMESPACE_XFORMS				("http://www.w3.org/2002/xforms"),
	INT_NAMESPACE_XHTML					("http://www.w3.org/1999/xhtml"),
	INT_PREFIX_FILENAME_OPERATORS		("search.operators."),
	INT_PREFIX_INSTANCE_OPERATORS		("SearchOperators"),
	INT_SEARCH_JSON_TYPE_ENUMS			("enums"),
	INT_SEARCH_OPERATOR_IGNORE			("ignore"),
	INT_SUBMIT_BUTTONS_GROUP_ID			("submitButtons"),
	INT_SUFFIX_FILENAME_SELECTORS		("_selector"),
	INT_SUFFIX_READ_ONLY_FORMS			("RO"),
	INT_SUFFIX_UPLOAD_FILE				("_fileContent"),
	INT_SUFFIX_UPLOAD_REPO				("_repoContent"),
	INT_TYPE_XSD_BOOLEAN				("boolean"),
	INT_TYPE_XSD_DATE 					("date"),
	INT_TYPE_XSD_DATETIME 				("dateTime"),
	INT_TYPE_XSD_STRING 				("string"),
	INT_TYPE_XSD_TIME 					("time"),
	INT_UPLOAD_DEST_FILE				("filesystem"),
	INT_UPLOAD_DEST_REPO				("repository"),
	INT_URI_SCHEME_READER				("sidereader://{@sessionid}/"),
	INT_URI_SCHEME_WRITER				("sidewriter://{@sessionid}/"),
	INT_WEBSCRIPT_OPCODE_AUTHENTICATE	("auth"),
	INT_WEBSCRIPT_OPCODE_BATCH			("batch"),
	INT_WEBSCRIPT_OPCODE_DELETE			("delete"),
	INT_WEBSCRIPT_OPCODE_ENUM			("enum"),
	INT_WEBSCRIPT_OPCODE_HELP			("help"),
	INT_WEBSCRIPT_OPCODE_LABELS			("labels"),
	INT_WEBSCRIPT_OPCODE_LIST			("list"),
	INT_WEBSCRIPT_OPCODE_MKDIR			("mkdir"),
	INT_WEBSCRIPT_OPCODE_NODE_INFO		("nodeinfo"),
	INT_WEBSCRIPT_OPCODE_PACKAGE		("package"),
	INT_WEBSCRIPT_OPCODE_READ			("read"),
	INT_WEBSCRIPT_OPCODE_SERVICE		("service"),
	INT_WEBSCRIPT_OPCODE_UPLOAD			("upload"),
	INT_WEBSCRIPT_OPCODE_WORKFLOW		("workflow"),
	//
	// strings by which we accept parameters in the url
	//
	/** the name of a Java class to be called */
	PARAM_ACTION_NAME ("actionName"),
	/** the address (protocol, host and port) to the Alfresco server. PERSISTENT.*/
	PARAM_ALFRESCO_HOST("alfrescoHost"),
	/** whether non BlueXML definitions should also be listed*/
	PARAM_ALLOW_ALL_DEFS ("allowAllDefs"),
	/** Used when redirecting from a workflow form to continually progress along a workflow path*/
	PARAM_AUTO_ADVANCE ("autoAdvance"),
	PARAM_CHECK_MATCH_DATA_FORM("checkMatchDF"),
	/** URL of a CSS for inclusion in templates sent to clients. PERSISTENT.*/
	PARAM_CSS_FILE("CSS"), 
	PARAM_DATA_ID("id"),
	PARAM_DATA_TYPE("type"),
	PARAM_FORM_TYPE("formType"),
	/**
	 * specifies a dummy call whose returned result will be "success" or "failure". During the
	 * processing of that call, persistent parameters are set to the given values if applicable.
	 */
	PARAM_INIT_CALL("init"),
	PARAM_LANGUAGE ("language"),
	/** output param for redirection; indicates which workflow form the redirection comes from*/ 
	PARAM_LEAVING_FORM("leavingForm"),
	PARAM_MAX_RESULTS("maxResults"),
	/** filesystem path to the forms.properties file. PERSISTENT.*/
	PARAM_PROPERTIES_FILE_FORMS ("formsPropertiesFile"),
	/** filesystem path to the messages.properties file. PERSISTENT.*/
	PARAM_PROPERTIES_FILE_MESSAGES ("messagesPropertiesFile"),
	PARAM_PAGE_CANCEL ("nextPageCancel"),
	PARAM_PAGE_DELETE ("nextPageDelete"),
	PARAM_PAGE_SUBMIT ("nextPageSubmit"),
	PARAM_PAGE_SUCCESS ("successPage"),
	PARAM_PAGE_FAILURE ("failurePage"),
	/** filesystem path to the redirect.xml file. PERSISTENT.*/
	PARAM_REDIRECTOR_CONFIG_FILE("redirectXmlFile"),
	/** whether to reload the mapping.xml file*/
	PARAM_RELOAD_MAPPING_FILE("dynamicReload"),
	/** whether to reload properties and redirection files*/
	PARAM_RELOAD_PROPERTIES("reloadProperties"),
	/** whether to send a JSON string to */
	PARAM_SEARCH_MODE("searchMode"),
	PARAM_SEARCH_USE_SHORT_NAMES("useShortNames"),
	PARAM_SERVE_TEST_PAGE("serveTestPage"),
	PARAM_SHOW_SUBMITS("showSubmitButtons"),
	PARAM_SHOW_CANCEL("showCancel"),
	PARAM_SHOW_DELETE("showDelete"),
	PARAM_SHOW_VALIDATE("showSubmit"),
	/** whether parameters should not be added to a next page URL*/
	PARAM_SKIP_ADDITIONAL_INFO("skipAdditionalInfo"),
	/** whether to avoid contacting the webscript on Alfresco*/
	PARAM_STANDALONE("standalone"),
	/** originally for passing the status msg to client URL, now also considered as an input*/
	PARAM_STATUS_MSG("statusMsg"),
	PARAM_USER_NAME("userName"),
	PARAM_USER_PSWD("userPswd"),
	PARAM_UPLOAD_DEPTH("uploadDepth"),
	PARAM_UPLOAD_DIRECTORY("uploadDirectory"),
	PARAM_UPLOAD_REPOSITORY("uploadRepository"),
	PARAM_UPLOAD_REPOSITORY_APPEND("uploadRepoAppend"),
	PARAM_UPLOAD_REPOSITORY_FORMAT("uploadRepoFormat"),
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
