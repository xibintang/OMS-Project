package com.nyit.oms.test;

import com.nyit.oms.views.LoginScreen;
import org.junit.Test;

public class TestOMS {
    @Test
    public void testOMS(){
        LoginScreen loginScreen = new LoginScreen();
        loginScreen.invoke();

        System.out.println("Hello World");
    }
}
