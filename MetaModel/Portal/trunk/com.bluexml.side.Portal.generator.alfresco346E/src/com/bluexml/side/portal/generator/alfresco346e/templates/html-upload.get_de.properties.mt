<%
metamodel http://www.kerblue.org/portal/1.0
import com.bluexml.side.portal.generator.alfresco.templates.services.ClazzService
import com.bluexml.side.clazz.service.alfresco.CommonServices
%>
<%script type="Portal" name="validatedFilename" post="trim()" %>
<%getProperty("alf.share.paths.web-ext.alf.components")%>/upload/html-upload.get_de.properties
<%script type="Portal" name="generator" file="<%validatedFilename%>"%>
header.singleUpload=Datei hochladen
header.singleUpdate=Datei aktualisieren

label.singleUploadTip=Mit dem neuesten Flash Player k\u00f6nnen Sie mehrere Dateien parallel hoch laden. Laden Sie sie unter \
<a href="http://www.adobe.com/go/getflashplayer"> aus dem Adobe Flash Player Download Center herunger</a>.

label.singleUpdateTip=Eine neue Version von {0} hochladen
button.upload=Datei hochladen

section.file=Datei ausw\u00e4hlen
label.file=Datei

section.version=Version Information
label.version=Diese Version:
label.minorVersion=kleine \u00c4nderungen
label.majorVersion=bedeutende \u00c4nderungen
label.minorVersion.more=kleine \u00c4nderungen ({0})
label.majorVersion.more=bedeutende \u00c4nderungen ({0})
label.comments=Kommentare

message.uploading=Dokument wird hochgeladen...
message.success=Dokument erfolgreich hochgeladen
message.failure=Dokument konnte nicht hochgeladen werden
message.failure.413=Kontingent \u00fcberschritten
message.illegalCharacters=Dateinamen d\u00fcrfen keine Zeichen enthalten, die auf irgendeinem Betriebssystem unzul\u00e4ssig sind.

type.cm_content=Inhalt
<%for (pageSet[ID.toLowerCase().trim() == "documentlibrary"].portlets.associationPortlet[name.toLowerCase().trim() == "uploadabletypes" && isPortletInternal != null]){%>
<%for (isPortletInternal.form.forms){%>
type.<%filter("form.ClassReference").real_class.getPrefixedQName("_")%><%filter("form.ClassReference").real_class.getLabel()%>
<%}%>
<%}%>
