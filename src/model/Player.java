package model;

import exceptions.PlacingOnHollowException;
public class Player extends MovingPiece
{
    private int numFood;
    private Food poison;
    private boolean isAlive;
    

    public Player(int x, int y, int numFood)
    {
        super(x, y);
        this.numFood = numFood;
        this.isAlive = true;
        poison = new Food(0,0);
    }

    public Boolean placeFood(Grid grid)
    {
        if (this.numFood <= 0)
        {
            System.out.println("No poison left to place!");
            return false;
        } 
        
        else
        {
            try
            {
                grid.setCellPiece(poison.getX(), poison.getY(), null);
                grid.setCellPiece(this.getX(), this.getY(), poison);
                
                poison.setX(this.getX());
                poison.setY(this.getY());
                
            } catch (PlacingOnHollowException e)
            {
                e.printStackTrace();
            }
            
            numFood--;
            System.out.println("Poison droped! " + numFood + " poison are left with player");
            return true;
        }
        
    }

    public int getNumFood()
    {
        return numFood;
    }

    public void setNumFood(int newFood)
    {
        this.numFood = newFood;
    }
    
    public Boolean getAliveStatus()
    {
        return isAlive;
    }
    
    public void setAliveStatus(Boolean isStatus)
    {
        this.isAlive = isAlive;
    }
    
    public Food getPoison()
    {
        return poison;
    }

}
