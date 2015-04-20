package java_metro;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import java.sql.DriverManager;

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
        /*String host = System.getenv("OPENSHIFT_MYSQL_DB_HOST");
        String port = System.getenv("OPENSHIFT_MYSQL_DB_PORT");
        String username = System.getenv("OPENSHIFT_MYSQL_DB_USERNAME");
        String password = System.getenv("OPENSHIFT_MYSQL_DB_PASSWORD");

        String url = String.format(":mysql://%s:%s/jbossas", host, port);
        Connection conn = DriverManager.getConnection(url, username, password);*/
        
        
        SQLServerDataSource ds = new SQLServerDataSource();
        ds.setUser("sa");
        ds.setPassword("50918950");
        ds.setServerName("idea-PC");
        ds.setPortNumber(1433); 
        ds.setDatabaseName("005");
        con = ds.getConnection();

        // Execute a stored procedure that returns some data.
        cstmt = con.prepareCall(Call);
        rs = cstmt.executeQuery();

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