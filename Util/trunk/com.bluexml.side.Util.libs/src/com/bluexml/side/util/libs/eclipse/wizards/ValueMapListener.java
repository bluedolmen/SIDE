package com.bluexml.side.util.libs.eclipse.wizards;

public interface ValueMapListener<T, V> {

	public void put(T key, V o);

	public void remove(Object o);
}
