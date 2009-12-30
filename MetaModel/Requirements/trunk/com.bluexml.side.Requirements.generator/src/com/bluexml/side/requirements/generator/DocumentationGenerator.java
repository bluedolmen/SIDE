package com.bluexml.side.requirements.generator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.resources.IFile;

public class DocumentationGenerator extends RequirementsGenerator {

	private static String MM_URI = "http://www.kerblue.org/requirements/1.0";
	
	@Override
	protected String getMetamodelURI() {
		return MM_URI;
	}

	@Override
	protected List<String> getTemplates(String keyGenerator) {
		List<String> l = new ArrayList<String>();
		l.add("/"+PLUGIN_ID+"/src/interpretation/documentation/generation/opendocument/content_chapter.mt");	
		l.add("/"+PLUGIN_ID+"/src/interpretation/documentation/generation/opendocument/content.mt");	
		l.add("/"+PLUGIN_ID+"/src/interpretation/documentation/generation/opendocument/manifest.mt");	
		l.add("/"+PLUGIN_ID+"/src/interpretation/documentation/generation/opendocument/meta.mt");	
		l.add("/"+PLUGIN_ID+"/src/interpretation/documentation/generation/opendocument/mimetype.mt");	
		l.add("/"+PLUGIN_ID+"/src/interpretation/documentation/generation/opendocument/styles.mt");	
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
		result.put("OUT", "Documentation");
		return result;
	}

	@Override
	protected List<String> getANTScripts(String keyGenerator) {
		return Collections.singletonList("src/interpretation/documentation/generation/opendocument/build-default/");
	}

	@Override
	protected String getASMFile(String keyGenerator) {
		return "src/interpretation/documentation/transformation/RWM2CdCF.asm";
	}

	@Override
	protected String getTargetMetamodel(String keyGenerator) {
		return "src/interpretation/documentation/documentation.ecore";
	}

	@Override
	protected Set<String> getTransformation() {
		return Collections.singleton("CdCF generator");
	}

	@Override
	protected String getTargetModelName(String keyGenerator) {
		return "cdcf.ecore";
	}
	
	protected Collection<String> getExtensionsForServices() {
		return Collections.singleton(".odt");
	}
}
