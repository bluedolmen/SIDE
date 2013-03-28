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
metamodel http://www.kerblue.org/form/1.0
import com.bluexml.side.util.generator.documentation.services.DocumentationServices
%>

<%script type="form.FormCollection" name="content_chapter"%>

<!-- Break line -->
<%for (forms.sort()){%>
	<text:h text:style-name="Heading_20_1" text:outline-level="1"><%getLabel()%></text:h>
	<%if (cast("FormClass")){%>
		Link to class : <%current("FormClass").real_class.getLabel()%>
	<%}%>
	<%if (cast("FormWorkflow")){%>
		Link to workflow : <%filter("FormWorkflow").ref.eContainer().filter("workflow.Process").name%>
	<%}%>

	<%if (documentation != null){%><%documentation%><%}%>
	<%content_form%>
<%}%>
<%if (getOutlineRelativePath.length > 0){%>
	<text:h text:style-name="Heading_20_1" text:outline-level="1">Outline view</text:h>
	<%for (getOutlineRelativePath) {%>
		<text:h text:style-name="Heading_20_2" text:outline-level="2"><%current%></text:h>
	<%}%>
<%}%>

<%script type="form.FormContainer" name="content_form"%>
<text:h text:style-name="Heading_20_2" text:outline-level="2"><%getLabel()%> Fields</text:h>
<%for (getFields()){%>
<text:h text:style-name="Heading_20_3" text:outline-level="3"><%getLabel()%> :</text:h>
<text:p>
	<%if (documentation != null){%><%documentation%><%}%>
</text:p>
<text:p>
	<%if (cast("Reference")){%>
		Link to Form :
		<%for (current("Reference").target) {%>
			<text:a xlink:type="simple"
       		 xlink:href="#1.<%current("FormContainer").getLabel()%>|outline"><%current("FormContainer").getLabel()%></text:a>
		<%}%>
        for Class <%current("Reference").real_class.getLabel()%>.
	<%}%>
</text:p>
		
<%}%>
