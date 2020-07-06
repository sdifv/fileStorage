## 给属性赋值的方式
下面方法的应用的应用场景：给JavaConfig绑定特定配置文件的值。可以在项目`Java`同级目录下的`resource`文件夹下
1. 使用`application.yaml`文件给对象object赋值，对应的类需要添加`@Component`和`@ConfigurationProperties(prefix = 'object')`注解，
   在使用对象object时，只需要在声明前添加`@Autowired`注解即可。
2. 使用[application|自定义].properties文件给对象赋值，在idea中，需要设置文件的编码为`UTF-8`来防止出现乱码。然后在对应类前添加`@Component`和`@PropertySource([value = 'property file path'])`加载指定的配置文件，在需要赋值的属性前添加`@Value('SPEL expression)`)来实现绑定。

推荐使用方法1来实现配置绑定。

## yaml语法[教程](https://www.runoob.com/w3cnote/yaml-intro.html)

   