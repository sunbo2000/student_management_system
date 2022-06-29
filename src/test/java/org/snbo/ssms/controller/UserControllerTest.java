package org.snbo.ssms.controller;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;

/**
 * UserController Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>06/28/2022</pre>
 */
public class UserControllerTest {

    private UserController userController;

    @Before
    public void before() throws Exception {
        userController = new UserController();
    }

    @After
    public void after() throws Exception {
    }

    /**
     * Method: login(String username, String password)
     */
    @Test
    public void testLogin() throws Exception {
        System.out.println(userController.login("snbo", "11111"));
    }

    /**
     * Method: register(String username, String password)
     */
    @Test
    public void testRegister() throws Exception {
        System.out.println(userController.register("admin", "111111"));
    }


} 
