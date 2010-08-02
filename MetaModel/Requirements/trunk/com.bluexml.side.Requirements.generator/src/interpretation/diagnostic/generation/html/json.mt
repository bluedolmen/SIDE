<%
metamodel http://www.bluexml.com/rwm/diagnostic/1.0/
%>
<%script type="Diagnostic.Diagnostic" name="Problem" file="analysis/problem.json"%>
{
	"diagnostic":[
      <%for (problem){%>
      	{
      		"type":"<%elementType%>",
      		"name":"<%if (elementName != null) {%><%elementName.replaceAll("\n"," ")%><%}%>",
      		"severity":"<%severity.toString()%>",
      		"description":"<%if (description != null) {%><%description.replaceAll("\n"," ")%><%}%>"
        }<%if (current() != current("Diagnostic").problem.nLast()){%>,<%}%>
      <%}%>
	]
}
