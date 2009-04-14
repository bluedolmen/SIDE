package com.bluexml.side.Portal.modeler.diagram.dialogs.viewer;

import java.util.Arrays;

import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ComboBoxCellEditor;
import org.eclipse.jface.viewers.ICellModifier;
import org.eclipse.jface.viewers.IStructuredContentProvider;
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

import com.bluexml.modeler.dialog.DynamicComboBoxCellEditor;
import com.bluexml.side.Portal.modeler.diagram.dialogs.dataStructure.PositionDataStructure;
import com.bluexml.side.Portal.modeler.diagram.dialogs.dataStructure.PositionDataStructure.PositionObject;

public class PositionViewer {

	private static final int LAYOUT_COLUMN_WIDTH = 150;

	private static final int COLUMN_COLUMN_WIDTH = 150;
	
	private static final int HEIGHT_COLUMN_WIDTH = 100;
	
	private TableViewer tableViewer;

	private String[] columnNames = new String[] { "Layout ID", "Column ID", "Height Position" };
	
	private PositionDataStructure dataStruct;
	
	protected CellEditor[] editors;
	
	protected Table table;
	
	private int numberPortlet;
	
	private String[] nbPortlets;
	
	public PositionViewer(Composite p_parent, PositionDataStructure p_dataStruct, int p_size) {
		if (p_dataStruct != null) {
			this.dataStruct = p_dataStruct;
		} else {
			this.dataStruct = new PositionDataStructure();
		}
		
		this.numberPortlet = p_size;
		
		createTableViewer(p_parent);		
	}

	private void createTableViewer(Composite p_parent) {
		Table table = createTable(p_parent);
		tableViewer = new TableViewer(table);
		
		tableViewer.setUseHashlookup(true);
		tableViewer.setColumnProperties(columnNames);

		editors = new CellEditor[3];
		
		TextCellEditor layoutEditor = new TextCellEditor(table);
        ((Text) layoutEditor.getControl()).setTextLimit(60);  
        layoutEditor.getControl().setEnabled(false);
        editors[0] = layoutEditor; 
        // TODO : make dynamic combo cell editor for portal
        //editors[1] = new DynamicComboBoxCellEditor(table, dataStruct);
        
        getNbPortletAsArray();
        editors[2] = new ComboBoxCellEditor(table, nbPortlets, SWT.READ_ONLY);
        
        tableViewer.setCellEditors(editors);
		tableViewer.setContentProvider(new PositionContentProvider());
		tableViewer.setLabelProvider(new PositionLabelProvider());
		tableViewer.setInput(dataStruct);
		tableViewer.setCellModifier(new PositionCellModifier());
	}
	
	private String[] getNbPortletAsArray() {
		nbPortlets = new String[numberPortlet];
		for (int i=0; i<numberPortlet; i++) {
			nbPortlets[i] = Integer.toString(i + 1);
		}		
		return nbPortlets;
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
		nameColumn.setWidth(LAYOUT_COLUMN_WIDTH);

		TableColumn widthColumn = new TableColumn(table, SWT.LEFT);
		widthColumn.setText(columnNames[1]);
		widthColumn.setWidth(COLUMN_COLUMN_WIDTH);
		
		TableColumn typeColumn = new TableColumn(table, SWT.LEFT);
		typeColumn.setText(columnNames[2]);
		typeColumn.setWidth(HEIGHT_COLUMN_WIDTH);

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
	 * @return
	 */
	public PositionDataStructure getData() {
		return dataStruct;
	}
	
	
	
	
	/**
	 * Internal class to handle modification
	 */
	class PositionContentProvider implements IStructuredContentProvider {
		/**
		 * 
		 * @see org.eclipse.jface.viewers.IStructuredContentProvider#getElements(java.lang.Object)
		 */
		public Object[] getElements(Object inputElement) {
			return dataStruct.getData().toArray();
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
	class PositionLabelProvider extends LabelProvider implements ITableLabelProvider {
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
				result = dataStruct.getLayoutId(element);
				break;
			case 1:
				result = dataStruct.getColumnId(element);
				break;
			case 2:
				result = nbPortlets[dataStruct.getHeight(element)];
				break;
			default:
				break;
			}
			return result;
		}
	}
	
	/**
	 * Internal class to handle modification
	 */
	class PositionCellModifier implements ICellModifier {
		/**
		 * 
		 * @see org.eclipse.jface.viewers.ICellModifier#canModify(java.lang.Object,
		 *      java.lang.String)
		 */
		public boolean canModify(Object element, String property) {
			
			int index = Arrays.asList(columnNames).indexOf(property);
			if (index == 0) {
				return false;
			}
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
                    result = dataStruct.getLayoutId(element);
                    break;
                case 1 :
                	result = dataStruct.findColumnIndex((PositionObject)element);
                	break;
                case 2:
                	result = dataStruct.getHeight(element);
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
                	dataStruct.setLayoutId(item.getData(), (String) value);
                    break;
                case 1:
                	dataStruct.setColumnIndex(item.getData(), (Integer)value);
                    break;
                case 2 :                	
                	dataStruct.setHeight(item.getData(), ((Integer) value).intValue());
                	break;
                default:
                    break;
            }
            tableViewer.update(item.getData(), null);
		}
	}
}
