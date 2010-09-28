package com.bluexml.side.Portal.modeler.diagram.commands.update;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.gef.commands.Command;

import com.bluexml.side.Portal.modeler.diagram.dialogs.HavePortletEditDialog;
import com.bluexml.side.Portal.modeler.diagram.dialogs.dataStructure.PositionDataStructure;
import com.bluexml.side.Portal.modeler.diagram.dialogs.dataStructure.PositionDataStructure.PositionObject;
import com.bluexml.side.portal.Column;
import com.bluexml.side.portal.HavePortlet;
import com.bluexml.side.portal.PortalFactory;
import com.bluexml.side.portal.PortalLayout;
import com.bluexml.side.portal.PositionGroup;

public class HavePortletUpdateCommand extends Command {

	private HavePortlet havePortlet;

	private Map<String, Object> newHavePortletData;

	public HavePortletUpdateCommand(HavePortlet p_havePortlet, Map<String, Object> p_data) {
		this.havePortlet = p_havePortlet;
		this.newHavePortletData = p_data;
	}

	/**
	 * Get the old values and set the new ones
	 * 
	 * @see org.eclipse.gef.commands.Command#execute()
	 */
	public void execute() {
		PositionDataStructure positions = (PositionDataStructure) newHavePortletData.get(HavePortletEditDialog.HAVEPORTLET_Position);

		// Perform update for input parameters
		List<PositionGroup> newPos = new ArrayList<PositionGroup>();
		Iterator<PositionObject> iterator = positions.getData().iterator();
		while (iterator.hasNext()) {
			Object object = iterator.next();

			int heightPos = positions.getHeight(object);
			String layourId = positions.getLayoutId(object);
			String columnId = positions.getColumnId(object);

			PositionGroup posGrp = getPositionGroup(layourId);
			if (posGrp == null) {
				posGrp = PortalFactory.eINSTANCE.createPositionGroup();
				posGrp.setOnLayout(havePortlet.getAssociationPage().getUseLayout());
			}
			posGrp.setPosition(heightPos);
			posGrp.setOnColumn(getColumnFromName(posGrp.getOnLayout(), columnId));

			newPos.add(posGrp);
		}

		havePortlet.getPositionGroup().clear();
		havePortlet.getPositionGroup().addAll(newPos);
	}

	/**
	 * Return the Position Group with a name equals to layoutId
	 * 
	 * @param columnId
	 * @return
	 */
	private PositionGroup getPositionGroup(String layoutId) {
		Iterator<PositionGroup> itPosGrp = havePortlet.getPositionGroup().iterator();
		while (itPosGrp.hasNext()) {
			PositionGroup posGrp = itPosGrp.next();
			if (posGrp.getOnLayout().getName().equals(layoutId)) {
				return posGrp;
			}
		}
		return null;
	}

	/**
	 * Return the Column with a name equal to ColumnId
	 * 
	 * @param layout
	 * @param columnId
	 * @return
	 */
	private Column getColumnFromName(PortalLayout layout, String columnId) {
		Iterator<Column> itCol = layout.getColumns().iterator();
		while (itCol.hasNext()) {
			Column col = itCol.next();
			if (col.getName().equals(columnId)) {
				return col;
			}
		}
		return null;
	}
}
