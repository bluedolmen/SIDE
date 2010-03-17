script: {
	if (shortName != null && ticketValide != null) {
		// get Memberships
		var site = siteService.getSite(shortName);

		// get the filters
		var nameFilter = args["nf"];
		var roleFilter = args["rf"];

		var sizeString = args["size"];
		var memberships = site.listMembers(nameFilter, roleFilter,
				sizeString != null ? parseInt(sizeString) : 0);

		var users = getUsers(memberships);
		model.validity = false;
		for ( var c = 0; c < users.length; c++) {
			var u = users[c];
			if (u == args["userName"]) {
				model.validity = true;
				break;
			}
		}
		model.validity = model.validity && ticketValide;
	} else {
		status.code = 500;
		status.message = "something wrong check given parameters";
		status.redirect = true;
		break script;
	}

}

function getUsers(memberships) {
	var users = [];
	for (userName in memberships) {
		var membershipRole = memberships[userName];
		if (userName.match("^GROUP_")) {
			var group_users = people.getMembers(userName);
			users = users.concat(group_users);

		} else {
			users.push(userName);
		}
	}
	return users;
}