/**
* DocumentList "side-editinline" action
* 
* @namespace Alfresco
* @class Alfresco.DocumentList
*/
(function()
{

/**
* Get detail information of content
*
* @method onActionSideEditOnline
* @param asset {object} represents the file to be actioned
*/
	YAHOO.Bubbling.fire("registerAction",
	{
	   actionName: "onActionSideEditOnline",
	   fn: function dlA_onActionSideEditOnline(asset)
		{
			var mimetype = asset.mimetype, appProgID = null, extensionMap = {
				xls : "application/vnd.ms-excel",
				ppt : "application/vnd.ms-powerpoint",
				doc : "application/msword",
				xlsx : "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet",
				pptx : "application/vnd.openxmlformats-officedocument.presentationml.presentation",
				docx : "application/vnd.openxmlformats-officedocument.wordprocessingml.document"
			};

			var extn = Alfresco.util.getFileExtension(asset.location.file);
			if (extn !== null) {
				extn = extn.toLowerCase();
				if (extensionMap.hasOwnProperty(extn)) {
					mimetype = extensionMap[extn];
					// call webscript
					//return window.open(Alfresco.constants.PROXY_URI + "side/editWordContentInline?webdavurl=" + asset.webdavUrl + "&mime=" + mimetype + "&mode=" + "write" + "&noderef=" + asset.nodeRef, "_blank",
					//		"directories=no,location=no,menubar=no,resizable=no,scrollbars=no,status=no,toolbar=no,width=500,height=500");
		         	var 
						Element = YAHOO.util.Element,
						url = Alfresco.constants.PROXY_URI + "side/editWordContentInline?webdavurl=" + asset.webdavUrl + "&mime="+ mimetype + "&mode=write" + "&noderef=" + asset.nodeRef, 
						iframe_ = document.createElement("iframe"),
						iframe = new Element(iframe_),
						body_ = new Element(document.body),
						simpledialog1 = new YAHOO.widget.SimpleDialog("simpledialog1", 
								{ width: "300px",
								  fixedcenter: true,
								  visible: true,
								  draggable: false,
								  close: false,
								  text: "Votre document est en cours de chargement, cette op\351ration peut durer 20 secondes.",
								  modal: true
								 } )
				
					;
					iframe.set("src", url);			
					
					// display:none does not work for firefox as the returned html is not interpreted and those the applet is not loaded
					//iframe.setStyle("display", "none");
					// with visibility:hidden the space is reserved at the end of the page and then a blank part appears
					//iframe.setStyle("visibility", "hidden");
					// better to move the frame outside the screen
					iframe.setStyle("position", "absolute");
					iframe.setStyle("top", "-99999px");
					iframe.setStyle("left", "-99999px");
					iframe.appendTo(document.body);
					simpledialog1.render(document.body);
					simpledialog1.center();
					YAHOO.util.Event.addListener(window, "message", onReceivedMessage);
					return;

					function onReceivedMessage(event) {
						var eventDescription = getMessageEventDescription(event);
						if (!eventDescription) return;
						
						var eventType = eventDescription.eventType;
						if (eventType == "endOfProcessing") {
							if (iframe == null) return;
							body_.removeChild(iframe);
							simpledialog1.hide();
							iframe = null;
							YAHOO.util.Event.removeListener(window, "message", onReceivedMessage);
						}
					}
					function getMessageEventDescription(event) {
						
						if (!event) return null;
			
						var data = event.data;
						if (YAHOO.lang.isString(data)) {
							// Try to decode the string to an Object
							try {
								data = YAHOO.lang.JSON.parse(data); // generate an exception if not valid...
							} catch (e) {
								Alfresco.logger.error('The received message' + data + 'is not a valid JSON String' );
								return null;
							}
						}
						
						var eventType = data.eventType;
						if (!eventType) return null;
						
						delete data.eventType;
						return {
							eventType : eventType,
							data : data
						};
					}
				}
			}
	        // No success in launching application via Applet; launch the WebDAV URL anyway
	        return window.open(Alfresco.constants.PROXY_URI + asset.contentUrl, "_blank");

		}
	});
})();


