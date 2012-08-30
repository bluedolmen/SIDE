 						Alfresco Inline Edition Side module Notes
==============================================================================

Table of Contents:
------------------
- Introduction
- Usage
- Prerequisite
- Development Note
- Configuration
- Customization
- Pointers & License Notice


Introduction:
-------------
This module allows to add inline editing on MS Office 2000+ and Open Office 3.2+ documents through a java applet.
 
Usage:
------
The /side/editWordContentInline?webdavurl={webdavurl}&amp;mime={mime?}&amp;mode={mode?} webscript must be used to load the applet. 
Before calling this webscript, it is necessary to test the mimetype of the document and to call it if the mimetype is in the following set:
               xls: "application/vnd.ms-excel",
               ppt: "application/vnd.ms-powerpoint",
               doc: "application/msword",
               xlsx: "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet",
               pptx: "application/vnd.openxmlformats-officedocument.presentationml.presentation",
               docx: "application/vnd.openxmlformats-officedocument.wordprocessingml.document",
               ods:	 "application/vnd.oasis.opendocument.spreadsheet",
         	   odp:	 "application/vnd.oasis.opendocument.presentation",
        	   odt:	 "application/vnd.oasis.opendocument.text"


Usually, action are added on Share in action component (see code on customization)

Prerequisite:
-------------
The prerequisites to run the applet are given in the common BlueXML_Module_Edition_en_ligne_pre_requis.odt which is annex of Administration guide.

Installation:
-------------
If the module has been declared in your side application model as a custom module, No installation is required except the call of the webscript

Configuration:
--------------
The text of the applet page which is displayed to user may be changed by overriding in the side server config
the file webapps/alfresco/WEB-INF//classes/alfresco/webscripts/extension/com/bluexml/side/alfresco/editInline/editWordContentInline.get.html.ftl 

Note: on some specific deployed site, the ${url.context} variable is not correctly set up; you may have to replace by the direct domain value of

Customization:
---------------
- applet tracing:
  to debug and trace applet execution:
      as Administraor, Control panel -> Java -> Advanced -> debug & Java console parameters to set up
    or
      C:\Users\<yourusername>\AppData\LocalLow\Sun\Java\Deployment\deployment.properties
          add:
            deployment.console.startup.mode=SHOW
            deployment.trace.level=all
- Applet transfer and caching:
  The following applet parameters may be added to:
  . optimize transfer:
     - pack the jar : pack200 editContentInline.jar.pack.gz editContentInline.jar 
       -> you get a editContentInline.jar.pack.gz with a reduced size
     - put this jar under webapps/share/applet
     - add the paramarer jnlp.packEnable
           <param name="java_arguments" value="-Djnlp.packEnabled=true"/>
       With this parameter at true, the java plugin will first load the jar in pack mode, ie it will try on '<archive>.pack.gz' then on '<archive> if no file '<archive>.pack.gz' has been found on the server.
	   Attention: tomcat must sent in its response a content type application/x-gzip for this type of file;
	   you may have to add this mimetype in the mime mapping list of the web.xml tomcat file:
		    <mime-mapping>
		        <extension>gz</extension>
		        <mime-type>application/x-gzip</mime-type>
		    </mime-mapping>
	. caching:  the following parameters may be used to manage jar caching (see http://docs.oracle.com/javase/1.4.2/docs/guide/plugin/developer_guide/applet_caching.html)
			<param name="archive" value="editContentInline-1.0.3.jar">
			<param name="cache_archive" VALUE="b.jar, c.jar, d.jar">
			<param name="cache_version" VALUE="0.0.0.1, 0.0.2A.1, 0.3D.22.FFFE">
			<param name="cache_archive_ex" VALUE="applet.jar;preload, util.jar;preload;0.9.0.AC1, tools.jar;0.9.8.7F">

- Share integration as action
  You need to update the following file with the code:
  . webapps/alfresco/WEB-INF/classes/alfresco/templates/webscripts/org/alfresco/slingshot/documentlibrary/evaluator.lib.js	
   to set the Share type of document according to the type of documents on which inline editing is authorized
   if it is an action part of a new actions set of a new type of document
   (not necessary if only on standard document)
  . webapps/share/WEB-INF/classes/alfresco/web-extension/site-webscripts/org/alfresco/components/documentlibrary/documentlist.get.config.xml
    webapps/share/WEB-INF/classes/alfresco/web-extension/site-webscripts/org/alfresco/components/document-details/document-actions.get.config.xml
    to add the action in the document action set or in the new actions set.
  . webapps/share/components/documentlibrary/actions.js
    to add the code to call the applet.

Pointers & License notices :
----------------------------
For support, refer to: http://www.side-labs.org/forum
More information at http://www.side-labs.org and www.bluexml.com

Copyright (C) 2007-2009  BlueXML - www.bluexml.com

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.


