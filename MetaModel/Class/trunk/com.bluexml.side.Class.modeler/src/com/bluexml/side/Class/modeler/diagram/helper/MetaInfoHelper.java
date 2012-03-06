/*******************************************************************************
 * 	Copyright (C) BlueXML 2005-2008
 *
 * This program is free software; you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation; either version 2.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Boston, MA 02111.
 ******************************************************************************/
package com.bluexml.side.Class.modeler.diagram.helper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import com.bluexml.side.Class.modeler.diagram.dialogs.ConstraintsDataStructure;
import com.bluexml.side.Class.modeler.diagram.utils.metainfo.OblAssociationMetaInfo;
import com.bluexml.side.Class.modeler.diagram.utils.metainfo.OblAttributeMetaInfo;
import com.bluexml.side.Class.modeler.diagram.utils.metainfo.value.booleanWithParameter;
import com.bluexml.side.clazz.ClazzPackage;
import com.bluexml.side.clazz.Enumeration;
import com.bluexml.side.common.CommonPackage;
import com.bluexml.side.common.MetaInfo;
import com.bluexml.side.common.MetaInfoGroup;
import com.bluexml.side.common.ModelElement;
import com.bluexml.side.common.Stereotype;

public class MetaInfoHelper {

	public static void drawConstraintGroup(Composite composite, MetaInfoGroup group, Map drawConstraint, EObject object) {
		if (group != null) {
			if (group.getChildren().size() > 0) {
				Group constraintGroup = createConstraintsGroup(composite, group.getName());

				for (Object o : group.getChildren()) {
					if (o instanceof MetaInfo) {
						MetaInfo c = (MetaInfo) o;
						drawConstraint(constraintGroup, c, drawConstraint, object);
					}
				}
			}
		}

	}

	public static Group createConstraintsGroup(Composite composite, String label) {
		Group group = new Group(composite, SWT.NONE);
		group.setText(label);
		GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalSpan = 2;
		group.setLayout(new GridLayout(2, false));
		group.setLayoutData(gd);
		return group;
	}

	public static void drawConstraint(Composite composite, MetaInfo co, Map drawConstraints, EObject element) {

		Label lb = new Label(composite, SWT.NONE);
		lb.setText(co.getKey() + " : ");

		Object value;

		if (co.getValueType().equals(boolean.class)) {
			Combo chooser = new Combo(composite, SWT.READ_ONLY);
			chooser.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
			chooser.setItems(new String[] { "Yes", "No" });
			if (co.getDefaultValueBoolean())
				chooser.select(chooser.indexOf("Yes"));
			else
				chooser.select(chooser.indexOf("No"));
			chooser.setEnabled(co.getValueType() != null);
			value = chooser;
		} else if (co.getValueType().equals(booleanWithParameter.class)) {
			Group group = new Group(composite, SWT.NONE);
			GridData gd = new GridData(GridData.FILL_HORIZONTAL);
			group.setLayout(new GridLayout(1, false));
			group.setLayoutData(gd);

			final Combo chooser = new Combo(group, SWT.READ_ONLY);
			chooser.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
			chooser.setItems(new String[] { "Yes", "No" });
			chooser.select(chooser.indexOf("No"));
			chooser.setEnabled(co.getValueType() != null);

			final Text text = new Text(group, SWT.BORDER);
			text.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
			text.setEnabled(false);

			chooser.addSelectionListener(new SelectionAdapter() {
				public void widgetSelected(SelectionEvent e) {
					// Enable the type selection
					text.setEnabled(chooser.indexOf("Yes") == chooser.getSelectionIndex());
				}
			});

			booleanWithParameter b = new booleanWithParameter(chooser, text);
			value = b;

		} else if (co.getValueType().isEnum()) {
			Combo chooser = new Combo(composite, SWT.READ_ONLY);
			chooser.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

			if (co.getValueSet() != null) {
				if (co.getValueSet() instanceof Collection) {
					String[] items = new String[((Collection) co.getValueSet()).size()];
					int i = 0;
					for (Object o : ((Collection) co.getValueSet())) {
						items[i] = o.toString();
						i++;
					}
					chooser.setItems(items);
					chooser.select(0);
					chooser.setEnabled(co.getValueType() != null);
				}
			}
			value = chooser;
		} else if (co.getValueType().equals(Stereotype.class)) {
			Combo chooser = new Combo(composite, SWT.READ_ONLY);
			chooser.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

			Collection<EObject> reachableStereotypes = ItemPropertyDescriptor.getReachableObjectsOfType(element, CommonPackage.eINSTANCE.getStereotype());

			Collection<String> items = new ArrayList<String>();
			items.add("");

			for (Object o : reachableStereotypes) {
				if (o instanceof Stereotype) {
					Stereotype s = (Stereotype) o;
					if (co.getValueSet() instanceof String) {
						if (s.getName().matches((String) co.getValueSet())) {
							if (!(items.contains(s.getName()))) {
								items.add(s.getName());
							}
						}
					}
				}
			}

			String[] tabItems = new String[items.size()];
			int i = 0;
			for (String s : items) {
				tabItems[i] = s;
				i++;
			}

			chooser.setItems(tabItems);
			chooser.select(0);
			value = chooser;
		} else if (co.getValueType().equals(Enumeration.class)) {
			Combo chooser = new Combo(composite, SWT.READ_ONLY);
			chooser.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

			Collection<EObject> reachableEnumeration = ItemPropertyDescriptor.getReachableObjectsOfType(element, ClazzPackage.eINSTANCE.getEnumeration());

			Collection<String> items = new ArrayList<String>();
			items.add("");

			for (Object o : reachableEnumeration) {
				if (o instanceof Enumeration) {
					Enumeration e = (Enumeration) o;
					items.add(e.getName());
				}
			}

			String[] tabItems = new String[items.size()];
			int i = 0;
			for (String s : items) {
				tabItems[i] = s;
				i++;
			}

			chooser.setItems(tabItems);
			chooser.select(0);
			value = chooser;
		} else {
			Text text = new Text(composite, SWT.BORDER);
			text.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
			text.setEnabled(co.getValueType() != null);
			String defaultString = co.getValue();
			if (defaultString != null) {
				text.setText(defaultString);
			}

			value = text;
		}

		drawConstraints.put(co, value);
	}

	public static ConstraintsDataStructure getDataStructure(Map drawConstraints) {
		ConstraintsDataStructure dataConstraints = new ConstraintsDataStructure();
		for (Object omi : drawConstraints.keySet()) {
			MetaInfo mi = (MetaInfo) omi;
			String o = mi.getKey();
			if (o instanceof String) {
				String key = mi.getKey();
				Object obj = drawConstraints.get(mi);
				if (obj instanceof Text) {
					Text t = (Text) obj;
					if (t.getText().length() > 0) {
						dataConstraints.add(key, t.getText());
					}
				} else if (obj instanceof Combo) {
					Combo soc = (Combo) obj;
					if (soc.getItem(soc.getSelectionIndex()).equalsIgnoreCase("Yes")) {
						dataConstraints.add(key, "True");
					} else if (soc.getItem(soc.getSelectionIndex()).equalsIgnoreCase("No") && mi.getDefaultValueBoolean()) {
						dataConstraints.add(key, "False");
					} else if (!soc.getItem(soc.getSelectionIndex()).equalsIgnoreCase("No") && !soc.getItem(soc.getSelectionIndex()).equalsIgnoreCase("Yes")) {
						dataConstraints.add(key, soc.getItem(soc.getSelectionIndex()));
					}
				} else if (obj instanceof booleanWithParameter) {
					booleanWithParameter b = (booleanWithParameter) obj;
					Combo c = (Combo) b.getCombo();
					Text t = (Text) b.getText();

					if (c.getItem(c.getSelectionIndex()).equalsIgnoreCase("Yes")) {
						dataConstraints.add(key, t.getText());
					}
				}
			}
		}
		return dataConstraints;
	}

	public static void loadData(ModelElement elt, Map drawConstraints) {
		for (Object o : elt.getMetainfo()) {
			MetaInfo c = (MetaInfo) o;
			for (Object k : drawConstraints.keySet()) {
				MetaInfo mi = (MetaInfo) k;
				String key = mi.getKey();
				if (key.equals(c.getKey())) {
					if (c.getValue() != null || c.getValueSet() != null) {
						Object comp = drawConstraints.get(mi);
						if (comp instanceof Text) {
							Text t = (Text) comp;
							t.setText(c.getValue().toString());
						} else if (comp instanceof Combo) {
							Combo soc = (Combo) comp;
							if (c.getValue().equals("True")) {
								soc.select(soc.indexOf("Yes"));
							} else if (c.getValue().equals("False")) {
								soc.select(soc.indexOf("No"));
							} else {
								soc.select(soc.indexOf(c.getValue()));
							}
						} else if (comp instanceof booleanWithParameter) {
							booleanWithParameter b = (booleanWithParameter) comp;
							Combo combo = (Combo) b.getCombo();
							Text t = (Text) b.getText();
							combo.select(combo.indexOf("Yes"));
							t.setText(c.getValue());
							t.setEnabled(true);
						}
					}
				}
			}
		}

		for (Object o : elt.getStereotypes()) {
			Stereotype s = (Stereotype) o;
			MetaInfo c = getMetaInfo(s);
			if (c != null) {
				for (Object k : drawConstraints.keySet()) {
					String key = (String) k;
					if (key.equals(c.getKey())) {
						if ((c.getValueType() != null) && (c.getValue() != null)) {
							Object comp = drawConstraints.get(key);
							if (comp instanceof Text) {
								Text t = (Text) comp;
								t.setText(c.getValue().toString());
							} else if (comp instanceof Combo) {
								Combo soc = (Combo) comp;
								if (c.getValueType().equals(boolean.class)) {
									soc.select(soc.indexOf("Yes"));
								} else {
									soc.select(soc.indexOf(c.getValue()));
								}
							}

						}
					}
				}
			}
		}
	}

	private static MetaInfo getMetaInfo(Stereotype s) {
		MetaInfo result = null;
		if (s.getName().contains(":")) {
			int index = s.getName().indexOf(':');
			String key = s.getName().substring(0, index);

			result = (new OblAssociationMetaInfo()).getMetaInfo(key);
			if (result == null) {
				result = (new OblAttributeMetaInfo()).getMetaInfo(key);
			}

			if (result != null) {
				result.setValue(s.getName());
			}
		} else {
			int i = 0;
			String key = s.getName();
			while ((i < key.length()) && !(key.charAt(i) >= 48 && key.charAt(i) <= 57)) {
				i++;
			}
			key = key.substring(0, i);
			result = (new OblAssociationMetaInfo()).getMetaInfo(key);
			if (result == null) {
				result = (new OblAttributeMetaInfo()).getMetaInfo(key);
			}

			if (result != null) {
				result.setValue(s.getName());
			}
		}
		return result;
	}
}
