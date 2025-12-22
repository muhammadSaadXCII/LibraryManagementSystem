package Project;

import java.sql.*;
import java.time.LocalDate;
import javax.swing.JOptionPane;
import java.time.format.DateTimeFormatter;

public class MainUI2 {

    public void updateodrissue(String sname, String bname) {
        try {
            Connection con=Connector.DBConnector();
            String qry1 = "select * from order_info";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(qry1);
            while (rs.next()) {
                int i = rs.getInt(1);
                String sn = rs.getString(2);
                String bn = rs.getString(3);
                String sts = rs.getString(6);
                if (sn.equals(sname) && bn.equals(bname) && sts.equals("Pending...")) {
                    String sql1 = "update order_info set order_status='Issued' where order_id='" + i + "'";
                    int cnt1 = stmt.executeUpdate(sql1);
                    break;
                }
            }
        }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void updateodrreturn(String sname, String bname) {
        try {
            Connection con=Connector.DBConnector();
            String qry2 = "select * from order_info";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(qry2);
            while (rs.next()) {
                int j = rs.getInt(1);
                String sn = rs.getString(2);
                String bn = rs.getString(3);
                String sts = rs.getString(6);
                if (sn.equals(sname) && bn.equals(bname) && sts.equals("Issued")) {
                    String sql2 = "update order_info set order_status='Returned' where order_id='" + j + "'";
                    int cnt2 = stmt.executeUpdate(sql2);
                    break;
                }
            }
        }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void updatercdreturn(String sname, String bname) {
        try {
            Connection con=Connector.DBConnector();
            String qry3 = "select * from record_info";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(qry3);
            while (rs.next()) {
                int k = rs.getInt(1);
                String sn = rs.getString(3);
                String bn = rs.getString(4);
                String sts = rs.getString(10);
                if (sn.equals(sname) && bn.equals(bname) && sts.equals("Issued")) {
                    String sql3 = "update record_info set record_status='Returned' where record_id='" + k + "'";
                    int cnt3 = stmt.executeUpdate(sql3);
                    break;
                }
            }
        }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void calcfine(String sname, String bname) {
        try {
            Connection con=Connector.DBConnector();
            String qry4 = "select * from record_info";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(qry4);
            long fine = 0;
            while (rs.next()) {
                int p = rs.getInt(1);
                String sn = rs.getString(3);
                String bn = rs.getString(4);
                String dd = rs.getString(8);
                String rd = rs.getString(9);
                String sts = rs.getString(10);
                if (sn.equals(sname) && bn.equals(bname) && sts.equals("Returned")) {
                    DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                    LocalDate dueDate = LocalDate.parse(dd, format);
                    LocalDate returnDate = LocalDate.parse(rd, format);
                    long daysLate = returnDate.toEpochDay() - dueDate.toEpochDay();
                    if (daysLate > 0) {
                        for (int i = 1; i <= daysLate; i++) {
                            fine = fine + 20;
                        }
                        JOptionPane.showMessageDialog(null, "Your Submisssion is " + daysLate + " days late.\nYour Total Fine is " + fine + " Rupees.");
                        String sql4 = "update record_info set record_fine='" + fine + "' where record_id='" + p + "'";
                        int cnt4 = stmt.executeUpdate(sql4);
                        break;
                    } else {
                        JOptionPane.showMessageDialog(null, "Book \"" + bname + "\" was Submitted before due date");
                        String sql5 = "update record_info set record_fine='" + fine + "' where record_id='" + p + "'";
                        int cnt5 = stmt.executeUpdate(sql5);
                        break;
                    }
                }
            }
        }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void setreturndate(String sname, String bname) {
        try {
            Connection con=Connector.DBConnector();
            String qry5 = "select * from record_info";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(qry5);
            LocalDate ld = LocalDate.now();
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            String returndt = ld.format(dtf);
            while (rs.next()) {
                int u = rs.getInt(1);
                String sn = rs.getString(3);
                String bn = rs.getString(4);
                String sts = rs.getString(10);
                if (sn.equals(sname) && bn.equals(bname) && sts.equals("Returned")) {
                    String sql6 = "update record_info set return_date='" + returndt + "' where record_id='" + u + "'";
                    int cnt6 = stmt.executeUpdate(sql6);
                    break;
                }
            }
        }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void deleteStd() {
        try {
            Connection con=Connector.DBConnector();
            String n = JOptionPane.showInputDialog("Please Enter ID :");
            if (n != null && !n.isEmpty()) {
                int id = Integer.parseInt(n);
                String qry = "delete from student_info where student_id='" + id + "'";
                PreparedStatement pstmt = con.prepareStatement(qry);
                int rowsAffected = pstmt.executeUpdate();
                if (rowsAffected > 0) {
                    JOptionPane.showMessageDialog(null, "Student Deleted Succcessfully!");
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid ID.");
                }
            } else {
                JOptionPane.showMessageDialog(null, "No ID entered. Deletion operation skipped.");
            }

        }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void deleteBook() {
        try {
            Connection con=Connector.DBConnector();
            String n = JOptionPane.showInputDialog("Please Enter ID :");
            if (n != null && !n.isEmpty()) {
                int id = Integer.parseInt(n);
                String qry = "delete from book_info where book_id='" + id + "'";
                PreparedStatement pstmt = con.prepareStatement(qry);
                int rowsAffected = pstmt.executeUpdate();
                if (rowsAffected > 0) {
                    JOptionPane.showMessageDialog(null, "Book Deleted Succcessfully!");
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid ID.");
                }
            } else {
                JOptionPane.showMessageDialog(null, "No ID entered. Deletion operation skipped.");
            }
        }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}
