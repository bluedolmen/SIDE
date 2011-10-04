<%
metamodel http://www.kerblue.org/class/1.0
import templates.servicesTemplates.Common
import com.bluexml.side.clazz.generator.alfresco.api.templates.javaAPI.lib
import com.bluexml.side.clazz.generator.alfresco.api.templates.services.classes
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
	 */
	public <%name.toU1Case()%> get<%name.toU1Case()%>(NodeRef uuid) {
		if (serviceRegistry.getNodeService().exists(uuid)) {
			<%name.toU1Case()%> obj = new <%name.toU1Case()%>();
			// set uuid
			obj.setUuid(uuid.toString());
			<%if (getAllSortedAttibutes().nSize() > 0){%>
			// get node properties
			Map<QName, Serializable> props = serviceRegistry.getNodeService().getProperties(uuid);
			setProperties(obj, props);
			<%}%>
			return obj;
		}
		return null;
	}
	
	
	/**
	 * get <%name.toU1Case()%> in given version label
	 * @param uuid identifier
	 * @param label version
	 * @return
	 */
	public <%name.toU1Case()%> get<%name.toU1Case()%>Version(NodeRef uuid, String label) {
		if (serviceRegistry.getNodeService().exists(uuid)) {
			Version v = serviceRegistry.getVersionService().getVersionHistory(uuid).getVersion(label);
			NodeRef nodev = v.getVersionedNodeRef();
			return get<%name.toU1Case()%>(nodev);
		}
		return null;
	}


	/**
	 * update properties object from given node
	 * 
	 * @param obj
	 * @param n
	 */
<%if (getAllSortedAttibutes().filter("Attribute").isMultivalued().nContains("true")){%>
	@SuppressWarnings("unchecked")
<%}%>
	private void setProperties(<%name.toU1Case()%> obj, Map<QName, Serializable> props) {
	
		for (Map.Entry<QName, Serializable> namedValue : props.entrySet()) {
			String propName = namedValue.getKey().toString();
			Serializable valueS = namedValue.getValue();
			String value = valueS.toString();
			
		<%for (getAllSortedAttibutes()){%>
			<%if (i() != 0){%>} else<%}%> if (propName.equals(ModelQNames.CONTENT_MODEL_ATTRIBUTE_<%eContainer().name.toUpperCase()%>_<%name.toUpperCase()%>.getQnameString())) {			
			<%if (current("Attribute").typ == "Date" || current("Attribute").typ == "DateTime" || current("Attribute").typ == "Time"){%>
				<%if (isMultivalued()){%>
				List<String> values = new ArrayList<String>();
				if (valueS instanceof List) {
					List<Date> dates = (List<Date>) namedValue.getValue();
					for (Date d : dates) {
						values.add(dateTimeFormatter.print(((Date) d).getTime()));
					}
				} else {
					values.add(dateTimeFormatter.print(((Date) valueS).getTime()));
				}				
				<%}%>
				obj.set<%name.toU1Case()%>(<%if (isMultivalued()){%>values<%}else{%>dateTimeFormatter.print(((Date) valueS).getTime())<%}%>);				
			<%}else{%>
				<%if (isMultivalued()){%>
				List<String> values = new ArrayList<String>();
				if (valueS instanceof List) {
					List<Serializable> values_ =(List<Serializable>) namedValue.getValue();
					for (Serializable serializable : values_) {
						values.add(serializable.toString());
					}	
				} else {
					values.add(value);
				}
				<%}%>
				obj.set<%name.toU1Case()%>(<%if (isMultivalued()){%>values<%}else{%>value<%}%>);
			<%}%>			
			<%if (i() == current("AbstractClass").getAllSortedAttibutes().nSize() -1){%>
			}
			<%}%>
		<%}%>

		}
	}
	
	private Map<QName, Serializable> getProperties(<%name.toU1Case()%> obj) {
		Map<QName, Serializable> props = new HashMap<QName, Serializable>();
		<%for (getAllSortedAttibutes()){%>
		props.put(QName.createQName(ModelQNames.CONTENT_MODEL_ATTRIBUTE_<%eContainer().name.toUpperCase()%>_<%name.toUpperCase()%>.getQnameString()), <%if (isMultivalued()){%>(Serializable) <%}%>obj.get<%name.toU1Case()%>());
		<%}%>		
		
		return props;
	}
	
	/**
	 * Save object in alfresco repository
	 * @param obj
	 * @return
	 */
	public <%name.toU1Case()%> update(<%name.toU1Case()%> obj) {
		Map<QName, Serializable> props = getProperties(obj);
		
		NodeRef nodeToUpdate = new NodeRef(obj.getUuid());
		updateProperties(nodeToUpdate, props);

		return get<%name.toU1Case()%>(nodeToUpdate);
	}
	
	/**
	 * Method to update <%name.toU1Case()%>
	 * 
	 * @param uuid
	 *            unique id of the object in repository
<%for (getAllSortedAttibutes()){%>
	 * @param <%name%>
	 *            <%current("AbstractClass").name.toU1Case()%> attribute
<%}%>
	 * @return
	 */
	public <%name.toU1Case()%> update(String uuid<%if (getAllSortedAttibutes().nSize() > 0){%>, <%}%><%getJavaPropertiesMethodSignature()%>) {
		NodeRef nodeToUpdate = new NodeRef(uuid);
		
<%if (getAllSortedAttibutes().nSize() > 0){%>		
<%generatePropertiesStatement()%>		
		updateProperties(nodeToUpdate, props);		
<%}%>
		return get<%name.toU1Case()%>(nodeToUpdate);
	}
	


<%script type="clazz.AbstractClass" name="commonWebScriptMethods" %>

	/**
	 * get <%name.toU1Case()%> from repository
	 * @param uuid
	 * @return
	 */
	public <%name.toU1Case()%> get<%name.toU1Case()%>(String uuid) {
		return <%getJavaAPIName().toL1Case()%>.get<%name.toU1Case()%>(new NodeRef(uuid));
	}
	
	/**
	 * get <%name.toU1Case()%> in given version label
	 * @param uuid identifier
	 * @param label version
	 * @return
	 */
	public <%name.toU1Case()%> get<%name.toU1Case()%>Version(String uuid, String label) {
		return <%getJavaAPIName().toL1Case()%>.get<%name.toU1Case()%>Version(new NodeRef(uuid), label);
	}
	
	/**
	 * Method to update <%name.toU1Case()%>
	 * 
	 * @param uuid
	 *            unique id of the object in repository
<%for (getAllSortedAttibutes()){%>
	 * @param <%name%>
	 *            <%current("AbstractClass").name.toU1Case()%> attribute
<%}%>
	 * @return
	 */
	public <%name.toU1Case()%> update(String uuid<%if (getAllSortedAttibutes().nSize() > 0){%>, <%}%><%getJavaPropertiesMethodSignatureWebscript()%>) {
		return <%getJavaAPIName().toL1Case()%>.update(uuid<%if (getAllSortedAttibutes().nSize() > 0){%>, <%}%><%getJavaPropertiesMethodCallWebscript("")%>);
	}


	public static List<<%getJavaModelObjectName()%>> load<%getJavaModelObjectName()%>(JSONArray array) {
		List<<%getJavaModelObjectName()%>> l = new ArrayList<<%getJavaModelObjectName()%>>();
		for (Object object : array) {
			<%getJavaModelObjectName()%> f = load<%getJavaModelObjectName()%>( (JSONObject) object);
			l.add(f);
		}
		return l;
	}
	
<%if (getAllSortedAttibutes().filter("Attribute").isMultivalued().nContains("true")){%>
	@SuppressWarnings("unchecked")
<%}%>
	public static <%getJavaModelObjectName()%> load<%getJavaModelObjectName()%>(JSONObject object) {
		JSONObject js<%getJavaModelObjectName()%> = object.getJSONObject("<%getJavaModelObjectName()%>");		
		
		<%getJavaModelObjectName()%> f = new <%getJavaModelObjectName()%>();
		
		String uuid = js<%getJavaModelObjectName()%>.getString("uuid");
		f.setUuid(uuid);
		
		<%for (getAllSortedAttibutes()){%>
			<%if (isMultivalued()){%>
		List<String> ll<%name.toLowerCase()%> = new ArrayList<String>();
		JSONArray <%name.toLowerCase()%>Values = js<%current("AbstractClass").getJavaModelObjectName()%>.getJSONArray("<%name%>");
		ll<%name.toLowerCase()%>.addAll(<%name.toLowerCase()%>Values);
		f.set<%name.toU1Case()%>(ll<%name.toLowerCase()%>);
			<%}else{%>			
		String <%name.toLowerCase()%> =	js<%current("AbstractClass").getJavaModelObjectName()%>.getString("<%name%>");
		f.set<%name.toU1Case()%>(<%name.toLowerCase()%>);
			<%}%>
		<%}%>
		return f;
	}
<%script type="clazz.AbstractClass" name="generatePropertiesStatement" %>
		Map<QName, Serializable> props = new HashMap<QName, Serializable>();
	<%for (getAllSortedAttibutes()){%>
		if (<%name%> != null) {
		<%if (isMultivalued()){%>
			if (<%name%>.size() == 1) {
				props.put(QName.createQName(ModelQNames.CONTENT_MODEL_ATTRIBUTE_<%eContainer().name.toUpperCase()%>_<%name.toUpperCase()%>.getQnameString()), <%name%>.get(0));
			} else {
				props.put(QName.createQName(ModelQNames.CONTENT_MODEL_ATTRIBUTE_<%eContainer().name.toUpperCase()%>_<%name.toUpperCase()%>.getQnameString()), (Serializable) <%name%>);
			}		
		<%}else{%>
		props.put(QName.createQName(ModelQNames.CONTENT_MODEL_ATTRIBUTE_<%eContainer().name.toUpperCase()%>_<%name.toUpperCase()%>.getQnameString()), (Serializable) <%name%>);
		<%}%>
		}
	<%}%>

<%script type="clazz.AbstractClass" name="javaWebScriptFactoryAdapterName" %>
<%getJavaAPIName()%>Adapter
<%script type="clazz.AbstractClass" name="getBeanId" %>
side.api.<%service::getRootContainer().name%>.<%args(0)%>