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
import com.bluexml.side.portal.generator.liferay.services.PortletService
%>
<%script type="portal.HavePortlet" name="validatedFilename"%>
<%getProperty("liferayGenerationLar")%>/groups/10131/portlets/<%associationPortlet.getLiferayPortletId(associationPage)%>/portlet.xml
<%script type="portal.HavePortlet" name="liferayGenerator" file="<%validatedFilename%>"%>
<?xml version="1.0" encoding="UTF-8"?>
<portlet portlet-id="<%associationPortlet.getLiferayPortletId(associationPage)%>" root-portlet-id="<%associationPortlet.getLiferayPortletType()%>">
	<portlet-preference path="/groups/10131/portlets/<%associationPortlet.getLiferayPortletId(associationPage)%>/preferences/layout/0/10696/portlet-preferences.xml"/>
	<permissions>
		<community-actions/>
		<guest-actions/>
	</permissions>
	<roles/>
</portlet>