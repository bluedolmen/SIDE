package com.bluexml.xforms.generator;

public class DefaultLogger implements Logger {

	private static Logger logger = new DefaultLogger();

	public static Logger getLogger() {
		return logger;
	}

	private DefaultLogger() {
		super();
	}

	public void error(Throwable t, String... logs) {
		t.printStackTrace(System.err);
		System.err.println("-------------- ERROR -------------");
		for (String string : logs) {
			System.err.println(string);
		}
	}

	public void info(String... logs) {
		for (String string : logs) {
			System.out.println(string);
		}
	}

}
