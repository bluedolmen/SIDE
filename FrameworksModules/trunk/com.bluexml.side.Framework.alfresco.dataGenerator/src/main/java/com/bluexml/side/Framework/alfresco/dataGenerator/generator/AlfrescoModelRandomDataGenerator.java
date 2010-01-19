/**
 * 
 */
package com.bluexml.side.Framework.alfresco.dataGenerator.generator;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.alfresco.service.cmr.dictionary.AspectDefinition;
import org.alfresco.service.cmr.dictionary.AssociationDefinition;
import org.alfresco.service.cmr.dictionary.ConstraintDefinition;
import org.alfresco.service.cmr.dictionary.DataTypeDefinition;
import org.alfresco.service.cmr.dictionary.PropertyDefinition;
import org.alfresco.service.cmr.dictionary.TypeDefinition;
import org.alfresco.service.namespace.QName;
import org.alfresco.util.ISO8601DateFormat;

import com.bluexml.side.Framework.alfresco.dataGenerator.context.SpringContext;
import com.bluexml.side.Framework.alfresco.dataGenerator.data.AlfrescoModelData;
import com.bluexml.side.Framework.alfresco.dataGenerator.data.IData;
import com.bluexml.side.Framework.alfresco.dataGenerator.dictionary.IDictionary;
import com.bluexml.side.Framework.alfresco.dataGenerator.graph.AlfrescoArc;
import com.bluexml.side.Framework.alfresco.dataGenerator.graph.AlfrescoNode;
import com.bluexml.side.Framework.alfresco.dataGenerator.graph.IArc;
import com.bluexml.side.Framework.alfresco.dataGenerator.graph.INode;
import com.bluexml.side.Framework.alfresco.dataGenerator.structure.AlfrescoModelStructure;
import com.bluexml.side.Framework.alfresco.dataGenerator.structure.IStructure;
import com.bluexml.side.framework.alfresco.unicity.UnicityXMLReader;

/**
 * @author davidchevrier
 *
 */
public class AlfrescoModelRandomDataGenerator implements IRandomGenerator {
	
	private IInstance instance;
	private IData alfrescoModelDatas;
	
	private IDictionary dictionary;
	private int numberOfNodes;
	private int numberOfOutputArcs;
	private static String scenario;
	
	private static int index;
	private static int numOfTypes;
	private int numOfSame;
	private Collection<Object> sameData = new ArrayList<Object>();
	
	private ArrayList<Serial> serializedData = new ArrayList<Serial>();
	
	private static class RandomMethods {
		
	    static Random randomGenerator = new Random();
		
		static Boolean generateRandomBoolean(){
			return Boolean.valueOf(randomGenerator.nextBoolean());
		}
		
		static Integer generateRandomInteger(){
			return Integer.valueOf(randomGenerator.nextInt());
		}
		
		static Long generateRandomLong(){
			return Long.valueOf(randomGenerator.nextLong());
		}
		
		static Float generateRandomFloat(){
			return Float.valueOf(randomGenerator.nextFloat());
		}
		
		static Double generateRandomDouble(){
			return Double.valueOf(randomGenerator.nextDouble());
		}
		
		static String generateRandomDate() throws Exception{
			//TODO besoin contraintes...
			
//			Date date = new Date(randomGenerator.nextLong());
//			return ISO8601DateFormat.format(date);
			//TODO besoin contraintes...
			
			DateFormat df = new SimpleDateFormat("yyyy/mm/dd");
			Date minDate = df.parse("1970/01/01");			
			Date maxDate = df.parse("3000/01/01");

			float f = randomGenerator.nextFloat();
			long l = (long)(f * (maxDate.getTime() - minDate.getTime()) ) + minDate.getTime();
			Date date = new Date(l);			
			
			return ISO8601DateFormat.format(date);
		}
		
		static String generateRandomString(String defaultValue, PropertyDefinition property){
			String data = null;
			if (scenario.equals("random")){
				if (defaultValue != null){
					data = defaultValue + "_" + Integer.valueOf(randomGenerator.nextInt()).toString();
				}
				else{
					String [] parts = property.getName().toString().split("_");
					String propertyName = parts[parts.length-1];
					data = propertyName.substring(0,4) + "_" + Integer.valueOf(randomGenerator.nextInt()).toString();
				}
			}
			else if (scenario.equals("incremental")){
				if (defaultValue != null){
					data = defaultValue + "_" + Integer.valueOf(index).toString();
				}
				else{
					String [] parts = property.getName().toString().split("_");
					String propertyName = parts[parts.length-1];
					data = propertyName.substring(0,4) + "_" + Integer.valueOf(index).toString();
				}
			}
			return data;
		}
		
		static Object generateRandomObject(){
			return new Object();
		}
		
		
		static TypeDefinition selectRandomlyType(Collection<TypeDefinition> types){
			int randomIndex = randomGenerator.nextInt(types.size());
			return ((ArrayList<TypeDefinition>)types).get(randomIndex);
		}
		

		static Object generateDataByDataTypeProperty(QName dataType, String defaultValue, PropertyDefinition property) throws Exception{
			//TODO � introduire: gestion des contraintes
			Object randomData = null;
			if (dataType.equals(DataTypeDefinition.BOOLEAN)){
				randomData = generateRandomBoolean();
			}
			else if (dataType.equals(DataTypeDefinition.INT)){
				randomData = generateRandomInteger();
			}
			else if (dataType.equals(DataTypeDefinition.LONG)){
				randomData = generateRandomLong();
			}
			else if (dataType.equals(DataTypeDefinition.FLOAT)){
				randomData = generateRandomFloat();
			}
			else if (dataType.equals(DataTypeDefinition.DOUBLE)){
				randomData = generateRandomDouble();
			}
			else if (dataType.equals(DataTypeDefinition.DATE) || dataType.equals(DataTypeDefinition.DATETIME)){
				randomData = generateRandomDate();
			}
			else if (dataType.equals(DataTypeDefinition.TEXT)){
				randomData = generateRandomString(defaultValue,property);
			}
			else if (dataType.equals(DataTypeDefinition.ANY)){
				randomData = generateRandomObject();
			}
			else {
				//throws exception
			}
			return randomData;
		}

		private static INode selectRandomlyNode(Collection<INode> nodes) {
			int randomIndex = randomGenerator.nextInt(nodes.size());
			return ((ArrayList<INode>) nodes).get(randomIndex);
		}

		@SuppressWarnings("unchecked")
		public static Object getRandomlyValue(ConstraintDefinition enumeration) {
			List<Object> allowedValues = new ArrayList<Object>();
			Map<String,Object> parameters = enumeration.getConstraint().getParameters();
			allowedValues = (List<Object>) parameters.get("allowedValues");
			int randomIndex = randomGenerator.nextInt(allowedValues.size());
			return allowedValues.get(randomIndex);
		}
		
	}
	
	/**
	 * @return the dictionary
	 */
	public IDictionary getDictionary() {
		return dictionary;
	}

	/**
	 * @param dictionary the dictionary to set
	 */
	public void setDictionary(IDictionary dictionary) {
		this.dictionary = dictionary;
	}

	/**
	 * @return the instance
	 */
	public IInstance getInstance() {
		return instance;
	}

	/**
	 * @param instance the instance to set
	 */
	public void setInstance(IInstance instance) {
		this.instance = instance;
	}

	/**
	 * @return the numberOfNodes
	 */
	public int getNumberOfNodes() {
		return numberOfNodes;
	}

	/**
	 * @param numberOfNodes the numberOfNodes to set
	 */
	public void setNumberOfNodes(int numberOfNodes) {
		this.numberOfNodes = numberOfNodes;
	}

	/**
	 * @return the numberOfOutputArcs
	 */
	public int getNumberOfOutputArcs() {
		return numberOfOutputArcs;
	}

	/**
	 * @param numberOfOutputArcs the numberOfOutputArcs to set
	 */
	public void setNumberOfOutputArcs(int numberOfOutputArcs) {
		this.numberOfOutputArcs = numberOfOutputArcs;
	}

	/**
	 * @return the alfrescoModelDatas
	 */
	public IData getAlfrescoModelDatas() {
		return alfrescoModelDatas;
	}

	/**
	 * @param alfrescoModelDatas the alfrescoModelDatas to set
	 */
	public void setAlfrescoModelDatas(IData alfrescoModelDatas) {
		this.alfrescoModelDatas = alfrescoModelDatas;
	}

	public String getScenario() {
		return scenario;
	}

	public void setScenario(String scenario) {
		this.scenario = scenario;
	}

	public boolean generateNodesInstances(IStructure structure) throws Exception{
		List<INode> nodesInstances = new ArrayList<INode>();
		Collection<TypeDefinition> createdTypes = new ArrayList<TypeDefinition>();
		Collection<TypeDefinition> types = ((AlfrescoModelStructure) structure).getTypes();
		Collection<AssociationDefinition> compositions = extractCompositions(((AlfrescoModelStructure) structure).getAssociations());
		Collection<TypeDefinition> sourcesTypesOfCompositions = getSourcesTypesOfCompositions(compositions);
		for (int numOfNodes = 0; numOfNodes < numberOfNodes; numOfNodes++){
				TypeDefinition type = RandomMethods.selectRandomlyType(types);
				createdTypes.add(type);
		}
		Collection<TypeDefinition> deletedTargetsTypes = deleteTargetsTypesOfNotCreatedSourcesTypesOfCompositions(getSourcesTypesOfCompositionsNotCreated(createdTypes, sourcesTypesOfCompositions),compositions);
		createdTypes.removeAll(deletedTargetsTypes);
		Map<TypeDefinition,Integer> sameNodes = new HashMap<TypeDefinition,Integer>();
		for (int k = 0; k < types.size(); k++){
			sameNodes.put(((ArrayList<TypeDefinition>)types).get(k), 0);
		}
		for (int i = 0; i < createdTypes.size(); i++){
			TypeDefinition type = ((ArrayList<TypeDefinition>)createdTypes).get(i);
			numOfTypes = countSameTypes(createdTypes,type) - sameNodes.get(type).intValue();
			if (getUnicityProperties(type,((AlfrescoModelStructure) structure).getProperties().get(type)).isEmpty()){
				if (scenario.equals("incremental")){
					if (numOfTypes > 0){
						numOfSame = RandomMethods.randomGenerator.nextInt(numOfTypes + 1);
					}
					else{
						numOfSame = 0;
					}
				}
				else if (scenario.equals("random")){
					numOfSame = 1;
				}
			}
			else{
				numOfSame = 1;
			}
			for (int j = 0; j < numOfSame; j++){
				nodesInstances.add(((Instance)instance).instanciation(type));
				if (j == 0){
					sameNodes.put(type,sameNodes.get(type).intValue() + Integer.valueOf(numOfSame));
				}
			}
			index++;
		}
		((AlfrescoModelData) alfrescoModelDatas).setGeneratedTypesInstances(nodesInstances);
		PrintWriter printwriter = new PrintWriter(new FileOutputStream("tomcat/webapps/alfresco/WEB-INF/classes/META-INF/unicity.serial"));
	    printwriter.println("");
	    printwriter.close();
	    numOfTypes = 0;
	    index = 0;
	    serializedData.clear(); //why because instance's variable ?
	    numOfSame = 0; //
		return true;
	}

	private int countSameTypes(Collection<TypeDefinition> createdTypes, TypeDefinition type) {
		int same =0;
		for (TypeDefinition typeDef : createdTypes){
			if (typeDef.equals(type)){
				same ++;
			}
		}
		return same;
	}

	private Collection<TypeDefinition> deleteTargetsTypesOfNotCreatedSourcesTypesOfCompositions(Collection<TypeDefinition> sourcesTypesOfCompositionsNotCreated, Collection<AssociationDefinition> compositions){
		Collection<TypeDefinition> deletedTargetsTypes = new ArrayList<TypeDefinition>();
		for (TypeDefinition sourceTypeNotCreated : sourcesTypesOfCompositionsNotCreated){
			for (AssociationDefinition composition : compositions){
				if (((TypeDefinition) composition.getSourceClass()).equals(sourceTypeNotCreated)){
					deletedTargetsTypes.add((TypeDefinition) composition.getTargetClass());
				}
			}
		}
		return deletedTargetsTypes;
	}

	private Collection<TypeDefinition> getSourcesTypesOfCompositionsNotCreated(Collection<TypeDefinition> createdTypes, Collection<TypeDefinition> sourcesTypesOfCompositions) {
		Collection<TypeDefinition> notCreatedSources = new ArrayList<TypeDefinition>();
		for (TypeDefinition sourceType : sourcesTypesOfCompositions){
			if (!createdTypes.contains(sourceType)){
				notCreatedSources.add(sourceType);
			}
		}
		return notCreatedSources;
	}

	private Collection<TypeDefinition> getSourcesTypesOfCompositions(Collection<AssociationDefinition> compositions) {
		Collection<TypeDefinition> sources = new ArrayList<TypeDefinition>();
		for (AssociationDefinition composition : compositions){
			sources.add((TypeDefinition) composition.getSourceClass());
		}
		return sources;
	}

	private Collection<AssociationDefinition> extractCompositions(Collection<AssociationDefinition> associations) {
		Collection<AssociationDefinition> compositions = new ArrayList<AssociationDefinition>();
		for (AssociationDefinition association : associations){
			if (association.isChild()){
				compositions.add(association);
			}
		}
		return compositions;
	}

	public void generateArcsInstances(IStructure structure) throws Exception{
			List<IArc> arcsInstances = new ArrayList<IArc>();
			Collection<AssociationDefinition> associations = ((AlfrescoModelStructure) structure).getAssociations();
			for (AssociationDefinition associationDefinition : associations) {
				List<IArc> arcsInstancesByAssociation = new ArrayList<IArc>();
				TypeDefinition sourceType = (TypeDefinition) associationDefinition.getSourceClass();
				Collection<INode> sourcesNodes = getGeneratedNodesByType(sourceType);
				boolean sourceMultiplicity = associationDefinition.isSourceMany();
				TypeDefinition targetType = (TypeDefinition) associationDefinition.getTargetClass();
				Collection<INode> targetsNodes = getGeneratedNodesByType(targetType);
				boolean targetMultiplicity = associationDefinition.isTargetMany();
				if (!sourcesNodes.isEmpty() && !targetsNodes.isEmpty()){
					if (!sourceMultiplicity && !targetMultiplicity){
						arcsInstancesByAssociation = generateArcsInstancesCase11(sourcesNodes,targetsNodes,associationDefinition,associations,arcsInstances);
					}
					else if (!sourceMultiplicity && targetMultiplicity){
						arcsInstancesByAssociation = generateArcsInstancesCase1N(sourcesNodes,targetsNodes,associationDefinition,associations,arcsInstances);
					}
					else if (sourceMultiplicity && !targetMultiplicity){
						arcsInstancesByAssociation = generateArcsInstancesCaseN1(sourcesNodes,targetsNodes,associationDefinition,associations,arcsInstances);
					}
					else if (sourceMultiplicity && targetMultiplicity){
						arcsInstancesByAssociation = generateArcsInstancesCaseNN(sourcesNodes,targetsNodes,associationDefinition);
					}
					arcsInstances.addAll(arcsInstancesByAssociation);
				}
			}
			((AlfrescoModelData) alfrescoModelDatas).setGeneratedAssociationsInstances(arcsInstances);
	}

	private List<IArc> generateArcsInstancesCaseN1(Collection<INode> sourcesNodes, Collection<INode> targetsNodes,AssociationDefinition associationDefinition,
			Collection<AssociationDefinition> associations, Collection<IArc> generatedArcs) {
		List<IArc> arcsInstances = new ArrayList<IArc>();
		while (!targetsNodes.isEmpty()){
			INode target = RandomMethods.selectRandomlyNode(targetsNodes);
			int numberOfArcs = 0;
			while (numberOfArcs < numberOfOutputArcs && !sourcesNodes.isEmpty()){
				INode source = RandomMethods.selectRandomlyNode(sourcesNodes);
				IArc arc = createRandomlyArc(source,target,associationDefinition);
				AssociationDefinition invAssoc = searchInverseAssoc(source, target, associationDefinition, associations);
				if (invAssoc != null){
					IArc invArc = getInverseGeneratedArc(source,target,invAssoc,generatedArcs);
					if (invArc != null){
						arc = null;
					}
				}
				if (arc != null){
					arcsInstances.add(arc);
					numberOfArcs++;
				}
				sourcesNodes.remove(source);
			}
			targetsNodes.remove(target);
		}
		return arcsInstances;
	}

	private List<IArc> generateArcsInstancesCaseNN(Collection<INode> sourcesNodes, Collection<INode> targetsNodes, AssociationDefinition associationDefinition) {
		List<IArc> arcsInstances = new ArrayList<IArc>();
		while (!sourcesNodes.isEmpty()){
			INode source = RandomMethods.selectRandomlyNode(sourcesNodes);
			Collection<INode> tempTargetsNodes = targetsNodes;
			int numberOfArcs = 0;
			if (!targetsNodes.isEmpty()){
				while (numberOfArcs < numberOfOutputArcs && /*targetsNodes.size() >= numberOfOutputArcs*/!tempTargetsNodes.isEmpty()){
					INode target = RandomMethods.selectRandomlyNode(targetsNodes);
					IArc arc = createRandomlyArc(source,target,associationDefinition);
					if (arc != null){
						arcsInstances.add(arc);
						numberOfArcs++;
					}
					tempTargetsNodes.remove(target);
				}
			}
			sourcesNodes.remove(source);
		}
		return arcsInstances;
	}

	private List<IArc> generateArcsInstancesCase1N(Collection<INode> sourcesNodes, Collection<INode> targetsNodes, AssociationDefinition associationDefinition,
			Collection<AssociationDefinition> associations, Collection<IArc> generatedArcs) {
		List<IArc> arcsInstances = new ArrayList<IArc>();
		while (!sourcesNodes.isEmpty()){
			INode source = RandomMethods.selectRandomlyNode(sourcesNodes);
			int numberOfArcs = 0;
			while (numberOfArcs < numberOfOutputArcs && !targetsNodes.isEmpty()){
				INode target = RandomMethods.selectRandomlyNode(targetsNodes);
				IArc arc = createRandomlyArc(source,target,associationDefinition);
				AssociationDefinition invAssoc = searchInverseAssoc(source, target, associationDefinition, associations);
				if (invAssoc != null && (invAssoc.isTargetMandatory() || invAssoc.isSourceMandatory())){
					arc = null;
				}
				if (invAssoc != null){
					IArc invArc = getInverseGeneratedArc(source,target,invAssoc,generatedArcs);
					if (invArc != null){
						arc = null;
					}
				}
				if (arc != null){
					arcsInstances.add(arc);
					numberOfArcs++;
				}
				targetsNodes.remove(target);
			}
			sourcesNodes.remove(source);
		}
		return arcsInstances;
	}

	public IArc createRandomlyArc(INode source, INode target, AssociationDefinition associationDefinition) {
		IArc arc = null;
		boolean isCreated = RandomMethods.randomGenerator.nextBoolean();
		if (associationDefinition.isTargetMandatory()){
			isCreated = true;
		}
		if (isCreated){
			arc = ((Instance) instance).instanciation(source,target,associationDefinition);
		}
		return arc;
	}
	
	private List<IArc> generateArcsInstancesCase11(Collection<INode> sourcesNodes, Collection<INode> targetsNodes, 
			AssociationDefinition associationDefinition, Collection<AssociationDefinition> associations,
			Collection<IArc> generatedArcs) {
		List<IArc> arcsInstances = new ArrayList<IArc>();
		while (!sourcesNodes.isEmpty() && !targetsNodes.isEmpty()){
			INode source = RandomMethods.selectRandomlyNode(sourcesNodes);
			INode target = RandomMethods.selectRandomlyNode(targetsNodes);
			IArc arc = createRandomlyArc(source,target,associationDefinition);
			AssociationDefinition invAssoc = searchInverseAssoc(source,target,associationDefinition,associations);
			if (invAssoc != null){
				IArc invArc = getInverseGeneratedArc(source,target,invAssoc,generatedArcs);
				if (invArc != null){
					arc = null;
				}
			}
			if (arc != null){
				arcsInstances.add(arc);
			}
			sourcesNodes.remove(source);
			targetsNodes.remove(target);
		}
		return arcsInstances;
	}

	private IArc getInverseGeneratedArc(INode source, INode target,AssociationDefinition invAssoc, Collection<IArc> generatedArcs) {
		for (IArc iArc : generatedArcs){
			if(invAssoc.equals(((AlfrescoArc)iArc).getTypeAssociation()) && (((AlfrescoArc)iArc).getSource().equals(target)) || ((AlfrescoArc)iArc).getTarget().equals(source)){
				return iArc;
			}
		}
		return null;
	}

	private AssociationDefinition searchInverseAssoc(INode src, INode tgt, AssociationDefinition associationDefinition, Collection<AssociationDefinition> associations) {
		TypeDefinition srcType = (TypeDefinition) associationDefinition.getSourceClass();
		TypeDefinition tgtType = (TypeDefinition) associationDefinition.getTargetClass();
		for (AssociationDefinition assoc : associations){
			TypeDefinition invSrcType = (TypeDefinition) assoc.getSourceClass();
			TypeDefinition invTgtType = (TypeDefinition) assoc.getTargetClass();
			if (srcType.equals(invTgtType) && tgtType.equals(invSrcType)){
				return assoc;
			}
		}
		return null;
	}

	private List<INode> getGeneratedNodesByType(TypeDefinition type){
		List<INode> nodesInstances = new ArrayList<INode>();
		Collection<INode> generatedNodes = ((AlfrescoModelData) alfrescoModelDatas).getGeneratedTypesInstances();
		for (INode node : generatedNodes) {
			if (((AlfrescoNode) node).getTypeDefinition() == type){
				nodesInstances.add(node);
			}
		}
		return nodesInstances;
	}


	public Map<PropertyDefinition,Object> generateDatasProperties(TypeDefinition type, Collection<PropertyDefinition> properties) throws Exception{
		Map<PropertyDefinition,Object> datasProperties = new HashMap<PropertyDefinition,Object>();
		Collection<PropertyDefinition> unicityProperties = getUnicityProperties(type,properties);
		Collection<PropertyDefinition> tempProperties = new ArrayList<PropertyDefinition>();
		tempProperties.addAll(properties);
		tempProperties.removeAll(unicityProperties);
		for (PropertyDefinition propertyDefinition : tempProperties) {
			datasProperties.put(propertyDefinition, generateDatasProperty(propertyDefinition));
		}
		for (PropertyDefinition propertyDefinition : unicityProperties) {
			FileInputStream fis = new FileInputStream("tomcat/webapps/alfresco/WEB-INF/classes/META-INF/unicity.serial");
			ObjectInputStream ois = null;
			try{
				ois = new ObjectInputStream(fis);
				serializedData = (ArrayList<Serial>) ois.readObject();
			} catch (EOFException e) {
				//Nothing to do
			}
			finally{
				if (ois != null){
					ois.close();
				}
				fis.close();
			}
			datasProperties.put(propertyDefinition, generateDatasForUnicityProperty(propertyDefinition));
		}
		return datasProperties;
	}

	private Object generateDatasForUnicityProperty(PropertyDefinition propertyDefinition) throws Exception {
		Object data = generateDatasProperty(propertyDefinition);
		while (checkIfDataExists(propertyDefinition,data)){
			index ++;
			data = generateDatasProperty(propertyDefinition);
		}
		serializedData.add(new Serial(propertyDefinition.getName().toString(),data));
		serializeData();
		return data;
	}

	private void serializeData() throws Exception {
		FileOutputStream fos = new FileOutputStream("tomcat/webapps/alfresco/WEB-INF/classes/META-INF/unicity.serial");
		ObjectOutputStream oos= new ObjectOutputStream(fos);
		oos.writeObject(serializedData); 
		oos.flush();
		oos.close();
		fos.close();
	}

	private boolean checkIfDataExists(PropertyDefinition propertyDefinition, Object data){
		for(int i = 0; i < serializedData.size(); i++){
//			Object castData = null;
//			if (data instanceof Boolean){
//				castData = (Boolean) serializedData.get(i).bData;
//			}
//			else if (data instanceof Integer){
//				castData = (Integer) serializedData.get(i).iData;
//			}
//			else if (data instanceof Long){
//				castData = (Long) serializedData.get(i).lData;
//			}
//			else if (data instanceof Float){
//				castData = (Float) serializedData.get(i).fData;
//			}
//			else if (data instanceof Double){
//				castData = (Double) serializedData.get(i).dData;
//			}
//			else if (data instanceof String){
//				castData = (String) serializedData.get(i).sData;
//			}
//			else if (data instanceof Date){
//				castData = (Date) serializedData.get(i).dateData;
//			}
			if (propertyDefinition.getName().toString().equals(serializedData.get(i).getProperty()) && data.equals(serializedData.get(i).getData())){
				return true;
			}
		}
		return false;
	}

	private Collection<PropertyDefinition> getUnicityProperties(TypeDefinition type, Collection<PropertyDefinition> properties) throws Exception{
		Collection<PropertyDefinition> unicityProperties = new ArrayList<PropertyDefinition>();
		Map<QName, List<QName>> qnamedUnicityPropertiesByType = ((UnicityXMLReader) SpringContext.getContext().getBean("unicityDescriptorReader")).getUnicityDictionary();
		List<QName> qnamedUnicityProperties = qnamedUnicityPropertiesByType.get(type.getName());
		for (QName qnameUnicityProperty : qnamedUnicityProperties) {
			for (PropertyDefinition property : properties){
				if (property.getName().equals(qnameUnicityProperty)){
					unicityProperties.add(property);
				}
			}
		}
		return unicityProperties;
	}

	private Object generateDatasProperty(PropertyDefinition propertyDefinition) throws Exception {
		Object randomData = new Object();
		String defaultValue = propertyDefinition.getDefaultValue();
		QName dataTypeOfProperty = propertyDefinition.getDataType().getName();
		List<ConstraintDefinition> constraints = propertyDefinition.getConstraints();
		if (constraints.size() == 1){//we suppose there is a one-to-one correspondance between property and enumeration (?)
			for (ConstraintDefinition constraint : constraints) {
				randomData = RandomMethods.getRandomlyValue(constraint);
			}		
		}
		else{
			if (numOfSame == 1){
				randomData = RandomMethods.generateDataByDataTypeProperty(dataTypeOfProperty, defaultValue, propertyDefinition);
			}
			else if(numOfSame != 1 && sameData.size() == 0){
				randomData = RandomMethods.generateDataByDataTypeProperty(dataTypeOfProperty, defaultValue, propertyDefinition);
				sameData.add(randomData);
			}
			else if(numOfSame != 1 && sameData.size() != 0){
				randomData = ((ArrayList<Object>)sameData).get(0);
			}
		}
		return randomData;
	}
	
	public Map<AspectDefinition,Map<PropertyDefinition,Object>> generateDataAspect(TypeDefinition type, Collection<AspectDefinition> aspects) throws Exception{
		Map<AspectDefinition,Map<PropertyDefinition,Object>> dataAspects = new HashMap<AspectDefinition,Map<PropertyDefinition,Object>>();
		for (AspectDefinition aspect : aspects){
			Map<QName,PropertyDefinition> aspectProperties = aspect.getProperties();
			Collection<PropertyDefinition> properties = aspectProperties.values();
			Map<PropertyDefinition,Object> dataProperties = generateDatasProperties(type,properties);
			dataAspects.put(aspect,dataProperties);
		}
		return dataAspects;
	}
	
	public void deleteExceededNodes(){
		Collection<INode> generatedNodes = ((AlfrescoModelData) alfrescoModelDatas).getGeneratedTypesInstances();
		Collection<IArc> generatedArcs = ((AlfrescoModelData) alfrescoModelDatas).getGeneratedAssociationsInstances();
		Collection<INode> generatedNonMadtryNodes = new ArrayList<INode>();
		Collection<INode> nodesAssociated = new ArrayList<INode>();
		boolean pass = false;
		for (IArc arc : generatedArcs){
			if (((AlfrescoArc)arc).getTypeAssociation().isTargetMandatory()){
				if (!nodesAssociated.contains(((AlfrescoArc)arc).getSource())){
					nodesAssociated.add(((AlfrescoArc)arc).getSource());
				}
				if (!pass){
					generatedNonMadtryNodes = getGeneratedNodesByType((TypeDefinition) ((AlfrescoArc)arc).getTypeAssociation().getSourceClass());
					pass = true;
				}
			}
			if (((AlfrescoArc)arc).getTypeAssociation().isSourceMandatory()){
				if (!nodesAssociated.contains(((AlfrescoArc)arc).getTarget())){
					nodesAssociated.add(((AlfrescoArc)arc).getTarget());
				}
				if (!pass){
					generatedNonMadtryNodes = getGeneratedNodesByType((TypeDefinition) ((AlfrescoArc)arc).getTypeAssociation().getTargetClass());
					pass =true;
				}
			}
		}
		
		generatedNonMadtryNodes.removeAll(nodesAssociated);
		generatedNodes.removeAll(generatedNonMadtryNodes);
		
		((AlfrescoModelData) alfrescoModelDatas).setGeneratedTypesInstances(generatedNodes);
	}
	
	public void deleteExceededArcs(){
		Collection<IArc> generatedArcs = ((AlfrescoModelData) alfrescoModelDatas).getGeneratedAssociationsInstances();
		Collection<IArc> temp = new ArrayList<IArc>();
		for (IArc iArc : generatedArcs) {
			if (((AlfrescoArc)iArc).getTypeAssociation().isSourceMandatory()){
				temp.add(iArc);
			}
		}
		generatedArcs.removeAll(temp);
		((AlfrescoModelData) alfrescoModelDatas).setGeneratedAssociationsInstances(generatedArcs);
	}

}
