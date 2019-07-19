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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.User;
import view.MainGame;

public class RegisterSceneController implements Initializable
{
	@FXML private GridPane mainPane;
	@FXML private Button cancelBtn, confirmBtn;
	@FXML private TextField userIDField;
	@FXML private PasswordField passwordField, cfmPasswordField;
	private Stage appStage;

	@FXML public void buttonClicked(ActionEvent event) throws IOException
	{
		if (event.getSource() == cancelBtn)
		{
			appStage = (Stage) mainPane.getScene().getWindow();
			appStage.setTitle("Login");
			appStage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/LoginScene.fxml"))));
			appStage.setResizable(false);
			appStage.show();
			appStage.centerOnScreen();
		}

		if (event.getSource() == confirmBtn)
		{
			if ((userIDField == null) || (passwordField == null) || (cfmPasswordField == null))
			{
				System.out.print("All fields must be filled in!\n");
			}
			if (!passwordField.getText().equals(cfmPasswordField.getText()))
			{
				System.out.print("Passwords do not match!\n");
			}
			if ((userIDField != null) && (passwordField.getText().equals(cfmPasswordField.getText())))
			{
				if (MainGame.getUserList().addUser(new User(userIDField.getText(), passwordField.getText())) == true)
				{
					System.out.print("Added User!\n");
				}
				else
				{
					System.out.print("User already exists!\n");
				}
				
			}
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources)
	{
		// TODO Auto-generated method stub

	}
}