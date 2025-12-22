package Project;

import java.sql.*;

public class Connector {
    
    public static Connection DBConnector(){
        try{
            Connection db=DriverManager.getConnection("jdbc:mysql://localhost:3306/firstproject","root","1qaz2wsx3edc4rfv");
            return db;
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return null;
    }
}
