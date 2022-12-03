package com.nyit.oms.views;
import com.nyit.oms.pojo.Product;
import com.nyit.oms.utils.SqlSessionUtil;
import org.apache.ibatis.session.SqlSession;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class QueryProduct extends JFrame {
    private JLabel title = new JLabel("Products");
    private JPanel titlePanel;
    private JPanel buttonPanel;
    private JTable table;
    private JTextField jtfSearch = new JTextField();
    private JScrollPane scrollPane;
    private JButton queryButton = new JButton("Query");
    private JButton backButton = new JButton("Back");

    // backend:
    List<Product> products;
    SqlSession sqlSession;


    public QueryProduct() {
        queryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Find all rows where ProductID === ProductID
                int index = table.getSelectedRow();
                System.out.println(index);
                if (index == -1) return;

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
        products = sqlSession.selectList("products.selectAll", Product.class);
        String[][] data = new String[products.size()][10];
        for(int i = 0; i < products.size(); i++){
            data[i][0] = products.get(i).getProductID().toString();
            data[i][1] = products.get(i).getProductName();
            data[i][2] = products.get(i).getSupplierID().toString();
            data[i][3] = products.get(i).getCategoryID().toString();
            data[i][4] = products.get(i).getQuantityPerUnit().toString();
            data[i][5] = products.get(i).getUnitPrice().toString();
            data[i][6] = products.get(i).getUnitsInStock().toString();
            data[i][7] = products.get(i).getUnitsOnOrder().toString();
            data[i][8] = products.get(i).getReOrderlevel().toString();
            data[i][9] = products.get(i).getDiscontinued().toString();
        }
        String[] names = {"Product ID", "Product Name", "Supplier ID", "Category ID", "Quantity Per Unit", "Unit Price", "Units In Stock", "Units On Order", "Reorder Level", "Discotinuted"};

        setTitle("Datagrid Display");

        // Adding Margins
        titlePanel = new JPanel();
        buttonPanel = new JPanel();
        JPanel leftPanel = new JPanel();
        JPanel rightPanel = new JPanel();
        add(leftPanel, BorderLayout.WEST);
        add(rightPanel, BorderLayout.EAST);

        // Displaying Table values into the JScrollPane JComponent
        table = new JTable(data, names) {
            public boolean editCellAt(int row, int column, java.util.EventObject e) {
                return false;
            }
        };
        scrollPane = new JScrollPane(table);

        table.setDragEnabled(false);
        titlePanel.add(title);
        jtfSearch.setColumns(10);
        buttonPanel.add(new JLabel("Supplier ID"));
        buttonPanel.add(jtfSearch);
        buttonPanel.add(queryButton);
        buttonPanel.add(backButton);
        add(titlePanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        setSize(800, 400);
        setLocation(500, 200);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}

