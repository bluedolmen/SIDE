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
package com.bluexml.side.form.generator.alfresco34d;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import org.eclipse.emf.common.util.EList;

import com.bluexml.side.clazz.service.alfresco.CommonServices;
import com.bluexml.side.common.MetaInfo;
import com.bluexml.side.common.NamedModelElement;
import com.bluexml.side.util.generator.alfresco.AbstractAlfrescoGenerator;

public class FormGenerator extends AbstractAlfrescoGenerator {
	public static String MMUri = "http://www.kerblue.org/form/1.0";

	public FormGenerator() {
		versionProperty = "com.bluexml.side.Form.generator.alfresco34d.module.version";
	}

	@Override
	public Properties buildModuleProperties(String modelId) {
		Date now = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
		Properties props = new Properties();
		props.put("module.id", "SIDE_FormExtension_" + modelId);
		props.put("module.version", getVersioNumber());
		props.put("module.title", "SIDE Form extension");
		props.put("module.description", "this module contains SIDE generated extension to extends Alfresco Forms,\n build at " + sdf.format(now));
		return props;
	}

	@Override
	protected String getMetamodelURI() {
		return MMUri;
	}

	@Override
	protected List<String> getMainTemplates() {
		List<String> templates = new ArrayList<String>();
		templates.add("/com.bluexml.side.Form.generator.alfresco34d/com/bluexml/side/form/generator/alfresco34d/templates/fdk-context.xml.mt");
		templates.add("/com.bluexml.side.Form.generator.alfresco34d/com/bluexml/side/form/generator/alfresco34d/templates/formGenerator.mt");
		templates.add("/com.bluexml.side.Form.generator.alfresco34d/com/bluexml/side/form/generator/alfresco34d/templates/messages.mt");
		templates.add("/com.bluexml.side.Form.generator.alfresco34d/com/bluexml/side/form/generator/alfresco34d/templates/messages_fr.mt");
		return templates;
	}

	// acceleo services
	public String getModuleIdService(NamedModelElement element) throws Exception {
		String modelId = getModelName(element);
		return buildModuleProperties(modelId).getProperty("module.id"); //$NON-NLS-1$
	}

	public String getModelName(NamedModelElement element) {
		com.bluexml.side.common.Package root = (com.bluexml.side.common.Package) CommonServices.getRootContainer(element);
		String modelId = root.getName();
		EList<MetaInfo> metainfo = root.getMetainfo();
		for (MetaInfo metaInfo2 : metainfo) {
			if (metaInfo2.getKey().equals("forcedModelName")) {
				modelId = metaInfo2.getValue();
				break;
			}
		}
		return modelId;
	}

}
