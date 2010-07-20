package com.bluexml.xforms.generator.forms.renderable.common;

import java.util.ArrayList;
import java.util.List;

import com.bluexml.side.clazz.Clazz;
import com.bluexml.side.form.ModelChoiceWidgetType;
import com.bluexml.xforms.generator.forms.FormTypeRendered;
import com.bluexml.xforms.generator.forms.Renderable;
import com.bluexml.xforms.messages.MsgId;

/**
 * The Class AssociationProperties. Holds the configuration of the selection widget that is used on
 * associations.
 * 
 * @author glandais
 * 
 */
public class AssociationProperties {

	/** The destination. */
	private Clazz destination;

	/** The destination renderable. */
	private Renderable destinationRenderable;

	/** The unique name for the form field. */
	private String uniqueName;

	/** The field label when rendered on a form. */
	private String assocTitle;

	/** The hint/help text for the form control. */
	private String hint;

	/** Whether the target class is rendered inline or (when non-inline) via a selection widget. */
	private boolean inline;

	/** The cardinality higher bound. */
	private int hiBound;

	/** The cardinality lower bound. */
	private int loBound;

	/** Tells whether action buttons should be displayed. */
	private boolean showingActions;

	/** The field size. Not initialized from the constructor parameters. */
	private String fieldSize;

	/** <code>true</code> if the field is read-only. */
	private boolean disabled;

	/** The mandatory/required property. See the setter method. */
	private boolean mandatory;

	/** The form type (class or form) for the form that is called by the create and edit buttons. */
	private FormTypeRendered createEditFormType;

	/**
	 * The create edit form names. Was initially a single form name but now, refers to a list of
	 * forms because each selection widget has only one target class but this class may have several
	 * subclasses and there may be forms better suited to some subclasses than the parent class'
	 * form.
	 */
	private List<String> createEditFormNames = null; // #1510

	/** The Alfresco name of the target class, for use in case no create/edit form names exist. */
	private String createEditDefaultFormName = null;

	/** The pattern for formatting the labels of the items displayed in the selection widget. */
	private String formatPattern;

	/** The length at which the labels must be truncated. If 0, no truncation happens. */
	private String labelLength;

	// ** #1530
	/**
	 * Whether this selection widget is for a field instead of an association. If <code>true</code>,
	 * showingActions must be reset (i.e. set to <code>false</code>).
	 */
	private boolean isForField;

	/** The content type (e.g. "cm:person") of the items to be shown in the widget. */
	private String overridingType = null;

	/** Local name of the property (from the type definition) used as id */
	private String identifierPropName;
	// ** #1530

	// ** #1536
	/**
	 * If non-null, implies that listed items will be filtered with respect to the given
	 * association.
	 */
	private String filterAssoc;

	/** Whether the filtering association is a composition. */
	private boolean isComposition;
	// ** #1536

	/** The CSS class to be applied to this widget. */
	private String style; // #1600

	/** User-defined URI for fetching the list items. */
	private String dataSourceUri;

	/** How the widget is to be used, as list-items-then-filter or as search-then-list-items. */
	private String featureMode;

	/** User defined Lucene query for overriding the default query. */
	private String luceneQuery;

	/** Whether the search is triggered automatically. If <code>true</code>, the trigger is manual. */
	private boolean noAutoSearch;

	/** Whether the stats output is rendered on the form.*/
	private boolean noStatsOutput;
	
	private ModelChoiceWidgetType widgetType;

	/**
	 * Used in formForm's (via RenderableModelChoiceField and RenderableField)
	 */
	public AssociationProperties() {
		super();
		initDefaults();
	}

	/**
	 * Used in formClass's (via addAssociation).
	 * <p>
	 * NOTE: several fields are not initialized from the constructor parameters
	 * 
	 */
	public AssociationProperties(Clazz destination, Renderable destinationRenderable, String name,
			String assocTitle, boolean inline, int hiBound, int loBound) {
		super();
		initDefaults();

		this.destination = destination;
		this.destinationRenderable = destinationRenderable;
		this.createEditFormType = FormTypeRendered.formClass;
		this.uniqueName = name;
		this.assocTitle = assocTitle;
		this.inline = inline;
		this.hiBound = hiBound;
		this.loBound = loBound;
	}

	/**
	 * 
	 */
	private void initDefaults() {
		this.destination = null;
		this.destinationRenderable = null;
		this.inline = false;
		this.createEditFormType = null;
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
		this.filterAssoc = null;
		this.isComposition = false;
		this.style = null;
		this.dataSourceUri = null;
		this.featureMode = MsgId.MODEL_XTENSION_FEATURE_MODE_FILTER.getText();
		this.luceneQuery = null;
		this.noAutoSearch = false;
		this.noStatsOutput = false;
		this.setWidgetType(ModelChoiceWidgetType.SELECT);
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
	 * @param overridingType
	 *            the overridingType to set
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
	 * @param identifierPropName
	 *            the identifierPropName to set
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
	 * @param filterAssoc
	 *            the filterAssoc to set
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
	 * @param isComposition
	 *            the isComposition to set
	 */
	public void setComposition(boolean isComposition) {
		this.isComposition = isComposition;
	}

	/**
	 * @param createEditDefaultFormName
	 *            the createEditDefaultFormName to set
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

	/**
	 * @param style
	 *            the style to set
	 */
	public void setStyle(String style) {
		this.style = style;
	}

	/**
	 * @return the style
	 */
	public String getStyle() {
		return style;
	}

	/**
	 * @return the createEditFormNames
	 */
	public List<String> getCreateEditFormNames() {
		return createEditFormNames;
	}

	/**
	 * @param createEditFormNames
	 *            the createEditFormNames to set
	 */
	public void setCreateEditFormNames(List<String> createEditFormNames) {
		this.createEditFormNames = createEditFormNames;
	}

	/**
	 * @return the dataSourceUri
	 */
	public String getDataSourceUri() {
		return dataSourceUri;
	}

	/**
	 * @param dataSourceUri
	 *            the dataSourceUri to set
	 */
	public void setDataSourceUri(String dataSourceUri) {
		this.dataSourceUri = dataSourceUri;
	}

	/**
	 * @return the featureMode
	 */
	public String getFeatureMode() {
		return featureMode;
	}

	/**
	 * @param featureMode
	 *            the featureMode to set
	 */
	public void setFeatureMode(String featureMode) {
		this.featureMode = featureMode;
	}

	/**
	 * @param luceneQuery
	 *            the luceneQuery to set
	 */
	public void setLuceneQuery(String luceneQuery) {
		this.luceneQuery = luceneQuery;
	}

	/**
	 * @return the luceneQuery
	 */
	public String getLuceneQuery() {
		return luceneQuery;
	}

	/**
	 * @return the noAutoSearch
	 */
	public boolean isNoAutoSearch() {
		return noAutoSearch;
	}

	/**
	 * @param noAutoSearch
	 *            the noAutoSearch to set
	 */
	public void setNoAutoSearch(boolean noAutoSearch) {
		this.noAutoSearch = noAutoSearch;
	}

	/**
	 * @param noStatsOutput the noStatsOutput to set
	 */
	public void setNoStatsOutput(boolean noStatsOutput) {
		this.noStatsOutput = noStatsOutput;
	}

	/**
	 * @return the noStatsOutput
	 */
	public boolean isNoStatsOutput() {
		return noStatsOutput;
	}

	/**
	 * @param widgetType the widgetType to set
	 */
	public void setWidgetType(ModelChoiceWidgetType widgetType) {
		this.widgetType = widgetType;
	}

	/**
	 * @return the widgetType
	 */
	public ModelChoiceWidgetType getWidgetType() {
		return widgetType;
	}

}
