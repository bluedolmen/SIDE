package com.bluexml.side.Requirements.modeler.goalDiagram.figures;

import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.ImageFigure;
import org.eclipse.draw2d.MarginBorder;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.ToolbarLayout;
import org.eclipse.swt.graphics.Image;
import org.topcased.draw2d.figures.ComposedLabel;
import org.topcased.draw2d.figures.EditableLabel;
import org.topcased.draw2d.figures.ILabel;
import org.topcased.draw2d.figures.ILabelFigure;

public class ImageWithLabelFigure extends Figure implements ILabelFigure
{
    protected ILabel label;

    protected ImageFigure imageFigure;
    
    protected int position;

	private ToolbarLayout layout;

    /**
     * Empty constructor. Use a default Image and create the label at the bottom of the image
     */
    public ImageWithLabelFigure()
    {
        this(null, PositionConstants.BOTTOM);
    }

    /**
     * Constructor - Create the label at the bottom of the image figure.
     * 
     * @param image the Image to draw as the main figure
     */
    public ImageWithLabelFigure(Image image)
    {
        this(image, PositionConstants.BOTTOM);
    }
    
    /**
     * Constructor. Creates the contents of the figure : by defauft, it creates a layout manager, a label and an image
     * 
     * @param image the Image to draw as the main figure
     * @param labelPosition the label position (from {@link PositionConstants} : PositionConstants.TOP,
     *        PositionConstants.BOTTOM, PositionConstants.LEFT or PositionConstants.RIGHT)
     */
    public ImageWithLabelFigure(Image image, int labelPosition)
    {
        layout = new ToolbarLayout();
        layout.setStretchMinorAxis(false);
        layout.setMinorAlignment(ToolbarLayout.ALIGN_CENTER);

        // Create the label and the body Figures
        imageFigure = new ImageFigure(image);
        label = createLabel();      
        switch (labelPosition)
        {
            case PositionConstants.LEFT:
                layout.setVertical(false);
                add(label);
                add(imageFigure);
                break;
            case PositionConstants.TOP:
                add(label);
                add(imageFigure);
                break;
            case PositionConstants.RIGHT:
                layout.setVertical(false);
                add(imageFigure);
                add(label);
                break;
            case PositionConstants.BOTTOM:
            default:
                add(imageFigure);
                add(label);
                break;
        }

        setLayoutManager(layout);
    }

    /**
     * Creates the label of the figure.<br>
     * <b>Subclasses can override this method to customize the appearance of the label (for example you can return a
     * {@link ComposedLabel} instead)</b>
     * 
     * @return the label of the figure
     */
    protected ILabel createLabel()
    {
        EditableLabel lbl = new EditableLabel();
        lbl.setTextAlignment(PositionConstants.LEFT);
        //lbl.setAlignment(PositionConstants.LEFT);
        lbl.setBorder(new MarginBorder(5));
        return lbl;
    }

    /**
     * @see org.topcased.draw2d.figures.ILabelFigure#getLabel()
     */
    public ILabel getLabel()
    {
        return label;
    }

    /**
     * Return the image figure.
     * 
     * @return ImageFigure the image figure
     */
    public ImageFigure getImageFigure()
    {
        return imageFigure;
    }

	public void setImageFigure(Image image, int position) {
		this.imageFigure = new ImageFigure(image);
		removeAll();
        switch (position)
        {
            case PositionConstants.LEFT:
                layout.setVertical(false);
                add(label);
                add(imageFigure);
                break;
            case PositionConstants.TOP:
                add(label);
                add(imageFigure);
                break;
            case PositionConstants.RIGHT:
                layout.setVertical(false);
                add(imageFigure);
                add(label);
                break;
            case PositionConstants.BOTTOM:
            default:
                add(imageFigure);
                add(label);
                break;
        }

	}
	
	@Override
	public void repaint() {
		super.repaint();
		label.repaint();
		imageFigure.repaint();
	}
}
