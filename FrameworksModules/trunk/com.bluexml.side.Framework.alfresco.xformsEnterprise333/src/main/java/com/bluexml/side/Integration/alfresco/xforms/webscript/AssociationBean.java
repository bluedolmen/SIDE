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
package com.bluexml.side.Integration.alfresco.xforms.webscript;

/**
 * Taken from BxDS dataLayer module.
 * 
 * @author Amenel
 */
public class AssociationBean {
	public enum Actions {
		// delete listed associations and keep the others (same type or not)
		DELETE(0, "delete"),
		// delete all associations of this type
		DELETE_ALL(1, "deleteAllAssoOfThisType"),
		// delete all associations of this type and add listed ones
		ADD_DELETE_OTHER(2, "add/deleteOther"),
		// add listed associations
		ADD(3, "add");
		String literal;
		int value;

		Actions(int value, String literal) {
			this.value = value;
			this.literal = literal;
		}

		public String getLiteral() {
			return literal;
		}

		public int getValue() {
			return value;
		}
	}

	public static enum AssoType {
		Simple, Composition;
	}

	private String associationName;
	private String targetQualifiedName;
	private String targetId;
	private String targetLabel;
	private Actions action = Actions.ADD;
	private AssoType assoType = null;

	public AssociationBean() {
		super();
	}

	public AssociationBean(String associationName, String targetId) {
		super();
		this.associationName = associationName;
		this.targetId = targetId;
	}

	public AssociationBean(String associationName, String targetId, String targetLabel, String targetQualifiedName) {
		super();
		this.associationName = associationName;
		this.targetId = targetId;
		this.targetLabel = targetLabel;
		this.assoType = AssoType.Simple;
		this.targetQualifiedName = targetQualifiedName;
	}

	public AssociationBean(String associationName, String targetId, String targetLabel, AssoType assoType, String targetQualifiedName) {
		super();
		this.associationName = associationName;
		this.targetId = targetId;
		this.targetLabel = targetLabel;
		this.assoType = assoType;
		this.targetQualifiedName = targetQualifiedName;
	}

	public String getAssociationName() {
		return associationName;
	}

	public void setAssociationName(String associationName) {
		this.associationName = associationName;
	}

	public String getTargetQualifiedName() {
		return targetQualifiedName;
	}

	public void setTargetQualifiedName(String targetQualifiedName) {
		this.targetQualifiedName = targetQualifiedName;
	}

	public String getTargetId() {
		return targetId;
	}

	public void setTargetId(String target) {
		this.targetId = target;
	}

	public String getTargetLabel() {
		return targetLabel;
	}

	public void setTargetLabel(String targetLabel) {
		this.targetLabel = targetLabel;
	}

	public Actions getAction() {
		return action;
	}

	public void setAction(Actions action) {
		this.action = action;
	}

	public void setAction(String action) {
		this.action = Actions.valueOf(action);
	}

	public AssoType getAssoType() {
		return assoType;
	}

	public void setAssoType(AssoType assoType) {
		this.assoType = assoType;
	}

	@Override
	public String toString() {
		String st = "AssociationBean :{";
		st += "";
		st += "associationName =" + associationName;
		st += ",targetQualifiedName =" + targetQualifiedName;
		st += ",targetId =" + targetId;
		st += ",targetLabel =" + targetLabel;
		st += ",action = " + action.literal;
		st += "}";
		return st;
	}

}
