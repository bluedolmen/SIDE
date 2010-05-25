package com.bluexml.side.integration.buildHudson.utils;

import java.io.File;
import java.util.List;

import org.jdom.Attribute;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.xpath.XPath;

public class ProductUpdater {
	BuilderUtils bu;
	FeatureUpdater fu;
	String newVersion = "";

	public String getNewVersion() {
		return newVersion;
	}

	public ProductUpdater(FeatureUpdater fu, BuilderUtils bu) {
		this.bu = bu;
		this.fu = fu;
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
		if (changes) {
			String[] pattern = bu.getNumVersionPattern();
			newVersion = MavenProjectUpdater.updatepom(version.getValue().split("\\."), pattern);

			version.setValue(newVersion);
			System.out.println("sideProduct version :" + oldVersion + " -> " + newVersion);
			// save changes
			BuilderUtils.saveXMLFile(product, productDoc);
		} else {
			newVersion = oldVersion;
		}
		return changes;
	}
}
