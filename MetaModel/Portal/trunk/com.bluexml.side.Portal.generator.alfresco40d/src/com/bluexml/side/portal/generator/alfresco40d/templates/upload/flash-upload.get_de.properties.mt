<%
metamodel http://www.kerblue.org/portal/1.0
import com.bluexml.side.portal.generator.alfresco.templates.services.ClazzService
import com.bluexml.side.clazz.service.alfresco.CommonServices
import com.bluexml.side.portal.generator.alfresco40d.templates.upload.upload-config-lib
%>
<%script type="Portal" name="validatedFilename" post="trim()" %>
<%getProperty("alf.share.paths.web-ext.alf.components")%>/upload/flash-upload.get_de.properties
<%script type="Portal" name="generator" file="<%validatedFilename%>"%>
header.singleUpload=Datei hochladen
header.multiUpload=Dateien hochladen
header.singleUpdate=Datei aktualisieren

label.browse=Datei(en) zum Hochladen ausw\u00e4hlen
label.multiUploadTip=Mehrere Dateien \u00fcber CTRL oder SHIFT ausw\u00e4hlen
label.singleUpdateTip=Klicken Sie auf das Icon, um eine neue Version von {0} hoch zu laden
label.noFiles=Keine Dateien anzuzeigen, klicken Sie auf das Icon um die hochzuladenden Dateien auszuw\u00e4hlen
label.noFlash=Sie ben\u00f6tigen einen Flash Player in der Version 9.0.45 um die Funktion nutzen zu k\u00f6nnen. Die aktuellste Version des Flash Player k\u00f6nnen Sie herunterladen von \
  <a href="http://www.adobe.com/go/getflashplayer">Adobe Flash Player Download Center</a>.
label.success=Erfolg
label.failure=Misserfolg
label.failure.413=Fehler: Kontingent \u00fcberschritten
label.illegalChars=Fehler: Dateiname enth\u00e4lt nicht zul\u00e4ssige Zeichen
button.upload=Dateien hochladen
label.uploadStatus=Status: {0}/{1} hochgeladen ({2} fehlgeschlagen)
message.cancelStatus=Upload(s) abgebrochen; {0} Datei(en) hochgeladen
message.zeroByteFileSelected=Sie haben die Datei {0} ausgew\u00e4hlt. Die Datei ist 0 Byte gro\u00df und kann nicht hochgeladen werden.
message.flashError.title=Flash Kommunikationsfehler
message.flashError.message=Der Browser kann nicht mit dem Flash Uploader kommunizieren. Sie k\u00f6nnen weiterhin Dateien im Einzeldateien Modus hochladen oder die Seite neu aufrufen, um Flash neu zu starten.

section.version=Version Information
label.version=Diese Version:
label.minorVersion=kleine \u00c4nderungen
label.majorVersion=bedeutende \u00c4nderungen
label.minorVersion.more=kleine \u00c4nderungen ({0})
label.majorVersion.more=bedeutende \u00c4nderungen ({0})
label.comments=Kommentare

type.cm_content=Inhalt

<%i18nGenerator%>
