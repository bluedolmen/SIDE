<%--
    Copyright (C) 2007-20013  BlueXML - www.bluexml.com

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU Affero General Public License as
    published by the Free Software Foundation, either version 3 of the
    License, or (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU Affero General Public License for more details.

    You should have received a copy of the GNU Affero General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
--%>
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
