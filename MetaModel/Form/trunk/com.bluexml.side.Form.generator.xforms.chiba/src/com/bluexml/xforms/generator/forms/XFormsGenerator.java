package com.bluexml.xforms.generator.forms;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.Map.Entry;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.bluexml.xforms.actions.AbstractAction;
import com.bluexml.xforms.messages.MsgId;
import com.bluexml.xforms.messages.MsgPool;
import org.eclipse.emf.common.util.EList;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.Namespace;
import org.jdom.input.SAXBuilder;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

import com.bluexml.side.clazz.Aspect;
import com.bluexml.side.clazz.Association;
import com.bluexml.side.clazz.Attribute;
import com.bluexml.side.clazz.Clazz;
import com.bluexml.side.clazz.Enumeration;
import com.bluexml.side.clazz.EnumerationLiteral;
import com.bluexml.side.form.ChoiceWidgetType;
import com.bluexml.side.form.FormClass;
import com.bluexml.side.form.FormContainer;
import com.bluexml.side.form.FormGroup;
import com.bluexml.side.form.FormGroupPresentationType;
import com.bluexml.side.form.FormWorkflow;
import com.bluexml.side.form.impl.FormGroupImpl;
import com.bluexml.xforms.generator.AbstractDataGenerator;
import com.bluexml.xforms.generator.forms.modelelement.ModelElementBindSimple;
import com.bluexml.xforms.generator.forms.renderable.RenderableXForm;
import com.bluexml.xforms.generator.forms.renderable.classes.RenderableClass;
import com.bluexml.xforms.generator.forms.renderable.classes.RenderableClassSelector;
import com.bluexml.xforms.generator.forms.renderable.common.AssociationBean;
import com.bluexml.xforms.generator.forms.renderable.common.RenderableDiv;
import com.bluexml.xforms.generator.forms.renderable.common.RenderableHR;
import com.bluexml.xforms.generator.forms.renderable.common.RenderableXGroup;
import com.bluexml.xforms.generator.forms.renderable.common.SelectBean;
import com.bluexml.xforms.generator.forms.renderable.common.association.selection.selector.RenderableSelector;
import com.bluexml.xforms.generator.forms.renderable.common.association.selection.unique.RenderableSSingle;
import com.bluexml.xforms.generator.forms.renderable.forms.group.RenderableFormContainer;
import com.bluexml.xforms.generator.forms.renderable.forms.group.RenderableGroup;
import com.bluexml.xforms.generator.forms.renderable.forms.group.RenderableGroupWorkflow;
import com.bluexml.xforms.generator.forms.renderable.lists.RenderableClassList;
import com.bluexml.xforms.generator.forms.rendered.RenderedParentGroup;
import com.bluexml.xforms.generator.tools.ClasseComparator;
import com.bluexml.xforms.generator.tools.ModelTools;

/**
 * The Class XFormsGenerator.
 */
public class XFormsGenerator extends AbstractDataGenerator {

	/** The Constant NAMESPACE_EVENTS. */
	public static final Namespace NAMESPACE_EVENTS = Namespace.getNamespace("ev",
			"http://www.w3.org/2001/xml-events");

	/** The Constant NAMESPACE_XFORMS. */
	public static final Namespace NAMESPACE_XFORMS = Namespace.getNamespace("xf",
			"http://www.w3.org/2002/xforms");

	/** The Constant NAMESPACE_XHTML. */
	public static final Namespace NAMESPACE_XHTML = Namespace.getNamespace("xhtml",
			"http://www.w3.org/1999/xhtml");

	/** The sax builder. */
	public static SAXBuilder saxBuilder;

	/** The outputter. */
	public static XMLOutputter outputter;

	/** The keys. */
	private static Map<String, Set<String>> keys = new HashMap<String, Set<String>>();

	/** The general keys. */
	private static Map<String, Set<String>> generalKeys = new HashMap<String, Set<String>>();

	/** The logger. */
	protected static Log logger = LogFactory.getLog(XFormsGenerator.class);

	static {
		saxBuilder = new SAXBuilder();
		outputter = new XMLOutputter(Format.getPrettyFormat());
	}

	/** The output xforms folder. */
	private File outputXForms;

	/** The classes. */
	private Map<Clazz, RenderableClass> classes = new TreeMap<Clazz, RenderableClass>(
			ClasseComparator.INSTANCE);

	/** The forms. */
	private Map<FormContainer, RenderableFormContainer> forms = new HashMap<FormContainer, RenderableFormContainer>();

	private static boolean renderingWorkflow;

	/**
	 * Creates a DOM element.
	 * 
	 * @param tagName
	 *            the tag name
	 * @param namespace
	 *            the namespace
	 * 
	 * @return the element
	 */
	public static Element createElement(String tagName, Namespace namespace) {
		return new Element(tagName, namespace);
	}

	/**
	 * Creates a DOM element with content.
	 * 
	 * @param tagName
	 *            the tag name
	 * @param namespace
	 *            the namespace
	 * @param content
	 *            the content
	 * 
	 * @return the element
	 */
	public static Element createElementWithContent(String tagName, Namespace namespace,
			String content) {
		Element createdElement = createElement(tagName, namespace);
		createdElement.setText(content);
		return createdElement;
	}

	/**
	 * Creates a DOM element with label.
	 * 
	 * @param tagName
	 *            the tag name
	 * @param namespace
	 *            the namespace
	 * @param label
	 *            the label
	 * 
	 * @return the element
	 */
	public static Element createElementWithLabel(String tagName, Namespace namespace, String label) {
		Element element = createElement(tagName, namespace);
		Element labelElement = createElementWithContent("label", NAMESPACE_XFORMS, label);
		element.addContent(labelElement);
		return element;
	}

	/**
	 * Creates a trigger with label image.
	 * 
	 * @param image
	 *            the image
	 * 
	 * @return the element
	 */
	public static Element createTriggerWithLabelImage(String image) {
		Element element = createElement("trigger", NAMESPACE_XFORMS);
		element.setAttribute("appearance", "minimal");
		Element labelElement = createElement("label", NAMESPACE_XFORMS);
		Element imageElement = createElement("img", NAMESPACE_XHTML);
		imageElement.setAttribute("border", "0");
		imageElement.setAttribute("src", image);
		labelElement.addContent(imageElement);
		element.addContent(labelElement);
		return element;
	}

	/**
	 * Creates a xforms group.
	 * 
	 * @param label
	 *            the label
	 * 
	 * @return the element
	 */
	public static Element createXFormsGroup(String label) {
		Element group = XFormsGenerator.createElementWithLabel("group", NAMESPACE_XFORMS, label);
		group.setAttribute("appearance", "full");
		return group;
	}

	/**
	 * Gets the nth bind.
	 * 
	 * @param parentRendered
	 *            the parent rendered
	 * @param nth
	 *            the nth
	 * 
	 * @return the bind
	 */
	public static ModelElementBindSimple getBind(Rendered parentRendered, int nth) {
		List<ModelElement> modelElements = new ArrayList<ModelElement>(parentRendered
				.getModelElements());
		// search also in parent binds if parent is a group
		if (parentRendered instanceof RenderedParentGroup) {
			modelElements.addAll(((RenderedParentGroup) parentRendered).getParent()
					.getModelElements());
		}

		int i = 0;
		for (ModelElement modelElement : modelElements) {
			if (modelElement instanceof ModelElementBindSimple) {
				i++;
				if (i == nth) {
					return (ModelElementBindSimple) modelElement;
				}
			}
		}
		throw new RuntimeException(new NullPointerException());
	}

	/**
	 * Gets an id.
	 * 
	 * @param id
	 *            the id
	 * 
	 * @return the id
	 */
	public static String getId(String id) {
		Set<String> list = keys.get(id);
		if (list == null) {
			list = new HashSet<String>();
			list.add(id);
			keys.put(id, list);
			return id;
		}
		int i = 0;
		String nid = null;

		do {
			i++;
			nid = id + "-" + i;
		} while (list.contains(nid));
		list.add(nid);
		return nid;
	}

	/**
	 * Gets the general id.
	 * 
	 * @param id
	 *            the id
	 * 
	 * @return the general id
	 */
	public static String getGeneralId(String id) {
		Set<String> list = generalKeys.get(id);
		if (list == null) {
			list = new HashSet<String>();
			list.add(id);
			generalKeys.put(id, list);
			return id;
		}
		int i = 0;
		String nid = null;

		do {
			i++;
			nid = id + "-" + i;
		} while (list.contains(nid));
		list.add(nid);
		return nid;
	}

	/**
	 * Reset keys.
	 */
	public static void resetKeys() {
		keys = new HashMap<String, Set<String>>();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.bluexml.xforms.generator.DataGenerator#addAspectForClass(com.bluexml
	 * .side.clazz.Clazz, com.bluexml.side.clazz.Aspect, com.bluexml.side.clazz.Clazz)
	 */
	public void addAspectForClass(Clazz classe, Aspect aspect, Clazz owner) {
		getClassBean(classe).addAspect(aspect);
	}

	/**
	 * Adds the association.
	 * 
	 * @param type
	 *            the type
	 * @param name
	 *            the name
	 * @param title
	 *            the title
	 * @param source
	 *            the source
	 * @param destination
	 *            the destination
	 * @param doublenav
	 *            the doublenav
	 * @param association
	 *            the association
	 */
	public void addAssociation(AssociationKind type, String name, String title, Clazz source,
			Clazz destination, boolean doublenav, Association association) {
		RenderableClass classBeanSource = getClassBean(source);
		RenderableClass classBeanDestination = getClassBean(destination);
		classBeanSource.addAssociation(destination, classBeanDestination, name, title, type,
				association);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.bluexml.xforms.generator.DataGenerator#addAssociation(org.blueXML
	 * .xforms.generator.DataGenerator.AssociationKind, java.lang.String, java.lang.String,
	 * com.bluexml.side.clazz.Clazz, com.bluexml.side.clazz.Clazz, java.lang.String, boolean,
	 * com.bluexml.side.clazz.Association, com.bluexml.side.clazz.Clazz)
	 */
	public void addAssociation(AssociationKind type, String name, String title, Clazz source,
			Clazz destination, String role, boolean doublenav, Association association, Clazz owner) {
		addAssociation(type, name, title, source, destination, doublenav, association);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.bluexml.xforms.generator.DataGenerator#addAttributeForAspect(com.
	 * bluexml.side.clazz.Aspect, com.bluexml.side.clazz.Attribute)
	 */
	public void addAttributeForAspect(Aspect aspect, Attribute attribute) {
		// nothing
	}

	/**
	 * Adds the attribute for class.
	 * 
	 * @param classe
	 *            the classe
	 * @param attribute
	 *            the attribute
	 */
	public void addAttributeForClass(Clazz classe, Attribute attribute) {
		getClassBean(classe).addAttribute(attribute);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.bluexml.xforms.generator.DataGenerator#addAttributeForClass(com.bluexml
	 * .side.clazz.Clazz, com.bluexml.side.clazz.Attribute, com.bluexml.side.clazz.Clazz)
	 */
	public void addAttributeForClass(Clazz classe, Attribute attribute, Clazz owner) {
		addAttributeForClass(classe, attribute);
	}

	/**
	 * Adds the id for class.
	 * 
	 * @param classe
	 *            the classe
	 * @param string
	 *            the string
	 */
	public void addIdForClass(Clazz classe, String string) {
		// nothing
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.bluexml.xforms.generator.DataGenerator#addIdForClass(com.bluexml. side.clazz.Clazz,
	 * java.lang.String, boolean)
	 */
	public void addIdForClass(Clazz classe, String string, boolean hasParent) {
		addIdForClass(classe, string);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.bluexml.xforms.generator.DataGenerator#beginAspect(com.bluexml.side .clazz.Aspect)
	 */
	public void beginAspect(Aspect aspect) {
		// nothing
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.bluexml.xforms.generator.DataGenerator#beginClasse(com.bluexml.side .clazz.Clazz,
	 * boolean)
	 */
	public void beginClasse(Clazz classe, boolean rendered) {
		RenderableClass classeBean = null;
		classeBean = new RenderableClass(classe, rendered);
		classes.put(classe, classeBean);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.bluexml.xforms.generator.DataGenerator#beginForm(com.bluexml.side .form.Form)
	 */
	public void beginForm(FormContainer formContainer) {
		RenderableFormContainer formBean = new RenderableFormContainer(this, null, formContainer);

		forms.put(formContainer, formBean);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.bluexml.xforms.generator.DataGenerator#beginGeneration()
	 */
	public void beginGeneration() {
		genLogger.info("XFormGenerator: Generating XHTML templates.");
		if (monitor != null) {
			monitor.setTaskName("Generation of XHTML templates.");
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.bluexml.xforms.generator.DataGenerator#beginListAspects()
	 */
	public void beginListAspects() {
		// nothing
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.bluexml.xforms.generator.DataGenerator#beginListAssociations()
	 */
	public void beginListAssociations() {
		// nothing
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.bluexml.xforms.generator.DataGenerator#beginListClasses()
	 */
	public void beginListClasses() {
		// nothing
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.bluexml.xforms.generator.DataGenerator#beginListEnums()
	 */
	public void beginListEnums() {
		// nothing
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.bluexml.xforms.generator.DataGenerator#beginListForms()
	 */
	public void beginListForms() {
		// nothing
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.bluexml.xforms.generator.DataGenerator#endAspect(com.bluexml.side .clazz.Aspect)
	 */
	public void endAspect(Aspect aspect) {
		// nothing
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.bluexml.xforms.generator.DataGenerator#endClasse(com.bluexml.side .clazz.Clazz)
	 */
	public void endClasse(Clazz classe) {
		// nothing
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.bluexml.xforms.generator.DataGenerator#endForm(com.bluexml.side.form .Form)
	 */
	public void endForm(FormContainer form) {
		// nothing
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.bluexml.xforms.generator.DataGenerator#endGeneration()
	 */
	public void endGeneration() {

		Set<Entry<Clazz, RenderableClass>> entrySet = classes.entrySet();
		for (Entry<Clazz, RenderableClass> entry : entrySet) {

			Clazz classe = entry.getKey();
			genLogger.info("  class: " + ModelTools.getCompleteName(classe));
			RenderableClass classeBean = entry.getValue();

			if (classeBean.isToRender()) {
				String formName = ModelTools.getCompleteName(classe);
				String title = ModelTools.getTitle(classe);
				if (!classe.isAbstract()) {
					render(outputXForms, classeBean, formName, title, FormTypeRendered.formClass,
							false, false);

					render(outputXForms, new RenderableClassList(classe), formName, title,
							FormTypeRendered.formClassList, false, false);
				}
				if (classeBean.hasSubClasses()) {
					render(outputXForms, new RenderableClassSelector(classeBean.getSubClasses()),
							formName, title, FormTypeRendered.formClassSubClassSelector, false,
							false);
				}
			}

		}

		boolean atLeastOneWorfklowForm = false;
		Set<Entry<FormContainer, RenderableFormContainer>> entrySetForms = forms.entrySet();
		for (Entry<FormContainer, RenderableFormContainer> formEntry : entrySetForms) {
			FormContainer form = formEntry.getKey();
			boolean isAWorkflowForm = form instanceof FormWorkflow;
			atLeastOneWorfklowForm = atLeastOneWorfklowForm || isAWorkflowForm;
			String logText = " Rendering "
					+ (form instanceof FormClass ? "FormClass" : "FormWorkflow") + ": "
					+ form.getId();
			genLogger.info(logText);
			if (monitor != null) {
				monitor.addText(logText);
			}
			RenderableFormContainer value = formEntry.getValue();

			render(outputXForms, form, value, isAWorkflowForm);
		}

		if (atLeastOneWorfklowForm) {
			renderWorkflowSelectionForm();
		}
		genLogger.info("XFormGenerator: Finished generating XHTML templates.");
	}

	/**
	 * Generates the form for selecting a process definition.
	 */
	private void renderWorkflowSelectionForm() {

		class IntGroupImpl extends FormGroupImpl {
			String id;
			String label;

			public IntGroupImpl(String id, String label) {
				this.id = id;
				this.label = label;
			}

			@Override
			public FormGroupPresentationType getPresentation() {
				return FormGroupPresentationType.AUTO;
			}

			@Override
			public String getLabel() {
				return label;
			}

			@Override
			public String getId() {
				return id;
			}
		}
		//
		IntGroupImpl fglobalGroup = new IntGroupImpl("workflow", MsgPool
				.getMsg(MsgId.MSG_WKFLW_GLOBAL_GROUP));
		RenderableGroup<FormGroup> rglobalGroup = new RenderableGroup<FormGroup>(this, null,
				fglobalGroup);
		//
		IntGroupImpl fprocessGroup = new IntGroupImpl(MsgId.INT_WKFLW_PROCESS_NODESET.getText(),
				MsgPool.getMsg(MsgId.MSG_WKFLW_PROCESS_GROUP));
		RenderableGroupWorkflow<FormGroup> rprocessGroup = new RenderableGroupWorkflow<FormGroup>(
				this, fglobalGroup, fprocessGroup);

		Renderable processList = newWorkflowSelectionWidget(true);
		rprocessGroup.add(processList);
		rglobalGroup.add(rprocessGroup);
		//
		IntGroupImpl finstanceGroup = new IntGroupImpl(MsgId.INT_WKFLW_INSTANCE_NODESET.getText(),
				MsgPool.getMsg(MsgId.MSG_WKFLW_INSTANCE_GROUP));
		RenderableGroupWorkflow<FormGroup> rinstanceGroup = new RenderableGroupWorkflow<FormGroup>(
				this, fglobalGroup, finstanceGroup);

		Renderable instanceList = newWorkflowSelectionWidget(false);
		rinstanceGroup.add(instanceList);
		rglobalGroup.add(rinstanceGroup);

		// RenderableXForm rform = new RenderableXForm(rglobalGroup,
		// MsgId.MSG_WKFLW_SEL_PAGE_TITLE.getText(), actions, true);
		render(outputXForms, rglobalGroup, MsgId.INT_WKFLW_SEL_FORM_FILENAME.getText(), MsgPool
				.getMsg(MsgId.MSG_WKFLW_SEL_PAGE_TITLE), FormTypeRendered.formWkflwSel, false, true);
	}

	/**
	 * Builds the widget for selecting process definitions
	 * 
	 * @param renderedParent
	 */
	private Renderable newWorkflowSelectionWidget(boolean isProcess) {
		String nodeset;
		MsgId widgetTitle;
		String groupName;

		if (isProcess) {
			nodeset = MsgId.INT_WKFLW_PROCESS_NODESET.getText();
			widgetTitle = MsgId.MSG_WKFLW_PROCESS_WIDGET_TITLE;
			groupName = "workflow_definitions";
		} else {
			nodeset = MsgId.INT_WKFLW_PROCESS_NODESET.getText();
			widgetTitle = MsgId.MSG_WKFLW_INSTANCE_WIDGET_TITLE;
			groupName = "workflow_instances";
		}
		SelectBean selectBean = new SelectBean();
		ModelElementBindSimple meb = new ModelElementBindSimple(MsgId.INT_WKFLW_NODESET_PREFIX
				.getText()
				+ "/" + nodeset);

		selectBean.setEnumeration(null);
		selectBean.setEnumContext(null);
		selectBean.setEnumParent(null);
		selectBean.setWidgetType(ChoiceWidgetType.SHOW_ONE);
		selectBean.setLabel(MsgPool.getMsg(widgetTitle));
		selectBean.setModelElementBindSimple(meb);
		selectBean.setMultiple(false);
		selectBean.setLimited(false);

		AssociationBean associationBean = new AssociationBean();

		associationBean.setForWorkflow(true);
		associationBean.setCreateEditForm(null);
		associationBean.setDestinationRenderable(null);
		if (isProcess) {
			associationBean.setDestinationProcessSelect(selectBean);
		} else {
			associationBean.setDestinationInstanceSelect(selectBean);
		}
		associationBean.setName(groupName);
		associationBean.setShowingActions(false);
		associationBean.setFieldSize("0");

		RenderableSelector selector = new RenderableSelector(associationBean);
		RenderableSSingle renderable = new RenderableSSingle(associationBean, selector, null);
		// Rendered rendered = renderable.recursiveRender();
		// MsgId.INT_WKFLW_NODESET_PREFIX.getText(), null, null);
		// renderedParent.addRendered(rendered, null);
		return renderable;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.bluexml.xforms.generator.DataGenerator#endListAspects()
	 */
	public void endListAspects() {
		// nothing
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.bluexml.xforms.generator.DataGenerator#endListAssociations()
	 */
	public void endListAssociations() {
		// nothing
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.bluexml.xforms.generator.DataGenerator#endListClasses()
	 */
	public void endListClasses() {
		Set<Entry<Clazz, RenderableClass>> entrySet = classes.entrySet();
		for (Entry<Clazz, RenderableClass> entry : entrySet) {
			addGeneralizations(entry.getKey(), entry.getKey());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.bluexml.xforms.generator.DataGenerator#endListEnums()
	 */
	public void endListEnums() {
		// nothing
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.bluexml.xforms.generator.DataGenerator#endListForms()
	 */
	public void endListForms() {
		Set<Entry<FormContainer, RenderableFormContainer>> entrySet = forms.entrySet();
		// System.out.println("listing forms ---");
		// for (FormContainer entry : forms.keySet()) {
		// System.out.println(entry.getId());
		// }
		// System.out.println("-----------------");
		//
		// deal with FormClass's first
		for (Entry<FormContainer, RenderableFormContainer> entry : entrySet) {
			FormContainer FC = entry.getKey();
			if (FC instanceof FormClass) {
				RenderableFormContainer renderableFC = entry.getValue();
				renderableFC.compute();
			}
		}

		//
		for (Entry<FormContainer, RenderableFormContainer> entry : entrySet) {
			FormContainer FC = entry.getKey();
			if (FC instanceof FormWorkflow) {
				RenderableFormContainer renderableFC = entry.getValue();
				// add all form elements defined in the form editor
				renderableFC.compute();

				//
				FormWorkflow FW = ((FormWorkflow) FC);
				FormClass dataForm = FW.getDataForm();
				if (dataForm == null) {
					throw new RuntimeException("Workflow Form '" + FW.getId()
							+ "' must have a data form.");
				}
				RenderableFormContainer attached = searchForForm(dataForm);
				if (attached == null) {
					throw new RuntimeException("Workflow Form '" + FW.getId()
							+ "' has no attached data form. Id of DataForm: " + dataForm.getId());
				}
				// TODO: clone the attached renderable
				if (formGenerator.isRenderDataBeforeWorkflow()) {
					renderableFC.addFirst(new RenderableHR());
					renderableFC.addFirst(attached);
				} else {
					renderableFC.add(new RenderableHR());
					renderableFC.add(attached);
				}
			}
		}
	}

	/**
	 * Finds in the map <b>forms</b> the renderable element attached to a form class.<br/>
	 * This function is made necessary by the fact that forms.get(dataForm) is apparently unable to
	 * find dataForm when the referenced FormClass object is from a file distinct from the workflow
	 * form's file: it returns null in those cases.
	 * 
	 * @param dataForm
	 *            the form class
	 * @return
	 */
	private RenderableFormContainer searchForForm(FormClass dataForm) {
		for (Entry<FormContainer, RenderableFormContainer> entry : forms.entrySet()) {
			FormContainer key = entry.getKey();
			if (key instanceof FormClass) {
				FormClass fcl = (FormClass) key;
				if (fcl.getId().equals(dataForm.getId())) {
					return entry.getValue();
				}
			}
		}
		return null;
	}

	/**
	 * Gets the class bean.
	 * 
	 * @param classe
	 *            the classe
	 * 
	 * @return the class bean
	 */
	public RenderableClass getClassBean(Clazz classe) {
		RenderableClass renderableClass = classes.get(classe);
		if (renderableClass == null) {
			String completeName = ModelTools.getCompleteName(classe);
			Set<Entry<Clazz, RenderableClass>> entrySet = classes.entrySet();
			for (Entry<Clazz, RenderableClass> entry : entrySet) {
				if (ModelTools.getCompleteName(entry.getKey()).equals(completeName)) {
					return entry.getValue();
				}
			}
		}
		return renderableClass;
	}

	/**
	 * Gets the renderable form.
	 * 
	 * @param form
	 *            the form
	 * 
	 * @return the renderable form
	 */
	public RenderableFormContainer getRenderableForm(FormContainer form) {
		return forms.get(form);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.bluexml.xforms.generator.DataGenerator#processEnum(com.bluexml.side
	 * .clazz.Enumeration)
	 */
	public void processEnum(Enumeration enumeration) {
		int i = 1;
		if (!enumeration.getDynamic()) {
			Element racineEnums = new Element("enums");
			Document documentEnums = new Document(racineEnums);

			EList<EnumerationLiteral> literals = enumeration.getLiterals();
			for (EnumerationLiteral enumerationLiteral : literals) {
				Element l = new Element("item");

				// order position for use with multiple selection enum fields
				Element name = new Element("id");
				name.setText(Integer.toString(i));
				i++;
				l.addContent(name);

				Element value = new Element("value");
				value.setText(enumerationLiteral.getName());
				l.addContent(value);

				racineEnums.addContent(l);
			}

			String fileName = ModelTools.getCompleteName(enumeration) + ".enum.xml";
			File file = new File(outputXForms.getAbsolutePath() + "/" + fileName);

			try {
				FileOutputStream fos = new FileOutputStream(file);
				outputter.output(documentEnums, fos);
				fos.close();
			} catch (Exception ex) {
				logger.error(ex);
				throw new RuntimeException(ex);
			}
		}
	}

	/**
	 * Sets the output folder.
	 * 
	 * @param xformsOutput
	 *            the new output folder
	 */
	public void setOutputFolder(String xformsOutput) {
		outputXForms = new File(xformsOutput);
		outputXForms.mkdirs();
	}

	/**
	 * Adds the generalizations.
	 * 
	 * @param leafClasse
	 *            the leaf classe
	 * @param classe
	 *            the classe
	 */
	private void addGeneralizations(Clazz leafClasse, Clazz classe) {
		EList<Clazz> generalizations = leafClasse.getGeneralizations();
		for (Clazz generalization : generalizations) {
			Clazz parentClasse = generalization;
			if (!classe.isAbstract()) {
				classes.get(parentClasse).addSubClass(classe, classes.get(classe));
			}
			addGeneralizations(parentClasse, classe);
		}
	}

	/**
	 * Render a FormClass or FormWorkflow.
	 * 
	 * @param outputXForms
	 *            the output x forms
	 * @param form
	 *            the form
	 * @param rFC
	 *            the form container
	 * @param isWorkflowAble
	 *            whether workflows can be started on the form
	 */
	private void render(File outputXForms, FormContainer form, RenderableFormContainer rFC,
			boolean isAWorkflowForm) {
		String formName = form.getId();
		String title = form.getLabel();
		FormTypeRendered formTypeRendered = (isAWorkflowForm) ? FormTypeRendered.formWkflw
				: FormTypeRendered.form;
		render(outputXForms, rFC, formName, title, formTypeRendered, isAWorkflowForm, false);
	}

	/**
	 * Render any renderable in a file.
	 * 
	 * @param outputXForms
	 *            the output x forms
	 * @param renderable
	 *            the renderable
	 * @param formName
	 *            the form name. Sets the name of the xhtml file template.
	 * @param classActions
	 *            the class actions. If not provided, will be built based on defaults for the form
	 *            type
	 * @param title
	 *            the title
	 * @param formType
	 *            the form type. CLASS, FORM, WKFLW, etc.
	 * @param isWorkflowAble
	 *            whether workflows can be started on the form, always false for formClass forms.
	 */
	private void render(File outputXForms, Renderable renderable, String formName, String title,
			FormTypeRendered formType, boolean isAWorkflowForm, boolean isWrkflwSelectionForm) {
		resetKeys();

		List<AbstractAction> actions;
		actions = new ArrayList<AbstractAction>();

		for (AbstractAction abstractAction : formType.getActions()) {
			actions.add(0, abstractAction);
		}

		Renderable realRenderable = renderable;
		if (formType == FormTypeRendered.formClass) {
			realRenderable = new RenderableXGroup(renderable, title);
		}

		RenderableXForm form = new RenderableXForm(realRenderable, title, actions, isAWorkflowForm,
				isWrkflwSelectionForm);
		// #976 adding the status bar
		RenderableDiv statusDiv = new RenderableDiv(MsgId.INT_CSS_STATUS_BAR_ID.getText());
		RenderableHR lineAbove = new RenderableHR();
		RenderableHR lineBelow = new RenderableHR();
		form.add(lineAbove);
		form.add(statusDiv);
		form.add(lineBelow);

		Rendered rendered = form.recursiveRender();
		try {
			StringBuffer fileName = new StringBuffer(outputXForms.getAbsolutePath());
			fileName.append(File.separatorChar);
			fileName.append(formType.getFolder());
			fileName.append(formName);
			fileName.append(formType.getSuffix());
			fileName.append(".xhtml");
			File file = new File(fileName.toString());
			file.getParentFile().mkdirs();
			Element xformsElement = rendered.getXformsElement();
			Element clonedElement = (Element) xformsElement.clone();
			Document newDocument = new Document(clonedElement);
			clonedElement.addNamespaceDeclaration(NAMESPACE_XHTML);
			clonedElement.addNamespaceDeclaration(NAMESPACE_XFORMS);
			clonedElement.addNamespaceDeclaration(NAMESPACE_EVENTS);
			FileOutputStream fos = new FileOutputStream(file);
			outputter.output(newDocument, fos);
			fos.close();
		} catch (Exception e) {
			logger.error(e);
			throw new RuntimeException(e);
		}
	}

	/**
	 * @param renderingWorkflow
	 *            the renderingWorkflow to set
	 */
	public static void setRenderingWorkflow(boolean renderingWorkflow) {
		XFormsGenerator.renderingWorkflow = renderingWorkflow;
	}

	/**
	 * @return the renderingWorkflow
	 */
	public static boolean isRenderingWorkflow() {
		return renderingWorkflow;
	}

}
