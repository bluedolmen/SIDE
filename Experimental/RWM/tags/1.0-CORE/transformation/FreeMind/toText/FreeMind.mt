<%
metamodel http://freemind.sourceforge.net/
%>
<%script type="Freemind.Map" name="Map" file="<%node.text%>.mm"%>
<?xml version="1.0" encoding="ISO-8859-1"?>
<map version="<%version%>">
  <%node.PrintNode%>
</map>
<%script type="Freemind.Node" name="PrintNode"%>
<node text="<%text%>" folded="<%folded%>" <%if (id != null) {%>id="<%id%>"<%}%>>
  <%for (node){%>
    <%PrintNode%>
  <%}%>
  <%for (font){%>
    <%PrintFont%>
  <%}%>
  <%for (arrowlink){%>
    <%PrintArrowLink%>
  <%}%>
</node>
<%script type="Freemind.Font" name="PrintFont"%>
<font bold="<%bold%>" italic="<%italic%>" size="<%size%>" name="<%name%>"/>
<%script type="Freemind.ArrowLink" name="PrintArrowLink"%>
<arrowlink 
  <%if (startarrow != null){%> startarrow="<%startarrow%>"<%}%>
  <%if (endarrow != null){%> endarrow="<%endarrow%>"<%}%>
  <%if (startinclination != null){%> startinclination="<%startinclination%>"<%}%>
  <%if (endinclination != null){%> endinclination="<%endinclination%>"<%}%>
  <%if (destination != null){%> destination="<%destination%>"<%}%>
  <%if (color != null){%> color="<%color%>"<%}%>
/>