package Project;

import java.sql.*;
import javax.swing.JOptionPane;

public class MainUI {

    private String uname;
    private String pswd;
    boolean flag = true;

    public void setUname(String uname) {
        this.uname = uname;
    }

    public void setPswd(String pswd) {
        this.pswd = pswd;
    }

    public boolean stdlogin() {
        boolean cnd = false;
        try {
            Connection con=Connector.DBConnector();
            String qry1 = "select * from student_info";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(qry1);
            while (rs.next()) {
                String nm = rs.getString(2);
                String un = rs.getString(8);
                String pd = rs.getString(9);
                if (un.equals(uname) && pd.equals(pswd)) {
                    StdLogin sl = new StdLogin();
                    sl.setVisible(true);
                    sl.lblName.setText(nm);
                    JOptionPane.showMessageDialog(null, "Login Successfull!");
                    flag = false;
                    cnd = true;
                    break;
                }
            }
            if (flag && cnd) {
                JOptionPane.showMessageDialog(null, "Login Failed!");
            }
        }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return cnd;
    }

    public boolean admlogin() {
        boolean cnd = false;
        try {
            Connection con=Connector.DBConnector();
            String qry2 = "select * from admin_info";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(qry2);
            while (rs.next()) {
                String nm = rs.getString(2);
                String un = rs.getString(6);
                String pd = rs.getString(7);
                if (un.equals(uname) && pd.equals(pswd)) {
                    AdmLogin al = new AdmLogin();
                    al.setVisible(true);
                    al.lblName.setText(nm);
                    JOptionPane.showMessageDialog(null, "Login Successfull!");
                    flag = false;
                    cnd = true;
                    break;
                }
            }
            if (flag) {
                JOptionPane.showMessageDialog(null, "Login Failed!");
            }
        }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return cnd;
    }

}
