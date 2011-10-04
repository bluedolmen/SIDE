package com.bluexml.side.view.generator.alfresco.enterprise;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.eclipse.core.resources.IFile;

import com.bluexml.side.util.security.SecurityHelper;
import com.bluexml.side.util.security.preferences.SidePreferences;
import com.bluexml.side.view.generator.alfresco.extension.sideenterprise.ViewGenerator;

public class ViewAlfrescoEntGenerator extends ViewGenerator {
	public static String GENERATOR_CODE = "CODE_GED_G_V_ALFRESCO_3";

	@Override
	public boolean check() {
		return SecurityHelper.check(GENERATOR_CODE, SidePreferences.getKey());
	}

	@Override
	public Collection<IFile> generate(Map<String, List<IFile>> modelsInfo, String idMetamodel) throws Exception {
		if (!check()) {
			throw new Exception("Bad, please to enter your licence number in preferencies page");
		}
		return super.generate(modelsInfo, idMetamodel);
	}
}
