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
<%script type="clazz.Aspect" name="validatedFilename"%>
<%getProperty("javaEmbeddedAPIPath")%>/<%getProperty("moduleWebscript")%>/<%service::getRootContainer().name%>/<%eContainer().getQualifiedName().replaceAll("_","/")%>/model-<%name%>-crud.get.js
<%script type="clazz.Aspect" name="alfrescoGenerator" file="<%validatedFilename%>"%>
script:
{
	var obj = "";
	if (args["action"]) {
		var action =args["action"];		 
		switch(action) {
			case "add":
				obj = side<%getJavaAPIName()%>.addAspectTo(args["nodeRef"], <%getJavaPropertiesMethodCallWebscriptArgs()%>);
				break;
			case "update":
				obj = side<%getJavaAPIName()%>.update(args["nodeRef"], <%getJavaPropertiesMethodCallWebscriptArgs()%>);
				break;
			case "remove":
				side<%getJavaAPIName()%>.removeAspect(args["nodeRef"]);
				obj="{\"removedFrom\":\""+args["nodeRef"]+"\"}";
				break;
			default:
				status.code = 500;
		        status.message = "action :" + action + "not available please use one of {create, request, update, delete}";
		        status.redirect = true;
		        break script;
		}
	}
	model.obj = obj.toString();
	
}

