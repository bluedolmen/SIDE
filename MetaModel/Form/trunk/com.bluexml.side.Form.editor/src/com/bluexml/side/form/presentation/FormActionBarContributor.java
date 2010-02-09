/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.bluexml.side.form.presentation;
 
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.common.ui.viewer.IViewerProvider;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.domain.IEditingDomainProvider;
import org.eclipse.emf.edit.ui.action.ControlAction;
import org.eclipse.emf.edit.ui.action.CreateChildAction;
import org.eclipse.emf.edit.ui.action.CreateSiblingAction;
import org.eclipse.emf.edit.ui.action.EditingDomainActionBarContributor;
import org.eclipse.emf.edit.ui.action.LoadResourceAction;
import org.eclipse.emf.edit.ui.action.ValidateAction;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.ActionContributionItem;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IContributionItem;
import org.eclipse.jface.action.IContributionManager;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.action.SubContributionItem;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.PartInitException;

import com.bluexml.side.clazz.Clazz;
import com.bluexml.side.form.Field;
import com.bluexml.side.form.FormClass;
import com.bluexml.side.form.FormCollection;
import com.bluexml.side.form.FormContainer;
import com.bluexml.side.form.FormElement;
import com.bluexml.side.form.FormSearch;
import com.bluexml.side.form.FormWorkflow;
import com.bluexml.side.form.ModelChoiceField;
import com.bluexml.side.form.Reference;
import com.bluexml.side.form.SearchFormCollection;
import com.bluexml.side.form.WorkflowFormCollection;
import com.bluexml.side.form.clazz.AddRefAction;
import com.bluexml.side.form.clazz.CollapseReferenceAction;
import com.bluexml.side.form.clazz.ExpandModelChoice;
import com.bluexml.side.form.clazz.InitializeFormClassAction;
import com.bluexml.side.form.clazz.ShowLinkedClassAction;
import com.bluexml.side.form.clazz.SynchonizeWithClassDiagramAction;
import com.bluexml.side.form.clazz.utils.ClassDiagramUtils;
import com.bluexml.side.form.common.CopyFormAction;
import com.bluexml.side.form.common.GroupAttributeAction;
import com.bluexml.side.form.common.RefreshOutlineAction;
import com.bluexml.side.form.common.RestoreFormElementAction;
import com.bluexml.side.form.common.TransformFieldAction;
import com.bluexml.side.form.common.utils.FieldTransformation;
import com.bluexml.side.form.common.utils.FormDiagramUtils;
import com.bluexml.side.form.search.InitializeFormSearchAction;
import com.bluexml.side.form.workflow.InitializeFormWorkflowAction;
import com.bluexml.side.form.workflow.ShowLinkedTaskAction;
import com.bluexml.side.form.workflow.SynchonizeWithWorkflowDiagramAction;

/**
 * This is the action bar contributor for the Form model editor.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class FormActionBarContributor
	extends EditingDomainActionBarContributor
	implements ISelectionChangedListener {
	/**
	 * This keeps track of the active editor.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected IEditorPart activeEditorPart;

	/**
	 * This keeps track of the current selection provider.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ISelectionProvider selectionProvider;

	/**
	 * This action opens the Properties view.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected IAction showPropertiesViewAction =
		new Action(FormsEditorPlugin.INSTANCE.getString("_UI_ShowPropertiesView_menu_item")) {
			@Override
			public void run() {
				try {
					getPage().showView("org.eclipse.ui.views.PropertySheet");
				}
				catch (PartInitException exception) {
					FormsEditorPlugin.INSTANCE.log(exception);
				}
			}
		};

	/**
	 * This action refreshes the viewer of the current editor if the editor
	 * implements {@link org.eclipse.emf.common.ui.viewer.IViewerProvider}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected IAction refreshViewerAction =
		new Action(FormsEditorPlugin.INSTANCE.getString("_UI_RefreshViewer_menu_item")) {
			@Override
			public boolean isEnabled() {
				return activeEditorPart instanceof IViewerProvider;
			}

			@Override
			public void run() {
				if (activeEditorPart instanceof IViewerProvider) {
					Viewer viewer = ((IViewerProvider)activeEditorPart).getViewer();
					if (viewer != null) {
						viewer.refresh();
					}
				}
			}
		};

	protected GroupAttributeAction groupAttributeAction = new GroupAttributeAction();
	protected InitializeFormClassAction initializeFormClassAction = new InitializeFormClassAction();
	protected InitializeFormWorkflowAction initializeFormWorkflowAction = new InitializeFormWorkflowAction();
	protected InitializeFormSearchAction initializeFormSearchAction = new InitializeFormSearchAction();
	protected ShowLinkedTaskAction showLinkedTaskAction = new ShowLinkedTaskAction();
	protected ShowLinkedClassAction showLinkedClassAction = new ShowLinkedClassAction();
	protected RefreshOutlineAction refreshOutlineAction = new RefreshOutlineAction();
	protected CollapseReferenceAction collapseReferenceAction = new CollapseReferenceAction();
	protected CopyFormAction copyFormAction = new CopyFormAction();
	protected SynchonizeWithClassDiagramAction synchronizeWithClassDiagram = new SynchonizeWithClassDiagramAction();
	protected SynchonizeWithWorkflowDiagramAction synchronizeWithWorkflowDiagram = new SynchonizeWithWorkflowDiagramAction();

	/**
	 * This will contain one {@link org.eclipse.emf.edit.ui.action.CreateChildAction} corresponding to each descriptor
	 * generated for the current selection by the item provider.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected Collection<IAction> createChildActions;

	/**
	 * This is the menu manager into which menu contribution items should be added for CreateChild actions.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected IMenuManager createChildMenuManager;

	/**
	 * This will contain one {@link org.eclipse.emf.edit.ui.action.CreateSiblingAction} corresponding to each descriptor
	 * generated for the current selection by the item provider.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected Collection<IAction> createSiblingActions;

	/**
	 * This is the menu manager into which menu contribution items should be added for CreateSibling actions.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected IMenuManager createSiblingMenuManager;

	/**
	 * This creates an instance of the contributor.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FormActionBarContributor() {
		super(ADDITIONS_LAST_STYLE);
		loadResourceAction = new LoadResourceAction();
		validateAction = new ValidateAction();
		controlAction = new ControlAction();
	}

	/**
	 * This adds Separators for editor additions to the tool bar.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void contributeToToolBar(IToolBarManager toolBarManager) {
		toolBarManager.add(new Separator("form-settings"));
		toolBarManager.add(new Separator("form-additions"));
	}

	/**
	 * This adds to the menu bar a menu and some separators for editor additions,
	 * as well as the sub-menus for object creation items.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void contributeToMenu(IMenuManager menuManager) {
		super.contributeToMenu(menuManager);

		IMenuManager submenuManager = new MenuManager(FormsEditorPlugin.INSTANCE.getString("_UI_FormEditor_menu"), "com.bluexml.side.formMenuID");
		menuManager.insertAfter("additions", submenuManager);
		submenuManager.add(new Separator("settings"));
		submenuManager.add(new Separator("actions"));
		submenuManager.add(new Separator("additions"));
		submenuManager.add(new Separator("additions-end"));

		// Prepare for CreateChild item addition or removal.
		//
		createChildMenuManager = new MenuManager(FormsEditorPlugin.INSTANCE.getString("_UI_CreateChild_menu_item"));
		submenuManager.insertBefore("additions", createChildMenuManager);

		// Prepare for CreateSibling item addition or removal.
		//
		createSiblingMenuManager = new MenuManager(FormsEditorPlugin.INSTANCE.getString("_UI_CreateSibling_menu_item"));
		submenuManager.insertBefore("additions", createSiblingMenuManager);

		// Force an update because Eclipse hides empty menus now.
		//
		submenuManager.addMenuListener
			(new IMenuListener() {
				 public void menuAboutToShow(IMenuManager menuManager) {
					 menuManager.updateAll(true);
				 }
			 });

		addGlobalActions(submenuManager);
	}

	/**
	 * When the active editor changes, this remembers the change and registers with it as a selection provider.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setActiveEditor(IEditorPart part) {
		super.setActiveEditor(part);
		activeEditorPart = part;

		// Switch to the new selection provider.
		//
		if (selectionProvider != null) {
			selectionProvider.removeSelectionChangedListener(this);
		}
		if (part == null) {
			selectionProvider = null;
		}
		else {
			selectionProvider = part.getSite().getSelectionProvider();
			selectionProvider.addSelectionChangedListener(this);

			// Fake a selection changed event to update the menus.
			//
			if (selectionProvider.getSelection() != null) {
				selectionChanged(new SelectionChangedEvent(selectionProvider, selectionProvider.getSelection()));
			}
		}
	}

	/**
	 * This implements {@link org.eclipse.jface.viewers.ISelectionChangedListener},
	 * handling {@link org.eclipse.jface.viewers.SelectionChangedEvent}s by querying for the children and siblings
	 * that can be added to the selected object and updating the menus accordingly.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void selectionChanged(SelectionChangedEvent event) {
		// Remove any menu items for old selection.
		//
		if (createChildMenuManager != null) {
			depopulateManager(createChildMenuManager, createChildActions);
		}
		if (createSiblingMenuManager != null) {
			depopulateManager(createSiblingMenuManager, createSiblingActions);
		}

		// Query the new selection for appropriate new child/sibling descriptors
		//
		Collection<?> newChildDescriptors = null;
		Collection<?> newSiblingDescriptors = null;

		ISelection selection = event.getSelection();
		if (selection instanceof IStructuredSelection && ((IStructuredSelection)selection).size() == 1) {
			Object object = ((IStructuredSelection)selection).getFirstElement();

			EditingDomain domain = ((IEditingDomainProvider)activeEditorPart).getEditingDomain();

			newChildDescriptors = domain.getNewChildDescriptors(object, null);
			newSiblingDescriptors = domain.getNewChildDescriptors(null, object);
		}

		// Generate actions for selection; populate and redraw the menus.
		//
		createChildActions = generateCreateChildActions(newChildDescriptors, selection);
		createSiblingActions = generateCreateSiblingActions(newSiblingDescriptors, selection);

		if (createChildMenuManager != null) {
			populateManager(createChildMenuManager, createChildActions, null);
			createChildMenuManager.update(true);
		}
		if (createSiblingMenuManager != null) {
			populateManager(createSiblingMenuManager, createSiblingActions, null);
			createSiblingMenuManager.update(true);
		}
	}

	/**
	 * This generates a {@link org.eclipse.emf.edit.ui.action.CreateChildAction} for each object in <code>descriptors</code>,
	 * and returns the collection of these actions.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected Collection<IAction> generateCreateChildActions(Collection<?> descriptors, ISelection selection) {
		Collection<IAction> actions = new ArrayList<IAction>();
		if (descriptors != null) {
			for (Object descriptor : descriptors) {
				actions.add(new CreateChildAction(activeEditorPart, selection, descriptor));
			}
		}
		return actions;
	}

	/**
	 * This generates a {@link org.eclipse.emf.edit.ui.action.CreateSiblingAction} for each object in <code>descriptors</code>,
	 * and returns the collection of these actions.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected Collection<IAction> generateCreateSiblingActions(Collection<?> descriptors, ISelection selection) {
		Collection<IAction> actions = new ArrayList<IAction>();
		if (descriptors != null) {
			for (Object descriptor : descriptors) {
				actions.add(new CreateSiblingAction(activeEditorPart, selection, descriptor));
			}
		}
		return actions;
	}

	/**
	 * This populates the specified <code>manager</code> with {@link org.eclipse.jface.action.ActionContributionItem}s
	 * based on the {@link org.eclipse.jface.action.IAction}s contained in the <code>actions</code> collection,
	 * by inserting them before the specified contribution item <code>contributionID</code>.
	 * If <code>contributionID</code> is <code>null</code>, they are simply added.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void populateManager(IContributionManager manager, Collection<? extends IAction> actions, String contributionID) {
		if (actions != null) {
			for (IAction action : actions) {
				if (contributionID != null) {
					manager.insertBefore(contributionID, action);
				}
				else {
					manager.add(action);
				}
			}
		}
	}

	/**
	 * This removes from the specified <code>manager</code> all {@link org.eclipse.jface.action.ActionContributionItem}s
	 * based on the {@link org.eclipse.jface.action.IAction}s contained in the <code>actions</code> collection.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void depopulateManager(IContributionManager manager, Collection<? extends IAction> actions) {
		if (actions != null) {
			IContributionItem[] items = manager.getItems();
			for (int i = 0; i < items.length; i++) {
				// Look into SubContributionItems
				//
				IContributionItem contributionItem = items[i];
				while (contributionItem instanceof SubContributionItem) {
					contributionItem = ((SubContributionItem)contributionItem).getInnerItem();
				}

				// Delete the ActionContributionItems with matching action.
				//
				if (contributionItem instanceof ActionContributionItem) {
					IAction action = ((ActionContributionItem)contributionItem).getAction();
					if (actions.contains(action)) {
						manager.remove(contributionItem);
					}
				}
			}
		}
	}

	/**
	 * This populates the pop-up menu before it appears.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void menuAboutToShow(IMenuManager menuManager) {
		super.menuAboutToShow(menuManager);
		MenuManager submenuManager = null;

		submenuManager = new MenuManager(FormsEditorPlugin.INSTANCE.getString("_UI_CreateChild_menu_item"));
		populateManager(submenuManager, createChildActions, null);
		menuManager.insertBefore("edit", submenuManager);

		submenuManager = new MenuManager(FormsEditorPlugin.INSTANCE.getString("_UI_CreateSibling_menu_item"));
		populateManager(submenuManager, createSiblingActions, null);
		menuManager.insertBefore("edit", submenuManager);
	}

	/**
	 * This inserts global actions before the "additions-end" separator.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @_generated
	 */
	@Override
	protected void addGlobalActions(IMenuManager menuManager) {
		Object o =  ((this.selectionProvider != null && this.selectionProvider.getSelection() != null )? ((TreeSelection) this.selectionProvider.getSelection()).getFirstElement() : null);

		menuManager.insertAfter("additions-end", new Separator("ui-actions"));
		menuManager.insertAfter("ui-actions", showPropertiesViewAction);

		refreshViewerAction.setEnabled(refreshViewerAction.isEnabled());
		menuManager.insertAfter("ui-actions", refreshViewerAction);

		menuManager.insertAfter("ui-actions", new Separator("ui-commonActions"));
		if (o != null) {
			// Addition of ImageDescriptor isn't available in current jface version.
			if (o instanceof ModelChoiceField) {
				MenuManager refMenu = new MenuManager("Relation","relation");
				collapseReferenceAction.setImageDescriptor(ImageDescriptor.createFromFile(this.getClass(), "/icons/menu/collapse.gif"));
				refMenu.add(collapseReferenceAction);

				// ---- Expand Menu
				IMenuManager expandMenu = new MenuManager("Expand to","expand");
				expandMenu.setRemoveAllWhenShown(true);
				if (this.selectionProvider != null && this.selectionProvider.getSelection() != null
						&& ((TreeSelection) this.selectionProvider.getSelection()).size() == 1
						&& ((TreeSelection) this.selectionProvider.getSelection()).getFirstElement() instanceof ModelChoiceField) {
					expandMenu.add(new Action("never shown entry"){});//needed if it's a submenu
				    IMenuListener expandListener = new IMenuListener() {
				        public void menuAboutToShow(IMenuManager m) {
				        	fillExpandContextMenu(m);
				        }
				     };
				    expandMenu.addMenuListener(expandListener);
				}
				refMenu.add(expandMenu);

				// ---- Add Menu
				IMenuManager addRefMenu = new MenuManager("Add reference","addRef");
				addRefMenu.setRemoveAllWhenShown(true);
				if (this.selectionProvider != null && this.selectionProvider.getSelection() != null
						&& ((TreeSelection) this.selectionProvider.getSelection()).size() == 1
						&& ((TreeSelection) this.selectionProvider.getSelection()).getFirstElement() instanceof Reference
						 ) {
					Reference ref = ((Reference)((TreeSelection) this.selectionProvider.getSelection()).getFirstElement());
					if (ref.getMax_bound() == -1 || ref.getMax_bound() > ref.getTarget().size()) {
						addRefMenu.add(new Action("never shown entry"){});//needed if it's a submenu
					    IMenuListener expandListener = new IMenuListener() {
					        public void menuAboutToShow(IMenuManager m) {
					        	fillAddRefContextMenu(m);
					        }
					     };
					     addRefMenu.addMenuListener(expandListener);
					}
				}
				refMenu.add(addRefMenu);
				menuManager.insertAfter("ui-actions",refMenu);
			}
			// ---- Transform Menu
			if (o instanceof Field) {
				IMenuManager transformMenu = new MenuManager("Transform","transform");
				transformMenu.add(new Action("never shown entry"){}); //needed if it's a submenu
				transformMenu.setRemoveAllWhenShown(true);
			    IMenuListener transformListener = new IMenuListener() {
			        public void menuAboutToShow(IMenuManager m) {
			        	fillTransformContextMenu(m);
			        }
			     };
			     transformMenu.addMenuListener(transformListener);
			     menuManager.insertAfter("ui-actions",transformMenu);
			}


			if (o instanceof FormContainer) {
				copyFormAction.setImageDescriptor(ImageDescriptor.createFromFile(this.getClass(), "/icons/menu/copy.png"));
				menuManager.insertAfter("ui-actions", copyFormAction);
			}

			if (((TreeSelection) this.selectionProvider.getSelection()).size() == 1 ||
					haveSameFormContainer((TreeSelection) this.selectionProvider.getSelection())) {
				groupAttributeAction.setImageDescriptor(ImageDescriptor.createFromFile(this.getClass(), "/icons/menu/group.png"));
				menuManager.insertAfter("ui-actions", groupAttributeAction);
			}

			if (o instanceof FormClass) {
				initializeFormClassAction.setImageDescriptor(ImageDescriptor.createFromFile(this.getClass(), "/icons/menu/initializeFromClass.png"));
				menuManager.insertAfter("ui-actions", initializeFormClassAction);
			}

			if (o instanceof WorkflowFormCollection) {
				initializeFormWorkflowAction.setImageDescriptor(ImageDescriptor.createFromFile(this.getClass(), "/icons/menu/initializeFromClass.png"));
				menuManager.insertAfter("ui-actions", initializeFormWorkflowAction);
			}
			
			if (o instanceof FormSearch) {
				initializeFormSearchAction.setImageDescriptor(ImageDescriptor.createFromFile(this.getClass(), "/icons/menu/initializeFromClass.png"));
				menuManager.insertAfter("ui-actions", initializeFormSearchAction);
			}
			
			if (o instanceof FormWorkflow) {
				showLinkedTaskAction.setImageDescriptor(ImageDescriptor.createFromFile(this.getClass(), "/icons/menu/show_link.png"));
				menuManager.insertAfter("ui-actions", showLinkedTaskAction);
			}

			if (o instanceof FormClass) {
				showLinkedClassAction.setImageDescriptor(ImageDescriptor.createFromFile(this.getClass(), "/icons/menu/show_link.png"));
				menuManager.insertAfter("ui-actions", showLinkedClassAction);
			}

			if (o instanceof FormContainer) {
				MenuManager restoreMenu = new MenuManager("Restore","restore");
				if (((FormContainer)o).getDisabled().size() > 0) {
					restoreMenu.add(new Action("never shown entry"){});
					restoreMenu.setRemoveAllWhenShown(true);
					IMenuListener restoreListener = new IMenuListener() {
				        public void menuAboutToShow(IMenuManager m) {
				        	fillRestoreContextMenu(m);
				        }
				     };
				     restoreMenu.addMenuListener(restoreListener);
				}
				menuManager.insertAfter("ui-actions",restoreMenu);
			}

			refreshOutlineAction.setImageDescriptor(ImageDescriptor.createFromFile(this.getClass(), "/icons/menu/refreshOutline.png"));
			menuManager.insertAfter("ui-actions", refreshOutlineAction);

			if (o instanceof FormCollection) {
				if (o instanceof WorkflowFormCollection) {
					synchronizeWithWorkflowDiagram.setImageDescriptor(ImageDescriptor.createFromFile(this.getClass(), "/icons/menu/synchronizeWithClassDiagram.png"));
					menuManager.insertAfter("ui-actions", synchronizeWithWorkflowDiagram);
				} else {
					synchronizeWithClassDiagram.setImageDescriptor(ImageDescriptor.createFromFile(this.getClass(), "/icons/menu/synchronizeWithClassDiagram.png"));
					menuManager.insertAfter("ui-actions", synchronizeWithClassDiagram);
				}
			}
		}

		super.addGlobalActions(menuManager);
	}

	/**
	 * Take a TreeSelection and return true if all have the same FormContainer as parent
	 * @param selection
	 * @return
	 */
	private boolean haveSameFormContainer(TreeSelection selection) {
		Object o = selection.getFirstElement();
		boolean result = true;
		FormContainer parent = null;
		if (o instanceof FormElement) {
			parent = FormDiagramUtils.getParentForm((FormElement)o);
		}
		if (parent != null) {
			for (Iterator<Object> iterator = selection.iterator(); iterator.hasNext();) {
				Object s = (Object) iterator.next();
				if (s instanceof FormElement) {
					FormContainer sParent = FormDiagramUtils.getParentForm((FormElement)s);
					if (!parent.equals(sParent)) {
						result = false;
					}
				}

			}
		}
		return result;
	}

	private void fillTransformContextMenu(IMenuManager mgr)
	{
         Object o = ((TreeSelection) this.selectionProvider.getSelection()).getFirstElement();
         if (o instanceof Field) {
			Field f = (Field) o;
			List<String> list = FieldTransformation.getAvailableTransformation(f);
			for (String fieldName : list) {
				if (fieldName != null && fieldName.length() > 0) {
					TransformFieldAction tfa = new TransformFieldAction(fieldName,f);
					tfa.setActiveWorkbenchPart(activeEditor);
					selectionProvider.addSelectionChangedListener((ISelectionChangedListener)tfa);
					mgr.add(tfa);
				}
			}
		}
	}

	private void fillRestoreContextMenu(IMenuManager mgr)
	{
         Object o = ((TreeSelection) this.selectionProvider.getSelection()).getFirstElement();
         if (o instanceof FormClass) {
        	FormClass fc = (FormClass) o;
			for (FormElement fe : fc.getDisabled()) {
				RestoreFormElementAction rfa = new RestoreFormElementAction(fe,fc);
				rfa.setActiveWorkbenchPart(activeEditor);
				selectionProvider.addSelectionChangedListener((ISelectionChangedListener)rfa);
				mgr.add(rfa);
			}
		}
	}

	protected void fillExpandContextMenu(IMenuManager mgr)
	{
         Object o = ((TreeSelection) this.selectionProvider.getSelection()).getFirstElement();
         if (o instanceof ModelChoiceField && !(o instanceof Reference)) {
        	ModelChoiceField mcf = (ModelChoiceField) o;
        	Set<Clazz> classes = ClassDiagramUtils.getDescendantClazzs(mcf.getReal_class());
			for (Clazz c : classes) {
				ExpandModelChoice emc = new ExpandModelChoice(c,mcf);
				emc.setActiveWorkbenchPart(activeEditor);
				selectionProvider.addSelectionChangedListener((ISelectionChangedListener)emc);
				mgr.add(emc);
			}
		}
	}

	protected void fillAddRefContextMenu(IMenuManager mgr)
	{
         Object o = ((TreeSelection) this.selectionProvider.getSelection()).getFirstElement();
         if (o instanceof Reference) {
        	 Reference ref = (Reference) o;
        	Set<Clazz> classes = ClassDiagramUtils.getDescendantClazzs(ref.getReal_class());
			for (Clazz c : classes) {
				AddRefAction ara = new AddRefAction(c,ref);
				ara.setActiveWorkbenchPart(activeEditor);
				selectionProvider.addSelectionChangedListener((ISelectionChangedListener)ara);
				mgr.add(ara);
			}
		}
	}

	/**
	 * This ensures that a delete action will clean up all references to deleted objects.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected boolean removeAllReferencesOnDelete() {
		return true;
	}

	@Override
	public void activate() {
		super.activate();
		groupAttributeAction.setActiveWorkbenchPart(activeEditor);
		initializeFormClassAction.setActiveWorkbenchPart(activeEditor);
		initializeFormWorkflowAction.setActiveWorkbenchPart(activeEditor);
		initializeFormSearchAction.setActiveWorkbenchPart(activeEditor);
		collapseReferenceAction.setActiveWorkbenchPart(activeEditor);
		copyFormAction.setActiveWorkbenchPart(activeEditor);
		synchronizeWithClassDiagram.setActiveWorkbenchPart(activeEditor);
		synchronizeWithWorkflowDiagram.setActiveWorkbenchPart(activeEditor);

		ISelectionProvider selectionProvider =
			       activeEditor instanceof ISelectionProvider ?
			         (ISelectionProvider)activeEditor :
			         activeEditor.getEditorSite().getSelectionProvider();
		selectionProvider.addSelectionChangedListener((ISelectionChangedListener) groupAttributeAction);
		selectionProvider.addSelectionChangedListener((ISelectionChangedListener) initializeFormClassAction);
		selectionProvider.addSelectionChangedListener((ISelectionChangedListener) initializeFormWorkflowAction);
		selectionProvider.addSelectionChangedListener((ISelectionChangedListener) initializeFormSearchAction);
		selectionProvider.addSelectionChangedListener((ISelectionChangedListener) showLinkedTaskAction);
		selectionProvider.addSelectionChangedListener((ISelectionChangedListener) showLinkedClassAction);
		selectionProvider.addSelectionChangedListener((ISelectionChangedListener) refreshOutlineAction);
		selectionProvider.addSelectionChangedListener((ISelectionChangedListener) collapseReferenceAction);
		selectionProvider.addSelectionChangedListener((ISelectionChangedListener) copyFormAction);
		selectionProvider.addSelectionChangedListener((ISelectionChangedListener) synchronizeWithClassDiagram);
		selectionProvider.addSelectionChangedListener((ISelectionChangedListener) synchronizeWithWorkflowDiagram);
	}
}
