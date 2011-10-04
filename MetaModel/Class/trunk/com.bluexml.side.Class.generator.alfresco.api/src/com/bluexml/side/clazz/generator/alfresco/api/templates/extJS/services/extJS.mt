<%
metamodel http://www.kerblue.org/class/1.0
import templates.servicesTemplates.Common
import templates.servicesTemplates.Attribute
import com.bluexml.side.clazz.service.alfresco.ClassServices
import com.bluexml.side.clazz.service.alfresco.CommonServices
import com.bluexml.side.clazz.service.alfresco.AttributeServices
import com.bluexml.side.clazz.service.alfresco.AssociationServices
import com.bluexml.side.clazz.generator.alfresco.api.service.ValueGenerator

%>
<%script type="clazz.Attribute" name="getExtJSEditor" post="trim()" %>
<%if (valueList != null){%>combo_enum_<%valueList.name%><%}else{%>
<%if (typ.toString().equalsIgnoreCase("String")){%>new Ext.form.TextField({allowBlank: <%isMandatory()%>})<%}else{%>
<%if (typ.toString().equalsIgnoreCase("Date")){%>new Ext.form.TextField({allowBlank: <%isMandatory()%>})<%}else{%>new Ext.form.NumberField({allowBlank: <%isMandatory()%>})<%}%>
<%}%>
<%}%>
<%script type="clazz.Attribute" name="getExtJSType" post="trim()" %>
<%if (typ.toString().equalsIgnoreCase("String")){%>String<%}else{%>
<%if (typ.toString().equalsIgnoreCase("Date")){%>String<%}else{%><%typ.toString()%><%}%>
<%}%>
