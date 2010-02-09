/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.bluexml.side.form.util;

import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

import com.bluexml.side.common.ModelElement;
import com.bluexml.side.common.NamedModelElement;
import com.bluexml.side.form.*;
import com.bluexml.side.form.ActionField;
import com.bluexml.side.form.BooleanField;
import com.bluexml.side.form.CharField;
import com.bluexml.side.form.ChoiceField;
import com.bluexml.side.form.ClassReference;
import com.bluexml.side.form.DateField;
import com.bluexml.side.form.DateTimeField;
import com.bluexml.side.form.DecimalField;
import com.bluexml.side.form.EmailField;
import com.bluexml.side.form.Field;
import com.bluexml.side.form.FileField;
import com.bluexml.side.form.FloatField;
import com.bluexml.side.form.FormAspect;
import com.bluexml.side.form.FormClass;
import com.bluexml.side.form.FormCollection;
import com.bluexml.side.form.FormContainer;
import com.bluexml.side.form.FormElement;
import com.bluexml.side.form.FormGroup;
import com.bluexml.side.form.FormPackage;
import com.bluexml.side.form.FormWorkflow;
import com.bluexml.side.form.ImageField;
import com.bluexml.side.form.IntegerField;
import com.bluexml.side.form.ModelChoiceField;
import com.bluexml.side.form.PasswordField;
import com.bluexml.side.form.PhoneNumberField;
import com.bluexml.side.form.Reference;
import com.bluexml.side.form.RegexField;
import com.bluexml.side.form.TextField;
import com.bluexml.side.form.TimeField;
import com.bluexml.side.form.URLField;
import com.bluexml.side.form.VirtualField;
import com.bluexml.side.form.WorkflowFormCollection;

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
 * @see com.bluexml.side.form.FormPackage
 * @generated
 */
public class FormSwitch<T> {
	/**
	 * The cached model package
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static FormPackage modelPackage;

	/**
	 * Creates an instance of the switch.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FormSwitch() {
		if (modelPackage == null) {
			modelPackage = FormPackage.eINSTANCE;
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
			case FormPackage.FORM_ELEMENT: {
				FormElement formElement = (FormElement)theEObject;
				T result = caseFormElement(formElement);
				if (result == null) result = caseModelElement(formElement);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case FormPackage.FORM_COLLECTION: {
				FormCollection formCollection = (FormCollection)theEObject;
				T result = caseFormCollection(formCollection);
				if (result == null) result = casePackage(formCollection);
				if (result == null) result = caseNamedModelElement(formCollection);
				if (result == null) result = caseModelElement(formCollection);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case FormPackage.FORM_GROUP: {
				FormGroup formGroup = (FormGroup)theEObject;
				T result = caseFormGroup(formGroup);
				if (result == null) result = caseFormElement(formGroup);
				if (result == null) result = caseModelElement(formGroup);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case FormPackage.WORKFLOW_FORM_COLLECTION: {
				WorkflowFormCollection workflowFormCollection = (WorkflowFormCollection)theEObject;
				T result = caseWorkflowFormCollection(workflowFormCollection);
				if (result == null) result = caseFormCollection(workflowFormCollection);
				if (result == null) result = casePackage(workflowFormCollection);
				if (result == null) result = caseNamedModelElement(workflowFormCollection);
				if (result == null) result = caseModelElement(workflowFormCollection);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case FormPackage.CLASS_FORM_COLLECTION: {
				ClassFormCollection classFormCollection = (ClassFormCollection)theEObject;
				T result = caseClassFormCollection(classFormCollection);
				if (result == null) result = caseFormCollection(classFormCollection);
				if (result == null) result = casePackage(classFormCollection);
				if (result == null) result = caseNamedModelElement(classFormCollection);
				if (result == null) result = caseModelElement(classFormCollection);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case FormPackage.FIELD: {
				Field field = (Field)theEObject;
				T result = caseField(field);
				if (result == null) result = caseFormElement(field);
				if (result == null) result = caseModelElement(field);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case FormPackage.FORM_CONTAINER: {
				FormContainer formContainer = (FormContainer)theEObject;
				T result = caseFormContainer(formContainer);
				if (result == null) result = caseFormGroup(formContainer);
				if (result == null) result = caseFormElement(formContainer);
				if (result == null) result = caseModelElement(formContainer);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case FormPackage.FORM_WORKFLOW: {
				FormWorkflow formWorkflow = (FormWorkflow)theEObject;
				T result = caseFormWorkflow(formWorkflow);
				if (result == null) result = caseFormContainer(formWorkflow);
				if (result == null) result = caseFormGroup(formWorkflow);
				if (result == null) result = caseFormElement(formWorkflow);
				if (result == null) result = caseModelElement(formWorkflow);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case FormPackage.FORM_CLASS: {
				FormClass formClass = (FormClass)theEObject;
				T result = caseFormClass(formClass);
				if (result == null) result = caseFormContainer(formClass);
				if (result == null) result = caseClassReference(formClass);
				if (result == null) result = caseFormGroup(formClass);
				if (result == null) result = caseFormElement(formClass);
				if (result == null) result = caseModelElement(formClass);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case FormPackage.BOOLEAN_FIELD: {
				BooleanField booleanField = (BooleanField)theEObject;
				T result = caseBooleanField(booleanField);
				if (result == null) result = caseField(booleanField);
				if (result == null) result = caseFormElement(booleanField);
				if (result == null) result = caseModelElement(booleanField);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case FormPackage.CHAR_FIELD: {
				CharField charField = (CharField)theEObject;
				T result = caseCharField(charField);
				if (result == null) result = caseField(charField);
				if (result == null) result = caseFormElement(charField);
				if (result == null) result = caseModelElement(charField);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case FormPackage.DATE_FIELD: {
				DateField dateField = (DateField)theEObject;
				T result = caseDateField(dateField);
				if (result == null) result = caseField(dateField);
				if (result == null) result = caseFormElement(dateField);
				if (result == null) result = caseModelElement(dateField);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case FormPackage.DATE_TIME_FIELD: {
				DateTimeField dateTimeField = (DateTimeField)theEObject;
				T result = caseDateTimeField(dateTimeField);
				if (result == null) result = caseDateField(dateTimeField);
				if (result == null) result = caseField(dateTimeField);
				if (result == null) result = caseFormElement(dateTimeField);
				if (result == null) result = caseModelElement(dateTimeField);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case FormPackage.DECIMAL_FIELD: {
				DecimalField decimalField = (DecimalField)theEObject;
				T result = caseDecimalField(decimalField);
				if (result == null) result = caseNumericalField(decimalField);
				if (result == null) result = caseField(decimalField);
				if (result == null) result = caseFormElement(decimalField);
				if (result == null) result = caseModelElement(decimalField);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case FormPackage.FLOAT_FIELD: {
				FloatField floatField = (FloatField)theEObject;
				T result = caseFloatField(floatField);
				if (result == null) result = caseNumericalField(floatField);
				if (result == null) result = caseField(floatField);
				if (result == null) result = caseFormElement(floatField);
				if (result == null) result = caseModelElement(floatField);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case FormPackage.INTEGER_FIELD: {
				IntegerField integerField = (IntegerField)theEObject;
				T result = caseIntegerField(integerField);
				if (result == null) result = caseNumericalField(integerField);
				if (result == null) result = caseField(integerField);
				if (result == null) result = caseFormElement(integerField);
				if (result == null) result = caseModelElement(integerField);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case FormPackage.MODEL_CHOICE_FIELD: {
				ModelChoiceField modelChoiceField = (ModelChoiceField)theEObject;
				T result = caseModelChoiceField(modelChoiceField);
				if (result == null) result = caseField(modelChoiceField);
				if (result == null) result = caseClassReference(modelChoiceField);
				if (result == null) result = caseFormElement(modelChoiceField);
				if (result == null) result = caseModelElement(modelChoiceField);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case FormPackage.EMAIL_FIELD: {
				EmailField emailField = (EmailField)theEObject;
				T result = caseEmailField(emailField);
				if (result == null) result = caseCharField(emailField);
				if (result == null) result = caseField(emailField);
				if (result == null) result = caseFormElement(emailField);
				if (result == null) result = caseModelElement(emailField);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case FormPackage.FILE_FIELD: {
				FileField fileField = (FileField)theEObject;
				T result = caseFileField(fileField);
				if (result == null) result = caseField(fileField);
				if (result == null) result = caseFormElement(fileField);
				if (result == null) result = caseModelElement(fileField);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case FormPackage.IMAGE_FIELD: {
				ImageField imageField = (ImageField)theEObject;
				T result = caseImageField(imageField);
				if (result == null) result = caseFileField(imageField);
				if (result == null) result = caseField(imageField);
				if (result == null) result = caseFormElement(imageField);
				if (result == null) result = caseModelElement(imageField);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case FormPackage.TIME_FIELD: {
				TimeField timeField = (TimeField)theEObject;
				T result = caseTimeField(timeField);
				if (result == null) result = caseDateField(timeField);
				if (result == null) result = caseField(timeField);
				if (result == null) result = caseFormElement(timeField);
				if (result == null) result = caseModelElement(timeField);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case FormPackage.URL_FIELD: {
				URLField urlField = (URLField)theEObject;
				T result = caseURLField(urlField);
				if (result == null) result = caseCharField(urlField);
				if (result == null) result = caseField(urlField);
				if (result == null) result = caseFormElement(urlField);
				if (result == null) result = caseModelElement(urlField);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case FormPackage.PHONE_NUMBER_FIELD: {
				PhoneNumberField phoneNumberField = (PhoneNumberField)theEObject;
				T result = casePhoneNumberField(phoneNumberField);
				if (result == null) result = caseCharField(phoneNumberField);
				if (result == null) result = caseField(phoneNumberField);
				if (result == null) result = caseFormElement(phoneNumberField);
				if (result == null) result = caseModelElement(phoneNumberField);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case FormPackage.FORM_ASPECT: {
				FormAspect formAspect = (FormAspect)theEObject;
				T result = caseFormAspect(formAspect);
				if (result == null) result = caseFormGroup(formAspect);
				if (result == null) result = caseFormElement(formAspect);
				if (result == null) result = caseModelElement(formAspect);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case FormPackage.REFERENCE: {
				Reference reference = (Reference)theEObject;
				T result = caseReference(reference);
				if (result == null) result = caseModelChoiceField(reference);
				if (result == null) result = caseField(reference);
				if (result == null) result = caseClassReference(reference);
				if (result == null) result = caseFormElement(reference);
				if (result == null) result = caseModelElement(reference);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case FormPackage.CHOICE_FIELD: {
				ChoiceField choiceField = (ChoiceField)theEObject;
				T result = caseChoiceField(choiceField);
				if (result == null) result = caseField(choiceField);
				if (result == null) result = caseFormElement(choiceField);
				if (result == null) result = caseModelElement(choiceField);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case FormPackage.REGEX_FIELD: {
				RegexField regexField = (RegexField)theEObject;
				T result = caseRegexField(regexField);
				if (result == null) result = caseCharField(regexField);
				if (result == null) result = caseField(regexField);
				if (result == null) result = caseFormElement(regexField);
				if (result == null) result = caseModelElement(regexField);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case FormPackage.CLASS_REFERENCE: {
				ClassReference classReference = (ClassReference)theEObject;
				T result = caseClassReference(classReference);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case FormPackage.PASSWORD_FIELD: {
				PasswordField passwordField = (PasswordField)theEObject;
				T result = casePasswordField(passwordField);
				if (result == null) result = caseCharField(passwordField);
				if (result == null) result = caseField(passwordField);
				if (result == null) result = caseFormElement(passwordField);
				if (result == null) result = caseModelElement(passwordField);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case FormPackage.VIRTUAL_FIELD: {
				VirtualField virtualField = (VirtualField)theEObject;
				T result = caseVirtualField(virtualField);
				if (result == null) result = caseField(virtualField);
				if (result == null) result = caseFormElement(virtualField);
				if (result == null) result = caseModelElement(virtualField);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case FormPackage.ACTION_FIELD: {
				ActionField actionField = (ActionField)theEObject;
				T result = caseActionField(actionField);
				if (result == null) result = caseField(actionField);
				if (result == null) result = caseFormElement(actionField);
				if (result == null) result = caseModelElement(actionField);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case FormPackage.TEXT_FIELD: {
				TextField textField = (TextField)theEObject;
				T result = caseTextField(textField);
				if (result == null) result = caseCharField(textField);
				if (result == null) result = caseField(textField);
				if (result == null) result = caseFormElement(textField);
				if (result == null) result = caseModelElement(textField);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case FormPackage.FORM_SEARCH: {
				FormSearch formSearch = (FormSearch)theEObject;
				T result = caseFormSearch(formSearch);
				if (result == null) result = caseFormContainer(formSearch);
				if (result == null) result = caseFormGroup(formSearch);
				if (result == null) result = caseFormElement(formSearch);
				if (result == null) result = caseModelElement(formSearch);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case FormPackage.NUMERICAL_FIELD: {
				NumericalField numericalField = (NumericalField)theEObject;
				T result = caseNumericalField(numericalField);
				if (result == null) result = caseField(numericalField);
				if (result == null) result = caseFormElement(numericalField);
				if (result == null) result = caseModelElement(numericalField);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case FormPackage.STATIC_TEXT: {
				StaticText staticText = (StaticText)theEObject;
				T result = caseStaticText(staticText);
				if (result == null) result = caseFormElement(staticText);
				if (result == null) result = caseModelElement(staticText);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case FormPackage.SEARCH_FORM_COLLECTION: {
				SearchFormCollection SearchFormCollection = (SearchFormCollection)theEObject;
				T result = caseSearchFormCollection(SearchFormCollection);
				if (result == null) result = caseFormCollection(SearchFormCollection);
				if (result == null) result = casePackage(SearchFormCollection);
				if (result == null) result = caseNamedModelElement(SearchFormCollection);
				if (result == null) result = caseModelElement(SearchFormCollection);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case FormPackage.SEARCH_FIELD: {
				SearchField searchField = (SearchField)theEObject;
				T result = caseSearchField(searchField);
				if (result == null) result = caseFormElement(searchField);
				if (result == null) result = caseModelElement(searchField);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case FormPackage.NUMERICAL_SEARCH_FIELD: {
				NumericalSearchField numericalSearchField = (NumericalSearchField)theEObject;
				T result = caseNumericalSearchField(numericalSearchField);
				if (result == null) result = caseSearchField(numericalSearchField);
				if (result == null) result = caseFormElement(numericalSearchField);
				if (result == null) result = caseModelElement(numericalSearchField);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case FormPackage.CHAR_SEARCH_FIELD: {
				CharSearchField charSearchField = (CharSearchField)theEObject;
				T result = caseCharSearchField(charSearchField);
				if (result == null) result = caseSearchField(charSearchField);
				if (result == null) result = caseFormElement(charSearchField);
				if (result == null) result = caseModelElement(charSearchField);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case FormPackage.DATE_SEARCH_FIELD: {
				DateSearchField dateSearchField = (DateSearchField)theEObject;
				T result = caseDateSearchField(dateSearchField);
				if (result == null) result = caseSearchField(dateSearchField);
				if (result == null) result = caseFormElement(dateSearchField);
				if (result == null) result = caseModelElement(dateSearchField);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case FormPackage.CHOICE_SEARCH_FIELD: {
				ChoiceSearchField choiceSearchField = (ChoiceSearchField)theEObject;
				T result = caseChoiceSearchField(choiceSearchField);
				if (result == null) result = caseSearchField(choiceSearchField);
				if (result == null) result = caseFormElement(choiceSearchField);
				if (result == null) result = caseModelElement(choiceSearchField);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case FormPackage.FILE_SEARCH_FIELD: {
				FileSearchField fileSearchField = (FileSearchField)theEObject;
				T result = caseFileSearchField(fileSearchField);
				if (result == null) result = caseSearchField(fileSearchField);
				if (result == null) result = caseFormElement(fileSearchField);
				if (result == null) result = caseModelElement(fileSearchField);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			default: return defaultCase(theEObject);
		}
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Element</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Element</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseFormElement(FormElement object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Group</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Group</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseFormGroup(FormGroup object) {
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
	 * Returns the result of interpreting the object as an instance of '<em>Char Field</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Char Field</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseCharField(CharField object) {
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
	 * Returns the result of interpreting the object as an instance of '<em>Decimal Field</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Decimal Field</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseDecimalField(DecimalField object) {
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
	 * Returns the result of interpreting the object as an instance of '<em>Model Choice Field</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Model Choice Field</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseModelChoiceField(ModelChoiceField object) {
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
	 * Returns the result of interpreting the object as an instance of '<em>Aspect</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Aspect</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseFormAspect(FormAspect object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Class</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Class</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseFormClass(FormClass object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Reference</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Reference</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseReference(Reference object) {
		return null;
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
	public T caseFormCollection(FormCollection object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Choice Field</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Choice Field</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseChoiceField(ChoiceField object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Regex Field</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Regex Field</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseRegexField(RegexField object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Class Reference</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Class Reference</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseClassReference(ClassReference object) {
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
	 * Returns the result of interpreting the object as an instance of '<em>Virtual Field</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Virtual Field</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseVirtualField(VirtualField object) {
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
	 * Returns the result of interpreting the object as an instance of '<em>Search</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Search</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseFormSearch(FormSearch object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Numerical Field</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Numerical Field</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseNumericalField(NumericalField object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Static Text</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Static Text</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseStaticText(StaticText object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Search Form Collection</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Search Form Collection</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseSearchFormCollection(SearchFormCollection object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Search Field</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Search Field</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseSearchField(SearchField object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Numerical Search Field</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Numerical Search Field</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseNumericalSearchField(NumericalSearchField object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Char Search Field</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Char Search Field</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseCharSearchField(CharSearchField object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Date Search Field</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Date Search Field</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseDateSearchField(DateSearchField object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Choice Search Field</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Choice Search Field</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseChoiceSearchField(ChoiceSearchField object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>File Search Field</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>File Search Field</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseFileSearchField(FileSearchField object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Workflow Form Collection</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Workflow Form Collection</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseWorkflowFormCollection(WorkflowFormCollection object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Class Form Collection</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Class Form Collection</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseClassFormCollection(ClassFormCollection object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Workflow</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Workflow</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseFormWorkflow(FormWorkflow object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Container</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Container</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseFormContainer(FormContainer object) {
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

} //formSwitch
