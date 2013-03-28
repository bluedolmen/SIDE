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
