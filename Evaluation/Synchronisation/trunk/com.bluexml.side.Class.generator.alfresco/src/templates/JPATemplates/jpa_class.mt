<%
metamodel http://www.kerblue.org/class/1.0
import templates.servicesTemplates.Common
import templates.servicesTemplates.Attribute
import templates.servicesTemplates.Association

import com.bluexml.side.clazz.generator.alfresco.services.AttributeServices
import com.bluexml.side.clazz.generator.alfresco.services.AssociationServices
import com.bluexml.side.clazz.generator.alfresco.services.ClassServices
%>

<%script type="clazz.Clazz" name="jpa_class" file="<%getFilePath()%>"%>

package <%ancestor().name.sep(".")%>;

import java.io.Serializable;
import java.util.*;
import javax.persistence.*;

@Entity
public class <%name%> <%extends()%> implements Serializable {

<%-- Defines an ID if the class does not have any generalization (else it already have an inherited id) --%>
<%if (! generalizations){%>
	@Id <%-- @GeneratedValue(strategy = GenerationType.AUTO) --%>
	private Long id;
	<%-- private Long originalId; --%>
	private String uuid;
<%}%>

<%if (attributes.length() > 0){%>
	/* 
	 * Intrinsic attributes
	 */
<%for (attributes){%>
	private <%getJPAJavaType()%> <%name%>;
<%}%>
<%}%>
<%for (aspects){%>
	/* 
	 * Aspect "<%name%>" attributes
	 */
	 <%for (attributes){%>
	private <%getJPAJavaType()%> <%name%>;
	 <%}%>	 
<%}%>

<%for (getSourceAssociations()){%>
	<%associationAnnotations(current("Clazz"))%>
	private <%getOppositeAssociationEnd(current("Clazz")).listOnMany()%> <%name%>;
	
<%}%>

	/*
	 * Accessors
	 */
<%if (! generalizations){%>
	 
	public Long getId() {
		return this.id;
	}	 
	public void setId(Long id_) {
		this.id = id_;
	}
<%-- 
	public Long getOriginalId() {
		return this.originalId;
	}	 
	public void setOriginalId(Long originalId_) {
		this.originalId = originalId_;
	}
--%>	
	public String getUuid() {
		return this.uuid;
	}
	public void setUuid(String uuid_) {
		this.uuid = uuid_;
	}
	
<%}%> 
<%getAccessor().indentTab()%>

	<%for (aspects){%><%getAccessor().indentTab()%>
	<%}%>
	<%for (getSourceAssociations()){%><%getAccessor(current("Clazz")).indentTab()%>
	<%}%>
	
}
<%script type="clazz.AbstractClass" name="getAccessor" post="trim()"%>
<%for (attributes){%>
public <%getJPAJavaType()%> get<%name.toU1Case()%>() {
	return this.<%name%>;
}
public void set<%name.toU1Case()%>(<%getJPAJavaType()%> <%name%>_) {
	this.<%name%> = <%name%>_;
}
	
<%}%>

<%script type="clazz.Association" name="getAccessor" post="trim()"%>
public <%getOppositeAssociationEnd(args(0)).listOnMany()%> get<%name.toU1Case()%>() {
	return this.<%name%>;
}

public void set<%name.toU1Case()%>(<%getOppositeAssociationEnd(args(0)).listOnMany()%> <%name%>_) {
	this.<%name%> = <%name%>_;
}

<%script type="clazz.Clazz" name="getFilePath" post="trim()"%>
<%ancestor().name.sep("/")%>/<%name%>.java

<%script type="clazz.Clazz" name="extends" post="trim()" %>
<%if (generalizations.length() > 0){%>
extends <%generalizations.nFirst().name%>
<%}%>

<%script type="clazz.Association" name="associationAnnotations" post="trim()"%>
<%if (!getAssociationEnd(args(0)).isMany() && !getOppositeAssociationEnd(args(0)).isMany()){%>
@OneToOne<%parenthesisIfNotEmpty(getCommonAnnotationOptions())%>
<%}else{%>
<%if (!getAssociationEnd(args(0)).isMany() && getOppositeAssociationEnd(args(0)).isMany()){%>
@OneToMany<%parenthesisIfNotEmpty(getCommonAnnotationOptions())%>
<%}else{%>
<%if (getAssociationEnd(args(0)).isMany() && !getOppositeAssociationEnd(args(0)).isMany()){%>
@ManyToOne<%parenthesisIfNotEmpty(getCommonAnnotationOptions())%>
<%}else{%>
<%if (getAssociationEnd(args(0)).isMany() && getOppositeAssociationEnd(args(0)).isMany()){%>
@ManyToMany<%parenthesisIfNotEmpty(getCommonAnnotationOptions())%>
<%}%>
<%}%>
<%}%>
<%}%>
<%if (getOppositeAssociationEnd(args(0)).isMandatory()){%>
@JoinColumn(nullable = false)
<%}%>

<%script type="clazz.Association" name="getCommonAnnotationOptions" post="trim()"%>
<%separateWithComma(getCascadeAnnotationProperty(),getFetchTypeAnnotationProperty())%>

<%script type="ecore.EObject" name="separateWithComma" post="trim()"%>
<%args(0)%><%if (!(args(0) == "" || args(1) == "")){%>,<%}%><%args(1)%>

<%script type="ecore.EObject" name="parenthesisIfNotEmpty" post="trim()" %>
<%if (! args(0).equalsIgnoreCase("")){%>
(<%args(0)%>)
<%}%>

<%script type="AssociationEnd" name="listOnMany" post="trim()"%>
<%if (isMany()){%>
List<<%linkedClass.name%>>
<%}else{%>
<%linkedClass.name%>
<%}%>

<%script type="clazz.Association" name="getCascadeAnnotationProperty" post="trim()"%>
<%if (associationType == "Composition"){%>
cascade = CascadeType.REMOVE
<%}%>

<%script type="clazz.Association" name="getFetchTypeAnnotationProperty" post="trim()"%>
fetch = FetchType.LAZY