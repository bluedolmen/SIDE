package com.bluexml.side.form.clazz.utils;

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
import org.eclipse.emf.ecore.plugin.EcorePlugin;

import com.bluexml.side.clazz.Aspect;
import com.bluexml.side.clazz.Association;
import com.bluexml.side.clazz.Attribute;
import com.bluexml.side.common.DataType;
import com.bluexml.side.clazz.ClassPackage;
import com.bluexml.side.clazz.Clazz;
import com.bluexml.side.clazz.EnumerationLiteral;
import com.bluexml.side.common.Comment;
import com.bluexml.side.common.MetaInfo;
import com.bluexml.side.common.Operation;
import com.bluexml.side.common.ModelElement;
import com.bluexml.side.common.OperationComponent;
import com.bluexml.side.common.Stereotype;
import com.bluexml.side.form.CharField;
import com.bluexml.side.form.ChoiceField;
import com.bluexml.side.form.Field;
import com.bluexml.side.form.FormElement;
import com.bluexml.side.form.ModelChoiceField;
import com.bluexml.side.form.FormFactory;

public class ClassDiagramUtils {

	/**
	 * Will return the field corresponding to the attribute
	 *
	 * @param att
	 * @return
	 */
	public static Field getFieldForAttribute(Attribute att) {
		Field field = null;
		if (att != null) {
			Map<String, String> metaInfoMap = InitializeMetaInfo(att
					.getMetainfo());
			// Choice Field
			if (att.getValueList() != null) {
				field = FormFactory.eINSTANCE.createChoiceField();
				if (metaInfoMap.containsKey("multiple")
						&& metaInfoMap.get("multiple") != null
						&& metaInfoMap.get("multiple").equals("True")) {
					((ChoiceField) field).setMultiple(true);
				}
			} else if (att.getTyp().equals(DataType.STRING)) {
				// Email Field
				if (Boolean.parseBoolean(metaInfoMap.get("email"))) {
					field = FormFactory.eINSTANCE.createEmailField();
				} else {
					// Char Field
					field = FormFactory.eINSTANCE.createCharField();
					if (metaInfoMap.containsKey("max-length")
							&& metaInfoMap.get("max-length") != null) {
						((CharField) field).setMax_length(Integer
								.parseInt(metaInfoMap.get("max-length")));
					}
					if (metaInfoMap.containsKey("min-length")
							&& metaInfoMap.get("min-length") != null) {
						((CharField) field).setMin_length(Integer
								.parseInt(metaInfoMap.get("min-length")));
					}
				}
				// Date Time Field
			} else if (att.getTyp().equals(DataType.DATE_TIME)) {
				field = FormFactory.eINSTANCE.createDateTimeField();
				// Date Field
			} else if (att.getTyp().equals(DataType.DATE)) {
				field = FormFactory.eINSTANCE.createDateField();
				// Time Field
			} else if (att.getTyp().equals(DataType.TIME)) {
				field = FormFactory.eINSTANCE.createTimeField();
			} else if (att.getTyp().equals(DataType.BOOLEAN)) {
				// Boolean Field
				field = FormFactory.eINSTANCE.createBooleanField();
			} else if (att.getTyp().equals(DataType.INT)) {
				// Integer Field
				field = FormFactory.eINSTANCE.createIntegerField();
			} else if (att.getTyp().equals(DataType.LONG)) {
				// Long Field
				field = FormFactory.eINSTANCE.createIntegerField();
			} else if (att.getTyp().equals(DataType.FLOAT)) {
				// Float Field
				field = FormFactory.eINSTANCE.createFloatField();
			} else if (att.getTyp().equals(DataType.DOUBLE)) {
				// Decimal Field
				field = FormFactory.eINSTANCE.createDecimalField();
			} else if (att.getTyp().equals(DataType.SHORT)) {
				// Short Field
				field = FormFactory.eINSTANCE.createIntegerField();
			} else {
				EcorePlugin.INSTANCE.log("No field available for "
						+ att.getTyp());
			}

			if (field == null) {
				// field = formFactory.eINSTANCE.createField();
			} else {
				field.setRef(att);
				field.setId(att.getName());
				if (att.getTitle() != null && att.getTitle().length() > 0) {
					field.setLabel(att.getTitle());
				} else {
					field.setLabel(att.getName());
				}
				if (att.getMockup().size() > 0) {
					field.getMockup().addAll(att.getMockup());
				}
				field.setHidden(Boolean.parseBoolean(metaInfoMap
								.get("hidden")));
				field.setHelp_text(att.getDescription());
				field.setMandatory(Boolean.parseBoolean(metaInfoMap
						.get("required")));
				field.setInitial(att.getInitialValue());
				field.setId(att.getName());
			}
		}
		return field;
	}

	public static Field getFieldForOperation(OperationComponent op) {
		Field f = null;
		if (op != null) {
			f = FormFactory.eINSTANCE.createActionField();
			f.setId(op.getName());
			f.setLabel(op.getName());
			f.setRef(op);
		}
		return f;
	}

	/**
	 * Transform an association into a model choice field 
	 *
	 * @param ass
	 * @param useSource
	 * @return
	 */
	public static FormElement transformAssociationIntoModelChoiceField(Association ass, Clazz srcClazz) {
		ModelChoiceField f = FormFactory.eINSTANCE.createModelChoiceField();

		// Modification since add of FirstEnd and SecondEnd : TODO : check if all case are OK. Or rewrite method.
		boolean useSource = true;
		if (ass.getFirstEnd().getLinkedClass().equals(srcClazz)) {
			useSource = false;
		}

		String id = getAssociationName(ass, useSource);

		f.setId(id);
		f.setRef(ass);

		if (ass.getTitle() != null && ass.getTitle().length() > 0) {
			f.setLabel(ass.getTitle());
		} else {
			f.setLabel(ass.getName());
		}
		if (useSource) {
			Clazz linkedClass = (Clazz)ass.getFirstEnd().getLinkedClass();
			f.setReal_class(linkedClass);
			f.setFormat_pattern(getViewForClass(linkedClass));
		} else {
			Clazz linkedClass = (Clazz)ass.getSecondEnd().getLinkedClass();
			f.setReal_class(linkedClass);
			f.setFormat_pattern(getViewForClass(linkedClass));
		}

		if (useSource) {
			f.setMin_bound(Integer.parseInt(ass.getFirstEnd().getCardMin()));
			f.setMax_bound(Integer.parseInt(ass.getFirstEnd().getCardMax()));
		} else {
			f.setMin_bound(Integer.parseInt(ass.getSecondEnd().getCardMin()));
			f.setMax_bound(Integer.parseInt(ass.getSecondEnd().getCardMax()));
		}



		return f;
	}

	/**
	 * Return Association Name
	 *
	 * @param ass
	 * @param useSource
	 * @return
	 */
	public static String getAssociationName(Association ass, boolean useSource) {
		String id = ass.getName();
		if (useSource && ass.getFirstEnd().getName().length() > 0) {
			id += ass.getFirstEnd().getName();
		} else if (ass.getSecondEnd().getName().length() > 0) {
			id += ass.getSecondEnd().getName();
		}
		return id;
	}

	/**
	 * Return inherited Clazzs from a class
	 *
	 * @param cl
	 * @return
	 */
	public static Collection<Clazz> getInheritedClazzs(Clazz cl) {
		Collection<Clazz> listClazz = new ArrayList<Clazz>();
		listClazz.add(cl);
		for (Clazz c : cl.getGeneralizations()) {
			listClazz.addAll(getInheritedClazzs((Clazz) c));
		}
		return listClazz;
	}

	protected static Map<Clazz, SortedSet<Clazz>> inheritings = null;

	/**
	 * Internal class, used in order to have sorted list of Clazz
	 *
	 * @author Eric
	 *
	 */
	public static class ClazzComparator implements Comparator<Clazz> {

		public int compare(Clazz c1, Clazz c2) {
			String name1, name2;
			name1 = c1.getLabel();
			name2 = c2.getLabel();
			return name1.compareToIgnoreCase(name2);
		}

	};

	/**
	 * Return all sub Clazzs
	 *
	 * @param cl
	 * @return
	 */
	public static SortedSet<Clazz> getDescendantClazzs(Clazz cl) {
		// if (inheritings == null) {
		SortedSet<Clazz> allClazzs = getAllClazzs(cl);
		inheritings = new HashMap<Clazz, SortedSet<Clazz>>();

		for (Clazz c : allClazzs) {
			Collection<Clazz> generalisations = getInheritedClazzs(c);
			for (Clazz gc : generalisations) {
				if (!inheritings.containsKey(gc)) {
					inheritings.put(gc, new TreeSet<Clazz>(
							new ClazzComparator()));
				}
				inheritings.get(gc).add(c);
			}
		}
		// }
		return inheritings.get(cl);
	}

	public static Map<String, String> InitializeMetaInfo(
			EList<MetaInfo> metainfo) {
		Map<String, String> metaInfoMap = new HashMap<String, String>(metainfo
				.size());
		for (MetaInfo m : metainfo) {
			metaInfoMap.put(m.getKey(), m.getValue());
		}
		return metaInfoMap;
	}

	/**
	 * Return a collection of choices for an Enumeration
	 *
	 * @param list
	 * @return
	 */
	public static Collection<? extends String> getChoices(
			EList<EnumerationLiteral> list) {
		List<String> choicesList = new ArrayList<String>();
		for (EnumerationLiteral enumerationLiteral : list) {
			choicesList.add(enumerationLiteral.getName());
		}
		return choicesList;
	}

	/**
	 * Return all instanceable Clazzs that inherit from the current class
	 *
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
	 *
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
	 *
	 * @param c
	 * @return
	 */
	public static List<ClassPackage> findAllPackage(Clazz c) {
		ClassPackage root = getRootPackage(c);
		return getAllChildrens(root);
	}

	/**
	 * Returns the root package
	 *
	 * @param elt
	 * @return
	 */
	public static ClassPackage getRootPackage(ModelElement elt) {
		if (elt == null) {
			return null;
		}
		if (elt.eContainer() == null) {
			if (elt instanceof ClassPackage) {
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
	 *
	 * @param p
	 * @return
	 */
	private static List<ClassPackage> getAllChildrens(ClassPackage p) {
		List<ClassPackage> l = new ArrayList<ClassPackage>();
		if (p != null) {
			l.add(p);
			for (Object o : p.getPackageSet()) {
				if (o instanceof ClassPackage) {
					ClassPackage p2 = (ClassPackage) o;
					l.addAll(getAllChildrens(p2));
				}
			}
		}
		return l;
	}

	/**
	 * Return a hashmap with all child of the given Clazzs (attributes, aspects,
	 * operation, associations)
	 *
	 * @param listClazz
	 * @return
	 */
	public static HashMap<String, ModelElement> getClazzChild(
			Collection<Clazz> listClazz) {
		HashMap<String, ModelElement> listChild = new HashMap<String, ModelElement>();
		for (Clazz cl : listClazz) {
			// TODO use OCL method
			for (Aspect asp : cl.getAspects()) {
				listChild.put(asp.getName(), asp);
				for (Attribute att : asp.getAttributes()) {
					listChild.put(att.getName(), att);
				}
			}
			for (Association ass : cl.getAllSourceAssociations()) {
				if (ass.getFirstEnd().getLinkedClass().equals(cl)
						&& ass.getSecondEnd().isNavigable()) {
					listChild.put(ClassDiagramUtils.getAssociationName(ass,
							false), ass);
				}
				if (ass.getSecondEnd().getLinkedClass().equals(cl)
						&& ass.getFirstEnd().isNavigable()) {
					listChild.put(ClassDiagramUtils.getAssociationName(ass,
							true), ass);
				}
			}
			for (Attribute att : cl.getAllInheritedAttributes()) {
				listChild.put(att.getName(), att);
			}
			for (OperationComponent op : cl.getOperations()) {
				listChild.put(op.getName(), op);
			}
		}
		return listChild;
	}

	public static String getViewForClass(Clazz clazz) {
		String result = null;
		for (Object o : clazz.getComments()) {
			Comment c = (Comment) o;
			for (Object obj : c.getStereotypes()) {
				if (obj instanceof Stereotype) {
					Stereotype s = (Stereotype) obj;
					if (s.getName().equalsIgnoreCase("view")) {
						result = c.getValue();
					}
				}
			}
		}
		return result;
	}
}
