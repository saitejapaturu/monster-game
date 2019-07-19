package model;

public class Score 
{
	private int wins;
	private int losses;

	public Score() 
	{
		this.wins = 0;
		this.losses = 0;
	}

	public int getWins() 
	{
		return wins;
	}

	public int getLosses() 
	{
		return losses;
	}

	public void setWins(int wins) 
	{
		this.wins = wins;
	}

	public void setLosses(int losses) 
	{
		this.losses = losses;
	}

	public void addWin() 
	{
		this.wins++;
	}

	public void addLoss()
	{
		this.losses++;
	}
}
