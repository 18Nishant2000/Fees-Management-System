
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;


public class adminMenu {
    adminMenu() throws ClassNotFoundException{
        JFrame frame=new JFrame("Admin window");
        JButton add=new JButton("Add Student");
        JButton remove=new JButton("Remove Student");
        JButton edit=new JButton("Edit Student");
        JButton view=new JButton("View Student");
        JButton whole=new JButton("View All");
        JButton logout=new JButton("LOGOUT");
        add.setBounds(40, 20, 150, 20);
        remove.setBounds(40, 90, 150, 20);
        edit.setBounds(40, 160, 150, 20);
        view.setBounds(40, 230, 150, 20);
        whole.setBounds(40, 300, 150, 20);
        logout.setBounds(40, 370, 150, 20);
        frame.setSize(400,400);
        frame.setVisible(true);
        frame.add(add);
        frame.add(remove);
        frame.add(edit);
        frame.add(view);
        frame.add(whole);
        frame.add(logout);
        frame.setLayout(new GridLayout(6, 0));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JFrame f=new JFrame();
        f.setSize(400,400);
        f.setLayout(new GridLayout());
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Class.forName("com.mysql.cj.jdbc.Driver");
        
        add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    new Registration();
                } catch (ClassNotFoundException ex) {
                    System.out.println("Exception occurs in adminMenu.java in add button listener");
                }
            }
        });
        
        remove.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try(Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/fees","root","contactmrnishantbansal@18");) {
                    String name=JOptionPane.showInputDialog(frame,"Enter name");
                    String pass=JOptionPane.showInputDialog(frame,"Enter password");
                    String query="select * from records";
                    Statement stmt1=con.createStatement();
                    ResultSet rst=stmt1.executeQuery(query);
                    int fail=0;
                    while(rst.next()){
                        if(rst.getString(1).equals(name)&&rst.getString(2).equals(pass))
                        {
                            query="delete from records where name=\""+name+"\"";
                            Statement stmt2=con.createStatement();
                            stmt2.execute(query);
                            JOptionPane.showMessageDialog(frame,"Record deleted successfully....");
                            fail=0;
                            break;
                        }
                        fail++;
                    }
                    if(fail>0)
                        JOptionPane.showMessageDialog(frame, "Record doesn't exists","Error",JOptionPane.ERROR_MESSAGE);
                    con.close();
                }catch(Exception ex){
                    System.out.println("Exception occured at remove button listener in admnMenu.java file");
                }
            }
        });
        
        edit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try(Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/fees","root","contactmrnishantbansal@18");){
                    String old_name=JOptionPane.showInputDialog(frame,"Enter name");
                    String old_pass=JOptionPane.showInputDialog(frame,"Enter pass");
                    String query="select * from records";
                    Statement stmt1=con.createStatement();
                    ResultSet rst=stmt1.executeQuery(query);
                    int fail=0;
                    while(rst.next()){
                        if(rst.getString(1).equals(old_name)&&rst.getString(2).equals(old_pass))
                        {
                            String new_name=JOptionPane.showInputDialog(frame,"Enter new name");
                            String new_pass=JOptionPane.showInputDialog(frame,"Enter new pass");
                            query="update records set name=\""+new_name+"\",password=\""+new_pass+"\" where name=\""+old_name+"\" and password=\""+old_pass+"\"";
                            Statement stmt2=con.createStatement();
                            stmt2.execute(query);
                            JOptionPane.showMessageDialog(frame, "Record updated successfully.....");
                            fail=0;
                            break;
                        }   
                        fail++;
                    }
                    if(fail>0)
                        JOptionPane.showMessageDialog(frame, "Record not exists","Error",JOptionPane.ERROR_MESSAGE);
                   con.close();
                }catch(Exception ex){
                    System.out.println(ex);
                }
            }
        });
        
        view.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try(Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/fees","root","contactmrnishantbansal@18");){
                    String name=JOptionPane.showInputDialog(frame,"Enter name");
                    String pass=JOptionPane.showInputDialog(frame,"Enter password");
                    String query="select * from records";
                    Statement stmt1=con.createStatement();
                    ResultSet rst=stmt1.executeQuery(query);
                    int fail=0;
                    String result="";
                    while(rst.next()){
                        if(rst.getString(1).equals(name)&&rst.getString(2).equals(pass))
                        {
                            result+=("Name: "+rst.getString(1)+" Password: "+rst.getString(2));
                            JLabel label=new JLabel(result);
                            JOptionPane.showMessageDialog(frame, result);
                            fail=0;
                            break;
                        }
                        fail++;    
                    }
                    if(fail>0)
                        JOptionPane.showMessageDialog(frame,"Record doesn't exists","Error",JOptionPane.ERROR_MESSAGE);
                    con.close();
                }catch(Exception ex){
                    System.out.println("Exception occured in view button listener in adminMenu.java file");
                }
            }
        });
        
        whole.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try(Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/fees","root","contactmrnishantbansal@18");){
                    String query="select * from records";
                    Statement stmt=con.createStatement();
                    ResultSet rst=stmt.executeQuery(query);
                    int fail=0;
                    String result="";
                    while(rst.next()){
                        result+=("Name: "+rst.getString(1)+" Password: "+rst.getString(2)+"\n");
                        fail++;
                    }
                    JLabel label=new JLabel(result);
                    JOptionPane.showMessageDialog(frame, result);
                    if(fail==0)
                        JOptionPane.showMessageDialog(frame,"Empty database","Error",JOptionPane.ERROR_MESSAGE);
                    con.close();
                }catch(Exception ex){
                    System.out.println("Exception occured in whole button listener in adminMenu.java file");
                }
            }
        });
        
        
        logout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new welcome();
            }
        });      
        
    }    
}
