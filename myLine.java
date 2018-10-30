package javaFXpaintOO;

import java.lang.reflect.Field;

import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Shape;

public class myLine extends myShape {

	public myLine(double x1, double y1, double x2, double y2, String color, String shapeType, String username) {
		super(x1, y1, x2, y2, color, shapeType, username);
	}

	public myLine(double x1, double y1, String color, String shapeType, String username) {
		super(x1, y1, color, shapeType, username);
	}

	public Shape makeShape() {//drawing a line
		Line l= new Line(getX1(), getY1(), getX2(), getY2());
		l.setFill(Color.TRANSPARENT);
		Color color;
		try {
		    Field field = Class.forName("javafx.scene.paint.Color").getField(this.getColor().toUpperCase());
		    color = (Color)field.get(null);
		} catch (Exception ex) {
		    color = Color.BLACK; // Not defined
		}
		l.setStroke(color);
		return l;
	}

}
