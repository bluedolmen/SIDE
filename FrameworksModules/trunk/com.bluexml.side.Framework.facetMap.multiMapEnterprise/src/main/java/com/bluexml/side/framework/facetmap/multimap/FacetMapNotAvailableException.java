package com.bluexml.side.framework.facetmap.multimap;

public class FacetMapNotAvailableException extends Exception {

	private static final long serialVersionUID = -1075021061881766940L;

	public FacetMapNotAvailableException(String message) {
		super(message);
	}

	public FacetMapNotAvailableException(String message, Exception e) {
		super(message, e);
	}

	public static Throwable getInitialCause(Throwable t) {
		if (t instanceof FacetMapNotAvailableException) {
			return t;
		}
		if (t.getCause() != null) {
			return getInitialCause(t.getCause());
		}
		return t;
	}
}
