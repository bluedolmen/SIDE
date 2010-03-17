package com.bluexml.xforms.controller.mapping;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.bluexml.xforms.controller.binding.FormFieldType;
import com.bluexml.xforms.controller.binding.FormType;
import com.bluexml.xforms.controller.binding.ModelChoiceType;
import com.bluexml.xforms.controller.binding.ReferenceType;
import com.bluexml.xforms.controller.binding.VirtualFieldType;
import com.bluexml.side.form.utils.DOMUtil;

import org.w3c.dom.Element;
import org.w3c.dom.Node;

/**
 * The Class VirtualResolver.<br>
 * Process virtual fields in forms
 */
public class VirtualResolver {

	/**
	 * The Class Value.<br>
	 * Store a value of a virtual field
	 */
	protected class Value {

		/** The virtual field type. */
		protected VirtualFieldType virtualFieldType;

		/** The virtual parent. */
		protected Element virtualParent;

		/** The virtual value. */
		protected Element virtualValue;

		/** The real form type. */
		protected FormType realFormType;

		/** The real field type. */
		protected String realFieldType;

		/** The real parent. */
		protected Element realParent;

		/** The real value. */
		protected Element realValue;

		/**
		 * Gets the virtual field type.
		 * 
		 * @return the virtual field type
		 */
		public VirtualFieldType getVirtualFieldType() {
			return virtualFieldType;
		}

		/**
		 * Gets the virtual parent.
		 * 
		 * @return the virtual parent
		 */
		public Element getVirtualParent() {
			return virtualParent;
		}

		/**
		 * Gets the virtual value.
		 * 
		 * @return the virtual value
		 */
		public Element getVirtualValue() {
			return virtualValue;
		}

		/**
		 * Gets the real form type.
		 * 
		 * @return the real form type
		 */
		public FormType getRealFormType() {
			return realFormType;
		}

		/**
		 * Gets the real field type.
		 * 
		 * @return the real field type
		 */
		public String getRealFieldType() {
			return realFieldType;
		}

		/**
		 * Gets the real parent.
		 * 
		 * @return the real parent
		 */
		public Element getRealParent() {
			return realParent;
		}

		/**
		 * Gets the real value.
		 * 
		 * @return the real value
		 */
		public Element getRealValue() {
			return realValue;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.lang.Object#toString()
		 */
		@Override
		public String toString() {
			try {
				return BeanUtils.describe(this).toString();
			} catch (Exception e) {
				if (logger.isErrorEnabled()) {
					logger.error("Error converting object to String", e);
				}
			}
			return super.toString();
		}

	}

	/** The logger. */
	protected static Log logger = LogFactory.getLog(VirtualResolver.class);

	/** The values. */
	protected List<Value> values = new ArrayList<Value>();

	/** The mapping tool. */
	protected MappingToolCommon mappingTool;

	/**
	 * Instantiates a new virtual resolver.
	 * 
	 * @param mappingTool
	 *            the mapping tool
	 */
	public VirtualResolver(MappingToolCommon mappingTool) {
		super();
		this.mappingTool = mappingTool;
	}

	/**
	 * Prepare alfresco to x forms.
	 * 
	 * @param source
	 *            the source
	 * @param formName
	 *            the form name
	 */
	public void prepareAlfrescoToXForms(Element source, String formName) {
		if (logger.isDebugEnabled()) {
			mappingTool.logXML(source, "prepareAlfrescoToXForms", "input");
		}
		collectValues(source, formName);
		for (Value value : values) {
			appendVirtualField(value);
		}
		if (logger.isDebugEnabled()) {
			mappingTool.logXML(source, "prepareAlfrescoToXForms", "output");
		}
	}

	/**
	 * Prepare x forms to alfresco.
	 * 
	 * @param source
	 *            the source
	 * @param formName
	 *            the form name
	 */
	public void prepareXFormsToAlfresco(Element source, String formName) {
		if (logger.isDebugEnabled()) {
			mappingTool.logXML(source, "prepareXFormsToAlfresco", "input");
		}
		collectValues(source, formName);
		for (Value value : values) {
			propagateVirtualField(value);
		}
		if (logger.isDebugEnabled()) {
			mappingTool.logXML(source, "prepareXFormsToAlfresco", "output");
		}
	}

	/**
	 * Append virtual field.
	 * 
	 * @param value
	 *            the value
	 */
	private void appendVirtualField(Value value) {
		if (value.realValue != null) {
			Element virtualElementParent = value.realValue.getOwnerDocument().createElement(
					value.virtualFieldType.getUniqueName());
			Node clonedValue = value.realValue.cloneNode(true);
			virtualElementParent.appendChild(clonedValue);
			value.virtualParent.appendChild(virtualElementParent);
		} else {
			if (logger.isErrorEnabled()) {
				logger.error("Invalid value " + value);
			}
		}
	}

	/**
	 * Propagate virtual field.
	 * 
	 * @param value
	 *            the value
	 */
	private void propagateVirtualField(Value value) {
		if (value.virtualValue != null) {
			boolean replace = false;
			if (value.realValue != null) {
				replace = true;
			}

			Node clonedVirtual = value.virtualValue.cloneNode(true);

			if (replace) {
				value.realParent.replaceChild(clonedVirtual, value.realValue);
			} else {
				value.realParent.appendChild(clonedVirtual);
			}
		} else {
			if (logger.isErrorEnabled()) {
				logger.error("Invalid value " + value);
			}
		}
	}

	/**
	 * Collect values.
	 * 
	 * @param source
	 *            the source
	 * @param formName
	 *            the form name
	 */
	private void collectValues(Element source, String formName) {
		FormType formType = mappingTool.getFormType(formName);
		// FIXME: adapt this test if virtual fields are ever supported in FormWorkflow's
		if (formType == null) {
			return;
		}
		List<VirtualFieldType> virtuals = formType.getVirtual();
		for (VirtualFieldType virtualFieldType : virtuals) {
			Value value = new Value();
			value.virtualFieldType = virtualFieldType;
			value.virtualParent = source;

			Element virtualElementParent = DOMUtil.getChild(source, virtualFieldType
					.getUniqueName());
			if (virtualElementParent != null) {
				value.virtualValue = DOMUtil.getChild(virtualElementParent, virtualFieldType
						.getFieldName());
			}

			value.realFormType = mappingTool.getFormType(virtualFieldType.getFormName());
			value.realFieldType = virtualFieldType.getFieldName();
			values.add(value);
		}
		List<FormFieldType> fields = formType.getField();
		for (FormFieldType formFieldType : fields) {
			String fieldName = formFieldType.getUniqueName();
			processValue(source, formName, fieldName);
		}
		List<ModelChoiceType> modelChoices = formType.getModelChoice();
		for (ModelChoiceType modelChoice : modelChoices) {
			String fieldName = modelChoice.getUniqueName();
			processValue(source, formName, fieldName);
		}
		List<ReferenceType> references = formType.getReference();
		for (ReferenceType referenceType : references) {
			String fieldName = referenceType.getUniqueName();
			processValue(source, formName, fieldName);

			Element referenceElement = DOMUtil.getChild(source, fieldName);
			if (referenceElement != null) {
				List<FormType> targets = referenceType.getTarget();
				for (FormType target : targets) {
					String targetName = target.getName();
					Element targetElement = DOMUtil.getChild(referenceElement, targetName);
					if (targetElement != null) {
						collectValues(targetElement, targetName);
					}
				}
			} else {
				if (logger.isErrorEnabled()) {
					logger.error("referenceElement is null");
				}
			}

		}
	}

	/**
	 * Process value.
	 * 
	 * @param source
	 *            the source
	 * @param formName
	 *            the form name
	 * @param fieldName
	 *            the field name
	 */
	private void processValue(Element source, String formName, String fieldName) {
		Value value = getValue(formName, fieldName);
		if (value != null) {
			value.realParent = source;
			value.realValue = DOMUtil.getChild(source, value.realFieldType);
		}
	}

	/**
	 * Gets the value.
	 * 
	 * @param formName
	 *            the form name
	 * @param fieldName
	 *            the field name
	 * 
	 * @return the value
	 */
	private Value getValue(String formName, String fieldName) {
		for (Value value : values) {
			String valueFormName = value.realFormType.getName();
			String valueFieldName = value.realFieldType;
			if (StringUtils.equals(valueFormName, formName)
					&& StringUtils.equals(valueFieldName, fieldName)) {
				return value;
			}
		}
		return null;
	}
}
