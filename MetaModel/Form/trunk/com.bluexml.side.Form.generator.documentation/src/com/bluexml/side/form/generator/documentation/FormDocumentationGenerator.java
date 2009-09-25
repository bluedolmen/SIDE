package com.bluexml.side.form.generator.documentation;

import java.util.Collection;
import java.util.List;

import org.eclipse.core.resources.IFile;

import com.bluexml.side.form.editor.views.service.OutlineViewService;
import com.bluexml.side.util.generator.documentation.DocumentationGenerator;
import com.bluexml.side.util.generator.documentation.services.DocumentationServices;

public class FormDocumentationGenerator extends DocumentationGenerator {

	public FormDocumentationGenerator() {
		MMUri = "http://www.kerblue.org/form/1.0"; //$NON-NLS-1$
	}

	@Override
	protected List<String> getTemplates() {
		templates.add("/com.bluexml.side.Form.generator.documentation/src/templates/content.mt"); //$NON-NLS-1$
		return templates;
	}

	public Collection<IFile> generate(IFile model) throws Exception {
		OutlineViewService.setDoAll(true);
		//TODO : lancer la génération de la vue outline (avec le doAll à true)
		// placer le fichier html ainsi généré dans un répertoire fixé (ex : doc/html)
		// appeller l'ajout du chemin relatif
		// faire en sorte que (comme pour les diagrammes) on face le lien dans le fichier odt vers ce fichier
		//DocumentationServices.addOutlineRelativePath();
		OutlineViewService.setDoAll(false);
		return super.generate(model);
	}
}
