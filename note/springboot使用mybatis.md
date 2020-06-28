
### 简介

**MyBatis**可以使用简单的 XML 或注解来配置和映射原生信息，将接口和 Java 的 POJOs(Plain Ordinary Java Object)映射成数据库中的记录。

[官方文档](https://www.baidu.com/link?url=agWvS-PB231vy9CxVkE9Z1YOqbOuB-BeGRBomvKaEbe_4WniYAH7d54B7L27nDPg&wd=&eqid=9092830c00046477000000065ef73da5)

### 使用方法

1. 添加maven依赖
   1. mysql-connector-java
   2. mybatis-spring-boot-starter
   3. spring-boot-starter-jdbc
    > 如果在pom.xml文件中输入依赖名，却无法所引到仓库中的包，建议复制完整的dependency依赖
2. 测试MySQL驱动
   >在IDEA的Database窗口选择MySQL,填写User,Password,Port,Database,URL，测试数据库驱动能否正常运行。如果使用的`mysql-connector-java`版本在6以上,URL中需要配置`serverTimezone`字段，否则会报错。
3. 配置spring.datasource
    ```yaml
    spring:
        datasource:
        username: root
        password: 12345
        # 指定时区、编码
        url: jdbc:mysql://localhost:3306/mybatis?serverTimezone=Asia/Shanghai&userUnicode=true&characterEncoding=utf-8
        # 使用推荐的驱动，如果报错，考虑MySQL版本
        driver-class-name: com.mysql.cj.jdbc.Driver
    ```
4. 定义Java POJO查询接口，然后被业务层调用，来与数据库进行交互；
5. 编写Mapper映射接口中的数据库查询功能；
6. 配置mybatis，将POJO接口于Mapper配置绑定;
   ```yaml
    mybatis: 
        # mapper.xml文件的路径
        mapper-locations: classpath:mybatis/mapper/*.xml
        # pojo定义的包路径
        type-aliases-package: com.yanhang.pojo
    ```

### Mapper编写规则
定义如下三个相关的pojo
```java
public class User {
    private  Integer id;
    private String name;
    private String email;
    private Job job;
    private List<Order> orders;
    // getter() and setter()
}

public class Job{
    private Integer id;
    private String name;
    // getter() and setter()
}

public class Order{
    private Integer userId;
    private Integer orderId;
    private Integer number;
    // getter() and setter()
}
```
配置UserMapper对应的查询语句

```xml
<!-- 文件头，固定写法 -->
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper
        PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<!-- 命名空间为对应的pojo查询接口全路径包名 -->
<mapper namespace="com.yhao.mapper.UserMapper">
    <!-- 
        id: 与命名空间里Mapper接口的函数名一致
        resultType: 查询结果对应的Java类型
        parameterType: 传递给查询语句的Java参数类型
     -->
    <delete id="deleteUser" parameterType="int">
        delete from user where id=#{id}
    </delete>

    <!-- 使用 resultMap 自定义返回类型 -->
    <!-- 
        id: 指定自定义类型的调用名称
        type: 自定义类型对应的Java类型
     -->
    <resultMap id="UserMap" type="User">
        <!-- 
            id: 指定主键列
            column: 数据表中的列名
            property: Java类型的属性名
         -->
        <id column="id" property="id"/>
        <result column="user_name" property="name"/>
        <result column="email" property="email"/>

        <!-- 一对一的引用类型使用 association -->
        <!-- 可以 来定义属性中引用类型的绑定规则 -->
        <association property="job" javaType="Job">
            <id property="id" column="job_id"/>
            <result property="name" column="job_name"/>
        </association>

        <!-- 一对多的引用类型使用 collection -->
        <collection property="ordersList" ofType="Orders">
        <id column="order_id" property="id" />
        <result column="user_id" property="userId" />
        <result column="number" property="number" />
        </collection>
    </resultMap>

    <select id="queryUserList" resultMap="UserMap">
        <!-- 可以通过定义别名的方式来解决表名和 column 字段的不一致问题 -->
        select u.id ,u.name user_name, email, 
            j.id job_id, j.name job_name, 
            order_id, user_id, number
        from user u, job j, order o
        where u.job_id == j.id and u.id == o.user_id
    </select>
</mapper>
```