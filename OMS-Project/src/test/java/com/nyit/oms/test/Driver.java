package com.nyit.oms.test;

import com.nyit.oms.pojo.Customer;
import com.nyit.oms.pojo.Employee;
import com.nyit.oms.pojo.Order;
import com.nyit.oms.utils.SqlSessionUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Driver {

    @Test
    public void testSelectOrderByCustomerID(){
        // query the order based on customer id:
        SqlSession sqlSession = SqlSessionUtil.openSession();
        // invoke the method:
        List<Order> orders = sqlSession.selectList("selectByCustomers",236487);
        orders.forEach(order -> System.out.println(order));
    }
    @Test
    public void testSelectAll(){
        // test the select all, or select multiple:
        SqlSession sqlSession = SqlSessionUtil.openSession();
        // call the method:
        List<Customer> objects = sqlSession.selectList("customer.selectAll");
        objects.forEach(element -> System.out.println(element));
    }

    @Test
    public void testSelectByID(){
        // test select one by id, or by name, etc
        SqlSession sqlSession = SqlSessionUtil.openSession();
        Customer customer = sqlSession.selectOne("customer.selectOne",123456);
        System.out.println(customer);
        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void testInsertEmployee(){
        // test the update function: using pojo
        SqlSession sqlSession = SqlSessionUtil.openSession();
        Employee employee = new Employee(2222,"Xibin","Tang","Hello","00-00-00","00-00-00");
        sqlSession.insert("employees.insertEmployee",employee);
        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void testInsertByPojo(){
        // using pojo object
        SqlSession sqlSession = SqlSessionUtil.openSession();
        // here:
        Customer customer = new Customer();
        sqlSession.insert("customer.insertCustomer",customer);

        sqlSession.commit();
        sqlSession.close();

    }
    @Test
    public void testInsert(){
        // using map -> key value
        Map<String,Object> map = new HashMap<>();
        // use map to pass the value to the query
        map.put("customerID",1234);
        map.put("companyName","Xibin");
        map.put("contactName","Tang");
        map.put("contactTitle","Hello");

        // use pojo to pass value:
        SqlSession sqlSession = SqlSessionUtil.openSession();
        sqlSession.insert("insertCustomer");
    }
}
