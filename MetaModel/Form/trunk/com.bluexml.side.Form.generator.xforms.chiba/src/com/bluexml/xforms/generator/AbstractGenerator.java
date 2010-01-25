package com.bluexml.xforms.generator;

import org.apache.commons.logging.Log;

import com.bluexml.side.util.componentmonitor.indy.CoreInterface;

/**
 * The Class AbstractDataGenerator.
 */
public abstract class AbstractGenerator implements GeneratorInterface {

	/** The form generator. */
	protected FormGeneratorsManager formGenerator;
	protected Log genLogger;
	protected boolean readOnlyMode; // #1222
	protected CoreInterface monitor;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.bluexml.xforms.generator.GeneratorInterface#setFormGenerator(com.bluexml.xforms.generator.
	 * FormGenerator)
	 */
	public void setFormGenerator(FormGeneratorsManager formGenerator) {
		this.formGenerator = formGenerator;
	}

	/**
	 * Gets the form generator.
	 * 
	 * @return the form generator
	 */
	public FormGeneratorsManager getFormGenerator() {
		return formGenerator;
	}

	public void setLogger(Log genLogger) {
		this.genLogger = genLogger;
	}
	
	public void setMonitor(CoreInterface monitor) {
		this.monitor = monitor;
	}

	/* (non-Javadoc)
	 * @see com.bluexml.xforms.generator.GeneratorInterface#isReadOnlyMode()
	 */
	public boolean isReadOnlyMode() {
		return false;
	}

	/* (non-Javadoc)
	 * @see com.bluexml.xforms.generator.GeneratorInterface#setReadOnlyMode(boolean)
	 */
	public void setReadOnlyMode(boolean onOff) {
		// nothing to do
	}

	/* (non-Javadoc)
	 * @see com.bluexml.xforms.generator.GeneratorInterface#supportsReadOnlyMode()
	 */
	public boolean supportsReadOnlyMode() {
		return false;
	}
	
}
