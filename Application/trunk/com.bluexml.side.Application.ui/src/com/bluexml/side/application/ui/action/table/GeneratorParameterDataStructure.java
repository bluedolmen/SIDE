package com.bluexml.side.application.ui.action.table;

import java.util.ArrayList;
import java.util.List;

public class GeneratorParameterDataStructure {
	
	List<GeneratorParameter> data = new ArrayList<GeneratorParameter>();
	
	public List<GeneratorParameter> getData() {
		return data;
	}

	public void addGeneratorParameter(String p_key, String p_value) {
		GeneratorParameter genParam = new GeneratorParameter(p_key,p_value);
		data.add(genParam);
	}
	
	
}
