import javafx.application.Application;
import javafx.application.Platform;

import java.io.FileNotFoundException;
import java.nio.file.NoSuchFileException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class wordOcurrencesGUI extends Application  {

	static String fileName;


	public void start(Stage stage) throws FileNotFoundException {

		//congifure grid
		GridPane grid = new GridPane();
		grid.setAlignment(Pos.TOP_CENTER);
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(10, 25, 25, 25));

		//declare scene, add grid to scene, set size
		Scene scene = new Scene(grid, 580, 300);

		//styling nodes   
		scene.getStylesheets().add("controlStyle1.css"); 

		//configure scene title
		Label scenetitle = new Label("Word Frequency Tool");
		scenetitle.setWrapText(true);
		scenetitle.setMinHeight(75);
		scenetitle.setTextAlignment(TextAlignment.CENTER);
		scenetitle.setAlignment(Pos.CENTER);
		scenetitle.setMaxWidth(Double.MAX_VALUE);
		scenetitle.getStyleClass().add("scene-title");

		//load custom font from local file system
		Font font = Font.loadFont("File:impact-label/Impact Label.ttf", 45);

		//setting the font
		scenetitle.setFont(font);

		//creating the inner shadow effect
		//creating the drop shadow effect
		DropShadow shadow = new DropShadow();
		shadow.setOffsetY(5.0);

		//setting the effect to the text
		scenetitle.setEffect(shadow);

		//configure description label
		Label appDescription = new Label("Enter a file name to count the frequency of each word in your text.");
		appDescription.setWrapText(true);
		appDescription.setMinHeight(50);
		appDescription.setTextAlignment(TextAlignment.CENTER);
		appDescription.getStyleClass().add("label-header");

		//configure textField, set default text
		TextField userTextField = new TextField("1065-h.htm");
		userTextField.getStyleClass().add("text-field");

		//configure WordCount() button
		Button btn = new Button("Start Counting");
		HBox hbBtn = new HBox(10);
		hbBtn.setEffect(shadow);
		hbBtn.setAlignment(Pos.BOTTOM_CENTER);
		hbBtn.getChildren().add(btn);

		//configure reset button
		Button resetBtn = new Button("Reset");
		HBox hbresetBtn = new HBox(10);
		hbresetBtn.setEffect(shadow);
		hbresetBtn.setAlignment(Pos.BOTTOM_CENTER);
		hbresetBtn.getChildren().add(resetBtn);

		//add elements to grid
		grid.add(scenetitle, 0,1);
		grid.add(appDescription, 0, 2);
		grid.add(userTextField, 0, 3);
		grid.add(hbBtn, 0, 5);

		//Label for tableView
		Label label = new Label("  Word Frequency Data    ");



		//on WordCount() button click, generate tableView
		btn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {

				//configure TableView and add data
				fileName = userTextField.getText();
				stage.setHeight(400);


				TableView<WordFrequencyPairing> table;

				try {
					table = setTableView(fileName, grid);
					//configure VBox for TableView
					VBox vbox = new VBox();
					vbox.setSpacing(5);
					vbox.setPadding(new Insets(10, 50, 50, 60));
					vbox.getChildren().addAll(label, table);
					vbox.getStyleClass().addAll("table-view");
					vbox.getStyleClass().addAll("vbox");
					vbox.setEffect(shadow);
					btn.setVisible(false);
					grid.add(vbox, 0, 5);
					grid.add(hbresetBtn, 0, 7);
					stage.setHeight(700);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					System.out.println("did i make it?");
				    Platform.runLater(() -> {

					Label e1ErrorMessage = new Label("Invalid File Name.");
					e1ErrorMessage.setWrapText(true);
					e1ErrorMessage.setMinHeight(50);
					e1ErrorMessage.setTextAlignment(TextAlignment.CENTER);
					e1ErrorMessage.setAlignment(Pos.CENTER);

					e1ErrorMessage.getStyleClass().add("error-message");
					grid.add(e1ErrorMessage, 0, 7);
				    });
				}
			}
		});

		grid.getStyleClass().addAll("grid", "title-text");

		//on reset button click rest
		resetBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {

				//configure VBox for TableView
				try {
					start(stage);
					stage.setHeight(300);

				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		stage.setTitle("Word Frequency Tool");
		stage.setScene(scene);
		stage.show();
	}
	public static void main(String args[]){
		launch(args);
	}

	public static TableView<WordFrequencyPairing> setTableView(String fileName, GridPane grid) throws FileNotFoundException {

		//Creating a table view
		TableView<WordFrequencyPairing> table = new TableView<WordFrequencyPairing>();

		//Creating columns
		TableColumn firstDataColumn = new TableColumn("Frequency");
		firstDataColumn.setCellValueFactory(new PropertyValueFactory<>("count"));

		TableColumn secondDataColumn = new TableColumn("Word");
		secondDataColumn.setCellValueFactory(new PropertyValueFactory<>("word"));

		firstDataColumn.prefWidthProperty().bind(table.widthProperty().multiply(0.5));
		secondDataColumn.prefWidthProperty().bind(table.widthProperty().multiply(0.5));

		firstDataColumn.setResizable(false);
		secondDataColumn.setResizable(false);

		//Adding data to the table
		ObservableList<WordFrequencyPairing> list = calculateWordFrequency(fileName, grid);

		System.out.println(list.toString());

		table.setItems(list);

		System.out.println(firstDataColumn);

		table.getColumns().addAll(firstDataColumn, secondDataColumn);

		//Setting the size of the table
		table.setMaxSize(500, 500);

		return table;

	}
	public static ObservableList<WordFrequencyPairing> calculateWordFrequency(String textFile, GridPane grid) throws FileNotFoundException{
		//TODO convert textAnalyzer to ObservableList to populate TableView
		ObservableList<WordFrequencyPairing> allData = FXCollections.observableArrayList(WordCount.textAnalyzer);

		System.out.println(textFile);
		WordCount.main(null);
		allData = FXCollections.observableArrayList(WordCount.textAnalyzer);
		return allData;
	}
}
