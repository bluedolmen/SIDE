package com.bluexml.side.view.edit.ui.utils.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.command.RemoveCommand;
import org.eclipse.emf.edit.domain.EditingDomain;

import com.bluexml.side.clazz.Attribute;
import com.bluexml.side.clazz.Clazz;
import com.bluexml.side.common.DataType;
import com.bluexml.side.common.MetaInfo;
import com.bluexml.side.common.ModelElement;
import com.bluexml.side.view.AbstractView;
import com.bluexml.side.view.AbstractViewOf;
import com.bluexml.side.view.Col;
import com.bluexml.side.view.DataTable;
import com.bluexml.side.view.Field;
import com.bluexml.side.view.FieldElement;
import com.bluexml.side.view.ViewFactory;
import com.bluexml.side.view.ViewPackage;
import com.bluexml.side.view.edit.ui.utils.FieldTransformation;

public class ClassUtils {
	
	/**
	 * Return a field for a given view. Will add specific informations if needed (like column for DataTable)
	 * @param a
	 * @param view
	 * @return
	 */
	public static FieldElement getField(Attribute a, AbstractView view) {
		FieldElement result = null;
		Field f = getFieldForAttribute(a);
		// Special case for DataTable
		if (view instanceof DataTable) {
			Col col = ViewFactory.eINSTANCE.createCol();
			col.getChildren().add(f);
			col.setName(f.getName());
			result = col;
		} else {
			result = f;
		}
		return result;
	}
	
	/**
	 * Return a field corresponding to the given attribute
	 * @param att
	 * @return
	 */
	static public Field getFieldForAttribute(Attribute att) {
		Field field = null;
		if (att != null) {
			Map<String, String> metaInfoMap = InitializeMetaInfo(att.getMetainfo());
			
			if (att.getTyp().equals(DataType.STRING)) { 
				// Email Field
				if (Boolean.parseBoolean(metaInfoMap.get("email"))) {
					field = ViewFactory.eINSTANCE.createEmailField();
				} else {
				// Char Field
					field = ViewFactory.eINSTANCE.createTextField();
				}
			// Date Time Field
			} else if (att.getTyp().equals(DataType.DATE_TIME)) {
				field = ViewFactory.eINSTANCE.createDateTimeField();
			// Date Field
			} else if (att.getTyp().equals(DataType.DATE)) {
				field = ViewFactory.eINSTANCE.createDateField();
			// Time Field
			} else if (att.getTyp().equals(DataType.TIME)) {
				field = ViewFactory.eINSTANCE.createTimeField();
			} else if(att.getTyp().equals(DataType.BOOLEAN)) {
			// Boolean Field
				field = ViewFactory.eINSTANCE.createBooleanField();
			} else if(att.getTyp().equals(DataType.INT)) {
			// Integer Field
				field = ViewFactory.eINSTANCE.createIntegerField();
			} else if(att.getTyp().equals(DataType.LONG)) {
			// Long Field
				field = ViewFactory.eINSTANCE.createIntegerField();
			} else if(att.getTyp().equals(DataType.FLOAT)) {
			// Float Field
				field = ViewFactory.eINSTANCE.createFloatField();
			} else if(att.getTyp().equals(DataType.DOUBLE)) {
			// Decimal Field
				//field = ViewFactory.eINSTANCE.createDecimalField();
				field = ViewFactory.eINSTANCE.createFloatField();
			} else if(att.getTyp().equals(DataType.SHORT)) {
			// Short Field
				field = ViewFactory.eINSTANCE.createIntegerField();
			} else {
				EcorePlugin.INSTANCE.log("No field available for " + att.getTyp());
			}
			if (field != null) {
				field.setName(att.getName());
				field.setMapTo(att);
			}
		}
		return field;
	}
	
	/**
	 * Return a map with Key / Value for a given metaInfo
	 * @param metainfo
	 * @return
	 */
	public static Map<String,String> InitializeMetaInfo(EList<MetaInfo> metainfo) {
		Map<String,String> metaInfoMap = new HashMap<String,String>(metainfo.size());
		for (MetaInfo m : metainfo) {
			metaInfoMap.put(m.getKey(), m.getValue());
		}
		return metaInfoMap;
	}
	
	/**
	 * Synchronize a view with class.
	 * @param view
	 * @param domain
	 * @return
	 */
	public static Command synchronizeView(AbstractViewOf view, EditingDomain domain) {
		CompoundCommand cmd = new CompoundCommand();
		ModelElement element = view.getViewOf();
		if (element instanceof Clazz) {
			// Collect information on class and view :
			EList<Field> fields = view.getDisabledAndEnabledField();
			
			Clazz c = (Clazz) view.getViewOf();
			EList<Attribute> attributes = c.getAllAttributes();
			// #1 : Search things to add (new in Class)
			List<Attribute> toAdd = getAddedAttributes(fields,attributes);
			// #2 : Search things to delete (deleted in Class)
			List<FieldElement> toDelete = getFieldsToDelete(fields,attributes);
			// #3 : Search thing to changes
			List<Attribute> toChange = getChangedAttributes(fields,attributes);
			
			// Create needed command
			CompoundCommand delCmd = getDeleteCommand(domain, toDelete, view);
			if (!delCmd.isEmpty()) {
				cmd.append(delCmd);
			}
			CompoundCommand addCmd = getAddCommand(domain, toAdd, view);
			if (!addCmd.isEmpty()) {
				cmd.append(addCmd);
			}
			CompoundCommand uptCmd = getModificationCommand(domain,toChange,view,fields);
			if (!uptCmd.isEmpty()) {
				cmd.append(uptCmd);
			}
		}
		return cmd;
	}

	/**
	 * Create the method to change field (change = remove & add)
	 * @param domain
	 * @param toChange
	 * @param view
	 * @param fields 
	 */
	private static CompoundCommand getModificationCommand(EditingDomain domain,
			List<Attribute> toChange, AbstractView view, EList<Field> fields) {
		CompoundCommand cmd = new CompoundCommand();
		List<FieldElement> toDelete = new ArrayList<FieldElement>();
		for (Attribute a : toChange) {
			Iterator<Field> it = fields.iterator();
			while (it.hasNext()) {
				Field f = it.next();
				if (f.getMapTo().equals(a) && f.getPath().size() == 0) {
					toDelete.add(f);
				}
			}
		}
		cmd.append(getDeleteCommand(domain, toDelete, view));
		cmd.append(getAddCommand(domain, toChange, view));
		return cmd;
	}

	/**
	 * Create command to delete given fields
	 * @param domain
	 * @param toDelete
	 * @param view 
	 * @return
	 */
	private static CompoundCommand getDeleteCommand(EditingDomain domain,
			List<FieldElement> toDelete, AbstractView view) {
		CompoundCommand cmd = new CompoundCommand();
		if (toDelete.size() > 0) {
			if (view instanceof DataTable) {
				List<FieldElement> newList = new ArrayList<FieldElement>();
				for (FieldElement f : toDelete){
					newList.add((FieldElement)f.eContainer());
				}
				toDelete = newList;
			}
			cmd.append(RemoveCommand.create(domain, toDelete));
		}
		return cmd;
	}
	
	/**
	 * Create command to add given field
	 * @param domain
	 * @param toAdd
	 * @param view 
	 * @return
	 */
	private static CompoundCommand getAddCommand(EditingDomain domain, List<Attribute> toAdd, AbstractView view) {
		CompoundCommand cmd = new CompoundCommand();
		for (Attribute a : toAdd) {
			FieldElement f = ClassUtils.getField(a, view);
			cmd.append(AddCommand.create(domain, view, ViewPackage.eINSTANCE.getFieldContainer_Children(), f));
		}
		return cmd;
	}

	/**
	 * Will check if attribute have been modified (here only type modification), return the new Field 
	 * @param fields
	 * @param attributes
	 * @return
	 */
	private static List<Attribute> getChangedAttributes(EList<Field> fields,
			EList<Attribute> attributes) {
		List<Attribute> result = new ArrayList<Attribute>();
		for (Attribute a : attributes) {
			Iterator<Field> it = fields.iterator();
			while (it.hasNext()) {
				Field f = it.next();
				if (f.getMapTo().equals(a)) {
					Field newField = ClassUtils.getFieldForAttribute(a);
					if (!FieldTransformation.getAvailableTransformation(newField).contains(f.eClass().getName())) {
						result.add(a);
					}
				}
			}
		}
		return result;
	}

	/**
	 * Compare fields with attribute and return the field that have to be deleted
	 * @param fields
	 * @param attributes
	 * @return
	 */
	private static List<FieldElement> getFieldsToDelete(EList<Field> fields,
			EList<Attribute> attributes) {
		List<FieldElement> result = new ArrayList<FieldElement>();
		for (Field f : fields) {
			if (f.getPath().size() == 0) {
				boolean found = false;
				Iterator<Attribute> it = attributes.iterator();
				while (!found && it.hasNext()) {
					Attribute a = it.next();
					if (f.getMapTo().equals(a)) {
						found = true;
					}
				}
				if (!found) {
					result.add(f);
				}
			} else {
				if (f.getMapTo() == null) {
					result.add(f);
				}
			}
		}
		
		return result;
	}

	/**
	 * Compare fields list with attribute list and return the added attributes
	 * @param fields
	 * @param attributes
	 * @return 
	 */
	private static List<Attribute> getAddedAttributes(EList<Field> fields, EList<Attribute> attributes) {
		List<Attribute> result = new ArrayList<Attribute>();
		for (Attribute a : attributes) {
			boolean found = false;
			Iterator<Field> it = fields.iterator();
			while (!found && it.hasNext()) {
				Field f = it.next();
				if (f.getMapTo().equals(a)) {
					found = true;
				}
			}
			if (!found) {
				result.add(a);
			}
		}
		return result;
	}
}
