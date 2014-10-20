package homework07;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JViewport;

/**
 * This class builds the TreePanel used to draw the visual representation of a given .tree file
 * 
 * @author 	Basil Vetas
 * @date 	4/29/14 
 */
public class TreePanel extends JPanel implements MouseMotionListener, MouseListener, ActionListener 
{
	// instance variables
	private Tree root;
    private JScrollPane enclosingPane;
    private boolean sizeWasComputed = false;
    private int width, height, treeWidth, treeHeight;
    //private int lastMouseX, lastMouseY;
    
	/**
	 * Constructor method to build the pane for the Tree Visualizer. The application entry point.
	 * 
	 * @param args
	 */
	public TreePanel(Tree root) 
	{
		this.root = root; 	// initialize
	}

    /**
     * Allows caller to set an instance variable in this panel.
     * 
     * @param enclosingPane a JScrollPane object that is controlling the view of this panel
     */
    public void setEnclosingPane (JScrollPane pane)
    {
        enclosingPane = pane;
    }
    
    /**
     * The paint method for drawing our panel.  Note that because our
     * panel is huge, and the visible window is small, the graphics object
     * will be set to 'clip' any drawing that occurs outside of the current
     * drawing area.  (The current drawing area can be quite small.  If the
     * window is scrolled, only the newly exposed region needs to be drawn.)
     * 
     * @param g  Graphics object
     */
    public void paint(Graphics g)
    {
    	//Get coordinates of the paint-able region
        int x = g.getClipBounds().x;
        int y = g.getClipBounds().y;
        this.width = g.getClipBounds().width;
        this.height = g.getClipBounds().height;

        // Clear the background.
        g.setColor (Color.WHITE);
        g.fillRect(x, y, width, height);
        
        if(!sizeWasComputed)
        {
        	// gets the dimensions of the tree and resets the panel size
        	this.treeWidth = root.getWidth(g);		
        	this.treeHeight = root.getHeight(g);	
        	this.setMinimumSize(new Dimension(treeWidth, treeHeight));	
            this.setPreferredSize(new Dimension(treeWidth,treeHeight));
            
            // make view start at the root of the tree
            JViewport view = enclosingPane.getViewport();
            view.setViewSize(new Dimension(treeWidth, treeHeight));
            view.setViewPosition(new Point(x + treeWidth/2 - width/2 , y));
            
        	this.revalidate();
        	sizeWasComputed = true;
        }
        
        // draw the tree 
        g.setColor (Color.BLACK);
        if(root != null)
        {
        	root.drawEdges(g, 0, 0);	// draws edges - should be done before drawing nodes
        	root.drawNodes(g, 0, 0);	// draws nodes
        }
    }
	
    /* Mouse events used for debugging */
    public void mouseDragged (MouseEvent e){}
    public void mousePressed (MouseEvent e){}
    public void mouseClicked (MouseEvent e){}

    // Unused event handlers
    public void mouseMoved (MouseEvent e){}
    public void mouseEntered (MouseEvent e){}
    public void mouseExited (MouseEvent e){}
    public void mouseReleased (MouseEvent e){}

    /**
     * Resets the panel to the root of the tree
     */
	@Override
	public void actionPerformed(ActionEvent arg0) 
	{
		// Make view reset to the root of the tree
        JViewport view = enclosingPane.getViewport();
        view.setViewPosition(new Point(treeWidth/2 - width/2, 0));
        this.revalidate();
	}
}
