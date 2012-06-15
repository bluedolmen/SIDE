package com.bluexml.side.dinamex.applet;

public class Exec extends Thread {
	private String monAppli = null;
	private String monFile = null;
	public Exec(String appli, String file) {
		monAppli = appli;
		monFile = file;
	}
	public void run() {
		try {
			Process p = new ProcessBuilder(monAppli, "C:\\Users\\Public\\" + monFile).start();
			p.waitFor();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
