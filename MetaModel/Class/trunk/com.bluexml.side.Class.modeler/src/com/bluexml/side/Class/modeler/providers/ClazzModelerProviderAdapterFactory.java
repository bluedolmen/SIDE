/*
    Copyright (C) 2007-20013  BlueXML - www.bluexml.com

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU Affero General Public License as
    published by the Free Software Foundation, either version 3 of the
    License, or (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU Affero General Public License for more details.

    You should have received a copy of the GNU Affero General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/
/*******************************************************************************
 * 
 ******************************************************************************/
package com.bluexml.side.Class.modeler.providers;

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.edit.provider.ChangeNotifier;
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.provider.IChangeNotifier;
import org.eclipse.emf.edit.provider.IDisposable;
import org.eclipse.emf.edit.provider.INotifyChangedListener;
import org.topcased.modeler.providers.ILabelFeatureProvider;

import com.bluexml.side.clazz.util.ClazzAdapterFactory;

/**
 * This is the factory that is used to provide the interfaces needed to support Viewers. The adapters generated by this factory convert EMF adapter notifications into calls to {@link #fireNotifyChanged fireNotifyChanged}. The adapters also support Eclipse property sheets. Note that most of the adapters are shared among multiple instances.
 * 
 * @generated
 */
public class ClazzModelerProviderAdapterFactory extends ClazzAdapterFactory
		implements ComposeableAdapterFactory, IChangeNotifier, IDisposable {
	/**
	 * This keeps track of the root adapter factory that delegates to this adapter factory.
	 * 
	 * @generated
	 */
	private ComposedAdapterFactory parentAdapterFactory;

	/**
	 * This is used to implement {@link org.eclipse.emf.edit.provider.IChangeNotifier}.
	 * 
	 * @generated
	 */
	private IChangeNotifier changeNotifier = new ChangeNotifier();

	/**
	 * This keeps track of all the supported types checked by {@link #isFactoryForType isFactoryForType}.
	 * 
	 * @generated
	 */
	private Collection supportedTypes = new ArrayList();

	/**
	 * This keeps track of the one adapter used for all {@link com.bluexml.side.clazz.ClassModelElement} instances.
	 * 
	 * @generated
	 */
	private ClassModelElementModelerProvider classmodelelementModelerProvider;

	/**
	 * This keeps track of the one adapter used for all {@link com.bluexml.side.clazz.NamedClassModelElement} instances.
	 * 
	 * @generated
	 */
	private NamedClassModelElementModelerProvider namedclassmodelelementModelerProvider;

	/**
	 * This keeps track of the one adapter used for all {@link com.bluexml.side.clazz.ClassPackage} instances.
	 * 
	 * @generated
	 */
	private ClassPackageModelerProvider classpackageModelerProvider;

	/**
	 * This keeps track of the one adapter used for all {@link com.bluexml.side.clazz.Clazz} instances.
	 * 
	 * @generated
	 */
	private ClazzModelerProvider clazzModelerProvider;

	/**
	 * This keeps track of the one adapter used for all {@link com.bluexml.side.clazz.Association} instances.
	 * 
	 * @generated
	 */
	private AssociationModelerProvider associationModelerProvider;

	/**
	 * This keeps track of the one adapter used for all {@link com.bluexml.side.clazz.Attribute} instances.
	 * 
	 * @generated
	 */
	private AttributeModelerProvider attributeModelerProvider;

	/**
	 * This keeps track of the one adapter used for all {@link com.bluexml.side.clazz.Enumeration} instances.
	 * 
	 * @generated
	 */
	private EnumerationModelerProvider enumerationModelerProvider;

	/**
	 * This keeps track of the one adapter used for all {@link com.bluexml.side.clazz.EnumerationLiteral} instances.
	 * 
	 * @generated
	 */
	private EnumerationLiteralModelerProvider enumerationliteralModelerProvider;

	/**
	 * This keeps track of the one adapter used for all {@link com.bluexml.side.common.Operation} instances.
	 * 
	 * @generated
	 */
	private OperationModelerProvider operationModelerProvider;

	/**
	 * This keeps track of the one adapter used for all {@link com.bluexml.side.common.Parameter} instances.
	 * 
	 * @generated
	 */
	private ParameterModelerProvider parameterModelerProvider;

	/**
	 * This keeps track of the one adapter used for all {@link com.bluexml.side.clazz.Aspect} instances.
	 * 
	 * @generated
	 */
	private AspectModelerProvider aspectModelerProvider;

	/**
	 * This keeps track of the one adapter used for all {@link com.bluexml.side.clazz.AbstractClass} instances.
	 * 
	 * @generated
	 */
	private AbstractClassModelerProvider abstractclassModelerProvider;

	/**
	 * This keeps track of the one adapter used for all {@link com.bluexml.side.clazz.AbstractContainer} instances.
	 * 
	 * @generated
	 */
	private AbstractContainerModelerProvider abstractcontainerModelerProvider;
	
	/**
	 * This keeps track of the one adapter used for all {@link com.bluexml.side.common.MetaInfo} instances.
	 * 
	 * @generated
	 */
	private MetaInfoModelerProvider metainfoModelerProvider;

	/**
	 * This keeps track of the one adapter used for all {@link com.bluexml.side.common.MetaInfoGroup} instances.
	 * 
	 * @generated
	 */
	private MetaInfoGroupModelerProvider metainfogroupModelerProvider;

	/**
	 * This constructs an instance.
	 * 
	 * @generated
	 */
	public ClazzModelerProviderAdapterFactory() {
		supportedTypes.add(ILabelFeatureProvider.class);
	}

	/**
	 * This returns the root adapter factory that contains this factory.
	 *
	 * @return the root AdapterFactory
	 * @generated
	 */
	public ComposeableAdapterFactory getRootAdapterFactory() {
		return parentAdapterFactory == null ? this : parentAdapterFactory
				.getRootAdapterFactory();
	}

	/**
	 * This sets the composed adapter factory that contains this factory.
	 *
	 * @param parentAdapterFactory the new parent adapter factory
	 * @generated
	 */
	public void setParentAdapterFactory(
			ComposedAdapterFactory parentAdapterFactory) {
		this.parentAdapterFactory = parentAdapterFactory;
	}

	/**
	 * @param type the type to test
	 * @return true if supported
	 * 
	 * @generated
	 */
	public boolean isFactoryForType(Object type) {
		return supportedTypes.contains(type) || super.isFactoryForType(type);
	}

	/**
	 * This implementation substitutes the factory itself as the key for the adapter.
	 *
	 * @param notifier the notifier
	 * @param type the object to adapt
	 * @return the Adapter the created adatper
	 * @generated
	 */
	public Adapter adapt(Notifier notifier, Object type) {
		return super.adapt(notifier, this);
	}

	/**
	 * @param object the object to adapt
	 * @param type the type to adapt
	 * @return the adapted Object
	 * @generated
	 */
	public Object adapt(Object object, Object type) {
		if (isFactoryForType(type)) {
			Object adapter = super.adapt(object, type);
			if (!(type instanceof Class)
					|| (((Class) type).isInstance(adapter))) {
				return adapter;
			}
		}

		return null;
	}

	/**
	 * This adds a listener.
	 *
	 * @param notifyChangedListener the listener to add
	 * @generated
	 */
	public void addListener(INotifyChangedListener notifyChangedListener) {
		changeNotifier.addListener(notifyChangedListener);
	}

	/**
	 * This removes a listener.
	 *
	 * @param notifyChangedListener the listener to remove
	 * 
	 * @generated
	 */
	public void removeListener(INotifyChangedListener notifyChangedListener) {
		changeNotifier.removeListener(notifyChangedListener);
	}

	/**
	 * This delegates to {@link #changeNotifier} and to {@link #parentAdapterFactory}.
	 *
	 * @param notification the notification to fire
	 * @generated
	 */
	public void fireNotifyChanged(Notification notification) {
		changeNotifier.fireNotifyChanged(notification);

		if (parentAdapterFactory != null) {
			parentAdapterFactory.fireNotifyChanged(notification);
		}
	}

	/**
	 * This creates an adapter for a {@link com.bluexml.side.clazz.ClassModelElement}.
	 *
	 * @return the Adapter
	 * @generated
	 */
	public Adapter createClassModelElementAdapter() {
		if (classmodelelementModelerProvider == null) {
			classmodelelementModelerProvider = new ClassModelElementModelerProvider(
					this);
		}

		return classmodelelementModelerProvider;
	}

	/**
	 * This creates an adapter for a {@link com.bluexml.side.clazz.NamedClassModelElement}.
	 *
	 * @return the Adapter
	 * @generated
	 */
	public Adapter createNamedClassModelElementAdapter() {
		if (namedclassmodelelementModelerProvider == null) {
			namedclassmodelelementModelerProvider = new NamedClassModelElementModelerProvider(
					this);
		}

		return namedclassmodelelementModelerProvider;
	}

	/**
	 * This creates an adapter for a {@link com.bluexml.side.clazz.ClassPackage}.
	 *
	 * @return the Adapter
	 * @generated
	 */
	public Adapter createClassPackageAdapter() {
		if (classpackageModelerProvider == null) {
			classpackageModelerProvider = new ClassPackageModelerProvider(this);
		}

		return classpackageModelerProvider;
	}

	/**
	 * This creates an adapter for a {@link com.bluexml.side.clazz.Clazz}.
	 *
	 * @return the Adapter
	 * @generated
	 */
	public Adapter createClazzAdapter() {
		if (clazzModelerProvider == null) {
			clazzModelerProvider = new ClazzModelerProvider(this);
		}

		return clazzModelerProvider;
	}

	/**
	 * This creates an adapter for a {@link com.bluexml.side.clazz.Association}.
	 *
	 * @return the Adapter
	 * @generated
	 */
	public Adapter createAssociationAdapter() {
		if (associationModelerProvider == null) {
			associationModelerProvider = new AssociationModelerProvider(this);
		}

		return associationModelerProvider;
	}

	/**
	 * This creates an adapter for a {@link com.bluexml.side.clazz.Attribute}.
	 *
	 * @return the Adapter
	 * @generated
	 */
	public Adapter createAttributeAdapter() {
		if (attributeModelerProvider == null) {
			attributeModelerProvider = new AttributeModelerProvider(this);
		}

		return attributeModelerProvider;
	}

	/**
	 * This creates an adapter for a {@link com.bluexml.side.clazz.Enumeration}.
	 *
	 * @return the Adapter
	 * @generated
	 */
	public Adapter createEnumerationAdapter() {
		if (enumerationModelerProvider == null) {
			enumerationModelerProvider = new EnumerationModelerProvider(this);
		}

		return enumerationModelerProvider;
	}

	/**
	 * This creates an adapter for a {@link com.bluexml.side.clazz.EnumerationLiteral}.
	 *
	 * @return the Adapter
	 * @generated
	 */
	public Adapter createEnumerationLiteralAdapter() {
		if (enumerationliteralModelerProvider == null) {
			enumerationliteralModelerProvider = new EnumerationLiteralModelerProvider(
					this);
		}

		return enumerationliteralModelerProvider;
	}

	/**
	 * This creates an adapter for a {@link com.bluexml.side.common.Operation}.
	 *
	 * @return the Adapter
	 * @generated
	 */
	public Adapter createOperationAdapter() {
		if (operationModelerProvider == null) {
			operationModelerProvider = new OperationModelerProvider(this);
		}

		return operationModelerProvider;
	}

	/**
	 * This creates an adapter for a {@link com.bluexml.side.common.Parameter}.
	 *
	 * @return the Adapter
	 * @generated
	 */
	public Adapter createParameterAdapter() {
		if (parameterModelerProvider == null) {
			parameterModelerProvider = new ParameterModelerProvider(this);
		}

		return parameterModelerProvider;
	}

	/**
	 * This creates an adapter for a {@link com.bluexml.side.clazz.Aspect}.
	 *
	 * @return the Adapter
	 * @generated
	 */
	public Adapter createAspectAdapter() {
		if (aspectModelerProvider == null) {
			aspectModelerProvider = new AspectModelerProvider(this);
		}

		return aspectModelerProvider;
	}

	/**
	 * This creates an adapter for a {@link com.bluexml.side.clazz.AbstractClass}.
	 *
	 * @return the Adapter
	 * @generated
	 */
	public Adapter createAbstractClassAdapter() {
		if (abstractclassModelerProvider == null) {
			abstractclassModelerProvider = new AbstractClassModelerProvider(
					this);
		}

		return abstractclassModelerProvider;
	}

	/**
	 * This creates an adapter for a {@link com.bluexml.side.clazz.AbstractContainer}.
	 *
	 * @return the Adapter
	 * @generated
	 */
	public Adapter createAbstractContainerAdapter() {
		if (abstractcontainerModelerProvider == null) {
			abstractcontainerModelerProvider = new AbstractContainerModelerProvider(
					this);
		}

		return abstractcontainerModelerProvider;
	}
	
	/**
	 * This creates an adapter for a {@link com.bluexml.side.common.MetaInfo}.
	 *
	 * @return the Adapter
	 * @generated
	 */
	public Adapter createMetaInfoAdapter() {
		if (metainfoModelerProvider == null) {
			metainfoModelerProvider = new MetaInfoModelerProvider(this);
		}

		return metainfoModelerProvider;
	}

	/**
	 * This creates an adapter for a {@link com.bluexml.side.common.MetaInfoGroup}.
	 *
	 * @return the Adapter
	 * @generated
	 */
	public Adapter createMetaInfoGroupAdapter() {
		if (metainfogroupModelerProvider == null) {
			metainfogroupModelerProvider = new MetaInfoGroupModelerProvider(
					this);
		}

		return metainfogroupModelerProvider;
	}

	/**
	 * This disposes all of the item providers created by this factory.
	 * 
	 * @generated
	 */
	public void dispose() {
		if (classmodelelementModelerProvider != null) {
			classmodelelementModelerProvider.dispose();
		}
		if (namedclassmodelelementModelerProvider != null) {
			namedclassmodelelementModelerProvider.dispose();
		}
		if (classpackageModelerProvider != null) {
			classpackageModelerProvider.dispose();
		}
		if (clazzModelerProvider != null) {
			clazzModelerProvider.dispose();
		}
		if (associationModelerProvider != null) {
			associationModelerProvider.dispose();
		}
		if (attributeModelerProvider != null) {
			attributeModelerProvider.dispose();
		}
		if (enumerationModelerProvider != null) {
			enumerationModelerProvider.dispose();
		}
		if (enumerationliteralModelerProvider != null) {
			enumerationliteralModelerProvider.dispose();
		}
		if (operationModelerProvider != null) {
			operationModelerProvider.dispose();
		}
		if (parameterModelerProvider != null) {
			parameterModelerProvider.dispose();
		}
		if (aspectModelerProvider != null) {
			aspectModelerProvider.dispose();
		}
		if (abstractclassModelerProvider != null) {
			abstractclassModelerProvider.dispose();
		}
		if (abstractcontainerModelerProvider != null) {
			abstractcontainerModelerProvider.dispose();
		}		
		if (metainfoModelerProvider != null) {
			metainfoModelerProvider.dispose();
		}
		if (metainfogroupModelerProvider != null) {
			metainfogroupModelerProvider.dispose();
		}
	}

}
