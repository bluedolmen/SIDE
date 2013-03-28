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
package com.bluexml.side.util.libs.eclipse;

import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.events.MouseTrackListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Text;

import com.bluexml.side.util.libs.ecore.ResourceTableCellData;

public abstract class AbstractCellEditor extends CellEditor {

	protected Text t;
	protected AbstractDialogCellEditor resourceSelector;

	public AbstractCellEditor(Composite parent) {
		super(parent);
	}

	public AbstractCellEditor(Composite parent, int style) {
		super(parent, style);
	}

	public abstract ResourceTableCellData getSelectedCellData();

	protected abstract AbstractDialogCellEditor createResourceSelector(Composite parent);

	@Override
	protected Object doGetValue() {
		return resourceSelector.getResourcePath();
	}

	@Override
	protected void doSetFocus() {
		// nothing to do
	}

	@Override
	protected void doSetValue(Object value) {
		t.setText((String) value);
		// initialize ResourceSelection dialog
		initControl();
	}

	@Override
	protected Control createControl(Composite parent) {
		resourceSelector = createResourceSelector(parent);
		t = new Text(parent, getStyle());
		t.setEditable(false);
		t.setFont(parent.getFont());
		t.setBackground(parent.getBackground());
		// bind action on control selection
		t.addMouseListener(new MouseListener() {
			public void mouseUp(MouseEvent e) {
				handleActivateEditor();
			}

			public void mouseDown(MouseEvent e) {
			}

			public void mouseDoubleClick(MouseEvent e) {

			}
		});

		t.addMouseTrackListener(new MouseTrackListener() {

			public void mouseHover(MouseEvent e) {
			}

			public void mouseExit(MouseEvent e) {
				deactivate();
			}

			public void mouseEnter(MouseEvent e) {
			}
		});

		// fire apply value change on "ok" button
		resourceSelector.addResourceSelectionListener(new ResourceSelectionListener() {
			public void ok() {
				manageOk();
			}

			public void cancel() {
				manageOk();
			}
		});

		return t;
	}

	protected void manageOk() {
		t.setText(resourceSelector.getResourcePath());
		fireApplyEditorValue();
		deactivate();
	}

	protected void handleActivateEditor() {
		resourceSelector.open();
	}

	protected void initControl() {
		ResourceTableCellData data = getSelectedCellData();
		resourceSelector.init(data.getLabel(), data.getValue(), data.getDataType());

	}

}
