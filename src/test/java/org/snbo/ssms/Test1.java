package org.snbo.ssms;

import org.junit.Test;
import org.snbo.ssms.view.studentview.StudentVo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.*;

/**
 * @author sunbo
 * @date 2022-06-02-13:34
 */

public class Test1 {


    Scanner input = new Scanner(System.in);
    BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

    @Test
    public void test() throws IOException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
/*        String a = in.readLine();
        System.out.println(a);
        HashMap<Object, Object> map = new HashMap<>();
        Set<Map.Entry<Object, Object>> entries = map.entrySet();
        entries.forEach((entry) -> {
            System.out.println(entry);
        });
        entries.forEach(System.out::println);*/
        Class<StudentVo> c = StudentVo.class;
        Method method = c.getMethod("getName");
//        Object invoke = method.invoke(new StudentVo(111, "fdsa", "fds", 23.1, 23.1, 23.1, 23.2));
//        System.out.println(invoke);


    }

}

interface Send {
    void sendMessage();
}

class SendImpl implements Send {
    private final String code = "code...";

    @Override
    public void sendMessage() {
        System.out.println(code);
    }

    @Override
    public String toString() {
        return "SendImpl{" +
                "code='" + code + '\'' +
                '}';
    }
}

class MyInvocationHandler implements InvocationHandler {

    private final Object target;

    public MyInvocationHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("previous invoke");
        Object result = method.invoke(target, args);
        System.out.println("after invoke");

        return result;
    }
}

class ProxyClass {
    public static Object getProxy(Object target) {
        return Proxy.newProxyInstance(target.getClass().getClassLoader(),
                target.getClass().getInterfaces(), new MyInvocationHandler(target));
    }
}



