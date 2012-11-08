package com.bluexml.side.alfresco.applet;

import java.io.InputStreamReader;
import java.io.Reader;


public class Exec extends Thread {
	private String monAppli = null;
	private String monFile = null;
	public Exec(String appli, String file) {
		monAppli = appli;
		monFile = file;
	}
	public void run() {
		try {
			String tmpdir = System.getProperty("java.io.tmpdir");
			tmpdir = tmpdir.replaceAll("/", "\\\\");
			ProcessBuilder p = new ProcessBuilder(monAppli, tmpdir + monFile);
	        p.redirectErrorStream(true);
	        Process proc = p.start();

	        Reader reader = new InputStreamReader(proc.getInputStream());
	        int ch;
	        while ((ch = reader.read()) != -1)
	            System.out.print((char) ch);
	        reader.close();
	        // Wait to be sure process ended
	        proc.waitFor();
	        

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
