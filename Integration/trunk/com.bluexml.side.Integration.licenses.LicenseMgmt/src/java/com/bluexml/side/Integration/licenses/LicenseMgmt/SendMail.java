package com.bluexml.side.Integration.licenses.LicenseMgmt;

import java.io.InputStream;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.sidelabs.is.common.StringXPath;
import org.xml.sax.InputSource;

public class SendMail {

	/*
	 * 
	 * private parameters for connection to smtp mail server
	 * 
	 */
	private String SMTP_PROTOCOL;
	private String SMTP_HOST_NAME;
	private String SMTP_PORT;
	private String SMTP_USERNAME;
	private String SMTP_PASSWORD;
	private String SMTP_AUTHENTICATION;
	private String TEMPLATES_PATH;
	
	/*
	 * 
	 * private parameter for xml config file
	 * 
	 */
	private String XML_FILE_NAME = "mail.xml";
	
	
	/*
	 * 
	 * private parameters for mail options
	 * 
	 */
	private String from;
	private String to;
	private String subject;
	private String format;
	private String template;
	private HashMap<String,String> templateMap = new HashMap<String,String>();

    public SendMail(String from, String to, String subject, String format, String template, HashMap<String,String> templateMap) {
		this.from = from;
		this.to = to;
		this.subject = subject;
		this.format = format;
		this.template = template;
		this.templateMap = templateMap;
	}
	
    private int setMailParameters() {
    	
    	int result1 = setAttr("protocol");
		int result2 = setAttr("host");
		int result3 = setAttr("port");
		int result4 = setAttr("username");
		int result5 = setAttr("password");
		int result6 = setAttr("authentication");
		int result7 = setAttr("templates_path");
		
		int result8 = setMap();
		
		if (result1==0||result2==0||result3==0||result4==0||result5==0||result6==0||result7==0||result8==0) {
			return 0;
		} else {
			return 1;
		}
    }
    
    @SuppressWarnings("unchecked")
	private int setAttr(String name) {
    	try {
			InputStream inputStream = GetResourceAsStream(XML_FILE_NAME);
			InputSource inputSource = new InputSource(inputStream);
			String expressionAttr = "/root/mail_config/"+name;
			String temp_attr = "";
			StringXPath stringXPath = new StringXPath();
			ArrayList resultAttr = stringXPath.recuperationValAttribut(inputSource, expressionAttr);
		
			if (resultAttr.size()==0) {
				return 0;
			} else {
				for (int i=0; i<resultAttr.size(); i++) {
					temp_attr = (String) resultAttr.get(i);
				}
				if (name.equals("protocol")) {
					SMTP_PROTOCOL = temp_attr;
				} else if (name.equals("host")) {
					SMTP_HOST_NAME = temp_attr;
				} else if (name.equals("port")) {
					SMTP_PORT = temp_attr;
				} else if (name.equals("username")) {
					SMTP_USERNAME = temp_attr;
				} else if (name.equals("password")) {
					SMTP_PASSWORD = temp_attr;
				} else if (name.equals("authentication")) {
					SMTP_AUTHENTICATION = temp_attr;
				} else if (name.equals("templates_path")) {
					TEMPLATES_PATH = temp_attr;
				}
			}	
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
    }
    
    private int setMap() {
		return 1;
	}
    
    /*
     * 
     * send()
     * 1- smtp mail server connection
     * 2- check format
     * 3- write mail
     * 4- send mail
     * 
     */
	public int send() throws Exception{
		
		this.setMailParameters();
		
		Properties props = new Properties();
		
		props.put("mail.transport.protocol", SMTP_PROTOCOL);
		props.put("mail.smtp.host", SMTP_HOST_NAME);
		props.put("mail.smtp.port", SMTP_PORT);
		props.put("mail.smtp.auth", SMTP_AUTHENTICATION);
		
		Session mailSession = null;
		
		if(SMTP_AUTHENTICATION.equalsIgnoreCase("true")) {
			props.put("mail.smtp.starttls.enable", "true");
			Authenticator auth = new SMTPAuthenticator();
		    mailSession = Session.getDefaultInstance(props, auth);
		} else {
			mailSession = Session.getDefaultInstance(props);
		}
		
		Message simpleMessage = new MimeMessage(mailSession);
		
		InternetAddress fromAddress = null;
		InternetAddress toAddress = null;
		try {
			fromAddress = new InternetAddress(from);
			toAddress = new InternetAddress(to);
		} catch (AddressException e) {
			e.printStackTrace();
			return 0;
		}
		try {
			String templated = "";
			if (format.equals("html")) {
				templated = template+".html.ftl";
			} else {
				templated = template+".txt.ftl";
			}
						
			VelocityEngine ve = new VelocityEngine();

			Properties p = new Properties();
			p.setProperty("resource.loader", "class");
			p.setProperty("class.resource.loader.class","org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
	        ve.init(p);
			Template t;
	        String temp_format="text/html";
	        if(format.equals("html")) {
	        	t = ve.getTemplate( TEMPLATES_PATH+templated );
		        temp_format = "text/html";
	        } else {
	        	t = ve.getTemplate( TEMPLATES_PATH+templated );
	        	temp_format = "text/plain";
	        }
	        
	        VelocityContext context = new VelocityContext();
	        
	        for (String key : templateMap.keySet()) {
				context.put(key, templateMap.get(key));
			}
	        
	        StringWriter writer = new StringWriter();
	        t.merge( context, writer );
	        
	        simpleMessage.setFrom(fromAddress);
			simpleMessage.setRecipient(RecipientType.TO, toAddress);
			simpleMessage.setSubject(subject);
			simpleMessage.setContent(writer.toString(), temp_format);
			
			Transport.send(simpleMessage);
	        
			return 1;
		
		} catch (MessagingException e) {
			e.printStackTrace();
			return 0;
		}	
	}
	
	/*
	 * 
	 * private class SMTPAuthenticator
	 * provides authentication
	 * 
	 */
	private class SMTPAuthenticator extends javax.mail.Authenticator
	{
		public PasswordAuthentication getPasswordAuthentication()
	    {
	        String username = SMTP_USERNAME;
	        String password = SMTP_PASSWORD;
	        return new PasswordAuthentication(username, password);
	    }
	}
	
	public static InputStream GetResourceAsStream(String file) throws Exception {
    	InputStream myurl = SendMail.class.getClassLoader().getResourceAsStream("com/bluexml/side/Integration/licenses/LicenseMgmt/resources/xml/"+file);
		return myurl;
	}
}


