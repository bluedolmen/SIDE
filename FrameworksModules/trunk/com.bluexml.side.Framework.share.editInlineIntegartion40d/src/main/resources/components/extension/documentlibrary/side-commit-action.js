/**
* DocumentList "side-commit" action
* 
* @namespace Alfresco
* @class Alfresco.DocumentList
*/
(function()
{

/**
* Get detail information of content
*
* @method onActionSideCommitEditing
* @param asset {object} represents the file to be actioned
*/
	YAHOO.Bubbling.fire("registerAction",
	{
	   actionName: "onActionSideCommitEditing",
	   fn: function dlA_onActionSideCommitEditing(asset)
		{
			var displayName = asset.displayName, nodeRef = new Alfresco.util.NodeRef(asset.nodeRef), original = asset.workingCopy.sourceNodeRef;

			this.modules.actions.genericAction({
				success : {
					callback : {
						fn : function(obj1, obj2) {
							Alfresco.util.navigateTo("document-details?nodeRef=" + original, "GET", "");
						}
					}
				},
				failure : {
					message : this.msg("message.checkin.failure", displayName)
				},
				webscript : {
					method : Alfresco.util.Ajax.POST,
					name : "checkin/node/{nodeRef}",
					params : {
						nodeRef : nodeRef.uri
					}
				}
			});
		}
	});
})();