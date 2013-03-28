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
package com.bluexml.side.integration.standalone.metamodel.documentation;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.eclipse.core.resources.IFile;

import com.bluexml.side.util.generator.documentation.DocumentationGenerator;
import com.bluexml.side.util.generator.documentation.services.DocumentationServices;

/**
 * DocumentationGenrator used for MetaModel Ecore
 * 
 */
public class MetaModelDocumentationGenerator extends DocumentationGenerator {

	public MetaModelDocumentationGenerator() {
		DocumentationServices.clearAll();

		MMUri = "http://www.eclipse.org/emf/2002/Ecore"; //$NON-NLS-1$
		setTEMP_FOLDER("test");

	}

	@Override
	protected List<String> getMainTemplates() {
		List<String> templates = new ArrayList<String>();
		templates.add("/com.bluexml.side.Integration.standAlone.metamodel.documentation/templates/content.mt"); //$NON-NLS-1$
		templates.add("/com.bluexml.side.Integration.standAlone.metamodel.documentation/templates/manifest.mt"); //$NON-NLS-1$
		templates.add("/com.bluexml.side.Integration.standAlone.metamodel.documentation/templates/meta.mt"); //$NON-NLS-1$
		templates.add("/com.bluexml.side.Integration.standAlone.metamodel.documentation/templates/mimetype.mt"); //$NON-NLS-1$
		//templates.add("/com.bluexml.side.Integration.standAlone.metamodel.documentation/src/templates/model2docBook.mt"); //$NON-NLS-1$
		templates.add("/com.bluexml.side.Integration.standAlone.metamodel.documentation/templates/settings.mt"); //$NON-NLS-1$
		templates.add("/com.bluexml.side.Integration.standAlone.metamodel.documentation/templates/styles.mt"); //$NON-NLS-1$
		return templates;
	}

	@Override
	public Collection<IFile> complete(Map<String, List<IFile>> models) throws Exception {
		return generatedFiles;
	}

}
