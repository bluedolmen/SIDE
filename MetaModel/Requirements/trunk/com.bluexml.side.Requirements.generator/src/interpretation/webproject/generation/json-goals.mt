<%
metamodel http://www.bluexml.com/rwm/webproject/1.0/
%>
<%script type="WebProject.GoalPage" name="goalPage" file="webtool/data/prototype/<%id%>.json"%>
[{
	text: 'Goals',
	expanded: true,
	children: [
		<%for (items){%>
			<%goalItem%><%if (current() != current("GoalPage").items.nLast()){%>,<%}%>
		<%}%>
	]
}]
<%script type="WebProject.GoalItem" name="goalItem"%>
{
	text:'<%label%>',
	id:'<%page.id%>',
	goTo:'<%page.name%>',
	<%if (sub.nSize() == 0){%>leaf:true
	<%}else{%>children: [
		<%for (sub){%>
		<%goalItem%><%if (current() != current("GoalItem").sub.nLast()){%>,<%}%>
		<%}%>
	]<%}%>
}