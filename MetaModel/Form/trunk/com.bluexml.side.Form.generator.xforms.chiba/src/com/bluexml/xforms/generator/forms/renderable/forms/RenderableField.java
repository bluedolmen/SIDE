package com.bluexml.xforms.generator.forms.renderable.forms;

import java.util.Collection;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.eclipse.emf.common.util.EList;
import org.jdom.Element;

import com.bluexml.side.common.ModelElement;
import com.bluexml.side.form.ActionField;
import com.bluexml.side.form.BooleanField;
import com.bluexml.side.form.CharField;
import com.bluexml.side.form.ChoiceField;
import com.bluexml.side.form.DateField;
import com.bluexml.side.form.DateTimeField;
import com.bluexml.side.form.DecimalField;
import com.bluexml.side.form.EmailField;
import com.bluexml.side.form.Field;
import com.bluexml.side.form.FileField;
import com.bluexml.side.form.FloatField;
import com.bluexml.side.form.FormElement;
import com.bluexml.side.form.ImageField;
import com.bluexml.side.form.IntegerField;
import com.bluexml.side.form.PasswordField;
import com.bluexml.side.form.PhoneNumberField;
import com.bluexml.side.form.RegexField;
import com.bluexml.side.form.TextField;
import com.bluexml.side.form.TimeField;
import com.bluexml.side.form.URLField;
import com.bluexml.side.workflow.Attribute;
import com.bluexml.xforms.generator.FormGenerator;
import com.bluexml.xforms.generator.forms.Renderable;
import com.bluexml.xforms.generator.forms.XFormsGenerator;
import com.bluexml.xforms.generator.forms.modelelement.ModelElementBindSimple;
import com.bluexml.xforms.generator.forms.renderable.common.field.AbstractRenderableField;
import com.bluexml.xforms.generator.forms.renderable.forms.field.RenderableActionField;
import com.bluexml.xforms.generator.forms.renderable.forms.field.RenderableChoiceInput;
import com.bluexml.xforms.generator.forms.renderable.forms.field.RenderableChoiceInputWorkflow;
import com.bluexml.xforms.generator.forms.renderable.forms.field.RenderableDateTimeInput;
import com.bluexml.xforms.generator.forms.renderable.forms.field.RenderableFileInput;
import com.bluexml.xforms.generator.forms.renderable.forms.field.RenderableMailInput;
import com.bluexml.xforms.generator.forms.renderable.forms.field.RenderablePasswordInput;
import com.bluexml.xforms.generator.forms.renderable.forms.field.RenderableRegexInput;
import com.bluexml.xforms.generator.forms.renderable.forms.field.RenderableSimpleInput;
import com.bluexml.xforms.generator.forms.renderable.forms.field.RenderableTextInput;
import com.bluexml.xforms.generator.forms.renderable.forms.field.RenderableURLInput;
import com.bluexml.xforms.generator.forms.rendered.RenderedXMLElement;
import com.bluexml.xforms.messages.MsgId;

/**
 * The Class RenderableField.
 */
public abstract class RenderableField<F extends Field> extends AbstractRenderableField {

	/** The generation manager. */
	protected XFormsGenerator generationManager;

	/** The parent form element. */
	protected FormElement parentFormElement;

	/** The form element. */
	protected F formElement;

	/**
	 * Instantiates a new renderable field.
	 * 
	 * @param generationManager
	 *            the generation manager
	 * @param parent
	 *            the parent
	 * @param formElement
	 *            the form element
	 */
	public RenderableField(XFormsGenerator generationManager, FormElement parent, F formElement) {
		super();
		this.generationManager = generationManager;
		this.parentFormElement = parent;
		this.formElement = formElement;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @seecom.bluexml.xforms.generator.forms.renderable.common.field.
	 * AbstractRenderableField#isReadOnly()
	 */
	@Override
	protected final boolean isReadOnly() {
		return getFormGenerator().isInReadOnlyMode() || formElement.isDisabled();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.bluexml.xforms.generator.forms.renderable.common.field.AbstractRenderableField#isRequired
	 * ()
	 */
	@Override
	protected boolean isRequired() {

		return formElement.isMandatory();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @seecom.bluexml.xforms.generator.forms.renderable.common.field.
	 * AbstractRenderableField#getOwner()
	 */
	@Override
	protected String getOwner() {
		return parentFormElement.getId();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @seecom.bluexml.xforms.generator.forms.renderable.common.field.
	 * AbstractRenderableField#getName()
	 */
	@Override
	protected final String getName() {
		return FormGenerator.getUniqueName(formElement);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @seecom.bluexml.xforms.generator.forms.renderable.common.field.
	 * AbstractRenderableField#getTitle()
	 */
	@Override
	protected final String getTitle() {
		return formElement.getLabel();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @seecom.bluexml.xforms.generator.forms.renderable.common.field.
	 * AbstractRenderableField#getHint()
	 */
	@Override
	protected final String getHint() {
		return formElement.getHelp_text();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @seecom.bluexml.xforms.generator.forms.renderable.common.field.AbstractRenderableField#
	 * applyConstraints(com.bluexml.xforms.generator.forms .modelelement.ModelElementBindSimple)
	 */
	@Override
	protected void applyConstraints(ModelElementBindSimple meb) {

		setHidden(meb, formElement.isHidden());
		// setRequired must be called before setLength!
		setRequired(meb, formElement.isMandatory());
		
		if (formElement instanceof CharField) {
			CharField charFieldElt = ((CharField) formElement);
			int minlength = charFieldElt.getMin_length();
			int maxlength = charFieldElt.getMax_length();

			if ((minlength > 0 || maxlength > 0) /* && (minlength < maxlength) */) {
				setLength(meb, "" + minlength, "" + maxlength);
			}
		}
	}

	/**
	 * Gets the renderable.
	 * 
	 * @param generationManager
	 *            the generation manager
	 * @param parent
	 *            the parent
	 * @param formElement
	 *            the form element
	 * 
	 * @return the renderable
	 */
	public static Renderable getRenderable(XFormsGenerator generationManager, FormElement parent,
			Field formElement) {
		Renderable renderable = null;

		if (formElement instanceof BooleanField) {
			renderable = new RenderableSimpleInput<BooleanField>(generationManager, parent,
					(BooleanField) formElement, "boolean");
		} else if (formElement instanceof ChoiceField) {
			ChoiceField choiceField = (ChoiceField) formElement;
			// if (choiceField.getWidget() == com.bluexml.side.form.ChoiceWidgetType.INLINE) {
			renderable = new RenderableChoiceInput(generationManager, parent, choiceField);
			// } else {
			// renderable = new RenderableChoiceInputSearch(generationManager, parent, choiceField);
			// }
		} else if (formElement instanceof DateTimeField) {
			renderable = new RenderableDateTimeInput(generationManager, parent, formElement);
		} else if (formElement instanceof TimeField) {
			renderable = new RenderableSimpleInput<TimeField>(generationManager, parent,
					(TimeField) formElement, "time");
		} else if (formElement instanceof DateField) {
			renderable = new RenderableSimpleInput<DateField>(generationManager, parent,
					(DateField) formElement, "date");
		} else if (formElement instanceof DecimalField) {
			renderable = new RenderableSimpleInput<DecimalField>(generationManager, parent,
					(DecimalField) formElement, "double");
		} else if (formElement instanceof EmailField) {
			renderable = new RenderableMailInput(generationManager, parent,
					(EmailField) formElement);
		} else if (formElement instanceof ImageField) {
			renderable = new RenderableFileInput(generationManager, parent, formElement, true);
		} else if (formElement instanceof FileField) {
			renderable = new RenderableFileInput(generationManager, parent, formElement, false);
		} else if (formElement instanceof FloatField) {
			renderable = new RenderableSimpleInput<FloatField>(generationManager, parent,
					(FloatField) formElement, "float");
		} else if (formElement instanceof IntegerField) {
			renderable = new RenderableSimpleInput<IntegerField>(generationManager, parent,
					(IntegerField) formElement, "integer");
		} else if (formElement instanceof PasswordField) {
			renderable = new RenderablePasswordInput(generationManager, parent,
					(PasswordField) formElement);
		} else if (formElement instanceof PhoneNumberField) {
			renderable = new RenderableSimpleInput<PhoneNumberField>(generationManager, parent,
					(PhoneNumberField) formElement, "string");
		} else if (formElement instanceof RegexField) {
			RegexField regexField = (RegexField) formElement;
			renderable = new RenderableRegexInput<RegexField>(generationManager, parent,
					regexField, regexField.getRegex());
		} else if (formElement instanceof URLField) {
			renderable = new RenderableURLInput(generationManager, parent, (URLField) formElement);
		} else if (formElement instanceof TextField) {
			renderable = new RenderableTextInput(generationManager, parent, (TextField) formElement);
		} else if (formElement instanceof CharField) {
			renderable = new RenderableSimpleInput<CharField>(generationManager, parent,
					(CharField) formElement, "string");
			// ** #1313
			CharField charField = (CharField) formElement;
			ModelElement ref = (ModelElement) getFormGenerator().getRealObject(charField.getRef());
			if (ref instanceof Attribute) {
				EList<String> elist = ((Attribute) ref).getAllowedValues();
				if ((elist != null) && (elist.size() > 0)) {
					renderable = new RenderableChoiceInputWorkflow(generationManager, parent, charField);
				}
			}
			// ** #1313
		} else if (formElement instanceof ActionField) {
			renderable = new RenderableActionField(generationManager, parent,
					(ActionField) formElement);
		}
		return renderable;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @seecom.bluexml.xforms.generator.forms.renderable.common.field.AbstractRenderableField#
	 * getErrorMessage()
	 */
	@Override
	protected String getErrorMessage() {
		String result = "";
		// getError_messages() is defined in Field, extended by F so all is OK
		Map<String, String> msgMap = formElement.getError_messages();

		if (msgMap != null) {
			Collection<String> msgColl = msgMap.values();
			// TODO Adapt the message building to the final format
			/*
			 * what format ? HTML, multi line ?
			 */
			result = "";
			for (String msg : msgColl) {
				result += " " + msg;
			}
		}
		return result;
	}

	/**
	 * Renders the element as nested into a div if the "appearance" property is set. The string
	 * value of the property is the CSS class of the div.
	 * 
	 * @param rendered
	 *            the XML element of this field. WILL BE MODIFIED IF A STYLE IS DEFINED.
	 */
	@Override
	protected void applyStyle(RenderedXMLElement rendered) {
		String appearance = StringUtils.trimToNull(formElement.getStyle());
		if (appearance != null) {
			Element div = XFormsGenerator.createElement("div", XFormsGenerator.NAMESPACE_XHTML);
			Element nestedElement = rendered.getXformsElement();
			div.setAttribute("class", appearance);
			div.addContent(nestedElement);

			Element divOut = XFormsGenerator.createElement("div", XFormsGenerator.NAMESPACE_XHTML);
			divOut.setAttribute("class", MsgId.INT_CSS_CLASS_BLUEXML_AUTOGEN.getText());
			divOut.addContent(div);

			rendered.setXMLElement(divOut);
		}
	}
}
