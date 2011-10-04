package com.bluexml.side.view.generator.alfresco.extension.sideenterprise;

import java.util.Collection;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;

import com.bluexml.side.clazz.generator.alfresco.enterprise.AlfrescoEnterprisePackager;
import com.bluexml.side.util.generator.alfresco.AlfrescoPackager;
import com.bluexml.side.util.libs.IFileHelper;
import com.bluexml.side.view.generator.alfresco.ViewAlfrescoGenerator;

public class ViewGenerator extends ViewAlfrescoGenerator {

	public final static String OPTION_EXTJS = "view.generator.alfresco.extJS";

	/*
	 * (non-Javadoc)
	 * @seecom.bluexml.side.view.generator.alfresco.ViewAlfrescoGenerator#
	 * getOptionalTemplates()
	 */
	@Override
	protected List<String> getOptionalTemplates() {
		List<String> result = super.getOptionalTemplates();
		// extJS generation
		if (getGeneratorOptionValue(OPTION_EXTJS)) {
			result.add("/com.bluexml.side.View.generator.alfresco.extension.sideEnterprise/com/bluexml/side/view/generator/alfresco/extension/sideenterprise/templates/extJS/template-json-editable-grid-1-js.mt");
			result.add("/com.bluexml.side.View.generator.alfresco.extension.sideEnterprise/com/bluexml/side/view/generator/alfresco/extension/sideenterprise/templates/extJS/template-json-editable-grid-2-html.mt");
			result.add("/com.bluexml.side.View.generator.alfresco.extension.sideEnterprise/com/bluexml/side/view/generator/alfresco/extension/sideenterprise/templates/extJS/template-json-editable-grid-1-html.mt");
			result.add("/com.bluexml.side.View.generator.alfresco.extension.sideEnterprise/com/bluexml/side/view/generator/alfresco/extension/sideenterprise/templates/extJS/template-json-editable-grid-2-js.mt");
			result.add("/com.bluexml.side.View.generator.alfresco.extension.sideEnterprise/com/bluexml/side/view/generator/alfresco/extension/sideenterprise/templates/extJS/template-json-grouping-html.mt");
			result.add("/com.bluexml.side.View.generator.alfresco.extension.sideEnterprise/com/bluexml/side/view/generator/alfresco/extension/sideenterprise/templates/extJS/template-json-grouping-js.mt");
			result.add("/com.bluexml.side.View.generator.alfresco.extension.sideEnterprise/com/bluexml/side/view/generator/alfresco/extension/sideenterprise/templates/extJS/template-json-paging-html.mt");
			result.add("/com.bluexml.side.View.generator.alfresco.extension.sideEnterprise/com/bluexml/side/view/generator/alfresco/extension/sideenterprise/templates/extJS/template-json-paging-js.mt");
			result.add("/com.bluexml.side.View.generator.alfresco.extension.sideEnterprise/com/bluexml/side/view/generator/alfresco/extension/sideenterprise/templates/extJS/template-json-simple-grid-html.mt");
			result.add("/com.bluexml.side.View.generator.alfresco.extension.sideEnterprise/com/bluexml/side/view/generator/alfresco/extension/sideenterprise/templates/extJS/template-json-simple-grid-js.mt");
			result.add("/com.bluexml.side.View.generator.alfresco.extension.sideEnterprise/com/bluexml/side/view/generator/alfresco/extension/sideenterprise/templates/extJS/template-tree-data-json.mt");

			// dojo sample
			result.add("/com.bluexml.side.View.generator.alfresco.extension.sideEnterprise/com/bluexml/side/view/generator/alfresco/extension/sideenterprise/templates/dojo/simple-grid.html.mt");
			result.add("/com.bluexml.side.View.generator.alfresco.extension.sideEnterprise/com/bluexml/side/view/generator/alfresco/extension/sideenterprise/templates/dojo/simple-grid.js.mt");
		}
		return result;
	}

	@Override
	public Collection<IFile> buildPackages(String modelId) throws Exception {
		// package amp, shareZip and eclipse project archive
		IFolder iFolder = IFileHelper.getIFolder(getTemporaryFolder());
		AlfrescoPackager alfrescoPackager = new AlfrescoEnterprisePackager(iFolder, buildModuleProperties(modelId), techVersion);
		Collection<IFile> pkgs = alfrescoPackager.buildPackages().values();
		return pkgs;
	}
}
