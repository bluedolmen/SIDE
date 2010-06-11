package com.bluexml.xforms.generator;

import com.bluexml.side.clazz.Aspect;
import com.bluexml.side.clazz.Association;
import com.bluexml.side.clazz.Attribute;
import com.bluexml.side.clazz.Clazz;
import com.bluexml.side.clazz.Enumeration;
import com.bluexml.side.form.FormContainer;
import com.bluexml.side.util.componentmonitor.indy.CoreInterface;

/**
 * The Interface DataGenerator.
 */
public interface GeneratorInterface {

	/**
	 * The Class AssociationKind.
	 */
	public class AssociationKind {

		/** The association cardinality. */
		private AssociationCardinality associationCardinality;

		/** The inline. */
		private boolean inline;

		/** The alfresco name. */
		private String alfrescoName;

		protected boolean simplifyClasses;

		private int hiBound;

		private int loBound;

		private boolean filtered;

		/**
		 * Instantiates a new association kind.
		 * 
		 * @param associationCardinality
		 *            the association cardinality
		 * @param inline
		 *            the inline
		 * @param associationClasse
		 *            the association classe
		 * @param alfrescoName
		 *            the alfresco name
		 */
		public AssociationKind(AssociationCardinality associationCardinality, boolean inline,
				boolean simplifyClasses, int hiBound, int loBound, boolean filtered) {
			super();
			this.associationCardinality = associationCardinality;
			this.inline = inline;
			this.simplifyClasses = simplifyClasses;
			this.hiBound = hiBound;
			this.loBound = loBound;
			this.filtered = filtered;
		}

		/**
		 * Gets the association cardinality.
		 * 
		 * @return the association cardinality
		 */
		public AssociationCardinality getAssociationCardinality() {
			return associationCardinality;
		}

		/**
		 * Sets the association cardinality.
		 * 
		 * @param associationCardinality
		 *            the new association cardinality
		 */
		public void setAssociationCardinality(AssociationCardinality associationCardinality) {
			this.associationCardinality = associationCardinality;
		}

		/**
		 * Checks if is inline.
		 * 
		 * @return true, if is inline
		 */
		public boolean isInline() {
			return inline && (!simplifyClasses);
		}

		/**
		 * Sets the inline.
		 * 
		 * @param inline
		 *            the new inline
		 */
		public void setInline(boolean inline) {
			this.inline = inline;
		}

		public int getHiBound() {
			return hiBound;
		}

		public void setHiBound(int hiBound) {
			this.hiBound = hiBound;
		}

		public int getLoBound() {
			return loBound;
		}

		public void setLoBound(int loBound) {
			this.loBound = loBound;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.lang.Object#toString()
		 */
		@Override
		public String toString() {
			StringBuffer sb = new StringBuffer();
			sb.append("AssociationCardinality : ");
			sb.append(associationCardinality);
			sb.append(" - inline : ");
			sb.append(isInline());
			sb.append(" - alfrescoName : ");
			sb.append(alfrescoName);
			return sb.toString();
		}

		/**
		 * @param filtered
		 *            the filtered to set
		 */
		public void setFiltered(boolean filtered) {
			this.filtered = filtered;
		}

		/**
		 * @return the filtered
		 */
		public boolean isFiltered() {
			return filtered;
		}

	}

	/**
	 * The Enum AssociationCardinality.
	 */
	public enum AssociationCardinality {

			/** The one to one. */
			oneToOne {
				@Override
				public AssociationCardinality getInverse() {
					return oneToOne;
				}

				@Override
				public boolean isMultiple() {
					return false;
				}

			},

			/** The one to many. */
			oneToMany {
				@Override
				public AssociationCardinality getInverse() {
					return manyToOne;
				}

				@Override
				public boolean isMultiple() {
					return true;
				}

			},

			/** The many to one. */
			manyToOne {
				@Override
				public AssociationCardinality getInverse() {
					return oneToMany;
				}

				@Override
				public boolean isMultiple() {
					return false;
				}

			},

			/** The many to many. */
			manyToMany {
				@Override
				public AssociationCardinality getInverse() {
					return manyToMany;
				}

				@Override
				public boolean isMultiple() {
					return true;
				}

			};

		/**
		 * Gets the inverse.
		 * 
		 * @return the inverse
		 */
		public abstract AssociationCardinality getInverse();

		/**
		 * Checks if is multiple.
		 * 
		 * @return true, if is multiple
		 */
		public abstract boolean isMultiple();
	}

	/**
	 * Sets the form generator.
	 * 
	 * @param formGenerator
	 *            the new form generator
	 */
	void setFormGenerator(FormGeneratorsManager formGenerator);

	/**
	 * Adds the aspect for class.
	 * 
	 * @param classe
	 *            the classe
	 * @param aspect
	 *            the aspect
	 * @param owner
	 *            the owner
	 */
	void addAspectForClass(Clazz classe, Aspect aspect, Clazz owner);

	/**
	 * Adds the association.
	 * 
	 * @param type
	 *            the type
	 * @param name
	 *            the name
	 * @param title
	 *            the title
	 * @param source
	 *            the source
	 * @param destination
	 *            the destination
	 * @param role
	 *            the role
	 * @param doublenav
	 *            the doublenav
	 * @param association
	 *            the association
	 * @param owner
	 *            the owner
	 */
	void addAssociation(AssociationKind type, String name, String title, Clazz source,
			Clazz destination, String role, boolean doublenav, Association association, Clazz owner);

	/**
	 * Adds the attribute for aspect.
	 * 
	 * @param aspect
	 *            the aspect
	 * @param attribute
	 *            the attribute
	 */
	void addAttributeForAspect(Aspect aspect, Attribute attribute);

	/**
	 * Adds the attribute for class.
	 * 
	 * @param classe
	 *            the classe
	 * @param attribute
	 *            the attribute
	 * @param owner
	 *            the owner
	 */
	void addAttributeForClass(Clazz classe, Attribute attribute, Clazz owner);

	/**
	 * Adds the id for class.
	 * 
	 * @param classe
	 *            the classe
	 * @param string
	 *            the string
	 * @param hasParent
	 *            the has parent
	 */
	// void addIdForClass(Clazz classe, String string, boolean hasParent);

	/**
	 * Begin aspect.
	 * 
	 * @param aspect
	 *            the aspect
	 */
	void beginAspect(Aspect aspect);

	/**
	 * Begin classe.
	 * 
	 * @param classe
	 *            the classe
	 * @param rendered
	 *            the rendered
	 */
	void beginClasse(Clazz classe, boolean rendered);

	/**
	 * Begin generation.
	 */
	void beginGeneration();

	/**
	 * Begin list aspects.
	 */
	void beginListAspects();

	/**
	 * Begin list associations.
	 */
	void beginListAssociations();

	/**
	 * Begin list classes.
	 */
	void beginListClasses();

	/**
	 * Begin list enums.
	 */
	void beginListEnums();

	/**
	 * End aspect.
	 * 
	 * @param aspect
	 *            the aspect
	 */
	void endAspect(Aspect aspect);

	/**
	 * End classe.
	 * 
	 * @param classe
	 *            the classe
	 */
	void endClasse(Clazz classe);

	/**
	 * End generation.
	 */
	void endGeneration();

	/**
	 * End list aspects.
	 */
	void endListAspects();

	/**
	 * End list associations.
	 */
	void endListAssociations();

	/**
	 * End list classes.
	 */
	void endListClasses();

	/**
	 * End list enums.
	 */
	void endListEnums();

	/**
	 * Process enum.
	 * 
	 * @param enumeration
	 *            the enumeration
	 */
	void processEnum(Enumeration enumeration);

	/**
	 * Begin list forms.
	 */
	void beginListForms();

	/**
	 * End list forms.
	 */
	void endListForms();

	/**
	 * Begin form.
	 * 
	 * @param form
	 *            the form
	 */
	void beginForm(FormContainer form);

	/**
	 * End form.
	 * 
	 * @param form
	 *            the form
	 */
	void endForm(FormContainer form);

	void setMonitor(CoreInterface monitor);

	/** Returns true if the generator makes a different processing when in readOnly mode. */
	boolean supportsReadOnlyMode();

	/** Puts the generator in the read-only status. */
	void setReadOnlyMode(boolean onOff);

	/**
	 * Tells whether the generator is in read only mode. Always returns false when the mode is not
	 * supported.
	 */
	boolean isReadOnlyMode();

}
