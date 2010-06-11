package com.bluexml.xforms.generator.forms.renderable.forms.field;

import org.apache.commons.lang.StringUtils;

import com.bluexml.side.form.CharField;
import com.bluexml.side.form.FormElement;
import com.bluexml.xforms.generator.forms.XFormsGenerator;
import com.bluexml.xforms.generator.forms.modelelement.ModelElementBindSimple;

public class RenderableRegexInput<CF extends CharField> extends RenderableSimpleInput<CF> {

	protected static final String MAIL_REGEXP = "^(|[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]+)$";

	protected static final String URL_REGEXP = "(http|ftp|https):\\/\\/[\\w\\-_]+(\\.[\\w\\-_]+)+([\\w\\-\\.,@?^=%&amp;:/~\\+#]*[\\w\\-\\@?^=%&amp;/~\\+#])?";

	private String regexp;

	public RenderableRegexInput(XFormsGenerator generationManager, FormElement parent,
			CF formElement, String regexp) {
		super(generationManager, parent, formElement, "string");
		this.regexp = StringUtils.trimToNull(regexp);
	}

	@Override
	protected void applyConstraints(ModelElementBindSimple meb) {
		super.applyConstraints(meb);
		if (regexp != null) {
			setConstraintRegexp(meb, regexp);
		}

	}

}
