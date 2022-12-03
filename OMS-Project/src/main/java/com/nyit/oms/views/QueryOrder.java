package com.nyit.oms.views;
import com.nyit.oms.pojo.Order;
import com.nyit.oms.utils.SqlSessionUtil;
import org.apache.ibatis.session.SqlSession;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class QueryOrder extends JFrame {
    private JLabel title = new JLabel("Orders");
    private JPanel titlePanel;
    private JPanel buttonPanel;
    private JTable table;
    private JTextField jtfSearch = new JTextField();
    private JScrollPane scrollPane;
    private JButton queryButton = new JButton("Query");
    private JButton backButton = new JButton("Back");

    SqlSession sqlSession;
    List<Order> orders;
    public QueryOrder() {
        queryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Find all rows where OrderID === OrderID
                int index = table.getSelectedRow();
                QueryWindow queryWindow = new QueryWindow();
                queryWindow.invoke(Integer.parseInt(jtfSearch.getText()),"order");
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
        orders = sqlSession.selectList("orders.selectAll", Order.class);
        String[][] data = new String[orders.size()][14];
        for(int i = 0; i < orders.size(); i++){
            data[i][0] = orders.get(i).getOrderID().toString();
            data[i][1] = orders.get(i).getCustomerID().toString();
            data[i][2] = orders.get(i).getEmployeeID().toString();
            data[i][3] = orders.get(i).getOrderDate();
            data[i][4] = orders.get(i).getRequiredDate();
            data[i][5] = orders.get(i).getShippedDate();
            data[i][6] = orders.get(i).getShipVia();
            data[i][7] = orders.get(i).getFreight();
            data[i][8] = orders.get(i).getShipName();
            data[i][9] = orders.get(i).getShipAddress();
            data[i][10] = orders.get(i).getShipCity();
            data[i][11] = orders.get(i).getShipRegion();
            data[i][12] = orders.get(i).getShipPostalCode().toString();
            data[i][13] = orders.get(i).getShipCountry();
        }
        String[] names = {
                "OrderID",
                "CustomerID",
                "EmployeeID",
                "Order Date",
                "Required Date",
                "Shipped Date",
                "Ship Via",
                "Freight",
                "Ship Name",
                "Ship Address",
                "Ship City",
                "Ship Region",
                "Ship PostalCode",
                "Ship Country"
        };

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
        buttonPanel.add(new JLabel("Customer ID"));
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
        sqlSession.commit();
        sqlSession.close();
    }
}

