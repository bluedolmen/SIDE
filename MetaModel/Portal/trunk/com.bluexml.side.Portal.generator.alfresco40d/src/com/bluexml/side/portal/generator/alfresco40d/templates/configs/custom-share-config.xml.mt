<%
metamodel http://www.kerblue.org/portal/1.0
import com.bluexml.side.portal.generator.alfresco.templates.services.ClazzService
import com.bluexml.side.clazz.service.alfresco.CommonServices
import com.bluexml.side.portal.generator.alfresco40d.templates.configs.custom-share-config-lib
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

<%for (pageSet[ID.toLowerCase().trim() == "documentlibrary"]){%>
<%generateDocumentLibraryConfiguration()%>
<%}%>

</alfresco-config>
