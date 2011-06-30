/*******************************************************************************
 * 	Copyright (C) BlueXML 2005-2008
 *
 * This program is free software; you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation; either version 2.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Boston, MA 02111.
 ******************************************************************************/
package com.bluexml.side.clazz.service.alfresco;

import java.util.List;

import org.eclipse.emf.common.util.EList;

import com.bluexml.side.Class.modeler.diagram.utils.metainfo.value.VisualTypeComponent_Enum;
import com.bluexml.side.clazz.AbstractClass;
import com.bluexml.side.clazz.Association;
import com.bluexml.side.clazz.Clazz;
import com.bluexml.side.common.Comment;
import com.bluexml.side.common.MetaInfo;
import com.bluexml.side.common.Stereotype;

public class ClassServices {

	public List<Association> getAllAssociationsByClass(Clazz cl) {
		List<Association> result = cl.getAllSourceAssociations();
		result.addAll(cl.getAllTargetAssociations());
		return result;
	}

	public static boolean useTabComponent(Clazz c) {
		for (Object obj : c.getMetainfo()) {
			if (obj instanceof MetaInfo) {
				MetaInfo mi = (MetaInfo) obj;
				if (mi.getKey().equalsIgnoreCase("visual-component")) {
					return mi.getValue().equalsIgnoreCase(VisualTypeComponent_Enum.HorizontalTab.toString()) || mi.getValue().equalsIgnoreCase(VisualTypeComponent_Enum.VerticalTab.toString());
				}
			}
		}
		return false;
	}

	public static boolean useSeperatorComponent(Clazz c) {
		for (Object obj : c.getMetainfo()) {
			if (obj instanceof MetaInfo) {
				MetaInfo mi = (MetaInfo) obj;
				if (mi.getKey().equalsIgnoreCase("visual-component")) {
					return mi.getValue().equalsIgnoreCase(VisualTypeComponent_Enum.Separator.toString());
				}
			}
		}
		return false;
	}

	public static boolean useVerticalTabComponent(Clazz c) {
		for (Object obj : c.getMetainfo()) {
			if (obj instanceof MetaInfo) {
				MetaInfo mi = (MetaInfo) obj;
				if (mi.getKey().equalsIgnoreCase("visual-component")) {
					return mi.getValue().equalsIgnoreCase(VisualTypeComponent_Enum.VerticalTab.toString());
				}
			}
		}
		return false;
	}

	public static boolean hasLabel(Clazz cl) {
		EList<?> list = cl.getComments();
		for (Object o : list) {
			if (o instanceof Comment) {
				Comment c = (Comment) o;
				EList<?> stereotypes = c.getStereotypes();
				for (Object o2 : stereotypes) {
					if (o2 instanceof Stereotype) {
						Stereotype s = (Stereotype) o2;
						if (s.getName().equalsIgnoreCase("view")) {
							return true;
						}
					}
				}
			}
		}
		return false;
	}

	public static String getLabelHTML(Clazz c1) {
		HTMLEncoder htmlEncoder = new HTMLEncoder();
		String label = c1.getLabel();
		label = htmlEncoder.encode(label);
		return label;
	}

	public static String getView(Clazz cl) {
		String resultLine = "";
		EList<?> list = cl.getComments();
		for (Object o : list) {
			if (o instanceof Comment) {
				Comment c = (Comment) o;
				EList<?> stereotypes = c.getStereotypes();
				for (Object o2 : stereotypes) {
					if (o2 instanceof Stereotype) {
						Stereotype s = (Stereotype) o2;
						if (s.getName().equalsIgnoreCase("view")) {
							String view = c.getValue();
							if (view.contains("\n")) {
								String firstLine = view.substring(0, view.lastIndexOf("\n") - 1);
								if (firstLine.startsWith("@actionsFile@")) {
									String othersLines = view.substring(firstLine.length() + 2);
									if (othersLines.contains("\n")) {
										String secondLine = othersLines.substring(0, view.lastIndexOf("\n") - 1);
										resultLine = secondLine;
									} else {
										resultLine = othersLines;
									}
								} else {
									resultLine = firstLine;
								}
							} else
								resultLine = view;
						}
					}
				}
			}
		}
		return resultLine.replaceAll("\\s", "");
	}

	public static boolean isFolder(AbstractClass cl2) {
		boolean is = false;
		if (cl2.getGeneralizations().size() > 0) {
			for (AbstractClass cl : cl2.getGeneralizations()) {
				if (("cm:folder").equals("cm:" + cl.getName())) {
					return true;
				} else {
					return isFolder(cl);
				}
			}
		}
		return is;
	}

	public static boolean isChildOfCmContent(Clazz c) throws Exception {
		EList<AbstractClass> l = c.getInheritedClasses();
		for (AbstractClass clazz : l) {
			String prefixedname = CommonServices.getPrefixedQName(clazz);
			if (prefixedname.equals("cm:content")) {
				return true;
			}
		}
		return false;
	}

	public static boolean isChildOfAlfrescoClazz(Clazz c) throws Exception {
		EList<AbstractClass> l = c.getInheritedClasses();
		for (AbstractClass clazz : l) {
			if (CommonServices.isNativeModel(clazz)) {
				return true;
			}
		}
		return false;
	}

}
