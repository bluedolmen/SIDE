package com.bluexml.side.framework.alfresco.commons.lucene;

import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.lucene.analysis.StopFilter;
import org.apache.lucene.analysis.fr.FrenchAnalyzer;

/**
 * Original code from Code from org.apache.lucene.analysis.StopAnalyzer
 * this class add a filter that remove accents
 * 
 * @author davidabad
 */
public class MyFrenchAnalyzer extends MyAnalyzer {
	protected static Log logger = LogFactory.getLog(MyFrenchAnalyzer.class);

	protected Set<?> stopWords;

	/** Builds an analyzer which removes words in FRENCH_STOP_WORDS. */
	public MyFrenchAnalyzer() {
		stopWords = StopFilter.makeStopSet(FrenchAnalyzer.FRENCH_STOP_WORDS);
	}

}
