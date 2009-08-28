<%
metamodel http://www.eclipse.org/emf/2002/Ecore
import com.bluexml.side.util.generator.documentation.services.DocumentationServices
%>
<%script type="ecore.EPackage" name="validatedFilename"%>
<%if (eContainer() == null) {%><%getModelName()%>/doc/mimetype<%}%>
<%script type="ecore.EPackage" name="mimetype" file="<%validatedFilename%>"%>
application/vnd.oasis.opendocument.text