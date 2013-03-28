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
