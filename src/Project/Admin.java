package Project;

import java.sql.*;

public class Admin {
    
    public void addAdmin(String name,String adrs,String cntno,String qlft,String uname,String pswd){
        try{
            Connection con=Connector.DBConnector();
            String sql2="insert into admin_info(admin_name,admin_address,admin_contact_no,admin_qualification,admin_user_name,admin_password) values (?,?,?,?,?,?)";
            PreparedStatement pstmt=con.prepareStatement(sql2);
            pstmt.setString(1,name);
            pstmt.setString(2,adrs);
            pstmt.setString(3,cntno);
            pstmt.setString(4,qlft);
            pstmt.setString(5,uname);
            pstmt.setString(6,pswd);
            pstmt.executeUpdate();
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
        }
    }
}
