script: {
	var uri = "/bluexml/doclist_user.xml";
	var result = remote.connect("alfresco").call(uri);
	
	if(result){
		model.result = true;
	}
}