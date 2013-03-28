/*
    Copyright (C) 2007-20013  BlueXML - www.bluexml.com

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU Affero General Public License as
    published by the Free Software Foundation, either version 3 of the
    License, or (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU Affero General Public License for more details.

    You should have received a copy of the GNU Affero General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/
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
