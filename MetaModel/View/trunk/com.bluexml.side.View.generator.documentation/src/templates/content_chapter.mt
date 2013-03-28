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
