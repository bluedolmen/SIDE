<%
metamodel http://www.bluexml.com/rwm/diagnostic/1.0/
import com.bluexml.side.requirements.generator.services.StringEscapeUtilsService
%>
<%script type="Diagnostic.Diagnostic" name="Problem" file="webtool/data/analysis/problem.json"%>
{
	"diagnostic":[
      <%for (problem){%>
      	{
      		"type":"<%elementType%>",
      		"name":"<%if (elementName != null) {%><%escapeHtml(elementName.replaceAll("\n"," "))%><%}%>",
      		"severity":"<%severity.toString()%>",
      		"description":"<%if (description != null) {%><%escapeHtml(description.replaceAll("\n"," "))%><%}%>"
        }<%if (current() != current("Diagnostic").problem.nLast()){%>,<%}%>
      <%}%>
	]
}
