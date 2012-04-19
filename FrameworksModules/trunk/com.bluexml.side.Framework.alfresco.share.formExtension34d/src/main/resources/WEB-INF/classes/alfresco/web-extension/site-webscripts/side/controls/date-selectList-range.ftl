<#assign controlId = fieldHtmlId + "-cntrl">



<div class="form-field">
<script type="text/javascript">//<![CDATA[
(function()
{	
	new SIDE.DateRangeWidget("${controlId}", "${fieldHtmlId}").setOptions(
   	{	
		currentValue: "${field.value?js_string}",
        mandatory: ${field.mandatory?string}
        <#if field.control.params.mandatoryDay??>, mandatoryDay :${field.control.params.mandatoryDay}</#if>
        <#if field.control.params.mandatoryMonth??>, mandatoryMonth :${field.control.params.mandatoryMonth}</#if>
        <#if field.control.params.minDate??>, minDate :"${field.control.params.minDate}"</#if>
        <#if field.control.params.maxDate??>, maxDate :"${field.control.params.maxDate}"</#if>
        
	}).setMessages(
		${messages}
	);
	
})();

//]]></script>


<label for="${fieldHtmlId}">${field.label?html}:<#if field.mandatory><span class="mandatory-indicator">${msg("form.required.fields.marker")}</span></#if></label>
<input id="${fieldHtmlId}" type="hidden" name="${field.name}-date-range" value="${field.value?html}" />
<span id="${controlId}">
<span>${msg("form.control.date-range.from")}:</span><@renderDate rangeId="min" field=field fieldHtmlId=fieldHtmlId />
<span>${msg("form.control.date-range.to")}:</span><@renderDate rangeId="max" field=field fieldHtmlId=fieldHtmlId />
</span>
</div>

<#macro renderDate rangeId field fieldHtmlId >
<span id="${rangeId}-${fieldHtmlId}-day-label">${msg("form.control.date.select.list.day")}</span>
<select id="${rangeId}-${fieldHtmlId}-day" name="-">
<option value="1">1</option>
<option value="2">2</option>
<option value="3">3</option>
<option value="4">4</option>
<option value="5">5</option>
<option value="6">6</option>
<option value="7">7</option>
<option value="8">8</option>
<option value="9">9</option>
<option value="10">10</option>
<option value="11">11</option>
<option value="12">12</option>
<option value="13">13</option>
<option value="14">14</option>
<option value="15">15</option>
<option value="16">16</option>
<option value="17">17</option>
<option value="18">18</option>
<option value="19">19</option>
<option value="20">20</option>
<option value="21">21</option>
<option value="22">22</option>
<option value="23">23</option>
<option value="24">24</option>
<option value="25">25</option>
<option value="26">26</option>
<option value="27">27</option>
<option value="28">28</option>
<option value="29">29</option>
<option value="30">30</option>
<option value="31">31</option>
</select>
<span id="${rangeId}-${fieldHtmlId}-month-label">${msg("form.control.date.select.list.month")}</span>
<select id="${rangeId}-${fieldHtmlId}-month" name="-">
<option value="1">1</option>
<option value="2">2</option>
<option value="3">3</option>
<option value="4">4</option>
<option value="5">5</option>
<option value="6">6</option>
<option value="7">7</option>
<option value="8">8</option>
<option value="9">9</option>
<option value="10">10</option>
<option value="11">11</option>
<option value="12">12</option>
</select>

<span id="${rangeId}-${fieldHtmlId}-year-label">${msg("form.control.date.select.list.year")}</span>
<select id="${rangeId}-${fieldHtmlId}-year" name="-">

</select>

</#macro>
