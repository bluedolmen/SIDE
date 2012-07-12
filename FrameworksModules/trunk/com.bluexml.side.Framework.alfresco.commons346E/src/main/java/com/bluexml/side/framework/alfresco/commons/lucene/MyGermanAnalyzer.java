package com.bluexml.side.framework.alfresco.commons.lucene;

import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.lucene.analysis.StopFilter;
import org.apache.lucene.analysis.de.GermanAnalyzer;

/**
 * Original code from Code from org.apache.lucene.analysis.StopAnalyzer
 * this class add a filter that remove accents
 * 
 * @author davidabad
 */
public class MyGermanAnalyzer extends MyAnalyzer {
	protected static Log logger = LogFactory.getLog(MyGermanAnalyzer.class);

	protected Set<?> stopWords;

	/** Builds an analyzer which removes words in GERMAN_STOP_WORDS. */
	public MyGermanAnalyzer() {
		stopWords = StopFilter.makeStopSet(GermanAnalyzer.GERMAN_STOP_WORDS);
	}

}
