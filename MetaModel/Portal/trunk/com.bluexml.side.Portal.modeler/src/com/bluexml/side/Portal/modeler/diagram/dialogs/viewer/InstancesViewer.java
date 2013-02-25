package com.bluexml.side.Portal.modeler.diagram.dialogs.viewer;

import java.util.Arrays;

import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ICellEditorListener;
import org.eclipse.jface.viewers.ICellModifier;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.StructuredSelection;
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

import com.bluexml.side.Portal.modeler.diagram.dialogs.dataStructure.InstancesDataStructure;
import com.bluexml.side.Portal.modeler.diagram.dialogs.dataStructure.InstancesDataStructure.InstancesObject;
import com.bluexml.side.util.libs.eclipse.DialogResourceCellEditor;

public class InstancesViewer {
	private static final int ATTRIBUTENAME_WIDTH = 150;

	private static final int VALUE_WIDTH = 150;

	private TableViewer tableViewer;

	private String[] columnNames = new String[] { "Attribute", "Value" };

	private InstancesDataStructure dataStruct;

	protected CellEditor[] editors;

	protected Table table;

	public InstancesViewer(Composite p_parent, InstancesDataStructure p_dataStruct) {
		if (p_dataStruct != null) {
			this.dataStruct = p_dataStruct;
		}
		createTableViewer(p_parent);
	}

	private void createTableViewer(Composite p_parent) {
		Table table = createTable(p_parent);
		tableViewer = new TableViewer(table);

		tableViewer.setUseHashlookup(true);
		tableViewer.setColumnProperties(columnNames);

		editors = new CellEditor[2];

		TextCellEditor attributeEditor = new TextCellEditor(table);
		((Text) attributeEditor.getControl()).setTextLimit(60);
		attributeEditor.getControl().setEnabled(false);
		editors[0] = attributeEditor;

		TextCellEditor valueEditor = new TextCellEditor(table);
		((Text) valueEditor.getControl()).setTextLimit(250);

		final DialogResourceCellEditor ceditor = new DialogResourceCellEditor(table);
		ceditor.addListener(new ICellEditorListener() {

			public void editorValueChanged(boolean oldValidState, boolean newValidState) {

			}

			public void cancelEditor() {

			}

			public void applyEditorValue() {
				ISelection selection = tableViewer.getSelection();
				tableViewer.getCellModifier().modify(selection, columnNames[1], ceditor.getValue());

			}
		});
		//        editors[1] = valueEditor;

		editors[1] = ceditor;

		tableViewer.setCellEditors(editors);
		tableViewer.setContentProvider(new InstancesContentProvider());
		tableViewer.setLabelProvider(new InstancesLabelProvider());
		tableViewer.setInput(dataStruct);
		tableViewer.setCellModifier(new InstancesCellModifier());
	}

	private Table createTable(Composite composite) {
		int style = SWT.SINGLE | SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL | SWT.FULL_SELECTION | SWT.HIDE_SELECTION;

		table = new Table(composite, style);
		TableColumn nameColumn = new TableColumn(table, SWT.LEFT);
		nameColumn.setText(columnNames[0]);
		nameColumn.setWidth(ATTRIBUTENAME_WIDTH);

		TableColumn widthColumn = new TableColumn(table, SWT.LEFT);
		widthColumn.setText(columnNames[1]);
		widthColumn.setWidth(VALUE_WIDTH);

		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		GridData gridData = new GridData(GridData.FILL_BOTH);
		gridData.grabExcessVerticalSpace = true;
		gridData.horizontalSpan = 2;
		table.setLayoutData(gridData);

		return table;
	}

	public void refresh() {
		tableViewer.refresh();
	}

	public Object getSelection() {
		return ((IStructuredSelection) tableViewer.getSelection()).getFirstElement();
	}

	/**
	 * 
	 */
	public void remove() {
		dataStruct.remove(getSelection());
		tableViewer.refresh();
	}

	/**
	 * Internal class to handle modification
	 */
	class InstancesContentProvider implements IStructuredContentProvider {
		/**
		 * @see org.eclipse.jface.viewers.IStructuredContentProvider#getElements(java.lang.Object)
		 */
		public Object[] getElements(Object inputElement) {
			return dataStruct.getData().toArray();
		}

		/**
		 * @see org.eclipse.jface.viewers.IContentProvider#dispose()
		 */
		public void dispose() {
			// nothing to do
		}

		/**
		 * @see org.eclipse.jface.viewers.IContentProvider#inputChanged(org.eclipse.jface.viewers.Viewer,
		 *      java.lang.Object, java.lang.Object)
		 */
		public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
			// nothing to do
		}
	}

	/**
	 * @return
	 */
	public InstancesDataStructure getData() {
		return dataStruct;
	}

	/**
	 * Internal class to handle modification
	 */
	class InstancesLabelProvider extends LabelProvider implements ITableLabelProvider {
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
				result = ((InstancesObject) element).getKey();
				break;
			case 1:
				result = ((InstancesObject) element).getValue();
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
	class InstancesCellModifier implements ICellModifier {
		/**
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
		 * @see org.eclipse.jface.viewers.ICellModifier#getValue(java.lang.Object,
		 *      java.lang.String)
		 */
		public Object getValue(Object element, String property) {
			Object result = null;
			int index = Arrays.asList(columnNames).indexOf(property);
			if (element instanceof InstancesObject) {
				switch (index) {
				case 0:

					break;
				case 1:
					result = ((InstancesObject) element).getValue();
					break;
				default:
					break;
				}
			}
			return result;
		}

		/**
		 * @see org.eclipse.jface.viewers.ICellModifier#modify(java.lang.Object,
		 *      java.lang.String, java.lang.Object)
		 */
		public void modify(Object element, String property, Object value) {
			InstancesObject instanceObject = null;
			if (element instanceof StructuredSelection) {
				Object s = ((StructuredSelection) element).getFirstElement();
				if (s != null && s instanceof InstancesObject) {
					instanceObject = (InstancesObject) s;
				}
			} else if (element instanceof TableItem) {
				TableItem item = (TableItem) element;
				if (item.getData() instanceof InstancesObject) {
					instanceObject = (InstancesObject) item.getData();
				}
			}
			if (instanceObject != null) {

				int index = Arrays.asList(columnNames).indexOf(property);

				switch (index) {
				case 0:
					break;
				case 1:
					instanceObject.setValue((String) value);
					break;
				default:
					break;
				}

				tableViewer.update(instanceObject, null);
			}
		}
	}

}
