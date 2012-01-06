package com.bluexml.xforms.messages;

/**
 * Classe d'indirection pour les messages indiquant les clés autorisées via une énumération. Elle
 * permet de découpler le nom choisi pour les clés de leur utilisation dans le reste du code, en
 * centralisant le texte ici pour une modification effective partout. La plupart de ces chaînes ne
 * sont pas modifiées par l'utilisateur sauf celles préfixées par "MSG_" ou "CAPTION_".
 * <p/>
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
		/** The key for the complete path when uploading to file system. */
		KEY_UPLOAD_DIRECTORY("upload.directory"),
		KEY_UPLOAD_DIR_PATH_DEPTH("upload.directory.random.path.depth"),
		/** The key for the repository upload path. */
		KEY_UPLOAD_REPOSITORY("upload.repository"),
		KEY_UPLOAD_REPOSITORY_APPEND("upload.repository.append.suffix"),
		KEY_UPLOAD_REPOSITORY_FORMAT_INFO("upload.repository.format.info"),
		KEY_USER_NAME("user.name"),
		KEY_USER_PSWD("user.pswd"),
		//
		//
		// keys for messages in the messages.properties file; user-changeable
		//
		MSG_ASSOC_MANDATORY("association.is.mandatory"),
		MSG_ERROR_ACCESS_DENIED("error.access.denied"),
		MSG_ERROR_DEFAULT_MSG("error.default.msg"),
		MSG_ERROR_INTEGRITY_VIOLATION("error.integrity.violation"),
		MSG_ERROR_WKFLW_START_FAILURE("error.workflow.start.failure"),
		MSG_FIELD_LABEL_FORMAT("field.label.format"),
		MSG_FIELD_MANDATORY("field.is.mandatory"),
		MSG_FILE_FIELD_FILENAME_TEMP("file.field.filename.temporary"),
		MSG_FILE_FIELD_LABEL("file.field.label"),
		MSG_FILL_MANDATORY_FIELDS("fill.mandatory.fields"),
		MSG_FORMAT_DATE_OUTPUT("format.date.output"),
		MSG_FORMAT_TIME_OUTPUT("format.time.output"),
		MSG_INVALID_REGEX_FORMAT("invalid.regex.format"),
		MSG_KEY_NOT_FOUND("key.not.found"),
		MSG_LENGTH_BETWEEN("length.between.min.and.max"),
		MSG_LENGTH_MAXIMAL("length.maximal"),
		MSG_LENGTH_MINIMAL("length.minimal"),
		MSG_SEARCH_OPERATOR_ABOVE("search.operator.above"),
		MSG_SEARCH_OPERATOR_AFTER("search.operator.after"),
		MSG_SEARCH_OPERATOR_ALLBUT("search.operator.allBut"),
		MSG_SEARCH_OPERATOR_BEFORE("search.operator.before"),
		MSG_SEARCH_OPERATOR_BELOW("search.operator.below"),
		MSG_SEARCH_OPERATOR_BETWEEN("search.operator.between"),
		MSG_SEARCH_OPERATOR_CONTAINS("search.operator.contains"),
		MSG_SEARCH_OPERATOR_CONTENTS("search.operator.contents"),
		MSG_SEARCH_OPERATOR_EMPTY("search.operator.empty"),
		MSG_SEARCH_OPERATOR_ENDSWITH("search.operator.endsWith"),
		MSG_SEARCH_OPERATOR_EXACTLY("search.operator.exactly"),
		MSG_SEARCH_OPERATOR_FILETYPE("search.operator.fileType"),
		MSG_SEARCH_OPERATOR_ICONTAINS("search.operator.icontains"),
		MSG_SEARCH_OPERATOR_IENDSWITH("search.operator.iendsWith"),
		MSG_SEARCH_OPERATOR_IGNORE("search.operator.ignore"),
		MSG_SEARCH_OPERATOR_IS("search.operator.is"),
		MSG_SEARCH_OPERATOR_ISNOT("search.operator.isNot"),
		MSG_SEARCH_OPERATOR_ISTARTSWITH("search.operator.istartsWith"),
		MSG_SEARCH_OPERATOR_NONE("search.operator.none"),
		MSG_SEARCH_OPERATOR_NOTBETWEEN("search.operator.notBetween"),
		MSG_SEARCH_OPERATOR_ONEOF("search.operator.oneOf"),
		MSG_SEARCH_OPERATOR_SIZE("search.operator.size"),
		MSG_SEARCH_OPERATOR_STARTSWITH("search.operator.startsWith"),
		MSG_SELECT_LIST_FILTER_LABEL("selection.list.filter.label"),
		MSG_SELECT_LIST_SEARCH_LABEL("selection.list.search.label"),
		MSG_SELECT_LIST_SHOW_ALL("selection.list.show.all.results"),
		MSG_SESSION_TIMED_OUT("session.timed.out"),
		MSG_STATUS_CREATE_FAILURE("status.message.create.failure"),
		MSG_STATUS_CREATE_SUCCESS("status.message.create.success"),
		MSG_STATUS_DELETE_FAILURE("status.message.delete.failure"),
		MSG_STATUS_DELETE_SUCCESS("status.message.delete.success"),
		MSG_STATUS_EDIT_FAILURE("status.message.edit.failure"),
		MSG_STATUS_EDIT_SUCCESS("status.message.edit.success"),

		/** When users/groups to be assigned next tasks are all unknown to Alfresco */
		MSG_STATUS_WKFLW_FAIL_ASSIGN("status.message.workflow.failure.assignment"),

		/** When the assignment of some tasks to users/groups fails */
		MSG_STATUS_WKFLW_FAIL_ASSIGN_TASKS("status.message.workflow.failure.assignment.tasks"),

		MSG_STATUS_WKFLW_FAIL_INITIATOR("status.message.workflow.failure.initiator"),
		MSG_STATUS_WKFLW_FAIL_NO_ACTOR("status.message.workflow.failure.noactor"),
		MSG_STATUS_WKFLW_FAIL_PACKAGE("status.message.workflow.failure.package"),
		MSG_STATUS_WKFLW_FAIL_SERVER("status.message.workflow.failure.server"),
		MSG_STATUS_WKFLW_FAIL_USER("status.message.workflow.failure.user.unknown"),
		MSG_STATUS_WKFLW_SUCCESS("status.message.workflow.success"),
		MSG_STATUS_WKFLW_SUCCESS_END("status.message.workflow.success.ended"),
		MSG_STATUS_EMPTY("status.message.empty"),
		MSG_STATUS_ITERATION("status.message.iteration.postfix"),
		MSG_TRIGGER_INLINE_MULTIPLE_ADD("trigger.inline.multiple.add"),
		MSG_TRIGGER_INLINE_MULTIPLE_DELETE("trigger.inline.multiple.delete"),
		MSG_UPLOAD_CONTENT_FIELD_LABEL("upload.node.content.field.label"),
		MSG_UPLOAD_CONTENT_GROUP_LABEL("upload.node.content.group.label"),
		MSG_UPLOAD_CONTENT_NO_CONTENT("upload.node.content.none"),
		MSG_UPLOAD_CONTENT_REPO_FORMAT("upload.node.content.repository.format"),
		MSG_UPLOAD_CONTENT_REPO_INFO("upload.node.content.repository.info"),
		MSG_UPLOAD_FAILED("upload.to.repository.failure"),
		// captions for buttons; user-changeable
		//
		CAPTION_BUTTON_CANCEL("caption.button.cancel"),
		// label for create in selection widget
		CAPTION_BUTTON_CREATE("caption.button.create"),
		CAPTION_BUTTON_DELETE("caption.button.delete"),
		// label for 'Modify' (edit) on right side of selection widget
		CAPTION_BUTTON_EDIT("caption.button.edit"),
		CAPTION_BUTTON_SEARCH("caption.button.search"),
		CAPTION_BUTTON_SETTYPE("caption.button.settype"),
		CAPTION_BUTTON_SUBMIT("caption.button.submit"),
		CAPTION_BUTTON_WORKFLOW_CANCEL("caption.button.workflow.cancel"),
		CAPTION_BUTTON_WORKFLOW_SELECT("caption.button.workflow.select"),
		CAPTION_BUTTON_WORKFLOW_START("caption.button.workflow.start"),
		//
		// strings by which we accept parameters in the url
		//
		/**
		 * The name of a Java class to be called after the form has been submitted. The class must
		 * implement a mandatory function specified in the documentation.
		 */
		PARAM_ACTION_NAME("actionName"),

		/**
		 * The address (protocol, host and port) to the Alfresco server. PERSISTENT (remains active
		 * until set differently).
		 */
		PARAM_ALFRESCO_HOST("alfrescoHost"),

		/**
		 * Used when redirecting from a workflow form to progress along the workflow path, which
		 * means automatically opening the next task form.
		 */
		PARAM_AUTO_ADVANCE("autoAdvance"),

		/** The full filesystem path (on the server) to the folder containing the 'forms' folders. */
		PARAM_BASE_FOLDER("baseFolder"),

		/**
		 * Whether it should be checked that the form being opened is suited to the object/task
		 * being displayed. In case the check fails, a message appears in the status bar.
		 */
		PARAM_CHECK_MATCH_DATA_FORM("checkMatchDF"),

		/** URL of a CSS for inclusion in templates sent to clients. PERSISTENT. */
		PARAM_CSS_FILE("CSS"),

		/** The id of an object to display, with or without the protocol and store. */
		PARAM_DATA_ID("id"),

		/** The id of the form to open. */
		PARAM_DATA_TYPE("type"),

		/** The type of the form to open, e.g. "form" or "search". */
		PARAM_FORM_TYPE("formType"),

		/**
		 * Specifier for a dummy call whose returned result will be "success" or "failure". The
		 * processing of the call consists in loading the mapping file. If given, persistent
		 * parameters are set to the given values if applicable. Useful only for cold starts.
		 */
		PARAM_INIT_CALL("init"),

		/**
		 * Method execution request for the AlfrescoControllerAPI via Javascript. Example call:
		 * <tt>http://localhost:8082/xforms/js?jscall=getAlfrescoUrl</tt> or
		 * <tt>http://localhost:8082/xforms/js?jscall=authenticate/admin/password</tt>
		 */
		PARAM_JAVASCRIPT_CALL("jscall"),

		/**
		 * The user-chosen code for the page language. Determines the suffix to be looked for in the
		 * properties file.
		 */
		PARAM_LANGUAGE("language"),

		/**
		 * <em>Output</em> param for redirection; indicates which workflow form the redirection
		 * comes from. This parameter may be useful to the redirection script.
		 */
		PARAM_LEAVING_FORM("leavingForm"),

		/** The comma-separated list of ids for mass tagging. */
		PARAM_MASS_IDS("ids"),

		/** Sets the maximum number of items in the selection widgets for associations. */
		PARAM_MAX_RESULTS("maxResults"),

		/** Server-side filesystem path to the forms.properties file. PERSISTENT. */
		PARAM_PROPERTIES_FILE_FORMS("formsPropertiesFile"),

		/** Server-side filesystem path to the messages.properties file. PERSISTENT. */
		PARAM_PROPERTIES_FILE_MESSAGES("messagesPropertiesFile"),

		/**
		 * URL to the page where to redirect the web client after the Cancel button has been
		 * clicked.
		 */
		PARAM_NEXT_PAGE_CANCEL("nextPageCancel"),

		/**
		 * URL to the page where to redirect the web client after the Delete button has been clicked
		 * and the delete operation performed.
		 */
		PARAM_NEXT_PAGE_DELETE("nextPageDelete"),

		/**
		 * URL to the page where to redirect the web client after the Submit button has been clicked
		 * and the submit operation performed.
		 */
		PARAM_NEXT_PAGE_SUBMIT("nextPageSubmit"),

		/**
		 * URL to the page where to redirect the web client after a workflow transition has
		 * succeeded.
		 */
		PARAM_SUCCESS_PAGE("successPage"),

		/** URL to the page where to redirect the web client after a workflow transition has failed. */
		PARAM_FAILURE_PAGE("failurePage"),

		/** Server-side filesystem path to the redirect.xml file. PERSISTENT. */
		PARAM_REDIRECTOR_CONFIG_FILE("redirectXmlFile"),

		/** If <code>true</code>, instructs to reload the mapping.xml file */
		PARAM_RELOAD_MAPPING_FILE("dynamicReload"),

		/** If <code>true</code>, whether to reload properties and redirection files */
		PARAM_RELOAD_PROPERTIES("reloadProperties"),

		/**
		 * Overrides the repository space where to save the data. Default is
		 * /app:company_home/app:dictonary/cm:BLUEXMLDATA
		 */
		PARAM_SAVE_DATA_TO("saveTo"),

		/**
		 * If <code>true</code>, sets the form in a seachr mode: the form contents is transmitted as
		 * a JSON string to the URL in the next page parameter.
		 */
		PARAM_SEARCH_MODE("searchMode"),

		/**
		 * If <code>true</code>, instructs to remove the type name from the full qualified name of
		 * properties. For instance, for a type
		 * <tt>{http://www.bluexml.com/model/content/com/1.0}bluexml_demo_rh_Person</tt>, the
		 * property "surname" is given as <tt>_surname</tt> instead of
		 * <tt>{http://www.bluexml.com/model/content/com/1.0}bluexml_demo_rh_Person_surname</tt>. <br/>
		 * Used for search forms and for data forms in search mode.
		 */
		PARAM_SEARCH_USE_SHORT_NAMES("useShortNames"),

		/**
		 * If <code>true</code>, instructs to serve a page without the usual redirection, which
		 * implies that the controller does not store the usual pieces of information attached to
		 * each page request. To be used only by test programs and not via a normal web client.
		 */
		PARAM_SERVE_TEST_PAGE("serveTestPage"),

		/** If <code>false</code>, removes the Submit, Delete and Cancel buttons from the form. */
		PARAM_SHOW_SUBMITS("showSubmitButtons"),

		/** If <code>false</code>, removes the Cancel button from the form. */
		PARAM_SHOW_CANCEL("showCancel"),

		/** If <code>false</code>, removes the Delete button from the form. */
		PARAM_SHOW_DELETE("showDelete"),

		/** If <code>false</code>, removes the Submit button from the form. */
		PARAM_SHOW_VALIDATE("showSubmit"),

		/**
		 * If <code>true</code>, parameters are not added to the next page URL when redirecting the
		 * web client. Adding the parameters is the default behavior.
		 */
		PARAM_SKIP_ADDITIONAL_INFO("skipAdditionalInfo"),

		/**
		 * If <code>true</code>, the Alfresco server is considered offline and not contacted.
		 * PERSISTENT.
		 */
		PARAM_STANDALONE("standalone"),

		/**
		 * Input and output parameter. Originally for passing the status message to the redirection
		 * URL, now also considered as an input.
		 */
		PARAM_STATUS_MSG("statusMsg"),

		/**
		 * A suffix which, when appended to a form name, allows selecting a different form. e.g. if
		 * the form id given in the URL is "Person" and a template "admin" is provided, the form
		 * definition will be read from "forms/custom/forms/Person_admin.xhtml" instead of
		 * "forms/forms/Person.xhtml".
		 */
		PARAM_TEMPLATE("template"),

		/** The name of the user issuing a request for a form. Mandatory. */
		PARAM_USER_NAME("userName"),

		/**
		 * The password for the user whose request is being served. Currently unused because
		 * Alfresco does not require it before performing actions on the server: only the user name
		 * is required. However, the user must have been authenticated; the controller API provides
		 * a function for that purpose.
		 */
		PARAM_USER_PSWD("userPswd"),

		/** The depth of the random folder path when uploading files to the server filesystem. */
		PARAM_UPLOAD_DEPTH("uploadDepth"),

		/** The root directory on the server filesystem for storing uploaded files. */
		PARAM_UPLOAD_DIRECTORY("uploadDirectory"),

		/** The repository space where to store uploaded files. */
		PARAM_UPLOAD_REPOSITORY("uploadRepository"),

		/**
		 * If <code>true</code>, requests a sequence number to be appended to the names of files
		 * uploaded into the repository, in case the filename already exists.
		 */
		PARAM_UPLOAD_REPOSITORY_APPEND("uploadRepoAppend"),

		/**
		 * If <code>true</code>, the node information will be formated when displaying an existing
		 * content-enabled object on a form. The format can be specified in the messages properties
		 * file.
		 */
		PARAM_UPLOAD_REPOSITORY_FORMAT("uploadRepoFormat"),

		/**
		 * The id of a workflow instance when calling a workflow form. Mandatory for all workflow
		 * forms except start task forms.
		 */
		PARAM_WORKFLOW_INSTANCE_ID("workflowInstanceId"),

		/** The id of a process definition. Mandatory for start task forms. */
		PARAM_WORKFLOW_PROCESS_ID("workflowProcessId"),
		//
		//
		// shortcuts for internal strings
		//
		// //
		// names of actions
		INT_ACT_CODE_CANCEL("cancel"),
		INT_ACT_CODE_CREATE_CLASS("create"),
		INT_ACT_CODE_CREATE_FORM("createForm"),
		INT_ACT_CODE_DELETE("delete"),
		INT_ACT_CODE_EDIT_CLASS("edit"),
		INT_ACT_CODE_EDIT_FORM("editForm"),
		INT_ACT_CODE_ENUM("enum"),
		INT_ACT_CODE_EXECUTE("execute"),
		INT_ACT_CODE_GET_FORM("get"),
		INT_ACT_CODE_I18N("i18n"),
		INT_ACT_CODE_LIST("list"),
		INT_ACT_CODE_SETTYPE("settype"),
		INT_ACT_CODE_SUBMIT("submit"),
		INT_ACT_CODE_WRKFLW_TRANSITION("transition"),
		INT_ACT_CODE_WRKFLW_SAVE("wrkflw_save"),
		// names of action parameters, internal to the actions hierarchy
		INT_ACT_PARAM_ANY_DATATYPE("type"),
		INT_ACT_PARAM_ANY_HINT("formTypeHint"),
		INT_ACT_PARAM_ANY_ID("id"),
		INT_ACT_PARAM_ANY_ASSOC("assoc"),
		INT_ACT_PARAM_ENUM_FILTER_DATA("filterData"),
		INT_ACT_PARAM_ENUM_FILTER_PARENT("filterParent"),
		INT_ACT_PARAM_ENUM_LIMITED("limited"),
		INT_ACT_PARAM_ENUM_RAWTYPE("rawtype"),
		INT_ACT_PARAM_EXEC_ACTION("action"),
		INT_ACT_PARAM_GET_FORMTYPE("formType"),
		INT_ACT_PARAM_LIST_FORMAT("format"),
		INT_ACT_PARAM_LIST_FILTER_ASSOC("filterAssoc"),
		INT_ACT_PARAM_LIST_IDENTIFIER("identifier"),
		INT_ACT_PARAM_LIST_IS_COMPOSITION("isComposition"),
		INT_ACT_PARAM_LIST_IS_SEARCH_MODE("isSearchMode"),
		INT_ACT_PARAM_LIST_LUCENE_QUERY("luceneQuery"),
		INT_ACT_PARAM_LIST_DATA_SOURCE_URI("dataSourceURI"),
		INT_ACT_PARAM_LIST_MAXLENGTH("maxLength"),
		INT_ACT_PARAM_LIST_QUERY("query"),
		INT_ACT_PARAM_LIST_SIZE("size"),
		// form type indicators
		INT_ACT_SUFFIX_GET_FORM_CLASS("class"),
		INT_ACT_SUFFIX_GET_FORM_FORM("form"),
		INT_ACT_SUFFIX_GET_FORM_LIST("list"),
		INT_ACT_SUFFIX_GET_FORM_SEARCH("search"),
		INT_ACT_SUFFIX_GET_FORM_SELECTOR("selector"),
		INT_ACT_SUFFIX_GET_FORM_WKFLW("workflow"),
		// our custom CSS rule names
		INT_CSS_DATETIME_DATE("side_datetime_date"),
		INT_CSS_DATETIME_TIME("side_datetime_time"),
		INT_CSS_FORM_TITLE("side_form_title"),
		INT_CSS_FORM_TITLE_CLASS("side_class_form_title"),
		INT_CSS_HORIZ_LINE("side_horizontal_line"),
		INT_CSS_INLINE_TRIGGERS("side_imultiple_triggers"),
		INT_CSS_INPUT_MULTIPLE("side_input_multiple"),
		INT_CSS_INPUT_MULTIPLE_TRIGGERS("side_input_multiple_triggers"),
		INT_CSS_INPUT_MULTIPLE_ITEMS("side_input_multiple_items"),
		INT_CSS_RO_TEXTAREA("side_ro_textarea"),
		INT_CSS_SEARCH_FIELD("side_search_field"),
		INT_CSS_SEARCH_OPERATOR("side_search_operator"),
		INT_CSS_SEARCH_VALUE("side_search_value"),
		INT_CSS_SELECT_LIST("side_select_list"),
		INT_CSS_SELECT_BUTTONS_ZONE("side_select_buttons_zone"),
		INT_CSS_SELECT_FEATURE_SEARCH("side_select_feature_search"),
		INT_CSS_SELECT_FEATURE_FILTER("side_select_feature_filter"),
		INT_CSS_SELECT_OUTPUT_ZONE("side_select_output_zone"),
		INT_CSS_SELECT_SEARCH_ZONE("side_select_search_zone"),
		INT_CSS_SELECT_SELECTED_ITEM("side_select_selected_item"), // Nx1 widget
		INT_CSS_SELECT_SELECTED_ITEMS("side_select_selected_items"), // NxN widget
		INT_CSS_SELECT_TRIGGER_BUTTON("side_select_trigger_button"),
		INT_CSS_SELECT_TRIGGER_IMG("side_select_trigger_img"),
		INT_CSS_SELECT_WIDGET("side_select_widget"),
		INT_CSS_STATIC_TEXT("side_static_text"),
		INT_CSS_STATUS_BAR_ID("side_status_bar"),
		INT_CSS_UPLOAD_FILENAME("side_upload_filename"),
		INT_CSS_UPLOAD_PREVIEW("side_upload_preview"),
		INT_CSS_UPLOAD_WIDGET("side_upload_widget"),
		// NOTE: all directories must be filled.
		INT_DIRECTORY_ENUMS("enums"),
		INT_DIRECTORY_FORM_CLASSES("defaults"),
		INT_DIRECTORY_FORM_FORMS("forms"),
		INT_DIRECTORY_FORM_LISTS("lists"),
		INT_DIRECTORY_FORM_READONLY("readonly"),
		INT_DIRECTORY_FORM_SEARCH("search"),
		INT_DIRECTORY_FORM_SELECTOR("selectors"),
		INT_DIRECTORY_FORM_WKFLW("workflows"),
		INT_ERR_NULL_WKFLW_ACTIVE_PATHS("No active path for instance: "),
		INT_ERR_NULL_WKFLW_INSTANCE_PATHS("No paths for instance: "),
		INT_EXC_ASSOCIATION_ENDS("Illegal association: both ends must be classes."),
		INT_EXC_WKFLW_GET_INSTANCE("Exception getting instance with id: "),
		INT_EXC_WKFLW_GET_INSTANCE_PATHS("Exception getting paths for instance: "),
		INT_FILEFIELD_PREVIEW_IMAGE("image"),
		INT_FILEFIELD_PREVIEW_NONE(""),
		INT_FORMTYPE_FORM("form"),
		INT_FORMTYPE_LIST("list"),
		INT_FORMTYPE_SEARCH("search"),
		INT_FORMTYPE_SELECTOR("selector"),
		INT_FORMTYPE_WKFLW("wkflw"),
		INT_GEN_DYN_ENUM_PREFIX("$"),
		INT_GEN_DYN_ENUM_CONTEXT("enumContext"),
		INT_GEN_DYN_ENUM_PARENT("enumParent"),
		INT_GEN_ID_OBJECTMODEL("objectmodel"),
		INT_GEN_PLACEHOLDER_CONTEXT_PATH("{@contextpath}"),
		INT_GEN_PLACEHOLDER_SESSION_ID("{@sessionid}"),
		INT_GEN_PREFIX_BIND_FORM("bind"),
		INT_GEN_REVERSED_TAG_KEY("reversed"),
		INT_GEN_REVERSED_TAG_VAL_ALFRESCO("alfresco"),
		INT_INSTANCE_ASSOCIATION_ITEM("selectionItem"),
		INT_INSTANCE_ENUM_ID("id"),
		INT_INSTANCE_ENUM_VALUE("value"),
		INT_INSTANCE_INPUT_MULT_INPUT("input"),
		INT_INSTANCE_INPUT_MULT_VALUE("value"),
		INT_INSTANCE_INPUT_MULT_ITEM("item"),
		INT_INSTANCE_SEARCH_OPCODE("op"),
		INT_INSTANCE_SEARCH_VALUE("val"),
		INT_INSTANCE_SEARCH_VALUE_HI("hiValue"),
		INT_INSTANCE_SEARCH_VALUE_LO("loValue"),
		INT_INSTANCE_SELECTEDID("SELECTEDID"),
		INT_INSTANCE_SELECTEDLABEL("SELECTEDLABEL"),
		INT_INSTANCE_SELECTEDMAX("SELECTEDMAX"),
		INT_INSTANCE_SELECTEDTYPE("SELECTEDTYPE"),
		INT_INSTANCE_SELECT_ID("id"),
		INT_INSTANCE_SELECT_ITEM("item"),
		INT_INSTANCE_SELECT_LABEL("label"),
		INT_INSTANCE_SELECT_TYPE("qname"),
		INT_INSTANCE_SIDE_DATATYPE("SIDEDataType"),
		INT_INSTANCE_SIDE_NODE_CONTENT("SIDENodeContent"),
		INT_INSTANCE_SIDEEDIT("SIDEEDIT"),
		INT_INSTANCE_SIDEID("SIDEID"),
		INT_INSTANCE_SIDELABEL("SIDELABEL"),
		INT_INSTANCE_SIDETYPE("SIDETYPE"),
		INT_INSTANCE_WKFLW_NODESET("workflow"),
		INT_MSG_ALFRESCO_SERVER_DOWN("Cannot perform the action: Alfresco server unavailable."),
		INT_MSGPOOL_NO_MESSAGE_FILE("INVALID MESSAGE FILE!"),
		INT_NAMESPACE_BLUEXML_CLASS("http://www.bluexml.com/model/content"),		
		INT_NAMESPACE_BLUEXML_WORKFLOW("http://www.bluexml.com/model/workflow"),
		INT_NAMESPACE_XFORMS("http://www.w3.org/2002/xforms"),
		INT_NAMESPACE_XHTML("http://www.w3.org/1999/xhtml"),
		INT_PREFIX_FILENAME_OPERATORS("search.operators."),
		INT_PREFIX_INSTANCE_OPERATORS("SearchOperators"),
		INT_REDIRECTION_ADD_PARAMS("addParams"),
		INT_REDIRECTION_AUTO_ADVANCE("auto"),
		INT_REDIRECTION_ENTRY("entry"),
		INT_REDIRECTION_NAME("name"),
		INT_REDIRECTION_URL("url"),
		INT_SEARCH_JSON_TYPE_ENUMS("enums"),
		INT_SEARCH_OPERATOR_IGNORE("ignore"),
		INT_SUBMIT_BUTTONS_GROUP_ID("submitButtons"),
		INT_SUFFIX_FILENAME_SELECTORS("_selector"),
		INT_SUFFIX_READ_ONLY_FORMS("RO"),
		INT_SUFFIX_UPLOAD_FILE("_fileContent"),
		INT_SUFFIX_UPLOAD_REPO("_repoContent"),
		INT_TYPE_XSD_BOOLEAN("boolean"),
		INT_TYPE_XSD_DATE("date"),
		INT_TYPE_XSD_DATETIME("dateTime"),
		INT_TYPE_XSD_STRING("string"),
		INT_TYPE_XSD_TIME("time"),
		INT_UPLOAD_DEST_FILE("filesystem"),
		INT_UPLOAD_DEST_REPO("repository"),
		INT_URI_SCHEME_READER("sidereader://{@sessionid}/"),
		INT_URI_SCHEME_WRITER("sidewriter://{@sessionid}/"),
		INT_WEBSCRIPT_OPCODE_AUTHENTICATE("auth"),
		INT_WEBSCRIPT_OPCODE_BATCH("batch"),
		INT_WEBSCRIPT_OPCODE_DELETE("delete"),
		INT_WEBSCRIPT_OPCODE_ENUM("enum"),
		INT_WEBSCRIPT_OPCODE_HELP("help"),
		INT_WEBSCRIPT_OPCODE_LABELS("labels"),
		INT_WEBSCRIPT_OPCODE_LIST("list"),
		INT_WEBSCRIPT_OPCODE_MKDIR("mkdir"),
		INT_WEBSCRIPT_OPCODE_NODE_INFO("nodeinfo"),
		INT_WEBSCRIPT_OPCODE_PACKAGE("package"),
		INT_WEBSCRIPT_OPCODE_READ("read"),
		INT_WEBSCRIPT_OPCODE_SERVICE("service"),
		INT_WEBSCRIPT_OPCODE_UPLOAD("upload"),
		INT_WEBSCRIPT_OPCODE_WORKFLOW("workflow"),
		//
		// strings for interfacing with the extension property
		//
		//
		// fields rendered using a selection widget
		MODEL_XTENSION_DATATYPE("datatype"),
		MODEL_XTENSION_FORMAT("format"),
		MODEL_XTENSION_IDENTIFIER("identifier"),
		MODEL_XTENSION_LABEL_LENGTH("labelLength"),
		//
		// associations (ModelChoiceFields on forms)
		/**
		 * If present, this key indicates the URI where the list items will be fetched from. The
		 * item provider service must conform to the instance defined in the webscript. The URI then
		 * overrides the default "sidereader://.../list" and "sidewriter://.../list" URIs.
		 */
		MODEL_XTENSION_DATA_SOURCE_URI("dataSourceURI"),

		/**
		 * Defines how the selection widget will be rendered, as a filter (search box below the
		 * list) or as a search widget (search box above the list)
		 */
		MODEL_XTENSION_FEATURE_MODE("featureMode"),

		/** The accepted value for 'featureMode' that indicates a filter. */
		MODEL_XTENSION_FEATURE_MODE_FILTER("filter"),

		/** The accepted value for 'featureMode' that indicates a search. */
		MODEL_XTENSION_FEATURE_MODE_SEARCH("search"),

		/**
		 * If present, this key provides a user-defined query that will be used for fetching the
		 * items in the selection widget. This user query overrides the default query that is
		 * normally defined and used in the webscript.
		 */
		MODEL_XTENSION_LUCENE_QUERY("luceneQuery"),

		/**
		 * If available in the Xtension, this will prevent the selection widgets from triggering an
		 * automatic refresh of the list, in which case a "launch the search" button is displayed.
		 */
		MODEL_XTENSION_NO_AUTO_SEARCH("noAutoSearch"),

		/**
		 * If present, this will prevent the output text (i.e. "10/100 Show All") the from being
		 * part of the widget
		 */
		MODEL_XTENSION_NO_STATS_OUTPUT("noStatsOutput"),

		/**
		 * If <code>true</code>, parameters are not added to the next page URL when redirecting the
		 * web client. For easier use, the text string is the same as for the equivalent PARAM.
		 */
		MODEL_XTENSION_SKIP_ADDITIONAL_INFO(PARAM_SKIP_ADDITIONAL_INFO.getText()),

		/**
		 * URL to the page where to redirect the web client after the Cancel button has been clicked
		 * and the submit operation performed. Same string as for the equivalent PARAM.
		 */
		MODEL_XTENSION_NEXT_PAGE_CANCEL(PARAM_NEXT_PAGE_CANCEL.getText()),

		/**
		 * URL to the page where to redirect the web client after the Delete button has been clicked
		 * and the submit operation performed. Same string as for the equivalent PARAM.
		 */
		MODEL_XTENSION_NEXT_PAGE_DELETE(PARAM_NEXT_PAGE_DELETE.getText()),

		/**
		 * URL to the page where to redirect the web client after the Submit button has been clicked
		 * and the submit operation performed. Same string as for the equivalent PARAM.
		 */
		MODEL_XTENSION_NEXT_PAGE_SUBMIT(PARAM_NEXT_PAGE_SUBMIT.getText()),

		/** URL to the page where to redirect the web client after a workflow transition has failed. Same string as for the equivalent PARAM. */
		MODEL_XTENSION_FAILURE_PAGE(PARAM_FAILURE_PAGE.getText()),
		
		/**
		 * URL to the page where to redirect the web client after a workflow transition has
		 * succeeded. Same string as for the equivalent PARAM.
		 */
		MODEL_XTENSION_SUCCESS_PAGE(PARAM_SUCCESS_PAGE.getText()),
		
		;

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
