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

    public Navframe() {
        customerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //System.out.println("Customer");
                DatagridCustomer dc = new DatagridCustomer();
                dc.invoke();
                setVisible(false);
            }
        });
        employeeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //System.out.println("Employee");
                DatagridEmployee de = new DatagridEmployee();
                de.invoke();
                setVisible(false);
            }
        });
        supplierButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //System.out.println("Supplier");
                DatagridSupplier ds = new DatagridSupplier();
                ds.invoke();
                setVisible(false);
            }
        });
        productButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //System.out.println("Product");
                QueryProduct qp = new QueryProduct();
                qp.invoke();
                setVisible(false);
            }
        });
        orderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //System.out.println("Order");
                QueryOrder qo = new QueryOrder();
                qo.invoke();
                setVisible(false);
            }
        });
    }

    public void invoke() {
        setTitle("welcome " + UserStatus.getUsername() + ", " + "You are " + UserStatus.getIsAdmin().toString() + " Admin");
        setSize(200, 400);
        setContentPane(panel);
        setLocation(500, 200);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
