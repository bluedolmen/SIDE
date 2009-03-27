<%--encoding=iso-8859-1--%>
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
metamodel http://www.kerblue.org/workflow/1.0
%>
<%script type="workflow.Process" name="validatedFilename"%>
shared/classes/alfresco/extension/generated/bpm/workflow-<%name%>-messages.properties
<%script type="workflow.Process" name="alfrescoGenerator" file="<%validatedFilename%>"%>
#Messages for workflow named <%name%>
wfbx_<%name%>.workflow.title=<%name%>

<%for (startstate){%>
#Messages for start task named <%name%>
wfbx_workflowmodel.type.wfbx_<%name%>.title=<%name%>
wfbx_<%current("Process").name%>.node.<%name%>.title=<%name%>
## Transitions
<%for (transition){%>
wfbx_<%current("Process").name%>.node.<%current("StartState").name%>.transition.<%name%>.title=<%title%>
<%}%>
## Attributes
<%for (attributes){%>
wfbx_workflowmodel.property.wfbx_<%name%>.title=<%title%>
<%}%>
<%}%>

<%for (endstate){%>
#Messages for end task named <%name%>
wfbx_workflowmodel.type.wfbx_<%name%>.title=<%name%>
wfbx_<%current("Process").name%>.node.<%name%>.title=<%name%>
<%}%>

<%for (tasknode){%>
#Messages for task named <%name%>
wfbx_workflowmodel.type.wfbx_<%name%>.title=<%name%>
wfbx_<%current("Process").name%>.node.<%name%>.title=<%name%>
## Transitions
<%for (transition){%>
wfbx_<%current("Process").name%>.node.<%current("TaskNode").name%>.transition.<%name%>.title=<%title%>
<%}%>
## Attributes
<%for (attributes){%>
wfbx_workflowmodel.property.wfbx_<%name%>.title=<%title%>
<%}%>
<%}%>