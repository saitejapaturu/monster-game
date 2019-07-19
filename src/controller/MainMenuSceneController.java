package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.User;
import view.MainGame;

public class MainMenuSceneController implements Initializable
{
	@FXML private GridPane mainPane;
	@FXML private Button playBtn, quitBtn, adminBtn;
	@FXML private TextField userIDField;
	@FXML private PasswordField passwordField, cfmPasswordField;
	@FXML private Label userDetails, userWL;
	private Stage appStage;
	private Scene scene;
	private User currentUser = MainGame.getCurrentUser();

	@FXML public void buttonClicked(ActionEvent event) throws IOException
	{
		if (event.getSource() == playBtn)
		{
			appStage = (Stage) mainPane.getScene().getWindow();
			appStage.setTitle("");
			scene = new Scene(FXMLLoader.load(getClass().getResource("../view/GameBoard-85x85.fxml")));
			appStage.setScene(scene);
			appStage.show();
			appStage.centerOnScreen();
			System.out.println("User clicked play!");
		}

		if (event.getSource() == quitBtn)
		{
			System.exit(0);
		}

		if (event.getSource() == adminBtn)
		{
			appStage = (Stage) mainPane.getScene().getWindow();
			appStage.setTitle("");
			scene = new Scene(FXMLLoader.load(getClass().getResource("../view/AdminMenuScene.fxml")));
			appStage.setScene(scene);
			appStage.show();
			appStage.centerOnScreen();
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources)
	{
		// TODO Auto-generated method stub
		userDetails.setText(currentUser.getUserID());
		userWL.setText("Wins: " + currentUser.getWins() + ", Losses: " + currentUser.getLosses());

		if (!currentUser.getIsAdmin())
		{
			adminBtn.setVisible(false);
		}
	}
}