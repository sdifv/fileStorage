package rpc.demo1;


import rpc.services.IProductService;
import rpc.services.Product;

public class ProductServiceImpl implements IProductService {
  public Product findProductById(Integer id){
    return new Product(id,"cake",5.5);
  }
}
