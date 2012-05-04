<%
metamodel http://www.kerblue.org/portal/1.0
import com.bluexml.side.portal.generator.alfresco.templates.services.ClazzService
import com.bluexml.side.clazz.service.alfresco.CommonServices
%>
<%script type="Portal" name="validatedFilename" post="trim()" %>
<%getProperty("alf.share.paths.web-ext.alf.components")%>/upload/flash-upload.get_it.properties
<%script type="Portal" name="generator" file="<%validatedFilename%>"%>
header.singleUpload=Carica file
header.multiUpload=Carica pi\u00f9 file
header.singleUpdate=Aggiorna file

label.browse=Selezionare i file da caricare
label.multiUploadTip=Utilizzare CTRL o MAIUSC per selezionare pi\u00f9 file
label.singleUpdateTip=Scegliere l''icona per caricare una nuova versione di {0}
label.noFiles=Nessun file da visualizzare, scegliere l'icona per selezionare i file da caricare
label.noFlash=Per utilizzare questo componente, \u00e8 necessario Flash Player versione 9.0.45. \u00c8 possibile scaricare l'ultima versione di Flash Player da \
  <a href="http://www.adobe.com/go/getflashplayer">Adobe Flash Player Download Center</a>.
label.success=Operazione riuscita
label.failure=Operazione non riuscita
label.failure.413=Operazione non riuscita: quota superata
label.illegalChars=Operazione non riuscita: il nome file contiene caratteri non validi
button.upload=Carica pi\u00f9 file
label.uploadStatus=Stato: {0}/{1} caricati ({2} non riusciti)
message.cancelStatus=Caricamenti annullati; {0} file caricati
message.zeroByteFileSelected=\u00c8 stato selezionato il file {0}. Questo file non pu\u00f2 essere caricato perch\u00e9 ha dimensioni pari a 0 byte.
message.flashError.title=Errore di comunicazione con Flash
message.flashError.message=Il browser non \u00e8 in grado di comunicare con Flash Uploader. \u00c8 possibile continuare a caricare in modalit\u00e0 file singolo o aggiornare la pagina per riavviare Flash.

section.version=Informazioni sulla versione
label.version=Questa versione dispone di:
label.minorVersion=cambiamenti minori
label.majorVersion=cambiamenti maggiori
label.minorVersion.more=cambiamenti minori ({0})
label.majorVersion.more=cambiamenti maggiori ({0})
label.comments=Commenti

type.cm_content=Contenuto
<%for (pageSet[ID.toLowerCase().trim() == "documentlibrary"].portlets.associationPortlet[name.toLowerCase().trim() == "uploadabletypes" && isPortletInternal != null]){%>
<%for (isPortletInternal.form.forms){%>
type.<%filter("form.ClassReference").real_class.getPrefixedQName("_")%><%filter("form.ClassReference").real_class.getLabel()%>
<%}%>
<%}%>
