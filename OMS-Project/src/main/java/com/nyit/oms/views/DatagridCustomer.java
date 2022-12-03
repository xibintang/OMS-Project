package com.nyit.oms.views;

import com.nyit.oms.pojo.Customer;
import com.nyit.oms.utils.SqlSessionUtil;
import com.nyit.oms.views.Navframe;
import org.apache.ibatis.session.SqlSession;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class DatagridCustomer extends JFrame {
    private JLabel title;
    private JPanel titlePanel;
    private JPanel buttonPanel;
    private JTable table;
    private JScrollPane scrollPane;
    //private JButton updateButton = new JButton("Update");
    private JButton insertButton = new JButton("Insert");
    private JButton deleteButton = new JButton("Delete");
    private JButton backButton = new JButton("Back");

    private List<Customer> customers;
    private SqlSession sqlSession;

    public DatagridCustomer() {

        insertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                EditCustomer ec = new EditCustomer();
                ec.invoke();
                setVisible(false);
            }
        });
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Delete selected row
                int index = table.getSelectedRow();
                System.out.println(index);
                if (index == -1) return;
                // index does not equal to -1, which means a column is selected:
                sqlSession = SqlSessionUtil.openSession();
                Integer customerID = customers.get(index).getCustomerID();
                System.out.println(customerID);
                sqlSession.delete("customer.deleteByID",customerID);
                // close the session:
                sqlSession.commit();
                sqlSession.close();
                // Refresh
                DatagridCustomer cd = new DatagridCustomer();
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
        customers = sqlSession.selectList("customer.selectAll", Customer.class);
        String[][] data = new String[customers.size()][11];
        String[] names = {"CustomerID", "CompanyName", "ContactName", "ContactTitle", "Address", "City", "Region", "PostalCode", "Country", "Phone", "Fax"};
        for(int i = 0; i < data.length; i++){
            data[i][0] = customers.get(i).getCustomerID().toString();
            data[i][1] = customers.get(i).getCompanyName().toString();
            data[i][2] = customers.get(i).getContactName().toString();
            data[i][3] = customers.get(i).getContactTitle().toString();
            data[i][4] = customers.get(i).getAddress().toString();
            data[i][5] = customers.get(i).getCity().toString();
            data[i][6] = customers.get(i).getRegion().toString();
            data[i][7] = customers.get(i).getPostalCode().toString();
            data[i][8] = customers.get(i).getCountry().toString();
            data[i][9] = customers.get(i).getPhone().toString();
            data[i][10] = customers.get(i).getFax().toString();
        }
        // close the session
        sqlSession.commit();
        sqlSession.close();

        setTitle("Datagrid Display");

        title = new JLabel("Customer");
        titlePanel = new JPanel();
        buttonPanel = new JPanel();

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
        if (/* user.isAdmin() */ UserStatus.getIsAdmin()) {
            buttonPanel.add(insertButton);
            buttonPanel.add(deleteButton);
        }
        buttonPanel.add(backButton);
        add(leftPanel, BorderLayout.WEST);
        add(rightPanel, BorderLayout.EAST);
        add(titlePanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        setSize(800, 400);
        setLocation(500, 200);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
}

