/**
 * 
 */
package com.bluexml.side.Framework.alfresco.dataGenerator.generator;

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
import org.alfresco.service.cmr.dictionary.ConstraintDefinition;
import org.alfresco.service.cmr.dictionary.DataTypeDefinition;
import org.alfresco.service.cmr.dictionary.PropertyDefinition;
import org.alfresco.service.cmr.dictionary.TypeDefinition;
import org.alfresco.service.namespace.QName;
import org.alfresco.util.ISO8601DateFormat;

import com.bluexml.side.Framework.alfresco.dataGenerator.data.AlfrescoModelData;
import com.bluexml.side.Framework.alfresco.dataGenerator.data.IData;
import com.bluexml.side.Framework.alfresco.dataGenerator.dictionary.IDictionary;
import com.bluexml.side.Framework.alfresco.dataGenerator.graph.AlfrescoArc;
import com.bluexml.side.Framework.alfresco.dataGenerator.graph.AlfrescoNode;
import com.bluexml.side.Framework.alfresco.dataGenerator.graph.IArc;
import com.bluexml.side.Framework.alfresco.dataGenerator.graph.INode;
import com.bluexml.side.Framework.alfresco.dataGenerator.structure.AlfrescoModelStructure;
import com.bluexml.side.Framework.alfresco.dataGenerator.structure.IStructure;

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
	
	private Collection<INode> nodesToDelete = new ArrayList<INode>();
	
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
		
		static String generateRandomString(String defaultValue){
			String data = null;
			Integer suffix = Integer.valueOf(randomGenerator.nextInt());
			data = defaultValue + "_" + suffix.toString();
			return data;
		}
		
		static Object generateRandomObject(){
			return new Object();
		}
		
		
		static TypeDefinition selectRandomlyType(Collection<TypeDefinition> types){
			int randomIndex = randomGenerator.nextInt(types.size());
			return ((ArrayList<TypeDefinition>)types).get(randomIndex);
		}
		

		static Object generateDataByDataTypeProperty(QName dataType, String defaultValue) throws Exception{
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
				randomData = generateRandomString(defaultValue);
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
	
	/**
	 * @return the nodesToDelete
	 */
	public Collection<INode> getNodesToDelete() {
		return nodesToDelete;
	}

	/**
	 * @param nodesToDelete the nodesToDelete to set
	 */
	public void setNodesToDelete(Collection<INode> nodesToDelete) {
		this.nodesToDelete = nodesToDelete;
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
		for (TypeDefinition type : createdTypes){
			nodesInstances.add(((Instance)instance).instanciation(type));
		}
		((AlfrescoModelData) alfrescoModelDatas).setGeneratedTypesInstances(nodesInstances);
		return true;
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
		//if (generateNodesInstances(structure)){
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
//				if (!sourceMultiplicity || !targetMultiplicity){
//					arcsInstances.removeAll(getSameArcs(arcsInstances));
//				}
//				if (!sourcesNodes.isEmpty() && associationDefinition.isTargetMandatory()){
//					nodesToDelete.addAll(sourcesNodes);
//				}
//				if (!targetsNodes.isEmpty() && associationDefinition.isSourceMandatory()){
//					nodesToDelete.addAll(targetsNodes);
//				}
			}
			((AlfrescoModelData) alfrescoModelDatas).setGeneratedAssociationsInstances(arcsInstances);
//		}
//		else{
//			//throws Exception
//		}
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

	private boolean searchInverseArc(INode source, INode target, AssociationDefinition invAssoc, Collection<IArc> generatedArcs) {
		for (IArc iArc : generatedArcs){
			AlfrescoArc invArc = (AlfrescoArc) iArc;
			if (invArc.getTypeAssociation().equals(invAssoc) && invArc.getSource().equals(target) && invArc.getTarget().equals(source)){
				return true;
			}
		}
		return false;
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
				if (invArc != null /**&& checkTarget(target,invArc)**/){
					arc = null;
//					arc = ((Instance) instance).instanciation(source,((AlfrescoArc)invArc).getSource(),associationDefinition);
//					targetsNodes.remove(((AlfrescoArc)invArc).getSource());
//					if (generatedArcs.contains(arc)){
//						arc = null;
//					}
				}
			}
			if (arc != null){
				arcsInstances.add(arc);
			}
			sourcesNodes.remove(source);
			targetsNodes.remove(target);
//			if (targetsNodes.contains(source)){
//				targetsNodes.remove(source);
//			}
//			if (sourcesNodes.contains(target)){
//				sourcesNodes.remove(target);
//			}
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


	public Map<PropertyDefinition,Object> generateDatasProperties(Collection<PropertyDefinition> properties) throws Exception{
		Map<PropertyDefinition,Object> datasProperties = new HashMap<PropertyDefinition,Object>();
		for (PropertyDefinition propertyDefinition : properties) {
			datasProperties.put(propertyDefinition, generateDatasProperty(propertyDefinition));
		}
		return datasProperties;
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
			randomData = RandomMethods.generateDataByDataTypeProperty(dataTypeOfProperty, defaultValue);
		}
		return randomData;
	}
	
	public Map<AspectDefinition,Map<PropertyDefinition,Object>> generateDataAspect(Collection<AspectDefinition> aspects) throws Exception{
		Map<AspectDefinition,Map<PropertyDefinition,Object>> dataAspects = new HashMap<AspectDefinition,Map<PropertyDefinition,Object>>();
		for (AspectDefinition aspect : aspects){
			Map<QName,PropertyDefinition> aspectProperties = aspect.getProperties();
			Collection<PropertyDefinition> properties = aspectProperties.values();
			Map<PropertyDefinition,Object> dataProperties = generateDatasProperties(properties);
			dataAspects.put(aspect,dataProperties);
		}
		return dataAspects;
	}
	
	public void deleteExceededNodes(){
//		Collection<INode> nodesToAdd = new ArrayList<INode>();
		Collection<INode> generatedNodes = ((AlfrescoModelData) alfrescoModelDatas).getGeneratedTypesInstances();
		Collection<IArc> generatedArcs = ((AlfrescoModelData) alfrescoModelDatas).getGeneratedAssociationsInstances();
//		Map<AssociationDefinition,Collection<INode>> nodesAssociatedByAssociation = new HashMap<AssociationDefinition, Collection<INode>>();
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
//			nodesAssociatedByAssociation.put(((AlfrescoArc)arc).getTypeAssociation(), nodesAssociated);
		}
		
		generatedNonMadtryNodes.removeAll(nodesAssociated);
		generatedNodes.removeAll(generatedNonMadtryNodes);
		
		
//		Set<AssociationDefinition> associations = nodesAssociatedByAssociation.keySet();
//		for (AssociationDefinition assoc : associations){
//			if (assoc.isTargetMandatory()){
//				TypeDefinition srcType = (TypeDefinition) assoc.getSourceClass();
//				Collection<INode> allGeneratedNodes = getGeneratedNodesByType(srcType);
//				allGeneratedNodes.removeAll(nodesAssociatedByAssociation.get(assoc));
//				nodesToDelete.addAll(allGeneratedNodes);
//			}
//			nodesToAdd.addAll(nodesAssociatedByAssociation.get(assoc));
//		}
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
	
	private Collection<IArc> getSameArcs(Collection<IArc> generatedArcs){
		Collection<INode> sources = new ArrayList<INode>();
		Collection<INode> targets = new ArrayList<INode>();
		for (IArc iArc : generatedArcs) {
			AlfrescoNode src = (AlfrescoNode)((AlfrescoArc)iArc).getSource();
			AlfrescoNode tgt = (AlfrescoNode)((AlfrescoArc)iArc).getTarget();
			sources.add(src);
			targets.add(tgt);
		}
		Collection<IArc> same = new ArrayList<IArc>();
		for (IArc iArc : generatedArcs){
			for (INode src : sources){
				for (INode tgt : targets){
					if (targets.contains(src) && sources.contains(tgt)){
						same.add(iArc);
					}
				}
			}
		}
		return same;
	}

}
