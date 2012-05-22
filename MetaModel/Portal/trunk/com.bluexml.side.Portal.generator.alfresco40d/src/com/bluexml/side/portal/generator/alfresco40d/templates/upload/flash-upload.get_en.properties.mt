<%
metamodel http://www.kerblue.org/portal/1.0
import com.bluexml.side.portal.generator.alfresco.templates.services.ClazzService
import com.bluexml.side.clazz.service.alfresco.CommonServices
%>
<%script type="Portal" name="validatedFilename" post="trim()" %>
<%getProperty("alf.share.paths.web-ext.alf.components")%>/upload/flash-upload.get_en.properties
<%script type="Portal" name="generator" file="<%validatedFilename%>"%>
header.singleUpload=Upload File
header.multiUpload=Upload File(s)
header.singleUpdate=Update File

label.browse=Select file(s) to upload
label.multiUploadTip=Use CTRL or SHIFT to select multiple files
label.singleUpdateTip=Click icon to upload a new version of {0}
label.noFiles=No files to display, click icon to select file(s) to upload
label.noFlash=You need a Flash Player of version 9.0.45 to use this component. You can download the latest version of Flash Player from the \
  <a href="http://www.adobe.com/go/getflashplayer">Adobe Flash Player Download Center</a>.
label.success=Success
label.failure=Failure
label.failure.413=Failure: Quota exceeded
label.illegalChars=Failure: Filename contains illegal characters
button.upload=Upload File(s)
label.uploadStatus=Status: {0}/{1} uploaded ({2} failed)
message.cancelStatus=Upload(s) cancelled; {0} file(s) uploaded
message.zeroByteFileSelected=You selected file {0}. It is 0 bytes in size and cannot be uploaded.
message.flashError.title=Flash Communication Error
message.flashError.message=The browser cannot communicate with the Flash Uploader. You can continue to upload in single-file mode, or refresh the page to restart Flash.

section.version=Version Information
label.version=This version has:
label.minorVersion=minor changes
label.majorVersion=major changes
label.minorVersion.more=minor changes ({0})
label.majorVersion.more=major changes ({0})
label.comments=Comments

type.cm_content=Content
<%for (pageSet[ID.toLowerCase().trim() == "documentlibrary"].portlets.associationPortlet[name.toLowerCase().trim() == "uploadabletypes" && isPortletInternal != null]){%>
<%for (isPortletInternal.form.forms){%>
type.<%filter("form.ClassReference").real_class.getPrefixedQName("_")%><%filter("form.ClassReference").real_class.getLabel()%>
<%}%>
<%}%>
