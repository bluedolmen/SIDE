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
<%if (eContainer() == null) {%><%getProperty("liferayGenerationLayout")%>/WEB-INF/liferay-plugin-package.xml<%}%>
<%script type="portal.Portal" name="liferayGenerator" file="<%validatedFilename%>"%>
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE plugin-package PUBLIC "-//Liferay//DTD Plugin Package 5.1.0//EN" "http://www.liferay.com/dtd/liferay-plugin-package_5_1_0.dtd">

<plugin-package>
	<name>BxDS layouts Templates</name>
	<module-id>liferay/BxDS layouts-layouttpl/5.1.0.1/war</module-id>
	<types>
		<type>layout-template</type>
	</types>
	<short-description>
		This plugin is a BxDS layouts layout template.
	</short-description>
	<change-log></change-log>
	<page-url>http://www.liferay.com</page-url>
	<author>Liferay, Inc.</author>
	<licenses>
		<license osi-approved="true">MIT</license>
	</licenses>
	<liferay-versions>
		<liferay-version>5.1.0+</liferay-version>
	</liferay-versions>
</plugin-package>