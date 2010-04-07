<%
metamodel http://www.kerblue.org/class/1.0
import templates.servicesTemplates.Common
import templates.servicesTemplates.Attribute
import templates.servicesTemplates.Association

%>

<%script type="clazz.ClassPackage" name="validatedFileName"%>
<%if (eContainer() == null) {%><%getConfModulePath()%>/synchronization-database-mapping.properties<%}%>

<%script type="clazz.ClassPackage" name="package" file="<%validatedFileName%>" %>
# ENUMERATIONS
<%for  (getAllEnumerations().nSort("name")){%><%if (dynamic){%>
class.name.<%getQualifiedName%>=<%name%>
class.attribute.name.<%getQualifiedName%>.<%getQualifiedName%>_code=code
class.attribute.name.<%getQualifiedName%>.<%getQualifiedName%>_label=label
<%}%><%}%> <%-- END for (enumerationSet) --%>

# ASPECTS
<%for (getAllAspects().nSort("name")) {%>
aspect.name.<%getQualifiedName%>=<%name%>
<%}%>

# CLASSES AND ATTRIBUTES
<%for (getAllClasses().nSort("name")){%>
class.name.<%getQualifiedName%>=<%getClassTableName()%>
<%for (getAllSortedAttibutes()){%>
class.attribute.name.<%current("Clazz").getQualifiedName%>.<%getQualifiedName()%>=<%if (eContainer() != current("Clazz")){%><%eContainer().getClassTableName()%>_<%}%><%name%>
<%}%>
class.attribute.name.<%current("Clazz").getQualifiedName%>.node-dbid=id
class.attribute.name.<%current("Clazz").getQualifiedName%>.node-uuid=uuid
<%}%>
# STATIC
class.name.Litteral=Litteral
class.attribute.name.Litteral.Litteral_code=code
class.name.EnumerationType=EnumerationType
class.attribute.name.EnumerationType.EnumerationType_name=name
class.name.LitteralTranslation=LitteralTranslation
class.attribute.name.LitteralTranslation.LitteralTranslation_lang=lang
class.attribute.name.LitteralTranslation.LitteralTranslation_value=value
class.name.VisibilityContext=VisibilityContext
class.attribute.name.VisibilityContext.VisibilityContext_value=value

# ASSOCIATIONS
<%for (getAllAssociations().nSort("name")){%>
#association <%firstEnd.linkedClass.name%>/<%name%>/<%secondEnd.linkedClass.name%>

<%if (secondEnd.navigable){%>
association.name.<%getQualifiedName(firstEnd)%>=<%getAssociationTableName(firstEnd)%>
association.source.<%getQualifiedName(firstEnd)%>=<%firstEnd.linkedClass.name%>
<%if (firstEnd.name != ""){%>
association.source.alias.<%getQualifiedName(firstEnd)%>=<%firstEnd.name%>
<%}%>
association.target.<%getQualifiedName(firstEnd)%>=<%secondEnd.linkedClass.name%>
<%if (secondEnd.name != ""){%>
association.target.alias.<%getQualifiedName(firstEnd)%>=<%secondEnd.name%>
<%}%>
<%}%>

<%if (firstEnd.navigable){%>
association.name.<%getQualifiedName(secondEnd)%>=<%getAssociationTableName(secondEnd)%>
association.source.<%getQualifiedName(secondEnd)%>=<%secondEnd.linkedClass.name%>
<%if (secondEnd.name != ""){%>
association.source.alias.<%getQualifiedName(secondEnd)%>=<%secondEnd.name%>
<%}%>
association.target.<%getQualifiedName(secondEnd)%>=<%firstEnd.linkedClass.name%>
<%if (firstEnd.name != ""){%>
association.target.alias.<%getQualifiedName(secondEnd)%>=<%firstEnd.name%>
<%}%>
<%}%>

<%}%>


<%script type="clazz.Enumeration" name="getEnumerationTableName" post="trim()"%>
<%name%>

<%script type="clazz.AbstractClass" name="getClassTableName" post="trim()"%>
<%if (tags[key == "table_name"]){%>
<%tags[key == "table_name"].nFirst().value%>
<%}else{%>
<%name%>
<%}%>
 
<%-- The first argument is the source association-end of the association--%>
<%script type="clazz.Association" name="getAssociationTableName" post="trim()"%>
<%if (tags[key == "table_name"]){%>
<%tags[key == "table_name"].nFirst().value%>
<%}else{%>
<%-- args(0) is the SOURCE association end --%>
<%args(0).linkedClass.name%>_<%name%><%if (args(0).getOpposite().name != ""){%>_<%args(0).getOpposite().name%><%}%>_<%args(0).getOpposite().linkedClass.name%>
<%}%>
