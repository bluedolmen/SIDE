package com.bluexml.tools.miscellaneous;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

public class ReadAlfrescoPermission {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JAXBContext jaxbContext;
		try {
			jaxbContext = JAXBContext.newInstance("com.bluexml.side.alfresco.binding", ReadAlfrescoPermission.class.getClassLoader());
			Unmarshaller unm = jaxbContext.createUnmarshaller();
			Object root = unm.unmarshal(new File(args[0]));
			com.bluexml.side.alfresco.binding.Permissions alfModel = (com.bluexml.side.alfresco.binding.Permissions) root;
			
			//  TODO build .dot file following permissions map
			
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
