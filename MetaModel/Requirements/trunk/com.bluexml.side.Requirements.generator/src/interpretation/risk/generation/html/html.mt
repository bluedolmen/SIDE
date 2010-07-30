<%
metamodel http://www.bluexml.com/rwm/risk/1.0/
%>
<%script type="Risk.Diagnostic" name="Diagnostic" file="analysis/diagnostic.json"%>
{
	"diagnostic":[
      <%for (estimation){%>
      	{
      		"type":"<%elementType%>",
      		"name":"<%if (elementName != null) {%><%elementName.replaceAll("\n"," ")%><%}%>",
      		"value":"<%value%>"
        }<%if (current() != current("Diagnostic").estimation.nLast()){%>,<%}%>
      <%}%>
	]
}