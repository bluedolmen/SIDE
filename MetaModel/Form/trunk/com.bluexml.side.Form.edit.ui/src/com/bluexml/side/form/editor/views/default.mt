<%
metamodel http://www.kerblue.org/workflow/1.0
import com.bluexml.side.form.editor.views.service.OutlineViewService
import com.bluexml.side.form.editor.views.service.HTMLEncoder
%>
<%script type="form.FormCollection" name="default" file="output.html"%>
<html>
	<head>
	<style type="text/css">
	body {
	font-family:"Lucida Grande","Trebuchet MS",Verdana,Helvetica,sans-serif;
	font-size:90%;
	font-size-adjust:none;
	font-style:normal;
	font-variant:normal;
	font-weight:normal;
	line-height:normal;
	}
form.cmxform fieldset {
  margin-bottom: 10px;
}
form.cmxform legend {
  padding: 0 2px;
  font-weight: bold;
}
form.cmxform label {
  display: inline-block;
  line-height: 1.8;
  vertical-align: top;
  text-align:justify;
  font-size:13px;
}
form.cmxform fieldset ol {
  margin: 0;
  padding: 0;
}
form.cmxform fieldset li {
  list-style: none;
  padding: 1px;
  margin: 0;
}

form.cmxform em {
  font-weight: bold;
  font-style: normal;
  color: #f00;
}
form.cmxform label {
  width: 120px; /* Width of labels */
}
form.cmxform fieldset fieldset label {
  margin-left: 30px; /* Width plus 3 (html space) */
}

select.association{
	font: 14px "Microsoft Sans Serif" , Arial;
	vertical-align: middle;
	color: #006699;
	background-color: #FFFFFF;
	width:150px;
	border: 2px solid black;
}

div.tabs {
margin:0 0 20px;
width:100%;
}
ul.tabs {
background:transparent url(pixel.gif) repeat-x scroll left bottom;
float:left;
list-style-type:none;
width:100%;
}
ul.tabs li {
float:left;
margin:0 2px 0 0;
}
ul.tabs a {
background:#EEEEEE none repeat scroll 0 0;
border-color:#CCCCCC #CCCCCC -moz-use-text-color;
border-style:solid solid none;
border-width:1px 1px 0;
color:#666666;
display:block;
float:left;
font-weight:bold;
padding:4px 8px;
text-decoration:none;
font-family:Verdana,Arial,Helvetica,sans-serif;
font-size:10px;
}
ul.tabs a:hover {
background:#FFFFFF none repeat scroll 0 0;
}
ul.tabs a.active {
background:#FFFFFF none repeat scroll 0 0;
color:#000;
cursor:default;
padding-bottom:5px;
}

li.tabs, ul.tabs {
	margin:0px;
}
	</style>
</head>
<body>

<script type="text/javascript">
/*Yetii - Yet (E)Another Tab Interface Implementation,version 1.5,http://www.kminek.pl/lab/yetii/,Copyright (c) 2007-2008 Grzegorz Wojcik,Code licensed under the BSD License: http://www.kminek.pl/bsdlicense.txt*/function Yetii(){this.defaults={id:null,active:1,interval:null,wait:null,persist:null,tabclass:'tab',activeclass:'active',callback:null,leavecallback:null};this.activebackup=null;for(var n in arguments[0]){this.defaults[n]=arguments[0][n]};this.getTabs=function(){var a=[];var b=document.getElementById(this.defaults.id).getElementsByTagName('*');var c=new RegExp("(^|\\s)"+this.defaults.tabclass.replace(/\-/g,"\\-")+"(\\s|$)");for(var i=0;i<b.length;i++){if(c.test(b[i].className))a.push(b[i])}return a};this.links=document.getElementById(this.defaults.id+'-nav').getElementsByTagName('a');this.listitems=document.getElementById(this.defaults.id+'-nav').getElementsByTagName('li');this.show=function(a){for(var i=0;i<this.tabs.length;i++){this.tabs[i].style.display=((i+1)==a)?'block':'none';if((i+1)==a){this.addClass(this.links[i],this.defaults.activeclass);this.addClass(this.listitems[i],this.defaults.activeclass)}else{this.removeClass(this.links[i],this.defaults.activeclass);this.removeClass(this.listitems[i],this.defaults.activeclass)}}if(this.defaults.leavecallback&&(a!=this.activebackup))this.defaults.leavecallback(this.defaults.active);this.activebackup=a;this.defaults.active=a;if(this.defaults.callback)this.defaults.callback(a)};this.rotate=function(a){this.show(this.defaults.active);this.defaults.active++;if(this.defaults.active>this.tabs.length)this.defaults.active=1;var b=this;if(this.defaults.wait)clearTimeout(this.timer2);this.timer1=setTimeout(function(){b.rotate(a)},a*1000)};this.next=function(){this.defaults.active++;if(this.defaults.active>this.tabs.length)this.defaults.active=1;this.show(this.defaults.active)};this.previous=function(){this.defaults.active--;if(!this.defaults.active)this.defaults.active=this.tabs.length;this.show(this.defaults.active)};this.gup=function(a){a=a.replace(/[\[]/,"\\\[").replace(/[\]]/,"\\\]");var b="[\\?&]"+a+"=([^&#]*)";var c=new RegExp(b);var d=c.exec(window.location.href);if(d==null)return null;else return d[1]};this.parseurl=function(a){var b=this.gup(a);if(b==null)return null;if(parseInt(b))return parseInt(b);if(document.getElementById(b)){for(var i=0;i<this.tabs.length;i++){if(this.tabs[i].id==b)return(i+1)}}return null};this.createCookie=function(a,b,c){if(c){var d=new Date();d.setTime(d.getTime()+(c*24*60*60*1000));var e="; expires="+d.toGMTString()}else var e="";document.cookie=a+"="+b+e+"; path=/"};this.readCookie=function(a){var b=a+"=";var d=document.cookie.split(';');for(var i=0;i<d.length;i++){var c=d[i];while(c.charAt(0)==' ')c=c.substring(1,c.length);if(c.indexOf(b)==0)return c.substring(b.length,c.length)}return null};this.contains=function(a,b,c){return a.indexOf(b,c)!=-1};this.hasClass=function(a,b){return this.contains(a.className,b,' ')};this.addClass=function(a,b){if(!this.hasClass(a,b))a.className=(a.className+' '+b).replace(/\s{2,}/g,' ').replace(/^\s+|\s+$/g,'')};this.removeClass=function(a,b){a.className=a.className.replace(new RegExp('(^|\\s)'+b+'(?:\\s|$)'),'$1');a.className.replace(/\s{2,}/g,' ').replace(/^\s+|\s+$/g,'')};this.tabs=this.getTabs();this.defaults.active=(this.parseurl(this.defaults.id))?this.parseurl(this.defaults.id):this.defaults.active;if(this.defaults.persist&&this.readCookie(this.defaults.id))this.defaults.active=this.readCookie(this.defaults.id);this.activebackup=this.defaults.active;this.show(this.defaults.active);var f=this;for(var i=0;i<this.links.length;i++){this.links[i].customindex=i+1;this.links[i].onclick=function(){if(f.timer1)clearTimeout(f.timer1);if(f.timer2)clearTimeout(f.timer2);f.show(this.customindex);if(f.defaults.persist)f.createCookie(f.defaults.id,this.customindex,0);if(f.defaults.wait)f.timer2=setTimeout(function(){f.rotate(f.defaults.interval)},f.defaults.wait*1000);return false}}if(this.defaults.interval)this.rotate(this.defaults.interval)};
</script>
<em>Outline view, generation output may change.</em>
<%for (forms){%>
	<%if (getNameOfSelectedForm().length() == 0 || (current().id == getNameOfSelectedForm())){%>

		<form name="exampleForm" method="post" class="cmxform">
			<fieldset>
				<legend><%label.encode()%></legend>
			<ol>

			<!-- Tabs -->
			<div id="tab-container-<%id%>" class="tabs">
				<ul id="tab-container-<%id%>-nav" class="tabs">
			<%for (current().children){%>
				<%if (cast("FormGroup") && !cast("FormClass") && !cast("FormAspect")){%>
						<%if (cast("FormGroup") && current().presentation.equalsIgnoreCase("Tabbed")){%>
						    <li class="tabs"><a href="#tab-<%id%>"><%label%></a></li>
						<%}%>
				<%}%>
			<%}%>
			 </ul>

			<%for (current().children){%>
				<%current().getHtmlForFormElem(true)%>
			<%}%>

			<script type="text/javascript">
				var tabber<%id%> = new Yetii({
				id: 'tab-container-<%id%>'
				});
			</script>
			<%if (cast("FormClass")){%>
				<div style="margin-left: 150px;">
					<input type="submit" value="Submit"/> <input type="reset" value="Reset"/>
				</div>
			<%}%>
			</ol>
			</div>
			</fieldset>
		</form>
		<hr>

	<%}%>
<%}%>
</body>
</html>

<%script type="form.FormElement" name="getHtmlForFormElem"%>
<%if (cast("VirtualField")){%>
	<%if (current("VirtualField").link != null){%>
		<%current("VirtualField").link.getHtmlForFormElem(false)%>
	<%}%>
<%}else if ((cast("FormAspect") || cast("FormGroup") || cast("Reference")) && !current().hidden) {%>
	<%if (cast("FormGroup") && current("FormGroup").presentation.equalsIgnoreCase("Tabbed")){%>
		<div class="tab" id="tab-<%id%>">
	<%}else if (cast("FormGroup") && current("FormGroup").presentation.equalsIgnoreCase("horizontal")){%>
	&nbsp;<table><tr>
	<%}else if((cast("Reference") && (current("Reference").target.nSize() == 1 && !current("Reference").target.nGet(0).hidden)) || !cast("Reference")){%>
		<fieldset>
		<legend><%label.encode()%></legend>
			<ol>
	<%}%>
			<%if (cast("FormAspect") || cast("FormGroup")){%>
				<%for (current().children){%>
					<%if (current("FormGroup") && current("FormGroup").presentation.equalsIgnoreCase("horizontal")){%>
						<td>
					<%}%>
					<%current().getHtmlForFormElem(true)%>
					<%if (current("FormGroup") && current("FormGroup").presentation.equalsIgnoreCase("horizontal")){%>
						</td>
					<%}%>
				<%}%>
			<%}else if(cast("Reference")){%>
				<%-- For each target we display the widget --%>
				<%if ((current("Reference").target.nSize() == 1 && !current("Reference").target.nGet(0).hidden)
				 || current("Reference").target.nSize() > 1){%>
					<%for (current("Reference").target){%>
						<%if (!current().hidden){%>
							<%if (current("Reference").association_formClass != null){%>
								<table><tr><td valign="top">
							<%}%>

							<%-- Default widget : list of type with Add button or inline form --%>
							<%if (current("Reference").widget.equalsIgnoreCase("AssociationClassSelect")){%>
								<%if ((!current("Reference").max_bound.equalsIgnoreCase("1")) && (current("Reference").real_class.isAbstract || current("Reference").real_class.generalizations.length() > 0)){%>
									<%-- TODO : manage differents cases--%>
									<%current().getHtmlForFormElem(true)%>
								<%}else{%>
									<%current().getHtmlForFormElem(true)%>
								<%}%>
							<%}else{%>
								<%current().getHtmlForFormElem(true)%>
							<%}%>

							<%if (current("Reference").association_formClass != null){%>
								</td>
								<td  valign="top">
									<%current("Reference").association_formClass.getHtmlForFormElem(true)%>
								</td>
								</tr>
								</table>
							<%}%>
						<%}%>
					<%}%>
					<%if (current("Reference").max_bound > 1 || current("Reference").max_bound == -1){%>
						<input type="button" value=" + " width="20px">
					<%}%>
				<%}%>
			<%}%>

	<%if (cast("FormGroup") && current("FormGroup").presentation.equalsIgnoreCase("Tabbed")){%>
		</div>
	<%}else if (cast("FormGroup") && current("FormGroup").presentation.equalsIgnoreCase("horizontal")){%>
	</tr></table>
	<%}else{%>
		</ol>
	</fieldset>
	<%}%>

<%}else if (cast("Field")){%>
	<%if ((args(0) && !current("Field").isVirtualized()) || !args(0)){%>
		<li>
		<%if (current("Field").hidden) {%>
			<input type="hidden" name="<%id%>" id="<%id%>">
		<%}else{%>
			<%if (!cast("ActionField")){%>
				<label for="<%id%>"><%label.encode()%> :</label>
			<%}%>

			<%if (eClass().name.equalsIgnoreCase("CharField") || eClass().name.equalsIgnoreCase("RegexField")) {%>
				<input type="text" name="<%id%>" id="<%id%>" <%if (current("KerblueForms.CharField").max_length > 0){%>maxlength="<%current("KerblueForms.CharField").max_length%>"<%}%> <%if (!current("KerblueForms.CharField").initial.equalsIgnoreCase("")){%>value="<%current("KerblueForms.CharField").initial.encode()%>"<%}%>>
			<%}else{%>
				<%if (cast("EmailField")) {%>
					<input type="text" name="<%id%>" id="<%id%>" value="@" class="email">
				<%}%>
				<%if (cast("URLField") ) {%>
					<input type="text" name="<%id%>" id="<%id%>" value="http://">
				<%}%>
				<%if (cast("PhoneNumberField")) {%>
					<input type="text" name="<%id%>" id="<%id%>" value="">
				<%}%>
				<%if (cast("PasswordField")) {%>
					<input type="password" name="<%id%>" id="<%id%>">
				<%}%>
				<%if (cast("TextField")) {%>
					<textarea name="<%id%>" id="<%id%>" cols="40" rows="8"></textarea>
				<%}%>
				<%if (cast("FileField") || cast("ImageField")) {%>
					<input type="file" name="<%id%>" id="<%id%>">
				<%}%>
			<%}%>
			<%if (cast("TimeField")) {%>
					<input type="text" name="<%id%>" id="<%id%>" value=":">
			<%}%>
			<%if (cast("IntegerField") || cast("DecimalField") || cast("FloatField")) {%>
				<input type="text" name="<%id%>" id="<%id%>" value="">
			<%}%>
			<%if (cast("BooleanField")) {%>
				<input type="checkbox" name="<%id%>" id="<%id%>">
			<%}%>
			<%if (cast("DateField") || cast("DateTimeField")) {%>
				<select name="<%id%>_d" id="<%id%>_d">
					<option>Day</option>
				</select>
				<select name="<%id%>_m" id="<%id%>_m">
					<option>Month</option>
				</select>
				<select name="<%id%>_y" id="<%id%>_y">
					<option>Year</option>
				</select>
				<%if (cast("DateTimeField")) {%>
					&nbsp;&nbsp;&nbsp; <select name="<%id%>_m" id="<%id%>_m">
											<option>Hour</option>
										</select>
										<select name="<%id%>_m" id="<%id%>_m">
											<option>Min</option>
										</select>
				<%}%>
			<%}%>
			<%if (cast("ActionField")) {%>
				<input type="button" name="<%id%>" id="<%id%>" value="<%if (label.length > 0){%><%label.encode()%><%}else{%><%id%><%}%>">
			<%}%>
			<%if (cast("ModelChoiceField") || cast("ChoiceField")) {%>

				<select name="<%id%>" id="<%id%>" <%if (current().max_bound > 1 || current().max_bound == -1){%>MULTIPLE<%}%>
					<%if (cast("ModelChoiceField")){%>class="association"<%}%>>
					<%if (cast("ModelChoiceField")){%>
						<option><%current("ModelChoiceField").real_class.title.encode()%></option>
					<%}else{%>
						<%if (current().ref != null && current().ref.valueList != null){%>
							<%for (current().ref.valueList.literals){%>
								<option value="<%current().value%>"><%current().name%></option>
							<%}%>
						<%}%>
					<%}%>
				</select>
			<%}%>
		<%}%>
		<%if (current("Form.Field").mandatory) {%>
			<em>*</em>
		<%}%>
		</li>
	<%}%>
<%}else if (cast("StaticText")){%>
	<li>
		<%if (current("StaticText").label != ""){%>
			<label for="<%id%>"><%label.encode()%> :</label>
		<%}%>
		<%current("StaticText").value%>
	</li>
<%}%>