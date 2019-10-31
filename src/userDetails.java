
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JButton;
import javax.swing.JFrame;
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
        
        Class.forName("com.mysql.jdbc.Driver");
        
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
        
        pay.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try(Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/fees","root","contactmrnishantbansal@18");){
                    
                    double amount=Double.parseDouble(JOptionPane.showInputDialog(frame, "Payment"));
                    double balance=-1d;
                    
                    String query1="select * from records where name=\""+name+"\" and password=\""+pass+"\"";
                    Statement stmt1=con.createStatement();
                    ResultSet rst1=stmt1.executeQuery(query1);
                    int init_day=0,fine=0;
                    float total=0,bal=0;
                    while(rst1.next()){
                        init_day=rst1.getInt("no_of_days");
                        fine=rst1.getInt("fine");
                        total=rst1.getFloat("total");
                        bal=rst1.getFloat("balance");
                    }
                    int no_of_days=java.time.LocalDate.now().getDayOfMonth()-init_day;
                    if(no_of_days>1){
                        int updated_fine=fine+100;
                        float updated_total=total+updated_fine;
                        float updated_balance=bal+updated_fine;
                        String query="update records set fine="+updated_fine+",total="+updated_total+", balance="+updated_balance+",no_of_days="+java.time.LocalDate.now().getDayOfMonth()+" where name=\""+name+"\" and password=\""+pass+"\"";
                        Statement stmt=con.createStatement();
                        stmt.execute(query);
                    }
                    String query2="select * from records where name=\""+name+"\" and password=\""+pass+"\"";
                    Statement stmt2=con.createStatement();
                    ResultSet rst2=stmt2.executeQuery(query2);
                    while(rst2.next()){
                        balance=rst2.getDouble("balance");
                        if(balance==0d){
                            JOptionPane.showMessageDialog(frame, "No need to pay.\nComplete fees payment is already done.");
                            break;                   
                        }
                        else{
                            if(amount<=balance){
                                double updated_amount=rst2.getDouble("paid")+amount;
                                balance-=amount;
                                String query="update records set paid="+updated_amount+",balance="+balance+" where name=\""+name+"\" and password=\""+pass+"\"";
                                Statement stmt=con.createStatement();
                                stmt2.execute(query);
                                JOptionPane.showMessageDialog(frame, "Paid Successfully...");
                                break;
                            }
                            else{
                                JOptionPane.showMessageDialog(frame, "Balance amount is less.("+balance+")");
                                break;
                            }    
                        }                                             
                    }
                    
                con.close();
                }catch(Exception ee){
                    System.out.println("Exception caught in pay button listener in userDetais.java"+ee);
                }
            }
        });
        
        balance.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try(Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/fees","root","contactmrnishantbansal@18");){
                    String query="select balance from records where name=\""+name+"\" and password=\""+pass+"\"";
                    Statement stmt=con.createStatement();
                    ResultSet rst=stmt.executeQuery(query);
                    while(rst.next()){
                        JOptionPane.showMessageDialog(frame, "BALANCE: "+rst.getFloat("balance"));
                        break;
                    }
                }catch(Exception ee){
                    System.out.println("Exception caught in balnace button listener in userDetails.java");
                }
            }
        });
        
        logout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new welcome();
                frame.setVisible(false);
            }
        });
    }
}
