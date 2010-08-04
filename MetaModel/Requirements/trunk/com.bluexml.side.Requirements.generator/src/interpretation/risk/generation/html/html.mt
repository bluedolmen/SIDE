<%
metamodel http://www.bluexml.com/rwm/risk/1.0/
import com.bluexml.side.requirements.generator.services.StringEscapeUtilsService
%>
<%script type="Risk.Diagnostic" name="Diagnostic" file="webtool/data/analysis/diagnostic.json"%>
{
	"diagnostic":[
      <%for (estimation){%>
      	{
      		"type":"<%elementType%>",
      		"name":"<%if (elementName != null) {%><%escapeHtml(elementName.replaceAll("\n"," "))%><%}%>",
      		"value":"<%value%>"
        }<%if (current() != current("Diagnostic").estimation.nLast()){%>,<%}%>
      <%}%>
	]
}