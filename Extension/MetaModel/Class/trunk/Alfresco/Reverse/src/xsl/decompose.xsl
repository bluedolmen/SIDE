<?xml version="1.0" encoding="UTF-8"?>
<!--
	This file is part of SIDE-Labs/Reverse.

	Copyright (C) 2009  BlueXML (http://www.bluexml.com)

    SIDE-Labs/Reverse is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    SIDE-Labs/Reverse is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with Foobar.  If not, see <http://www.gnu.org/licenses/>.
-->
<xsl:stylesheet version="1.1" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" 
                xmlns:clazz="http://www.kerblue.org/class/1.0">

	<xsl:output method="xml" encoding="UTF-8" indent="yes" />

	<xsl:template match="/">
		<xsl:for-each select="//clazz:Model[@name!='models']">
		<xsl:variable name="file_name" select="concat(@name,'.dt')"/>
			<xsl:result-document href="{$file_name}" method="xml">
				<xsl:copy-of select="."/>
			</xsl:result-document>
		</xsl:for-each>
	</xsl:template>

</xsl:stylesheet>