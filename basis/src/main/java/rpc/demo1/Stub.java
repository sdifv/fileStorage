package rpc.demo1;


import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.Socket;

public class Stub {
  public static Object getStub(final Class clazz) {
    InvocationHandler handler = new InvocationHandler() {
      public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Socket s = new Socket("127.0.0.1", 8888);

        ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());

        String clazzName = clazz.getName();
        String methodName = method.getName();
        Class[] parametersTypes = method.getParameterTypes();

        oos.writeUTF(clazzName);
        oos.writeUTF(methodName);
        oos.writeObject(parametersTypes);
        oos.writeObject(args);
        oos.flush();

        ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
        Object o = ois.readObject();

        oos.close();
        s.close();
        return o;
      }
    };
    Object o = Proxy.newProxyInstance(clazz.getClassLoader(), new Class[]{clazz}, handler);
    return o;
  }
}
