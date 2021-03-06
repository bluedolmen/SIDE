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
package com.bluexml.side.util.libs.eclipse;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.variables.IStringVariableManager;
import org.eclipse.core.variables.VariablesPlugin;
import org.eclipse.debug.internal.ui.SWTFactory;
import org.eclipse.debug.ui.StringVariableSelectionDialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.accessibility.AccessibleAdapter;
import org.eclipse.swt.accessibility.AccessibleEvent;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.dialogs.ContainerSelectionDialog;
import org.eclipse.ui.dialogs.ElementTreeSelectionDialog;
import org.eclipse.ui.externaltools.internal.ui.FileSelectionDialog;
import org.eclipse.ui.model.BaseWorkbenchContentProvider;
import org.eclipse.ui.model.WorkbenchLabelProvider;

public class RessourcesSelection extends AbstractDialogCellEditor {

	private Text locationField;
	private String initialValue = "";
	private Button workspaceLocationButton;
	private Button fileLocationButton;
	private Button variablesLocationButton;
	private WidgetListener fListener = new WidgetListener();
	private String resourcePath = "";
	private String locationLabel;
	private RESOURCE_TYPE dataType;

	private List<ResourceSelectionListener> resourceSelectionListener = new ArrayList<ResourceSelectionListener>();

	public void addResourceSelectionListener(ResourceSelectionListener listener) {
		resourceSelectionListener.add(listener);
	}

	public String getResourcePath() {
		return resourcePath;
	}

	protected boolean isResizable() {
		return true;
	}

	public RessourcesSelection(Shell parent) {
		super(parent);
	}

	public void init(String locationLabel, String initialValue, RESOURCE_TYPE resource_type) {
		if (initialValue != null) {
			this.initialValue = initialValue;
		} else {
			// no default value so initialValue parameter is null
			// to avoid setting initialValue with value from another field we set it here
			this.initialValue = "";
		}
		this.dataType = resource_type;
		this.locationLabel = locationLabel;
		this.resourcePath = initialValue;

	}

	public void init(String locationLabel, String initialValue, String resource_type) {
		init(locationLabel, initialValue, RESOURCE_TYPE.getType(resource_type));
	}

	public Text getLocationField() {
		return locationField;
	}

	/**
	 * Creates the controls needed to edit the working directory
	 * attribute of an external tool
	 * 
	 * @param parent
	 *            the composite to create the controls in
	 */
	protected void createWorkDirectoryComponent(Composite parent) {
		Group group = new Group(parent, SWT.NONE);
		String locationLabel = getLocationLabel();
		group.setText(locationLabel);
		GridLayout layout = new GridLayout();
		layout.numColumns = 1;
		GridData gridData = new GridData(GridData.FILL_HORIZONTAL);
		group.setLayout(layout);
		group.setLayoutData(gridData);

		int style = SWT.BORDER;
		if (dataType.equals(RESOURCE_TYPE.RESOURCE_TYPE_STRING_MULTILINE)) {
			style = SWT.MULTI | SWT.BORDER;
		}
		locationField = new Text(group, style);

		locationField.setText(initialValue);
		if (dataType.equals(RESOURCE_TYPE.RESOURCE_TYPE_STRING_MULTILINE)) {
			gridData = new GridData(GridData.FILL_HORIZONTAL);
			int lineCount = locationField.getLineCount();
			int lineHeight = locationField.getLineHeight();

			int i = (lineCount + 10) * lineHeight;
			gridData.heightHint = i;
			locationField.setSize(100, i);
			gridData.widthHint = IDialogConstants.ENTRY_FIELD_WIDTH * 2;
		} else {
			gridData = new GridData(GridData.FILL_HORIZONTAL);
			gridData.widthHint = IDialogConstants.ENTRY_FIELD_WIDTH;
		}

		locationField.setLayoutData(gridData);
		locationField.addModifyListener(fListener);
		addControlAccessibleListener(locationField, group.getText());

		Composite buttonComposite = new Composite(group, SWT.NONE);
		layout = new GridLayout();
		layout.marginHeight = 0;
		layout.marginWidth = 0;
		layout.numColumns = 3;
		gridData = new GridData(GridData.FILL_BOTH);
		buttonComposite.setLayout(layout);
		buttonComposite.setLayoutData(gridData);
		buttonComposite.setFont(parent.getFont());

		if (!(dataType.equals(RESOURCE_TYPE.RESOURCE_TYPE_STRING) || dataType.equals(RESOURCE_TYPE.RESOURCE_TYPE_STRING_MULTILINE))) {
			workspaceLocationButton = createPushButton(buttonComposite, StylingUtil.Messages.getString("SelectResources.2"), null); //$NON-NLS-1$
			workspaceLocationButton.addSelectionListener(fListener);
			addControlAccessibleListener(workspaceLocationButton, group.getText() + " " + workspaceLocationButton.getText()); //$NON-NLS-1$

			fileLocationButton = createPushButton(buttonComposite, StylingUtil.Messages.getString("SelectResources.3"), null); //$NON-NLS-1$
			fileLocationButton.addSelectionListener(fListener);
			addControlAccessibleListener(fileLocationButton, group.getText() + " " + fileLocationButton.getText()); //$NON-NLS-1$
		}
		variablesLocationButton = createPushButton(buttonComposite, StylingUtil.Messages.getString("SelectResources.4"), null); //$NON-NLS-1$
		
		if (!dataType.equals(RESOURCE_TYPE.RESOURCE_TYPE_STRING_MULTILINE)) {			
			variablesLocationButton.addSelectionListener(fListener);
			addControlAccessibleListener(variablesLocationButton, group.getText() + " " + variablesLocationButton.getText()); //$NON-NLS-1$
		} else {
			variablesLocationButton.setVisible(false);
		}
		
	}

	private String getLocationLabel() {
		return locationLabel;
	}

	public void addControlAccessibleListener(Control control, String controlName) {
		//strip mnemonic (&)
		String[] strs = controlName.split("&"); //$NON-NLS-1$
		StringBuffer stripped = new StringBuffer();
		for (int i = 0; i < strs.length; i++) {
			stripped.append(strs[i]);
		}
		control.getAccessible().addAccessibleListener(new ControlAccessibleListener(stripped.toString()));
	}

	/**
	 * Creates and returns a new push button with the given
	 * label and/or image.
	 * 
	 * @param parent
	 *            parent control
	 * @param label
	 *            button label or <code>null</code>
	 * @param image
	 *            image of <code>null</code>
	 * @return a new push button
	 */
	protected Button createPushButton(Composite parent, String label, Image image) {
		return SWTFactory.createPushButton(parent, label, image);
	}

	@SuppressWarnings("restriction")
	public static void handleWorkspaceLocationButtonSelected(Shell shell, Text locationField) {
		FileSelectionDialog dialog;
		dialog = new FileSelectionDialog(shell, ResourcesPlugin.getWorkspace().getRoot(), StylingUtil.Messages.getString("SelectResources.5"));
		dialog.open();
		IStructuredSelection result = dialog.getResult();
		if (result == null) {
			return;
		}
		Object file = result.getFirstElement();
		if (file instanceof IFile) {
			IFile fNewFile = (IFile) file;
			locationField.setText(VariablesPlugin.getDefault().getStringVariableManager().generateVariableExpression("workspace_loc", fNewFile.getFullPath().toString())); //$NON-NLS-1$
		}
	}

	/**
	 * Prompts the user for a working directory location within the workspace
	 * and sets the working directory as a String containing the workspace_loc
	 * variable or <code>null</code> if no location was obtained from the user.
	 * 
	 * @param locationField
	 * @param shell
	 */
	public static void handleWorkspaceDirectoryButtonSelected(Shell shell, Text locationField) {
		ContainerSelectionDialog containerDialog;
		containerDialog = new ContainerSelectionDialog(shell, ResourcesPlugin.getWorkspace().getRoot(), false, StylingUtil.Messages.getString("SelectResources.6"));
		containerDialog.open();
		Object[] resource = containerDialog.getResult();
		String text = null;
		if (resource != null && resource.length > 0) {
			text = newVariableExpression("workspace_loc", ((IPath) resource[0]).toString()); //$NON-NLS-1$
		}
		if (text != null) {
			locationField.setText(text);
		}
	}

	/**
	 * Prompts the user to choose a location from the filesystem and
	 * sets the location as the full path of the selected file.
	 * 
	 * @param locationField
	 * @param shell
	 */
	public static void handleFileSystemLocationButtonSelected(Shell shell, Text locationField) {
		FileDialog fileDialog = new FileDialog(shell, SWT.NONE);
		fileDialog.setFileName(locationField.getText());
		String text = fileDialog.open();
		if (text != null) {
			locationField.setText(text);
		}
	}

	public static Map<String, IResource> handleWorkspaceMultiIResourceSelection(String title, String message, ViewerFilter filter, Object root) {
		Map<String, IResource> selection = new TreeMap<String, IResource>();
		if (root == null) {
			root = ResourcesPlugin.getWorkspace().getRoot();
		}

		ElementTreeSelectionDialog ets = new ElementTreeSelectionDialog(Display.getDefault().getActiveShell(), new WorkbenchLabelProvider(), new BaseWorkbenchContentProvider());
		ets.setBlockOnOpen(true);
		ets.setAllowMultiple(true);
		ets.setTitle(title);
		ets.setMessage(message);

		ets.setInput(root);
		ets.setHelpAvailable(false);
		ets.addFilter(filter);

		if (ElementTreeSelectionDialog.OK == ets.open()) {
			Object[] result = ets.getResult();
			for (Object o : result) {
				IResource file = (IResource) o;
				String filePath = file.getFullPath().toPortableString();

				if (filePath != null) {
					selection.put(filePath, file);

				}
			}
		}

		return selection;
	}

	/**
	 * Prompts the user to choose a working directory from the filesystem.
	 */
	public static void handleFileSystemDirecoryButtonSelected(Shell shell, Text locationField) {
		DirectoryDialog dialog = new DirectoryDialog(shell, SWT.SAVE);
		dialog.setMessage(StylingUtil.Messages.getString("SelectResources.7"));
		dialog.setFilterPath(locationField.getText());

		String text = dialog.open();
		if (text != null) {
			locationField.setText(text);
		}
	}

	/**
	 * A variable entry button has been pressed for the given text
	 * field. Prompt the user for a variable and enter the result
	 * in the given field.
	 */
	private void handleVariablesButtonSelected(Text textField) {
		String variable = getVariable(getShell());
		if (variable != null) {
			textField.insert(variable);
		}
	}

	/**
	 * Returns a new variable expression with the given variable and the given
	 * argument.
	 * 
	 * @see IStringVariableManager#generateVariableExpression(String, String)
	 */
	protected static String newVariableExpression(String varName, String arg) {
		return VariablesPlugin.getDefault().getStringVariableManager().generateVariableExpression(varName, arg);
	}

	private class ControlAccessibleListener extends AccessibleAdapter {
		private String controlName;

		ControlAccessibleListener(String name) {
			controlName = name;
		}

		public void getName(AccessibleEvent e) {
			e.result = controlName;
		}
	}

	@Override
	protected Control createDialogArea(Composite parent) {

		Composite mainComposite = new Composite(parent, SWT.NONE);
		GridLayout layout = new GridLayout();
		layout.numColumns = 1;
		GridData gridData = new GridData(GridData.FILL_HORIZONTAL);
		mainComposite.setLayout(layout);
		mainComposite.setLayoutData(gridData);
		mainComposite.setFont(parent.getFont());
		createWorkDirectoryComponent(mainComposite);
		return mainComposite;
	}

	/**
	 * A listener to update for text modification and widget selection.
	 */
	protected class WidgetListener extends SelectionAdapter implements ModifyListener {
		public void modifyText(ModifyEvent e) {
			if (e.getSource() == locationField) {
				resourcePath = locationField.getText();
			}
		}

		public void widgetSelected(SelectionEvent e) {
			Object source = e.getSource();
			if (source == workspaceLocationButton) {
				if (dataType.equals(RESOURCE_TYPE.RESOURCE_TYPE_DIRECTORY)) {
					handleWorkspaceDirectoryButtonSelected(getShell(), locationField);
				} else {
					handleWorkspaceLocationButtonSelected(getShell(), locationField);
				}
			} else if (source == fileLocationButton) {
				if (dataType.equals(RESOURCE_TYPE.RESOURCE_TYPE_DIRECTORY)) {
					handleFileSystemDirecoryButtonSelected(getShell(), locationField);
				} else {
					handleFileSystemLocationButtonSelected(getShell(), locationField);
				}
			} else if (source == variablesLocationButton) {
				handleVariablesButtonSelected(locationField);
			}
		}
	}

	@Override
	protected void cancelPressed() {
		this.resourcePath = initialValue;
		for (ResourceSelectionListener listener : resourceSelectionListener) {
			listener.cancel();
		}
		super.cancelPressed();
	}

	@Override
	protected void okPressed() {
		for (ResourceSelectionListener listener : resourceSelectionListener) {
			listener.ok();
		}
		super.okPressed();
	}

	public static String getVariable(Shell shell) {
		StringVariableSelectionDialog dialog = new StringVariableSelectionDialog(shell);
		dialog.open();
		return dialog.getVariableExpression();
	}

	public enum RESOURCE_TYPE {
		RESOURCE_TYPE_DIRECTORY("Directory"), RESOURCE_TYPE_FILE("File"), RESOURCE_TYPE_STRING("String"), RESOURCE_TYPE_STRING_MULTILINE("multiline"), RESOURCE_TYPE_IFILE("IFile"), RESOURCE_TYPE_IFILES("IFiles");
		String code;

		RESOURCE_TYPE(String code) {
			this.code = code;
		}

		public String toString() {
			return code;
		}

		public static RESOURCE_TYPE getType(String s) {
			for (RESOURCE_TYPE t : values()) {
				if (t.toString().endsWith(s)) {
					return t;
				}
			}
			return null;
		}
	}
}
