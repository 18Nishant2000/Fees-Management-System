
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class welcome {

    public welcome() {
        JFrame frame=new JFrame("Welcome");
        JButton admin =new JButton("ADMIN");
        JButton user=new JButton("USER");
        JLabel label=new JLabel("LOGIN");
        frame.setSize(400, 400);
        frame.add(label);
        frame.add(admin);
        frame.add(user);
        frame.setVisible(true);
        frame.setLayout(new GridLayout(3, 0));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        admin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    frame.setVisible(false);
                    new admin();
                } catch (ClassNotFoundException ex) {
                    System.out.println("Exception caught in admin button actionlistener in welcome.java file");
                }
            }
        });
        
        user.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    frame.setVisible(false);
                    new user();
                } catch (ClassNotFoundException ex) {
                    System.out.println(ex);
                }
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            }
        });
    }    
    
}
