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