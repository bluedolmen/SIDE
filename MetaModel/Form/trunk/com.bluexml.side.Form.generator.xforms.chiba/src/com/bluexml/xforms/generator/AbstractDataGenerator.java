package com.bluexml.xforms.generator;

import org.apache.commons.logging.Log;

import com.bluexml.side.util.componentmonitor.ComponentMonitor;

/**
 * The Class AbstractDataGenerator.
 */
public abstract class AbstractDataGenerator implements DataGenerator {

	/** The form generator. */
	protected FormGenerator formGenerator;
	protected Log genLogger;
	protected ComponentMonitor monitor;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.bluexml.xforms.generator.DataGenerator#setFormGenerator(com.bluexml.xforms.generator.
	 * FormGenerator)
	 */
	public void setFormGenerator(FormGenerator formGenerator) {
		this.formGenerator = formGenerator;
	}

	/**
	 * Gets the form generator.
	 * 
	 * @return the form generator
	 */
	public FormGenerator getFormGenerator() {
		return formGenerator;
	}

	public void setLogger(Log genLogger) {
		this.genLogger = genLogger;
	}
	
	public void setMonitor(ComponentMonitor monitor) {
		this.monitor = monitor;
	}
	

}
