package com.bluexml.side.alfresco.applet;

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
			Process p = new ProcessBuilder(monAppli, tmpdir + monFile).start();
			p.waitFor();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
