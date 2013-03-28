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
metamodel http://www.bluexml.com/rwm/webproject/1.0/
import com.bluexml.side.requirements.generator.services.StringEscapeUtilsService
%>
<%script type="WebProject.GoalPage" name="goalPage" file="webtool/data/prototype/<%id%>.json"%>
[{
	text:'Goals',
	expanded: true,
	children: [
		<%for (items){%>
			<%goalItem%><%if (current() != current("GoalPage").items.nLast()){%>,<%}%>
		<%}%>
	]
}]
<%script type="WebProject.GoalItem" name="goalItem"%>
{
	text:"<%escapeHtml(label)%>",
	id:'<%page.id%>',
	<%if (page.eClass().name == "DataPage") {%>	goTo:'<%page.name%>',
	<%}else{%>	goTo:'',
	<%}%>
	<%if (sub.nSize() == 0){%>	leaf:true
	<%}else{%>	children: [
		<%for (sub){%>
		<%goalItem%><%if (current() != current(1).sub.nLast()){%>,<%}%>
		<%}%>
	]<%}%>
}
