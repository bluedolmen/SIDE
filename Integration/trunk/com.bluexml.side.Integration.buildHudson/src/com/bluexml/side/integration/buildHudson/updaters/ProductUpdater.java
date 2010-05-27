package com.bluexml.side.integration.buildHudson.updaters;

import java.io.File;
import java.util.List;

import org.apache.log4j.Logger;
import org.jdom.Attribute;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.xpath.XPath;

import com.bluexml.side.integration.buildHudson.utils.BuilderUtils;

public class ProductUpdater {
	private Logger logger = Logger.getLogger(getClass());
	BuilderUtils bu;
	FeatureUpdater fu;
	boolean forceUpdate;
	String newVersion = "";

	public String getNewVersion() {
		return newVersion;
	}

	public ProductUpdater(FeatureUpdater fu, BuilderUtils bu, boolean forceUpdate) {
		this.bu = bu;
		this.fu = fu;
		this.forceUpdate = forceUpdate;
	}

	public boolean updateProduct() throws Exception {
		File product = bu.getProductFile();
		boolean changes = false;
		Document productDoc = BuilderUtils.buildJdomDocument(product);
		// search for features reference to update
		// product/features/feature
		XPath xpa = XPath.newInstance("//product/features/feature");
		List<?> lmd = xpa.selectNodes(productDoc.getRootElement());
		for (Object object : lmd) {
			Element el = (Element) object;
			String featureId = el.getAttributeValue("id");
			if (bu.getProjects().contains(featureId)) {
				String version = el.getAttributeValue("version");

				// get feature version in feature.xml
				String versionFromFile = fu.getFeatureVersion(featureId);

				if (!version.equals(versionFromFile)) {
					// update feature reference
					el.setAttribute("version", versionFromFile);
					// mark as updated
					changes = true;
				}
			}
		}
		Attribute version = productDoc.getRootElement().getAttribute("version");
		String oldVersion = version.getValue();
		if (changes || forceUpdate) {
			String[] pattern = bu.getNumVersionPattern();
			newVersion = MavenProjectUpdater.updatepom(version.getValue().split("\\."), pattern);

			version.setValue(newVersion);
			logger.debug("sideProduct version :" + oldVersion + " -> " + newVersion);
			// save changes
			BuilderUtils.saveXMLFile(product, productDoc);
		} else {
			newVersion = oldVersion;
		}
		return changes;
	}
}
