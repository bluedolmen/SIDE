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
package com.bluexml.side.view.generator.facetmap.enterprise;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.eclipse.core.resources.IFile;
import org.eclipse.emf.ecore.EObject;

import com.bluexml.side.util.security.SecurityHelper;
import com.bluexml.side.util.security.preferences.SidePreferences;
import com.bluexml.side.view.generator.facetmap.ViewFacetmapGenerator;

/**
 * @author <a href="mailto:pbertrand@bluexml.com"> Pierre BERTRAND </a>
 * @author davidabad
 */
public class ViewFacetmapGeneratorEnterprise extends ViewFacetmapGenerator {
	public static String GENERATOR_CODE = "CODE_GED_G_C_FACETMAP_2"; //$NON-NLS-1$

	/*
	 * (non-Javadoc)
	 * @see com.bluexml.side.application.security.Checkable#check()
	 */
	@Override
	public boolean check() {
		return SecurityHelper.check(GENERATOR_CODE, SidePreferences.getKey());
	}

	@Override
	public String getGeneratorParameter(EObject ob, String paramName) throws Exception {
		return configurationParameters.get(paramName);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * 
	 * com.bluexml.side.view.generator.facetmap.ViewFacetmapGenerator#getTemplates
	 * ()
	 */
	@Override
	protected List<String> getMainTemplates() {
		List<String> templates = new ArrayList<String>();
		// common
		templates.add("/com.bluexml.side.View.generator.facetmap/templates/facetmap-propertyfile-generation.mt"); //$NON-NLS-1$
		// facets
		templates.add("/com.bluexml.side.View.generator.facetmap/templates/facetmap-facets-buildproperties-generation.mt"); //$NON-NLS-1$

		// for Enterprise Alfresco 3.2rE
		templates.add("/com.bluexml.side.View.generator.facetmap.enterprise/com/bluexml/side/view/generator/facetmap/enterprise/templates/facetmap-facets-cmis2xfml-generation.mt"); //$NON-NLS-1$

		templates.add("/com.bluexml.side.View.generator.facetmap/templates/facetmap-facets-sql2xfml-generation.mt"); //$NON-NLS-1$
		templates.add("/com.bluexml.side.View.generator.facetmap/templates/facetmap-facets-xslbasicfacets-generation.mt"); //$NON-NLS-1$
		templates.add("/com.bluexml.side.View.generator.facetmap/templates/facetmap-facets-xslrightnav-generation.mt"); //$NON-NLS-1$
		templates.add("/com.bluexml.side.View.generator.facetmap/templates/facetmap-facets-xslglobal-generation.mt"); //$NON-NLS-1$
		templates.add("/com.bluexml.side.View.generator.facetmap/templates/facetmap-facets-xslrightnavContent-generation.mt"); //$NON-NLS-1$

		// results
		templates.add("/com.bluexml.side.View.generator.facetmap/templates/facetmap-content-results-generation.mt"); //$NON-NLS-1$
		templates.add("/com.bluexml.side.View.generator.facetmap/templates/xml-grid_content.js.mt"); //$NON-NLS-1$
		return templates;
	}

	@Override
	public Collection<IFile> generate(Map<String, List<IFile>> modelsInfo, String idMetamodel) throws Exception {
		if (!check()) {
			throw new Exception("Bad, please to enter your licence number in preferencies page");
		}
		return super.generate(modelsInfo, idMetamodel);
	}
}
