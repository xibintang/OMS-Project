package com.nyit.oms.views;
import com.nyit.oms.pojo.Employee;
import com.nyit.oms.utils.SqlSessionUtil;
import org.apache.ibatis.session.SqlSession;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class EditEmployee extends JFrame{
    private JPanel formPanel = new JPanel();
    private JTextField employeeID = new JTextField("");
    private JTextField lastName = new JTextField("");
    private JTextField firstName = new JTextField("");
    private JTextField contactTitle = new JTextField("");
    private JTextField birthDate = new JTextField("");
    private JTextField hireDate = new JTextField("");
    private JLabel labelEmployeeID = new JLabel("Employee ID");
    private JLabel labelLastName = new JLabel("Last Name");
    private JLabel labelFirstName = new JLabel("First Name");
    private JLabel labelContactTitle = new JLabel("Contact Title");
    private JLabel labelBirthDate = new JLabel("Birth Date");
    private JLabel labelHireDate = new JLabel("Hire Date");
    private JButton insertButton = new JButton("Insert");
    private JButton cancelButton = new JButton("Cancel");

    private SqlSession sqlSession;
    private Employee employee;

    public EditEmployee() {
        insertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Open Sql Session:
                sqlSession = SqlSessionUtil.openSession();

                if(employee != null){
                    // a row is selected:
                    Employee newEmployee = new Employee(
                            Integer.parseInt(employeeID.getText()),
                            lastName.getText(),
                            firstName.getText(),
                            contactTitle.getText(),
                            birthDate.getText(),
                            hireDate.getText()
                    );
                    sqlSession.insert("employees.updateByID",newEmployee);
                }else{
                    // Create an Employee Object:
                    Employee newEmployee = new Employee(
                            Integer.parseInt(employeeID.getText()),
                            lastName.getText(),
                            firstName.getText(),
                            contactTitle.getText(),
                            birthDate.getText(),
                            hireDate.getText()
                    );
                    // Insert into the table:
                    sqlSession.insert("insertEmployee",newEmployee);
                }

                // commit and close the session
                sqlSession.commit();
                sqlSession.close();
                // refresh:
                DatagridEmployee ed = new DatagridEmployee();
                ed.invoke();
                setVisible(false);
            }
        });
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Exit Window
                DatagridEmployee ed = new DatagridEmployee();
                ed.invoke();
                setVisible(false);
            }
        });
    }
    public void invoke() {
        formPanel.setLayout(new GridLayout(8,2,0,6));
        setTitle("Employee Form");
        add(new JPanel(), BorderLayout.WEST);
        add(new JPanel(), BorderLayout.EAST);
        add(new JLabel(), BorderLayout.NORTH);
        formPanel.add(labelEmployeeID);
        formPanel.add(employeeID);
        formPanel.add(labelLastName);
        formPanel.add(lastName);
        formPanel.add(labelFirstName);
        formPanel.add(firstName);
        formPanel.add(labelContactTitle);
        formPanel.add(contactTitle);
        formPanel.add(labelBirthDate);
        formPanel.add(birthDate);
        formPanel.add(labelHireDate);
        formPanel.add(hireDate);
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

    public void invoke(Employee employee) {
        this.employee = employee;
        formPanel.setLayout(new GridLayout(8,2,0,6));
        setTitle("Employee Form");
        add(new JPanel(), BorderLayout.WEST);
        add(new JPanel(), BorderLayout.EAST);
        add(new JLabel(), BorderLayout.NORTH);
        formPanel.add(labelEmployeeID);
        formPanel.add(employeeID);
        employeeID.setEditable(false);
        employeeID.setText(employee.getEmployeeID().toString());
        formPanel.add(labelLastName);
        formPanel.add(lastName);
        lastName.setText(employee.getLastName());
        formPanel.add(labelFirstName);
        formPanel.add(firstName);
        firstName.setText(employee.getFirstName());
        formPanel.add(labelContactTitle);
        formPanel.add(contactTitle);
        contactTitle.setText(employee.getTitle());
        formPanel.add(labelBirthDate);
        formPanel.add(birthDate);
        birthDate.setText(employee.getBirthDate());
        formPanel.add(labelHireDate);
        formPanel.add(hireDate);
        hireDate.setText(employee.getHireDate());
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
