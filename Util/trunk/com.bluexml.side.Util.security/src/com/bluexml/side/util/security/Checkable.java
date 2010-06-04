package com.bluexml.side.util.security;

/**
 * An interface to check if the component can be activate
 * 
 * @author <a href="mailto:pbertrand@bluexml.com">Pierre BERTRAND</a>
 */
public interface Checkable {
	/**
	 * This method check if the user have the license to use this component.
	 * 
	 * @return true if the component can be used.
	 */
	public abstract boolean check();
	
	public abstract boolean checkOption(String optionID);
}
