
import java.awt.GridLayout;
import java.sql.Connection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
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
            String query="insert into records(name,password,no_of_days,paid,fine,total,balance)"+"values(?,?,?,?,?,?,?)";
            PreparedStatement pstmt=con.prepareStatement(query);
            pstmt.setString(1,name_field.getText());
            pstmt.setString(2, pass_field.getText());
            pstmt.setInt(3,java.time.LocalDate.now().getDayOfMonth());
            pstmt.setFloat(4, 0);
            pstmt.setInt(5, 0);
            pstmt.setFloat(6, 1000);
            pstmt.setFloat(7, 1000);
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
