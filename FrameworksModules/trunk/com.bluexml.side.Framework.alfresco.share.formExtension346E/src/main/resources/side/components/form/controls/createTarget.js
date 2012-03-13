/**
 * SIDE root namespace.
 * 
 * @namespace SIDE
 */
// Ensure SIDE root object exists
if (typeof SIDE == "undefined" || !SIDE) {
	var SIDE = {};
}
if (console == undefined) {
	// create a fake console object to avoid error (console is provided by
	// firebug)
	var console = {
		log : function(msg) {
		}
	};
}

/**
 * ComboBox component.
 * 
 * @namespace SIDE
 * @class SIDE.ComboBox
 */
(function() {

	var lang = YAHOO.lang, Event = YAHOO.util.Event, Dom = YAHOO.util.Dom;

	SIDE.CreateTarget = function(htmlId, currentValueHtmlId) {
		SIDE.CreateTarget.superclass.constructor.call(this, "SIDE.CreateTarget", htmlId, [ "button", "menu", "container", "resize", "datasource", "datatable" ]);
		this.currentValueHtmlId = currentValueHtmlId;

		YAHOO.Bubbling.on("/side-labs/CreateTarget/openDialog" + this.id, this.onNewItem, this);
	};
	YAHOO.extend(SIDE.CreateTarget, Alfresco.component.Base, {

		log : function(msg) {
			console.log("[SIDE.CreateTarget] " + msg);
		},
		/**
		 * Object container for initialization options
		 * 
		 * @property options
		 * @type object
		 */
		options : {
			formconfig : {
				itemKind : null, /* node, type */
				itemId : null, /* cm:content, cm:folder ... or nodeRef to edit */
				destination : null, /*
									 * container noderef only needed in create
									 * mode
									 */
				mode : "create", /* create | edit | view */
				submitType : "json",
				formId : "" /* the formId to use */
			}
		},
		/**
		 * Fired by YUI when parent element is available for scripting
		 * 
		 * @method onReady
		 */
		onReady : function CreateTarget_onReady() {
			this.log("onReady");
		},

		/**
		 * New Folder button click handler
		 * 
		 * @method onNewFolder
		 * @param e
		 *            {object} DomEvent
		 * @param p_obj
		 *            {object} Object passed back from addListener method
		 */
		onNewItem : function DLTB_onNewFolder(e, p_obj) {
			var destination = this.options.destination;

			// Intercept before dialog show
			var doBeforeDialogShow = function DLTB_onNewFolder_doBeforeDialogShow(p_form, p_dialog) {
//				Dom.get(p_dialog.id + "-dialogTitle").innerHTML = this.msg("label.new-folder.title");
//				Dom.get(p_dialog.id + "-dialogHeader").innerHTML = this.msg("label.new-folder.header");
			};

			var templateUrl = YAHOO.lang.substitute(Alfresco.constants.URL_SERVICECONTEXT
					+ "components/form?itemKind={itemKind}&itemId={itemId}&destination={destination}&mode={mode}&submitType={submitType}&formId={formId}&showCancelButton={showCancelButton}",
					this.options.formconfig);
			templateUrl = YAHOO.lang.substitute(templateUrl, {
				site : "cm:" + Alfresco.constants.SITE
			});
			// Using Forms Service, so always create new instance
			var createFolder = new Alfresco.module.SimpleDialog(this.id + "-dialog-" + Alfresco.util.generateDomId());
			var me = this;
			createFolder.setOptions({
				width : "33em",
				templateUrl : templateUrl,
				actionUrl : null,
				destroyOnHide : true,
				doBeforeDialogShow : {
					fn : doBeforeDialogShow,
					scope : this
				},
				onSuccess : {
					fn : function DLTB_onNewFolder_success(response) {
						var folderName = "";
						if (me.options.formconfig.mode == "create") {
							folderName = response.config.dataObj["prop_cm_name"];
							YAHOO.Bubbling.fire("/side-labs/onCreateNewItem/" + this.currentValueHtmlId, {
								mode : "add",
								values : [ response.json.persistedObject ]
							});
						} else if (me.options.formconfig.mode == "edit") {
							
							YAHOO.Bubbling.fire("/side-labs/onCreateNewItem/" + this.currentValueHtmlId, {
								mode : "keep",
								values : []
							});
						}
						Alfresco.util.PopupManager.displayMessage({
							text : this.msg("message." + me.options.formconfig.mode + ".success", folderName)
						});
						
					},
					scope : this
				},
				onFailure : {
					fn : function DLTB_onNewFolder_failure(response) {
						var folderName = "";
						if (response) {
							folderName = response.config.dataObj["prop_cm_name"];
						}
						Alfresco.util.PopupManager.displayMessage({
							text : this.msg("message." + me.options.formconfig.mode + ".failure", folderName)
						});
					},
					scope : this
				}
			}).show();
		}
	});
})();