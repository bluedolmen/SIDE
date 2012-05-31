package com.bluexml.side.alfresco.share.page.binding;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.xml.namespace.QName;

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

/**
 * This object contains factory methods for each
 * Java content interface and Java element interface
 * generated in the com.bluexml.side.alfresco.share.page.binding package.
 * <p>
 * An ObjectFactory allows you to programatically construct new instances of the
 * Java representation for XML content. The Java representation of XML content
 * can consist of schema derived interfaces and classes representing the binding
 * of schema type definitions, element declarations and model groups. Factory
 * methods for each of these are provided in this class.
 */
@XmlRegistry
public class ObjectFactory {

	private final static QName _Id_QNAME = new QName("", "id");

	private final static QName _Title_QNAME = new QName("", "title");
	private final static QName _TemplateInstance_QNAME = new QName("", "template-instance");
	private final static QName _TitleId_QNAME = new QName("", "title-id");
	private final static QName _Authentication_QNAME = new QName("", "authentication");
	private final static QName _Description_QNAME = new QName("", "description");
	private final static QName _DescriptionId_QNAME = new QName("", "description-id");
	private final static QName _PageType_QNAME = new QName("", "page-type");

	/**
	 * Create a new ObjectFactory that can be used to create new instances of
	 * schema derived classes for package:
	 * com.bluexml.side.alfresco.share.page.binding
	 */
	public ObjectFactory() {
	}

	/**
	 * Create an instance of {@link Component }
	 */
	public Component createComponent() {
		return new Component();
	}

	/**
	 * Create an instance of {@link Evaluators }
	 */
	public Evaluators createEvaluators() {
		return new Evaluators();
	}

	/**
	 * Create an instance of {@link Evaluation }
	 */
	public Evaluation createEvaluation() {
		return new Evaluation();
	}

	/**
	 * Create an instance of {@link Properties }
	 */
	public Properties createProperties() {
		return new Properties();
	}

	/**
	 * Create an instance of {@link Page }
	 */
	public Page createPage() {
		return new Page();
	}

	/**
	 * Create an instance of {@link Evaluations }
	 */
	public Evaluations createEvaluations() {
		return new Evaluations();
	}

	/**
	 * Create an instance of {@link Components }
	 */
	public Components createComponents() {
		return new Components();
	}

	/**
	 * Create an instance of {@link Params }
	 */
	public Params createParams() {
		return new Params();
	}

	/**
	 * Create an instance of {@link Evaluator }
	 */
	public Evaluator createEvaluator() {
		return new Evaluator();
	}

	/**
	 * Create an instance of {@link SubComponent }
	 */
	public SubComponent createSubComponent() {
		return new SubComponent();
	}

	/**
	 * Create an instance of {@link SubComponents }
	 */
	public SubComponents createSubComponents() {
		return new SubComponents();
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
	 */
	@XmlElementDecl(namespace = "", name = "id")
	@XmlJavaTypeAdapter(CollapsedStringAdapter.class)
	public JAXBElement<String> createId(String value) {
		return new JAXBElement<String>(_Id_QNAME, String.class, null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
	 */
	@XmlElementDecl(namespace = "", name = "title")
	public JAXBElement<String> createTitle(String value) {
		return new JAXBElement<String>(_Title_QNAME, String.class, null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
	 */
	@XmlElementDecl(namespace = "", name = "template-instance")
	@XmlJavaTypeAdapter(CollapsedStringAdapter.class)
	public JAXBElement<String> createTemplateInstance(String value) {
		return new JAXBElement<String>(_TemplateInstance_QNAME, String.class, null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
	 */
	@XmlElementDecl(namespace = "", name = "title-id")
	@XmlJavaTypeAdapter(CollapsedStringAdapter.class)
	public JAXBElement<String> createTitleId(String value) {
		return new JAXBElement<String>(_TitleId_QNAME, String.class, null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
	 */
	@XmlElementDecl(namespace = "", name = "authentication")
	@XmlJavaTypeAdapter(CollapsedStringAdapter.class)
	public JAXBElement<String> createAuthentication(String value) {
		return new JAXBElement<String>(_Authentication_QNAME, String.class, null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
	 */
	@XmlElementDecl(namespace = "", name = "description")
	public JAXBElement<String> createDescription(String value) {
		return new JAXBElement<String>(_Description_QNAME, String.class, null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
	 */
	@XmlElementDecl(namespace = "", name = "description-id")
	@XmlJavaTypeAdapter(CollapsedStringAdapter.class)
	public JAXBElement<String> createDescriptionId(String value) {
		return new JAXBElement<String>(_DescriptionId_QNAME, String.class, null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
	 */
	@XmlElementDecl(namespace = "", name = "page-type")
	@XmlJavaTypeAdapter(CollapsedStringAdapter.class)
	public JAXBElement<String> createPageType(String value) {
		return new JAXBElement<String>(_PageType_QNAME, String.class, null, value);
	}

}
