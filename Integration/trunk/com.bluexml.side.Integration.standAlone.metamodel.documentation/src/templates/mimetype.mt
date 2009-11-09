<%
metamodel http://www.kerblue.org/common/1.0/
import com.bluexml.side.util.generator.documentation.services.DocumentationServices
%>
<%script type="ecore.EPackage" name="validatedFilename"%>
<%if (eContainer() == null) {%><%getModelName()%>/doc/mimetype<%}%>
<%script type="ecore.EPackage" name="mimetype" file="<%validatedFilename%>"%>
application/vnd.oasis.opendocument.text