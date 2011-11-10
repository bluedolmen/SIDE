package com.bluexml.side.build.tools.graph.jung.algorithms;

import java.util.Collection;

import org.apache.commons.collections15.Predicate;
import org.apache.log4j.Logger;

import com.bluexml.side.build.tools.componants.Componant;

import edu.uci.ics.jung.algorithms.filters.VertexPredicateFilter;
import edu.uci.ics.jung.graph.Graph;

public class GraphFilter {
	static Logger logger = Logger.getLogger(GraphFilter.class);
	Graph<Componant, String> graph;

	public GraphFilter(Graph<Componant, String> graph) {
		this.graph = graph;
	}

	public Graph<Componant, String> getFilteredVertex(final String vertexPatern, final boolean includesAscendant, final boolean caseSensitive) {

		Predicate<Componant> vertex_pred = new Predicate<Componant>() {
			public boolean evaluate(Componant object) {
				// TODO Auto-generated method stub
				boolean matches = match(vertexPatern, object);
				boolean isAscendant = false;

				if (includesAscendant) {
					// search if this vertex is an ascendent of matching vertex

					isAscendant = ascandantMatches(vertexPatern, object);
				}
				return matches || isAscendant;
			}

			private boolean ascandantMatches(final String vertexPatern, Componant object) {
				boolean matches = match(vertexPatern, object);
				if (!matches) {
					// try to search for children
					Collection<Componant> successors = graph.getSuccessors(object);
					for (Componant componant : successors) {
						if (ascandantMatches(vertexPatern, componant)) {
							matches = true;
							break;
						}
					}
				}
				return matches;
			}

			private boolean match(String vertexPatern, Componant object) {
				String string = object.toString();
				if (!caseSensitive) {
					string = string.toLowerCase();
					vertexPatern = vertexPatern.toLowerCase();
				}
				boolean matches = string.matches(vertexPatern);
				if (logger.isDebugEnabled() && matches) {
					logger.debug("vertex match " + vertexPatern + " :" + string);
				}
				return matches;
			}
		};

		VertexPredicateFilter<Componant, String> vf = new VertexPredicateFilter<Componant, String>(vertex_pred);

		return vf.transform(this.graph);
	}
}
