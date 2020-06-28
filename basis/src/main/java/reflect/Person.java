package reflect;

public class Person {
  public int id;
  private String name;
  private int age;

  public Person(int id, String name, int age) {
    this.id = id;
    this.name = name;
    this.age = age;
  }

  public Person() {
  }

  public static void walk() {
    System.out.println("a person is walking");
  }

  private String chat(String msg) {
    return "received a msg: "+msg;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getAge() {
    return age;
  }

  public void setAge(int age) {
    this.age = age;
  }

  @Override
  public String toString() {
    return "User{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", age=" + age +
            '}';
  }
}
