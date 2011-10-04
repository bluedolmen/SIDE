<%
metamodel http://www.kerblue.org/class/1.0
import templates.servicesTemplates.Common
import com.bluexml.side.clazz.generator.alfresco.api.templates.services.classes
import com.bluexml.side.clazz.generator.alfresco.api.templates.javaAPI.lib
import com.bluexml.side.clazz.service.alfresco.ClassServices
import com.bluexml.side.clazz.service.alfresco.CommonServices
import com.bluexml.side.clazz.service.alfresco.AttributeServices
import com.bluexml.side.clazz.service.alfresco.AssociationServices
import com.bluexml.side.clazz.generator.alfresco.api.service.ValueGenerator

%>
<%script type="clazz.AbstractClass" name="commonMethods" %>
	
	/**
	 * get <%name.toU1Case()%> from repository
	 * @param uuid
	 * @return
	 * @throws RemoteException
	 * @throws RepositoryFault
	 */
	public <%name.toU1Case()%> get<%name.toU1Case()%>(String uuid) throws RemoteException, RepositoryFault {
		<%name.toU1Case()%> obj = new <%name.toU1Case()%>();
		// set uuid
		obj.setUuid(uuid);
		<%if (getAllSortedAttibutes().nSize() > 0){%>
		// get node			
		Node n = getNode(uuid);
		// set Properties
		setProperties(obj, n);
		<%}%>
		return obj;
	}
	
	/**
	 * update properties object from given node
	 * 
	 * @param obj
	 * @param n
	 */
	private void setProperties(<%name.toU1Case()%> obj, Node n) {
		NamedValue[] props = n.getProperties();
		for (NamedValue namedValue : props) {
			String propName = namedValue.getName();
			String value = namedValue.getValue();
			<%if (getAllSortedAttibutes().filter("Attribute").isMultivalued().nContains("true")){%>
			String[] values_ = namedValue.getValues();
			List<String> values = new ArrayList<String>();
			if (values_ != null) {
				for (String value_ : values_) {
					values.add(value_);
				}
			} else {
				values.add(value);
			}			
			<%}%>			
		<%for (getAllSortedAttibutes()){%>
			<%if (i() != 0){%>} else<%}%> if (propName.equals(ModelQNames.CONTENT_MODEL_ATTRIBUTE_<%eContainer().name.toUpperCase()%>_<%name.toUpperCase()%>.getQnameString())) {
				obj.set<%name.toU1Case()%>(<%if (isMultivalued()){%>values<%}else{%>value<%}%>);
				<%if (i() == current("AbstractClass").getAllSortedAttibutes().nSize() -1){%>
			}
			<%}%>
		<%}%>

		}
	}
	
<%script type="clazz.Attribute" name="generatePropertiesStatement" %>
		if (<%name%> != null) {
		<%if (isMultivalued()){%>
			if (<%name%>.size() > 1) {
				props.add(Utils.createNamedValue(ModelQNames.CONTENT_MODEL_ATTRIBUTE_<%eContainer().name.toUpperCase()%>_<%name.toUpperCase()%>.getQnameString(), <%name%>.toArray(new String[<%name%>.size()])));
			} else {
				props.add(Utils.createNamedValue(ModelQNames.CONTENT_MODEL_ATTRIBUTE_<%eContainer().name.toUpperCase()%>_<%name.toUpperCase()%>.getQnameString(), <%name%>.get(0)));
			}
		<%}else{%>
			props.add(Utils.createNamedValue(ModelQNames.CONTENT_MODEL_ATTRIBUTE_<%eContainer().name.toUpperCase()%>_<%name.toUpperCase()%>.getQnameString(), <%name%>));
		<%}%>
		}