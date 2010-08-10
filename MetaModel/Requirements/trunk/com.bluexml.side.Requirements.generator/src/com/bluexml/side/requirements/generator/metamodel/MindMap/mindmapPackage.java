/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.bluexml.side.requirements.generator.metamodel.MindMap;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see com.bluexml.side.requirements.generator.metamodel.MindMap.mindmapFactory
 * @model kind="package"
 * @generated
 */
public interface mindmapPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "MindMap";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://www.bluexml.com/rwm/mindmap/1.0/";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "MindMap";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	mindmapPackage eINSTANCE = com.bluexml.side.requirements.generator.metamodel.MindMap.impl.mindmapPackageImpl.init();

	/**
	 * The meta object id for the '{@link com.bluexml.side.requirements.generator.metamodel.MindMap.impl.MapsImpl <em>Maps</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.bluexml.side.requirements.generator.metamodel.MindMap.impl.MapsImpl
	 * @see com.bluexml.side.requirements.generator.metamodel.MindMap.impl.mindmapPackageImpl#getMaps()
	 * @generated
	 */
	int MAPS = 0;

	/**
	 * The feature id for the '<em><b>Maps</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MAPS__MAPS = 0;

	/**
	 * The number of structural features of the '<em>Maps</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MAPS_FEATURE_COUNT = 1;

	/**
	 * The meta object id for the '{@link com.bluexml.side.requirements.generator.metamodel.MindMap.impl.MapImpl <em>Map</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.bluexml.side.requirements.generator.metamodel.MindMap.impl.MapImpl
	 * @see com.bluexml.side.requirements.generator.metamodel.MindMap.impl.mindmapPackageImpl#getMap()
	 * @generated
	 */
	int MAP = 1;

	/**
	 * The feature id for the '<em><b>Node</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MAP__NODE = 0;

	/**
	 * The number of structural features of the '<em>Map</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MAP_FEATURE_COUNT = 1;

	/**
	 * The meta object id for the '{@link com.bluexml.side.requirements.generator.metamodel.MindMap.impl.NodeImpl <em>Node</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.bluexml.side.requirements.generator.metamodel.MindMap.impl.NodeImpl
	 * @see com.bluexml.side.requirements.generator.metamodel.MindMap.impl.mindmapPackageImpl#getNode()
	 * @generated
	 */
	int NODE = 2;

	/**
	 * The feature id for the '<em><b>Presentation</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NODE__PRESENTATION = 0;

	/**
	 * The feature id for the '<em><b>Text</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NODE__TEXT = 1;

	/**
	 * The feature id for the '<em><b>Sub</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NODE__SUB = 2;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NODE__ID = 3;

	/**
	 * The feature id for the '<em><b>Arrowlink</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NODE__ARROWLINK = 4;

	/**
	 * The number of structural features of the '<em>Node</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NODE_FEATURE_COUNT = 5;

	/**
	 * The meta object id for the '{@link com.bluexml.side.requirements.generator.metamodel.MindMap.impl.ArrowLinkImpl <em>Arrow Link</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.bluexml.side.requirements.generator.metamodel.MindMap.impl.ArrowLinkImpl
	 * @see com.bluexml.side.requirements.generator.metamodel.MindMap.impl.mindmapPackageImpl#getArrowLink()
	 * @generated
	 */
	int ARROW_LINK = 3;

	/**
	 * The feature id for the '<em><b>Color</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARROW_LINK__COLOR = 0;

	/**
	 * The feature id for the '<em><b>Endarrow</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARROW_LINK__ENDARROW = 1;

	/**
	 * The feature id for the '<em><b>Endinclination</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARROW_LINK__ENDINCLINATION = 2;

	/**
	 * The feature id for the '<em><b>Startarrow</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARROW_LINK__STARTARROW = 3;

	/**
	 * The feature id for the '<em><b>Startinclination</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARROW_LINK__STARTINCLINATION = 4;

	/**
	 * The feature id for the '<em><b>Destination</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARROW_LINK__DESTINATION = 5;

	/**
	 * The number of structural features of the '<em>Arrow Link</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARROW_LINK_FEATURE_COUNT = 6;

	/**
	 * The meta object id for the '{@link com.bluexml.side.requirements.generator.metamodel.MindMap.impl.NodePresentationImpl <em>Node Presentation</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.bluexml.side.requirements.generator.metamodel.MindMap.impl.NodePresentationImpl
	 * @see com.bluexml.side.requirements.generator.metamodel.MindMap.impl.mindmapPackageImpl#getNodePresentation()
	 * @generated
	 */
	int NODE_PRESENTATION = 4;

	/**
	 * The feature id for the '<em><b>Bold</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NODE_PRESENTATION__BOLD = 0;

	/**
	 * The feature id for the '<em><b>Italic</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NODE_PRESENTATION__ITALIC = 1;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NODE_PRESENTATION__NAME = 2;

	/**
	 * The feature id for the '<em><b>Size</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NODE_PRESENTATION__SIZE = 3;

	/**
	 * The feature id for the '<em><b>Background Color</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NODE_PRESENTATION__BACKGROUND_COLOR = 4;

	/**
	 * The feature id for the '<em><b>Color</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NODE_PRESENTATION__COLOR = 5;

	/**
	 * The feature id for the '<em><b>Folded</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NODE_PRESENTATION__FOLDED = 6;

	/**
	 * The number of structural features of the '<em>Node Presentation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NODE_PRESENTATION_FEATURE_COUNT = 7;

	/**
	 * Returns the meta object for class '{@link com.bluexml.side.requirements.generator.metamodel.MindMap.Maps <em>Maps</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Maps</em>'.
	 * @see com.bluexml.side.requirements.generator.metamodel.MindMap.Maps
	 * @generated
	 */
	EClass getMaps();

	/**
	 * Returns the meta object for the containment reference list '{@link com.bluexml.side.requirements.generator.metamodel.MindMap.Maps#getMaps <em>Maps</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Maps</em>'.
	 * @see com.bluexml.side.requirements.generator.metamodel.MindMap.Maps#getMaps()
	 * @see #getMaps()
	 * @generated
	 */
	EReference getMaps_Maps();

	/**
	 * Returns the meta object for class '{@link com.bluexml.side.requirements.generator.metamodel.MindMap.Map <em>Map</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Map</em>'.
	 * @see com.bluexml.side.requirements.generator.metamodel.MindMap.Map
	 * @generated
	 */
	EClass getMap();

	/**
	 * Returns the meta object for the containment reference '{@link com.bluexml.side.requirements.generator.metamodel.MindMap.Map#getNode <em>Node</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Node</em>'.
	 * @see com.bluexml.side.requirements.generator.metamodel.MindMap.Map#getNode()
	 * @see #getMap()
	 * @generated
	 */
	EReference getMap_Node();

	/**
	 * Returns the meta object for class '{@link com.bluexml.side.requirements.generator.metamodel.MindMap.Node <em>Node</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Node</em>'.
	 * @see com.bluexml.side.requirements.generator.metamodel.MindMap.Node
	 * @generated
	 */
	EClass getNode();

	/**
	 * Returns the meta object for the containment reference '{@link com.bluexml.side.requirements.generator.metamodel.MindMap.Node#getPresentation <em>Presentation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Presentation</em>'.
	 * @see com.bluexml.side.requirements.generator.metamodel.MindMap.Node#getPresentation()
	 * @see #getNode()
	 * @generated
	 */
	EReference getNode_Presentation();

	/**
	 * Returns the meta object for the containment reference list '{@link com.bluexml.side.requirements.generator.metamodel.MindMap.Node#getArrowlink <em>Arrowlink</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Arrowlink</em>'.
	 * @see com.bluexml.side.requirements.generator.metamodel.MindMap.Node#getArrowlink()
	 * @see #getNode()
	 * @generated
	 */
	EReference getNode_Arrowlink();

	/**
	 * Returns the meta object for the attribute '{@link com.bluexml.side.requirements.generator.metamodel.MindMap.Node#getText <em>Text</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Text</em>'.
	 * @see com.bluexml.side.requirements.generator.metamodel.MindMap.Node#getText()
	 * @see #getNode()
	 * @generated
	 */
	EAttribute getNode_Text();

	/**
	 * Returns the meta object for the containment reference list '{@link com.bluexml.side.requirements.generator.metamodel.MindMap.Node#getSub <em>Sub</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Sub</em>'.
	 * @see com.bluexml.side.requirements.generator.metamodel.MindMap.Node#getSub()
	 * @see #getNode()
	 * @generated
	 */
	EReference getNode_Sub();

	/**
	 * Returns the meta object for the attribute '{@link com.bluexml.side.requirements.generator.metamodel.MindMap.Node#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see com.bluexml.side.requirements.generator.metamodel.MindMap.Node#getId()
	 * @see #getNode()
	 * @generated
	 */
	EAttribute getNode_Id();

	/**
	 * Returns the meta object for class '{@link com.bluexml.side.requirements.generator.metamodel.MindMap.ArrowLink <em>Arrow Link</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Arrow Link</em>'.
	 * @see com.bluexml.side.requirements.generator.metamodel.MindMap.ArrowLink
	 * @generated
	 */
	EClass getArrowLink();

	/**
	 * Returns the meta object for the attribute '{@link com.bluexml.side.requirements.generator.metamodel.MindMap.ArrowLink#getColor <em>Color</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Color</em>'.
	 * @see com.bluexml.side.requirements.generator.metamodel.MindMap.ArrowLink#getColor()
	 * @see #getArrowLink()
	 * @generated
	 */
	EAttribute getArrowLink_Color();

	/**
	 * Returns the meta object for the reference '{@link com.bluexml.side.requirements.generator.metamodel.MindMap.ArrowLink#getDestination <em>Destination</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Destination</em>'.
	 * @see com.bluexml.side.requirements.generator.metamodel.MindMap.ArrowLink#getDestination()
	 * @see #getArrowLink()
	 * @generated
	 */
	EReference getArrowLink_Destination();

	/**
	 * Returns the meta object for class '{@link com.bluexml.side.requirements.generator.metamodel.MindMap.NodePresentation <em>Node Presentation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Node Presentation</em>'.
	 * @see com.bluexml.side.requirements.generator.metamodel.MindMap.NodePresentation
	 * @generated
	 */
	EClass getNodePresentation();

	/**
	 * Returns the meta object for the attribute '{@link com.bluexml.side.requirements.generator.metamodel.MindMap.NodePresentation#isBold <em>Bold</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Bold</em>'.
	 * @see com.bluexml.side.requirements.generator.metamodel.MindMap.NodePresentation#isBold()
	 * @see #getNodePresentation()
	 * @generated
	 */
	EAttribute getNodePresentation_Bold();

	/**
	 * Returns the meta object for the attribute '{@link com.bluexml.side.requirements.generator.metamodel.MindMap.NodePresentation#isItalic <em>Italic</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Italic</em>'.
	 * @see com.bluexml.side.requirements.generator.metamodel.MindMap.NodePresentation#isItalic()
	 * @see #getNodePresentation()
	 * @generated
	 */
	EAttribute getNodePresentation_Italic();

	/**
	 * Returns the meta object for the attribute '{@link com.bluexml.side.requirements.generator.metamodel.MindMap.NodePresentation#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see com.bluexml.side.requirements.generator.metamodel.MindMap.NodePresentation#getName()
	 * @see #getNodePresentation()
	 * @generated
	 */
	EAttribute getNodePresentation_Name();

	/**
	 * Returns the meta object for the attribute '{@link com.bluexml.side.requirements.generator.metamodel.MindMap.NodePresentation#getSize <em>Size</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Size</em>'.
	 * @see com.bluexml.side.requirements.generator.metamodel.MindMap.NodePresentation#getSize()
	 * @see #getNodePresentation()
	 * @generated
	 */
	EAttribute getNodePresentation_Size();

	/**
	 * Returns the meta object for the attribute '{@link com.bluexml.side.requirements.generator.metamodel.MindMap.NodePresentation#getBackgroundColor <em>Background Color</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Background Color</em>'.
	 * @see com.bluexml.side.requirements.generator.metamodel.MindMap.NodePresentation#getBackgroundColor()
	 * @see #getNodePresentation()
	 * @generated
	 */
	EAttribute getNodePresentation_BackgroundColor();

	/**
	 * Returns the meta object for the attribute '{@link com.bluexml.side.requirements.generator.metamodel.MindMap.NodePresentation#getColor <em>Color</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Color</em>'.
	 * @see com.bluexml.side.requirements.generator.metamodel.MindMap.NodePresentation#getColor()
	 * @see #getNodePresentation()
	 * @generated
	 */
	EAttribute getNodePresentation_Color();

	/**
	 * Returns the meta object for the attribute '{@link com.bluexml.side.requirements.generator.metamodel.MindMap.NodePresentation#isFolded <em>Folded</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Folded</em>'.
	 * @see com.bluexml.side.requirements.generator.metamodel.MindMap.NodePresentation#isFolded()
	 * @see #getNodePresentation()
	 * @generated
	 */
	EAttribute getNodePresentation_Folded();

	/**
	 * Returns the meta object for the attribute '{@link com.bluexml.side.requirements.generator.metamodel.MindMap.ArrowLink#getEndarrow <em>Endarrow</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Endarrow</em>'.
	 * @see com.bluexml.side.requirements.generator.metamodel.MindMap.ArrowLink#getEndarrow()
	 * @see #getArrowLink()
	 * @generated
	 */
	EAttribute getArrowLink_Endarrow();

	/**
	 * Returns the meta object for the attribute '{@link com.bluexml.side.requirements.generator.metamodel.MindMap.ArrowLink#getEndinclination <em>Endinclination</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Endinclination</em>'.
	 * @see com.bluexml.side.requirements.generator.metamodel.MindMap.ArrowLink#getEndinclination()
	 * @see #getArrowLink()
	 * @generated
	 */
	EAttribute getArrowLink_Endinclination();

	/**
	 * Returns the meta object for the attribute '{@link com.bluexml.side.requirements.generator.metamodel.MindMap.ArrowLink#getStartarrow <em>Startarrow</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Startarrow</em>'.
	 * @see com.bluexml.side.requirements.generator.metamodel.MindMap.ArrowLink#getStartarrow()
	 * @see #getArrowLink()
	 * @generated
	 */
	EAttribute getArrowLink_Startarrow();

	/**
	 * Returns the meta object for the attribute '{@link com.bluexml.side.requirements.generator.metamodel.MindMap.ArrowLink#getStartinclination <em>Startinclination</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Startinclination</em>'.
	 * @see com.bluexml.side.requirements.generator.metamodel.MindMap.ArrowLink#getStartinclination()
	 * @see #getArrowLink()
	 * @generated
	 */
	EAttribute getArrowLink_Startinclination();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	mindmapFactory getmindmapFactory();

	/**
	 * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 *   <li>each class,</li>
	 *   <li>each feature of each class,</li>
	 *   <li>each enum,</li>
	 *   <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	interface Literals {
		/**
		 * The meta object literal for the '{@link com.bluexml.side.requirements.generator.metamodel.MindMap.impl.MapsImpl <em>Maps</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.bluexml.side.requirements.generator.metamodel.MindMap.impl.MapsImpl
		 * @see com.bluexml.side.requirements.generator.metamodel.MindMap.impl.mindmapPackageImpl#getMaps()
		 * @generated
		 */
		EClass MAPS = eINSTANCE.getMaps();

		/**
		 * The meta object literal for the '<em><b>Maps</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MAPS__MAPS = eINSTANCE.getMaps_Maps();

		/**
		 * The meta object literal for the '{@link com.bluexml.side.requirements.generator.metamodel.MindMap.impl.MapImpl <em>Map</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.bluexml.side.requirements.generator.metamodel.MindMap.impl.MapImpl
		 * @see com.bluexml.side.requirements.generator.metamodel.MindMap.impl.mindmapPackageImpl#getMap()
		 * @generated
		 */
		EClass MAP = eINSTANCE.getMap();

		/**
		 * The meta object literal for the '<em><b>Node</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MAP__NODE = eINSTANCE.getMap_Node();

		/**
		 * The meta object literal for the '{@link com.bluexml.side.requirements.generator.metamodel.MindMap.impl.NodeImpl <em>Node</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.bluexml.side.requirements.generator.metamodel.MindMap.impl.NodeImpl
		 * @see com.bluexml.side.requirements.generator.metamodel.MindMap.impl.mindmapPackageImpl#getNode()
		 * @generated
		 */
		EClass NODE = eINSTANCE.getNode();

		/**
		 * The meta object literal for the '<em><b>Presentation</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference NODE__PRESENTATION = eINSTANCE.getNode_Presentation();

		/**
		 * The meta object literal for the '<em><b>Arrowlink</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference NODE__ARROWLINK = eINSTANCE.getNode_Arrowlink();

		/**
		 * The meta object literal for the '<em><b>Text</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute NODE__TEXT = eINSTANCE.getNode_Text();

		/**
		 * The meta object literal for the '<em><b>Sub</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference NODE__SUB = eINSTANCE.getNode_Sub();

		/**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute NODE__ID = eINSTANCE.getNode_Id();

		/**
		 * The meta object literal for the '{@link com.bluexml.side.requirements.generator.metamodel.MindMap.impl.ArrowLinkImpl <em>Arrow Link</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.bluexml.side.requirements.generator.metamodel.MindMap.impl.ArrowLinkImpl
		 * @see com.bluexml.side.requirements.generator.metamodel.MindMap.impl.mindmapPackageImpl#getArrowLink()
		 * @generated
		 */
		EClass ARROW_LINK = eINSTANCE.getArrowLink();

		/**
		 * The meta object literal for the '<em><b>Color</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ARROW_LINK__COLOR = eINSTANCE.getArrowLink_Color();

		/**
		 * The meta object literal for the '<em><b>Destination</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ARROW_LINK__DESTINATION = eINSTANCE.getArrowLink_Destination();

		/**
		 * The meta object literal for the '{@link com.bluexml.side.requirements.generator.metamodel.MindMap.impl.NodePresentationImpl <em>Node Presentation</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.bluexml.side.requirements.generator.metamodel.MindMap.impl.NodePresentationImpl
		 * @see com.bluexml.side.requirements.generator.metamodel.MindMap.impl.mindmapPackageImpl#getNodePresentation()
		 * @generated
		 */
		EClass NODE_PRESENTATION = eINSTANCE.getNodePresentation();

		/**
		 * The meta object literal for the '<em><b>Bold</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute NODE_PRESENTATION__BOLD = eINSTANCE.getNodePresentation_Bold();

		/**
		 * The meta object literal for the '<em><b>Italic</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute NODE_PRESENTATION__ITALIC = eINSTANCE.getNodePresentation_Italic();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute NODE_PRESENTATION__NAME = eINSTANCE.getNodePresentation_Name();

		/**
		 * The meta object literal for the '<em><b>Size</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute NODE_PRESENTATION__SIZE = eINSTANCE.getNodePresentation_Size();

		/**
		 * The meta object literal for the '<em><b>Background Color</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute NODE_PRESENTATION__BACKGROUND_COLOR = eINSTANCE.getNodePresentation_BackgroundColor();

		/**
		 * The meta object literal for the '<em><b>Color</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute NODE_PRESENTATION__COLOR = eINSTANCE.getNodePresentation_Color();

		/**
		 * The meta object literal for the '<em><b>Folded</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute NODE_PRESENTATION__FOLDED = eINSTANCE.getNodePresentation_Folded();

		/**
		 * The meta object literal for the '<em><b>Endarrow</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ARROW_LINK__ENDARROW = eINSTANCE.getArrowLink_Endarrow();

		/**
		 * The meta object literal for the '<em><b>Endinclination</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ARROW_LINK__ENDINCLINATION = eINSTANCE.getArrowLink_Endinclination();

		/**
		 * The meta object literal for the '<em><b>Startarrow</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ARROW_LINK__STARTARROW = eINSTANCE.getArrowLink_Startarrow();

		/**
		 * The meta object literal for the '<em><b>Startinclination</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ARROW_LINK__STARTINCLINATION = eINSTANCE.getArrowLink_Startinclination();

	}

} //mindmapPackage
