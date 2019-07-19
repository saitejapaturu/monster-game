package model;

public class Food extends Piece
{
	private int slowTime;
	private final int SLOW_STRENGTH = 2;

	public Food(int x, int y)
	{
		super(x, y);
		
	}

	public int getSlowTime()
	{
		return slowTime;
	}

	public int getSlowStrength()
	{
		return SLOW_STRENGTH;
	}

	public void setSlowTime(int slowTime)
	{
		this.slowTime = slowTime;
	}
}
