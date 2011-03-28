/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.bluexml.side.requirements.generator.metamodel.MindMap.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;

import com.bluexml.side.requirements.generator.metamodel.MindMap.ArrowLink;
import com.bluexml.side.requirements.generator.metamodel.MindMap.Map;
import com.bluexml.side.requirements.generator.metamodel.MindMap.Maps;
import com.bluexml.side.requirements.generator.metamodel.MindMap.Node;
import com.bluexml.side.requirements.generator.metamodel.MindMap.NodePresentation;
import com.bluexml.side.requirements.generator.metamodel.MindMap.mindmapFactory;
import com.bluexml.side.requirements.generator.metamodel.MindMap.mindmapPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class mindmapFactoryImpl extends EFactoryImpl implements mindmapFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static mindmapFactory init() {
		try {
			mindmapFactory themindmapFactory = (mindmapFactory)EPackage.Registry.INSTANCE.getEFactory("http://www.bluexml.com/rwm/mindmap/1.0/"); 
			if (themindmapFactory != null) {
				return themindmapFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new mindmapFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public mindmapFactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
			case mindmapPackage.MAPS: return createMaps();
			case mindmapPackage.MAP: return createMap();
			case mindmapPackage.NODE: return createNode();
			case mindmapPackage.ARROW_LINK: return createArrowLink();
			case mindmapPackage.NODE_PRESENTATION: return createNodePresentation();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Maps createMaps() {
		MapsImpl maps = new MapsImpl();
		return maps;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Map createMap() {
		MapImpl map = new MapImpl();
		return map;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Node createNode() {
		NodeImpl node = new NodeImpl();
		return node;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ArrowLink createArrowLink() {
		ArrowLinkImpl arrowLink = new ArrowLinkImpl();
		return arrowLink;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NodePresentation createNodePresentation() {
		NodePresentationImpl nodePresentation = new NodePresentationImpl();
		return nodePresentation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public mindmapPackage getmindmapPackage() {
		return (mindmapPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static mindmapPackage getPackage() {
		return mindmapPackage.eINSTANCE;
	}

} //mindmapFactoryImpl
