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
