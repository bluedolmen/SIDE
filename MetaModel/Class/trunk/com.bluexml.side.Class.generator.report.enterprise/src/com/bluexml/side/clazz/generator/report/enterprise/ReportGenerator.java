package com.bluexml.side.clazz.generator.report.enterprise;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.eclipse.core.resources.IFile;
import org.eclipse.emf.ecore.EObject;

import com.bluexml.side.util.security.SecurityHelper;
import com.bluexml.side.util.security.preferences.SidePreferences;

public class ReportGenerator extends com.bluexml.side.clazz.generator.report.ReportGenerator {

	public static String GENERATOR_CODE = "CODE_GED_G_C_REPORT"; //$NON-NLS-1$


	public boolean check() {
		return SecurityHelper.check(GENERATOR_CODE, SidePreferences.getKey());
	}

	public String getRunasforReport(EObject o) {
		String result = getGenerationParameter(GENERATOR_PARAMETER_WEBSCRIPT_REPORT_RUNAS);
		if (result == null || result.equals("")) {
			result = "admin";
			System.out.println(GENERATOR_PARAMETER_WEBSCRIPT_REPORT_RUNAS + ", default value used :" + result);
		}
		return result;
	}

	@Override
	protected String getPackageName(String modelId) {
		return "SIDE_Enterprise_Birt_" + modelId;
	}
	
	
	@Override
	public Collection<IFile> generate(Map<String, List<IFile>> modelsInfo, String idMetamodel) throws Exception {
		if (!check()) {
			throw new Exception("Bad, please to enter your licence number in preferencies page");
		}
		return super.generate(modelsInfo, idMetamodel);
	}
}
