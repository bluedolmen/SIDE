/*
    Copyright (C) 2007-20013  BlueXML - www.bluexml.com

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU Affero General Public License as
    published by the Free Software Foundation, either version 3 of the
    License, or (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU Affero General Public License for more details.

    You should have received a copy of the GNU Affero General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/
package com.bluexml.side.build.tools.reader;

import java.io.File;
import java.util.List;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.xpath.XPath;

import com.bluexml.side.build.tools.componants.Feature;
import com.bluexml.side.build.tools.componants.Product;

public class ProductReader extends Reader {
	Logger logger = Logger.getLogger(this.getClass());
	File productFile;
	boolean addAll = false;

	public ComponantsRegisters getRegistries() {
		return registries;
	}

	public ProductReader(File proFile, ComponantsRegisters registries, Properties props) {
		super(registries, props);
		this.productFile = proFile;

		addAll = getBooleanPropertyValueFor("addAll", addAll);
	}

	public Product read() throws Exception {
		logger.debug("Read :" + productFile);
		Product product;
		// getFileStream
		Document doc = Utils.buildJdomDocument(productFile);

		// get properties
		Element root = doc.getRootElement();
		product = new Product();
		String id = root.getAttributeValue("id");
		product.setId(id);
		product.setUid(root.getAttributeValue("uid"));
		product.setName(root.getAttributeValue("name"));
		product.setVersion(root.getAttributeValue("version"));

		// set feature list
		FeatureReader fr = new FeatureReader(registries, props);
		XPath xpa = XPath.newInstance("//product/features/feature");
		List<?> lmd = xpa.selectNodes(root);
		for (Object object : lmd) {
			Element el = (Element) object;
			String featureId = el.getAttributeValue("id");

			Feature f = registries.getFeature(featureId);
			if (f == null) {
				File featureFolder = registries.getProjectFolder(featureId, id);
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
					registries.featuresRegister.put(featureId, f);
					Utils.add(registries.tree, product, f);
					product.getFeatures().add(f);
				}
			} else {
				product.getFeatures().add(f);
			}

		}
		return product;
	}
}
