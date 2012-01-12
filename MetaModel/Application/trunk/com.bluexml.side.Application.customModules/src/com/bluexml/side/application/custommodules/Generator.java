package com.bluexml.side.application.custommodules;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.resources.IFile;

import com.bluexml.side.util.generator.AbstractGenerator;

public class Generator extends AbstractGenerator {

	@Override
	public boolean shouldGenerate(HashMap<String, List<IFile>> modelsInfo, String id_metamodel) {
		return true;
	}

	@Override
	public Collection<IFile> generate(Map<String, List<IFile>> models, String id_metamodel) throws Exception {
		return Collections.emptyList();
	}

	@Override
	public Collection<IFile> complete(Map<String, List<IFile>> models) throws Exception {
		addDependences();
		return Collections.emptyList();
	}

}
