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
package com.bluexml.side.Util.ecore.autofix.xsl;

import java.io.File;
import java.io.FileOutputStream;

import com.bluexml.side.Util.ecore.autofix.AbstractFixer;
import com.bluexml.side.util.libs.xml.XmlHelper;
import com.bluexml.side.util.libs.xml.XslTransformer;

public abstract class XSLFixer extends AbstractFixer {

	protected String transformerPath = null;

	public XSLFixer(String xslPath) {
		transformerPath = xslPath;
	}

	@Override
	public boolean fixe(File model, Exception e) {
		boolean ok = false;
		if (canFixe(model, e)) {
			// form model seem to be outdated
			// try to execute xsl to update form model
			try {
				fixedFile = File.createTempFile(model.getName(), "tmp");

				XslTransformer.transform(XmlHelper.buildW3cDocument(model.getAbsolutePath()), this.getClass().getResourceAsStream(transformerPath), new FileOutputStream(fixedFile));
				ok = true;
			} catch (Exception e1) {
				e.printStackTrace();
			}
		}
		return ok;
	}
}
