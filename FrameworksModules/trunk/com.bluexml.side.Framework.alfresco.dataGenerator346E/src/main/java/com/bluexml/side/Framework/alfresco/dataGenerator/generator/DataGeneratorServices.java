/**
 * Contains useful methods for AlfrescoRandomDataGenerator
 */
package com.bluexml.side.Framework.alfresco.dataGenerator.generator;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.alfresco.service.cmr.dictionary.AssociationDefinition;
import org.alfresco.service.cmr.dictionary.ClassDefinition;
import org.alfresco.service.cmr.dictionary.PropertyDefinition;
import org.alfresco.service.namespace.QName;

import com.bluexml.side.Framework.alfresco.dataGenerator.context.SpringContext;
import com.bluexml.side.Framework.alfresco.dataGenerator.data.AlfrescoModelData;
import com.bluexml.side.Framework.alfresco.dataGenerator.graph.AlfrescoArc;
import com.bluexml.side.Framework.alfresco.dataGenerator.graph.AlfrescoNode;
import com.bluexml.side.Framework.alfresco.dataGenerator.graph.IArc;
import com.bluexml.side.Framework.alfresco.dataGenerator.graph.INode;
import com.bluexml.side.Framework.alfresco.dataGenerator.structure.AlfrescoModelStructure;
import com.bluexml.side.framework.alfresco.unicity.UnicityXMLReader;

/**
 * @author dchevrier
 */
public class DataGeneratorServices {

	private AlfrescoModelRandomDataGenerator generator;

	public AlfrescoModelRandomDataGenerator getGenerator() {
		return generator;
	}

	public void setGenerator(AlfrescoModelRandomDataGenerator generator) {
		this.generator = generator;
	}

	@SuppressWarnings("unchecked")
	public void getSerializedDataFromFile() throws IOException, ClassNotFoundException {
		FileInputStream fis = new FileInputStream("tomcat/webapps/alfresco/WEB-INF/classes/META-INF/unicity.serial");
		ObjectInputStream ois = null;
		try {
			ois = new ObjectInputStream(fis);
			generator.setSerializedData((ArrayList<Serial>) ois.readObject());
		} catch (EOFException e) {
			//Nothing to do
		} finally {
			if (ois != null) {
				ois.close();
			}
			fis.close();
		}
	}

	public void initializeUnicityVariables(Collection<ClassDefinition> createdTypes, ClassDefinition type) {
		AlfrescoModelRandomDataGenerator.setTypeRef(type);

		Map<ClassDefinition, Map<PropertyDefinition, Integer>> index = AlfrescoModelRandomDataGenerator.getIndex();
		index.put(type, new HashMap<PropertyDefinition, Integer>());
		AlfrescoModelRandomDataGenerator.setIndex(index);

		Map<ClassDefinition, Map<PropertyDefinition, Integer>> numOfSame = generator.getNumOfSame();
		numOfSame.put(type, new HashMap<PropertyDefinition, Integer>());
		generator.setNumOfSame(numOfSame);

		Map<ClassDefinition, Map<PropertyDefinition, Object>> sameData = generator.getSameData();
		sameData.put(type, new HashMap<PropertyDefinition, Object>());
		generator.setSameData(sameData);

		AlfrescoModelRandomDataGenerator.setNumOfTypes(countSameTypes(createdTypes, type));
	}

	private int countSameTypes(Collection<ClassDefinition> createdTypes, ClassDefinition type) {
		int same = 0;
		for (ClassDefinition typeDef : createdTypes) {
			if (typeDef.equals(type)) {
				same++;
			}
		}
		return same;
	}

	public void reInitialization() throws FileNotFoundException {
		PrintWriter printwriter = new PrintWriter(new FileOutputStream("tomcat/webapps/alfresco/WEB-INF/classes/META-INF/unicity.serial"));
		printwriter.println("");
		printwriter.close();

		AlfrescoModelRandomDataGenerator.setNumOfTypes(0);

		ArrayList<Serial> serializedData = generator.getSerializedData();
		serializedData.clear();
		generator.setSerializedData(serializedData);

		Map<ClassDefinition, Map<PropertyDefinition, Integer>> numOfSame = generator.getNumOfSame();
		numOfSame.clear();
		generator.setNumOfSame(numOfSame);

		Map<ClassDefinition, Map<PropertyDefinition, Object>> sameData = generator.getSameData();
		sameData.clear();
		generator.setSameData(sameData);

	}

	public Collection<ClassDefinition> deleteTargetsTypesOfNotCreatedTargetsTypesOfCompositions(Map<AssociationDefinition, Collection<ClassDefinition>> sourcesTypesOfCompositions, Collection<ClassDefinition> createdTypes) {
		Collection<ClassDefinition> deletedTargetsTypes = new ArrayList<ClassDefinition>();
		Set<AssociationDefinition> compositions = sourcesTypesOfCompositions.keySet();
		for (AssociationDefinition composition : compositions) {
			if (sourcesTypesOfCompositions.get(composition).isEmpty() || generator.getNumberOfOutputArcs() == 0) {
				deletedTargetsTypes.addAll(getTargetsTypesOfComposition(composition, createdTypes));
			}
		}
		return deletedTargetsTypes;
	}

	private Collection<ClassDefinition> getTargetsTypesOfComposition(AssociationDefinition composition, Collection<ClassDefinition> createdTypes) {
		Collection<ClassDefinition> targets = new ArrayList<ClassDefinition>();
		for (ClassDefinition type : createdTypes) {
			if (type.getName().equals((composition.getTargetClass()).getName())) {
				targets.add(type);
			}
		}
		return targets;
	}

	public Map<AssociationDefinition, Collection<ClassDefinition>> getSourcesTypesOfCompositions(Collection<ClassDefinition> createdTypes, Collection<AssociationDefinition> compositions) {
		Map<AssociationDefinition, Collection<ClassDefinition>> sourcesByCompo = new HashMap<AssociationDefinition, Collection<ClassDefinition>>();
		Collection<ClassDefinition> sources = new ArrayList<ClassDefinition>();
		for (AssociationDefinition composition : compositions) {
			for (ClassDefinition createdType : createdTypes) {
				ClassDefinition source = composition.getSourceClass();
				if (createdType.getName().equals(source.getName())) {
					sources.add(createdType);
				}
			}
			sourcesByCompo.put(composition, sources);
		}
		return sourcesByCompo;
	}

	public Collection<AssociationDefinition> extractCompositions(Collection<AssociationDefinition> associations) {
		Collection<AssociationDefinition> compositions = new ArrayList<AssociationDefinition>();
		for (AssociationDefinition association : associations) {
			if (association.isChild()) {
				compositions.add(association);
			}
		}
		return compositions;
	}

	public IArc getInverseGeneratedArc(INode source, INode target, AssociationDefinition invAssoc, Collection<IArc> generatedArcs) {
		for (IArc iArc : generatedArcs) {
			if (invAssoc.equals(((AlfrescoArc) iArc).getTypeAssociation()) && (((AlfrescoArc) iArc).getSource().equals(target)) || ((AlfrescoArc) iArc).getTarget().equals(source)) {
				return iArc;
			}
		}
		return null;
	}

	public AssociationDefinition searchInverseAssoc(INode src, INode tgt, AssociationDefinition associationDefinition, Collection<AssociationDefinition> associations) {
		ClassDefinition srcType = associationDefinition.getSourceClass();
		ClassDefinition tgtType = associationDefinition.getTargetClass();
		for (AssociationDefinition assoc : associations) {
			ClassDefinition invSrcType = assoc.getSourceClass();
			ClassDefinition invTgtType = assoc.getTargetClass();
			if (srcType.equals(invTgtType) && tgtType.equals(invSrcType)) {
				return assoc;
			}
		}
		return null;
	}

	public List<INode> getGeneratedNodesByType(ClassDefinition type) {
		List<INode> nodesInstances = new ArrayList<INode>();
		Collection<INode> generatedNodes = ((AlfrescoModelData) generator.getAlfrescoModelDatas()).getGeneratedTypesInstances();
		for (INode node : generatedNodes) {
			if (((AlfrescoNode) node).getClassDefinition() == type) {
				nodesInstances.add(node);
			}
		}
		return nodesInstances;
	}

	public boolean deleteExceededNodes() {
		Collection<INode> generatedNodes = ((AlfrescoModelData) generator.getAlfrescoModelDatas()).getGeneratedTypesInstances();
		Collection<IArc> generatedArcs = ((AlfrescoModelData) generator.getAlfrescoModelDatas()).getGeneratedAssociationsInstances();
		Collection<INode> generatedIsolatedNodesSrc = new ArrayList<INode>();
		Collection<INode> generatedIsolatedNodesTgt = new ArrayList<INode>();
		Collection<INode> nodesAssociatedSrc = new ArrayList<INode>();
		Collection<INode> nodesAssociatedTgt = new ArrayList<INode>();
		boolean passSrc = false;
		boolean passTgt = false;
		if (generatedArcs.size() > 0) {
			for (IArc arc : generatedArcs) {
				if (((AlfrescoArc) arc).getTypeAssociation().isTargetMandatory()) {
					if (!nodesAssociatedSrc.contains(((AlfrescoArc) arc).getSource())) {
						nodesAssociatedSrc.add(((AlfrescoArc) arc).getSource());
					}
					if (!passSrc) {
						generatedIsolatedNodesSrc = getGeneratedNodesByType(((AlfrescoArc) arc).getTypeAssociation().getSourceClass());
						passSrc = true;
					}
				}
				if (((AlfrescoArc) arc).getTypeAssociation().isSourceMandatory()) {
					if (!nodesAssociatedTgt.contains(((AlfrescoArc) arc).getTarget())) {
						nodesAssociatedTgt.add(((AlfrescoArc) arc).getTarget());
					}
					if (!passTgt) {
						generatedIsolatedNodesTgt = getGeneratedNodesByType(((AlfrescoArc) arc).getTypeAssociation().getTargetClass());
						passTgt = true;
					}
				}
			}
			generatedIsolatedNodesSrc.removeAll(nodesAssociatedSrc);
			generatedIsolatedNodesTgt.removeAll(nodesAssociatedTgt);
		} else {
			Collection<AssociationDefinition> assocs = ((AlfrescoModelStructure) generator.getStructure()).getAssociations();
			for (AssociationDefinition assoc : assocs) {
				if (assoc.isTargetMandatory()) {
					generatedIsolatedNodesSrc = getGeneratedNodesByType(assoc.getSourceClass());
				}
				if (assoc.isSourceMandatory()) {
					generatedIsolatedNodesTgt = getGeneratedNodesByType(assoc.getTargetClass());
				}
			}
		}

		generatedNodes.removeAll(generatedIsolatedNodesSrc);
		generatedNodes.removeAll(generatedIsolatedNodesTgt);

		((AlfrescoModelData) generator.getAlfrescoModelDatas()).setGeneratedTypesInstances(generatedNodes);

		return true;
	}

	public void serializeData() throws Exception {
		PrintWriter printwriter = new PrintWriter(new FileOutputStream("tomcat/webapps/alfresco/WEB-INF/classes/META-INF/unicity.serial"));
		printwriter.println("");
		printwriter.close();

		FileOutputStream fos = new FileOutputStream("tomcat/webapps/alfresco/WEB-INF/classes/META-INF/unicity.serial");
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		if (generator.getSerializedData().size() > 0) {
			oos.writeObject(generator.getSerializedData());
		}
		oos.flush();
		oos.close();
		fos.close();
	}

	public Collection<PropertyDefinition> getUnicityProperties(ClassDefinition type, Collection<PropertyDefinition> properties) throws Exception {
		Collection<PropertyDefinition> unicityProperties = new ArrayList<PropertyDefinition>();
		UnicityXMLReader reader = null;
		try {
			reader = ((UnicityXMLReader) SpringContext.getContext().getBean("unicityDescriptorReader"));
		} catch (Exception e) {
			//to avoid problems if module unicity not used
		}
		if (reader != null) {
			List<QName> qnamedUnicityProperties = reader.getValue(type.getName());
			if (qnamedUnicityProperties != null) {
				for (QName qnameUnicityProperty : qnamedUnicityProperties) {
					for (PropertyDefinition property : properties) {
						if (property.getName().equals(qnameUnicityProperty) && !unicityProperties.contains(property)) {
							unicityProperties.add(property);
						}
					}
				}
			}
		}
		return unicityProperties;
	}

	public int getMaxAttributeIndex() throws Exception {
		int max = 0;
		Set<ClassDefinition> indexes = AlfrescoModelRandomDataGenerator.getIndex().keySet();
		for (ClassDefinition ind : indexes) {
			Map<PropertyDefinition, Integer> propertiesValues = AlfrescoModelRandomDataGenerator.getIndex().get(ind);
			Set<PropertyDefinition> properties = propertiesValues.keySet();
			Collection<PropertyDefinition> unicityProperties = getUnicityProperties(ind, properties);
			for (PropertyDefinition prop : unicityProperties) {
				if (propertiesValues.get(prop).intValue() > max) {
					max = propertiesValues.get(prop).intValue();
				}
			}
		}
		return max + 1;
	}

}
