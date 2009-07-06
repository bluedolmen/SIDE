package com.bluexml.side.util.documentation.structure;

import java.net.URI;
import java.net.URISyntaxException;

import com.thoughtworks.xstream.converters.SingleValueConverter;

public class URIConverter implements SingleValueConverter {

    public String toString(Object obj) {
            return ((URI) obj).toString();
    }

    public Object fromString(String url) {
    	URI result = null;
            try {
            	result = new URI(url);
			} catch (URISyntaxException e) {
				e.printStackTrace();
			}
		return result;
    }

    public boolean canConvert(Class type) {
            return type.equals(URI.class);
    }

}