/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.bluexml.side.requirements.generator.metamodel.WebProject.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;

import com.bluexml.side.requirements.generator.metamodel.WebProject.Component;
import com.bluexml.side.requirements.generator.metamodel.WebProject.ComponentAttribute;
import com.bluexml.side.requirements.generator.metamodel.WebProject.ComponentRelationShip;
import com.bluexml.side.requirements.generator.metamodel.WebProject.DataPage;
import com.bluexml.side.requirements.generator.metamodel.WebProject.Field;
import com.bluexml.side.requirements.generator.metamodel.WebProject.FramePage;
import com.bluexml.side.requirements.generator.metamodel.WebProject.GoalItem;
import com.bluexml.side.requirements.generator.metamodel.WebProject.GoalPage;
import com.bluexml.side.requirements.generator.metamodel.WebProject.Link;
import com.bluexml.side.requirements.generator.metamodel.WebProject.LoginPage;
import com.bluexml.side.requirements.generator.metamodel.WebProject.Project;
import com.bluexml.side.requirements.generator.metamodel.WebProject.Schema;
import com.bluexml.side.requirements.generator.metamodel.WebProject.Table;
import com.bluexml.side.requirements.generator.metamodel.WebProject.WebProjectFactory;
import com.bluexml.side.requirements.generator.metamodel.WebProject.WebProjectPackage;
import com.bluexml.side.requirements.generator.metamodel.WebProject.dataType;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class WebProjectFactoryImpl extends EFactoryImpl implements WebProjectFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static WebProjectFactory init() {
		try {
			WebProjectFactory theWebProjectFactory = (WebProjectFactory)EPackage.Registry.INSTANCE.getEFactory("http://www.bluexml.com/rwm/webproject/1.0/"); 
			if (theWebProjectFactory != null) {
				return theWebProjectFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new WebProjectFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public WebProjectFactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
			case WebProjectPackage.PROJECT: return createProject();
			case WebProjectPackage.LINK: return createLink();
			case WebProjectPackage.LOGIN_PAGE: return createLoginPage();
			case WebProjectPackage.GOAL_PAGE: return createGoalPage();
			case WebProjectPackage.GOAL_ITEM: return createGoalItem();
			case WebProjectPackage.DATA_PAGE: return createDataPage();
			case WebProjectPackage.FRAME_PAGE: return createFramePage();
			case WebProjectPackage.COMPONENT: return createComponent();
			case WebProjectPackage.COMPONENT_ATTRIBUTE: return createComponentAttribute();
			case WebProjectPackage.COMPONENT_RELATION_SHIP: return createComponentRelationShip();
			case WebProjectPackage.SCHEMA: return createSchema();
			case WebProjectPackage.TABLE: return createTable();
			case WebProjectPackage.FIELD: return createField();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object createFromString(EDataType eDataType, String initialValue) {
		switch (eDataType.getClassifierID()) {
			case WebProjectPackage.DATA_TYPE:
				return createdataTypeFromString(eDataType, initialValue);
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String convertToString(EDataType eDataType, Object instanceValue) {
		switch (eDataType.getClassifierID()) {
			case WebProjectPackage.DATA_TYPE:
				return convertdataTypeToString(eDataType, instanceValue);
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Project createProject() {
		ProjectImpl project = new ProjectImpl();
		return project;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Link createLink() {
		LinkImpl link = new LinkImpl();
		return link;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LoginPage createLoginPage() {
		LoginPageImpl loginPage = new LoginPageImpl();
		return loginPage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public GoalPage createGoalPage() {
		GoalPageImpl goalPage = new GoalPageImpl();
		return goalPage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public GoalItem createGoalItem() {
		GoalItemImpl goalItem = new GoalItemImpl();
		return goalItem;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DataPage createDataPage() {
		DataPageImpl dataPage = new DataPageImpl();
		return dataPage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FramePage createFramePage() {
		FramePageImpl framePage = new FramePageImpl();
		return framePage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Component createComponent() {
		ComponentImpl component = new ComponentImpl();
		return component;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ComponentAttribute createComponentAttribute() {
		ComponentAttributeImpl componentAttribute = new ComponentAttributeImpl();
		return componentAttribute;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ComponentRelationShip createComponentRelationShip() {
		ComponentRelationShipImpl componentRelationShip = new ComponentRelationShipImpl();
		return componentRelationShip;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Schema createSchema() {
		SchemaImpl schema = new SchemaImpl();
		return schema;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Table createTable() {
		TableImpl table = new TableImpl();
		return table;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Field createField() {
		FieldImpl field = new FieldImpl();
		return field;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public dataType createdataTypeFromString(EDataType eDataType, String initialValue) {
		dataType result = dataType.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertdataTypeToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public WebProjectPackage getWebProjectPackage() {
		return (WebProjectPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static WebProjectPackage getPackage() {
		return WebProjectPackage.eINSTANCE;
	}

} //WebProjectFactoryImpl
