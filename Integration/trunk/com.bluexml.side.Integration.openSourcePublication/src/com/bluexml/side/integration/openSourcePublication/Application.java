package com.bluexml.side.integration.openSourcePublication;
 
import java.io.File;
import java.io.IOException;

import com.bluexml.side.integration.openSourcePublication.utils.Util;

public class Application {

	public static void main(String[] args) {

		if (args.length != 0) {
			try {
				Util.doTreatment(new File(args[0]));
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("Indiquez un répertoire en entrée");
			System.exit(2);
		}

	}

}
