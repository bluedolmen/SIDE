package com.bluexml.xforms.generator.forms.renderable.common.association.selection.selector;

import java.util.Stack;

import javax.xml.namespace.QName;

import com.bluexml.xforms.controller.messages.MsgId;

import com.bluexml.xforms.generator.forms.Renderable;
import com.bluexml.xforms.generator.forms.Rendered;
import com.bluexml.xforms.generator.forms.XFormsGenerator;
import com.bluexml.xforms.generator.forms.modelelement.AbstractModelElementUpdater;
import com.bluexml.xforms.generator.forms.modelelement.ModelElementBindSimple;
import com.bluexml.xforms.generator.forms.modelelement.ModelElementEnumeration;
import com.bluexml.xforms.generator.forms.modelelement.ModelElementInstanceList;
import com.bluexml.xforms.generator.forms.modelelement.ModelElementUpdaterEnum;
import com.bluexml.xforms.generator.forms.modelelement.ModelElementUpdaterList;
import com.bluexml.xforms.generator.forms.modelelement.ModelElementWorkflowInstanceList;
import com.bluexml.xforms.generator.forms.modelelement.ModelElementWorkflowInstanceUpdater;
import com.bluexml.xforms.generator.forms.modelelement.ModelElementWorkflowProcessList;
import com.bluexml.xforms.generator.forms.renderable.common.AssociationBean;
import com.bluexml.xforms.generator.forms.renderable.common.AssociationBean.AssociationType;
import com.bluexml.xforms.generator.forms.renderable.common.association.AbstractRenderable;
import com.bluexml.xforms.generator.forms.rendered.RenderedDiv;
import com.bluexml.xforms.generator.tools.ModelTools;

/**
 * The Class RenderableSelector.
 */
public class RenderableSelector extends AbstractRenderable {

	/** The bind id. */
	private ModelElementBindSimple bindId;

	/** The bind label. */
	private ModelElementBindSimple bindLabel;

	/** The bind for max results. */
	private ModelElementBindSimple bindMaxResults;

	/** The bind for implementing the "mandatory" property. */
	private ModelElementBindSimple bindMandatory;

	/** The instance name. */
	private String instanceName;

	/** The instance path. */
	private String instancePath;

	/** The instance node path. */
	private String instanceNodePath;

	/** The model element list updater. */
	private AbstractModelElementUpdater modelElementUpdater;

	private boolean isForWorkflow;

	/**
	 * Instantiates a new renderable selector.
	 * 
	 * @param associationBean
	 *            the association bean
	 */
	public RenderableSelector(AssociationBean associationBean) {
		super(associationBean);

		isForWorkflow = associationBean.isForWorkflow();

		if (isForWorkflow) {
			if (associationBean.getAssociationType() == AssociationType.wkflwProcess) {
				instanceName = MsgId.INT_WKFLW_PROCESS_INSTANCE_NAME.getText();
				modelElementUpdater = new ModelElementWorkflowInstanceUpdater();
			} else {
				instanceName = MsgId.INT_WKFLW_INSTANCE_INSTANCE_NAME.getText();
			}
		} else if (associationBean.getAssociationType() == AssociationType.clazz) {
			instanceName = XFormsGenerator.getId(ModelTools.getCompleteNameJAXB(associationBean
					.getDestinationClass())
					+ "ListInstance");
			modelElementUpdater = new ModelElementUpdaterList(
					associationBean.getDestinationClass(), instanceName, associationBean
							.getFormatPattern(), associationBean.getLabelLength());
		} else {
			instanceName = XFormsGenerator.getId(ModelTools.getCompleteNameJAXB(associationBean
					.getDestinationSelect().getEnumeration())
					+ "EnumInstance");
			modelElementUpdater = new ModelElementUpdaterEnum(associationBean, instanceName);
		}
		instancePath = "instance('" + instanceName + "')/";
		instanceNodePath = instancePath + "item";

		bindId = new ModelElementBindSimple("");
		bindLabel = new ModelElementBindSimple("");
		bindMaxResults = new ModelElementBindSimple("");
		bindMandatory = new ModelElementBindSimple("");

		bindId.setType(new QName("string"));
		bindLabel.setType(new QName("string"));
		bindMaxResults.setType(new QName("string"));
		bindMaxResults.setHidden(true);
		bindMandatory.setType(new QName("integer"));
		bindMandatory.setHidden(true);

		bindId.setNodeset(instancePath + MsgId.INT_INSTANCE_SELECTEDID);
		bindLabel.setNodeset(instancePath + MsgId.INT_INSTANCE_SELECTEDLABEL);
		bindMaxResults.setNodeset(instancePath + MsgId.INT_INSTANCE_SELECTEDMAX);
		bindMandatory.setNodeset(instancePath + MsgId.INT_INSTANCE_SELECTEDNUMBER);

		if (bean.isMandatory()) { // #978
			// setting bindId's required to true will give the visual cue (a red star under Chiba)
			bindId.setRequired(true);
			// !!! the constraint will be set by the actions !!!
		}
		// for workflows, we don't want anything but the list
		add(new RenderableSelectorList(associationBean, this));
		if (isForWorkflow == false) {
			add(new RenderableSelectorCount(associationBean, this));
			add(new RenderableSelectorSearcher(associationBean, this));
			if (associationBean.isShowingActions()
					&& associationBean.getAssociationType() == AssociationBean.AssociationType.clazz) {
				add(new RenderableSelectorCreate(associationBean, this));
			}
		}
	}

	/**
	 * Gets the instance name.
	 * 
	 * @return the instance name
	 */
	public String getInstanceName() {
		return instanceName;
	}

	/**
	 * Gets the instance path.
	 * 
	 * @return the instance path
	 */
	public String getInstancePath() {
		return instancePath;
	}

	/**
	 * Gets the instance node path.
	 * 
	 * @return the instance node path
	 */
	public String getInstanceNodePath() {
		return instanceNodePath;
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
		return ROOT_RELATIVE;
	}

	/**
	 * Gets the bind id.
	 * 
	 * @return the bind id
	 */
	public ModelElementBindSimple getBindId() {
		return bindId;
	}

	/**
	 * Gets the bind label.
	 * 
	 * @return the bind label
	 */
	public ModelElementBindSimple getBindLabel() {
		return bindLabel;
	}

	/**
	 * @return the bindMaxResults
	 */
	public ModelElementBindSimple getBindMaxResults() {
		return bindMaxResults;
	}

	public AbstractModelElementUpdater getModelElementUpdater() {
		return modelElementUpdater;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.bluexml.xforms.generator.forms.Renderable#render(java.lang.String, java.util.Stack,
	 * java.util.Stack)
	 */
	@Override
	public Rendered render(String path, Stack<Renderable> parents, Stack<Rendered> renderedParents) {
		RenderedDiv rendered = new RenderedDiv(XFormsGenerator.getId("Selector"));

		if (isForWorkflow == false) {
			if (bean.getAssociationType() == AssociationType.clazz) {
				rendered.addModelElement(new ModelElementInstanceList(bean.getDestinationClass(),
						instanceName, bean.getFormatPattern(), bean.getLabelLength()));
			} else {
				rendered.addModelElement(new ModelElementEnumeration(bean.getDestinationSelect(),
						instanceName));
			}
			rendered.addModelElement(modelElementUpdater);
		} else {
			if (bean.getAssociationType() == AssociationType.wkflwProcess) {
				rendered.addModelElement(new ModelElementWorkflowProcessList());
				rendered.addModelElement(modelElementUpdater);
			} else {
				rendered.addModelElement(new ModelElementWorkflowInstanceList());
			}
		}
		rendered.addModelElement(bindId);
		rendered.addModelElement(bindLabel);
		rendered.addModelElement(bindMaxResults);
		rendered.addModelElement(bindMandatory);

		return rendered;
	}

	/**
	 * @return the bindMandatory
	 */
	public ModelElementBindSimple getBindMandatory() {
		return bindMandatory;
	}

}
