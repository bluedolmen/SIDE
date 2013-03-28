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
package org.redpill.alfresco.module.metadatawriter.factories.impl;

import java.util.HashSet;
import java.util.Set;

import org.redpill.alfresco.module.metadatawriter.factories.MetadataServiceRegistry;
import org.redpill.alfresco.module.metadatawriter.factories.UnknownServiceNameException;
import org.redpill.alfresco.module.metadatawriter.services.MetadataService;

public class MetadataServiceRegistryImpl implements MetadataServiceRegistry {

  private final Set<MetadataService> _services = new HashSet<MetadataService>();

  // ---------------------------------------------------
  // Public constructor
  // ---------------------------------------------------

  public MetadataServiceRegistryImpl() {
    // Empty
  }

  // ---------------------------------------------------
  // Public methods
  // ---------------------------------------------------

  public MetadataService findService(final String serviceName) throws UnknownServiceNameException {

    assert serviceName != null : "Must provide non null serviceName!";

    for (final MetadataService s : _services) {
      if (serviceName.equals(s.getServiceName())) {
        return s;
      }
    }

    throw new UnknownServiceNameException("Could not find any metadata service named " + serviceName + " among " + describeAvailableServices());

  }

  public void register(final MetadataService service) {
    assert service != null : "Will not register null service!";

    _services.add(service);
  }

  @Override
  public String toString() {
    return super.toString() + " " + describeAvailableServices();
  }

  // ---------------------------------------------------
  // Private methods
  // ---------------------------------------------------
  private String describeAvailableServices() {
    final StringBuilder sb = new StringBuilder("Available services:");

    for (final MetadataService service : _services) {
      sb.append(" [" + service.toString() + "]");
    }

    return sb.toString();
  }

}
