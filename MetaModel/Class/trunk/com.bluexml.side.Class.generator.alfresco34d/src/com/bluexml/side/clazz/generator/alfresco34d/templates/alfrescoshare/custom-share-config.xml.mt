<%
metamodel http://www.kerblue.org/class/1.0
import templates.servicesTemplates.Common
%>

<%script type="clazz.ClassPackage" name="getCustomWebFwkConfigOutputFile"%>
<%if (eContainer() == null) {%><%getProperty("alf.share.paths.web-ext")%>share-config-custom.xml<%}%>

<%script type="clazz.ClassPackage" name="customWebFrameworkConfig" file="<%getCustomWebFwkConfigOutputFile%>"%>
<alfresco-config>
	<config evaluator="string-compare" condition="AdvancedSearch">
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
	<%for (getAllClasses().nSort("name")){%>
				<form labelId="search.form.label.<%getPrefixedQualifiedName().replaceAll(":", "_")%>" descriptionId="search.form.desc.<%getPrefixedQualifiedName().replaceAll(":", "_")%>"><%getPrefixedQualifiedName()%>
				</form>
	<%}%>
			</forms>
		</advanced-search>
	</config>
	
	
	<config evaluator="string-compare" condition="DocumentLibrary">
		<types>
			<type name="cm:content">
			<%for (getAllClasses()[generalizations.nSize() == 0 && !abstract]){%>
			<%-- class that inerite from cm:content by default --%>			
				<subtype name="<%getPrefixedQualifiedName()%>" />	
			<%}%>
			</type>
			
			<%for (getAllClasses()[!abstract]){%>
			<type name="<%getPrefixedQualifiedName()%>">
				<%for (getAllSubTypes().filter("Clazz")[!abstract]){%>
				<subtype name="<%getPrefixedQualifiedName()%>" />
				<%}%>
			</type>
			<%}%>
		</types>
		
		<aspects>         
			<visible>
			<%for (getAllAspects()){%>
				<aspect name="<%getPrefixedQualifiedName()%>" />
			<%}%>
			</visible>
		</aspects>
	</config>
	
</alfresco-config>
