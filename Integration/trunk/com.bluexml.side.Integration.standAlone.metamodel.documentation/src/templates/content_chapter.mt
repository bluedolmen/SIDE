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
metamodel http://www.eclipse.org/emf/2002/Ecore
import com.bluexml.side.integration.standalone.metamodel.documentation.CustomDocumentationService
%>

<%script type="ecore.EPackage" name="content_chapter"%>
<!-- Break line --> 
<%for (eClassifiers().sort()){%>
	<text:h text:style-name="Heading_20_2" text:outline-level="2"><%name%></text:h>
<!--	<%if (service::getSrvDocumentation() != null){%><%service::getSrvDocumentation()%><%}%> -->
	<text:p>
		<%service::getSrvDocumentation().replaceAll("\\n","</text:p><text:p>" )%>
	</text:p>
	<%content_class%>
<%}%>

<%script type="ecore.EClassifier" name="content_class"%>
<text:h text:style-name="Heading_20_3" text:outline-level="3"><%name%> attributes</text:h>
<%for (service::getSrvEAttributes()){%>
	<text:h text:style-name="Heading_20_4" text:outline-level="4"><%name%> :</text:h>
	<text:p>
		<%service::getSrvDocumentation().replaceAll("\\n","</text:p><text:p>" )%>
	</text:p>
<%}%>

<text:h text:style-name="Heading_20_3" text:outline-level="3"><%name%> associations</text:h>
<%for (getSrvAssociations()){%>

<text:h text:style-name="Heading_20_4" text:outline-level="4"><%name%></text:h>
<text:p>
	<text:a xlink:type="simple" xlink:href="#1.<%eType().name%>|outline"><%eType().name%></text:a>
</text:p>
<text:p>
	<%service::getSrvDocumentation().replaceAll("\\n","</text:p><text:p>" )%>
</text:p>
<%}%>

<text:h text:style-name="Heading_20_3" text:outline-level="3"><%name%> validation rules</text:h>
<%if (service::getSrvValidationRuleDescription()!=null){%>
	<text:p>
		<%service::getSrvValidationRuleDescription().replaceAll("\\n","</text:p><text:p>" )%>
	</text:p>
<%}%>
<%if (service::getSrvValidationRule()!=null){%>
<%for (service::getSrvValidationRule()){%>
<text:h text:style-name="Heading_20_4" text:outline-level="4"><%getKey()%> :</text:h>
<text:p>
	<%getValue().processString().replaceAll("\\n","</text:p><text:p>" )%>
</text:p>
<%}%>
<%}%>


