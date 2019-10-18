
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.midi.SysexMessage;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class user {
    user(){
        JFrame frame=new JFrame("USER LOGIN");
        JLabel label=new JLabel("USER LOGIN");
        JLabel name=new JLabel("NAME: ");
        JLabel pass=new JLabel("PASSWORD: ");
        JTextField name_field=new JTextField();
        JPasswordField pass_field=new JPasswordField();
        JButton login=new JButton("LOGIN");
        JButton new_user=new JButton("New USER?");
        label.setBounds(30,10, 50, 30);
        name.setBounds(10,80,40,30);
        name_field.setBounds(70, 80, 100, 50);
        pass.setBounds(10, 150, 60, 30);
        pass_field.setBounds(100,150,100,50);
        login.setBounds(30, 250, 50, 40);
        new_user.setBounds(10, 330,100, 30);
        frame.setSize(400, 400);
        frame.add(new_user);
        frame.add(login);
        frame.add(pass_field);
        frame.add(pass);
        frame.add(name_field);
        frame.add(name);
        frame.add(label);
        frame.setVisible(true);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        new_user.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    new Registration();
                } catch (ClassNotFoundException ex) {
                    System.out.println(e);
                }
            }
        });
                
        
    }
}
