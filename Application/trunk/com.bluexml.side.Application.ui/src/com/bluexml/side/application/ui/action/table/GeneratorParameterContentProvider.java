package com.bluexml.side.application.ui.action.table;

import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.Viewer;

public class GeneratorParameterContentProvider implements IStructuredContentProvider {

	private GeneratorParameterDataStructure dataStructure;

	public GeneratorParameterContentProvider(
			GeneratorParameterDataStructure p_dataStructure) {
		dataStructure = p_dataStructure;
	}

	public Object[] getElements(Object inputElement) {
		if (dataStructure != null && dataStructure.getData() != null) {
			return dataStructure.getData().toArray();
		} else {
			return null;
		}
			
	}

	public void setDataStructure(GeneratorParameterDataStructure dataStructure) {
		this.dataStructure = dataStructure;
	}

	public void dispose() {
		//nothing to do
	}

	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		//nothing to do
	}

}
