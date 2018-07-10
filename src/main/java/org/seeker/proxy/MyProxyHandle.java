package org.seeker.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class MyProxyHandle implements InvocationHandler {
    private Object obj;


    public MyProxyHandle(DemoImpl i) {
        obj=i;
    }
    public MyProxyHandle() {
        obj=this;
    }

    /**
     * @param proxy 代理对象
     * @param method  代理对象里面的方法名称
     * @param args 参数
     * @return
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("============================");
        if(Object.class.equals(method.getDeclaringClass())){
            Object result=method.invoke(this,args);
        }
        System.out.println("----------------------------");
        return null;
    }

    public static void main(String[] args) {
//        DemoImpl i=new DemoImpl();
//        MyProxyHandle proxy=new MyProxyHandle(i);
//        Demo s = (Demo) Proxy.newProxyInstance(DemoImpl.class.getClassLoader(), DemoImpl.class.getInterfaces(), proxy);
//        s.say();


//        DemoImpl i=new DemoImpl();
//        MyProxyHandle proxy=new MyProxyHandle();
//        Demo s = (Demo) Proxy.newProxyInstance(Demo.class.getClassLoader(), new Class[]{Demo.class}, proxy);
//        s.say();


        MyProxyHandle proxy=new MyProxyHandle();
        Demo s = (Demo) Proxy.newProxyInstance(DemoImpl.class.getClassLoader(), DemoImpl.class.getInterfaces(), proxy);
        s.say();
        s.abc();

    }

}
