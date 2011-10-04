<%
metamodel http://www.kerblue.org/form/1.0
import com.bluexml.side.clazz.service.alfresco.CommonServices
import com.bluexml.side.form.generator.alfresco34d.FormGenerator
import com.bluexml.side.form.generator.alfresco34d.templates.formGenerator
import com.bluexml.side.form.generator.alfresco34d.templates.services.form
import com.bluexml.side.form.generator.alfresco34d.templates.messages
%>

  
<%script type="form.FormCollection" name="fileName"%>
<%if (eContainer() == null) {%><%getProperty("alf.share.paths.web-ext")%>/<%getModuleIdService(getRootPackage().name)%>/<%getRootPackage().name%>_fr.properties<%}%>

<%script type="form.FormCollection" name="generate" file="<%fileName()%>" %>
<%for (forms){%>
form.set.label.<%getPrefixedQualifiedName()%>.general=D\u00e9tails du workflow
<%}%>
<%generate_messages()%>