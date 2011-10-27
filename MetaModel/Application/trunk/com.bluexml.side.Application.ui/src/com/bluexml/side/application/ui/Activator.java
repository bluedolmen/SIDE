package com.bluexml.side.application.ui;

import org.eclipse.core.runtime.Platform;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.BundleException;

import com.bluexml.side.Util.ecore.QuietModelModification;
import com.bluexml.side.util.libs.Messages;

/**
 * The activator class controls the plug-in life cycle
 */
public class Activator extends AbstractUIPlugin {

	// The plug-in ID
	public static final String PLUGIN_ID = "com.bluexml.side.Application.ui";

	// The shared instance
	private static Activator plugin;

	public static final Messages Messages = new Messages(PLUGIN_ID, "com.bluexml.side.application.ui.action.messages");

	private QuietModelModification quietModifs = new QuietModelModification();

	/**
	 * The constructor
	 */
	public Activator() {
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext
	 * )
	 */
	public void start(BundleContext context) throws Exception {
		super.start(context);
		plugin = this;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext
	 * )
	 */
	public void stop(BundleContext context) throws Exception {
		plugin = null;
		super.stop(context);
	}

	/**
	 * Returns the shared instance
	 * 
	 * @return the shared instance
	 */
	public static Activator getDefault() {
		
		System.out.println(PLUGIN_ID + " Activator state :");
		Bundle bundle = Platform.getBundle(PLUGIN_ID);
		int state = bundle.getState();
		if (state == Bundle.UNINSTALLED) {
			System.out.println("UNINSTALLED");
		} else if (state == Bundle.INSTALLED) {
			System.out.println("INSTALLED");
		} else if (state == Bundle.RESOLVED) {
			System.out.println("RESOLVED, try to start it");
			try {
				bundle.start();
			} catch (BundleException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (state == Bundle.STARTING) {
			System.out.println("STARTING");
		} else if (state == Bundle.STOPPING) {
			System.out.println("STOPPING");
		} else if (state == Bundle.ACTIVE) {
			System.out.println("ACTIVE");
		}
		
		
		return plugin;
	}

	public QuietModelModification getQuietModifs() {
		return quietModifs;
	}

}
