<%
metamodel http://rwm
%>
<%script type="rwm.RequirementsDefinition" name="main" file="out.svg"%>
<?xml version="1.0" encoding="ISO-8859-1" standalone="no"?>
<svg xmlns="http://www.w3.org/2000/svg"
     xmlns:xlink="http://www.w3.org/1999/xlink"
     version="1.1" baseprofle="full" width="<%width%>px" height="<%height%>px">
<%for (members){%>
  <%printAgent%>
<%}%>
<%for (goals){%>
  <%printGoal%>
<%}%>
<%for (relations){%>
  <%printRelationShip%>
<%}%>
<%for (entities){%>
  <%printEntity%>
<%}%>
</svg>
<%script type="rwm.Goal" name="printGoal"%>
<%for (subgoals){%>
<%prinLink(current(1))%>
<%}%>
<%for (privilegeGroup){%>
<%printEntryPoint(current(1))%>
<%}%>
<rect
	x="<%x%>"
	y="<%y%>"
	width="<%width%>px"
	height="<%height%>px"
	style="fill:rgb(169,201,236);stroke-width:1;stroke:rgb(0,0,0);"
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
	style="fill:none;fill-rule:evenodd;stroke:rgb(75,189,14);stroke-width:3px;stroke-linecap:butt;stroke-linejoin:miter;stroke-opacity:1">
</path>
<%script type="rwm.PrivilegeGroup" name="printEntryPoint"%>
<path d="M <%args(0).x+args(0).width/2%>,<%args(0).y+args(0).height/2%> L <%entryPoint.x+entryPoint.width/2%>,<%entryPoint.y+entryPoint.height/2%>"
	style="fill:none;fill-rule:evenodd;stroke:rgb(34,52,76);stroke-width:3px;stroke-linecap:butt;stroke-linejoin:miter;stroke-opacity:1">
</path>
<%script type="rwm.Agent" name="printAgent"%>
<%for (isResponsible){%>
<%printResponsibility(current(1))%>
<%}%>
<image
     xlink:href="data:image/png;base64,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
"
     width="<%width-12%>"
     height="<%height%>"
     x="<%x+12%>"
     y="<%y%>" />
<text
	style="-inkscape-font-specification:Bitstream Vera Sans;font-size=10px;fill:#77af04;fill-opacity:1"
	x="<%x+10%>"
	y="<%y+height%>"
	transform="rotate(270,<%x+10%>,<%y+height%>)"
	>
	<%name%>
</text>
<%script type="rwm.Goal" name="printResponsibility"%>
<path d="M <%args(0).x+args(0).width/2%>,<%args(0).y+args(0).height/2%> L <%x+width/2%>,<%y+height/2%>"
	style="fill:none;fill-rule:evenodd;stroke:#FFBC00;stroke-width:1px;stroke-linecap:butt;stroke-linejoin:miter;stroke-opacity:1">
</path>
<%script type="rwm.Entity" name="printEntity"%>
<rect
	x="<%x%>"
	y="<%y%>"
	width="<%width%>px"
	height="<%height%>px"
	style="fill:rgb(230,230,230);stroke-width:1;stroke:rgb(0,0,0);"
	/>
<rect
	x="<%x+5%>"
	y="<%y+5%>"
	width="<%width-10%>px"
	height="<%25%>px"
	style="fill:rgb(130,130,130)"
	/>
<text
	style="-inkscape-font-specification:Bitstream Vera Sans;font-size=20px;font-weight:bold"
	x="<%x+10%>"
	y="<%y+20%>"
	>
	<%name%>
</text>
<%for (attributes){%>
	<%printAttribute(current(1).x+5,current(1).y+35+i()*(current(1).height-35)/current(1).attributes.nSize(),current(1).width-10,(current(1).height-35)/current(1).attributes.nSize()-2)%>
<%}%>
<%script type="rwm.Attribute" name="printAttribute"%>
<rect
	x="<%args(0)%>"
	y="<%args(1)%>"
	width="<%args(2)%>px"
	height="<%args(3)%>px"
	style="fill:rgb(180,180,180)"
	/>
<text
	style="-inkscape-font-specification:Bitstream Vera Sans;font-size=10px"
	x="<%args(0)+5%>"
	y="<%args(1)+10+(args(3)-10)/2%>"
	>
	<%name%> : <%type%>
</text>
<%script type="rwm.RelationShip" name="printRelationShip"%>
<path d="M <%ends.nFirst().object.x+ends.nFirst().object.width/2%>,<%ends.nFirst().object.y+ends.nFirst().object.height/2%> L <%ends.nLast().object.x+ends.nLast().object.width/2%>,<%ends.nLast().object.y+ends.nLast().object.height/2%>"
	style="fill:none;fill-rule:evenodd;stroke:rgb(211,100,211);stroke-width:3px;stroke-linecap:butt;stroke-linejoin:miter;stroke-opacity:1">
</path>