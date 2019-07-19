package model;
import java.io.*;
import java.util.HashMap;
import exceptions.*;

public class UserList 
{
	private static HashMap<String, User> userList;
	private static UserList userListInstance = new UserList();
	private static final String filename = "users.txt";

	private  UserList()
	{
		userList = new HashMap<String, User>();
		populate();
	}

	
	public static UserList getInstance()
	{
		return userListInstance;
	}
	// Opens a file and populates the user list with it. Returns false if unsuccessful
	private boolean populate()
	{
		try {
			FileReader f = new FileReader(filename);
			BufferedReader b = new BufferedReader(f);
			
			String line = "";
			
			// Read every line of the file
			while ((line = b.readLine()) != null)
			{
				// Username and passwords are on a single line, separated by a space
				String[] tokens = line.split(" ");
				
				// Strip the newline off of the password (?)
				String password = tokens[1].substring(0, tokens[1].length());
				String userName = tokens[0];
				Boolean isAdmin = Boolean.valueOf(tokens[2]);
				int wins = Integer.valueOf(tokens[3]);
				int losses = Integer.valueOf(tokens[4]);
				
				// The constructor for User hashes the already hashed password, so it's reset here
				User user = new User(userName, password, isAdmin);
				user.setPasswordHash(password);
				// Set the rest of the attributes
				user.setWins(wins);
				user.setLosses(losses);
				
				UserList.userList.put(userName, user);
			}
			f.close();
			b.close();
		}
		catch (IOException e)
		{
			return false;
		}
		return true;
	}
	
	
	// Writes a new user to the user file 
	private void writeUser(User user)
	{
		try
		{
			// We pass true to FileWriter constructor to indicate that we are appending
			BufferedWriter b = new BufferedWriter(new FileWriter(filename, true));
			
			b.write(userString(user));
			b.close();
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	// Generates the output String for a given User
	private static String userString(User user)
	{
		// Pull the info from user
		String userName = user.getUserID();
		String password = user.getPassword();
		String isAdmin = String.valueOf(user.getIsAdmin());
		String wins = String.valueOf(user.getWins());
		String losses = String.valueOf(user.getLosses());
		
		// Line to be written
		return String.format("%s %s %s %s %s\n", userName, password, isAdmin, wins, losses);
	}
	
	
	// Writes the entirety of the userList to the file (not appending)
	// This must be called every time a User has any of its details updated, in order to
	// keep the user file up to date
	protected static void writeUserList()
	{
		try
		{
			// Giving false means it will completely overwrite the file if it exists
			BufferedWriter b = new BufferedWriter(new FileWriter(filename, false));
			String output = "";
			
			// Iterate for every key in userList
			for (String key : userList.keySet())
			{
				// Build the output string containing info for all users
				output += userString(userList.get(key));
			}
			b.write(output);
			b.close();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
	
	public boolean addUser(User user)
	{
		String userID = user.getUserID();
		
		// User already exists, don't add it
		if (userList.containsKey(userID)) 
		{
			return false;
		}
		else
		{
			userList.put(userID, user);
			writeUser(user);
			return true;
		}
	}

	
	// TODO: write whole userList to filename (not appending) everytime user removed
	public boolean removeUser(User user)
	{
		String userID = user.getUserID();

		if (!userList.containsKey(userID))
		{
			return false;
		}
		else
		{
			userList.remove(userID);
			writeUserList();
			return true;
		}
	}

	public boolean removeUser(String userID)
	{
		if (!userList.containsKey(userID))
		{
			return false;
		}
		else
		{
			userList.remove(userID);
			writeUserList();
			return true;
		}
	}

	public User getUser(String userID) throws NonExistentUserException
	{
		if (!userList.containsKey(userID))
		{
			String exceptionMsg = String.format("%s does not exist", userID);
			throw new NonExistentUserException(exceptionMsg);
		}
		else
		{
			return userList.get(userID);
		}
	}
	
	// for testing
	public void printUserList() {
		for (String key : userList.keySet())
		{
			User user = userList.get(key);
			String password = user.getPassword();
			int wins = user.getWins();
			int losses = user.getLosses();
			Boolean isAdmin = user.getIsAdmin();
			System.out.println(String.format("%s : %s || wins: %d losses: %d || isAdmin = %s",
					key, password, wins, losses, String.valueOf(isAdmin)));
		}
	}
}
