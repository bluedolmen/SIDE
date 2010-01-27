package com.bluexml.side.Util.ecore;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.draw2d.IFigure;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.GraphicalViewer;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;
import org.eclipse.gef.editparts.ScalableFreeformRootEditPart;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.ide.IDE;
import org.topcased.modeler.di.model.Diagram;
import org.topcased.modeler.di.model.internal.impl.EMFSemanticModelBridgeImpl;
import org.topcased.modeler.diagrams.model.util.DiagramsUtils;
import org.topcased.modeler.editor.Modeler;
import org.topcased.modeler.export.ExporterDescriptor;
import org.topcased.modeler.export.ExporterManager;

public abstract class DiagramImageExporter {

	private static String lastExtension;
	private List<String> fileNames = new ArrayList<String>();

	/**
	 *
	 * @param f
	 * @param selectedDirectory
	 * @return
	 */
	public boolean exportFile(IFile f, String selectedDirectory) {
		final IFile _f = f;
		final String _selectedDirectory = selectedDirectory;
		final Object[] result = new Object[1];
		
		Display.getDefault().syncExec(new Runnable() {
			
		    public void run() {
		    	result[0] = false;
		    	IEditorPart editorPart = null;
		    	try {
		    		editorPart = IDE.openEditor(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage(), _f, true);
		    	} catch (PartInitException e) {
		    		e.printStackTrace();
		    		result[0] = true; // An error occurred during the opening of the graphical editor
		    	}
		    	if (editorPart != null && editorPart instanceof Modeler) {
		    		Modeler editor = (Modeler) editorPart;
		    		GraphicalViewer viewer = (GraphicalViewer) editor
		    		.getAdapter(GraphicalViewer.class);
		    		
		    		List<Diagram> allDiagrams = DiagramsUtils.findAllDiagrams(editor
		    				.getDiagrams());
		    		for (Diagram d : allDiagrams) {
		    			editor.setActiveDiagram(d);
		    			viewer.flush();
		    			
		    			IFigure figure = ((AbstractGraphicalEditPart) viewer
		    					.getRootEditPart()).getFigure();
		    			// Remove selection handles
		    			((EditPart) ((AbstractGraphicalEditPart) viewer
		    					.getRootEditPart()).getChildren().get(0))
		    					.setSelected(EditPart.SELECTED_NONE);
		    			// Store initial zoom value
		    			double initialZoomValue = d.getZoom();
		    			// Enforce zoom level as 100%
		    			((ScalableFreeformRootEditPart) viewer.getRootEditPart())
		    			.getZoomManager().setZoom(1.0);
		    			
		    			FileOutputStream fos = null;
		    			try {
		    				File file = getFile(_f, _selectedDirectory, d.getName());
		    				fos = new FileOutputStream(file);
		    				
		    				ExporterDescriptor descriptor = getExporter(file);
		    				if (descriptor != null) {
		    					descriptor.getExporter().export(figure, fos);
		    					
		    					// Remember values
		    					lastExtension = descriptor.getExtension();
		    				}
		    				
		    				if (d.getSemanticModel() instanceof EMFSemanticModelBridgeImpl)
		    					executeAction(file, ((EMFSemanticModelBridgeImpl) d
		    							.getSemanticModel()).getElement());
		    				
		    				fileNames.add(file.getName());
		    			} catch (Exception e) {
		    				result[0] = true;
		    				e.printStackTrace();
		    			} finally {
		    				try {
		    					if (fos != null) {
		    						fos.close();
		    						
		    					}
		    				} catch (IOException ioe) {
		    					result[0] = true;
		    					ioe.printStackTrace();
		    				}
		    				// Restore initial zool level
		    				d.setZoom(initialZoomValue);
		    			}
		    			
		    		}
		    		
		    		/*
			ep = page.findEditor(input);
			page.closeEditor(ep, true);
		    		 */
		    	}
		    }
		});

		return Boolean.valueOf(result[0].toString());
	}

	public List<String> getFileNames() {
		return fileNames;
	}

	protected static String[] getExtensions() {
		ExporterDescriptor[] descriptors = ExporterManager.getInstance()
				.getExporters();
		String[] extensions = new String[descriptors.length];

		for (int i = 0; i < descriptors.length; i++) {
			ExporterDescriptor descriptor = descriptors[i];

			extensions[i] = "*." + descriptor.getExtension();
		}

		// Set the last extension first
		if (lastExtension != null) {
			String last = "*." + lastExtension;
			boolean done = false;
			for (int j = 0; j < extensions.length && !done; j++) {
				if (last.equals(extensions[j])) {
					extensions[j] = extensions[0];
					extensions[0] = last;
					done = true;
				}
			}
		}

		return extensions;
	}

	protected ExporterDescriptor getExporter(File f) {
		ExporterDescriptor foundExporter = null;
		ExporterDescriptor[] descriptors = ExporterManager.getInstance()
				.getExporters();

		for (int i = 0; i < descriptors.length && foundExporter == null; i++) {
			if (f.getName().endsWith(descriptors[i].getExtension())) {
				foundExporter = descriptors[i];
			}
		}
		return foundExporter;
	}

	private File getFile(IFile obldiFile, String selectedDirectory,
			String nameDiagram) {
		return new File(selectedDirectory + obldiFile.getName().replaceAll("\\.", "_") + "_"
				+ nameDiagram.replaceAll("\\.", "_") + "." + getExtension());
	}

	protected abstract String getExtension();



	protected void executeAction(File file, EObject object) {

	}
}
