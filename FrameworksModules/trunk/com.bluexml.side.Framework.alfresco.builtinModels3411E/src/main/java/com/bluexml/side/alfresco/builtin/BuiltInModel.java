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
package com.bluexml.side.alfresco.builtin;

import org.alfresco.service.namespace.QName;

public class BuiltInModel {

	public static final String MODEL_URI = "http://www.bluexml.com/model/content/buildInLibraryAlfresco/1.0";
	
	public static final QName TYPE_STRUCTURE_PAGETYPE = QName.createQName(MODEL_URI, "HasTreeFilter");
	public static final QName ASPECT_HasTreeFilter = QName.createQName(MODEL_URI, "HasTreeFilter");
	public static final QName ASPECT_TreeFilter = QName.createQName(MODEL_URI, "TreeFilter");
	public static final QName PROP_TreeFilter_ROOT = QName.createQName(MODEL_URI, "TreeFilter_root");
	public static final QName ASSO_HasTreeFilter = QName.createQName(MODEL_URI, "HasTreeFilter_hasTreeFilter_TreeFilter");
	public static final QName ASSO_TreeFilter_PARENT = QName.createQName(MODEL_URI, "TreeFilter_AssociationTree_TreeFilter");

}
