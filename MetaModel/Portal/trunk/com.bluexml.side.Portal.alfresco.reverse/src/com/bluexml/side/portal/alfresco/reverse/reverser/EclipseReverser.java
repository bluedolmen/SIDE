package com.bluexml.side.portal.alfresco.reverse.reverser;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.Unmarshaller;
import javax.xml.namespace.QName;

import org.apache.commons.lang.StringUtils;
import org.eclipse.emf.common.util.EList;
import org.w3c.dom.Element;

import com.bluexml.side.alfresco.share.all.binding.Component;
import com.bluexml.side.alfresco.share.all.binding.Components;
import com.bluexml.side.alfresco.share.all.binding.Evaluation;
import com.bluexml.side.alfresco.share.all.binding.Evaluations;
import com.bluexml.side.alfresco.share.all.binding.Evaluator;
import com.bluexml.side.alfresco.share.all.binding.Evaluators;
import com.bluexml.side.alfresco.share.all.binding.Params;
import com.bluexml.side.alfresco.share.all.binding.Properties;
import com.bluexml.side.alfresco.share.all.binding.SubComponent;
import com.bluexml.side.alfresco.share.all.binding.SubComponents;
import com.bluexml.side.alfresco.share.all.binding.TemplateInstance;
import com.bluexml.side.common.Visibility;
import com.bluexml.side.portal.Column;
import com.bluexml.side.portal.Page;
import com.bluexml.side.portal.Portal;
import com.bluexml.side.portal.PortalLayout;
import com.bluexml.side.portal.Portlet;
import com.bluexml.side.portal.alfresco.reverse.reverser.data.Region;
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
	File components = null;

	public EclipseReverser(File templateInstance, Portal portal, String pageName, File components) throws Exception {
		this.rootObject = portal;
		this.components = components;

		if (templateInstance.exists() && templateInstance.isFile() && FileHelper.getFileExt(templateInstance).equals("xml")) {
			System.out.println("open :" + templateInstance.getName());
			this.pageName = pageName;
			jaxbContext = JAXBContext.newInstance("com.bluexml.side.alfresco.share.all.binding", EclipseReverser.class.getClassLoader());
			Unmarshaller unm = jaxbContext.createUnmarshaller();
			Object root = unm.unmarshal(templateInstance);
			model = (TemplateInstance) root;

		} else {
			throw new Exception("bad file :" + templateInstance);
		}
	}

	public Page reverse(PortalLayout layout, Components fromPage, List<Region> regions) throws Exception {
		this.portalLayout = layout;
		String id = pageName;

		Visibility visibility = Visibility.PUBLIC;
		boolean generate = true;
		Page page = PortalHelper.createPage(rootObject, portalLayout, generate, id, visibility);

		Properties properties = ReversePortal.getValueProperties(model.getContent());

		Map<String, String> props = new HashMap<String, String>();
		handleProperties(props, properties);

		PortalHelper.createMetaInfos(props, page, false, "prop_");

		Components fromInstance = ReversePortal.getValueComponents(model.getContent());

		for (Region region : regions) {
			Component component = getComponent(region, fromInstance, fromPage);
			if (component != null) {
				handleComponent(page, component);
			} else {
				System.err.println("Missing component for region :" + region.getRegionId() + " scope:" + region.getScope());
			}
		}

		return page;
	}

	public void handleComponent(Page page, Component component) throws Exception {
		String portletName = component.getRegionId();
		String componentURL = component.getUrl();
		String sourceId = component.getSourceId();
		String scope = component.getScope() != null ? component.getScope() : "template";
		Map<String, String> props = new TreeMap<String, String>();
		if (StringUtils.trimToNull(componentURL) != null) {
			props.put("url", componentURL);
		}
		props.put("scope", scope);

		Properties properties = component.getProperties();

		handleProperties(props, properties);

		Portlet createPortlet = PortalHelper.createPortlet(rootObject, portletName, props);

		handleSubComponents(component, portletName, createPortlet);

		PortalHelper.createHavePortlet(portalLayout, getMatchingColumn(portletName), 1, page, createPortlet);
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
				Portlet subcreatePortlet_ = PortalHelper.createPortlet(rootObject, portletName);
				PortalHelper.createMetaInfo(subcreatePortlet_, "subComponent_id", id2, false);
				createPortlet.getSubPortlets().add(subcreatePortlet_);
				Evaluations evaluationsE = subComponent.getEvaluations();
				if (evaluationsE != null) {

					for (Evaluation evaluation : evaluationsE.getEvaluation()) {
						// evaluation data
						String evaluation_id = evaluation.getId(); // Meta-info
						String url = evaluation.getUrl();
						Properties properties2 = evaluation.getProperties();
						Map<String, String> subPortelt_props = new HashMap<String, String>();
						subPortelt_props.put("url", url);
						handleProperties(subPortelt_props, properties2);
						Portlet subcreatePortlet = PortalHelper.createPortlet(rootObject, portletName, subPortelt_props);

						PortalHelper.createMetaInfo(subcreatePortlet, "evaluation_id", evaluation_id, false);

						Evaluators evaluatorsE = evaluation.getEvaluators(); // Meta-info
						if (evaluatorsE != null) {
							List<Evaluator> evaluators = evaluatorsE.getEvaluator();
							for (Evaluator evaluator : evaluators) {
								String evaluator_type = evaluator.getType();

								PortalHelper.createMetaInfo(subcreatePortlet, "evaluator_type", evaluator_type, false);
								Params params = evaluator.getParams();
								if (params != null) {
									System.out.println("EclipseReverser.handleSubComponents() params");
									List<Object> any = params.getAny();
									Map<String, String> paramsMap = new HashMap<String, String>();
									readAnyElements(paramsMap, any);
									PortalHelper.createMetaInfos(paramsMap, subcreatePortlet, false, "params-");
								}
							}
						}

						subcreatePortlet_.getSubPortlets().add(subcreatePortlet);

					}
				}
			}
		}
	}

	public void handleProperties(Map<String, String> props, Properties properties) {
		if (properties != null) {
			List<Object> any = properties.getAny();
			readAnyElements(props, any);
		}
	}

	protected void readAnyElements(Map<String, String> props, List<Object> any) {
		for (Object object : any) {
			String nodeName = null;
			String nodeValue = null;
			if (object instanceof Element) {
				System.out.println(" any Element (w3c) ?" + object);
				Element el = (Element) object;
				nodeName = el.getNodeName();
				nodeValue = el.getTextContent();
				props.put(nodeName, nodeValue);
			} else if (object instanceof JAXBElement) {
				JAXBElement<String> jaxbE = (JAXBElement<String>) object;
				QName name = jaxbE.getName();
				nodeName = name.getLocalPart();
				nodeValue = jaxbE.getValue();
			}

			props.put(nodeName, nodeValue);
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

	public Component getComponent(Region region, Components fromInstance, Components fromPage) throws Exception {
		Component com = null;
		// maybe in page components or template-instances components or finally in SIDE-DATA/component directory

		com = searchComponent(region, fromInstance);

		if (com == null) {
			com = searchComponent(region, fromPage);
		}

		if (com == null) {
			String regionId = region.getRegionId();
			String scope = region.getScope();
			String fileName = "";
			if (scope.equals("global")) {
				fileName = scope + "." + regionId + ".xml";
			} else {
				fileName = scope + "." + this.pageName + "." + regionId + ".xml";
			}
			// need to search in site-data/components
			File componentFile = null;
			File[] listFiles = components.listFiles();
			for (File file : listFiles) {
				if (file.getName().equals(fileName)) {
					componentFile = file;
				}
			}

			if (componentFile != null && componentFile.exists()) {
				// read component configuration
				Unmarshaller createUnmarshaller = jaxbContext.createUnmarshaller();
				com = (Component) createUnmarshaller.unmarshal(componentFile);
			}
		}
		// fill scope if not provided
		if (com != null && StringUtils.trimToNull(com.getScope()) == null) {
			com.setScope(region.getScope());
		}
		return com;
	}

	protected Component searchComponent(Region region, Components fromInstance) throws Exception {
		Component com = null;
		if (fromInstance != null) {
			List<Component> components = fromInstance.getComponent();
			for (Component component : components) {
				String regionId = region.getRegionId();
				if (regionId.equals(component.getRegionId())) {
					if (com == null) {
						com = component;
					} else {
						throw new Exception("hum more than one component found for this id :" + regionId);
					}
				}
			}
		}
		return com;
	}
}
