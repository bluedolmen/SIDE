package com.bluexml.side.build.tools.componants;

import java.util.ArrayList;
import java.util.List;

public class Product extends Componant{

	String uid;
	String version;
	String name;
	String id;
	List<Feature> features= new ArrayList<Feature>();
	
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public List<Feature> getFeatures() {
		return features;
	}
	public void setFeatures(List<Feature> features) {
		this.features = features;
	}
	
	public String toString() {
		return id + "(" + name + ")";
	}
}
