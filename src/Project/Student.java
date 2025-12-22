package Project;

import java.sql.*;
import javax.swing.JOptionPane;

public class Student {

    public void addStudent(String name, String fname, String adrs, String cntno, String gen, String gpa, String uname, String pswd) {
        try {
            Connection con = Connector.DBConnector();
            String sql1 = "insert into student_info(student_name,student_father_name,student_address,student_contact_no,student_gender,student_gpa,student_user_name,student_password) values (?,?,?,?,?,?,?,?)";
            PreparedStatement pstmt = con.prepareStatement(sql1);
            pstmt.setString(1, name);
            pstmt.setString(2, fname);
            pstmt.setString(3, adrs);
            pstmt.setString(4, cntno);
            pstmt.setString(5, gen);
            pstmt.setString(6, gpa);
            pstmt.setString(7, uname);
            pstmt.setString(8, pswd);
            pstmt.executeUpdate();
        }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void updateStd(String id,String name,String fname,String adrs,String cntno,String gen,String gpa){
        boolean flag=true;
        try{
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/firstproject","root","1qaz2wsx3edc4rfv");
            String sql="select * from student_info";
            Statement stmt=con.createStatement();
            ResultSet rs=stmt.executeQuery(sql);
            boolean cnd=false;
            while(rs.next()){
                String i=rs.getString(1);
                if(i.equals(id)){
                    if(name!=null && !name.isEmpty()){
                        String qry1="update student_info set student_name='"+name+"' where student_id='"+id+"'";
                        int ct1=stmt.executeUpdate(qry1);
                        cnd=true;
                    }
                    if(fname!=null && !fname.isEmpty()){
                        String qry2="update student_info set student_father_name='"+fname+"' where student_id='"+id+"'";
                        int ct2=stmt.executeUpdate(qry2);
                        cnd=true;
                    }
                    if(adrs!=null && !adrs.isEmpty()){
                        String qry3="update student_info set student_address='"+adrs+"' where student_id='"+id+"'";
                        int ct3=stmt.executeUpdate(qry3);
                        cnd=true;
                    }
                    if(cntno!=null && !cntno.isEmpty()){
                        String qry4="update student_info set student_contact_no='"+cntno+"' where student_id='"+id+"'";
                        int ct4=stmt.executeUpdate(qry4);
                        cnd=true;
                    }
                    if(gen!=null && !gen.isEmpty()){
                        String qry5="update student_info set student_gender='"+gen+"' where student_id='"+id+"'";
                        int ct5=stmt.executeUpdate(qry5);
                        cnd=true;
                    }
                    if(gpa!=null && !gpa.isEmpty()){
                        String qry6="update student_info set student_gpa='"+gpa+"' where student_id='"+id+"'";
                        int ct6=stmt.executeUpdate(qry6);
                        cnd=true;
                    }
                    if(cnd){
                        JOptionPane.showMessageDialog(null,"Data Updated.");
                    }
                    flag=false;
                    break;
                }
            }
            if(flag){
                JOptionPane.showMessageDialog(null,"Invalid ID!");
            }
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
        }
    }

}
