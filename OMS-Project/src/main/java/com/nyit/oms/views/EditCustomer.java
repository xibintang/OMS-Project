package com.nyit.oms.views;
import com.nyit.oms.pojo.Customer;
import com.nyit.oms.utils.SqlSessionUtil;
import org.apache.ibatis.session.SqlSession;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;


public class EditCustomer extends JFrame{

    private JPanel formPanel = new JPanel();
    private JTextField customerID = new JTextField("");
    private JTextField companyName = new JTextField("");
    private JTextField address = new JTextField("");
    private JTextField city = new JTextField("");
    private JTextField contactName = new JTextField("");
    private JTextField contactTitle = new JTextField("");
    private JTextField region = new JTextField("");
    private JTextField postalCode = new JTextField("");
    private JTextField country = new JTextField("");
    private JTextField phoneNum = new JTextField("");
    private JTextField fax = new JTextField("");
    private JLabel labelCustomerID = new JLabel("Customer ID");
    private JLabel labelCompanyName = new JLabel("Company Name");
    private JLabel labelAddress = new JLabel("Address");
    private JLabel labelCity = new JLabel("City");
    private JLabel labelContactName = new JLabel("Contact Name");
    private JLabel labelContactTitle = new JLabel("Contact Title");
    private JLabel labelRegion = new JLabel("Region");
    private JLabel labelPostalCode = new JLabel("Postal Code");
    private JLabel labelCountry = new JLabel("Country");
    private JLabel labelPhoneNum = new JLabel("Phone Number");
    private JLabel labelFax = new JLabel("Fax");
    private JButton insertButton = new JButton("Insert");
    private JButton cancelButton = new JButton("Cancel");

    private SqlSession sqlSession;
    private Customer updateCustomer;


    public EditCustomer() {
        insertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // open the session:
                sqlSession = SqlSessionUtil.openSession();
                if(updateCustomer != null){
                    Customer newCust = new Customer(
                            Integer.parseInt(customerID.getText()),
                            companyName.getText(),
                            contactName.getText(),
                            contactTitle.getText(),
                            address.getText(),
                            city.getText(),
                            region.getText(),
                            Integer.parseInt(postalCode.getText()),
                            country.getText(),
                            Integer.parseInt(phoneNum.getText()),
                            Integer.parseInt(fax.getText())
                    );
                    sqlSession.insert("customer.updateByID",newCust);
                }else{
                    // make a customer object:
                    Customer customer = new Customer(
                            Integer.parseInt(customerID.getText()),
                            companyName.getText(),
                            contactName.getText(),
                            contactTitle.getText(),
                            address.getText(),
                            city.getText(),
                            region.getText(),
                            Integer.parseInt(postalCode.getText()),
                            country.getText(),
                            Integer.parseInt(phoneNum.getText()),
                            Integer.parseInt(fax.getText())
                    );
                    sqlSession.insert("customer.insertCustomer",customer);
                }

                // close the session:
                sqlSession.commit();
                sqlSession.close();
                DatagridCustomer cd = new DatagridCustomer();
                cd.invoke();
                setVisible(false);
            }
        });
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Exit Window
                DatagridCustomer cd = new DatagridCustomer();
                cd.invoke();
                setVisible(false);
            }
        });
    }
    public void invoke() {
        formPanel.setLayout(new GridLayout(7,4,0,6));
        setTitle("Customer Form");
        add(new JPanel(), BorderLayout.WEST);
        add(new JPanel(), BorderLayout.EAST);
        add(new JLabel(), BorderLayout.NORTH);
        formPanel.add(labelCustomerID);
        formPanel.add(customerID);
        formPanel.add(labelContactName);
        formPanel.add(contactName);
        formPanel.add(labelCompanyName);
        formPanel.add(companyName);
        formPanel.add(labelContactTitle);
        formPanel.add(contactTitle);
        formPanel.add(labelAddress);
        formPanel.add(address);
        formPanel.add(labelCity);
        formPanel.add(city);
        formPanel.add(labelRegion);
        formPanel.add(region);
        formPanel.add(labelPostalCode);
        formPanel.add(postalCode);
        formPanel.add(labelCountry);
        formPanel.add(country);
        formPanel.add(labelPhoneNum);
        formPanel.add(phoneNum);
        formPanel.add(labelFax);
        formPanel.add(fax);
        formPanel.add(new JPanel());
        formPanel.add(new JPanel());
        formPanel.add(new JPanel());
        formPanel.add(insertButton);
        formPanel.add(cancelButton);
        add(formPanel, BorderLayout.CENTER);
        setSize(800, 400);
        setLocation(500, 200);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void invoke(Customer customer) {
        updateCustomer = customer;
        formPanel.setLayout(new GridLayout(7,4,0,6));
        setTitle("Customer Form");
        add(new JPanel(), BorderLayout.WEST);
        add(new JPanel(), BorderLayout.EAST);
        add(new JLabel(), BorderLayout.NORTH);
        formPanel.add(labelCustomerID);
        formPanel.add(customerID);
        customerID.setEditable(false);
        customerID.setText(customer.getCustomerID().toString());
        formPanel.add(labelContactName);
        formPanel.add(contactName);
        contactName.setText(customer.getContactName());
        formPanel.add(labelCompanyName);
        formPanel.add(companyName);
        companyName.setText(customer.getCompanyName());
        formPanel.add(labelContactTitle);
        formPanel.add(contactTitle);
        contactTitle.setText(customer.getContactTitle());
        formPanel.add(labelAddress);
        formPanel.add(address);
        address.setText(customer.getAddress());
        formPanel.add(labelCity);
        formPanel.add(city);
        city.setText(customer.getCity());
        formPanel.add(labelRegion);
        formPanel.add(region);
        region.setText(customer.getRegion());
        formPanel.add(labelPostalCode);
        formPanel.add(postalCode);
        postalCode.setText(customer.getPostalCode().toString());
        formPanel.add(labelCountry);
        formPanel.add(country);
        country.setText(customer.getCountry());
        formPanel.add(labelPhoneNum);
        formPanel.add(phoneNum);
        phoneNum.setText(customer.getPhone().toString());
        formPanel.add(labelFax);
        formPanel.add(fax);
        fax.setText(customer.getFax().toString());
        formPanel.add(new JPanel());
        formPanel.add(new JPanel());
        formPanel.add(new JPanel());
        formPanel.add(insertButton);
        formPanel.add(cancelButton);
        add(formPanel, BorderLayout.CENTER);
        setSize(800, 400);
        setLocation(500, 200);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
