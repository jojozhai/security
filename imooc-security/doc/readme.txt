1.引入依赖(pom.xml)
<dependency>
	<groupId>com.imooc.security</groupId>
	<artifactId>imooc-security-browser</artifactId>
	<version>1.0.0-SNAPSHOT</version>
</dependency>

2.配置系统(参见 application-example.properties)

3.增加UserDetailsService接口实现

4.如果需要记住我功能，需要创建数据库表(参见 db.sql)

5.如果需要社交登录功能，需要以下额外的步骤
1).配置appId和appSecret
2).创建并配置用户注册页面，并实现注册服务(需要配置访问权限)，注意在服务中要调用ProviderSignInUtils的doPostSignUp方法。
3).添加SocialUserDetailsService接口实现
4).创建社交登录用的表 (参见 db.sql)