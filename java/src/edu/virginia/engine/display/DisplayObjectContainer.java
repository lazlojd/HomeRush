package edu.virginia.engine.display;

import java.awt.*;
import java.util.ArrayList;

public class DisplayObjectContainer extends DisplayObject {

    //Instance variable
    ArrayList<DisplayObject> children;

    public DisplayObjectContainer(String id) {
        super(id);
    }
    public DisplayObjectContainer(String id, String fileName) {
        super(id, fileName);
    }



    @Override
    protected void update(ArrayList<Integer> pressedKeys) {

    }

    /* Display object children: add and remove */
    public void addChild(DisplayObjectContainer child) {

    }

    public void addChildAtIndex(DisplayObjectContainer child, Integer index) {

    }

    public void removeChild(DisplayObjectContainer child) {

    }

    public void removeChildAtIndex(DisplayObjectContainer child, Integer index) {

    }

    public void removeAll() {

    }




}
