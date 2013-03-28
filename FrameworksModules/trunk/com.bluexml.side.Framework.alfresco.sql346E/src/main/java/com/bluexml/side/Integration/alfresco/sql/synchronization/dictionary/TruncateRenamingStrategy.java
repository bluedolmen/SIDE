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
package com.bluexml.side.Integration.alfresco.sql.synchronization.dictionary;

public class TruncateRenamingStrategy extends AbstractRenamingStrategy {

	public String renameTable(String tableName) {
		int tableNameLength = tableName.length();
		int maxTableNameLength = getMaxTableNameLength();
		int maxLength = (tableNameLength < maxTableNameLength ? tableNameLength : maxTableNameLength);
		String result = tableName.substring(0, maxLength);

		if (maxTableNameLength < tableNameLength) {
			if (logger.isInfoEnabled())
				logger.info("Truncating table name \"" + tableName + "\" to \"" + result + "\"");
		}
		return result;
	}

}
