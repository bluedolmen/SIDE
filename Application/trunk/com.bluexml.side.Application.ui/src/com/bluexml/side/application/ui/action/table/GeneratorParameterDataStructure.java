package com.bluexml.side.application.ui.action.table;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.IConfigurationElement;

public class GeneratorParameterDataStructure {
	
	List<GeneratorParameter> data = new ArrayList<GeneratorParameter>();

	public List<GeneratorParameter> getData() {
		return data;
	}

	public void addGeneratorParameter(String p_key, String p_label, String p_value, String p_documentation) {
		GeneratorParameter genParam = new GeneratorParameter(p_key,p_label,p_value, p_documentation);
		data.add(genParam);
	}
	
	
	public void addGeneratorParameter(IConfigurationElement elt) {
		GeneratorParameter genParam = new GeneratorParameter(elt.getAttribute("key"),elt.getAttribute("label"),"",elt.getAttribute("documentation"));
		data.add(genParam);
	}
	
	
	public String getLabel(Object element) {
		String result = "";
		if (element instanceof GeneratorParameter) {
			GeneratorParameter genParam = (GeneratorParameter) element;
			result = genParam.getKey();
		}
		return result;
	}

	public String getValue(Object element) {
		String result = "";
		if (element instanceof GeneratorParameter) {
			GeneratorParameter genParam = (GeneratorParameter) element;
			result = genParam.getValue();
		}
		return result;
	}

	public void setLabel(Object element, String value) {
		if (element instanceof GeneratorParameter) {
			GeneratorParameter genParam = (GeneratorParameter) element;
			genParam.setKey(value);
		}
	}

	public void setValue(Object element, String value) {
		if (element instanceof GeneratorParameter) {
			GeneratorParameter genParam = (GeneratorParameter) element;
			genParam.setValue(value);
		}
	}

	/**
	 * Return the generatorParameter matching the given key
	 * @param key
	 * @return
	 */
	public GeneratorParameter getParamMatching(String key) {
		GeneratorParameter result = null; 
		int i = 0;
		int size = data.size();
		while (i < size && result == null){
			if (data.get(i).getKey().equals(key)) {
				result = data.get(i);
			}
			i++;
		}
		return result; 
	}

	
}
