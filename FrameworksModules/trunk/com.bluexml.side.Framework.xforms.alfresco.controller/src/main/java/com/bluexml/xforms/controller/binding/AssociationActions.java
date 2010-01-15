
package com.bluexml.xforms.controller.binding;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for AssociationActions.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="AssociationActions">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="DELETE"/>
 *     &lt;enumeration value="DELETE_ALL"/>
 *     &lt;enumeration value="ADD_DELETE_OTHER"/>
 *     &lt;enumeration value="ADD"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "AssociationActions")
@XmlEnum
public enum AssociationActions {

    DELETE,
    DELETE_ALL,
    ADD_DELETE_OTHER,
    ADD;

    public String value() {
        return name();
    }

    public static AssociationActions fromValue(String v) {
        return valueOf(v);
    }

}
