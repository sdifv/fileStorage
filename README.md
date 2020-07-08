## 项目介绍
**fileStorage**是一个用于提供文件存储服务的实战项目，业务场景是用户能够通过网页方便地在线管理本地和远程存储的文件。非常适合springboot框架的初学者，喜欢的小伙伴可以点上面的Star支持一下嘛！

项目原型来自B站教程 [基于SpringBoot和Mybatis企业级文件上传下载项目实战](https://www.bilibili.com/video/BV1764y1u7gn)，并在教程的基础上，实现和完善以下功能：
1. 增加新用户注册环节，实现多用户注册、登录功能；
2. 增加对资源访问的拦截机制，使用Shiro对用户进行认证和授权；
3. 前端使用layUI框架对界面进行美化，增加分页机制；
4. 实现文件上传、下载、在线预览的基本业务功能，使用ajax来响应行工具事件，并同步刷新表格；

## 技术栈
- **前端**
  HTML、CSS、JavaScript、JQuery
  LayUI框架中的table、laypage、layer模块
- **后端**
  Springboot+mybatis
  Shiro安全框架
  themyleaf模板引擎

## 界面展示
- 主界面
  ![fileIndex](https://github.com/sdifv/fileStorage/blob/master/doc/img/fileList.PNG)
- 预览界面
  ![preview](https://github.com/sdifv/fileStorage/blob/master/doc/img/preview.PNG)

## 项目启动
1. 克隆项目
2. 导入`pom.xml`中的maven依赖
3. 使用`fileStorage/src/main/resources/database/fileStorage.sql`脚本生成数据表
4. 修改`fileStorage/src/main/resources/application.yaml`配置文件中数据库配置
5. 启动项目，访问`localhost:8080/fileStorage/index`进行访问，新用户需注册
6. 上传的文件路径在项目根目录下的`fileStorage/classpath/fileContainer`目录下

## 写在最后
作者只是一个刚入坑springboot的新手，项目勉强能跑起来，存在很多的不规范以及未排查出的bug，报错欢迎大家一起debug,嘿嘿嘿
  


