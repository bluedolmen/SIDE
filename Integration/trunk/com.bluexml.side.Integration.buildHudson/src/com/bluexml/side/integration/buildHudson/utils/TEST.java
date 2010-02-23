package com.bluexml.side.integration.buildHudson.utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class TEST {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(Utils.getBuildPath());
		File testBuild = new File("/Users/davidabad/Workspace2.0/test/BuildAutoHudson/build.xml");
		FileWriter fw;
		try {
			testBuild.createNewFile();
			fw = new FileWriter(testBuild);
			//fw.write(Application.getCorpsBuild());
			fw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String workspace = "/Users/davidabad/.hudson/jobs/SIDE_Enterprise_Product_Builder/workspace";
		String workspace_2 = "/Users/davidabad/Workspace2.0";
		File product =new File(workspace_2+"/S-IDE/Integration/trunk/com.bluexml.side.Integration.eclipse.branding/side.product");
		File plugin_featureRepo = new File(workspace+"/sources");
		boolean changes = Utils.updateProduct(product, plugin_featureRepo);
		System.out.println(product+"changes :"+changes);
		
	}

}
