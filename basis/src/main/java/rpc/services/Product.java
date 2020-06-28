package rpc.services;

import java.io.Serializable;

public class Product implements Serializable {

  private static final long serialVersionUID = -1L;

  public Integer id;
  public String name;
  public double price;

  public Product(Integer id, String name, double price) {
    this.id = id;
    this.name = name;
    this.price = price;
  }

  @Override
  public String toString() {
    return "Product{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", price=" + price +
            '}';
  }
}
