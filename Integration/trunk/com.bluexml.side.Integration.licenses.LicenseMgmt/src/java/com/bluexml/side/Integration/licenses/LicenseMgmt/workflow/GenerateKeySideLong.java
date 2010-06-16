package com.bluexml.side.Integration.licenses.LicenseMgmt.workflow;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.alfresco.repo.jscript.ScriptNode;
import org.alfresco.repo.security.authority.AuthorityDAO;
import org.alfresco.repo.workflow.jbpm.JBPMNode;
import org.alfresco.repo.workflow.jbpm.JBPMSpringActionHandler;
import org.alfresco.service.ServiceRegistry;
import org.alfresco.service.cmr.repository.ChildAssociationRef;
import org.alfresco.service.cmr.repository.NodeRef;
import org.alfresco.service.cmr.repository.NodeService;
import org.alfresco.service.cmr.security.PersonService;
import org.alfresco.service.namespace.QName;
import org.apache.log4j.Logger;
import org.jbpm.JbpmContext;
import org.jbpm.graph.def.ProcessDefinition;
import org.jbpm.graph.exe.ExecutionContext;
import org.jbpm.graph.exe.ProcessInstance;
import org.jbpm.graph.exe.Token;
import org.jbpm.svc.Services;
import org.sidelabs.is.common.StringXPath;
import org.springframework.beans.factory.BeanFactory;
import org.xml.sax.InputSource;

import com.bluexml.side.util.security.license.KeyGenerator;
import com.bluexml.side.util.security.license.SendMail;

@SuppressWarnings("unused")
public class GenerateKeySideLong extends JBPMSpringActionHandler {

	private static Logger logger = Logger.getLogger(GenerateKeySideLong.class);
	private ServiceRegistry		serviceRegistry;
	private NodeService nodeService;
	private PersonService personService; 
	private AuthorityDAO authorityDAO;
	private static final long	serialVersionUID	= 1L;
	
	private static final String QNAME_CODES =  "{http://www.bluexml.com/model/content/licenseKeyManagement/1.0}com_bluexml_side_util_security_license_query_codesComp";
	private static final String QNAME_IDCOMPTE = "{http://www.bluexml.com/model/content/licenseKeyManagement/1.0}com_bluexml_side_util_security_license_query_idCompte";
	private static final String QNAME_IDMACH = "{http://www.bluexml.com/model/content/licenseKeyManagement/1.0}com_bluexml_side_util_security_license_query_idMach";
	private static final String QNAME_FORMAT = "{http://www.bluexml.com/model/content/licenseKeyManagement/1.0}com_bluexml_side_util_security_license_query_format";
	private static final String QNAME_EMAIL = "{http://www.alfresco.org/model/content/1.0}email";
	
	private String XML_FILE_NAME = "constants.xml";
	
	private String from;
	private String subject;
	private int delayUser;
	private int delayAdmin;
	private String template_user;
	private String template_manager;
	private String manager;
	
	@SuppressWarnings("unchecked")
	private int setAttribute(String name) {
		
		try {
			InputStream inputStream = GetResourceAsStream(XML_FILE_NAME);
			InputSource inputSource = new InputSource(inputStream);
			String expressionAttr = "/root/side/generate_long/"+name;
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
				} else if (name.equals("delay_user")) {
					delayUser = Integer.parseInt(temp_attr);
				} else if (name.equals("delay_admin")) {
					delayAdmin = Integer.parseInt(temp_attr);
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
	
	@SuppressWarnings({ "unchecked", "static-access" })
	public void execute(ExecutionContext executionContext) throws Exception {

		//setup parameters
		setAttribute("from");
		setAttribute("subject");
		setAttribute("delay_user");
		setAttribute("delay_admin");
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
		ArrayList<String> codesComp2 = (ArrayList<String>) map.get(qnameCodesComp);
		for (int i=0; i<codesComp2.size();i++) {
			if(i==0) {
				codesComp = codesComp.concat(codesComp2.get(i).toString());
			} else {
				codesComp = codesComp.concat(","+codesComp2.get(i).toString());
			}
		}
		String format = (String) map.get(qnameFormat);
		
		//key gen user
		KeyGenerator keygen = new KeyGenerator(codesComp, delayUser, idCompte, idMach);
		String license = keygen.generateKey(codesComp, delayUser, idCompte, idMach);

		//key gen manager
		KeyGenerator keygen2 = new KeyGenerator(codesComp, delayAdmin, idCompte, idMach);
		String license2 = keygen.generateKey(codesComp, delayAdmin, idCompte, idMach);
		
		//construct templateMap
		HashMap<String,String> templateMap = new HashMap<String,String>();
		templateMap.put("value_key_user_generated", license);
		templateMap.put("value_key_manager_generated", license2);
		templateMap.put("user", toUser);
		templateMap.put("manager", manager);
		templateMap.put("type", "SIDE");
		
		//send mail user
		SendMail sendMail = new SendMail(from, toUser, subject, format, template_user, templateMap);
		sendMail.send();
		
		//send mail manager
		SendMail sendMail2 = new SendMail(from, manager, subject, format, template_manager, templateMap);
		sendMail2.send();
	}
	
	protected void initialiseHandler(BeanFactory bfac) {
		this.serviceRegistry = (ServiceRegistry) bfac.getBean("ServiceRegistry");
		this.nodeService = (NodeService)bfac.getBean("nodeService");
		this.authorityDAO = (AuthorityDAO)bfac.getBean("authorityDAO");
		this.personService = (PersonService)bfac.getBean("personService");
	}
	
	public static InputStream GetResourceAsStream(String file) throws Exception {
    	InputStream myurl = GenerateKeySideLong.class.getClassLoader().getResourceAsStream("com/bluexml/side/Integration/licenses/LicenseMgmt/resources/xml/"+file);
		return myurl;
	}
}
