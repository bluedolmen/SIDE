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
import templates.servicesTemplates.Common
import com.bluexml.side.clazz.service.alfresco.ClassServices
import com.bluexml.side.clazz.service.alfresco.CommonServices
import com.bluexml.side.clazz.service.alfresco.AttributeServices
import com.bluexml.side.clazz.service.alfresco.AssociationServices
import com.bluexml.side.clazz.generator.alfresco.api.service.ValueGenerator

%>

<%script type="ClassPackage" name="getAllSortedAbstractClasses"%>
<%getAllAbstractClasses().nSort("name")%>

<%script type="AbstractClass" name="getAllSortedAttibutes"%>
<%service::getAllSortedAttibutes().nSort("name")%>

<%script type="AbstractClass" name="getAllAbstractClassSortedAttibutes"%>
<%service::getAllAbstractClassSortedAttibutes().nSort("name")%>
<%script type="AbstractClass" name="getAllSearchableSortedAttibutes"%>
<%getSearchableAttibutes().nSort("name")%>

<%script type="AbstractClass" name="getSortedAttibutes"%>
<%attributes.nSort("name")%>

<%script type="Attribute" name="isMultivalued"%>
<%metainfo[key.equalsIgnoreCase("multiple")].nSize()>0%>
