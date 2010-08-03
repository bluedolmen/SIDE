<%
metamodel http://www.bluexml.com/rwm/webproject/1.0/
%>
<%script type="WebProject.LoginPage" name="listOfAgents" file="webtool/data/prototype/agent.json"%>
{
	"agent":[
      <%for (links){%>
      	{
      		"name":"<%label%>",
      		"goTo":"<%page.id%>"
        }<%if (current() != current("LoginPage").links.nLast()){%>,<%}%>
      <%}%>
	]
}
