package com.bluexml.side.Portal.modeler.diagram.dialogs.viewer.labelProvider;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;

import com.bluexml.side.Portal.modeler.diagram.dialogs.dataStructure.PortletTypeAttributeDataStructure.PortletTypeAttributeObject;
import com.bluexml.side.Portal.modeler.diagram.dialogs.viewer.PortletTypeAttributeViewer;

/**
 * Internal class to handle modification
 */
public class AttributeLabelProvider extends LabelProvider implements ITableLabelProvider {
	
	// Names of images used to represent checkboxes
	public static final String CHECKED_IMAGE 	= "check";
	public static final String UNCHECKED_IMAGE  = "uncheck";
	
	// For the checkbox images
	private static ImageRegistry imageRegistry = new ImageRegistry();
	
	static {
		String iconPath = "icons/"; 
		imageRegistry.put(CHECKED_IMAGE, ImageDescriptor.createFromFile(
				PortletTypeAttributeViewer.class, 
				iconPath + CHECKED_IMAGE + ".gif"
				)
			);
		imageRegistry.put(UNCHECKED_IMAGE, ImageDescriptor.createFromFile(
				PortletTypeAttributeViewer.class, 
				iconPath + UNCHECKED_IMAGE + ".gif"
				)
			);	
	}
	
	/**
	 * @see org.eclipse.jface.viewers.ITableLabelProvider#getColumnImage(java.lang.Object,
	 *      int)
	 */
	public Image getColumnImage(Object element, int columnIndex) {		
		Image result = null;				
		switch (columnIndex) {
			case 2:
				result = getImage(((PortletTypeAttributeObject) element).isRequired()) ;
				break;
			case 3: 
				result = getImage(((PortletTypeAttributeObject) element).isMultiValued()) ; 
	           break;
			default:
				break;
				
		}
		return result;
	}
	
    public Image getImage( Object element )
    {
        if ( element instanceof PortletTypeAttributeObject )
        {	
        	PortletTypeAttributeObject attr = (PortletTypeAttributeObject) element;
            return  getImage(attr.isRequired());
        }
        // Default
        return null;
    }

    
    public Image getImage(boolean checked) {
    	if (checked) {
    		return imageRegistry.get(CHECKED_IMAGE);
    	} else {
    		return imageRegistry.get(UNCHECKED_IMAGE);
    	}
    }

	/**
	 * @see org.eclipse.jface.viewers.ITableLabelProvider#getColumnText(java.lang.Object,
	 *      int)
	 */
	public String getColumnText(Object element, int columnIndex) {
		String result = "";
		switch (columnIndex) {
		case 0:
			result = ((PortletTypeAttributeObject) element).getName();
			break;
		case 1:
			result = ((PortletTypeAttributeObject) element).getTypeName();
			break;
		case 2:
			
			break;
		case 3:			
			break;
		default:
			break;
		}
		return result;
	}
}