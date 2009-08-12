/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.bluexml.side.form;




/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Form Class</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Definition: The Form Class is the container for the form itself. If not empty, this element generates a form. Moreover, the Form Class is the link between a single class of the loaded Class Diagram and the customized form.
 * Operations: 
 * -	The operation 'Load Ressources...' enables to select and import Class Diagrams in the Form Model. One of the first steps to modelize form is to connect this form diagram with the data diagram through this operation: Right click on the editor and select Load Resource to choose a data model (and not a diagram). The Real Class attribute of Form Class may then be set up by selecting the name of class of the loaded data model. 
 * -	The operation 'Initialize form class' creates the default form from a selected Class. The selected Class is set up with the attribute 'Real class'. This action creates all fields corresponding to the Attributes and Associations defined in the data model: this default may then be refined according to the needs.
 * -	The operation ‘Refresh Outline’ refreshes an outline html view of the form. This view is useful to see the transformations made on the form before generating it and to have a general view of what will be generated. It is important to note that this outline view does not give the final generated form and the shape may be different according to the targeted technology.
 * -	The operation ‘Restore’ allows restoring a field which has been previously deleted.
 * Inherits: ClassReference, FormContainer.
 * 
 * <!-- end-model-doc -->
 *
 *
 * @see com.bluexml.side.form.FormPackage#getFormClass()
 * @model
 * @generated
 */
public interface FormClass extends FormContainer, ClassReference {
} // FormClass
