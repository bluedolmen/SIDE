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
package com.bluexml.side.clazz.generator.report.enterprise;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.eclipse.core.resources.IFile;
import org.eclipse.emf.ecore.EObject;


public class ReportGenerator extends com.bluexml.side.clazz.generator.report.ReportGenerator {

	public static String GENERATOR_CODE = "CODE_GED_G_C_REPORT"; //$NON-NLS-1$


	public boolean check() {
		return true;
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
