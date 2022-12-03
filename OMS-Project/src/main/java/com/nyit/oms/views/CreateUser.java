package com.nyit.oms.views;

import com.nyit.oms.utils.SqlSessionUtil;
import org.apache.ibatis.session.SqlSession;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class CreateUser extends JFrame {
    private JLabel title;
    private JPanel titlePanel;
    private JPanel buttonPanel;
    private JPanel formPanel;
    private JPanel fieldPanel;
    private JTable table;
    private JScrollPane scrollPane;

    private JLabel labelUsername = new JLabel("Username");
    private JTextField username = new JTextField("");
    private JLabel labelPassword = new JLabel("Password");
    private JPasswordField password = new JPasswordField("");
    private JLabel checkAdmin = new JLabel("isAdmin");
    private JCheckBox admin = new JCheckBox("");

    private JButton createButton = new JButton("Create User");
    private JButton deleteButton = new JButton("Delete");
    private JButton backButton = new JButton("Back");

    SqlSession sqlSession;
    List<UserObject> users;

    public CreateUser() {
        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sqlSession = SqlSessionUtil.openSession();
                UserObject userObject = new UserObject(username.getText(),String.valueOf(password.getPassword()));
                userObject.setIsAdmin(admin.isSelected());
                sqlSession.insert("user.insertUser",userObject);

                sqlSession.commit();
                sqlSession.close();

                CreateUser createUser = new CreateUser();
                createUser.invoke();
                setVisible(false);

            }
        });
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Delete selected row
                int index = table.getSelectedRow();
                if (index == -1) return;
                //
                sqlSession = SqlSessionUtil.openSession();
                sqlSession.delete("user.deleteUser",users.get(index).getUserID());
                sqlSession.commit();
                sqlSession.close();

                // Refresh
                CreateUser cd = new CreateUser();
                cd.invoke();
                setVisible(false);
            }
        });
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Navframe nf = new Navframe();
                nf.invoke();
                setVisible(false);
            }
        });
    }

    public void invoke() {

        sqlSession = SqlSessionUtil.openSession();
        users = sqlSession.selectList("user.selectAll", UserObject.class);
        String[][] data = new String[users.size()][4];
        for(int i = 0; i < data.length; i++){
            data[i][0] = users.get(i).getUserID().toString();
            data[i][1] = users.get(i).getUsername();
            data[i][2] = users.get(i).getPassword();
            data[i][3] = users.get(i).isAdmin().toString();
        }
        sqlSession.commit();
        sqlSession.close();

        String[] names = {"userID", "username", "password", "isAdmin"};

        setTitle("Datagrid Display");

        title = new JLabel("Users");
        titlePanel = new JPanel();
        buttonPanel = new JPanel();
        formPanel = new JPanel();
        fieldPanel = new JPanel();
        fieldPanel.setLayout(new FlowLayout());

        JPanel leftPanel = new JPanel();
        JPanel rightPanel = new JPanel();

        table = new JTable(data, names) {
            public boolean editCellAt(int row, int column, java.util.EventObject e) {
                return false;
            }
        };
        scrollPane = new JScrollPane(table);

        table.setDragEnabled(false);
        titlePanel.add(title);
        buttonPanel.add(createButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(backButton);

        username.setColumns(10);
        password.setColumns(10);
        fieldPanel.add(labelUsername);
        fieldPanel.add(username);
        fieldPanel.add(labelPassword);
        fieldPanel.add(password);
        fieldPanel.add(checkAdmin);
        fieldPanel.add(admin);

        formPanel.add(fieldPanel, BorderLayout.CENTER);
        formPanel.add(buttonPanel, BorderLayout.SOUTH);
        add(leftPanel, BorderLayout.WEST);
        add(rightPanel, BorderLayout.EAST);
        add(titlePanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(formPanel, BorderLayout.SOUTH);

        setSize(800, 400);
        setLocation(500, 200);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}

