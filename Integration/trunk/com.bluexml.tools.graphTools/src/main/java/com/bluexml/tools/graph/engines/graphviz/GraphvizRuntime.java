package com.bluexml.tools.graph.engines.graphviz;

import java.io.File;
import java.io.IOException;

public class GraphvizRuntime {

	File location;

	public GraphvizRuntime(File location) {
		this.location = location;
	}

	public void render(File dot, File output) {
		Runtime rt = Runtime.getRuntime();

		// patch by Mike Chenault
		String[] args = { location.getAbsolutePath(), "-Tjpg", dot.getAbsolutePath(), "-o", output.getAbsolutePath() };
		Process p;
		try {
			p = rt.exec(args);
			p.waitFor();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
