package java_metro;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import com.microsoft.sqlserver.jdbc.SQLServerDataSource;

public class DBConnect {
    
    private static CallableStatement cstmt;
    private static Connection con;
    private static ResultSet rs = null;
    
    public static ResultSet connect(String Call) {

    // Declare the JDBC objects.
    //Connection con;
    //CallableStatement cstmt;
    //ResultSet rs = null;

    try {
        // Establish the connection. 
        SQLServerDataSource ds = new SQLServerDataSource();
        ds.setUser("sa");
        ds.setPassword("50918950");
        ds.setServerName("idea-PC");
        ds.setPortNumber(1433); 
        ds.setDatabaseName("005");
        con = ds.getConnection();

        // Execute a stored procedure that returns some data.
        cstmt = con.prepareCall(Call);
        //cstmt.setInt(1, 50);
        rs = cstmt.executeQuery();

        // Iterate through the data in the result set and display it.
        /*while (rs.next()) {
            System.out.println( rs.getInt("ID") + ", " + rs.getString(2) +"  "+ rs.getString(3));
            System.out.println();
        }*/
    }

    // Handle any errors that may have occurred.
    catch (Exception e) {
        e.printStackTrace();
    }
    return rs;
    }
    
    public static void disconnectFromDB(){

    try{
        if (rs != null){rs.close();}
        if (cstmt != null){cstmt.close();}
        if (con != null){con.close();}
    }
        catch (Exception ex)
        {
            System.out.println("Unable to close connection");
        }
    }
}