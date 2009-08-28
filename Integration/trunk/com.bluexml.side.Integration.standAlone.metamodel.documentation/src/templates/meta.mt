<%
metamodel http://www.eclipse.org/emf/2002/Ecore
import com.bluexml.side.util.generator.documentation.services.DocumentationServices
%>
<%script type="ecore.EPackage" name="validatedFilename"%>
<%if (eContainer() == null) {%><%getModelName()%>/doc/meta.xml<%}%>
<%script type="ecore.EPackage" name="meta" file="<%validatedFilename%>"%>
<?xml version="1.0" encoding="UTF-8"?>
<office:document-meta
	xmlns:office="urn:oasis:names:tc:opendocument:xmlns:office:1.0"
	xmlns:xlink="http://www.w3.org/1999/xlink"
	xmlns:dc="http://purl.org/dc/elements/1.1/"
	xmlns:meta="urn:oasis:names:tc:opendocument:xmlns:meta:1.0"
	xmlns:ooo="http://openoffice.org/2004/office" office:version="1.2">
	<office:meta>
		<meta:initial-creator>
			Documentation generator
		</meta:initial-creator>
	</office:meta>
</office:document-meta>