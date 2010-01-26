package com.bluexml.side.Requirements.modeler.views.internal;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.SafeRunner;
import org.eclipse.jface.util.SafeRunnable;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;

/**
 * MarkerSelectionProviderAdapter adapts the concrete markers
 * to IMarkers for contributions.
 */
class MarkerSelectionProviderAdapter implements ISelectionProvider {

    List listeners = new ArrayList();

    ISelection theSelection = null;

    public void addSelectionChangedListener(ISelectionChangedListener listener) {
        listeners.add(listener);
    }

    public ISelection getSelection() {
        return theSelection;
    }

    public void removeSelectionChangedListener(
            ISelectionChangedListener listener) {
        listeners.remove(listener);
    }

    public void setSelection(ISelection selection) {
        theSelection = selection;
        final SelectionChangedEvent e = new SelectionChangedEvent(this, selection);
        Object[] listenersArray = listeners.toArray();
        
        for (int i = 0; i < listenersArray.length; i++) {
            final ISelectionChangedListener l = (ISelectionChangedListener) listenersArray[i];
            SafeRunner.run(new SafeRunnable() {
                public void run() {
                    l.selectionChanged(e);
                }
            });
		}
    }

}
