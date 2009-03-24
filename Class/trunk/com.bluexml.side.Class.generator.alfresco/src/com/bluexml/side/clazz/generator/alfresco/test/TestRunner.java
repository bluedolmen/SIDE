package com.bluexml.side.clazz.generator.alfresco.test;

import java.util.Collection;
import java.util.Map;

import org.eclipse.core.resources.IFile;

import com.bluexml.side.clazz.generator.alfresco.ClassAlfrescoGenerator;

public class TestRunner {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub

		ClassAlfrescoGenerator cag = new ClassAlfrescoGenerator();
		String[] generationParameters=null;
		String[] generatorOptions=null;
		Map<String,String> configurationParameters=null;
		//cag.initialize(generationParameters, generatorOptions, configurationParameters);
		IFile model = ClassAlfrescoGenerator.getIFile("/com.bluexml.side.Class.generator.alfresco/modelTest/");
		Collection<String> r = cag.generate(model);
	}

}
