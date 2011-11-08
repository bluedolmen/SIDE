package com.bluexml.clazz.generator.alfresco34d.ext.sidelabs;

import java.util.List;


public class AlfrescoGenerator extends com.bluexml.side.clazz.generator.alfresco.ClassAlfrescoGenerator {

	

	/*
	 * (non-Javadoc)
	 * @seecom.bluexml.side.clazz.generator.alfresco.ClassAlfrescoGenerator#
	 * getMainTemplates()
	 */
	@Override
	protected List<String> getMainTemplates() {
		List<String> templates = super.getMainTemplates();
		// let form processor build default alfresco forms
		templates.remove("/com.bluexml.side.Class.generator.alfresco/templates/alfrescoshare/DefaultEditForms/web-framework-config-defaults.mt"); //$NON-NLS-1$

		// define configuration for advancedSearch
		templates.add("/com.bluexml.side.Class.generator.alfresco34d/com/bluexml/side/clazz/generator/alfresco34d/templates/alfrescoshare/custom-share-config.xml.mt"); //$NON-NLS-1$

		// 3.4.d specific
		templates.remove("/com.bluexml.side.Class.generator.alfresco/templates/Model/alfrescoGenerator_model.mt");
		templates.add("/com.bluexml.side.Class.generator.alfresco34d/com/bluexml/side/clazz/generator/alfresco34d/templates/model/alfrescoGenerator_model.mt");

		templates.remove("/com.bluexml.side.Class.generator.alfresco/templates/alfrescoshare/uploadForm/flash-upload.get.html.ftl.mt");
		templates.add("/com.bluexml.side.Class.generator.alfresco34d/com/bluexml/side/clazz/generator/alfresco34d/templates/alfrescoshare/upload/flash-upload.get.html.ftl.mt");

		// i18n messages
		templates.remove("/com.bluexml.side.Class.generator.alfresco/templates/alfrescoshare/defaultdocListView/documentlist.get.properties.mt"); //$NON-NLS-1$
		templates.add("/com.bluexml.side.Class.generator.alfresco34d/com/bluexml/side/clazz/generator/alfresco34d/templates/alfrescoshare/doclist/documentlist.get_de.properties.mt"); //$NON-NLS-1$
		templates.add("/com.bluexml.side.Class.generator.alfresco34d/com/bluexml/side/clazz/generator/alfresco34d/templates/alfrescoshare/doclist/documentlist.get_es.properties.mt"); //$NON-NLS-1$
		templates.add("/com.bluexml.side.Class.generator.alfresco34d/com/bluexml/side/clazz/generator/alfresco34d/templates/alfrescoshare/doclist/documentlist.get_fr.properties.mt"); //$NON-NLS-1$
		templates.add("/com.bluexml.side.Class.generator.alfresco34d/com/bluexml/side/clazz/generator/alfresco34d/templates/alfrescoshare/doclist/documentlist.get_it.properties.mt"); //$NON-NLS-1$
		templates.add("/com.bluexml.side.Class.generator.alfresco34d/com/bluexml/side/clazz/generator/alfresco34d/templates/alfrescoshare/doclist/documentlist.get.properties.mt"); //$NON-NLS-1$
		templates.add("/com.bluexml.side.Class.generator.alfresco34d/com/bluexml/side/clazz/generator/alfresco34d/templates/alfrescoshare/changetype/change-type.get_de.properties.mt");
		templates.add("/com.bluexml.side.Class.generator.alfresco34d/com/bluexml/side/clazz/generator/alfresco34d/templates/alfrescoshare/changetype/change-type.get_es.properties.mt");
		templates.add("/com.bluexml.side.Class.generator.alfresco34d/com/bluexml/side/clazz/generator/alfresco34d/templates/alfrescoshare/changetype/change-type.get_fr.properties.mt");
		templates.add("/com.bluexml.side.Class.generator.alfresco34d/com/bluexml/side/clazz/generator/alfresco34d/templates/alfrescoshare/changetype/change-type.get_it.properties.mt");

		templates.add("/com.bluexml.side.Class.generator.alfresco34d/com/bluexml/side/clazz/generator/alfresco34d/templates/alfrescoshare/messages/slingshot-context.mt");
		templates.add("/com.bluexml.side.Class.generator.alfresco34d/com/bluexml/side/clazz/generator/alfresco34d/templates/alfrescoshare/messages/slingshot.properties.mt");

		return templates;
	}

}
