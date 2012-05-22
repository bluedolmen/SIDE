package com.bluexml.side.framework.alfresco.unicity;

import java.util.List;
import java.util.Map;

import org.alfresco.model.ContentModel;
import org.alfresco.repo.domain.node.Node;
import org.alfresco.service.cmr.repository.NodeRef;
import org.alfresco.service.cmr.repository.StoreRef;
import org.alfresco.service.cmr.search.SearchParameters;
import org.alfresco.service.cmr.search.SearchService;
import org.alfresco.service.namespace.QName;
import org.apache.log4j.Logger;

public class LuceneUnicityCheckerImpl extends AbstractUnicityChecker {
	static StoreRef storeRef = StoreRef.STORE_REF_WORKSPACE_SPACESSTORE;

	private static Logger logger = Logger.getLogger(LuceneUnicityCheckerImpl.class);

	public String buildUnicityRequest(QName nodeType, Map<?, ?> prop, String uuid) throws Exception {

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

	private String buildTypeClause(QName type) {
		String clause = "(TYPE:\"{" + type.getNamespaceURI() + "}" + type.getLocalName() + "\")";
		return clause;
	}

	private String buildAttributeClause(QName attribute, String value) {
		String attb = "@\\{" + attribute.getNamespaceURI().replaceFirst("http:", "http\\\\:") + "\\}";
		attb += attribute.getLocalName();
		String clause = attb + ":" + "\"" + value + "\"";
		return clause;
	}

	private String buildIDClause(String uuid) {
		return buildAttributeClause(ContentModel.PROP_NODE_UUID, uuid);
	}

	public boolean exist(QName nodeType, Map<?, ?> prop, String uuid) throws Exception {
		String luceneUnicityQuery = buildUnicityRequest(nodeType, prop, uuid);
		return exist_(luceneUnicityQuery);
	}

	public boolean exist(org.alfresco.repo.domain.node.Node node) throws Exception {
		String luceneUnicityQuery = buildUnicityRequest(node);
		return exist_(luceneUnicityQuery);
	}

	private boolean exist_(String luceneUnicityQuery) throws Exception {
		if (luceneUnicityQuery == null || luceneUnicityQuery.equals("")) {
			return false;
		}
		// use ADMLuceneSearcherImpl
		org.alfresco.service.cmr.search.ResultSet results = null;
		org.alfresco.service.cmr.search.ResultSet resultsADM = null;
		//		org.alfresco.repo.search.impl.lucene.LuceneSearcher searcher = getUnsecureLuceneSearcher();

		// build searchParameters
		logger.debug(" luceneQuery :" + luceneUnicityQuery);
		
		
		SearchParameters sp = buildSearchParameters(null, luceneUnicityQuery);
		results = getSearchService().query(sp);

		// test with admSearch
		resultsADM = getAdmSearchService().query(sp);

		if (resultsADM.getNodeRefs().size() == 0) {
			logger.debug("ADM NO Matches found !");
		} else {
			logger.debug("ADM some node matches !");
			for (NodeRef resultSetRow : resultsADM.getNodeRefs()) {
				logger.debug("ADM \t" + resultSetRow);
			}
		}

		if (results.getNodeRefs().size() == 0) {
			//System.out.println("exist :"+false+" , luceneQuery :"+luceneUnicityQuery);
			logger.debug("exist :" + false + " , luceneQuery :" + luceneUnicityQuery);
			return false;
		} else {
			//System.out.println("exist :"+true+" , luceneQuery :"+luceneUnicityQuery);
			logger.debug("exist :" + true + " , luceneQuery :" + luceneUnicityQuery);
			for (NodeRef resultSetRow : results.getNodeRefs()) {
				logger.debug("\t" + resultSetRow);
			}
			return true;
		}
	}

	//	/**
	//	 * setup an instance of ADMLuceneSearcherImpl lucene searcher
	//	 * 
	//	 * @return
	//	 */
	//	public LuceneSearcher getUnsecureLuceneSearcher() {
	//		LuceneIndexer indexer = (LuceneIndexer) indexerAndSearcherFactory.getIndexer(storeRef);
	//		LuceneConfig config = (LuceneConfig) indexerAndSearcherFactory;
	//		ADMLuceneSearcherImpl searcher = ADMLuceneSearcherImpl.getSearcher(storeRef, indexer, config);
	//		searcher.setTenantService(tenantService);
	//		searcher.setDictionaryService(dictionaryService);
	//		searcher.setNamespacePrefixResolver(namespacePrefixResolver);
	//		return searcher;
	//	}

	/**
	 * build the SearchParameters Object with sort and query
	 * 
	 * @param sorting
	 * @param luceneFinalQuery
	 * @return
	 */
	public SearchParameters buildSearchParameters(Map<String, Object> sorting, String luceneFinalQuery) {
		SearchParameters sp = new SearchParameters();
		sp.addStore(storeRef);
		sp.setLanguage(SearchService.LANGUAGE_LUCENE);
		sp.setQuery(luceneFinalQuery);
		if (sorting != null && sorting.containsKey("sortby")) {
			sp.addSort((String) sorting.get("sortby"), (Boolean) sorting.get("order"));
		}
		return sp;
	}

	public String buildUnicityRequest(Node node) throws Exception {
		throw new Exception("Not implemented");
	}

	// strings stuff
	//	private IndexerAndSearcher indexerAndSearcherFactory;
	//	private TenantService tenantService;
	//	private NamespacePrefixResolver namespacePrefixResolver;
	//	private DictionaryService dictionaryService;

	private SearchService searchService;
	private SearchService admSearchService;

	public LuceneUnicityCheckerImpl() throws Exception {
		super();
	}

	public SearchService getSearchService() {
		return searchService;
	}

	public void setSearchService(SearchService searchService) {
		this.searchService = searchService;
	}

	public SearchService getAdmSearchService() {
		return admSearchService;
	}

	public void setAdmSearchService(SearchService admSearchService) {
		this.admSearchService = admSearchService;
	}

}
