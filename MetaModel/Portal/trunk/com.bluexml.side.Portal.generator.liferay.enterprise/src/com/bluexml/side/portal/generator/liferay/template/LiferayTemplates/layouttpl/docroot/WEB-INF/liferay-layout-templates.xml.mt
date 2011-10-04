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
<%script type="portal.Portal" name="validatedFilename"%>
<%if (eContainer() == null) {%><%getProperty("liferayGenerationLayout")%>/WEB-INF/liferay-layout-templates.xml<%}%>
<%script type="portal.Portal" name="liferayGenerator" file="<%validatedFilename%>"%>
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE layout-templates PUBLIC "-//Liferay//DTD Layout Templates 5.1.0//EN" "http://www.liferay.com/dtd/liferay-layout-templates_5_1_0.dtd">

<layout-templates>
	<custom>
	<%for (layoutSet){%>
		<layout-template id="<%name%>" name="<%name%>">
			<template-path>/<%name%>.tpl</template-path>
			<wap-template-path>/<%name%>.wap.tpl</wap-template-path>
			<thumbnail-path>/<%name%>.png</thumbnail-path>
		</layout-template>
	<%}%>	
	</custom>
</layout-templates>