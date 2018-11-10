package edu.virginia.engine.display;

import java.awt.*;
import java.util.ArrayList;

public class DisplayObjectContainer extends DisplayObject {

    //Instance variable
    /* Because leaves are of type DisplayObject, this list should be a
       list of DisplayObjects. Only when we see we need to add children to a
       specific element in the children list, should we cast that element
       to a DisplayObjectContainer and utilize the methods below.
     */
    ArrayList<DisplayObject> children;

    public DisplayObjectContainer(String id) {
        super(id);
        children = new ArrayList<DisplayObject>();
    }
    public DisplayObjectContainer(String id, String fileName) {
        super(id, fileName);
        children = new ArrayList<DisplayObject>();
    }

    public void update(ArrayList<Integer> pressedKeys) {
        super.update(pressedKeys);

        for (DisplayObject child : children) {
            child.update(pressedKeys);
        }
    }

    public void draw(Graphics g) {
        super.draw(g);
        /*
         * Get the graphics and apply this objects transformations
         * (rotation, etc.)
         */
        Graphics2D g2d = (Graphics2D) g;
        applyTransformations(g2d);
        int len = children.size();
        for (int i = 0 ; i < len; i++) {
            children.get(i).draw(g);
        }
        reverseTransformations(g2d);


    }

    /* Display object children: add and remove */
    public void addChild(DisplayObject child) {
        child.setParentObject(this);
        children.add(child);
    }

    public void addChildAtIndex(DisplayObject child, Integer index) {
        child.setParentObject(this);
        children.add(index, child);
    }

    public void removeChild(String id) {
        int len = children.size();
        System.out.println("length: " + len);
        for (int i = 0 ; i < len; i++) {
            System.out.println("i " + i);
            DisplayObject currentChild = children.get(i);
            if (currentChild.getId().compareTo(id) == 0) {
                children.remove(i);
                return;
            }
        }
    }

    public void removeChildAtIndex(Integer index) {
        DisplayObject child = children.get(index);
        child.removeParentObject();
        children.remove(index);
    }

    public void removeAll() {
        children.removeAll(children);
    }

    public Boolean contains(DisplayObject child) {
        int len = children.size();
        for (int i = 0 ; i < len; i++) {
            DisplayObject currentChild = children.get(i);
            if (currentChild.getId().compareTo(child.getId()) == 0)
                return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    public ArrayList<DisplayObject> getChildren() {
        return children;
    }



}
