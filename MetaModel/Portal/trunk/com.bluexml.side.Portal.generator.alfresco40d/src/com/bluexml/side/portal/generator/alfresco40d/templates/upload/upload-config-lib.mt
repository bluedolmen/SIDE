<%
metamodel http://www.kerblue.org/portal/1.0
import com.bluexml.side.portal.generator.alfresco.templates.services.ClazzService
import com.bluexml.side.clazz.service.alfresco.CommonServices
import com.bluexml.side.clazz.service.alfresco.ClassServices
%>

<%script type="Portal" name="alfrescoGenerator"%>
<%for (pageSet[ID.toLowerCase().trim() == "documentlibrary"].portlets.associationPortlet[name.toLowerCase().trim() == "uploadabletypes" && isPortletInternal != null]){%>
<upload>
<%for (isPortletInternal.form.forms){%>
	<%if (!filter("form.ClassReference").real_class.isFolder()){%>
	<type qname="<%filter("form.ClassReference").real_class.getPrefixedQName()%>" labelId="type.<%filter("form.ClassReference").real_class.getPrefixedQName("_")%>" />
	<%}%>	
<%}%>
</upload>
<%}%>