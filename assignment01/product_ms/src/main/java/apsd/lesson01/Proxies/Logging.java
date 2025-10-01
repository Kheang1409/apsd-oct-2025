package apsd.lesson01.Proxies;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class Logging implements InvocationHandler {
    private final Object target;
    public Logging(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("Logging: " + method.getName());
        return method.invoke(target, args);
    }
}
