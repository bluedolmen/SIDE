package com.bluexml.side.framework.alfresco.sharePortalExtension;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.alfresco.web.scripts.Cache;
import org.alfresco.web.scripts.DeclarativeWebScript;
import org.alfresco.web.scripts.Status;
import org.alfresco.web.scripts.WebScriptRequest;
import org.apache.log4j.Logger;

public class CreateSiteWebScript extends DeclarativeWebScript {
	private static Logger logger = Logger.getLogger(CreateSiteWebScript.class);
	protected PresetsManagerExtension persetManager;

	/**
	 * @return the persetManager
	 */
	public PresetsManagerExtension getPersetManager() {
		return persetManager;
	}

	/**
	 * @param persetManager
	 *            the persetManager to set
	 */
	public void setPersetManager(PresetsManagerExtension persetManager) {
		this.persetManager = persetManager;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.alfresco.web.scripts.DeclarativeWebScript#executeImpl(org.alfresco
	 * .web.scripts.WebScriptRequest, org.alfresco.web.scripts.Status,
	 * org.alfresco.web.scripts.Cache)
	 */
	@Override
	protected Map<String, Object> executeImpl(WebScriptRequest req, Status status, Cache cache) {
		logger.debug("create Site JavaBacked webScript");
		Map<String, Object> model = new HashMap<String, Object>();
		List<String> ids = getPersetManager().getPresetsIds();
		String[] idst = ids.toArray(new String[ids.size()]);
		model.put("presets", idst);

		return model;
	}

}
