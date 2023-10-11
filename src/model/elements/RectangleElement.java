package model.elements;

import controller.StrokeSerializationAdapter;

import java.awt.*;
import java.awt.geom.GeneralPath;

public class RectangleElement extends Slot{

    public RectangleElement(Point position, int width, int height, Color color, StrokeSerializationAdapter stroke, Shape shape) {
        super(position, width, height, color, stroke, shape);

    }
    public RectangleElement(Point position, int width, int height, Color color, StrokeSerializationAdapter stroke) {
        super(position, width, height, color, stroke, new Rectangle());
        Shape shape=new GeneralPath();
        ((GeneralPath)shape).moveTo(position.x,position.y);

        ((GeneralPath)shape).lineTo(position.x+width,position.y);

        ((GeneralPath)shape).lineTo(position.x+ width,position.y+height);

        ((GeneralPath)shape).lineTo(position.x,position.y+height);

        ((GeneralPath)shape).closePath();
        this.setShape(shape);

    }
}
