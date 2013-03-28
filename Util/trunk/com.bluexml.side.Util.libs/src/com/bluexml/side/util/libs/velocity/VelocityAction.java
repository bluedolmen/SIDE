/*
    Copyright (C) 2007-20013  BlueXML - www.bluexml.com

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU Affero General Public License as
    published by the Free Software Foundation, either version 3 of the
    License, or (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU Affero General Public License for more details.

    You should have received a copy of the GNU Affero General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/
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
