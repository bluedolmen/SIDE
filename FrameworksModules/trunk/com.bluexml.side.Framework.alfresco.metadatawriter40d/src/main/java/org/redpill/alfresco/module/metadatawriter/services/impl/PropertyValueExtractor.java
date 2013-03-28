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

import java.io.Serializable;
import java.util.Date;

import org.alfresco.service.cmr.repository.MLText;
import org.springframework.extensions.surf.util.I18NUtil;

//TODO: Move to converter
public class PropertyValueExtractor {
  // ---------------------------------------------------
  // Public constructor
  // ---------------------------------------------------

  // ---------------------------------------------------
  // Public methods
  // ---------------------------------------------------
  public static Serializable extractValue(final Serializable property) {
    if (null == property) {
      return null;
    }

    if (property instanceof MLText) {
      return ((MLText) property).getClosestValue(I18NUtil.getContentLocale());
    }

    if (property instanceof String) {
      return property;
    }

    if (property instanceof Date) {
      return property;
    }

    // This is the best guess if the underlying class is not supported in any
    // other extract value method
    return property.toString();
  }

  // ---------------------------------------------------
  // Private methods
  // ---------------------------------------------------

}
