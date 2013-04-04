<div class="dashlet">
    <div class="title">${msg("activity-stats.header")}</div>
        <div class="toolbar">
            <a href="${url.context}/page/user/${user.name}/user-activity-stats" class="theme-color-1">${msg("link.fullReport")}</a>
        </div>
   	<div class="body activity-stats-body" id="${args.htmlid}-body">
	    <script type='text/javascript'>
	      google.load('visualization', '1', {packages:['gauge']});
	
	      google.setOnLoadCallback(drawChart);
	
	      function drawChart() {
	        var data = new google.visualization.DataTable();
	        data.addColumn('string', 'Label');
	        data.addColumn('number', 'Value');
	        data.addRows(3);
	        data.setValue(0, 0, '${msg("activity-stats.sessions")}');
	        data.setValue(0, 1, ${connectedUsersCount});
	        data.setValue(1, 0, '${msg("activity-stats.activities")}');
	        data.setValue(1, 1, ${activitiesNumber});
	        data.setValue(2, 0, '${msg("activity-stats.documents")}');
	        data.setValue(2, 1, ${documentsNumber});
	
	        var chart = new google.visualization.Gauge(document.getElementById('${args.htmlid}-gauges_div'));
	        var options = {width: 400, height: 120, redFrom: 400, redTo: 500, yellowFrom:300, yellowTo: 400, minorTicks: 20};
	        chart.draw(data, options);
	      }
	    </script>
	    
	    <div id="${args.htmlid}-gauges_div"></div>

            <#--
            <div class="detail-list-item first-item last-item">
		${connectedUsersCount} currently connected users.
	    </div>
            <div class="detail-list-item first-item last-item">
		 ${activitiesNumber} activities today.
	    </div>
	    -->
	    
	    <#-- If user is admin -->
	    <#if user.isAdmin>
                <div class="detail-list-item first-item last-item">
	    	    <br/>
	    	    <img src="${page.url.context}/res/components/extension/alfea/dashlets/activity-stats/email_users.gif" style="vertical-align:top"/>&nbsp;&nbsp;<a href="mailto:<#list connectedUsers as connectedUser>${connectedUser.email}<#if connectedUser_has_next>;</#if></#list>">${msg("activity-stats.send-email", connectedUsersCount)}</a> 
	        </div>
            </#if>
	    
	</div>
</div>