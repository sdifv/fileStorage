package rpc.demo1;

import rpc.services.IProductService;
import rpc.services.IUserService;

public class Client {
  public static void main(String[] args) {
    IUserService userService = (IUserService) Stub.getStub(IUserService.class);
    System.out.println(userService.findUserById(123));

    IProductService productService = (IProductService) Stub.getStub(IProductService.class);
    System.out.println(productService.findProductById(123));
  }
}
