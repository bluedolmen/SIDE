package com.bluexml.side.application.ui.action;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.resources.IFile;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.xmi.XMIResource;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceImpl;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.ProgressBar;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.forms.widgets.FormText;

import com.bluexml.side.application.Application;
import com.bluexml.side.application.ApplicationPackage;
import com.bluexml.side.application.Configuration;
import com.bluexml.side.application.Model;
import com.bluexml.side.application.ui.action.utils.ApplicationUtil;
import com.bluexml.side.application.ui.action.utils.Generate;
import com.bluexml.side.util.security.preferences.SWTResourceManager;

public class GeneratePopUp extends Dialog {

	private Configuration configuration;
	private List<String> staticParameters;
	private List<Model> models;

	/**
	 * Create the dialog
	 *
	 * @param parentShell
	 * @param list
	 * @param staticFieldsName
	 * @param configuration
	 */
	public GeneratePopUp(Shell parentShell, Configuration p_configuration) {
		super(parentShell);
		setShellStyle(SWT.DIALOG_TRIM | SWT.MODELESS
                | getDefaultOrientation());
		configuration = p_configuration;
		staticParameters = ApplicationDialog.staticFieldsName;
		models = ApplicationUtil.getModels((Application) p_configuration.eContainer());
	}

	public GeneratePopUp(Shell parentShell, IFile file, String name) throws IOException {
		super(parentShell);
		URI uri = URI.createFileURI(file.getRawLocation().toFile().getAbsolutePath());
		XMIResource resource = new XMIResourceImpl(uri);

		FileInputStream fi = new FileInputStream(file.getRawLocation().toFile());
		Map<Object, Object> map = new HashMap<Object, Object>();
		map.put(ApplicationPackage.eINSTANCE.getNsURI(), ApplicationPackage.eINSTANCE);
		map.put(XMLResource.OPTION_SCHEMA_LOCATION_IMPLEMENTATION, Boolean.TRUE);
		resource.load(fi, map);
		Application application = (Application) resource.getContents().get(0);
		configuration = application.getConfiguration(name);
		staticParameters = ApplicationDialog.staticFieldsName;
		models = ApplicationUtil.getModels((Application) configuration.eContainer());
	}

	/**
	 * Create contents of the dialog
	 *
	 * @param parent
	 */
	@Override
	protected Control createDialogArea(Composite parent) {
		Composite container = (Composite) super.createDialogArea(parent);
		container.setLayout(null);

		final ProgressBar progressBar = new ProgressBar(container, SWT.SMOOTH);
		progressBar.setBounds(10, 69, 514, 17);
		Display.getDefault().getActiveShell();

		final Label label = new Label(container, SWT.NONE);
		label.setForeground(SWTResourceManager.getColor(92, 92, 92));
		label.setAlignment(SWT.CENTER);
		label.setBounds(10, 92, 464, 15);

		final Label generationsOptionsLabel = new Label(container, SWT.NONE);
		generationsOptionsLabel.setAlignment(SWT.CENTER);
		generationsOptionsLabel.setBounds(10, 24, 514, 24);
		generationsOptionsLabel.setFont(SWTResourceManager.getFont("", 12, SWT.BOLD));
		generationsOptionsLabel.setText("Generation");

		final StyledText styletext = new StyledText(container, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
		styletext.setBounds(10, 113, 514, 201);

		//final Browser logLink = new Browser(container, SWT.NONE);

		FormText logLink = new FormText(container, SWT.WRAP);
		logLink.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_WIDGET_BACKGROUND));
		logLink.setBounds(137, 320, 223, 24);
		logLink.setVisible(false);
		logLink.setText("<form><p>Log File can be found <a href=\"log\">here</a>.</p></form>", true, true);

		try {
			Generate gen = new Generate();
			gen.run(configuration, staticParameters, models, progressBar, label, styletext, logLink);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return container;
	}

	/**
	 * Create contents of the button bar
	 *
	 * @param parent
	 */
	@Override
	protected void createButtonsForButtonBar(Composite parent) {
		createButton(parent, IDialogConstants.OK_ID, IDialogConstants.OK_LABEL, true);
		createButton(parent, IDialogConstants.CANCEL_ID, IDialogConstants.CANCEL_LABEL, false);
	}

	/**
	 * Return the initial size of the dialog
	 */
	@Override
	protected Point getInitialSize() {
		return new Point(550, 431);
	}

}
