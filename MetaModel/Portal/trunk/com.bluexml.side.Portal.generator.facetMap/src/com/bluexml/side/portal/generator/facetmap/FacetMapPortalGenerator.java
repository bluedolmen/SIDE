package com.bluexml.side.portal.generator.facetmap;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import org.eclipse.core.resources.IFile;

import com.bluexml.side.util.generator.acceleo.AbstractAcceleoPackageGenerator;
import com.bluexml.side.util.libs.IFileHelper;


public class FacetMapPortalGenerator extends AbstractAcceleoPackageGenerator {

	@Override
	public Collection<IFile> buildPackages(String modelId) throws Exception {
		MultiFacetMapPackager mfPackager = new MultiFacetMapPackager(IFileHelper.getIFolder(getTemporaryFolder()), buildModuleProperties(modelId), techVersion);
		Collection<IFile> pkgs = mfPackager.buildPackages().values();
		return pkgs;
	}

	@Override
	protected String getMetamodelURI() {
		return "http://www.kerblue.org/portal/1.0";
	}

	@Override
	protected List<String> getTemplates() {
		List<String> templates = new ArrayList<String>();	
		
		templates.add("/com.bluexml.side.Portal.generator.facetMap/templates/facetmap-facets-css.mt");
		templates.add("/com.bluexml.side.Portal.generator.facetMap/templates/facetmap-facets-buildproperties.mt");
		templates.add("/com.bluexml.side.Portal.generator.facetMap/templates/facetmap-facets-buildsql.mt");
		templates.add("/com.bluexml.side.Portal.generator.facetMap/templates/facetmap-facets-setServletParameters.mt");
		templates.add("/com.bluexml.side.Portal.generator.facetMap/templates/facetmap-facets-javascript.mt");
		templates.add("/com.bluexml.side.Portal.generator.facetMap/templates/facetmap-facets-setServletParameters.mt");
		templates.add("/com.bluexml.side.Portal.generator.facetMap/templates/facetmap-facets-updatesqljsp.mt");
		templates.add("/com.bluexml.side.Portal.generator.facetMap/templates/facetmap-facets-basicfacets.mt");
		templates.add("/com.bluexml.side.Portal.generator.facetMap/templates/facetmap-facets-rightnav.mt");
		templates.add("/com.bluexml.side.Portal.generator.facetMap/templates/facetmap-content-rightnav.mt");
		templates.add("/com.bluexml.side.Portal.generator.facetMap/templates/facetmap-content-basicresults.mt");
		
		return templates;
	}
	public Properties buildModuleProperties(String modelId) {

		Date now = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
		Properties props = new Properties();
		props.put("module.id", "SIDE_PortalExtension_" + modelId);
		props.put("module.version", getVersioNumber());
		props.put("module.title", "S-IDE portal extension");
		props.put("module.description", "this module contains S-IDE generated extension to extends Alfresco Share,\n build at " + sdf.format(now));
		return props;
	}
}
