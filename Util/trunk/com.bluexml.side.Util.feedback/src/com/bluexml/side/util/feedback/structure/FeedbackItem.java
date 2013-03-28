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
package com.bluexml.side.util.feedback.structure;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class FeedbackItem {
	protected String id;
	protected String metaModel;
	protected String technoVersion;
	protected List<Option> options = new ArrayList<Option>();

	public FeedbackItem(String id, String metaModel, String technoVersion, Map<String, Boolean> p_options) {
		this.id = id;
		this.metaModel = metaModel;
		this.technoVersion = technoVersion;
		for (String key : p_options.keySet()) {
			options.add(new Option(key, p_options.get(key)));
		}
	}


}
