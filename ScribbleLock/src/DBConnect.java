import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnect {
    //connection as class variable
    private Connection connection = null;
    //connection show
    public DBConnect(){
        connection = getConnection();
        System.out.println("Database connection successful");
    }
    //gets connection + return
    public Connection getConnection(){
        //if no connection, get one
        if(connection == null){
            try{
                //call JDBC SQLite driver
                Class.forName("org.sqlite.JDBC");
                connection = DriverManager.getConnection("jdbc:sqlite:PasswordGUI.db");
                return connection;
            } catch (Exception e){
                System.err.println(e.getClass().getName() + ": " + e.getMessage());
                System.exit(0);
                return null;
            }
        }else{
            return connection;
        }
    }

}
