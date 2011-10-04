<%
metamodel http://www.kerblue.org/class/1.0
import templates.servicesTemplates.Common
import com.bluexml.side.clazz.generator.alfresco.api.service.ValueGenerator
import com.bluexml.side.clazz.generator.alfresco.api.templates.javaAPI.embedded.javaAPI
import com.bluexml.side.clazz.generator.alfresco.api.templates.javaAPI.lib
import com.bluexml.side.clazz.service.alfresco.ClassServices
import com.bluexml.side.clazz.service.alfresco.CommonServices
import com.bluexml.side.clazz.service.alfresco.AttributeServices
import com.bluexml.side.clazz.service.alfresco.AssociationServices


%>
<%script type="clazz.Clazz" name="validatedFilename"%>
<%getProperty("javaEmbeddedAPIPath")%>/<%getProperty("moduleWebscript")%>/<%service::getRootContainer().name%>/<%eContainer().getQualifiedName().replaceAll("_","/")%>/model-<%name%>-crud.get.js
<%script type="clazz.Clazz" name="alfrescoGenerator" file="<%validatedFilename%>"%>
script:
{
	var obj = "{action :\"notDefined\"}";
	if (args["action"]) {		
		var action =args["action"];				 
		switch(action) {
			case "create":			
				obj = side<%getJavaAPIName()%>.create(null<%if (getJavaPropertiesMethodCallWebscriptArgs().nSize() > 0){%>, <%}%><%getJavaPropertiesMethodCallWebscriptArgs()%>);
				break;
			case "request":
				obj = side<%getJavaAPIName()%>.request<%getJavaModelObjectName()%>("AND"<%if (getJavaPropertiesMethodSearchCallWebscriptArgs().nSize() > 0){%>, <%}%><%getJavaPropertiesMethodSearchCallWebscriptArgs()%>);				
				break;
			case "update":
				obj = side<%getJavaAPIName()%>.update(args["nodeRef"]<%if (getJavaPropertiesMethodCallWebscriptArgs().nSize() > 0){%>, <%}%><%getJavaPropertiesMethodCallWebscriptArgs()%>);
				break;
			case "delete":
				side<%getJavaAPIName()%>.delete<%getJavaModelObjectName()%>(args["nodeRef"]);
				obj="{\"deleted\" :\""+args["nodeRef"]+"\"}";
				break;
			<%for (getAllSourceAssociationEnds()){%>
			case "createAssociation_<%eContainer().getAssociationQName(current("AssociationEnd"))%>":
  				side<%current("Clazz").getJavaAPIName()%>.createAssociation_<%eContainer().getAssociationQName(current("AssociationEnd"))%>(args["source"],args["target"]);
  				obj = "{\"action\" :\"ok\"}";
  				break;
  			case "removeAssociation_<%eContainer().getAssociationQName(current("AssociationEnd"))%>":
  				side<%current("Clazz").getJavaAPIName()%>.removeAssociation_<%eContainer().getAssociationQName(current("AssociationEnd"))%>(args["source"],args["target"]);
  				obj = "{\"action\" :\"ok\"}";
  				break;
  			case "getAssociatedTarget_<%eContainer().getAssociationQName(current("AssociationEnd"))%>":
  				obj = side<%current("Clazz").getJavaAPIName()%>.convertNodesToJson(side<%current("Clazz").getJavaAPIName()%>.getAssociatedTarget_<%eContainer().getAssociationQName(current("AssociationEnd"))%>(args["source"]));
  				break;
  			case "getAssociatedTarget_<%eContainer().getAssociationQName(current("AssociationEnd"))%>As<%getOpposite().linkedClass.name.toU1Case()%>":
  				obj = side<%current("Clazz").getJavaAPIName()%>.getAssociatedTarget_<%eContainer().getAssociationQName(current("AssociationEnd"))%>As<%getOpposite().linkedClass.name.toU1Case()%>(args["source"]);
  				break;
  				
  			<%}%>
			default:
				status.code = 500;
		        status.message = "action :" + action + "not available please use one of {create, request, update, delete}";
		        status.redirect = true;
		        break script;
		}
	}
	model.obj = obj.toString();
	
}

