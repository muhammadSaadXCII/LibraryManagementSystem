package Project;

import java.sql.*;
import javax.swing.JOptionPane;

public class Book {

    public void addBook(String bname, String aname, String edtn, String lge, String pbhy) {
        try {
            Connection con=Connector.DBConnector();
            String qry = "insert into book_info(book_name,book_author_name,book_edition,book_language,book_publish_year,book_status) values ('" + bname + "','" + aname + "','" + edtn + "','" + lge + "','" + pbhy + "','Available')";
            Statement stmt = con.createStatement();
            stmt.executeUpdate(qry);
            JOptionPane.showMessageDialog(null, "Book Added.");
        }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void issueBook(String aname, String sname, String bname, String edtn, String lge, String issuedt, String duedt) {
        boolean flag = true;
        try {
            Connection con=Connector.DBConnector();
            String qry2 = "select * from book_info";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(qry2);
            while (rs.next()) {
                int i = rs.getInt(1);
                String bn = rs.getString(2);
                String sts = rs.getString(7);
                if (bn.toLowerCase().equals(bname.toLowerCase()) && sts.equals("Not Available")) {
                    JOptionPane.showMessageDialog(null, "Book has already been Issued.");
                    flag = false;
                    break;
                }
                if (bn.toLowerCase().equals(bname.toLowerCase()) && sts.equals("Available")) {
                    MainUI2 mui2 = new MainUI2();
                    JOptionPane.showMessageDialog(null, "Book Issued.");
                    String sql1 = "update book_info set book_status='Not Available' where book_id='" + i + "'";
                    String sql2 = "insert into record_info(admin_name,student_name,book_name,book_edition,book_language,issue_date,due_date,record_status) values ('" + aname + "','" + sname + "','" + bname + "','" + edtn + "','" + lge + "','" + issuedt + "','" + duedt + "','Issued')";
                    int count = stmt.executeUpdate(sql1);
                    stmt.executeUpdate(sql2);
                    mui2.updateodrissue(sname, bname);
                    flag = false;
                    break;
                }
            }
            if (flag) {
                JOptionPane.showMessageDialog(null, "Book does not Exist.");
            }
        }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void returnBook(String sname, String bname, String edtn, String lge) {
        boolean flag = true;
        try {
            Connection con=Connector.DBConnector();
            String qry6 = "select * from book_info";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(qry6);
            while (rs.next()) {
                int j = rs.getInt(1);
                String bn = rs.getString(2);
                String sts = rs.getString(7);
                if (bname.toLowerCase().equals(bn.toLowerCase()) && sts.equals("Not Available")) {
                    MainUI2 mui2 = new MainUI2();
                    JOptionPane.showMessageDialog(null, "Book Returned.");
                    String sql1 = "update book_info set book_status='Available' where book_id='" + j + "'";
                    int count = stmt.executeUpdate(sql1);
                    mui2.updateodrreturn(sname, bname);
                    mui2.updatercdreturn(sname, bname);
                    mui2.setreturndate(sname, bname);
                    mui2.calcfine(sname, bname);
                    flag = false;
                    break;
                }
            }
            if (flag) {
                JOptionPane.showMessageDialog(null, "Invalid Book!");
            }
        }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void placeOrder(String name, String bname, String edtn, String lge) {
        boolean flag = true;
        try {
            Connection con=Connector.DBConnector();
            String qry7 = "select * from book_info";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(qry7);
            while (rs.next()) {
                String bn = rs.getString(2);
                if (bname.toLowerCase().equals(bn.toLowerCase())) {
                    String sql = "insert into order_info(student_name,book_name,book_edition,book_language,order_status) values ('" + name + "','" + bname + "','" + edtn + "','" + lge + "','Pending...')";
                    int count = stmt.executeUpdate(sql);
                    JOptionPane.showMessageDialog(null, "Order Placed.");
                    flag = false;
                    break;
                }
            }
            if (flag) {
                JOptionPane.showMessageDialog(null, "Book does not Exist.");
            }
        }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void viewOrder1() {
        try {
            Connection con=Connector.DBConnector();
            String qry8 = "select * from order_info";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(qry8);
            String order = "";
            String temp = "";
            while (rs.next()) {
                String name = rs.getString(2);
                String bname = rs.getString(3);
                String sts = rs.getString(6);
                if (sts.equals("Pending...")) {
                    temp = name + " orderd the book \"" + bname + "\"";
                    order += temp + "\n";
                }
            }
            JOptionPane.showMessageDialog(null, order);
        }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void viewOrder2(String sname) {
        try {
            Connection con=Connector.DBConnector();
            String qry8 = "select * from order_info";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(qry8);
            String order = "";
            String temp = "";
            while (rs.next()) {
                String name = rs.getString(2);
                String bname = rs.getString(3);
                String sts = rs.getString(6);
                if (name.equals(sname) && sts.equals("Pending...")) {
                    temp = name + " orderd the book \"" + bname + "\"";
                    order += temp + "\n";
                }
            }
            JOptionPane.showMessageDialog(null, order);
        }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void updateBook(String id, String bname, String aname, String edtn, String lge, String pbhy) {
        boolean flag = true;
        try {
            Connection con=Connector.DBConnector();
            String sql = "select * from book_info";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            boolean cnd = false;
            while (rs.next()) {
                String i = rs.getString(1);
                if (i.equals(id)) {
                    if (bname != null && !bname.isEmpty()) {
                        String qry1 = "update book_info set book_name='" + bname + "' where book_id='" + id + "'";
                        int ct1 = stmt.executeUpdate(qry1);
                        cnd = true;
                    }
                    if (aname != null && !aname.isEmpty()) {
                        String qry2 = "update book_info set book_author_name='" + aname + "' where book_id='" + id + "'";
                        int ct2 = stmt.executeUpdate(qry2);
                        cnd = true;
                    }
                    if (edtn != null && !edtn.isEmpty()) {
                        String qry3 = "update book_info set book_edition='" + edtn + "' where book_id='" + id + "'";
                        int ct3 = stmt.executeUpdate(qry3);
                        cnd = true;
                    }
                    if (lge != null && !lge.isEmpty()) {
                        String qry4 = "update book_info set book_language='" + lge + "' where book_id='" + id + "'";
                        int ct4 = stmt.executeUpdate(qry4);
                        cnd = true;
                    }
                    if (pbhy != null && !pbhy.isEmpty()) {
                        String qry5 = "update book_info set book_publish_year='" + pbhy + "' where book_id='" + id + "'";
                        int ct5 = stmt.executeUpdate(qry5);
                        cnd = true;
                    }
                    if (cnd) {
                        JOptionPane.showMessageDialog(null, "Data Updated.");
                    }
                    flag = false;
                    break;
                }
            }
            if (flag) {
                JOptionPane.showMessageDialog(null, "Invalid ID!");
            }
        }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void showissueBook(String sname) {
        try {
            Connection con=Connector.DBConnector();
            String sql2 = "select * from record_info";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql2);
            String line = "";
            String temp = "";
            while (rs.next()) {
                String sn = rs.getString(3);
                String bname = rs.getString(4);
                String duedt = rs.getString(8);
                String sts = rs.getString(10);
                if (sn.equals(sname) && sts.equals("Issued")) {
                    temp = "You were Issued the Book \"" + bname + "\" till " + duedt;
                    line += temp + "\n";
                }
            }
            JOptionPane.showMessageDialog(null, line);
        }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

}
