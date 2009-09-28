/**
 * 
 */
package com.bluexml.side.framework.alfresco.datasGenerator.serialization.mapping;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;

/**
 * @author davidchevrier
 *
 */
public class FillDatasFactory extends AbstractXmlOutput{
	
	private Random randomGenerator;
	
	public void init(){
		xmlOutPut = new StringBuffer();
		randomGenerator = new Random();
	}

	/**
	 * @return the randomGenerator
	 */
	public Random getRandomGenerator() {
		return randomGenerator;
	}

	/**
	 * @param randomGenerator the randomGenerator to set
	 */
	public void setRandomGenerator(Random randomGenerator) {
		this.randomGenerator = randomGenerator;
	}

	public StringBuffer fillPropertyTag(Object data) {
		xmlOutPut = clear();
		xmlOutPut.append(data);	
		return xmlOutPut;
	}

	public StringBuffer fillNativePropertyTag(Object dataProperty) {
		xmlOutPut = clear();
		xmlOutPut.append(dataProperty.toString());
		return xmlOutPut;
	}

	public StringBuffer fillAclTag() {
		xmlOutPut = clear();
		xmlOutPut.append("");
		return xmlOutPut;
	}

}
