package com.bluexml.side.clazz.alfresco.reverse.reverser;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.eclipse.core.resources.IFile;
import org.eclipse.emf.ecore.EObject;

import com.bluexml.side.alfresco.binding.Class;
import com.bluexml.side.alfresco.binding.Model;
import com.bluexml.side.util.libs.ecore.EResourceUtils;

public class Reverser {

	public static void executeReverse(Collection<File> alfrescoModels, File sideModelRepo, List<IFile> sideModels, boolean verbose) throws Exception {
		ReverseScheduler rs = new ReverseScheduler(alfrescoModels, verbose);
		rs.schedule();
		Map<Integer, List<Model>> tree = rs.getTree();
		// reverse according to scheduled order (import dependencies tree)
		ReverseModel rm = new ReverseModel(verbose);
		// load and register EObject from existing SIDE models
		rm.loadSIDEModels(sideModels);
		// execute reverse
		for (Map.Entry<Integer, List<Model>> ent : tree.entrySet()) {
			for (Model model : ent.getValue()) {
				EObject sideO = rm.reverse(model);
				File file = new File(sideModelRepo, ReverseHelper.extractLocalNameFromAlfQName(model.getName()) + ".dt");
				file.getParentFile().mkdirs();
				file.createNewFile();
				System.out.println("save model :" + file);
				EResourceUtils.saveModel(file, sideO);
			}
		}

		List<Object> notReverted = rm.getNotReverted();

		if (notReverted.size() > 0) {
			List<String> errorRepport = new ArrayList<String>();
			for (Object object : notReverted) {
				Class c = (Class) object;
				errorRepport.add(c.getName());
			}

			System.err.println("Fail to reverse Overrides for :" + errorRepport);
		}
	}
}
