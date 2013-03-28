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
package com.bluexml.side.util.libs.maven;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import com.bluexml.side.util.libs.maven.pom.Model;

public class PomHelper {

	private static final String JAXB_PACKAGE = "com.bluexml.side.util.libs.maven.pom";

	public static JAXBElement<Model> load(File file) throws JAXBException {

		JAXBContext jaxbContext = JAXBContext.newInstance(JAXB_PACKAGE, PomHelper.class.getClassLoader());
		Unmarshaller unm = jaxbContext.createUnmarshaller();
		Object object = unm.unmarshal(file);
		return (JAXBElement<Model>) object;
	}

	public static void save(JAXBElement<Model> project, File file) throws JAXBException {
		JAXBContext jaxbContext = JAXBContext.newInstance(JAXB_PACKAGE);

		Marshaller alfrescoMarshaller = jaxbContext.createMarshaller();
		alfrescoMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
		alfrescoMarshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
		alfrescoMarshaller.setProperty(Marshaller.JAXB_SCHEMA_LOCATION, "http://maven.apache.org/POM/4.0.0 http://maven.apache.org/maven-v4_0_0.xsd");
		alfrescoMarshaller.marshal(project, file);
	}
}
