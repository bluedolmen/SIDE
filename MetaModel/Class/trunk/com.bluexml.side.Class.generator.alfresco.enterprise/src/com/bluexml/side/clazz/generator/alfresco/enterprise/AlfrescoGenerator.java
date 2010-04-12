package com.bluexml.side.clazz.generator.alfresco.enterprise;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;

import com.bluexml.side.clazz.generator.alfresco.ClassAlfrescoGenerator;
import com.bluexml.side.util.generator.alfresco.AlfrescoPackager;
import com.bluexml.side.util.generator.packager.JavaProjectPackager;
import com.bluexml.side.util.libs.IFileHelper;



public class AlfrescoGenerator extends ClassAlfrescoGenerator {

	
	static final String ALFRESCO_WEBSERVICES_CLIENT_API= "com.bluexml.side.Class.generator.alfresco.enterprise.webserviceJavaAPI";
	/* (non-Javadoc)
	 * @see com.bluexml.side.clazz.generator.alfresco.ClassAlfrescoGenerator#check()
	 */
	@Override
	public boolean check() {
		return true;
	}

	/* (non-Javadoc)
	 * @see com.bluexml.side.clazz.generator.alfresco.ClassAlfrescoGenerator#getTemplates()
	 */
	@Override
	protected List<String> getTemplates() {
		List<String> result =super.getClassTemplates();
		result.addAll(getEnterpriseTemplates());
		return result;
	}

	
	public List<String> getEnterpriseTemplates() {
		List<String> result = new ArrayList<String>();
		if (getGeneratorOptionValue(ALFRESCO_WEBSERVICES_CLIENT_API)) {
			// model
			result.add("/com.bluexml.side.Class.generator.alfresco.enterprise/templates/modelQNames.java.mt");
			result.add("/com.bluexml.side.Class.generator.alfresco.enterprise/templates/modelJavaObjectModel.mt");
			result.add("/com.bluexml.side.Class.generator.alfresco.enterprise/templates/modelJavaObjectModelEnum.mt");
			result.add("/com.bluexml.side.Class.generator.alfresco.enterprise/templates/eclipseproject.mt");
			result.add("/com.bluexml.side.Class.generator.alfresco.enterprise/templates/eclipseClassPath.mt");
			
			// webService API
			result.add("/com.bluexml.side.Class.generator.alfresco.enterprise/templates/javaAPI.mt");
			result.add("/com.bluexml.side.Class.generator.alfresco.enterprise/templates/commonAPI.java.mt");
			result.add("/com.bluexml.side.Class.generator.alfresco.enterprise/templates/javaAPI_Aspect.mt");
			result.add("/com.bluexml.side.Class.generator.alfresco.enterprise/templates/TestClass.mt");
			result.add("/com.bluexml.side.Class.generator.alfresco.enterprise/templates/eclipseprojectWebServicesAPI.mt");
			result.add("/com.bluexml.side.Class.generator.alfresco.enterprise/templates/eclipseWebServicesClassPath.mt");
			
		}
		return result;
	}
	
	@Override
	public Collection<IFile> buildPackages(String modelId) throws Exception {
		// package amp, shareZip and eclipse project archive
		IFolder iFolder = IFileHelper.getIFolder(getTemporaryFolder());
		AlfrescoPackager alfrescoPackager = new AlfrescoEnterprisePackager(iFolder, buildModuleProperties(modelId), techVersion);
		Collection<IFile> pkgs = alfrescoPackager.buildPackages().values();
		return pkgs;
	}
	
}
