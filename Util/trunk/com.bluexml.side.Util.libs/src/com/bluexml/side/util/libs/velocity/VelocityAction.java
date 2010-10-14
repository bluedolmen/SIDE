package com.bluexml.side.util.libs.velocity;

import java.util.Map;

import org.apache.velocity.VelocityContext;

public class VelocityAction {

	VelocityContext context;
	String templatePath;
	String outputFilePath;

	public VelocityContext getContext() {
		return context;
	}

	public String getTemplatePath() {
		return templatePath;
	}

	public String getOutputFilePath() {
		return outputFilePath;
	}

	public VelocityAction(VelocityContext context, String templatePath, String outputFilePath) {
		super();
		this.context = context;
		this.templatePath = templatePath;
		this.outputFilePath = outputFilePath;
	}

	public VelocityAction(Map<String, Object> context, String templatePath, String outputFilePath) {
		super();
		VelocityContext mainContext = new VelocityContext();
		for (Map.Entry<String, Object> ent : context.entrySet()) {
			mainContext.put(ent.getKey(), ent.getValue());
		}

		this.context = mainContext;
		this.templatePath = templatePath;
		this.outputFilePath = outputFilePath;
	}

}
