/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.bluexml.side.view.presentation;
 
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.ui.viewer.IViewerProvider;
import org.eclipse.emf.common.util.EList;
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

import com.bluexml.side.clazz.Association;
import com.bluexml.side.clazz.Attribute;
import com.bluexml.side.clazz.Clazz;
import com.bluexml.side.view.AbstractView;
import com.bluexml.side.view.AbstractViewOf;
import com.bluexml.side.view.Col;
import com.bluexml.side.view.Field;
import com.bluexml.side.view.FieldElement;
import com.bluexml.side.view.ViewCollection;
import com.bluexml.side.view.edit.ui.actions.AddLinkedFieldAction;
import com.bluexml.side.view.edit.ui.actions.CopyColConfAction;
import com.bluexml.side.view.edit.ui.actions.InitializeView;
import com.bluexml.side.view.edit.ui.actions.MergeCols;
import com.bluexml.side.view.edit.ui.actions.PasteColConfAction;
import com.bluexml.side.view.edit.ui.actions.RefreshOutline;
import com.bluexml.side.view.edit.ui.actions.RestoreFieldAction;
import com.bluexml.side.view.edit.ui.actions.ShowLinkedClassAction;
import com.bluexml.side.view.edit.ui.actions.SynchronizeViews;
import com.bluexml.side.view.edit.ui.actions.TransformField;
import com.bluexml.side.view.edit.ui.utils.FieldTransformation;

/**
 * This is the action bar contributor for the View model editor. <!--
 * begin-user-doc --> <!-- end-user-doc -->
 *
 * @generated
 */
public class ViewActionBarContributor extends EditingDomainActionBarContributor
		implements ISelectionChangedListener {

	protected InitializeView initializeView = new InitializeView();
	protected MergeCols mergeCol = new MergeCols();
	protected SynchronizeViews synchronizeView = new SynchronizeViews();
	protected RefreshOutline refreshOutline = new RefreshOutline();
	protected CopyColConfAction copyColConfAction = new CopyColConfAction();
	protected PasteColConfAction pasteColConfAction = new PasteColConfAction();
	protected ShowLinkedClassAction showLinkedClassAction = new ShowLinkedClassAction();


	/**
	 * This keeps track of the active editor.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @generated
	 */
	protected IEditorPart activeEditorPart;

	/**
	 * This keeps track of the current selection provider.
	 * <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * @generated
	 */
	protected ISelectionProvider selectionProvider;

	/**
	 * This action opens the Properties view.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @generated
	 */
	protected IAction showPropertiesViewAction = new Action(ViewEditorPlugin.INSTANCE.getString("_UI_ShowPropertiesView_menu_item")) {
			@Override
			public void run() {
				try {
					getPage().showView("org.eclipse.ui.views.PropertySheet");
				}
				catch (PartInitException exception) {
					ViewEditorPlugin.INSTANCE.log(exception);
				}
			}
		};

	/**
	 * This action refreshes the viewer of the current editor if the editor
	 * implements {@link org.eclipse.emf.common.ui.viewer.IViewerProvider}. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 *
	 * @generated
	 */
	protected IAction refreshViewerAction = new Action(ViewEditorPlugin.INSTANCE.getString("_UI_RefreshViewer_menu_item")) {
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

	/**
	 * This will contain one {@link org.eclipse.emf.edit.ui.action.CreateChildAction} corresponding to each descriptor
	 * generated for the current selection by the item provider.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	protected Collection<IAction> createChildActions;

	/**
	 * This is the menu manager into which menu contribution items should be
	 * added for CreateChild actions. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 *
	 * @generated
	 */
	protected IMenuManager createChildMenuManager;

	/**
	 * This will contain one {@link org.eclipse.emf.edit.ui.action.CreateSiblingAction} corresponding to each descriptor
	 * generated for the current selection by the item provider.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	protected Collection<IAction> createSiblingActions;

	/**
	 * This is the menu manager into which menu contribution items should be added for CreateSibling actions.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @generated
	 */
	protected IMenuManager createSiblingMenuManager;

	/**
	 * This creates an instance of the contributor.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @generated
	 */
	public ViewActionBarContributor() {
		super(ADDITIONS_LAST_STYLE);
		loadResourceAction = new LoadResourceAction();
		validateAction = new ValidateAction();
		controlAction = new ControlAction();
	}

	/**
	 * This adds Separators for editor additions to the tool bar. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 *
	 * @generated
	 */
	@Override
	public void contributeToToolBar(IToolBarManager toolBarManager) {
		toolBarManager.add(new Separator("view-settings"));
		toolBarManager.add(new Separator("view-additions"));
	}

	/**
	 * This adds to the menu bar a menu and some separators for editor
	 * additions, as well as the sub-menus for object creation items. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 *
	 * @generated
	 */
	@Override
	public void contributeToMenu(IMenuManager menuManager) {
		super.contributeToMenu(menuManager);

		IMenuManager submenuManager = new MenuManager(ViewEditorPlugin.INSTANCE.getString("_UI_ViewEditor_menu"), "com.bluexml.side.viewMenuID");
		menuManager.insertAfter("additions", submenuManager);
		submenuManager.add(new Separator("settings"));
		submenuManager.add(new Separator("actions"));
		submenuManager.add(new Separator("additions"));
		submenuManager.add(new Separator("additions-end"));

		// Prepare for CreateChild item addition or removal.
		//
		createChildMenuManager = new MenuManager(ViewEditorPlugin.INSTANCE.getString("_UI_CreateChild_menu_item"));
		submenuManager.insertBefore("additions", createChildMenuManager);

		// Prepare for CreateSibling item addition or removal.
		//
		createSiblingMenuManager = new MenuManager(ViewEditorPlugin.INSTANCE.getString("_UI_CreateSibling_menu_item"));
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
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
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
	 * This implements
	 * {@link org.eclipse.jface.viewers.ISelectionChangedListener}, handling
	 * {@link org.eclipse.jface.viewers.SelectionChangedEvent}s by querying for
	 * the children and siblings that can be added to the selected object and
	 * updating the menus accordingly. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 *
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
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	protected Collection<IAction> generateCreateChildActions(
			Collection<?> descriptors, ISelection selection) {
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
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	protected Collection<IAction> generateCreateSiblingActions(
			Collection<?> descriptors, ISelection selection) {
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
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	protected void populateManager(IContributionManager manager,
			Collection<? extends IAction> actions, String contributionID) {
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
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @generated
	 */
	protected void depopulateManager(IContributionManager manager,
			Collection<? extends IAction> actions) {
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

		submenuManager = new MenuManager(ViewEditorPlugin.INSTANCE.getString("_UI_CreateChild_menu_item"));
		populateManager(submenuManager, createChildActions, null);
		menuManager.insertBefore("edit", submenuManager);

		submenuManager = new MenuManager(ViewEditorPlugin.INSTANCE.getString("_UI_CreateSibling_menu_item"));
		populateManager(submenuManager, createSiblingActions, null);
		menuManager.insertBefore("edit", submenuManager);
	}

	/**
	 * This ensures that a delete action will clean up all references to deleted objects.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected boolean removeAllReferencesOnDelete() {
		return true;
	}

	/**
	 * This inserts global actions before the "additions-end" separator. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 *
	 * @_generated
	 */
	@Override
	protected void addGlobalActions(IMenuManager menuManager) {
		Object o = ((this.selectionProvider != null && this.selectionProvider
				.getSelection() != null) ? ((TreeSelection) this.selectionProvider
				.getSelection()).getFirstElement()
				: null);

		menuManager.insertAfter("additions-end", new Separator("ui-actions"));
		menuManager.insertAfter("ui-actions", showPropertiesViewAction);

		refreshViewerAction.setEnabled(refreshViewerAction.isEnabled());
		menuManager.insertAfter("ui-actions", refreshViewerAction);

		menuManager
				.insertAfter("ui-actions", new Separator("ui-commonActions"));
		if (o instanceof AbstractView) {
			addActionsForViews(menuManager, o);
			showLinkedClassAction.setImageDescriptor(ImageDescriptor.createFromFile(this
					.getClass(), "/icons/menu/show_link.png"));
			menuManager.insertAfter("ui-actions", showLinkedClassAction);
		}

		if (o instanceof Field) {
			addActionsForFields(menuManager);
		}


		if (o instanceof Col && !(o instanceof AbstractView)) {
			addActionsForCols(menuManager);
		}

		if (o instanceof ViewCollection) {
			addActionsForCollections(menuManager);
		}

		super.addGlobalActions(menuManager);
	}

	private void addActionsForCollections(IMenuManager menuManager) {
		// Synchronize
		synchronizeView.setImageDescriptor(ImageDescriptor.createFromFile(this
				.getClass(), "/icons/menu/synchronize.png"));
		menuManager.insertAfter("ui-actions", synchronizeView);
	}

	/**
	 * Add actions specific to cols
	 * @param menuManager
	 * @param o
	 */
	private void addActionsForCols(IMenuManager menuManager) {
		// Merge cols :
		TreeSelection selection = ((TreeSelection) this.selectionProvider
				.getSelection());
		// Check if there is more than one col selected
		if (selection.size() > 0) {
			// Check if all selected objects are cols
			boolean allCol = true;
			for (Object s : selection.toList()) {
				if (!(s instanceof Col)) {
					allCol = false;
				}
			}
			if (allCol) {
				mergeCol.setImageDescriptor(ImageDescriptor.createFromFile(this
						.getClass(), "/icons/menu/merge.gif"));
				menuManager.insertAfter("ui-actions", mergeCol);
			}
		}
		// Paste configuration
		pasteColConfAction.setImageDescriptor(ImageDescriptor.createFromFile(this
				.getClass(), "/icons/menu/pasteColConf.gif"));
		menuManager.insertAfter("ui-actions", pasteColConfAction);

		// Copy configuration
		copyColConfAction.setImageDescriptor(ImageDescriptor.createFromFile(this
				.getClass(), "/icons/menu/copyColConf.png"));
		menuManager.insertAfter("ui-actions", copyColConfAction);
	}

	/**
	 * Add actions specific to fields
	 * @param menuManager
	 * @param o
	 */
	private void addActionsForFields(IMenuManager menuManager) {
		IMenuManager transformMenu = new MenuManager("Transform to",
				"addLinked");
		transformMenu.add(new Action("never shown entry") {
		});
		transformMenu.setRemoveAllWhenShown(true);
		IMenuListener transformListener = new IMenuListener() {
			public void menuAboutToShow(IMenuManager m) {
				fillTransformContextMenu(m);
			}
		};
		transformMenu.addMenuListener(transformListener);
		menuManager.insertAfter("ui-actions", transformMenu);
	}

	/**
	 * Add actions specific to views
	 * @param menuManager
	 * @param o
	 */
	private void addActionsForViews(IMenuManager menuManager, Object o) {
		// Initialize
		initializeView.setImageDescriptor(ImageDescriptor.createFromFile(this
				.getClass(), "/icons/menu/initialize.png"));
		menuManager.insertAfter("ui-actions", initializeView);

		// Refresh Outline
		refreshOutline.setImageDescriptor(ImageDescriptor.createFromFile(this
				.getClass(), "/icons/menu/refreshOutline.png"));
		menuManager.insertAfter("ui-actions", refreshOutline);

		// Restore
		MenuManager restoreMenu = new MenuManager("Restore", ImageDescriptor
				.createFromFile(this.getClass(), "/icons/menu/restore.png"),
				"restore");
		if (((AbstractView) o).getDisabled().size() > 0) {
			restoreMenu.add(new Action("never shown entry") {
			});
			restoreMenu.setRemoveAllWhenShown(true);
			IMenuListener restoreListener = new IMenuListener() {
				public void menuAboutToShow(IMenuManager m) {
					fillRestoreContextMenu(m);
				}
			};
			restoreMenu.addMenuListener(restoreListener);
		}
		menuManager.insertAfter("ui-actions", restoreMenu);

		// Add linked field
		final IMenuManager addLinkedFieldMenu = new MenuManager(
				"Add linked Field", ImageDescriptor.createFromFile(this
						.getClass(), "/icons/menu/addLinkedField.png"),
				"transform");
		addLinkedFieldMenu.add(new Action("never shown entry") {
		});
		addLinkedFieldMenu.setRemoveAllWhenShown(true);
		IMenuListener addLinkedFieldListener = new IMenuListener() {
			public void menuAboutToShow(IMenuManager m) {
				fillAddLinkedMenu(m, addLinkedFieldMenu);
			}
		};
		addLinkedFieldMenu.addMenuListener(addLinkedFieldListener);
		menuManager.insertAfter("ui-actions", addLinkedFieldMenu);
	}

	/**
	 * Get the selected element of the menu and will show sub element
	 *
	 * @param mgr
	 * @param topMenu
	 */
	protected void fillAddLinkedMenu(IMenuManager mgr, IMenuManager topMenu) {
		Object o = ((TreeSelection) this.selectionProvider.getSelection())
				.getFirstElement();
		if (o instanceof AbstractViewOf) {
			AbstractViewOf av = (AbstractViewOf) o;
			if (av.getViewOf() != null && av.getViewOf() instanceof Clazz) {
				Clazz c = (Clazz) av.getViewOf();
				List<Association> path = new ArrayList<Association>();
				addLinkedFieldAssociation(topMenu, c, path, av);
			}
		}
	}

	/**
	 * Add a subMenu for Association
	 *
	 * @param topMenu
	 * @param c
	 * @param av
	 */
	private void addLinkedFieldAssociation(IMenuManager topMenu, final Clazz c,
			final List<Association> path, final AbstractViewOf av) {
		for (final Association a : c.getSourceAssociations()) {
			if (!path.contains(a)) {
				IMenuManager addLinkedFieldMenu = new MenuManager(a.getLabel(),
						"browse" + a.getName());
				addLinkedFieldMenu.add(new Action("never shown entry") {
				});
				addLinkedFieldMenu.setRemoveAllWhenShown(true);
				IMenuListener addLinkedFieldListener = new IMenuListener() {
					public void menuAboutToShow(IMenuManager m) {
						fillAddLinkedSubMenu(m, a, path, av, c);
					}
				};
				addLinkedFieldMenu.addMenuListener(addLinkedFieldListener);
				topMenu.add(addLinkedFieldMenu);
			}
		}

		for (final Clazz inheritedClass : c.getInheritedClasses()) {
			for (final Association a : inheritedClass.getSourceAssociations()) {
				if (!path.contains(a)) {
					IMenuManager addLinkedFieldMenu = new MenuManager(a.getLabel(),
							"browse" + a.getName());
					addLinkedFieldMenu.add(new Action("never shown entry") {
					});
					addLinkedFieldMenu.setRemoveAllWhenShown(true);
					IMenuListener addLinkedFieldListener = new IMenuListener() {
						public void menuAboutToShow(IMenuManager m) {
							fillAddLinkedSubMenu(m, a, path, av, inheritedClass);
						}
					};
					addLinkedFieldMenu.addMenuListener(addLinkedFieldListener);
					topMenu.add(addLinkedFieldMenu);
				}
			}
		}
	}

	/**
	 * Add attributes for sub menu
	 *
	 * @param mgr
	 * @param a
	 * @param av
	 * @param c
	 */
	protected void fillAddLinkedSubMenu(IMenuManager mgr, Association a,
			List<Association> p, AbstractViewOf av, Clazz c) {
		List<Association> path = new ArrayList<Association>(p);
		path.add(a);

		if (av.getViewOf() != null) {
			EList<Clazz> targets = a.getTarget();
			if (targets.size() > 0) {
				//c = targets.get(0);
				if (a.getAssociationEnd(c).get(0).getLinkedClass().equals(c)) {
					if (a.getAssociationEnd(c).get(0).getOpposite().getLinkedClass() instanceof Clazz) {
						c = (Clazz) a.getAssociationEnd(c).get(0).getOpposite().getLinkedClass();
					}
				} else {
					if (a.getAssociationEnd(c).get(0).getLinkedClass() instanceof Clazz) {
						c = (Clazz) a.getAssociationEnd(c).get(0).getLinkedClass();
					}
				}
				for (Attribute att : c.getAllAttributes()) {
					AddLinkedFieldAction alfa = new AddLinkedFieldAction(att, path,
							av);
					alfa.setActiveWorkbenchPart(activeEditor);
					selectionProvider
							.addSelectionChangedListener((ISelectionChangedListener) alfa);
					mgr.add(alfa);
				}
				addLinkedFieldAssociation(mgr, c, path, av);
			}
		}
	}

	/**
	 * Show deleted field that can be restored
	 *
	 * @param mgr
	 */
	private void fillRestoreContextMenu(IMenuManager mgr) {
		Object o = ((TreeSelection) this.selectionProvider.getSelection())
				.getFirstElement();
		if (o instanceof AbstractView) {
			AbstractView av = (AbstractView) o;
			for (FieldElement fe : av.getDisabled()) {
				RestoreFieldAction rfa = new RestoreFieldAction(fe, av);
				rfa.setActiveWorkbenchPart(activeEditor);
				selectionProvider
						.addSelectionChangedListener((ISelectionChangedListener) rfa);
				mgr.add(rfa);
			}
		}
	}

	private void fillTransformContextMenu(IMenuManager mgr) {
		Object o = ((TreeSelection) this.selectionProvider.getSelection())
				.getFirstElement();
		if (o instanceof Field) {
			Field f = (Field) o;
			List<String> list = FieldTransformation
					.getAvailableTransformation(f);
			for (String fieldName : list) {
				if (fieldName != null && fieldName.length() > 0) {
					TransformField tfa = new TransformField(fieldName, f);
					tfa.setActiveWorkbenchPart(activeEditor);
					selectionProvider
							.addSelectionChangedListener((ISelectionChangedListener) tfa);
					mgr.add(tfa);
				}
			}
		}
	}

	@Override
	public void activate() {
		super.activate();
		initializeView.setActiveWorkbenchPart(activeEditor);
		mergeCol.setActiveWorkbenchPart(activeEditor);
		synchronizeView.setActiveWorkbenchPart(activeEditor);
		copyColConfAction.setActiveWorkbenchPart(activeEditor);
		pasteColConfAction.setActiveWorkbenchPart(activeEditor);

		ISelectionProvider selectionProvider = activeEditor instanceof ISelectionProvider ? (ISelectionProvider) activeEditor
				: activeEditor.getEditorSite().getSelectionProvider();
		selectionProvider
				.addSelectionChangedListener((ISelectionChangedListener) initializeView);
		selectionProvider
				.addSelectionChangedListener((ISelectionChangedListener) mergeCol);
		selectionProvider
				.addSelectionChangedListener((ISelectionChangedListener) synchronizeView);
		selectionProvider
				.addSelectionChangedListener((ISelectionChangedListener) refreshOutline);
		selectionProvider
		.addSelectionChangedListener((ISelectionChangedListener) copyColConfAction);
		selectionProvider
		.addSelectionChangedListener((ISelectionChangedListener) pasteColConfAction);
		selectionProvider
		.addSelectionChangedListener((ISelectionChangedListener) showLinkedClassAction);

	}
}