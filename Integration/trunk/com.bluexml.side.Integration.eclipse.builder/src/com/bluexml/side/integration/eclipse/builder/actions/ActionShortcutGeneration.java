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
package com.bluexml.side.integration.eclipse.builder.actions;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;

import com.bluexml.side.application.ui.menu.DynamicMenuAction;
import com.bluexml.side.integration.eclipse.builder.settings.SIDEBuilderConfiguration;

public class ActionShortcutGeneration extends DynamicMenuAction {

	/*
	 * (non-Javadoc)
	 * @see
	 * com.bluexml.side.application.ui.menu.DynamicMenuAction#setApplication()
	 */
	@Override
	protected IFile setApplication() throws FileNotFoundException, IOException {
		final IProject project = (IProject) selection.getFirstElement();
		SIDEBuilderConfiguration sideconf = new SIDEBuilderConfiguration(project);
		if (sideconf.load()) {
			application= sideconf.getApplication();
			return sideconf.getApplicationFile();
		}
		return null;
	}

}
