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
metamodel http://www.kerblue.org/portal/1.0
import com.bluexml.side.portal.generator.alfresco.templates.services.ClazzService
import com.bluexml.side.clazz.service.alfresco.CommonServices
import com.bluexml.side.portal.generator.alfresco34d.templates.custom-share-config-lib
%>
<%script type="Portal" name="validatedFilename" post="trim()" %>
<%getProperty("alf.share.paths.web-ext")%>/share-config-custom.xml

<%script type="Portal" name="alfrescoGenerator" file="<%validatedFilename%>"%>
<alfresco-config>
<%for (pageSet[ID.toLowerCase().trim() == "advsearch"].portlets.associationPortlet[name.toLowerCase().trim() == "search" && isPortletInternal != null]){%>
	<config evaluator="string-compare" condition="AdvancedSearch"<%if (getFirstMetainfoValue("replace","false") == "true"){%> replace="true"<%}%>>
		<advanced-search>
			<!-- Forms for the advanced search type list -->
			<forms>
				<!--
					The 'form' config element contains the name of the model type of
					the form to display. The element supports the following optional
					attributes: id = form id, the id of "search" will be assumed if not
					set label = label text to display - defaults to model type if not
					set labelId = I18N message id of label text to display description
					= description text to display descriptionId = I18N message id of
					description text to display
				-->
	<%for (isPortletInternal.form.forms.filter("form.ClassReference").real_class){%>
				<form labelId="search.form.label.<%service::getPrefixedQName("_")%>" descriptionId="search.form.desc.<%service::getPrefixedQName("_")%>"><%service::getPrefixedQName()%></form>
	<%}%>
			</forms>
		</advanced-search>
	</config>
<%}%>

<%for (pageSet[ID.toLowerCase().trim() == "documentlibrary"].portlets.associationPortlet[name.toLowerCase().trim() == "subtypes" && isPortletInternal != null && isPortletInternal.form != null]){%>
	<%generateDocumentLibraryConfiguration(isPortletInternal.form.forms.filter("form.ClassReference").real_class)%>
<%}%>
<%for (pageSet[ID.toLowerCase().trim() == "documentlibrary"].portlets.associationPortlet[name.toLowerCase().trim() == "subtypes" && isPortletInternal != null && isPortletInternal.view != null]){%>
	<%generateDocumentLibraryConfiguration(isPortletInternal.view.getInnerView().filter("view.AbstractViewOf").viewOf)%>
<%}%>

</alfresco-config>
