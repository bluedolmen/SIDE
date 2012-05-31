package com.bluexml.side.portal.alfresco.reverse.reverser;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.namespace.QName;

import com.bluexml.side.alfresco.share.all.binding.Components;
import com.bluexml.side.alfresco.share.all.binding.Page;
import com.bluexml.side.alfresco.share.all.binding.Properties;
import com.bluexml.side.portal.Portal;
import com.bluexml.side.portal.PortalFactory;
import com.bluexml.side.portal.PortalLayout;
import com.bluexml.side.portal.alfresco.reverse.reverser.data.Region;
import com.bluexml.side.portal.helper.PortalHelper;
import com.bluexml.side.util.libs.ecore.EResourceUtils;

public class ReversePortal {

	public final static String PATH_SITE_DATA = "site-data";
	public final static String PATH_PAGES = PATH_SITE_DATA + File.separator + "pages";
	public final static String PATH_COMPONENTS = PATH_SITE_DATA + File.separator + "components";
	public final static String PATH_INSTANCES = PATH_SITE_DATA + File.separator + "template-instances";
	public final static String PATH_TEMPLATE_TYPES = PATH_SITE_DATA + File.separator + "template-types";
	public final static String PATH_PAGE_TEMPLATES = "templates";

	File pages;
	File instances;
	File components;
	File outputDir;
	Portal portal;
	String portalName = "alfrescoShare";
	File templates;
	JAXBContext jaxbContext;
	Unmarshaller unm;
	File templates_types;

	public ReversePortal(File home, File outputDir) throws JAXBException {
		pages = new File(home, PATH_PAGES);
		components = new File(home, PATH_COMPONENTS);
		instances = new File(home, PATH_INSTANCES);
		templates = new File(home, PATH_PAGE_TEMPLATES);
		templates_types = new File(home, PATH_TEMPLATE_TYPES);
		this.outputDir = outputDir;

		jaxbContext = JAXBContext.newInstance("com.bluexml.side.alfresco.share.all.binding", EclipseReverser.class.getClassLoader());
		unm = jaxbContext.createUnmarshaller();
	}

	public Portal reverse() throws Exception {
		portal = PortalFactory.eINSTANCE.createPortal();
		portal.setName(portalName);
		// get Page
		FilenameFilter filter = new FilenameFilter() {

			@Override
			public boolean accept(File arg0, String arg1) {

				return arg1.toLowerCase().endsWith(".xml");
			}
		};

		for (File page : this.pages.listFiles(filter)) {

			String name = page.getName().replace(".xml", "");
			// extract page information
			Page p = loadPage(page);
			List<Object> content = p.getTemplateInstance().getContent();

			String templateInstance = getValueString(content);

			File templateInstanceFile = getTemplateInstanceFile(templateInstance);
			EclipseReverser er = new EclipseReverser(templateInstanceFile, portal, name, components);
			//er.model.getTemplateType()
			String templateType = getValueJaxBE(er.model.getContent(), "template-type");

			File templateFile = getTemplateFile(templateType);

			if (templateInstanceFile.exists() && templateFile.exists()) {
				// revert pages layouts
				LayoutReverser lr = new LayoutReverser(templateFile, portal, name);
				PortalLayout layout = lr.parse();

				// each region must match to a component
				// components can be defined in tree places ... hum
				// SIDE-DATA/components, into page definition and into template-instance definition
				List<Region> regions = lr.getRegions();

				// reverse pages instances

				com.bluexml.side.portal.Page reverse = er.reverse(layout, p.getComponents(), regions);
				// search for existing js file
				String jsFileName = templateFile.getName().replace(".ftl", ".js");
				File file = new File(templateFile.getParentFile(), jsFileName);
				if (file.exists()) {
					System.out.println("ReversePortal.reverse() page have a js file");
					PortalHelper.createMetaInfo(reverse, "rawContentJs", "<import resource=\"classpath:/alfresco/templates/org/alfresco/" + jsFileName + "\">", true);
				}
			}

		}

		return portal;
	}

	public static Components getValueComponents(List<Object> content) {

		for (Object object : content) {
			if (object instanceof Components) {
				return (Components) object;
			}
		}
		return null;
	}

	public static String getValueString(List<Object> content) {
		for (Object object : content) {
			if (object instanceof String) {
				return (String) object;
			}
		}
		return null;
	}

	public static Properties getValueProperties(List<Object> content) {
		for (Object object : content) {
			if (object instanceof Properties) {
				return (Properties) object;
			}
		}
		return null;
	}

	public static String getValueJaxBE(List<Object> content, String tagName) {
		for (Object object : content) {
			if (object instanceof JAXBElement) {
				JAXBElement<String> jaxbE = (JAXBElement<String>) object;
				QName name = jaxbE.getName();
				if (name.getLocalPart().equals(tagName)) {
					return jaxbE.getValue();
				}

			}
		}
		return null;
	}

	public File getTemplateFile(String templateType) {
		return new File(templates.getAbsolutePath() + File.separator + templateType + ".ftl");
	}

	public File getTemplateInstanceFile(String templateInstance) {
		return new File(instances, templateInstance + ".xml");
	}

	public void persist() throws IOException {
		File file = new File(outputDir, "alfresco.portal");
		file.getParentFile().mkdirs();
		file.createNewFile();
		System.out.println("save model :" + file);
		EResourceUtils.saveModel(file, portal);
	}

	public Page loadPage(File page) throws JAXBException {
		Object root = unm.unmarshal(page);
		return (Page) root;

	}
}
