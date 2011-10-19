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
	private List<QuiteModelModificationListener> listeners = new ArrayList<QuiteModelModificationListener>();

	public void addListener(QuiteModelModificationListener l) {
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
		
		for (QuiteModelModificationListener l : listeners) {
			l.beforeAction();
		}
		System.out.println("QuiteModelModification.notifyBeforeModelModif()");
	}

	/**
	 * notify all listener about the AfterModification event
	 */
	public void notifyAfterModelModif() {
		for (QuiteModelModificationListener l : listeners) {
			l.afterAction();
		}
		System.out.println("QuiteModelModification.notifyAfterModelModif()");
	}

	public interface QuiteModelModificationListener {

		public void beforeAction();

		public void afterAction();

	}
}
