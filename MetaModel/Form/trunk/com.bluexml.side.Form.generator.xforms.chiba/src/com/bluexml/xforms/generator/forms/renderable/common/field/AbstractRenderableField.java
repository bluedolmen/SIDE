package com.bluexml.xforms.generator.forms.renderable.common.field;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import javax.xml.namespace.QName;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang.StringUtils;
import org.jdom.Element;

import com.bluexml.side.form.ChoiceWidgetType;
import com.bluexml.xforms.generator.forms.Renderable;
import com.bluexml.xforms.generator.forms.Rendered;
import com.bluexml.xforms.generator.forms.XFormsGenerator;
import com.bluexml.xforms.generator.forms.modelelement.ModelElementBindSimple;
import com.bluexml.xforms.generator.forms.modelelement.ModelElementEnumeration;
import com.bluexml.xforms.generator.forms.renderable.common.SelectBean;
import com.bluexml.xforms.generator.forms.rendered.RenderedXMLElement;
import com.bluexml.xforms.generator.tools.ModelTools;
import com.bluexml.xforms.messages.MsgId;
import com.bluexml.xforms.messages.MsgPool;

/**
 * The Class AbstractRenderableField.
 */
public abstract class AbstractRenderableField extends Renderable {

	/** The attribute id. */
	private String attributeId = null;
	private String maxLength = null;
	private String minLength = null;
	private String regex = null;

	/**
	 * Instantiates a new abstract renderable field.
	 */
	public AbstractRenderableField() {
		super();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.bluexml.xforms.generator.forms.Renderable#getPath(java.lang.String, java.util.Stack,
	 * java.util.Stack)
	 */
	@Override
	public Path getPath(String parentPath, Stack<Renderable> parents,
			Stack<Rendered> renderedParents) {
		return new Path(PathType.relativePath, getName());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.bluexml.xforms.generator.forms.Renderable#render(java.lang.String, java.util.Stack,
	 * java.util.Stack)
	 */
	@Override
	public Rendered render(String path, Stack<Renderable> parents, Stack<Rendered> renderedParents,
			boolean isInIMultRepeater) {
		attributeId = XFormsGenerator.getId(getOwner() + "_" + getName());

		RenderedXMLElement rendered = new RenderedXMLElement();

		ModelElementBindSimple meb = null;
		String xsdType = getXsdType();
		if (xsdType.equals(MsgId.INT_TYPE_XSD_DATETIME.getText())) {
			if (isReadOnly()) {
				meb = new ModelElementBindSimple(path);
				// meb.setType(new QName("string"));
			} else {
				meb = new ModelElementBindSimple(path + "/date");
				meb.setType(new QName(MsgId.INT_TYPE_XSD_DATE.getText()));
				ModelElementBindSimple mebTime = new ModelElementBindSimple(path + "/time");
				mebTime.setType(new QName(MsgId.INT_TYPE_XSD_TIME.getText()));
				meb.setAnotherMeb(mebTime);
				rendered.addModelElement(mebTime);
			}
		} else {
			meb = new ModelElementBindSimple(path);
			meb.setType(new QName(xsdType));
			applyConstraints(meb);
		}
		rendered.addModelElement(meb);

		String slabel = MsgPool.getMsg(MsgId.MSG_FIELD_LABEL_FORMAT, getTitle());

		Element element = null;
		if (isReadOnly()) {
			meb.setReadOnly(true);
			if (StringUtils.equals(getXsdType(), MsgId.INT_TYPE_XSD_DATE.getText())
					|| StringUtils.equals(getXsdType(), MsgId.INT_TYPE_XSD_TIME.getText())
					|| StringUtils.equals(getXsdType(), MsgId.INT_TYPE_XSD_DATETIME.getText())) {
				element = getReadOnlyElement(meb, slabel, false); // #1248
			} else {
				element = getCustomElement(rendered, meb, slabel, parents, renderedParents);
			}
		} else {
			element = getCustomElement(rendered, meb, slabel, parents, renderedParents);
		}
		rendered.setXformsElement(element);
		applyStyle(rendered);

		return rendered;
	}

	protected abstract void applyStyle(RenderedXMLElement rendered);

	/**
	 * Gets the label element.
	 * 
	 * @param slabel
	 *            the slabel
	 * 
	 * @return the label element
	 */
	protected Element getLabelElement(String slabel) {
		return XFormsGenerator.createElementWithContent("label", XFormsGenerator.NAMESPACE_XFORMS,
				slabel);
	}

	/**
	 * Gets the attribute id.
	 * 
	 * @return the attribute id
	 */
	protected String getAttributeId() {
		return attributeId;
	}

	/**
	 * Gets the owner.
	 * 
	 * @return the owner
	 */
	protected abstract String getOwner();

	/**
	 * Gets the name.
	 * 
	 * @return the name
	 */
	protected abstract String getName();

	/**
	 * Gets the title.
	 * 
	 * @return the title
	 */
	protected abstract String getTitle();

	/**
	 * Checks if is read only.
	 * 
	 * @return true, if is read only
	 */
	protected abstract boolean isReadOnly();

	// /**
	// * Tells whether the field is marked as required/mandatory.
	// * Must be overriden by fields that CAN be mandatory
	// *
	// * @return true, if is read only
	// */
	// protected boolean isMandatory() {
	// return false;
	// }
	//
	/**
	 * Tells whether the field is marked as required/mandatory. Must be overriden by fields that CAN
	 * be mandatory
	 * 
	 * @return true, if marked as "mandatory" or "required"
	 */
	protected boolean isRequired() {
		return false;
	}

	/**
	 * Gets the xsd type.
	 * 
	 * @return the xsd type
	 */
	protected abstract String getXsdType();

	/**
	 * Gets the hint.
	 * 
	 * @return the hint
	 */
	protected abstract String getHint();

	/**
	 * Gets the error message. This message contains indications (to the user) for all possible
	 * error sources.
	 * 
	 * @return the error message that is built or an <b>empty string</b> if not defined
	 */
	protected String getErrorMessage() {

		String errMsg = getErrorMsgForLength();
		String errMsgRegex = getErrorMsgForRegex();

		if (StringUtils.trimToNull(errMsgRegex) != null) {
			if (StringUtils.trimToNull(errMsg) != null) {
				errMsg += " ";
			}
			errMsg += errMsgRegex;
		}

		return errMsg;
	}

	/**
	 * Builds the error message that is related to length constraints on the field.
	 * 
	 * @return
	 */
	protected String getErrorMsgForLength() {
		String errMsg = "";
		if ((getMinLength() != null) && (getMaxLength() != null)) {
			errMsg = MsgPool.getMsg(MsgId.MSG_LENGTH_BETWEEN, getMinLength(), getMaxLength(),
					getTitle());
		} else if (getMinLength() != null) {
			errMsg = MsgPool.getMsg(MsgId.MSG_LENGTH_MINIMAL, getMinLength(), getTitle());
		} else if (getMaxLength() != null) {
			errMsg = MsgPool.getMsg(MsgId.MSG_LENGTH_MAXIMAL, getMaxLength(), getTitle());
		}
		// if (StringUtils.trimToNull(errMsg) != null) {
		// if (!isRequired()) {
		// errMsg += MsgPool.getMsg(MsgId.MSG_LENGTH_POST_INFO_PART1) + getTitle()
		// + MsgPool.getMsg(MsgId.MSG_LENGTH_POST_INFO_PART2);
		// }
		// }
		return errMsg;
	}

	/**
	 * Builds the error message that is related to length constraints on the field.
	 * 
	 * @return
	 */
	protected String getErrorMsgForRegex() {
		String errMsg = "";
		if (getRegex() != null) {
			errMsg = MsgPool.getMsg(MsgId.MSG_INVALID_REGEX_FORMAT);
		}
		return errMsg;
	}

	/**
	 * Gets the custom element.
	 * 
	 * @param rendered
	 *            the rendered
	 * @param meb
	 *            the meb
	 * @param slabel
	 *            the slabel
	 * @param parents
	 *            the parents
	 * @param renderedParents
	 *            the rendered parents
	 * 
	 * @return the custom element
	 */
	protected abstract Element getCustomElement(Rendered rendered, ModelElementBindSimple meb,
			String slabel, Stack<Renderable> parents, Stack<Rendered> renderedParents);

	/**
	 * Apply constraints.
	 * 
	 * @param meb
	 *            the meb
	 */
	protected abstract void applyConstraints(ModelElementBindSimple meb);

	/**
	 * Gets the standard element.
	 * 
	 * @param bind
	 *            the bind
	 * @param slabel
	 *            the slabel
	 * 
	 * @return the standard element
	 */
	protected Element getStandardElement(ModelElementBindSimple bind, String slabel) {
		return getTypedElement(bind, slabel, "input");
	}

	protected Element getSecretElement(ModelElementBindSimple bind, String slabel) {
		return getTypedElement(bind, slabel, "secret");
	}

	private Element getTypedElement(ModelElementBindSimple bind, String slabel, String type) {
		Element input = XFormsGenerator.createElement(type, XFormsGenerator.NAMESPACE_XFORMS);
		input.setAttribute("id", getAttributeId());
		bind.addLinkedElement(input);
		input.addContent(getLabelElement(slabel));
		addHintAndMessages(input);
		return input;
	}

	/**
	 * Adds the hint, alert and help elements.
	 * 
	 * @param input
	 *            the input
	 */
	private void addHintAndMessages(Element input) {
		String hint = getHint();

		if (StringUtils.trimToNull(hint) != null) {
			Element hintElement = XFormsGenerator.createElement("hint",
					XFormsGenerator.NAMESPACE_XFORMS);
			hintElement.setText(hint);
			input.addContent(hintElement);
			Element helpElement = XFormsGenerator.createElement("help",
					XFormsGenerator.NAMESPACE_XFORMS);
			helpElement.setText(hint);
			input.addContent(helpElement);
		}
		// deal with error message
		String errMsg = "";
		if (isRequired()) {
			errMsg = MsgPool.getMsg(MsgId.MSG_FIELD_MANDATORY, getTitle());
		}
		if (StringUtils.trimToNull(errMsg) != null) {
			errMsg += " ";
		}
		// get full message for the constraints of the field
		errMsg += getErrorMessage();
		if (StringUtils.trimToNull(errMsg) != null) {
			Element alertElement = XFormsGenerator.createElement("alert",
					XFormsGenerator.NAMESPACE_XFORMS);
			alertElement.setText(errMsg);
			input.addContent(alertElement);
		}
	}

	/**
	 * Gets the text area element.
	 * 
	 * @param meb
	 *            the meb
	 * @param slabel
	 *            the slabel
	 * @param isRichTextEditor
	 *            the is rich text editor
	 * 
	 * @return the text area element
	 */
	protected Element getTextAreaElement(ModelElementBindSimple meb, String slabel,
			boolean isRichTextEditor) {
		Element element;
		element = XFormsGenerator.createElement("textarea", XFormsGenerator.NAMESPACE_XFORMS);
		if (isRichTextEditor) {
			if (isReadOnly()) {
				element = getReadOnlyElement(meb, slabel, true); // #1265
				return element;
			}
			element.setAttribute("mediatype", "text/html");
		}
		element.setAttribute("id", getAttributeId());
		meb.addLinkedElement(element);
		element.addContent(getLabelElement(slabel));
		addHintAndMessages(element);
		return element;
	}

	protected Element getDateTimeElement(ModelElementBindSimple meb, String slabel) {
		Element globaldiv = XFormsGenerator.createElement("div", XFormsGenerator.NAMESPACE_XHTML);

		Element dateDiv = XFormsGenerator.createElement("div", XFormsGenerator.NAMESPACE_XHTML);
		dateDiv.setAttribute("class", "xformstdleft");

		Element inputDate = XFormsGenerator
				.createElement("input", XFormsGenerator.NAMESPACE_XFORMS);
		inputDate.setAttribute("id", getAttributeId() + "-date");
		meb.addLinkedElement(inputDate);
		inputDate.addContent(getLabelElement(slabel));
		addHintAndMessages(inputDate);
		dateDiv.addContent(inputDate);

		Element timeDiv = XFormsGenerator.createElement("div", XFormsGenerator.NAMESPACE_XHTML);
		dateDiv.setAttribute("class", "xformstdleft");

		Element inputTime = XFormsGenerator
				.createElement("input", XFormsGenerator.NAMESPACE_XFORMS);
		inputTime.setAttribute("id", getAttributeId() + "-time");
		meb.getAnotherMeb().addLinkedElement(inputTime);
		timeDiv.addContent(inputTime);

		Element clearDiv = XFormsGenerator.createElement("div", XFormsGenerator.NAMESPACE_XHTML);
		clearDiv.setAttribute("class", "xformstdclear");

		globaldiv.addContent(dateDiv);
		globaldiv.addContent(timeDiv);
		globaldiv.addContent(clearDiv);

		return globaldiv;

	}

	/**
	 * Creates the appropriate select element.
	 * 
	 * @param rendered
	 *            the rendered
	 * @param meb
	 *            the meb
	 * @param slabel
	 *            the slabel
	 * @param enumeration
	 *            the enumeration
	 * @param choices
	 *            the choices
	 * 
	 * @return the select element
	 */
	protected Element getSelectElement(Rendered rendered, SelectBean selectBean) {
		Element element;
		String elemName;
		String elemAppearance;
		String refValueStr = "value";
		String refIdStr = "id";

		// Top element and its attributes
		if (selectBean.isMultiple()) {
			elemName = "select";
		} else {
			elemName = "select1";
		}
		if (selectBean.getWidgetType() == ChoiceWidgetType.INLINE) {
			elemAppearance = "full";
		} else if (selectBean.getWidgetType() == ChoiceWidgetType.LIST_ALL) {
			elemAppearance = "compact";
		} else {
			// the control is for an enumeration
			elemAppearance = "minimal";
			refIdStr = MsgId.INT_INSTANCE_ENUM_ID.getText();
			refValueStr = MsgId.INT_INSTANCE_ENUM_VALUE.getText();
		}
		element = XFormsGenerator.createElement(elemName, XFormsGenerator.NAMESPACE_XFORMS);
		element.setAttribute("id", getAttributeId());
		element.addContent(getLabelElement(selectBean.getLabel()));
		selectBean.getModelElementBindSimple().addLinkedElement(element);
		element.setAttribute("appearance", elemAppearance);

		// deal with choice options
		if (selectBean.isWorkflowEnum() == false) {
			ModelElementEnumeration modelElementEnumeration = getModelElementEnumeration(selectBean);
			// TODO: deal with returned null value
			rendered.addModelElement(modelElementEnumeration);

			String enumInstance = modelElementEnumeration.getEnumInstanceName();
			Element itemset = XFormsGenerator.createElement("itemset",
					XFormsGenerator.NAMESPACE_XFORMS);
			itemset.setAttribute("nodeset", "instance('" + enumInstance + "')/item");
			Element itemLabel = XFormsGenerator.createElement("label",
					XFormsGenerator.NAMESPACE_XFORMS);
			itemLabel.setAttribute("ref", refValueStr);
			itemset.addContent(itemLabel);
			Element itemValue = XFormsGenerator.createElement("value",
					XFormsGenerator.NAMESPACE_XFORMS);
			itemValue.setAttribute("ref", refIdStr);
			itemset.addContent(itemValue);
			element.addContent(itemset);
		} else { // #1313
			for (String itemText : selectBean.getAllowedValues()) {
				Element item = XFormsGenerator.createElement("item",
						XFormsGenerator.NAMESPACE_XFORMS);
				Element label = XFormsGenerator.createElement("label",
						XFormsGenerator.NAMESPACE_XFORMS);
				Element value = XFormsGenerator.createElement("value",
						XFormsGenerator.NAMESPACE_XFORMS);
				label.setText(itemText);
				value.setText(itemText);
				item.addContent(label);
				item.addContent(value);

				element.addContent(item);
			}
		}

		addHintAndMessages(element);
		return element;
	}

	private ModelElementEnumeration getModelElementEnumeration(SelectBean selectBean) {

		if (selectBean == null) {
			System.err.println("SelectBean is null!");
			return null;
		}
		String enumInstance = ModelTools.toJAXB(ModelTools.getCompleteName(selectBean
				.getEnumeration()))
				+ "Instance";
		ModelElementEnumeration modelElementEnum = new ModelElementEnumeration(selectBean,
				enumInstance);
		return modelElementEnum;
	}

	/**
	 * Gets the description of a file upload field.
	 * 
	 * @param meb
	 *            the meb
	 * @param slabel
	 *            the slabel
	 * @return the file element
	 */
	protected Element getFileElement(ModelElementBindSimple meb, String slabel, MsgId previewer) {
		Element element;
		element = XFormsGenerator.createXFormsGroup(slabel, null);
		element.setAttribute("id", attributeId + "_global");

		if (previewer.equals(MsgId.INT_FILEFIELD_PREVIEW_IMAGE)) {
			Element previewDiv = XFormsGenerator.createElement("div",
					XFormsGenerator.NAMESPACE_XHTML);
			previewDiv.setAttribute("id", attributeId + "_preview");
			previewDiv.setAttribute("class", MsgId.INT_CSS_UPLOAD_PREVIEW.getText());
			//
			Element preview = XFormsGenerator.createElement("output",
					XFormsGenerator.NAMESPACE_XFORMS);
			StringBuffer value = new StringBuffer();
			value.append("concat('<img src=\"" + MsgId.INT_GEN_PLACEHOLDER_CONTEXT_PATH
					+ "/file?location=', current()/");
			value.append(meb.getNodeset());
			value.append(", '\" />')");
			preview.setAttribute("value", value.toString());
			preview.setAttribute("mediatype", "text/html");
			previewDiv.addContent(preview);
			element.addContent(previewDiv);
		}

		//
		Element filenameDiv = XFormsGenerator.createElement("div", XFormsGenerator.NAMESPACE_XHTML);
		filenameDiv.setAttribute("id", attributeId + "_filename");
		filenameDiv.setAttribute("class", MsgId.INT_CSS_UPLOAD_FILENAME.getText());
		//
		Element filenameElement = XFormsGenerator.createElement("output",
				XFormsGenerator.NAMESPACE_XFORMS);
		StringBuffer valueFilename = new StringBuffer();
		// valueFilename.append("if (current()/");
		// valueFilename.append(meb.getNodeset());
		// valueFilename.append(" eq '') then '' else concat('");
		// valueFilename.append(MsgPool.getMsg(MsgId.MSG_FILE_FIELD_FILENAME_TEMP));
		// valueFilename.append("', current()/");
		// valueFilename.append(meb.getNodeset());
		// valueFilename.append(")");

		String curNodeSet = "current()/" + meb.getNodeset();
		valueFilename.append("if (");
		valueFilename.append(curNodeSet);
		valueFilename.append(" eq '') then '' else (if (not(starts-with(");
		valueFilename.append(curNodeSet);
		valueFilename.append(", 'file'))) then concat('");
		valueFilename.append(MsgPool.getMsg(MsgId.MSG_UPLOAD_CONTENT_REPO_INFO));
		valueFilename.append("', ");
		valueFilename.append(curNodeSet);
		valueFilename.append(") else concat('");
		valueFilename.append(MsgPool.getMsg(MsgId.MSG_FILE_FIELD_FILENAME_TEMP));
		valueFilename.append("', ");
		valueFilename.append(curNodeSet);
		valueFilename.append("))");

		filenameElement.setAttribute("value", valueFilename.toString());
		filenameElement.setAttribute("mediatype", "text/html");
		filenameDiv.addContent(filenameElement);
		element.addContent(filenameDiv);

		Element widgetDiv = XFormsGenerator.createElement("div", XFormsGenerator.NAMESPACE_XHTML);
		widgetDiv.setAttribute("class", MsgId.INT_CSS_UPLOAD_WIDGET.getText());
		Element input = XFormsGenerator.createElement("upload", XFormsGenerator.NAMESPACE_XFORMS);
		input.setAttribute("incremental", "true");
		input.setAttribute("id", attributeId);
		meb.addLinkedElement(input);
		// ** #1267
		String labelStr = MsgPool.testMsg(MsgId.MSG_FILE_FIELD_LABEL);
		if (labelStr != null) {
			input.addContent(getLabelElement(labelStr));
		}
		// ** #1267
		Element filename = XFormsGenerator.createElement("filename",
				XFormsGenerator.NAMESPACE_XFORMS);
		filename.setAttribute("ref", "attribute::file");
		input.addContent(filename);
		Element mediatype = XFormsGenerator.createElement("mediatype",
				XFormsGenerator.NAMESPACE_XFORMS);
		mediatype.setAttribute("ref", "attribute::type");
		input.addContent(mediatype);
		Element action = XFormsGenerator.createElement("action", XFormsGenerator.NAMESPACE_XFORMS);
		action.setAttribute("event", "xforms-value-changed", XFormsGenerator.NAMESPACE_EVENTS);
		Element recalculate = XFormsGenerator.createElement("recalculate",
				XFormsGenerator.NAMESPACE_XFORMS);
		action.addContent(recalculate);
		input.addContent(action);
		addHintAndMessages(input);

		widgetDiv.addContent(input); // #1480
		element.addContent(widgetDiv);
		return element;
	}

	/**
	 * Gets the read only element.
	 * 
	 * @param meb
	 *            the meb
	 * @param slabel
	 *            the slabel
	 * 
	 * @return the read only element
	 */
	protected Element getReadOnlyElement(ModelElementBindSimple meb, String slabel,
			boolean isTextArea) {
		Element element;
		element = XFormsGenerator.createElement("div", XFormsGenerator.NAMESPACE_XHTML);
		element.setAttribute("id", attributeId + "_container");
		String css = "xformstdclear";
		if (isTextArea) {
			css = css + " " + MsgId.INT_CSS_RO_TEXTAREA;
		}
		element.setAttribute("class", css); // #1248

		if (meb.getAnotherMeb() != null) {
			List<ModelElementBindSimple> binds = new ArrayList<ModelElementBindSimple>();
			binds.add(meb);
			binds.add(meb.getAnotherMeb());
			for (ModelElementBindSimple modelElementBindSimple : binds) {
				Element output = getReadOnlyOutput(modelElementBindSimple, slabel, isTextArea);
				element.addContent(output);
			}
		} else {
			Element output = getReadOnlyOutput(meb, slabel, isTextArea);
			element.addContent(output);
		}

		return element;
	}

	private Element getReadOnlyOutput(ModelElementBindSimple meb, String slabel, boolean isTextArea) {
		Element output = null;
		output = XFormsGenerator.createElement("output", XFormsGenerator.NAMESPACE_XFORMS);
		output.setAttribute("id", XFormsGenerator.getId(attributeId));
		if (isTextArea) {
			output.setAttribute("mediatype", "text/html");
		}
		meb.addLinkedElement(output);
		output.addContent(getLabelElement(slabel));
		return output;
	}

	/**
	 * Sets the constraint mail.
	 * 
	 * @param bind
	 *            the new constraint mail
	 */
	protected void setConstraintMail(ModelElementBindSimple bind) {
		String regexp = "^(|[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]+)$";
		setConstraint(bind, regexp);
	}

	/**
	 * Sets the constraint regexp.
	 * 
	 * @param bind
	 *            the bind
	 * @param regularExpression
	 *            the regular expression
	 */
	protected void setConstraintRegexp(ModelElementBindSimple bind, String regularExpression) {
		setRegex(escapeRegEx(regularExpression));
		setConstraint(bind, regularExpression);
	}

	/**
	 * Escapes XML entities in the regular expression.
	 * 
	 * @param expr
	 * @return
	 */
	protected String escapeRegEx(String expr) {
		// unescape first to avoid over-escaping
		String unescaped = StringEscapeUtils.unescapeXml(expr);
		String escaped = StringEscapeUtils.escapeXml(unescaped);
		return escaped;
	}

	/**
	 * Sets the constraint.
	 * 
	 * @param bind
	 *            the bind
	 * @param regexp
	 *            the regexp
	 */
	private void setConstraint(ModelElementBindSimple bind, String regexp) {
		if (regexp != null) {
			// #1210: check constraint only for non-null text
			bind.setConstraint("(.eq '' or (chibafn:match(.,'" + regexp + "','')))");
		}
	}

	/**
	 * Sets the length. Calcule l'expression logique représentant la contrainte sur la longueur et
	 * la fournit au ModelElementBind associé. Ne comporte que les termes relatifs à la longueur min
	 * et à la longueur max, éventuellement liés par un "and". Pas de parenthèses.
	 * 
	 * @param meb
	 *            the meb
	 * @param sMinLength
	 *            the s min length
	 * @param sMaxLength
	 *            the s max length
	 */
	protected void setLength(ModelElementBindSimple meb, String sMinLength, String sMaxLength) {
		boolean maxOK = (sMaxLength == null) ? false : !sMaxLength.equals("0");
		boolean minOK = (sMinLength == null) ? false : !sMinLength.equals("0");

		if ((maxOK == false) && (minOK == false)) {
			return;
		}
		String constraint = null;
		String realMaxLength = null;
		String realMinLength = null;

		if (maxOK && minOK) {
			realMaxLength = sMaxLength;
			realMinLength = sMinLength;
		} else if (maxOK) {
			realMaxLength = sMaxLength;
		} else if (minOK) {
			realMinLength = sMinLength;
		}

		maxLength = realMaxLength;
		minLength = realMinLength;

		if (realMinLength != null && realMaxLength != null) {
			constraint = realMinLength + " <= string-length(.) and string-length(.) <= "
					+ realMaxLength;
		} else {
			if (realMinLength != null) {
				constraint = realMinLength + " <= string-length(.)";
			}
			if (realMaxLength != null) {
				constraint = "string-length(.) <= " + realMaxLength;
			}
		}
		if (constraint != null) {
			meb.setLengthConstraint(constraint);
			// setIncremental(true); // #1264
		}
	}

	/**
	 * Sets the required.
	 * 
	 * @param bind
	 *            the bind
	 * @param required
	 *            the required
	 */
	protected void setRequired(ModelElementBindSimple bind, boolean required) {
		bind.setRequired(required);
	}

	/**
	 * Sets the "relevant" attribute of a forms control.
	 * 
	 * @param bind
	 *            the bind
	 * @param value
	 *            the value of relevant, equivalent to the visibility
	 */
	protected void setHidden(ModelElementBindSimple bind, boolean value) {
		bind.setHidden(value);
	}

	/**
	 * @return the maxLength
	 */
	public String getMaxLength() {
		return maxLength;
	}

	/**
	 * @return the minLength
	 */
	public String getMinLength() {
		return minLength;
	}

	/**
	 * @param maxLength
	 *            the maxLength to set
	 */
	public void setMaxLength(String maxLength) {
		this.maxLength = maxLength;
	}

	/**
	 * @param minLength
	 *            the minLength to set
	 */
	public void setMinLength(String minLength) {
		this.minLength = minLength;
	}

	/**
	 * @return the regex
	 */
	private String getRegex() {
		return regex;
	}

	/**
	 * @param regex
	 *            the regex to set
	 */
	private void setRegex(String regex) {
		this.regex = regex;
	}

}
