package com.bluexml.side.Util.ecore;

import java.util.ArrayList;
import java.util.List;

/**
 * designed to encapsulate action that change models whithout used notification.
 * this can be used to handle some behaviors affected by models changes.
 * this is originally designed to handle SIDE builder and opening
 * "manage configuration" dialog
 * so we can disable builder when dialog is opened and enable it after the
 * dialog close or after SIDE process
 * 
 * @author davidabad
 */
public class QuietModelModification {
	private List<QuietModelModificationListener> listeners = new ArrayList<QuietModelModificationListener>();

	public void addListener(QuietModelModificationListener l) {
		listeners.add(l);
	}

	/**
	 * could be used if action can be encapsulated in runnable
	 * @param r
	 */
	public void execute(Runnable r) {

		notifyAfterModelModif();

		r.run();

		notifyBeforeModelModif();

	}

	/**
	 * notify all listener about the BeforeModification event
	 */
	public void notifyBeforeModelModif() {
		
		for (QuietModelModificationListener l : listeners) {
			l.beforeAction();
		}
		System.out.println("QuietModelModification.notifyBeforeModelModif()");
	}

	/**
	 * notify all listener about the AfterModification event
	 */
	public void notifyAfterModelModif() {
		for (QuietModelModificationListener l : listeners) {
			l.afterAction();
		}
		System.out.println("QuietModelModification.notifyAfterModelModif()");
	}

	public interface QuietModelModificationListener {

		public void beforeAction();

		public void afterAction();

	}
}
