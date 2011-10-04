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
import com.bluexml.side.portal.generator.liferay.services.PageService
import com.bluexml.side.portal.generator.liferay.LiferayGenerator
%>
<%script type="portal.Portal" name="validatedFilename"%>
<%if (eContainer() == null) {%><%getProperty("liferayGenerationLar")%>/manifest.xml<%}%>
<%script type="portal.Portal" name="liferayGenerator" file="<%validatedFilename%>"%>
<?xml version="1.0" encoding="UTF-8"?>
<root>
	<header build-number="5101" export-date="jeu., 02 oct. 2008 08:18:54 +0000" type="layout-set" group-id="10131" private-layout="true" theme-id="<%getTheme()%>" color-scheme-id="<%getColorSchemeId()%>"/>
	<layouts>	
		<%for (pageSet){%>
			<layout layout-id="<%getPageIndex()+1%>" path="/groups/10131/layouts/<%getPageIndex()+1%>/layout.xml"/>
		<%}%>
	</layouts>
	<roles/>
	<portlets>
		<%for (pageSet){%>
			<%for (portlets){%>
		<portlet portlet-id="<%associationPortlet.getLiferayPortletId(associationPage)%>" layout-id="<%current("portal.Page").getPageIndex()+1%>" path="/groups/10131/portlets/<%associationPortlet.getLiferayPortletId(associationPage)%>/portlet.xml"/>
			<%}%>
		<%}%>
	</portlets>
</root>