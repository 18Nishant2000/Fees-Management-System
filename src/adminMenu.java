
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
                    String name=(String)JOptionPane.showInputDialog(frame,"Enter name of the student");
                    String query="select * from records";
                    Statement stmt1=con.createStatement();
                    ResultSet rst=stmt1.executeQuery(query);
                    while(rst.next()){
                        if(rst.getString(1).equals(name))
                        {
                            query="delete from records where name=\""+name+"\"";
                            Statement stmt2=con.createStatement();
                            stmt2.execute(query);
                            JOptionPane.showMessageDialog(frame,"Record deleted successfully....");
                            break;
                        }
                        else{
                            JOptionPane.showMessageDialog(frame,"Record not exixts","Error",JOptionPane.ERROR_MESSAGE);
                            break;
                        }
                    }
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
                    String query="update records set name=\"Nishant\" where name=\"hello\"";
                    Statement stmt=con.createStatement();
                    stmt.execute(query);
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
                    String query="select * from records where name=\"abc\"";
                    Statement stmt=con.createStatement();
                    ResultSet rst=stmt.executeQuery(query);
                    frame.removeAll();
                    int size=0;
                    String[] heads={"Name","Password"};
                    String[][] data=new String[2][2];
                    while(rst.next()){
                        data[0][0]=rst.getString(1);
                        data[0][1]=rst.getString(2);
                    }
                    JTable table=new JTable(data,heads);
                    table.setBounds(10, 10, 300, 200);
                    frame.add(table);
                    frame.setSize(400, 400);
                    frame.setLayout(null);
                    frame.setVisible(true);
                    con.close();
                }catch(Exception ex){
                    System.out.println(ex);
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
                    while(rst.next()){
                        System.out.println(rst.getString(1)+" "+rst.getString(2));
                    }
                    con.close();
                }catch(Exception ex){
                    System.out.println(ex);
                }
            }
        });
        
        
        logout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
            }
        });      
        
    }    
}
