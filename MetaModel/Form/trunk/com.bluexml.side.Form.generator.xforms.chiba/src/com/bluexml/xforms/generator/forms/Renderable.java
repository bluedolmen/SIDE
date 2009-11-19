package com.bluexml.xforms.generator.forms;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.bluexml.xforms.messages.MsgId;

import com.bluexml.xforms.generator.FormGenerator;
import com.bluexml.xforms.generator.forms.renderable.forms.group.RenderableFormContainer;
import com.bluexml.xforms.generator.forms.rendered.RenderedRepeater;
import com.bluexml.xforms.generator.forms.rendered.RenderedTabContainer;

/**
 * The Class Renderable.<br>
 * Own form meta model representing form elements
 */
public abstract class Renderable {

	/**
	 * The Enum PathType<br>
	 * Relative or absolute.
	 */
	public static enum PathType {

			/** Relative path. */
			relativePath,

			/** Absolute path. */
			absolutePath;
	}

	/**
	 * The Class Path.<br>
	 * Gives a path, either absolute or relative
	 */
	public static class Path {

		/** The path type. */
		protected PathType pathType;

		/** The path. */
		protected String path;

		/**
		 * Instantiates a new path.
		 * 
		 * @param pathType
		 *            the path type
		 * @param path
		 *            the path
		 */
		public Path(PathType pathType, String path) {
			super();
			this.pathType = pathType;
			this.path = path;
		}

	}

	/** The logger. */
	protected static Log logger = LogFactory.getLog(Renderable.class);

	/** Static path : relative without a suffix. */
	protected static Path ROOT_RELATIVE = new Path(PathType.relativePath, "");

	/** Absolute path : root. */
	protected static Path ROOT_ABSOLUTE = new Path(PathType.absolutePath, "");

	private static FormGenerator formGenerator;

	/**
	 * The children.<br>
	 * Renderable elements owned (even if a renderable can have multiple parents)
	 */
	private List<Renderable> children;

	private boolean inWorkflowForm;

	/**
	 * Instantiates a new renderable.
	 */
	public Renderable() {
		super();
		children = new ArrayList<Renderable>();
	}

	/**
	 * Adds a child.
	 * 
	 * @param renderable
	 *            the child
	 */
	public void add(Renderable renderable) {
		// if (!(renderable instanceof RenderableFormContainer)) {
		// renderable.setInWorkflowForm(inWorkflowForm);
		// }
		children.add(renderable);
	}

	/**
	 * Adds a child at first place.
	 * 
	 * @param renderable
	 *            a child
	 */
	public void addFirst(Renderable renderable) {
		if (!(renderable instanceof RenderableFormContainer)) {
			renderable.setInWorkflowForm(inWorkflowForm);
		}
		children.add(0, renderable);
	}

	/**
	 * Gets the children size.
	 * 
	 * @return the children size
	 */
	public int getChildrenSize() {
		return children.size();
	}

	/**
	 * Indicates if tabs have to be shown.<br>
	 * Done as DOJO tabs used in Chiba 2 can not be added inside a repeater
	 * 
	 * @param renderedParents
	 *            the rendered parents
	 * 
	 * @return true, if tabs should be shown
	 */
	protected boolean doShowTab(Stack<Rendered> renderedParents) {
		Rendered parent = renderedParents.peek();
		// if a parent is a tab container and tabs are not shown for it,
		// do no show tab too
		if (parent instanceof RenderedTabContainer) {
			if (!((RenderedTabContainer) parent).isShowTabs()) {
				return false;
			}
		}
		// search for a repeater
		for (Rendered rendered : renderedParents) {
			if (rendered instanceof RenderedRepeater) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Gets the data path of rendered element.<br />
	 * Rendered will be initialized with this path
	 * 
	 * @param parents
	 *            the parents
	 * @param renderedParents
	 *            the rendered parents
	 * @param parentPath
	 *            the parent path
	 * 
	 * @return the path
	 */
	public abstract Path getPath(String parentPath, Stack<Renderable> parents,
			Stack<Rendered> renderedParents);

	/**
	 * Recursive render.<br>
	 * Initiate rendering
	 * 
	 * @return the rendered
	 */
	public Rendered recursiveRender() {
		logger.debug("-------------------------------------------------");
		logger.debug("RENDERING " + this.toString());
		logger.debug("-------------------------------------------------");
		return recursiveRender("", new Stack<Renderable>(), new Stack<Rendered>());
	}

	/**
	 * Recursive render.
	 * 
	 * @param parentPath
	 *            the parent path
	 * @param parents
	 *            the parents
	 * @param renderedParents
	 *            the rendered parents
	 * 
	 * @return the rendered
	 */
	private Rendered recursiveRender(String parentPath, Stack<Renderable> parents,
			Stack<Rendered> renderedParents) {
		boolean previous = XFormsGenerator.isRenderingWorkflow();
		// logger.debug(this.toString() );

		// retrieve path for this renderable in this context
		Path path = getPath(parentPath, parents, renderedParents);
		// translate Path object into an absolute path
		String sPath = null;
		if (path.pathType == PathType.absolutePath) {
			sPath = path.path;
		} else {
			sPath = parentPath + path.path;
		}

		// if (StringUtils.trimToNull(sPath) != null) {
		// System.out.println("non empty path");
		// }
		// real render
		Rendered rendered = render(sPath, parents, renderedParents);

		// recursive render
		parents.push(this);

		if (this instanceof RenderableFormContainer) {
			XFormsGenerator.setRenderingWorkflow(isInWorkflowForm());
		}
		renderedParents.push(rendered);
		for (Renderable child : children) {
			if (child.shouldRender(parents)) {
				Rendered renderedChild = child.recursiveRender(sPath, parents, renderedParents);
				rendered.addRendered(renderedChild, child);
			}
		}
		//
		if (this instanceof RenderableFormContainer) {
			XFormsGenerator.setRenderingWorkflow(previous);
		}

		renderedParents.pop();
		rendered.renderEnd(this);
		renderEnd(rendered);

		parents.pop();

		return rendered;
	}

	/**
	 * Abstract method aiming to render this instance.
	 * 
	 * @param path
	 *            the path
	 * @param parents
	 *            the parents
	 * @param renderedParents
	 *            the rendered parents
	 * 
	 * @return the rendered
	 */
	public abstract Rendered render(String path, Stack<Renderable> parents,
			Stack<Rendered> renderedParents);

	/**
	 * Called when render is over.
	 * 
	 * @param rendered
	 *            the rendered
	 */
	public void renderEnd(Rendered rendered) {
		// nothing by default
	}

	/**
	 * Indicates if the element should be rendered in that context.
	 * 
	 * @param parents
	 *            the parents
	 * 
	 * @return true, if successful
	 */
	protected boolean shouldRender(Stack<Renderable> parents) {
		return true;
	}

	/**
	 * Gets the root path.<br>
	 * Retrieves the path from where we can access directly to the data.<br>
	 * This is used for association class.
	 * 
	 * @param renderedParents
	 *            the rendered parents
	 * 
	 * @return the root path
	 */
	protected String getRootPath(Stack<Rendered> renderedParents) {
		StringBuffer result = new StringBuffer("");
		for (Rendered rendered : renderedParents) {
			if (rendered instanceof RenderedRepeater) {
				RenderedRepeater repeated = (RenderedRepeater) rendered;
				String repeaterNodeSet = repeated.getNodeSet();
				repeaterNodeSet = StringUtils.left(repeaterNodeSet, repeaterNodeSet
						.lastIndexOf("["));
				repeaterNodeSet = repeaterNodeSet + "[index('" + repeated.getRepeatId() + "')]";
				result.append(repeaterNodeSet + "/");
			}
		}
		return result.toString();
	}

	protected String getDynEnumContextString(String id) {
		return MsgId.INT_GEN_DYN_ENUM_PREFIX + id + MsgId.INT_GEN_DYN_ENUM_PREFIX_CONTEXT;
	}

	protected String getDynEnumParentString(String id) {
		return MsgId.INT_GEN_DYN_ENUM_PREFIX + id + MsgId.INT_GEN_DYN_ENUM_PREFIX_PARENT;
	}

	/**
	 * @param inWorkflowForm
	 *            the inWorkflowForm to set
	 */
	public void setInWorkflowForm(boolean inWorkflowForm) {
		this.inWorkflowForm = inWorkflowForm;
	}

	/**
	 * @return the inWorkflowForm
	 */
	public boolean isInWorkflowForm() {
		return inWorkflowForm;
	}
	/**
	 * @return the formGenerator
	 */
	public static FormGenerator getFormGenerator() {
		return formGenerator;
	}

	/**
	 * @param formGenerator
	 *            the formGenerator to set
	 */
	public static void setFormGenerator(FormGenerator formGenerator) {
		Renderable.formGenerator = formGenerator;
	}

}
