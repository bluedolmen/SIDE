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
 * DocumentList TreeView component.
 * 
 * @namespace Alfresco
 * @class SIDE.DocListTree
 */
(function() {
   /**
    * YUI Library aliases
    */
   var Dom = YAHOO.util.Dom, Event = YAHOO.util.Event, JSON = YAHOO.lang.JSON;

   /**
    * Alfresco Slingshot aliases
    */
   var $html = Alfresco.util.encodeHTML, $combine = Alfresco.util.combinePaths;

   /**
    * DocumentList TreeView constructor.
    * 
    * @param {String}
    *           htmlId The HTML id of the parent element
    * @return {SIDE.DocListTree} The new DocListTree instance
    * @constructor
    */
   SIDE.DocListTree = function DLT_constructor(htmlId) {
      Alfresco.DocListTree.superclass.constructor.call(this, "SIDE.DocListTree", htmlId, [ "treeview", "json" ]);

      // Register with Filter Manager
      Alfresco.util.FilterManager.register(this.name, "metadata");

      // Initialise prototype properties
      this.currentFilter = {};
      this.pathsToExpand = [];

      // Decoupled event listeners
      YAHOO.Bubbling.on("folderCopied", this.onFolderCopied, this);
      YAHOO.Bubbling.on("folderCreated", this.onFolderCreated, this);
      YAHOO.Bubbling.on("folderDeleted", this.onFolderDeleted, this);
      YAHOO.Bubbling.on("folderMoved", this.onFolderMoved, this);
      YAHOO.Bubbling.on("folderRenamed", this.onFolderRenamed, this);
      YAHOO.Bubbling.on("filterChanged", this.onFilterChanged, this);

      return this;
   };

   YAHOO.extend(SIDE.DocListTree, Alfresco.DocListTree, {
      log : function(msg) {
         console.log("[SIDE.DocListTree] " + msg);
      },

      onFolderCopied : function _onFolderCopied() {
         // nothing to do
         this.log("onFolderCopied");
      },

      onFolderCreated : function _onFolderCreated() {
         // nothing to do
         this.log("onFolderCreated");
      },
      onFolderDeleted : function _onFolderDeleted() {
         // nothing to do
         this.log("onFolderDeleted");
      },
      onFolderMoved : function _onFolderMoved() {
         // nothing to do
         this.log("onFolderMoved");
      },
      onFolderRenamed : function _onFolderRenamed() {
         // nothing to do
         this.log("onFolderRenamed");

      },

      /**
       * Fired by YUI when parent element is available for scripting
       * 
       * @method onReady
       */
      onReady : function DLT_onReady() {
         // Reference to self - used in inline functions
         var me = this;

         // Create twister from our H2 tag
         Alfresco.util.createTwister(this.id + "-h2", this.name.substring(this.name.lastIndexOf(".") + 1));

         /**
          * Dynamically loads TreeView nodes. This MUST be inline in order to
          * have access to the Alfresco.DocListTree class.
          * 
          * @method fnLoadNodeData
          * @param node
          *           {object} Parent node
          * @param fnLoadComplete
          *           {function} Expanding node's callback function
          */
         this.fnLoadNodeData = function DLT_oR_fnLoadNodeData(node, fnLoadComplete) {
            // Get the path this node refers to
            var nodePath = node.data.path;

            // Prepare URI for XHR data request
            var uri = me._buildTreeNodeUrl_nodeChild.call(me, node);

            // Prepare the XHR callback object
            var callback = {
               success : function DLT_lND_success(oResponse) {
                  var results = YAHOO.lang.JSON.parse(oResponse.responseText), item, treeNode;

                  // Update parent node's nodeRef if we didn't have it
                  // before
                  if (results.parent && node.data.nodeRef.length === 0) {
                     node.data.nodeRef = results.parent.nodeRef;
                  }

                  if (results.items) {
                     for ( var i = 0, j = results.items.length; i < j; i++) {
                        item = results.items[i];
                        item.path = $combine(nodePath, item.name);
                        treeNode = this._buildTreeNode(item, node, false);

                        if (!item.hasChildren) {
                           treeNode.isLeaf = true;
                        }
                     }
                  }

                  if (results.resultsTrimmed) {
                     tempNode = new YAHOO.widget.TextNode({
                        label : this.msg("message.folders-trimmed"),
                        style : "folders-trimmed"
                     }, node, false);
                     tempNode.isLeaf = true;
                  }

                  /**
                   * Execute the node's loadComplete callback method which comes
                   * in via the argument in the response object
                   */
                  oResponse.argument.fnLoadComplete();
               },

               // If the XHR call is not successful, fire the TreeView
               // callback anyway
               failure : function DLT_lND_failure(oResponse) {
                  if (oResponse.status == 401) {
                     // Our session has likely timed-out, so refresh to
                     // offer the login page
                     window.location.reload();
                  } else {
                     try {
                        var response = YAHOO.lang.JSON.parse(oResponse.responseText);

                        // Get the "Documents" node
                        var rootNode = this.widgets.treeview.getRoot();
                        var docNode = rootNode.children[0];
                        docNode.isLoading = false;
                        docNode.isLeaf = true;
                        docNode.label = response.message;
                        docNode.labelStyle = "ygtverror";
                        rootNode.refresh();
                     } catch (e) {
                     }
                  }
               },

               // Callback function scope
               scope : me,

               // XHR response argument information
               argument : {
                  "node" : node,
                  "fnLoadComplete" : fnLoadComplete
               }
            };

            // Make the XHR call using Connection Manager's asyncRequest
            // method
            YAHOO.util.Connect.asyncRequest('GET', uri, callback);
         };

         // Build the TreeView widget
         this._buildTree();

         this.isReady = true;
         if (this.initialFilter !== null) {
            // We weren't ready for the first filterChanged event, so fake
            // it here
            this.onFilterChanged("filterChanged", [ null, this.initialFilter ]);
         }
      },

      /**
       * Fired by YUI TreeView when a node label is clicked
       * 
       * @method onNodeClicked
       * @param args.event
       *           {HTML Event} the event object
       * @param args.node
       *           {YAHOO.widget.Node} the node clicked
       * @return allowExpand {boolean} allow or disallow node expansion
       */
      onNodeClicked : function DLT_onNodeClicked(args) {
         var node = args.node;
         this.log("OnNodeClicked");
         if (this.isFilterOwner && node == this.selectedNode) {
            this.log("isFilterOwner && this.selectedNode");
            YAHOO.Bubbling.fire("metadataRefresh");
         } else {
            this.log("else >>>");
            this._updateSelectedNode(node);

            // Fire the change filter event
            this.log("this.name :" + this.name);
            YAHOO.Bubbling.fire("changeFilter", {
               filterOwner : this.name,
               filterId : "metadata",
               filterData : (JSON.stringify(this._buildTreeNodeDocLibQuery(node)) + "|" + this.msg("filter.metadata.filterDisplay", window.unescape(this.options.rootLabel), node.data.description))
            });
         }

         Event.stopEvent(args.event);
         // Prevent the tree node from expanding (TODO: user preference?)
         return false;
      },

      /**
       * Build the metadata query to fill doclist
       */
      _buildTreeNodeDocLibQuery : function __buildTreeNodeDocLibQuery(node) {
         var query = {};
         if (this.options.documentTypeIsAspect == "true") {
            query.aspect = this.options.nodeTypeDocument;
         } else {
            query.type = this.options.nodeTypeDocument;
         }
         query.fields = {};
         query.fields[this.options.assoTypeDocument + "search"] = {
            type : "String",
            operator : "is",
            values : [ (node.data.nodeRef ? node.data.nodeRef : "null") ]
         };

         return query;
      },

      /**
       * Build URI parameter string for treenode JSON data webscript
       * 
       * @method _buildTreeNodeUrl
       * @param path
       *           {string} Path to query
       */
      _buildTreeNodeUrl_nodeChild : function DLT__buildTreeNodeUrl(node) {
         var uriTemplate = "side/slingshot/doclib/treenode/site/"
               + $combine(encodeURIComponent(this.options.siteId), encodeURIComponent(this.options.containerId), Alfresco.util.encodeURIPath(node.data.path));
         uriTemplate += "?perms=false&children=" + this.options.evaluateChildFolders + "&max=" + this.options.maximumFolderCount;
         uriTemplate += "&nodeType=" + this.options.nodeType;
         uriTemplate += "&selectableTypeIsAspect=" + this.options.selectableTypeIsAspect;
         uriTemplate += "&assoType=" + this.options.assoType;
         uriTemplate += "&rootProperty=" + this.options.rootProperty;
         uriTemplate += "&rootName=" + this.options.rootName;
         uriTemplate += "&nodeRef=" + node.data.nodeRef;
         this.log("URL :" + uriTemplate);

         return Alfresco.constants.PROXY_URI + uriTemplate;
      }
   });
})();
