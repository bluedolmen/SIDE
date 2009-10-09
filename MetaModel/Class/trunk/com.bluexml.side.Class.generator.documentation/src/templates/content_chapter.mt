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