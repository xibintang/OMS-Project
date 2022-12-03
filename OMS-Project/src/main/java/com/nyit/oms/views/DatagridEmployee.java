package com.nyit.oms.views;
import com.nyit.oms.pojo.Employee;
import com.nyit.oms.utils.SqlSessionUtil;
import org.apache.ibatis.session.SqlSession;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class DatagridEmployee extends JFrame {
    private JLabel title;
    private JPanel titlePanel;
    private JPanel buttonPanel;
    private JTable table;
    private JScrollPane scrollPane;
    //private JButton updateButton = new JButton("Update");
    private JButton insertButton = new JButton("Insert");
    private JButton deleteButton = new JButton("Delete");
    private JButton backButton = new JButton("Back");
    private JButton updateButton = new JButton("Update");

    private SqlSession sqlSession;
    private List<Employee> employees;

    public DatagridEmployee() {
        insertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EditEmployee ee = new EditEmployee();
                ee.invoke();
                setVisible(false);
            }
        });

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = table.getSelectedRow();
                if(index == -1) return;
                EditEmployee ee = new EditEmployee();
                ee.invoke(employees.get(index));
                setVisible(false);
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sqlSession = SqlSessionUtil.openSession();
                // Delete selected row
                int index = table.getSelectedRow();

                if (index == -1) return;
                // selected id:
                Integer employeeID = employees.get(index).getEmployeeID();
                sqlSession.delete("employees.deleteByID",employeeID);
                sqlSession.commit();
                sqlSession.close();
                // Refresh
                DatagridEmployee ed = new DatagridEmployee();
                ed.invoke();
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
        employees = sqlSession.selectList("employees.selectAll", Employee.class);
        String[][] data = new String[employees.size()][6];
        for(int i = 0; i < data.length; i++){
            data[i][0] = employees.get(i).getEmployeeID().toString();
            data[i][1] = employees.get(i).getLastName().toString();
            data[i][2] = employees.get(i).getFirstName().toString();
            data[i][3] = employees.get(i).getTitle().toString();
            data[i][4] = employees.get(i).getBirthDate().toString();
            data[i][5] = employees.get(i).getHireDate().toString();
        }
        // close the session:
        sqlSession.commit();
        sqlSession.close();
        String[] names = {"EmployeeID", "Last Name", "First Name", "Title", "Birth Date", "Hire Date"};
        setTitle("Datagrid Display");

        title = new JLabel("Employee");
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
            buttonPanel.add(updateButton);
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
