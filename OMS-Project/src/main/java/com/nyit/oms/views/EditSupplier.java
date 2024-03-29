package com.nyit.oms.views;
import com.nyit.oms.pojo.Supplier;
import com.nyit.oms.utils.SqlSessionUtil;
import org.apache.ibatis.session.SqlSession;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class EditSupplier extends JFrame{

    private JPanel formPanel = new JPanel();
    private JTextField supplierID = new JTextField("");
    private JTextField companyName = new JTextField("");
    private JTextField contactName = new JTextField("");
    private JTextField contactTitle = new JTextField("");
    private JTextField address = new JTextField("");
    private JTextField city = new JTextField("");
    private JTextField region = new JTextField("");
    private JTextField postalCode = new JTextField("");
    private JTextField country = new JTextField("");
    private JTextField phoneNum = new JTextField("");
    private JTextField fax = new JTextField("");
    private JTextField homepage = new JTextField("");
    private JLabel labelSupplierID = new JLabel("Supplier ID");
    private JLabel labelCompanyName = new JLabel("Company Name");
    private JLabel labelContactName = new JLabel("Contact Name");
    private JLabel labelContactTitle = new JLabel("Contact Title");
    private JLabel labelAddress = new JLabel("Address");
    private JLabel labelCity = new JLabel("City");
    private JLabel labelRegion = new JLabel("Region");
    private JLabel labelPostalCode = new JLabel("Postal Code");
    private JLabel labelCountry = new JLabel("Country");
    private JLabel labelPhoneNum = new JLabel("Phone Number");
    private JLabel labelFax = new JLabel("Fax");
    private JLabel labelHomepage = new JLabel("Homepage");
    private JButton insertButton = new JButton("Insert");
    private JButton cancelButton = new JButton("Cancel");


    private SqlSession sqlSession;
    private Supplier supplier;

    public EditSupplier() {
        insertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                sqlSession = SqlSessionUtil.openSession();
                // insert:
                if(supplier != null){
                    // a row is selected:
                    Supplier newSupplier = new Supplier(
                            Integer.parseInt(supplierID.getText()),
                            companyName.getText(),
                            contactName.getText(),
                            contactTitle.getText(),
                            address.getText(),
                            city.getText(),
                            region.getText(),
                            Integer.parseInt(postalCode.getText()),
                            country.getText(),
                            Integer.parseInt(phoneNum.getText()),
                            Integer.parseInt(fax.getText()),
                            homepage.getText()
                    );
                    sqlSession.insert("suppliers.updateByID",newSupplier);
                }else{
                    Supplier newSupplier = new Supplier(
                            Integer.parseInt(supplierID.getText()),
                            companyName.getText(),
                            contactName.getText(),
                            contactTitle.getText(),
                            address.getText(),
                            city.getText(),
                            region.getText(),
                            Integer.parseInt(postalCode.getText()),
                            country.getText(),
                            Integer.parseInt(phoneNum.getText()),
                            Integer.parseInt(fax.getText()),
                            homepage.getText()
                    );
                    sqlSession.insert("suppliers.insertSuppliers",newSupplier);
                }


                sqlSession.commit();
                sqlSession.close();

                DatagridSupplier ds = new DatagridSupplier();
                ds.invoke();
                setVisible(false);
            }
        });
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Exit Window
                DatagridSupplier ds = new DatagridSupplier();
                ds.invoke();
                setVisible(false);
            }
        });
    }
    public void invoke() {
        formPanel.setLayout(new GridLayout(14,4,0,6));
        setTitle("Supplier Form");
        add(new JPanel(), BorderLayout.WEST);
        add(new JPanel(), BorderLayout.EAST);
        add(new JLabel(), BorderLayout.NORTH);
        formPanel.add(labelSupplierID);
        formPanel.add(supplierID);
        formPanel.add(labelCompanyName);
        formPanel.add(companyName);
        formPanel.add(labelContactName);
        formPanel.add(contactName);
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
        formPanel.add(labelHomepage);
        formPanel.add(homepage);
        formPanel.add(new JPanel());
        formPanel.add(new JPanel());
        formPanel.add(insertButton);
        formPanel.add(cancelButton);
        add(formPanel, BorderLayout.CENTER);
        setSize(800, 500);
        setLocation(500, 200);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void invoke(Supplier supplier) {
        this.supplier = supplier;
        formPanel.setLayout(new GridLayout(14,4,0,6));
        setTitle("Supplier Form");
        add(new JPanel(), BorderLayout.WEST);
        add(new JPanel(), BorderLayout.EAST);
        add(new JLabel(), BorderLayout.NORTH);
        formPanel.add(labelSupplierID);
        formPanel.add(supplierID);
        supplierID.setText(supplier.getSupplierID().toString());
        formPanel.add(labelCompanyName);
        formPanel.add(companyName);
        companyName.setText(supplier.getCompanyName());
        formPanel.add(labelContactName);
        formPanel.add(contactName);
        contactName.setText(supplier.getContactName());
        formPanel.add(labelContactTitle);
        formPanel.add(contactTitle);
        contactTitle.setText(supplier.getContactTitle());
        formPanel.add(labelAddress);
        formPanel.add(address);
        address.setText(supplier.getAddress());
        formPanel.add(labelCity);
        formPanel.add(city);
        city.setText(supplier.getCity());
        formPanel.add(labelRegion);
        formPanel.add(region);
        region.setText(supplier.getRegion());
        formPanel.add(labelPostalCode);
        formPanel.add(postalCode);
        postalCode.setText(supplier.getPostalCode().toString());
        formPanel.add(labelCountry);
        formPanel.add(country);
        country.setText(supplier.getCountry());
        formPanel.add(labelPhoneNum);
        formPanel.add(phoneNum);
        phoneNum.setText(supplier.getPhone().toString());
        formPanel.add(labelFax);
        formPanel.add(fax);
        fax.setText(supplier.getFax().toString());
        formPanel.add(labelHomepage);
        formPanel.add(homepage);
        homepage.setText(supplier.getHomePage());
        formPanel.add(new JPanel());
        formPanel.add(new JPanel());
        formPanel.add(insertButton);
        formPanel.add(cancelButton);
        add(formPanel, BorderLayout.CENTER);
        setSize(800, 500);
        setLocation(500, 200);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
