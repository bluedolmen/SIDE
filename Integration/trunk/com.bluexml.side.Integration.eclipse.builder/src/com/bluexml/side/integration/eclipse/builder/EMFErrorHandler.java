/**
 * 
 */
package com.bluexml.side.integration.eclipse.builder;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IMarker;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

class EMFErrorHandler extends DefaultHandler {

	private IFile file;

	public EMFErrorHandler(IFile file) {
		this.file = file;
	}

	private void addMarker(SAXParseException e, int severity) {
		MarkerHelper.addMarker(file, e.getMessage(), e.getLineNumber(), severity);
	}

	@Override
	public void error(SAXParseException exception) throws SAXException {
		addMarker(exception, IMarker.SEVERITY_ERROR);
	}

	@Override
	public void fatalError(SAXParseException exception) throws SAXException {
		addMarker(exception, IMarker.SEVERITY_ERROR);
	}

	@Override
	public void warning(SAXParseException exception) throws SAXException {
		addMarker(exception, IMarker.SEVERITY_WARNING);
	}
}