
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class welcome {

    public welcome() {
        JFrame frame=new JFrame("Welcome");
        JButton admin =new JButton("ADMIN");
        JButton user=new JButton("USER");
        JLabel label=new JLabel("LOGIN");
        label.setBounds(30,30,150,20);      
        admin.setBounds(30,80, 100, 30);
        user.setBounds(30,110, 100, 30);
        frame.setSize(400, 400);
        frame.add(user);
        frame.add(admin);
        frame.add(label);
        frame.setVisible(true);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
        admin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new admin();
            }
        });
        
        user.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    new user();
                } catch (ClassNotFoundException ex) {
                    System.out.println(ex);
                }
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            }
        });
    }    
    
}
