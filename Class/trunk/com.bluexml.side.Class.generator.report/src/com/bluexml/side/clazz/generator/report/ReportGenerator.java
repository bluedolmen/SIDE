package com.bluexml.side.clazz.generator.report;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.core.resources.IFile;

import com.bluexml.side.application.generator.acceleo.AbstractAcceleoGenerator;

public class ReportGenerator extends AbstractAcceleoGenerator {
	
	public static String GENERATOR_OPTIONS_BIRT = "report.birt";
	public static String GENERATOR_CONFIGURATION_PARAMETER_AUTHOR = "report.author";
	public static String MMUri = "http://www.kerblue.org/class/1.0";


	@Override
	protected String getMetamodelURI() {
		return MMUri;
	}

	@Override
	protected List<String> getTemplates() {
		List<String> birtTemplates = new ArrayList<String>();
		
		birtTemplates.add("/com.bluexml.side.Class.generator.report/src/com/bluexml/side/clazz/generator/report/templates/Webscript/alfrescoGenerator_template_def_get.mt");
		birtTemplates.add("/com.bluexml.side.Class.generator.report/src/com/bluexml/side/clazz/generator/report/templates/Webscript/alfrescoGenerator_template_def_post.mt");
		birtTemplates.add("/com.bluexml.side.Class.generator.report/src/com/bluexml/side/clazz/generator/report/templates/Webscript/alfrescoGenerator_template_js_get.mt");
		birtTemplates.add("/com.bluexml.side.Class.generator.report/src/com/bluexml/side/clazz/generator/report/templates/Webscript/alfrescoGenerator_template_js_post.mt");
			
		birtTemplates.add("/com.bluexml.side.Class.generator.report/src/com/bluexml/side/clazz/generator/report/templates/Webscript/html/alfrescoGenerator_template_html.mt");
		birtTemplates.add("/com.bluexml.side.Class.generator.report/src/com/bluexml/side/clazz/generator/report/templates/Webscript/html/alfrescoGenerator_template_result_post.mt");
		birtTemplates.add("/com.bluexml.side.Class.generator.report/src/com/bluexml/side/clazz/generator/report/templates/Webscript/html/alfrescoGenerator_template_result_get.mt");
			
		birtTemplates.add("/com.bluexml.side.Class.generator.report/src/com/bluexml/side/clazz/generator/report/templates/Webscript/json/alfrescoGenerator_template_json.mt");
		birtTemplates.add("/com.bluexml.side.Class.generator.report/src/com/bluexml/side/clazz/generator/report/templates/Webscript/json/alfrescoGenerator_template_result_post.mt");
		birtTemplates.add("/com.bluexml.side.Class.generator.report/src/com/bluexml/side/clazz/generator/report/templates/Webscript/json/alfrescoGenerator_template_result_get.mt");
			
		birtTemplates.add("/com.bluexml.side.Class.generator.report/src/com/bluexml/side/clazz/generator/report/templates/Webscript/rss/alfrescoGenerator_template_rss.mt");
		birtTemplates.add("/com.bluexml.side.Class.generator.report/src/com/bluexml/side/clazz/generator/report/templates/Webscript/rss/alfrescoGenerator_template_result_post.mt");
		birtTemplates.add("/com.bluexml.side.Class.generator.report/src/com/bluexml/side/clazz/generator/report/templates/Webscript/rss/alfrescoGenerator_template_result_get.mt");
			
		birtTemplates.add("/com.bluexml.side.Class.generator.report/src/com/bluexml/side/clazz/generator/report/templates/Webscript/xml/alfrescoGenerator_template_xml.mt");
		birtTemplates.add("/com.bluexml.side.Class.generator.report/src/com/bluexml/side/clazz/generator/report/templates/Webscript/xml/alfrescoGenerator_template_result_post.mt");
		birtTemplates.add("/com.bluexml.side.Class.generator.report/src/com/bluexml/side/clazz/generator/report/templates/Webscript/xml/alfrescoGenerator_template_result_get.mt");
		
		if (getGeneratorOptionValue(GENERATOR_OPTIONS_BIRT)) {
			birtTemplates.add("/com.bluexml.side.Class.generator.report/src/com/bluexml/side/clazz/generator/report/birt/templates/alfrescoGenerator_birt.mt");
			
			birtTemplates.add("/com.bluexml.side.Class.generator.report/src/com/bluexml/side/clazz/generator/report/birt/templates/alfrescoGeneretor_birt_all_in_one.mt");
		}
			
		return birtTemplates;
	}

	
	public Collection<IFile> complete() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	
	public boolean check() {
		return true;
	}
	
	/**
	 * This method return the author of the generation
	 * 
	 * @return the author of the generation
	 */
	public static String getAuthor(){
		
		String auteur = "John Doe";
		if(!"".equals(getGenerationParameter(GENERATOR_CONFIGURATION_PARAMETER_AUTHOR))){
			auteur = getGenerationParameter(GENERATOR_CONFIGURATION_PARAMETER_AUTHOR);
		}
		return auteur;
	}

}
