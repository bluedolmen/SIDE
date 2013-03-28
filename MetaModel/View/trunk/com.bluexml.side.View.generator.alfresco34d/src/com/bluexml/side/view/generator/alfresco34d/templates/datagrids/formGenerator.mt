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
metamodel http://www.kerblue.org/view/1.0
import com.bluexml.side.clazz.service.alfresco.CommonServices
import com.bluexml.side.clazz.service.alfresco.AssociationServices
import com.bluexml.side.clazz.service.alfresco.AttributeServices
import com.bluexml.side.view.generator.alfresco34d.ViewGenerator
%>

  
<%script type="view.ViewCollection" name="fileName"%>
<%if (eContainer() == null) {%><%getProperty("alf.share.paths.web-ext")%>/<%getModuleIdService(getRootPackage().name)%>/share-datagrid-config.xml<%}%>

<%script type="view.ViewCollection" name="generate" file="<%fileName()%>" %>
<alfresco-config>
<%for (getAllViews().filter("view.ComposedView")){%>
<!-- SIDE <%name%> View -->
<%for (children.filter("view.AbstractViewOf")[viewOf.getInheritedClasses()[getPrefixedQName() == "dl:dataListItem"].nSize() > 0]){%>
	<config evaluator="model-type" condition="<%viewOf.getPrefixedQName()%>">
		<%generate_fromClass()%>
	</config>
<%}%>
<%}%>
</alfresco-config>

<%script type="view.AbstractViewOf" name="generate_fromClass"%>
<forms>
	<form id="<%current("view.ComposedView").name%>">
		<field-visibility>
			<%generate_visibilityForClass()%>
		</field-visibility>
	</form>
</forms>
<%script type="view.AbstractViewOf" name="generate_visibilityForClass"%>
<%for (getFields()){%>
<%if (mapTo.filter("clazz.Attribute")){%>
<show id="<%mapTo.getPrefixedQName()%>" force="true" />
<%}%>
<%}%>
