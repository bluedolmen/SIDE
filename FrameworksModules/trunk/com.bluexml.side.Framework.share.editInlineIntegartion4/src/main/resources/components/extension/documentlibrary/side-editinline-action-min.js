(function(){YAHOO.Bubbling.fire("registerAction",{actionName:"onActionSideEditOnline",fn:function(t){var n=t.mimetype,r=null,i={xls:"application/vnd.ms-excel",ppt:"application/vnd.ms-powerpoint",doc:"application/msword",xlsx:"application/vnd.openxmlformats-officedocument.spreadsheetml.sheet",pptx:"application/vnd.openxmlformats-officedocument.presentationml.presentation",docx:"application/vnd.openxmlformats-officedocument.wordprocessingml.document"};var s=Alfresco.util.getFileExtension(t.location.file);if(s!==null){s=s.toLowerCase();if(i.hasOwnProperty(s)){n=i[s];var o=YAHOO.util.Element,u=Alfresco.constants.PROXY_URI+"side/editWordContentInline?webdavurl="+t.webdavUrl+"&mime="+n+"&mode=write"+"&noderef="+t.nodeRef,a=document.createElement("iframe"),f=new o(a),l=new o(document.body),c=new YAHOO.widget.SimpleDialog("simpledialog1",{width:"300px",fixedcenter:true,visible:true,draggable:false,close:false,text:"Votre document est en cours de chargement, cette op�ration peut durer 20 secondes.",modal:true});f.set("src",u);f.setStyle("position","absolute");f.setStyle("top","-99999px");f.setStyle("left","-99999px");f.appendTo(document.body);c.render(document.body);c.center();YAHOO.util.Event.addListener(window,"message",h);return;function h(e){var t=p(e);if(!t)return;var n=t.eventType;if(n=="endOfProcessing"){if(f==null)return;l.removeChild(f);c.hide();f=null;YAHOO.util.Event.removeListener(window,"message",h)}}function p(e){if(!e)return null;var t=e.data;if(YAHOO.lang.isString(t)){try{t=YAHOO.lang.JSON.parse(t)}catch(n){Alfresco.logger.error("The received message"+t+"is not a valid JSON String");return null}}var r=t.eventType;if(!r)return null;delete t.eventType;return{eventType:r,data:t}}}}return window.open(Alfresco.constants.PROXY_URI+t.contentUrl,"_blank")}})})()