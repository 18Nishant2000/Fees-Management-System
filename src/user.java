
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
import javax.sound.midi.SysexMessage;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class user {
    user() throws ClassNotFoundException{
        JFrame frame=new JFrame("USER LOGIN");
        JLabel label=new JLabel("USER LOGIN");
        JLabel name=new JLabel("NAME: ");
        JLabel pass=new JLabel("PASSWORD: ");
        JTextField name_field=new JTextField();
        JPasswordField pass_field=new JPasswordField();
        JButton login=new JButton("LOGIN");
        JButton new_user=new JButton("New USER?");
        
        frame.setSize(400, 400);
        frame.add(label);
        frame.add(name);
        frame.add(name_field);
        frame.add(pass);
        frame.add(pass_field);
        frame.add(login);
        frame.add(new_user);
        frame.setVisible(true);
        frame.setLayout(new GridLayout(7, 0));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        Class.forName("com.mysql.cj.jdbc.Driver");
        login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            try(Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/fees","root","contactmrnishantbansal@18");) {
            Statement stmt=con.createStatement();
            String query="select * from records";
            ResultSet rst=stmt.executeQuery(query);
            int fail=0;
            while(rst.next()){
                if(rst.getString(1).equals(name_field.getText()) && rst.getString(2).equals(pass_field.getText()))
                {
                    JOptionPane.showMessageDialog(frame, "Login successfully..");
                    new userDetails(name_field.getText(),pass_field.getText());
                    fail=0;
                    frame.setVisible(false);
                    break;
                }
                fail++;
            }
            if(fail>0)
                JOptionPane.showMessageDialog(frame, "Record not found\nLogin Unsuccessfull","Error",JOptionPane.ERROR_MESSAGE);
            con.close();
        } catch (Exception exception) {
            System.out.println("Exception caught in login button listener in user.java file");
        }
            }
        });
        
        new_user.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    new Registration("user");
                    frame.setVisible(false);
                } catch (ClassNotFoundException ex) {
                    System.out.println(e);
                }
            }
        });
    }
}
