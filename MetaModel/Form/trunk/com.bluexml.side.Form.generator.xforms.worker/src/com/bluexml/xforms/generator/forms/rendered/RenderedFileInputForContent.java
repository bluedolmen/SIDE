/**
 * 
 */
package com.bluexml.xforms.generator.forms.rendered;

import org.jdom.Element;

import com.bluexml.xforms.generator.forms.Rendered;
import com.bluexml.xforms.generator.forms.XFormsGenerator;
import com.bluexml.xforms.generator.forms.modelelement.ModelElementBindSimple;
import com.bluexml.xforms.messages.MsgId;
import com.bluexml.xforms.messages.MsgPool;

/**
 * Defines the XHTML/XForms description of the file field for implicit content upload.
 * 
 * @author Amenel
 * 
 */
public class RenderedFileInputForContent extends Rendered {

	/**
	 * Builds the DOM section for the content upload field. Mostly taken from
	 * AbstractRenderableField.getFileElement.
	 */
	public RenderedFileInputForContent(ModelElementBindSimple meb) {
		Element element;
		String attributeId = MsgId.INT_INSTANCE_SIDE_NODE_CONTENT.getText();
		element = XFormsGenerator.createXFormsGroup(MsgPool
				.getMsg(MsgId.MSG_UPLOAD_CONTENT_GROUP_LABEL), null);
		element.setAttribute("id", attributeId + "_global");

		//
		Element filenameDiv = XFormsGenerator.createElement("div", XFormsGenerator.NAMESPACE_XHTML);
		filenameDiv.setAttribute("id", attributeId + "_filename");
		filenameDiv.setAttribute("class", MsgId.INT_CSS_UPLOAD_FILENAME.getText());
		//
		Element filenameElement = XFormsGenerator.createElement("output",
				XFormsGenerator.NAMESPACE_XFORMS);
		StringBuffer valueFilename = new StringBuffer();
		String curNodeSet = "current()/" + meb.getNodeset();
		valueFilename.append("if (");
		valueFilename.append(curNodeSet);
		valueFilename.append(" eq '') then '' else (if (not(starts-with(");
		valueFilename.append(curNodeSet);
		valueFilename.append(", 'file'))) then concat('");
		valueFilename.append(MsgPool.getMsg(MsgId.MSG_UPLOAD_CONTENT_REPO_INFO));
		valueFilename.append("', ");
		valueFilename.append(curNodeSet);
		valueFilename.append(") else concat('");
		valueFilename.append(MsgPool.getMsg(MsgId.MSG_FILE_FIELD_FILENAME_TEMP));
		valueFilename.append("', ");
		valueFilename.append(curNodeSet);
		valueFilename.append("))");
		filenameElement.setAttribute("value", valueFilename.toString());
		filenameElement.setAttribute("mediatype", "text/html");
		filenameDiv.addContent(filenameElement);
		element.addContent(filenameDiv);

		Element widgetDiv = XFormsGenerator.createElement("div", XFormsGenerator.NAMESPACE_XHTML);
		widgetDiv.setAttribute("class", MsgId.INT_CSS_UPLOAD_WIDGET.getText());
		Element input = XFormsGenerator.createElement("upload", XFormsGenerator.NAMESPACE_XFORMS);
		input.setAttribute("incremental", "true");
		input.setAttribute("id", attributeId);
		meb.addLinkedElement(input);

		String labelStr = MsgPool.testMsg(MsgId.MSG_UPLOAD_CONTENT_FIELD_LABEL);
		if (labelStr != null) {
			Element labelElt = XFormsGenerator.createElementWithContent("label",
					XFormsGenerator.NAMESPACE_XFORMS, labelStr);
			input.addContent(labelElt);
		}

		Element filename = XFormsGenerator.createElement("filename",
				XFormsGenerator.NAMESPACE_XFORMS);
		filename.setAttribute("ref", "attribute::file");
		input.addContent(filename);
		Element mediatype = XFormsGenerator.createElement("mediatype",
				XFormsGenerator.NAMESPACE_XFORMS);
		mediatype.setAttribute("ref", "attribute::type");
		input.addContent(mediatype);
		Element action = XFormsGenerator.createElement("action", XFormsGenerator.NAMESPACE_XFORMS);
		action.setAttribute("event", "xforms-value-changed", XFormsGenerator.NAMESPACE_EVENTS);
		Element recalculate = XFormsGenerator.createElement("recalculate",
				XFormsGenerator.NAMESPACE_XFORMS);
		action.addContent(recalculate);
		input.addContent(action);

		widgetDiv.addContent(input);
		element.addContent(widgetDiv);

		this.addModelElement(meb);
		xformsElement = element;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.bluexml.xforms.generator.forms.Rendered#isHolder()
	 */
	@Override
	public boolean isHolder() {
		return false;
	}

}
