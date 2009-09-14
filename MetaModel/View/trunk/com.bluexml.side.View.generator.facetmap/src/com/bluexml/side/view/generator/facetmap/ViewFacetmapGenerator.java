/*******************************************************************************
 * 	Copyright (C) BlueXML 2005-2009
 *
 * This program is free software; you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation; either version 2.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Boston, MA 02111.
 ******************************************************************************/
package com.bluexml.side.view.generator.facetmap;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.emf.ecore.EObject;

import com.bluexml.side.util.generator.acceleo.AbstractAcceleoPackageGenerator;
import com.bluexml.side.util.libs.FileHelper;
import com.bluexml.side.util.libs.IFileHelper;
import com.bluexml.side.util.libs.zip.ZipManager;
import com.bluexml.side.util.security.SecurityHelper;
import com.bluexml.side.util.security.preferences.SidePreferences;
import com.bluexml.side.view.generator.facetmap.utils.FacetmapConstants;

/**
 * @author <a href="mailto:pbertrand@bluexml.com"> Pierre BERTRAND </a>
 * 
 */
public class ViewFacetmapGenerator extends AbstractAcceleoPackageGenerator implements FacetmapConstants {
	public static String GENERATOR_CODE = "CODE_GED_G_C_FACETMAP_2"; //$NON-NLS-1$
	public static String ALFRESCO_URL_KEY = "alfresco.url"; //$NON-NLS-1$
	public static String ALFRESCO_URL_defaultValue = "http://localhost:8080/alfresco"; //$NON-NLS-1$
	public static String ALFRESCO_SHARE_URL_KEY = "alfresco.share.url"; //$NON-NLS-1$
	public static String ALFRESCO_SHARE_URL_defaultValue = "http://localhost:8080/share"; //$NON-NLS-1$
	public static String MMUri = "http://www.kerblue.org/view/1.0"; //$NON-NLS-1$

	public ViewFacetmapGenerator() {
		techVersion = "Facetmap_2.x"; //$NON-NLS-1$
		this.setTEMP_FOLDER(getTechVersion());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.bluexml.side.application.generator.acceleo.AbstractAcceleoGenerator
	 * #getMetamodelURI()
	 */
	@Override
	protected String getMetamodelURI() {
		return MMUri;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.bluexml.side.application.generator.acceleo.AbstractAcceleoGenerator
	 * #getTemplates()
	 */
	@Override
	protected List<String> getTemplates() {
		List<String> templates = new ArrayList<String>();
		// common
		templates.add("/com.bluexml.side.View.generator.facetmap/templates/facetmap-propertyfile-generation.mt"); //$NON-NLS-1$
		// facets
		templates.add("/com.bluexml.side.View.generator.facetmap/templates/facetmap-facets-buildproperties-generation.mt"); //$NON-NLS-1$
		templates.add("/com.bluexml.side.View.generator.facetmap/templates/facetmap-facets-cmis2xfml-generation.mt"); //$NON-NLS-1$
		templates.add("/com.bluexml.side.View.generator.facetmap/templates/facetmap-facets-sql2xfml-generation.mt"); //$NON-NLS-1$
		templates.add("/com.bluexml.side.View.generator.facetmap/templates/facetmap-facets-xslbasicfacets-generation.mt"); //$NON-NLS-1$
		templates.add("/com.bluexml.side.View.generator.facetmap/templates/facetmap-facets-xslrightnav-generation.mt"); //$NON-NLS-1$
		// results
		templates.add("/com.bluexml.side.View.generator.facetmap/templates/facetmap-content-basicresults-generation.mt"); //$NON-NLS-1$
		return templates;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.bluexml.side.application.security.Checkable#check()
	 */
	public boolean check() {
		return SecurityHelper.check(GENERATOR_CODE, SidePreferences.getKey());
	}

	public String getGeneratorParameter(EObject ob, String paramName) throws Exception {
		return configurationParameters.get(paramName);
	}

	public Collection<IFile> complete() throws Exception {
		if (groupedModels.entrySet().size() > 1)
			try {
				throw new Exception(Activator.Messages.getString("ViewFacetmapGenerator.14")); //$NON-NLS-1$
			} catch (Exception e) {
				monitor.addErrorTextAndLog(Activator.Messages.getString("ViewFacetmapGenerator.15"), e, null); //$NON-NLS-1$
			}
		setTEMP_FOLDER("generator_" + getClass().getName()); //$NON-NLS-1$
		// Adding file generated to log
		generatedFiles.addAll(buildPackages(groupedModels.keySet().toArray()[0].toString()));
		for (IFile f : generatedFiles) {
			monitor.getLog().addFileGeneratedLog(Activator.Messages.getString("ViewFacetmapGenerator.17"), f.getLocation().toOSString() + "", IFileHelper.getFile(f).toURI()); //$NON-NLS-1$ //$NON-NLS-2$
		}

		// add resources to match with package dependencies
		try {
			addDependences();
		} catch (Exception e) {
			monitor.addErrorTextAndLog(Activator.Messages.getString("ViewFacetmapGenerator.19"), e, null); //$NON-NLS-1$
		}
		// Adding services to log
		// CMIS
		String alfrescoUrl = generationParameters.get(ALFRESCO_URL_KEY);
		if (alfrescoUrl != null && alfrescoUrl.length() > 0) {
			if (!alfrescoUrl.endsWith("/")) { //$NON-NLS-1$
				alfrescoUrl += "/"; //$NON-NLS-1$
			}
		} else {
			alfrescoUrl = ALFRESCO_URL_defaultValue;
			monitor.getLog().addWarningLog(Activator.Messages.getString("ViewFacetmapGenerator.22"), Activator.Messages.getString("ViewFacetmapGenerator.23") + alfrescoUrl, ""); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		}
		String cmisUri = alfrescoUrl + "service/com/bluexml/side/facetMap/doclist_user.xml"; //$NON-NLS-1$
		monitor.getLog().addServiceLog(Activator.Messages.getString("ViewFacetmapGenerator.26"), Activator.Messages.getString("ViewFacetmapGenerator.27"), cmisUri); //$NON-NLS-1$ //$NON-NLS-2$
		// Dashlets
		String shareUrl = generationParameters.get(ALFRESCO_SHARE_URL_KEY);
		if (shareUrl != null && shareUrl.length() > 0) {
			if (!shareUrl.endsWith("/")) { //$NON-NLS-1$
				shareUrl += "/"; //$NON-NLS-1$
			}
		} else {
			// set to default value
			shareUrl = ALFRESCO_SHARE_URL_defaultValue;
			monitor.getLog().addWarningLog(Activator.Messages.getString("ViewFacetmapGenerator.24"), Activator.Messages.getString("ViewFacetmapGenerator.31") + shareUrl, ""); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		}
		String dashletContentUri = shareUrl + "service/com/bluexml/side/facetMap/doclist_user/content"; //$NON-NLS-1$
		String dashletFacetstUri = shareUrl + "service/com/bluexml/side/facetMap/doclist_user/facet"; //$NON-NLS-1$
		monitor.getLog().addServiceLog(Activator.Messages.getString("ViewFacetmapGenerator.35"), Activator.Messages.getString("ViewFacetmapGenerator.36"), dashletContentUri); //$NON-NLS-1$ //$NON-NLS-2$
		monitor.getLog().addServiceLog(Activator.Messages.getString("ViewFacetmapGenerator.37"), Activator.Messages.getString("ViewFacetmapGenerator.38"), dashletFacetstUri); //$NON-NLS-1$ //$NON-NLS-2$

		return generatedFiles;
	}

	@Override
	public Collection<IFile> buildPackages(String modelId) {
		String folder = IFileHelper.getSystemFolderPath(getTemporaryFolder() + FILESEP + modelId) + FILESEP;
		// Destinations
		String destFacets = folder + "zip" + FILESEP + WEBAPP_FACETS; //$NON-NLS-1$
		String destContent = folder + "zip" + FILESEP + WEBAPP_CONTENT; //$NON-NLS-1$
		try {
			// Copy
			FileHelper.copyFiles(new File(folder + "common"), new File(destFacets), true); //$NON-NLS-1$
			FileHelper.copyFiles(new File(folder + "common"), new File(destContent), true); //$NON-NLS-1$
			FileHelper.copyFiles(new File(folder + "facets"), new File(destFacets), true); //$NON-NLS-1$
			FileHelper.copyFiles(new File(folder + "content"), new File(destContent), true); //$NON-NLS-1$
		} catch (IOException e) {
			monitor.addErrorTextAndLog(Activator.Messages.getString("ViewFacetmapGenerator.45"), e, null); //$NON-NLS-1$
		}
		// Zip
		String zipFolder = IFileHelper.getSystemFolderPath(getTargetPath() + FILESEP + getTechVersion()) + FILESEP;
		new File(zipFolder).mkdirs();
		File zipFacets = new File(zipFolder + WEBAPP_FACETS + ".zip"); //$NON-NLS-1$
		File zipContent = new File(zipFolder + WEBAPP_CONTENT + ".zip"); //$NON-NLS-1$
		try {
			ZipManager.zip(new File(destFacets), zipFacets, false);
			ZipManager.zip(new File(destContent), zipContent, false);
		} catch (Exception e) {
			monitor.addErrorTextAndLog(Activator.Messages.getString("ViewFacetmapGenerator.48"), e, null); //$NON-NLS-1$
		}
		// Creating file collection
		IFolder workingDir = IFileHelper.getIFolder(getTemporaryFolder() + FILESEP + "../"); //$NON-NLS-1$
		Collection<IFile> pkgs = new ArrayList<IFile>();
		pkgs.add(IFileHelper.getIFile(workingDir.toString().replaceFirst("[^/]*/", "/") + FILESEP + getTechVersion() + FILESEP + WEBAPP_FACETS + ".zip")); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		pkgs.add(IFileHelper.getIFile(workingDir.toString().replaceFirst("[^/]*/", "/") + FILESEP + getTechVersion() + FILESEP + WEBAPP_CONTENT + ".zip")); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		return pkgs;
	}

}
