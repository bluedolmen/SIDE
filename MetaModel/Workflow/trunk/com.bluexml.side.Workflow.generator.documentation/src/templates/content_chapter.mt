<%
metamodel http://www.kerblue.org/workflow/1.0
import com.bluexml.side.util.generator.documentation.services.DocumentationServices
%>

<%script type="workflow.Process" name="content_chapter"%>
<%for (swimlane) {%>
	<text:h text:style-name="Heading_20_1" text:outline-level="1"><%name%></text:h>
	<%if (documentation != null){%><%documentation%><%}%>
<%}%>
<%for (startstate) {%>
	<%displayStateDoc()%>
<%}%>
<%for (processstate) {%>
	<%displayStateDoc()%>
<%}%>
<%for (tasknode) {%>
	<%displayStateDoc()%>
<%}%>
<%for (endstate) {%>
	<%displayStateDoc()%>
<%}%>
<%if (getDiagImgPath().length > 0){%>
	<text:h text:style-name="Heading_20_1" text:outline-level="1">Diagrams</text:h>
	<%for (getDiagImgPath()) {%>
		<text:h text:style-name="Heading_20_2" text:outline-level="2"><%current%></text:h>
		<text:p text:style-name="Standard">
			<draw:frame draw:style-name="fr1" draw:name="<%current%>" text:anchor-type="paragraph" svg:width="15cm" svg:height="15cm" draw:z-index="0">
				<draw:image xlink:href="Pictures/<%current%>" xlink:type="simple" xlink:show="embed" xlink:actuate="onLoad"/>
			</draw:frame>
		</text:p>
	<%}%>
<%}%>

<%script type="State" name="displayStateDoc"%>
	<text:h text:style-name="Heading_20_1" text:outline-level="1"><%name%></text:h>
	<%if (documentation != null){%><%documentation%><%}%>
	<%if (cast("ProcessState")){%>
		<%displayStateTableHeader("Variable")%>
		<%for (current("ProcessState").variable){%>
			<%displayStateRow(name,documentation)%>
		<%}%>	
	<%}%>
	<%if (cast("UserTask")){%>
		<%displayStateTableHeader("Attribute")%>
		<%for (current("UserTask").attributes){%>
			<%displayStateRow(name,documentation)%>
		<%}%>		
	<%}%>
	<%if (cast("TransitionTask")){%>
		<%displayStateTableHeader("Transition")%>
		<%for (current("TransitionTask").transition){%>
			<%displayStateRow(name,documentation)%>
		<%}%>		
	<%}%>
	<%if (event.length() > 0){%>
		<%displayStateTableHeader('Event')%>
		<%for (event){%>
			<%displayStateRow(type,documentation)%>
		<%}%>
	<%}%>

<%script type="State" name="displayStateTableHeader"%>
<%-- args[0] : object name --%>
<text:h text:style-name="Heading_20_2" text:outline-level="2"><%name%> <%args(0)%></text:h>

<%script type="EObject" name="displayStateRow"%>
<%--
	 args[0] : name
     args[1] : documentation
--%>
<text:h text:style-name="Heading_20_3" text:outline-level="3"><%args(0)%> :</text:h>
<text:p>
<%if (args(1) != null){%><%args(1)%><%}%>
</text:p>
