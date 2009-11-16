package com.bluexml.side.util.componentmonitor.guiAdapter;

import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;

import com.bluexml.side.util.componentmonitor.guiAdapter.AdaptedRunable.WidgetNotAvailable;
import com.bluexml.side.util.componentmonitor.headLessinterface.LabelInterface;
import com.bluexml.side.util.libs.ui.UIUtils;

public class LabelAdapter implements LabelInterface {
	private Label label;

	public LabelAdapter(Label label) {
		this.label = label;
	}

	public Point computeSize(final int wHint, final int hHint, final boolean changed) throws WidgetNotAvailable {
		Display currentDisp = UIUtils.getDisplay();
		AdaptedRunable ad = new AdaptedRunable(label) {
			@Override
			public void secureRun() {
				result = label.computeSize(wHint, hHint, changed);
			}
		};
		currentDisp.syncExec(ad);
		return (Point) ad.getResult();
	}

	public int getAlignment() throws WidgetNotAvailable {
		Display currentDisp = UIUtils.getDisplay();
		AdaptedRunable ad = new AdaptedRunable(label) {
			@Override
			public void secureRun() {
				result = label.getAlignment();
			}
		};
		currentDisp.syncExec(ad);
		return (Integer) ad.getResult();
	}

	public Image getImage() throws WidgetNotAvailable {
		Display currentDisp = UIUtils.getDisplay();
		AdaptedRunable ad = new AdaptedRunable(label) {
			@Override
			public void secureRun() {
				result = label.getImage();
			}
		};
		currentDisp.syncExec(ad);
		return (Image) ad.getResult();
	}

	public String getText() throws WidgetNotAvailable {
		Display currentDisp = UIUtils.getDisplay();
		AdaptedRunable ad = new AdaptedRunable(label) {
			@Override
			public void secureRun() {
				result = label.getText();
			}
		};
		currentDisp.syncExec(ad);
		return (String) ad.getResult();
	}

	public void setAlignment(final int value) {
		Display currentDisp = UIUtils.getDisplay();
		currentDisp.syncExec(new AdaptedRunable(label) {
			@Override
			public void secureRun() {
				label.setAlignment(value);
			}
		});
	}

	public void setImage(final Image img) {
		Display currentDisp = UIUtils.getDisplay();
		currentDisp.syncExec(new AdaptedRunable(label) {
			@Override
			public void secureRun() {
				label.setImage(img);
			}
		});
	}

	public void setText(final String text) {
		Display currentDisp = UIUtils.getDisplay();
		currentDisp.syncExec(new AdaptedRunable(label) {
			@Override
			public void secureRun() {
				label.setText(text);
			}
		});
	}

}
