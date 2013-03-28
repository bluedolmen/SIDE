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
package com.bluexml.xforms.controller.navigation;

/**
 * The Enum FormTypeEnum.<br/>
 * This should be kept in sync with FormTypeRendered, at least for forms (and not enums).
 */
public enum FormTypeEnum {

		/** Forms built from class diagrams. */
		CLASS,
		/** Forms built from form diagrams, for data. */
		FORM,
		/** Forms for abstract types (and types with subtypes) */
		SELECTOR,
		/** Forms built from search forms */
		SEARCH,
		/** Forms built from form diagrams, for workflows. */
		WKFLW,
		LIST,
		/** Default value signaling that something is wrong. */
		BOTH;

}
