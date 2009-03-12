package com.bluexml.side.Portal.modeler.diagram.dialogs.viewer;

import java.util.Arrays;

import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.CheckboxCellEditor;
import org.eclipse.jface.viewers.ComboBoxCellEditor;
import org.eclipse.jface.viewers.ICellModifier;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;

import com.bluexml.side.Portal.modeler.diagram.dialogs.dataStructure.PortletTypeAttributeDataStructure;
import com.bluexml.side.Portal.modeler.diagram.dialogs.dataStructure.PortletTypeAttributeDataStructure.PortletTypeAttributeObject;
import com.bluexml.side.Portal.modeler.diagram.dialogs.viewer.labelProvider.AttributeLabelProvider;


public class PortletTypeAttributeViewer {

	private static final int NAME_WIDTH = 120;

	private static final int TYPE_WIDTH = 100;
	
	private static final int REQUIRED_WIDTH = 75;
	
	private static final int GROUP_WIDTH = 100;
	
	private PortletTypeAttributeDataStructure dataStruct;
	
	private TableViewer tableViewer;
	
	protected CellEditor[] editorsAttributes;	
	
	private String[] columnAttributesNames = new String[] { "Name", "Type", "Required", "MultiValued" };
	
	protected Table attributeTable;
	
	protected String[] arrType;
	
	public PortletTypeAttributeViewer(Composite p_parent, 
			PortletTypeAttributeDataStructure p_dataStruct, String[] p_arrType) {
		if (p_dataStruct != null) {
			this.dataStruct = p_dataStruct;
						
			
		} 	
		arrType = p_arrType;
		
			
		
		createTablesViewer(p_parent);	
		
	}
	
	private void createTablesViewer(Composite p_parent) {
		
		createAttributeTableViewer(p_parent);
		
	}
	
	private void createAttributeTableViewer(Composite p_parent) {
		Table table = createAttributeTable(p_parent);
		tableViewer = new TableViewer(table);
		tableViewer.setUseHashlookup(true);
		tableViewer.setColumnProperties(columnAttributesNames);
		editorsAttributes = new CellEditor[4];
		
		TextCellEditor nameEditor = new TextCellEditor(table);
        ((Text) nameEditor.getControl()).setTextLimit(60);          
        editorsAttributes[0] = nameEditor;                   
        editorsAttributes[1] = new ComboBoxCellEditor(table, arrType, SWT.READ_ONLY);		               
        editorsAttributes[2] = new CheckboxCellEditor(table, SWT.CENTER);          
        editorsAttributes[3] = new CheckboxCellEditor(table, SWT.CENTER);   
       
        tableViewer.setCellEditors(editorsAttributes);
		tableViewer.setContentProvider(new AttributeContentProvider());
		tableViewer.setLabelProvider(new AttributeLabelProvider());
		tableViewer.setInput(dataStruct);		
		tableViewer.setCellModifier(new AttributeCellModifier());
	}			

	private Table createAttributeTable(Composite composite) {
		int style = SWT.SINGLE | SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL | SWT.FULL_SELECTION | SWT.HIDE_SELECTION;

		attributeTable = new Table(composite, style);
		TableColumn nameColumn = new TableColumn(attributeTable, SWT.LEFT);
		nameColumn.setText(columnAttributesNames[0]);
		nameColumn.setWidth(NAME_WIDTH);

		TableColumn typeColumn = new TableColumn(attributeTable, SWT.LEFT);
		typeColumn.setText(columnAttributesNames[1]);
		typeColumn.setWidth(TYPE_WIDTH);
		
		TableColumn requiredColumn = new TableColumn(attributeTable, SWT.LEFT);
		requiredColumn.setText(columnAttributesNames[2]);
		requiredColumn.setWidth(REQUIRED_WIDTH);
		
		TableColumn groupColumn = new TableColumn(attributeTable, SWT.LEFT);
		groupColumn.setText(columnAttributesNames[3]);
		groupColumn.setWidth(GROUP_WIDTH);
		 
		attributeTable.setHeaderVisible(true);
		attributeTable.setLinesVisible(true);

		GridData gridData = new GridData(GridData.FILL_BOTH);
		gridData.grabExcessVerticalSpace = true;
		gridData.horizontalSpan = 2;
		attributeTable.setLayoutData(gridData);

		return attributeTable;
	}
	
	
	/**
	 * Internal class to handle modification
	 */
	class AttributeContentProvider implements IStructuredContentProvider {
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
	class AttributeCellModifier implements ICellModifier {
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
            int index = Arrays.asList(columnAttributesNames).indexOf(property);
            switch (index)
            {
                case 0:
                    result = ((PortletTypeAttributeObject) element).getName();;
                    break;
                case 1 :
                	result = ((PortletTypeAttributeObject) element).getType();
                	break;
                case 2:
                	result = ((PortletTypeAttributeObject) element).isRequired();
                    break;
                case 3:
                	result = ((PortletTypeAttributeObject) element).isMultiValued();
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
            int index = Arrays.asList(columnAttributesNames).indexOf(property);
            switch (index)
            {
                case 0:
                	((PortletTypeAttributeObject) item.getData()).setName((String)value);                	
                    break;
                case 1:
                	((PortletTypeAttributeObject) item.getData()).setType((Integer)value);                	
                    break;
                case 2 :                	
                	((PortletTypeAttributeObject) item.getData()).setRequired((Boolean)value);
                	break;
                case 3 :                	
                	((PortletTypeAttributeObject) item.getData()).setMultiValued((Boolean)value);
                default:
                    break;
            }
            tableViewer.update(item.getData(), null);
		}
	}

	public void addAttribute() {
		dataStruct.add("newAttribute_" + Integer.toString(dataStruct.getData().size()),0,false, false);
		tableViewer.refresh();
	}

	public void removeAttribute() {		
		dataStruct.remove(((IStructuredSelection) tableViewer.getSelection()).getFirstElement());
		tableViewer.refresh();				
	}
	
	public PortletTypeAttributeDataStructure getData() {
		return dataStruct;
	}

	public TableViewer getTableViewer() {
		return tableViewer;
	}
}
