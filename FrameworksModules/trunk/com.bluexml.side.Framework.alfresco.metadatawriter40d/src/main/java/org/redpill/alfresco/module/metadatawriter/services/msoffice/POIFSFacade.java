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
package org.redpill.alfresco.module.metadatawriter.services.msoffice;

import java.io.IOException;
import java.util.Date;

import org.redpill.alfresco.module.metadatawriter.services.ContentFacade.ContentException;

public interface POIFSFacade {

  void writeProperties() throws IOException;

  void setTitle(String title) throws ContentException;

  void setAuthor(String author) throws ContentException;

  void setKeywords(String keywords) throws ContentException;

  void setCreateDateTime(Date dateTime) throws ContentException;

  void setCustomMetadata(String field, String value) throws ContentException;

  void close() throws IOException;

}
