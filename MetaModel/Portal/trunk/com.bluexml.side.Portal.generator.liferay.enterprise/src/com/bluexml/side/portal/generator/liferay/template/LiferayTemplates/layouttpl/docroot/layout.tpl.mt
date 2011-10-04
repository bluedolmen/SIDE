<%--
Copyright (C) BlueXML 2005-2008

This program is free software; you can redistribute it and/or modify
 it under the terms of the GNU General Public License as published by
 the Free Software Foundation; either version 2.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with this program; if not, write to the Free Software
Foundation, Inc., 59 Temple Place, Boston, MA 02111.
 --%>
<%
metamodel http://www.kerblue.org/portal/1.0
%>
<%script type="portal.PortalLayout" name="validatedFilename"%>
<%getProperty("liferayGenerationLayout")%>/<%name%>.tpl
<%script type="portal.PortalLayout" name="liferayGenerator" file="<%validatedFilename%>"%>
<%if (columnMode){%>
<div class="<%name%>" id="content-wrapper">
	<table class="lfr-grid" id="layout-grid">
	<tr>
		<%for (columns){%>
		<td class="lfr-column" id="<%name%>" valign="top" style="width:<%width%><%if ( unit.equalsIgnoreCase("percent")){%>em<%}%>;">
			$processor.processColumn("<%name%>")
		</td>
		<%}%>
	</tr>
	</table>
</div>
<%}else{%>
<div class="<%name%>" id="content-wrapper">
	<%for (columns){%>
		<div class="layout_area" id="<%name%>">
			$processor.processColumn("<%name%>")
		</div>
	<%}%>
</div>
<%}%>

