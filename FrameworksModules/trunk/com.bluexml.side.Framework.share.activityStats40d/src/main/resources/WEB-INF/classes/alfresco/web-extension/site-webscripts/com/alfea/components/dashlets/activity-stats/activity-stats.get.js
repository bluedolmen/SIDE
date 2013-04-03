/**
 * Call remote Repo script to get the number of connected users
 * @return
 */
function getConnectedUsers() {
	var connector = remote.connect("alfresco");
	var result = connector.get("/alfea/activity-stats/connected-users");

	if (result.status == 200) {
		// Create javascript objects from the server response
		var connectedUsers = eval("(" + result + ")");
		return connectedUsers;
	}

	status.setCode(result.status, result.response);
	return null;
}

/**
 * Call remote Repo script to get relevant activities number
 * @param oldestDate
 * @return
 */
function getActivitiesNumber(oldestDate) {
	var activitiesNumber = 0, mode = args.mode, site = args.site, userFilter = args.userFilter, result = {
		status : 0
	};
	var connector = remote.connect("alfresco");
	//TODO May be create a faster java backed webscript to get the number of activities
	result = connector.get("/api/activities/feed/user?format=json");

	if (result.status == 200) {
		// Create javascript objects from the server response
		var activityList = eval("(" + result + ")");

		for ( var i = 0, ii = activityList.length; i < ii; i++) {
			var activity = activityList[i];

			var date = fromISO8601(activity.postDate);

			// Outside oldest date?
			if (date >= oldestDate) {
				activitiesNumber++;
			}
		}
		return activitiesNumber
	}

	status.setCode(result.status, result.response);
	return null;
}

/**
 * Convert from ISO8601 date to JavaScript date
 * @param formattedString
 * @return
 */
function fromISO8601(formattedString)
 {
    var isoRegExp = /^(?:(\d{4})(?:-(\d{2})(?:-(\d{2}))?)?)?(?:T(\d{2}):(\d{2})(?::(\d{2})(.\d+)?)?((?:[+-](\d{2}):(\d{2}))|Z)?)?$/;

    var match = isoRegExp.exec(formattedString);
    var result = null;

    if (match)
    {
       match.shift();
       if (match[1]){match[1]--;} // Javascript Date months are 0-based
       if (match[6]){match[6] *= 1000;} // Javascript Date expects fractional seconds as milliseconds

       result = new Date(match[0]||1970, match[1]||0, match[2]||1, match[3]||0, match[4]||0, match[5]||0, match[6]||0);

       var offset = 0;
       var zoneSign = match[7] && match[7].charAt(0);
       if (zoneSign != 'Z')
       {
          offset = ((match[8] || 0) * 60) + (Number(match[9]) || 0);
          if (zoneSign != '-')
          {
             offset *= -1;
          }
       }
       if (zoneSign)
       {
          offset -= result.getTimezoneOffset();
       }
       if (offset)
       {
          result.setTime(result.getTime() + offset * 60000);
       }
    }

    return result; // Date or null
 }

 /**
  * Main function
  * @return
  */
function main() {
	var connectedUsers = getConnectedUsers();
	var date = new Date();
	date.setHours(0, 0, 0, 0);
	var activitiesNumber = getActivitiesNumber(date);

	model.connectedUsersCount = connectedUsers.connectedUsersCount;
	model.connectedUsers = connectedUsers.connectedUsers;
	model.activitiesNumber = activitiesNumber;	
}

main();