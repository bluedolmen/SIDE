
package com.bluexml.xforms.controller.binding;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.bluexml.xforms.controller.binding package. 
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

    private final static QName _Form_QNAME = new QName("", "form");
    private final static QName _Class_QNAME = new QName("", "Class");
    private final static QName _Task_QNAME = new QName("", "task");
    private final static QName _Canister_QNAME = new QName("", "canister");
    private final static QName _Workflow_QNAME = new QName("", "Workflow");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.bluexml.xforms.controller.binding
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link FormType }
     * 
     */
    public FormType createFormType() {
        return new FormType();
    }

    /**
     * Create an instance of {@link Batch }
     * 
     */
    public Batch createBatch() {
        return new Batch();
    }

    /**
     * Create an instance of {@link ServiceRequestSource }
     * 
     */
    public ServiceRequestSource createServiceRequestSource() {
        return new ServiceRequestSource();
    }

    /**
     * Create an instance of {@link GenericClassReference }
     * 
     */
    public GenericClassReference createGenericClassReference() {
        return new GenericClassReference();
    }

    /**
     * Create an instance of {@link GenericAssociations }
     * 
     */
    public GenericAssociations createGenericAssociations() {
        return new GenericAssociations();
    }

    /**
     * Create an instance of {@link GenericAttribute }
     * 
     */
    public GenericAttribute createGenericAttribute() {
        return new GenericAttribute();
    }

    /**
     * Create an instance of {@link GenericCreate }
     * 
     */
    public GenericCreate createGenericCreate() {
        return new GenericCreate();
    }

    /**
     * Create an instance of {@link Classes }
     * 
     */
    public Classes createClasses() {
        return new Classes();
    }

    /**
     * Create an instance of {@link AssociationType }
     * 
     */
    public AssociationType createAssociationType() {
        return new AssociationType();
    }

    /**
     * Create an instance of {@link ValueType }
     * 
     */
    public ValueType createValueType() {
        return new ValueType();
    }

    /**
     * Create an instance of {@link Workflows }
     * 
     */
    public Workflows createWorkflows() {
        return new Workflows();
    }

    /**
     * Create an instance of {@link EnumType }
     * 
     */
    public EnumType createEnumType() {
        return new EnumType();
    }

    /**
     * Create an instance of {@link GenericDelete }
     * 
     */
    public GenericDelete createGenericDelete() {
        return new GenericDelete();
    }

    /**
     * Create an instance of {@link Mapping }
     * 
     */
    public Mapping createMapping() {
        return new Mapping();
    }

    /**
     * Create an instance of {@link WorkflowTaskType }
     * 
     */
    public WorkflowTaskType createWorkflowTaskType() {
        return new WorkflowTaskType();
    }

    /**
     * Create an instance of {@link FileFieldType }
     * 
     */
    public FileFieldType createFileFieldType() {
        return new FileFieldType();
    }

    /**
     * Create an instance of {@link ActionFieldType }
     * 
     */
    public ActionFieldType createActionFieldType() {
        return new ActionFieldType();
    }

    /**
     * Create an instance of {@link ModelChoiceType }
     * 
     */
    public ModelChoiceType createModelChoiceType() {
        return new ModelChoiceType();
    }

    /**
     * Create an instance of {@link ClassType }
     * 
     */
    public ClassType createClassType() {
        return new ClassType();
    }

    /**
     * Create an instance of {@link AttachContentInfo }
     * 
     */
    public AttachContentInfo createAttachContentInfo() {
        return new AttachContentInfo();
    }

    /**
     * Create an instance of {@link GenericAttributes }
     * 
     */
    public GenericAttributes createGenericAttributes() {
        return new GenericAttributes();
    }

    /**
     * Create an instance of {@link FieldType }
     * 
     */
    public FieldType createFieldType() {
        return new FieldType();
    }

    /**
     * Create an instance of {@link GenericTransitions }
     * 
     */
    public GenericTransitions createGenericTransitions() {
        return new GenericTransitions();
    }

    /**
     * Create an instance of {@link AttributeType }
     * 
     */
    public AttributeType createAttributeType() {
        return new AttributeType();
    }

    /**
     * Create an instance of {@link GenericUpdate }
     * 
     */
    public GenericUpdate createGenericUpdate() {
        return new GenericUpdate();
    }

    /**
     * Create an instance of {@link GenericWorkflow }
     * 
     */
    public GenericWorkflow createGenericWorkflow() {
        return new GenericWorkflow();
    }

    /**
     * Create an instance of {@link VirtualFieldType }
     * 
     */
    public VirtualFieldType createVirtualFieldType() {
        return new VirtualFieldType();
    }

    /**
     * Create an instance of {@link GenericTransition }
     * 
     */
    public GenericTransition createGenericTransition() {
        return new GenericTransition();
    }

    /**
     * Create an instance of {@link AspectType }
     * 
     */
    public AspectType createAspectType() {
        return new AspectType();
    }

    /**
     * Create an instance of {@link GenericAssociation }
     * 
     */
    public GenericAssociation createGenericAssociation() {
        return new GenericAssociation();
    }

    /**
     * Create an instance of {@link FormFieldType }
     * 
     */
    public FormFieldType createFormFieldType() {
        return new FormFieldType();
    }

    /**
     * Create an instance of {@link ReferenceType }
     * 
     */
    public ReferenceType createReferenceType() {
        return new ReferenceType();
    }

    /**
     * Create an instance of {@link GenericClass }
     * 
     */
    public GenericClass createGenericClass() {
        return new GenericClass();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FormType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "form", substitutionHeadNamespace = "", substitutionHeadName = "canister")
    public JAXBElement<FormType> createForm(FormType value) {
        return new JAXBElement<FormType>(_Form_QNAME, FormType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GenericClass }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "Class")
    public JAXBElement<GenericClass> createClass(GenericClass value) {
        return new JAXBElement<GenericClass>(_Class_QNAME, GenericClass.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link WorkflowTaskType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "task", substitutionHeadNamespace = "", substitutionHeadName = "canister")
    public JAXBElement<WorkflowTaskType> createTask(WorkflowTaskType value) {
        return new JAXBElement<WorkflowTaskType>(_Task_QNAME, WorkflowTaskType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CanisterType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "canister")
    public JAXBElement<CanisterType> createCanister(CanisterType value) {
        return new JAXBElement<CanisterType>(_Canister_QNAME, CanisterType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GenericWorkflow }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "Workflow")
    public JAXBElement<GenericWorkflow> createWorkflow(GenericWorkflow value) {
        return new JAXBElement<GenericWorkflow>(_Workflow_QNAME, GenericWorkflow.class, null, value);
    }

}
