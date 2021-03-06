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
 * No CopyrightText Defined in the configurator file.
 ******************************************************************************/
package com.bluexml.side.Requirements.modeler.providers;

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

import com.bluexml.side.requirements.util.RequirementsAdapterFactory;

/**
 * This is the factory that is used to provide the interfaces needed to support Viewers. The adapters generated by this factory convert EMF adapter notifications into calls to {@link #fireNotifyChanged fireNotifyChanged}. The adapters also support Eclipse property sheets. Note that most of the adapters are shared among multiple instances.
 * 
 * @generated
 */
public class RequirementsModelerProviderAdapterFactory extends
		RequirementsAdapterFactory implements ComposeableAdapterFactory,
		IChangeNotifier, IDisposable {
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
	 * This keeps track of the one adapter used for all {@link com.bluexml.side.requirements.ModelElement} instances.
	 * 
	 * @generated
	 */
	private ModelElementModelerProvider modelelementModelerProvider;

	/**
	 * This keeps track of the one adapter used for all {@link com.bluexml.side.requirements.BasicElement} instances.
	 * 
	 * @generated
	 */
	private BasicElementModelerProvider basicelementModelerProvider;

	/**
	 * This keeps track of the one adapter used for all {@link com.bluexml.side.requirements.Entity} instances.
	 * 
	 * @generated
	 */
	private EntityModelerProvider entityModelerProvider;

	/**
	 * This keeps track of the one adapter used for all {@link com.bluexml.side.requirements.RelationShip} instances.
	 * 
	 * @generated
	 */
	private RelationShipModelerProvider relationshipModelerProvider;

	/**
	 * This keeps track of the one adapter used for all {@link com.bluexml.side.requirements.Attribute} instances.
	 * 
	 * @generated
	 */
	private AttributeModelerProvider attributeModelerProvider;

	/**
	 * This keeps track of the one adapter used for all {@link com.bluexml.side.requirements.Organization} instances.
	 * 
	 * @generated
	 */
	private OrganizationModelerProvider organizationModelerProvider;

	/**
	 * This keeps track of the one adapter used for all {@link com.bluexml.side.requirements.Agent} instances.
	 * 
	 * @generated
	 */
	private AgentModelerProvider agentModelerProvider;

	/**
	 * This keeps track of the one adapter used for all {@link com.bluexml.side.requirements.Goal} instances.
	 * 
	 * @generated
	 */
	private GoalModelerProvider goalModelerProvider;

	/**
	 * This keeps track of the one adapter used for all {@link com.bluexml.side.requirements.Privilege} instances.
	 * 
	 * @generated
	 */
	private PrivilegeModelerProvider privilegeModelerProvider;

	/**
	 * This keeps track of the one adapter used for all {@link com.bluexml.side.requirements.RequirementsDefinition} instances.
	 * 
	 * @generated
	 */
	private RequirementsDefinitionModelerProvider requirementsdefinitionModelerProvider;

	/**
	 * This keeps track of the one adapter used for all {@link com.bluexml.side.requirements.PrivilegeGroup} instances.
	 * 
	 * @generated
	 */
	private PrivilegeGroupModelerProvider privilegegroupModelerProvider;

	/**
	 * This constructs an instance.
	 * 
	 * @generated
	 */
	public RequirementsModelerProviderAdapterFactory() {
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
	 * This creates an adapter for a {@link com.bluexml.side.requirements.ModelElement}.
	 *
	 * @return the Adapter
	 * @generated
	 */
	public Adapter createModelElementAdapter() {
		if (modelelementModelerProvider == null) {
			modelelementModelerProvider = new ModelElementModelerProvider(this);
		}

		return modelelementModelerProvider;
	}

	/**
	 * This creates an adapter for a {@link com.bluexml.side.requirements.BasicElement}.
	 *
	 * @return the Adapter
	 * @generated
	 */
	public Adapter createBasicElementAdapter() {
		if (basicelementModelerProvider == null) {
			basicelementModelerProvider = new BasicElementModelerProvider(this);
		}

		return basicelementModelerProvider;
	}

	/**
	 * This creates an adapter for a {@link com.bluexml.side.requirements.Entity}.
	 *
	 * @return the Adapter
	 * @generated
	 */
	public Adapter createEntityAdapter() {
		if (entityModelerProvider == null) {
			entityModelerProvider = new EntityModelerProvider(this);
		}

		return entityModelerProvider;
	}

	/**
	 * This creates an adapter for a {@link com.bluexml.side.requirements.RelationShip}.
	 *
	 * @return the Adapter
	 * @generated
	 */
	public Adapter createRelationShipAdapter() {
		if (relationshipModelerProvider == null) {
			relationshipModelerProvider = new RelationShipModelerProvider(this);
		}

		return relationshipModelerProvider;
	}

	/**
	 * This creates an adapter for a {@link com.bluexml.side.requirements.Attribute}.
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
	 * This creates an adapter for a {@link com.bluexml.side.requirements.Organization}.
	 *
	 * @return the Adapter
	 * @generated
	 */
	public Adapter createOrganizationAdapter() {
		if (organizationModelerProvider == null) {
			organizationModelerProvider = new OrganizationModelerProvider(this);
		}

		return organizationModelerProvider;
	}

	/**
	 * This creates an adapter for a {@link com.bluexml.side.requirements.Agent}.
	 *
	 * @return the Adapter
	 * @generated
	 */
	public Adapter createAgentAdapter() {
		if (agentModelerProvider == null) {
			agentModelerProvider = new AgentModelerProvider(this);
		}

		return agentModelerProvider;
	}

	/**
	 * This creates an adapter for a {@link com.bluexml.side.requirements.Goal}.
	 *
	 * @return the Adapter
	 * @generated
	 */
	public Adapter createGoalAdapter() {
		if (goalModelerProvider == null) {
			goalModelerProvider = new GoalModelerProvider(this);
		}

		return goalModelerProvider;
	}

	/**
	 * This creates an adapter for a {@link com.bluexml.side.requirements.Privilege}.
	 *
	 * @return the Adapter
	 * @generated
	 */
	public Adapter createPrivilegeAdapter() {
		if (privilegeModelerProvider == null) {
			privilegeModelerProvider = new PrivilegeModelerProvider(this);
		}

		return privilegeModelerProvider;
	}

	/**
	 * This creates an adapter for a {@link com.bluexml.side.requirements.RequirementsDefinition}.
	 *
	 * @return the Adapter
	 * @generated
	 */
	public Adapter createRequirementsDefinitionAdapter() {
		if (requirementsdefinitionModelerProvider == null) {
			requirementsdefinitionModelerProvider = new RequirementsDefinitionModelerProvider(
					this);
		}

		return requirementsdefinitionModelerProvider;
	}

	/**
	 * This creates an adapter for a {@link com.bluexml.side.requirements.PrivilegeGroup}.
	 *
	 * @return the Adapter
	 * @generated
	 */
	public Adapter createPrivilegeGroupAdapter() {
		if (privilegegroupModelerProvider == null) {
			privilegegroupModelerProvider = new PrivilegeGroupModelerProvider(
					this);
		}

		return privilegegroupModelerProvider;
	}

	/**
	 * This disposes all of the item providers created by this factory.
	 * 
	 * @generated
	 */
	public void dispose() {
		if (modelelementModelerProvider != null) {
			modelelementModelerProvider.dispose();
		}
		if (basicelementModelerProvider != null) {
			basicelementModelerProvider.dispose();
		}
		if (entityModelerProvider != null) {
			entityModelerProvider.dispose();
		}
		if (relationshipModelerProvider != null) {
			relationshipModelerProvider.dispose();
		}
		if (attributeModelerProvider != null) {
			attributeModelerProvider.dispose();
		}
		if (organizationModelerProvider != null) {
			organizationModelerProvider.dispose();
		}
		if (agentModelerProvider != null) {
			agentModelerProvider.dispose();
		}
		if (goalModelerProvider != null) {
			goalModelerProvider.dispose();
		}
		if (privilegeModelerProvider != null) {
			privilegeModelerProvider.dispose();
		}
		if (requirementsdefinitionModelerProvider != null) {
			requirementsdefinitionModelerProvider.dispose();
		}
		if (privilegegroupModelerProvider != null) {
			privilegegroupModelerProvider.dispose();
		}
	}

}
