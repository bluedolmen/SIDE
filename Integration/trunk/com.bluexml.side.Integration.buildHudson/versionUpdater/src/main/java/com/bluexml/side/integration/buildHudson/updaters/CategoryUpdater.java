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
package com.bluexml.side.integration.buildHudson.updaters;

import java.io.File;
import java.util.List;

import org.apache.log4j.Logger;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.xpath.XPath;

import com.bluexml.side.integration.buildHudson.utils.BuilderUtils;

public class CategoryUpdater {
	private Logger logger = Logger.getLogger(getClass());
	BuilderUtils bu;
	FeatureUpdater fu;

	public CategoryUpdater(FeatureUpdater fu, BuilderUtils bu) {
		this.bu = bu;
		this.fu = fu;
	}

	public boolean updateCategory() throws Exception {
		return updateCategory(bu.getCategoryFile());
	}
	
	public boolean updateCategory(String pluginId) throws Exception {
		return updateCategory(bu.getCategoryFile(pluginId));
	}

	public boolean updateCategory(File catf) throws Exception {
		logger.debug("CategoryUpdater.updateCategory() start");
		boolean changes = false;
		// load file
		//File catf = bu.getCategoryFile();
		Document catxml = BuilderUtils.buildJdomDocument(catf);
		// search features references to update
		XPath xpa = XPath.newInstance("/site/feature");
		List<?> lmd = xpa.selectNodes(catxml.getRootElement());
		for (Object object : lmd) {
			Element el = (Element) object;
			String featureId = el.getAttributeValue("id");
			if (bu.getProjects().contains(featureId)) {
				String featureVersion = el.getAttributeValue("version");
				String featureUrl = el.getAttributeValue("url");

				String newVersion = fu.getFeatureVersion(featureId);
				String newUrl = "features/" + featureId + "_" + newVersion + ".jar";

				if (!featureVersion.equals(newVersion)) {
					logger.debug("update " + featureId + " version:" + featureVersion + " -> " + newVersion);
					logger.debug("update " + featureId + " url:" + featureUrl + " -> " + newUrl);
					// update ref
					el.setAttribute("version", newVersion);
					el.setAttribute("url", newUrl);
					changes = true;
				}
			} else {
				logger.trace("CategoryUpdater.updateCategory() skipped feature :" + featureId);
			}
		}
		if (changes) {
			// save file
			BuilderUtils.saveXMLFile(catf, catxml);
		}
		return changes;
	}

}
