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
package org.redpill.alfresco.module.metadatawriter.services;

import java.io.Serializable;

public interface ContentFacade {

  void writeMetadata(String field, Serializable serializable) throws ContentException;

  /**
   * Writes the updated content.
   * 
   * @throws ContentException
   *           If the write operation failed
   */
  void save() throws ContentException;

  public class ContentException extends Exception {

    private static final long serialVersionUID = -5625363611017820280L;

    public ContentException(final String message, final Throwable cause) {
      super(message, cause);
    }

  }

  /**
   * Close any streams to the underlying data. Cleans up dependencies.
   * 
   * @throws ContentException
   */
  void abort() throws ContentException;

}
