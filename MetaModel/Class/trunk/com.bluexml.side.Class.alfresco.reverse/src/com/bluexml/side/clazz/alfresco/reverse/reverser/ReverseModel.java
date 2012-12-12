package com.bluexml.side.clazz.alfresco.reverse.reverser;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.eclipse.core.resources.IFile;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.w3c.dom.Element;

import com.bluexml.side.alfresco.binding.Aspect;
import com.bluexml.side.alfresco.binding.Association;
import com.bluexml.side.alfresco.binding.Association.Source;
import com.bluexml.side.alfresco.binding.Association.Target;
import com.bluexml.side.alfresco.binding.ChildAssociation;
import com.bluexml.side.alfresco.binding.Class.Associations;
import com.bluexml.side.alfresco.binding.Class.MandatoryAspects;
import com.bluexml.side.alfresco.binding.Class.Overrides;
import com.bluexml.side.alfresco.binding.Class.Properties;
import com.bluexml.side.alfresco.binding.Constraint;
import com.bluexml.side.alfresco.binding.MandatoryDef;
import com.bluexml.side.alfresco.binding.Model.Aspects;
import com.bluexml.side.alfresco.binding.Model.Constraints;
import com.bluexml.side.alfresco.binding.Model.DataTypes;
import com.bluexml.side.alfresco.binding.Model.Namespaces;
import com.bluexml.side.alfresco.binding.Model.Namespaces.Namespace;
import com.bluexml.side.alfresco.binding.Model.Types;
import com.bluexml.side.alfresco.binding.NamedValue;
import com.bluexml.side.alfresco.binding.Property;
import com.bluexml.side.alfresco.binding.Property.Index;
import com.bluexml.side.alfresco.binding.PropertyOverride;
import com.bluexml.side.alfresco.binding.Type;
import com.bluexml.side.clazz.AbstractClass;
import com.bluexml.side.clazz.AssociationEnd;
import com.bluexml.side.clazz.AssociationType;
import com.bluexml.side.clazz.Attribute;
import com.bluexml.side.clazz.Clazz;
import com.bluexml.side.clazz.ClazzFactory;
import com.bluexml.side.clazz.Enumeration;
import com.bluexml.side.clazz.EnumerationLiteral;
import com.bluexml.side.clazz.Model;
import com.bluexml.side.clazz.impl.AspectImpl;
import com.bluexml.side.clazz.impl.ClazzImpl;
import com.bluexml.side.clazz.impl.EnumerationImpl;
import com.bluexml.side.clazz.service.alfresco.AttributeServices;
import com.bluexml.side.clazz.service.alfresco.CommonServices;
import com.bluexml.side.common.CommonFactory;
import com.bluexml.side.common.ConstraintParam;
import com.bluexml.side.common.CustomDataType;
import com.bluexml.side.common.DataType;
import com.bluexml.side.common.MetaInfo;
import com.bluexml.side.common.ModelElement;
import com.bluexml.side.common.NameSpace;
import com.bluexml.side.common.NamedModelElement;
import com.bluexml.side.common.impl.CustomDataTypeImpl;
import com.bluexml.side.util.libs.ecore.EResourceUtils;

public class ReverseModel {

	private static final String CONSTRAINTS_ENUMERATION_TYPE = "LIST";
	private static final String CONSTRAINTS_LENGTH = "LENGTH";
	private static final String CONSTRAINTS_REGEX = "REGEX";
	Register register = new Register();
	List<Object> notReverted = new ArrayList<Object>();
	boolean verbose = false;

	public ReverseModel(boolean verbose) {
		this.verbose = verbose;
	}

	/**
	 * @return the notReverted
	 */
	public List<Object> getNotReverted() {
		return notReverted;
	}

	/**
	 * @return the register
	 */
	public Register getRegister() {
		return register;
	}

	public void loadSIDEModels(List<IFile> models) throws Exception {
		for (IFile iFile : models) {
			EList<EObject> openModel = EResourceUtils.openModel(iFile);
			if (openModel.size() == 1) {
				EObject eObject = openModel.get(0);
				if (eObject instanceof Model) {
					loadExternalSIDEModels((Model) eObject);
				}
			}
		}
	}

	/**
	 * use case : user have some custom alfresco dataModel to revert
	 * 
	 * @param m
	 * @throws Exception
	 */
	public void loadExternalSIDEModels(Model m) throws Exception {
		// scan existing side model and load to register, so references can be resolved

		List<NamedModelElement> namedElements = new ArrayList<NamedModelElement>();

		namedElements.add(m);

		// AbstractClasses
		EList<AbstractClass> allAbstractClasses = m.getAllAbstractClasses();
		namedElements.addAll(allAbstractClasses);

		// Associations
		EList<com.bluexml.side.clazz.Association> allAssociations = m.getAllAssociations();
		namedElements.addAll(allAssociations);

		// Constraints
		EList<com.bluexml.side.common.Constraint> allConstraints = m.getAllConstraints();
		namedElements.addAll(allConstraints);

		// Enumerations
		EList<Enumeration> allEnumerations = m.getAllEnumerations();
		namedElements.addAll(allEnumerations);

		// DataType
		EList<CustomDataType> allCustomDataType = m.getAllCustomDataType();
		if (allCustomDataType.size() > 0) {
			namedElements.addAll(allCustomDataType);
		}

		// records all NamedModelElement
		for (NamedModelElement abstractClass : namedElements) {
			String prefixedQName = CommonServices.getPrefixedQName(abstractClass);
			register.recordNewEObject(abstractClass, prefixedQName);
		}

		// records all NameSpace Object
		EList<NameSpace> allNamespaces = m.getAllNamespaces();
		for (NameSpace nameSpace : allNamespaces) {
			register.addNewNS(nameSpace);
		}

	}

	public EObject reverse(Object o) throws Exception {
		Model model = ClazzFactory.eINSTANCE.createModel();

		com.bluexml.side.alfresco.binding.Model alfModel = (com.bluexml.side.alfresco.binding.Model) o;
		// set name
		String qname = alfModel.getName();
		// System.out.println("Reverse :" + qname);
		register.recordNewEObject(model, qname);

		// NS declaration
		addNS(model, alfModel);

		// set name and prefix (must be done after NS declaration)
		setNameAndNS(model, qname);

		// DataType (CustomDataType)
		addDataTypes(model, alfModel);

		// Constraints
		addConstraints(model, alfModel);

		// Clazz
		addClazzs(model, alfModel);

		// Aspects
		addAspects(model, alfModel);

		return model;
	}

	private void addDataTypes(Model model, com.bluexml.side.alfresco.binding.Model alfModel) throws Exception {
		DataTypes dataTypesE = alfModel.getDataTypes();
		if (dataTypesE != null) {
			List<com.bluexml.side.alfresco.binding.Model.DataTypes.DataType> dataTypes = dataTypesE.getDataType();
			for (com.bluexml.side.alfresco.binding.Model.DataTypes.DataType dataType : dataTypes) {
				createDataType(model, dataType);
			}
		}
	}

	protected void createDataType(Model model, com.bluexml.side.alfresco.binding.Model.DataTypes.DataType dataType) {
		CustomDataType createCustomDataType = CommonFactory.eINSTANCE.createCustomDataType();
		// extract data from dataType 
		Element analyserClass = (Element) dataType.getAnalyserClass();
		if (analyserClass == null) {
			// model from alfresco 4.0+
			analyserClass = (Element) dataType.getDefaultAnalyserClass();
		}

		System.out.println("ReverseModel.addDataTypes() " + analyserClass.getClass().getName());
		String description = dataType.getDescription();
		Element javaClass = (Element) dataType.getJavaClass();
		System.out.println("ReverseModel.addDataTypes() " + javaClass.getClass().getName());
		String qname = dataType.getName();
		String title = dataType.getTitle();

		// set customDataType
		setNameAndNS(createCustomDataType, qname);
		createCustomDataType.setDescription(description);
		createCustomDataType.setDataTypeImp(javaClass.getTextContent());

		MetaInfo createMetaInfo = ReverseHelper.createMetaInfo("analyser", null, analyserClass.getTextContent());
		createCustomDataType.getMetainfo().add(createMetaInfo);
		model.getCustomDataTypeSet().add(createCustomDataType);
		register.recordNewEObject(createCustomDataType, qname);
		System.out.println("ReverseModel.addDataTypes() reccord :" + qname);
		ReverseHelper.addSimpleNameTag(createCustomDataType);
	}

	private void addAspects(Model model, com.bluexml.side.alfresco.binding.Model alfModel) throws Exception {
		Aspects aspectsE = alfModel.getAspects();
		if (aspectsE != null) {
			List<Aspect> aspects = aspectsE.getAspect();
			if (aspects != null) {
				for (Aspect aspect : aspects) {
					if (register.getEObject(AspectImpl.class, aspect.getName()) == null) {
						createNewAspect(model, aspect, alfModel);
					} else {
						// // System.out.println("already done in another createNewClass subProcess");
					}
				}
			}
		}
	}

	private void addParent(Model model, com.bluexml.side.alfresco.binding.Class type, com.bluexml.side.alfresco.binding.Model alfModel, com.bluexml.side.clazz.AbstractClass c) throws Exception {
		String parent = type.getParent();
		if (parent != null) {
			c.getGeneralizations().add(getAbClazzRef(parent, model, alfModel));
		}
	}

	private void addAspects(Model model, com.bluexml.side.alfresco.binding.Class type, com.bluexml.side.alfresco.binding.Model alfModel, com.bluexml.side.clazz.AbstractClass c) throws Exception {
		MandatoryAspects mandatoryAspects = type.getMandatoryAspects();
		if (mandatoryAspects != null) {
			List<String> aspect = mandatoryAspects.getAspect();
			for (String string : aspect) {
				com.bluexml.side.clazz.Aspect abClazzRef = (com.bluexml.side.clazz.Aspect) getAbClazzRef(string, model, alfModel);
				c.getAspects().add(abClazzRef);
			}
		}
	}

	private void addClazzs(Model model, com.bluexml.side.alfresco.binding.Model alfModel) throws Exception {
		Types typesE = alfModel.getTypes();
		if (typesE != null) {

			List<Type> types = typesE.getType();
			if (types != null) {
				for (Type type : types) {
					if (register.getEObject(ClazzImpl.class, type.getName()) == null) {
						createNewClazz(model, type, alfModel);
					} else {
						// // System.out.println("already done in another createNewClass subProcess");
					}
				}
			}
		}
	}

	private void createNewAbstractClass(Model model, com.bluexml.side.alfresco.binding.Class type, com.bluexml.side.alfresco.binding.Model alfModel, com.bluexml.side.clazz.AbstractClass c) throws Exception {
		String qname = type.getName();
		register.recordNewEObject(c, qname);

		setNameAndNS(c, qname);
		c.setTitle(type.getTitle());

		if (type.isArchive() != null && type.isArchive()) {
			MetaInfo createArchive = ReverseHelper.createArchive();
			c.getMetainfo().add(createArchive);
		}

		if (type.isIncludedInSuperTypeQuery() != null && type.isIncludedInSuperTypeQuery()) {
			MetaInfo createIncludedInSuperTypeQuery = ReverseHelper.createIncludedInSuperTypeQuery();
			c.getMetainfo().add(createIncludedInSuperTypeQuery);
		}

		// generalization
		addParent(model, type, alfModel, c);

		addAspects(model, type, alfModel, c);

		addProperties(model, c, type);

		addAssociations(model, type, alfModel);

		//TODO <overrides>
		Overrides overrides = type.getOverrides();
		if (overrides != null) {
			notReverted.add(type);
			if (verbose) {
				System.out.println("WARN Override found for :" + type.getName());
				List<PropertyOverride> properties = overrides.getProperty();
				for (PropertyOverride propertyOverride : properties) {
					System.out.println("* Property :" + propertyOverride.getName());
				}
			}
		}
		ReverseHelper.addSimpleNameTag(c);
	}

	private com.bluexml.side.clazz.Aspect createNewAspect(Model model, com.bluexml.side.alfresco.binding.Class type, com.bluexml.side.alfresco.binding.Model alfModel) throws Exception {
		com.bluexml.side.clazz.Aspect c = ClazzFactory.eINSTANCE.createAspect();
		//TODO add to model EObject in right package ??
		model.getAspectSet().add(c);

		createNewAbstractClass(model, type, alfModel, c);
		return c;

	}

	private Clazz createNewClazz(Model model, com.bluexml.side.alfresco.binding.Class type, com.bluexml.side.alfresco.binding.Model alfModel) throws Exception {
		Clazz c = ClazzFactory.eINSTANCE.createClazz();
		//TODO add to model EObject in right package ??
		model.getClassSet().add(c);

		createNewAbstractClass(model, type, alfModel, c);
		return c;
	}

	private void addAssociations(Model model, com.bluexml.side.alfresco.binding.Class type, com.bluexml.side.alfresco.binding.Model alfModel) throws Exception {
		Associations associationsE = type.getAssociations();
		if (associationsE != null) {
			List<Association> associations = associationsE.getAssociation();
			if (associations != null) {
				for (Association association : associations) {
					com.bluexml.side.clazz.Association createAssociation = createAssociation(model, type, alfModel, association);
					createAssociation.setAssociationType(AssociationType.DIRECT);
				}
			}
			List<ChildAssociation> childAssociations = associationsE.getChildAssociation();
			for (ChildAssociation childAssociation : childAssociations) {
				com.bluexml.side.clazz.Association createAssociation = createAssociation(model, type, alfModel, childAssociation);
				createAssociation.setAssociationType(AssociationType.AGGREGATION);
				// duplicate
				if (childAssociation.isDuplicate() != null && childAssociation.isDuplicate()) {
					MetaInfo createChildAssoDuplicate = ReverseHelper.createChildAssoDuplicate();
					createAssociation.getMetainfo().add(createChildAssoDuplicate);
				}
				// propagateTimestamps
				if (childAssociation.isPropagateTimestamps() != null && childAssociation.isPropagateTimestamps()) {
					MetaInfo createChildAssoPropagateTimestamps = ReverseHelper.createChildAssoPropagateTimestamps();
					createAssociation.getMetainfo().add(createChildAssoPropagateTimestamps);
				}
			}
		}

	}

	private com.bluexml.side.clazz.Association createAssociation(Model model, com.bluexml.side.alfresco.binding.Class type, com.bluexml.side.alfresco.binding.Model alfModel, Association association) throws Exception {
		com.bluexml.side.clazz.Association asso = ClazzFactory.eINSTANCE.createAssociation();
		model.getAssociationSet().add(asso);

		String qname = association.getName();
		register.recordNewEObject(asso, qname);
		setNameAndNS(asso, qname);

		asso.setTitle(association.getTitle());

		// create Source
		Source source = association.getSource();
		Boolean mandatory = source.isMandatory();
		Boolean many = source.isMany();
		boolean nav = false;
		String role = source.getRole();
		AssociationEnd createAssociationEndSource = createAssoEnd(mandatory, many, nav, role, type.getName(), model, alfModel);
		asso.setFirstEnd(createAssociationEndSource);

		// create Target
		Target target = association.getTarget();
		String role2 = target.getRole();
		boolean many2 = target.isMany();
		boolean parseBoolean = false;
		MandatoryDef mandatory2 = target.getMandatory();
		if (mandatory2 != null) {
			String content = mandatory2.getContent();
			parseBoolean = Boolean.parseBoolean(content);
		}
		AssociationEnd createAssoEndTarget = createAssoEnd(parseBoolean, many2, true, role2, target.getClazz(), model, alfModel);
		asso.setSecondEnd(createAssoEndTarget);
		ReverseHelper.addSimpleNameTag(asso);
		return asso;
	}

	private AssociationEnd createAssoEnd(Boolean mandatory, Boolean many, boolean nav, String role, String ref, Model model, com.bluexml.side.alfresco.binding.Model alfModel) throws Exception {
		AssociationEnd createAssociationEndSource = ClazzFactory.eINSTANCE.createAssociationEnd();

		createAssociationEndSource.setNavigable(nav);
		if (role != null) {
			createAssociationEndSource.setName(role);
		}

		createAssociationEndSource.setLinkedClass(getAbClazzRef(ref, model, alfModel));

		String srcMin = "0";
		if (mandatory != null && mandatory) {
			srcMin = "1";
		}
		createAssociationEndSource.setCardMin(srcMin);
		String srcMax = "1";

		if (many != null && many) {
			srcMax = "-1";
		}
		createAssociationEndSource.setCardMax(srcMax);
		ReverseHelper.addSimpleNameTag(createAssociationEndSource);
		return createAssociationEndSource;
	}

	private void addProperties(Model model, AbstractClass ac, com.bluexml.side.alfresco.binding.Class type) throws Exception {
		Properties propertiesE = type.getProperties();
		if (propertiesE != null) {
			List<Property> properties = propertiesE.getProperty();
			if (properties != null) {
				for (Property property : properties) {
					createAttribute(model, ac, property);
				}
			}
		}
	}

	protected void createAttribute(Model model, AbstractClass ac, Property property) throws Exception {
		Attribute attribute = ClazzFactory.eINSTANCE.createAttribute();
		ac.getAttributes().add(attribute);

		String qname = property.getName();
		register.recordNewEObject(attribute, qname);
		setNameAndNS(attribute, qname);
		attribute.setTitle(property.getTitle());

		String propType = property.getType();
		DataType propertyType = AttributeServices.getPropertyType(propType);
		if (propertyType == null) {
			// no existing mapping so use Custom
			propertyType = DataType.CUSTOM;
		}
		attribute.setTyp(propertyType);

		if (propertyType.equals(DataType.CUSTOM)) {
			// need to search for matching reference in register
			System.out.println("ReverseModel.addProperties() search CustomDataType for :" + propType);
			CustomDataType eObject = (CustomDataType) register.getEObject(CustomDataTypeImpl.class, propType);
			if (eObject == null) {
				register.printX();
				throw new Exception("CustomDataType Object not found ");
			}
			attribute.setCustomType(eObject);
		}

		String description = property.getDescription();
		attribute.setDescription(description);

		Boolean multiple = property.isMultiple();
		if (multiple != null && multiple) {
			MetaInfo createMultiple = ReverseHelper.createMultiple();
			attribute.getMetainfo().add(createMultiple);
		}
		
		Boolean encrypted = property.isEncrypted();
		if (encrypted != null && encrypted) {
			MetaInfo createEncrypted = ReverseHelper.createEncrypted();
			attribute.getMetainfo().add(createEncrypted);
		}
		
		Object default1 = property.getDefault();
		if (default1 instanceof String) {
			attribute.setInitialValue((String) default1);
		}

		// Mandatory
		MandatoryDef mandatory2 = property.getMandatory();
		if (mandatory2 != null) {
			String mandatory = mandatory2.getContent();
			if (mandatory.trim().equals("true")) {
				MetaInfo createRequired = ReverseHelper.createRequired();
				attribute.getMetainfo().add(createRequired);

				// mandatory/@enforced
				Boolean enforced = mandatory2.isEnforced();
				if (enforced != null && enforced) {
					MetaInfo createMandatoryEnforced = ReverseHelper.createMandatoryEnforced();
					attribute.getMetainfo().add(createMandatoryEnforced);
				}
			}
		}

		Index index = property.getIndex();
		if (index != null) {
			String tokenised = index.getTokenised();
			Boolean atomic = index.isAtomic();
			boolean enabled = index.isEnabled();
			Boolean stored = index.isStored();
			if (enabled) {
				MetaInfo createPropertySearched = ReverseHelper.createPropertySearched();
				attribute.getMetainfo().add(createPropertySearched);
			}

			if (atomic != null && atomic) {
				MetaInfo createIndexAtomic = ReverseHelper.createIndexAtomic();
				attribute.getMetainfo().add(createIndexAtomic);
			}
			
			if (stored != null && stored) {
				MetaInfo createIndexStored = ReverseHelper.createIndexStored();
				attribute.getMetainfo().add(createIndexStored);
			}
			
			if (StringUtils.trimToNull(tokenised) != null) {
				MetaInfo createIndexTokenised = ReverseHelper.createIndexTokenised(tokenised);
				attribute.getMetainfo().add(createIndexTokenised);
			}

		}

		// NOT REVERSED SIDE MM do not are equivalent
		// protected

		// constraints
		// SIDE Have Enumeration the equivalent for Alfresco is constraints type=LIST
		com.bluexml.side.alfresco.binding.Property.Constraints constraintsE = property.getConstraints();
		if (constraintsE != null) {
			List<Constraint> constraints = constraintsE.getConstraint();
			for (Constraint constraint : constraints) {
				EObject eObject = null;
				String ref = constraint.getRef();
				if (ref == null) {
					// local declaration
					String constraintsType = constraint.getType();
					if (constraintsType.equals(CONSTRAINTS_ENUMERATION_TYPE)) {
						// Enumeration					
						eObject = createEnumeration(model, constraint, property);
					} else if (constraintsType.equals(CONSTRAINTS_LENGTH)) {
						List<NamedValue> parameters = constraint.getParameter();
						for (NamedValue namedValue : parameters) {
							if (namedValue.getName().equals("minLength")) {
								MetaInfo createMinLength = ReverseHelper.createMinLength(Integer.parseInt(namedValue.getValue()));
								attribute.getMetainfo().add(createMinLength);
							} else if (namedValue.getName().equals("maxLength")) {
								MetaInfo createMaxLength = ReverseHelper.createMaxLength(Integer.parseInt(namedValue.getValue()));
								attribute.getMetainfo().add(createMaxLength);
							}
						}
					} else if (constraintsType.equals(CONSTRAINTS_REGEX)) {
						List<NamedValue> parameters = constraint.getParameter();
						for (NamedValue namedValue : parameters) {
							if (namedValue.getName().equals("expression")) {
								MetaInfo createRegularExpression = ReverseHelper.createRegularExpression(namedValue.getValue());
								attribute.getMetainfo().add(createRegularExpression);

							} else if (namedValue.getName().equals("requiresMatch")) {
								MetaInfo createRegularExpressionMatch = ReverseHelper.createRegularExpressionMatch();
								attribute.getMetainfo().add(createRegularExpressionMatch);
							}
						}

					} else {
						eObject = createConstraints(model, constraint, property);
					}
				} else {
					// referenced constraints
					eObject = register.getEObject(com.bluexml.side.common.impl.ConstraintImpl.class, ref);
					if (eObject == null) {
						// search an Enumeration
						eObject = register.getEObject(EnumerationImpl.class, ref);
					}
				}

				if (eObject instanceof com.bluexml.side.common.Constraint) {
					attribute.getConstraints().add((com.bluexml.side.common.Constraint) eObject);
				} else if (eObject instanceof Enumeration) {
					attribute.setValueList((Enumeration) eObject);
				} else {
					System.err.println("ReverseModel.createAttribute() oups contraints not applied on this attribute " + attribute.getName() + " contraint " + ref);
				}

			}
		}
		ReverseHelper.addSimpleNameTag(attribute);
	}

	private com.bluexml.side.common.Constraint createConstraints(Model model, Constraint constraint, Property localDeclarationContext) {
		String qname;
		if (localDeclarationContext != null) {
			qname = localDeclarationContext.getName();
		} else {
			qname = constraint.getName();
		}
		String constraintsType = constraint.getType();

		com.bluexml.side.common.Constraint createConstraint = CommonFactory.eINSTANCE.createConstraint();
		setNameAndNS(createConstraint, qname);
		model.getConstraintSet().add(createConstraint);
		register.recordNewEObject(createConstraint, qname);

		createConstraint.setConstraintType(constraintsType);
		List<NamedValue> parameters = constraint.getParameter();
		for (NamedValue namedValue : parameters) {
			ConstraintParam createConstraintParam = CommonFactory.eINSTANCE.createConstraintParam();
			createConstraintParam.setName(namedValue.getName());
			createConstraintParam.getValues().add(namedValue.getValue());
			createConstraint.getParams().add(createConstraintParam);
		}
		ReverseHelper.addSimpleNameTag(createConstraint);
		return createConstraint;
	}

	private void addConstraints(Model model, com.bluexml.side.alfresco.binding.Model alfModel) {
		// create constraints
		Constraints constraintsE = alfModel.getConstraints();
		if (constraintsE != null) {

			List<Constraint> constraints = constraintsE.getConstraint();
			if (constraints != null) {
				for (Constraint constraint : constraints) {
					String constraintsType = constraint.getType();
					if (constraintsType.equals(CONSTRAINTS_ENUMERATION_TYPE)) {
						createEnumeration(model, constraint, null);
					} else {
						createConstraints(model, constraint, null);
					}
				}
			}
		}
	}

	private Enumeration createEnumeration(Model model, Constraint constraint, Property localDeclarationContext) {
		Enumeration createEnumeration = ClazzFactory.eINSTANCE.createEnumeration();
		String qname;
		if (localDeclarationContext != null) {
			qname = localDeclarationContext.getName();
		} else {
			qname = constraint.getName();
		}

		setNameAndNS(createEnumeration, qname);
		model.getEnumerationSet().add(createEnumeration);
		register.recordNewEObject(createEnumeration, qname);
		// Enumeration
		List<NamedValue> parameters = constraint.getParameter();
		for (NamedValue namedValue : parameters) {
			if (namedValue.getName().equals("allowedValues")) {
				com.bluexml.side.alfresco.binding.NamedValue.List list = namedValue.getList();
				List<String> values = list.getValue();
				for (String value : values) {
					EnumerationLiteral createEnumerationLiteral = ClazzFactory.eINSTANCE.createEnumerationLiteral();
					createEnumerationLiteral.setName(value);
					createEnumerationLiteral.setValue(value);
					createEnumeration.getLiterals().add(createEnumerationLiteral);
				}
			} else if (namedValue.getName().equals("caseSensitive")) {
				MetaInfo m = CommonFactory.eINSTANCE.createMetaInfo();
				m.setKey("caseSensitive");
				// check that the value is boolean, and set value
				boolean parseBoolean = Boolean.parseBoolean(namedValue.getValue());
				m.setValue(Boolean.toString(parseBoolean));
				createEnumeration.getMetainfo().add(m);
			}

		}
		ReverseHelper.addSimpleNameTag(createEnumeration);
		return createEnumeration;
	}

	private void addNS(Model model, com.bluexml.side.alfresco.binding.Model alfModel) {
		// create/records new namespaces		
		Namespaces namespacesE = alfModel.getNamespaces();
		if (namespacesE != null) {
			List<Namespace> namespaces = namespacesE.getNamespace();
			for (Namespace namespace : namespaces) {
				NameSpace ns = CommonFactory.eINSTANCE.createNameSpace();
				model.getNamespaceSet().add(ns);

				ns.setPrefix(namespace.getPrefix());
				ns.setURI(namespace.getUri());
				model.getNamespaceSet().add(ns);
				register.addNewNS(ns);
			}
		}
	}

	private void setNameAndNS(NamedModelElement nme, String qname) {
		if (qname != null) {
			String name = ReverseHelper.extractLocalNameFromAlfQName(qname);
			String prefix = ReverseHelper.extractPrefixFromAlfQName(qname);
			nme.setName(name);
			setNSRef(nme, prefix);
		} else {
			System.err.println("Unable to set Name and NS for qname null :" + nme);
		}

	}

	private void setNSRef(ModelElement me, String prefix) {
		NameSpace ns = register.getNSForPrefix(prefix);
		me.setNamespace(ns);
	}

	private AbstractClass getAbClazzRef(String qname, Model model, com.bluexml.side.alfresco.binding.Model alfModel) throws Exception {
		com.bluexml.side.clazz.Aspect oa = (com.bluexml.side.clazz.Aspect) register.getEObject(AspectImpl.class, qname);
		Clazz oc = (Clazz) register.getEObject(ClazzImpl.class, qname);

		if (oc == null && oa == null) {
			com.bluexml.side.alfresco.binding.Class alf_modelObject = getAspectOrType(qname, alfModel);
			if (alf_modelObject instanceof Aspect) {
				return createNewAspect(model, alf_modelObject, alfModel);
			} else if (alf_modelObject instanceof Type) {
				return createNewClazz(model, alf_modelObject, alfModel);
			}
		} else if (oc != null && oa != null) {
			System.err.println("Error found a Type AND an Aspect for QName :" + qname);
			return null;
		} else if (oc != null) {
			return oc;
		} else if (oa != null) {
			return oa;
		}

		throw new Exception("Refrence not found :" + qname);
	}

	private com.bluexml.side.alfresco.binding.Class getAspectOrType(String qname, com.bluexml.side.alfresco.binding.Model alfModel) throws Exception {
		List<Aspect> aspect = alfModel.getAspects().getAspect();
		for (Aspect type2 : aspect) {
			if (type2.getName().equals(qname)) {
				return type2;
			}
		}
		List<Type> type = alfModel.getTypes().getType();
		for (Type type2 : type) {
			if (type2.getName().equals(qname)) {
				return type2;
			}
		}
		throw new Exception("Fail to found Type object for " + qname + " please add missing models");
	}
}
