script: {
	if (ticketValide != null) {
		model.validity = ticketValide;
	} else {
		status.code = 500;
		status.message = "something wrong check given parameters";
		status.redirect = true;
		break script;
	}

}