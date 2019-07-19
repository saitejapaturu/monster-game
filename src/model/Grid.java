package model;
import exceptions.*;

public class Grid
{
	final static int GRID_WIDTH = 11;
	final static int GRID_HEIGHT = 11;
	Cell[][] board;

	public Grid(int GRID_WIDTH, int GRID_HEIGHT)
	{
		this.board = new Cell[GRID_HEIGHT][GRID_WIDTH];
		initBoard();
	}
	
	public int getWidth() {
		return GRID_WIDTH;
	}
	
	public int getHeight() {
		return GRID_HEIGHT;
	}
	
	public Cell[][] getGrid()
	{
		return board;
	}

	public Cell getCell(int x, int y)
	{
		return board[y][x];
	}

	public Piece getCellPiece(int x, int y)
	{
		return board[y][x].getPiece();
	}

	public void setCell(int x, int y, Cell cell)
	{
		board[y][x] = cell;
	}

	public void setCellPiece(int x, int y, Piece piece) throws PlacingOnHollowException
	{
		board[y][x].setPiece(piece);
	}
	
	private void initBoard()
	{
		for (int i=0; i < GRID_WIDTH; i++)
		{
			for (int j=0; j < GRID_HEIGHT; j++)
			{
				board[j][i] = new Cell(true);
			}
		}
		
		for (int i=1; i < 5; i++)
		{
		    for (int j=1; j<5; j++)
		    {
		        board[j][i] = new Cell(false);
		    }
		    
		    for (int j=6; j<10; j++)
		    {
		        board[j][i] = new Cell(false);
		    }
		}
		
		for (int i=6; i < 10; i++)
        {
            for (int j=1; j<5; j++)
            {
                board[j][i] = new Cell(false);
            }
            
            for (int j=6; j<10; j++)
            {
                board[j][i] = new Cell(false);
            }
        }
	}
}