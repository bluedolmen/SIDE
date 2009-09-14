package com.bluexml.side.Integration.alfresco.xforms.webscript;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import org.alfresco.repo.search.impl.lucene.ADMLuceneIndexerAndSearcherFactory;
import org.alfresco.repo.security.authentication.AuthenticationUtil;
import org.alfresco.repo.security.authority.AuthorityDAO;
import org.alfresco.repo.tenant.TenantService;
import org.alfresco.repo.transaction.RetryingTransactionHelper;
import org.alfresco.service.ServiceRegistry;
import org.alfresco.service.cmr.dictionary.DictionaryService;
import org.alfresco.service.cmr.dictionary.TypeDefinition;
import org.alfresco.service.cmr.repository.StoreRef;
import org.alfresco.service.namespace.NamespacePrefixResolver;
import org.alfresco.service.namespace.QName;
import org.alfresco.web.scripts.AbstractWebScript;
import org.alfresco.web.scripts.WebScriptRequest;
import org.alfresco.web.scripts.WebScriptResponse;
import org.alfresco.web.scripts.servlet.WebScriptServletRequest;
import org.apache.commons.lang.StringUtils;

public class XFormsWebscript extends AbstractWebScript {

	private Map<String, List<QName>> subTypesMapCache = new HashMap<String, List<QName>>();
	private Map<QName, QName> parentsMapCache = null;
	private ServiceRegistry serviceRegistry;
	private ADMLuceneIndexerAndSearcherFactory indexerAndSearcherFactory;
	private TenantService tenantService;
	private DictionaryService dictionaryService;
	private NamespacePrefixResolver namespacePrefixResolver;
	private RetryingTransactionHelper transactionHelper;
	private AuthorityDAO authorityDAO;
	private StoreRef storeRef = new StoreRef("workspace://SpacesStore");
	
	public enum XFormsQueryType {
			delete,
			upload,
			service,
			workflow,
			read,
			list,
			enum_,
			labels,
			createPath,
			addToPackage,
			batch;
	}

	public synchronized List<QName> getSubTypes(String type) {
		List<QName> list = subTypesMapCache.get(type);
		if (list == null) {
			if (parentsMapCache == null) {
				parentsMapCache = new HashMap<QName, QName>();
				Collection<QName> allTypes = dictionaryService.getAllTypes();
				for (QName name : allTypes) {
					TypeDefinition atype = dictionaryService.getType(name);
					QName parentName = atype.getParentName();
					parentsMapCache.put(name, parentName);
				}
			}
			list = new ArrayList<QName>();
			QName qType = getQName(type, dictionaryService.getAllTypes());
			list.add(qType);
			Set<Entry<QName, QName>> entrySet = parentsMapCache.entrySet();
			collectChilds(qType, entrySet, list);
			subTypesMapCache.put(type, list);
		}
		return list;
	}

	private QName getQName(String type, Collection<QName> allTypes) {
		for (QName name : allTypes) {
			if (name.getLocalName().equals(type)) {
				return name;
			}
		}
		return null;
	}

	private void collectChilds(QName type, Set<Entry<QName, QName>> entrySet, List<QName> list) {
		for (Entry<QName, QName> entry : entrySet) {
			QName parentType = entry.getValue();
			if (parentType != null) {
				if (parentType.equals(type)) {
					QName childType = entry.getKey();
					list.add(childType);
					collectChilds(childType, entrySet, list);
				}
			}
		}
	}

	public StoreRef getStoreRef() {
		return storeRef;
	}

	public ServiceRegistry getServiceRegistry() {
		return serviceRegistry;
	}

	public void setServiceRegistry(ServiceRegistry serviceRegistry) {
		this.serviceRegistry = serviceRegistry;
	}

	public ADMLuceneIndexerAndSearcherFactory getIndexerAndSearcherFactory() {
		return indexerAndSearcherFactory;
	}

	public void setIndexerAndSearcherFactory(
			ADMLuceneIndexerAndSearcherFactory indexerAndSearcherFactory) {
		this.indexerAndSearcherFactory = indexerAndSearcherFactory;
	}

	public TenantService getTenantService() {
		return tenantService;
	}

	public void setTenantService(TenantService tenantService) {
		this.tenantService = tenantService;
	}

	public NamespacePrefixResolver getNamespacePrefixResolver() {
		return namespacePrefixResolver;
	}

	public RetryingTransactionHelper getTransactionHelper() {
		return transactionHelper;
	}

	public void setTransactionHelper(RetryingTransactionHelper transactionHelper) {
		this.transactionHelper = transactionHelper;
	}

	/**
	 * @param authorityDAO the authorityDAO to set
	 */
	public void setAuthorityDAO(AuthorityDAO authorityDAO) {
		this.authorityDAO = authorityDAO;
	}

	/**
	 * @return the authorityDAO
	 */
	public AuthorityDAO getAuthorityDAO() {
		return authorityDAO;
	}

	public void execute(WebScriptRequest webscriptrequest, WebScriptResponse webscriptresponse)
			throws IOException {
		WebScriptServletRequest webScriptServletRequest = (WebScriptServletRequest) webscriptrequest;
		HttpServletRequest httpServletRequest = webScriptServletRequest.getHttpServletRequest();
		String remoteAddr = httpServletRequest.getRemoteAddr();
		String localAddr = httpServletRequest.getLocalAddr();
		if (StringUtils.equalsIgnoreCase(remoteAddr, "127.0.0.1")
				|| StringUtils.equalsIgnoreCase(remoteAddr, localAddr)) {
			String username = webscriptrequest.getParameter("username");
			// FIXME temp patch
			if (StringUtils.trimToNull(username) == null) {
				username = "admin";
			}
			webscriptresponse.setContentType("text/xml");

			dictionaryService = serviceRegistry.getDictionaryService();
			namespacePrefixResolver = serviceRegistry.getNamespaceService();
			
			XFormsQueryType queryType = getQueryType(webscriptrequest);
			Map<String, String> parameters = getParameters(webscriptrequest);
			String result = AuthenticationUtil.runAs(new XFormsWork(this, queryType, parameters,
					getServiceRegistry()), username);

			OutputStream outputStream = webscriptresponse.getOutputStream();
			outputStream.write(result.getBytes());
		}
	}

	private Map<String, String> getParameters(WebScriptRequest webscriptrequest) {
		Map<String, String> result = new HashMap<String, String>();
		String[] parameterNames = webscriptrequest.getParameterNames();
		for (String parameterName : parameterNames) {
			result.put(parameterName, webscriptrequest.getParameter(parameterName));
		}
		return result;
	}

	private XFormsQueryType getQueryType(WebScriptRequest webscriptrequest) {
		XFormsQueryType result = XFormsQueryType.read;
		String query = webscriptrequest.getServicePath();
		// the opcodes must be the same as in the webscript description file
		if (query.endsWith("read")) {
			result = XFormsQueryType.read;
		}
		if (query.endsWith("batch")) {
			result = XFormsQueryType.batch;
		}
		if (query.endsWith("list")) {
			result = XFormsQueryType.list;
		}
		if (query.endsWith("delete")) {
			result = XFormsQueryType.delete;
		}
		if (query.endsWith("labels")) {
			result = XFormsQueryType.labels;
		}
		if (query.endsWith("enum")) {
			result = XFormsQueryType.enum_;
		}
		if (query.endsWith("workflow")) {
			result = XFormsQueryType.workflow;
		}
		if (query.endsWith("service")) {
			result = XFormsQueryType.service;
		}
		if (query.endsWith("package")) {
			result = XFormsQueryType.addToPackage;
		}
		if (query.endsWith("upload")) {
			result = XFormsQueryType.upload;
		}
		if (query.endsWith("mkdir")) {
			result = XFormsQueryType.createPath;
		}
		return result;
	}

}
