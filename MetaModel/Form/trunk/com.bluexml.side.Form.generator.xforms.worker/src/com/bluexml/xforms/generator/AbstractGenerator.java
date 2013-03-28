/*
    Copyright (C) 2007-20013  BlueXML - www.bluexml.com

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU Affero General Public License as
    published by the Free Software Foundation, either version 3 of the
    License, or (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU Affero General Public License for more details.

    You should have received a copy of the GNU Affero General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/
package com.bluexml.xforms.generator;

import com.bluexml.side.util.componentmonitor.indy.CoreInterface;

/**
 * The Class AbstractDataGenerator.
 */
public abstract class AbstractGenerator implements GeneratorInterface {

	/** The form generator. */
	protected FormGeneratorsManager formGenerator;
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
