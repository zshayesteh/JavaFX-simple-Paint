package javaFXpaintOO;

import java.lang.reflect.Field;

import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Shape;

public class myCircle extends myShape {

	public myCircle(double x1, double y1, double x2, double y2, String color, String shapeType, String username) {
		super(x1, y1, x2, y2, color, shapeType, username);
	}

	public myCircle(double x1, double y1, String color, String shapeType, String username) {
		super(x1, y1, color, shapeType, username);
	}

	public Shape makeShape() {// drawing an ellipse
		Ellipse e=new Ellipse(Math.min(getX1(), getX2()), Math.min(getY1(), getY2()), Math.abs(getX1() - getX2()), Math.abs(getY1() - getY2())); 
		e.setFill(Color.TRANSPARENT);
		Color color;
		try {
		    Field field = Class.forName("javafx.scene.paint.Color").getField(this.getColor().toUpperCase());
		    color = (Color)field.get(null);
		} catch (Exception ex) {
		    color = Color.BLACK; // Not defined
		}
		e.setStroke(color);
		return e;
	}
}
