package jUnit;
import model.*;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PlayerTest {
	
	Player testPlayer;
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

		testPlayer = new Player(0, 0, numFood);
	}

	
	// Player class tests

		@Test // Check and see if player moved south successfully
		void movePlayerSouth()
		{
			int originalY = testPlayer.getY();
			testPlayer.moveDown(grid);
			assertEquals(originalY + 1, testPlayer.getY());
		}


		@Test // Set player position and check to see if player is at new set position
		void checkPosition() 
		{
			int setX = 5;
			int setY = testPlayer.getY();

			testPlayer.setX(setX);
			testPlayer.setY(setY);
			assertTrue((testPlayer.getX() == setX) && (testPlayer.getY() == setY));
		}

		@Test // Check player's food amount
		void checkFood()
		{
			assertEquals(2, testPlayer.getNumFood());
		}

		@Test // Places a food and checks amount of food left
		void placeFood()
		{
			testPlayer.placeFood(grid);
			assertEquals((numFood - 1), testPlayer.getNumFood());
		}

		@Test // Check and see if return statement is true when there is no food left
		void checkNoFoodLeft()
		{
			testPlayer.setNumFood(0);
			assertEquals("There is no food left!", testPlayer.placeFood(grid));
		}
	
}
