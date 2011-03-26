/**
 * This class is a data source for getting database connections.  It requires
 * a database.properties file with jdbc.username, jdbc.password, jdbc.driver
 * to be passed to it in order to create the connection.
 * @author Mitch
 * Learned from Big Java 5th edition by Cay Horstmann
 * pg 938
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class DataSource 
{
    // varaibles
    
    private static String url;
    private static String username;
    private static String password;

    // methods

    /**
     * This method initializes the data source
     * @param filename the name of the property file.
     * requires jdbc.username, jdbc.password, jdbc.driver in it.
     * @throws IOException if there are errors reading the file name
     * @throws ClassNotFoundException
     */
    public static void init(String filename)
            throws IOException, ClassNotFoundException
    {
        Properties properties = new Properties();
        FileInputStream fin = new FileInputStream(filename);
        properties.load(fin);
        
        String driver = properties.getProperty("jdbc.driver");
        url = properties.getProperty("jdbc.url");
        username = properties.getProperty("jdbc.username");
        password = properties.getProperty("jdbc.password");
        denull(url);
        denull(username);
        denull(password);
        if (driver != null)
        {
            Class.forName(driver);
        }
        
    }

    /**
     * This method replaces a null with a blank string.  If the string passed
     * is not null, it is returned the way it is.
     * @param possibleNull a string that might be null.
     * @return an empty string if the passed string was null, otherwise the
     * original string is returned unmolested.
     */
    private static String denull(String possibleNull)
    {
        if (possibleNull == null)
        {
            return "";
        }
        else
        {
            return possibleNull;
        }
    }

    public static Connection getConnection() throws SQLException
    {
        return DriverManager.getConnection(url, username, password);
    }
}
