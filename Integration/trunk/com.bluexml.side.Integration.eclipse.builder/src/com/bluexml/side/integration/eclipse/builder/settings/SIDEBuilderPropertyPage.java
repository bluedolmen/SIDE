package com.bluexml.side.integration.eclipse.builder.settings;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;

import com.bluexml.side.application.Configuration;
import com.bluexml.side.util.libs.eclipse.RessourcesSelection.RESOURCE_TYPE;
import com.bluexml.side.util.libs.eclipse.pages.PageControlsHelper;
import com.bluexml.side.util.libs.eclipse.wizards.AbstractFieldsPropertyPage;
import com.bluexml.side.util.libs.eclipse.wizards.ValueMapListener;

public class SIDEBuilderPropertyPage extends AbstractFieldsPropertyPage {

	private static final String CONF = "conf";
	private static final String APPLICATION_MODEL = "applicationModel";

	public void createFieldsControls(Composite composite) {
		IAdaptable element = this.getElement();
		final IProject project = (IProject) element;
		SIDEBuilderConfiguration confs = new SIDEBuilderConfiguration(project);
		ArrayList<String> allowedValues = new ArrayList<String>();
		try {
			if (confs.load()) {
				// project have properties
				values.put(APPLICATION_MODEL, confs.applicationRessourcePath);
				values.put(CONF, confs.configurationName);
				initializeConfigurations(allowedValues, confs);
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		createResourceControl(composite, "application model", APPLICATION_MODEL, RESOURCE_TYPE.RESOURCE_TYPE_IFILE);

		final Combo createComboControl = (Combo) createComboControl(composite, "configuration", CONF, allowedValues)[1];

		values.addListener(new ValueMapListener<String, Object>() {

			public void remove(Object o) {
				// TODO Auto-generated method stub

			}

			public void put(String key, Object o) {
				try {
					if (key.equals(APPLICATION_MODEL)) {
						List<String> allowedValues = new ArrayList<String>();
						// get configuration names
						String o2 = (String) o;
						//						String path = ApplicationUtil.eclipseVariableSubstitution(o2);
						SIDEBuilderConfiguration conf = new SIDEBuilderConfiguration(project, o2);

						initializeConfigurations(allowedValues, conf);
						if (allowedValues.size() > 0) {
							String string = allowedValues.get(0);
							values.put(CONF, string);
						}
						// update comboBox
						PageControlsHelper.initializeCombo(CONF, allowedValues, values, createComboControl);
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}

		});
	}

	private static void initializeConfigurations(List<String> allowedValues, SIDEBuilderConfiguration conf) throws IOException {
		List<Configuration> configurations = conf.getConfigurations();
		for (Configuration configuration : configurations) {
			allowedValues.add(configuration.getName());
		}
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.preference.PreferencePage#performOk()
	 */
	@Override
	public boolean performOk() {
		IAdaptable element = this.getElement();
		final IProject project = (IProject) element;
		String path;
		try {
			path = (String) values.get(APPLICATION_MODEL);
			SIDEBuilderConfiguration conf = new SIDEBuilderConfiguration(project, path);
			conf.selectConfiguration((String) values.get(CONF));
			conf.persist();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
}
