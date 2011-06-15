package com.bluexml.side.util.dependencies;

import org.apache.commons.lang.StringUtils;
import org.apache.maven.repository.RepositorySystem;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

import com.bluexml.side.util.libs.eclipse.StylingUtil;
import com.bluexml.side.util.libs.eclipse.RessourcesSelection.RESOURCE_TYPE;
import com.bluexml.side.util.libs.eclipse.wizards.AbstractFieldsPreferencePage;

public class WorkbenchPreferencePage1 extends AbstractFieldsPreferencePage implements IWorkbenchPreferencePage {

	public static String MAVEN_REPO_LOCATION = "maven.repository.location"; //$NON-NLS-1$
	public static String MAVEN_REPO_LAST_UPDATE = "maven.repository.last.update"; //$NON-NLS-1$

	public void init(IWorkbench workbench) {
		IPreferenceStore store = Activator.getDefault().getPreferenceStore();
		setPreferenceStore(store);
		String locationPreference = getLocationPreference();
		if (StringUtils.trimToNull(locationPreference) == null) {
			values.put(MAVEN_REPO_LOCATION, RepositorySystem.defaultUserLocalRepository.toString());
		} else {
			values.put(MAVEN_REPO_LOCATION, locationPreference);
		}
	}

	//	protected Control createContents(Composite parent) {
	//		Composite composite = createComposite(parent);
	//		createFieldsControls(composite);
	//		return composite;
	//	}

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
		store.setValue(MAVEN_REPO_LOCATION, values.get(MAVEN_REPO_LOCATION));
		return true;
	}

	public static String getLocationPreference() {
		IPreferenceStore store = Activator.getDefault().getPreferenceStore();
		return store.getString(MAVEN_REPO_LOCATION);
	}

	public static String getLastRepoVersion(String repoId) {
		IPreferenceStore store = Activator.getDefault().getPreferenceStore();
		return store.getString(getPreferencesIdForRepo(repoId));
	}

	private static String getPreferencesIdForRepo(String repoId) {
		return MAVEN_REPO_LAST_UPDATE + ":" + repoId; //$NON-NLS-1$
	}

	public static void setRepoVersion(String repoId, String version) {
		IPreferenceStore store = Activator.getDefault().getPreferenceStore();
		store.setValue(getPreferencesIdForRepo(repoId), version);
	}

	public void createFieldsControls(Composite parent) {

		createResourceControl(parent, Messages.WorkbenchPreferencePage1_3, MAVEN_REPO_LOCATION, RESOURCE_TYPE.RESOURCE_TYPE_DIRECTORY);

		Label update_label = new Label(parent, SWT.NONE);
		update_label.setText(Messages.WorkbenchPreferencePage1_4);
		GridData layoutData = StylingUtil.getNewLayoutData();
		layoutData.horizontalSpan = 3;
		update_label.setLayoutData(layoutData);
		Button update_repo = new Button(parent, SWT.PUSH);

		update_repo.setText(Messages.WorkbenchPreferencePage1_5);

		update_repo.addSelectionListener(new SelectionListener() {

			public void widgetSelected(SelectionEvent e) {
				try {
					DependenciesDeployer.deploy(true);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}

			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub

			}
		});

	}
}
