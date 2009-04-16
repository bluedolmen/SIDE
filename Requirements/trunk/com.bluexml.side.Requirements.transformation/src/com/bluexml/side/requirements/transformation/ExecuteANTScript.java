package com.bluexml.side.requirements.transformation;

import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.eclipse.ant.core.AntRunner;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.osgi.framework.Bundle;

public class ExecuteANTScript {

	private Set<String> scripts = new HashSet<String>();
	private String contributor;
	
	public void execute(IFolder outputFolder) throws Exception {
		Set<IFile> antScripts = new HashSet<IFile>();
		
		for (String script : scripts) {
			
			IFolder buildFolder = outputFolder.getFolder(".build");
			if (buildFolder.exists())
				buildFolder.delete(true, new NullProgressMonitor());
			if (!buildFolder.exists())
				buildFolder.create(true, true, new NullProgressMonitor());
			
			Bundle b = Platform.getBundle(contributor);
			Enumeration<?> files = b.findEntries(script, "*", true);
			while (files.hasMoreElements()) {
				URL o = (URL) files.nextElement();
				File f = new File(FileLocator.toFileURL(o).toURI());
				
				if (f.isDirectory()) {
					String dir = o.getPath().substring(script.length());
					IFolder sub = buildFolder.getFolder(dir);
					if (!sub.exists())
						sub.create(true, true, new NullProgressMonitor());
				} else {
					String file = o.getPath().substring(script.length());
					IFile fi = buildFolder.getFile(file);
					if (!fi.exists()) {
						InputStream stream = FileLocator.openStream(b, new Path(o.getFile()), false);
						fi.create(stream, true, new NullProgressMonitor());
					}
					if (fi.getName().equalsIgnoreCase("build.xml"))
						antScripts.add(fi);
				}
			}
			
			AntRunner runner = new AntRunner();
			Map<String,String> properties = new HashMap<String, String>();
			properties.put("generated_dir", outputFolder.getLocation().toString());
			runner.addUserProperties(properties);
			for (IFile f : antScripts) {
				runner.setBuildFileLocation(f.getRawLocation().toFile().getAbsolutePath());
				runner.run();
			};
		}
		
		//Delete build dir
		IFolder buildFolder = outputFolder.getFolder(".build");
		if (buildFolder.exists())
			buildFolder.delete(true, new NullProgressMonitor());
	}

	public void addScript(String script) {
		scripts.add(script);
	}
	
	public void setContributor(String _contributor) {
		contributor = _contributor;
	}

}
