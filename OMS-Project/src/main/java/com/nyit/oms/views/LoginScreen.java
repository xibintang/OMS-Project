package com.nyit.oms.views;

import com.nyit.oms.utils.SqlSessionUtil;
import org.apache.ibatis.session.SqlSession;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LoginScreen extends JFrame {

    private JPanel panel;
    private JTextField jtfUsername;
    private JPasswordField jpfPassword;
    private JLabel jlblUsername;
    private JLabel jlblPassword;
    private JButton OKButton;

    private SqlSession sqlSession;


    public LoginScreen() {
        OKButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Check Username in Accounts table, then check if Password matches.
                System.out.println("Logging in...");
                //System.out.println("Username: " + jtfUsername.getText());
                //System.out.println("Password: " + new String().valueOf(jpfPassword.getPassword()));
                sqlSession = SqlSessionUtil.openSession();
                UserObject userObject = new UserObject(jtfUsername.getText(),String.valueOf(jpfPassword.getPassword()));
                UserObject result = sqlSession.selectOne("user.selectUser",userObject);
                if(result != null){
                    // this means the query returns a user, the username and password is valid:
                    if(result.isAdmin()){
                        System.out.println("Login Success!");
                        UserStatus.setIsAdmin(true);
                        UserStatus.setUsername(result.getUsername());
                    }else{
                        System.out.println("Login Success!");
                        UserStatus.setIsAdmin(false);
                        UserStatus.setUsername(result.getUsername());
                    }
                    Navframe nf = new Navframe();
                    nf.invoke();
                    setVisible(false);
                }else{
                    System.out.println("Login Fail!");
                    JOptionPane.showMessageDialog(null,"Username and Password does not match");
                }
            }
        });
    }

    public void invoke() {
        setTitle("Login Screen");
        setSize(400, 300);
        setContentPane(panel);
        jtfUsername.setMinimumSize(new Dimension(80, 20));
        setLocation(500, 200);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
