<%
metamodel http://www.kerblue.org/form/1.0
import com.bluexml.side.portal.generator.alfresco.templates.services.ClazzService
import com.bluexml.side.clazz.service.alfresco.CommonServices
%>

<%script type="form.FormCollection" name="generateAvailableTypeHierachy2"%>
<config evaluator="string-compare" condition="DocumentLibrary">
	<types>
		<type name="cm:content">
		<%for (args(0).filter("clazz.Clazz")[generalizations.nSize() == 0 && !abstract]){%>
			<%-- class that inerite from cm:content by default --%>			
			<subtype name="<%getPrefixedQName()%>" />	
		<%}%>
		</type>
		
		<%for (args(0).filter("clazz.Clazz")[!abstract]){%>
		<type name="<%getPrefixedQName()%>">
			<%for (getAllSubTypes().filter("clazz.Clazz")[!abstract]){%>
			<subtype name="<%getPrefixedQName()%>" />
			<%}%>
		</type>
		<%}%>
	</types>
	
	<aspects>         
		<visible>
		<%for (args(0).filter("clazz.Aspect")){%>
			<aspect name="<%getPrefixedQName()%>" />
		<%}%>
		</visible>
	</aspects>
</config>

<%script type="form.FormCollection" name="generateAvailableTypeHierachy"%>
<config evaluator="string-compare" condition="DocumentLibrary">
	<types>
		<type name="cm:content">
		<%for (forms.filter("form.ClassReference").real_class.filter("clazz.Clazz")[generalizations.nSize() == 0 && !abstract]){%>
			<%-- class that inerite from cm:content by default --%>			
			<subtype name="<%getPrefixedQName()%>" />	
		<%}%>
		</type>
		
		<%for (forms.filter("form.ClassReference").real_class.filter("clazz.Clazz")[!abstract]){%>
		<type name="<%getPrefixedQName()%>">
			<%for (getAllSubTypes().filter("clazz.Clazz")[!abstract]){%>
			<subtype name="<%getPrefixedQName()%>" />
			<%}%>
		</type>
		<%}%>
	</types>
	
	<aspects>         
		<visible>
		<%for (forms.filter("form.ClassReference").real_class.filter("clazz.Aspect")){%>
			<aspect name="<%getPrefixedQName()%>" />
		<%}%>
		</visible>
	</aspects>
</config>