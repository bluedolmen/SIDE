package com.bluexml.britair.dinamex.applet;

import java.applet.Applet;
import java.awt.Graphics;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.SocketPermission;
import java.net.URL;
import java.net.URLConnection;

import netscape.javascript.JSException;
import netscape.javascript.JSObject;


public class EditingDocument extends Applet {

	private static final long serialVersionUID = 1L;
	String text = "debug: ";
	File myFile = null;
	URL url = null;
	Exec monAppli = null;
	String fileName = null;
	long lastModified = 0;
	private JSObject jso;

	public void init() {
		try {
			try {
				jso = JSObject.getWindow(this);
			} catch (JSException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			url = new URL(getParameter("webdavUrl"));
			URLConnection uc = url.openConnection();
			fileName = getFileName(getParameter("webdavUrl"));
			uc.setUseCaches(false);
			uc.setDoInput(true);
			uc.setDoOutput(true);
			uc.addRequestProperty("ticket", getParameter("ticket"));
			uc.connect();

			InputStream input = uc.getInputStream();
			myFile = new File("C:/Documents and Settings/Default User/Local Settings/Temp/tmp.doc");
			FileOutputStream fos = new FileOutputStream(myFile);
			byte[] buffer = new byte[1024];
			int count = 0;
			while ((count = input.read(buffer)) > 0) {
				fos.write(buffer, 0, count);
			}
			input.close();
			fos.close();

			Thread.sleep(10000);
			String[] command = { "cmd", "/c", "copy /y C:\\Users\\Public\\tmp.doc" + " " + "C:\\Users\\Public\\" + fileName };
			ProcessBuilder copyFiles = new ProcessBuilder(command);
			copyFiles.redirectErrorStream(true);
			Process p = copyFiles.start();
			
			BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
			String line;
			do {
			    line = reader.readLine();
			    if (line != null) { System.out.println(line); }
			} while (line != null);
			reader.close();

			
			p.waitFor();
			//Runtime runtime = Runtime.getRuntime();
			//Process p = runtime.exec("cmd /c start " + "copy" + " "
			//		+ "C:\\Users\\Public\\tmp.doc" + " " + "C:\\Users\\Public\\" + fileName);
			//Thread.sleep(5000);
			myFile.delete();
			myFile = new File("C:/Users/Public/" + fileName);
			if (!getParameter("mode").equals("write")) {
				myFile.setReadOnly();
			}
			if (getParameter("mime").equals("application/vnd.ms-powerpoint")) {
				monAppli = new Exec("powerpnt.exe", fileName);
				monAppli.start();
			} else if (getParameter("mime").equals("application/vnd.ms-excel")) {
				monAppli = new Exec("excel.exe", fileName);
				monAppli.start();
			} else if (getParameter("mime").equals("application/msword")) {
				monAppli = new Exec("winword.exe", fileName);
				monAppli.start();
			}
			Thread.sleep(1000);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void start() {
		lastModified = myFile.lastModified();
		while (true) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if (lastModified < myFile.lastModified()) {
				lastModified = myFile.lastModified();
				save();
			}
			if (!monAppli.isAlive()) {
				myFile.setWritable(true);
				try {
					jso.call("Close", new String[1]);
				} catch (Exception e) {
					text += e.getMessage();
					repaint();
				}
			}
		}
	}

	public String convertStreamToString(java.io.InputStream is) {
		try {
			return new java.util.Scanner(is).useDelimiter("\\A").next();
		} catch (java.util.NoSuchElementException e) {
			return "";
		}
	}

	public void save() {
		try {
			HttpURLConnection huc = (HttpURLConnection) url.openConnection();
			// text += " huc=" + huc.toString() + "\n";
			huc.setUseCaches(false);
			huc.setDoOutput(true);
			huc.setRequestMethod("PUT");
			huc.setRequestProperty("Content-type", "application/binary");
			huc.addRequestProperty("ticket", getParameter("ticket"));
			huc.connect();
			DataOutputStream os = new DataOutputStream(huc.getOutputStream());
			// text += " os=" + os.toString() + "\n";
			FileInputStream fis = new FileInputStream(myFile);
			byte[] buffer = new byte[1024];
			int count = 0;
			while ((count = fis.read(buffer)) > 0) {
				os.write(buffer, 0, count);
			}
			fis.close();
			os.close();
			os.flush();
			huc.disconnect();

			InputStream inputStream;
			int responseCode = huc.getResponseCode();
			if ((responseCode >= 200) && (responseCode <= 202)) {
				inputStream = huc.getInputStream();
				// text += convertStreamToString(inputStream);
				int j;
				while ((j = inputStream.read()) > 0) {
					System.out.println(j);
				}

			} else {
				inputStream = huc.getErrorStream();
				// text += convertStreamToString(inputStream);
			}
			huc.disconnect();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private String getFileName(String url) {
		String[] urlSansTicket = url.split("\\?");
		String[] urlDecompose = urlSansTicket[0].split("/");
		return urlDecompose[urlDecompose.length - 1];
	}

	public void paint(Graphics g) {
		//update(g);
	}

	public void update(Graphics g) {
		 g.drawString(text, 5, 50);
	}
}
