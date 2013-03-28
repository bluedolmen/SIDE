package com.bluexml.tools.miscellaneous;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.commons.io.FileUtils;

public class FixJSFileToCompare {

	public static void main(String[] args) {
		try {
			String string = args[0];
			File f = new File(string);
			if (f.isFile()) {

				normalizeFile(f);

			} else {
				Collection<?> listFiles = FileUtils.listFiles(f, null, true);
				for (Object object : listFiles) {
					normalizeFile((File) object);
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static void normalizeFile(File f) throws IOException, InterruptedException {
		File destFile = new File(f.getParent(), "copy-" + f.getName());
		FileUtils.copyFile(f, destFile);
		f = destFile;

		// normalize comments
		List<String> output = new ArrayList<String>();

		boolean previousIsComment = false;
		boolean previousIsJsdoc = false;

		List readLines = FileUtils.readLines(f);

		for (Object object : readLines) {
			String l = (String) object;
			if (isComment(l)) {

				if (previousIsComment) {
					//						System.out.println("FixJSFileToCompare.normalizeFile() previus is comment");
					String concat = output.get(output.size() - 1).concat(l.replaceAll("[\\s]*//", " "));
					output.set(output.size() - 1, concat);
				} else {
					output.add(l);
				}

				previousIsComment = true;
			} else if (isJsDoc(l)) {
				if (previousIsJsdoc) {
					System.out.println("FixJSFileToCompare.normalizeFile() previus is jsdoc");
					String concat = output.get(output.size() - 1).concat(l.replaceAll("[\\s]*\\*[^/]", " ")).replaceAll("[ ]+", " ");
					output.set(output.size() - 1, concat);

				} else {
					output.add(l);
				}

				previousIsJsdoc = true;
			} else {
				previousIsComment = false;

				previousIsJsdoc = false;
				output.add(l);
			}
		}

		//			FileUtils.writeLines(f, output);
		String data = "";
		boolean previusIsComment = false;
		for (String string : output) {
			boolean comment = isComment(string);
			if (comment && !previousIsComment) {
				data += "\n";
				previousIsComment = true;
			} else if (comment && previousIsComment) {
				previousIsComment = true;
			} else if (!comment) {
				if (previousIsComment) {
					data += "\n";
				}
				previousIsComment = false;
			}
			data += string;
		}
		FileUtils.writeStringToFile(f, data);
		PrintStream out = System.out;
		for (String string : output) {
			out.println(string);
		}
		Process exec = Runtime.getRuntime().exec("/bin/sh /Users/davidabad/scripts/jsbeautify.sh " + f);
		InputStream inputStream = exec.getInputStream();
		copy(inputStream, out);
		copy(exec.getErrorStream(), out);
		exec.waitFor();

	}

	private static boolean isComment(String l) {
		return l.matches("^[\\s]*//.*");
	}

	private static boolean isJsDoc(String l) {
		return l.matches("^[\\s]*/\\*\\*.*") || l.matches("^[\\s]*\\*.*");
	}

	private static final int BUFFER_SIZE = 8192;

	public static long copy(InputStream is, OutputStream os) {
		byte[] buf = new byte[BUFFER_SIZE];
		long total = 0;
		int len = 0;
		try {
			while (-1 != (len = is.read(buf))) {
				os.write(buf, 0, len);
				total += len;
			}
		} catch (IOException ioe) {
			throw new RuntimeException("error reading stream", ioe);
		}
		return total;
	}
}
