/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.bluexml.side.view.util;

import java.util.List;

import org.eclipse.emf.ecore.EClass;
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
 * The <b>Switch</b> for the model's inheritance hierarchy.
 * It supports the call {@link #doSwitch(EObject) doSwitch(object)}
 * to invoke the <code>caseXXX</code> method for each class of the model,
 * starting with the actual class of the object
 * and proceeding up the inheritance hierarchy
 * until a non-null result is returned,
 * which is the result of the switch.
 * <!-- end-user-doc -->
 * @see com.bluexml.side.view.ViewPackage
 * @generated
 */
public class ViewSwitch<T> {
	/**
	 * The cached model package
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static ViewPackage modelPackage;

	/**
	 * Creates an instance of the switch.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ViewSwitch() {
		if (modelPackage == null) {
			modelPackage = ViewPackage.eINSTANCE;
		}
	}

	/**
	 * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the first non-null result returned by a <code>caseXXX</code> call.
	 * @generated
	 */
	public T doSwitch(EObject theEObject) {
		return doSwitch(theEObject.eClass(), theEObject);
	}

	/**
	 * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the first non-null result returned by a <code>caseXXX</code> call.
	 * @generated
	 */
	protected T doSwitch(EClass theEClass, EObject theEObject) {
		if (theEClass.eContainer() == modelPackage) {
			return doSwitch(theEClass.getClassifierID(), theEObject);
		}
		else {
			List<EClass> eSuperTypes = theEClass.getESuperTypes();
			return
				eSuperTypes.isEmpty() ?
					defaultCase(theEObject) :
					doSwitch(eSuperTypes.get(0), theEObject);
		}
	}

	/**
	 * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the first non-null result returned by a <code>caseXXX</code> call.
	 * @generated
	 */
	protected T doSwitch(int classifierID, EObject theEObject) {
		switch (classifierID) {
			case ViewPackage.VIEW_COLLECTION: {
				ViewCollection viewCollection = (ViewCollection)theEObject;
				T result = caseViewCollection(viewCollection);
				if (result == null) result = casePackage(viewCollection);
				if (result == null) result = caseNamedModelElement(viewCollection);
				if (result == null) result = caseModelElement(viewCollection);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ViewPackage.FIELD_CONTAINER: {
				FieldContainer fieldContainer = (FieldContainer)theEObject;
				T result = caseFieldContainer(fieldContainer);
				if (result == null) result = caseFieldElement(fieldContainer);
				if (result == null) result = caseStylable(fieldContainer);
				if (result == null) result = caseNamedModelElement(fieldContainer);
				if (result == null) result = caseModelElement(fieldContainer);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ViewPackage.FIELD_ELEMENT: {
				FieldElement fieldElement = (FieldElement)theEObject;
				T result = caseFieldElement(fieldElement);
				if (result == null) result = caseStylable(fieldElement);
				if (result == null) result = caseNamedModelElement(fieldElement);
				if (result == null) result = caseModelElement(fieldElement);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ViewPackage.ABSTRACT_VIEW: {
				AbstractView abstractView = (AbstractView)theEObject;
				T result = caseAbstractView(abstractView);
				if (result == null) result = caseFieldContainer(abstractView);
				if (result == null) result = caseFieldElement(abstractView);
				if (result == null) result = caseStylable(abstractView);
				if (result == null) result = caseNamedModelElement(abstractView);
				if (result == null) result = caseModelElement(abstractView);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ViewPackage.ABSTRACT_VIEW_OF: {
				AbstractViewOf abstractViewOf = (AbstractViewOf)theEObject;
				T result = caseAbstractViewOf(abstractViewOf);
				if (result == null) result = caseAbstractView(abstractViewOf);
				if (result == null) result = caseFieldContainer(abstractViewOf);
				if (result == null) result = caseFieldElement(abstractViewOf);
				if (result == null) result = caseStylable(abstractViewOf);
				if (result == null) result = caseNamedModelElement(abstractViewOf);
				if (result == null) result = caseModelElement(abstractViewOf);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ViewPackage.ABSTRACT_DATA_TABLE: {
				AbstractDataTable abstractDataTable = (AbstractDataTable)theEObject;
				T result = caseAbstractDataTable(abstractDataTable);
				if (result == null) result = caseAbstractViewOf(abstractDataTable);
				if (result == null) result = caseDataTableElement(abstractDataTable);
				if (result == null) result = casePaginable(abstractDataTable);
				if (result == null) result = caseAbstractView(abstractDataTable);
				if (result == null) result = caseFieldContainer(abstractDataTable);
				if (result == null) result = caseFieldElement(abstractDataTable);
				if (result == null) result = caseStylable(abstractDataTable);
				if (result == null) result = caseNamedModelElement(abstractDataTable);
				if (result == null) result = caseModelElement(abstractDataTable);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ViewPackage.COL: {
				Col col = (Col)theEObject;
				T result = caseCol(col);
				if (result == null) result = caseFieldContainer(col);
				if (result == null) result = caseMovable(col);
				if (result == null) result = caseEditable(col);
				if (result == null) result = caseFilterable(col);
				if (result == null) result = caseSortable(col);
				if (result == null) result = caseActionable(col);
				if (result == null) result = caseFieldElement(col);
				if (result == null) result = caseStylable(col);
				if (result == null) result = caseNamedModelElement(col);
				if (result == null) result = caseModelElement(col);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ViewPackage.PAGING: {
				Paging paging = (Paging)theEObject;
				T result = casePaging(paging);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ViewPackage.SORTING: {
				Sorting sorting = (Sorting)theEObject;
				T result = caseSorting(sorting);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ViewPackage.FILTERING: {
				Filtering filtering = (Filtering)theEObject;
				T result = caseFiltering(filtering);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ViewPackage.STYLING: {
				Styling styling = (Styling)theEObject;
				T result = caseStyling(styling);
				if (result == null) result = caseMetaInfoGroup(styling);
				if (result == null) result = caseMetaData(styling);
				if (result == null) result = caseNamedModelElement(styling);
				if (result == null) result = caseModelElement(styling);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ViewPackage.DATA_TABLE_ELEMENT: {
				DataTableElement dataTableElement = (DataTableElement)theEObject;
				T result = caseDataTableElement(dataTableElement);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ViewPackage.DATA_LIST: {
				DataList dataList = (DataList)theEObject;
				T result = caseDataList(dataList);
				if (result == null) result = caseAbstractDataTable(dataList);
				if (result == null) result = caseCol(dataList);
				if (result == null) result = caseAbstractViewOf(dataList);
				if (result == null) result = caseDataTableElement(dataList);
				if (result == null) result = casePaginable(dataList);
				if (result == null) result = caseMovable(dataList);
				if (result == null) result = caseEditable(dataList);
				if (result == null) result = caseFilterable(dataList);
				if (result == null) result = caseSortable(dataList);
				if (result == null) result = caseActionable(dataList);
				if (result == null) result = caseAbstractView(dataList);
				if (result == null) result = caseFieldContainer(dataList);
				if (result == null) result = caseFieldElement(dataList);
				if (result == null) result = caseStylable(dataList);
				if (result == null) result = caseNamedModelElement(dataList);
				if (result == null) result = caseModelElement(dataList);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ViewPackage.DATA_TABLE: {
				DataTable dataTable = (DataTable)theEObject;
				T result = caseDataTable(dataTable);
				if (result == null) result = caseAbstractDataTable(dataTable);
				if (result == null) result = caseActionable(dataTable);
				if (result == null) result = caseAbstractViewOf(dataTable);
				if (result == null) result = caseDataTableElement(dataTable);
				if (result == null) result = casePaginable(dataTable);
				if (result == null) result = caseAbstractView(dataTable);
				if (result == null) result = caseFieldContainer(dataTable);
				if (result == null) result = caseFieldElement(dataTable);
				if (result == null) result = caseStylable(dataTable);
				if (result == null) result = caseNamedModelElement(dataTable);
				if (result == null) result = caseModelElement(dataTable);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ViewPackage.FACET_MAP: {
				FacetMap facetMap = (FacetMap)theEObject;
				T result = caseFacetMap(facetMap);
				if (result == null) result = caseAbstractViewOf(facetMap);
				if (result == null) result = casePaginable(facetMap);
				if (result == null) result = caseActionable(facetMap);
				if (result == null) result = caseAbstractView(facetMap);
				if (result == null) result = caseFieldContainer(facetMap);
				if (result == null) result = caseFieldElement(facetMap);
				if (result == null) result = caseStylable(facetMap);
				if (result == null) result = caseNamedModelElement(facetMap);
				if (result == null) result = caseModelElement(facetMap);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ViewPackage.TREE: {
				Tree tree = (Tree)theEObject;
				T result = caseTree(tree);
				if (result == null) result = caseAbstractViewOf(tree);
				if (result == null) result = caseSortable(tree);
				if (result == null) result = caseEditable(tree);
				if (result == null) result = caseMovable(tree);
				if (result == null) result = caseFilterable(tree);
				if (result == null) result = caseActionable(tree);
				if (result == null) result = caseAbstractView(tree);
				if (result == null) result = caseFieldContainer(tree);
				if (result == null) result = caseFieldElement(tree);
				if (result == null) result = caseStylable(tree);
				if (result == null) result = caseNamedModelElement(tree);
				if (result == null) result = caseModelElement(tree);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ViewPackage.COMPOSED_VIEW: {
				ComposedView composedView = (ComposedView)theEObject;
				T result = caseComposedView(composedView);
				if (result == null) result = caseAbstractView(composedView);
				if (result == null) result = caseFieldContainer(composedView);
				if (result == null) result = caseFieldElement(composedView);
				if (result == null) result = caseStylable(composedView);
				if (result == null) result = caseNamedModelElement(composedView);
				if (result == null) result = caseModelElement(composedView);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ViewPackage.FIELD: {
				Field field = (Field)theEObject;
				T result = caseField(field);
				if (result == null) result = caseFieldElement(field);
				if (result == null) result = caseStylable(field);
				if (result == null) result = caseNamedModelElement(field);
				if (result == null) result = caseModelElement(field);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ViewPackage.TEXT_FIELD: {
				TextField textField = (TextField)theEObject;
				T result = caseTextField(textField);
				if (result == null) result = caseField(textField);
				if (result == null) result = caseFieldElement(textField);
				if (result == null) result = caseStylable(textField);
				if (result == null) result = caseNamedModelElement(textField);
				if (result == null) result = caseModelElement(textField);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ViewPackage.BOOLEAN_FIELD: {
				BooleanField booleanField = (BooleanField)theEObject;
				T result = caseBooleanField(booleanField);
				if (result == null) result = caseField(booleanField);
				if (result == null) result = caseFieldElement(booleanField);
				if (result == null) result = caseStylable(booleanField);
				if (result == null) result = caseNamedModelElement(booleanField);
				if (result == null) result = caseModelElement(booleanField);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ViewPackage.PASSWORD_FIELD: {
				PasswordField passwordField = (PasswordField)theEObject;
				T result = casePasswordField(passwordField);
				if (result == null) result = caseField(passwordField);
				if (result == null) result = caseFieldElement(passwordField);
				if (result == null) result = caseStylable(passwordField);
				if (result == null) result = caseNamedModelElement(passwordField);
				if (result == null) result = caseModelElement(passwordField);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ViewPackage.FLOAT_FIELD: {
				FloatField floatField = (FloatField)theEObject;
				T result = caseFloatField(floatField);
				if (result == null) result = caseField(floatField);
				if (result == null) result = caseFieldElement(floatField);
				if (result == null) result = caseStylable(floatField);
				if (result == null) result = caseNamedModelElement(floatField);
				if (result == null) result = caseModelElement(floatField);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ViewPackage.ACTION_FIELD: {
				ActionField actionField = (ActionField)theEObject;
				T result = caseActionField(actionField);
				if (result == null) result = caseField(actionField);
				if (result == null) result = caseFieldElement(actionField);
				if (result == null) result = caseStylable(actionField);
				if (result == null) result = caseNamedModelElement(actionField);
				if (result == null) result = caseModelElement(actionField);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ViewPackage.DATE_FIELD: {
				DateField dateField = (DateField)theEObject;
				T result = caseDateField(dateField);
				if (result == null) result = caseField(dateField);
				if (result == null) result = caseFieldElement(dateField);
				if (result == null) result = caseStylable(dateField);
				if (result == null) result = caseNamedModelElement(dateField);
				if (result == null) result = caseModelElement(dateField);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ViewPackage.TIME_FIELD: {
				TimeField timeField = (TimeField)theEObject;
				T result = caseTimeField(timeField);
				if (result == null) result = caseField(timeField);
				if (result == null) result = caseFieldElement(timeField);
				if (result == null) result = caseStylable(timeField);
				if (result == null) result = caseNamedModelElement(timeField);
				if (result == null) result = caseModelElement(timeField);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ViewPackage.DATE_TIME_FIELD: {
				DateTimeField dateTimeField = (DateTimeField)theEObject;
				T result = caseDateTimeField(dateTimeField);
				if (result == null) result = caseField(dateTimeField);
				if (result == null) result = caseFieldElement(dateTimeField);
				if (result == null) result = caseStylable(dateTimeField);
				if (result == null) result = caseNamedModelElement(dateTimeField);
				if (result == null) result = caseModelElement(dateTimeField);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ViewPackage.PHONE_NUMBER_FIELD: {
				PhoneNumberField phoneNumberField = (PhoneNumberField)theEObject;
				T result = casePhoneNumberField(phoneNumberField);
				if (result == null) result = caseField(phoneNumberField);
				if (result == null) result = caseFieldElement(phoneNumberField);
				if (result == null) result = caseStylable(phoneNumberField);
				if (result == null) result = caseNamedModelElement(phoneNumberField);
				if (result == null) result = caseModelElement(phoneNumberField);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ViewPackage.EMAIL_FIELD: {
				EmailField emailField = (EmailField)theEObject;
				T result = caseEmailField(emailField);
				if (result == null) result = caseField(emailField);
				if (result == null) result = caseFieldElement(emailField);
				if (result == null) result = caseStylable(emailField);
				if (result == null) result = caseNamedModelElement(emailField);
				if (result == null) result = caseModelElement(emailField);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ViewPackage.INTEGER_FIELD: {
				IntegerField integerField = (IntegerField)theEObject;
				T result = caseIntegerField(integerField);
				if (result == null) result = caseField(integerField);
				if (result == null) result = caseFieldElement(integerField);
				if (result == null) result = caseStylable(integerField);
				if (result == null) result = caseNamedModelElement(integerField);
				if (result == null) result = caseModelElement(integerField);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ViewPackage.FILE_FIELD: {
				FileField fileField = (FileField)theEObject;
				T result = caseFileField(fileField);
				if (result == null) result = caseField(fileField);
				if (result == null) result = caseFieldElement(fileField);
				if (result == null) result = caseStylable(fileField);
				if (result == null) result = caseNamedModelElement(fileField);
				if (result == null) result = caseModelElement(fileField);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ViewPackage.SELECT_FIELD: {
				SelectField selectField = (SelectField)theEObject;
				T result = caseSelectField(selectField);
				if (result == null) result = caseField(selectField);
				if (result == null) result = caseFieldElement(selectField);
				if (result == null) result = caseStylable(selectField);
				if (result == null) result = caseNamedModelElement(selectField);
				if (result == null) result = caseModelElement(selectField);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ViewPackage.HTML_FIELD: {
				HtmlField htmlField = (HtmlField)theEObject;
				T result = caseHtmlField(htmlField);
				if (result == null) result = caseField(htmlField);
				if (result == null) result = caseFieldElement(htmlField);
				if (result == null) result = caseStylable(htmlField);
				if (result == null) result = caseNamedModelElement(htmlField);
				if (result == null) result = caseModelElement(htmlField);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ViewPackage.URL_FIELD: {
				URLField urlField = (URLField)theEObject;
				T result = caseURLField(urlField);
				if (result == null) result = caseField(urlField);
				if (result == null) result = caseFieldElement(urlField);
				if (result == null) result = caseStylable(urlField);
				if (result == null) result = caseNamedModelElement(urlField);
				if (result == null) result = caseModelElement(urlField);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ViewPackage.IMAGE_FIELD: {
				ImageField imageField = (ImageField)theEObject;
				T result = caseImageField(imageField);
				if (result == null) result = caseFileField(imageField);
				if (result == null) result = caseField(imageField);
				if (result == null) result = caseFieldElement(imageField);
				if (result == null) result = caseStylable(imageField);
				if (result == null) result = caseNamedModelElement(imageField);
				if (result == null) result = caseModelElement(imageField);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ViewPackage.STYLABLE: {
				Stylable stylable = (Stylable)theEObject;
				T result = caseStylable(stylable);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ViewPackage.PAGINABLE: {
				Paginable paginable = (Paginable)theEObject;
				T result = casePaginable(paginable);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ViewPackage.ACTIONABLE: {
				Actionable actionable = (Actionable)theEObject;
				T result = caseActionable(actionable);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ViewPackage.SORTABLE: {
				Sortable sortable = (Sortable)theEObject;
				T result = caseSortable(sortable);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ViewPackage.EDITABLE: {
				Editable editable = (Editable)theEObject;
				T result = caseEditable(editable);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ViewPackage.MOVABLE: {
				Movable movable = (Movable)theEObject;
				T result = caseMovable(movable);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ViewPackage.FILTERABLE: {
				Filterable filterable = (Filterable)theEObject;
				T result = caseFilterable(filterable);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ViewPackage.FIELD_GROUP: {
				FieldGroup fieldGroup = (FieldGroup)theEObject;
				T result = caseFieldGroup(fieldGroup);
				if (result == null) result = caseFieldContainer(fieldGroup);
				if (result == null) result = caseFieldElement(fieldGroup);
				if (result == null) result = caseStylable(fieldGroup);
				if (result == null) result = caseNamedModelElement(fieldGroup);
				if (result == null) result = caseModelElement(fieldGroup);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			default: return defaultCase(theEObject);
		}
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Collection</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Collection</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseViewCollection(ViewCollection object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Field Element</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Field Element</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseFieldElement(FieldElement object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Abstract View</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Abstract View</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseAbstractView(AbstractView object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Abstract Data Table</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Abstract Data Table</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseAbstractDataTable(AbstractDataTable object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Col</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Col</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseCol(Col object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Paging</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Paging</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T casePaging(Paging object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Sorting</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Sorting</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseSorting(Sorting object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Filtering</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Filtering</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseFiltering(Filtering object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Styling</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Styling</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseStyling(Styling object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Data Table Element</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Data Table Element</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseDataTableElement(DataTableElement object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Data List</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Data List</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseDataList(DataList object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Data Table</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Data Table</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseDataTable(DataTable object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Facet Map</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Facet Map</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseFacetMap(FacetMap object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Tree</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Tree</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTree(Tree object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Composed View</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Composed View</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseComposedView(ComposedView object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Field Container</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Field Container</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseFieldContainer(FieldContainer object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Field</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Field</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseField(Field object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Text Field</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Text Field</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTextField(TextField object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Password Field</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Password Field</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T casePasswordField(PasswordField object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Boolean Field</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Boolean Field</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseBooleanField(BooleanField object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Float Field</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Float Field</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseFloatField(FloatField object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Action Field</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Action Field</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseActionField(ActionField object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Date Field</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Date Field</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseDateField(DateField object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Time Field</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Time Field</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTimeField(TimeField object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Date Time Field</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Date Time Field</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseDateTimeField(DateTimeField object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Phone Number Field</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Phone Number Field</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T casePhoneNumberField(PhoneNumberField object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Email Field</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Email Field</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseEmailField(EmailField object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Integer Field</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Integer Field</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseIntegerField(IntegerField object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>File Field</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>File Field</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseFileField(FileField object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Select Field</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Select Field</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseSelectField(SelectField object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Html Field</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Html Field</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseHtmlField(HtmlField object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>URL Field</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>URL Field</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseURLField(URLField object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Image Field</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Image Field</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseImageField(ImageField object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Stylable</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Stylable</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseStylable(Stylable object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Paginable</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Paginable</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T casePaginable(Paginable object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Sortable</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Sortable</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseSortable(Sortable object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Editable</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Editable</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseEditable(Editable object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Movable</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Movable</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseMovable(Movable object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Filterable</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Filterable</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseFilterable(Filterable object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Field Group</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Field Group</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseFieldGroup(FieldGroup object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Abstract View Of</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Abstract View Of</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseAbstractViewOf(AbstractViewOf object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Actionable</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Actionable</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseActionable(Actionable object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Model Element</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Model Element</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseModelElement(ModelElement object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Named Model Element</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Named Model Element</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseNamedModelElement(NamedModelElement object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Package</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Package</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T casePackage(com.bluexml.side.common.Package object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Meta Data</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Meta Data</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseMetaData(MetaData object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Meta Info Group</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Meta Info Group</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseMetaInfoGroup(MetaInfoGroup object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>EObject</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch, but this is the last case anyway.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>EObject</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject)
	 * @generated
	 */
	public T defaultCase(EObject object) {
		return null;
	}

} //ViewSwitch
