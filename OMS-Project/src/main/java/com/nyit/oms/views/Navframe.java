package com.nyit.oms.views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Navframe extends JFrame {
    private JButton customerButton;
    private JButton employeeButton;
    private JButton supplierButton;
    private JButton productButton;
    private JButton orderButton;
    private JPanel panel;
    private JButton userButton;

    public Navframe() {
        userButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CreateUser createUser = new CreateUser();
                createUser.invoke();
                setVisible(false);
            }
        });
        customerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DatagridCustomer dc = new DatagridCustomer();
                dc.invoke();
                setVisible(false);
            }
        });
        employeeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DatagridEmployee de = new DatagridEmployee();
                de.invoke();
                setVisible(false);
            }
        });
        supplierButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DatagridSupplier ds = new DatagridSupplier();
                ds.invoke();
                setVisible(false);
            }
        });
        productButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                QueryProduct qp = new QueryProduct();
                qp.invoke();
                setVisible(false);
            }
        });
        orderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                QueryOrder qo = new QueryOrder();
                qo.invoke();
                setVisible(false);
            }
        });
    }

    public void invoke() {
        if(UserStatus.getIsAdmin()){
            setTitle("welcome " + UserStatus.getUsername() + ", " + "You are an Admin");
        }else{
            setTitle("welcome " + UserStatus.getUsername() + ", " + "You are an User");
        }

        if(!UserStatus.getIsAdmin()){
            userButton.setVisible(false);
        }
        setSize(500, 400);
        setContentPane(panel);
        setLocation(500, 200);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
