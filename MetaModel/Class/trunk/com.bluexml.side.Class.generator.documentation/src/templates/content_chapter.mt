<%--
    Copyright (C) 2007-20013  BlueXML - www.bluexml.com

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU Affero General Public License as
    published by the Free Software Foundation, either version 3 of the
    License, or (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU Affero General Public License for more details.

    You should have received a copy of the GNU Affero General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
--%>
<%
metamodel http://www.kerblue.org/class/1.0
import com.bluexml.side.util.generator.documentation.services.DocumentationServices
%>

<%script type="clazz.ClassPackage" name="content_chapter"%>
<%for (getAllClasses().sort()){%>
	<text:h text:style-name="Heading_20_1" text:outline-level="1"><%getLabel()%></text:h>
	<%if (documentation != null){%><%documentation%><%}%>
	<%content_class%>
<%}%>
<%if (getDiagImgPath.length > 0){%>
	<text:h text:style-name="Heading_20_1" text:outline-level="1">Diagrams</text:h>
	<%for (getDiagImgPath) {%>
		<text:h text:style-name="Heading_20_2" text:outline-level="2"><%if (i() > 0){%><%}%><%current%></text:h>
		<text:p text:style-name="Standard">
			<draw:frame draw:style-name="fr1" draw:name="<%current%>" text:anchor-type="paragraph" svg:x="0.52cm" svg:y="0.044cm" svg:width="15cm" svg:height="15cm" draw:z-index="0">
				<draw:image xlink:href="Pictures/<%current%>" xlink:type="simple" xlink:show="embed" xlink:actuate="onLoad"/>
			</draw:frame>
		</text:p>
	<%}%>
<%}%>

<%script type="clazz.Clazz" name="content_class"%>
<text:p>
	<%if (documentation != null){%><%documentation%><%}%>
</text:p>
<text:h text:style-name="Heading_20_2" text:outline-level="2"><%getLabel()%> attributes</text:h>
<%for (getAllAttributes()){%>
<text:h text:style-name="Heading_20_3" text:outline-level="3"><%getLabel()%> :</text:h>
<text:p>
	<%if (documentation != null){%><%documentation%><%}%>
</text:p>
<%}%>

<text:h text:style-name="Heading_20_2" text:outline-level="2"><%getLabel()%> associations</text:h>
<%for (getSourceAssociations()){%>
<text:h text:style-name="Heading_20_3" text:outline-level="3"><%getLabel()%></text:h>
<text:p>
	<text:a xlink:type="simple" xlink:href="#1.<%getTarget().getLabel()%>|outline"><%getTarget().getLabel()%></text:a>
</text:p>
<text:p>
	<%if (documentation != null){%><%documentation%><%}%>
</text:p>
<%}%>
