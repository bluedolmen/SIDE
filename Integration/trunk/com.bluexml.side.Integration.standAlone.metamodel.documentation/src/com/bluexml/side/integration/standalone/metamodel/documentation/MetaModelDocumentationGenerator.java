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
