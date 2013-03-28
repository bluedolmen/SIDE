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
