package com.bluexml.side.clazz.alfresco.reverse.reverser;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.eclipse.core.resources.IFile;
import org.eclipse.emf.ecore.EObject;

import com.bluexml.side.Util.ecore.ModelInitializationUtils;
import com.bluexml.side.alfresco.binding.Class;
import com.bluexml.side.alfresco.binding.Model;

public class Reverser {

	public static void executeReverse(Collection<File> alfrescoModels, File sideModelRepo, List<IFile> sideModels) throws Exception {
		ReverseScheduler rs = new ReverseScheduler(alfrescoModels);
		rs.schedule();
		Map<Integer, List<Model>> tree = rs.getTree();
		// reverse according to scheduled order (import dependencies tree)
		ReverseModel rm = new ReverseModel();
		// load and register EObject from existing SIDE models
		rm.loadSIDEModels(sideModels);
		for (Map.Entry<Integer, List<Model>> ent : tree.entrySet()) {
			for (Model model : ent.getValue()) {
				EObject sideO = rm.reverse(model);
				File file = new File(sideModelRepo, ReverseHelper.extractLocalNameFromAlfQName(model.getName()) + ".dt");
				file.getParentFile().mkdirs();
				file.createNewFile();
				ModelInitializationUtils.saveModel(file, sideO);
				//				System.out.println("Reverse ok for " + model.getName());
				//				System.out.println("Register x :");
				//				rm.getRegister().printX();
			}
		}

		List<Object> notReverted = rm.getNotReverted();

		if (notReverted.size() > 0) {
			List<String> errorRepport = new ArrayList<String>();
			for (Object object : notReverted) {
				Class c = (Class) object;
				errorRepport.add(c.getName());
			}
			//			throw new Exception("Fail to reverse Overrides for :" + errorRepport);
			System.err.println("Fail to reverse Overrides for :" + errorRepport);
		}
		System.out.println("EObjects types :" + rm.getRegister().getTypes());
	}
}
