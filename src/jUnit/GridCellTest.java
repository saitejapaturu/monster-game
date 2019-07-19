package jUnit;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import exceptions.PlacingOnHollowException;
import model.Cell;
import model.Food;
import model.Grid;
import model.Monster;
import model.MovingPiece;
import model.Player;

class GridCellTest {

	int movementSpeed, numFood;
	Grid grid;
	Cell[][] board;
	ArrayList<MovingPiece> pieceList;
	Player testPlayer;
	Food food;
	Monster monster1, monster2;

	@BeforeEach
	public void setUp() throws Exception
	{
		movementSpeed = 20;
		numFood = 2;

		grid = new Grid(11,11);
		board = grid.getGrid();

		pieceList = new ArrayList<MovingPiece>();

		testPlayer = new Player(0, 0, numFood);
		food = new Food(0, 1);
		monster1 = new Monster(10, 10, movementSpeed);
		monster2 = new Monster(0, 10, movementSpeed);
	}

	// Grid & Cell class tests

	@Test // Check and see if grid is filled with empty cells
	void checkBoardEmpty()
	{
		for (int i = 0; i < grid.getHeight(); i++)
		{
			for (int j = 0; j < grid.getWidth(); j++)
			{
				Cell tempCell = grid.getGrid()[j][i];
				assertTrue(tempCell.getPiece() == null);
			}
		}
	}
	
	@Test // Set player to be in cell (0,0) and check
	void checkCellPlayerPiece()
	{
		try {
			grid.setCellPiece(0, 0, testPlayer);
		} catch (PlacingOnHollowException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertTrue(grid.getCellPiece(0, 0) instanceof Player);
	}
}
