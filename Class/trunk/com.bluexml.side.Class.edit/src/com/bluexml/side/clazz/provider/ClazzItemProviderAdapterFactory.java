/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.bluexml.side.clazz.provider;

import com.bluexml.side.clazz.util.ClazzAdapterFactory;

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
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.INotifyChangedListener;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;

/**
 * This is the factory that is used to provide the interfaces needed to support Viewers.
 * The adapters generated by this factory convert EMF adapter notifications into calls to {@link #fireNotifyChanged fireNotifyChanged}.
 * The adapters also support Eclipse property sheets.
 * Note that most of the adapters are shared among multiple instances.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class ClazzItemProviderAdapterFactory extends ClazzAdapterFactory implements ComposeableAdapterFactory, IChangeNotifier, IDisposable {
	/**
	 * This keeps track of the root adapter factory that delegates to this adapter factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ComposedAdapterFactory parentAdapterFactory;

	/**
	 * This is used to implement {@link org.eclipse.emf.edit.provider.IChangeNotifier}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected IChangeNotifier changeNotifier = new ChangeNotifier();

	/**
	 * This keeps track of all the supported types checked by {@link #isFactoryForType isFactoryForType}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected Collection<Object> supportedTypes = new ArrayList<Object>();

	/**
	 * This constructs an instance.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ClazzItemProviderAdapterFactory() {
		supportedTypes.add(IEditingDomainItemProvider.class);
		supportedTypes.add(IStructuredItemContentProvider.class);
		supportedTypes.add(ITreeItemContentProvider.class);
		supportedTypes.add(IItemLabelProvider.class);
		supportedTypes.add(IItemPropertySource.class);
	}

	/**
	 * This keeps track of the one adapter used for all {@link com.bluexml.side.clazz.ClassModelElement} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ClassModelElementItemProvider classModelElementItemProvider;

	/**
	 * This creates an adapter for a {@link com.bluexml.side.clazz.ClassModelElement}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createClassModelElementAdapter() {
		if (classModelElementItemProvider == null) {
			classModelElementItemProvider = new ClassModelElementItemProvider(this);
		}

		return classModelElementItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link com.bluexml.side.clazz.NamedClassModelElement} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected NamedClassModelElementItemProvider namedClassModelElementItemProvider;

	/**
	 * This creates an adapter for a {@link com.bluexml.side.clazz.NamedClassModelElement}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createNamedClassModelElementAdapter() {
		if (namedClassModelElementItemProvider == null) {
			namedClassModelElementItemProvider = new NamedClassModelElementItemProvider(this);
		}

		return namedClassModelElementItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link com.bluexml.side.clazz.ClassPackage} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ClassPackageItemProvider classPackageItemProvider;

	/**
	 * This creates an adapter for a {@link com.bluexml.side.clazz.ClassPackage}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createClassPackageAdapter() {
		if (classPackageItemProvider == null) {
			classPackageItemProvider = new ClassPackageItemProvider(this);
		}

		return classPackageItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link com.bluexml.side.clazz.Clazz} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ClazzItemProvider clazzItemProvider;

	/**
	 * This creates an adapter for a {@link com.bluexml.side.clazz.Clazz}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createClazzAdapter() {
		if (clazzItemProvider == null) {
			clazzItemProvider = new ClazzItemProvider(this);
		}

		return clazzItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link com.bluexml.side.clazz.Association} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected AssociationItemProvider associationItemProvider;

	/**
	 * This creates an adapter for a {@link com.bluexml.side.clazz.Association}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createAssociationAdapter() {
		if (associationItemProvider == null) {
			associationItemProvider = new AssociationItemProvider(this);
		}

		return associationItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link com.bluexml.side.clazz.Attribute} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected AttributeItemProvider attributeItemProvider;

	/**
	 * This creates an adapter for a {@link com.bluexml.side.clazz.Attribute}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createAttributeAdapter() {
		if (attributeItemProvider == null) {
			attributeItemProvider = new AttributeItemProvider(this);
		}

		return attributeItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link com.bluexml.side.clazz.Enumeration} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EnumerationItemProvider enumerationItemProvider;

	/**
	 * This creates an adapter for a {@link com.bluexml.side.clazz.Enumeration}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createEnumerationAdapter() {
		if (enumerationItemProvider == null) {
			enumerationItemProvider = new EnumerationItemProvider(this);
		}

		return enumerationItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link com.bluexml.side.clazz.EnumerationLiteral} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EnumerationLiteralItemProvider enumerationLiteralItemProvider;

	/**
	 * This creates an adapter for a {@link com.bluexml.side.clazz.EnumerationLiteral}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createEnumerationLiteralAdapter() {
		if (enumerationLiteralItemProvider == null) {
			enumerationLiteralItemProvider = new EnumerationLiteralItemProvider(this);
		}

		return enumerationLiteralItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link com.bluexml.side.clazz.Operation} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected OperationItemProvider operationItemProvider;

	/**
	 * This creates an adapter for a {@link com.bluexml.side.clazz.Operation}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createOperationAdapter() {
		if (operationItemProvider == null) {
			operationItemProvider = new OperationItemProvider(this);
		}

		return operationItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link com.bluexml.side.clazz.Parameter} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ParameterItemProvider parameterItemProvider;

	/**
	 * This creates an adapter for a {@link com.bluexml.side.clazz.Parameter}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createParameterAdapter() {
		if (parameterItemProvider == null) {
			parameterItemProvider = new ParameterItemProvider(this);
		}

		return parameterItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link com.bluexml.side.clazz.Aspect} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected AspectItemProvider aspectItemProvider;

	/**
	 * This creates an adapter for a {@link com.bluexml.side.clazz.Aspect}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createAspectAdapter() {
		if (aspectItemProvider == null) {
			aspectItemProvider = new AspectItemProvider(this);
		}

		return aspectItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link com.bluexml.side.clazz.View} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ViewItemProvider viewItemProvider;

	/**
	 * This creates an adapter for a {@link com.bluexml.side.clazz.View}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createViewAdapter() {
		if (viewItemProvider == null) {
			viewItemProvider = new ViewItemProvider(this);
		}

		return viewItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link com.bluexml.side.clazz.ViewItem} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ViewItemItemProvider viewItemItemProvider;

	/**
	 * This creates an adapter for a {@link com.bluexml.side.clazz.ViewItem}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createViewItemAdapter() {
		if (viewItemItemProvider == null) {
			viewItemItemProvider = new ViewItemItemProvider(this);
		}

		return viewItemItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link com.bluexml.side.clazz.MetaInfo} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected MetaInfoItemProvider metaInfoItemProvider;

	/**
	 * This creates an adapter for a {@link com.bluexml.side.clazz.MetaInfo}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createMetaInfoAdapter() {
		if (metaInfoItemProvider == null) {
			metaInfoItemProvider = new MetaInfoItemProvider(this);
		}

		return metaInfoItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link com.bluexml.side.clazz.MetaInfoGroup} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected MetaInfoGroupItemProvider metaInfoGroupItemProvider;

	/**
	 * This creates an adapter for a {@link com.bluexml.side.clazz.MetaInfoGroup}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createMetaInfoGroupAdapter() {
		if (metaInfoGroupItemProvider == null) {
			metaInfoGroupItemProvider = new MetaInfoGroupItemProvider(this);
		}

		return metaInfoGroupItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link com.bluexml.side.clazz.ClassComment} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ClassCommentItemProvider classCommentItemProvider;

	/**
	 * This creates an adapter for a {@link com.bluexml.side.clazz.ClassComment}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createClassCommentAdapter() {
		if (classCommentItemProvider == null) {
			classCommentItemProvider = new ClassCommentItemProvider(this);
		}

		return classCommentItemProvider;
	}

	/**
	 * This returns the root adapter factory that contains this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ComposeableAdapterFactory getRootAdapterFactory() {
		return parentAdapterFactory == null ? this : parentAdapterFactory.getRootAdapterFactory();
	}

	/**
	 * This sets the composed adapter factory that contains this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setParentAdapterFactory(ComposedAdapterFactory parentAdapterFactory) {
		this.parentAdapterFactory = parentAdapterFactory;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isFactoryForType(Object type) {
		return supportedTypes.contains(type) || super.isFactoryForType(type);
	}

	/**
	 * This implementation substitutes the factory itself as the key for the adapter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter adapt(Notifier notifier, Object type) {
		return super.adapt(notifier, this);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object adapt(Object object, Object type) {
		if (isFactoryForType(type)) {
			Object adapter = super.adapt(object, type);
			if (!(type instanceof Class) || (((Class<?>)type).isInstance(adapter))) {
				return adapter;
			}
		}

		return null;
	}

	/**
	 * This adds a listener.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void addListener(INotifyChangedListener notifyChangedListener) {
		changeNotifier.addListener(notifyChangedListener);
	}

	/**
	 * This removes a listener.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void removeListener(INotifyChangedListener notifyChangedListener) {
		changeNotifier.removeListener(notifyChangedListener);
	}

	/**
	 * This delegates to {@link #changeNotifier} and to {@link #parentAdapterFactory}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void fireNotifyChanged(Notification notification) {
		changeNotifier.fireNotifyChanged(notification);

		if (parentAdapterFactory != null) {
			parentAdapterFactory.fireNotifyChanged(notification);
		}
	}

	/**
	 * This disposes all of the item providers created by this factory. 
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void dispose() {
		if (classModelElementItemProvider != null) classModelElementItemProvider.dispose();
		if (namedClassModelElementItemProvider != null) namedClassModelElementItemProvider.dispose();
		if (classPackageItemProvider != null) classPackageItemProvider.dispose();
		if (clazzItemProvider != null) clazzItemProvider.dispose();
		if (associationItemProvider != null) associationItemProvider.dispose();
		if (attributeItemProvider != null) attributeItemProvider.dispose();
		if (enumerationItemProvider != null) enumerationItemProvider.dispose();
		if (enumerationLiteralItemProvider != null) enumerationLiteralItemProvider.dispose();
		if (operationItemProvider != null) operationItemProvider.dispose();
		if (parameterItemProvider != null) parameterItemProvider.dispose();
		if (aspectItemProvider != null) aspectItemProvider.dispose();
		if (viewItemProvider != null) viewItemProvider.dispose();
		if (viewItemItemProvider != null) viewItemItemProvider.dispose();
		if (metaInfoItemProvider != null) metaInfoItemProvider.dispose();
		if (metaInfoGroupItemProvider != null) metaInfoGroupItemProvider.dispose();
		if (classCommentItemProvider != null) classCommentItemProvider.dispose();
	}

}
