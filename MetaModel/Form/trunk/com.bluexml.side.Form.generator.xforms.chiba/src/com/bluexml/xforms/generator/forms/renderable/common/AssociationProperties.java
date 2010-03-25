package com.bluexml.xforms.generator.forms.renderable.common;

import java.util.ArrayList;
import java.util.List;

import com.bluexml.side.clazz.Clazz;
import com.bluexml.xforms.generator.forms.FormTypeRendered;
import com.bluexml.xforms.generator.forms.Renderable;

/**
 * The Class AssociationProperties.
 */
/**
 * @author glandais
 * 
 */
public class AssociationProperties {

	/** The destination. */
	private Clazz destination;

	/** The destination renderable. */
	private Renderable destinationRenderable;

	/** The name. */
	private String uniqueName;

	/** The assoc title. */
	private String assocTitle;

	/** The hint/help text for the form control. */
	private String hint;

	/** The inline. */
	private boolean inline;

	private int hiBound;

	private int loBound;

	/** Tells whether action buttons should be displayed. */
	private boolean showingActions;

	/**
	 * The field size. Not initialized from the constructor parameters.
	 */
	private String fieldSize;

	private boolean disabled;

	/** The mandatory/required property. See the setter method. */
	private boolean mandatory;

	/** The create and edit form type, whether class or form. */
	private FormTypeRendered createEditFormType;

	/** The create edit form name. */
	private List<String> createEditFormNames = null; // #1510
	
	/** The Alfresco name of the target class, for use in case no create/edit form names exist.*/
	private String createEditDefaultFormName = null;

	private String formatPattern;

	private String labelLength;
	
	//** #1530
	/**
	 * Whether this selection widget is for a field instead of an association. If set,
	 * showingActions must be reset.
	 */
	private boolean isForField; 
	private String overridingType;
	/** Local name of the property (from the type definition) used as id*/
	private String identifierPropName;
	// ** #1530
	// ** #1536
	private String filterAssoc;
	private boolean isComposition;
	// ** #1536

	/**
	 * Used in formForm's (via RenderableModelChoiceField's)
	 */
	public AssociationProperties() {
		super();
	}

	/**
	 * Used in formClass's (via addAssociation).
	 * <p>
	 * NOTE: several fields are not initialized from the constructor parameters
	 * 
	 * @param destination
	 *            the destination
	 * @param destinationRenderable
	 *            the destination renderable
	 * @param associationClasse
	 *            the association classe
	 * @param associationClasseRenderable
	 *            the association classe renderable
	 * @param name
	 *            the name
	 * @param assocTitle
	 *            the assoc title
	 * @param inline
	 *            the inline
	 * @param multiple
	 *            the multiple
	 */
	public AssociationProperties(Clazz destination, Renderable destinationRenderable, String name,
			String assocTitle, boolean inline, int hiBound, int loBound) {
		super();
		this.destination = destination;
		this.destinationRenderable = destinationRenderable;
		this.uniqueName = name;
		this.assocTitle = assocTitle;
		this.inline = inline;
		this.hiBound = hiBound;
		this.loBound = loBound;
		this.createEditFormType = FormTypeRendered.formClass;
		this.createEditFormNames = null;
		this.showingActions = true;
		this.fieldSize = "0";
		this.disabled = false;
		this.hint = null;
		this.formatPattern = "";
		this.labelLength = "0";
		this.isForField = false;
		this.overridingType = null;
		this.identifierPropName = null;
	}

	/**
	 * Gets the destination.
	 * 
	 * @return the destination
	 */
	public Clazz getDestination() {
		return destination;
	}

	/**
	 * Sets the destination.
	 * 
	 * @param destination
	 *            the new destination
	 */
	public void setDestination(Clazz destination) {
		this.destination = destination;
	}

	/**
	 * Gets the destination renderable.
	 * 
	 * @return the destination renderable
	 */
	public Renderable getDestinationRenderable() {
		return destinationRenderable;
	}

	/**
	 * Sets the destination renderable.
	 * 
	 * @param destinationRenderable
	 *            the new destination renderable
	 */
	public void setDestinationRenderable(Renderable destinationRenderable) {
		this.destinationRenderable = destinationRenderable;
	}

	/**
	 * Gets the name.
	 * 
	 * @return the name
	 */
	public String getUniqueName() {
		return uniqueName;
	}

	/**
	 * Sets the name.
	 * 
	 * @param name
	 *            the new name
	 */
	public void setUniqueName(String name) {
		this.uniqueName = name;
	}

	/**
	 * Gets the assoc title.
	 * 
	 * @return the assoc title
	 */
	public String getAssocTitle() {
		return assocTitle;
	}

	/**
	 * Sets the assoc title.
	 * 
	 * @param assocTitle
	 *            the new assoc title
	 */
	public void setAssocTitle(String assocTitle) {
		this.assocTitle = assocTitle;
	}

	/**
	 * Checks if is inline.
	 * 
	 * @return true, if is inline
	 */
	public boolean isInline() {
		return inline;
	}

	/**
	 * Sets the inline.
	 * 
	 * @param inline
	 *            the new inline
	 */
	public void setInline(boolean inline) {
		this.inline = inline;
	}

	public int getHiBound() {
		return hiBound;
	}

	public void setHiBound(int hiBound) {
		this.hiBound = hiBound;
	}

	/**
	 * Gets the creates the edit form type.
	 * 
	 * @return the creates the edit form type
	 */
	public FormTypeRendered getCreateEditFormType() {
		return createEditFormType;
	}

	/**
	 * Sets the creates the edit form type.
	 * 
	 * @param createEditFormType
	 *            the new creates the edit form type
	 */
	public void setCreateEditFormType(FormTypeRendered createEditFormType) {
		this.createEditFormType = createEditFormType;
	}

	/**
	 * Gets the creates the edit form name.
	 * 
	 * @return the creates the edit form name
	 */
	public List<String> getCreateEditFormName() {
		return createEditFormNames;
	}

	/**
	 * Sets the creates the edit form name.
	 * 
	 * @param createEditFormName
	 *            the new creates the edit form name
	 */
	public void addCreateEditFormName(String createEditFormName) {
		if (this.createEditFormNames == null) {
			this.createEditFormNames = new ArrayList<String>();
		}
		this.createEditFormNames.add(createEditFormName);
	}

	/**
	 * Sets whether action buttons should be added to the form
	 * 
	 * @param isShowingActions
	 */
	public void setShowingActions(boolean isShowingActions) {
		this.showingActions = isShowingActions;
	}

	/**
	 * 
	 * @return true if action buttons should be displayed
	 */
	public boolean isShowingActions() {
		return showingActions;
	}

	public boolean isMultiple() {
		return (hiBound == -1 || hiBound > 1);
	}

	/**
	 * @return the fieldSize
	 */
	public String getFieldSize() {
		return fieldSize;
	}

	/**
	 * Sets the field size to a value different from the <b>default value</b> ( which is 0).
	 * 
	 * @param fieldSize
	 *            the fieldSize to set
	 */
	public void setFieldSize(String fieldSize) {
		this.fieldSize = fieldSize;
	}

	/**
	 * @return the disabled
	 */
	public boolean isDisabled() {
		return disabled;
	}

	/**
	 * @param disabled
	 *            the disabled to set
	 */
	public void setDisabled(boolean disabled) {
		this.disabled = disabled;
	}

	/**
	 * @return the mandatory
	 */
	public boolean isMandatory() {
		return mandatory;
	}

	/**
	 * Sets the mandatory/required property. The setting is guarded by the lower bound so that 0..*
	 * can never be forced to mandatory and 1..* may be set to non mandatory (for testing purposes).
	 * 
	 * @param mandatory
	 *            the mandatory to set
	 */
	public void setMandatory(boolean value) {
		// this.mandatory = value && (this.loBound > 0);
		this.mandatory = value;
	}

	/**
	 * @return the loBound
	 */
	public int getLoBound() {
		return loBound;
	}

	/**
	 * @param loBound
	 *            the loBound to set
	 */
	public void setLoBound(int loBound) {
		this.loBound = loBound;
	}

	/**
	 * @return the hint
	 */
	public String getHint() {
		return hint;
	}

	/**
	 * @param hint
	 *            the hint to set
	 */
	public void setHint(String hint) {
		this.hint = hint;
	}

	/**
	 * @param formatPattern
	 *            the formatPattern to set
	 */
	public void setFormatPattern(String formatPattern) {
		this.formatPattern = formatPattern;
	}

	/**
	 * @return the formatPattern
	 */
	public String getFormatPattern() {
		return formatPattern;
	}

	/**
	 * @param labelLength
	 *            the labelLength to set
	 */
	public void setLabelLength(String labelLength) {
		this.labelLength = labelLength;
	}

	/**
	 * @return the labelLength
	 */
	public String getLabelLength() {
		return labelLength;
	}

	/**
	 * @param isForField
	 *            the isForField to set
	 */
	public void setForField(boolean isForField) {
		this.isForField = isForField;
	}

	/**
	 * @return the isForField
	 */
	public boolean isForField() {
		return isForField;
	}

	/**
	 * @param overridingType the overridingType to set
	 */
	public void setOverridingType(String overridingType) {
		this.overridingType = overridingType;
	}

	/**
	 * @return the overridingType
	 */
	public String getOverridingType() {
		return overridingType;
	}

	/**
	 * @param identifierPropName the identifierPropName to set
	 */
	public void setIdentifierPropName(String identifierPropName) {
		this.identifierPropName = identifierPropName;
	}

	/**
	 * @return the identifierPropName
	 */
	public String getIdentifierPropName() {
		return identifierPropName;
	}

	/**
	 * @return the filterAssoc
	 */
	public String getFilterAssoc() {
		return filterAssoc;
	}

	/**
	 * @param filterAssoc the filterAssoc to set
	 */
	public void setFilterAssoc(String filterAssoc) {
		this.filterAssoc = filterAssoc;
	}

	/**
	 * @return the isComposition
	 */
	public boolean isComposition() {
		return isComposition;
	}

	/**
	 * @param isComposition the isComposition to set
	 */
	public void setComposition(boolean isComposition) {
		this.isComposition = isComposition;
	}

	/**
	 * @param createEditDefaultFormName the createEditDefaultFormName to set
	 */
	public void setCreateEditDefaultFormName(String createEditDefaultFormName) {
		this.createEditDefaultFormName = createEditDefaultFormName;
	}

	/**
	 * @return the createEditDefaultFormName
	 */
	public String getCreateEditDefaultFormName() {
		return createEditDefaultFormName;
	}

}
