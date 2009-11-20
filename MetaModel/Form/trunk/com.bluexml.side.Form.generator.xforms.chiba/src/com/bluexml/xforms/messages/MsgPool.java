/**
 * 
 */
package com.bluexml.xforms.messages;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.Properties;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author Amenel
 * 
 */
public class MsgPool {
	private static final String INVALID_MESSAGE_FILE = "INVALID MESSAGE FILE!";

	/** The loaded properties. */
	private Properties pool;

	/** The complete path to the message file that contains the properties. */
	private static String messageFilePath;

	private static InputStream inputStream = null;

	/** The logger. */
	protected static Log logger = LogFactory.getLog(MsgPool.class);

	private static MsgPool instance = null;

	private MsgPool() {
		try {
			if (inputStream == null) {
				File messagesFile = new File(messageFilePath);
				inputStream = new FileInputStream(messagesFile);
			}
			pool = new Properties();
			pool.load(inputStream);
		} catch (Exception e) {
			logger.error("Error opening the message file.");
			throw new RuntimeException(e);
		}
	}

	/**
	 * Sets the stream from which to load a message file. Previously loaded messages are lost.
	 * 
	 * @param inputStream
	 *            the inputStream to set
	 */
	public static void setInputStream(InputStream stream) {
		inputStream = stream;
		instance = null;
	}

	/**
	 * Sets the path to a new message file. Previously loaded messages are lost.
	 * 
	 * @param path
	 *            the complete path to the message file.
	 */
	public static void setMessagesFile(String path) {
		messageFilePath = path;
		inputStream = null;
		instance = null;
	}

	/**
	 * Returns the string that is associated to a key in the messages file. If no message is
	 * associated with the key, returns the message associated with the MSG_KEY_NOT_FOUND string. If
	 * this is not defined either, returns a default string.
	 * 
	 * @param msgKey
	 *            the key, either an enum from class MsgId or a String
	 * @return the string attached to the key
	 */
	public static String getMsg(Object msgKey, String... args) {
		String theKey = null;

		if (msgKey instanceof MsgId) {
			theKey = ((MsgId) msgKey).getText();
		} else if (msgKey instanceof String) {
			theKey = (String) msgKey;
		}

		String res = getInstance().getPool().getProperty(theKey);
		if (res != null) {
			return replaceArgs(res, args);
		}
		// a key may be set to an empty string in the properties file
		if (hasProperty(theKey) == true) {
			return "";
		}
		logger.warn("Message not found for key: " + theKey);
		if (StringUtils.equals(theKey, MsgId.MSG_KEY_NOT_FOUND.getText())) {
			return INVALID_MESSAGE_FILE;
		}
		return MsgPool.getMsg(MsgId.MSG_KEY_NOT_FOUND.getText());
	}

	private static String replaceArgs(String msg, String[] args) {
		String result = msg;
		int idx = 0;
		for (String arg : args) {
			String idxStr = "{" + idx + "}";
			result = StringUtils.replace(result, idxStr, arg);
			idx++;
		}
		return result;
	}

	/**
	 * Tests whether a key exists in the properties file.
	 * 
	 * @param theKey
	 *            the reference key text being tested
	 * @return
	 */
	private static boolean hasProperty(String theKey) {
		Enumeration<?> properties = getInstance().getPool().propertyNames();
		while (properties.hasMoreElements()) {
			String aKey = (String) properties.nextElement();
			if (StringUtils.equals(theKey, aKey)) {
				return true;
			}
		}
		return false;
	}

	public static MsgPool getInstance() {
		if (instance == null) {
			instance = new MsgPool();
		}
		return instance;
	}

	/**
	 * @return the pool
	 */
	private Properties getPool() {
		return pool;
	}

	/**
	 * Equivalent to getMsg, except for the return value.
	 * 
	 * @param msgKey
	 * @param args
	 * @return null if a key is not found or if the key is empty
	 */
	public static String testMsg(Object msgKey, String... args) { // added for #1267
		String msg = getMsg(msgKey, args);
		if (StringUtils.equals(msg, getMsg(MsgId.MSG_KEY_NOT_FOUND))
				|| (StringUtils.equals(msg, INVALID_MESSAGE_FILE))) {
			return null;
		}
		return StringUtils.trimToNull(msg);
	}

}
