<%--
Copyright (C) BlueXML 2005-2008

This program is free software; you can redistribute it and/or modify
 it under the terms of the GNU General Public License as published by
 the Free Software Foundation; either version 2.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with this program; if not, write to the Free Software
Foundation, Inc., 59 Temple Place, Boston, MA 02111.
 --%>
<%
metamodel http://www.kerblue.org/class/1.0
import templates.servicesTemplates.Common
import templates.servicesTemplates.Attribute
import templates.servicesTemplates.Association
import templates.Model.alfrescoGenerator_model_aspects
import templates.Model.alfrescoGenerator_model_classes
import com.bluexml.side.clazz.generator.alfresco.ClassAlfrescoGenerator
import com.bluexml.side.clazz.service.alfresco.ClassServices
import com.bluexml.side.clazz.service.alfresco.CommonServices
import com.bluexml.side.clazz.service.alfresco.AttributeServices
import com.bluexml.side.clazz.service.alfresco.AssociationServices
%>

<%script type="clazz.Model" name="model_constraints" %>
	 <constraints>
		<constraint name="<%name%>:nomenclature:comparison" type="LIST">
            <parameter name="allowedValues">
                <list>
                    <value>&lt;</value>
                    <value>&gt;</value>
                    <value>=</value>
                </list>
            </parameter>
        </constraint>
        
        <constraint name="<%name%>:nomenclature:booleanoperator" type="LIST">
            <parameter name="allowedValues">
                <list>
                    <value>AND</value>
                    <value>OR</value>
                    <value>NOT</value>
                </list>
            </parameter>
        </constraint>
        
		<constraint name="<%name%>:constraint:mail" type="REGEX">
	        <parameter name="expression"><value>^(|[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\.[A-Za-z]+)$</value></parameter>
	        <parameter name="requiresMatch"><value>true</value></parameter>
	    </constraint>
	    
	    <%for (getAllConstraints()){%>
	    <constraint name="<%getPrefixedQualifiedName%>" type="<%constraintType%>">
	        <%for (params){%>
	        <parameter name="<%name%>">
	        <%if (values.nSize() > 1){%>
	        	<list>
	        		<%constraints_values%>
	        	</list>
	        <%}else{%>
	        	<%constraints_values%>
	        <%}%>
	        </parameter>
	        <%}%>
	    </constraint>
	    <%}%>
	    
		<!-- STARTMMLOOP -->
	    <%for (getAllEnumerations().nSort("name")) {%>		   
	    <constraint name="<%getListContraintPrefixedQName()%>" type="LIST">
            <parameter name="allowedValues">
                <list>
			<%for (literals.nSort("name")) {%>
				<%if (value != null){%>
					<value><%value%></value>
				<%}else{%>
					<value><%name%></value>
				<%}%>
			<%}%>
                </list>
            </parameter>
            <%if (metainfo[key.equalsIgnoreCase("caseSensitive")].nSize()>0){%>
            <parameter name="caseSensitive">
				<value><%metainfo[key.equalsIgnoreCase("caseSensitive")].nFirst().value%></value>
			</parameter>
			<%}%>
        </constraint>
        <%}%>
		<!-- ENDMMLOOP -->
        
	 </constraints>
<%script type="common.ConstraintParam" name="constraints_values"%>
<%for (values){%>
<value><%current()%></value>
<%}%>