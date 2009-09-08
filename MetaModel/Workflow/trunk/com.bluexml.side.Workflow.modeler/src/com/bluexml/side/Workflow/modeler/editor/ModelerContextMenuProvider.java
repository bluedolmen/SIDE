package com.bluexml.side.Workflow.modeler.editor;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.Platform;
import org.eclipse.gef.ContextMenuProvider;
import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.ui.actions.ActionRegistry;
import org.eclipse.gef.ui.actions.GEFActionConstants;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IContributionItem;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.resource.ImageDescriptor;

import com.bluexml.side.Workflow.modeler.WorkflowPlugin;
import com.bluexml.side.Workflow.modeler.actions.popup.AddAction;
import com.bluexml.side.Workflow.modeler.actions.popup.ShowFormAction;

public class ModelerContextMenuProvider extends ContextMenuProvider {

	private static final String EXTENSIONPOINT_ID = "com.bluexml.side.Workflow.modeler.action";
	private ActionRegistry actionRegistry;
	private WorkflowEditor editor;
	private IMenuManager actionMenu;

	public ModelerContextMenuProvider(EditPartViewer viewer) {
		super(viewer);
	}

	public ModelerContextMenuProvider(WorkflowEditor workflowEditor,
			EditPartViewer viewer, ActionRegistry registry) {
		super(viewer);
		setActionRegistry(registry);
		editor = workflowEditor;
	}

	@Override
	public void buildContextMenu(IMenuManager manager) {
		org.topcased.modeler.editor.ModelerContextMenuProvider p = new org.topcased.modeler.editor.ModelerContextMenuProvider(
				getViewer(), actionRegistry);
		p.buildContextMenu(manager);

		IAction action;

		action = actionRegistry.getAction(ShowFormAction.ID);
		if (action.isEnabled())
			manager.appendToGroup(GEFActionConstants.GROUP_VIEW, action);

		manager.appendToGroup(GEFActionConstants.GROUP_EDIT, buildActionMenu());

	}

	/**
	 * Set the ActionRegistry for this ContextMenuProvider
	 * 
	 * @param registry
	 *            the ActionRegistry
	 */
	protected void setActionRegistry(ActionRegistry registry) {
		actionRegistry = registry;
	}

	protected IMenuManager buildActionMenu() {
    	IMenuManager submenuAddAction = new MenuManager(WorkflowPlugin.Messages.getString("AddAction.1"));
    	Map<String,Set<IAction>> actions = new HashMap<String, Set<IAction>>();
    	
    	IConfigurationElement[] contributions = Platform.getExtensionRegistry().getConfigurationElementsFor(EXTENSIONPOINT_ID);
		for (IConfigurationElement configurationElement : contributions) {
			String name = configurationElement.getAttribute("name");
			String code = configurationElement.getAttribute("code");
			String icon = configurationElement.getAttribute("icon");
			String technology = configurationElement.getAttribute("technology");
			
			String codeValue = "";
			try {
				InputStream fis = editor.getClass().getClassLoader().getResourceAsStream(code);
				BufferedInputStream bis = new BufferedInputStream(fis);
				DataInputStream dis = new DataInputStream(bis);
				while (dis.available() != 0)
					codeValue += dis.readLine() + "\r\n";
				fis.close();
				bis.close();
				dis.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			
			ImageDescriptor iconDescriptor = null;
			if (icon != null && icon.length() > 0) {
				URL url = editor.getClass().getClassLoader().getResource(icon);
				iconDescriptor = ImageDescriptor.createFromURL(url);
			}
			
			AddAction action = new AddAction(editor, name, codeValue, iconDescriptor);
			actionRegistry.registerAction(action);
			if (!actions.containsKey(technology))
				actions.put(technology, new HashSet<IAction>());
			actions.get(technology).add(action);
		}
		
		for (String technology : actions.keySet()) {
			submenuAddAction.add(buildTechnologyMenu(technology, actions.get(technology)));
		}
    	
    	return submenuAddAction;
    }

	private IMenuManager buildTechnologyMenu(String technology, Set<IAction> set) {
		IMenuManager submenu = new MenuManager(technology);
		for (IAction action : set) {
			submenu.add(action);
		}
		return submenu;
	}
}
