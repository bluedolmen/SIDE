<%--
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
metamodel http://www.kerblue.org/common/1.0/
import templates.servicesTemplates.Common
import templates.servicesTemplates.Attribute
import templates.servicesTemplates.Association
import templates.Model.alfrescoGenerator_model_abstractClass

import com.bluexml.side.clazz.service.alfresco.ClassServices
import com.bluexml.side.clazz.service.alfresco.CommonServices
import com.bluexml.side.clazz.service.alfresco.AttributeServices
import com.bluexml.side.clazz.service.alfresco.AssociationServices
%>

<%script type="CustomDataType" name="alfrescoGeneratorModelDataType" %>	
<data-type name="<%getPrefixedQName()%>">
   <default-analyser-class><%if (metainfo[key.equalsIgnoreCase("analyser")].nSize() > 0){%><%metainfo[key.equalsIgnoreCase("analyser")].nFirst().value%><%}else{%>org.alfresco.repo.search.impl.lucene.analysis.AlfrescoStandardAnalyser<%}%></default-analyser-class>
   <java-class><%dataTypeImp%></java-class>
</data-type>