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
package org.redpill.alfresco.module.metadatawriter.services.msoffice.impl;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.redpill.alfresco.module.metadatawriter.services.ContentFacade;
import org.redpill.alfresco.module.metadatawriter.services.msoffice.POIFSFacade;

public class MSOfficeFacade implements ContentFacade {

  private static final Log logger = LogFactory.getLog(MSOfficeFacade.class);

  private final POIFSFacade poifsFacade;

  // ---------------------------------------------------
  // Public constructor
  // ---------------------------------------------------
  public MSOfficeFacade(final InputStream in, final OutputStream out) throws IOException {
    this.poifsFacade = new POIFSFacadeImpl(in, out);
  }

  // For unit testing purposes
  protected MSOfficeFacade(final POIFSFacade poifsFacade) {
    this.poifsFacade = poifsFacade;
  }

  // ---------------------------------------------------
  // Public methods
  // ---------------------------------------------------
  public void save() throws ContentException {

    if (logger.isDebugEnabled()) {
      logger.debug("Saving content");
    }

    try {
      poifsFacade.writeProperties();
    } catch (final IOException e) {
      throw new ContentException("Could not save metadata", e);
    }
  }

  public void abort() throws ContentException {
    try {
      poifsFacade.close();
    } catch (final IOException ioe) {
      throw new ContentException("Unable to abort the POIFSFacade!", ioe);
    }
  }

  public void writeMetadata(final String field, final Serializable value) throws ContentException {

    if (logger.isDebugEnabled()) {
      logger.debug("Exporting metadata " + field + " with value " + value);
    }

    final MSOfficeMetadata metadata = MSOfficeMetadata.find(field);

    try {
      metadata.update(field, value, poifsFacade);
    } catch (final ContentException e) {
      throw new ContentException("Could not export metadata " + field + " with value " + value, e);
    }
  }

}
