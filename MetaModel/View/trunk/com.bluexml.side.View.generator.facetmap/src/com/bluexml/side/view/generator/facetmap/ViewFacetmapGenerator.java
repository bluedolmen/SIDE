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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import org.eclipse.core.resources.IFile;
import org.eclipse.emf.ecore.EObject;

import com.bluexml.side.util.generator.acceleo.AbstractAcceleoPackageGenerator;
import com.bluexml.side.util.generator.packager.WarPatchPackager;
import com.bluexml.side.util.libs.IFileHelper;
import com.bluexml.side.util.security.SecurityHelper;
import com.bluexml.side.util.security.preferences.SidePreferences;

/**
 * @author <a href="mailto:pbertrand@bluexml.com"> Pierre BERTRAND </a>
 * 
 */
public class ViewFacetmapGenerator extends AbstractAcceleoPackageGenerator {
	public static String GENERATOR_CODE = "CODE_GED_G_C_FACETMAP_2"; //$NON-NLS-1$

	public static String ALFRESCO_URL_defaultValue = "http://localhost:8080/alfresco"; //$NON-NLS-1$

	public static String ALFRESCO_SHARE_URL_defaultValue = "http://localhost:8080/share"; //$NON-NLS-1$
	public static String MMUri = "http://www.kerblue.org/view/1.0"; //$NON-NLS-1$

	public ViewFacetmapGenerator() {
		techVersion = "Facetmap_2.x"; //$NON-NLS-1$
		versionProperty="";
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
		templates.add("/com.bluexml.side.View.generator.facetmap/templates/facetmap-facets-xslglobal-generation.mt"); //$NON-NLS-1$
		templates.add("/com.bluexml.side.View.generator.facetmap/templates/facetmap-facets-xslrightnavContent-generation.mt"); //$NON-NLS-1$
		
		// results
		templates.add("/com.bluexml.side.View.generator.facetmap/templates/facetmap-content-results-generation.mt"); //$NON-NLS-1$
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

	@Override
	public Collection<IFile> buildPackages(String modelId) throws Exception {
		Collection<IFile> pkgs = new ArrayList<IFile>();
		WarPatchPackager pkger = new WarPatchPackager(IFileHelper.getIFolder(getTemporaryFolder()), buildModuleProperties(modelId).getProperty("module.id"), techVersion, "facetmap");
		IFile package_ = pkger.buildPackage();
		pkgs.add(package_);
		return pkgs;
	}

	public Properties buildModuleProperties(String rootPackage) {
		Date now = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss"); //$NON-NLS-1$
		Properties props = new Properties();
		props.put("module.id", "SIDE_FacetMapExtension_" + rootPackage); //$NON-NLS-1$ //$NON-NLS-2$
		props.put("module.version", getVersioNumber()); //$NON-NLS-1$
		props.put("module.title", Activator.Messages.getString("ClassAlfrescoGenerator_7")); //$NON-NLS-1$
		props.put("module.description", Activator.Messages.getString("ClassAlfrescoGenerator_8") + sdf.format(now)); //$NON-NLS-1$
		return props;
	}
}
