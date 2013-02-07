
package com.bluexml.side.alfresco.binding;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.bluexml.side.alfresco.binding package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {


    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.bluexml.side.alfresco.binding
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Permissions }
     * 
     */
    public Permissions createPermissions() {
        return new Permissions();
    }

    /**
     * Create an instance of {@link GrantedToGroup }
     * 
     */
    public GrantedToGroup createGrantedToGroup() {
        return new GrantedToGroup();
    }

    /**
     * Create an instance of {@link Namespace }
     * 
     */
    public Namespace createNamespace() {
        return new Namespace();
    }

    /**
     * Create an instance of {@link IncludePermissionGroup }
     * 
     */
    public IncludePermissionGroup createIncludePermissionGroup() {
        return new IncludePermissionGroup();
    }

    /**
     * Create an instance of {@link PermissionGroup }
     * 
     */
    public PermissionGroup createPermissionGroup() {
        return new PermissionGroup();
    }

    /**
     * Create an instance of {@link RequiredPermission }
     * 
     */
    public RequiredPermission createRequiredPermission() {
        return new RequiredPermission();
    }

    /**
     * Create an instance of {@link DynamicAuthority }
     * 
     */
    public DynamicAuthority createDynamicAuthority() {
        return new DynamicAuthority();
    }

    /**
     * Create an instance of {@link Namespaces }
     * 
     */
    public Namespaces createNamespaces() {
        return new Namespaces();
    }

    /**
     * Create an instance of {@link GlobalPermission }
     * 
     */
    public GlobalPermission createGlobalPermission() {
        return new GlobalPermission();
    }

    /**
     * Create an instance of {@link PermissionSet }
     * 
     */
    public PermissionSet createPermissionSet() {
        return new PermissionSet();
    }

    /**
     * Create an instance of {@link Permission }
     * 
     */
    public Permission createPermission() {
        return new Permission();
    }

}
