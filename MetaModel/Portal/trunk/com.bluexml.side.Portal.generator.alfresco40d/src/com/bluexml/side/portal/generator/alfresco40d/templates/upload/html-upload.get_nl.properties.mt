<%
metamodel http://www.kerblue.org/portal/1.0
import com.bluexml.side.portal.generator.alfresco.templates.services.ClazzService
import com.bluexml.side.clazz.service.alfresco.CommonServices
%>
<%script type="Portal" name="validatedFilename" post="trim()" %>
<%getProperty("alf.share.paths.web-ext.alf.components")%>/upload/html-upload.get_nl.properties
<%script type="Portal" name="generator" file="<%validatedFilename%>"%>
header.singleUpload=Bestand uploaden
header.singleUpdate=Bestand bijwerken

label.singleUploadTip=Wanneer u de nieuwste Flash Player hebt ge\u00efnstalleerd, kunt u meerdere bestanden uploaden. \
  Download deze via het <a href="http://www.adobe.com/nl/getflashplayer">downloadcentrum van Adobe Flash Player</a>.

label.singleUpdateTip=Een nieuwe versie van {0} uploaden
button.upload=Bestand uploaden

section.file=Bestand kiezen
label.file=Bestand

section.version=Versie-informatie
label.version=Deze versie heeft:
label.minorVersion=kleine wijzigingen
label.majorVersion=grote wijzigingen
label.minorVersion.more=kleine wijzigingen ({0})
label.majorVersion.more=grote wijzigingen ({0})
label.comments=Opmerkingen

message.uploading=Document wordt ge\u00fcpload...
message.success=Document is ge\u00fcpload
message.failure=Kan document niet uploaden
message.failure.413=Quota overschreden
message.illegalCharacters=Bestandsnamen mogen geen tekens bevatten die op geen enkel besturingssysteem toegestaan zijn.

type.cm_content=Content
<%for (pageSet[ID.toLowerCase().trim() == "documentlibrary"].portlets.associationPortlet[name.toLowerCase().trim() == "uploadabletypes" && isPortletInternal != null]){%>
<%for (isPortletInternal.form.forms){%>
type.<%filter("form.ClassReference").real_class.getPrefixedQName("_")%><%filter("form.ClassReference").real_class.getLabel()%>
<%}%>
<%}%>
