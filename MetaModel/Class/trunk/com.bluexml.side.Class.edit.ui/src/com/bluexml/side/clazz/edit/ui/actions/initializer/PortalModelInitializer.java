package com.bluexml.side.clazz.edit.ui.actions.initializer;

import java.io.IOException;

import org.apache.commons.lang.StringUtils;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IPath;
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
import com.bluexml.side.portal.InternalPortletType;
import com.bluexml.side.portal.Page;
import com.bluexml.side.portal.Portal;
import com.bluexml.side.portal.PortalFactory;
import com.bluexml.side.portal.PortalLayout;
import com.bluexml.side.portal.Portlet;
import com.bluexml.side.portal.PortletInternal;
import com.bluexml.side.portal.helper.PortalHelper;
import com.bluexml.side.util.libs.IFileHelper;
import com.bluexml.side.util.libs.ecore.EResourceUtils;
import com.bluexml.side.view.ComposedView;
import com.bluexml.side.view.ViewCollection;

public class PortalModelInitializer extends ModelAndDiagramInitializer {

	private static final String PORTAL_DIAGRAM_ID = "com.bluexml.side.Portal.modeler.diagram";
	private static final String PORTAL_EDITOR_ID = ModelInitializationUtils.getExtensionForEditorId("com.bluexml.side.portal.presentation.PortalEditorID"); //$NON-NLS-1$

	public PortalModelInitializer(IFile classModel, ClassPackage root, InitializerRegister register, ASK_USER ask) throws IOException {
		super(classModel, root, PORTAL_EDITOR_ID, "portal", PORTAL_DIAGRAM_ID, register, ask, null); //$NON-NLS-1$ //$NON-NLS-2$
		dependencies.add(FormModelInitializer.class);
		dependencies.add(ViewModelInitializer.class);
	}

	@Override
	protected void headLessInitialize() throws Exception {

		
		// create layout
		PortalLayout layout = createDefaultPortalLayout();

		Portal portal = (Portal) newRootObject;
		portal.getLayoutSet().add(layout);

		// create pages

		// blog-postlist
		Page blogpostlist = createPage(portal, layout, false, "blog-postlist", Visibility.PUBLIC); //$NON-NLS-1$
		blogpostlist.setPosition(0);

		blogpostlist.getPortlets();

		// discussions-topiclist
		createPage(portal, layout, false, "discussions-topiclist", Visibility.PUBLIC); //$NON-NLS-1$

		// links
		createPage(portal, layout, false, "links", Visibility.PUBLIC); //$NON-NLS-1$

		// wiki-page
		createPage(portal, layout, false, "wiki-page", Visibility.PUBLIC); //$NON-NLS-1$

		// data-lists
		createPage(portal, layout, false, "data-lists", Visibility.PUBLIC); //$NON-NLS-1$

		// calendar
		createPage(portal, layout, false, "calendar", Visibility.PUBLIC); //$NON-NLS-1$

		// document library
		Column column = layout.getColumns().get(0);
		createDocumentLibraryPage(portal, layout, column);
		// document details
		createDocumentDetailsPage(portal, layout, column);

		// advanced search page
		createAdvsearchPage(portal, layout, column);
	}

	private PortalLayout createDefaultPortalLayout() {
		PortalLayout layout = PortalFactory.eINSTANCE.createPortalLayout();
		layout.setName("defaultLayout"); //$NON-NLS-1$
		Column createColumn = PortalFactory.eINSTANCE.createColumn();
		createColumn.setName("defaultColumn"); //$NON-NLS-1$
		layout.getColumns().add(createColumn);
		return layout;
	}

	@Override
	protected Command initialize(EditingDomain editingDomain) throws Exception {
		CompoundCommand cc = new CompoundCommand();
		headLessInitialize();
		return cc;
	}

	private void createDocumentLibraryPage(Portal portal, PortalLayout layout, Column createColumn) throws Exception {
		String pageId = "documentlibrary"; //$NON-NLS-1$
		int index = 0;
		String initializerIndex = InitializerRegister.DEFAULT_INITIALIZER_KEY;
		create_page_docLib_internal_portlet(portal, layout, createColumn, pageId, index, initializerIndex, InternalPortletType.VIEW, Visibility.PUBLIC, null);
	}

	private void createDocumentDetailsPage(Portal portal, PortalLayout layout, Column createColumn) throws Exception {
		String pageId = "document-details"; //$NON-NLS-1$
		int index = 0;
		String initializerIndex = InitializerRegister.DEFAULT_ANOTHER_FORM_COLLECTION; //$NON-NLS-1$
		create_page_internal_portlet(portal, layout, createColumn, pageId, index, initializerIndex, InternalPortletType.FORM, Visibility.PRIVATE, null);
	}

	private void createAdvsearchPage(Portal portal, PortalLayout layout, Column createColumn) throws Exception {
		String pageId = "advsearch"; //$NON-NLS-1$
		int index = 0;
		String initializerIndex = InitializerRegister.DEFAULT_INITIALIZER_KEY; //$NON-NLS-1$
		create_page_internal_portlet(portal, layout, createColumn, pageId, index, initializerIndex, InternalPortletType.FORM, Visibility.PRIVATE, "search");
	}

	private Page createPage(Portal portal, PortalLayout layout, boolean generate, String id, Visibility visibility) {
		Page p = PortalFactory.eINSTANCE.createPage();
		p.setUseLayout(layout);
		p.setID(id);
		p.setGenerate(generate);
		p.setVisibility(visibility);
		portal.getPageSet().add(p);
		return p;
	}

	private ComposedView getFirstExtrenalComposedView(IFile model, int index) throws Exception {
		ComposedView ob = null;
		EList<EObject> viewRoot = EResourceUtils.openModel(model);
		ob = ((ViewCollection) viewRoot.get(0)).getComposedViews().get(index);
		return ob;
	}

	private Page create_page_internal_portlet(Portal portal, PortalLayout layout, Column createColumn, String pageId, int eobjectIndex, String initializerIndex, InternalPortletType type, Visibility visibility, String subtype) throws Exception {
		Page page = createPage(portal, layout, false, pageId, visibility);
		// set HavePortlets
		PortletInternal portletInternal = createPortletInternal(portal, eobjectIndex, initializerIndex, type, subtype);

		// set Portlet - HavePortlet
		createHavePortlet(portal, layout, createColumn, pageId, eobjectIndex, page, portletInternal);
		return page;
	}

	private Page create_page_docLib_internal_portlet(Portal portal, PortalLayout layout, Column createColumn, String pageId, int eobjectIndex, String initializerIndex, InternalPortletType type, Visibility visibility, String subtype) throws Exception {
		Page page = create_page_internal_portlet(portal, layout, createColumn, pageId, eobjectIndex, initializerIndex, type, visibility, subtype);

		// create internalProtlet on default form
		PortletInternal portletInternal2 = createPortletInternal(portal, eobjectIndex, initializerIndex, InternalPortletType.FORM, subtype);
		// create "uploadableTypes" portlet
		createHavePortlet(portal, layout, createColumn, "uploadableTypes", 1, page, portletInternal2);
		createHavePortlet(portal, layout, createColumn, "toolbar-create-content", 2, page, portletInternal2);
		return page;
	}

	private void createHavePortlet(Portal portal, PortalLayout layout, Column createColumn, String pageId, int eobjectIndex, Page page, PortletInternal portletInternal) {
		Portlet portletView = createPortletInstance_Internal(portal, pageId, portletInternal);
		
		PortalHelper.createHavePortlet(layout, createColumn, eobjectIndex, page, portletView);
	}

	private Portlet createPortletInstance_Internal(Portal portal, String pageId, PortletInternal portletInternal) {
		Portlet portletView = PortalFactory.eINSTANCE.createPortlet();
		portletView.setIsPortletInternal(portletInternal);
		portletView.setName(pageId);
		portal.getPortletSet().add(portletView);
		return portletView;
	}

	private PortletInternal createPortletInternal(Portal portal, int eobjectIndex, String initializerIndex, InternalPortletType type, String subType) throws Exception {
		PortletInternal portletInternal = PortalFactory.eINSTANCE.createPortletInternal();
		portletInternal.setType(type);
		portal.getInternalPortletSet().add(portletInternal);
		// get initialized View

		if (type.equals(InternalPortletType.FORM)) {
			if (StringUtils.trimToEmpty(subType).equals("search")) {
				setSearchForm(eobjectIndex, initializerIndex, portletInternal);
			} else {
				setForm(eobjectIndex, initializerIndex, portletInternal);
			}

		} else if (type.equals(InternalPortletType.VIEW)) {
			setView(eobjectIndex, initializerIndex, portletInternal);
		}
		return portletInternal;
	}

	private void setForm(int objectIndex, String initializerIndex, PortletInternal portletInternal) throws Exception {
		IPath newModelPath2 = register.getInitializers(FormModelInitializer.class).get(initializerIndex).newModelPath;
		setForm(objectIndex, portletInternal, newModelPath2);
	}

	private void setSearchForm(int objectIndex, String initializerIndex, PortletInternal portletInternal) throws Exception {
		IPath newModelPath2 = register.getInitializers(FormSearchModelInitializer.class).get(initializerIndex).newModelPath;
		setForm(objectIndex, portletInternal, newModelPath2);
	}

	private void setForm(int objectIndex, PortletInternal portletInternal, IPath newModelPath2) throws Exception {
		IFile iFile = IFileHelper.getIFile(newModelPath2);
		FormCollection form = getFirstExtrenalFormCollection(iFile, objectIndex);
		portletInternal.setForm(form);
	}

	private void setView(int index, String initializerIndex, PortletInternal views) throws Exception {
		IPath newModelPath2 = register.getInitializers(ViewModelInitializer.class).get(initializerIndex).newModelPath;
		IFile iFile = IFileHelper.getIFile(newModelPath2);
		ComposedView view = getFirstExtrenalComposedView(iFile, index);
		views.setView(view);
	}

	private FormCollection getFirstExtrenalFormCollection(IFile model, int index) throws Exception {
		FormCollection ob = null;
		EList<EObject> viewRoot = EResourceUtils.openModel(model);
		ob = ((FormCollection) viewRoot.get(0));
		return ob;
	}

	@Override
	protected void createRootObject() {
		Portal portal = PortalFactory.eINSTANCE.createPortal();
		portal.setName(newModelPath.lastSegment().replace(newModelExt, "")); //$NON-NLS-1$
		newRootObject = portal;
	}

}
