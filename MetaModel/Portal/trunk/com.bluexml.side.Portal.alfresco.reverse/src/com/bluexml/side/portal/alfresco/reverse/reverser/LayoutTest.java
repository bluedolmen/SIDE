package com.bluexml.side.portal.alfresco.reverse.reverser;

import java.io.File;
import java.io.IOException;
import java.util.List;

import com.bluexml.side.portal.PortalFactory;
import com.bluexml.side.portal.alfresco.reverse.reverser.data.Region;

public class LayoutTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		File page = new File(args[0]);
		try {
			LayoutReverser l = new LayoutReverser(page, PortalFactory.eINSTANCE.createPortal(), "TEST");
			l.parse();

			List<Region> regions = l.getRegions();
			System.out.println("REGIONS :");
			for (Region region : regions) {
				System.out.println("id :" + region.getRegionId());
				System.out.println("scope :" + region.getScope());
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
