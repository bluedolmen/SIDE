package com.bluexml.side.alfresco.applet;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.io.Reader;

import netscape.javascript.JSObject;


public class Exec extends Thread {
	private String monAppli = null;
	private String monFile = null;
	private String monTmpdir = null;
	private String monExe = null;
	
	public Exec(String appli, String file) {
		monAppli = appli;
		System.out.println(monAppli);
		monFile = file;
		String tmpdir = System.getProperty("java.io.tmpdir");
		tmpdir = tmpdir.replaceAll("/", "\\\\");
		monTmpdir = tmpdir;
		String [] monExeSplited = monAppli.split("\\\\");
		monExe = monExeSplited[monExeSplited.length - 1];
		System.out.println(monExe);
	}
	
	public void run() {
		try {
			boolean exist = false;
	        String line;
	        String[] command = { "tasklist.exe", "/fi", "\"imagename eq " + monExe + "\"" };
			ProcessBuilder tasklist = new ProcessBuilder(command);
	        Process test = tasklist.start();
	        BufferedReader input =
	                new BufferedReader(new InputStreamReader(test.getInputStream()));
	        if ((line = input.readLine()) != null) {
	        	System.out.println("1st line not null");
	            if (line.equals("")) {
	            	//System.out.println("A process has already been started");
	            	//A process has already been started 
	            	exist = true;
	            }
	        }
	        //System.out.println(exist);
	        input.close();
	        String[] command2 = { monAppli, monTmpdir + monFile };
			ProcessBuilder p = new ProcessBuilder(command2);
	        p.redirectErrorStream(true);
	        Process proc = p.start();

	        if (!exist) {
	        	//System.out.println("waitfor");
	        	proc.waitFor();
	        } else {
	        	//System.out.println("watch instance");
	        	watchInstance();
	        }
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void watchInstance() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		String mySavedFileString = "~$" + monFile.substring(2);
		//System.out.println(mySavedFileString);
		File mySavedFile = new File(monTmpdir + mySavedFileString);
		System.out.println(mySavedFile.exists());
		if (!mySavedFile.exists()) {
			mySavedFile = new File(monTmpdir + ".~lock." + monFile + "#");
		}
		System.out.println(mySavedFile.exists());
		while (mySavedFile.exists()) {
			try{
		        Thread.sleep(1000);
			} catch(Exception e) {
				e.printStackTrace();
			}
		}	
	}
}
