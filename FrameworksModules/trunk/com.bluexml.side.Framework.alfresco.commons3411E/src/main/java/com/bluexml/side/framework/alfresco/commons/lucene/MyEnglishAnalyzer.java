package com.bluexml.side.framework.alfresco.commons.lucene;

import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.lucene.analysis.StopAnalyzer;
import org.apache.lucene.analysis.StopFilter;

/**
 * Original code from Code from org.apache.lucene.analysis.StopAnalyzer
 * this class add a filter that remove accents
 * 
 * @author davidabad
 */
public class MyEnglishAnalyzer extends MyAnalyzer {
	protected static Log logger = LogFactory.getLog(MyEnglishAnalyzer.class);

	protected Set<?> stopWords;

	/** Builds an analyzer which removes words in ENGLISH_STOP_WORDS. */
	public MyEnglishAnalyzer() {
		stopWords = StopFilter.makeStopSet(StopAnalyzer.ENGLISH_STOP_WORDS);
	}

}
