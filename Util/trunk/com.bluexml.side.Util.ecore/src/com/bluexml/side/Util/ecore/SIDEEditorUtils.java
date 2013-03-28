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
package com.bluexml.side.Util.ecore;

/**
 * This class is used as an helper to factorize the use of Ecore editor ids.
 * <p>
 * This class should be probably improved to ensure the validity
 * (synchronization) with editor declarations (introspection ?).
 * 
 * @author pajot-b
 * 
 */
public final class SIDEEditorUtils {

	public static final String DATA_MODEL_EDITOR_ID = "com.bluexml.side.clazz.presentation.ClazzEditorID";
	public static final String FORM_MODEL_EDITOR_ID = "com.bluexml.side.form.presentation.formEditorID";
	public static final String PORTAL_MODEL_EDITOR_ID = "com.bluexml.side.portal.presentation.PortalEditorID";
	public static final String REQUIREMENTS_MODEL_EDITOR_ID = "com.bluexml.side.requirements.presentation.RequirementsEditorID";
	public static final String VIEW_MODEL_EDITOR_ID = "com.bluexml.side.view.presentation.ViewEditorID";
	public static final String WORKFLOW_MODEL_EDITOR_ID = "com.bluexml.side.workflow.presentation.WorkflowEditorID";
	public static final String APPLICATION_MODEL_EDITOR_ID = "com.bluexml.side.application.presentation.ApplicationEditorID";
	
	private SIDEEditorUtils() {} // Utility class
}
