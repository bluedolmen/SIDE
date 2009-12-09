package com.facetmap.simple;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import com.facetmap.DataException;
import com.facetmap.InternalException;
import com.facetmap.Map;
import com.facetmap.simple.SimpleFacetmap;
import com.facetmap.simple.CustomXmlConverter;

public class SimpleFacetMapX extends SimpleFacetmap {

	public void setResultLimit(int limit) {
		this.resultLimit = limit;
	}
	
	public static Map createFromXml(String s, URL url, File file) throws IOException, DataException, InternalException {
		return (new CustomXmlConverter(url, file)).parse(s);
	}

	public static Map createFromXml(InputStream inputstream, URL url, File file) throws IOException, DataException, InternalException {
		return (new CustomXmlConverter(url, file)).parse(inputstream);
	}
}
