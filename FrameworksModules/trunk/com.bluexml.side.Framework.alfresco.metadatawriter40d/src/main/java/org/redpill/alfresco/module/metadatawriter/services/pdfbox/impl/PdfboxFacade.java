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

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.io.IOUtils;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.redpill.alfresco.module.metadatawriter.services.ContentFacade;

public class PdfboxFacade implements ContentFacade {

  private final InputStream _inputStream;

  private final OutputStream _outputStream;

  private final PDDocument _document;

  public PdfboxFacade(final InputStream inputStream, final OutputStream outputStream) {
    _inputStream = inputStream;

    _outputStream = outputStream;

    try {
      _document = PDDocument.load(_inputStream);
    } catch (final IOException ex) {
      throw new RuntimeException(ex);
    }
  }

  public void writeMetadata(final String field, final Serializable value) throws ContentException {
    final PdfboxMetadata metadata = PdfboxMetadata.find(field);

    metadata.update(field, value, this);
  }

  public void save() throws ContentException {
    try {
      _document.save(_outputStream);
    } catch (final Exception ex) {
      throw new RuntimeException(ex);
    } finally {
      IOUtils.closeQuietly(_inputStream);
      IOUtils.closeQuietly(_outputStream);
    }
  }

  public void abort() throws ContentException {
    IOUtils.closeQuietly(_inputStream);
    IOUtils.closeQuietly(_outputStream);
  }

  public void setAuthor(final String author) {
    _document.getDocumentInformation().setAuthor(author);
  }

  public void setTitle(final String title) {
    _document.getDocumentInformation().setTitle(title);
  }

  public void setKeywords(final String keywords) {
    _document.getDocumentInformation().setKeywords(keywords);
  }

  public void setCreateDateTime(final Date date) {
    final Calendar calendar = Calendar.getInstance();

    calendar.setTime(date);

    _document.getDocumentInformation().setCreationDate(calendar);
  }

  public void setCustomMetadata(final String field, final String value) {
    _document.getDocumentInformation().setCustomMetadataValue(field, value);
  }

}
