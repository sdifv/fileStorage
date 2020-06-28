package rpc.demo1;


import rpc.services.IUserService;
import rpc.services.User;

public class UserServiceImpl implements IUserService {
  public User findUserById(Integer id) {
    return new User(id, "lucy");
  }
}
