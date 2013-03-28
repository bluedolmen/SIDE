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
package com.bluexml.side.framework.alfresco.commons.configurations;

import java.util.Properties;
/**
 * SIDE Extension
 * 
 * @author davidabad
 */
public class PolicyPropertiesConfiguration extends PropertiesConfiguration {

	public static final String POLICY_ENABLED = "policy.enabled";

	@Override
	public boolean isValidePropertiesResource(Properties props) {
		String property = props.getProperty(POLICY_ENABLED);
		if (property == null) {
			property = "true";
		} else {
			// this key only used to tell if other key in bundle must be loaded, but has no signification for the policy
			props.remove(POLICY_ENABLED);
		}
		return property.trim().equals("true");
	}

}
