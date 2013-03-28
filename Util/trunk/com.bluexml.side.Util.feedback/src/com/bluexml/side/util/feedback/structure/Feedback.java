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
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.bluexml.side.util.libs.SystemInfoGetter;

public class Feedback {
	protected Date date;
	protected List<FeedbackItem> feedbacks = new ArrayList<FeedbackItem>();
	protected String userId;

	public void addItem(String id, String metaModel, String version, Map<String, Boolean> options) {
		FeedbackItem feedbackItem = new FeedbackItem(id, metaModel, version, options);
		date = new Date();
		userId = SystemInfoGetter.getHostWithHash();
		feedbacks.add(feedbackItem);
	}
}
