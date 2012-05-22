<%
metamodel http://www.kerblue.org/portal/1.0
import com.bluexml.side.portal.generator.alfresco.templates.services.ClazzService
import com.bluexml.side.clazz.service.alfresco.CommonServices
%>
<%script type="Portal" name="validatedFilename" post="trim()" %>
<%getProperty("alf.share.paths.web-ext.alf.components")%>/upload/html-upload.get.properties
<%script type="Portal" name="generator" file="<%validatedFilename%>"%>
header.singleUpload=Upload File
header.singleUpdate=Update File

label.singleUploadTip=With the latest Flash Player installed, you can upload multiple files. \
  Download it from the <a href="http://www.adobe.com/go/getflashplayer">Adobe Flash Player Download Center</a>.

label.singleUpdateTip=Upload a new version of {0}
button.upload=Upload File

section.file=Choose File
label.file=File

section.version=Version Information
label.version=This version has:
label.minorVersion=minor changes
label.majorVersion=major changes
label.minorVersion.more=minor changes ({0})
label.majorVersion.more=major changes ({0})
label.comments=Comments

message.uploading=Document is being uploaded...
message.success=Document successfully uploaded
message.failure=Document could not be uploaded
message.failure.413=Quota Exceeded
message.illegalCharacters=Filenames cannot contain characters that are illegal on any operation system.

type.cm_content=Content
<%for (pageSet[ID.toLowerCase().trim() == "documentlibrary"].portlets.associationPortlet[name.toLowerCase().trim() == "uploadabletypes" && isPortletInternal != null]){%>
<%for (isPortletInternal.form.forms){%>
type.<%filter("form.ClassReference").real_class.getPrefixedQName("_")%><%filter("form.ClassReference").real_class.getLabel()%>
<%}%>
<%}%>
