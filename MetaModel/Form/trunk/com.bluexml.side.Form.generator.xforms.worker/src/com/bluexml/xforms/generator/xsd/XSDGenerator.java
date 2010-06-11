package com.bluexml.xforms.generator.xsd;

//import java.io.File;
//import java.io.IOException;
//import java.util.Collections;
//import java.util.List;
//import java.util.Map;
//
//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;
//import com.bluexml.xforms.generator.AbstractDataGenerator;
//import com.bluexml.xforms.generator.tools.ModelTools;
//import org.eclipse.emf.common.util.URI;
//import org.eclipse.emf.ecore.resource.Resource;
//import org.eclipse.xsd.XSDAnnotation;
//import org.eclipse.xsd.XSDAttributeDeclaration;
//import org.eclipse.xsd.XSDAttributeUse;
//import org.eclipse.xsd.XSDAttributeUseCategory;
//import org.eclipse.xsd.XSDComplexTypeDefinition;
//import org.eclipse.xsd.XSDCompositor;
//import org.eclipse.xsd.XSDDerivationMethod;
//import org.eclipse.xsd.XSDElementDeclaration;
//import org.eclipse.xsd.XSDFactory;
//import org.eclipse.xsd.XSDModelGroup;
//import org.eclipse.xsd.XSDParticle;
//import org.eclipse.xsd.XSDSchema;
//import org.eclipse.xsd.util.XSDConstants;
//import org.eclipse.xsd.util.XSDResourceImpl;
//import org.topcased.MMUseCase.AbstractClass;
//import org.topcased.MMUseCase.Aspect;
//import org.topcased.MMUseCase.Association;
//import org.topcased.MMUseCase.Attribute;
//import org.topcased.MMUseCase.Classe;
//import org.topcased.MMUseCase.Enumeration;
//import org.w3c.dom.Element;
//
//import KerblueForms.Form;
//import KerblueForms.FormElement;

/**
 * The Class XSDGenerator.
 */
@Deprecated
public class XSDGenerator
// extends AbstractDataGenerator
{
	//
	// /** The Constant HTTP_JAVA_SUN_COM_XML_NS_JAXB. */
	// private static final String HTTP_JAVA_SUN_COM_XML_NS_JAXB =
	// "http://java.sun.com/xml/ns/jaxb";
	//
	// /** The Constant HTTP_HYPERJAXB2_JVNET_ORG_CUSTOMIZATIONS. */
	// private static final String HTTP_HYPERJAXB2_JVNET_ORG_CUSTOMIZATIONS =
	// "http://hyperjaxb2.jvnet.org/customizations";
	//
	// /** The Constant HTTP_HYPERJAXB2_JVNET_ORG_CUSTOMIZATIONS_LOCATION. */
	// // private static final String
	// // HTTP_HYPERJAXB2_JVNET_ORG_CUSTOMIZATIONS_LOCATION = "customizations.xsd";
	// protected static Log logger = LogFactory.getLog(XSDGenerator.class);
	//
	// /** The xsd factory. */
	// private XSDFactory xsdFactory = XSDFactory.eINSTANCE;
	//
	// /** The xsd schema. */
	// private XSDSchema xsdSchema;
	//
	// /** The output xsd. */
	// private File outputXSD;
	//
	// /** The is hyper jaxb. */
	// private boolean isHyperJAXB = false;
	//
	// /**
	// * Adds the abstract class.
	// *
	// * @param abstractClass
	// * the abstract class
	// */
	// private void addAbstractClass(AbstractClass abstractClass) {
	// XSDComplexTypeDefinition complexType = xsdFactory
	// .createXSDComplexTypeDefinition();
	// complexType.setName(ModelTools.getCompleteName(abstractClass) + "Type");
	//
	// boolean hasParent = false;
	// if (abstractClass instanceof Classe) {
	// Classe parent = ModelTools.getParent((Classe) abstractClass);
	// if (parent != null) {
	// XSDComplexTypeDefinition parentDefinition = xsdSchema
	// .resolveComplexTypeDefinition(ModelTools
	// .getCompleteName(parent)
	// + "Type");
	// complexType.setBaseTypeDefinition(parentDefinition);
	//
	// complexType
	// .setDerivationMethod(XSDDerivationMethod.EXTENSION_LITERAL);
	// hasParent = true;
	// }
	// }
	//
	// XSDParticle particle = xsdFactory.createXSDParticle();
	// XSDModelGroup typeSequence = xsdFactory.createXSDModelGroup();
	// typeSequence.setCompositor(XSDCompositor.SEQUENCE_LITERAL);
	// particle.setContent(typeSequence);
	//
	// if (!hasParent) {
	// addCaptionAttribute(complexType);
	// }
	//
	// complexType.setContent(particle);
	// xsdSchema.getContents().add(complexType);
	//
	// if (abstractClass instanceof Classe) {
	// XSDElementDeclaration elementType = xsdFactory
	// .createXSDElementDeclaration();
	// elementType.setName(ModelTools.getCompleteName(abstractClass));
	// elementType.setTypeDefinition(complexType);
	// xsdSchema.getContents().add(elementType);
	// }
	//
	// }
	//
	// /**
	// * Adds the annotation property.
	// *
	// * @param declaration
	// * the declaration
	// * @param annotationProperties
	// * the annotation properties
	// */
	// private void addAnnotationProperty(XSDElementDeclaration declaration,
	// Element... annotationProperties) {
	// XSDAnnotation annotationId = xsdFactory.createXSDAnnotation();
	// declaration.setAnnotation(annotationId);
	// appendAnnotationProperty(annotationId, annotationProperties);
	// }
	//
	// /**
	// * Append annotation property.
	// *
	// * @param annotationId
	// * the annotation id
	// * @param annotationProperties
	// * the annotation properties
	// */
	// private void appendAnnotationProperty(XSDAnnotation annotationId,
	// Element... annotationProperties) {
	// if (isHyperJAXB) {
	// Element appinfo = annotationId
	// .createApplicationInformation(HTTP_HYPERJAXB2_JVNET_ORG_CUSTOMIZATIONS);
	// Element element = annotationId.getElement();
	// element.appendChild(appinfo);
	// for (Element annotationProperty : annotationProperties) {
	// appinfo.appendChild(annotationProperty);
	// }
	// }
	// }
	//
	// /*
	// private void addAnnotationProperty(XSDTypeDefinition declaration,
	// Element... annotationProperties) {
	// XSDAnnotation annotationId = xsdFactory.createXSDAnnotation();
	// declaration.setAnnotation(annotationId);
	// appendAnnotationProperty(annotationId, annotationProperties);
	// }
	// */
	//
	// /* (non-Javadoc)
	// * @see
	// com.bluexml.xforms.generator.DataGenerator#addAspectForClass(org.topcased.MMUseCase.Classe,
	// org.topcased.MMUseCase.Aspect, org.topcased.MMUseCase.Classe)
	// */
	// public void addAspectForClass(Classe classe, Aspect aspect, Classe owner) {
	// if (classe == owner) {
	// XSDElementDeclaration aspectDeclaration = xsdFactory
	// .createXSDElementDeclaration();
	// aspectDeclaration.setName(aspect.getName());
	// XSDComplexTypeDefinition elementDefinition = xsdSchema
	// .resolveComplexTypeDefinition(ModelTools
	// .getCompleteName(aspect)
	// + "Type");
	// aspectDeclaration.setTypeDefinition(elementDefinition);
	// XSDParticle aspectParticle = xsdFactory.createXSDParticle();
	// aspectParticle.setContent(aspectDeclaration);
	// aspectParticle.setMinOccurs(0);
	// aspectParticle.setMaxOccurs(1);
	// getTypeSequence(classe).getContents().add(aspectParticle);
	//
	// Element annotationProperty = xsdSchema.getDocument().createElement(
	// "hj:complexSingleProperty");
	// Element component = xsdSchema.getDocument().createElement(
	// "hj:component");
	// annotationProperty.appendChild(component);
	// addAnnotationProperty(aspectDeclaration, annotationProperty);
	// }
	// }
	//
	// /**
	// * Adds the attribute for abstract class.
	// *
	// * @param abstractClass
	// * the abstract class
	// * @param attribute
	// * the attribute
	// */
	// private void addAttributeForAbstractClass(AbstractClass abstractClass,
	// Attribute attribute) {
	// String attibuteName = attribute.getName();
	// String attributeType = ModelTools.toXSDType(attribute.getTyp());
	// addAttributeForAbstractClass(abstractClass, attibuteName,
	// attributeType, false);
	// }
	//
	// /**
	// * Adds the attribute for abstract class.
	// *
	// * @param abstractClass
	// * the abstract class
	// * @param attibuteName
	// * the attibute name
	// * @param attributeType
	// * the attribute type
	// * @param isId
	// * the is id
	// */
	// private void addAttributeForAbstractClass(AbstractClass abstractClass,
	// String attibuteName, String attributeType, boolean isId) {
	// XSDElementDeclaration declaration = xsdFactory
	// .createXSDElementDeclaration();
	// declaration.setName(attibuteName);
	// String type = attributeType;
	// declaration.setTypeDefinition(xsdSchema.getSchemaForSchema()
	// .resolveSimpleTypeDefinition(type));
	//
	// XSDParticle particle = xsdFactory.createXSDParticle();
	// particle.setContent(declaration);
	// particle.setMinOccurs(0);
	// getTypeSequence(abstractClass).getContents().add(particle);
	//
	// if (isHyperJAXB) {
	// if (isId) {
	// Element annotationProperty = xsdSchema.getDocument()
	// .createElement("hj:id");
	// addAnnotationProperty(declaration, annotationProperty);
	// }
	// }
	//
	// }
	//
	// /* (non-Javadoc)
	// * @see
	// com.bluexml.xforms.generator.DataGenerator#addAttributeForAspect(org.topcased.MMUseCase.Aspect,
	// org.topcased.MMUseCase.Attribute)
	// */
	// public void addAttributeForAspect(Aspect aspect, Attribute attribute) {
	// addAttributeForAbstractClass(aspect, attribute);
	// }
	//
	// /* (non-Javadoc)
	// * @see
	// com.bluexml.xforms.generator.DataGenerator#addAttributeForClass(org.topcased.MMUseCase.Classe,
	// org.topcased.MMUseCase.Attribute, org.topcased.MMUseCase.Classe)
	// */
	// public void addAttributeForClass(Classe classe, Attribute attribute,
	// Classe owner) {
	// if (classe == owner) {
	// addAttributeForAbstractClass(classe, attribute);
	// }
	// }
	//
	// /**
	// * Adds the caption attribute.
	// *
	// * @param complexType
	// * the complex type
	// */
	// private void addCaptionAttribute(XSDComplexTypeDefinition complexType) {
	// XSDAttributeDeclaration captionDeclaration = xsdFactory
	// .createXSDAttributeDeclaration();
	// captionDeclaration.setName("selectionCaption");
	// captionDeclaration.setTypeDefinition(xsdSchema.getSchemaForSchema()
	// .resolveSimpleTypeDefinition("string"));
	//
	// XSDAttributeUse captionAttribute = xsdFactory.createXSDAttributeUse();
	// captionAttribute.setContent(captionDeclaration);
	// captionAttribute.setUse(XSDAttributeUseCategory.OPTIONAL_LITERAL);
	// complexType.getAttributeContents().add(captionAttribute);
	// }
	//
	// /* (non-Javadoc)
	// * @see
	// com.bluexml.xforms.generator.DataGenerator#addIdForClass(org.topcased.MMUseCase.Classe,
	// java.lang.String, boolean)
	// */
	// public void addIdForClass(Classe classe, String string, boolean hasParent) {
	// if (!hasParent) {
	// addAttributeForAbstractClass(classe, string, "string", true);
	// }
	// }
	//
	// /**
	// * Adds the lazy for class.
	// *
	// * @param classe
	// * the classe
	// * @param string
	// * the string
	// */
	// public void addLazyForClass(Classe classe, String string) {
	// addAttributeForAbstractClass(classe, string, "boolean", false);
	// }
	//
	// /* (non-Javadoc)
	// * @see com.bluexml.xforms.generator.DataGenerator#beginAspect(org.topcased.MMUseCase.Aspect)
	// */
	// public void beginAspect(Aspect aspect) {
	// addAbstractClass(aspect);
	// }
	//
	// /* (non-Javadoc)
	// * @see com.bluexml.xforms.generator.DataGenerator#beginClasse(org.topcased.MMUseCase.Classe,
	// boolean)
	// */
	// public void beginClasse(Classe classe, boolean rendered) {
	// addAbstractClass(classe);
	// }
	//
	// /* (non-Javadoc)
	// * @see com.bluexml.xforms.generator.DataGenerator#beginGeneration()
	// */
	// public void beginGeneration() {
	// xsdSchema = xsdFactory.createXSDSchema();
	//
	// xsdSchema.setSchemaForSchemaQNamePrefix("xsd");
	//
	// Map<String, String> qNamePrefixToNamespaceMap = xsdSchema
	// .getQNamePrefixToNamespaceMap();
	// qNamePrefixToNamespaceMap.put(
	// xsdSchema.getSchemaForSchemaQNamePrefix(),
	// XSDConstants.SCHEMA_FOR_SCHEMA_URI_2001);
	//
	// if (isHyperJAXB) {
	// qNamePrefixToNamespaceMap.put("hj",
	// HTTP_HYPERJAXB2_JVNET_ORG_CUSTOMIZATIONS);
	// qNamePrefixToNamespaceMap
	// .put("jaxb", HTTP_JAVA_SUN_COM_XML_NS_JAXB);
	// }
	//
	// xsdSchema.updateElement();
	// if (isHyperJAXB) {
	// xsdSchema.getElement().setAttribute(
	// "jaxb:extensionBindingPrefixes", "hj");
	// }
	//
	// // XSDImport hyperjaxbImport = xsdFactory.createXSDImport();
	// // hyperjaxbImport.setNamespace(HTTP_HYPERJAXB2_JVNET_ORG_CUSTOMIZATIONS);
	// // hyperjaxbImport.setSchemaLocation(HTTP_HYPERJAXB2_JVNET_ORG_CUSTOMIZATIONS_LOCATION);
	// // xsdSchema.getContents().add(hyperjaxbImport);
	// }
	//
	// /* (non-Javadoc)
	// * @see com.bluexml.xforms.generator.DataGenerator#beginListAspects()
	// */
	// public void beginListAspects() {
	// // nothing
	// }
	//
	// /* (non-Javadoc)
	// * @see com.bluexml.xforms.generator.DataGenerator#beginListAssociations()
	// */
	// public void beginListAssociations() {
	// // nothing
	// }
	//
	// /* (non-Javadoc)
	// * @see com.bluexml.xforms.generator.DataGenerator#beginListClasses()
	// */
	// public void beginListClasses() {
	// // nothing
	// }
	//
	// /* (non-Javadoc)
	// * @see com.bluexml.xforms.generator.DataGenerator#beginListEnums()
	// */
	// public void beginListEnums() {
	// // nothing
	// }
	//
	// /**
	// * Adds the association.
	// *
	// * @param type
	// * the type
	// * @param name
	// * the name
	// * @param title
	// * the title
	// * @param source
	// * the source
	// * @param destination
	// * the destination
	// * @param role
	// * the role
	// * @param roleTitle
	// * the role title
	// * @param doublenav
	// * the doublenav
	// * @param association
	// * the association
	// * @param owner
	// * the owner
	// */
	// public void addAssociation(AssociationKind type, String name, String title,
	// Classe source, Classe destination, String role, String roleTitle,
	// boolean doublenav, Association association, Classe owner) {
	// }
	//
	// /*
	// public void createMtM(ModelElement source, ModelElement destination, String name, String
	// tableName,
	// boolean setIsInverse, boolean isInverse) {
	// XSDModelGroup typeSequence = getTypeSequence((AbstractClass) source);
	//
	// XSDElementDeclaration declaration = xsdFactory.createXSDElementDeclaration();
	// declaration.setName(name);
	// XSDComplexTypeDefinition elementDefinition = xsdSchema.resolveComplexTypeDefinition(OblTools
	// .getCompleteName(destination)
	// + "Type");
	// declaration.setTypeDefinition(elementDefinition);
	//
	// // XSDParticle particle = xsdFactory.createXSDParticle();
	// // particle.setContent(declaration);
	// // particle.setMinOccurs(0);
	// // particle.setMaxOccurs(-1);
	// // typeSequence.getContents().add(particle);
	//
	// Element annotationProperty =
	// xsdSchema.getDocument().createElement("hj:complexCollectionProperty");
	//
	// if (setIsInverse) {
	// annotationProperty.setAttribute("inverse", Boolean.toString(isInverse));
	// Element tableElement = xsdSchema.getDocument().createElement("hj:table");
	// tableElement.setAttribute("name", tableName);
	// annotationProperty.appendChild(tableElement);
	// }
	//
	// Element mtm = xsdSchema.getDocument().createElement("hj:many-to-many");
	// annotationProperty.appendChild(mtm);
	//
	// addDeclarationAsList(typeSequence, declaration, name);
	//
	// addAnnotationProperty(declaration, annotationProperty);
	//
	// }
	//
	// private void addDeclarationAsElement(XSDModelGroup typeSequence,
	// XSDElementDeclaration declaration, String name) {
	// XSDElementDeclaration itemsElementDeclaration = xsdFactory
	// .createXSDElementDeclaration();
	// itemsElementDeclaration.setName(name);
	//
	// XSDParticle itemParticle = xsdFactory.createXSDParticle();
	// itemParticle.setContent(declaration);
	// itemParticle.setMinOccurs(0);
	//
	// XSDParticle itemsParticle = xsdFactory.createXSDParticle();
	// itemsParticle.setMinOccurs(0);
	// XSDComplexTypeDefinition anonymousComplexTypeDefinition = xsdFactory
	// .createXSDComplexTypeDefinition();
	// XSDModelGroup anonymousComplexTypeDefinitionSequence = xsdFactory
	// .createXSDModelGroup();
	// anonymousComplexTypeDefinitionSequence
	// .setCompositor(XSDCompositor.SEQUENCE_LITERAL);
	// anonymousComplexTypeDefinitionSequence.getContents().add(itemParticle);
	//
	// XSDParticle anonymousComplexTypeDefinitionParticle = xsdFactory
	// .createXSDParticle();
	// anonymousComplexTypeDefinitionParticle
	// .setContent(anonymousComplexTypeDefinitionSequence);
	// anonymousComplexTypeDefinition
	// .setContent(anonymousComplexTypeDefinitionParticle);
	// itemsElementDeclaration
	// .setAnonymousTypeDefinition(anonymousComplexTypeDefinition);
	//
	// itemsParticle.setContent(itemsElementDeclaration);
	// typeSequence.getContents().add(itemsParticle);
	//
	// Element ignoredProperty = xsdSchema.getDocument().createElement(
	// "hj:ignored");
	// Element componentProperty = xsdSchema.getDocument().createElement(
	// "hj:component");
	//
	// addAnnotationProperty(anonymousComplexTypeDefinition, ignoredProperty,
	// componentProperty);
	// }
	//
	// private void addDeclarationAsList(XSDModelGroup typeSequence,
	// XSDElementDeclaration declaration, String name) {
	// XSDElementDeclaration itemsElementDeclaration = xsdFactory
	// .createXSDElementDeclaration();
	// itemsElementDeclaration.setName(name + "List");
	//
	// XSDParticle itemParticle = xsdFactory.createXSDParticle();
	// itemParticle.setContent(declaration);
	// itemParticle.setMinOccurs(0);
	// itemParticle.setMaxOccurs(-1);
	//
	// XSDParticle itemsParticle = xsdFactory.createXSDParticle();
	// itemsParticle.setMinOccurs(0);
	// XSDComplexTypeDefinition anonymousComplexTypeDefinition = xsdFactory
	// .createXSDComplexTypeDefinition();
	// XSDModelGroup anonymousComplexTypeDefinitionSequence = xsdFactory
	// .createXSDModelGroup();
	// anonymousComplexTypeDefinitionSequence
	// .setCompositor(XSDCompositor.SEQUENCE_LITERAL);
	// anonymousComplexTypeDefinitionSequence.getContents().add(itemParticle);
	//
	// XSDParticle anonymousComplexTypeDefinitionParticle = xsdFactory
	// .createXSDParticle();
	// anonymousComplexTypeDefinitionParticle
	// .setContent(anonymousComplexTypeDefinitionSequence);
	// anonymousComplexTypeDefinition
	// .setContent(anonymousComplexTypeDefinitionParticle);
	// itemsElementDeclaration
	// .setAnonymousTypeDefinition(anonymousComplexTypeDefinition);
	//
	// itemsParticle.setContent(itemsElementDeclaration);
	// typeSequence.getContents().add(itemsParticle);
	//
	// Element ignoredProperty = xsdSchema.getDocument().createElement(
	// "hj:ignored");
	// Element componentProperty = xsdSchema.getDocument().createElement(
	// "hj:component");
	//
	// addAnnotationProperty(anonymousComplexTypeDefinition, ignoredProperty,
	// componentProperty);
	// }
	//
	// public void createMtO(ModelElement source, ModelElement destination, String name, String
	// column) {
	//
	// XSDModelGroup typeSequence = getTypeSequence((AbstractClass) source);
	//
	// XSDElementDeclaration declaration = xsdFactory.createXSDElementDeclaration();
	// declaration.setName(name);
	// XSDComplexTypeDefinition elementDefinition = xsdSchema.resolveComplexTypeDefinition(OblTools
	// .getCompleteName(destination)
	// + "Type");
	// declaration.setTypeDefinition(elementDefinition);
	//
	// XSDParticle particle = xsdFactory.createXSDParticle();
	// particle.setContent(declaration);
	// particle.setMinOccurs(0);
	// particle.setMaxOccurs(1);
	// typeSequence.getContents().add(particle);
	//
	// Element annotationProperty =
	// xsdSchema.getDocument().createElement("hj:complexSingleProperty");
	// Element mto = xsdSchema.getDocument().createElement("hj:many-to-one");
	//
	// if (column != null) {
	// Element columnElement = xsdSchema.getDocument().createElement("hj:column");
	// columnElement.setAttribute("name", column);
	// columnElement.setAttribute("not-null", "true");
	//
	// mto.setAttribute("insert", "false");
	// mto.setAttribute("update", "false");
	//
	// mto.appendChild(columnElement);
	// }
	//
	// // <class name="Parent">
	// // <id name="name"/>
	// // ...
	// // <list name="children" cascade="all,delete-orphan">
	// // <key column="parentName"
	// // not-null="true"
	// // update="false"/>
	// // <list-index column="sibling"/>
	// // <one-to-many class="Child"/>
	// // </list>
	// // </class>
	// //
	// // <class name="Child">
	// // <id name="name"/>
	// // ...
	// // <many-to-one name="parent"
	// // column="parentName"
	// // not-null="true"
	// // insert="false"
	// // update="false"/>
	// // </class>
	//
	// annotationProperty.appendChild(mto);
	// addAnnotationProperty(declaration, annotationProperty);
	//
	// }
	//
	// public void createOtM(ModelElement source, ModelElement destination, String name, String
	// keyColum) {
	// XSDModelGroup typeSequence = getTypeSequence((AbstractClass) source);
	//
	// XSDElementDeclaration declaration = xsdFactory.createXSDElementDeclaration();
	// declaration.setName(name);
	// XSDComplexTypeDefinition elementDefinition = xsdSchema.resolveComplexTypeDefinition(OblTools
	// .getCompleteName(destination)
	// + "Type");
	// declaration.setTypeDefinition(elementDefinition);
	//
	// // XSDParticle particle = xsdFactory.createXSDParticle();
	// // particle.setContent(declaration);
	// // particle.setMinOccurs(0);
	// // particle.setMaxOccurs(-1);
	// // typeSequence.getContents().add(particle);
	//
	// Element annotationProperty =
	// xsdSchema.getDocument().createElement("hj:complexCollectionProperty");
	// if (keyColum != null) {
	// Element key = xsdSchema.getDocument().createElement("hj:key");
	// key.setAttribute("not-null", "true");
	//
	// Element column = xsdSchema.getDocument().createElement("hj:column");
	// column.setAttribute("name", keyColum);
	// key.appendChild(column);
	//
	// annotationProperty.appendChild(key);
	// }
	//
	// Element otm = xsdSchema.getDocument().createElement("hj:one-to-many");
	// annotationProperty.appendChild(otm);
	//
	// addDeclarationAsList(typeSequence, declaration, name);
	//
	// addAnnotationProperty(declaration, annotationProperty);
	// }
	//
	// public void createOtO(ModelElement source, ModelElement destination, String name, String
	// propertyref) {
	// XSDModelGroup typeSequence = getTypeSequence((AbstractClass) source);
	//
	// XSDElementDeclaration declaration = xsdFactory.createXSDElementDeclaration();
	// // declaration.setName(name);
	// declaration.setResolvedElementDeclaration(xsdSchema.resolveElementDeclaration(OblTools
	// .getCompleteName(destination)));
	//
	// XSDComplexTypeDefinition elementDefinition = xsdSchema.resolveComplexTypeDefinition(OblTools
	// .getCompleteName(destination)
	// + "Type");
	// declaration.setTypeDefinition(elementDefinition);
	//
	// XSDParticle particle = xsdFactory.createXSDParticle();
	// particle.setContent(declaration);
	// particle.setMinOccurs(0);
	//
	// addDeclarationAsElement(typeSequence, declaration, name);
	// // typeSequence.getContents().add(particle);
	//
	// Element annotationProperty =
	// xsdSchema.getDocument().createElement("hj:complexSingleProperty");
	// Element oto = xsdSchema.getDocument().createElement("hj:many-to-one");
	// if (propertyref != null) {
	// oto.setAttribute("property-ref", StringUtils.capitalize(propertyref) + "."
	// + OblTools.getCompleteNameJAXB((Classe) source));
	// }
	// // oto.setAttribute("constrained", "true");
	// annotationProperty.appendChild(oto);
	// addAnnotationProperty(declaration, annotationProperty);
	// }
	// */
	//
	// /* (non-Javadoc)
	// * @see com.bluexml.xforms.generator.DataGenerator#endAspect(org.topcased.MMUseCase.Aspect)
	// */
	// public void endAspect(Aspect aspect) {
	// // nothing
	// }
	//
	// /* (non-Javadoc)
	// * @see com.bluexml.xforms.generator.DataGenerator#endClasse(org.topcased.MMUseCase.Classe)
	// */
	// public void endClasse(Classe classe) {
	// // nothing
	// }
	//
	// /* (non-Javadoc)
	// * @see com.bluexml.xforms.generator.DataGenerator#endGeneration()
	// */
	// public void endGeneration() {
	// Resource resource = new XSDResourceImpl(URI.createFileURI(outputXSD
	// .getAbsolutePath()));
	// resource.getContents().add(xsdSchema);
	// try {
	// resource.save(Collections.EMPTY_MAP);
	// } catch (IOException e) {
	// logger.error(e);
	// throw new RuntimeException(e);
	// }
	// }
	//
	// /* (non-Javadoc)
	// * @see com.bluexml.xforms.generator.DataGenerator#endListAspects()
	// */
	// public void endListAspects() {
	// // nothing
	// }
	//
	// /* (non-Javadoc)
	// * @see com.bluexml.xforms.generator.DataGenerator#endListAssociations()
	// */
	// public void endListAssociations() {
	// // nothing
	// }
	//
	// /* (non-Javadoc)
	// * @see com.bluexml.xforms.generator.DataGenerator#endListClasses()
	// */
	// public void endListClasses() {
	// // nothing
	// }
	//
	// /* (non-Javadoc)
	// * @see com.bluexml.xforms.generator.DataGenerator#endListEnums()
	// */
	// public void endListEnums() {
	// // nothing
	// }
	//
	// /**
	// * Gets the type sequence.
	// *
	// * @param source
	// * the source
	// *
	// * @return the type sequence
	// */
	// private XSDModelGroup getTypeSequence(AbstractClass source) {
	// XSDComplexTypeDefinition sourceDefinition = xsdSchema
	// .resolveComplexTypeDefinition(ModelTools
	// .getCompleteName(source)
	// + "Type");
	// XSDModelGroup typeSequence = (XSDModelGroup) ((XSDParticle) sourceDefinition
	// .getContent()).getContent();
	// return typeSequence;
	// }
	//
	// /* (non-Javadoc)
	// * @see
	// com.bluexml.xforms.generator.DataGenerator#processEnum(org.topcased.MMUseCase.Enumeration)
	// */
	// public void processEnum(Enumeration enumeration) {
	// // nothing
	// }
	//
	// /**
	// * Sets the output file.
	// *
	// * @param xsdOutput
	// * the new output file
	// */
	// public void setOutputFile(String xsdOutput) {
	// outputXSD = new File(xsdOutput);
	// outputXSD.getParentFile().mkdirs();
	// }
	//
	// /* (non-Javadoc)
	// * @see
	// com.bluexml.xforms.generator.DataGenerator#addAssociation(com.bluexml.xforms.generator.DataGenerator.AssociationKind,
	// java.lang.String, java.lang.String, org.topcased.MMUseCase.Classe,
	// org.topcased.MMUseCase.Classe, java.lang.String, boolean, org.topcased.MMUseCase.Association,
	// org.topcased.MMUseCase.Classe)
	// */
	// public void addAssociation(AssociationKind type, String name, String title,
	// Classe source, Classe destination, String role, boolean doublenav,
	// Association association, Classe owner) {
	// // TODO Auto-generated method stub
	//
	// }
	//
	// /**
	// * Sets the association classes.
	// *
	// * @param associationClasses
	// * the new association classes
	// */
	// public void setAssociationClasses(List<Classe> associationClasses) {
	// // TODO Auto-generated method stub
	//
	// }
	//
	// /**
	// * Adds the root for form.
	// *
	// * @param form
	// * the form
	// * @param root
	// * the root
	// */
	// public void addRootForForm(Form form, FormElement root) {
	// // TODO Auto-generated method stub
	//
	// }
	//
	// /* (non-Javadoc)
	// * @see com.bluexml.xforms.generator.DataGenerator#beginForm(KerblueForms.Form)
	// */
	// public void beginForm(Form form) {
	// // TODO Auto-generated method stub
	//
	// }
	//
	// /* (non-Javadoc)
	// * @see com.bluexml.xforms.generator.DataGenerator#beginListForms()
	// */
	// public void beginListForms() {
	// // TODO Auto-generated method stub
	//
	// }
	//
	// /* (non-Javadoc)
	// * @see com.bluexml.xforms.generator.DataGenerator#endForm(KerblueForms.Form)
	// */
	// public void endForm(Form form) {
	// // TODO Auto-generated method stub
	//
	// }
	//
	// /* (non-Javadoc)
	// * @see com.bluexml.xforms.generator.DataGenerator#endListForms()
	// */
	// public void endListForms() {
	// // TODO Auto-generated method stub
	//
	// }
	//
}
