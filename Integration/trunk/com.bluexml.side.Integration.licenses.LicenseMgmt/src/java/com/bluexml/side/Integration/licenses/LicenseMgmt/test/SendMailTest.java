package com.bluexml.side.Integration.licenses.LicenseMgmt.test;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;

import org.sidelabs.is.common.StringXPath;
import org.xml.sax.InputSource;
import com.bluexml.side.util.security.license.SendMail;
import junit.framework.TestCase;

public class SendMailTest extends TestCase {

	/*
	 * 
	 * private parameters for xml test file access
	 * 
	 */
	private String XML_FILE_NAME = "test01.xml";
	
	/*
	 * 
	 * private parameters for sending mail
	 */
	private String from;
	private String subject;
	private String format;
	private String template_user;
	private String template_manager;
	private HashMap<String,String> templateMap = new HashMap<String,String>();
	
	/*
	 * 
	 * setParameters()
	 * 1- read values from xml file
	 * 2- set private parameters with retrieved values
	 * 
	 */
	private int setTestParameters() {
		
		int result1 = setAttr("from");
		int result3 = setAttr("subject");
		int result4 = setAttr("format");
		int result5 = setAttr("template_user");
		int result6 = setAttr("template_manager");
		
		int result7 = setMap("type");
		int result8 = setMap("user");
		int result9 = setMap("manager");
		int result10 = setMap("value_key_user_generated");
		int result11 = setMap("value_key_manager_generated");
		
		if (result1==0||result3==0||result4==0||result5==0||result6==0||result7==0||result8==0||result9==0||result10==0||result11==0) {
			return 1;
		} else {
			return 0;
		}
	}
	
	@SuppressWarnings("unchecked")
	private int setAttr(String name) {
		try {
			InputStream inputStream = GetResourceAsStream(XML_FILE_NAME);
			InputSource inputSource = new InputSource(inputStream);
			String expressionAttr = "/root/mail/"+name;
			String temp_attr = "";
			StringXPath stringXPath = new StringXPath();
			ArrayList resultAttr = stringXPath.recuperationValAttribut(inputSource, expressionAttr);
		
			if (resultAttr.size()==0) {
				return 0;
			} else {
				for (int i=0; i<resultAttr.size(); i++) {
					temp_attr = (String) resultAttr.get(i);
				}
				if (name.equals("from")) {
					from = temp_attr;
				} else if (name.equals("subject")) {
					subject = temp_attr;
				} else if (name.equals("format")) {
					format = temp_attr;
				} else if (name.equals("template_user")) {
					template_user = temp_attr;
				} else if (name.equals("template_manager")) {
					template_manager = temp_attr;
				}
			}	
			return 0;
		} catch (Exception e) {
			e.printStackTrace();
			return 1;
		}
	}
	
	@SuppressWarnings("unchecked")
	private int setMap(String name) {
		try {
			InputStream inputStream = GetResourceAsStream(XML_FILE_NAME);
			InputSource inputSource = new InputSource(inputStream);
			String expressionAttr = "/root/mail/template_map/"+name;
			String temp_attr = "";
			StringXPath stringXPath = new StringXPath();
			ArrayList resultAttr = stringXPath.recuperationValAttribut(inputSource, expressionAttr);
			
			if (resultAttr.size()==0) {
				return 1;
			} else {
				for (int i=0; i<resultAttr.size(); i++) {
					temp_attr = (String) resultAttr.get(i);
				}
				if (name.equals("type")) {
					templateMap.put("type", temp_attr);
				} else if (name.equals("user")) {
					templateMap.put("user", temp_attr);
				} else if (name.equals("manager")) {
					templateMap.put("manager", temp_attr);
				} else if (name.equals("value_key_user_generated")) {
					templateMap.put("value_key_user_generated", temp_attr);
				} else if (name.equals("value_key_manager_generated")) {
					templateMap.put("value_key_manager_generated", temp_attr);
				}
			}	
			return 0;
		} catch (Exception e) {
			e.printStackTrace();
			return 1;
		}
	}

	/*
	 * 
	 * testSend()
	 * 0- set parameters
	 * 1- send mail
	 * 
	 */
	public final void testSend() throws Exception {
		
		/*
		 * 0- set parameters
		 */
		int result0 = this.setTestParameters();
		assertEquals(result0,1);
		System.out.println("Parameters set.");
		
		/*
		 * 1- send mail (user)
		 */
		SendMail sendMail = new SendMail(
				this.from, 
				this.templateMap.get("user"),
				this.subject,
				this.format,
				this.template_user,
				this.templateMap);
		int result = sendMail.send();
		assertEquals(result,1);
		System.out.println("Email sent to "+templateMap.get("user"));
		/*
		 * 1- send mail (user)
		 */
		SendMail sendMail2 = new SendMail(
				this.from, 
				templateMap.get("manager"),
				this.subject,
				this.format,
				this.template_manager,
				this.templateMap);
		int result2 = sendMail2.send();
		assertEquals(result2,1);
		System.out.println("Email sent to "+templateMap.get("manager"));
		
		/*
		 * finished
		 */
		System.out.println("Test done.");
	}
	
	public static InputStream GetResourceAsStream(String file) throws Exception {
    	InputStream myurl = KeyGeneratorTest.class.getClassLoader().getResourceAsStream("com/bluexml/side/Integration/licenses/LicenseMgmt/test/xml/"+file);
		return myurl;
	}
}
