
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class admin {
    admin(){
        JFrame frame=new JFrame("ADMIN LOGIN");
        JLabel label=new JLabel("ADMIN LOGIN");
        JLabel name=new JLabel("NAME: ");
        JLabel pass=new JLabel("PASSWORD: ");
        JTextField name_field=new JTextField();
        JPasswordField pass_field=new JPasswordField();
        JButton login=new JButton("LOGIN");
        label.setBounds(30,10, 50, 30);
        name.setBounds(10,80,40,30);
        name_field.setBounds(70, 80, 100, 50);
        pass.setBounds(10, 150, 60, 30);
        pass_field.setBounds(100,150,100,50);
        login.setBounds(30, 250, 50, 40);
        frame.setSize(400, 400);
        frame.add(login);
        frame.add(pass_field);
        frame.add(pass);
        frame.add(name_field);
        frame.add(name);
        frame.add(label);
        frame.setVisible(true);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        String user_name="Nishant";
        String user_password="123";
        login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(user_name.equals(name_field.getText()) && user_password.equals(pass_field.getText()))
                    System.out.println("Login successfully");
                else
                    System.out.println("Login Failed");
            }
        });
    }
}
