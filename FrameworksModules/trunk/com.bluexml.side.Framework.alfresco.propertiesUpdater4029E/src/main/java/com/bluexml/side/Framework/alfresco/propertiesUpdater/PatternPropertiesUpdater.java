package com.bluexml.side.Framework.alfresco.propertiesUpdater;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class PatternPropertiesUpdater {
	private static Log logger = LogFactory.getLog(PatternPropertiesUpdater.class);

	public static String EXPRESSION_PATTERN = "\\$\\{([^\\}]*)\\}";

	Map<String, String> oldValues = new HashMap<String, String>();
	Map<String, String> newValues = new HashMap<String, String>();

	PatternPropertiesUpdater(Map<String, String> oldValues, Map<String, String> newValues) {
		this.oldValues = oldValues;
		this.newValues = newValues;
	}

	public String getNewValue(String currentKey, String template) {
		if (logger.isDebugEnabled()) {
			logger.debug("[getNewValue] currentProperty :" + currentKey);
			logger.debug("[getNewValue] template :" + template);
			logger.debug("[getNewValue] oldValues:" + oldValues);
			logger.debug("[getNewValue] newValues:" + newValues);
		}
		Pattern p = Pattern.compile(EXPRESSION_PATTERN);

		String expressionProperty = getExpressionProperty(currentKey);

		if (template.contains(expressionProperty)) {
			// need special treatment since the property to update is used in template
			extractCurrentPropertyValue(currentKey, template, p, expressionProperty);
		}

		Matcher matcher = p.matcher(template);
		// simply apply the template
		String newValue = template;
		while (matcher.find()) {
			String key = matcher.group(1);

			String newPropertyValue = getNewValue(key);
			if (logger.isDebugEnabled()) {
				logger.debug("** replace " + key + " by :" + newPropertyValue);
			}
			newValue = newValue.replaceAll(Pattern.quote(matcher.group()), newPropertyValue);

		}
		if (logger.isDebugEnabled()) {
			logger.debug("* Computed new value :" + newValue);
		}
		return newValue;
	}

	protected String getNewValue(String key) {
		return newValues.get(key) != null ? newValues.get(key) : "";
	}

	protected String getOldValue(String key) {
		return oldValues.get(key) != null ? oldValues.get(key) : "";
	}

	protected void extractCurrentPropertyValue(String currentKey, String template, Pattern p, String expressionProperty) {
		String oldPropertyValue = getOldValue(currentKey);
		if (logger.isDebugEnabled()) {
			logger.debug("[getNewValue] oldProperty Value :" + oldPropertyValue);
		}
		String currentPropertyValue = getNewValue(currentKey);
		if (logger.isDebugEnabled()) {
			logger.debug("[getNewValue] Property Value :" + currentPropertyValue);
		}
		if (currentPropertyValue != null && currentPropertyValue.equals(oldPropertyValue)) {
			if (logger.isDebugEnabled()) {
				logger.debug("[getNewValue] currentValue not changed, need to compute the real propertyValue");
			}
			Matcher matcher = p.matcher(template);
			// need to extract from name value the matching value from template
			// before compute new value

			// compute regexp (replace in template by old value except for the
			// property to update)
			String newValue = template;
			while (matcher.find()) {
				String key = matcher.group(1);
				if (!key.equals(currentKey)) {
					String oldValue = getOldValue(key);
					newValue = newValue.replaceAll(Pattern.quote(matcher.group()), oldValue);
				}
			}

			// create the regExp used to identify the original value
			String currentValueMAtchedRegExp = "";
			String[] split = newValue.split(Pattern.quote(expressionProperty));
			for (int i = 0; i < split.length; i++) {
				String string = split[i];
				currentValueMAtchedRegExp += Pattern.quote(string);
				if (i < split.length - 1) {
					currentValueMAtchedRegExp += "(.*)";
				}
			}
			if (logger.isDebugEnabled()) {
				logger.debug("*** matched old currentKeyvalueRegExp :" + currentValueMAtchedRegExp);
			}
			Pattern compile = Pattern.compile(currentValueMAtchedRegExp);
			if (logger.isDebugEnabled()) {
				logger.debug("*** oldValue to match, search currentKeyvalue :" + oldPropertyValue);
			}
			Matcher matcher2 = compile.matcher(oldPropertyValue);

			matcher2.find();
			String currentValueMAtched = matcher2.group(1);
			if (logger.isDebugEnabled()) {
				logger.debug("*** identified value (pattern matching) :" + currentValueMAtched);
			}
			// change the newValue to be able to apply template
			newValues.put(currentKey, currentValueMAtched);
		}
	}

	public static String getExpressionProperty(String currentKey) {
		return "${" + currentKey + "}";
	}
}
