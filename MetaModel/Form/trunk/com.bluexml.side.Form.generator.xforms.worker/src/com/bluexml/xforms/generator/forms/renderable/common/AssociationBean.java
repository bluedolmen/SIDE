package com.bluexml.xforms.generator.forms.renderable.common;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.bluexml.side.clazz.AbstractClass;
import com.bluexml.side.form.ModelChoiceWidgetType;
import com.bluexml.xforms.generator.forms.FormTypeRendered;
import com.bluexml.xforms.generator.forms.Renderable;
import com.bluexml.xforms.messages.MsgId;

/**
 * The Class AssociationBean.
 */
public class AssociationBean {

	/**
	 * The Enum AssociationType.
	 */
	public enum AssociationType {

			/** The target is a list of clazz objects. */
			clazz,

			/** The target list is a list of enumerations items. */
			enume;
	}

	/** The association type. */
	private AssociationType associationType;

	/** The destination. */
	private AbstractClass destinationClass = null;

	/** The destination enumeration. */
	private SelectBean destinationSelect = null;

	/** The name. */
	private String name;

	/** The label displayed as name of the form control. */
	private String title;

	/** Hint/help text for the form control */
	private String hint;

	/** The destination renderable. */
	private Renderable destinationRenderable;

	/** The create edit form. */
	private List<String> createEditForms = null;

	/** Display status of action buttons. */
	private boolean showingActions;

	private boolean disabled;

	/** The default number of elements to display. */
	private String fieldSize;

	/** The higher bound. */
	private int hiBound;

	/** The lower bound. */
	private int loBound;

	/** Whether at least one element is required. */
	private boolean mandatory;

	/** The create and edit form type, whether class or form. */
	private FormTypeRendered createEditFormType;

	private String createEditDefaultFormName = null;

	/** The format of labels displayed for the association items. */
	private String formatPattern;

	/** The length at which to truncate labels. */
	private String labelLength;

	// ** #1530
	/**
	 * Whether this selection widget is for a field instead of an association. If set,
	 * showingActions must be reset.
	 */
	private boolean isForField;
	private String overridingType;
	/** Local name of the property (from the type definition) used as id */
	private String identifierPropName;
	// ** #1530

	private String filterAssoc;

	private boolean isComposition;

	private String style; // #1600

	// Xtension-defined (for associations)
	private String dataSourceUri; // #1660
	// no bugzilla reference for the other Xtension defined below
	private String featureMode;
	private String luceneQuery;
	private boolean noAutoSearch;
	private boolean noStatsOutput;

	private ModelChoiceWidgetType widgetType;

	public AssociationBean() {
		super();
		fieldSize = "0";
		formatPattern = "";
		labelLength = "0";
		isForField = false;
		filterAssoc = null;
		isComposition = false;
		setWidgetType(ModelChoiceWidgetType.SELECT);
	}

	/**
	 * Gets the name.
	 * 
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name.
	 * 
	 * @param name
	 *            the new name
	 */
	public void setName(String name) {
		this.name = name;
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
	 * Gets the creates the edit form.
	 * 
	 * @return the creates the edit form
	 */
	public List<String> getCreateEditForms() {
		return createEditForms;
	}

	/**
	 * Sets the creates the edit form.
	 * 
	 * @param createEditForms
	 *            the list of edit forms
	 */
	public void setCreateEditForms(List<String> createEditForms) {
		this.createEditForms = createEditForms;
	}

	/**
	 * Sets the showing actions.
	 * 
	 * @param showingActions
	 *            the new showing actions
	 */
	public void setShowingActions(boolean showingActions) {
		this.showingActions = showingActions;
	}

	/**
	 * Checks if is showing actions.
	 * 
	 * @return true, if is showing actions
	 */
	public boolean isShowingActions() {
		return showingActions;
	}

	/**
	 * Gets the destination class.
	 * 
	 * @return the destination class
	 */
	public AbstractClass getDestinationClass() {
		return destinationClass;
	}

	public int getHiBound() {
		return hiBound;
	}

	public void setHiBound(int bound) {
		this.hiBound = bound;
	}

	/**
	 * Sets the destination class.
	 * 
	 * @param destinationClass
	 *            the new destination class
	 */
	public void setDestinationClass(AbstractClass destinationClass) {
		this.destinationClass = destinationClass;
		this.associationType = AssociationType.clazz;
	}

	public SelectBean getDestinationSelect() {
		return destinationSelect;
	}

	public void setDestinationSelect(SelectBean destinationSelect) {
		this.destinationSelect = destinationSelect;
		this.associationType = AssociationType.enume;
	}

	/**
	 * Gets the association type.
	 * 
	 * @return the association type
	 */
	public AssociationType getAssociationType() {
		return associationType;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("destination : ");
		sb.append(" - name : ");
		sb.append(name);
		return sb.toString();
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
	 * @param mandatory
	 *            the mandatory to set
	 */
	public void setMandatory(boolean mandatory) {
		this.mandatory = mandatory;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title
	 *            the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
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
	 * 
	 * @return whether the association is multiple
	 */
	public boolean isMultiple() {
		return (getHiBound() > 1 || getHiBound() == -1);
	}

	/**
	 * @param loBound
	 *            the loBound to set
	 */
	public void setLoBound(int loBound) {
		this.loBound = loBound;
	}

	/**
	 * @return the loBound
	 */
	public int getLoBound() {
		return loBound;
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
	 * @param createEditFormType
	 *            the createEditFormType to set
	 */
	public void setCreateEditFormType(FormTypeRendered createEditFormType) {
		this.createEditFormType = createEditFormType;
	}

	/**
	 * @return the createEditFormType
	 */
	public FormTypeRendered getCreateEditFormType() {
		return createEditFormType;
	}

	/**
	 * @param isForField
	 *            the isForField to set
	 */
	public void setForField(boolean isForField) {
		this.isForField = isForField;
		if (isForField) {
			setShowingActions(false);
		}
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
	 * @return the isComposition status
	 */
	public boolean isComposition() {
		return isComposition;
	}

	/**
	 * @param isComposition
	 *            whether the association is a composition
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
	 * @param featureMode
	 *            the featureMode to set
	 */
	public void setFeatureMode(String featureMode) {
		this.featureMode = featureMode;
	}

	/**
	 * Tests the feature mode for search.
	 * 
	 * @return <code>true</code> if the feature mode matches the string for search mode.
	 */
	public boolean isInFeatureSearchMode() {
		return StringUtils.equalsIgnoreCase(featureMode, MsgId.MODEL_XTENSION_FEATURE_MODE_SEARCH
				.getText());
	}

	/**
	 * Tests the feature mode for filter.
	 * 
	 * @return <code>true</code> if the feature mode matches the string for filter mode.
	 */
	public boolean isInFeatureFilterMode() {
		boolean result = StringUtils.equalsIgnoreCase(featureMode,
				MsgId.MODEL_XTENSION_FEATURE_MODE_FILTER.getText());
		// this mode is the default mode so we force it if none is defined
		result = result || (isInFeatureSearchMode() == false);
		return result;
	}

	/**
	 * @return the luceneQuery
	 */
	public String getLuceneQuery() {
		return luceneQuery;
	}

	/**
	 * @param luceneQuery
	 *            the luceneQuery to set
	 */
	public void setLuceneQuery(String luceneQuery) {
		this.luceneQuery = luceneQuery;
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
	 * @return the noStatsOutput
	 */
	public boolean isNoStatsOutput() {
		return noStatsOutput;
	}

	/**
	 * @param noStatsOutput
	 *            the noStatsOutput to set
	 */
	public void setNoStatsOutput(boolean noStatsOutput) {
		this.noStatsOutput = noStatsOutput;
	}

	/**
	 * @param widgetType
	 *            the widgetType to set
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

	/**
	 * Tells whether the association is rendered as an item selector (i.e. as an extended widget).
	 * 
	 * @return <code>true</code> if the widget type is "item selector".
	 */
	public boolean isItemSelector() { // #1677
		// return true;
		return (widgetType.equals(ModelChoiceWidgetType.ITEM_SELECTOR) || isForField());
	}

}
