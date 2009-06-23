package com.bluexml.side.clazz.generator.alfrescoshare;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import org.eclipse.core.resources.IFile;

import com.bluexml.side.application.generator.acceleo.AbstractAcceleoGenerator;
import com.bluexml.side.util.libs.IFileHelper;

public class ClassAlfrescoShareGenerator extends AbstractAcceleoGenerator {
	public static String GENERATOR_OPTIONS_DEFAULTFORMS = "class.alfrescoShare.defaultForms";
	public static final String MMUri = "http://www.kerblue.org/class/1.0";
	protected static final String versionProperty ="com.bluexml.side.Class.generator.alfrescoShare.module.version";
	@Override
	protected String getMetamodelURI() {
		return MMUri;
	}

	@Override
	protected List<String> getTemplates() {
		List<String> result = new ArrayList<String>();

		// extension to manage custom types
		result.add("/com.bluexml.side.Class.generator.alfrescoShare/com/bluexml/side/clazz/generator/alfrescoshare/templates/uploadForm/file-upload-response-get-patch.mt");
		result.add("/com.bluexml.side.Class.generator.alfrescoShare/com/bluexml/side/clazz/generator/alfrescoshare/templates/uploadForm/flash-upload-js-get-patch.mt");
		result.add("/com.bluexml.side.Class.generator.alfrescoShare/com/bluexml/side/clazz/generator/alfrescoshare/templates/uploadForm/html-upload-js-get-patch.mt");
		result.add("/com.bluexml.side.Class.generator.alfrescoShare/com/bluexml/side/clazz/generator/alfrescoshare/templates/defaultdocListView/documentlist.get.properties.mt");

		if (getGeneratorOptionValue(GENERATOR_OPTIONS_DEFAULTFORMS)) {
			// default Forms for custom types
			result.add("/com.bluexml.side.Class.generator.alfrescoShare/com/bluexml/side/clazz/generator/alfrescoshare/templates/DefaultEditForms/web-framework-config-custom.mt");
		}
		return result;
	}

	

	public boolean check() {
		return true;
	}

	@Override
	public IFile buildPackage(String modelId) throws Exception {
		Packager p = new Packager(IFileHelper.getIFolder(getTemporaryFolder()), buildModuleProperties(modelId), techVersion);
		return p.buildPackage(generatedFiles, doClean());
	}

	private Properties buildModuleProperties(String modelId) {		
		Date now = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
		Properties props = new Properties();
		props.put("module.id", "SIDE_AlfrescoShareExtension_" + modelId);
		props.put("module.version", getVersioNumber());
		props.put("module.title", "S-IDE Alfresco Share extension");
		props.put("module.description", "this module contains S-IDE generated extension to extends Alfresco Share,\n build at " + sdf.format(now));
		return props;
	}

}
