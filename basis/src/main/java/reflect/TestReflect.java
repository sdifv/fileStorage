package reflect;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;

public class TestReflect {

  public static void test1() throws Exception{
    /*
     * 获取反射类的三种方法:
     * 1. Class.forname(package+classname)
     * 2. obj.getClass()
     * 3. Classname.class
     */
    Class<?> clazz = Class.forName("reflect.Person");
    System.out.println(clazz);

    Person person = new Person();
    System.out.println(person.getClass());

    System.out.println(Person.class);
  }

  public static void test2() throws Exception{
    // 通过反射类获取属性，并操作
    Person person = new Person(1,"zs",22);
    Class<?> clazz = person.getClass();

    // 获取当前反射类的符合访问修饰符的属性
    Field[] pubFields = clazz.getFields();
    System.out.println(Arrays.toString(pubFields));

    // 获取当前反射类的所有属性
    Field[] fields = clazz.getDeclaredFields();
    System.out.println(Arrays.toString(fields));

    // 根据属性名获取对应的属性
    Field ageField = clazz.getDeclaredField("age");
    // 修改访问权限，对私有属性进行操作
    ageField.setAccessible(true);
    ageField.set(person, 25);
    System.out.println(person);
  }

  public static void test3() throws Exception{
    Class<?> clazz = Person.class;

    // 获取当前类和从父类方法继承的方法,符合访问修饰符限制
    Method[] allMethods = clazz.getMethods();
    System.out.println(Arrays.toString(allMethods));

    // 获取当前类定义的所有方法
    Method[] methods = clazz.getDeclaredMethods();
    System.out.println(Arrays.toString(methods));

    // 获取方法并调用, 符合访问限制符
    Method walkMethod = clazz.getMethod("walk", null);
    walkMethod.invoke(clazz.newInstance(),null);

    // 获取私有方法
    Method chatMethod = clazz.getDeclaredMethod("chat", String.class);
    chatMethod.setAccessible(true);
    Object invoke = chatMethod.invoke(clazz.newInstance(), "hello!");
    System.out.println(invoke);
  }

  public static void main(String[] args) {
    try {
      test3();
    }catch (Exception e){
      e.printStackTrace();
    }
  }
}
