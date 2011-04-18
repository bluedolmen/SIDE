package com.bluexml.side.Portal.modeler.diagram.dialogs;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;

import com.bluexml.side.Portal.modeler.PortalPlugin;
import com.bluexml.side.Portal.modeler.diagram.dialogs.dataStructure.PositionDataStructure;
import com.bluexml.side.Portal.modeler.diagram.dialogs.dataStructure.PositionDataStructure.PositionObject;
import com.bluexml.side.Portal.modeler.diagram.dialogs.viewer.PositionViewer;
import com.bluexml.side.portal.HavePortlet;
import com.bluexml.side.portal.Page;
import com.bluexml.side.portal.PortalLayout;
import com.bluexml.side.portal.isChildPage;

public class HavePortletEditDialog  extends Dialog implements IDialogConstants {

	private HavePortlet havePortlet;
	
	private Map<String, Object> data;
	
	private PositionViewer inputPosition;
	
	private static final int MIN_DIALOG_WIDTH = 500;

	private static final int MIN_DIALOG_HEIGHT = 300;
	
	public static final String HAVEPORTLET_Position = "havePortlet positions";
	
	public HavePortletEditDialog(HavePortlet p_havePortlet, Shell p_parentShell) {
		super(p_parentShell);
		setBlockOnOpen(true);
		setShellStyle(getShellStyle() | SWT.RESIZE);
		havePortlet = p_havePortlet;
	}
	
	public Map<String, Object> getData() {
		return data;
	}
	
	protected Control createDialogArea(Composite parent) {
		Composite dialogComposite = (Composite) super.createDialogArea(parent);
		GridData dialogLayoutData = new GridData(GridData.FILL_BOTH);
		dialogLayoutData.widthHint = MIN_DIALOG_WIDTH;
		dialogLayoutData.heightHint = MIN_DIALOG_HEIGHT;
		dialogComposite.setLayoutData(dialogLayoutData);

		createHavePortletGroup(dialogComposite);		
		
		return dialogComposite;
	}
	
	protected void createHavePortletGroup(Composite parent) {
		TabFolder tabFolder = new TabFolder(parent, SWT.TOP);
		tabFolder.setLayoutData(new GridData(GridData.FILL_BOTH));

		createGeneralTab(tabFolder);		
	}
	
	private void createGeneralTab(Composite parent) {
		// Create tab item and add it composite that fills it
		TabItem generalItem = new TabItem((TabFolder) parent, SWT.NONE);
		generalItem.setText("General");
		Composite composite = new Composite(parent, SWT.NONE);
		generalItem.setControl(composite);

		composite.setLayout(new GridLayout(2, false));
		composite.setLayoutData(new GridData(GridData.FILL_BOTH));
		
		Label propertyIdLbl = new Label(composite, SWT.NONE);
		propertyIdLbl.setText("Position : ");
		
		
		PortalLayout layout = getUsedLayout( havePortlet.getAssociationPage());
		
		PositionDataStructure dataStruct = new PositionDataStructure(havePortlet.getPositionGroup(), layout);
		
		inputPosition = new PositionViewer(composite,  dataStruct, havePortlet.getAssociationPage().getPortlets().size());				
	}

	private PortalLayout getUsedLayout(Page page) {
		if (page.getUseLayout() == null) {
			isChildPage isChildPageOf = page.getIsChildPageOf();
			if (isChildPageOf != null
					&& isChildPageOf.isInherit()) {
				return getUsedLayout(isChildPageOf.getIsChildPageOf());
			} else {
				return null;
			}
		} else {
			return page.getUseLayout();
		}
	}
	
	/**
	 * Save the values before the widgets are disposed
	 * 
	 * @see org.eclipse.jface.dialogs.Dialog#okPressed()
	 */
	protected void okPressed() {
		data = new HashMap<String, Object>();
		try {
			List<PositionObject> listPos = inputPosition.getData().getData();
			for (PositionObject positionObject : listPos) {
				if (positionObject.getColumnId().equals("") || positionObject.getLayoutId().equals("")) {
					throw new Exception("Not valide please check Page and Layout Elements ...");
				}
			}
			data.put(HAVEPORTLET_Position, inputPosition.getData());
			super.okPressed();
		} catch (Exception e) {
			// TODO change this with a validation listener that disable the ok
			// button until the widgets are valid
			PortalPlugin.log("Required fields", IStatus.WARNING);
			MessageDialog.openWarning(getShell(), "Required parameters",
							"Some parameters are not set.\nPlease, fill those fields before validating.");
		}
	}
}
