package com.bluexml.side.form.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import org.eclipse.emf.common.util.EList;

import com.bluexml.side.clazz.AbstractContainer;
import com.bluexml.side.clazz.Aspect;
import com.bluexml.side.clazz.Association;
import com.bluexml.side.clazz.Attribute;
import com.bluexml.side.clazz.AttributeType;
import com.bluexml.side.clazz.ClassPackage;
import com.bluexml.side.clazz.Clazz;
import com.bluexml.side.clazz.EnumerationLiteral;
import com.bluexml.side.clazz.MetaInfo;
import com.bluexml.side.clazz.Operation;
import com.bluexml.side.common.ModelElement;
import com.bluexml.side.form.CharField;
import com.bluexml.side.form.ChoiceField;
import com.bluexml.side.form.Field;
import com.bluexml.side.form.FormElement;
import com.bluexml.side.form.ModelChoiceField;
import com.bluexml.side.form.formFactory;

public class ClassDiagramUtils {
	
	/**
	 * Will return the field corresponding to the attribute
	 * @param att
	 * @return
	 */
	public static Field getFieldForAttribute(Attribute att) {
		Field field = null;
		if (att != null) {
			Map<String, String> metaInfoMap = InitializeMetaInfo(att.getMetainfo());
			// Choice Field
			if (att.getValueList() != null) {
				field = formFactory.eINSTANCE.createChoiceField();
				((ChoiceField) field).getChoices().addAll(getChoices(att.getValueList().getLiterals()));
				if (metaInfoMap.containsKey("multiple") && metaInfoMap.get("multiple") != null && metaInfoMap.get("multiple").equals("True")) {
					((ChoiceField) field).setMultiple(true);
				}
			} else if (att.getTyp().equals(AttributeType.STRING)) { 
				// Email Field
				if (Boolean.parseBoolean(metaInfoMap.get("email"))) {
					field = formFactory.eINSTANCE.createEmailField();
				} else {
				// Char Field
					field = formFactory.eINSTANCE.createCharField();
					if (metaInfoMap.containsKey("max-length") && metaInfoMap.get("max-length") != null) {
						((CharField)field).setMax_length(Integer.parseInt(metaInfoMap.get("max-length")));
					}
					if (metaInfoMap.containsKey("min-length") && metaInfoMap.get("min-length") != null) {
						((CharField)field).setMin_length(Integer.parseInt(metaInfoMap.get("min-length")));
					}
				}
			// Date Time Field
			} else if (att.getTyp().equals(AttributeType.DATE_TIME)) {
				field = formFactory.eINSTANCE.createDateTimeField();
			// Date Field
			} else if (att.getTyp().equals(AttributeType.DATE)) {
				field = formFactory.eINSTANCE.createDateField();
			// Time Field
			} else if (att.getTyp().equals(AttributeType.TIME)) {
				field = formFactory.eINSTANCE.createTimeField();
			} else if(att.getTyp().equals(AttributeType.BOOLEAN)) {
			// Boolean Field
				field = formFactory.eINSTANCE.createBooleanField();
			} else if(att.getTyp().equals(AttributeType.INT)) {
			// Integer Field
				field = formFactory.eINSTANCE.createIntegerField();
			} else if(att.getTyp().equals(AttributeType.FLOAT)) {
			// Float Field
				field = formFactory.eINSTANCE.createFloatField();
			} else if(att.getTyp().equals(AttributeType.DOUBLE)) {
			// Decimal Field
				field = formFactory.eINSTANCE.createDecimalField();
			} 
			
			if (field == null) {
				//field = formFactory.eINSTANCE.createField();
			} else {
				field.setRef(att);
				field.setId(att.getName());
				if (att.getTitle() != null && att.getTitle().length() > 0) {
					field.setLabel(att.getTitle());
				} else {
					field.setLabel(att.getName());
				}
				field.setHidden(Boolean.parseBoolean(metaInfoMap.get("hidden")));
				field.setHelp_text(att.getDescription());
				field.setMandatory(Boolean.parseBoolean(metaInfoMap.get("required")));
				field.setInitial(att.getInitialValue());
				field.setId(att.getName());
			}
		}
		return field;
	}
	
	public static Field getFieldForOperation(Operation op) {
		Field f = null;
		if (op != null) {
			f = formFactory.eINSTANCE.createActionField();
			f.setId(op.getName());
			f.setLabel(op.getName());
			f.setRef(op);
		}
		return f;
	}
	
	/**
	 * Transform an association into a model choice field
	 * @param ass
	 * @param useSource
	 * @return
	 */
	public static FormElement transformAssociationIntoModelChoiceField(Association ass, boolean useSource) {
		ModelChoiceField f = formFactory.eINSTANCE.createModelChoiceField();
		
		String id = getAssociationName(ass, useSource);
		
		f.setId(id);
		f.setRef(ass);
		
		if (ass.getTitle() != null && ass.getTitle().length() > 0) {
			f.setLabel(ass.getTitle());
		} else {
			f.setLabel(ass.getName());
		}
		if (useSource) {
			f.setReal_class((Clazz)ass.getSource());
		} else {
			f.setReal_class((Clazz)ass.getDestination());
		}
		
		if (useSource) {
			f.setMin_bound(Integer.parseInt(ass.getMinSRC()));
			f.setMax_bound(Integer.parseInt(ass.getMaxSRC()));
		} else {
			f.setMin_bound(Integer.parseInt(ass.getMinTARGET()));
			f.setMax_bound(Integer.parseInt(ass.getMaxTARGET()));
		}
		
		// Association class
		if (ass.getAssociationsClass().size() > 0) {
			f.setAssociation_class(ass.getAssociationsClass().get(0));
		}
		
		return f;
	}
	
	/**
	 * Return Association Name
	 * @param ass
	 * @param useSource
	 * @return
	 */
	public static String getAssociationName(Association ass, boolean useSource) {
		String id = ass.getName();
		if (useSource && ass.getRoleSrc().length() > 0) {
			id += "." + ass.getRoleSrc();
		} else if(ass.getRoleTarget().length() > 0) {
			id += "." + ass.getRoleTarget();
		}
		return id;
	}
	
	/**
	 * Return inherited Clazzs from a class
	 * @param cl
	 * @return
	 */
	public static Collection<Clazz> getInheritedClazzs(Clazz cl) {
		Collection<Clazz> listClazz = new ArrayList<Clazz>();
		listClazz.add(cl);
		for(Clazz c : cl.getGeneralizations()) {
			listClazz.addAll(getInheritedClazzs((Clazz)c));
		}
		return listClazz;
	}
	
	protected static Map<Clazz, SortedSet<Clazz>> inheritings = null;
	
	/**
	 * Internal class, used in order to have sorted list of Clazz
	 * @author Eric
	 *
	 */
	public static class ClazzComparator implements Comparator<Clazz> {

		public int compare(Clazz c1, Clazz c2) {
			String name1, name2;
			name1 = ClassDiagramUtils.getLabel(c1);
			name2 = ClassDiagramUtils.getLabel(c2);
			return name1.compareToIgnoreCase(name2);
		}
		
	};
	
	/**
	 * Return all sub Clazzs
	 * @param cl
	 * @return
	 */
	public static SortedSet<Clazz> getDescendantClazzs(Clazz cl) {
		//if (inheritings == null) {
			SortedSet<Clazz> allClazzs = getAllClazzs(cl);
			inheritings = new HashMap<Clazz, SortedSet<Clazz>>();
			
			for (Clazz c : allClazzs) {
				Collection<Clazz> generalisations = getInheritedClazzs(c);
				for (Clazz gc : generalisations) {
					if (!inheritings.containsKey(gc)) {
						inheritings.put(gc, new TreeSet<Clazz>(new ClazzComparator()));
					}
					inheritings.get(gc).add(c);
				}
			}
		//}
		return inheritings.get(cl);
	}
	
	public static Map<String,String> InitializeMetaInfo(EList<MetaInfo> metainfo) {
		Map<String,String> metaInfoMap = new HashMap<String,String>(metainfo.size());
		for (MetaInfo m : metainfo) {
			metaInfoMap.put(m.getKey(), m.getValue());
		}
		return metaInfoMap;
	}
	
	/**
	 * Return a collection of choices for an Enumeration
	 * @param list
	 * @return
	 */
	public static Collection<? extends String> getChoices(EList<EnumerationLiteral> list) {
		List<String> choicesList = new ArrayList<String>();
		for (EnumerationLiteral enumerationLiteral : list) {
			choicesList.add(enumerationLiteral.getName());
		}
		return choicesList;
	}
	
	/**
	 * Return the label of an abstractContainer
	 * @param cont
	 * @return
	 */
	public static String getLabel(AbstractContainer cont) {
		String label = cont.getName();
		if (cont.getTitle() != null && cont.getTitle().length() > 0) {
			label = cont.getTitle();
		}
		return label;
	}
	
	/**
	 * Return all instanceable Clazzs that inherit from the current class
	 * @param c
	 * @return
	 */
	public static Set<Clazz> getClazzsForExpand(Clazz c) {
		Collection<Clazz> allClazzs = ClassDiagramUtils.getAllClazzs(c);
		Map<Clazz, Set<Clazz>> inheritings = new HashMap<Clazz, Set<Clazz>>();
		// We iterate on each Clazzs of the model
		for (Clazz clazz : allClazzs) {
			Collection<Clazz> generalisations = getInheritedClazzs(clazz);
			// We iterate on 
			for (Clazz gc : generalisations) {
				if (!inheritings.containsKey(gc)) {
					inheritings.put(gc, new HashSet<Clazz>());
				}
				inheritings.get(gc).add(clazz);
			}
		}
		return inheritings.get(c);
	}
	
	/**
	 * Get all class from the model
	 * @param c
	 * @return
	 */
	public static SortedSet<Clazz> getAllClazzs(Clazz c) {
		SortedSet<Clazz> s = new TreeSet<Clazz>(new ClazzComparator());
		List<ClassPackage> l = findAllPackage(c);
		for (ClassPackage p2 : l) {
			EList<Clazz> Clazzs = p2.getClassSet();
			s.addAll(Clazzs);
		}

		return s;
	}
	
	/**
	 * Get all package
	 * @param c
	 * @return
	 */
	public static List<ClassPackage> findAllPackage(Clazz c) {
		ClassPackage root = getRootPackage(c);
		return getAllChildrens(root);
	}
	
	/**
	 * Returns the root package
	 * @param elt
	 * @return
	 */
	public static ClassPackage getRootPackage(ModelElement elt) {
		if (elt == null) {
			return null;
		}
		if (elt.eContainer() == null) {
			if (elt instanceof Package) {
				ClassPackage p = (ClassPackage) elt;
				return p;
			} else
				return null;
		}
		if (elt.eContainer() instanceof ModelElement) {
			ModelElement me = (ModelElement) elt.eContainer();
			return getRootPackage(me);
		}
		return null;
	}
	
	/**
	 * Returns all children packages of the given package.
	 * @param p
	 * @return
	 */
	private static List<ClassPackage> getAllChildrens(ClassPackage p) {
		List<ClassPackage> l = new ArrayList<ClassPackage>();
		l.add(p);
		for (Object o : p.getPackageSet())
			if (o instanceof ClassPackage) {
				ClassPackage p2 = (ClassPackage) o;
				l.addAll(getAllChildrens(p2));
			}
		return l;
	}

	/**
	 * Return a hashmap with all child of the given Clazzs (attributes, aspects, operation, associations)
	 * @param listClazz
	 * @return
	 */
	public static HashMap<String, ModelElement> getClazzChild(Collection<Clazz> listClazz) {
		HashMap<String, ModelElement> listChild = new HashMap<String, ModelElement>();
		for (Clazz cl : listClazz) {
			for (Aspect asp : cl.getAspects()) {
				listChild.put(asp.getName(), asp);
				for (Attribute att : asp.getAttributes()) {
					listChild.put(att.getName(), att);
				}
			}
			for (Association ass : cl.getAssociations()) {
				if (ass.getSource().equals(cl) && ass.isIsNavigableTARGET()) {
					listChild.put(ClassDiagramUtils.getAssociationName(ass, false), ass);
				}
				if (ass.getDestination().equals(cl) && ass.isIsNavigableSRC()) {
					listChild.put(ClassDiagramUtils.getAssociationName(ass, true), ass);
				}
			}
			for (Attribute att : cl.getAttributes()) {
				listChild.put(att.getName(), att);
			}
			for (Operation op : cl.getOperations()) {
				listChild.put(op.getName(), op);
			}
		}
		return listChild;
	}
}
