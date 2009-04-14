/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.bluexml.side.form.util;

import com.bluexml.side.form.*;

import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

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
 * @see com.bluexml.side.form.formPackage
 * @generated
 */
public class formSwitch<T> {
	/**
	 * The cached model package
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static formPackage modelPackage;

	/**
	 * Creates an instance of the switch.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public formSwitch() {
		if (modelPackage == null) {
			modelPackage = formPackage.eINSTANCE;
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
			case formPackage.FORM: {
				Form form = (Form)theEObject;
				T result = caseForm(form);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case formPackage.FORM_ELEMENT: {
				FormElement formElement = (FormElement)theEObject;
				T result = caseFormElement(formElement);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case formPackage.FORM_GROUP: {
				FormGroup formGroup = (FormGroup)theEObject;
				T result = caseFormGroup(formGroup);
				if (result == null) result = caseFormElement(formGroup);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case formPackage.FIELD: {
				Field field = (Field)theEObject;
				T result = caseField(field);
				if (result == null) result = caseFormElement(field);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case formPackage.BOOLEAN_FIELD: {
				BooleanField booleanField = (BooleanField)theEObject;
				T result = caseBooleanField(booleanField);
				if (result == null) result = caseField(booleanField);
				if (result == null) result = caseFormElement(booleanField);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case formPackage.CHAR_FIELD: {
				CharField charField = (CharField)theEObject;
				T result = caseCharField(charField);
				if (result == null) result = caseField(charField);
				if (result == null) result = caseFormElement(charField);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case formPackage.DATE_FIELD: {
				DateField dateField = (DateField)theEObject;
				T result = caseDateField(dateField);
				if (result == null) result = caseField(dateField);
				if (result == null) result = caseFormElement(dateField);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case formPackage.DATE_TIME_FIELD: {
				DateTimeField dateTimeField = (DateTimeField)theEObject;
				T result = caseDateTimeField(dateTimeField);
				if (result == null) result = caseDateField(dateTimeField);
				if (result == null) result = caseField(dateTimeField);
				if (result == null) result = caseFormElement(dateTimeField);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case formPackage.DECIMAL_FIELD: {
				DecimalField decimalField = (DecimalField)theEObject;
				T result = caseDecimalField(decimalField);
				if (result == null) result = caseField(decimalField);
				if (result == null) result = caseFormElement(decimalField);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case formPackage.FLOAT_FIELD: {
				FloatField floatField = (FloatField)theEObject;
				T result = caseFloatField(floatField);
				if (result == null) result = caseField(floatField);
				if (result == null) result = caseFormElement(floatField);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case formPackage.INTEGER_FIELD: {
				IntegerField integerField = (IntegerField)theEObject;
				T result = caseIntegerField(integerField);
				if (result == null) result = caseField(integerField);
				if (result == null) result = caseFormElement(integerField);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case formPackage.MODEL_CHOICE_FIELD: {
				ModelChoiceField modelChoiceField = (ModelChoiceField)theEObject;
				T result = caseModelChoiceField(modelChoiceField);
				if (result == null) result = caseField(modelChoiceField);
				if (result == null) result = caseClassReference(modelChoiceField);
				if (result == null) result = caseFormElement(modelChoiceField);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case formPackage.EMAIL_FIELD: {
				EmailField emailField = (EmailField)theEObject;
				T result = caseEmailField(emailField);
				if (result == null) result = caseCharField(emailField);
				if (result == null) result = caseField(emailField);
				if (result == null) result = caseFormElement(emailField);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case formPackage.FILE_FIELD: {
				FileField fileField = (FileField)theEObject;
				T result = caseFileField(fileField);
				if (result == null) result = caseField(fileField);
				if (result == null) result = caseFormElement(fileField);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case formPackage.IMAGE_FIELD: {
				ImageField imageField = (ImageField)theEObject;
				T result = caseImageField(imageField);
				if (result == null) result = caseFileField(imageField);
				if (result == null) result = caseField(imageField);
				if (result == null) result = caseFormElement(imageField);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case formPackage.TIME_FIELD: {
				TimeField timeField = (TimeField)theEObject;
				T result = caseTimeField(timeField);
				if (result == null) result = caseDateField(timeField);
				if (result == null) result = caseField(timeField);
				if (result == null) result = caseFormElement(timeField);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case formPackage.URL_FIELD: {
				URLField urlField = (URLField)theEObject;
				T result = caseURLField(urlField);
				if (result == null) result = caseCharField(urlField);
				if (result == null) result = caseField(urlField);
				if (result == null) result = caseFormElement(urlField);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case formPackage.PHONE_NUMBER_FIELD: {
				PhoneNumberField phoneNumberField = (PhoneNumberField)theEObject;
				T result = casePhoneNumberField(phoneNumberField);
				if (result == null) result = caseCharField(phoneNumberField);
				if (result == null) result = caseField(phoneNumberField);
				if (result == null) result = caseFormElement(phoneNumberField);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case formPackage.FORM_ASPECT: {
				FormAspect formAspect = (FormAspect)theEObject;
				T result = caseFormAspect(formAspect);
				if (result == null) result = caseFormGroup(formAspect);
				if (result == null) result = caseFormElement(formAspect);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case formPackage.FORM_CLASS: {
				FormClass formClass = (FormClass)theEObject;
				T result = caseFormClass(formClass);
				if (result == null) result = caseFormContainer(formClass);
				if (result == null) result = caseClassReference(formClass);
				if (result == null) result = caseFormGroup(formClass);
				if (result == null) result = caseFormElement(formClass);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case formPackage.REFERENCE: {
				Reference reference = (Reference)theEObject;
				T result = caseReference(reference);
				if (result == null) result = caseModelChoiceField(reference);
				if (result == null) result = caseField(reference);
				if (result == null) result = caseClassReference(reference);
				if (result == null) result = caseFormElement(reference);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case formPackage.FORM_COLLECTION: {
				FormCollection formCollection = (FormCollection)theEObject;
				T result = caseFormCollection(formCollection);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case formPackage.CHOICE_FIELD: {
				ChoiceField choiceField = (ChoiceField)theEObject;
				T result = caseChoiceField(choiceField);
				if (result == null) result = caseField(choiceField);
				if (result == null) result = caseFormElement(choiceField);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case formPackage.REGEX_FIELD: {
				RegexField regexField = (RegexField)theEObject;
				T result = caseRegexField(regexField);
				if (result == null) result = caseCharField(regexField);
				if (result == null) result = caseField(regexField);
				if (result == null) result = caseFormElement(regexField);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case formPackage.CLASS_REFERENCE: {
				ClassReference classReference = (ClassReference)theEObject;
				T result = caseClassReference(classReference);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case formPackage.PASSWORD_FIELD: {
				PasswordField passwordField = (PasswordField)theEObject;
				T result = casePasswordField(passwordField);
				if (result == null) result = caseCharField(passwordField);
				if (result == null) result = caseField(passwordField);
				if (result == null) result = caseFormElement(passwordField);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case formPackage.VIRTUAL_FIELD: {
				VirtualField virtualField = (VirtualField)theEObject;
				T result = caseVirtualField(virtualField);
				if (result == null) result = caseField(virtualField);
				if (result == null) result = caseFormElement(virtualField);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case formPackage.ACTION_FIELD: {
				ActionField actionField = (ActionField)theEObject;
				T result = caseActionField(actionField);
				if (result == null) result = caseField(actionField);
				if (result == null) result = caseFormElement(actionField);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case formPackage.TEXT_FIELD: {
				TextField textField = (TextField)theEObject;
				T result = caseTextField(textField);
				if (result == null) result = caseCharField(textField);
				if (result == null) result = caseField(textField);
				if (result == null) result = caseFormElement(textField);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case formPackage.WORKFLOW_FORM_COLLECTION: {
				WorkflowFormCollection workflowFormCollection = (WorkflowFormCollection)theEObject;
				T result = caseWorkflowFormCollection(workflowFormCollection);
				if (result == null) result = caseFormCollection(workflowFormCollection);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case formPackage.FORM_WORKFLOW: {
				FormWorkflow formWorkflow = (FormWorkflow)theEObject;
				T result = caseFormWorkflow(formWorkflow);
				if (result == null) result = caseFormContainer(formWorkflow);
				if (result == null) result = caseFormGroup(formWorkflow);
				if (result == null) result = caseFormElement(formWorkflow);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case formPackage.FORM_CONTAINER: {
				FormContainer formContainer = (FormContainer)theEObject;
				T result = caseFormContainer(formContainer);
				if (result == null) result = caseFormGroup(formContainer);
				if (result == null) result = caseFormElement(formContainer);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			default: return defaultCase(theEObject);
		}
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Form</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Form</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseForm(Form object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Form Element</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Form Element</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseFormElement(FormElement object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Form Group</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Form Group</em>'.
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
	 * Returns the result of interpreting the object as an instance of '<em>Form Aspect</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Form Aspect</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseFormAspect(FormAspect object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Form Class</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Form Class</em>'.
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
	 * Returns the result of interpreting the object as an instance of '<em>Form Collection</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Form Collection</em>'.
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
	 * Returns the result of interpreting the object as an instance of '<em>Form Workflow</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Form Workflow</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseFormWorkflow(FormWorkflow object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Form Container</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Form Container</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseFormContainer(FormContainer object) {
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
