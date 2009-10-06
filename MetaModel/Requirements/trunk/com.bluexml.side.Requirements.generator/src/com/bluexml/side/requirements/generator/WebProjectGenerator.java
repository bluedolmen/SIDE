package com.bluexml.side.requirements.generator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.resources.IFile;

public class WebProjectGenerator extends RequirementsGenerator {

	private static String MM_URI = "http://www.kerblue.org/requirements/1.0";
	
	@Override
	protected String getMetamodelURI() {
		return MM_URI;
	}

	@Override
	protected List<String> getTemplates(String keyGenerator) {
		List<String> l = new ArrayList<String>();
		l.add("/"+PLUGIN_ID+"/src/interpretation/webproject/generation/data.mt");	
		l.add("/"+PLUGIN_ID+"/src/interpretation/webproject/generation/main_edit.mt");	
		l.add("/"+PLUGIN_ID+"/src/interpretation/webproject/generation/main.mt");	
		return l;
	}

	@Override
	public Collection<IFile> complete() throws Exception {
		//Nothing to do
		return null;
	}
	
	@Override
	protected Map<String, String> getInputModels(String keyGenerator) {
		Map<String, String> result = new HashMap<String, String>();
		result.put("IN", "RWM");
		return result;
	}

	@Override
	protected Map<String, String> getOutputModels(String keyGenerator) {
		Map<String, String> result = new HashMap<String, String>();
		result.put("OUT", "WebProject");
		return result;
	}

	@Override
	protected List<String> getANTScripts(String keyGenerator) {
		return Collections.singletonList("src/interpretation/webproject/generation/build-default/");
	}

	@Override
	protected String getASMFile(String keyGenerator) {
		return "src/interpretation/webproject/transformation/RWM2WebProject.asm";
	}

	@Override
	protected String getTargetMetamodel(String keyGenerator) {
		return "src/interpretation/webproject/webproject.ecore";
	}

	@Override
	protected Set<String> getTransformation() {
		return Collections.singleton("webProject generator");
	}

	@Override
	protected String getTargetModelName(String keyGenerator) {
		return "webproject.ecore";
	}

}
