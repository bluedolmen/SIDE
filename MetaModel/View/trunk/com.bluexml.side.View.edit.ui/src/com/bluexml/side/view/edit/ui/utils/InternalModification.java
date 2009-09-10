package com.bluexml.side.view.edit.ui.utils;

public class InternalModification {

	protected static boolean moveToDisabled = true;
	
	public static void moveToDisabled() {
		moveToDisabled = true;
	}
	
	public static void dontMoveToDisabled() {
		moveToDisabled = false;
	}
	
	public static boolean getMoveToDisabled() {
		return moveToDisabled;
	}
}
