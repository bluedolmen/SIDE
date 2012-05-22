package com.bluexml.side.clazz.generator.alfresco40d.ext.enterprise;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bluexml.side.clazz.generator.alfresco346e.AlfrescoGenerator346e;

public class AlfClassGenerator40d extends AlfrescoGenerator346e {
	protected final static String templateBase40d = com.bluexml.side.clazz.generator.alfresco40d.Activator.templateBase40d;

	@Override
	protected List<String> getMainTemplates() {
		List<String> templates = super.getMainTemplates();

		templates.add(templateBase40d + doclist + "/documentlist.get_nl" + i18nFileExt); //$NON-NLS-1$
		templates.add(templateBase40d + "/alfrescoshare/changetype/change-type.get_nl" + i18nFileExt); //$NON-NLS-1$

		return templates;
	}

	@Override
	protected List<Map<String, String>> getTemplatesSubstitution() {
		List<Map<String, String>> templatesSubstitution = super.getTemplatesSubstitution();
		Map<String, String> map = new HashMap<String, String>();

		// replace doclib i18n message
		String string = doclist + "/documentlist.get_de" + i18nFileExt;
		map.put(templateBase346e + string, templateBase40d + string); //$NON-NLS-1$
		string = doclist + "/documentlist.get_en" + i18nFileExt;
		map.put(templateBase346e + string, templateBase40d + string); //$NON-NLS-1$
		string = doclist + "/documentlist.get_es" + i18nFileExt;
		map.put(templateBase346e + string, templateBase40d + string); //$NON-NLS-1$
		string = doclist + "/documentlist.get_fr" + i18nFileExt;
		map.put(templateBase346e + string, templateBase40d + string); //$NON-NLS-1$
		string = doclist + "/documentlist.get_it" + i18nFileExt;
		map.put(templateBase346e + string, templateBase40d + string); //$NON-NLS-1$
		string = doclist + "/documentlist.get_ja" + i18nFileExt;
		map.put(templateBase346e + string, templateBase40d + string); //$NON-NLS-1$
		
		
		// remove
		map.put(templateBase346e + doclist + "/repo-documentlist.get_de" + i18nFileExt, null); //$NON-NLS-1$
		map.put(templateBase346e + doclist + "/repo-documentlist.get_en" + i18nFileExt, null); //$NON-NLS-1$
		map.put(templateBase346e + doclist + "/repo-documentlist.get_es" + i18nFileExt, null); //$NON-NLS-1$
		map.put(templateBase346e + doclist + "/repo-documentlist.get_fr" + i18nFileExt, null); //$NON-NLS-1$
		map.put(templateBase346e + doclist + "/repo-documentlist.get_it" + i18nFileExt, null); //$NON-NLS-1$
		map.put(templateBase346e + doclist + "/repo-documentlist.get_ja" + i18nFileExt, null); //$NON-NLS-1$

		templatesSubstitution.add(map);
		return templatesSubstitution;
	}

}
