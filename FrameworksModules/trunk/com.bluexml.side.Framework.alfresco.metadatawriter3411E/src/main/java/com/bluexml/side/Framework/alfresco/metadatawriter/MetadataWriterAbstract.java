package com.bluexml.side.Framework.alfresco.metadatawriter;


import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import org.alfresco.error.AlfrescoRuntimeException;
import org.alfresco.model.ContentModel;
import org.alfresco.repo.workflow.jbpm.JBPMNode;
import org.alfresco.service.ServiceRegistry;
import org.alfresco.service.cmr.model.FileFolderService;
import org.alfresco.service.cmr.model.FileInfo;
import org.alfresco.service.cmr.repository.AssociationRef;
import org.alfresco.service.cmr.repository.ChildAssociationRef;
import org.alfresco.service.cmr.repository.NodeRef;
import org.alfresco.service.cmr.repository.NodeService;
import org.alfresco.service.namespace.QName;
import org.apache.log4j.Logger;
import org.redpill.alfresco.module.metadatawriter.factories.UnknownServiceNameException;
import org.redpill.alfresco.module.metadatawriter.factories.impl.MetadataServiceRegistryImpl;
import org.redpill.alfresco.module.metadatawriter.services.MetadataService;
import org.redpill.alfresco.module.metadatawriter.services.MetadataService.UpdateMetadataException;

import com.bluexml.side.framework.alfresco.commons.configurations.IConfigurationFile;


public abstract class MetadataWriterAbstract {
	private static Logger logger = Logger.getLogger(MetadataWriterAbstract.class);

	protected NodeService nodeService;
	protected FileFolderService fileFolderService;
	MetadataServiceRegistryImpl metadataServiceRegistryImpl;
	ServiceRegistry serviceRegistry;
	private MetadataWriterResolverImpl  resolver;
	
	protected final SimpleDateFormat dateFormat = new SimpleDateFormat(
			"dd/MM/yyyy");
	JBPMNode curnode = null;
	Properties mappingProperties = null;
	
	protected String NAMESPACEURI;
	protected String TYPE_ACCES;
	protected String FOLDER_DOCUMENT_VISA;
	protected String PROCESSUS;
	protected QName TYPE_FILE;
	protected QName ASSOC_FILE_MODELE_CONTENT;
	protected QName TYPE_VISA;
	protected QName PROP_QNAME_VISA_TYPEVISA;
	protected QName PROP_REF_DOC;
	
	protected abstract Map<QName, Serializable> getSpecialProperties();
	protected abstract Map<QName, Serializable> getSpecialVisaProperties();

	public void init(String processus) throws Exception {
			if (((IConfigurationFile)resolver).getDictionary().size() > 0) {
				PROCESSUS = getProcessus(processus);
				NAMESPACEURI = (String) resolver.resolve(PROCESSUS + ".mapping.qname.uri");
				FOLDER_DOCUMENT_VISA = (String) resolver.resolve(PROCESSUS + ".mapping.dossier.etape.name");
				TYPE_FILE = QName.createQName("{" + NAMESPACEURI +"}" + resolver.resolve(PROCESSUS + ".mapping.qname.file"));
				ASSOC_FILE_MODELE_CONTENT = QName.createQName("{" + NAMESPACEURI +"}" + resolver.resolve(PROCESSUS + ".mapping.qname.file.content"));
				TYPE_VISA = QName.createQName("{" + NAMESPACEURI +"}" + resolver.resolve(PROCESSUS + ".mapping.qname.visa"));
				PROP_QNAME_VISA_TYPEVISA = QName.createQName("{" + NAMESPACEURI +"}" + resolver.resolve(PROCESSUS + ".mapping.qname.typevisa"));
				PROP_REF_DOC = QName.createQName("{" + NAMESPACEURI +"}" + resolver.resolve(PROCESSUS + ".mapping.qname.refdoc"));
				TYPE_ACCES = (String) resolver.resolve(PROCESSUS + "");
			} else {
				logger.error("Le dictionnaire n'a pu être initialiser.");
				throw new Exception();
			}
	}

	private String getProcessus(String processus) throws Exception {
		String test = (String) resolver.resolve(processus + ".name");
		if (test != null && test.equals(processus)) {
			return processus;
		}
		logger.warn("Fichier de propriété du processus " + processus + "non trouvé. Utilisation de la configuration par défault."); 
		test = (String) resolver.resolve("default.name");
		if (test != null && test.equals("default")) {
			return "default";
		}
		logger.error("La configuration par défault n'a pu être trouvé.");
		throw new Exception();
	}
	public void execute(String processus, NodeRef entree) throws Exception {
		try {
			init(processus);
		} catch (Exception e) {
			throw new Exception("Error during initialisation of metadataWriter's configuration.");
		}
		if (logger.isDebugEnabled())
			logger.debug("curnode=" + entree);
		if (entree != null && nodeService.exists(entree)) {
			List<FileInfo> fileList = getFilesList(entree);
			for (FileInfo file : fileList) {
				if (logger.isDebugEnabled())
					logger.debug("search if necessary to inject metadata to the file = "
							+ file.getName());
				QName type = nodeService.getType(file.getNodeRef());
				if (type.equals(TYPE_FILE)) {
					Object obj = nodeService.getProperty(file.getNodeRef(), QName.createQName("http://www.alfresco.org/model/content/1.0", "content"));
					if (obj != null) {
						String content = obj.toString();
						String[] tmp = content.split("\\|");
						if (tmp != null && tmp.length>1) {
							tmp = tmp[1].split("\\=");
							String mimeType = tmp[1];
							if (logger.isDebugEnabled())logger.debug("mimetype is = " + mimeType);
							if (mimeType.equals("application/msword")) {
								// Check if the current file is associated with a model.
								List<AssociationRef> curNodeAssoc = nodeService
										.getTargetAssocs(file.getNodeRef(),
												ASSOC_FILE_MODELE_CONTENT);
								if (curNodeAssoc.size() == 1) {
									try {
										injectMetada(file.getNodeRef());
									} catch(Exception e) {
										throw e;
									}
								} else if (curNodeAssoc.size() > 1) {
									logger.error("file = " + file.getName()
											+ " is associated to more than one model");
									logger.error("It is not possible to inject metadata");
								}
							}
						}
					}
				}
			}
		} else {
			throw new Exception("Error during initialisation of metadataWriter's input folder.");
		}
	}

	private List<FileInfo> getFilesList(NodeRef documentsFolder) {
		List<FileInfo> result = new ArrayList<FileInfo>();
		List<ChildAssociationRef> myChildren = nodeService.getChildAssocs(documentsFolder);
		for(ChildAssociationRef myChild : myChildren) {
			if (nodeService.getType(myChild.getChildRef()).equals(ContentModel.TYPE_FOLDER) ) {
				List<FileInfo> contentFolder = getFilesList(myChild.getChildRef());
				if (!contentFolder.isEmpty()) result.addAll(contentFolder);
			} else {
				result.add(fileFolderService.getFileInfo(myChild.getChildRef()));
			}
		}
		return result;
	}
	protected void injectMetada(NodeRef fileNodeRef) throws Exception {
		
		Map<QName, Serializable> accesProperties = null;
		Map<QName, Serializable> columnDefinition = null;
		Map<QName, Serializable> specialProperties = null;
		Map<QName, Serializable> associationProperties = null;
		Map<QName, Serializable> properties = nodeService.getProperties(curnode
				.getNodeRef());
		
		specialProperties = getSpecialProperties();
		if (specialProperties != null) {
			properties.putAll(specialProperties);
		}
		
		associationProperties = getAssociationProperties();
		if (associationProperties.size() > 0) {
			properties.putAll(associationProperties);
		}
		
		try {
			columnDefinition = getColumnDefinition();
		} catch (Exception e) {
			throw new Exception("Error during column definition.");
		}
		properties.putAll(columnDefinition);
		
		properties = formatDate(properties);
		NodeRef acces =  nodeService.getChildByName(curnode.getNodeRef(), ContentModel.ASSOC_CONTAINS, FOLDER_DOCUMENT_VISA);
		if (acces != null) {
			 accesProperties = getAccesProperties(acces);
			 properties.putAll(accesProperties);
		}

		updateProperties(fileNodeRef, properties);
	}

	private Map<QName, Serializable> getAssociationProperties() {
		Map<QName, Serializable> associationProperties = new HashMap<QName, Serializable>();
		int i = 1;
		Serializable result = null;
		while(resolver.notNull(PROCESSUS + ".mapping.association" + i + ".name")) {
			List<AssociationRef> assoc = nodeService.getTargetAssocs(curnode.getNodeRef(), QName.createQName("{" + NAMESPACEURI +"}" + resolver.resolve(PROCESSUS + ".mapping.association" + i + ".nameassoc")));
			if (assoc.size() > 0) {
				String name = (String) resolver.resolve(PROCESSUS + ".mapping.association" + i + ".qname");
				String[] splittedName = name.split(":");
				if (splittedName.length > 0) {
					result = nodeService.getProperty(assoc.get(0).getTargetRef(), QName.createQName("{" + NAMESPACEURI +"}" + splittedName[1]));
					if (result != null) {
						associationProperties.put(QName.createQName("{" + NAMESPACEURI +"}" + splittedName[1]), result);
					}
				}
			}
			i++;
		}
		return associationProperties;
	}

	protected Map<QName, Serializable> getColumnDefinition() throws Exception {
		Map<QName, Serializable> columnDefinition = new HashMap<QName, Serializable>();
		int i = 1;
		while (resolver.notNull(PROCESSUS + ".colonne" + i)) {
			columnDefinition.put(QName.createQName("{" + NAMESPACEURI +"}" + "colonne" + i), resolver.resolve(PROCESSUS + ".colonne" + i));
			i++;
		}
		if (columnDefinition.isEmpty()) {
			logger.error("Pas de définition de colonne(s) disponible");
			throw new Exception();
		}
		return columnDefinition;
	}
	
	public Map<QName, Serializable> formatDate(
			Map<QName, Serializable> properties) {
		for (QName typeQName : properties.keySet()) {
			if(properties.get(typeQName) instanceof Date) {
				properties.put(typeQName, dateFormat.format(properties.get(typeQName)));
			}
		}
		return properties;
	}

	protected Map<QName, Serializable> getAccesProperties(NodeRef acces) {
		
		Map<String, Map<QName, Serializable>> etapesMap = new HashMap<String, Map<QName, Serializable>>();
		int i = 1;
		while(resolver.notNull(PROCESSUS + ".mapping.etape" + i + ".name")) {
			etapesMap.put((String) resolver.resolve(PROCESSUS + ".mapping.etape" + i + ".name"), null);
			i++;
		}
		Map<QName, Serializable> temp = null;
		Map<QName, Serializable> specialVisaProperties = null;
		
		List<ChildAssociationRef> listAcces = nodeService.getChildAssocs(acces);
		for (ChildAssociationRef monAcces : listAcces) {
			if (nodeService.getType(monAcces.getChildRef()).equals(TYPE_VISA)) {
				String type = (String) nodeService.getProperty(monAcces.getChildRef(), PROP_QNAME_VISA_TYPEVISA);
				temp = nodeService.getProperties(monAcces.getChildRef());
				specialVisaProperties = getSpecialVisaProperties();
				if (specialVisaProperties != null) {
					temp.putAll(specialVisaProperties);
				}
				if (etapesMap.containsKey(type)) {
					etapesMap.put(type, getEtapeAttributs(temp, etapesMap, type));
				}
			}
		}
		return concatEtapeAttributs(etapesMap);
	}

	public Map<QName, Serializable> getEtapeAttributs(
			Map<QName, Serializable> temp, 
			Map<String, Map<QName, Serializable>> etapesMap,
			String type) {
		
		Map<QName, Serializable> etapeTemp = etapesMap.get(type);
		if (etapeTemp == null) {
			etapeTemp = new HashMap<QName, Serializable>(temp);
		} else {
			for (QName key : temp.keySet()) {
				Serializable value = null;
				if (temp.get(key) instanceof Date) {
					if (etapeTemp.get(key) instanceof Date) {
					value = dateFormat.format(etapeTemp.get(key)) + ", " + dateFormat.format(temp.get(key));
					} else {
						value = etapeTemp.get(key) + ", " + dateFormat.format(temp.get(key));
					}
				} else {
					value = etapeTemp.get(key) + ", " + temp.get(key);
				}
				
				etapeTemp.put(key, value);
			}
		}
		return etapeTemp;
	}
	public Map<QName, Serializable> concatEtapeAttributs(
			Map<String, Map<QName, Serializable>> etapesMap) {

		Set<QName> qnameSet = new HashSet<QName>();
		int i = 1;
		while(resolver.notNull(PROCESSUS + ".mapping.att" + i + ".qname")) {
			qnameSet.add(QName.createQName(NAMESPACEURI, (String) resolver.resolve(PROCESSUS + ".mapping.att" + i + ".qname")));
			i++;
		}
		
		Map<QName, Serializable> result = new HashMap<QName, Serializable>();
		for (String etapeName : etapesMap.keySet()) {
			if (etapesMap.get(etapeName) != null) {
				for(QName key : etapesMap.get(etapeName).keySet()) {
					if (qnameSet.contains(key)) {
						String [] nameTemp = key.toString().split("_"); 
						if (etapesMap.get(etapeName).get(key) instanceof Date) {
							result.put(QName.createQName(NAMESPACEURI, "VisaEtape_" + etapeName + "_" + nameTemp[nameTemp.length - 1]), dateFormat.format(etapesMap.get(etapeName).get(key)));
						} else {
							result.put(QName.createQName(NAMESPACEURI, "VisaEtape_" + etapeName + "_" + nameTemp[nameTemp.length - 1]), etapesMap.get(etapeName).get(key));
						}
					} 
				}
			}
		}
		return result;
	}

	protected void updateProperties(final NodeRef node,
			final Map<QName, Serializable> properties) {
		try {
			final MetadataService s = metadataServiceRegistryImpl
					.findService("BxMetadaWriterService");
			s.write(node, properties);

		} catch (final UnknownServiceNameException e) {
			logger.warn("Could not find Metadata service named "
					+ "BxMetadaWriterService", e);
		} catch (final UpdateMetadataException ume) {
			throw new AlfrescoRuntimeException("Could not write properties "
					+ properties + " to node "
					+ nodeService.getProperty(node, ContentModel.PROP_NAME),
					ume);
		}
	}
	

	
	public ServiceRegistry getServiceRegistry() {
		return serviceRegistry;
	}

	public void setServiceRegistry(ServiceRegistry serviceRegistry) {
		this.serviceRegistry = serviceRegistry;
	}

	public NodeService getNodeService() {
		return nodeService;
	}

	public void setNodeService(NodeService nodeService) {
		this.nodeService = nodeService;
	}

	public FileFolderService getFileFolderService() {
		return fileFolderService;
	}

	public void setFileFolderService(FileFolderService fileFolderService) {
		this.fileFolderService = fileFolderService;
	}

	public MetadataServiceRegistryImpl getMetadataServiceRegistryImpl() {
		return metadataServiceRegistryImpl;
	}

	public void setMetadataServiceRegistryImpl(
			MetadataServiceRegistryImpl metadataServiceRegistryImpl) {
		this.metadataServiceRegistryImpl = metadataServiceRegistryImpl;
	}

	public void setCurnode(JBPMNode curnode) {
		this.curnode = curnode;
	}
	
	public MetadataWriterResolverImpl getResolver() {
		return resolver;
	}

	public void setResolver(MetadataWriterResolverImpl resolver) {
		this.resolver = resolver;
	}
	
	public String getNAMESPACEURI() {
		return NAMESPACEURI;
	}
	public String getFOLDER_DOCUMENT_VISA() {
		return FOLDER_DOCUMENT_VISA;
	}
	public QName getTYPE_FILE() {
		return TYPE_FILE;
	}
	public QName getASSOC_FILE_MODELE_CONTENT() {
		return ASSOC_FILE_MODELE_CONTENT;
	}
	public QName getTYPE_VISA() {
		return TYPE_VISA;
	}
	public QName getPROP_QNAME_VISA_TYPEVISA() {
		return PROP_QNAME_VISA_TYPEVISA;
	}
	public String getPROCESSUS() {
		return PROCESSUS;
	}
}
