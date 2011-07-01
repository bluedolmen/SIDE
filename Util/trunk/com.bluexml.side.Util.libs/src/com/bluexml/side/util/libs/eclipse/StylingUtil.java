package com.bluexml.side.util.libs.eclipse;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Text;

import com.bluexml.side.util.libs.Activator;
import com.bluexml.side.util.libs.Messages;

public class StylingUtil {
	public static final Messages Messages = new Messages(Activator.PLUGIN_ID, "com.bluexml.side.util.libs.eclipse.messages");
	static final public GridLayout layout = getLayout();
	static final public Color color = new Color(Display.getCurrent(), 255, 255, 255);

	public static GridData getNewLayoutData() {
		GridData gridData = new GridData(GridData.FILL_HORIZONTAL);
		return gridData;
	}

	static public void applyStyle(GridLayout gl, Text t) {
		GridData gd = getNewLayoutData();
		gd.horizontalSpan = gl.numColumns - 1;

		t.setLayoutData(gd);
		t.setBackground(color);
	}

	static public Text getNewText(Composite composite, GridLayout gl, String initialValue) {
		if (gl == null) {
			gl = layout;
		}
		Text textField = new Text(composite, SWT.BORDER);
		if (initialValue != null) {
			textField.setText(initialValue);
		}
		StylingUtil.applyStyle(gl, textField);
		return textField;
	}

	static private GridLayout getLayout() {
		GridLayout layout = new GridLayout();
		layout.numColumns = 4;
		return layout;
	}

	public static Composite getDefaultComposite(Composite composite) {
		Composite textFieldControl = new Composite(composite, SWT.NONE);
		textFieldControl.setLayout(StylingUtil.layout);
		return textFieldControl;
	}
	
	public static Color getColor(int r,int g,int b) {
		return new Color(Display.getCurrent(), r, g, b);
	}
}
