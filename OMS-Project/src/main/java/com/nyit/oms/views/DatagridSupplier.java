package com.nyit.oms.views;
import com.nyit.oms.pojo.Supplier;
import com.nyit.oms.utils.SqlSessionUtil;
import org.apache.ibatis.session.SqlSession;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class DatagridSupplier extends JFrame {
    private JLabel title;
    private JPanel titlePanel;
    private JPanel buttonPanel;
    private JTable table;
    private JScrollPane scrollPane;
    //private JButton updateButton = new JButton("Update");
    private JButton insertButton = new JButton("Insert");
    private JButton deleteButton = new JButton("Delete");
    private JButton backButton = new JButton("Back");

    private SqlSession sqlSession;
    private List<Supplier> suppliers;

    public DatagridSupplier() {
        insertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EditSupplier es = new EditSupplier();
                es.invoke();
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
                // open session:
                sqlSession = SqlSessionUtil.openSession();
                // delete:
                Integer supplierID = suppliers.get(index).getSupplierID();
                sqlSession.delete("suppliers.deleteByID",supplierID);
                // close session:
                sqlSession.commit();
                sqlSession.close();
                // Refresh
                DatagridSupplier ds = new DatagridSupplier();
                ds.invoke();
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

        // open the session:
        sqlSession = SqlSessionUtil.openSession();
        suppliers = sqlSession.selectList("suppliers.selectAll", Supplier.class);
        // map to the 2 dimension array
        String[][] data = new String[suppliers.size()][12];
        for(int i = 0; i < suppliers.size(); i++){
            data[i][0] = suppliers.get(i).getSupplierID().toString();
            data[i][1] = suppliers.get(i).getCompanyName();
            data[i][2] = suppliers.get(i).getContactName();
            data[i][3] = suppliers.get(i).getContactTitle();
            data[i][4] = suppliers.get(i).getAddress();
            data[i][5] = suppliers.get(i).getCity();
            data[i][6] = suppliers.get(i).getRegion();
            data[i][7] = suppliers.get(i).getPostalCode().toString();
            data[i][8] = suppliers.get(i).getCountry();
            data[i][9] = suppliers.get(i).getPhone().toString();
            data[i][10] = suppliers.get(i).getFax().toString();
            data[i][11] = suppliers.get(i).getHomePage();
        }
        // close the session:
        sqlSession.commit();
        sqlSession.close();

        String[] names = {"SupplierID", "CompanyName", "ContactName", "ContactTitle", "Address", "City", "Region", "PostalCode", "Country", "Phone", "Fax","HomePage"};

        setTitle("Datagrid Display");

        title = new JLabel("Supplier");
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
        if (UserStatus.getIsAdmin()) {
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

