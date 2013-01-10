package com.bluexml.side.framework.alfresco.commons.configurations;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;

import org.alfresco.model.ContentModel;
import org.alfresco.repo.security.authentication.AuthenticationUtil;
import org.alfresco.repo.security.authentication.AuthenticationUtil.RunAsWork;
import org.alfresco.service.ServiceRegistry;
import org.alfresco.service.cmr.repository.ContentReader;
import org.alfresco.service.cmr.repository.NodeRef;
import org.alfresco.service.cmr.repository.Path;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.core.io.Resource;

public class RepositoryResource implements Resource {
	/** The logger. */
	static protected Log logger = LogFactory.getLog(RepositoryResource.class);

	protected NodeRef nodeRef;
	protected Path path;

	private ContentReader reader;

	public RepositoryResource(final ServiceRegistry services, final NodeRef nodeRef) {
		this.services = services;
		this.nodeRef = nodeRef;
		this.reader = AuthenticationUtil.runAs(new RunAsWork<ContentReader>() {
			public ContentReader doWork() throws Exception {
				return services.getContentService().getReader(nodeRef, ContentModel.PROP_CONTENT);
			}
		}, AuthenticationUtil.getSystemUserName());
		
		this.path = AuthenticationUtil.runAs(new RunAsWork<Path>() {
			public Path doWork() throws Exception {
				return services.getNodeService().getPath(nodeRef);
			}
		}, AuthenticationUtil.getSystemUserName());
		
	}

	public InputStream getInputStream() throws IOException {
		return reader.getContentInputStream();
	}

	public boolean exists() {
		return AuthenticationUtil.runAs(new RunAsWork<Boolean>() {
			public Boolean doWork() throws Exception {
				return services.getNodeService().exists(nodeRef);
			}
		}, AuthenticationUtil.getSystemUserName());
	}

	public boolean isReadable() {
		return reader.exists();
	}

	public boolean isOpen() {
		return reader.isChannelOpen();
	}

	public URL getURL() throws IOException {
		throw new java.lang.UnsupportedOperationException("Not Yet Implemented");
		//		return new URL(services.getFileFolderService().getFileInfo(nodeRef).getContentData().getContentUrl());
	}

	public URI getURI() throws IOException {
		throw new java.lang.UnsupportedOperationException("Not Yet Implemented");
		//		try {
		//			return new URI(services.getFileFolderService().getFileInfo(nodeRef).getContentData().getContentUrl());
		//		} catch (URISyntaxException e) {
		//			throw new IOException(e);
		//		}
	}

	public File getFile() throws IOException {
		throw new java.lang.UnsupportedOperationException("Not Yet Implemented");

		//		return null;
	}

	public long lastModified() throws IOException {
		return AuthenticationUtil.runAs(new RunAsWork<Long>() {
			public Long doWork() throws Exception {
				return services.getFileFolderService().getFileInfo(nodeRef).getModifiedDate().getTime();
			}
		}, AuthenticationUtil.getSystemUserName());
	}

	public Resource createRelative(String relativePath) throws IOException {
		throw new java.lang.UnsupportedOperationException("Not Yet Implemented");
	}

	public String getFilename() {
		return AuthenticationUtil.runAs(new RunAsWork<String>() {
			public String doWork() throws Exception {
				return services.getFileFolderService().getFileInfo(nodeRef).getName();
			}
		}, AuthenticationUtil.getSystemUserName());
	}

	public String getDescription() {
		return AuthenticationUtil.runAs(new RunAsWork<String>() {
			public String doWork() throws Exception {
				return (String) services.getNodeService().getProperty(nodeRef, ContentModel.PROP_DESCRIPTION);
			}
		}, AuthenticationUtil.getSystemUserName());
	}

	
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return AuthenticationUtil.runAs(new RunAsWork<String>() {
			public String doWork() throws Exception {
				return path.toPrefixString(services.getNamespaceService());
			}
		}, AuthenticationUtil.getSystemUserName());
	}



	/** Alfresco services */
	private ServiceRegistry services;

}
