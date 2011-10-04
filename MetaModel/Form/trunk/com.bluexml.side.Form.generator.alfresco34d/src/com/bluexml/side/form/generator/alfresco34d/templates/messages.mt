<%
metamodel http://www.kerblue.org/form/1.0
import com.bluexml.side.clazz.service.alfresco.CommonServices
import com.bluexml.side.form.generator.alfresco34d.FormGenerator
import com.bluexml.side.form.generator.alfresco34d.templates.formGenerator
import com.bluexml.side.form.generator.alfresco34d.templates.services.form
%>

  
<%script type="form.FormCollection" name="fileName"%>
<%if (eContainer() == null) {%><%getProperty("alf.share.paths.web-ext")%>/<%getModuleIdService(getRootPackage().name)%>/<%getRootPackage().name%>.properties<%}%>

<%script type="form.FormCollection" name="generate" file="<%fileName()%>" %>
<%for (forms){%>
form.set.label.<%getPrefixedQualifiedName()%>.general=Details
<%}%>
<%generate_messages()%>
<%script type="form.FormCollection" name="generate_messages" %>
<%for (forms){%>
form.set.label.<%getPrefixedQualifiedName()%>=<%getLabelOrName()%>
<%for (getAllSubGroups()){%>
<%getGroupLabelId()%>=<%getLabelOrName()%>
<%}%>
<%for (getAllKindFields()){%>
<%getFieldLabelId("")%>=<%getLabelOrName()%>
<%if (help_text != null && help_text != ""){%>
<%getFieldLabelId("help.")%>=<%help_text%>
<%}%>
<%if (description != null && description != ""){%>
<%getFieldLabelId("description.")%>=<%description%>
<%}%>
<%}%>
<%}%>
<%for (forms.filter("FormSearch")){%>
search.form.label.<%real_class.getPrefixedQName("_")%>=<%getLabelOrName()%>
search.form.desc.<%real_class.getPrefixedQName("_")%>=<%if (description != null){%><%description%><%}%>
<%}%>