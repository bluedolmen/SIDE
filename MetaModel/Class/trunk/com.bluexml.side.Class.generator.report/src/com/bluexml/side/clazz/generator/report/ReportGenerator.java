package com.bluexml.side.clazz.generator.report;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.core.resources.IFile;

import com.bluexml.side.util.generator.acceleo.AbstractAcceleoGenerator;

public class ReportGenerator extends AbstractAcceleoGenerator {
	
	public static String GENERATOR_OPTIONS_BIRT = "report.birt";
	public static String GENERATOR_CONFIGURATION_PARAMETER_AUTHOR = "report.author";
	public static String MMUri = "http://www.kerblue.org/class/1.0";


	public ReportGenerator(){
		techVersion = "Birt_2.3.x";
		this.setTEMP_FOLDER(getTechVersion());
	}
	
	@Override
	protected String getMetamodelURI() {
		return MMUri;
	}

	@Override
	protected List<String> getTemplates() {
		List<String> birtTemplates = new ArrayList<String>();
		
		if (getGeneratorOptionValue(GENERATOR_OPTIONS_BIRT)) {
			birtTemplates.add("/com.bluexml.side.Class.generator.report/src/com/bluexml/side/clazz/generator/report/birt/templates/alfrescoGeneretor_birt_all_in_one.mt");
		}
			
		return birtTemplates;
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

	public Collection<IFile> complete() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<IFile> buildPackages(String modelId) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
