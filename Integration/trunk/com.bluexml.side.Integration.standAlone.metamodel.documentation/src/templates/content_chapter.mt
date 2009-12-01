<%
metamodel http://www.eclipse.org/emf/2002/Ecore
import com.bluexml.side.integration.standalone.metamodel.documentation.CustomDocumentationService
%>

<%script type="ecore.EPackage" name="content_chapter"%>
<!-- Break line --> 
<%for (eClassifiers().sort()){%>
	<text:h text:style-name="Heading_20_2" text:outline-level="2"><%name%></text:h>
<!--	<%if (service::getSrvDocumentation() != null){%><%service::getSrvDocumentation()%><%}%> -->
	<text:p>
		<%service::getSrvDocumentation()%>
	</text:p>
	<%content_class%>
<%}%>

<%script type="ecore.EClassifier" name="content_class"%>
<text:h text:style-name="Heading_20_3" text:outline-level="3"><%name%> attributes</text:h>
<%for (service::getSrvEAttributes()){%>
	<text:h text:style-name="Heading_20_4" text:outline-level="4"><%name%> :</text:h>
	<text:p>
		<%service::getSrvDocumentation()%>
	</text:p>
<%}%>

<text:h text:style-name="Heading_20_3" text:outline-level="3"><%name%> associations</text:h>
<%for (getSrvAssociations()){%>

<text:h text:style-name="Heading_20_4" text:outline-level="4"><%name%></text:h>
<text:p>
	<text:a xlink:type="simple" xlink:href="#1.<%eType().name%>|outline"><%eType().name%></text:a>
</text:p>
<text:p>
	<%service::getSrvDocumentation()%>
</text:p>
<%}%>

<text:h text:style-name="Heading_20_3" text:outline-level="3"><%name%> validation rules</text:h>
<%if (service::getSrvValidationRuleDescription()!=null){%>
	<text:p>
		<%service::getSrvValidationRuleDescription()%>
	</text:p>
<%}%>
<%if (service::getSrvValidationRule()!=null){%>
<%for (service::getSrvValidationRule()){%>
<text:h text:style-name="Heading_20_4" text:outline-level="4"><%getKey()%> :</text:h>
<text:p>
	<%getValue().processString()%>
</text:p>
<%}%>
<%}%>


