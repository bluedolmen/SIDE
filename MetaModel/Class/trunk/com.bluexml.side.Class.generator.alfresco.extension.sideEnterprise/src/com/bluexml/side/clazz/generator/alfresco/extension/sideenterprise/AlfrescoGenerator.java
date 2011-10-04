package com.bluexml.side.clazz.generator.alfresco.extension.sideenterprise;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;

import com.bluexml.side.clazz.generator.alfresco.api.Generator;
import com.bluexml.side.clazz.generator.alfresco.enterprise.AlfrescoEnterprisePackager;
import com.bluexml.side.util.generator.AbstractGenerator;
import com.bluexml.side.util.generator.alfresco.AlfrescoPackager;
import com.bluexml.side.util.libs.IFileHelper;
import com.bluexml.side.util.security.SecurityHelper;
import com.bluexml.side.util.security.preferences.SidePreferences;

public class AlfrescoGenerator extends Generator {

	@Override
	public boolean checkOption(String optionID) {
		String key = "CODE_OPT_" + optionID;
		// System.out.println("AlfrescoGenerator.checkOption() code :"+key);
		Boolean check = SecurityHelper.check(key, SidePreferences.getKey());
		if (check == null) {
			// key not in code list, so free to use
			check = true;
		}
		return check;
	}

	@Override
	public Collection<IFile> generate(Map<String, List<IFile>> modelsInfo, String idMetamodel) throws Exception {
		if (!check()) {
			throw new Exception(com.bluexml.side.application.ui.Activator.Messages.getString("Generate.44", this.id));
		}
		Map<String, Boolean> opts = AbstractGenerator.generatorOptions;
		for (Map.Entry<String, Boolean> opt : opts.entrySet()) {
			if (opt.getValue()) {
				String key = opt.getKey();
				key = getId() + "_" + key;
				if (!checkOption(key)) {
					throw new Exception(com.bluexml.side.application.ui.Activator.Messages.getString("Generate.45", key));
				}
			}
		}
		return super.generate(modelsInfo, idMetamodel);
	}

	/*
	 * (non-Javadoc)
	 * @seecom.bluexml.side.clazz.generator.alfresco.ClassAlfrescoGenerator#
	 * getOptionalTemplates()
	 */
	@Override
	protected List<String> getOptionalTemplates() {
		List<String> result = super.getOptionalTemplates();
		result.addAll(getEnterpriseTemplates());
		return result;
	}

	protected List<String> getEnterpriseTemplates() {
		List<String> result = new ArrayList<String>();
		if (getGeneratorOptionValue(ALFRESCO_WEBSERVICES_CLIENT_API) || getGeneratorOptionValue(ALFRESCO_EMBEDDED_API)) {
			result.addAll(getAlfrescoAPIObjectModel());
		}
		if (getGeneratorOptionValue(ALFRESCO_WEBSERVICES_CLIENT_API)) {
			result.addAll(getAlfrescoAPITemplates());
		}
		if (getGeneratorOptionValue(ALFRESCO_EMBEDDED_API)) {
			result.addAll(getAlfrescoAPIEmbedded());
		}
		if (getGeneratorOptionValue(ALFRESCO_EXTJS_API)) {
			result.addAll(getAlfrescoExtJSTemplates());
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
