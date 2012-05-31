package com.bluexml.side.portal.alfresco.reverse.reverser;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import org.eclipse.emf.common.util.EList;
import org.w3c.dom.Element;

import com.bluexml.side.alfresco.share.instances.binding.Component;
import com.bluexml.side.alfresco.share.instances.binding.Components;
import com.bluexml.side.alfresco.share.instances.binding.Evaluation;
import com.bluexml.side.alfresco.share.instances.binding.Evaluations;
import com.bluexml.side.alfresco.share.instances.binding.Evaluator;
import com.bluexml.side.alfresco.share.instances.binding.Evaluators;
import com.bluexml.side.alfresco.share.instances.binding.Params;
import com.bluexml.side.alfresco.share.instances.binding.Properties;
import com.bluexml.side.alfresco.share.instances.binding.SubComponent;
import com.bluexml.side.alfresco.share.instances.binding.SubComponents;
import com.bluexml.side.alfresco.share.instances.binding.TemplateInstance;
import com.bluexml.side.common.Visibility;
import com.bluexml.side.portal.Column;
import com.bluexml.side.portal.Page;
import com.bluexml.side.portal.Portal;
import com.bluexml.side.portal.PortalLayout;
import com.bluexml.side.portal.Portlet;
import com.bluexml.side.portal.helper.PortalHelper;
import com.bluexml.side.util.libs.FileHelper;

public class EclipseReverser {
	HashMap<File, TemplateInstance> models = new HashMap<File, TemplateInstance>();
	HashMap<TemplateInstance, File> outputFiles = new HashMap<TemplateInstance, File>();
	Portal rootObject = null;
	JAXBContext jaxbContext = null;

	TemplateInstance model = null;
	String pageName;
	PortalLayout portalLayout;

	public EclipseReverser(File templateInstance, Portal portal, String pageName) throws Exception {
		this.rootObject = portal;

		if (templateInstance.exists() && templateInstance.isFile() && FileHelper.getFileExt(templateInstance).equals("xml")) {
			System.out.println("open :" + templateInstance.getName());
			this.pageName = pageName;
			jaxbContext = JAXBContext.newInstance("com.bluexml.side.alfresco.share.instances.binding", EclipseReverser.class.getClassLoader());
			Unmarshaller unm = jaxbContext.createUnmarshaller();
			Object root = unm.unmarshal(templateInstance);
			model = (TemplateInstance) root;

		} else {
			throw new Exception("bad file :" + templateInstance);
		}
	}

	public Page reverse(PortalLayout layout, Components fromPage) throws Exception {
		this.portalLayout = layout;
		String id = pageName;

		Visibility visibility = Visibility.PUBLIC;
		boolean generate = true;
		Page page = PortalHelper.createPage(rootObject, portalLayout, generate, id, visibility);

		Properties properties = model.getProperties();

		Map<String, String> props = new HashMap<String, String>();
		handleProperties(props, properties);

		PortalHelper.createMetaInfos(props, page, false, "prop_");

		Components components2 = model.getComponents();
		reverseComponents(page, fromPage);
		reverseComponents(page, components2);
		return page;
	}

	public void reverseComponents(Page page, Components components2) throws Exception {
		if (components2 != null) {
			List<Component> components = components2.getComponent();
			for (Component component : components) {
				String portletName = component.getRegionId();
				String componentURL = component.getUrl();

				Map<String, String> props = new TreeMap<String, String>();
				props.put("url", componentURL);
				props.put("scope", "template");

				Properties properties = component.getProperties();

				handleProperties(props, properties);

				Portlet createPortlet = PortalHelper.createPortlet(rootObject, portletName, props);

				handleSubComponents(component, portletName, createPortlet);

				PortalHelper.createHavePortlet(portalLayout, getMatchingColumn(portletName), 1, page, createPortlet);
			}
		}
	}

	public void handleSubComponents(Component component, String portletName, Portlet createPortlet) throws Exception {
		// search for sub-components
		SubComponents subComponentsE = component.getSubComponents();

		if (subComponentsE != null) {
			System.out.println("EclipseReverser.reverseComponents() subPortlet ... for :" + portletName);

			// no mapping exists in Portal MM so we use metainfo injection
			//						MetaInfo createMetaInfo = CommonFactory.eINSTANCE.createMetaInfo();
			//						// do not confound rawConfig with rawContent the old rawContent is now in Layout.columns
			//						// rawConfig is copy of 
			//						createMetaInfo.setKey("rawConfig");
			//						StringWriter stringWriter = new StringWriter();
			//						jaxbContext.createMarshaller().marshal(component, stringWriter);
			//						stringWriter.flush();
			//						// remove <?xml version="1.0" encoding="UTF-8" standalone="yes"?>
			//						createMetaInfo.setMultilineValue(stringWriter.toString().substring(55));
			//						createPortlet.getMetainfo().add(createMetaInfo);

			// create subPortelts
			List<SubComponent> subComponents = subComponentsE.getSubComponent();
			for (SubComponent subComponent : subComponents) {
				String id2 = subComponent.getId(); // Meta-info

				Evaluations evaluationsE = subComponent.getEvaluations();
				if (evaluationsE != null) {

					for (Evaluation evaluation : evaluationsE.getEvaluation()) {

						// subportlet
						String url = evaluation.getUrl();
						Properties properties2 = evaluation.getProperties();
						Map<String, String> subPortelt_props = new HashMap<String, String>();
						subPortelt_props.put("url", url);
						handleProperties(subPortelt_props, properties2);
						Portlet subcreatePortlet = PortalHelper.createPortlet(rootObject, portletName, subPortelt_props);

						String evaluator_id = evaluation.getId(); // Meta-info
						PortalHelper.createMetaInfo(subcreatePortlet, "evaluator_id", evaluator_id, false);
						PortalHelper.createMetaInfo(subcreatePortlet, "subComponent_id", id2, false);
						Evaluators evaluatorsE = evaluation.getEvaluators(); // Meta-info
						if (evaluatorsE != null) {
							Evaluator evaluator = evaluatorsE.getEvaluator();
							String evaluator_type = evaluator.getType();
							PortalHelper.createMetaInfo(subcreatePortlet, "evaluator_type", evaluator_type, false);
							Params params = evaluator.getParams();
							if (params != null) {
								System.out.println("EclipseReverser.handleSubComponents() params");
								String element = params.getElement();
								PortalHelper.createMetaInfo(subcreatePortlet, "params-element", element, false);
							}

						}

						createPortlet.getSubPortlets().add(subcreatePortlet);

					}
				}
			}
		}
	}

	public void handleProperties(Map<String, String> props, Properties properties) {
		if (properties != null) {
			List<Object> any = properties.getAny();
			for (Object object : any) {
				if (object instanceof Element) {
					System.out.println(" any Element (w3c) ?" + object);
					Element el = (Element) object;
					String nodeName = el.getNodeName();
					String nodeValue = el.getTextContent();
					props.put(nodeName, nodeValue);
				}
			}
		}
	}

	public Column getMatchingColumn(String portletName) {
		EList<Column> allColumns = portalLayout.getAllColumns();
		for (Column column : allColumns) {
			if (column.getName().equals(portletName)) {
				return column;
			}
		}

		return null;
	}
}
