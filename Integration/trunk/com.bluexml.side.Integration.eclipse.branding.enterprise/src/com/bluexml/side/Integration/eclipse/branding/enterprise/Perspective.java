/*
    Copyright (C) 2007-20013  BlueXML - www.bluexml.com

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU Affero General Public License as
    published by the Free Software Foundation, either version 3 of the
    License, or (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU Affero General Public License for more details.

    You should have received a copy of the GNU Affero General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/
package com.bluexml.side.Integration.eclipse.branding.enterprise;

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
		addView(layout, right, "com.bluexml.view.SIDELabsHTMLView");
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
