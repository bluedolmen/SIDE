/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.bluexml.side.view.provider;

import com.bluexml.side.view.util.ViewAdapterFactory;

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
public class ViewItemProviderAdapterFactory extends ViewAdapterFactory implements ComposeableAdapterFactory, IChangeNotifier, IDisposable {
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
	public ViewItemProviderAdapterFactory() {
		supportedTypes.add(IEditingDomainItemProvider.class);
		supportedTypes.add(IStructuredItemContentProvider.class);
		supportedTypes.add(ITreeItemContentProvider.class);
		supportedTypes.add(IItemLabelProvider.class);
		supportedTypes.add(IItemPropertySource.class);
	}

	/**
	 * This keeps track of the one adapter used for all {@link com.bluexml.side.view.Col} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ColItemProvider colItemProvider;

	/**
	 * This creates an adapter for a {@link com.bluexml.side.view.Col}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createColAdapter() {
		if (colItemProvider == null) {
			colItemProvider = new ColItemProvider(this);
		}

		return colItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link com.bluexml.side.view.Paging} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected PagingItemProvider pagingItemProvider;

	/**
	 * This creates an adapter for a {@link com.bluexml.side.view.Paging}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createPagingAdapter() {
		if (pagingItemProvider == null) {
			pagingItemProvider = new PagingItemProvider(this);
		}

		return pagingItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link com.bluexml.side.view.Sorting} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected SortingItemProvider sortingItemProvider;

	/**
	 * This creates an adapter for a {@link com.bluexml.side.view.Sorting}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createSortingAdapter() {
		if (sortingItemProvider == null) {
			sortingItemProvider = new SortingItemProvider(this);
		}

		return sortingItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link com.bluexml.side.view.TextField} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected TextFieldItemProvider textFieldItemProvider;

	/**
	 * This creates an adapter for a {@link com.bluexml.side.view.TextField}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createTextFieldAdapter() {
		if (textFieldItemProvider == null) {
			textFieldItemProvider = new TextFieldItemProvider(this);
		}

		return textFieldItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link com.bluexml.side.view.PasswordField} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected PasswordFieldItemProvider passwordFieldItemProvider;

	/**
	 * This creates an adapter for a {@link com.bluexml.side.view.PasswordField}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createPasswordFieldAdapter() {
		if (passwordFieldItemProvider == null) {
			passwordFieldItemProvider = new PasswordFieldItemProvider(this);
		}

		return passwordFieldItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link com.bluexml.side.view.BooleanField} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected BooleanFieldItemProvider booleanFieldItemProvider;

	/**
	 * This creates an adapter for a {@link com.bluexml.side.view.BooleanField}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createBooleanFieldAdapter() {
		if (booleanFieldItemProvider == null) {
			booleanFieldItemProvider = new BooleanFieldItemProvider(this);
		}

		return booleanFieldItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link com.bluexml.side.view.FloatField} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected FloatFieldItemProvider floatFieldItemProvider;

	/**
	 * This creates an adapter for a {@link com.bluexml.side.view.FloatField}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createFloatFieldAdapter() {
		if (floatFieldItemProvider == null) {
			floatFieldItemProvider = new FloatFieldItemProvider(this);
		}

		return floatFieldItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link com.bluexml.side.view.ActionField} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ActionFieldItemProvider actionFieldItemProvider;

	/**
	 * This creates an adapter for a {@link com.bluexml.side.view.ActionField}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createActionFieldAdapter() {
		if (actionFieldItemProvider == null) {
			actionFieldItemProvider = new ActionFieldItemProvider(this);
		}

		return actionFieldItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link com.bluexml.side.view.DateField} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected DateFieldItemProvider dateFieldItemProvider;

	/**
	 * This creates an adapter for a {@link com.bluexml.side.view.DateField}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createDateFieldAdapter() {
		if (dateFieldItemProvider == null) {
			dateFieldItemProvider = new DateFieldItemProvider(this);
		}

		return dateFieldItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link com.bluexml.side.view.TimeField} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected TimeFieldItemProvider timeFieldItemProvider;

	/**
	 * This creates an adapter for a {@link com.bluexml.side.view.TimeField}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createTimeFieldAdapter() {
		if (timeFieldItemProvider == null) {
			timeFieldItemProvider = new TimeFieldItemProvider(this);
		}

		return timeFieldItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link com.bluexml.side.view.DateTimeField} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected DateTimeFieldItemProvider dateTimeFieldItemProvider;

	/**
	 * This creates an adapter for a {@link com.bluexml.side.view.DateTimeField}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createDateTimeFieldAdapter() {
		if (dateTimeFieldItemProvider == null) {
			dateTimeFieldItemProvider = new DateTimeFieldItemProvider(this);
		}

		return dateTimeFieldItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link com.bluexml.side.view.PhoneNumberField} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected PhoneNumberFieldItemProvider phoneNumberFieldItemProvider;

	/**
	 * This creates an adapter for a {@link com.bluexml.side.view.PhoneNumberField}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createPhoneNumberFieldAdapter() {
		if (phoneNumberFieldItemProvider == null) {
			phoneNumberFieldItemProvider = new PhoneNumberFieldItemProvider(this);
		}

		return phoneNumberFieldItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link com.bluexml.side.view.EmailField} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EmailFieldItemProvider emailFieldItemProvider;

	/**
	 * This creates an adapter for a {@link com.bluexml.side.view.EmailField}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createEmailFieldAdapter() {
		if (emailFieldItemProvider == null) {
			emailFieldItemProvider = new EmailFieldItemProvider(this);
		}

		return emailFieldItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link com.bluexml.side.view.FileField} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected FileFieldItemProvider fileFieldItemProvider;

	/**
	 * This creates an adapter for a {@link com.bluexml.side.view.FileField}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createFileFieldAdapter() {
		if (fileFieldItemProvider == null) {
			fileFieldItemProvider = new FileFieldItemProvider(this);
		}

		return fileFieldItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link com.bluexml.side.view.FacetMap} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected FacetMapItemProvider facetMapItemProvider;

	/**
	 * This creates an adapter for a {@link com.bluexml.side.view.FacetMap}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createFacetMapAdapter() {
		if (facetMapItemProvider == null) {
			facetMapItemProvider = new FacetMapItemProvider(this);
		}

		return facetMapItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link com.bluexml.side.view.DataList} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected DataListItemProvider dataListItemProvider;

	/**
	 * This creates an adapter for a {@link com.bluexml.side.view.DataList}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createDataListAdapter() {
		if (dataListItemProvider == null) {
			dataListItemProvider = new DataListItemProvider(this);
		}

		return dataListItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link com.bluexml.side.view.Tree} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected TreeItemProvider treeItemProvider;

	/**
	 * This creates an adapter for a {@link com.bluexml.side.view.Tree}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createTreeAdapter() {
		if (treeItemProvider == null) {
			treeItemProvider = new TreeItemProvider(this);
		}

		return treeItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link com.bluexml.side.view.ComposedView} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ComposedViewItemProvider composedViewItemProvider;

	/**
	 * This creates an adapter for a {@link com.bluexml.side.view.ComposedView}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createComposedViewAdapter() {
		if (composedViewItemProvider == null) {
			composedViewItemProvider = new ComposedViewItemProvider(this);
		}

		return composedViewItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link com.bluexml.side.view.DataTable} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected DataTableItemProvider dataTableItemProvider;

	/**
	 * This creates an adapter for a {@link com.bluexml.side.view.DataTable}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createDataTableAdapter() {
		if (dataTableItemProvider == null) {
			dataTableItemProvider = new DataTableItemProvider(this);
		}

		return dataTableItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link com.bluexml.side.view.Styling} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected StylingItemProvider stylingItemProvider;

	/**
	 * This creates an adapter for a {@link com.bluexml.side.view.Styling}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createStylingAdapter() {
		if (stylingItemProvider == null) {
			stylingItemProvider = new StylingItemProvider(this);
		}

		return stylingItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link com.bluexml.side.view.SelectField} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected SelectFieldItemProvider selectFieldItemProvider;

	/**
	 * This creates an adapter for a {@link com.bluexml.side.view.SelectField}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createSelectFieldAdapter() {
		if (selectFieldItemProvider == null) {
			selectFieldItemProvider = new SelectFieldItemProvider(this);
		}

		return selectFieldItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link com.bluexml.side.view.ViewCollection} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ViewCollectionItemProvider viewCollectionItemProvider;

	/**
	 * This creates an adapter for a {@link com.bluexml.side.view.ViewCollection}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createViewCollectionAdapter() {
		if (viewCollectionItemProvider == null) {
			viewCollectionItemProvider = new ViewCollectionItemProvider(this);
		}

		return viewCollectionItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link com.bluexml.side.view.HtmlField} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected HtmlFieldItemProvider htmlFieldItemProvider;

	/**
	 * This creates an adapter for a {@link com.bluexml.side.view.HtmlField}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createHtmlFieldAdapter() {
		if (htmlFieldItemProvider == null) {
			htmlFieldItemProvider = new HtmlFieldItemProvider(this);
		}

		return htmlFieldItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link com.bluexml.side.view.URLField} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected URLFieldItemProvider urlFieldItemProvider;

	/**
	 * This creates an adapter for a {@link com.bluexml.side.view.URLField}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createURLFieldAdapter() {
		if (urlFieldItemProvider == null) {
			urlFieldItemProvider = new URLFieldItemProvider(this);
		}

		return urlFieldItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link com.bluexml.side.view.ImageField} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ImageFieldItemProvider imageFieldItemProvider;

	/**
	 * This creates an adapter for a {@link com.bluexml.side.view.ImageField}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createImageFieldAdapter() {
		if (imageFieldItemProvider == null) {
			imageFieldItemProvider = new ImageFieldItemProvider(this);
		}

		return imageFieldItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link com.bluexml.side.view.FieldGroup} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected FieldGroupItemProvider fieldGroupItemProvider;

	/**
	 * This creates an adapter for a {@link com.bluexml.side.view.FieldGroup}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createFieldGroupAdapter() {
		if (fieldGroupItemProvider == null) {
			fieldGroupItemProvider = new FieldGroupItemProvider(this);
		}

		return fieldGroupItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link com.bluexml.side.view.Actionable} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ActionableItemProvider actionableItemProvider;

	/**
	 * This creates an adapter for a {@link com.bluexml.side.view.Actionable}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createActionableAdapter() {
		if (actionableItemProvider == null) {
			actionableItemProvider = new ActionableItemProvider(this);
		}

		return actionableItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link com.bluexml.side.view.Filtering} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected FilteringItemProvider filteringItemProvider;

	/**
	 * This creates an adapter for a {@link com.bluexml.side.view.Filtering}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createFilteringAdapter() {
		if (filteringItemProvider == null) {
			filteringItemProvider = new FilteringItemProvider(this);
		}

		return filteringItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link com.bluexml.side.view.IntegerField} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected IntegerFieldItemProvider integerFieldItemProvider;

	/**
	 * This creates an adapter for a {@link com.bluexml.side.view.IntegerField}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createIntegerFieldAdapter() {
		if (integerFieldItemProvider == null) {
			integerFieldItemProvider = new IntegerFieldItemProvider(this);
		}

		return integerFieldItemProvider;
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
		if (viewCollectionItemProvider != null) viewCollectionItemProvider.dispose();
		if (colItemProvider != null) colItemProvider.dispose();
		if (pagingItemProvider != null) pagingItemProvider.dispose();
		if (sortingItemProvider != null) sortingItemProvider.dispose();
		if (filteringItemProvider != null) filteringItemProvider.dispose();
		if (stylingItemProvider != null) stylingItemProvider.dispose();
		if (dataListItemProvider != null) dataListItemProvider.dispose();
		if (dataTableItemProvider != null) dataTableItemProvider.dispose();
		if (facetMapItemProvider != null) facetMapItemProvider.dispose();
		if (treeItemProvider != null) treeItemProvider.dispose();
		if (composedViewItemProvider != null) composedViewItemProvider.dispose();
		if (textFieldItemProvider != null) textFieldItemProvider.dispose();
		if (booleanFieldItemProvider != null) booleanFieldItemProvider.dispose();
		if (passwordFieldItemProvider != null) passwordFieldItemProvider.dispose();
		if (floatFieldItemProvider != null) floatFieldItemProvider.dispose();
		if (actionFieldItemProvider != null) actionFieldItemProvider.dispose();
		if (dateFieldItemProvider != null) dateFieldItemProvider.dispose();
		if (timeFieldItemProvider != null) timeFieldItemProvider.dispose();
		if (dateTimeFieldItemProvider != null) dateTimeFieldItemProvider.dispose();
		if (phoneNumberFieldItemProvider != null) phoneNumberFieldItemProvider.dispose();
		if (emailFieldItemProvider != null) emailFieldItemProvider.dispose();
		if (integerFieldItemProvider != null) integerFieldItemProvider.dispose();
		if (fileFieldItemProvider != null) fileFieldItemProvider.dispose();
		if (selectFieldItemProvider != null) selectFieldItemProvider.dispose();
		if (htmlFieldItemProvider != null) htmlFieldItemProvider.dispose();
		if (urlFieldItemProvider != null) urlFieldItemProvider.dispose();
		if (imageFieldItemProvider != null) imageFieldItemProvider.dispose();
		if (actionableItemProvider != null) actionableItemProvider.dispose();
		if (fieldGroupItemProvider != null) fieldGroupItemProvider.dispose();
	}

}
