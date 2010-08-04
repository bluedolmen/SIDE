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