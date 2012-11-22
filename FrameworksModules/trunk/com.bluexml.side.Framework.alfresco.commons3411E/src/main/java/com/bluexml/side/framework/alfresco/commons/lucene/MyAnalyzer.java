package com.bluexml.side.framework.alfresco.commons.lucene;

import java.io.IOException;
import java.io.Reader;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.ISOLatin1AccentFilter;
import org.apache.lucene.analysis.LowerCaseTokenizer;
import org.apache.lucene.analysis.StopFilter;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.Tokenizer;

/**
 * Original code from Code from org.apache.lucene.analysis.StopAnalyzer
 * this class add a filter that remove accents
 * 
 * @author davidabad
 */
public class MyAnalyzer extends Analyzer {
	protected static Log logger = LogFactory.getLog(MyAnalyzer.class);

	protected Set<?> stopWords;

	/** Builds an analyzer without stop words but that remove accents */
	public MyAnalyzer() {
		stopWords = StopFilter.makeStopSet(new String[] {});
	}

	/** Filters LowerCaseTokenizer with StopFilter. */
	public TokenStream tokenStream(String fieldName, Reader reader) {
		TokenStream stopFilter = new StopFilter(new LowerCaseTokenizer(reader), stopWords);
		stopFilter = new ISOLatin1AccentFilter(stopFilter);
		return stopFilter;
	}

	/** Filters LowerCaseTokenizer with StopFilter. */
	private class SavedStreams {
		Tokenizer source;
		TokenStream result;
	};

	public TokenStream reusableTokenStream(String fieldName, Reader reader) throws IOException {
		SavedStreams streams = (SavedStreams) getPreviousTokenStream();
		if (streams == null) {
			streams = new SavedStreams();
			streams.source = new LowerCaseTokenizer(reader);
			streams.result = new StopFilter(streams.source, stopWords);
			streams.result = new ISOLatin1AccentFilter(streams.result);
			setPreviousTokenStream(streams);
		} else
			streams.source.reset(reader);
		return streams.result;
	}
}
