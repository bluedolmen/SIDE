<%
metamodel http://www.kerblue.org/portal/1.0
import com.bluexml.side.portal.generator.alfresco.templates.services.ClazzService
import com.bluexml.side.clazz.service.alfresco.CommonServices
%>

<%script type="Portal" name="addPages" %>
   <config evaluator="string-compare" condition="SitePages" replace="true">
		<pages>
		   <page id="calendar">calendar</page>
		   <page id="wiki-page">wiki-page?title=Main_Page</page>
		   <page id="documentlibrary">documentlibrary</page>
		   <page id="discussions-topiclist">discussions-topiclist</page>
		   <page id="blog-postlist">blog-postlist</page>
		   <%for (pageSet){%>
		   <page id="<%ID.toLowerCase()%>"><%ID.toLowerCase()%></page>
		   <%}%>
		</pages>
	</config>
	