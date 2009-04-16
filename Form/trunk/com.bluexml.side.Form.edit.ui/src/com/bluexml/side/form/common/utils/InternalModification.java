package com.bluexml.side.form.common.utils;

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
