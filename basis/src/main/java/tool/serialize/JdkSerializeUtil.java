package tool.serialize;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class JdkSerializeUtil extends AbstractSerialize {
  public <T> byte[] serialize(T obj) {
    if (obj == null) {
      throw new NullPointerException();
    }

    ByteArrayOutputStream bos = new ByteArrayOutputStream();
    try {
      ObjectOutputStream oos = new ObjectOutputStream(bos);
      oos.writeObject(obj);

      return bos.toByteArray();
    } catch (Exception e) {
      e.printStackTrace();
    }

    return new byte[0];
  }

  public <T> T deserialize(byte[] data, Class<T> clazz) {
    ByteArrayInputStream bis = new ByteArrayInputStream(data);
    try {
      ObjectInputStream ois = new ObjectInputStream(bis);
      T obj = (T) ois.readObject();
      return obj;
    } catch (Exception e) {
      e.printStackTrace();
    }

    return null;
  }
}
