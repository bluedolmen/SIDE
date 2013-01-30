package com.bluexml.side.framework.alfresco.unicity;

import java.util.List;
import java.util.Map;

import org.alfresco.model.ContentModel;
import org.alfresco.service.cmr.repository.NodeRef;
import org.alfresco.service.cmr.repository.NodeService;
import org.alfresco.service.cmr.repository.StoreRef;
import org.alfresco.service.cmr.search.ResultSet;
import org.alfresco.service.cmr.search.SearchParameters;
import org.alfresco.service.cmr.search.SearchService;
import org.alfresco.service.namespace.QName;
import org.apache.log4j.Logger;

public class LuceneUnicityCheckerImpl extends AbstractUnicityChecker {
	static StoreRef storeRef = StoreRef.STORE_REF_WORKSPACE_SPACESSTORE;

	protected static Logger logger = Logger.getLogger(LuceneUnicityCheckerImpl.class);

	protected String buildTypeClause(QName type) {
		String clause = "(TYPE:\"{" + type.getNamespaceURI() + "}" + type.getLocalName() + "\")";
		return clause;
	}

	protected String buildAttributeClause(QName attribute, String value) {
		String attb = "@\\{" + attribute.getNamespaceURI().replaceFirst("http:", "http\\\\:") + "\\}";
		attb += attribute.getLocalName();
		String clause = attb + ":" + "\"" + value + "\"";
		return clause;
	}

	protected String buildIDClause(String uuid) {
		return buildAttributeClause(ContentModel.PROP_NODE_UUID, uuid);
	}

	public boolean exist(QName nodeType, Map<?, ?> prop, String uuid) throws Exception {
		String luceneUnicityQuery = buildUnicityRequest(nodeType, prop, uuid);
		return exist_(luceneUnicityQuery);
	}

	public boolean exist(NodeRef node) throws Exception {
		String luceneUnicityQuery = buildUnicityRequest(node);
		return exist_(luceneUnicityQuery);
	}

	protected boolean exist_(String luceneUnicityQuery) throws Exception {
		if (luceneUnicityQuery == null || luceneUnicityQuery.equals("")) {
			return false;
		}

		ResultSet results = null;

		// build searchParameters
		logger.debug(" luceneQuery :" + luceneUnicityQuery);

		SearchParameters sp = buildSearchParameters(null, luceneUnicityQuery);
		results = searchService.query(sp);

		if (results.getNodeRefs().size() == 0) {
			logger.debug("exist :" + false + " , luceneQuery :" + luceneUnicityQuery);
			return false;
		} else {
			logger.debug("exist :" + true + " , luceneQuery :" + luceneUnicityQuery);
			for (NodeRef resultSetRow : results.getNodeRefs()) {
				logger.debug("\t" + resultSetRow);
			}
			return true;
		}
	}

	/**
	 * build the SearchParameters Object with sort and query
	 * 
	 * @param sorting
	 * @param luceneFinalQuery
	 * @return
	 */
	protected SearchParameters buildSearchParameters(Map<String, Object> sorting, String luceneFinalQuery) {
		SearchParameters sp = new SearchParameters();
		sp.addStore(storeRef);
		sp.setLanguage(SearchService.LANGUAGE_LUCENE);
		sp.setQuery(luceneFinalQuery);
		if (sorting != null && sorting.containsKey("sortby")) {
			sp.addSort((String) sorting.get("sortby"), (Boolean) sorting.get("order"));
		}
		return sp;
	}

	protected String buildUnicityRequest(NodeRef nodeRef) throws Exception {
		return buildUnicityRequest(nodeService.getType(nodeRef), nodeService.getProperties(nodeRef), nodeRef.getId());
	}

	protected String buildUnicityRequest(QName nodeType, Map<?, ?> prop, String uuid) throws Exception {

		List<QName> criterias = getUnicityKeysFor(nodeType);
		int clauseNb = 0;
		String filterClause = buildTypeClause(nodeType);
		if (criterias != null) {
			// add lucene type clause
			for (int c = 0; c < criterias.size(); c++) {
				QName colQname = criterias.get(c);
				// TODO test Date and boolean values
				Object ob = prop.get(colQname);
				if (ob != null) {
					String colomnValue = ob.toString();
					if (!colomnValue.equals("")) {
						filterClause += " AND ";
						filterClause += buildAttributeClause(colQname, colomnValue);
						clauseNb++;
					}
				} else if (logger.isDebugEnabled()) {
					logger.debug("Property not found:" + colQname);
				}
			}
		}

		if (clauseNb != 0) {
			if (uuid != null) {
				// add uuid clause (so do not include current node)				
				filterClause += " AND -" + buildIDClause(uuid);
			}
			return filterClause;
		} else {
			return null;
		}

	}

	protected SearchService searchService;

	protected NodeService nodeService;

	public void setSearchService(SearchService searchService) {
		this.searchService = searchService;
	}

	public void setNodeService(NodeService nodeService) {
		this.nodeService = nodeService;
	}

}
