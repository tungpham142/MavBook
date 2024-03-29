package company_management.data;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
 
import company_management.bean.LoginBean;
import company_management.util.SQLConnection;
 
public class LoginDao {
	static SQLConnection DBMgr = SQLConnection.getInstance();
 
public String authenticateUser(LoginBean loginBean)
{
	 String userName = loginBean.getUserName();
	 String password = loginBean.getPassword();
	 
	 Statement statement = null;
	 ResultSet resultSet = null;
	 
	 String userNameDB = "";
	 String passwordDB = "";
	 String roleDB = "";
	 
	 try
	 {
		 Connection conn = SQLConnection.getDBConnection();  
		 statement = conn.createStatement();
		 resultSet = statement.executeQuery("select username,password,role from users");
	 
		 while(resultSet.next())
		 {
			 userNameDB = resultSet.getString("username");
			 passwordDB = resultSet.getString("password");
			 roleDB = resultSet.getString("role");
			 
			 if(userName.equals(userNameDB) && password.equals(passwordDB) && roleDB.equals("Admin"))
				 return "Admin_Role";
			 else if(userName.equals(userNameDB) && password.equals(passwordDB) && roleDB.equals("Manager"))
				 return "Manager_Role";
			 else if(userName.equals(userNameDB) && password.equals(passwordDB) && roleDB.equals("User"))
				 return "User_Role";
		 }
	 }
	 catch(SQLException e)
	 {
		 e.printStackTrace();
	 }
	 return "Invalid user credentials";
	}
}
