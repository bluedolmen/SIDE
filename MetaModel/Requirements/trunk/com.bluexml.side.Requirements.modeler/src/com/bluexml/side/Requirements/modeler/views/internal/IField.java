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
 * IField is the definition of fields for marker views.
 *
 */
public interface IField {

    /**
     * @return String the description of the field.
     */
    String getDescription();

    /**
     * @return the image associated with the description of the field or <code>null<code>.
     */
    Image getDescriptionImage();

    /**
     * @return The text to be displayed in the column header for this field.
     */
    String getColumnHeaderText();

    /**
     * @return The image to be displayed in the column header for this field or <code>null<code>.
     */
    Image getColumnHeaderImage();

    /**
     * @param obj
     * @return The String value of the object for this particular field to be displayed to the user.  
     */
    String getValue(Object obj);

    /**
     * @param obj
     * @return The image value of the object for this particular field to be displayed to the user
     * or <code>null<code>.
     */
    Image getImage(Object obj);

    /**
     * @param obj1
     * @param obj2
     * @return Either:
     * 	<li>a negative number if the value of obj1 is less than the value of obj2 for this field.
     *  <li><code>0</code> if the value of obj1 and the value of obj2 are equal for this field.
     *  <li>a positive number if the value of obj1 is greater than the value of obj2 for this field.
     */
    int compare(Object obj1, Object obj2);
    
    /**
     * Get the default direction for the receiver. Return either 
     * {@link TableComparator#ASCENDING } or {@link TableComparator#DESCENDING }
     * @return int
     */
   int getDefaultDirection();
   
   /**
    * Get the preferred width of the receiver.
    * @return int
    */
   int getPreferredWidth();

   /**
    * Return whether not the receiver is showing.
    * @return boolean
    */
   boolean isShowing();
   
   /**
    * Set whether or not the receiver is showing.
    * @param showing
    */
  void setShowing(boolean showing);
   
 
}
