package com.bluexml.side.util.dependencies;

import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;
import org.eclipse.ui.internal.IPreferenceConstants;

public class WorkbenchPreferencePage1 extends PreferencePage implements IWorkbenchPreferencePage {

	public static String MAVEN_REPO_LOACATION = "maven.repository.localtion";
	String repoPathStirng = "";

	@Override
	protected Control createContents(Composite parent) {
		Composite composite = createComposite(parent);

		Group groupMavenRepository = new Group(composite, SWT.LEFT);
		groupMavenRepository.setText("custom maven repository loaction :");
		groupMavenRepository.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		groupMavenRepository.setFont(composite.getFont());
		GridLayout layout = new GridLayout();
		layout.numColumns = 2;
		groupMavenRepository.setLayout(layout);

		final Text repositoryPath = new Text(groupMavenRepository, SWT.NONE);
		repositoryPath.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				repoPathStirng = repositoryPath.getText();
			}
		});
		return composite;
	}

	public void init(IWorkbench workbench) {
		IPreferenceStore store = Activator.getDefault().getPreferenceStore();
		setPreferenceStore(store);
		repoPathStirng = store.getString(MAVEN_REPO_LOACATION);
	}

	/**
	 * Creates the composite which will contain all the preference controls for
	 * this page.
	 * 
	 * @param parent
	 *            the parent composite
	 * @return the composite for this page
	 */
	protected Composite createComposite(Composite parent) {
		Composite composite = new Composite(parent, SWT.NONE);
		GridData data = new GridData(GridData.FILL_BOTH);
		composite.setLayoutData(data);
		composite.setFont(parent.getFont());
		GridLayout layout = new GridLayout();
		layout.marginWidth = 0;
		layout.marginHeight = 0;
		layout.verticalSpacing = 10;
		composite.setLayout(layout);
		return composite;
	}

	public boolean performOk() {
		IPreferenceStore store = getPreferenceStore();

		// store the open view mode setting
		store.setValue(MAVEN_REPO_LOACATION, this.repoPathStirng);
		return true;
	}

}
