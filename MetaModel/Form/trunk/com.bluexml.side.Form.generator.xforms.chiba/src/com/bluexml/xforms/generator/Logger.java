package com.bluexml.xforms.generator;

public interface Logger {

	void info(String... logs);

	void error(Throwable t, String... logs);

}
