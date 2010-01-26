package com.bluexml.side.Requirements.modeler.views.internal;

import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.swt.graphics.Image;

/**
 * The MarkerViewLabelProvider is a label provider for an individual field.
 * 
 * @since 3.3
 * 
 */
public class MarkerViewLabelProvider extends ColumnLabelProvider {

	IField field;

	/**
	 * Create a MarkerViewLabelProvider on a field
	 * 
	 * @param field
	 */
	MarkerViewLabelProvider(IField field) {
		this.field = field;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.viewers.ColumnLabelProvider#getText(java.lang.Object)
	 */
	public String getText(Object element) {
		return field.getValue(element);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.viewers.ColumnLabelProvider#getImage(java.lang.Object)
	 */
	public Image getImage(Object element) {
		return field.getImage(element);
	}

	
}
