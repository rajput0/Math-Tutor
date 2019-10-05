
import java.util.Random;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class newMathTutor extends Application {
	private Alert a;
	private int userInputInt;
	private int num1;
	private int num2;
	private int corrCount, wrongCount;
	private Scene scene;
	private VBox Vbox;
	private HBox HboxButton, HboxQuestion, HboxResult;
	private BorderPane bp;
	private MenuBar menuBar = new MenuBar();
	private Menu menu;
	private MenuItem exit;
	private Label queTxt, queDigit, feedback, corrAns, wrongAns;			
	private TextField userInput;
	private Button newExe, submit;	

	@Override
	public void start(Stage ps) throws Exception {
		
		
		exit = new MenuItem("Exit CTRL+X");
		menu = new Menu("Application");
		menu.getItems().add(exit);
		menuBar.getMenus().add(menu);
		
		queTxt = new Label("How much is:");
		queTxt.setStyle("-fx-font-weight:BOLD; -fx-text-fill: brown; -fx-font-size: 15pt");
		queDigit = new Label("");
		
		userInput = new TextField();
		userInput.setMaxWidth(35);
		userInput.setAlignment(Pos.CENTER);
		userInput.setDisable(true);
		feedback = new Label("");
		feedback.setStyle("-fx-font-weight: BOLD; -fx-text-fill: BLUE; -fx-font-size: 11pt");
		newExe = new Button("New Exercise");
		submit = new Button("Answer");
		newExe.setMinWidth(200);
		submit.setMinWidth(200);
		submit.setDisable(true);
		corrAns = new Label("Number of correct \nAnswer: 0");
		wrongAns = new Label("Number of wrong \nAnswer: 0");
		corrAns.setAlignment(Pos.CENTER);
		wrongAns.setAlignment(Pos.CENTER);
		corrAns.setStyle("-fx-font-size: 11pt");
		wrongAns.setStyle("-fx-font-size: 11pt");
		
		submit.setDefaultButton(true);
		newExe.setOnAction(actionEvent->{
			newExe.setDisable(true);
			submit.setDisable(false);
			generateRandom();
			generateQue();
			userInput.setDisable(false);
			userInput.requestFocus();
			checkProcess();
		});
		
		HboxButton = new HBox(newExe,submit);
		HboxButton.setAlignment(Pos.CENTER);
		
		HboxQuestion = new HBox(queDigit,userInput);
		HboxQuestion.setAlignment(Pos.CENTER);
		
		HboxResult = new HBox(corrAns,wrongAns);
		HboxResult.setAlignment(Pos.CENTER);
		HboxResult.setSpacing(50);
		
		
		
		Vbox = new VBox(queTxt,HboxQuestion,feedback,HboxButton,HboxResult);
		Vbox.setAlignment(Pos.CENTER);
		
		Vbox.setSpacing(7);
		
		bp = new BorderPane();
		bp.setTop(menuBar);
		bp.setCenter(Vbox);
		
		scene = new Scene(bp,400,300);
		
		ps.setTitle("Practice Math");
		ps.setScene(scene);
		ps.show();
		
		exit.setOnAction(actionEvent->{
			System.exit(0);
		});
	}
	
	public void generateQue() {
		queDigit.setText(getNum1() + " X " + getNum2() + " = ");
		queDigit.setStyle("-fx-font-weight: BOLD; -fx-font-size: 13pt");
	}
	
	public void generateRandom() {
		Random rand = new Random();
		setNum1(rand.nextInt(9)+1);
		setNum2(rand.nextInt(9)+1);
		
	}
	
	public int getResult() {
		return getNum1()*getNum2();
	}
	
	public void checkProcess() {
		submit.setOnAction(actionEvent->{
			try{
				userInputInt = Integer.parseInt(userInput.getText());
				if(userInputInt == getResult()) {
					corrCount ++;
					corrAns.setText("Number of correct \nAnswer: " + corrCount);
					userInput.setText("");
					userInput.requestFocus();
					generateRandom();
					generateQue();
					limit();
					
				}else {
					wrongCount++;
					wrongAns.setText("Number of wrong \nAnswer: "  + wrongCount);
					userInput.setText("");
					userInput.requestFocus();
					generateRandom();
					generateQue();
					limit();
				}
				
			}catch(NumberFormatException e) {
				a = new Alert(AlertType.INFORMATION);
				a.setTitle("Error");
				a.setContentText("Please enter whole number");
				a.show();
				userInput.setText("");
				userInput.requestFocus();
				
			}
		});
		
	}
	
	public void limit() {
		if(wrongCount + corrCount == 10) {
			submit.setDisable(true);
			newExe.setDisable(false);
			userInput.setDisable(true);
			queDigit.setText("");
			
			if(corrCount >= 7) {
				feedback.setText("Keep up the good work!");
				resetCountLabel();
			}else {
				feedback.setText("You need Practice");
				resetCountLabel();
			}
		
		}
	}
	
	public void resetCountLabel() {
		corrAns.setText("Number of correct \nAnswer: 0");
		wrongAns.setText("Number of wrong \nAnswer: 0");
		corrCount = 0;
		wrongCount = 0;
	}

	public static void main(String[] args) {
		launch(args);
	}
	
	public int getNum1() {
		return num1;
	}

	public void setNum1(int num1) {
		this.num1 = num1;
	}

	public int getNum2() {
		return num2;
	}

	public void setNum2(int num2) {
		this.num2 = num2;
	}


}
