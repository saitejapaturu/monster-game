package jUnit;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.Cell;
import model.Food;
import model.Grid;
import model.Monster;
import model.MovingPiece;
import model.Player;

class MovingPieceTest {

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
	
	// Pieces & MovingPiece (Players & Monsters) class test

	@Test // Check and see if monster moved west successfully
	void moveMonsterWest()
	{
		int originalX = monster1.getX();
		monster1.moveLeft(grid);
		int afterX = monster1.getX();
		monster1.moveLeft(grid);
		assertEquals(originalX - 1, afterX);
	}
	
	@Test // Add a player into list and count the number of players
	void countPlayers()
	{
		pieceList.add(testPlayer);
		int count = 0;

		for (int i = 0; i < pieceList.size(); i++)
		{
			if (pieceList.get(i) instanceof Player)
			{
				count++;
			}
		}
		assertEquals(1, count);
	}

	@Test // Check to see if list is filled with monsters
	void checkMonsters()
	{
		pieceList.add(monster1);
		pieceList.add(monster2);

		for (int i = 0; i < pieceList.size(); i++)
		{
			assertTrue(pieceList.get(i) instanceof Monster);
		}
	}

	@Test // Add a monster into list and count the number of monsters
	void countMonsters()
	{
		pieceList.add(monster1);
		pieceList.add(monster2);
		int count = 0;

		for (int i = 0; i < pieceList.size(); i++)
		{
			if (pieceList.get(i) instanceof Monster)
			{
				count++;
			}
		}
		assertEquals(2, count);
	}

	@Test // Add all pieces into list and count the number of moving pieces
	void countMovingPieces()
	{
		pieceList.add(monster1);
		pieceList.add(monster2);
		pieceList.add(testPlayer);

		int count = 0;

		for (int i = 0; i < pieceList.size(); i++)
		{
			if (pieceList.get(i) instanceof MovingPiece)
			{
				count++;
			}
		}
		assertEquals(3, count);
	}
}
