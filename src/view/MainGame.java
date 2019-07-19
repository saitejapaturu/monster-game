
package view;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.User;
import model.UserList;

public class MainGame extends Application
{
	private static UserList userList = UserList.getInstance();
	private static User currentUser;


	public static void main(String[] args)
	{
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws IOException
	{
		userList.addUser(new User("Henry", "1234"));
		userList.addUser(new User("admin", "1234", true));
		Parent loginRoot = FXMLLoader.load(getClass().getResource("LoginScene.fxml"));
//		Parent loginRoot = FXMLLoader.load(getClass().getResource("GameBoard-85x85.fxml"));
		Scene loginScene = new Scene(loginRoot);
		primaryStage.setScene(loginScene);
		primaryStage.setTitle("Login");
		primaryStage.setResizable(false);
		primaryStage.show();
	}
	
	public static UserList getUserList()
	{
		return userList;
	}
	
	public static User getCurrentUser()
	{
		return currentUser;
	}
	
	public static void setCurrentUser(User user)
	{
		currentUser = user;
	}
}
