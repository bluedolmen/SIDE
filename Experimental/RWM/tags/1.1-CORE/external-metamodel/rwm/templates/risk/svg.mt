<%
metamodel http://rwm
%>
<%script type="rwm.RequirementsDefinition" name="main" file="out.svg"%>
<?xml version="1.0" encoding="ISO-8859-1" standalone="no"?>
<svg xmlns="http://www.w3.org/2000/svg" version="1.1" baseprofle="full" width="<%width%>px" height="<%height%>px">
<%for (goals){%>
  <%printGoal%>
<%}%>
</svg>
<%script type="rwm.Goal" name="printGoal"%>
<%for (subgoals){%>
<%prinLink(current(1))%>
<%}%>
<rect
	x="<%x%>"
	y="<%y%>"
	width="<%width/2%>px"
	height="<%height%>px"
	style="fill:rgb(<%(stereotypeInstanciation.properties[def.name.equalsIgnoreCase("initialValue")].nFirst().value.adapt("double")*255).adapt("int")%>,<%((1-stereotypeInstanciation.properties[def.name.equalsIgnoreCase("initialValue")].nFirst().value.adapt("double"))*255).adapt("int")%>,0);stroke-width:1;stroke:rgb(0,0,0);"
	/>
<rect
	x="<%x+width/2%>"
	y="<%y%>"
	width="<%width/2%>px"
	height="<%height%>px"
	style="fill:rgb(<%(stereotypeInstanciation.properties[def.name.equalsIgnoreCase("computedValue")].nFirst().value.adapt("double")*255).adapt("int")%>,<%((1-stereotypeInstanciation.properties[def.name.equalsIgnoreCase("computedValue")].nFirst().value.adapt("double"))*255).adapt("int")%>,0);stroke-width:1;stroke:rgb(0,0,0);"
	/>
<text
	style="-inkscape-font-specification:Bitstream Vera Sans;text-align=center;font-size=10px;text-anchor:middle;"
	x="<%x+width/2%>"
	y="<%y+height/2+5%>"
	>
	<%name%>
</text>
<%for (subgoals){%>
<%printGoal%>
<%}%>
<%script type="rwm.Goal" name="prinLink"%>
<path d="M <%args(0).x+args(0).width/2%>,<%args(0).y+args(0).height%> L <%x+width/2%>,<%y%>"
	style="fill:none;fill-rule:evenodd;stroke:#000000;stroke-width:1px;stroke-linecap:butt;stroke-linejoin:miter;stroke-opacity:1">
</path>