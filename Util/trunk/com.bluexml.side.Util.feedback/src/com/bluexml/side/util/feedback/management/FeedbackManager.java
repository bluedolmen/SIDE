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
package com.bluexml.side.util.feedback.management;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.eclipse.core.runtime.IPath;

import com.bluexml.side.util.feedback.structure.Feedback;
import com.bluexml.side.util.feedback.structure.FeedbackItem;
import com.bluexml.side.util.feedback.structure.Option;
import com.bluexml.side.util.feedback.utils.FeedbackUtils;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

public class FeedbackManager {

	Feedback feedback = new Feedback();

	/**
	 * Add feedback for a generator
	 * @param id
	 * @param metaModel
	 * @param version
	 * @param options
	 */
	public void addFeedBackItem(String id, String metaModel, String version, Map<String, Boolean> options) {
		feedback.addItem(id, metaModel, version, options);
	}

	/**
	 * Save under .metadata/.plugin/pluginid/yy-MM-dd--hh-mm-ss-log.xml the informations for feedback.
	 * @throws IOException
	 */
	public void save() throws IOException {
		IPath pluginSaveFolder = FeedbackUtils.getFeedbackSaveFolder();
		XStream xstream = new XStream(new DomDriver());

		File f = pluginSaveFolder.toFile();
		FileOutputStream fos;
		File file = new File(f, getFileName());
		file.createNewFile();
		fos = new FileOutputStream(file);

		xstream.alias("item", FeedbackItem.class);
		xstream.alias("feedback", Feedback.class);
		xstream.alias("option", Option.class);
		xstream.addImplicitCollection(Feedback.class, "feedbacks");
		xstream.toXML(feedback, fos);
	}

	/**
	 * Return the filename
	 * @return
	 */
	private String getFileName() {
		Date date = new Date();
		DateFormat dateFormat = new SimpleDateFormat("yy-MM-dd--hh-mm-ss");
		return dateFormat.format(date) + FeedbackUtils.END_FILE_NAME;
	}
}
