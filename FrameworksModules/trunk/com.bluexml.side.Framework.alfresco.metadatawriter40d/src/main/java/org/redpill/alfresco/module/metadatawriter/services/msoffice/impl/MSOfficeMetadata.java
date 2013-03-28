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

import java.io.Serializable;
import java.util.Date;

import org.redpill.alfresco.module.metadatawriter.services.ContentFacade.ContentException;
import org.redpill.alfresco.module.metadatawriter.services.msoffice.FieldUpdateSpecification;
import org.redpill.alfresco.module.metadatawriter.services.msoffice.POIFSFacade;

public enum MSOfficeMetadata {

  AUTHOR("Author", new FieldUpdateSpecification() {
    public void update(final String field, final Serializable value, final POIFSFacade facade) throws ContentException {
      facade.setAuthor((String) value);
    }

  }), TITLE("Title", new FieldUpdateSpecification() {
    public void update(final String field, final Serializable value, final POIFSFacade facade) throws ContentException {
      facade.setTitle((String) value);
    }

  }), KEYWORDS("Keywords", new FieldUpdateSpecification() {
    public void update(final String field, final Serializable value, final POIFSFacade facade) throws ContentException {
      facade.setKeywords((String) value);
    }
  }), CREATE_DATETIME("CreateDateTime", new FieldUpdateSpecification() {
    public void update(final String field, final Serializable value, final POIFSFacade facade) throws ContentException {
      facade.setCreateDateTime((Date) value);
    }
  }), CUSTOM("Custom", new FieldUpdateSpecification() {
    public void update(final String field, final Serializable value, final POIFSFacade facade) throws ContentException {
      facade.setCustomMetadata(field, (String) value);
    }
  });

  private final String fieldName;
  private final FieldUpdateSpecification spec;

  private MSOfficeMetadata(final String fieldName, final FieldUpdateSpecification spec) {
    assert null != fieldName;
    assert null != spec;

    this.fieldName = fieldName;
    this.spec = spec;
  }

  private boolean correspondsTo(final String fieldName) {
    // TODO: Translation...
    return this.fieldName.equalsIgnoreCase(fieldName);
  }

  public void update(final String field, final Serializable value, final POIFSFacade facade) throws ContentException {
    assert null != value;
    assert null != facade;

    spec.update(field, value, facade);

  }

  static MSOfficeMetadata find(final String field) {
    for (final MSOfficeMetadata metadataField : MSOfficeMetadata.values()) {
      if (metadataField.correspondsTo(field)) {
        return metadataField;
      }
    }

    return CUSTOM;
  }

}
