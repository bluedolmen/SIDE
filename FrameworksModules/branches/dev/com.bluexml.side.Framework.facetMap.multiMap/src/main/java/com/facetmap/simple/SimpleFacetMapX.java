package com.facetmap.simple;

import java.io.IOException;
import java.io.InputStream;

import com.facetmap.DataException;
import com.facetmap.InternalException;
import com.facetmap.Map;

public class SimpleFacetMapX extends SimpleFacetmap {

	public void setResultLimit(int limit) {
		this.resultLimit = limit;
	}
	
	public static Map createFromXml(String s) throws IOException, DataException, InternalException {
		return (new CustomXmlConverter()).parse(s);
	}

	public static Map createFromXml(InputStream inputstream) throws IOException, DataException, InternalException {
		return (new CustomXmlConverter()).parse(inputstream);
	}
}
