package MAC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public abstract class Vars {

/* Developer: MAC
 * Date Develop: 8/15/21
 */
// Sql scheme
protected final String url = "jdbc:mysql://localhost:3306/ATM";
protected final String username = "marcthe5";
protected final String password = "Myloveones1";
protected static Connection connection;
protected PreparedStatement ps;
protected ResultSet rs;
protected static boolean SQLreturn = false;
protected static boolean SQLdep = false;


// Registration & Table
protected int id = 0;
protected String lname;
protected String fname;
protected String phone;
protected short mpin;
protected int mpinLength;




// Account
protected int cash;
protected int deposit;
protected long withdraw;




}
