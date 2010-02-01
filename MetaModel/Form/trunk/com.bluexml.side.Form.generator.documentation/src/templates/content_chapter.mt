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
		Link to workflow : <%current("FormWorkflow").ref.eContainer().evaluate("title")%>
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