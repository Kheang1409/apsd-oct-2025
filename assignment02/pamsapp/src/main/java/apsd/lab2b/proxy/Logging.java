package apsd.lab2b.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class Logging implements InvocationHandler{

    private Object target;
    
    public Logging(Object target) {
        this.target = target;
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("Invoking method: " + method.getName());
        if (args != null) {
            for (Object arg : args) {
                System.out.println("  with argument: " + arg);
            }
        }
        Object result = method.invoke(target, args);
        System.out.println("Method " + method.getName() + " completed");
        return result;
    }
    
}
