package com.bluexml.side.deployer.alfresco.directcopy;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import org.alfresco.webservice.authentication.AuthenticationFault;
import org.alfresco.webservice.content.Content;
import org.alfresco.webservice.repository.RepositoryFault;
import org.alfresco.webservice.repository.UpdateResult;
import org.alfresco.webservice.types.CML;
import org.alfresco.webservice.types.CMLCreate;
import org.alfresco.webservice.types.ContentFormat;
import org.alfresco.webservice.types.NamedValue;
import org.alfresco.webservice.types.Node;
import org.alfresco.webservice.types.ParentReference;
import org.alfresco.webservice.types.Predicate;
import org.alfresco.webservice.types.Reference;
import org.alfresco.webservice.types.Store;
import org.alfresco.webservice.util.AuthenticationUtils;
import org.alfresco.webservice.util.Constants;
import org.alfresco.webservice.util.WebServiceFactory;
import org.apache.commons.io.FileUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.auth.AuthenticationException;
import org.apache.http.auth.Credentials;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.client.DefaultHttpClient;
import org.jdom.Document;
import org.jdom.Element;

import com.bluexml.side.util.libs.xml.XmlHelper;

public class AlfrescoHotDeployer extends AlfrescoAmpDirectDeployer {
	private static final String ALFRESCO_ADMIN_PWD = "alfresco.admin.pwd";
	private static final String ALFRESCO_ADMIN_LOGIN = "alfresco.admin.login";
	public static final String FORM_URL_ENCODED_CONTENT_TYPE = "application/x-www-form-urlencoded";
	public static final String CONFIGURATION_PARAMETER_ALFRESCO_URL = "alfresco.url";
	String rootServiceURL = "";
	
	

	@Override
	protected void postProcess(File fileToDeploy) throws Exception {
		// implements hot deployment
		try {
			reloadWebScripts();

			reloadWorkflowDefinition();

			reloadDataTypeDefinition();
		} catch (org.apache.http.conn.HttpHostConnectException e) {
			// server is offline
			System.out.println("Alfresco is offline, hot deployement fails");
			e.printStackTrace();
		}

	}

	private void reloadDataTypeDefinition() throws IOException {
		List<String> list = new ArrayList<String>();
		// search for processDefinition files
		for (String string : deployedFiles) {
			String regex = ".*/module/[^/]*/model/[^.]*.xml";
			String regex2 = ".*/module/[^/]*/bpm/model.xml";
			boolean matches = string.matches(regex);
			boolean matches2 = string.matches(regex2);
			if (matches || matches2) {
				list.add(string);
				System.out.println("model found :" + string);
			}
		}
		for (String filepath : list) {
			File model = new File(filepath);
			String modelRepoPath = "/app:company_home/app:dictionary/app:models";
			String moduleName = model.getParentFile().getParentFile().getName();
			String modelRepoName = moduleName + "_" + model.getName();

			// validate the xml file against XSD ?

			// upload dataType definition into /Company Home/Data Dictionary/Models
			// open connection to Alfresco
			try {
				AuthenticationUtils.startSession(getGenerationParameters().get(ALFRESCO_ADMIN_LOGIN),getGenerationParameters().get(ALFRESCO_ADMIN_PWD));
				Store store = new Store(Constants.WORKSPACE_STORE, "SpacesStore");

				ParentReference parentReference = new ParentReference(store, null, modelRepoPath, Constants.ASSOC_CONTAINS, Constants.createQNameString(Constants.NAMESPACE_CONTENT_MODEL, modelRepoName));

				// get the node
				String uuid = null;
				// model exists ?
				String modelPath = modelRepoPath + "/cm:" + modelRepoName;
				Reference r = new Reference(store, null, modelPath);
				System.out.println("search for :" + modelPath);
				try {
					Node[] n = WebServiceFactory.getRepositoryService().get(new Predicate(new Reference[] { r }, store, null));
					if (n.length > 0) {
						// node exist do update instead of create
						uuid = n[0].getReference().getUuid();
						System.out.println("Update model content in repository");
					}
				} catch (Exception e) {
					e.printStackTrace();
					// node do not exists
					System.out.println("Create model in repository");

					NamedValue[] props = new NamedValue[2];
					props[0] = new NamedValue("{http://www.alfresco.org/model/content/1.0}modelActive", false, "true", null);
					props[1] = new NamedValue("{http://www.alfresco.org/model/content/1.0}name", false, modelRepoName, null);
					CMLCreate create = new CMLCreate(null, parentReference, null, null, null, "{http://www.alfresco.org/model/content/1.0}content", props);
					CML cml = new CML();
					cml.setCreate(new CMLCreate[] { create });
					UpdateResult[] results = WebServiceFactory.getRepositoryService().update(cml);
					uuid = results[0].getDestination().getUuid();
				}

				// set node content

				ContentFormat format = new ContentFormat("text/xml", "UTF-8");
				// read xml file
				byte[] bytes = FileUtils.readFileToByteArray(model);
				// set content
				Content content = WebServiceFactory.getContentService().write(new Reference(store, uuid, null), Constants.PROP_CONTENT, bytes, format);
				System.out.println("model content updated " + content);
			} catch (AuthenticationFault e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (RepositoryFault e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				// job's done close session 
				AuthenticationUtils.endSession();
			}

		}
	}

	private void reloadWorkflowDefinition() throws Exception {
		// get the list of workflow process definition (search in deployed files)
		List<String> list = new ArrayList<String>();
		// search for processDefinition files
		for (String string : deployedFiles) {
			if (string.endsWith("processdefinition.xml")) {
				list.add(string);
				System.out.println("process :" + string);
			}
		}
		// now call service to deploy this definition
		String alfrescoURL = getGenerationParameters().get(CONFIGURATION_PARAMETER_ALFRESCO_URL);
		for (String string : list) {
			// comput the url
			HttpClient httpclient = new DefaultHttpClient();
			HttpPost post = new HttpPost(alfrescoURL + "/service/side/workflowhotdeploy?filepath=" + string);

			Credentials defaultcreds = new UsernamePasswordCredentials("admin", "admin");
			post.addHeader(new BasicScheme().authenticate(defaultcreds, post));

			post.addHeader("Content-Type", FORM_URL_ENCODED_CONTENT_TYPE + "; charset=UTF-8");
			String serializedDoc = executeRequest(httpclient, post);
			System.out.println(serializedDoc);
			// search for error
			Document buildJdomDocument = XmlHelper.buildJdomDocument(serializedDoc);
			Element rootElement = buildJdomDocument.getRootElement();
			String textTrim = rootElement.getChild("defId").getTextTrim();

			List<?> children = rootElement.getChildren("problem");
			if (children.size() > 0) {
				String errorRepport = "Problem while load process definition : " + textTrim + "\n";
				for (Object object : children) {
					Element el = (Element) object;
					errorRepport += el.getTextTrim() + "\n";
				}
				throw new Exception(errorRepport);
			} else {
				// ok
				System.out.println("workflow process definition succesfully loaded id : " + textTrim);
			}
		}
	}

	private void reloadWebScripts() throws AuthenticationException, Exception {
		String alfrescoURL = getGenerationParameters().get(CONFIGURATION_PARAMETER_ALFRESCO_URL);

		HttpClient httpclient = new DefaultHttpClient();
		HttpPost post = new HttpPost(alfrescoURL + "/service/?reset=on&submit=Refresh%20Web%20Scripts");

		Credentials defaultcreds = new UsernamePasswordCredentials("admin", "admin");
		post.addHeader(new BasicScheme().authenticate(defaultcreds, post));

		post.addHeader("Content-Type", FORM_URL_ENCODED_CONTENT_TYPE + "; charset=UTF-8");
		String executeRequest = executeRequest(httpclient, post);

		// search for error

		System.out.println(executeRequest);
	}

	private String executeRequest(HttpClient httpclient, HttpRequestBase post) throws Exception {
		String responseS = "";
		// Execute the request
		HttpResponse response;

		response = httpclient.execute(post);

		// Examine the response status
		StatusLine statusLine = response.getStatusLine();
		int statusCode = statusLine.getStatusCode();
		if (statusCode != 200) {
			throw new Exception("Request Fail HTTP response :" + statusLine);
		}
		System.out.println(response.getStatusLine());

		// Get hold of the response entity
		HttpEntity entity = response.getEntity();

		// If the response does not enclose an entity, there is no need
		// to worry about connection release
		if (entity != null) {
			InputStream instream = entity.getContent();
			try {

				BufferedReader reader = new BufferedReader(new InputStreamReader(instream));
				// do something useful with the response

				responseS = readBuffer(responseS, reader);

			} catch (IOException ex) {

				// In case of an IOException the connection will be released
				// back to the connection manager automatically
				throw ex;

			} catch (RuntimeException ex) {

				// In case of an unexpected exception you may want to abort
				// the HTTP request in order to shut down the underlying 
				// connection and release it back to the connection manager.

				post.abort();
				throw ex;

			} finally {

				// Closing the input stream will trigger connection release
				instream.close();

			}

			// When HttpClient instance is no longer needed, 
			// shut down the connection manager to ensure
			// immediate deallocation of all system resources
			httpclient.getConnectionManager().shutdown();
		}
		return responseS;
	}

	private String readBuffer(String responseS, BufferedReader reader) throws IOException {
		while (true) {
			String readLine = reader.readLine();
			if (readLine != null) {
				responseS += readLine + "\n";
			} else {
				break;
			}
		}
		return responseS;
	}

}
