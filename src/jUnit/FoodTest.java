package jUnit;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.Cell;
import model.Food;
import model.Grid;
import model.Player;

	


class FoodTest {
	
	Player testPlayer;
	Food food;
	
	int numFood;
	int movementSpeed;
	Grid grid;
	Cell[][] board;
	
	
	@BeforeEach
	public void setUp() throws Exception
	{
		grid = new Grid(11,11);
		board = grid.getGrid();
		
		movementSpeed = 20;
		numFood = 2;
		
		food = new Food(0, 1);
		Player testPlayer = new Player(0, 0, numFood);
	}
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@Test // Move player and check change in player speed when player steps on food
	void playerOnFood()
	{
		// Move player south from (0,0) to food at (0,1)
		testPlayer.moveDown(grid);

		// If player position == food position, set new movement speed = current speed / food slow strength
		if ((testPlayer.getX() == food.getX()) && (testPlayer.getY() == food.getY()))
		{
			testPlayer.setAliveStatus(false);;
		}
		assertEquals(true, testPlayer.getAliveStatus());
	}

	

}
