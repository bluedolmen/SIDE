package com.bluexml.side.Framework.alfresco.share.formExtension;

import java.io.InputStream;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.extensions.config.source.UrlConfigSource;
import org.springframework.extensions.webscripts.ClassPathStore;

public class MyUrlConfigSource extends UrlConfigSource {
	static Log logger = LogFactory.getLog(MyUrlConfigSource.class);
	static String regexp = UrlConfigSource.PREFIX_CLASSPATH + "([^*]*)/([^*]*\\*.*)";
	ClassPathStore shareConfigStore;

	public ClassPathStore getShareConfigStore() {
		return shareConfigStore;
	}

	public void setShareConfigStore(ClassPathStore shareConfigStore) {
		this.shareConfigStore = shareConfigStore;
	}

	public MyUrlConfigSource(String sourceLocation) {
		super(sourceLocation);
	}

	public MyUrlConfigSource(List<String> sourceLocations, ClassPathStore shareConfigStore) {
		super("invalid");
		this.shareConfigStore = shareConfigStore;
		for (String location : sourceLocations) {
			processSourceString(location);
		}
	}

	@Override
	protected void processSourceString(String sourceString) {
		if (sourceString.startsWith(UrlConfigSource.PREFIX_CLASSPATH) && sourceString.contains("*")) {
			processWildcardClassPath(sourceString);
		} else {
			super.processSourceString(sourceString);
		}
	}

	/**
	 * @param sourceString
	 */
	private void processWildcardClassPath(String sourceString) {
		logger.debug("process :" + sourceString);
		// get a classPathStore instance

		// get store part from sourceString
		if (sourceString.matches(regexp)) {
			// ok
			Pattern p = Pattern.compile(regexp);
			Matcher m = p.matcher(sourceString);
			m.find();
			if (m.groupCount() == 2) {
				// ok
				String storeString = m.group(1);
				String filepathtoMatch = m.group(2);

				logger.debug("store :" + storeString);
				logger.debug("fileName :" + filepathtoMatch);

				ClassPathStore store = getClassPathStore(storeString);

				if (store.hasDocument(filepathtoMatch)) {
					logger.debug("file found in store");

					computeSourceString(filepathtoMatch, storeString);

				} else {
					logger.debug("fileName not found");

					if (filepathtoMatch.indexOf("*") != -1) {
						logger.debug("use white card");
						// convert pattern to regexp
						filepathtoMatch = filepathtoMatch.replaceAll("\\.", "\\\\.");
						filepathtoMatch = filepathtoMatch.replaceAll("\\*", "\\.\\*");
					}
					String[] allDocumentPaths = store.getAllDocumentPaths();
					logger.debug("documents in store :" + allDocumentPaths.length);
					for (String fileNamePath : allDocumentPaths) {
						if (fileNamePath.matches(filepathtoMatch)) {
							logger.debug("matches found :" + fileNamePath);
							computeSourceString(fileNamePath, storeString);
						}
					}
				}
			}
		}
	}

	/**
	 * @param filepathtoMatch
	 */
	private void computeSourceString(String filepathtoMatch, String store) {
		addSourceString(UrlConfigSource.PREFIX_CLASSPATH + store + "/" + filepathtoMatch);
	}

	@Override
	public InputStream getInputStream(String sourceUrl) {
		logger.debug("get InputStream from :" + sourceUrl);
		InputStream inputStream = super.getInputStream(sourceUrl);
		logger.debug("imputStream :" + inputStream);
		return inputStream;
	}

	protected ClassPathStore getClassPathStore(String storeString) {
		ClassPathStore store = shareConfigStore;

		store.setClassPath(storeString);
		store.setMustExist(false);
		// FIXME : Why init() are not executed by Springs ?
		store.init();
		return store;
	}
}
