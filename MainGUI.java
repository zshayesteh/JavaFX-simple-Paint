package javaFXpaintOO;

import java.sql.SQLException;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class MainGUI extends Application {
	private Scene loginScene;
	private Scene drawScene;

	private Pane drawPanel;
	private Person person;
	private PersonDBManager personDBM;
	private String color= "BLACK";
	private String shapeType="line";
	private myShape shape;
	private double startX;
	private double startY;
	private double endX;
	private double endY;
	private ShapeDBManager SDBM;
	private BorderPane root;

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) {


		//***********************************************************
		//login page scene
		//***********************************************************


		BorderPane borderP=new BorderPane();
		Label welcome=new Label("Welcome To Your Simple Paint App :)");
		welcome.setFont(Font.font(24));
		borderP.setTop(welcome);
		VBox v1box=new VBox(20);
		v1box.setAlignment(Pos.CENTER);
		borderP.setCenter(v1box);

		Label lblNote=new Label("Note:In order to see your page you must login with your username and password.");
		Label lblUsername=new Label("Username: ");
		Label lblPassword=new Label("Password: ");
		TextField txtUsername=new TextField();
		PasswordField txtPass=new PasswordField();
		Button btnLogin=new Button("Login");
		HBox NoteHbox=new HBox(lblNote);
		NoteHbox.setAlignment(Pos.CENTER);
		HBox UnameHbox=new HBox(10,lblUsername,txtUsername);
		UnameHbox.setAlignment(Pos.CENTER);
		HBox PassHbox=new HBox(10,lblPassword,txtPass);
		PassHbox.setAlignment(Pos.CENTER);
		HBox butHbox=new HBox(btnLogin);
		butHbox.setAlignment(Pos.CENTER);
		v1box.getChildren().addAll(NoteHbox,UnameHbox,PassHbox,butHbox);
		borderP.setBackground(Background.EMPTY);
		borderP.setAlignment(v1box, Pos.CENTER);
		borderP.setAlignment(welcome, Pos.CENTER);

		loginScene=new Scene(borderP,630,500);
		loginScene.setFill(Color.ANTIQUEWHITE);


		btnLogin.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				if (!txtUsername.getText().trim().isEmpty() & txtPass.getText().length() != 0) {
					String passText = new String(txtPass.getText());
					String userText = new String(txtUsername.getText());
					person = new Person(userText, passText);
					try {
						personDBM = new PersonDBManager();
						if (personDBM.authentication(person)) {
							primaryStage.setScene(drawScene);
							primaryStage.show();
							drawPreviousShapes();
						} else
						{
							Alert alert = new Alert(AlertType.INFORMATION);
							alert.setTitle("Error");
							alert.setHeaderText(null);
							alert.setContentText("The username or password you entered doesn't/dont match!");
							alert.showAndWait();
						}
					} catch (SQLException e) {
					}
				} else {
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("warning");
					alert.setHeaderText(null);
					alert.setContentText("You must fill both fields of username and password to continue!");
					alert.showAndWait();
				}
			}
		});

		//***********************************************************
		//draw page scene
		//***********************************************************

		root=new BorderPane();
		VBox vbox=new VBox(30);
		drawPanel=new Pane();        

		drawPanel.prefHeight(500);
		drawPanel.prefWidth(500);
		drawPanel.maxHeight(500);
		drawPanel.maxWidth(500);
		drawPanel.setStyle("-fx-border-color: black");
		drawPanel.setStyle("-fx-background-color: white");
		Button btnLine=new Button("Line");
		Button btnCircle=new Button("Circle/Ellipse");
		Button btnRectangle=new Button("Rectangle");

		final ToggleGroup group = new ToggleGroup();

		RadioButton rdbBlackColor=new RadioButton("Black");
		rdbBlackColor.setToggleGroup(group);
		RadioButton rdbRedColor=new RadioButton("Red");
		rdbRedColor.setToggleGroup(group);
		RadioButton rdbBlueColor=new RadioButton("Blue");
		rdbBlueColor.setToggleGroup(group);
		RadioButton rdbGreenColor=new RadioButton("Green");
		rdbGreenColor.setToggleGroup(group);

		Button btnLogOut=new Button("Logout");

		vbox.getChildren().addAll(btnLine,btnCircle,btnRectangle,rdbBlackColor,rdbRedColor,rdbBlueColor,rdbGreenColor,btnLogOut);
		vbox.setBackground(Background.EMPTY);
		vbox.setAlignment(Pos.CENTER);
		vbox.setPadding(new Insets(20));
		vbox.setStyle("-fx-background-color: ANTIQUEWHITE");

		drawPanel.setPadding(new Insets(50));
		root.setRight(vbox);
		root.setCenter(drawPanel);

		btnLogOut.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				primaryStage.setScene(loginScene);
				primaryStage.show();

			}
		});

		btnLine.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				shapeType="line";
			}
		});

		btnCircle.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				shapeType="circle";
			}
		});

		btnRectangle.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				shapeType="rectangle";
			}
		});

		rdbBlackColor.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				color="BLACK";
			}
		});

		rdbRedColor.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				color="RED";
			}
		});

		rdbBlueColor.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				color="BLUE";
			}
		});

		rdbGreenColor.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				color="GREEN";
			}
		});


		drawPanel.setOnMousePressed(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				// TODO Auto-generated method stub
				startX=event.getX();
				startY=event.getY();
				drawPanel.getChildren().add(new Line(startX,startY,startX,startY));
			}
		});

		drawPanel.setOnMouseReleased(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				// TODO Auto-generated method stub
				endX=event.getX();
				endY=event.getY();
				if(shapeType.equals("line"))
					shape=new myLine(startX,startY,endX,endY,color.toString(),shapeType,Person.getUsername());
				else if(shapeType.equals("circle"))
					shape=new myCircle(startX,startY,endX,endY,color.toString(),shapeType,Person.getUsername());
				else if(shapeType.equals("rectangle"))
					shape=new myRectangle(startX,startY,endX,endY,color.toString(),shapeType,Person.getUsername());

				Shape s=shape.makeShape();
				
				drawPanel.getChildren().add(s);
				SDBM=new ShapeDBManager();
				try {
					SDBM.addShape(shape);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		});

		drawPanel.setOnMouseDragged(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				// TODO Auto-generated method stub
				endX=event.getX();
				endY=event.getY();
				if(shapeType.equals("line"))
					shape=new myLine(startX,startY,endX,endY,color.toString(),shapeType,Person.getUsername());
				else if(shapeType.equals("circle"))
					shape=new myCircle(startX,startY,endX,endY,color.toString(),shapeType,Person.getUsername());
				else if(shapeType.equals("rectangle"))
					shape=new myRectangle(startX,startY,endX,endY,color.toString(),shapeType,Person.getUsername());

				Shape s=shape.makeShape();
				drawPanel.getChildren().add(s);

				drawPanel.getChildren().remove(drawPanel.getChildren().size()-2);//for not showing all dragged shapes
			}
		});

		//clipping the pane so performing its border into the shapes
		final Rectangle outputClip = new Rectangle();
		outputClip.setWidth(500);
		outputClip.setHeight(500);
		drawPanel.setClip(outputClip);


		drawScene=new Scene(root,630,500);
		drawScene.setFill(Color.BEIGE);


		primaryStage.setScene(loginScene);
		primaryStage.setTitle("Paint");
		primaryStage.setResizable(false);
		primaryStage.show();

	}

	public void drawPreviousShapes(){
		drawPanel.getChildren().removeAll(drawPanel.getChildren());
		
		ArrayList<myShape> shapes=new ArrayList<myShape>();
		try {
			SDBM=new ShapeDBManager();
			shapes=SDBM.getShapes(person);
			for(int i=0;i<shapes.size();i++)
			{
				Shape sh=shapes.get(i).makeShape();
				drawPanel.getChildren().add(sh);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
}
