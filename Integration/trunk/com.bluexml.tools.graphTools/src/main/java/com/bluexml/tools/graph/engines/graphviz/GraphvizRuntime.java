/*
    Copyright (C) 2007-20013  BlueXML - www.bluexml.com

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU Affero General Public License as
    published by the Free Software Foundation, either version 3 of the
    License, or (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU Affero General Public License for more details.

    You should have received a copy of the GNU Affero General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/
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
