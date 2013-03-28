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
	
