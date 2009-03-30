<%
metamodel http://www.kerblue.org/class/1.0
%>

<%--
  -- This file is a patched copy of Alfresco's default upload webscript, but it is kept
  -- separate for ease of maintainability.
  --%>


<%script type="clazz.ClassPackage" name="getBlueXmlUploadDescOutputFile"%>
<%if (eContainer() == null) {%><%getTEMP_FOLDER()%>/<%getProperty("alf.paths.extension.webscripts")%>com/bluexml/upload/bluexml-upload.post.desc.xml<%}%>

<%script type="clazz.ClassPackage" name="blueXmlUploadDesc" file="<%getBlueXmlUploadDescOutputFile%>"%>
<webscript>
  <shortname>BlueXML File Upload</shortname>
  <description><![CDATA[
  Upload file content and meta-data into repository.
  
  <BR />
  HTML form data
  <ul>
     <li>filedata, (mandatory) HTML type file</li>
     <li>siteid</li>
     <li>containerid</li>
     <li>uploaddirectory</li>   
     <li>updatenoderef</li>
     <li>filename</li>    
     <li>description</li>
     <li>contenttype</li>
     <li>majorversion</li>
     <li>overwrite</li>
     <li>thumbnails</li>
  </ul> 
  
  <BR />
  Return content
  <ul>
  <li>nodeRef</li>
  </ul>

  <BR />
  Return status: STATUS_OK (200)

  ]]>
  </description>
  <url>/com/bluexml/api/upload</url>
  <format default="json"/>
  <authentication>user</authentication>
  <transaction>required</transaction>
  <lifecycle>public_api</lifecycle>
</webscript>