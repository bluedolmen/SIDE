package com.bluexml.side.Requirements.modeler.views.internal;

import org.eclipse.swt.graphics.Image;


/**
 * AbstractField is the abstract superclass for fields.
 * @since 3.2
 *
 */
public abstract class AbstractField implements IField {
	
	boolean visible = true;
	
	/* (non-Javadoc)
	 * @see org.eclipse.ui.views.markers.internal.IField#isShowing()
	 */
	public boolean isShowing() {
		return visible;
	}
	
	/**
	 * Set whether or not the receiver is showing.
	 * @param showing
	 */
	public void setShowing(boolean showing){
		visible = showing;
	}
	
	public int compare(Object obj1, Object obj2) {
		String v1 = getValue(obj1);
		String v2 = getValue(obj2);
		if (v1 != null && v2 != null)
			return v1.compareTo(v2);
		else
			return 0;
	}
	
	public Image getColumnHeaderImage() {
		return null;
	}
	
	public int getDefaultDirection() {
		return 1;
	}
	
	public Image getDescriptionImage() {
		return null;
	}
	
	public Image getImage(Object obj) {
		return null;
	}
}
