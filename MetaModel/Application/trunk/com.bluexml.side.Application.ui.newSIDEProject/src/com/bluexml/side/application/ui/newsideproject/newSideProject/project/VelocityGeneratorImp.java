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
package com.bluexml.side.application.ui.newsideproject.newSideProject.project;

import java.util.Map;

import com.bluexml.side.util.libs.velocity.VelocityGenerator;
/**
 * using this Class rather than VelocityGenerator in Util.lib to have access of plugin classLoader
 * to get templates
 * @author davidabad
 *
 */
public class VelocityGeneratorImp extends VelocityGenerator {

	public VelocityGeneratorImp(String velocityTemplatePath, String fileoutPath, Map<String, Object> context) {
		super(velocityTemplatePath, fileoutPath, context);
	}

}
