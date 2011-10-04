<%
metamodel http://www.kerblue.org/class/1.0
import templates.servicesTemplates.Common
import com.bluexml.side.clazz.service.alfresco.ClassServices
import com.bluexml.side.clazz.service.alfresco.CommonServices
import com.bluexml.side.clazz.service.alfresco.AttributeServices
import com.bluexml.side.clazz.service.alfresco.AssociationServices
import com.bluexml.side.clazz.generator.alfresco.api.templates.services.classes
import com.bluexml.side.clazz.generator.alfresco.api.service.ValueGenerator

%>


<%script type="Attribute" name="getSignature"%>
<%if (isMultivalued()){%>List<String><%}else{%>String<%}%>
<%script type="Attribute" name="getSignatureWebscript"%>
<%if (isMultivalued()){%>NativeArray<%}else{%>String<%}%>
<%script type="Attribute" name="getPropertyCall"%>
<%name%><%if (args(0) != null){%><%args(0)%><%}%>
<%script type="Attribute" name="getPropertyCallWebscript"%>
<%if (isMultivalued()){%>convertToList(<%getPropertyCall(args(0))%>)<%}else{%><%getPropertyCall(args(0))%><%}%>
<%script type="Attribute" name="getPropertyCallWebscriptArgs" post="trim()"%>
<%if (isMultivalued()){%>
eval(args["<%name%>"])
<%}else{%>
args["<%name%>"]
<%}%>

<%script type="AbstractClass" name="getJavaModelPackage"%>
<%getProperty("javaPackageModel")%>.<%service::getRootContainer().name%>
<%script type="AbstractClass" name="getJavaModelObjectPackage"%>
<%getJavaModelPackage()%>.<%eContainer().getQualifiedName().replaceAll("_","\\.")%>
<%script type="AbstractClass" name="getJavaAPIPackage"%>
<%getProperty("javaPackageAPI")%>.<%service::getRootContainer().name%>.<%eContainer().getQualifiedName().replaceAll("_","\\.")%>
<%script type="AbstractClass" name="getJavaTestPackage"%>
<%getProperty("javaPackageTest")%>.<%service::getRootContainer().name%>.test.<%eContainer().getQualifiedName().replaceAll("_",".")%>

<%script type="AbstractClass" name="getJavaModelObjectName"%>
<%name.toU1Case()%>
<%script type="AbstractClass" name="getJavaAPIName"%>
<%name.toU1Case()%>Factory
<%script type="AbstractClass" name="getJavaTestName"%>
<%getJavaAPIName()%>Test

<%script type="AbstractClass" name="getJavaModelObjectQualifiedName"%>
<%getJavaModelPackage()%>.<%getJavaModelObjectName()%>
<%script type="AbstractClass" name="getJavaAPIQualifiedName"%>
<%getJavaAPIPackage()%>.<%getJavaAPIName()%>
<%script type="AbstractClass" name="getJavaTestQualifiedName"%>
<%getJavaTestPackage()%>.<%getJavaTestName()%>

<%script type="AbstractClass" name="getJavaPropertiesMethodSignature"%>
<%for (getAllSortedAttibutes()){%><%getSignature()%> <%name%><%if (i() < current("AbstractClass").getAllSortedAttibutes().nSize() -1){%>, <%}%><%}%>
<%script type="AbstractClass" name="getJavaPropertiesMethodSignatureWebscript"%>
<%for (getAllSortedAttibutes()){%><%getSignatureWebscript()%> <%name%><%if (i() < current("AbstractClass").getAllSortedAttibutes().nSize() -1){%>, <%}%><%}%>
<%script type="AbstractClass" name="getJavaPropertiesMethodCall"%>
<%for (getAllSortedAttibutes()){%><%getPropertyCall(args(0))%><%if (i() < current("AbstractClass").getAllSortedAttibutes().nSize() -1){%>, <%}%><%}%>
<%script type="AbstractClass" name="getJavaPropertiesMethodCallWebscript"%>
<%for (getAllSortedAttibutes()){%><%getPropertyCallWebscript(args(0))%><%if (i() < current("AbstractClass").getAllSortedAttibutes().nSize() -1){%>, <%}%><%}%>
<%script type="AbstractClass" name="getJavaPropertiesMethodCallWebscriptArgs"%>
<%for (getAllSortedAttibutes()){%><%getPropertyCallWebscriptArgs()%><%if (i() < current("AbstractClass").getAllSortedAttibutes().nSize() -1){%>, <%}%><%}%>

<%script type="AbstractClass" name="getJavaPropertiesMethodSearchSignature"%>
<%for (getAllSearchableSortedAttibutes()){%><%getSignature()%> <%name%><%if (i() < current("AbstractClass").getSearchableAttibutes().nSize() -1){%>, <%}%><%}%>
<%script type="AbstractClass" name="getJavaPropertiesMethodSearchSignatureWebscript"%>
<%for (getAllSearchableSortedAttibutes()){%><%getSignatureWebscript()%> <%name%><%if (i() < current("AbstractClass").getSearchableAttibutes().nSize() -1){%>, <%}%><%}%>
<%script type="AbstractClass" name="getJavaPropertiesMethodSearchCall" %>
<%for (getAllSearchableSortedAttibutes()){%><%getPropertyCall(args(0))%><%if (i() < current("AbstractClass").getSearchableAttibutes().nSize() -1){%>, <%}%><%}%>
<%script type="AbstractClass" name="getJavaPropertiesMethodSearchCallWebscript" %>
<%for (getAllSearchableSortedAttibutes()){%><%getPropertyCallWebscript(args(0))%><%if (i() < current("AbstractClass").getSearchableAttibutes().nSize() -1){%>, <%}%><%}%>
<%script type="AbstractClass" name="getJavaPropertiesMethodSearchCallWebscriptArgs" %>
<%for (getAllSearchableSortedAttibutes()){%><%getPropertyCallWebscriptArgs()%><%if (i() < current("AbstractClass").getSearchableAttibutes().nSize() -1){%>, <%}%><%}%>

<%script type="AbstractClass" name="generatePropertiesStatement" %>
<%for (getAllSortedAttibutes()){%>
<%getSignature()%> <%name%><%args(0)%> = <%if (metainfo[key.equalsIgnoreCase("multiple")].nSize()>0){%>new ArrayList<String>();
<%name%><%args(0)%>.add("<%current("Attribute").getPropertyTestValue(args(0))%>");<%}else{%>"<%current("Attribute").getPropertyTestValue(args(0))%>";<%}%>
<%}%>

<%script type="Clazz" name="generateCreateStatement" post="trim()"%>
<%generatePropertiesStatement(args(1))%>
<%getJavaModelObjectName()%> <%args(0)%> = <%name.toLowerCase()%>Factory.create(null<%if (getAllSortedAttibutes().nSize() > 0){%>, <%}%><%getJavaPropertiesMethodCall(args(1))%>);
created.add(<%args(0)%>.getUuid());

<%script type="AbstractClass" name="generatePropertiesAssertion" %>
// assert for properties
<%for (getAllSortedAttibutes()){%>
<%generatePropertiesAssertion_(args(0),args(1))%>
<%}%>
<%script type="AbstractClass" name="generatePropertiesAssertionSearchable" %>
// assert for properties
<%for (getAllSearchableSortedAttibutes()){%>
<%generatePropertiesAssertion_(args(0),args(1))%>
<%}%>
<%script type="Attribute" name="generatePropertiesAssertion_" %>
<%if (metainfo[key.equalsIgnoreCase("multiple")].nSize()>0){%>
assertEquals(<%name%><%args(0)%>.size(), <%args(1)%>.get<%name.toU1Case()%>().size());		
for (int i = 0; i<<%name%><%args(0)%>.size(); i++) {
	<%getPropertyTestAssertion(name+args(0)+".get(i)",args(1)+".get"+name.toU1Case()+"().get(i)")%>
}
<%}else{%>
<%getPropertyTestAssertion(name+args(0),args(1)+".get"+name.toU1Case()+"()")%>
<%}%>
<%script type="EObject" name="getFistClass" %>
<%getRootContainer().filter("ClassPackage").getAllClasses().nFirst().filter("Clazz")%>

<%script type="AbstractClass" name="mustAddcmContent" post="trim()" %>
<%if (filter("Aspect") != null){%>
false
<%}else{%>
<%if (filter("Clazz").isChildOfAlfrescoClazz() != "true"){%>
true
<%}else{%>
false
<%}%>
<%}%>
