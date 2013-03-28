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
package com.bluexml.side.Util.ecore.autofix;

import java.io.File;
import java.io.IOException;

import com.bluexml.side.Util.ecore.autofix.xsl.XSLFixer;
import com.bluexml.side.util.libs.FileHelper;

/**
 * This class aim to apply correction to update Form models to be complient with
 * new Form MM
 * 
 * @author davidabad
 *         TODO : must move this in Form plugin and use extension point to
 *         declare new Fixer ...
 */
public class FormFixer extends XSLFixer {

	public FormFixer() {
		super("xsl/FormFixer.xsl");
	}

	@Override
	public boolean canFixe(File model, Exception e) {
		boolean canFixe = false;
		try {
			String ext = FileHelper.getFileExt(model);
			boolean extok = ext.toLowerCase().equals("form"); // good file type
			boolean errorok = e instanceof IOException || e.getClass().getName().equals("org.eclipse.emf.ecore.resource.impl.ResourceSetImpl$1DiagnosticWrappedException"); // good Exception
			String message = e.getMessage();
			errorok &= message.matches(".*Class '.*' is not found or is abstract.*"); // good message
			canFixe = extok && errorok; // all good
		} catch (Exception e1) {
			canFixe = false;
		}
		return canFixe;
	}

}
