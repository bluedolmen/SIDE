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
import java.util.Set;

import org.alfresco.service.cmr.dictionary.AspectDefinition;
import org.alfresco.service.cmr.dictionary.AssociationDefinition;
import org.alfresco.service.cmr.dictionary.ClassDefinition;
import org.alfresco.service.cmr.dictionary.ConstraintDefinition;
import org.alfresco.service.cmr.dictionary.DataTypeDefinition;
import org.alfresco.service.cmr.dictionary.DictionaryService;
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
	private NativeAlfrescoModelRandomDataGenerator nativeGenerator;
	private IStructure structure;
	
	private IDictionary dictionary;
	private DictionaryService service;
	private int numberOfNodes;
	private int numberOfOutputArcs;
	private static String scenario;
	private static int startAttributeIndex;
	private Integer savedStartIndexAttribute;
	
	private static Map<TypeDefinition,Integer> indexType = new HashMap<TypeDefinition, Integer>();
	private static Map<TypeDefinition,Map<PropertyDefinition,Integer>> index = new HashMap<TypeDefinition, Map<PropertyDefinition,Integer>>();
	private static int numOfTypes;
	private static TypeDefinition typeRef;
	private Map<TypeDefinition,Map<PropertyDefinition,Integer>> numOfSame = new HashMap<TypeDefinition, Map<PropertyDefinition,Integer>>();
	private Map<TypeDefinition,Map<PropertyDefinition,Object>> sameData = new HashMap<TypeDefinition, Map<PropertyDefinition,Object>>();
	
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
			
			Date today = new Date();
			long sup = today.getTime()*5/4;
			long inf = today.getTime()*3/4;
			
			long randomDateValue = randomGenerator.nextLong();
			while (randomDateValue > sup || randomDateValue < inf){
				randomDateValue = randomGenerator.nextLong();
			}
			
			Date randomDate = new Date(randomDateValue);
			DateFormat df = new SimpleDateFormat("yyyy/mm/dd");
			Date simpleDate = df.parse(df.format(randomDate));
			
//			DateFormat df = new SimpleDateFormat("yyyy/mm/dd");
//			Date minDate = df.parse("1970/01/01");			
//			Date maxDate = df.parse("3000/01/01");
//			
//			df.format(today);
//
//			float f = randomGenerator.nextFloat();
//			long l = (long)(f * (maxDate.getTime() - minDate.getTime()) ) + minDate.getTime();
//			Date date = new Date(l);			
			
//			return ISO8601DateFormat.format(date);
			return ISO8601DateFormat.format(simpleDate);
		}
		
		static String generateRandomDateTime() throws Exception{			
			Date today = new Date();
			long sup = today.getTime()*5/4;
			long inf = today.getTime()*3/4;
			
			long randomDateValue = randomGenerator.nextLong();
			while (randomDateValue > sup || randomDateValue < inf){
				randomDateValue = randomGenerator.nextLong();
			}
			
			return ISO8601DateFormat.format(new Date(randomDateValue));
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
					data = propertyName.substring(0,propertyName.length()/2) + "_" + Integer.valueOf(randomGenerator.nextInt()).toString();
				}
			}
			else if (scenario.equals("incremental")){
				if (defaultValue != null){
					data = defaultValue + "_" + Integer.valueOf(index.get(typeRef).get(property)).toString();
				}
				else{
					String [] parts = property.getName().toString().split("_");
					String propertyName = parts[parts.length-1];
					data = propertyName.substring(0,propertyName.length()/2) + "_" + Integer.valueOf(index.get(typeRef).get(property)).toString();
				}
			}
			return data;
		}
		
		static Object generateRandomObject(){
			return new Object();
		}
		
		static Integer nextIntInInterval(int inf, int sup){
			Integer integer = randomGenerator.nextInt(sup);
			while (integer.intValue() < inf){
				integer = randomGenerator.nextInt(sup);
			}
			return integer;
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
			else if (dataType.equals(DataTypeDefinition.DATE)){
				randomData = generateRandomDate();
			}
			else if(dataType.equals(DataTypeDefinition.DATETIME)){
				randomData = generateRandomDateTime();
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
	
	public Integer getSavedStartIndexAttribute() {
		return savedStartIndexAttribute;
	}

	public void setSavedStartIndexAttribute(Integer savedStartIndexAttribute) {
		this.savedStartIndexAttribute = savedStartIndexAttribute;
	}

	public static int getStartAttributeIndex() {
		return startAttributeIndex;
	}

	public static void setStartAttributeIndex(int startAttributeIndex) {
		AlfrescoModelRandomDataGenerator.startAttributeIndex = startAttributeIndex;
	}

	public IStructure getStructure() {
		return structure;
	}

	public void setStructure(IStructure structure) {
		this.structure = structure;
	}

	public DictionaryService getService() {
		return service;
	}

	public void setService(DictionaryService service) {
		this.service = service;
	}

	public static Map<TypeDefinition, Integer> getIndexType() {
		return indexType;
	}

	public static void setIndexType(Map<TypeDefinition, Integer> indexType) {
		AlfrescoModelRandomDataGenerator.indexType = indexType;
	}

	public NativeAlfrescoModelRandomDataGenerator getNativeGenerator() {
		return nativeGenerator;
	}

	public void setNativeGenerator(
			NativeAlfrescoModelRandomDataGenerator nativeGenerator) {
		this.nativeGenerator = nativeGenerator;
	}

	public static int getNumOfTypes() {
		return numOfTypes;
	}

	public static void setNumOfTypes(int numOfTypes) {
		AlfrescoModelRandomDataGenerator.numOfTypes = numOfTypes;
	}

	public ArrayList<Serial> getSerializedData() {
		return serializedData;
	}

	public void setSerializedData(ArrayList<Serial> serializedData) {
		this.serializedData = serializedData;
	}

	public static Map<TypeDefinition, Map<PropertyDefinition, Integer>> getIndex() {
		return index;
	}

	public static void setIndex(
			Map<TypeDefinition, Map<PropertyDefinition, Integer>> index) {
		AlfrescoModelRandomDataGenerator.index = index;
	}

	public static TypeDefinition getTypeRef() {
		return typeRef;
	}

	public static void setTypeRef(TypeDefinition typeRef) {
		AlfrescoModelRandomDataGenerator.typeRef = typeRef;
	}

	public Map<TypeDefinition, Map<PropertyDefinition, Integer>> getNumOfSame() {
		return numOfSame;
	}

	public void setNumOfSame(
			Map<TypeDefinition, Map<PropertyDefinition, Integer>> numOfSame) {
		this.numOfSame = numOfSame;
	}

	public Map<TypeDefinition, Map<PropertyDefinition, Object>> getSameData() {
		return sameData;
	}

	public void setSameData(
			Map<TypeDefinition, Map<PropertyDefinition, Object>> sameData) {
		this.sameData = sameData;
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
		AlfrescoModelRandomDataGenerator.scenario = scenario;
	}

	public boolean generateNodesInstances(IStructure structure) throws Exception{
		List<INode> nodesInstances = new ArrayList<INode>();
		Collection<TypeDefinition> createdTypes = new ArrayList<TypeDefinition>();
		Collection<TypeDefinition> types = ((AlfrescoModelStructure) structure).getTypes();
		Collection<AssociationDefinition> compositions = extractCompositions(((AlfrescoModelStructure) structure).getAssociations());
		
		for (int numOfNodes = 0; numOfNodes < numberOfNodes; numOfNodes++){
			TypeDefinition type = RandomMethods.selectRandomlyType(types);
			createdTypes.add(type);
		}
		
		Map<AssociationDefinition,Collection<TypeDefinition>> sourcesTypesOfCompositions = getSourcesTypesOfCompositions(createdTypes,compositions);
		Collection<TypeDefinition> deletedTargetsTypes = deleteTargetsTypesOfNotCreatedTargetsTypesOfCompositions(sourcesTypesOfCompositions,createdTypes);
		createdTypes.removeAll(deletedTargetsTypes);
		
		for (TypeDefinition type : createdTypes){
			indexType.put(type, Integer.valueOf(0));
		}
		for (TypeDefinition type : createdTypes){
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
			
			typeRef = type;
			index.put(typeRef, new HashMap<PropertyDefinition, Integer>());
			numOfSame.put(typeRef, new HashMap<PropertyDefinition, Integer>());
			sameData.put(typeRef, new HashMap<PropertyDefinition, Object>());
			
			numOfTypes = countSameTypes(createdTypes,type);
			
			nodesInstances.add(((Instance)instance).instanciation(type));
			
			Integer newValue = Integer.valueOf(indexType.get(type).intValue() + 1);
			indexType.put(type, newValue);
			
			serializeData();
		}
		((AlfrescoModelData) alfrescoModelDatas).setGeneratedTypesInstances(nodesInstances);
		
		//Reinitialization because beans still exist until Alfresco be shutdown
		PrintWriter printwriter = new PrintWriter(new FileOutputStream("tomcat/webapps/alfresco/WEB-INF/classes/META-INF/unicity.serial"));
	    printwriter.println("");
	    printwriter.close();
	    numOfTypes = 0;
	    serializedData.clear();
	    numOfSame.clear();
	    sameData.clear();
	    
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

	private Collection<TypeDefinition> deleteTargetsTypesOfNotCreatedTargetsTypesOfCompositions(Map<AssociationDefinition,Collection<TypeDefinition>> sourcesTypesOfCompositions,
			Collection<TypeDefinition> createdTypes){
		Collection<TypeDefinition> deletedTargetsTypes = new ArrayList<TypeDefinition>();
		Set<AssociationDefinition> compositions = sourcesTypesOfCompositions.keySet();
		for (AssociationDefinition composition : compositions) {
			if (sourcesTypesOfCompositions.get(composition).isEmpty() || numberOfOutputArcs == 0){
				deletedTargetsTypes.addAll(getTargetsTypesOfComposition(composition,createdTypes));
			}
		}
		return deletedTargetsTypes;
	}

	private Collection<TypeDefinition> getTargetsTypesOfComposition(AssociationDefinition composition, Collection<TypeDefinition> createdTypes) {
		Collection<TypeDefinition> targets = new ArrayList<TypeDefinition>();
		for (TypeDefinition type : createdTypes){
			if (type.getName().equals(((TypeDefinition)composition.getTargetClass()).getName())){
				targets.add(type);
			}
		}
		return targets;
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

	private Map<AssociationDefinition,Collection<TypeDefinition>> getSourcesTypesOfCompositions(Collection<TypeDefinition> createdTypes, Collection<AssociationDefinition> compositions) {
		Map<AssociationDefinition,Collection<TypeDefinition>> sourcesByCompo = new HashMap<AssociationDefinition, Collection<TypeDefinition>>();
		Collection<TypeDefinition> sources = new ArrayList<TypeDefinition>();
		for (AssociationDefinition composition : compositions){
			for (TypeDefinition createdType : createdTypes){
				TypeDefinition source = (TypeDefinition) composition.getSourceClass();
				if (createdType.getName().equals(source.getName())){
					sources.add(createdType);
				}
			}
			sourcesByCompo.put(composition, sources);
		}
		return sourcesByCompo;
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
		if (numberOfOutputArcs > 0){
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

	public List<INode> getGeneratedNodesByType(TypeDefinition type){
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
			startAttributeIndex = 0;
			datasProperties.put(propertyDefinition, generateDatasProperty(propertyDefinition));
		}
		
		if (unicityProperties.size() > 0){
			startAttributeIndex = savedStartIndexAttribute.intValue();
			Map<PropertyDefinition,Object> dataUnicityProperties = generateDataForUnicityProperties(type,unicityProperties);
			if (dataUnicityProperties.size() > 0){
				datasProperties.putAll(dataUnicityProperties);
			}
		}
		
		return datasProperties;
	}

	private Map<PropertyDefinition, Object> generateDataForUnicityProperties(TypeDefinition type,Collection<PropertyDefinition> unicityProperties) throws Exception {
		Collection<Serial> dataProperties = new ArrayList<Serial>();
		boolean same = true;
		while (same){
			dataProperties.clear();
			for (PropertyDefinition propertyDefinition : unicityProperties) {
				dataProperties.add(new Serial(type.getName().toString(),propertyDefinition.getName().toString(),generateDatasProperty(propertyDefinition)));
			}
			if (serializedData.size() > 0 && unicityProperties.size() > 0){
				for (Serial dataProperty : dataProperties){
					boolean contains = false;
					for (Serial serializedDataProperty : serializedData){
						if (dataProperty.getType().equals(serializedDataProperty.getType())
							&& dataProperty.getProperty().equals(serializedDataProperty.getProperty())
							&& dataProperty.getData().toString().equals(serializedDataProperty.getData().toString())){
							contains = true;
						}
					}
					same &= contains;
				}
			}
			else{
				same = false;
			}
		}
		Map<PropertyDefinition,Object> finalDataProperties = new HashMap<PropertyDefinition, Object>();
		for (Serial dataProperty : dataProperties){
			PropertyDefinition property = service.getProperty(QName.createQName(dataProperty.getProperty()));
			finalDataProperties.put(property,dataProperty.getData());
		}
		return finalDataProperties;
	}

	private void serializeData() throws Exception {
		PrintWriter printwriter = new PrintWriter(new FileOutputStream("tomcat/webapps/alfresco/WEB-INF/classes/META-INF/unicity.serial"));
	    printwriter.println("");
	    printwriter.close();
	    
		FileOutputStream fos = new FileOutputStream("tomcat/webapps/alfresco/WEB-INF/classes/META-INF/unicity.serial");
		ObjectOutputStream oos= new ObjectOutputStream(fos);
		if (serializedData.size() > 0){
			oos.writeObject(serializedData);
		}
		oos.flush();
		oos.close();
		fos.close();
	}

	public Collection<PropertyDefinition> getUnicityProperties(ClassDefinition type, Collection<PropertyDefinition> properties) throws Exception{
		Collection<PropertyDefinition> unicityProperties = new ArrayList<PropertyDefinition>();
		UnicityXMLReader reader = null;
		try{
			reader = ((UnicityXMLReader) SpringContext.getContext().getBean("unicityDescriptorReader"));
		}
		catch (Exception e){
			//to avoid problems if module unicity not used
		}
		if (reader != null){
			Map<QName, List<QName>> qnamedUnicityPropertiesByType = reader.getUnicityDictionary();
			List<QName> qnamedUnicityProperties = qnamedUnicityPropertiesByType.get(type.getName());
			if (qnamedUnicityProperties != null){
				for (QName qnameUnicityProperty : qnamedUnicityProperties) {
					for (PropertyDefinition property : properties){
						if (property.getName().equals(qnameUnicityProperty) && !unicityProperties.contains(property)){
							unicityProperties.add(property);
						}
					}
				}
			}
		}
		return unicityProperties;
	}

	private Object generateDatasProperty(PropertyDefinition propertyDefinition) throws Exception {
		if (scenario.equals("incremental")){
			if (numOfSame.get(typeRef).get(propertyDefinition) == null){
				Integer value = Integer.valueOf(RandomMethods.nextIntInInterval(startAttributeIndex, startAttributeIndex + numOfTypes + 1));
				numOfSame.get(typeRef).put(propertyDefinition, value);
				index.get(typeRef).put(propertyDefinition, Integer.valueOf(startAttributeIndex));
			}
			else{
				Integer value = Integer.valueOf(numOfSame.get(typeRef).get(propertyDefinition).intValue() - 1);
				if (value.intValue() >= startAttributeIndex){
					numOfSame.get(typeRef).put(propertyDefinition, value);
					Integer indexValue = Integer.valueOf(index.get(typeRef).get(propertyDefinition).intValue() + 1);
					index.get(typeRef).put(propertyDefinition, indexValue);
				}
				else{
					Integer newValue = Integer.valueOf(RandomMethods.nextIntInInterval(startAttributeIndex, startAttributeIndex + numOfTypes + 1));
					numOfSame.get(typeRef).put(propertyDefinition, newValue);
					index.get(typeRef).put(propertyDefinition, Integer.valueOf(startAttributeIndex));
				}
			}
		}
		else if(scenario.equals("random")){
			numOfSame.get(typeRef).put(propertyDefinition,Integer.valueOf(0));
		}
		Object randomData = new Object();
		String defaultValue = propertyDefinition.getDefaultValue();
		QName dataTypeOfProperty = propertyDefinition.getDataType().getName();
		List<ConstraintDefinition> constraints = propertyDefinition.getConstraints();
		if (constraints.size() == 1){//we suppose there is a one-to-one correspondance between property and enumeration
			for (ConstraintDefinition constraint : constraints) {
				randomData = RandomMethods.getRandomlyValue(constraint);
			}		
		}
		else{
			if (numOfSame.get(typeRef).get(propertyDefinition).equals(Integer.valueOf(startAttributeIndex))){
				randomData = RandomMethods.generateDataByDataTypeProperty(dataTypeOfProperty, defaultValue, propertyDefinition);
			}
			else if(!numOfSame.get(typeRef).get(propertyDefinition).equals(Integer.valueOf(startAttributeIndex)) && sameData.get(typeRef).get(propertyDefinition) == null){
				randomData = RandomMethods.generateDataByDataTypeProperty(dataTypeOfProperty, defaultValue, propertyDefinition);
				sameData.get(typeRef).put(propertyDefinition,randomData);
			}
			else if(!numOfSame.get(typeRef).get(propertyDefinition).equals(Integer.valueOf(startAttributeIndex)) && sameData.get(typeRef).get(propertyDefinition) != null){
				randomData = sameData.get(typeRef).get(propertyDefinition);
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
		Collection<INode> generatedIsolatedNodesSrc = new ArrayList<INode>();
		Collection<INode> generatedIsolatedNodesTgt = new ArrayList<INode>();
		Collection<INode> nodesAssociatedSrc = new ArrayList<INode>();
		Collection<INode> nodesAssociatedTgt = new ArrayList<INode>();
		boolean passSrc = false;
		boolean passTgt = false;
		if (generatedArcs.size() > 0){
			for (IArc arc : generatedArcs){
				if (((AlfrescoArc)arc).getTypeAssociation().isTargetMandatory()){
					if (!nodesAssociatedSrc.contains(((AlfrescoArc)arc).getSource())){
						nodesAssociatedSrc.add(((AlfrescoArc)arc).getSource());
					}
					if (!passSrc){
						generatedIsolatedNodesSrc = getGeneratedNodesByType((TypeDefinition) ((AlfrescoArc)arc).getTypeAssociation().getSourceClass());
						passSrc = true;
					}
				}
				if (((AlfrescoArc)arc).getTypeAssociation().isSourceMandatory()){
					if (!nodesAssociatedTgt.contains(((AlfrescoArc)arc).getTarget())){
						nodesAssociatedTgt.add(((AlfrescoArc)arc).getTarget());
					}
					if (!passTgt){
						generatedIsolatedNodesTgt = getGeneratedNodesByType((TypeDefinition) ((AlfrescoArc)arc).getTypeAssociation().getTargetClass());
						passTgt =true;
					}
				}
			}
			generatedIsolatedNodesSrc.removeAll(nodesAssociatedSrc);
			generatedIsolatedNodesTgt.removeAll(nodesAssociatedTgt);
		}
		else{
			Collection<AssociationDefinition> assocs = ((AlfrescoModelStructure)structure).getAssociations();
			for (AssociationDefinition assoc : assocs){
				if (assoc.isTargetMandatory()){
					generatedIsolatedNodesSrc = getGeneratedNodesByType((TypeDefinition) assoc.getSourceClass());
				}
				if (assoc.isSourceMandatory()){
					generatedIsolatedNodesTgt = getGeneratedNodesByType((TypeDefinition) assoc.getTargetClass());
				}
			}
		}
		
		generatedNodes.removeAll(generatedIsolatedNodesSrc);
		generatedNodes.removeAll(generatedIsolatedNodesTgt);
		
		((AlfrescoModelData) alfrescoModelDatas).setGeneratedTypesInstances(generatedNodes);
	}
	
	public int getMaxAttributeIndex() throws Exception{
		int max = 0;
		Set<TypeDefinition> indexes = index.keySet();
		for (TypeDefinition ind : indexes){
			Map<PropertyDefinition,Integer> propertiesValues = index.get(ind);
			Set<PropertyDefinition> properties = propertiesValues.keySet();
			Collection<PropertyDefinition> unicityProperties = getUnicityProperties(ind, properties);
			for (PropertyDefinition prop : unicityProperties){
				if (propertiesValues.get(prop).intValue() > max){
					max = propertiesValues.get(prop).intValue();
				}
			}
		}
		return max + 1;
	}

}
