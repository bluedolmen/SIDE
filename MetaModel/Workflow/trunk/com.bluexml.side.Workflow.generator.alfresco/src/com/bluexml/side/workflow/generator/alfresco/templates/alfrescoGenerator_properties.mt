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
import com.bluexml.side.workflow.generator.alfresco.templates.servicesTemplates.Common
import com.bluexml.side.workflow.generator.alfresco.WorkflowGenerator
%>
<%script type="workflow.Process" name="validatedFilename"%>
<%getTEMP_FOLDER()%>/<%getConfModulePath()%>/bpm/messages.properties
<%script type="workflow.Process" name="alfrescoGenerator" file="<%validatedFilename%>"%>
#Messages for workflow called <%name%>
wfbx<%name%>_<%name%>.workflow.title=<%if (title != null && title.length()>0){%><%title%><%}else{%><%name%><%}%>
wfbx<%name%>_<%name%>.workflow.description=<%if (processDescription != null && processDescription.length()>0){%><%processDescription%><%}%>

<%for (startstate){%>
#Messages for start task called <%name%>
wfbx<%current("Process").name%>_<%current("Process").name%>.task.wfbx<%current("Process").name%>_<%name%>.title=<%if (title != null && title.length()>0){%><%title%><%}else{%><%name%><%}%>
wfbx<%current("Process").name%>_<%current("Process").name%>.task.wfbx<%current("Process").name%>_<%name%>.description=<%if (stateDescription != null && stateDescription.length()>0){%><%stateDescription%><%}%>
## Transitions
<%for (transition){%>
wfbx<%current("Process").name%>_<%current("Process").name%>.node.<%current("StartState").name%>.transition.<%name%>.title=<%if (title != null && title.length()>0){%><%title%><%}else{%><%name%><%}%>
<%}%>
## Attributes
<%for (attributes){%>
wfbx<%current("Process").name%>_<%current("Process").name%>.property.wfbx<%current("Process").name%>_<%name%>.title=<%if (title != null && title.length()>0){%><%title%><%}else{%><%name%><%}%>
<%}%>
<%}%>

<%for (endstate){%>
#Messages for end task called <%name%>
wfbx<%current("Process").name%>_<%current("Process").name%>.task.wfbx<%current("Process").name%>_<%name%>.title=<%if (title != null && title.length()>0){%><%title%><%}else{%><%name%><%}%>
wfbx<%current("Process").name%>_<%current("Process").name%>.task.wfbx<%current("Process").name%>_<%name%>.description=<%if (stateDescription != null && stateDescription.length()>0){%><%stateDescription%><%}%>
<%}%>

<%for (tasknode){%>
#Messages for task called <%name%>
wfbx<%current("Process").name%>_<%current("Process").name%>.task.wfbx<%current("Process").name%>_<%name%>.title=<%if (title != null && title.length()>0){%><%title%><%}else{%><%name%><%}%>
wfbx<%current("Process").name%>_<%current("Process").name%>.task.wfbx<%current("Process").name%>_<%name%>.description=<%if (stateDescription != null && stateDescription.length()>0){%><%stateDescription%><%}%>

## Transitions
<%for (transition){%>
wfbx<%current("Process").name%>_<%current("Process").name%>.node.<%current("TaskNode").name%>.transition.<%name%>.title=<%if (title != null && title.length()>0){%><%title%><%}else{%><%name%><%}%>
<%}%>
## Attributes
<%for (attributes){%>
wfbx<%current("Process").name%>_<%current("Process").name%>.property.wfbx<%current("Process").name%>_<%name%>.title=<%if (title != null && title.length()>0){%><%title%><%}else{%><%name%><%}%>
<%}%>
<%}%>