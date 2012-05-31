
package com.bluexml.side.alfresco.share.instances.binding;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.bluexml.side.alfresco.share.instances.binding package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    public final static QName _RegionId_QNAME = new QName("", "region-id");
    public final static QName _Element_QNAME = new QName("", "element");
    private final static QName _TemplateType_QNAME = new QName("", "template-type");
    public final static QName _Url_QNAME = new QName("", "url");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.bluexml.side.alfresco.share.instances.binding
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link SubComponents }
     * 
     */
    public SubComponents createSubComponents() {
        return new SubComponents();
    }

    /**
     * Create an instance of {@link SubComponent }
     * 
     */
    public SubComponent createSubComponent() {
        return new SubComponent();
    }

    /**
     * Create an instance of {@link Component }
     * 
     */
    public Component createComponent() {
        return new Component();
    }

    /**
     * Create an instance of {@link Evaluator }
     * 
     */
    public Evaluator createEvaluator() {
        return new Evaluator();
    }

    /**
     * Create an instance of {@link Evaluators }
     * 
     */
    public Evaluators createEvaluators() {
        return new Evaluators();
    }

    /**
     * Create an instance of {@link Evaluation }
     * 
     */
    public Evaluation createEvaluation() {
        return new Evaluation();
    }

    /**
     * Create an instance of {@link Evaluations }
     * 
     */
    public Evaluations createEvaluations() {
        return new Evaluations();
    }

    /**
     * Create an instance of {@link Components }
     * 
     */
    public Components createComponents() {
        return new Components();
    }

    /**
     * Create an instance of {@link Properties }
     * 
     */
    public Properties createProperties() {
        return new Properties();
    }

    /**
     * Create an instance of {@link TemplateInstance }
     * 
     */
    public TemplateInstance createTemplateInstance() {
        return new TemplateInstance();
    }

    /**
     * Create an instance of {@link Params }
     * 
     */
    public Params createParams() {
        return new Params();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "region-id")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    public JAXBElement<String> createRegionId(String value) {
        return new JAXBElement<String>(_RegionId_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "element")
    public JAXBElement<String> createElement(String value) {
        return new JAXBElement<String>(_Element_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "template-type")
    public JAXBElement<String> createTemplateType(String value) {
        return new JAXBElement<String>(_TemplateType_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "url")
    public JAXBElement<String> createUrl(String value) {
        return new JAXBElement<String>(_Url_QNAME, String.class, null, value);
    }

}
