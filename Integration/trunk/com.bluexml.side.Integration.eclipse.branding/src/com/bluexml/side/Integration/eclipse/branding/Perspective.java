package com.bluexml.side.Integration.eclipse.branding;

import org.eclipse.ui.IFolderLayout;
import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;
import org.eclipse.ui.IViewLayout;

public class Perspective implements IPerspectiveFactory {

	private static final String VIEW_ID = "com.bluexml.side.Integration.eclipse.branding.intro.Perspective"; //$NON-NLS-1$


	public void createInitialLayout(IPageLayout layout) {
		defineLayout(layout);
	}

	private void defineLayout(IPageLayout layout) {
		IFolderLayout left = layout.createFolder("Left", IPageLayout.LEFT,
				0.25f, IPageLayout.ID_EDITOR_AREA);
		IFolderLayout bottom = layout.createFolder("Bottom",
				IPageLayout.BOTTOM, 0.75f, IPageLayout.ID_EDITOR_AREA);
		IFolderLayout right = layout.createFolder("Right", IPageLayout.RIGHT,
				0.75f, IPageLayout.ID_EDITOR_AREA);

		addView(layout, left, IPageLayout.ID_RES_NAV);
		addView(layout, right, IPageLayout.ID_OUTLINE);

		addView(layout, bottom, IPageLayout.ID_PROP_SHEET);
		addView(layout, bottom, IPageLayout.ID_PROBLEM_VIEW);
		addView(layout, bottom, "com.bluexml.view.OutlineHTMLView");
		addView(layout, bottom, "com.bluexml.side.Requirements.modeler.views.AnnotationView");
	}

	private void addView(IPageLayout parent, IFolderLayout folder, String viewid) {
		folder.addView(viewid);
		IViewLayout layout = parent.getViewLayout(viewid);
		if (layout != null) {
			layout.setCloseable(true);
			layout.setMoveable(true);
		}
	}
}
