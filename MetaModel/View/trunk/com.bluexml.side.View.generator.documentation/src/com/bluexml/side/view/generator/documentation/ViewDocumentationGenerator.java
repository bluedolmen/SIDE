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
package com.bluexml.side.view.generator.documentation;

import java.util.List;

import com.bluexml.side.util.generator.documentation.DocumentationGenerator;

public class ViewDocumentationGenerator extends DocumentationGenerator {

	public ViewDocumentationGenerator() {
		MMUri = "http://www.kerblue.org/view/1.0"; //$NON-NLS-1$
	}

	@Override
	protected List<String> getMainTemplates() {
		List<String> templates = super.getMainTemplates();
		templates.add("/com.bluexml.side.View.generator.documentation/templates/content.mt"); //$NON-NLS-1$
		return templates;
	}
}
