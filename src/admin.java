import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class admin{
    admin() throws ClassNotFoundException{
        JFrame frame=new JFrame("ADMIN LOGIN");
        JLabel label=new JLabel("ADMIN LOGIN");
        JLabel name=new JLabel("NAME: ");
        JLabel pass=new JLabel("PASSWORD: ");
        JTextField name_field=new JTextField();
        JPasswordField pass_field=new JPasswordField();
        JButton login=new JButton("LOGIN");
        
        frame.setSize(400, 400);
        frame.add(label);
        frame.add(name);
        frame.add(name_field);
        frame.add(pass);
        frame.add(pass_field);
        frame.add(login);
        frame.setVisible(true);
        frame.setLayout(new GridLayout(6, 0));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        Class.forName("com.mysql.jdbc.Driver");
        
        login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                try(Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/fees","root","contactmrnishantbansal@18")){
                    String query="select * from admins";
                    Statement stmt=con.createStatement();
                    ResultSet rst=stmt.executeQuery(query);
                    int fail=0;
                    while(rst.next()){
                        if(rst.getString(1).equals(name_field.getText())&&rst.getString(2).equals(pass_field.getText()))
                        {
                            JOptionPane.showMessageDialog(frame, "Login Successfully");
                            fail=0;
                            new adminMenu();
                            frame.setVisible(false);
                            break;
                        }
                        fail++;
                    }
                    if(fail>0)
                        JOptionPane.showMessageDialog(frame, "Record doesn't exists\nLogin Unsuccessful","Error",JOptionPane.ERROR_MESSAGE);
                    con.close();
                }catch(Exception r){
                    System.out.println("Exception in admin.java file on login button listener"+r);
                }
            }
        });
    }
}
