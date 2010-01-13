<%
metamodel http://www.kerblue.org/portal/1.0
import com.bluexml.side.portal.generator.alfresco.templates.services.ClazzService
import com.bluexml.side.clazz.service.alfresco.CommonServices
import com.bluexml.side.portal.generator.alfresco.templates.formsDetails.web-framework-config-custom
import com.bluexml.side.portal.generator.alfresco.PortalAlfrescoGenerator
%>

<%script type="Portal" name="getCustomWebFwkConfigOutputFile"%>
<%if (eContainer() == null) {%><%getProperty("alf.share.paths.web-ext")%>web-framework-config-custom.xml<%}%>
<%script type="Portal" name="alfrescoGenerator" file="<%getCustomWebFwkConfigOutputFile%>"%>
<alfresco-config>

   <!--plug-ins>
      <evaluators>
           <evaluator id="node-type" class="org.alfresco.web.config.NodeTypeEvaluator" />
           <evaluator id="aspect-name" class="org.alfresco.web.config.AspectEvaluator" />
      </evaluators>
   </plug-ins-->


<%if (getGeneratorOptionValue("com.bluexml.side.Portal.generator.alfresco.forms")){%>
<%buildForms()%>
<%}%>


</alfresco-config>
