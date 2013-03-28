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
package com.bluexml.side.util.generator;

import java.io.File;

import org.eclipse.core.resources.IFile;
import org.jdom.Document;

import com.bluexml.side.util.libs.xml.XmlHelper;

public class XMLConflictResolver {
	ConflitResolverHelper cresolver;

	public XMLConflictResolver(ConflitResolverHelper cresolver) {
		this.cresolver = cresolver;
	}

	/**
	 * the merged file override the final one (in final folder)
	 * @param f
	 * @throws Exception
	 */
	public void resolveByMerging(IFile f) throws Exception {
		IFile tmpFile = cresolver.getTempFilePath(f);

		File ff = f.getRawLocation().makeAbsolute().toFile();
		File gf = tmpFile.getRawLocation().makeAbsolute().toFile();
		// open the file as XML jdom
		Document alreadyGenerated = XmlHelper.buildJdomDocument(ff);
		Document generated = XmlHelper.buildJdomDocument(gf);

		// TODO : add comment to mark insertion point

		alreadyGenerated = XmlHelper.includeDocument(alreadyGenerated, generated, false);
		// write to file
		XmlHelper.writeXmlFile(ff, alreadyGenerated);
	}
}
