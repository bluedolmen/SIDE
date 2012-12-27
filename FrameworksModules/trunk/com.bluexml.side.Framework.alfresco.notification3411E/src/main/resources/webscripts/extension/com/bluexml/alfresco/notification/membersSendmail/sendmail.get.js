function main() {
	logger.log("Site Members Send Mail Service");
	// Parse file attributes
	var siteId = null;
	var message = null;
	var subject = null;

	siteId = args["siteId"];
	message = args["message"];
	subject = args["subject"];

	// Get the site

	logger.log("site.shortName :" + siteId);
	var site = siteService.getSite(siteId);
	var authorities = site.listMembers("", "", 0, true);
	var memberships = [];
	for (var id in authorities) {
		memberships.push(id);
	}
	
	
	var mail = actions.create("mail");
	mail.parameters.to_many = memberships;
	mail.parameters.subject = subject;
	mail.parameters.from = person.email;
	mail.parameters.text = message;
	// execute action against a document
	mail.execute(site.node.nodeRef);

}

main();