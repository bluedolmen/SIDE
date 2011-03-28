/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.bluexml.side.common.util;

import java.util.Map;

import org.eclipse.emf.common.util.DiagnosticChain;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.util.EObjectValidator;
import org.eclipse.ocl.ecore.Constraint;
import org.eclipse.ocl.ecore.OCL;

import com.bluexml.side.common.Comment;
import com.bluexml.side.common.CommonPackage;
import com.bluexml.side.common.Container;
import com.bluexml.side.common.DataType;
import com.bluexml.side.common.MetaData;
import com.bluexml.side.common.MetaInfo;
import com.bluexml.side.common.MetaInfoGroup;
import com.bluexml.side.common.ModelElement;
import com.bluexml.side.common.NamedModelElement;
import com.bluexml.side.common.Operation;
import com.bluexml.side.common.OperationComponent;
import com.bluexml.side.common.OperationGroup;
import com.bluexml.side.common.Parameter;
import com.bluexml.side.common.Stereotype;
import com.bluexml.side.common.Tag;
import com.bluexml.side.common.Visibility;
import com.bluexml.side.util.metaModel.validate.OCLextension.KerblueOCL;

/**
 * <!-- begin-user-doc -->
 * The <b>Validator</b> for the model.
 * <!-- end-user-doc -->
 * @see com.bluexml.side.common.CommonPackage
 * @generated
 */
public class CommonValidator extends EObjectValidator {
	/**
	 * The cached model package
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final CommonValidator INSTANCE = new CommonValidator();

	/**
	 * A constant for the {@link org.eclipse.emf.common.util.Diagnostic#getSource() source} of diagnostic {@link org.eclipse.emf.common.util.Diagnostic#getCode() codes} from this package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.common.util.Diagnostic#getSource()
	 * @see org.eclipse.emf.common.util.Diagnostic#getCode()
	 * @generated
	 */
	public static final String DIAGNOSTIC_SOURCE = "com.bluexml.side.common";

	/**
	 * A constant with a fixed name that can be used as the base value for additional hand written constants.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final int GENERATED_DIAGNOSTIC_CODE_COUNT = 0;

	/**
	 * A constant with a fixed name that can be used as the base value for additional hand written constants in a derived class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static final int DIAGNOSTIC_CODE_COUNT = GENERATED_DIAGNOSTIC_CODE_COUNT;

	/**
	 * The parsed OCL expression for the definition of the '<em>PackageNameNull</em>' invariant constraint.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static Constraint package__PackageNameNullInvOCL;
	private static final String OCL_ANNOTATION_SOURCE = "http://www.bluexml.com/OCL";

	private static final OCL OCL_ENV = KerblueOCL.newInstance();
	/**
	 * Creates an instance of the switch.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CommonValidator() {
		super();
	}

	/**
	 * Returns the package of this validator switch.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EPackage getEPackage() {
	  return CommonPackage.eINSTANCE;
	}

	/**
	 * Calls <code>validateXXX</code> for the corresonding classifier of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected boolean validate(int classifierID, Object value, DiagnosticChain diagnostics, Map<Object, Object> context) {
		switch (classifierID) {
			case CommonPackage.MODEL_ELEMENT:
				return validateModelElement((ModelElement)value, diagnostics, context);
			case CommonPackage.NAMED_MODEL_ELEMENT:
				return validateNamedModelElement((NamedModelElement)value, diagnostics, context);
			case CommonPackage.COMMENT:
				return validateComment((Comment)value, diagnostics, context);
			case CommonPackage.TAG:
				return validateTag((Tag)value, diagnostics, context);
			case CommonPackage.STEREOTYPE:
				return validateStereotype((Stereotype)value, diagnostics, context);
			case CommonPackage.PACKAGE:
				return validatePackage((com.bluexml.side.common.Package)value, diagnostics, context);
			case CommonPackage.OPERATION:
				return validateOperation((Operation)value, diagnostics, context);
			case CommonPackage.PARAMETER:
				return validateParameter((Parameter)value, diagnostics, context);
			case CommonPackage.OPERATION_GROUP:
				return validateOperationGroup((OperationGroup)value, diagnostics, context);
			case CommonPackage.OPERATION_COMPONENT:
				return validateOperationComponent((OperationComponent)value, diagnostics, context);
			case CommonPackage.META_INFO:
				return validateMetaInfo((MetaInfo)value, diagnostics, context);
			case CommonPackage.META_INFO_GROUP:
				return validateMetaInfoGroup((MetaInfoGroup)value, diagnostics, context);
			case CommonPackage.META_DATA:
				return validateMetaData((MetaData)value, diagnostics, context);
			case CommonPackage.CONTAINER:
				return validateContainer((Container)value, diagnostics, context);
			case CommonPackage.DATA_TYPE:
				return validateDataType((DataType)value, diagnostics, context);
			case CommonPackage.VISIBILITY:
				return validateVisibility((Visibility)value, diagnostics, context);
			default:
				return true;
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateModelElement(ModelElement modelElement, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(modelElement, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateNamedModelElement(NamedModelElement namedModelElement, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(namedModelElement, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateComment(Comment comment, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(comment, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTag(Tag tag, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(tag, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateStereotype(Stereotype stereotype, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(stereotype, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validatePackage(com.bluexml.side.common.Package package_, DiagnosticChain diagnostics, Map<Object, Object> context) {
		boolean result = validate_EveryMultiplicityConforms(package_, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(package_, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(package_, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(package_, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(package_, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(package_, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(package_, diagnostics, context);
		return result;
	}


	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateOperation(Operation operation, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(operation, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateParameter(Parameter parameter, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(parameter, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateOperationGroup(OperationGroup operationGroup, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(operationGroup, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateOperationComponent(OperationComponent operationComponent, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(operationComponent, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateMetaInfo(MetaInfo metaInfo, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(metaInfo, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateMetaInfoGroup(MetaInfoGroup metaInfoGroup, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(metaInfoGroup, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateMetaData(MetaData metaData, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(metaData, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateContainer(Container container, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(container, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateDataType(DataType dataType, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return true;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateVisibility(Visibility visibility, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return true;
	}

} //CommonValidator
