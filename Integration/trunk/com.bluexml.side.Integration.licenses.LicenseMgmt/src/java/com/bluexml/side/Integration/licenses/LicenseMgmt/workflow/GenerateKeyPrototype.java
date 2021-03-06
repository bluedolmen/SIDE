package com.bluexml.side.Integration.licenses.LicenseMgmt.workflow;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.alfresco.repo.workflow.jbpm.JBPMNode;
import org.alfresco.repo.workflow.jbpm.JBPMSpringActionHandler;
import org.alfresco.service.ServiceRegistry;
import org.alfresco.service.cmr.model.FileFolderService;
import org.alfresco.service.cmr.repository.ChildAssociationRef;
import org.alfresco.service.cmr.repository.NodeRef;
import org.alfresco.service.cmr.repository.NodeService;
import org.alfresco.service.namespace.QName;
import org.apache.log4j.Logger;
import org.jbpm.graph.exe.ExecutionContext;
import org.sidelabs.is.common.StringXPath;
import org.springframework.beans.factory.BeanFactory;
import org.xml.sax.InputSource;

import com.bluexml.side.util.security.license.KeyGenerator;
import com.bluexml.side.util.security.license.SendMail;

@SuppressWarnings("unused")
public class GenerateKeyPrototype extends JBPMSpringActionHandler {

	private static Logger logger = Logger.getLogger(GenerateKeyPrototype.class);
	private ServiceRegistry		serviceRegistry;
	private NodeService nodeService;
	private FileFolderService fileFolderService;
	private static final long	serialVersionUID	= 1L;
	
	private static final String QNAME_CODES =  "{http://www.bluexml.com/model/content/licenseKeyManagement/1.0}com_bluexml_side_util_security_license_query_codesComp";
	private static final String QNAME_IDCOMPTE = "{http://www.bluexml.com/model/content/licenseKeyManagement/1.0}com_bluexml_side_util_security_license_query_idCompte";
	private static final String QNAME_IDMACH = "{http://www.bluexml.com/model/content/licenseKeyManagement/1.0}com_bluexml_side_util_security_license_query_idMach";
	private static final String QNAME_FORMAT = "{http://www.bluexml.com/model/content/licenseKeyManagement/1.0}com_bluexml_side_util_security_license_query_format";
	private static final String QNAME_EMAIL = "{http://www.alfresco.org/model/content/1.0}email";
	
	private String XML_FILE_NAME = "constants.xml";
	
	private String from;
	private String subject;
	private int delay;
	private String template_user;
	private String template_manager;
	private String manager;
	
	@SuppressWarnings("unchecked")
	private int setAttribute(String name) {
		
		try {
			InputStream inputStream = GetResourceAsStream(XML_FILE_NAME);
			InputSource inputSource = new InputSource(inputStream);
			String expressionAttr = "/root/prototype/generate/"+name;
			String temp_attr = "";
			StringXPath stringXPath = new StringXPath();
			ArrayList resultAttr = stringXPath.recuperationValAttribut(inputSource, expressionAttr);
		
			if (resultAttr.size()==0) {
				return 0;
			} else {
				temp_attr = (String) resultAttr.get(0);
				if (name.equals("from")) {
					from = temp_attr;
				} else if (name.equals("subject")) {
					subject = temp_attr;
				} else if (name.equals("delay")) {
					delay = Integer.parseInt(temp_attr);
				} else if (name.equals("template_user")) {
					template_user = temp_attr;
				} else if (name.equals("template_manager")) {
					template_manager = temp_attr;
				} else if (name.equals("manager")) {
					manager = temp_attr;
				}
			}	
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
	
	@SuppressWarnings({ "static-access", "unchecked" })
	public void execute(ExecutionContext executionContext) throws Exception {
		
		//setup parameters
		setAttribute("from");
		setAttribute("subject");
		setAttribute("delay");
		setAttribute("template_user");
		setAttribute("template_manager");
		setAttribute("manager");
		
		//get node reference
		Object res = executionContext.getContextInstance().getVariable("bpm_package");
		NodeRef tplNodeRef = ((JBPMNode) res).getNodeRef();
		List<ChildAssociationRef> childRefs = nodeService.getChildAssocs(tplNodeRef);
		NodeRef licenseQuery = childRefs.get(0).getChildRef();

		//create QNAMEs
		QName qnameEmail = QName.createQName(QNAME_EMAIL);
		QName qnameCodesComp = QName.createQName(QNAME_CODES);
		QName qnameIdCompte = QName.createQName(QNAME_IDCOMPTE);
		QName qnameIdMach = QName.createQName(QNAME_IDMACH);
		QName qnameFormat = QName.createQName(QNAME_FORMAT);
		
		//properties map
		Map map = nodeService.getProperties(licenseQuery);
		Object user = executionContext.getContextInstance().getVariable("initiator");
		NodeRef userNodeRef = ((JBPMNode) user).getNodeRef();
		Map map2 = nodeService.getProperties(userNodeRef);
		
		String toUser = (String) map2.get(qnameEmail);
		String idCompte = (String) map.get(qnameIdCompte);
		String idMach = (String) map.get(qnameIdMach);
		String codesComp = "";
		ArrayList codesComp2 = (ArrayList) map.get(qnameCodesComp);
		for(int i=0; i<codesComp2.size();i++) {
			if(i==0)
				codesComp = codesComp.concat(codesComp2.get(i).toString());
			else
				codesComp = codesComp.concat(","+codesComp2.get(i).toString());
		}
		String format = (String) map.get(qnameFormat);
		
		//key gen
		KeyGenerator keygen = new KeyGenerator(codesComp, delay, idCompte, idMach);
		String license = keygen.generateKey(codesComp, delay, idCompte, idMach);
		
		//construct templateMap
		HashMap<String,String> templateMap = new HashMap<String,String>();
		templateMap.put("value_key_user_generated", license);
		templateMap.put("value_key_manager_generated", license);
		templateMap.put("user", toUser);
		templateMap.put("manager", manager);
		templateMap.put("type", "Prototype");
		
		//send mail user
		SendMail sendMail = new SendMail(from, toUser, subject, format, template_user, templateMap);
		sendMail.send();
		
		//send mail admin
		SendMail sendMail2 = new SendMail(from, manager, subject, format, template_manager, templateMap);
		sendMail2.send();
		
	}
	
	protected void initialiseHandler(BeanFactory bfac) {
		this.serviceRegistry = (ServiceRegistry) bfac.getBean("ServiceRegistry");
		this.nodeService = ((NodeService)bfac.getBean("nodeService"));
		this.fileFolderService = ((FileFolderService)bfac.getBean("fileFolderService"));

	}
	
	public static InputStream GetResourceAsStream(String file) throws Exception {
    	InputStream myurl = GenerateKeyPrototype.class.getClassLoader().getResourceAsStream("com/bluexml/side/Integration/licenses/LicenseMgmt/resources/xml/"+file);
		return myurl;
	}

}
