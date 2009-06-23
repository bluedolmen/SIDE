/*******************************************************************************
 * 	Copyright (C) BlueXML 2005-2008
 *
 * This program is free software; you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation; either version 2.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Boston, MA 02111.
 ******************************************************************************/
package com.bluexml.side.Portal.modeler.diagram.dialogs.viewer;

import java.util.Arrays;

import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ComboBoxCellEditor;
import org.eclipse.jface.viewers.ICellModifier;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;

import com.bluexml.side.Portal.modeler.diagram.dialogs.dataStructure.ColumnDataStructure;

public class ColumnViewer {
	private static final int ID_COLUMN_WIDTH = 150;

	private static final int WIDTH_COLUMN_WIDTH = 75;
	
	private static final int TYPE_COLUMN_WIDTH = 75;

	private TableViewer tableViewer;

	private String[] columnNames = new String[] { "ID", "Width", "% / px" };	
	
	private String[] widthType;

	private ColumnDataStructure dataStructure;

	protected CellEditor[] editors;
	protected Table table;

	/**
	 * 
	 * @param parent
	 * @param dataStruct
	 * @param ty
	 * @param tyNames
	 */
	public ColumnViewer(Composite p_parent, ColumnDataStructure p_dataStructure, String[] p_widthType) {		
		if (p_dataStructure != null) {
			dataStructure = p_dataStructure;
		} else {
			dataStructure = new ColumnDataStructure();
		}
		widthType = p_widthType;
		createTableViewer(p_parent);		
	}
	

	/**
	 * 
	 * 
	 */
	public void addColumn() {		
		dataStructure.add("new_" + Integer.toString(dataStructure.getData().size()),widthType[0],0);
		tableViewer.refresh();
	}

	/**
	 * 
	 * 
	 */
	public void removeColumn() {
		dataStructure.remove(((IStructuredSelection) tableViewer.getSelection()).getFirstElement());
		tableViewer.refresh();
	}

	/**
	 * 
	 * @return
	 */
	public ColumnDataStructure getData() {
		return dataStructure;
	}

	/**
	 * 
	 * @param composite
	 * @return
	 */
	private Table createTable(Composite composite) {
		int style = SWT.SINGLE | SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL | SWT.FULL_SELECTION | SWT.HIDE_SELECTION;

		table = new Table(composite, style);
		TableColumn nameColumn = new TableColumn(table, SWT.LEFT);
		nameColumn.setText(columnNames[0]);
		nameColumn.setWidth(ID_COLUMN_WIDTH);

		TableColumn widthColumn = new TableColumn(table, SWT.LEFT);
		widthColumn.setText(columnNames[1]);
		widthColumn.setWidth(WIDTH_COLUMN_WIDTH);
		
		TableColumn typeColumn = new TableColumn(table, SWT.LEFT);
		typeColumn.setText(columnNames[2]);
		typeColumn.setWidth(TYPE_COLUMN_WIDTH);

		table.setHeaderVisible(true);
		table.setLinesVisible(true);

		GridData gridData = new GridData(GridData.FILL_BOTH);
		gridData.grabExcessVerticalSpace = true;
		gridData.horizontalSpan = 2;
		table.setLayoutData(gridData);

		return table;
	}

	/**
	 * 
	 * @param composite
	 */
	private void createTableViewer(Composite composite) {
		Table table = createTable(composite);
		tableViewer = new TableViewer(table);

		tableViewer.setUseHashlookup(true);
		tableViewer.setColumnProperties(columnNames);
		editors = new CellEditor[3];	
		
		TextCellEditor nameEditor = new TextCellEditor(table);
        ((Text) nameEditor.getControl()).setTextLimit(60);
        editors[0] = nameEditor;
		
        TextCellEditor widthEditor = new TextCellEditor(table);
        ((Text) widthEditor.getControl()).setTextLimit(3);
        editors[1] = widthEditor;    
        
        editors[2] = new ComboBoxCellEditor(table, widthType, SWT.READ_ONLY);

		tableViewer.setCellEditors(editors);
		tableViewer.setContentProvider(new ColumnContentProvider());
		tableViewer.setLabelProvider(new ColumnLabelProvider());
		tableViewer.setInput(dataStructure);
		tableViewer.setCellModifier(new ColumnCellModifier());
	}
	
	public void useColumns() {
		table.getColumn(1).setWidth(WIDTH_COLUMN_WIDTH);
		table.getColumn(1).setResizable(true);
		
		table.getColumn(2).setWidth(TYPE_COLUMN_WIDTH);
		table.getColumn(2).setResizable(true);
	}
	
	public void useAreaName() {
		table.getColumn(1).setWidth(0);
		table.getColumn(1).setResizable(false);
		
		table.getColumn(2).setWidth(0);
		table.getColumn(2).setResizable(false);
	}
	

	/**
	 * Internal class to handle modification
	 */
	class ColumnCellModifier implements ICellModifier {
		/**
		 * 
		 * @see org.eclipse.jface.viewers.ICellModifier#canModify(java.lang.Object,
		 *      java.lang.String)
		 */
		public boolean canModify(Object element, String property) {
			return true;
		}

		/**
		 * 
		 * @see org.eclipse.jface.viewers.ICellModifier#getValue(java.lang.Object,
		 *      java.lang.String)
		 */
		public Object getValue(Object element, String property) {			
			Object result = null;
            int index = Arrays.asList(columnNames).indexOf(property);
            switch (index)
            {
                case 0:
                    result = dataStructure.getName(element);
                    break;
                case 1 :
                	result = Integer.toString((Integer)dataStructure.getWidth(element));
                	break;
                case 2:
                	//result = dataStructure.getType(element);
                	String type = dataStructure.getType(element);
                	if (type != null)
                    {
                        String[] choices = widthType;
                        int i = choices.length - 1;
                        while (!type.equals(choices[i]) && i > 0)
                            --i;
                        result = new Integer(i);
                    }
                    break;
                default:
                    break;
            }
          
			return result;
		}

		/**
		 * 
		 * @see org.eclipse.jface.viewers.ICellModifier#modify(java.lang.Object,
		 *      java.lang.String, java.lang.Object)
		 */
		public void modify(Object element, String property, Object value) {
			TableItem item = (TableItem) element;
            int index = Arrays.asList(columnNames).indexOf(property);
            switch (index)
            {
                case 0:
                    dataStructure.setName(item.getData(), (String) value);
                    break;
                case 1:
                	dataStructure.setWidth(item.getData(), Integer.parseInt((String)value));
                    break;
                case 2 :                	
                	dataStructure.setType(item.getData(), widthType[((Integer) value).intValue()]);
                default:
                    break;
            }
            tableViewer.update(item.getData(), null);
		}
	}

	/**
	 * Internal class to handle modification
	 */
	class ColumnContentProvider implements IStructuredContentProvider {
		/**
		 * 
		 * @see org.eclipse.jface.viewers.IStructuredContentProvider#getElements(java.lang.Object)
		 */
		public Object[] getElements(Object inputElement) {
			return dataStructure.getData().toArray();
		}

		/**
		 * 
		 * @see org.eclipse.jface.viewers.IContentProvider#dispose()
		 */
		public void dispose() {
			// nothing to do
		}

		/**
		 * 
		 * @see org.eclipse.jface.viewers.IContentProvider#inputChanged(org.eclipse.jface.viewers.Viewer,
		 *      java.lang.Object, java.lang.Object)
		 */
		public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
			// nothing to do
		}
	}

	/**
	 * Internal class to handle modification
	 */
	class ColumnLabelProvider extends LabelProvider implements ITableLabelProvider {
		/**
		 * @see org.eclipse.jface.viewers.ITableLabelProvider#getColumnImage(java.lang.Object,
		 *      int)
		 */
		public Image getColumnImage(Object element, int columnIndex) {
			return null;
		}

		/**
		 * @see org.eclipse.jface.viewers.ITableLabelProvider#getColumnText(java.lang.Object,
		 *      int)
		 */
		public String getColumnText(Object element, int columnIndex) {
			String result = "";
			switch (columnIndex) {
			case 0:
				result = dataStructure.getName(element);
				break;
			case 1:
				result = Integer.toString(dataStructure.getWidth(element));
				break;
			case 2:
				result = dataStructure.getType(element);
				break;
			default:
				break;
			}
			return result;
		}
	}

	
}
