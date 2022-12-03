package com.nyit.oms.utils;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import java.io.IOException;

public class SqlSessionUtil {

    private static SqlSessionFactory sqlSessionFactory;

    // privatize construction to prevent object creation
    private SqlSessionUtil(){}

    static{
        try {
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream("mybatis-config.xml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // tool to open a session:
    public static SqlSession openSession(){
        return sqlSessionFactory.openSession();
    }
}
