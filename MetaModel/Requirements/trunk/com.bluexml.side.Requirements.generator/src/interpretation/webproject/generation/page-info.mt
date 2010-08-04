<%
metamodel http://www.bluexml.com/rwm/webproject/1.0/
import com.bluexml.side.requirements.generator.services.StringEscapeUtilsService
%>
<%script type="WebProject.DataPage" name="dataPage-info" file="webtool/data/prototype/page/<%id%>.json"%>
{
	"info":[{
		"id":"<%id%>",
		"title":"<%escapeHtml(title)%>",
		"description":"<%escapeHtml(comment.nFirst()).replaceAll("&lt;br/&gt;","<br/>")%>",
		"synopsis":"<%escapeHtml(comment.nLast()).replaceAll("&lt;br/&gt;","<br/>")%>"
	}]
}
<%script type="WebProject.GoalPage" name="goalPage-info" file="webtool/data/prototype/page/<%id%>.json"%>
{
	"info":[{
		"id":"<%id%>",
		"title":"<%escapeHtml(title)%>",
		"description":"<%escapeHtml(comment.nFirst()).replaceAll("&lt;br/&gt;","<br/>")%>",
		"synopsis":"<%escapeHtml(comment.nLast()).replaceAll("&lt;br/&gt;","<br/>")%>"
	}]
}
<%script type="WebProject.LoginPage" name="goalPage-info" file="webtool/data/prototype/page/project.json"%>
{
	"info":[{
		"id":"<%id%>",
		"title":"<%escapeHtml(title)%>",
		"description":"<%escapeHtml(comment.nFirst()).replaceAll("&lt;br/&gt;","<br/>")%>",
		"synopsis":""
	}]
}