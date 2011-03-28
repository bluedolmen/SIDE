/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.bluexml.side.requirements.generator.metamodel.WebProject.util;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;
import org.eclipse.emf.ecore.EObject;

import com.bluexml.side.requirements.generator.metamodel.WebProject.Component;
import com.bluexml.side.requirements.generator.metamodel.WebProject.ComponentAttribute;
import com.bluexml.side.requirements.generator.metamodel.WebProject.ComponentProperty;
import com.bluexml.side.requirements.generator.metamodel.WebProject.ComponentRelationShip;
import com.bluexml.side.requirements.generator.metamodel.WebProject.DataPage;
import com.bluexml.side.requirements.generator.metamodel.WebProject.Field;
import com.bluexml.side.requirements.generator.metamodel.WebProject.FramePage;
import com.bluexml.side.requirements.generator.metamodel.WebProject.GoalItem;
import com.bluexml.side.requirements.generator.metamodel.WebProject.GoalPage;
import com.bluexml.side.requirements.generator.metamodel.WebProject.Link;
import com.bluexml.side.requirements.generator.metamodel.WebProject.LoginPage;
import com.bluexml.side.requirements.generator.metamodel.WebProject.Page;
import com.bluexml.side.requirements.generator.metamodel.WebProject.Project;
import com.bluexml.side.requirements.generator.metamodel.WebProject.Schema;
import com.bluexml.side.requirements.generator.metamodel.WebProject.Table;
import com.bluexml.side.requirements.generator.metamodel.WebProject.WebProjectPackage;

/**
 * <!-- begin-user-doc -->
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see com.bluexml.side.requirements.generator.metamodel.WebProject.WebProjectPackage
 * @generated
 */
public class WebProjectAdapterFactory extends AdapterFactoryImpl {
	/**
	 * The cached model package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static WebProjectPackage modelPackage;

	/**
	 * Creates an instance of the adapter factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public WebProjectAdapterFactory() {
		if (modelPackage == null) {
			modelPackage = WebProjectPackage.eINSTANCE;
		}
	}

	/**
	 * Returns whether this factory is applicable for the type of the object.
	 * <!-- begin-user-doc -->
	 * This implementation returns <code>true</code> if the object is either the model's package or is an instance object of the model.
	 * <!-- end-user-doc -->
	 * @return whether this factory is applicable for the type of the object.
	 * @generated
	 */
	@Override
	public boolean isFactoryForType(Object object) {
		if (object == modelPackage) {
			return true;
		}
		if (object instanceof EObject) {
			return ((EObject)object).eClass().getEPackage() == modelPackage;
		}
		return false;
	}

	/**
	 * The switch that delegates to the <code>createXXX</code> methods.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected WebProjectSwitch<Adapter> modelSwitch =
		new WebProjectSwitch<Adapter>() {
			@Override
			public Adapter caseProject(Project object) {
				return createProjectAdapter();
			}
			@Override
			public Adapter caseLink(Link object) {
				return createLinkAdapter();
			}
			@Override
			public Adapter casePage(Page object) {
				return createPageAdapter();
			}
			@Override
			public Adapter caseLoginPage(LoginPage object) {
				return createLoginPageAdapter();
			}
			@Override
			public Adapter caseGoalPage(GoalPage object) {
				return createGoalPageAdapter();
			}
			@Override
			public Adapter caseGoalItem(GoalItem object) {
				return createGoalItemAdapter();
			}
			@Override
			public Adapter caseDataPage(DataPage object) {
				return createDataPageAdapter();
			}
			@Override
			public Adapter caseFramePage(FramePage object) {
				return createFramePageAdapter();
			}
			@Override
			public Adapter caseComponent(Component object) {
				return createComponentAdapter();
			}
			@Override
			public Adapter caseComponentProperty(ComponentProperty object) {
				return createComponentPropertyAdapter();
			}
			@Override
			public Adapter caseComponentAttribute(ComponentAttribute object) {
				return createComponentAttributeAdapter();
			}
			@Override
			public Adapter caseComponentRelationShip(ComponentRelationShip object) {
				return createComponentRelationShipAdapter();
			}
			@Override
			public Adapter caseSchema(Schema object) {
				return createSchemaAdapter();
			}
			@Override
			public Adapter caseTable(Table object) {
				return createTableAdapter();
			}
			@Override
			public Adapter caseField(Field object) {
				return createFieldAdapter();
			}
			@Override
			public Adapter defaultCase(EObject object) {
				return createEObjectAdapter();
			}
		};

	/**
	 * Creates an adapter for the <code>target</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param target the object to adapt.
	 * @return the adapter for the <code>target</code>.
	 * @generated
	 */
	@Override
	public Adapter createAdapter(Notifier target) {
		return modelSwitch.doSwitch((EObject)target);
	}


	/**
	 * Creates a new adapter for an object of class '{@link com.bluexml.side.requirements.generator.metamodel.WebProject.Project <em>Project</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.bluexml.side.requirements.generator.metamodel.WebProject.Project
	 * @generated
	 */
	public Adapter createProjectAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.bluexml.side.requirements.generator.metamodel.WebProject.Link <em>Link</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.bluexml.side.requirements.generator.metamodel.WebProject.Link
	 * @generated
	 */
	public Adapter createLinkAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.bluexml.side.requirements.generator.metamodel.WebProject.Page <em>Page</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.bluexml.side.requirements.generator.metamodel.WebProject.Page
	 * @generated
	 */
	public Adapter createPageAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.bluexml.side.requirements.generator.metamodel.WebProject.LoginPage <em>Login Page</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.bluexml.side.requirements.generator.metamodel.WebProject.LoginPage
	 * @generated
	 */
	public Adapter createLoginPageAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.bluexml.side.requirements.generator.metamodel.WebProject.GoalPage <em>Goal Page</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.bluexml.side.requirements.generator.metamodel.WebProject.GoalPage
	 * @generated
	 */
	public Adapter createGoalPageAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.bluexml.side.requirements.generator.metamodel.WebProject.GoalItem <em>Goal Item</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.bluexml.side.requirements.generator.metamodel.WebProject.GoalItem
	 * @generated
	 */
	public Adapter createGoalItemAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.bluexml.side.requirements.generator.metamodel.WebProject.DataPage <em>Data Page</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.bluexml.side.requirements.generator.metamodel.WebProject.DataPage
	 * @generated
	 */
	public Adapter createDataPageAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.bluexml.side.requirements.generator.metamodel.WebProject.FramePage <em>Frame Page</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.bluexml.side.requirements.generator.metamodel.WebProject.FramePage
	 * @generated
	 */
	public Adapter createFramePageAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.bluexml.side.requirements.generator.metamodel.WebProject.Component <em>Component</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.bluexml.side.requirements.generator.metamodel.WebProject.Component
	 * @generated
	 */
	public Adapter createComponentAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.bluexml.side.requirements.generator.metamodel.WebProject.ComponentProperty <em>Component Property</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.bluexml.side.requirements.generator.metamodel.WebProject.ComponentProperty
	 * @generated
	 */
	public Adapter createComponentPropertyAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.bluexml.side.requirements.generator.metamodel.WebProject.ComponentAttribute <em>Component Attribute</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.bluexml.side.requirements.generator.metamodel.WebProject.ComponentAttribute
	 * @generated
	 */
	public Adapter createComponentAttributeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.bluexml.side.requirements.generator.metamodel.WebProject.ComponentRelationShip <em>Component Relation Ship</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.bluexml.side.requirements.generator.metamodel.WebProject.ComponentRelationShip
	 * @generated
	 */
	public Adapter createComponentRelationShipAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.bluexml.side.requirements.generator.metamodel.WebProject.Schema <em>Schema</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.bluexml.side.requirements.generator.metamodel.WebProject.Schema
	 * @generated
	 */
	public Adapter createSchemaAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.bluexml.side.requirements.generator.metamodel.WebProject.Table <em>Table</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.bluexml.side.requirements.generator.metamodel.WebProject.Table
	 * @generated
	 */
	public Adapter createTableAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.bluexml.side.requirements.generator.metamodel.WebProject.Field <em>Field</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.bluexml.side.requirements.generator.metamodel.WebProject.Field
	 * @generated
	 */
	public Adapter createFieldAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for the default case.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @generated
	 */
	public Adapter createEObjectAdapter() {
		return null;
	}

} //WebProjectAdapterFactory
