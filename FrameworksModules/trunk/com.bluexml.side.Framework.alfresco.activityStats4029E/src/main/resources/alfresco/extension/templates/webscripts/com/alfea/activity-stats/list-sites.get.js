/**
 * Retrieve list sites
 * @return
 */
function main(){
	var sites;
	var userName = person.properties["cm:userName"];
	if(people.isAdmin(person)){
		sites = siteService.listSites(null,null);
	}
	else{
		sites = siteService.listUserSites(userName);
	}
	model.sites = sites;
}

main();