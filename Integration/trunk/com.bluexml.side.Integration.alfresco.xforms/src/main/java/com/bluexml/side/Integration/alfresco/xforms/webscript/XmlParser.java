/**
 * 
 */
package com.bluexml.side.Integration.alfresco.xforms.webscript;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.w3c.dom.Element;

import com.bluexml.side.form.utils.DOMUtil;

/**
 * Builds a map of standard properties for an object. Taken from BxDS dataLayer module.
 * This uses the definition in Class.xsd
 * 
 * @author Amenel
 * 
 */
public class XmlParser {

	public String getQualifiedName(Element e) {
		return e.getAttribute("qualifiedName");
	}

	public Map<String, Object> parse(DataLayer dataLayer, Element element) throws Exception {
		Map<String, Object> objectModel = new HashMap<String, Object>();

		String qualifiedName = getQualifiedName(element);
		objectModel.put("dataType", qualifiedName);

		// collect attributes
		Element attributeContainer = DOMUtil.getChild(element, "attributes");
		Map<String, Object> attrs = new HashMap<String, Object>();
		if (attributeContainer != null) {
			List<Element> attributes = DOMUtil.getChildren(attributeContainer, "attribute");
			for (Element e : attributes) {
				// only legitimate attributes should find their way into the attributes map
				if (StringUtils.trimToNull(e.getAttribute("skipMe")) == null) {
					String attributeName = getQualifiedName(e);
					List<Element> value = DOMUtil.getChildren(e, "value");

					if (value.size() == 1) {
						String stringValue = value.get(0).getTextContent();
						attrs.put(attributeName, stringValue);
					} else if (value.size() > 1) {
						List<String> values = new ArrayList<String>(value.size());
						for (Element valueElement : value) {
							String stringValue = StringUtils.trimToEmpty(valueElement
									.getTextContent());
							values.add(stringValue);
						}
						attrs.put(attributeName, values);
					}
				}
			}
		}
		objectModel.put("attributes", attrs);

		// collect associations
		Element associationsContener = DOMUtil.getChild(element, "associations");

		List<AssociationBean> assos = new ArrayList<AssociationBean>();
		if (associationsContener != null) {
			String associationsAction = associationsContener.getAttribute("action");
			if (StringUtils.trimToNull(associationsAction) != null) {
				objectModel.put("associationsAction", associationsAction);
			}
			List<Element> associations = DOMUtil.getChildren(associationsContener, "association");
			for (Element e : associations) {
				AssociationBean association = new AssociationBean();
				association.setAssociationName(getQualifiedName(e));
				Element target = DOMUtil.getChild(e, "target");
				String action = e.getAttribute("action");
				if (StringUtils.trimToNull(action) != null) {
					association.setAction(AssociationBean.Actions.valueOf(action));
				}
				if (target != null) {
					String targetRef = target.getTextContent();
					String targetQualifiedName = getQualifiedName(target);
					association.setTargetQualifiedName(targetQualifiedName);
					association.setTargetId(targetRef);
				}
				assos.add(association);
			}
		}
		objectModel.put("associations", assos);

		return objectModel;
	}
}
