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
metamodel http://www.bluexml.com/rwm/mindmap/1.0/
%>
<%script type="MindMap.Map" name="Map" file="<%node.text%>.mm"%>
<?xml version="1.0" encoding="ISO-8859-1"?>
<map version="1.0">
  <%node.PrintNode%>
</map>
<%script type="MindMap.Node" name="PrintNode"%>
<node text="<%text%>" folded="<%if (presentation != null){%><%presentation.folded%><%}else{%>true<%}%>" <%if (id != null) {%>id="<%id%>"<%}%>>
  <%for (sub){%>
    <%PrintNode%>
  <%}%>
  <%for (presentation){%>
    <%PrintPresentation%>
  <%}%>
  <%for (arrowlink){%>
    <%PrintArrowLink%>
  <%}%>
</node>
<%script type="MindMap.NodePresentation" name="PrintPresentation"%>
<font bold="<%bold%>" italic="<%italic%>" size="<%size%>" name="<%name%>"/>
<%script type="MindMap.ArrowLink" name="PrintArrowLink"%>
<arrowlink 
  <%if (startarrow != null){%> startarrow="<%startarrow%>"<%}%>
  <%if (endarrow != null){%> endarrow="<%endarrow%>"<%}%>
  <%if (startinclination != null){%> startinclination="<%startinclination%>"<%}%>
  <%if (endinclination != null){%> endinclination="<%endinclination%>"<%}%>
  <%if (destination != null){%> destination="<%destination.id%>"<%}%>
  <%if (color != null){%> color="<%color%>"<%}%>
/>
