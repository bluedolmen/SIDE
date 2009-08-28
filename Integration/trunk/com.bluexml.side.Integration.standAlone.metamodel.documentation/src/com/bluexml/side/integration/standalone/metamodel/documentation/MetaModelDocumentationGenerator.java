package com.bluexml.side.integration.standalone.metamodel.documentation;

import java.util.List;

import com.bluexml.side.util.generator.documentation.DocumentationGenerator;

/**
 * DocumentationGenrator used for MetaModel Ecore
 *
 */
public class MetaModelDocumentationGenerator extends DocumentationGenerator {


	public MetaModelDocumentationGenerator() {
		MMUri = "http://www.eclipse.org/emf/2002/Ecore"; //$NON-NLS-1$
		templates.add("/com.bluexml.side.Integration.standAlone.metamodel.documentation/src/templates/manifest.mt"); //$NON-NLS-1$
		templates.add("/com.bluexml.side.Integration.standAlone.metamodel.documentation/src/templates/meta.mt"); //$NON-NLS-1$
		templates.add("/com.bluexml.side.Integration.standAlone.metamodel.documentation/src/templates/mimetype.mt"); //$NON-NLS-1$
		templates.add("/com.bluexml.side.Integration.standAlone.metamodel.documentation/src/templates/styles.mt"); //$NON-NLS-1$
		templates.add("/com.bluexml.side.Integration.standAlone.metamodel.documentation/src/templates/settings.mt"); //$NON-NLS-1$
	}

	@Override
	protected List<String> getTemplates() {
		templates.add("/com.bluexml.side.Integration.standAlone.metamodel.documentation/src/templates/content.mt"); //$NON-NLS-1$
		return templates;
	}
	


}
