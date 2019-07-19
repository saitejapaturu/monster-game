package model;

public class User 
{
	private String userID;
	private String password;
	private Score score;
	private final boolean isAdmin;

	public User(String userID, String password)
	{
		isAdmin = false;
		this.userID = userID;
		this.password = String.valueOf(password.hashCode());
		this.score = new Score();
	}

	public User(String userID, String password, boolean isAdmin)
	{
		this.userID = userID;
		this.password = String.valueOf(password.hashCode());
		this.isAdmin = isAdmin;
		this.score = new Score();
	}

	public boolean getIsAdmin()
	{
		return isAdmin;
	}

	public String getUserID()
	{
		return userID;
	}

	public String getPassword()
	{
		return password;
	}

	public int getWins()
	{
		return score.getWins();
	}

	public int getLosses()
	{
		return score.getLosses();
	}

	public void setUserID(String userID)
	{
		this.userID = userID;
		UserList.writeUserList();
	}

	public void setPassword(String password)
	{
		this.password = String.valueOf(password.hashCode());
		UserList.writeUserList();
	}
	
	public void setPasswordHash(String password)
	{
		this.password = password;
		UserList.writeUserList();
	}

	public void addWin()
	{
		score.addWin();
		UserList.writeUserList();
	}

	public void addLoss()
	{
		score.addLoss();
		UserList.writeUserList();
	}
	
	public void setWins(int wins)
	{
		score.setWins(wins);
		UserList.writeUserList();
	}
	
	public void setLosses(int losses)
	{
		score.setLosses(losses);
		UserList.writeUserList();
	}
}
