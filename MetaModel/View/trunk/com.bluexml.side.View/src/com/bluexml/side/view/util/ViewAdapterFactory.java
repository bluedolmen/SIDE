/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.bluexml.side.view.util;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;
import org.eclipse.emf.ecore.EObject;

import com.bluexml.side.common.MetaData;
import com.bluexml.side.common.MetaInfoGroup;
import com.bluexml.side.common.ModelElement;
import com.bluexml.side.common.NamedModelElement;
import com.bluexml.side.view.AbstractDataTable;
import com.bluexml.side.view.AbstractView;
import com.bluexml.side.view.AbstractViewOf;
import com.bluexml.side.view.ActionField;
import com.bluexml.side.view.Actionable;
import com.bluexml.side.view.BooleanField;
import com.bluexml.side.view.Col;
import com.bluexml.side.view.ComposedView;
import com.bluexml.side.view.DataList;
import com.bluexml.side.view.DataTable;
import com.bluexml.side.view.DataTableElement;
import com.bluexml.side.view.DateField;
import com.bluexml.side.view.DateTimeField;
import com.bluexml.side.view.Editable;
import com.bluexml.side.view.EmailField;
import com.bluexml.side.view.FacetMap;
import com.bluexml.side.view.Field;
import com.bluexml.side.view.FieldContainer;
import com.bluexml.side.view.FieldElement;
import com.bluexml.side.view.FieldGroup;
import com.bluexml.side.view.FileField;
import com.bluexml.side.view.Filterable;
import com.bluexml.side.view.Filtering;
import com.bluexml.side.view.FloatField;
import com.bluexml.side.view.HtmlField;
import com.bluexml.side.view.ImageField;
import com.bluexml.side.view.IntegerField;
import com.bluexml.side.view.Movable;
import com.bluexml.side.view.Paginable;
import com.bluexml.side.view.Paging;
import com.bluexml.side.view.PasswordField;
import com.bluexml.side.view.PhoneNumberField;
import com.bluexml.side.view.SelectField;
import com.bluexml.side.view.Sortable;
import com.bluexml.side.view.Sorting;
import com.bluexml.side.view.Stylable;
import com.bluexml.side.view.Styling;
import com.bluexml.side.view.TextField;
import com.bluexml.side.view.TimeField;
import com.bluexml.side.view.Tree;
import com.bluexml.side.view.URLField;
import com.bluexml.side.view.ViewCollection;
import com.bluexml.side.view.ViewPackage;

/**
 * <!-- begin-user-doc -->
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see com.bluexml.side.view.ViewPackage
 * @generated
 */
public class ViewAdapterFactory extends AdapterFactoryImpl {
	/**
	 * The cached model package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static ViewPackage modelPackage;

	/**
	 * Creates an instance of the adapter factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ViewAdapterFactory() {
		if (modelPackage == null) {
			modelPackage = ViewPackage.eINSTANCE;
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
	protected ViewSwitch<Adapter> modelSwitch =
		new ViewSwitch<Adapter>() {
			@Override
			public Adapter caseViewCollection(ViewCollection object) {
				return createViewCollectionAdapter();
			}
			@Override
			public Adapter caseFieldContainer(FieldContainer object) {
				return createFieldContainerAdapter();
			}
			@Override
			public Adapter caseFieldElement(FieldElement object) {
				return createFieldElementAdapter();
			}
			@Override
			public Adapter caseAbstractView(AbstractView object) {
				return createAbstractViewAdapter();
			}
			@Override
			public Adapter caseAbstractViewOf(AbstractViewOf object) {
				return createAbstractViewOfAdapter();
			}
			@Override
			public Adapter caseAbstractDataTable(AbstractDataTable object) {
				return createAbstractDataTableAdapter();
			}
			@Override
			public Adapter caseCol(Col object) {
				return createColAdapter();
			}
			@Override
			public Adapter casePaging(Paging object) {
				return createPagingAdapter();
			}
			@Override
			public Adapter caseSorting(Sorting object) {
				return createSortingAdapter();
			}
			@Override
			public Adapter caseFiltering(Filtering object) {
				return createFilteringAdapter();
			}
			@Override
			public Adapter caseStyling(Styling object) {
				return createStylingAdapter();
			}
			@Override
			public Adapter caseDataTableElement(DataTableElement object) {
				return createDataTableElementAdapter();
			}
			@Override
			public Adapter caseDataList(DataList object) {
				return createDataListAdapter();
			}
			@Override
			public Adapter caseDataTable(DataTable object) {
				return createDataTableAdapter();
			}
			@Override
			public Adapter caseFacetMap(FacetMap object) {
				return createFacetMapAdapter();
			}
			@Override
			public Adapter caseTree(Tree object) {
				return createTreeAdapter();
			}
			@Override
			public Adapter caseComposedView(ComposedView object) {
				return createComposedViewAdapter();
			}
			@Override
			public Adapter caseField(Field object) {
				return createFieldAdapter();
			}
			@Override
			public Adapter caseTextField(TextField object) {
				return createTextFieldAdapter();
			}
			@Override
			public Adapter caseBooleanField(BooleanField object) {
				return createBooleanFieldAdapter();
			}
			@Override
			public Adapter casePasswordField(PasswordField object) {
				return createPasswordFieldAdapter();
			}
			@Override
			public Adapter caseFloatField(FloatField object) {
				return createFloatFieldAdapter();
			}
			@Override
			public Adapter caseActionField(ActionField object) {
				return createActionFieldAdapter();
			}
			@Override
			public Adapter caseDateField(DateField object) {
				return createDateFieldAdapter();
			}
			@Override
			public Adapter caseTimeField(TimeField object) {
				return createTimeFieldAdapter();
			}
			@Override
			public Adapter caseDateTimeField(DateTimeField object) {
				return createDateTimeFieldAdapter();
			}
			@Override
			public Adapter casePhoneNumberField(PhoneNumberField object) {
				return createPhoneNumberFieldAdapter();
			}
			@Override
			public Adapter caseEmailField(EmailField object) {
				return createEmailFieldAdapter();
			}
			@Override
			public Adapter caseIntegerField(IntegerField object) {
				return createIntegerFieldAdapter();
			}
			@Override
			public Adapter caseFileField(FileField object) {
				return createFileFieldAdapter();
			}
			@Override
			public Adapter caseSelectField(SelectField object) {
				return createSelectFieldAdapter();
			}
			@Override
			public Adapter caseHtmlField(HtmlField object) {
				return createHtmlFieldAdapter();
			}
			@Override
			public Adapter caseURLField(URLField object) {
				return createURLFieldAdapter();
			}
			@Override
			public Adapter caseImageField(ImageField object) {
				return createImageFieldAdapter();
			}
			@Override
			public Adapter caseStylable(Stylable object) {
				return createStylableAdapter();
			}
			@Override
			public Adapter casePaginable(Paginable object) {
				return createPaginableAdapter();
			}
			@Override
			public Adapter caseActionable(Actionable object) {
				return createActionableAdapter();
			}
			@Override
			public Adapter caseSortable(Sortable object) {
				return createSortableAdapter();
			}
			@Override
			public Adapter caseEditable(Editable object) {
				return createEditableAdapter();
			}
			@Override
			public Adapter caseMovable(Movable object) {
				return createMovableAdapter();
			}
			@Override
			public Adapter caseFilterable(Filterable object) {
				return createFilterableAdapter();
			}
			@Override
			public Adapter caseFieldGroup(FieldGroup object) {
				return createFieldGroupAdapter();
			}
			@Override
			public Adapter caseModelElement(ModelElement object) {
				return createModelElementAdapter();
			}
			@Override
			public Adapter caseNamedModelElement(NamedModelElement object) {
				return createNamedModelElementAdapter();
			}
			@Override
			public Adapter casePackage(com.bluexml.side.common.Package object) {
				return createPackageAdapter();
			}
			@Override
			public Adapter caseMetaData(MetaData object) {
				return createMetaDataAdapter();
			}
			@Override
			public Adapter caseMetaInfoGroup(MetaInfoGroup object) {
				return createMetaInfoGroupAdapter();
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
	 * Creates a new adapter for an object of class '{@link com.bluexml.side.view.ViewCollection <em>Collection</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.bluexml.side.view.ViewCollection
	 * @generated
	 */
	public Adapter createViewCollectionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.bluexml.side.view.FieldElement <em>Field Element</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.bluexml.side.view.FieldElement
	 * @generated
	 */
	public Adapter createFieldElementAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.bluexml.side.view.AbstractView <em>Abstract View</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.bluexml.side.view.AbstractView
	 * @generated
	 */
	public Adapter createAbstractViewAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.bluexml.side.view.AbstractDataTable <em>Abstract Data Table</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.bluexml.side.view.AbstractDataTable
	 * @generated
	 */
	public Adapter createAbstractDataTableAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.bluexml.side.view.Col <em>Col</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.bluexml.side.view.Col
	 * @generated
	 */
	public Adapter createColAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.bluexml.side.view.Paging <em>Paging</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.bluexml.side.view.Paging
	 * @generated
	 */
	public Adapter createPagingAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.bluexml.side.view.Sorting <em>Sorting</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.bluexml.side.view.Sorting
	 * @generated
	 */
	public Adapter createSortingAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.bluexml.side.view.Filtering <em>Filtering</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.bluexml.side.view.Filtering
	 * @generated
	 */
	public Adapter createFilteringAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.bluexml.side.view.Styling <em>Styling</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.bluexml.side.view.Styling
	 * @generated
	 */
	public Adapter createStylingAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.bluexml.side.view.DataTableElement <em>Data Table Element</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.bluexml.side.view.DataTableElement
	 * @generated
	 */
	public Adapter createDataTableElementAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.bluexml.side.view.DataList <em>Data List</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.bluexml.side.view.DataList
	 * @generated
	 */
	public Adapter createDataListAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.bluexml.side.view.DataTable <em>Data Table</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.bluexml.side.view.DataTable
	 * @generated
	 */
	public Adapter createDataTableAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.bluexml.side.view.FacetMap <em>Facet Map</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.bluexml.side.view.FacetMap
	 * @generated
	 */
	public Adapter createFacetMapAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.bluexml.side.view.Tree <em>Tree</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.bluexml.side.view.Tree
	 * @generated
	 */
	public Adapter createTreeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.bluexml.side.view.ComposedView <em>Composed View</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.bluexml.side.view.ComposedView
	 * @generated
	 */
	public Adapter createComposedViewAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.bluexml.side.view.FieldContainer <em>Field Container</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.bluexml.side.view.FieldContainer
	 * @generated
	 */
	public Adapter createFieldContainerAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.bluexml.side.view.Field <em>Field</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.bluexml.side.view.Field
	 * @generated
	 */
	public Adapter createFieldAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.bluexml.side.view.TextField <em>Text Field</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.bluexml.side.view.TextField
	 * @generated
	 */
	public Adapter createTextFieldAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.bluexml.side.view.PasswordField <em>Password Field</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.bluexml.side.view.PasswordField
	 * @generated
	 */
	public Adapter createPasswordFieldAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.bluexml.side.view.BooleanField <em>Boolean Field</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.bluexml.side.view.BooleanField
	 * @generated
	 */
	public Adapter createBooleanFieldAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.bluexml.side.view.FloatField <em>Float Field</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.bluexml.side.view.FloatField
	 * @generated
	 */
	public Adapter createFloatFieldAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.bluexml.side.view.ActionField <em>Action Field</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.bluexml.side.view.ActionField
	 * @generated
	 */
	public Adapter createActionFieldAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.bluexml.side.view.DateField <em>Date Field</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.bluexml.side.view.DateField
	 * @generated
	 */
	public Adapter createDateFieldAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.bluexml.side.view.TimeField <em>Time Field</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.bluexml.side.view.TimeField
	 * @generated
	 */
	public Adapter createTimeFieldAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.bluexml.side.view.DateTimeField <em>Date Time Field</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.bluexml.side.view.DateTimeField
	 * @generated
	 */
	public Adapter createDateTimeFieldAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.bluexml.side.view.PhoneNumberField <em>Phone Number Field</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.bluexml.side.view.PhoneNumberField
	 * @generated
	 */
	public Adapter createPhoneNumberFieldAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.bluexml.side.view.EmailField <em>Email Field</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.bluexml.side.view.EmailField
	 * @generated
	 */
	public Adapter createEmailFieldAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.bluexml.side.view.IntegerField <em>Integer Field</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.bluexml.side.view.IntegerField
	 * @generated
	 */
	public Adapter createIntegerFieldAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.bluexml.side.view.FileField <em>File Field</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.bluexml.side.view.FileField
	 * @generated
	 */
	public Adapter createFileFieldAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.bluexml.side.view.SelectField <em>Select Field</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.bluexml.side.view.SelectField
	 * @generated
	 */
	public Adapter createSelectFieldAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.bluexml.side.view.HtmlField <em>Html Field</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.bluexml.side.view.HtmlField
	 * @generated
	 */
	public Adapter createHtmlFieldAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.bluexml.side.view.URLField <em>URL Field</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.bluexml.side.view.URLField
	 * @generated
	 */
	public Adapter createURLFieldAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.bluexml.side.view.ImageField <em>Image Field</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.bluexml.side.view.ImageField
	 * @generated
	 */
	public Adapter createImageFieldAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.bluexml.side.view.Stylable <em>Stylable</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.bluexml.side.view.Stylable
	 * @generated
	 */
	public Adapter createStylableAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.bluexml.side.view.Paginable <em>Paginable</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.bluexml.side.view.Paginable
	 * @generated
	 */
	public Adapter createPaginableAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.bluexml.side.view.Sortable <em>Sortable</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.bluexml.side.view.Sortable
	 * @generated
	 */
	public Adapter createSortableAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.bluexml.side.view.Editable <em>Editable</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.bluexml.side.view.Editable
	 * @generated
	 */
	public Adapter createEditableAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.bluexml.side.view.Movable <em>Movable</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.bluexml.side.view.Movable
	 * @generated
	 */
	public Adapter createMovableAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.bluexml.side.view.Filterable <em>Filterable</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.bluexml.side.view.Filterable
	 * @generated
	 */
	public Adapter createFilterableAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.bluexml.side.view.FieldGroup <em>Field Group</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.bluexml.side.view.FieldGroup
	 * @generated
	 */
	public Adapter createFieldGroupAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.bluexml.side.view.AbstractViewOf <em>Abstract View Of</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.bluexml.side.view.AbstractViewOf
	 * @generated
	 */
	public Adapter createAbstractViewOfAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.bluexml.side.view.Actionable <em>Actionable</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.bluexml.side.view.Actionable
	 * @generated
	 */
	public Adapter createActionableAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.bluexml.side.common.ModelElement <em>Model Element</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.bluexml.side.common.ModelElement
	 * @generated
	 */
	public Adapter createModelElementAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.bluexml.side.common.NamedModelElement <em>Named Model Element</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.bluexml.side.common.NamedModelElement
	 * @generated
	 */
	public Adapter createNamedModelElementAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.bluexml.side.common.Package <em>Package</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.bluexml.side.common.Package
	 * @generated
	 */
	public Adapter createPackageAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.bluexml.side.common.MetaData <em>Meta Data</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.bluexml.side.common.MetaData
	 * @generated
	 */
	public Adapter createMetaDataAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.bluexml.side.common.MetaInfoGroup <em>Meta Info Group</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.bluexml.side.common.MetaInfoGroup
	 * @generated
	 */
	public Adapter createMetaInfoGroupAdapter() {
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

} //ViewAdapterFactory
