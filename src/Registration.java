
import java.awt.GridLayout;
import java.sql.Connection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Registration {

    Registration(String s) throws ClassNotFoundException {
    
        JFrame frame=new JFrame("USER REGISTRATION");
        JLabel label=new JLabel("USER REGISTRATION");
        JLabel name=new JLabel("NAME: ");
        JLabel pass=new JLabel("PASSWORD: ");
        JTextField name_field=new JTextField();
        JPasswordField pass_field=new JPasswordField();
        JButton register=new JButton("REGISTER");
        JButton old_user=new JButton("ALREADY REGISTERED?");
        label.setBounds(30,10, 50, 30);
        name.setBounds(10,80,40,30);
        name_field.setBounds(70, 80, 100, 50);
        pass.setBounds(10, 150, 60, 30);
        pass_field.setBounds(100,150,100,50);
        register.setBounds(30, 250, 50, 40);
        old_user.setBounds(10, 330,100, 30);
        frame.setSize(400, 400);
        frame.add(label);
        frame.add(name);
        frame.add(name_field);
        frame.add(pass);
        frame.add(pass_field);
        frame.add(register);
        frame.add(old_user);
        frame.setVisible(true);
        frame.setLayout(new GridLayout(7, 0));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Class.forName("com.mysql.cj.jdbc.Driver");
        
        register.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
            try(Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/fees","root","contactmrnishantbansal@18");) {
            String query="insert into records(name,password)"+"values(?,?)";
            PreparedStatement pstmt=con.prepareStatement(query);
            pstmt.setString(1,name_field.getText());
            pstmt.setString(2, pass_field.getText());
            pstmt.execute();
            JOptionPane.showMessageDialog(frame, "Registered Successfully...");
            con.close();
            if(s.equals("admin"))
                new adminMenu();
            else
            new user();
            frame.setVisible(false);
        } catch (Exception exception) {
            System.err.println(exception);
        }         
            
            }
        });
        
        old_user.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    new user();
                    frame.setVisible(false);
                } catch (ClassNotFoundException ex) {
                    System.out.println(ex);
                }
            }
        });
    }  
    
}
