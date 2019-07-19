package model;

import exceptions.PlacingOnHollowException;

public abstract class MovingPiece extends Piece
{
	private boolean poisonStatus;

	public MovingPiece(int x, int y)
	{
		super(x, y);
		this.setPoisonStatus(false);
	}

	
	public boolean getPoisonStatus()
    {
        return poisonStatus;
    }

    public void setPoisonStatus(boolean poisonStatus)
    {
        this.poisonStatus = poisonStatus;
    }

    public String moveLeft(Grid grid) 
	{
        return validateMove(grid, this.getX()-1, this.getY());
    }
	
	public String moveRight(Grid grid) 
	{
        return validateMove(grid,  this.getX()+1, this.getY());
    }
	
	public String moveUp(Grid grid) 
	{
        return validateMove(grid, this.getX(), this.getY()-1);
    }
	
	public String moveDown(Grid grid) 
	{
        return validateMove(grid, this.getX(), this.getY()+1);
    }
	
	public String validateMove(Grid grid, int nextX, int nextY)
    {
	    int currentX = this.getX();
	    int currentY = this.getY();
	    
	    if(nextX<0 || nextX>10 || nextY<0 || nextY>10)
	    {
            System.out.println("Can't move outside Bounds");
	        return "NOT_MOVED";
	    }
        if (grid.getCell(nextX, nextY).getType())
        {
            // Updating player pos
            this.setX(nextX);
            this.setY(nextY);

            // If next piece is food
            if (grid.getCellPiece(nextX, nextY) instanceof Food)
            {
                
             // Old pos is now null
                try
                {
                    grid.setCellPiece(currentX, currentY, null);
                } catch (PlacingOnHollowException e)
                {
                    e.printStackTrace();
                }

                // Setting new pos on board
                try
                {
                    grid.setCellPiece(nextX, nextY, this);
                } catch (PlacingOnHollowException e)
                {
                    e.printStackTrace();
                }
                
                return "POISONED";

            }

            // If next piece is monster
            if ((grid.getCellPiece(nextX, nextY) instanceof Monster && this instanceof Player) || (grid.getCellPiece(nextX, nextY) instanceof Player && this instanceof Monster))
            {
                return "GAMEOVER";
            }
            
         // Old pos is now null
            //If player drops food, when he moves the old position i.e. the position with poison is not set to null.
            try
            {
                if(grid.getCellPiece(currentX, currentY) instanceof Food)
                {
                    System.out.println("Food is active.");
                }
                else
                {
                    grid.setCellPiece(currentX, currentY, null);
                }
            } catch (PlacingOnHollowException e)
            {
                e.printStackTrace();
            }

            // Setting new pos on board
            try
            {
                grid.setCellPiece(nextX, nextY, this);
            } catch (PlacingOnHollowException e)
            {
                e.printStackTrace();
            }
            return "MOVED";
        }

        if(this instanceof Player)
        {
            System.out.println("Can't move to hollow spaces.");
        }
        return "NOT_MOVED";

    }
	
}
