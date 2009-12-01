<%
metamodel http://www.w3.org/Graphics/SVG/1.1/
%>
<%script type="SVG.Svg" name="toSVG" file="file.svg"%>
<?xml version="1.0" encoding="UTF-8" standalone="no"?>
<svg
	<%if (baseProfile.length() > 0){%> baseprofile="<%baseProfile%>"<%}%>
	<%if (namespace.length() > 0){%> xmlns="<%namespace%>"<%}%>
	<%if (version.length() > 0){%> version="<%version%>"<%}%>
	<%if (width.length() > 0){%> width="<%width%>"<%}%>
	<%if (height.length() > 0){%> height="<%height%>"<%}%>
	>
	<%for (children[eClass().name.equalsIgnoreCase("Path")]){%>
		<%cast("Path").writePath%>
	<%}%>
	<%for (children[eClass().name.equalsIgnoreCase("Rect")]){%>
		<%cast("Rect").writeRect%>
	<%}%>
	<%for (children[eClass().name.equalsIgnoreCase("Text")]){%>
		<%cast("Text").writeText%>
	<%}%>
</svg>
<%script type="SVG.Rect" name="writeRect"%>
<rect
	x="<%position.x.toString().replaceAll("Ê","")%>"
	y="<%position.y.toString().replaceAll("Ê","")%>"
	width="<%size.width%>"
	height="<%size.height%>"
	style="<%fill%><%stroke%>"
	>
</rect>
<%script type="SVG.Text" name="writeText"%>
<text
	style="-inkscape-font-specification:Bitstream Vera Sans;text-align=<%textAlign%>;font-size=<%fontSize%>;text-anchor:middle;"
	x="<%position.x.toString().replaceAll("Ê","")%>"
	y="<%position.y.toString().replaceAll(" ","")%>"
	>
	<%content%>
</text>
<%script type="SVG.Path" name="writePath"%>
<path d="<%d%>"
	style="fill:none;fill-rule:evenodd;stroke:#000000;stroke-width:1px;stroke-linecap:butt;stroke-linejoin:miter;stroke-opacity:1">
</path>