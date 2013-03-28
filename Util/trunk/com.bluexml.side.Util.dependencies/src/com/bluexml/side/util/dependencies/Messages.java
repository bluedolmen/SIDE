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
package com.bluexml.side.util.dependencies;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
	private static final String BUNDLE_NAME = "com.bluexml.side.util.dependencies.messages"; //$NON-NLS-1$
	public static String DependenciesDeployer_13;
	public static String DependenciesDeployer_14;
	public static String MavenTmpProject_18;
	public static String MavenUtil_17;
	public static String MavenUtil_23;
	public static String WorkbenchPreferencePage1_3;
	public static String WorkbenchPreferencePage1_4;
	public static String WorkbenchPreferencePage1_5;
	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}

	private Messages() {
	}
}
