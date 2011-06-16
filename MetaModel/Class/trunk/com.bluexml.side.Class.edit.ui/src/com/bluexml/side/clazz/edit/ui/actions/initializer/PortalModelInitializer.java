package com.bluexml.side.clazz.edit.ui.actions.initializer;

import java.io.File;
import java.io.IOException;

import org.eclipse.core.resources.IFile;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.domain.EditingDomain;

import com.bluexml.side.Util.ecore.ModelInitializationUtils;
import com.bluexml.side.clazz.ClassPackage;
import com.bluexml.side.common.Visibility;
import com.bluexml.side.form.FormCollection;
import com.bluexml.side.portal.Column;
import com.bluexml.side.portal.HavePortlet;
import com.bluexml.side.portal.InternalPortletType;
import com.bluexml.side.portal.Page;
import com.bluexml.side.portal.Portal;
import com.bluexml.side.portal.PortalFactory;
import com.bluexml.side.portal.PortalLayout;
import com.bluexml.side.portal.Portlet;
import com.bluexml.side.portal.PortletInternal;
import com.bluexml.side.portal.PositionGroup;
import com.bluexml.side.util.libs.IFileHelper;
import com.bluexml.side.view.ComposedView;
import com.bluexml.side.view.ViewCollection;

public class PortalModelInitializer extends ModelAndDiagramInitializer {

	private static final String PORTAL_EDITOR_ID = ModelInitializationUtils.getExtensionForExtensionId("com.bluexml.side.portal.presentation.PortalEditorID");

	public PortalModelInitializer(IFile classModel, ClassPackage root, InitializerRegister register, ASK_USER ask) throws IOException {
		super(classModel, root, PORTAL_EDITOR_ID, "portal", "com.bluexml.side.Portal.modeler.diagram", register, ask, null);
	}

	@Override
	protected Command initialize(EditingDomain editingDomain) throws Exception {
		CompoundCommand cc = new CompoundCommand();
		Portal portal = PortalFactory.eINSTANCE.createPortal();
		portal.setName(newModelPath.lastSegment().replace(newModelExt, ""));

		// create layout
		PortalLayout layout = PortalFactory.eINSTANCE.createPortalLayout();
		layout.setName("defaultLayout");
		Column createColumn = PortalFactory.eINSTANCE.createColumn();
		createColumn.setName("defaultColumn");
		layout.getColumns().add(createColumn);

		portal.getLayoutSet().add(layout);

		// create pages
		// blog-postlist
		Page blogpostlist = createPage(layout, false, "blog-postlist", Visibility.PUBLIC);
		blogpostlist.setPosition(0);

		blogpostlist.getPortlets();
		// discussions-topiclist
		Page discussionstopiclist = createPage(layout, false, "discussions-topiclist", Visibility.PUBLIC);

		// links
		Page links = createPage(layout, false, "links", Visibility.PUBLIC);

		// wiki-page
		Page wikipage = createPage(layout, false, "wiki-page", Visibility.PUBLIC);

		// data-lists
		Page datalists = createPage(layout, false, "data-lists", Visibility.PUBLIC);

		// calendar
		Page calendar = createPage(layout, false, "calendar", Visibility.PUBLIC);

		portal.getPageSet().add(calendar);
		portal.getPageSet().add(datalists);
		portal.getPageSet().add(wikipage);
		portal.getPageSet().add(links);
		portal.getPageSet().add(discussionstopiclist);
		portal.getPageSet().add(blogpostlist);

		// document library
		createDocumentLibraryPage(portal, layout, createColumn);

		createDocumentDetailsPage(portal, layout, createColumn);

		newRootObject = portal;
		return cc;
	}

	private void createDocumentLibraryPage(Portal portal, PortalLayout layout, Column createColumn) throws Exception {
		/* documentlibrary */
		String pageId = "documentlibrary";
		int index = 0;
		String initializerIndex = "";
		create_page_internal_portlet(portal, layout, createColumn, pageId, index, initializerIndex, InternalPortletType.VIEW, Visibility.PUBLIC);
	}

	private void createDocumentDetailsPage(Portal portal, PortalLayout layout, Column createColumn) throws Exception {
		/* documentlibrary */
		String pageId = "document-details";
		int index = 1;
		String initializerIndex = "anotherFormCollection.form";
		create_page_internal_portlet(portal, layout, createColumn, pageId, index, initializerIndex, InternalPortletType.FORM, Visibility.PRIVATE);
	}

	private Page createPage(PortalLayout layout, boolean generate, String id, Visibility visibility) {
		Page p = PortalFactory.eINSTANCE.createPage();
		p.setUseLayout(layout);
		p.setID(id);
		p.setGenerate(generate);
		p.setVisibility(visibility);

		return p;
	}

	private ComposedView getFirstExtrenalComposedView(IFile model, int index) throws Exception {
		ComposedView ob = null;
		EList<EObject> viewRoot = ModelInitializationUtils.openModel(model);
		ob = ((ViewCollection) viewRoot.get(0)).getComposedViews().get(index);
		return ob;
	}

	private void create_page_internal_portlet(Portal portal, PortalLayout layout, Column createColumn, String pageId, int eobjectIndex, String initializerIndex, InternalPortletType type, Visibility visibility) throws Exception {
		Page page = createPage(layout, false, pageId, visibility);
		// set HavePortlets
		PortletInternal portletInternal = PortalFactory.eINSTANCE.createPortletInternal();
		portletInternal.setType(type);
		// get initialized View

		if (type.equals(InternalPortletType.FORM)) {
			setForm(eobjectIndex, initializerIndex, portletInternal);
		} else {
			setView(eobjectIndex, initializerIndex, portletInternal);
		}

		// set Portlet - HavePortlet
		Portlet portletView = PortalFactory.eINSTANCE.createPortlet();
		portletView.setIsPortletInternal(portletInternal);
		portletView.setName(pageId);
		HavePortlet haveProtView = PortalFactory.eINSTANCE.createHavePortlet();
		haveProtView.setAssociationPage(page);
		haveProtView.setAssociationPortlet(portletView);
		portal.getPageSet().add(page);

		PositionGroup createPositionGroup = PortalFactory.eINSTANCE.createPositionGroup();
		createPositionGroup.setOnColumn(createColumn);
		createPositionGroup.setOnLayout(layout);
		createPositionGroup.setPosition(eobjectIndex);
		haveProtView.getPositionGroup().add(createPositionGroup);
		page.getPortlets().add(haveProtView);
		portal.getInternalPortletSet().add(portletInternal);
		portal.getPortletSet().add(portletView);
	}

	private void setForm(int objectIndex, String initializerIndex, PortletInternal portletInternal) throws Exception {
		String path = register.getFormInitializer().get(initializerIndex).newModelPath.toOSString();
		IFile iFile = IFileHelper.getIFile(new File(path));
		FormCollection form = getFirstExtrenalFormCollection(iFile, objectIndex);
		portletInternal.setForm(form);
	}

	private void setView(int index, String initializerIndex, PortletInternal views) throws Exception {
		String path = register.getViewInitializer().get(initializerIndex).newModelPath.toOSString();
		IFile iFile = IFileHelper.getIFile(new File(path));
		ComposedView view = getFirstExtrenalComposedView(iFile, index);
		views.setView(view);
	}

	private FormCollection getFirstExtrenalFormCollection(IFile model, int index) throws Exception {
		FormCollection ob = null;
		EList<EObject> viewRoot = ModelInitializationUtils.openModel(model);
		ob = ((FormCollection) viewRoot.get(0));
		return ob;
	}
}
