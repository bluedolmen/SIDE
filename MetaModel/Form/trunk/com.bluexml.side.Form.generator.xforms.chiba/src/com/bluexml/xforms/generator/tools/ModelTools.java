package com.bluexml.xforms.generator.tools;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.commons.lang.StringUtils;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;

import com.bluexml.side.clazz.AbstractClass;
import com.bluexml.side.clazz.Aspect;
import com.bluexml.side.clazz.Association;
import com.bluexml.side.clazz.Attribute;
import com.bluexml.side.clazz.ClassPackage;
import com.bluexml.side.clazz.Clazz;
import com.bluexml.side.clazz.Enumeration;
import com.bluexml.side.common.MetaInfo;
import com.bluexml.side.clazz.TitledNamedClassModelElement;
import com.bluexml.side.common.DataType;
import com.bluexml.side.common.ModelElement;
import com.bluexml.side.common.NamedModelElement;
import com.bluexml.side.common.Package;
import com.bluexml.side.form.FormCollection;
import com.bluexml.side.form.FormContainer;

/**
 * The Class ModelTools.
 */
public class ModelTools {

	/**
	 * Gets the model.
	 * 
	 * @param resource
	 *            the resource
	 * 
	 * @return the model
	 */
	public static com.bluexml.side.common.Package getModel(Resource resource) {
		if (resource.getContents().size() > 0) {
			EObject object = resource.getContents().get(0);
			if (object instanceof com.bluexml.side.common.Package) {
				return (com.bluexml.side.common.Package) object;
			}
			return null;
		}
		return null;
	}

	/**
	 * Gets the form collection.
	 * 
	 * @param resource
	 *            the resource
	 * 
	 * @return the form collection
	 */
	public static FormCollection getFormCollection(Resource resource) {
		if (resource.getContents().size() > 0)
			return (FormCollection) resource.getContents().get(0);
		return null;
	}

	/**
	 * Package concate.
	 * 
	 * @param parent
	 *            the parent
	 * @param name
	 *            the name
	 * 
	 * @return the string
	 */
	public static String packageConcate(String parent, String name) {
		if (parent.equals("")) {
			return name;
		}
		return parent + "." + name;
	}

	/**
	 * Gets the package.
	 * 
	 * @param me
	 *            the me
	 * 
	 * @return the package
	 */
	public static String getPackage(ModelElement me) {
		String result = null;
		EObject parent = me.eContainer();
		if (me instanceof AbstractClass) {
			result = getCompleteName((ModelElement) parent);
		} else if (me instanceof Enumeration) {
			result = getCompleteName((ModelElement) parent);
		} else {
			result = getCompleteName(me);
		}
		return result;
	}

	/**
	 * Gets the complete name.
	 * 
	 * @param me
	 *            the me
	 * 
	 * @return the complete name
	 */
	public static String getCompleteName(FormContainer me) {
		String result = null;
		result = me.getId();
		return result;
	}

	/**
	 * Gets the complete name.
	 * 
	 * @param me
	 *            the me
	 * 
	 * @return the complete name
	 */
	public static String getCompleteName(ModelElement me) {
		String result = "";
		if (me instanceof TitledNamedClassModelElement) {
			result = ((TitledNamedClassModelElement) me).getFullName();
		}
		if (me instanceof NamedModelElement) {
			EObject parent = me.eContainer();
			String parentName = "";
			if (parent instanceof NamedModelElement
					|| parent instanceof TitledNamedClassModelElement) {
				parentName = getCompleteName((ModelElement) parent);
			}
			result = packageConcate(parentName, ((NamedModelElement) me).getName());
		}
		return result;
	}

	/**
	 * Gets the complete name jaxb.
	 * 
	 * @param classe
	 *            the classe
	 * 
	 * @return the complete name jaxb
	 */
	public static String getCompleteNameJAXB(ModelElement classe) {
		String completeName = getCompleteName(classe);
		return toJAXB(completeName);
	}

	/**
	 * To jaxb.
	 * 
	 * @param completeName
	 *            the complete name
	 * 
	 * @return the string
	 */
	public static String toJAXB(String completeName) {
		String[] split = completeName.split("\\.");
		StringBuffer sb = new StringBuffer("");
		for (String string : split) {
			sb.append(StringUtils.capitalize(string));
		}
		return sb.toString();
	}

	/**
	 * To xsd type.
	 * 
	 * @param value
	 *            the value
	 * 
	 * @return the string
	 */
	public static String toXSDType(DataType value) {
		switch (value.getValue()) {
			case DataType.BOOLEAN_VALUE: // '\0'
				return "boolean";
			case DataType.BYTE_VALUE: // '\001'
				return "byte";
			case DataType.CHAR_VALUE: // '\002'
				return "string";
			case DataType.DOUBLE_VALUE: // '\003'
				return "double";
			case DataType.FLOAT_VALUE: // '\004'
				return "float";
			case DataType.INT_VALUE: // '\005'
				return "integer";
			case DataType.LONG_VALUE: // '\006'
				return "long";
			case DataType.SHORT_VALUE: // '\007'
				return "short";
			case DataType.STRING_VALUE: // '\b'
				return "string";
			case DataType.DATE_VALUE: // '\n'
				return "date";
			case DataType.OBJECT_VALUE: // '\013'
				return "anyType";
			case DataType.DATE_TIME_VALUE: // '\f'
				return "dateTime";
			case DataType.TIME_VALUE: // '\r'
				return "time";
		}
		return null;
	}

	/**
	 * Gets the enums.
	 * 
	 * @param models
	 *            the models
	 * 
	 * @return the enums
	 */
	public static List<Enumeration> getAllEnums(List<Package> models) {
		List<Enumeration> enums = new ArrayList<Enumeration>();
		for (Package model : models) {
			collectEnums(model, enums);
		}
		return enums;
	}

	/**
	 * Collect enums.
	 * 
	 * @param p
	 *            the p
	 * @param enums
	 *            the enums
	 */
	private static void collectEnums(Package p, List<Enumeration> enums) {
		if (p instanceof ClassPackage) {
			enums.addAll(((ClassPackage) p).getEnumerationSet());
		}
		EList<Package> packageSet = p.getPackageSet();
		for (Package childp : packageSet) {
			collectEnums(childp, enums);
		}
	}

	/**
	 * Gets the aspects.
	 * 
	 * @param p
	 *            the p
	 * 
	 * @return the aspects
	 */
	public static List<Aspect> getAllAspects(Package p) {
		List<Aspect> aspects = new ArrayList<Aspect>();
		collectAspects(p, aspects);
		return aspects;
	}

	/**
	 * Gets the all aspects.
	 * 
	 * @param models
	 *            the models
	 * 
	 * @return the all aspects
	 */
	public static List<Aspect> getAllAspects(List<Package> models) {
		List<Aspect> aspects = new ArrayList<Aspect>();
		for (Package p : models) {
			collectAspects(p, aspects);
		}
		return aspects;
	}

	/**
	 * Collect aspects.
	 * 
	 * @param p
	 *            the p
	 * @param aspects
	 *            the aspects
	 */
	private static void collectAspects(Package p, List<Aspect> aspects) {
		if (p instanceof ClassPackage) {
			aspects.addAll(((ClassPackage) p).getAspectSet());
		}
		EList<Package> packageSet = p.getPackageSet();
		for (Package childp : packageSet) {
			collectAspects(childp, aspects);
		}
	}

	/**
	 * Gets the class aspects.
	 * 
	 * @param oblClasse
	 *            the obl classe
	 * 
	 * @return the class aspects
	 */
	public static Map<Aspect, Clazz> getClassAspects(Clazz oblClasse) {
		Map<Aspect, Clazz> aspects = new TreeMap<Aspect, Clazz>(AspectComparator.INSTANCE);
		collectAspects(oblClasse, aspects);
		return aspects;
	}

	/**
	 * Collect aspects.
	 * 
	 * @param oblClasse
	 *            the obl classe
	 * @param aspects
	 *            the aspects
	 */
	private static void collectAspects(Clazz oblClasse, Map<Aspect, Clazz> aspects) {
		EList<Aspect> ownClassAspects = oblClasse.getAspects();
		for (Aspect aspect : ownClassAspects) {
			aspects.put(aspect, oblClasse);
		}
		EList<Clazz> generalizations = oblClasse.getGeneralizations();
		for (Clazz generalization : generalizations) {
			if (generalization != null) {
				collectAspects(generalization, aspects);
			}
		}
	}

	/**
	 * Gets the class attributes.
	 * 
	 * @param oblClasse
	 *            the obl classe
	 * 
	 * @return the class attributes
	 */
	public static Map<Attribute, Clazz> getClassAttributes(Clazz oblClasse) {
		Map<Attribute, Clazz> attributes = new TreeMap<Attribute, Clazz>(
				AttributeComparator.INSTANCE);
		collectAttributes(oblClasse, attributes);
		return attributes;
	}

	/**
	 * Collect attributes.
	 * 
	 * @param oblClasse
	 *            the obl classe
	 * @param attributes
	 *            the attributes
	 */
	private static void collectAttributes(Clazz oblClasse, Map<Attribute, Clazz> attributes) {
		EList<Attribute> ownAttributes = oblClasse.getAttributes();
		for (Attribute attribute : ownAttributes) {
			attributes.put(attribute, oblClasse);
		}
		EList<Clazz> generalizations = oblClasse.getGeneralizations();
		for (Clazz generalization : generalizations) {
			if (generalization != null) {
				collectAttributes(generalization, attributes);
			}
		}
	}

	/**
	 * Gets the all associations.
	 * 
	 * @param models
	 *            the models
	 * 
	 * @return the all associations
	 */
	public static List<Association> getAllAssociations(List<Package> models) {
		List<Association> associations = new ArrayList<Association>();
		for (Package p : models) {
			collectAssociations(p, associations);
		}
		return associations;
	}

	/**
	 * Collect associations.
	 * 
	 * @param p
	 *            the p
	 * @param associations
	 *            the associations
	 */
	private static void collectAssociations(Package p, List<Association> associations) {
		if (p instanceof ClassPackage) {
			associations.addAll(((ClassPackage) p).getAssociationSet());
		}
		EList<Package> packageSet = p.getPackageSet();
		for (Package childp : packageSet) {
			collectAssociations(childp, associations);
		}
	}

	/**
	 * Gets the parent.
	 * 
	 * @param oblClasse
	 *            the obl classe
	 * 
	 * @return the parent
	 */
	public static Clazz getParent(Clazz oblClasse) {
		Clazz parent = null;
		EList<Clazz> generalizations = oblClasse.getGeneralizations();
		for (Clazz generalization : generalizations) {
			if (generalization != null) {
				parent = generalization;
			}
		}
		return parent;
	}

	/**
	 * Gets the classes.
	 * 
	 * @param models
	 *            the models
	 * 
	 * @return the classes
	 */
	public static List<Clazz> getAllClasses(List<Package> models) {
		List<Clazz> classes = new ArrayList<Clazz>();
		for (Package p : models) {
			collectClasses(p, classes);
		}
		return classes;
	}

	/**
	 * Collect classes.
	 * 
	 * @param p
	 *            the p
	 * @param classes
	 *            the classes
	 */
	private static void collectClasses(Package p, List<Clazz> classes) {
		if (p instanceof ClassPackage) {
			classes.addAll(((ClassPackage) p).getClassSet());
		}
		EList<Package> packageSet = p.getPackageSet();
		for (Package childp : packageSet) {
			collectClasses(childp, classes);
		}
	}

	/**
	 * Gets the association field.
	 * 
	 * @param association
	 *            the association
	 * @param inverse
	 *            the inverse
	 * 
	 * @return the association field
	 */
	public static String getAssociationField(Association association, boolean inverse) {
		// String sourceS = getCompleteName(source).replace('.', '_');
		// String targetS = getCompleteName(destination).replace('.', '_');

		// ModelElement source = null;
		// ModelElement destination = null;
		String role = null;
		if (!inverse) {
			role = association.getSecondEnd().getName();
			// source = association.getSource();
			// destination = association.getDestination();
		} else {
			role = association.getFirstEnd().getName();
			// source = association.getDestination();
			// destination = association.getSource();
		}
		// String sourceS = ((Clazz) source).getName();
		// String targetS = ((Clazz) destination).getName();
		String field = null;
		if (role != null && !role.equals("")) {
			// field = sourceS + "_" + association.getName() + "_" + role + "_"
			// + targetS;
			field = association.getName() + StringUtils.capitalize(role);
		} else {
			// field = sourceS + "_" + association.getName() + "_" + targetS;
			field = association.getName();
		}
		if (inverse) {
			field = "Inverse" + field;
		}
		return field;
	}

	/**
	 * Checks if is property.
	 * 
	 * @param attribute
	 *            the attribute
	 * @param property
	 *            the property
	 * 
	 * @return true, if is property
	 */
	public static boolean isProperty(Attribute attribute, String property) {
		boolean result = false;
		EList<MetaInfo> metas = attribute.getMetainfo();
		for (MetaInfo metaInfo : metas) {
			if (metaInfo.getKey().equals(property)
					&& (StringUtils.equalsIgnoreCase("True", metaInfo.getValue()))) {
				result = true;
			}
		}
		return result;
	}

	/**
	 * Gets the levels.
	 * 
	 * @param ac
	 *            the ac
	 * 
	 * @return the levels
	 */
	private static int getLevels(AbstractClass ac) {
		int result = 0;
		if (ac instanceof Clazz) {
			Clazz c = (Clazz) ac;
			EList<Clazz> generalizations = c.getGeneralizations();
			if (generalizations.size() > 0) {
				ModelElement target = c.getGeneralizations().get(0);
				if (target instanceof AbstractClass) {
					result = 1 + getLevels((AbstractClass) target);
				}
			}
		}
		return result;
	}

	/**
	 * Sort classes.
	 * 
	 * @param abstractClasses
	 *            the abstract classes
	 */
	public static void sortClasses(List<AbstractClass> abstractClasses) {
		Collections.sort(abstractClasses, new Comparator<AbstractClass>() {
			@SuppressWarnings("synthetic-access")
			public int compare(AbstractClass o1, AbstractClass o2) {
				return getLevels(o1) - getLevels(o2);
			}
		});
	}

	/**
	 * Gets the title.
	 * 
	 * @param abstractClass
	 *            the abstract class
	 * 
	 * @return the title
	 */
	public static String getTitle(AbstractClass abstractClass) {
		String result = null;
		if (abstractClass.getTitle() != null && !"".equals(abstractClass.getTitle())) {
			result = abstractClass.getTitle();
		} else {
			result = abstractClass.getName();
		}
		return result;
	}

	/**
	 * Gets the title.
	 * 
	 * @param attribute
	 *            the attribute
	 * 
	 * @return the title
	 */
	public static String getTitle(Attribute attribute) {
		String result = null;
		if (attribute.getTitle() != null && !"".equals(attribute.getTitle())) {
			result = attribute.getTitle();
		} else {
			result = attribute.getName();
		}
		return result;
	}

	/**
	 * Checks if is generalization of.
	 * 
	 * @param subClasse
	 *            the sub classe
	 * @param classe
	 *            the classe
	 * 
	 * @return true, if is generalization of
	 */
	public static boolean isGeneralizationOf(Clazz subClasse, Clazz classe) {
		boolean result = false;

		EList<Clazz> generalizations = subClasse.getGeneralizations();
		for (Clazz generalization : generalizations) {
			if (generalization != null) {
				Clazz parentClasse = generalization;
				if (parentClasse == classe) {
					result = true;
				} else {
					if (isGeneralizationOf(parentClasse, classe)) {
						result = true;
					}
				}
			}
		}
		return result;
	}

	/**
	 * Gets all forms.
	 * 
	 * @param formCollections
	 *            the form collections
	 * 
	 * @return all forms, whatever the subclasses of FormContainer
	 */
	public static List<FormContainer> getAllForms(List<FormCollection> formCollections) {
		List<FormContainer> result = new ArrayList<FormContainer>();
		for (FormCollection formCollection : formCollections) {
			EList<FormContainer> forms = formCollection.getForms();
			result.addAll(forms);
		}
		return result;
	}

	/**
	 * Get the workflow forms, from the given workflow form collection.
	 * 
	 * @param formCollections
	 *            the form collections
	 * 
	 * @return the all forms
	 */
	/**
	 * 
	 * @param attribute
	 * @param key
	 * @return The value of <b>case-insensitive</b> metainfo "key" for "attribute", or null if "key"
	 *         is not present, or empty string if "key" is present but has no value.
	 */
	public static String getMetaInfoValue(Attribute attribute, String key) {
		String result = null;
		EList<MetaInfo> metas = attribute.getMetainfo();

		for (MetaInfo metaInfo : metas) {
			if (StringUtils.equalsIgnoreCase(metaInfo.getKey(), key)) {
				result = metaInfo.getValue();
				if (result == null) {
					result = "";
				}
				return result; // bail out as early as possible
			}
		}
		return result;
	}
}
