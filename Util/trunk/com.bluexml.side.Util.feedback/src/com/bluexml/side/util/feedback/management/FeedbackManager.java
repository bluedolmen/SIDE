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
	 * Save under .metadata/.plugin/pluginid/dd-MM-yy--hh-mm-ss-log.xml the informations for feedback.
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
		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yy--hh-mm-ss");
		return dateFormat.format(date) + FeedbackUtils.END_FILE_NAME;
	}
}
