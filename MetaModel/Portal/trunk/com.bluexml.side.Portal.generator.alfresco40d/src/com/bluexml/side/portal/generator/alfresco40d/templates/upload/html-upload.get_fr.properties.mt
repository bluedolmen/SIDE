<%
metamodel http://www.kerblue.org/portal/1.0
import com.bluexml.side.portal.generator.alfresco.templates.services.ClazzService
import com.bluexml.side.clazz.service.alfresco.CommonServices
%>
<%script type="Portal" name="validatedFilename" post="trim()" %>
<%getProperty("alf.share.paths.web-ext.alf.components")%>/upload/html-upload.get_fr.properties
<%script type="Portal" name="generator" file="<%validatedFilename%>"%>
header.singleUpload=Ajouter un fichier
header.singleUpdate=Mettre le fichier \u00e0 jour

label.singleUploadTip=Gr\u00e2ce \u00e0 la derni\u00e8re version de Flash Player, vous pouvez ajouter plusieurs fichiers. T\u00e9l\u00e9chargez-la depuis le \
<a href="http://www.adobe.com/go/getflashplayer">Adobe Flash Player Download Center</a>.

label.singleUpdateTip=Ajouter une nouvelle version de {0}
button.upload=Ajouter un fichier

section.file=Choix de fichier
label.file=Fichier

section.version=Informations de version
label.version=Cette version a :
label.minorVersion=changements mineurs
label.majorVersion=changements majeurs
label.minorVersion.more=changements mineurs ({0})
label.majorVersion.more=changements majeurs ({0})
label.comments=Commentaires

message.uploading=Document en cours d'ajout...
message.success=Document ajout\u00e9 avec succ\u00e8s
message.failure=Le document ne peut pas \u00eatre ajout\u00e9
message.failure.413=Quota d\u00e9pass\u00e9
message.illegalCharacters=Les noms de fichier ne peuvent contenir aucun caract\u00e8re ill\u00e9gal sur un syst\u00e8me d'exploitation.

type.cm_content=Contenu
<%for (pageSet[ID.toLowerCase().trim() == "documentlibrary"].portlets.associationPortlet[name.toLowerCase().trim() == "uploadabletypes" && isPortletInternal != null]){%>
<%for (isPortletInternal.form.forms){%>
type.<%filter("form.ClassReference").real_class.getPrefixedQName("_")%><%filter("form.ClassReference").real_class.getLabel()%>
<%}%>
<%}%>
