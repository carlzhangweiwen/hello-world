可将应用打包成war部署到tomcat7或tomcat8

单元测试测试可通过继承com\autumnsinger\AutumnSingerApplicationTests.java来写单元测试；


配置信息
application.yml
com\autumnsinger\configure\MybatisConfigurer.java


自动化生成代码：
新增表可配置在src\test\resources\generatorConfig.xml;
通过运行\src\test\java\com\autumnsinger\generator\GenerateMapper.java;
可通过mybatis-generator自动生成代码.