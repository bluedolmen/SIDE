package com.bluexml.side.build.tools.reader;

import java.io.File;
import java.util.List;

import org.apache.log4j.Logger;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.xpath.XPath;

import com.bluexml.side.build.tools.componants.Feature;
import com.bluexml.side.build.tools.componants.Product;

public class ProductReader {
	Logger logger = Logger.getLogger(this.getClass());
	File productFile;
	boolean addAll = false;
	ComponantsRegisters util;

	public ComponantsRegisters getUtil() {
		return util;
	}

	public ProductReader(File proFile, ComponantsRegisters util) {
		this.productFile = proFile;
		this.util = util;
	}

	public Product read() throws Exception {
		logger.debug("Read :" + productFile);
		Product product;
		// getFileStream
		Document doc = Utils.buildJdomDocument(productFile);

		// get properties
		Element root = doc.getRootElement();
		product = new Product();
		product.setId(root.getAttributeValue("id"));
		product.setUid(root.getAttributeValue("uid"));
		product.setName(root.getAttributeValue("name"));
		product.setVersion(root.getAttributeValue("version"));

		// set feature list
		FeatureReader fr = new FeatureReader(util);
		XPath xpa = XPath.newInstance("//product/features/feature");
		List<?> lmd = xpa.selectNodes(root);
		for (Object object : lmd) {
			Element el = (Element) object;
			String featureId = el.getAttributeValue("id");
			
			Feature f = util.getFeature(featureId);
			if (f == null) {
				File featureFolder = util.getProjectFolder(featureId);
				boolean side = false;
				if (featureFolder != null) {
					f = fr.read(featureFolder);
					side = true;
				} else {
					// not found in repository, not SIDE
					f = new Feature();
					f.setId(featureId);
					f.setVersion(el.getAttributeValue("version"));
				}
				if (side || addAll) {
					util.featuresRegister.put(featureId, f);
					Utils.add(util.tree, product, f);
					product.getFeatures().add(f);
				}
			} else {
				product.getFeatures().add(f);
			}
			
			
		}
		return product;
	}
}
