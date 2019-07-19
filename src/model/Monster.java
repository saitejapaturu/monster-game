package model;

public class Monster extends MovingPiece
{ 

    private int movementTime;
    
    public Monster(int x, int y, int movementTime)
    {
        super(x, y);
        this.movementTime = movementTime;
    }

    public int getMovementTime()
    {
        return movementTime;
    }

    public void setMovementTime(int newSpeed)
    {
        this.movementTime = newSpeed;
    }
}
