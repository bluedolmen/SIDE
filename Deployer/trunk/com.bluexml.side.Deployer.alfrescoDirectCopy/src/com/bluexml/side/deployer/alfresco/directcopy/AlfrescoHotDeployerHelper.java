package com.bluexml.side.deployer.alfresco.directcopy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpRequestBase;

public class AlfrescoHotDeployerHelper {

	public static String executeRequest(HttpClient httpclient, HttpRequestBase post) throws Exception {
		String responseS = "";
		// Execute the request
		HttpResponse response;

		response = httpclient.execute(post);

		// Examine the response status
		StatusLine statusLine = response.getStatusLine();
		int statusCode = statusLine.getStatusCode();

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
		if (statusCode != 200) {
			throw new Exception("Request Fail HTTP response :" + statusLine);
		}
		return responseS;
	}

	private static String readBuffer(String responseS, BufferedReader reader) throws IOException {
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
