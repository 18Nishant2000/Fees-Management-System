
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

public class userDetails {

    public userDetails(String name, String pass) throws ClassNotFoundException {
        JFrame frame=new JFrame("User Details");
        JButton profile=new JButton("Profile");
        JButton balance=new JButton("Balance");
        JButton pay=new JButton("Pay");
        JButton logout=new JButton("Logout");
        frame.setSize(400,400);
        frame.add(profile);
        frame.add(balance);
        frame.add(pay);
        frame.add(logout);
        frame.setLayout(new GridLayout(4, 0));
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        Class.forName("com.mysql.cj.jdbc.Driver");
        
        profile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try(Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/fees","root","contactmrnishantbansal@18");){
                    String query="select * from records where name=\""+name+"\" and password=\""+pass+"\"";
                    Statement stmt=con.createStatement();
                    ResultSet rst=stmt.executeQuery(query);
                    String result="";
                    while(rst.next()){
                        result=rst.getString(1)+" "+rst.getString(2);
                    }
                    JOptionPane.showMessageDialog(frame, result);
                    con.close();
                }catch(Exception ee){
                    System.out.println("Exception caught in profile button listener in userDetails.java file");
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
