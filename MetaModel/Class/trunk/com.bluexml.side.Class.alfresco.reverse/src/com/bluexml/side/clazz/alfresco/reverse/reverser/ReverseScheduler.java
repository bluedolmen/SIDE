package com.bluexml.side.clazz.alfresco.reverse.reverser;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import com.bluexml.side.alfresco.binding.Model;
import com.bluexml.side.alfresco.binding.Model.Imports;
import com.bluexml.side.alfresco.binding.Model.Namespaces;
import com.bluexml.side.alfresco.binding.Model.Imports.Import;
import com.bluexml.side.alfresco.binding.Model.Namespaces.Namespace;
import com.bluexml.side.util.libs.CollectionHelper;
import com.bluexml.side.util.libs.FileHelper;

public class ReverseScheduler {

	Map<Integer, List<Model>> tree = new TreeMap<Integer, List<Model>>();
	CollectionHelper<Integer, Model> colh = new CollectionHelper<Integer, Model>(tree);
	Set<Model> models = new HashSet<Model>();
	Set<Model> scheduled = new HashSet<Model>();

	Map<String, Model> uriToModel = new HashMap<String, Model>();

	/**
	 * @return the tree
	 */
	public Map<Integer, List<Model>> getTree() {
		return tree;
	}

	boolean verbose = false;

	public static void main(String[] args) {
		File alfrescoModelRepository = new File("/Volumes/Data/SVN/side/HEAD/S-IDE/MetaModel/Class/trunk/com.bluexml.side.Class.alfresco.reverse/models");
		ReverseScheduler rev;
		try {
			List<File> listAll = FileHelper.listAll(alfrescoModelRepository);
			rev = new ReverseScheduler(listAll, true);
			rev.schedule();
			// System.out.println(rev.tree);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public ReverseScheduler(Collection<File> listAll, boolean verbose) throws Exception {
		if (verbose)
			System.out.println("ReverseScheduler.ReverseScheduler()");
		for (File file : listAll) {
			if (FileHelper.getFileExt(file).equals("xml")) {
				if (verbose)
					System.out.println("open :" + file.getName());
				JAXBContext jaxbContext = JAXBContext.newInstance("com.bluexml.side.alfresco.binding", ReverseHelper.class.getClassLoader());

				Unmarshaller unm = jaxbContext.createUnmarshaller();
				Object root = unm.unmarshal(file);
				com.bluexml.side.alfresco.binding.Model alfModel = (com.bluexml.side.alfresco.binding.Model) root;
				models.add(alfModel);
				Namespaces namespacesE = alfModel.getNamespaces();
				if (namespacesE != null) {
					List<Namespace> namespaces = namespacesE.getNamespace();
					for (Namespace namespace : namespaces) {
						uriToModel.put(namespace.getUri(), alfModel);
					}
				}
			}
		}
		if (verbose)
			System.out.println("ReverseScheduler.ReverseScheduler() end");

	}

	public void schedule() {
		// initialize
		for (Model model : models) {
			Imports importsE = model.getImports();
			if (importsE != null) {
				List<Import> imports = importsE.getImport();
				if (imports.size() == 0) {
					colh.addToMap(0, model, true);
					scheduled.add(model);
				}
			} else {
				colh.addToMap(0, model, true);
				scheduled.add(model);
			}
		}
		// System.out.println("init tree" + tree);
		// propagate
		Integer level = 1;
		while ((scheduled.size() < models.size())) {
			// System.out.println("Tree at level " + level);
			// System.out.println("while :" + scheduled.size() + " models :" + models.size());

			for (Model m : models) {
				if (!scheduled.contains(m)) {
					readImports(m, level);
				} else {
					// System.out.println("already scheduled");
				}

			}

			level++;
		}

	}

	private void readImports(com.bluexml.side.alfresco.binding.Model alfModel, Integer i) {
		// System.out.println("read tree :" + tree.get(0).size());
		//		displayTree();
		// System.out.println("try to add " + alfModel.getName() + " for level " + i);
		Imports importsE = alfModel.getImports();
		if (importsE != null) {
			List<Import> imports = importsE.getImport();

			List<Model> list = new ArrayList<Model>();
			for (int c = 0; c < i; c++) {
				List<Model> list2 = tree.get(c);
				if (list2 != null) {
					list.addAll(list2);
				}
			}
			// System.out.println("parents :" + displayModels(list));
			List<Model> resolveToModel = resolveToModel(imports);
			// System.out.println("imports :" + displayModels(resolveToModel));

			if (list.containsAll(resolveToModel)) {
				if (verbose)
					System.out.println("model : " + alfModel.getName() + " match for level " + i);
				colh.addToMap(i, alfModel, true);
				scheduled.add(alfModel);
			} else {
				// model do not match with current level
				//				System.err.println("do not match " + alfModel.getName());
			}
		}
		// System.out.println("end read tree :" + tree.get(0).size());
		//		displayTree();
	}

	protected List<Model> resolveToModel(List<Import> imports) {
		List<Model> models = new ArrayList<Model>();
		for (Import import1 : imports) {
			String uri = import1.getUri();
			Model model = uriToModel.get(uri);
			if (model != null) {
				models.add(model);
			} else {
				// missing model
				System.err.println("missing model that define " + import1.getPrefix() + " to " + import1.getUri());
			}
		}
		return models;
	}

	public void displayTree() {
		for (Map.Entry<Integer, List<Model>> ent : tree.entrySet()) {
			System.out.println(ent.getKey());
			System.out.println("->");
			for (Model model : ent.getValue()) {
				System.out.print(model.getName() + ", ");
			}
			System.out.println();
		}

	}

	protected String displayModels(List<Model> ent) {
		String rt = "";
		rt += "[";
		for (Model model : ent) {
			if (model != null) {
				rt += model.getName() + ", ";
			} else {
				rt += null + ", ";
			}

		}
		rt += "]";
		return rt;
	}
}
