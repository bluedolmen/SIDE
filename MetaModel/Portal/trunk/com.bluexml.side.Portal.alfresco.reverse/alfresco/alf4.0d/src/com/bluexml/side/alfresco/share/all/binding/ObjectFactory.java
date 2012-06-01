
package com.bluexml.side.alfresco.share.all.binding;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.bluexml.side.alfresco.share.all.binding package. 
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

    private final static QName _Element_QNAME = new QName("", "element");
    private final static QName _SourceId_QNAME = new QName("", "source-id");
    private final static QName _Scope_QNAME = new QName("", "scope");
    private final static QName _DescriptionId_QNAME = new QName("", "description-id");
    private final static QName _RootLabelId_QNAME = new QName("", "rootLabelId");
    private final static QName _Url_QNAME = new QName("", "url");
    private final static QName _EventData_QNAME = new QName("", "eventData");
    private final static QName _PageType_QNAME = new QName("", "page-type");
    private final static QName _Id_QNAME = new QName("", "id");
    private final static QName _RootPage_QNAME = new QName("", "rootPage");
    private final static QName _RegionId_QNAME = new QName("", "region-id");
    private final static QName _Title_QNAME = new QName("", "title");
    private final static QName _TitleId_QNAME = new QName("", "title-id");
    private final static QName _Container_QNAME = new QName("", "container");
    private final static QName _Authentication_QNAME = new QName("", "authentication");
    private final static QName _Description_QNAME = new QName("", "description");
    private final static QName _Subtitle_QNAME = new QName("", "subtitle");
    private final static QName _NodeRef_QNAME = new QName("", "nodeRef");
    private final static QName _EventName_QNAME = new QName("", "eventName");
    private final static QName _ActivityType_QNAME = new QName("", "activityType");
    private final static QName _TemplateType_QNAME = new QName("", "template-type");
    private final static QName _PageFamily_QNAME = new QName("", "pageFamily");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.bluexml.side.alfresco.share.all.binding
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link SubComponent }
     * 
     */
    public SubComponent createSubComponent() {
        return new SubComponent();
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
     * Create an instance of {@link Components }
     * 
     */
    public Components createComponents() {
        return new Components();
    }

    /**
     * Create an instance of {@link Page }
     * 
     */
    public Page createPage() {
        return new Page();
    }

    /**
     * Create an instance of {@link Evaluations }
     * 
     */
    public Evaluations createEvaluations() {
        return new Evaluations();
    }

    /**
     * Create an instance of {@link Evaluator }
     * 
     */
    public Evaluator createEvaluator() {
        return new Evaluator();
    }

    /**
     * Create an instance of {@link SubComponents }
     * 
     */
    public SubComponents createSubComponents() {
        return new SubComponents();
    }

    /**
     * Create an instance of {@link Evaluation }
     * 
     */
    public Evaluation createEvaluation() {
        return new Evaluation();
    }

    /**
     * Create an instance of {@link Component }
     * 
     */
    public Component createComponent() {
        return new Component();
    }

    /**
     * Create an instance of {@link Properties }
     * 
     */
    public Properties createProperties() {
        return new Properties();
    }

    /**
     * Create an instance of {@link Evaluators }
     * 
     */
    public Evaluators createEvaluators() {
        return new Evaluators();
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
    @XmlElementDecl(namespace = "", name = "source-id")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    public JAXBElement<String> createSourceId(String value) {
        return new JAXBElement<String>(_SourceId_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "scope")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    public JAXBElement<String> createScope(String value) {
        return new JAXBElement<String>(_Scope_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "description-id")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    public JAXBElement<String> createDescriptionId(String value) {
        return new JAXBElement<String>(_DescriptionId_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "rootLabelId")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    public JAXBElement<String> createRootLabelId(String value) {
        return new JAXBElement<String>(_RootLabelId_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "url")
    public JAXBElement<String> createUrl(String value) {
        return new JAXBElement<String>(_Url_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "eventData")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    public JAXBElement<String> createEventData(String value) {
        return new JAXBElement<String>(_EventData_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "page-type")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    public JAXBElement<String> createPageType(String value) {
        return new JAXBElement<String>(_PageType_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "id")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    public JAXBElement<String> createId(String value) {
        return new JAXBElement<String>(_Id_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "rootPage")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    public JAXBElement<String> createRootPage(String value) {
        return new JAXBElement<String>(_RootPage_QNAME, String.class, null, value);
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
    @XmlElementDecl(namespace = "", name = "title")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    public JAXBElement<String> createTitle(String value) {
        return new JAXBElement<String>(_Title_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "title-id")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    public JAXBElement<String> createTitleId(String value) {
        return new JAXBElement<String>(_TitleId_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "container")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    public JAXBElement<String> createContainer(String value) {
        return new JAXBElement<String>(_Container_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "authentication")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    public JAXBElement<String> createAuthentication(String value) {
        return new JAXBElement<String>(_Authentication_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "description")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    public JAXBElement<String> createDescription(String value) {
        return new JAXBElement<String>(_Description_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "subtitle")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    public JAXBElement<String> createSubtitle(String value) {
        return new JAXBElement<String>(_Subtitle_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "nodeRef")
    public JAXBElement<String> createNodeRef(String value) {
        return new JAXBElement<String>(_NodeRef_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "eventName")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    public JAXBElement<String> createEventName(String value) {
        return new JAXBElement<String>(_EventName_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "activityType")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    public JAXBElement<String> createActivityType(String value) {
        return new JAXBElement<String>(_ActivityType_QNAME, String.class, null, value);
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
    @XmlElementDecl(namespace = "", name = "pageFamily")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    public JAXBElement<String> createPageFamily(String value) {
        return new JAXBElement<String>(_PageFamily_QNAME, String.class, null, value);
    }

}
