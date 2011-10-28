package com.bluexml.side.view.generator.alfresco34d;

import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;

import com.bluexml.side.util.security.SecurityHelper;
import com.bluexml.side.util.security.preferences.SidePreferences;

public class ViewGenerator extends com.bluexml.side.view.generator.alfresco.extension.sideenterprise.ViewGenerator {

	public final static String OPTION_datagrid = "view.generator.share.datagrid";

	@Override
	public String getComponentKey() {
		return "CODE_GED_G_V_ALFRESCO_34D";
	}

	@Override
	public boolean check() {
		return SecurityHelper.check(getComponentKey(), SidePreferences.getKey());
	}

	@Override
	protected List<String> getOptionalTemplates() {
		List<String> optionalTemplates = super.getOptionalTemplates();
		if (getGeneratorOptionValue(OPTION_datagrid)) {
			optionalTemplates.add("/com.bluexml.side.View.generator.alfresco34d/com/bluexml/side/view/generator/alfresco34d/templates/datagrids/context.xml.mt");
			optionalTemplates.add("/com.bluexml.side.View.generator.alfresco34d/com/bluexml/side/view/generator/alfresco34d/templates/datagrids/formGenerator.mt");			
		}
		return optionalTemplates;
	}

	/*
	 * (non-Javadoc)
	 * @seecom.bluexml.side.view.generator.alfresco.ViewAlfrescoGenerator#
	 * getTemplatesSubstitution()
	 */
	@Override
	protected Map<String, String> getTemplatesSubstitution() {
		Map<String, String> templatesSubstitution = super.getTemplatesSubstitution();
		templatesSubstitution.put("/com.bluexml.side.View.generator.alfresco/com/bluexml/side/view/generator/alfresco/templates/doclistView/doclist_ftl.mt", "/com.bluexml.side.View.generator.alfresco34d/com/bluexml/side/view/generator/alfresco34d/templates/doclist_ftl.mt");
		return templatesSubstitution;
	}

	// acceleo services
	public String getModuleIdService(EObject ob, String modelId) throws Exception {
		return buildModuleProperties(modelId).getProperty("module.id"); //$NON-NLS-1$
	}
}
