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
