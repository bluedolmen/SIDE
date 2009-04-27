package com.bluexml.side.application.ui.action;

import java.io.IOException;
import java.util.List;

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

import com.bluexml.side.application.Application;
import com.bluexml.side.application.Configuration;
import com.bluexml.side.application.Model;
import com.bluexml.side.application.ui.action.utils.ApplicationUtil;
import com.bluexml.side.application.ui.action.utils.Generate;
import com.swtdesigner.SWTResourceManager;

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
		configuration = p_configuration;
		staticParameters = ApplicationDialog.staticFieldsName;
		models = ApplicationUtil.getModels((Application)p_configuration.eContainer());
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
		progressBar.setBounds(10, 69, 464, 17);
		Display.getDefault().getActiveShell();

		final Label label = new Label(container, SWT.NONE);
		label.setForeground(SWTResourceManager.getColor(92, 92, 92));
		label.setAlignment(SWT.CENTER);
		label.setBounds(10, 100, 464, 15);

		final Label generationsOptionsLabel = new Label(container, SWT.NONE);
		generationsOptionsLabel.setAlignment(SWT.CENTER);
		generationsOptionsLabel.setBounds(10, 24, 464, 24);
		generationsOptionsLabel.setFont(SWTResourceManager.getFont("", 12, SWT.BOLD));
		generationsOptionsLabel.setText("Generation");
		
		final StyledText styletext = new StyledText(container, SWT.BORDER);
		styletext.setBounds(10, 135, 464, 128);
		
		try {
			Generate.run(configuration, staticParameters, models, progressBar, label, styletext);
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
		createButton(parent, IDialogConstants.OK_ID, IDialogConstants.OK_LABEL,
				true);
		createButton(parent, IDialogConstants.CANCEL_ID,
				IDialogConstants.CANCEL_LABEL, false);
	}

	/**
	 * Return the initial size of the dialog
	 */
	@Override
	protected Point getInitialSize() {
		return new Point(500, 375);
	}

}
