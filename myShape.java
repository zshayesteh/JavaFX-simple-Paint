package javaFXpaintOO;

import javafx.scene.shape.Shape;

public abstract class myShape {
	private double x1;
	private double y1;
	private double x2;
	private double y2;
	private String color;
	private String shapeType;
	private String username;

	public double getX1() {
		return x1;
	}

	public void setX1(double x1) {
		this.x1 = x1;
	}

	public double getY1() {
		return y1;
	}

	public void setY1(double y1) {
		this.y1 = y1;
	}

	public double getX2() {
		return x2;
	}

	public void setX2(double x2) {
		this.x2 = x2;
	}

	public double getY2() {
		return y2;
	}

	public void setY2(double y2) {
		this.y2 = y2;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		if (color != null)
			this.color = color;
	}

	public String getShapeType() {
		return shapeType;
	}

	public void setShapeType(String shapeType) {
		this.shapeType = shapeType;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		if (username != null)
			this.username = username;
	}

	public myShape(double x1, double y1, double x2, double y2, String color, String shapeType, String username) {
		setX1(x1);
		setY1(y1);
		setX2(x2);
		setY2(y2);
		setColor(color);
		setShapeType(shapeType);
		setUsername(username);
	}

	public myShape(double x1, double y1, String color, String shapeType, String username) {
		setX1(x1);
		setY1(y1);
		setColor(color);
		setShapeType(shapeType);
		setUsername(username);
	}

	public abstract Shape makeShape();// drawing a shape

}
