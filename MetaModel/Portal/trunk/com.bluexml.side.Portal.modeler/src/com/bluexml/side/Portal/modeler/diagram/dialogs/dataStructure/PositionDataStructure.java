package com.bluexml.side.Portal.modeler.diagram.dialogs.dataStructure;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.util.EList;

import com.bluexml.side.portal.Column;
import com.bluexml.side.portal.PortalLayout;
import com.bluexml.side.portal.PositionGroup;

public class PositionDataStructure {			
	
	public class PositionObject{
		private int position;
		
		private String layoutId;
		
		private String columnId;
		
		private int columnIndex;
		
		public int getColumnIndex() {
			return columnIndex;
		}

		public void setColumnIndex(int columnIndex) {
			this.columnIndex = columnIndex;
		}

		public PositionObject(int p_pos, String p_layoutId, String p_columnId) {
			position = p_pos;
			layoutId = p_layoutId;
			columnId = p_columnId;
		}

		public int getPosition() {
			return position;
		}

		public void setPosition(int position) {
			this.position = position;
		}

		public String getLayoutId() {
			return layoutId;
		}

		public void setLayoutId(String layoutId) {
			this.layoutId = layoutId;
		}

		public String getColumnId() {
			return columnId;
		}

		public void setColumnId(String columnId) {
			this.columnId = columnId;
		}
		
		
	}

	private Map<String, EList<Column>> possibleDataMap = new HashMap<String, EList<Column>>();
	
	
	
	private List<PositionObject> listPos = new ArrayList<PositionObject>();
	
	/**
	 * Constructor
	 * @param positionGroup
	 * @param useLayout
	 */
	public PositionDataStructure(EList<PositionGroup> positionGroup,
			PortalLayout useLayout) {
		// We get all already created position
		addAll(positionGroup);		
		// We complete with other Layout
		if (useLayout != null) {
			addAllLayout(useLayout);
			buildChoices(useLayout);
		}
	}

	public PositionDataStructure() {
		
	}

	/**
	 * Add all position group
	 * @param positionGroup
	 */
	private void addAll(EList<PositionGroup> positionGroup) {		
		Iterator<PositionGroup> itPos = positionGroup.iterator();
        while (itPos.hasNext())
        {
        	PositionGroup pos = (PositionGroup) itPos.next();
            add(pos);
        }		
	}
	/**
	 * Add layout that aren't already present in listPos
	 * @param layouts
	 */
	private void addAllLayout(PortalLayout layout) {				
		if (layout != null) {
	    	String layoutName = layout.getName();
	    	String firstColumnName = "";   
	    	
	    	// If layout ID isn't in listPos we add it :
	    	if (!layoutAlreadyIn(layoutName)) {
	        	if (layout.getColumns().size() > 0 && layout.getColumns().get(0) != null) {
	        		firstColumnName = layout.getColumns().get(0).getName();
	        	} 
	        	add(0,layoutName,firstColumnName);
	    	}
		}
	}
		
	public void add(PositionGroup pos) {
		listPos.add(new PositionObject(pos.getPosition(), pos.getOnLayout().getName(), pos.getOnColumn().getName()));
	}
	
	public void add(int p_pos, String p_layoutId, String p_columnId) {
		listPos.add(new PositionObject(p_pos,p_layoutId,p_columnId));
	}
	
	/**
	 * Check if layout name is already in listPos
	 * @param p_layoutName
	 * @return
	 */
	protected boolean layoutAlreadyIn(String p_layoutName) {
		boolean isIn = false;
		if (listPos != null && listPos.size() > 0) {
			for (int i=0; i <listPos.size() && !isIn; i++) {
				if (listPos.get(i).getLayoutId().equals(p_layoutName)) {
					isIn = true;
				}
			}				
		}
		return isIn;
	}
	
	/**
	 * Return all data
	 * @return
	 */
	public List<PositionObject> getData() {
		return listPos;
	}

	
	/**
     * Get the columnId of a given object
     * 
     * @param object PositionObject object
     * @return the columnId of the PositionObject
     */
    public String getLayoutId(Object object)
    {
    	return ((PositionObject) object).getLayoutId();               
    }
    
    public void setLayoutId(Object object, String layoutId)
    {
    	((PositionObject) object).setLayoutId(layoutId);               
    }
	
	/**
     * Get the columnId of a given object
     * 
     * @param object PositionObject object
     * @return the layoutId of the PositionObject
     */
    public int getColumnIndex(Object object)
    {
    	return ((PositionObject) object).getColumnIndex();               
    }
    
    public void setColumnIndex(Object object, int columnIndex)
    {
    	if (columnIndex > -1) {
	    	PositionObject pos = (PositionObject) object;
	    	pos.setColumnIndex(columnIndex);  
	    	String columnId = this.possibleDataMap.get(pos.getLayoutId()).get(columnIndex).getName();
	    	pos.setColumnId(columnId);
    	}
    }
    
    
    public String getColumnId(Object object) {
    	return ((PositionObject) object).getColumnId();  
    }
    
    /**
     * Get the height of a given object
     * 
     * @param object PositionObject object
     * @return the position of the PositionObject
     */
    public int getHeight(Object object)
    {
    	return ((PositionObject) object).getPosition();              
    }
    
    public void setHeight(Object object, int height)
    {
    	((PositionObject) object).setPosition(height);               
    }
    
    /**
     * Return the corresponding list of columns for a portalLayout
     * @param attribute
     * @return
     */
    public String[] getColumnList(PositionObject pos) {
    	EList<Column> columns = possibleDataMap.get(pos.getLayoutId());		
		String[] colName = new String[columns.size()];
		int i = 0;
		Iterator<Column> itCol = columns.iterator();
		while (itCol.hasNext()) {
			colName[i] = itCol.next().getName();
			i++;
		}
		return colName;
	}
    
    /**
     * Initialize a map linking layout id with his columns
     * @param p_layouts
     */
    protected void buildChoices(PortalLayout layout) {
    	this.possibleDataMap.put(layout.getName(), layout.getColumns());

    }
    
    /**
     * Get the column index for the row
     * @param pos
     * @return
     */
    public Integer findColumnIndex(PositionObject pos) {
    	// We get columns for the selected layout
    	EList<Column> columns = possibleDataMap.get(pos.getLayoutId());	
    	// We get the index of this columns
    	int i = 0;
		Iterator<Column> itCol = columns.iterator();
		while (itCol.hasNext()) {
			if (itCol.next().getName().equals(pos.getColumnId())) {
				return i;
			}
			i++;
		}
    	return -1;
    }
}
