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
package org.redpill.alfresco.module.metadatawriter.services.pdfbox.impl;

import java.io.Serializable;
import java.util.Date;

import org.redpill.alfresco.module.metadatawriter.services.ContentFacade.ContentException;
import org.redpill.alfresco.module.metadatawriter.services.pdfbox.FieldUpdateSpecification;

public enum PdfboxMetadata {

  AUTHOR("Author", new FieldUpdateSpecification() {

    public void update(final String field, final Serializable value, final PdfboxFacade facade) {
      facade.setAuthor((String) value);
    }

  }), TITLE("Title", new FieldUpdateSpecification() {

    public void update(final String field, final Serializable value, final PdfboxFacade facade) {
      facade.setTitle((String) value);
    }

  }), KEYWORDS("Keywords", new FieldUpdateSpecification() {

    public void update(final String field, final Serializable value, final PdfboxFacade facade) {
      facade.setKeywords((String) value);
    }

  }), CREATE_DATETIME("CreateDateTime", new FieldUpdateSpecification() {

    public void update(final String field, final Serializable value, final PdfboxFacade facade) {
      facade.setCreateDateTime((Date) value);
    }

  }), CUSTOM("Custom", new FieldUpdateSpecification() {

    public void update(final String field, final Serializable value, final PdfboxFacade facade) {
      facade.setCustomMetadata(field, (String) value);
    }

  });

  private final String _fieldname;
  private final FieldUpdateSpecification _specification;

  private PdfboxMetadata(final String fieldname, final FieldUpdateSpecification specification) {
    assert null != fieldname;
    assert null != specification;

    _fieldname = fieldname;
    _specification = specification;
  }

  private boolean correspondsTo(final String fieldname) {
    return _fieldname.equalsIgnoreCase(fieldname);
  }

  public void update(final String field, final Serializable value, final PdfboxFacade facade) throws ContentException {
    assert null != value;
    assert null != facade;

    _specification.update(field, value, facade);
  }

  static PdfboxMetadata find(final String field) {
    for (final PdfboxMetadata metadataField : PdfboxMetadata.values()) {
      if (metadataField.correspondsTo(field)) {
        return metadataField;
      }
    }

    return CUSTOM;
  }

}
