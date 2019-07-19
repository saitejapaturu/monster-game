package model;
import exceptions.*;

public class Cell
{
	private Piece piece;
	private boolean placeable;

	public Cell(boolean placeable)
	{
		this.placeable = placeable;
		piece = null;
	}

	public Piece getPiece()
	{
		return piece;
	}

	public void setPiece(Piece piece) throws PlacingOnHollowException
	{
		if (placeable)
		{
			this.piece = piece;
		}
		else
		{
			throw new PlacingOnHollowException("Tried to place piece on hollow cell");
		}
	}

	public boolean getType()
	{
		return placeable;
	}

	public void setType(boolean placeable)
	{
		this.placeable = placeable;
	}
}
