package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import exceptions.NonExistentUserException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.User;
import view.MainGame;

public class LoginSceneController implements Initializable
{
	@FXML private GridPane mainPane;
	@FXML private Button regBtn, loginBtn;
	@FXML private TextField userIDField;
	@FXML private PasswordField passwordField;
	private Stage appStage;
	private Parent root;
	private Scene scene;

	@FXML public void buttonClicked(ActionEvent event) throws IOException, NonExistentUserException
	{
		// On register button press, show the register scene
		if (event.getSource() == regBtn)
		{
			appStage = (Stage) mainPane.getScene().getWindow();
			root = FXMLLoader.load(getClass().getResource("../view/RegisterScene.fxml"));
			scene = new Scene(root);
			appStage.setTitle("Registration");
			appStage.setScene(scene);
			appStage.setResizable(false);
			appStage.show();
			appStage.centerOnScreen();
		}
		
		// On login button press
		if (event.getSource() == loginBtn)
		{
			try
			{
				String IdInput = userIDField.getText();
				String passwordInput = passwordField.getText();
				if (validateLogin(IdInput, passwordInput))
				{
					// Set the current user to the user that was succesfully logged in
					User currentUser = MainGame.getUserList().getUser(IdInput);
					MainGame.setCurrentUser(currentUser);
					
					// Load the main menu
					appStage = (Stage) mainPane.getScene().getWindow();
					root = FXMLLoader.load(getClass().getResource("../view/MainMenuScene.fxml"));
					scene = new Scene(root);
					appStage.setTitle("Main Menu");
					appStage.setScene(scene);
					appStage.centerOnScreen();
					appStage.setResizable(false);
					appStage.show();
				}
				else
				{
					System.out.println("Incorrect password!");
				}
			}
			catch(NonExistentUserException e)
			{
				 System.out.println("User does not exist!");
			}
		}
	}
	
	
	// Return true if login details are valid
	private Boolean validateLogin(String userID, String password) throws NonExistentUserException
	{
		// Hash the password that has been input
		password = String.valueOf(password.hashCode());
		
		// Attempt to grab User with given userID
		User user = MainGame.getUserList().getUser(userID);
		
		// Check that the password is correct
		if (password.equals(user.getPassword()))
		{
			return true;
		}
		return false;
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources)
	{
		// TODO Auto-generated method stub

	}
}