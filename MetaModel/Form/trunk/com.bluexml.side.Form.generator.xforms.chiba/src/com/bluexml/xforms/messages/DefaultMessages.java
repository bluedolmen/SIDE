package com.bluexml.xforms.messages;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * This class is used to generate the content of the default properties files.
 * 
 * @author Amenel
 * 
 */
public class DefaultMessages {

	private static final String[] allFormsProperties = {
			"# address of the Alfresco server",
			"alfresco.url=http://localhost:8080/alfresco",
			"# CAS server URL",
			"cas.url=https://CASserver:8443/cas",
			"# hostname and port used by the XForms web application",
			"cas.serverName=127.0.0.1:8081",
			"# enable cas authentication",
			"cas.enabled=false",
			"# number of results displayed in selection lists of associations. 0 = unlimited (dangerous)",
			"selection.list.max.results=50",
			"",
			"# temporary folder (on the server's filesystem) for storing the files uploaded by users",
			"temp.directory=/tmp",
			"# archive folder (on the server's filesystem) for storing the files uploaded by users",
			"upload.directory=/tmp",
			"# number of levels between the archive folder and the actual folders where to-server uploads are stored. If set to 0, all uploads are stored in the archive folder.",
			"upload.directory.random.path.depth=3",
			"",
			"# path for uploads in the repository. If not given, \"/app:company_home\" is used.",
			"upload.repository=/app:company_home/app:user_homes",
			"# whether names of non-content files uploaded to the repository are appended with a '(x)' in case the initial name already exists.",
			"upload.repository.append.suffix=true", "", "# authentication", "user.name=admin",
			"user.pswd=admin" };

	private static final String[] allMessagesProperties = {
			"#",
			"# THESE ENTRIES ARE USED AT GENERATION TIME ONLY. Any modifications after a",
			"# generation will NOT have any effect at runtime. To make the modifications",
			"# effective, a new generation is REQUIRED: these are essentially static entries.",
			"# See the documentation for instructions on making the entries dynamic.",
			"#",
			"# Runtime entries can be found further in the file.",
			"#",
			"# 0: label/title of the field",
			"association.is.mandatory=Association \"{0}\" is mandatory.",
			"",
			"caption.button.cancel=Cancel",
			"caption.button.create=Create",
			"caption.button.delete=Delete",
			"caption.button.edit=Edit",
			"caption.button.settype=Submit",
			"caption.button.submit=Submit",
			"",
			"default.error.msg=An unexpected error occurred. Please report it to the administrator.",
			"",
			"# 0: label/title of the field",
			"field.is.mandatory=Field \"{0}\" is mandatory.",
			"",
			"file.field.label=Choose a file:",
			"file.field.filename.temporary=File uploaded to temporary location: ",
			"",
			"# 0: list of labels for mandatory fields that are not filled at submission time",
			"fill.mandatory.fields=Please fill mandatory fields ({0}).",
			"",
			"# 0: 4-digit year, 1: two-digit year, {2}: two-digit month, {3}: two-digit day",
			"format.date.output={0}-{2}-{3}",
			"",
			"# 0: hour (00 - 23), 1: minutes, 2: seconds, 3: milliseconds",
			"format.time.output={0}:{1}:{2}",
			"",
			"# 0: label of the field (the selection list) that implements the association on the current form",
			"integrity.violation=Integrity constraint violation on \"{0}\"",
			"#integrity.violation=the item chosen in field \"{0}\" is already used. Please select another item.",
			"",
			"# 0: label of the field",
			"invalid.regex.format=Invalid content for this field",
			"",
			"key.not.found=Message key not found",
			"# 0: min length allowed; 1: max length allowed; 2: label of the field",
			"length.between.min.and.max=The text length must be between {0} and {1} for the field \"{2}\"",
			"",
			"# 0: max length allowed; 1: label of the field",
			"length.maximal=Maximal length allowed is {0} for the field \"{1}\"",
			"",
			"# 0: min length allowed; 1: label of the field",
			"length.minimal=Minimal length allowed is {0} for the field \"{1}\"",
			"",
			"selection.list.label=Select",
			"selection.list.show.all.results=Show all",
			"selection.list.search.label=Search items:",
			"upload.node.content.field.label=Choose a file:",
			"upload.node.content.group.label=Attach content",
			"upload.node.content.no.content=(no content)",
			"# 0: node name; 1: content size in bytes with digit grouping; 2: content size in best-fitting unit",
			"upload.node.content.repository.format={0}: {1} bytes({2})",
			"upload.node.content.repository.info=Content information:", "#", "# workflow-related",
			"#", "caption.button.workflow.cancel=Cancel",
			"caption.button.workflow.select=Select this workflow",
			"caption.button.workflow.start=Activate this workflow",
			"workflow.error.submit.data=Please submit the data form before starting a workflow.",
			"workflow.error.start.failure=Failed in starting the workflow.",
			"workflow.instance.widget.title=Instances",
			"workflow.process.widget.title=Process definitions",
			"workflow.section.label=Workflow Task",
			"workflow.selection.page.title=Select a workflow",
			"workflow.selection.process.group=Process definitions",
			"workflow.selection.global.group=Workflows",
			"workflow.selection.instance.group=In-progress instances", "#",
			"# ALL ENTRIES BELOW ARE RUNTIME ENTRIES: THEY CAN BE MODIFIED.",
			"# Use \"reloadProperties=true\" as url parameter to make modifications",
			"# effective in the webapp.", "#", "session.timed.out=Your session has timed out.",
			"status.message.create.failure=Error while saving the data.",
			"status.message.create.success=Data successfully saved.",
			"status.message.delete.failure=Error while deleting.",
			"status.message.delete.success=Data successfully deleted.",
			"status.message.edit.failure=Error while editing the data.",
			"status.message.edit.success=Data successfully edited.",
			"# status.message.empty should have at least one whitespace",
			"status.message.empty=\\ ", "# 0: sequence number of the iteration",
			"status.message.iteration.postfix=\\ ({0})", "upload.random.path.depth=3",
			"upload.to.repository.failure=Upload to the repository failed.", "" };

	/**
	 * @param args
	 * @throws IOException
	 */
	public static boolean generateMessages(String filePath) throws IOException {
		return generate(filePath, allMessagesProperties);
	}

	/**
	 * @param args
	 * @throws IOException
	 */
	public static boolean generateForms(String filePath) throws IOException {
		return generate(filePath, allFormsProperties);
	}

	/**
	 * 
	 * @param filePath
	 * @param allLines
	 * @return
	 * @throws IOException
	 */
	public static boolean generate(String filePath, String[] allLines) throws IOException {
		String line;
		FileOutputStream fos = null;
		try {
			int pos = filePath.lastIndexOf(File.separator);
			String dirPath = filePath.substring(0, pos);
			File dir = new File(dirPath);
			dir.mkdirs();
			fos = new FileOutputStream(filePath);
			for (String property : allLines) {
				line = property + "\n";
				fos.write(line.getBytes());
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			if (fos != null) {
				fos.close();
			}
		}
		return true;
	}

}
