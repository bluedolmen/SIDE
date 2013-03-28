/*
    Copyright (C) 2007-20013  BlueXML - www.bluexml.com

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU Affero General Public License as
    published by the Free Software Foundation, either version 3 of the
    License, or (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU Affero General Public License for more details.

    You should have received a copy of the GNU Affero General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/
package com.bluexml.side.alfresco.repo.content.cleanup;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.extensions.webscripts.Cache;
import org.springframework.extensions.webscripts.DeclarativeWebScript;
import org.springframework.extensions.webscripts.Status;
import org.springframework.extensions.webscripts.WebScriptRequest;


public class Purge extends DeclarativeWebScript {
	private static Log LOGGER = LogFactory.getLog(Purge.class);

	
	private static final String PARAM_DAYSNBOLDERTHAN = "daysNbOlderThan";
	private static final int DEFAULT_DAYSNBOLDERTHAN = 14;

	@Override
	protected Map<String, Object> executeImpl(WebScriptRequest request, Status status, Cache cache) {
		if (LOGGER.isDebugEnabled()) LOGGER.debug("Purge.executeImpl()");

		int daysNb = DEFAULT_DAYSNBOLDERTHAN;
		String daysNbOlderThan = request.getParameter(PARAM_DAYSNBOLDERTHAN);
		if (daysNbOlderThan != null) {
			try {
				daysNb = Integer.parseInt(daysNbOlderThan);				
			} catch (NumberFormatException e) {
				daysNb = DEFAULT_DAYSNBOLDERTHAN;
			}
		}
		if (LOGGER.isDebugEnabled()) LOGGER.debug("Purge.executeImpl() daysNb="+daysNb);

		Map<String, Object> model = new HashMap<String, Object>();
 
		this.trashcanCleaner.setProtectedDays(daysNb);
		int nbDeleted = this.trashcanCleaner.execute();
		if (LOGGER.isDebugEnabled()) LOGGER.debug("Purge.executeImpl() nbDeleted="+nbDeleted);
		
		model.put("nbDeleted", nbDeleted);
		model.put("daysNb", daysNb);

		status.setCode(Status.STATUS_OK);

		return model;
	}

	public void setTrashcanCleaner(TrashcanCleaner trashcanCleaner) {
		this.trashcanCleaner = trashcanCleaner;
	}
	private TrashcanCleaner trashcanCleaner;
}
