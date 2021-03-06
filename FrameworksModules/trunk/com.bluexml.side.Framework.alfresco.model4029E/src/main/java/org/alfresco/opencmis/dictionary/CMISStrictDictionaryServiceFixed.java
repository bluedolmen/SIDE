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
/*
 * Copyright (C) 2005-2010 Alfresco Software Limited.
 *
 * This file is part of Alfresco
 *
 * Alfresco is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Alfresco is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with Alfresco. If not, see <http://www.gnu.org/licenses/>.
 */
package org.alfresco.opencmis.dictionary;

import java.util.Collection;

import org.alfresco.opencmis.mapping.CMISMapping;
import org.alfresco.service.cmr.dictionary.AssociationDefinition;
import org.alfresco.service.cmr.dictionary.ClassDefinition;
import org.alfresco.service.namespace.QName;
import org.apache.chemistry.opencmis.commons.enums.BaseTypeId;

/**
 * CMIS Dictionary which provides Types that strictly conform to the CMIS
 * specification.
 * 
 * That is, only maps types to one of root Document, Folder, Relationship &
 * Policy.
 * 
 * @author davidc
 */
public class CMISStrictDictionaryServiceFixed extends CMISAbstractDictionaryService
{

    @Override
    protected void createDefinitions(DictionaryRegistry registry)
    {
        createTypeDefs(registry, dictionaryService.getAllTypes());
        createAssocDefs(registry, dictionaryService.getAllAssociations());
        createTypeDefs(registry, dictionaryService.getAllAspects());
    }

    /**
     * Create Type Definitions
     * 
     * @param registry
     * @param classQNames
     */
    private void createTypeDefs(DictionaryRegistry registry, Collection<QName> classQNames)
    {
        for (QName classQName : classQNames)
        {
            // skip items that are remapped to CMIS model
            if (cmisMapping.isRemappedType(classQName))
                continue;

            // create appropriate kind of type definition
            ClassDefinition classDef = dictionaryService.getClass(classQName);
            String typeId = null;
            AbstractTypeDefinitionWrapper objectTypeDef = null;
            if (cmisMapping.isValidCmisDocument(classQName))
            {
                typeId = cmisMapping.getCmisTypeId(BaseTypeId.CMIS_DOCUMENT, classQName);
                objectTypeDef = new DocumentTypeDefinitionWrapper(cmisMapping, accessorMapping, luceneBuilderMapping, typeId, classDef);
            } else if (cmisMapping.isValidCmisFolder(classQName))
            {
                typeId = cmisMapping.getCmisTypeId(BaseTypeId.CMIS_FOLDER, classQName);
                objectTypeDef = new FolderTypeDefintionWrapper(cmisMapping, accessorMapping, luceneBuilderMapping, typeId, classDef);
            } else if (cmisMapping.isValidCmisPolicy(classQName))
            {
                typeId = cmisMapping.getCmisTypeId(BaseTypeId.CMIS_POLICY, classQName);
                // SIDE FIX for https://issues.alfresco.com/jira/browse/ALF-12723
                // please to remove Fix for alfresco 4.1 and 4.0.1 enterprise 
                objectTypeDef = new PolicyTypeDefintionWrapperFixed(cmisMapping, accessorMapping, luceneBuilderMapping, typeId, classDef);
            }

            if (objectTypeDef != null)
            {
                registry.registerTypeDefinition(objectTypeDef);
            }
        }
    }

    /**
     * Create Relationship Definitions
     * 
     * @param registry
     * @param classQNames
     */
    private void createAssocDefs(DictionaryRegistry registry, Collection<QName> classQNames)
    {
        // register base type
        String typeId = cmisMapping.getCmisTypeId(BaseTypeId.CMIS_RELATIONSHIP, CMISMapping.RELATIONSHIP_QNAME);
        RelationshipTypeDefintionWrapper objectTypeDef = new RelationshipTypeDefintionWrapper(cmisMapping,
                accessorMapping, luceneBuilderMapping, typeId, dictionaryService.getClass(CMISMapping.RELATIONSHIP_QNAME));

        registry.registerTypeDefinition(objectTypeDef);

        // register all other relationships
        for (QName classQName : classQNames)
        {
            if (!cmisMapping.isValidCmisRelationship(classQName))
                continue;

            // create appropriate kind of type definition
            AssociationDefinition assocDef = dictionaryService.getAssociation(classQName);
            typeId = cmisMapping.getCmisTypeId(BaseTypeId.CMIS_RELATIONSHIP, classQName);
            objectTypeDef = new RelationshipTypeDefintionWrapper(cmisMapping, accessorMapping, luceneBuilderMapping, 
                    typeId, assocDef);

            registry.registerTypeDefinition(objectTypeDef);
        }
    }
}
