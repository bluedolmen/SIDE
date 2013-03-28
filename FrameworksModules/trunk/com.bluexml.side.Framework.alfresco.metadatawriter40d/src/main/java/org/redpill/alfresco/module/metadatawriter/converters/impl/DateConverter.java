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
package org.redpill.alfresco.module.metadatawriter.converters.impl;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.redpill.alfresco.module.metadatawriter.converters.ValueConverter;

public class DateConverter implements ValueConverter {

  private final SimpleDateFormat _dateFormat;

  // ---------------------------------------------------
  // Public constructor
  // ---------------------------------------------------

  public DateConverter(final String dateFormat) {
    _dateFormat = new SimpleDateFormat(dateFormat);
  }

  // ---------------------------------------------------
  // Public methods
  // ---------------------------------------------------

  
  public boolean applicable(final Serializable value) {
    return value instanceof Date;
  }

  
  public Serializable convert(final Serializable value) {
    return value != null ? _dateFormat.format(value) : null;
  }

  // ---------------------------------------------------
  // Private methods
  // ---------------------------------------------------

}
