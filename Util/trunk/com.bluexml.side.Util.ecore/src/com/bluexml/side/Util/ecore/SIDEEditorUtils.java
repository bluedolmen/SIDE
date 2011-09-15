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
