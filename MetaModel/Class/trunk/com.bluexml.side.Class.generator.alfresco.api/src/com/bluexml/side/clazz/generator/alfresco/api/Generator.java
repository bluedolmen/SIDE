package com.bluexml.side.clazz.generator.alfresco.api;

import java.util.ArrayList;
import java.util.List;

import com.bluexml.side.clazz.generator.alfresco.ClassAlfrescoGenerator;

public abstract class Generator extends ClassAlfrescoGenerator {
	protected static final String ALFRESCO_WEBSERVICES_CLIENT_API = "com.bluexml.side.Class.generator.alfresco.enterprise.webserviceJavaAPI";
	protected static final String ALFRESCO_EMBEDDED_API = "com.bluexml.side.Class.generator.alfresco.enterprise.embeddedJavaAPI";
	protected static final String ALFRESCO_EXTJS_API = "com.bluexml.side.Class.generator.alfresco.enterprise.extJS";

	public List<String> getAlfrescoAPIObjectModel() {
		List<String> result = new ArrayList<String>();

		// model
		result.add("/com.bluexml.side.Class.generator.alfresco.api/com/bluexml/side/clazz/generator/alfresco/api/templates/javaAPI/model/modelQNames.java.mt");
		result.add("/com.bluexml.side.Class.generator.alfresco.api/com/bluexml/side/clazz/generator/alfresco/api/templates/javaAPI/model/modelJavaObjectModel.mt");
		result.add("/com.bluexml.side.Class.generator.alfresco.api/com/bluexml/side/clazz/generator/alfresco/api/templates/javaAPI/model/modelJavaObjectModelEnum.mt");

		// eclipse project
		result.add("/com.bluexml.side.Class.generator.alfresco.api/com/bluexml/side/clazz/generator/alfresco/api/templates/javaAPI/model/eclipseproject.mt");
		result.add("/com.bluexml.side.Class.generator.alfresco.api/com/bluexml/side/clazz/generator/alfresco/api/templates/javaAPI/model/eclipseClassPath.mt");
		result.add("/com.bluexml.side.Class.generator.alfresco.api/com/bluexml/side/clazz/generator/alfresco/api/templates/javaAPI/model/readmeModel.mt");

		// maven2 project
		result.add("/com.bluexml.side.Class.generator.alfresco.api/com/bluexml/side/clazz/generator/alfresco/api/templates/javaAPI/model/pom.xml.mt");
		return result;
	}

	public List<String> getAlfrescoAPIEmbedded() {
		List<String> result = new ArrayList<String>();

		// Eclipse project files		
		result.add("/com.bluexml.side.Class.generator.alfresco.api/com/bluexml/side/clazz/generator/alfresco/api/templates/javaAPI/embedded/eclipseEmbeddedClassPath.mt");
		result.add("/com.bluexml.side.Class.generator.alfresco.api/com/bluexml/side/clazz/generator/alfresco/api/templates/javaAPI/embedded/eclipseprojectEmbeddedAPI.mt");
		result.add("/com.bluexml.side.Class.generator.alfresco.api/com/bluexml/side/clazz/generator/alfresco/api/templates/javaAPI/embedded/readme.mt");

		// Maven amp packaging project		
		result.add("/com.bluexml.side.Class.generator.alfresco.api/com/bluexml/side/clazz/generator/alfresco/api/templates/javaAPI/embedded/module.properties.mt");
		result.add("/com.bluexml.side.Class.generator.alfresco.api/com/bluexml/side/clazz/generator/alfresco/api/templates/javaAPI/embedded/pom.xml.mt");

		// Embedded API		
		result.add("/com.bluexml.side.Class.generator.alfresco.api/com/bluexml/side/clazz/generator/alfresco/api/templates/javaAPI/embedded/AbstractAspectFactory.java.mt");
		result.add("/com.bluexml.side.Class.generator.alfresco.api/com/bluexml/side/clazz/generator/alfresco/api/templates/javaAPI/embedded/AbstractClassFactory.java.mt");
		result.add("/com.bluexml.side.Class.generator.alfresco.api/com/bluexml/side/clazz/generator/alfresco/api/templates/javaAPI/embedded/FactoryRegistry.mt");
		result.add("/com.bluexml.side.Class.generator.alfresco.api/com/bluexml/side/clazz/generator/alfresco/api/templates/javaAPI/embedded/javaAPI_Aspect.mt");
		result.add("/com.bluexml.side.Class.generator.alfresco.api/com/bluexml/side/clazz/generator/alfresco/api/templates/javaAPI/embedded/javaAPI_Class.mt");
		result.add("/com.bluexml.side.Class.generator.alfresco.api/com/bluexml/side/clazz/generator/alfresco/api/templates/javaAPI/embedded/javaAPI.mt");

		// Webscript extension API		
		result.add("/com.bluexml.side.Class.generator.alfresco.api/com/bluexml/side/clazz/generator/alfresco/api/templates/javaAPI/embedded/webscript/AbstractFactoryAdpater.mt");
		result.add("/com.bluexml.side.Class.generator.alfresco.api/com/bluexml/side/clazz/generator/alfresco/api/templates/javaAPI/embedded/webscript/FactoryAdapterRegistry.mt");
		result.add("/com.bluexml.side.Class.generator.alfresco.api/com/bluexml/side/clazz/generator/alfresco/api/templates/javaAPI/embedded/webscript/webScriptAPI_Aspect.mt");
		result.add("/com.bluexml.side.Class.generator.alfresco.api/com/bluexml/side/clazz/generator/alfresco/api/templates/javaAPI/embedded/webscript/webScriptAPI_Class.mt");

		// Spring configuration May be splited to separate webscript extension from core beans		
		result.add("/com.bluexml.side.Class.generator.alfresco.api/com/bluexml/side/clazz/generator/alfresco/api/templates/javaAPI/embedded/webscript/alfrescoModuleContext.mt");

		// Webscripts CRUD example (used by junit)		
		result.add("/com.bluexml.side.Class.generator.alfresco.api/com/bluexml/side/clazz/generator/alfresco/api/templates/javaAPI/embedded/webscript/example/Aspect-model-frais-crud.get.desc.xml.mt");
		result.add("/com.bluexml.side.Class.generator.alfresco.api/com/bluexml/side/clazz/generator/alfresco/api/templates/javaAPI/embedded/webscript/example/Aspect-model-frais-crud.get.js.mt");
		result.add("/com.bluexml.side.Class.generator.alfresco.api/com/bluexml/side/clazz/generator/alfresco/api/templates/javaAPI/embedded/webscript/example/model-frais-crud.get.desc.xml.mt");
		result.add("/com.bluexml.side.Class.generator.alfresco.api/com/bluexml/side/clazz/generator/alfresco/api/templates/javaAPI/embedded/webscript/example/model-frais-crud.get.js.mt");
		result.add("/com.bluexml.side.Class.generator.alfresco.api/com/bluexml/side/clazz/generator/alfresco/api/templates/javaAPI/embedded/webscript/example/model-frais-crud.get.json.ftl.mt");

		// Junit		
		result.add("/com.bluexml.side.Class.generator.alfresco.api/com/bluexml/side/clazz/generator/alfresco/api/templates/javaAPI/embedded/webscript/JunitTestCase.mt");
		result.add("/com.bluexml.side.Class.generator.alfresco.api/com/bluexml/side/clazz/generator/alfresco/api/templates/javaAPI/embedded/webscript/JunitTestCaseAspects.mt");
		result.add("/com.bluexml.side.Class.generator.alfresco.api/com/bluexml/side/clazz/generator/alfresco/api/templates/javaAPI/embedded/webscript/JunitTestSuite.mt");

		return result;
	}

	public List<String> getAlfrescoAPITemplates() {
		List<String> result = new ArrayList<String>();

		// webService API
		result.add("/com.bluexml.side.Class.generator.alfresco.api/com/bluexml/side/clazz/generator/alfresco/api/templates/javaAPI/webservice/AbstractAspectFactory.java.mt");
		result.add("/com.bluexml.side.Class.generator.alfresco.api/com/bluexml/side/clazz/generator/alfresco/api/templates/javaAPI/webservice/AbstractClassFactory.java.mt");
		result.add("/com.bluexml.side.Class.generator.alfresco.api/com/bluexml/side/clazz/generator/alfresco/api/templates/javaAPI/webservice/javaAPI_Aspect.mt");
		result.add("/com.bluexml.side.Class.generator.alfresco.api/com/bluexml/side/clazz/generator/alfresco/api/templates/javaAPI/webservice/javaAPI_Class.mt");

		// eclipse project
		result.add("/com.bluexml.side.Class.generator.alfresco.api/com/bluexml/side/clazz/generator/alfresco/api/templates/javaAPI/webservice/eclipseprojectWebServicesAPI.mt");
		result.add("/com.bluexml.side.Class.generator.alfresco.api/com/bluexml/side/clazz/generator/alfresco/api/templates/javaAPI/webservice/eclipseWebServicesClassPath.mt");
		result.add("/com.bluexml.side.Class.generator.alfresco.api/com/bluexml/side/clazz/generator/alfresco/api/templates/javaAPI/webservice/readmeWebservice.mt");

		// maven2 project
		result.add("/com.bluexml.side.Class.generator.alfresco.api/com/bluexml/side/clazz/generator/alfresco/api/templates/javaAPI/webservice/pom.xml.mt");

		// testJunit
		result.add("/com.bluexml.side.Class.generator.alfresco.api/com/bluexml/side/clazz/generator/alfresco/api/templates/javaAPI/webservice/JunitTestCase.mt");
		result.add("/com.bluexml.side.Class.generator.alfresco.api/com/bluexml/side/clazz/generator/alfresco/api/templates/javaAPI/webservice/JunitTestCaseAspects.mt");
		result.add("/com.bluexml.side.Class.generator.alfresco.api/com/bluexml/side/clazz/generator/alfresco/api/templates/javaAPI/webservice/JunitTestSuite.mt");

		return result;
	}

	public List<String> getAlfrescoExtJSTemplates() {
		List<String> result = new ArrayList<String>();
		result.add("/com.bluexml.side.Class.generator.alfresco.api/com/bluexml/side/clazz/generator/alfresco/api/templates/extJS/template-tree-data-json.mt");
		
		result.add("/com.bluexml.side.Class.generator.alfresco.api/com/bluexml/side/clazz/generator/alfresco/api/templates/extJS/dataModel/template-json-editable-grid-1-html.mt");
		result.add("/com.bluexml.side.Class.generator.alfresco.api/com/bluexml/side/clazz/generator/alfresco/api/templates/extJS/dataModel/template-json-editable-grid-1-js.mt");
		result.add("/com.bluexml.side.Class.generator.alfresco.api/com/bluexml/side/clazz/generator/alfresco/api/templates/extJS/dataModel/template-json-editable-grid-2-html.mt");
		result.add("/com.bluexml.side.Class.generator.alfresco.api/com/bluexml/side/clazz/generator/alfresco/api/templates/extJS/dataModel/template-json-editable-grid-2-js.mt");
		result.add("/com.bluexml.side.Class.generator.alfresco.api/com/bluexml/side/clazz/generator/alfresco/api/templates/extJS/dataModel/template-json-grouping-html.mt");
		result.add("/com.bluexml.side.Class.generator.alfresco.api/com/bluexml/side/clazz/generator/alfresco/api/templates/extJS/dataModel/template-json-grouping-js.mt");
		result.add("/com.bluexml.side.Class.generator.alfresco.api/com/bluexml/side/clazz/generator/alfresco/api/templates/extJS/dataModel/template-json-paging-html.mt");
		result.add("/com.bluexml.side.Class.generator.alfresco.api/com/bluexml/side/clazz/generator/alfresco/api/templates/extJS/dataModel/template-json-paging-js.mt");
		result.add("/com.bluexml.side.Class.generator.alfresco.api/com/bluexml/side/clazz/generator/alfresco/api/templates/extJS/dataModel/template-json-simple-grid-html.mt");
		result.add("/com.bluexml.side.Class.generator.alfresco.api/com/bluexml/side/clazz/generator/alfresco/api/templates/extJS/dataModel/template-json-simple-grid-js.mt");
		
		return result;
	}
}
