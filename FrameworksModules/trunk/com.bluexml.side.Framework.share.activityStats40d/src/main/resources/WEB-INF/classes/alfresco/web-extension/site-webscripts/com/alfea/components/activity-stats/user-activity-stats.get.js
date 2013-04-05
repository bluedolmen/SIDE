/**
 * Call remote Repo script to get the available sites
 * @return
 */
function getSites()
{
	var myConfig = new XML(config.script), sites = [];
	for each(var site in myConfig.sites.site)
	{
		sites.push({
	        name: site.@name.toString(),
	        displayName: site.@displayName.toString()
		});
  	}
	if (sites.length == 0) {
		// Call the correct repo script depending on the mode
		var connector = remote.connect("alfresco");
		var result = connector.get("/alfea/activity-stats/list-sites");
	   	if (result.status == 200)
	   	{
	      // Create javascript objects from the server response
	      var st = eval("(" + result + ")");
	      return st.sites;
	   	}   
   		status.setCode(result.status, result.response);
	} else {
		return sites;
	}
   	return null;
}
/**
* Parses the config file and returns an object model of activity types
*/
function getActivityTypes()
{
	var myConfig = new XML(config.script), activityTypes = [];
	for each(var activityType in myConfig..activitytype)
	{
		activityTypes.push({
	        id: activityType.@id.toString(),
	        label: activityType.@label.toString()
		});
  }
  return activityTypes;
}

/**
 * Main function to prepare Activity Stats Component
 * @return
 */
function main()
{	
	var sites = getSites();
	var activityTypes = getActivityTypes();
	model.sites = sites;
	model.activityTypes = activityTypes;
}

main();