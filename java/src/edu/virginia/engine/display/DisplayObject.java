package edu.virginia.engine.display;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.awt.geom.Area;

import javax.imageio.ImageIO;

/**
 * A very basic display object for a java based gaming engine
 *
 *
 *  rotate then translate
 * */
public class DisplayObject {

    /*
     The root of a display tree will always be a DisplayObjectContainer
     */
	private DisplayObject parentObject;

	/* All DisplayObject have a unique id */
	private String id;

	/* The image that is displayed by this object */
	private BufferedImage displayImage;

	/* Lab 1 part 1 */
	protected Point position;
	private Point pivotPoint;
	private float rotation;

	/* Lab 1 part 2*/
	public Boolean visible;
	public Float alpha;
	public Float oldAlpha;
	public Double scaleX;
	public Double scaleY;

	/* Lab 4 */
	private Shape hitbox;



	private int visibleHelper;

	// Use of AffineTransform sourced from
	// http://www.java-gaming.org/index.php/topic,25177
	private AffineTransform old;


	/**
	 * Constructors: can pass in the id OR the id and image's file path and
	 * position OR the id and a buffered image and position
	 */
	public DisplayObject(String id) {

		this.setId(id);
		init();
	}

	public DisplayObject(String id, String fileName) {
		this.setId(id);
		this.setImage(fileName);
		init();
	}

	private void init() {
	    this.position = new Point(0,0);
	    this.pivotPoint = new Point(0,0);
	    this.rotation = 0.0f;
        this.visible = true;
        this.alpha = 1.0f;
        this.oldAlpha = 0.0f;
        this.scaleX = 1.0;
        this.scaleY = 1.0;
        this.visibleHelper = 1;

	}

	public void initializeRectangleHitbox() {
	    this.hitbox = new Rectangle(this.getPosition().x, this.getPosition().y,
				this.getUnscaledWidth(), this.getUnscaledHeight());

	    System.out.println(this.hitbox.getBounds());
    }
	
	public Boolean getVisible() {
		return this.visible;
	}

	public Float getAlpha() {
		return alpha;
	}

	public Float getOldAlpha() {
		return oldAlpha;
	}

	public Double getScaleX() {
		return scaleX;
	}

	public Double getScaleY() {
		return scaleY;
	}

	public void setVisible(Boolean visible) {
		this.visible = visible;
		if (visible) {
		    this.setAlpha(1.0f);
        } else {
		    this.setAlpha(0.0f);
        }
	}

	public void setAlpha(Float alpha) {
		this.alpha = alpha;
	}

	public void setOldAlpha(Float oldAlpha) {
		this.oldAlpha = oldAlpha;
	}

	public void setScaleX(Double scaleX) {
		this.scaleX = scaleX;
	}

	public void setScaleY(Double scaleY) {
	    this.scaleY = scaleY;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getId() {
	    return id;
	}

	public Point getPosition() {
	    return position;
	}

	public Point getPivotPoint() { return pivotPoint; }

	public float getRotation() {
	    return rotation;
	}

	public void setPosition(Point position) {
	    this.position = position;
	}

	public void setPivotPoint(Point pivotPoint) {
	    this.pivotPoint = pivotPoint;
	}

	public void setRotation(float rotation) {
	    this.rotation = rotation;
	}

	/**
	 * Convert local points to global points and global points to local points.
	 * */

	public Point localToGlobal(Point localPoint) {
        Integer x = localPoint.x;
        Integer y = localPoint.y;
        DisplayObject tempObject = this;
        while(tempObject.parentObject != null) {
            tempObject = tempObject.parentObject;
            Point tempPoint = new Point(tempObject.getPosition());
            x += tempPoint.x;
            y += tempPoint.y;
        }
        return new Point(x, y);
    }

    public Point globalToLocal(Point globalPoint) {
	    Integer x = globalPoint.x;
	    Integer y = globalPoint.y;
        DisplayObject tempObject = this;
        while(tempObject.parentObject != null) {
            tempObject = tempObject.parentObject;
            Point tempPoint = new Point(tempObject.getPosition());
            x -= tempPoint.x;
            y -= tempPoint.y;
        }
        return new Point(x, y);
    }

    public void setParentObject(DisplayObject parentObject) {
	    this.parentObject = parentObject;
    }

    public void removeParentObject() {
	    this.parentObject = null;
    }

    public Shape getHitbox() {
		return this.hitbox;
	}


	public boolean collidesWith(DisplayObject other) {
	    Area thisHitbox = new Area(this.hitbox);
	    Area otherHitbox = new Area(other.hitbox);
	    thisHitbox.intersect(otherHitbox);
	    return !thisHitbox.isEmpty();
	}

	//Translation
	public void updateHitbox(int translateX, int translateY) {
		AffineTransform ht = new AffineTransform();
		ht.setToTranslation(translateX, translateY);
		this.hitbox = ht.createTransformedShape(this.getHitbox());
		System.out.println(this.hitbox.getBounds());
	}

	//Rotation
	public void updateHitbox(float rotationValue) {
		AffineTransform ht = new AffineTransform();
		ht.setToRotation(Math.toRadians(rotationValue), this.getPivotPoint().x, this.getPivotPoint().y);
		this.hitbox = ht.createTransformedShape(this.getHitbox());
	}

	//Scaling
	public void updateHitbox(double scale) {
		AffineTransform ht = new AffineTransform();
		ht.setToScale(this.getScaleX() + scale, this.getScaleY() + scale);
		this.hitbox = ht.createTransformedShape(this.getHitbox());
	}





	/**
	 * Returns the unscaled width and height of this display object
	 * */
	public int getUnscaledWidth() {
		if(displayImage == null) return 0;
		return displayImage.getWidth();
	}

	public int getUnscaledHeight() {
		if(displayImage == null) return 0;
		return displayImage.getHeight();
	}

	public BufferedImage getDisplayImage() {
		return this.displayImage;
	}

	protected void setImage(String imageName) {
		if (imageName == null) {
			return;
		}
		displayImage = readImage(imageName);
		if (displayImage == null) {
			System.err.println("[DisplayObject.setImage] ERROR: " + imageName + " does not exist!");
		}
	}


	/**
	 * Helper function that simply reads an image from the given image name
	 * (looks in resources\\) and returns the bufferedimage for that filename
	 * */
	public BufferedImage readImage(String imageName) {
		BufferedImage image = null;
		try {
			String file = ("resources" + File.separator + imageName);
			image = ImageIO.read(new File(file));
		} catch (IOException e) {
			System.out.println("[Error in DisplayObject.java:readImage] Could not read image " + imageName);
			e.printStackTrace();
		}
		//System.out.println(imageName + "height: " + image.getHeight() + " width: " + image.getWidth());
		return image;
	}

	public void setImage(BufferedImage image) {
		if(image == null) return;
		displayImage = image;
	}

	

	/**
	 * Invoked on every frame before drawing. Used to update this display
	 * objects state before the draw occurs. Should be overridden if necessary
	 * to update objects appropriately.
	 * */
	protected void update(ArrayList<Integer> pressedKeys) {
		
	}

	/**
	 * Draws this image. This should be overloaded if a display object should
	 * draw to the screen differently. This method is automatically invoked on
	 * every frame.
	 * */
	public void draw(Graphics g) {
		
		if (displayImage != null) {
			
			/*
			 * Get the graphics and apply this objects transformations
			 * (rotation, etc.)
			 */
			Graphics2D g2d = (Graphics2D) g;

			applyTransformations(g2d);

			/* Actually draw the image, perform the pivot point translation here */
			g2d.drawImage(displayImage, 0, 0,
					(int) (getUnscaledWidth()),
					(int) (getUnscaledHeight()), null);
			if (this.hitbox != null)
				g2d.draw(this.hitbox);


			/*
			 * undo the transformations so this doesn't affect other display
			 * objects
			 */
			reverseTransformations(g2d);


		}
	}

	/**
	 * Applies transformations for this display object to the given graphics
	 * object
	 * */
	protected void applyTransformations(Graphics2D g2d) {
        old = g2d.getTransform();
	    g2d.translate(this.position.x, this.position.y);
	    g2d.rotate(Math.toRadians(this.getRotation()), this.getPivotPoint().x, this.getPivotPoint().y);
		g2d.scale(this.scaleX, this.scaleY);
		float curAlpha;
		this.oldAlpha = curAlpha = ((AlphaComposite)
				g2d.getComposite()).getAlpha();
		g2d.setComposite(AlphaComposite.getInstance(3, curAlpha *
				this.alpha));
	}

	/**
	 * Reverses transformations for this display object to the given graphics
	 * object
	 * */
	protected void reverseTransformations(Graphics2D g2d){
	    g2d.setTransform(old);
	}

}
