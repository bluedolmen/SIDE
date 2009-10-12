package com.bluexml.xforms.messages;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * This class // TODO
 * 
 * @author Amenel
 * 
 */
public class DefaultMessages {

	private static final String[] properties = { "association.mandatory.part1=Association \"",
			"association.mandatory.part2=\" is mandatory!", "caption.button.cancel=Cancel",
			"caption.button.create=Create", "caption.button.create.class=Create",
			"caption.button.create.form=Create", "caption.button.delete=Delete",
			"caption.button.edit=Modify", "caption.button.edit.class=Edit",
			"caption.button.edit.form=Edit form.", "caption.button.submit=Submit",
			"field.mandatory.part1=Field \"", "field.mandatory.part2=\" is mandatory !",
			"key.not.found=Message key not found", "# trailing space",
			"length.between.part1=Allowed length between", "# leading space and trailing space",
			"length.between.part2=\\ et ", "# trailing space", "length.maximal=Maximal length ",
			"# trailing space", "length.minimal=Minimal length ", "# leading space",
			"length.post.info.part1=\\ for field \"", "length.post.info.part2=\"",
			"workflow.section.label=Workflow Task", "# trailing space",
			"selection.list.label.mandatory=Select ", "selection.list.show.all.results=Show all",
			"session.timed.out=Your session has timed out.",
			"status.message.delete.success=Deletion successful.",
			"status.message.delete.failure=Error while deleting.",
			"status.message.edit.success=Modification successful.",
			"status.message.edit.failure=Error while modifying.",
			"# status.message.empty should have at least one whitespace",
			"status.message.empty=\\ ", "status.message.iteration.after=)",
			"status.message.iteration.before=\\ (",
			"status.message.submit.success=Creation successful.",
			"status.message.submit.failure=Error while saving.", "# workflow-related",
			"caption.button.workflow.cancel=Cancel",
			"caption.button.workflow.select=Select this workflow",
			"caption.button.workflow.start=Activate this workflow",
			"workflow.selection.page.title=Select a workflow",
			"workflow.selection.process.group=Process definitions",
			"workflow.selection.instance.group=In-progress instances",
			"workflow.selection.global.group=Workflows",
			"workflow.process.widget.title=Process definitions",
			"workflow.instance.widget.title=Instances",
			"workflow.error.submit.data=Please submit the data form before starting a workflow.",
			"workflow.error.start.failure=Failed in starting the workflow." };

	/**
	 * @param args
	 * @throws IOException
	 */
	public static boolean generate(String filePath) throws IOException {
		String line;
		FileOutputStream fos = null;
		try {
			int pos = filePath.lastIndexOf(File.separator);
			String dirPath = filePath.substring(0, pos);
			File dir = new File(dirPath);
			dir.mkdirs();
//			File file = new File(filePath);
			fos = new FileOutputStream(filePath);
			for (String property : properties) {
				line = property + "\n";
				fos.write(line.getBytes());
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			fos.close();
		}
		return true;
	}

}
