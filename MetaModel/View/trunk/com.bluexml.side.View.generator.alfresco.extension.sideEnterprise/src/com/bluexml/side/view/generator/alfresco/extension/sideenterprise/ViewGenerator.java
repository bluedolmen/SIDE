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
