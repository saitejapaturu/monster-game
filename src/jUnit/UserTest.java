package jUnit;
import model.*;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import exceptions.NonExistentUserException;
import model.User;

class UserTest {
	
	UserList userList = UserList.getInstance();
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	
	// User & UserList class tests

		@Test // Check to see if User added is an Admin
		void checkAdmin()
		{
			User user = new User("admin", "123", true);
			assertTrue(user.getIsAdmin());
		}

		@Test // Attempt to add a user with a duplicate ID to the userList
		void addDuplicateUser()
		{
			User user1 = new User("account1", "123");
			User user2 = new User("account1", "123");

			userList.addUser(user1);
			assertFalse(userList.addUser(user2));
		}

		@Test // Add wins and losses to a User
		void addWinsLosses()
		{
			User user = new User("account0", "123");
			user.addWin();
			user.addLoss();
			assertTrue(user.getWins() == 1 && user.getLosses() == 1);
		}

		@Test // Attempt to get a userID that doesn't exist
		void getNonExistentUser()
		{
			assertThrows(NonExistentUserException.class, ()->{userList.getUser("account");} );
		}

		@Test // Attempt to remove a userID that doesn't exist in the userList
		void removeNonExistentUser()
		{
			assertFalse(userList.removeUser("account"));
		}


}
