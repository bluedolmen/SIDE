package com.bluexml.tools.graph.engines.jung.transformer;

import org.apache.commons.collections15.Transformer;

import com.bluexml.tools.graph.model.GraphElement;

public class GraphElementLabelTrasnformer<V> implements Transformer<V, String> {

	public String transform(V input) {
		if (input instanceof GraphElement) {
			return ((GraphElement) input).getLabel();
		}
		return input.toString();
	}

}
