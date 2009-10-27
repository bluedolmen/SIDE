package com.bluexml.side.util.componentmonitor.guiAdapter;

import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Label;

import com.bluexml.side.util.componentmonitor.headLessinterface.LabelInterface;

public class LabelAdapter implements LabelInterface {
	private Label label;

	public LabelAdapter(Label label) {
		this.label = label;
	}

	public Point computeSize(int wHint, int hHint, boolean changed) {
		return label.computeSize(wHint, hHint, changed);
	}

	public int getAlignment() {
		return label.getAlignment();
	}

	public Image getImage() {
		return label.getImage();
	}

	public String getText() {
		return label.getText();
	}

	public void setAlignment(int value) {
		label.setAlignment(value);
	}

	public void setImage(Image img) {
		label.setImage(img);
	}

	public void setText(String text) {
		label.setText(text);
	}

}
