package model;

public class UserListTester {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		UserList userList = UserList.getInstance();
		
		//Adding users
		
//		userList.addUser(new User("greg", "123"));
//		userList.addUser(new User("teja", "abc"));
		
		
		userList.printUserList();
		System.out.println("");
		
		
		userList.removeUser("greg");
		userList.printUserList();
	}

}
