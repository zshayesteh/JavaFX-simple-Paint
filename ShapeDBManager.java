package javaFXpaintOO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.MessageFormat;
import java.util.ArrayList;

public class ShapeDBManager {
	Statement stmt;
	ArrayList<myShape> s=new ArrayList<myShape>();

	public ShapeDBManager() {
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			String url = "jdbc:mysql://127.0.0.1/paint-oo?user=root&password=";
			Connection conn = DriverManager.getConnection(url);
			stmt = conn.createStatement();
		} catch (Exception e) {
			System.out.println("exception " + e);
		}
	}

	public void addShape(myShape s) throws SQLException {// add a new shape
		stmt.executeUpdate(MessageFormat.format("insert into shape (`x1`,`y1`,`x2`,`y2`,`color`,`shapeType`,`person_username`) values({0},{1},{2},{3},''{4}'',''{5}'',''{6}'');",
				s.getX1(), s.getY1(), s.getX2(), s.getY2(), s.getColor().toString(), s.getShapeType(), Person.getUsername()));
	}

	public ArrayList<myShape> getShapes(Person p) throws SQLException {
		ResultSet rs = stmt.executeQuery("select * from shape where person_username='" + p.getUsername() + "';");
		while (rs.next()) {
			int x1 = rs.getInt(2);
			int y1 = rs.getInt(3);
			int x2 = rs.getInt(4);
			int y2 = rs.getInt(5);
			String color =rs.getString(6);
			String shapeType = rs.getString(7);
			if (shapeType.equals("line"))
				s.add(new myLine(x1, y1, x2, y2, color, shapeType, p.getUsername()));
			else if (shapeType.equals("circle"))
				s.add(new myCircle(x1, y1, x2, y2, color, shapeType, p.getUsername()));
			else if (shapeType.equals("rectangle"))
				s.add(new myRectangle(x1, y1, x2, y2, color, shapeType, p.getUsername()));
		}
		return s;
	}
	
}
