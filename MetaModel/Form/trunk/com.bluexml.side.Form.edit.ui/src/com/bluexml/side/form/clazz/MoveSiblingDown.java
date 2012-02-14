package com.bluexml.side.form.clazz;

import org.eclipse.emf.common.util.EList;

import com.bluexml.side.form.FormElement;
import com.bluexml.side.form.FormUI;

public class MoveSiblingDown extends MoveSiblingUp {

	@Override
	public int getNewIndex(FormElement fc, EList<?> list) {
		int newIndex;
		int indexOf = list.indexOf(fc);
		int lastIndex = list.size() -1;
		newIndex = indexOf < lastIndex ? indexOf + 1 : lastIndex;
		return newIndex;
	}

	@Override
	public String getText() {
		return FormUI.Messages.getString("Actions_moveSiblingDown");
	}

}
