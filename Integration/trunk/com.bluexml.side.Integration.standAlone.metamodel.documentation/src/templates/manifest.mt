<%
metamodel http://www.kerblue.org/common/1.0/
import com.bluexml.side.util.generator.documentation.services.DocumentationServices
%>
<%script type="ecore.EPackage" name="validatedFilename"%>
<%if (eContainer() == null) {%><%getModelName()%>/doc/META-INF/manifest.xml<%}%>
<%script type="ecore.EPackage" name="manifest" file="<%validatedFilename%>"%>
<?xml version="1.0" encoding="UTF-8"?>
<manifest:manifest xmlns:manifest="urn:oasis:names:tc:opendocument:xmlns:manifest:1.0">
 <manifest:file-entry manifest:media-type="application/vnd.oasis.opendocument.text" manifest:version="1.2" manifest:full-path="/"/>
 <manifest:file-entry manifest:media-type="text/xml" manifest:full-path="content.xml"/>
 <manifest:file-entry manifest:media-type="text/xml" manifest:full-path="styles.xml"/>
 <manifest:file-entry manifest:media-type="text/xml" manifest:full-path="meta.xml"/>
 <manifest:file-entry manifest:media-type="text/xml" manifest:full-path="settings.xml"/>
 <%for (getDiagImgPath) {%>
  <manifest:file-entry manifest:media-type="image/jpeg" manifest:full-path="Pictures/<%current%>"/>
 <%}%>
</manifest:manifest>