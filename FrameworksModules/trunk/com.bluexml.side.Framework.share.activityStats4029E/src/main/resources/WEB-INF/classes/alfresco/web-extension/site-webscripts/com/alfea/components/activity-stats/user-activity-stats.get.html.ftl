<script type="text/javascript">//<![CDATA[
	new Alfea.component.ActivityStats("${args.htmlid}").setOptions({
		types: 	"all", 
		period: "today",
		sites:	"all",
		person: "all", 
		chartByDate: 
		{
			title:"<#if user.isAdmin>${msg("activity-stats.chartByDateAdmin.title")}<#else>${msg("activity-stats.chartByDate.title")}</#if>", 
			width:800,
			height:500
		},
		chartBySite: 
		{
			title:"<#if user.isAdmin>${msg("activity-stats.chartBySiteAdmin.title")}<#else>${msg("activity-stats.chartBySite.title")}</#if>", 
			width:450,
			height:300
		}
	});
//]]></script>

<div class="body activity-stats-body" id="${args.htmlid}-body">
	<div id="${args.htmlid}">
	<div id="${args.htmlid}-filters" class="activity-stats-filters">
		<form id="${args.htmlid}-filters-form" action="" method="GET">
			<div>
				<label>${msg("activity-stats.label.types")}</label><br/>
				<div class="scroll_checkboxes" id="${args.htmlid}-filters-form-types">
					<label class="highlight">${msg("activity-stats.label.all-types")}<input type="checkbox" style="visibility:hidden" name="types" value="all" checked="true"/></label>
				    <#list activityTypes as activityType>
				    	<label>${msg(activityType.label)}<input type="checkbox" style="visibility:hidden" name="types" value="${activityType.id}"/></label>
				    </#list>
				</div>
			</div>
			<div>
				<label>${msg("activity-stats.label.sites")}</label><br/>
				<div class="scroll_checkboxes" id="${args.htmlid}-filters-form-sites">
					<label class="highlight"><#if user.isAdmin>${msg("activity-stats.label.all-sites-admin")}<#else>${msg("activity-stats.label.all-sites")}</#if><input type="checkbox" style="visibility:hidden" name="sites" value="all" checked="true"/></label>
				    <#list sites as site>
				    	<label>${site.displayName}<input type="checkbox" style="visibility:hidden" name="sites" value="${site.name}"/></label>
				    </#list>
				</div>
			</div>
			<div>
				<label>${msg("activity-stats.label.person")}</label><br/>
				<select name="person" id="${args.htmlid}-filters-form-person" <#if user.isAdmin>disabled="disabled"</#if>>
					<option value="all" selected="selected">${msg("activity-stats.label.all-person")}</option>
					<option value="mine">${msg("activity-stats.label.mine")}</option>
					<option value="others">${msg("activity-stats.label.others")}</option>
				</select>
				<br/>
				<label>${msg("activity-stats.label.period")}</label><br/>
				<select name="period" id="${args.htmlid}-filters-form-period">
					<option value="today" selected="selected">${msg("activity-stats.label.today")}</option>
					<option value="week">${msg("activity-stats.label.week")}</option>
					<option value="month">${msg("activity-stats.label.month")}</option>
					<option value="year">${msg("activity-stats.label.year")}</option>
				</select>
			</div>
			<br/><br/><br/><br/>
			<input type="submit" id="${args.htmlid}-filters-refresh" name="refresh" value="Refresh"/>
		</form>
	</div>

	<div style="clear: both;"/>
	<div id="${args.htmlid}-chart-activities-by-date"></div>
	</div>
	<div id="${args.htmlid}-chart-activities-by-site"></div>
	</div>
</div>
