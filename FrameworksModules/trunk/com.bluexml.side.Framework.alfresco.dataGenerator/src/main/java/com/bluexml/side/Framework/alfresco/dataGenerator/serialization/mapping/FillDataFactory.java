/**
 * 
 */
package com.bluexml.side.Framework.alfresco.dataGenerator.serialization.mapping;

import java.util.Random;

/**
 * @author davidchevrier
 *
 */
public class FillDataFactory extends AbstractXmlOutput{
	
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

	public StringBuffer fillNativeAspects(Object dataAspect) {
		xmlOutPut = clear();
		xmlOutPut.append(dataAspect.toString());
		return xmlOutPut;
	}

}
