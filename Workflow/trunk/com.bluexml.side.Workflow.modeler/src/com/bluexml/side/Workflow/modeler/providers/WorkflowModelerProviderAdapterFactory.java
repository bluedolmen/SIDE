/*******************************************************************************
 * No CopyrightText Defined in the configurator file.
 ******************************************************************************/
package com.bluexml.side.Workflow.modeler.providers;

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

import com.bluexml.side.workflow.util.WorkflowAdapterFactory;

/**
 * This is the factory that is used to provide the interfaces needed to support Viewers. The adapters generated by this factory convert EMF adapter notifications into calls to {@link #fireNotifyChanged fireNotifyChanged}. The adapters also support Eclipse property sheets. Note that most of the adapters are shared among multiple instances.
 * 
 * @generated
 */
public class WorkflowModelerProviderAdapterFactory extends
		WorkflowAdapterFactory implements ComposeableAdapterFactory,
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
	 * This keeps track of the one adapter used for all {@link com.bluexml.side.workflow.WorkflowModelElement} instances.
	 * 
	 * @generated
	 */
	private WorkflowModelElementModelerProvider workflowmodelelementModelerProvider;

	/**
	 * This keeps track of the one adapter used for all {@link com.bluexml.side.workflow.Process} instances.
	 * 
	 * @generated
	 */
	private ProcessModelerProvider processModelerProvider;

	/**
	 * This keeps track of the one adapter used for all {@link com.bluexml.side.workflow.Swimlane} instances.
	 * 
	 * @generated
	 */
	private SwimlaneModelerProvider swimlaneModelerProvider;

	/**
	 * This keeps track of the one adapter used for all {@link com.bluexml.side.workflow.StartState} instances.
	 * 
	 * @generated
	 */
	private StartStateModelerProvider startstateModelerProvider;

	/**
	 * This keeps track of the one adapter used for all {@link com.bluexml.side.workflow.EndState} instances.
	 * 
	 * @generated
	 */
	private EndStateModelerProvider endstateModelerProvider;

	/**
	 * This keeps track of the one adapter used for all {@link com.bluexml.side.workflow.Node} instances.
	 * 
	 * @generated
	 */
	private NodeModelerProvider nodeModelerProvider;

	/**
	 * This keeps track of the one adapter used for all {@link com.bluexml.side.workflow.TaskNode} instances.
	 * 
	 * @generated
	 */
	private TaskNodeModelerProvider tasknodeModelerProvider;

	/**
	 * This keeps track of the one adapter used for all {@link com.bluexml.side.workflow.ProcessState} instances.
	 * 
	 * @generated
	 */
	private ProcessStateModelerProvider processstateModelerProvider;

	/**
	 * This keeps track of the one adapter used for all {@link com.bluexml.side.workflow.Fork} instances.
	 * 
	 * @generated
	 */
	private ForkModelerProvider forkModelerProvider;

	/**
	 * This keeps track of the one adapter used for all {@link com.bluexml.side.workflow.Join} instances.
	 * 
	 * @generated
	 */
	private JoinModelerProvider joinModelerProvider;

	/**
	 * This keeps track of the one adapter used for all {@link com.bluexml.side.workflow.Decision} instances.
	 * 
	 * @generated
	 */
	private DecisionModelerProvider decisionModelerProvider;

	/**
	 * This keeps track of the one adapter used for all {@link com.bluexml.side.workflow.Event} instances.
	 * 
	 * @generated
	 */
	private EventModelerProvider eventModelerProvider;

	/**
	 * This keeps track of the one adapter used for all {@link com.bluexml.side.workflow.Action} instances.
	 * 
	 * @generated
	 */
	private ActionModelerProvider actionModelerProvider;

	/**
	 * This keeps track of the one adapter used for all {@link com.bluexml.side.workflow.Script} instances.
	 * 
	 * @generated
	 */
	private ScriptModelerProvider scriptModelerProvider;

	/**
	 * This keeps track of the one adapter used for all {@link com.bluexml.side.workflow.Timer} instances.
	 * 
	 * @generated
	 */
	private TimerModelerProvider timerModelerProvider;

	/**
	 * This keeps track of the one adapter used for all {@link com.bluexml.side.workflow.Variable} instances.
	 * 
	 * @generated
	 */
	private VariableModelerProvider variableModelerProvider;

	/**
	 * This keeps track of the one adapter used for all {@link com.bluexml.side.workflow.Transition} instances.
	 * 
	 * @generated
	 */
	private TransitionModelerProvider transitionModelerProvider;

	/**
	 * This keeps track of the one adapter used for all {@link com.bluexml.side.workflow.State} instances.
	 * 
	 * @generated
	 */
	private StateModelerProvider stateModelerProvider;

	/**
	 * This keeps track of the one adapter used for all {@link com.bluexml.side.workflow.Attribute} instances.
	 * 
	 * @generated
	 */
	private AttributeModelerProvider attributeModelerProvider;

	/**
	 * This constructs an instance.
	 * 
	 * @generated
	 */
	public WorkflowModelerProviderAdapterFactory() {
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
	 * This creates an adapter for a {@link com.bluexml.side.workflow.WorkflowModelElement}.
	 *
	 * @return the Adapter
	 * @generated
	 */
	public Adapter createWorkflowModelElementAdapter() {
		if (workflowmodelelementModelerProvider == null) {
			workflowmodelelementModelerProvider = new WorkflowModelElementModelerProvider(
					this);
		}

		return workflowmodelelementModelerProvider;
	}

	/**
	 * This creates an adapter for a {@link com.bluexml.side.workflow.Process}.
	 *
	 * @return the Adapter
	 * @generated
	 */
	public Adapter createProcessAdapter() {
		if (processModelerProvider == null) {
			processModelerProvider = new ProcessModelerProvider(this);
		}

		return processModelerProvider;
	}

	/**
	 * This creates an adapter for a {@link com.bluexml.side.workflow.Swimlane}.
	 *
	 * @return the Adapter
	 * @generated
	 */
	public Adapter createSwimlaneAdapter() {
		if (swimlaneModelerProvider == null) {
			swimlaneModelerProvider = new SwimlaneModelerProvider(this);
		}

		return swimlaneModelerProvider;
	}

	/**
	 * This creates an adapter for a {@link com.bluexml.side.workflow.StartState}.
	 *
	 * @return the Adapter
	 * @generated
	 */
	public Adapter createStartStateAdapter() {
		if (startstateModelerProvider == null) {
			startstateModelerProvider = new StartStateModelerProvider(this);
		}

		return startstateModelerProvider;
	}

	/**
	 * This creates an adapter for a {@link com.bluexml.side.workflow.EndState}.
	 *
	 * @return the Adapter
	 * @generated
	 */
	public Adapter createEndStateAdapter() {
		if (endstateModelerProvider == null) {
			endstateModelerProvider = new EndStateModelerProvider(this);
		}

		return endstateModelerProvider;
	}

	/**
	 * This creates an adapter for a {@link com.bluexml.side.workflow.Node}.
	 *
	 * @return the Adapter
	 * @generated
	 */
	public Adapter createNodeAdapter() {
		if (nodeModelerProvider == null) {
			nodeModelerProvider = new NodeModelerProvider(this);
		}

		return nodeModelerProvider;
	}

	/**
	 * This creates an adapter for a {@link com.bluexml.side.workflow.TaskNode}.
	 *
	 * @return the Adapter
	 * @generated
	 */
	public Adapter createTaskNodeAdapter() {
		if (tasknodeModelerProvider == null) {
			tasknodeModelerProvider = new TaskNodeModelerProvider(this);
		}

		return tasknodeModelerProvider;
	}

	/**
	 * This creates an adapter for a {@link com.bluexml.side.workflow.ProcessState}.
	 *
	 * @return the Adapter
	 * @generated
	 */
	public Adapter createProcessStateAdapter() {
		if (processstateModelerProvider == null) {
			processstateModelerProvider = new ProcessStateModelerProvider(this);
		}

		return processstateModelerProvider;
	}

	/**
	 * This creates an adapter for a {@link com.bluexml.side.workflow.Fork}.
	 *
	 * @return the Adapter
	 * @generated
	 */
	public Adapter createForkAdapter() {
		if (forkModelerProvider == null) {
			forkModelerProvider = new ForkModelerProvider(this);
		}

		return forkModelerProvider;
	}

	/**
	 * This creates an adapter for a {@link com.bluexml.side.workflow.Join}.
	 *
	 * @return the Adapter
	 * @generated
	 */
	public Adapter createJoinAdapter() {
		if (joinModelerProvider == null) {
			joinModelerProvider = new JoinModelerProvider(this);
		}

		return joinModelerProvider;
	}

	/**
	 * This creates an adapter for a {@link com.bluexml.side.workflow.Decision}.
	 *
	 * @return the Adapter
	 * @generated
	 */
	public Adapter createDecisionAdapter() {
		if (decisionModelerProvider == null) {
			decisionModelerProvider = new DecisionModelerProvider(this);
		}

		return decisionModelerProvider;
	}

	/**
	 * This creates an adapter for a {@link com.bluexml.side.workflow.Event}.
	 *
	 * @return the Adapter
	 * @generated
	 */
	public Adapter createEventAdapter() {
		if (eventModelerProvider == null) {
			eventModelerProvider = new EventModelerProvider(this);
		}

		return eventModelerProvider;
	}

	/**
	 * This creates an adapter for a {@link com.bluexml.side.workflow.Action}.
	 *
	 * @return the Adapter
	 * @generated
	 */
	public Adapter createActionAdapter() {
		if (actionModelerProvider == null) {
			actionModelerProvider = new ActionModelerProvider(this);
		}

		return actionModelerProvider;
	}

	/**
	 * This creates an adapter for a {@link com.bluexml.side.workflow.Script}.
	 *
	 * @return the Adapter
	 * @generated
	 */
	public Adapter createScriptAdapter() {
		if (scriptModelerProvider == null) {
			scriptModelerProvider = new ScriptModelerProvider(this);
		}

		return scriptModelerProvider;
	}

	/**
	 * This creates an adapter for a {@link com.bluexml.side.workflow.Timer}.
	 *
	 * @return the Adapter
	 * @generated
	 */
	public Adapter createTimerAdapter() {
		if (timerModelerProvider == null) {
			timerModelerProvider = new TimerModelerProvider(this);
		}

		return timerModelerProvider;
	}

	/**
	 * This creates an adapter for a {@link com.bluexml.side.workflow.Variable}.
	 *
	 * @return the Adapter
	 * @generated
	 */
	public Adapter createVariableAdapter() {
		if (variableModelerProvider == null) {
			variableModelerProvider = new VariableModelerProvider(this);
		}

		return variableModelerProvider;
	}

	/**
	 * This creates an adapter for a {@link com.bluexml.side.workflow.Transition}.
	 *
	 * @return the Adapter
	 * @generated
	 */
	public Adapter createTransitionAdapter() {
		if (transitionModelerProvider == null) {
			transitionModelerProvider = new TransitionModelerProvider(this);
		}

		return transitionModelerProvider;
	}

	/**
	 * This creates an adapter for a {@link com.bluexml.side.workflow.State}.
	 *
	 * @return the Adapter
	 * @generated
	 */
	public Adapter createStateAdapter() {
		if (stateModelerProvider == null) {
			stateModelerProvider = new StateModelerProvider(this);
		}

		return stateModelerProvider;
	}

	/**
	 * This creates an adapter for a {@link com.bluexml.side.workflow.Attribute}.
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
	 * This disposes all of the item providers created by this factory.
	 * 
	 * @generated
	 */
	public void dispose() {
		if (workflowmodelelementModelerProvider != null) {
			workflowmodelelementModelerProvider.dispose();
		}
		if (processModelerProvider != null) {
			processModelerProvider.dispose();
		}
		if (swimlaneModelerProvider != null) {
			swimlaneModelerProvider.dispose();
		}
		if (startstateModelerProvider != null) {
			startstateModelerProvider.dispose();
		}
		if (endstateModelerProvider != null) {
			endstateModelerProvider.dispose();
		}
		if (nodeModelerProvider != null) {
			nodeModelerProvider.dispose();
		}
		if (tasknodeModelerProvider != null) {
			tasknodeModelerProvider.dispose();
		}
		if (processstateModelerProvider != null) {
			processstateModelerProvider.dispose();
		}
		if (forkModelerProvider != null) {
			forkModelerProvider.dispose();
		}
		if (joinModelerProvider != null) {
			joinModelerProvider.dispose();
		}
		if (decisionModelerProvider != null) {
			decisionModelerProvider.dispose();
		}
		if (eventModelerProvider != null) {
			eventModelerProvider.dispose();
		}
		if (actionModelerProvider != null) {
			actionModelerProvider.dispose();
		}
		if (scriptModelerProvider != null) {
			scriptModelerProvider.dispose();
		}
		if (timerModelerProvider != null) {
			timerModelerProvider.dispose();
		}
		if (variableModelerProvider != null) {
			variableModelerProvider.dispose();
		}
		if (transitionModelerProvider != null) {
			transitionModelerProvider.dispose();
		}
		if (stateModelerProvider != null) {
			stateModelerProvider.dispose();
		}
		if (attributeModelerProvider != null) {
			attributeModelerProvider.dispose();
		}
	}

}
