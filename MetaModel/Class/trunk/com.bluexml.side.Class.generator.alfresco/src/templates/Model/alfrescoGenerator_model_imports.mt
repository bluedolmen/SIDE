<%--
Copyright (C) BlueXML 2005-2008

This program is free software; you can redistribute it and/or modify
 it under the terms of the GNU General Public License as published by
 the Free Software Foundation; either version 2.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with this program; if not, write to the Free Software
Foundation, Inc., 59 Temple Place, Boston, MA 02111.
 --%>
<%
metamodel http://www.kerblue.org/class/1.0
import templates.servicesTemplates.Common
import templates.servicesTemplates.Attribute
import templates.servicesTemplates.Association
import templates.Model.alfrescoGenerator_model_aspects
import templates.Model.alfrescoGenerator_model_classes
import com.bluexml.side.clazz.generator.alfresco.ClassAlfrescoGenerator
import com.bluexml.side.clazz.service.alfresco.ClassServices
import com.bluexml.side.clazz.service.alfresco.CommonServices
import com.bluexml.side.clazz.service.alfresco.AttributeServices
import com.bluexml.side.clazz.service.alfresco.AssociationServices
%>



<%script type="clazz.Model" name="model_imports"%>
	<!-- Imports are required to allow references to definitions in other models -->
	<imports>
		<!-- Import Alfresco Definitions -->
		<import uri="http://www.alfresco.org/model/dictionary/1.0" prefix="d" />
		<import uri="http://www.alfresco.org/model/application/1.0" prefix="app"/>
		<import uri="http://www.alfresco.org/model/wcmmodel/1.0" prefix="wcm"/>
      	<import uri="http://www.alfresco.org/model/forum/1.0" prefix="fm"/>
      	<import uri="http://www.alfresco.org/model/bpm/1.0" prefix="bpm" />
      	<import uri="http://www.alfresco.org/model/content/1.0" prefix="cm" />
      	<!-- Import BlueXML Definitions -->
      	<import uri="http://www.bluexml.com/model/content/1.0" prefix="bxcm" />
      	<%for (getNSL()[!isNativeModel()]) {%>
      	<import uri="<%getNamespaceURI()%>" prefix="<%getPrefixe()%>" />
      	<%}%>
	</imports>