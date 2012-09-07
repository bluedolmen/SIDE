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

import org.apache.commons.lang.StringUtils;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.plugin.EcorePlugin;

import com.bluexml.side.clazz.AbstractClass;
import com.bluexml.side.clazz.Aspect;
import com.bluexml.side.clazz.Association;
import com.bluexml.side.clazz.AssociationEnd;
import com.bluexml.side.clazz.Attribute;
import com.bluexml.side.clazz.ClassPackage;
import com.bluexml.side.clazz.Clazz;
import com.bluexml.side.clazz.EnumerationLiteral;
import com.bluexml.side.common.CustomDataType;
import com.bluexml.side.common.DataType;
import com.bluexml.side.common.MetaInfo;
import com.bluexml.side.common.ModelElement;
import com.bluexml.side.common.OperationComponent;
import com.bluexml.side.form.CharField;
import com.bluexml.side.form.ChoiceField;
import com.bluexml.side.form.ClassReference;
import com.bluexml.side.form.Field;
import com.bluexml.side.form.FormElement;
import com.bluexml.side.form.FormFactory;
import com.bluexml.side.form.ModelChoiceField;
import com.bluexml.side.form.ModelChoiceSearchField;

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
			Map<String, String> metaInfoMap = InitializeMetaInfo(att.getMetainfo());
			// Choice Field

			DataType typ = att.getTyp();
			if (typ.equals(DataType.CUSTOM)) {
				// map to handled type
				typ = getFieldForAlfrescoCustomType(att);
			}

			if (att.getValueList() != null) {
				field = FormFactory.eINSTANCE.createChoiceField();
				if (metaInfoMap.containsKey("multiple") && metaInfoMap.get("multiple") != null && metaInfoMap.get("multiple").equals("True")) {
					((ChoiceField) field).setMultiple(true);
				}
			} else if (typ.equals(DataType.STRING)) {
				// Email Field
				if (Boolean.parseBoolean(metaInfoMap.get("email"))) {
					field = FormFactory.eINSTANCE.createEmailField();
				} else {
					// Char Field
					field = FormFactory.eINSTANCE.createCharField();
					if (metaInfoMap.containsKey("max-length") && metaInfoMap.get("max-length") != null) {
						((CharField) field).setMax_length(Integer.parseInt(metaInfoMap.get("max-length")));
					}
					if (metaInfoMap.containsKey("min-length") && metaInfoMap.get("min-length") != null) {
						((CharField) field).setMin_length(Integer.parseInt(metaInfoMap.get("min-length")));
					}
				}
				// Date Time Field
			} else if (typ.equals(DataType.DATE_TIME)) {
				field = FormFactory.eINSTANCE.createDateTimeField();
				// Date Field
			} else if (typ.equals(DataType.DATE)) {
				field = FormFactory.eINSTANCE.createDateField();
				// Time Field
			} else if (typ.equals(DataType.TIME)) {
				field = FormFactory.eINSTANCE.createTimeField();
			} else if (typ.equals(DataType.BOOLEAN)) {
				// Boolean Field
				field = FormFactory.eINSTANCE.createBooleanField();
			} else if (typ.equals(DataType.INT)) {
				// Integer Field
				field = FormFactory.eINSTANCE.createIntegerField();
			} else if (typ.equals(DataType.LONG)) {
				// Long Field
				field = FormFactory.eINSTANCE.createIntegerField();
			} else if (typ.equals(DataType.FLOAT)) {
				// Float Field
				field = FormFactory.eINSTANCE.createFloatField();
			} else if (typ.equals(DataType.DOUBLE)) {
				// Decimal Field
				field = FormFactory.eINSTANCE.createDecimalField();
			} else if (typ.equals(DataType.SHORT)) {
				// Short Field
				field = FormFactory.eINSTANCE.createIntegerField();
			} else if (typ.equals(DataType.OBJECT)) {
				field = FormFactory.eINSTANCE.createCharField();
			} else if (typ.equals(DataType.CUSTOM)) {
				field = FormFactory.eINSTANCE.createCharField();
			} else {
				EcorePlugin.INSTANCE.log("No field available for " + typ);
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
				field.setHidden(Boolean.parseBoolean(metaInfoMap.get("hidden")));
				field.setHelp_text(att.getDescription());
				field.setMandatory(Boolean.parseBoolean(metaInfoMap.get("required")));
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
	public static ModelChoiceField getModelChoiceFieldForAssociation(Association ass, AbstractClass srcClazz) {
		ModelChoiceField f = FormFactory.eINSTANCE.createModelChoiceField();

		AssociationEnd target = setModelChoiceField(ass, srcClazz, f);

		//		if (useSource) {
		//			f.setMin_bound(Integer.parseInt(ass.getFirstEnd().getCardMin()));
		//			f.setMax_bound(Integer.parseInt(ass.getFirstEnd().getCardMax()));
		//		} else {
		f.setMin_bound(Integer.parseInt(target.getCardMin()));
		f.setMax_bound(Integer.parseInt(target.getCardMax()));
		//		}
		//		if (useSource) {
		//			f.setMandatory(ass.getFirstEnd().isMandatory());
		//		} else {
		f.setMandatory(target.isMandatory());
		//		}
		return f;
	}

	public static AssociationEnd setModelChoiceField(Association ass, AbstractClass srcClazz, FormElement f) {
		// we needs to get the target type
		AssociationEnd source = null;
		AssociationEnd target = null;

		EList<AbstractClass> sourceClass = ass.getSource();
		boolean twoWay = sourceClass.size() > 1;
		if (twoWay) {
			AssociationEnd firstEnd = ass.getFirstEnd();
			AssociationEnd secondEnd = ass.getSecondEnd();
			AbstractClass firstlinkedClass = firstEnd.getLinkedClass();
			if (firstlinkedClass.getAllSubTypes().contains(srcClazz) || firstlinkedClass.equals(srcClazz)) {
				source = firstEnd;
			} else {
				AbstractClass secondlinkedClass = secondEnd.getLinkedClass();
				if (secondlinkedClass.getAllSubTypes().contains(srcClazz) || secondlinkedClass.equals(srcClazz)) {
					source = secondEnd;
				} else {
					// error the given srcClazz is not in association ends hierarchy 
					throw new RuntimeException("the given srcClazz is not in association ends hierarchy, please validate your model");
				}
			}
		} else {
			EList<AssociationEnd> associationEnd = ass.getAssociationEnd(sourceClass.get(0));
			source = associationEnd.get(0);
		}

		target = source.getOpposite();
		AbstractClass second_linkedClass = target.getLinkedClass();

		String id = getAssociationName(ass, target);

		f.setId(id);
		f.setRef(ass);

		if (ass.getTitle() != null && ass.getTitle().length() > 0) {
			f.setLabel(ass.getTitle());
		} else {
			f.setLabel(ass.getName());
		}

		AbstractClass linkedClass = second_linkedClass;
		((ClassReference) f).setReal_class(linkedClass);

		return target;
	}

	public static ModelChoiceSearchField transformAssociationIntoModelChoiceSearchField(Association ass, AbstractClass srcClazz) {
		ModelChoiceSearchField f = FormFactory.eINSTANCE.createModelChoiceSearchField();

		setModelChoiceField(ass, srcClazz, f);

		return f;
	}

	/**
	 * Return Association Name
	 * 
	 * @param ass
	 * @param useSource
	 * @return
	 */
	public static String getAssociationName(Association ass, AssociationEnd target) {
		String id = ass.getName();

		id += StringUtils.trimToEmpty(target.getName());

		return id;
	}

	/**
	 * Return inherited Clazzs from a class
	 * 
	 * @param cl
	 * @return
	 */
	public static Collection<AbstractClass> getInheritedClazzs(AbstractClass cl) {
		Collection<AbstractClass> listClazz = new ArrayList<AbstractClass>();
		listClazz.add(cl);
		listClazz.addAll(cl.getInheritedClasses());
		return listClazz;
	}

	protected static Map<AbstractClass, SortedSet<AbstractClass>> inheritings = null;

	/**
	 * Internal class, used in order to have sorted list of Clazz
	 * 
	 * @author Eric
	 */
	public static class ClazzComparator implements Comparator<AbstractClass> {

		public int compare(AbstractClass c1, AbstractClass c2) {
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
	public static SortedSet<AbstractClass> getDescendantClazzs(AbstractClass cl) {
		// if (inheritings == null) {
		SortedSet<AbstractClass> allClazzs = getAllClazzs(cl);
		inheritings = new HashMap<AbstractClass, SortedSet<AbstractClass>>();

		for (AbstractClass c : allClazzs) {
			Collection<AbstractClass> generalisations = getInheritedClazzs(c);
			for (AbstractClass gc : generalisations) {
				if (!inheritings.containsKey(gc)) {
					inheritings.put(gc, new TreeSet<AbstractClass>(new ClazzComparator()));
				}
				inheritings.get(gc).add(c);
			}
		}
		// }
		return inheritings.get(cl);
	}

	public static Map<String, String> InitializeMetaInfo(EList<MetaInfo> metainfo) {
		Map<String, String> metaInfoMap = new HashMap<String, String>(metainfo.size());
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
	public static Collection<? extends String> getChoices(EList<EnumerationLiteral> list) {
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
	public static Set<AbstractClass> getClazzsForExpand(Clazz c) {
		Collection<AbstractClass> allClazzs = ClassDiagramUtils.getAllClazzs(c);
		Map<AbstractClass, Set<AbstractClass>> inheritings = new HashMap<AbstractClass, Set<AbstractClass>>();
		// We iterate on each Clazzs of the model
		for (AbstractClass clazz : allClazzs) {
			Collection<AbstractClass> generalisations = getInheritedClazzs(clazz);
			// We iterate on
			for (AbstractClass gc : generalisations) {
				if (!inheritings.containsKey(gc)) {
					inheritings.put(gc, new HashSet<AbstractClass>());
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
	public static SortedSet<AbstractClass> getAllClazzs(AbstractClass c) {
		SortedSet<AbstractClass> s = new TreeSet<AbstractClass>(new ClazzComparator());
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
	public static List<ClassPackage> findAllPackage(AbstractClass c) {
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
			} else return null;
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
	public static HashMap<String, ModelElement> getClazzChild(Collection<Clazz> listClazz) {
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
				AssociationEnd firstEnd = ass.getFirstEnd();
				AssociationEnd secondEnd = ass.getSecondEnd();
				if (firstEnd.getLinkedClass().equals(cl) && secondEnd.isNavigable()) {
					listChild.put(ClassDiagramUtils.getAssociationName(ass, secondEnd), ass);
				}
				if (secondEnd.getLinkedClass().equals(cl) && firstEnd.isNavigable()) {
					listChild.put(ClassDiagramUtils.getAssociationName(ass, firstEnd), ass);
				}
			}
			for (Attribute att : cl.getAllAttributes()) {
				listChild.put(att.getName(), att);
			}
			for (OperationComponent op : cl.getOperations()) {
				listChild.put(op.getName(), op);
			}
		}
		return listChild;
	}

	static Map<String, DataType> m = null;

	public static DataType getFieldForAlfrescoCustomType(Attribute att) {
		if (m == null) {
			m = new HashMap<String, DataType>();

			m.put("d:any", DataType.STRING);
			m.put("d:mltext", DataType.STRING);
			m.put("d:qname", DataType.STRING);
			m.put("d:noderef", DataType.STRING);
			m.put("d:childassocref", DataType.STRING);
			m.put("d:assocref", DataType.STRING);
			m.put("cmis:html", DataType.STRING);
			m.put("cmis:uri", DataType.STRING);
			m.put("cmis:html", DataType.STRING);
		}

		CustomDataType customType = att.getCustomType();
		String logicalNameSpace = customType.getLogicalNameSpace().getPrefix();
		String name = customType.getName();
		String dtype = logicalNameSpace + ":" + name;
		DataType dataType2 = m.get(dtype);
		DataType dataType = dataType2 != null ? dataType2 : DataType.CUSTOM;
		return dataType;

	}
}
