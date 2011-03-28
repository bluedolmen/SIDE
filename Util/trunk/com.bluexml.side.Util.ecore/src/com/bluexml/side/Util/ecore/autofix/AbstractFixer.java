package com.bluexml.side.Util.ecore.autofix;

import java.io.File;

public abstract class AbstractFixer {
	protected File fixedFile;
	
	public abstract boolean fixe(File model, Exception e);

	protected abstract boolean canFixe(File model, Exception e);

	public File getFixedFile() {
		return fixedFile;
	}

}
