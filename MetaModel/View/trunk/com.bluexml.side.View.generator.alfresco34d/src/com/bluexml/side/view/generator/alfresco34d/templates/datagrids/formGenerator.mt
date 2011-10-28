<%
metamodel http://www.kerblue.org/view/1.0
import com.bluexml.side.clazz.service.alfresco.CommonServices
import com.bluexml.side.clazz.service.alfresco.AssociationServices
import com.bluexml.side.clazz.service.alfresco.AttributeServices
import com.bluexml.side.view.generator.alfresco34d.ViewGenerator
%>

  
<%script type="view.ViewCollection" name="fileName"%>
<%if (eContainer() == null) {%><%getProperty("alf.share.paths.web-ext")%>/<%getModuleIdService(getRootPackage().name)%>/share-datagrid-config.xml<%}%>

<%script type="view.ViewCollection" name="generate" file="<%fileName()%>" %>
<alfresco-config>
<%for (getAllViews().filter("view.ComposedView")){%>
<!-- SIDE <%name%> View -->
<%for (children.filter("view.AbstractViewOf")[viewOf.getInheritedClasses()[getPrefixedQName() == "dl:dataListItem"].nSize() > 0]){%>
	<config evaluator="model-type" condition="<%viewOf.getPrefixedQName()%>">
		<%generate_fromClass()%>
	</config>
<%}%>
<%}%>
</alfresco-config>

<%script type="view.AbstractViewOf" name="generate_fromClass"%>
<forms>
	<form id="<%current("view.ComposedView").name%>">
		<field-visibility>
			<%generate_visibilityForClass()%>
		</field-visibility>
	</form>
</forms>
<%script type="view.AbstractViewOf" name="generate_visibilityForClass"%>
<%for (getFields()){%>
<%if (mapTo.filter("clazz.Attribute")){%>
<show id="<%mapTo.getPrefixedQName()%>" force="true" />
<%}%>
<%}%>