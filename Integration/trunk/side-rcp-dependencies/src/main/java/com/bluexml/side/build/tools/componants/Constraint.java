package com.bluexml.side.build.tools.componants;

public class Constraint extends Componant{

	Plugin bundle;
	Option option;
	boolean check;
	
	public Plugin getBundle() {
		return bundle;
	}
	public void setBundle(Plugin bundle) {
		this.bundle = bundle;
	}
	public Option getOption() {
		return option;
	}
	public void setOption(Option option) {
		this.option = option;
	}
	public boolean isCheck() {
		return check;
	}
	public void setCheck(boolean check) {
		this.check = check;
	}
	
	public String toString() {
		String s="";
		if (check) {
			s="check";
		} else {
			s="Uncheck";
		}
		return s+":"+bundle.getId()+":"+option.getFullKey();
	}
	
}
