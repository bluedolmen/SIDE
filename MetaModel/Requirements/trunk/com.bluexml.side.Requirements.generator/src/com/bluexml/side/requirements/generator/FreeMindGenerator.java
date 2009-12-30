package com.bluexml.side.requirements.generator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.resources.IFile;

public class FreeMindGenerator extends RequirementsGenerator {

	private static String MM_URI = "http://www.kerblue.org/requirements/1.0";
	private static String KEY_LISTOFAGENTS = "com.bluexml.side.Requirements.generator.mindmap.freemind.agentList";
	private static String KEY_LISTOFENTITIES = "com.bluexml.side.Requirements.generator.mindmap.freemind.entityList";
	private static String KEY_LISTOFENTITIESBYAGENT = "com.bluexml.side.Requirements.generator.mindmap.freemind.entityListByAgent";
	private static String KEY_LISTOFGOALS = "com.bluexml.side.Requirements.generator.mindmap.freemind.goalList";
	private static String KEY_LISTOFGOALSBYAGENT = "com.bluexml.side.Requirements.generator.mindmap.freemind.goalListByAgent";
	
	@Override
	protected String getMetamodelURI() {
		return MM_URI;
	}

	@Override
	protected List<String> getTemplates(String keyGenerator) {
		List<String> l = new ArrayList<String>();
		l.add("/"+PLUGIN_ID+"/src/interpretation/mindmap/generation/freemind/freemind.mt");	
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
		result.put("OUT", "MindMap");
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	protected List<String> getANTScripts(String keyGenerator) {
		return Collections.EMPTY_LIST;
	}

	@Override
	protected String getASMFile(String keyGenerator) {
		if (keyGenerator.equals(KEY_LISTOFAGENTS))
			return "src/interpretation/mindmap/transformation/agentList/RWM2AgentList.asm";
		if (keyGenerator.equals(KEY_LISTOFENTITIES))
			return "src/interpretation/mindmap/transformation/entityList/RWM2EntityList.asm";
		if (keyGenerator.equals(KEY_LISTOFGOALS))
			return "src/interpretation/mindmap/transformation/goalList/RWM2GoalList.asm";
		if (keyGenerator.equals(KEY_LISTOFENTITIESBYAGENT))
			return "src/interpretation/mindmap/transformation/entityListByAgent/RWM2EntityListByAgent.asm";
		if (keyGenerator.equals(KEY_LISTOFGOALSBYAGENT))
			return "src/interpretation/mindmap/transformation/goalListByAgent/RWM2GoalListByAgent.asm";
		return "";
	}

	@Override
	protected String getTargetMetamodel(String keyGenerator) {
		return "src/interpretation/mindmap/mindmap.ecore";
	}

	@Override
	protected Set<String> getTransformation() {
		Set<String> l = new HashSet<String>();
		
		if (generatorOptions.keySet().contains(KEY_LISTOFAGENTS))
			l.add(KEY_LISTOFAGENTS);
		if (generatorOptions.keySet().contains(KEY_LISTOFENTITIES))
			l.add(KEY_LISTOFENTITIES);
		if (generatorOptions.keySet().contains(KEY_LISTOFGOALS))
			l.add(KEY_LISTOFGOALS);
		if (generatorOptions.keySet().contains(KEY_LISTOFGOALSBYAGENT))
			l.add(KEY_LISTOFGOALSBYAGENT);
		if (generatorOptions.keySet().contains(KEY_LISTOFENTITIESBYAGENT))
			l.add(KEY_LISTOFENTITIESBYAGENT);
		
		return l;
	}

	@Override
	protected String getTargetModelName(String keyGenerator) {
		if (keyGenerator.equals(KEY_LISTOFAGENTS))
			return "agentList.ecore";
		if (keyGenerator.equals(KEY_LISTOFENTITIES))
			return "entityList.ecore";
		if (keyGenerator.equals(KEY_LISTOFGOALS))
			return "goalList.ecore";
		if (keyGenerator.equals(KEY_LISTOFENTITIESBYAGENT))
			return "entityListByAgent.ecore";
		if (keyGenerator.equals(KEY_LISTOFGOALSBYAGENT))
			return "goalListByAgent.ecore";
		return "";
	}

	protected Collection<String> getExtensionsForServices() {
		return Collections.singleton(".mm");
	}
}
