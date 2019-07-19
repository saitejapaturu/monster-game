package controller;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import view.MainGame;

public class AdminMenuSceneController implements Initializable
{
	@FXML private GridPane mainPane;
	@FXML private Button timeBtn, removeBtn, returnBtn, logoutBtn;
	private Stage appStage;
	private Parent root;
	private Scene scene;

	@FXML public void buttonClicked(ActionEvent event) throws IOException
	{
		if (event.getSource() == timeBtn)
		{
			timeDialogBox();
		}

		if (event.getSource() == removeBtn)
		{
			removePlayerDialogBox();
		}

		if (event.getSource() == returnBtn)
		{
			appStage = (Stage) mainPane.getScene().getWindow();
			root = FXMLLoader.load(getClass().getResource("../view/MainMenuScene.fxml"));
			scene = new Scene(root);
			appStage.setTitle("Main Menu");
			appStage.setScene(scene);
			appStage.centerOnScreen();
			appStage.setResizable(false);
			appStage.show();
		}
		
		if (event.getSource() == logoutBtn)
		{
			appStage = (Stage) mainPane.getScene().getWindow();
			root = FXMLLoader.load(getClass().getResource("../view/LoginScene.fxml"));
			scene = new Scene(root);
			appStage.setTitle("Login");
			appStage.setScene(scene);
			appStage.centerOnScreen();
			appStage.setResizable(false);
			appStage.show();
		}
	}
	
	public void timeDialogBox()
	{
        TextInputDialog dialog = new TextInputDialog("Time (In Seconds)");
 
        dialog.setTitle("Set Timer");
        dialog.setHeaderText("Enter new time:");
        dialog.setContentText("Time:");
 
        Optional<String> result = dialog.showAndWait();
 
        result.ifPresent(timer -> {
            MainBoardController.setTimer(Integer.parseInt(timer));
            System.out.println("Game time is now " + MainBoardController.gameTimer);
        });
	}

	public void removePlayerDialogBox()
	{
        TextInputDialog dialog = new TextInputDialog("User ID (Case Sensitive)");
 
        dialog.setTitle("Remove Player");
        dialog.setHeaderText("Enter User ID:");
        dialog.setContentText("User ID:");
 
        Optional<String> result = dialog.showAndWait();
 
        result.ifPresent(userID -> {
            MainGame.getUserList().removeUser(userID);
            System.out.println("Removed user " + userID);
        });
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources)
	{
		// TODO Auto-generated method stub
	}
}