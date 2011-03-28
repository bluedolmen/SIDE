package com.bluexml.side.Class.modeler.diagram.figure.colorScaler;

import org.eclipse.swt.graphics.Color;

public class PurpleScaler implements ColorScale {
	private static int COLOR_MIN = 170;
	private static int COLOR_MAX = 220;

	public Color getColor(int index, int count) {
		if (index == 1)
			return new Color(null, 255, COLOR_MIN, 255);
		else if (index == count)
			return new Color(null, 255, COLOR_MAX, 255);
		else {
			int i = COLOR_MIN + (COLOR_MAX - COLOR_MIN) * index / count;
			return new Color(null, 255, i, 255);
		}
	}
}
