/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.bluexml.side.workflow;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>BPM Event Type</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * <!-- begin-model-doc -->
 * Definition: The enumeration 'BPMEventType' defines all possible value for the type of an event.
 * <!-- end-model-doc -->
 * @see com.bluexml.side.workflow.WorkflowPackage#getBPMEventType()
 * @model
 * @generated
 */
public enum BPMEventType implements Enumerator {
	/**
	 * The '<em><b>Task create</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #TASK_CREATE_VALUE
	 * @generated
	 * @ordered
	 */
	TASK_CREATE(0, "task_create", "task-create"),

	/**
	 * The '<em><b>Task start</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #TASK_START_VALUE
	 * @generated
	 * @ordered
	 */
	TASK_START(1, "task_start", "task-start"),

	/**
	 * The '<em><b>Task assign</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #TASK_ASSIGN_VALUE
	 * @generated
	 * @ordered
	 */
	TASK_ASSIGN(2, "task_assign", "task-assign"),

	/**
	 * The '<em><b>Task end</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #TASK_END_VALUE
	 * @generated
	 * @ordered
	 */
	TASK_END(3, "task_end", "task-end"),

	/**
	 * The '<em><b>Node enter</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #NODE_ENTER_VALUE
	 * @generated
	 * @ordered
	 */
	NODE_ENTER(4, "node_enter", "node-enter"),

	/**
	 * The '<em><b>Node leave</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #NODE_LEAVE_VALUE
	 * @generated
	 * @ordered
	 */
	NODE_LEAVE(5, "node_leave", "node-leave"),

	/**
	 * The '<em><b>Before signal</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #BEFORE_SIGNAL_VALUE
	 * @generated
	 * @ordered
	 */
	BEFORE_SIGNAL(6, "before_signal", "before-signal"),

	/**
	 * The '<em><b>After signal</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #AFTER_SIGNAL_VALUE
	 * @generated
	 * @ordered
	 */
	AFTER_SIGNAL(7, "after_signal", "after-signal"),

	/**
	 * The '<em><b>Superstate enter</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #SUPERSTATE_ENTER_VALUE
	 * @generated
	 * @ordered
	 */
	SUPERSTATE_ENTER(8, "superstate_enter", "superstate-enter"),

	/**
	 * The '<em><b>Superstate leave</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #SUPERSTATE_LEAVE_VALUE
	 * @generated
	 * @ordered
	 */
	SUPERSTATE_LEAVE(9, "superstate_leave", "superstate-leave"),

	/**
	 * The '<em><b>Transition</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #TRANSITION_VALUE
	 * @generated
	 * @ordered
	 */
	TRANSITION(10, "transition", "transition"),

	/**
	 * The '<em><b>Timer</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #TIMER_VALUE
	 * @generated
	 * @ordered
	 */
	TIMER(11, "timer", "timer"),

	/**
	 * The '<em><b>Subprocess created</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #SUBPROCESS_CREATED_VALUE
	 * @generated
	 * @ordered
	 */
	SUBPROCESS_CREATED(12, "subprocess_created", "subprocess-created"),

	/**
	 * The '<em><b>Subprocess end</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #SUBPROCESS_END_VALUE
	 * @generated
	 * @ordered
	 */
	SUBPROCESS_END(13, "subprocess_end", "subprocess-end"),

	/**
	 * The '<em><b>Process start</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #PROCESS_START_VALUE
	 * @generated
	 * @ordered
	 */
	PROCESS_START(14, "process_start", "process-start"),

	/**
	 * The '<em><b>Process end</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #PROCESS_END_VALUE
	 * @generated
	 * @ordered
	 */
	PROCESS_END(15, "process_end", "process-end");

	/**
	 * The '<em><b>Task create</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Task create</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Definition: The event is launched at the creation of a task.
	 * <!-- end-model-doc -->
	 * @see #TASK_CREATE
	 * @model name="task_create" literal="task-create"
	 * @generated
	 * @ordered
	 */
	public static final int TASK_CREATE_VALUE = 0;

	/**
	 * The '<em><b>Task start</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Task start</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Definition: The event is launched when the function TaskInstance.start() is called.
	 * <!-- end-model-doc -->
	 * @see #TASK_START
	 * @model name="task_start" literal="task-start"
	 * @generated
	 * @ordered
	 */
	public static final int TASK_START_VALUE = 1;

	/**
	 * The '<em><b>Task assign</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Task assign</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Definition: The event is launch when a task is being assigned to a swimlane.
	 * <!-- end-model-doc -->
	 * @see #TASK_ASSIGN
	 * @model name="task_assign" literal="task-assign"
	 * @generated
	 * @ordered
	 */
	public static final int TASK_ASSIGN_VALUE = 2;

	/**
	 * The '<em><b>Task end</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Task end</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Definition: The event is launch when the function TaskInstance.end() is called.
	 * <!-- end-model-doc -->
	 * @see #TASK_END
	 * @model name="task_end" literal="task-end"
	 * @generated
	 * @ordered
	 */
	public static final int TASK_END_VALUE = 3;

	/**
	 * The '<em><b>Node enter</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Node enter</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #NODE_ENTER
	 * @model name="node_enter" literal="node-enter"
	 * @generated
	 * @ordered
	 */
	public static final int NODE_ENTER_VALUE = 4;

	/**
	 * The '<em><b>Node leave</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Node leave</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #NODE_LEAVE
	 * @model name="node_leave" literal="node-leave"
	 * @generated
	 * @ordered
	 */
	public static final int NODE_LEAVE_VALUE = 5;

	/**
	 * The '<em><b>Before signal</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Before signal</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #BEFORE_SIGNAL
	 * @model name="before_signal" literal="before-signal"
	 * @generated
	 * @ordered
	 */
	public static final int BEFORE_SIGNAL_VALUE = 6;

	/**
	 * The '<em><b>After signal</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>After signal</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #AFTER_SIGNAL
	 * @model name="after_signal" literal="after-signal"
	 * @generated
	 * @ordered
	 */
	public static final int AFTER_SIGNAL_VALUE = 7;

	/**
	 * The '<em><b>Superstate enter</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Superstate enter</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Definition: The event is launched when entering a superstate.
	 * <!-- end-model-doc -->
	 * @see #SUPERSTATE_ENTER
	 * @model name="superstate_enter" literal="superstate-enter"
	 * @generated
	 * @ordered
	 */
	public static final int SUPERSTATE_ENTER_VALUE = 8;

	/**
	 * The '<em><b>Superstate leave</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Superstate leave</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Definition: The event is launched when leaving a superstate.
	 * <!-- end-model-doc -->
	 * @see #SUPERSTATE_LEAVE
	 * @model name="superstate_leave" literal="superstate-leave"
	 * @generated
	 * @ordered
	 */
	public static final int SUPERSTATE_LEAVE_VALUE = 9;

	/**
	 * The '<em><b>Transition</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Transition</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #TRANSITION
	 * @model name="transition"
	 * @generated
	 * @ordered
	 */
	public static final int TRANSITION_VALUE = 10;

	/**
	 * The '<em><b>Timer</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Timer</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #TIMER
	 * @model name="timer"
	 * @generated
	 * @ordered
	 */
	public static final int TIMER_VALUE = 11;

	/**
	 * The '<em><b>Subprocess created</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Subprocess created</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #SUBPROCESS_CREATED
	 * @model name="subprocess_created" literal="subprocess-created"
	 * @generated
	 * @ordered
	 */
	public static final int SUBPROCESS_CREATED_VALUE = 12;

	/**
	 * The '<em><b>Subprocess end</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Subprocess end</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #SUBPROCESS_END
	 * @model name="subprocess_end" literal="subprocess-end"
	 * @generated
	 * @ordered
	 */
	public static final int SUBPROCESS_END_VALUE = 13;

	/**
	 * The '<em><b>Process start</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Process start</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #PROCESS_START
	 * @model name="process_start" literal="process-start"
	 * @generated
	 * @ordered
	 */
	public static final int PROCESS_START_VALUE = 14;

	/**
	 * The '<em><b>Process end</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Process end</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #PROCESS_END
	 * @model name="process_end" literal="process-end"
	 * @generated
	 * @ordered
	 */
	public static final int PROCESS_END_VALUE = 15;

	/**
	 * An array of all the '<em><b>BPM Event Type</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final BPMEventType[] VALUES_ARRAY =
		new BPMEventType[] {
			TASK_CREATE,
			TASK_START,
			TASK_ASSIGN,
			TASK_END,
			NODE_ENTER,
			NODE_LEAVE,
			BEFORE_SIGNAL,
			AFTER_SIGNAL,
			SUPERSTATE_ENTER,
			SUPERSTATE_LEAVE,
			TRANSITION,
			TIMER,
			SUBPROCESS_CREATED,
			SUBPROCESS_END,
			PROCESS_START,
			PROCESS_END,
		};

	/**
	 * A public read-only list of all the '<em><b>BPM Event Type</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List<BPMEventType> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>BPM Event Type</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static BPMEventType get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			BPMEventType result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>BPM Event Type</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static BPMEventType getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			BPMEventType result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>BPM Event Type</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static BPMEventType get(int value) {
		switch (value) {
			case TASK_CREATE_VALUE: return TASK_CREATE;
			case TASK_START_VALUE: return TASK_START;
			case TASK_ASSIGN_VALUE: return TASK_ASSIGN;
			case TASK_END_VALUE: return TASK_END;
			case NODE_ENTER_VALUE: return NODE_ENTER;
			case NODE_LEAVE_VALUE: return NODE_LEAVE;
			case BEFORE_SIGNAL_VALUE: return BEFORE_SIGNAL;
			case AFTER_SIGNAL_VALUE: return AFTER_SIGNAL;
			case SUPERSTATE_ENTER_VALUE: return SUPERSTATE_ENTER;
			case SUPERSTATE_LEAVE_VALUE: return SUPERSTATE_LEAVE;
			case TRANSITION_VALUE: return TRANSITION;
			case TIMER_VALUE: return TIMER;
			case SUBPROCESS_CREATED_VALUE: return SUBPROCESS_CREATED;
			case SUBPROCESS_END_VALUE: return SUBPROCESS_END;
			case PROCESS_START_VALUE: return PROCESS_START;
			case PROCESS_END_VALUE: return PROCESS_END;
		}
		return null;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private final int value;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private final String name;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private final String literal;

	/**
	 * Only this class can construct instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private BPMEventType(int value, String name, String literal) {
		this.value = value;
		this.name = name;
		this.literal = literal;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getValue() {
	  return value;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getName() {
	  return name;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getLiteral() {
	  return literal;
	}

	/**
	 * Returns the literal value of the enumerator, which is its string representation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		return literal;
	}
	
} //BPMEventType
