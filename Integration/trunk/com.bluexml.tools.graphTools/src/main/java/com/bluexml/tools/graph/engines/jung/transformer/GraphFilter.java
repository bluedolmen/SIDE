/*
    Copyright (C) 2007-20013  BlueXML - www.bluexml.com

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU Affero General Public License as
    published by the Free Software Foundation, either version 3 of the
    License, or (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU Affero General Public License for more details.

    You should have received a copy of the GNU Affero General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/
package com.bluexml.tools.graph.engines.jung.transformer;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.commons.collections15.Predicate;
import org.apache.log4j.Logger;

import com.bluexml.tools.graph.model.Component;

import edu.uci.ics.jung.algorithms.filters.EdgePredicateFilter;
import edu.uci.ics.jung.algorithms.filters.VertexPredicateFilter;
import edu.uci.ics.jung.graph.Graph;

public class GraphFilter<V, E> {
	static Logger logger = Logger.getLogger(GraphFilter.class);
	Graph<V, E> graph;

	String edgesfilter;
	String vertexFilter;
	boolean includesAscendant;
	boolean includesdecendant;
	boolean caseSensitive;
	String vertexTypeFilter;

	public GraphFilter(Graph<V, E> graph, String edgesfilter, String vertexFilter, String vertexTypeFilter, boolean includesAscendant, boolean includesdecendant, boolean caseSensitive) {
		this.graph = graph;
		this.edgesfilter = edgesfilter;
		this.vertexFilter = vertexFilter;
		this.includesAscendant = includesAscendant;
		this.caseSensitive = caseSensitive;
		this.vertexTypeFilter = vertexTypeFilter;
		this.includesdecendant = includesdecendant;
	}

	public Graph<V, E> filter() {
		Graph<V, E> graph_ = graph;

		graph_ = applyEdgesFilter(graph_, edgesfilter);

		graph_ = applyVertexTypeFilter(graph_, vertexTypeFilter, includesAscendant, includesdecendant, caseSensitive);

		graph_ = applyVertexNameFilter(graph_, vertexFilter, includesAscendant, includesdecendant, caseSensitive);

		return graph_;
	}

	public static <V, E> Graph<V, E> applyEdgesFilter(final Graph<V, E> graph, String edgesfilter) {
		logger.debug("Apply Edges Filter" + edgesfilter);
		if (!edgesfilter.equals("")) {

			final List<String[]> l = new ArrayList<String[]>();

			String[] split = edgesfilter.split(",");
			for (String string : split) {
				l.add(string.split(">"));
			}

			Predicate<E> edge_pred = new Predicate<E>() {

				public boolean evaluate(E object) {
					V source = graph.getSource(object);
					V dest = graph.getDest(object);
					boolean sok = false;
					for (String[] strings : l) {
						if (testClassName(source, strings[0].trim())) {
							sok = true;
							break;
						}
					}
					boolean dok = false;
					for (String[] strings : l) {
						if (testClassName(dest, strings[1].trim())) {
							dok = true;
							break;
						}
					}

					return sok && dok;
				}
			};

			EdgePredicateFilter<V, E> pred = new EdgePredicateFilter<V, E>(edge_pred);
			return pred.transform(graph);
		} else {
			return graph;
		}
	}

	public static boolean testClassName(Object o, String name) {
		Class<?> forName;
		try {
			forName = Class.forName("com.bluexml.side.build.tools.componants." + name);
			return forName.isInstance(o);
		} catch (ClassNotFoundException e) {
			logger.error(e.getMessage(), e);
		}
		return false;
	}

	public static <V, E> Graph<V, E> applyVertexNameFilter(final Graph<V, E> graph, final String vertexNameFilter, final boolean includesAscendant, final boolean includesdecendant, final boolean caseSensitive) {
		logger.debug("Apply Vertex Name Filter" + vertexNameFilter);
		if (!vertexNameFilter.equals("")) {
			Predicate<V> vertex_pred = new Predicate<V>() {
				
				public boolean evaluate(V object) {
					// TODO Auto-generated method stub
					boolean matches = match(vertexNameFilter, object);
					boolean isAscendant = false, isdescendant = false;

					if (includesAscendant) {
						// search if this vertex is an ascendent of matching vertex
						isAscendant = ascandantMatches(vertexNameFilter, object);
					}

					if (includesdecendant) {
						isdescendant = decendantMatches(vertexNameFilter, object);
					}
					return matches || isAscendant || isdescendant;
				}

				private boolean ascandantMatches(final String vertexPatern, V object) {
					boolean matches = match(vertexPatern, object);
					if (!matches) {
						// try to search for children
						Collection<V> successors = graph.getSuccessors(object);
						for (V componant : successors) {
							if (ascandantMatches(vertexPatern, componant)) {
								matches = true;
								break;
							}
						}
					}
					return matches;
				}

				private boolean decendantMatches(final String vertexPatern, V object) {
					boolean matches = match(vertexPatern, object);
					if (!matches) {
						// try to search for ancestor that match
						Collection<V> predecessors = graph.getPredecessors(object);
						for (V componant : predecessors) {
							if (decendantMatches(vertexPatern, componant)) {
								matches = true;
								break;
							}
						}
					}
					return matches;
				}

				private boolean match(String vertexPatern, V object) {
					String string = object.toString();
					if (!caseSensitive) {
						string = string.toLowerCase();
						vertexPatern = vertexPatern.toLowerCase();
					}
					boolean matches = string.matches(vertexPatern);
					if (matches && object instanceof Component) {
						logger.debug("vertex match " + vertexPatern + " :" + string);
						((Component)object).setFilterMatch(true);
					}
					return matches;
				}
			};

			VertexPredicateFilter<V, E> vf = new VertexPredicateFilter<V, E>(vertex_pred);

			return vf.transform(graph);
		} else {
			return graph;
		}
	}

	public static <V, E> Graph<V, E> applyVertexTypeFilter(final Graph<V, E> graph, final String vertexTypeFilter, final boolean includesAscendant, final boolean includesdescendant, final boolean caseSensitive) {
		logger.debug("Apply Vertex Type Filter" + vertexTypeFilter);
		if (!vertexTypeFilter.equals("")) {

			final List<String> vertexTypes = new ArrayList<String>();

			String[] split = vertexTypeFilter.split(",");
			for (String string : split) {
				vertexTypes.add(string);
			}

			Predicate<V> vertex_pred = new Predicate<V>() {
				public boolean evaluate(V object) {
					// TODO Auto-generated method stub
					boolean matches = match(vertexTypes, object);
					boolean isAscendant = false, isdecendant = false;

					if (includesAscendant) {
						// search if this vertex is an ascendent of matching vertex

						isAscendant = ascandantMatches(vertexTypes, object);
					}

					if (includesdescendant) {
						isdecendant = descendantMatches(vertexTypes, object);
					}
					return matches || isAscendant || isdecendant;
				}

				private boolean ascandantMatches(final List<String> vertexTypes, V object) {
					boolean matches = match(vertexTypes, object);
					if (!matches) {
						// try to search for children
						Collection<V> successors = graph.getSuccessors(object);
						for (V componant : successors) {
							if (ascandantMatches(vertexTypes, componant)) {
								matches = true;
								break;
							}
						}
					}
					return matches;
				}

				private boolean descendantMatches(final List<String> vertexTypes, V object) {
					boolean matches = match(vertexTypes, object);
					if (!matches) {
						// try to search for children
						Collection<V> predecessors = graph.getPredecessors(object);
						for (V componant : predecessors) {
							if (descendantMatches(vertexTypes, componant)) {
								matches = true;
								break;
							}
						}
					}
					return matches;
				}

				private boolean match(List<String> vertexTypes, V object) {
					boolean ok = false;
					for (String string2 : vertexTypes) {
						ok = testClassName(object, string2);
						break;
					}
					return ok;
				}
			};

			VertexPredicateFilter<V, E> vf = new VertexPredicateFilter<V, E>(vertex_pred);

			return vf.transform(graph);
		} else {
			return graph;
		}
	}

	public static <V, E> Graph<V, E> applyVertexRemoveOrphelanFilter(final Graph<V, E> graph) {

		Predicate<V> vertex_pred = new Predicate<V>() {
			public boolean evaluate(V object) {
				return graph.getNeighborCount(object) != 0;
			}			
		};

		VertexPredicateFilter<V, E> vf = new VertexPredicateFilter<V, E>(vertex_pred);

		return vf.transform(graph);

	}
}
