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
%>

<%-- CSS creation --%>
<%script type="Page" name="createPagesCssStyle"%>
<%ID.toLowerCase().nPut("css_name")%>
<%if (generate){%>
<%getProperty("alf.share.paths.core.css")%><%nGet("css_name")%>/<%nGet("css_name")%>.css
<%}%>

<%script type="Page" name="alfrescoGenerator" file="<%createPagesCssStyle%>"%>
<%ID.toLowerCase().nPut("css_name")%>
.yui-t1
{
   width: auto;
}
.yui-t1 #yui-main
{
   margin-left: -50em;
}
