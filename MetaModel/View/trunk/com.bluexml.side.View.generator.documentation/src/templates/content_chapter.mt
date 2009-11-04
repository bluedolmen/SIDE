<%
metamodel http://www.kerblue.org/view/1.0
%>

<%script type="view.ViewCollection" name="content_chapter"%>
<%for (views){%>
<%viewsHeader()%>
<%}%>
<%for (composedViews){%>
<%viewsHeader()%>
<%}%>
<%script type="view.AbstractView" name="viewsHeader"%>
	<text:h text:style-name="Heading_20_1" text:outline-level="1"><%name%></text:h>
		<%viewFields(2)%>
		<%viewInnerView(2)%>
<%script type="view.AbstractView" name="viewFields"%>
<text:h text:style-name="Heading_20_<%args(0)%>" text:outline-level="<%args(0)%>"><%name%> Fields</text:h>
<%for (getFields()){%>
<text:h text:style-name="Heading_20_<%args(0)+1%>" text:outline-level="<%args(0)+1%>"><%name%> :</text:h>
<text:p>
	<%if (documentation != null){%><%documentation%><%}%>
</text:p>
<%}%>


<%script type="view.AbstractView" name="viewInnerView"%>
<%-- args0 : level --%>
<%if (getInnerView().length() > 0){%>
	<%for (getInnerView()){%>
		<text:h text:style-name="Heading_20_<%args(0)%>" text:outline-level="<%args(0)%>"><%name%> inner view</text:h>
		<%viewFields(args(0)+1)%>
		<%viewInnerView(args(0)+1)%>
	<%}%>
<%}%>