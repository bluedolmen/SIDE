package com.bluexml.side.build.tools;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.bluexml.side.build.tools.componants.Componant;
import com.bluexml.side.build.tools.componants.Product;
import com.bluexml.side.build.tools.graph.DisplayGraph;
import com.bluexml.side.build.tools.graph.JungConverter;
import com.bluexml.side.build.tools.reader.ComponantsRegisters;
import com.bluexml.side.build.tools.reader.ProductReader;
import com.bluexml.side.build.tools.renderer.DotRenderer;

import edu.uci.ics.jung.graph.Graph;

public class DependencyTree {

	File product;
	List<File> repo;

	public DependencyTree(File productFile, List<File> repo) {
		product = productFile;
		this.repo = repo;
	}

	public static void main(String[] args) {

		if (args.length < 2) {
			System.out.println("Usage : <.product> <projects repository>");
			System.out.println("");
			System.exit(-1);
		} else {
			if (args[0].equals("-graphmlFile")) {
				File file = new File(args[1]);

				Graph<Componant, String> g;
				try {
					g = JungConverter.convert(file);
					JungConverter.saveGraph(g, file);
					DisplayGraph.display(g);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			} else {

				// 
				String product = args[0];
				File productFile = new File(product);
				if (!productFile.exists()) {
					System.err.println("please check .product location");
				}

				List<File> repos = new ArrayList<File>();
				for (int c = 1; c < args.length; c++) {

					File repo = new File(args[c]);
					repos.add(repo);
				}

				DependencyTree dt = new DependencyTree(productFile, repos);
				try {
					dt.doIt();

				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

	}

	public void doIt() throws Exception {
		// create component registers

		// create ProductReader
		ProductReader pr = new ProductReader(product, new ComponantsRegisters(repo));
		// read product and all associated resources
		Product p = pr.read();
		ComponantsRegisters compReg = pr.getUtil();
		// print out
		compReg.print();

		// render as .dot file
		FileWriter fw = new FileWriter(new File("graph.dot"));
		Map<Componant, Set<Componant>> tree = compReg.getTree();
		DotRenderer dotRenderer = new DotRenderer(fw, tree);
		dotRenderer.render();
		fw.flush();
		fw.close();

		// jung rendering
		Graph<Componant, String> g = JungConverter.convert(tree);
		JungConverter.saveGraph(g, new File("graph.xml"));

		DisplayGraph.display(g);

	}

}
