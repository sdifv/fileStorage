package rpc.services;

import java.io.Serializable;

public class User implements Serializable {

  private static final long serialVersionUID = -1L;

  public Integer id;
  public String name;

  public User(Integer id, String name) {
    this.id = id;
    this.name = name;
  }

  @Override
  public String toString() {
    return "User{" +
            "id=" + id +
            ", name='" + name + '\'' +
            '}';
  }
}
