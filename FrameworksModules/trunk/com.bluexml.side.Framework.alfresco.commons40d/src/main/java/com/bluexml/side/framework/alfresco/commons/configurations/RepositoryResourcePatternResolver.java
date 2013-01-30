package com.bluexml.side.framework.alfresco.commons.configurations;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.alfresco.repo.security.authentication.AuthenticationUtil;
import org.alfresco.repo.security.authentication.AuthenticationUtil.RunAsWork;
import org.alfresco.service.ServiceRegistry;
import org.alfresco.service.cmr.repository.NodeRef;
import org.alfresco.service.cmr.repository.StoreRef;
import org.alfresco.service.cmr.search.SearchService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
/**
 * SIDE Extension
 * 
 * @author davidabad
 */
public class RepositoryResourcePatternResolver implements ResourcePatternResolver {
	/** The logger. */
	static protected Log logger = LogFactory.getLog(RepositoryResourcePatternResolver.class);

	static public String URL_PREFIX = "alfresco:";

	protected ResourcePatternResolver classPathResolver = new PathMatchingResourcePatternResolver();

	public RepositoryResourcePatternResolver(ServiceRegistry serviceRegistry) {
		this.serviceRegistry = serviceRegistry;
	}

	public Resource getResource(String location) {
		if (isSupportedPrefix(location)) {
			try {
				Resource[] resources = getResources(location);
				if (resources.length == 1) {
					return resources[0];
				}
			} catch (IOException e) {
				logger.error("unable to get resource", e);
			}
		} else if (isClassPathLocation(location)) {
			return classPathResolver.getResource(location);
		}
		return null;
	}

	protected boolean isClassPathLocation(String location) {
		return location.startsWith(ResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX) || location.startsWith(ResourcePatternResolver.CLASSPATH_URL_PREFIX);
	}

	protected boolean isSupportedPrefix(String location) {
		return location.startsWith(URL_PREFIX);
	}

	public ClassLoader getClassLoader() {
		return RepositoryResourcePatternResolver.class.getClassLoader();
	}

	public Resource[] getResources(final String locationPattern) throws IOException {

		logger.debug("getResources() load resources from :" + locationPattern);
		if (isSupportedPrefix(locationPattern)) {
			List<Resource> resources = AuthenticationUtil.runAs(new RunAsWork<List<Resource>>() {
				public List<Resource> doWork() throws Exception {
					String query = "PATH:\"" + locationPattern.replace(URL_PREFIX, "") + "\"";
					logger.debug("getResources() query :" + query);
					List<NodeRef> nodeRefs = serviceRegistry.getSearchService().query(StoreRef.STORE_REF_WORKSPACE_SPACESSTORE, SearchService.LANGUAGE_LUCENE, query).getNodeRefs();
					List<Resource> resources = new ArrayList<Resource>();
					for (NodeRef nodeRef : nodeRefs) {
						resources.add(new RepositoryResource(serviceRegistry, nodeRef));
						logger.debug("add resource :" + nodeRef);
					}
					return resources;
				}
			}, AuthenticationUtil.getSystemUserName());

			return resources.toArray(new Resource[] {});
		} else if (isClassPathLocation(locationPattern)) {
			return classPathResolver.getResources(locationPattern);
		}
		return null;
	}

	/** Alfresco services */
	private ServiceRegistry serviceRegistry;

}
