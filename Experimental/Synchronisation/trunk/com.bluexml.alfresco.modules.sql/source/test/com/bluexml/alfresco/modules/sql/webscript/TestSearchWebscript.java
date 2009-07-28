package test.com.bluexml.alfresco.modules.sql.webscript;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.httpclient.Credentials;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.UsernamePasswordCredentials;
import org.apache.commons.httpclient.auth.AuthScope;
import org.apache.commons.httpclient.methods.GetMethod;

public class TestSearchWebscript {
	static final String REST_PROVIDER_ADDRESS = "http://localhost:8080";
	static final String REST_SEARCH_ENTRY_POINT = "/alfresco/service/sql/search";

	static final String USER = "bpajot";
	static final String PASSWORD = "Magali";
	
	private static String __call_rest_script(GetMethod method) {     
        HttpClient client = new HttpClient();
        String line = null;
        StringWriter buffer = new StringWriter();
        
        client.getParams().setAuthenticationPreemptive(true);
        Credentials defaultcreds = new UsernamePasswordCredentials(USER, PASSWORD);
		client.getState().setCredentials(AuthScope.ANY, defaultcreds);

        int statusCode = 0;
		try {
			statusCode = client.executeMethod(method);
	        if (statusCode != HttpStatus.SC_OK) {
		          System.err.println("Method failed: " + method.getStatusLine());
		          //System.err.println(method.getStatusText());
		        }
		    InputStream rstream = null;
			rstream = method.getResponseBodyAsStream();
	        // Process the response 
	        BufferedReader br = new BufferedReader(new InputStreamReader(rstream));
			while ((line = br.readLine()) != null) {
				buffer.append(line);
			}
			br.close();
		} catch (HttpException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        		        				
        return buffer.toString();
	}

	private static String __call_alfresco(Map<String, String> arguments) {
		String request = REST_PROVIDER_ADDRESS + REST_SEARCH_ENTRY_POINT ;
		
		if (arguments.size() > 0) {
			request += "?";
		}
		for (Entry<String, String> e : arguments.entrySet()) {
			request += e.getKey() + "=" + e.getValue() + "&";
		}
		request = request.substring(0, request.length()-1);
		
		GetMethod method = new GetMethod(request);
		method.setDoAuthentication(true);
		String output = __call_rest_script(method);
		return output;
	}

	
	private static void __loadFile(String filename_, ByteArrayOutputStream output_) {
		//StringWriter writer = new StringWriter();
		try {
			FileInputStream is = new FileInputStream(filename_);
			//BufferedReader buffer = new BufferedReader(new InputStreamReader(is));
			byte[] buffer = new byte[1024];
			//ByteArrayOutputStream outputData = new ByteArrayOutputStream();
			
			//String line = "";
			int len = 0;
			while (-1 != (len = is.read(buffer) ) ) {
				output_.  write(buffer, 0, len);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void testSimpleType(String typeName) {
		//ByteArrayOutputStream dataStream = new ByteArrayOutputStream();
		//__loadFile(filename_, dataStream);
		Map<String, String> getArguments_ = new HashMap<String, String>();
		getArguments_.put("type", typeName);
		String response = __call_alfresco(getArguments_);
		
		System.out.println("Response: " + response);
	}
	
}
