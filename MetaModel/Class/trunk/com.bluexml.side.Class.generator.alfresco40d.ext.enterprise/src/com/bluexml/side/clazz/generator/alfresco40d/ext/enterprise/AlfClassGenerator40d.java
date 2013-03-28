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

		templates.add(templateBase40d + doclist + "documentlist.get_nl" + i18nFileExt); //$NON-NLS-1$
		templates.add(templateBase40d + "alfrescoshare/changetype/change-type.get_nl" + i18nFileExt); //$NON-NLS-1$
		templates.add(templateBase40d + doclist + "portal-evaluators-context.mt"); //$NON-NLS-1$
		return templates;
	}

	@Override
	protected List<Map<String, String>> getTemplatesSubstitution() {
		List<Map<String, String>> templatesSubstitution = super.getTemplatesSubstitution();
		Map<String, String> map = new HashMap<String, String>();

		// replace doclib i18n message
		String string = doclist + "documentlist.get_de" + i18nFileExt;
		map.put(templateBase346e + string, templateBase40d + string); //$NON-NLS-1$
		string = doclist + "documentlist.get_en" + i18nFileExt;
		map.put(templateBase346e + string, templateBase40d + string); //$NON-NLS-1$
		string = doclist + "documentlist.get_es" + i18nFileExt;
		map.put(templateBase346e + string, templateBase40d + string); //$NON-NLS-1$
		string = doclist + "documentlist.get_fr" + i18nFileExt;
		map.put(templateBase346e + string, templateBase40d + string); //$NON-NLS-1$
		string = doclist + "documentlist.get_it" + i18nFileExt;
		map.put(templateBase346e + string, templateBase40d + string); //$NON-NLS-1$
		string = doclist + "documentlist.get_ja" + i18nFileExt;
		map.put(templateBase346e + string, templateBase40d + string); //$NON-NLS-1$
		string = doclist + "documentlist.get" + i18nFileExt;
		map.put(templateBase346e + string, templateBase40d + string); //$NON-NLS-1$

		string = "alfrescoGenerator_model.mt";
		map.put(templateBase34d + "model/" + string, templateBase40d + "Model/" + string); //$NON-NLS-1$

		// remove
		map.put(templateBase346e + doclist + "repo-documentlist.get_de" + i18nFileExt, null); //$NON-NLS-1$
		map.put(templateBase346e + doclist + "repo-documentlist.get_en" + i18nFileExt, null); //$NON-NLS-1$
		map.put(templateBase346e + doclist + "repo-documentlist.get_es" + i18nFileExt, null); //$NON-NLS-1$
		map.put(templateBase346e + doclist + "repo-documentlist.get_fr" + i18nFileExt, null); //$NON-NLS-1$
		map.put(templateBase346e + doclist + "repo-documentlist.get_it" + i18nFileExt, null); //$NON-NLS-1$
		map.put(templateBase346e + doclist + "repo-documentlist.get_ja" + i18nFileExt, null); //$NON-NLS-1$

		templatesSubstitution.add(map);
		return templatesSubstitution;
	}
}
