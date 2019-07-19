package controller;

import java.io.IOException;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Food;
import model.Grid;
import model.Monster;
import model.MovingPiece;
import model.Piece;
import model.Player;
import model.User;

public class MainBoardController implements Initializable
{
    //Linking scene builder items to code.
    @FXML
    private AnchorPane mainPane;
    @FXML
    private MenuItem menuQuit;
    @FXML
    private MenuItem playGame;
    @FXML
    private MenuItem moveUp;
    @FXML
    private MenuItem moveDown;
    @FXML
    private MenuItem moveLeft;
    @FXML
    private MenuItem moveRight;
    @FXML
    private MenuItem dropPoison;
    
    @FXML
    private ImageView heroGUI;
    @FXML
    private ImageView monster1GUI;
    @FXML
    private ImageView monster2GUI;
    
    @FXML
    private ImageView poisonedHeroGUI;
    @FXML
    private ImageView poisonedMonster1GUI;
    @FXML
    private ImageView poisonedMonster2GUI;
    
    @FXML
    private ImageView poisonGUI;

    //Creating a grid
    private Grid grid = new Grid(11, 11);
    
    //Initialising player, monster.
    private Player hero = new Player(0, 0, 3);
    private Monster monster1 = new Monster(10, 10, 1);
    private Monster monster2 = new Monster(5, 5, 2);
    
    private Boolean startGame = false;
    
    //Game timer in seconds.
    public static int gameTimer = 30;
    

    private Stage appStage;
    private Parent root;
    private Scene scene;
    
    
    //Timer to move monsters.
    Timer timer = new Timer();
    
    //Initialising Executors.
    ScheduledExecutorService timerExecutor = Executors.newSingleThreadScheduledExecutor();
    ScheduledExecutorService monster1Executor = Executors.newSingleThreadScheduledExecutor();
    ScheduledExecutorService monster2Executor = Executors.newSingleThreadScheduledExecutor();
    
    //Method to decrease the time.
    private Runnable gameTime = new Runnable() 
    {
        public void run() {
            
            System.out.println(gameTimer + "..");
            if(gameTimer<=0)
            {
                System.out.println("Congrats! You won!");
                loadMainMenu();
            }
            gameTimer--;
        }
    };
    
    //Method to moveMonster1.
    private Runnable moveMonster1 = new Runnable() 
    {
        public void run() 
        {
            if(!(hero.getAliveStatus()))
            {
                System.out.println("You lost! :( ");
                loadMainMenu();
            }
            
            String move = controller1(monster1);
            
            updateMove(monster1, monster1GUI, poisonedMonster1GUI, poisonGUI, move);
            
        }
    };
    
    //Method to moveMonster2.
    private Runnable moveMonster2 = new Runnable() 
    {
        public void run() 
        {
            if(!(hero.getAliveStatus()))
            {
                System.out.println("You lost! :( ");
                loadMainMenu();
            }
            String move = controller2(monster2);
            
            updateMove(monster2, monster2GUI, poisonedMonster2GUI, poisonGUI, move);
            
            
        }
    };

    //When playGame is clicked, starts the game timer and makes the monsters move.
    @FXML
    public void menuItem(ActionEvent event) throws IOException
    {
        if(event.getSource() == playGame)
        {
            startGame = true;

            ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
            
            //Decreases time every second.
            timerExecutor.scheduleAtFixedRate(gameTime, 0, 1, TimeUnit.SECONDS);
            
            //Makes the monsters move depending on their movement speed.
            monster1Executor.scheduleAtFixedRate(moveMonster1, 0, monster1.getMovementTime(), TimeUnit.SECONDS);
            monster2Executor.scheduleAtFixedRate(moveMonster2, 0, monster2.getMovementTime(), TimeUnit.SECONDS);

            
        }
        
        //Quits to main menu if quit is pressed.
        if (event.getSource() == menuQuit)
        {
            loadMainMenu();
        }
            
    }
    
    //Calls appropriate method of player depending on the button clicked.
    @FXML
    public void controlPlayer(ActionEvent event) throws IOException
    {
        if(startGame)
        {
            String move = "NOT_MOVED";
            if(event.getSource() != dropPoison)
            {
                if (event.getSource() == moveUp)
                {
                    move = hero.moveUp(grid);
                }
                
                if (event.getSource() == moveDown)
                {
                    move = hero.moveDown(grid);
                }
                
                if (event.getSource() == moveLeft)
                {
                    move = hero.moveLeft(grid);
                }
                
                if (event.getSource() == moveRight)
                {
                    move = hero.moveRight(grid);
                }  
            
            }
            
            if (event.getSource() == dropPoison)
            {
                dropPoison(poisonGUI);
            }
            
            updateMove(hero, heroGUI, poisonedHeroGUI, poisonGUI,move);
            if(!(hero.getAliveStatus()))
            {
                loadMainMenu();
            }
        }
    }
    
    //Goes to main Menu, when quit or game is over.
    private void loadMainMenu()
    {
        //Shutdown Executors
        timerExecutor.shutdown();
        monster1Executor.shutdown();
        monster2Executor.shutdown();
        
     // Load the main menu
        appStage = (Stage) mainPane.getScene().getWindow();
        try
        {
            root = FXMLLoader.load(getClass().getResource("../view/MainMenuScene.fxml"));
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        scene = new Scene(root);
        appStage.setTitle("Main Menu");
        appStage.setScene(scene);
        appStage.centerOnScreen();
        appStage.setResizable(false);
        appStage.show();
    }
    
    //Drops poison and displays it on board.
    private void dropPoison(ImageView poison)
    {
        if(hero.placeFood(grid))
        {
            poison.setVisible(true);
            reposition(poison, hero.getPoison());
        }
    }
    
    //Depending upon the response, triggers methods.
    private void updateMove(MovingPiece piece, ImageView image, ImageView poisonImage,ImageView poison, String response)
    {
        //If GAMEOVER is returned, game quits to MainMenu
        if(response.equals("GAMEOVER"))
        {
            hero.setAliveStatus(false);
            System.out.println("You lost! :( ");
            loadMainMenu();
        }
        
        //if poisioned, poisons the movingPiece.
        if(response.equals("POISONED"))
        {
            poisonPiece(piece, image, poisonImage, poison);
        }
        
        //Depending on poison status moves appropriate imageView.
        if(piece.getPoisonStatus())
        {
            reposition(poisonImage, piece);
        }
        
        else
        {
            reposition(image, piece);
        }
        
    }
    
    //Repositions the image to appropriate cell on board.
    public void reposition(ImageView image, Piece piece)
    {
        image.relocate((piece.getX()*85),(piece.getY()*85 ));
    }
    
    //Makes the imageView visible or invisible.
    public void setVisibility(ImageView image, boolean visible)
    {
        image.setVisible(visible);
    }
    
    //Makes the image of movingPiece invisible and makes the poison image visible and changes the Movement speed.
    public void poisonPiece(MovingPiece piece,ImageView image, ImageView poisonImage, ImageView poison)
    {
        setVisibility(poison, false);
        setVisibility(image, false);
        setVisibility(poisonImage, true);
        
        piece.setPoisonStatus(true);
        
        if(piece instanceof Monster)
        {
            ((Monster) piece).setMovementTime(((Monster) piece).getMovementTime()*2);
        }
        
        //If player is poisoned make the monsters move faster
        else
        {
            monster1.setMovementTime(monster1.getMovementTime()/2);
            monster2.setMovementTime(monster2.getMovementTime()/2);
        }
        
//        //Unpoison after 10 seconds.
//        try
//        {
//            TimeUnit.SECONDS.sleep(5);
//            unPoisonPiece(piece, image, poisonImage);
//        } catch (InterruptedException e)
//        {
//            e.printStackTrace();
//        }
    }
    
    //unPoisions does opposite to poison image
    public void unPoisonPiece(MovingPiece piece,ImageView image, ImageView poisonImage)
    {       
        poisonImage.setVisible(false);
        image.setVisible(true);
        
        if(piece instanceof Monster)
        {
            ((Monster) piece).setMovementTime(((Monster) piece).getMovementTime()/2);
        }
        
        else
        {
            monster1.setMovementTime(monster1.getMovementTime()*2);
            monster2.setMovementTime(monster2.getMovementTime()*2);
        }
    }
    
    //Controller for monster 1, which moves constantly on outer bounds.
    private String controller1(Monster monster)
    {
        int monsterX = monster.getX();
        int monsterY = monster.getY();
        
        if(monsterX == 0 || monsterX == 10 )
        {
            if(monsterX == 0 && monsterY == 0)
            {
                return monster.moveDown(grid);
            }
            
            if(monsterX == 0 && monsterY == 10)
            {
                return monster.moveRight(grid);
            }
            
            if(monsterX == 10 && monsterY == 0)
            {
                return monster.moveLeft(grid);
            }
            
            if(monsterX == 10 && monsterY == 10)
            {
                return monster.moveUp(grid);
            }
            
            if(monsterY>0 && monsterY<10)
            {
                if(monsterX==0)
                {
                    return monster.moveDown(grid);
                }
                else if (monsterX==10)
                {
                    return monster.moveUp(grid);
                }
            }
        }


        if(monsterX>0 && monsterX<10)
        {
            if(monsterY==0)
            {
                return monster.moveLeft(grid);
            }
            else if (monsterY==10)
            {
                return monster.moveRight(grid);
            }
        }
        
       return "NOT_MOVED";
    }
    
    //Controller for monster two, which moves according to the location of player.
    private String controller2(Monster monster)
    {
        int monsterX = monster.getX();
        int monsterY = monster.getY();
        
        int heroX = hero.getX();
        int heroY = hero.getY();
        
        String checkMove;
        
        if(monsterX>heroX)
        {
           checkMove = monster.moveLeft(grid);
           
           if(!(checkMove.equals("NOT_MOVED")))
           {
               return checkMove;
           }
        }
        
        if(monsterX<heroX)
        {
           checkMove = monster.moveRight(grid);
           
           if(!(checkMove.equals("NOT_MOVED")))
           {
               return checkMove;
           }
        }
        
        if(monsterY>heroY)
        {
           checkMove = monster.moveUp(grid);
           
           if(!(checkMove.equals("NOT_MOVED")))
           {
               return checkMove;
           }
        }
        
        if(monsterY<heroY)
        {
           checkMove = monster.moveDown(grid);
           
           if(!(checkMove.equals("NOT_MOVED")))
           {
               return checkMove;
           }
        }
        
        return "NOT_MOVED";
    }
    
    public static void setTimer(int time)
    {
    	MainBoardController.gameTimer = time;
    }


    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        
    }
    
}