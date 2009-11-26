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
package com.bluexml.side.Class.modeler.diagram.dialogs;

import java.util.Arrays;

import org.eclipse.jface.viewers.CellEditor;
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

public class EnumerationLiteralViewer
{
    private static final int NAME_COLUMN_WIDTH = 150;

    private TableViewer tableViewer;

    private String[] columnNames = new String[] {"Name","Value"};

    private EnumerationLiteralDataStructure dataStructure;

	private Table table;

    /**
     * 
     * @param parent
     * @param dataStruct
     * @param ty
     * @param tyNames
     */
    public EnumerationLiteralViewer(Composite parent,EnumerationLiteralDataStructure dataStruct)
    {
        dataStructure = dataStruct;
        createTableViewer(parent);
    }

    /**
     * 
     *
     */
    public void addParameter()
    {
        dataStructure.add("new","");
        tableViewer.refresh();
    }

    /**
     * 
     *
     */
    public void removeParameter()
    {
        dataStructure.remove(((IStructuredSelection) tableViewer.getSelection()).getFirstElement());
        tableViewer.refresh();
    }

    /**
     * 
     * @return
     */
    public EnumerationLiteralDataStructure getData()
    {
        return dataStructure;
    }

    /**
     * 
     * @param composite
     * @return
     */
    private Table createTable(Composite composite)
    {
        int style = SWT.SINGLE | SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL | SWT.FULL_SELECTION | SWT.HIDE_SELECTION;

        table = new Table(composite, style);
        TableColumn nameColumn = new TableColumn(table, SWT.LEFT);
        nameColumn.setText(columnNames[0]);
        nameColumn.setWidth(NAME_COLUMN_WIDTH);
        TableColumn nameColumn2 = new TableColumn(table, SWT.LEFT);
        nameColumn2.setText(columnNames[1]);
        nameColumn2.setWidth(NAME_COLUMN_WIDTH);

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
    private void createTableViewer(Composite composite)
    {
        Table table = createTable(composite);
        tableViewer = new TableViewer(table);

        tableViewer.setUseHashlookup(true);
        tableViewer.setColumnProperties(columnNames);

        CellEditor[] editors = new CellEditor[2];

        TextCellEditor textEditor = new TextCellEditor(table);
        ((Text) textEditor.getControl()).setTextLimit(60);
        editors[0] = textEditor;
        TextCellEditor textEditor2 = new TextCellEditor(table);
        ((Text) textEditor2.getControl()).setTextLimit(60);
        editors[1] = textEditor2;

        tableViewer.setCellEditors(editors);
        tableViewer.setContentProvider(new ParameterContentProvider());
        tableViewer.setLabelProvider(new ParameterLabelProvider());
        tableViewer.setInput(dataStructure);
        tableViewer.setCellModifier(new ParameterCellModifier());
    }

    class ParameterCellModifier implements ICellModifier
    {
        /**
         * 
         * @see org.eclipse.jface.viewers.ICellModifier#canModify(java.lang.Object, java.lang.String)
         */
        public boolean canModify(Object element, String property)
        {
            return true;
        }
        /**
         * 
         * @see org.eclipse.jface.viewers.ICellModifier#getValue(java.lang.Object, java.lang.String)
         */
        public Object getValue(Object element, String property)
        {
            Object result = null;
            int index = Arrays.asList(columnNames).indexOf(property);
            switch (index)
            {
                case 0:
                    result = dataStructure.getDisplayName(element);
                    break;
                case 1:
                    result = dataStructure.getDisplayValue(element);
                    break;
                default:
                    break;
            }
            return result;
        }
        /**
         * 
         * @see org.eclipse.jface.viewers.ICellModifier#modify(java.lang.Object, java.lang.String, java.lang.Object)
         */
        public void modify(Object element, String property, Object value)
        {
            TableItem item = (TableItem) element;
            int index = Arrays.asList(columnNames).indexOf(property);
            switch (index)
            {
                case 0:
                    dataStructure.setName(item.getData(), (String) value);
                    break;
                case 1:
                    dataStructure.setValue(item.getData(), (String) value);
                    break;
                    
                default:
                    break;
            }
            tableViewer.update(item.getData(), null);
        }
    }

    class ParameterContentProvider implements IStructuredContentProvider
    {
        /**
         * 
         * @see org.eclipse.jface.viewers.IStructuredContentProvider#getElements(java.lang.Object)
         */
        public Object[] getElements(Object inputElement)
        {
            return dataStructure.getData().toArray();
        }
        /**
         * 
         * @see org.eclipse.jface.viewers.IContentProvider#dispose()
         */
        public void dispose()
        {
            //nothing to do
        }
        /**
         * 
         * @see org.eclipse.jface.viewers.IContentProvider#inputChanged(org.eclipse.jface.viewers.Viewer, java.lang.Object, java.lang.Object)
         */
        public void inputChanged(Viewer viewer, Object oldInput, Object newInput)
        {
            //nothing to do
        }
    }

    class ParameterLabelProvider extends LabelProvider implements ITableLabelProvider
    {
        /**
         * @see org.eclipse.jface.viewers.ITableLabelProvider#getColumnImage(java.lang.Object, int)
         */
        public Image getColumnImage(Object element, int columnIndex)
        {
            return null;
        }
        /**
         * @see org.eclipse.jface.viewers.ITableLabelProvider#getColumnText(java.lang.Object, int)
         */
        public String getColumnText(Object element, int columnIndex)
        {
            String result = "";
            switch (columnIndex)
            {
                case 0:
                    result = dataStructure.getDisplayName(element);
                    break;
                case 1:
                    result = dataStructure.getDisplayValue(element);
                    break;
                    
                default:
                    break;
            }
            return result;
        }
    }

	public Table getTable() {
		return table;
	}

	public void setTable(Table table) {
		this.table = table;
	}

	public TableViewer getTableViewer() {
		return tableViewer;
	}

	public void setTableViewer(TableViewer tableViewer) {
		this.tableViewer = tableViewer;
	}
}
