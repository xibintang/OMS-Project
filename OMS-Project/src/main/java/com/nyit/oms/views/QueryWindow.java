package com.nyit.oms.views;

import com.nyit.oms.pojo.Order;
import com.nyit.oms.pojo.Product;
import com.nyit.oms.utils.SqlSessionUtil;
import org.apache.ibatis.session.SqlSession;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class QueryWindow extends JFrame {
    private JLabel title;
    private JPanel titlePanel;
    private JTable table;
    private JScrollPane scrollPane;

    private SqlSession sqlSession;


    public void invoke(Integer id, String type) {

        String[][] data = null;
        String[] orderNames = {
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
        String[] productNames = {"Product ID", "Product Name", "Supplier ID", "Category ID", "Quantity Per Unit", "Unit Price", "Units In Stock", "Units On Order", "Reorder Level", "Discotinuted"};

        if(type.equals("order")){
            // order clicked
            sqlSession = SqlSessionUtil.openSession();
            List<Order> orders = sqlSession.selectList("orders.selectByCustomers", id);
            data = new String[orders.size()][14];
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
        }
        if(type.equals("product")){
            // product clicked
            sqlSession = SqlSessionUtil.openSession();
            List<Product> products = sqlSession.selectList("products.selectBySuppliers", id);
            data = new String[products.size()][10];
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
        }

        setTitle("Query Display");

        title = new JLabel("Query");
        titlePanel = new JPanel();

        JPanel leftPanel = new JPanel();
        JPanel rightPanel = new JPanel();
        if(type.equals("product")){
            table = new JTable(data, productNames) {
                public boolean editCellAt(int row, int column, java.util.EventObject e) {
                    return false;
                }
            };
        }
        if(type.equals("order")){
            table = new JTable(data, orderNames) {
                public boolean editCellAt(int row, int column, java.util.EventObject e) {
                    return false;
                }
            };
        }

        scrollPane = new JScrollPane(table);

        table.setDragEnabled(false);
        titlePanel.add(title);

        add(leftPanel, BorderLayout.WEST);
        add(rightPanel, BorderLayout.EAST);
        add(titlePanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        setSize(800, 400);
        setLocation(500, 200);
        setVisible(true);
    }
}

