package com.bluexml.xforms.generator.forms.renderable.classes;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;
import java.util.TreeMap;

import org.apache.commons.lang.StringUtils;
import org.eclipse.emf.common.util.EList;

import com.bluexml.side.clazz.AbstractClass;
import com.bluexml.side.clazz.Association;
import com.bluexml.side.clazz.Attribute;
import com.bluexml.side.common.Comment;
import com.bluexml.side.common.Stereotype;
import com.bluexml.xforms.generator.FormGeneratorsManager;
import com.bluexml.xforms.generator.forms.Renderable;
import com.bluexml.xforms.generator.forms.Rendered;
import com.bluexml.xforms.generator.forms.renderable.common.RenderableDiv;
import com.bluexml.xforms.generator.forms.renderable.common.RenderableLine;
import com.bluexml.xforms.generator.forms.rendered.RenderedDiv;
import com.bluexml.xforms.generator.tools.ModelTools;

/**
 * The Class RenderableLayout.
 */
public class RenderableLayout extends Renderable {

	/** The lines. */
	private List<List<String>> lines = null;

	/** The all names. */
	private List<String> allNames;

	/** The items. */
	private Map<String, Renderable> items;

	/** The first render. */
	private boolean firstRender = true;

	/** The is empty. */
	private boolean isEmpty = true;

	/** The classe. */
	private AbstractClass classe;

	/**
	 * Instantiates a new renderable layout.
	 * 
	 * @param classe
	 *            the classe
	 */
	public RenderableLayout(AbstractClass classe) {
		this.classe = classe;
		String layout = getLayout(classe);
		allNames = new ArrayList<String>();
		lines = new ArrayList<List<String>>();
		if (layout != null) {
			computeLayout(filterLayoutString(layout));
		}
		items = new TreeMap<String, Renderable>();
	}

	/**
	 * Checks if is empty.
	 * 
	 * @return true, if is empty
	 */
	public boolean isEmpty() {
		return isEmpty;
	}

	/**
	 * Gets the association name.
	 * 
	 * @param association
	 *            the association
	 * 
	 * @return the association name
	 */
	public String getAssociationName(Association association) {
		EList<Comment> comments = association.getComments();
		for (Comment comment : comments) {
			EList<Stereotype> stereotypes = comment.getStereotypes();
			for (Stereotype stereotype : stereotypes) {
				if (stereotype.getName().equals(FormGeneratorsManager.ALFRESCO_NAME_ASSOCIATION)) {
					return comment.getValue();
				}
			}
		}
		return association.getName();
	}

	/**
	 * Adds the association.
	 * 
	 * @param association
	 *            the association
	 * @param renderableAssociation
	 *            the renderable association
	 */
	public void addAssociation(Association association, RenderableAssociation renderableAssociation) {
		isEmpty = false;
		items.put(getAssociationName(association), renderableAssociation);
	}

	/**
	 * Adds the attribute.
	 * 
	 * @param attribute
	 *            the attribute
	 * @param renderableAttribute
	 *            the renderable attribute
	 */
	public void addAttribute(Attribute attribute, RenderableAttribute renderableAttribute) {
		isEmpty = false;
		items.put(attribute.getName(), renderableAttribute);
	}

	/**
	 * Compute layout.
	 * 
	 * @param layout
	 *            the layout
	 */
	private void computeLayout(String layout) {
		List<String> alines = new ArrayList<String>();
		parseLines(layout, alines);
		for (String string : alines) {
			List<String> rcolumns = new ArrayList<String>();
			String[] columns = string.split(",");
			for (String column : columns) {
				String realColumn = column.subSequence(1, column.length() - 1).toString();
				rcolumns.add(realColumn);
			}
			allNames.addAll(rcolumns);
			lines.add(rcolumns);
		}
	}

	/**
	 * Filter layout string.
	 * 
	 * @param input
	 *            the input
	 * 
	 * @return the string
	 */
	private String filterLayoutString(String input) {
		StringBuffer output = new StringBuffer("");
		for (int i = 0; i < input.length(); i++) {
			char c = input.charAt(i);
			if (c > 32) {
				output.append(c);
			}
		}
		return output.toString();
	}

	/**
	 * Gets the layout.
	 * 
	 * @param classe
	 *            the classe
	 * 
	 * @return the layout
	 */
	private String getLayout(AbstractClass classe) {
		String result = null;
		EList<Comment> comments = classe.getComments();
		for (Comment comment : comments) {
			EList<Stereotype> stereotypes = comment.getStereotypes();
			for (Stereotype stereotype : stereotypes) {
				if (StringUtils.trimToEmpty(stereotype.getName()).equals("layout")) {
					result = comment.getValue();
				}
			}
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.bluexml.xforms.generator.forms.Renderable#getPath(java.lang.String, java.util.Stack,
	 * java.util.Stack)
	 */
	@Override
	public Path getPath(String parentPath, Stack<Renderable> parents,
			Stack<Rendered> renderedParents) {
		return ROOT_RELATIVE;
	}

	/**
	 * Checks for association.
	 * 
	 * @param association
	 *            the association
	 * 
	 * @return true, if successful
	 */
	public boolean hasAssociation(Association association) {
		return allNames.contains(getAssociationName(association));
	}

	/**
	 * Checks for attribute.
	 * 
	 * @param attribute
	 *            the attribute
	 * 
	 * @return true, if successful
	 */
	public boolean hasAttribute(Attribute attribute) {
		return allNames.contains(attribute.getName());
	}

	/**
	 * Parses the lines.
	 * 
	 * @param layout
	 *            the layout
	 * @param lines
	 *            the lines
	 */
	private void parseLines(String layout, List<String> lines) {
		int r = 0;
		StringBuffer currentPart = new StringBuffer("");

		int copen = 0;
		for (int i = 0; i < layout.length(); i++) {
			char c = layout.charAt(i);
			if (c == '[') {
				copen++;
				if (copen == 1) {
					currentPart = new StringBuffer("");
				} else {
					currentPart.append(c);
				}
			} else if (c == ']') {
				copen--;
				if (copen == 0) {
					r++;
					parseLines(currentPart.toString(), lines);
				} else {
					currentPart.append(c);
				}
			} else {
				currentPart.append(c);
			}
		}
		if (r == 0) {
			lines.add(currentPart.toString());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.bluexml.xforms.generator.forms.Renderable#render(java.lang.String, java.util.Stack,
	 * java.util.Stack)
	 */
	@Override
	public Rendered render(String path, Stack<Renderable> parents, Stack<Rendered> renderedParents, boolean isInIMultRepeater) {
		if (firstRender) {
			for (List<String> line : lines) {
				RenderableLine renderableLine = new RenderableLine();
				for (String itemName : line) {
					Renderable item = items.get(itemName);
					if (item != null) {
						renderableLine.add(item);
						items.remove(itemName);
					}
				}
				add(renderableLine);
			}
			RenderableDiv renderableElse = new RenderableDiv(ModelTools.getCompleteNameJAXB(classe)
					+ "Else");
			Set<String> keySet = items.keySet();
			for (String itemName : keySet) {
				renderableElse.add(items.get(itemName));
			}
			if (renderableElse.getChildrenSize() > 0) {
				add(renderableElse);
			}
			firstRender = false;
		}
		return new RenderedDiv(ModelTools.getCompleteNameJAXB(classe) + "Layout");
	}

}
