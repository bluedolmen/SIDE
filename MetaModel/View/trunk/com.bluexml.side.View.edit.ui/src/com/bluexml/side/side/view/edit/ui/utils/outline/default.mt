<%
metamodel http://www.kerblue.org/workflow/1.0
import com.bluexml.side.side.view.edit.ui.utils.outline.service.OutlineViewService
import com.bluexml.side.side.view.edit.ui.utils.outline.service.HTMLEncoder
%>
<%script type="view.ViewCollection" name="default" file="output.html"%>
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

	.dataTable {
	width: 700px;
	padding: 0;
	margin: 0;
}

caption {
	padding: 0 0 5px 0;
	width: 700px;
	font: italic 11px "Trebuchet MS", Verdana, Arial, Helvetica, sans-serif;
	text-align: right;
}

th, .header {
	font: bold 11px "Trebuchet MS", Verdana, Arial, Helvetica, sans-serif;
	color: #4f6b72;
	border-right: 1px solid #C1DAD7;
	border-bottom: 1px solid #C1DAD7;
	border-top: 1px solid #C1DAD7;
	letter-spacing: 2px;
	text-transform: uppercase;
	text-align: left;
	padding: 6px 6px 6px 12px;
	background: #CAE8EA url(images/bg_header.jpg) no-repeat;
}

th.nobg {
	border-top: 0;
	border-left: 0;
	border-right: 1px solid #C1DAD7;
	background: none;
}

td {
	border-right: 1px solid #C1DAD7;
	border-bottom: 1px solid #C1DAD7;
	background: #fff;
	padding: 6px 6px 6px 12px;
	color: #4f6b72;
}


td.alt {
	background: #F5FAFA;
	color: #797268;
}

td.spec {
	border-left: 1px solid #C1DAD7;
	border-top: 0;
	text-align:center;
	font: bold 10px "Trebuchet MS", Verdana, Arial, Helvetica, sans-serif;
}

th.specalt {
	border-left: 1px solid #C1DAD7;
	border-top: 0;
	background: #f5fafa url(images/bullet2.gif) no-repeat;
	font: bold 10px "Trebuchet MS", Verdana, Arial, Helvetica, sans-serif;
	color: #797268;
}

ul#main-nav {font-family:helvetica,arial,sans-serif;margin:0;padding:0;width:40em;}
ul#main-nav li {margin:0;padding:0;list-style:none;margin:0 0 0.3em 0;}
ul#main-nav li a {text-decoration:none;display:block;padding:0.3em 0.5em;border:1px solid silver;color:#003;background:#fff;}
ul#main-nav li a:hover {border:1px solid gray;color:#000;background:#efefef}

#facetmap{
	position:absolute;
}
#criteria {
	width:200px;
	float: left;
}
#result {
	margin-left:205px;
}
</style>
</head>
<body>

<script type="text/javascript">

</script>
<em>Outline view, generation output may change.</em>
<%for (views){%>
	<%if (current().name == getNameOfSelectedView()){%>
		<%if (cast("FacetMap")){%>
			<%current().getHTMLForFacetMap()%>
		<%}else if(cast("Tree")){%>
			<%current().getHTMLForTree()%>
		<%}else if(cast("DataList")){%>
			<%current().getHTMLForDataList()%>
		<%}else if(cast("DataTable")){%>
			<%current().getHTMLForDataTable()%>
		<%}%>
	<%}%>
<%}%>
<%for (composedViews){%>
	<%if (cast("ComposedView")){%>
		<%current().getHTMLForComposedView()%>
	<%}%>
<%}%>
</body>
</html>

<%script type="view.AbstractView" name="getHTMLForFacetMap"%>
<br>
<div id="facetmap">
	<div id="criteria">
		<%for (children){%>
			<%if (cast("Field")){%>
				<div>
					<div class="header">
						<%name%>
					</div>
					<div id="">
						E.G. value, E.G. value, E.G. value,
						E.G. value, ...
						<br><br>
					</div>
				</div>
			<%}%>
		<%}%>
	</div>
	<div id="result">
		<h2>Results :</h2>
		<%for (children){%>
			<%if (cast("AbstractView")){%>
				<%if (cast("FacetMap")){%>
					<%current().getHTMLForFacetMap()%>
				<%}else if(cast("Tree")){%>
					<%current().getHTMLForTree()%>
				<%}else if(cast("DataList")){%>
					<%current().getHTMLForDataList()%>
				<%}else if(cast("DataTable")){%>
					<%current().getHTMLForDataTable()%>
				<%}%>
			<%}%>
		<%}%>
	</div>
</div>

<%script type="view.AbstractView" name="getHTMLForTree"%>
<ul>
	<%for (children){%>
		<li><a href="#">An entry with
			<%if (i == 0){%>
				<%for (current("Tree").children.sep(", ")){%>
				<%name%>
				<%}%> values
				<ul>
					<li>
			<%}%>

			An entry with
			<%for (current("Tree").children.sep(", ")){%>
				<%name%>
			<%}%> values
			<%if (i == 0){%>
				</li>
			</ul>
			<%}%>
			</a>
		</li>
	<%}%>
</ul>

<%script type="view.AbstractView" name="getHTMLForDataList"%>
<ul id="main-nav">
	<%for (children){%>
		<li><a href="#">An entry with
			<%for (current("DataList").children.sep(", ")){%>
				<%name%>
			<%}%> values
			</a>
		</li>
	<%}%>
</ul>

<%script type="view.AbstractView" name="getHTMLForComposedView"%>
<br>
<h1><%name%></h1>
<br>
<%for (children){%>
	<%if (cast("AbstractView")){%>
		<%if (cast("FacetMap")){%>
			<%current().getHTMLForFacetMap()%>
		<%}else if(cast("Tree")){%>
			<%current().getHTMLForTree()%>
		<%}else if(cast("DataList")){%>
			<%current().getHTMLForDataList()%>
		<%}else if(cast("DataTable")){%>
			<%current().getHTMLForDataTable()%>
		<%}%>
	<%}%>
<%}%>

<%script type="view.AbstractView" name="getHTMLForDataTable"%>
<table class="dataTable">
	<tr>
		<th class="nobg">
			&nbsp;
		</th>
		<%for (children){%>
		<th>
			<%name%>
		</th>
		<%}%>
	</tr>
	<%for (children){%>
		<tr>
			<td class="spec">
				<input type="checkbox"/>
			</td>
			<%for (current("DataTable").children){%>
				<td>
				&nbsp;
				</td>
			<%}%>
		</tr>
	<%}%>
</table>