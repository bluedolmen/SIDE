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
package org.redpill.alfresco.module.metadatawriter.services.impl;

import java.io.IOException;

import org.alfresco.repo.content.MimetypeMap;
import org.alfresco.service.cmr.repository.ContentReader;
import org.alfresco.service.cmr.repository.ContentWriter;
import org.redpill.alfresco.module.metadatawriter.services.ContentFacade;
import org.redpill.alfresco.module.metadatawriter.services.MetadataContentInstantiator;
import org.redpill.alfresco.module.metadatawriter.services.msoffice.impl.MSOfficeFacade;

public class DefaultContentInstantiator {

  // ---------------------------------------------------
  // Public classes
  // ---------------------------------------------------
  public static class MSWordContentInstantiator implements MetadataContentInstantiator {

    public boolean supports(final String mimetype) {
      return MimetypeMap.MIMETYPE_WORD.equalsIgnoreCase(mimetype);
    }

    public ContentFacade create(final ContentReader reader, final ContentWriter writer) throws IOException {
      return new MSOfficeFacade(reader.getContentInputStream(), writer.getContentOutputStream());
    }
  }

  public static class MSExcelContentInstantiator implements MetadataContentInstantiator {

    public boolean supports(final String mimetype) {
      return MimetypeMap.MIMETYPE_EXCEL.equalsIgnoreCase(mimetype);
    }

    public ContentFacade create(final ContentReader reader, final ContentWriter writer) throws IOException {
      return new MSOfficeFacade(reader.getContentInputStream(), writer.getContentOutputStream());
    }
  }

  public static class MSPowerPointContentInstantiator implements MetadataContentInstantiator {

    public boolean supports(final String mimetype) {
      return MimetypeMap.MIMETYPE_PPT.equalsIgnoreCase(mimetype);
    }

    public ContentFacade create(final ContentReader reader, final ContentWriter writer) throws IOException {
      return new MSOfficeFacade(reader.getContentInputStream(), writer.getContentOutputStream());
    }
  }

}
