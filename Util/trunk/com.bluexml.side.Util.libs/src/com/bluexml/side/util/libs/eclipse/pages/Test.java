package com.bluexml.side.util.libs.eclipse.pages;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class Test {

	public static class StylingUtil {

		public static GridData getNewLayoutData() {
			return new GridData(SWT.BEGINNING, SWT.BEGINNING, true, true);
		}

		public static GridLayout getLayout() {
			GridLayout layout = new GridLayout();
			layout.numColumns = 4;
			return layout;
		}

	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Display display = new Display();
		Shell shell = new Shell(display);

		GridLayout gridLayout = new GridLayout();
		gridLayout.numColumns = 1;
		shell.setLayout(gridLayout);

		try {
			//						shell.setBounds(100, 100, 600, 900);
			//			createControls(shell);
			controls2(shell);

			shell.pack();
			shell.open();

			while (!shell.isDisposed()) {
				if (!display.readAndDispatch())
					display.sleep();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void createControls(Shell shell) {
		GridLayout gridLayout = new GridLayout();
		gridLayout.numColumns = 3;
		shell.setLayout(gridLayout);
		new Button(shell, SWT.PUSH).setText("B1");
		new Button(shell, SWT.PUSH).setText("Wide Button 2");
		new Button(shell, SWT.PUSH).setText("Button 3");
		new Button(shell, SWT.PUSH).setText("B4");
		new Button(shell, SWT.PUSH).setText("Button 5");
	}

	private static void controls2(Shell shell) {

		Color color = new Color(Display.getCurrent(), 0, 255, 255);
		shell.setBackground(color);

		Composite c1 = new Composite(shell, SWT.NONE);
		c1.setBackground(new Color(Display.getCurrent(), 255, 0, 255));
		GridLayout layoutC1 = StylingUtil.getLayout();

		layoutC1.numColumns = 5;
		layoutC1.makeColumnsEqualWidth = false;
		c1.setLayout(layoutC1);
		GridData newLayoutDataC1 = StylingUtil.getNewLayoutData();
		newLayoutDataC1.horizontalSpan = 5;
		newLayoutDataC1.grabExcessHorizontalSpace = true;
		c1.setLayoutData(newLayoutDataC1);

		// Cell 1
		final org.eclipse.swt.widgets.List modelList = new org.eclipse.swt.widgets.List(c1, SWT.BORDER | SWT.V_SCROLL);
		GridData newLayoutData = StylingUtil.getNewLayoutData();
		newLayoutData.horizontalSpan = 2;
		newLayoutData.verticalSpan = 3;
		newLayoutData.widthHint = 200;
		newLayoutData.verticalAlignment = GridData.FILL;
		newLayoutData.horizontalAlignment = GridData.FILL;
		modelList.setLayoutData(newLayoutData);
		modelList.setBackground(color);	

		// Cell 2
		final Button addModelButton = new Button(c1, SWT.NONE);

		// Cell 3
		Composite fill2 = new Composite(c1, SWT.NONE);
		fill2.setLayout(layoutC1);
		fill2.setBackground(new Color(Display.getCurrent(), 0, 0, 0));
		GridData newLayoutDataF2 = StylingUtil.getNewLayoutData();
		newLayoutDataF2.horizontalSpan = 2;
		newLayoutDataF2.verticalSpan = 3;
		newLayoutDataF2.widthHint = 200;
		newLayoutDataF2.verticalAlignment = GridData.FILL;
		newLayoutDataF2.horizontalAlignment = GridData.FILL;
		fill2.setLayoutData(newLayoutDataF2);

		// Cell 4
		final Button removeModelButton = new Button(c1, SWT.NONE);

		// Cell 5
		Composite fill1 = new Composite(c1, SWT.NONE);
		fill1.setLayout(layoutC1);
		fill1.setBackground(new Color(Display.getCurrent(), 0, 255, 0));
		GridData newLayoutDataF1 = StylingUtil.getNewLayoutData();
		newLayoutDataF1.horizontalSpan = 1;
		newLayoutDataF1.verticalSpan = 1;
		fill1.setLayoutData(newLayoutDataF1);

		addModelButton.setText("add");

		removeModelButton.setText("remove");
	}
}
