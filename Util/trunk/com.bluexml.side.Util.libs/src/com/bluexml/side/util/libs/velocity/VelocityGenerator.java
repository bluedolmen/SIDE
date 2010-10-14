package com.bluexml.side.util.libs.velocity;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.eclipse.core.runtime.Assert;

public class VelocityGenerator {
	List<VelocityAction> vActions = new ArrayList<VelocityAction>();

	public void addAction(VelocityAction vc) {
		vActions.add(vc);
	}

	public void addAction(String velocityTemplatePath, String fileoutPath, Map<String, Object> context) {
		vActions.add(new VelocityAction(context, velocityTemplatePath, fileoutPath));
	}

	public VelocityGenerator(VelocityAction vc) {
		addAction(vc);
	}

	public VelocityGenerator(String velocityTemplatePath, String fileoutPath, Map<String, Object> context) {
		addAction(velocityTemplatePath, fileoutPath, context);
	}

	public void generateAll() throws Exception {
		for (VelocityAction va : vActions) {
			Assert.isNotNull(va.getOutputFilePath(), "this velocity generator must have an output stream where to write");
			// the output is a path where it's possible to obtain a stream
			String out = va.getOutputFilePath();
			File f = new File(out);
			Writer fw = new FileWriter(f);

			generate(va, fw);

		}
	}

	/**
	 * @param va
	 * @param fw
	 * @throws Exception
	 * @throws IOException
	 */
	public void generate(VelocityAction va, Writer fw) throws Exception, IOException {
		Assert.isNotNull(va.getContext(), "velocity context must be given");
		Assert.isNotNull(va.getTemplatePath(), "velocity template must be given");
		
		// get Template
		VelocityContext context = va.getContext();
		String templatePath = va.getTemplatePath();
		InputStream template=null;
		File templateFile = new File(templatePath);
		if (templateFile.exists()) {
			// the template is located on File System
			template = new FileInputStream(templateFile);
		} else {
			// resource may be acceded in classPath
			template= getClass().getResourceAsStream(templatePath);
		}		 
		
		Assert.isNotNull(template, "velocity template resource can't be loaded");
		// execute
		Velocity.init();
		String s = IOUtils.toString(template);
		Velocity.evaluate(context, fw, "log", s);
		
		// close streams
		template.close();
		fw.flush();
		fw.close();
	}

}
