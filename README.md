## Samples Built with Spring Boot


### Spring Boot
Spring Boot, not a subset of Spring Framework, is like a wrapped platform where we can easily access full Spring features. When, for example, we new a web Spring Starter Project, we won't have the web.xml in the project by default, because Spring Boot wraps in the configuration. When we access a database with JDBC, we just need to put down the connection information in the application property file. As for Mybatis, the configuration file, mybatis-config.xml, doesn't show up in the project.   
These are a few examples. With Spring Boot, we can quickly create a Spring app and get it run with little Spring configuration. However, if you have some special requirements that the default is unable to satisfy, you can customize the configuration, or even completely take over it.   
Spring Boot also provides a bunch of non-functional features, such as embedded web server, database server, security, exception handling etc. In the market are unnumbered tools for building apps, with all kinds of functions, meeting all kinds of needs like fancy feels and looks, in all kinds of domains, that look radically complicate; on the other hand, the goal is very simple and clear, that could be summed up as, taking over system-related job as much as possible and leaving user-specific things to users. Spring Boot would be typical of this design principle. So we can to a significant extent shorten time-to-market of an app.   

<img src="https://github.com/plus-tech/codesamples/blob/main/shared/img/framework.jpg"  alt="Spring Framework" />
    
   
### Dependency Injection (DI)
For example, we would like to access the database using JDBC, so we create a DAO object, in which a JdbcTemplate object is used to execute queries. Will we need to programmatically new a JdbcTemplate object by ourselves? Of course, no, we won't because the container, to be more specific, IoC container in Spring, will instantiate it. We just need to inject it into our DAO object with @Autowired annotation.     
It is common that an object depends on some other objects performing some tasks, which could be Spring default objects or user defined objects. As long as we define these dependencies, the container will automatically inject them when it creates that object, which We also call a bean.    
Dependency injection, a core technology in Spring, frees us of bean instantiation and their dependency relationship construction. We simply inject the beans in need and use them to complete the tasks; this enables us to focus more on programming user specific functions, specifically business logics.     
How does the container identify those beans? We need to tell it through configurations. Specifically, there are three types of configuration:  
<ul>
	<li>Java-based configuration(@Bean)</li>
	<li>Annotation configuration(@Component, @Controller, @Service, @Reporsitory etc.)</li>
	<li>XML-based configuration(e.g., beans.xml)</li>
</ul>
   
Based on the configuration metadata, the container instantiate, configure and assemble the beans. If a bean requires extra data during instantiation, We can supply it using property files, environmental variables, constants, XML tags etc.
Deep down, how does injection work? Our application interacts with the container through ApplicationContext interface. For instance, in the SpringWebClient sample, the interface's method "getBean" is used to look up the client bean.    
```java
SpringWebClient webClient = context.getBean(SpringWebClient.class);
```    
Put all these together, DI is conceptually illustrated in the following diagram.    

<div align="center">
<img src="https://github.com/plus-tech/codesamples/blob/main/shared/img/di.jpg"  alt="Dependency Injection" width="65%" />
</div>    
    
### Aspect Oriented Programming (AOP)
AOP, another core technology in Spring, is a methodology to design and implement cross-cutting concerns across classes. Here are the mostly used terms:
<ul>
<li>Advice: codes implementing a concern, which will be executed at join points.</li>
<li>Pointcut: scope of join points of interest, namely, which methods on which beans in which packages.</li>
<li>Join Point: when an advice is triggered, which is limited to the execution of methods in Spring AOP.</li>
</ul>
    
<div align="center">
<img src="https://github.com/plus-tech/codesamples/blob/main/shared/img/aop.jpg"  alt="Aspect Oriented Programming" width="90%" />
</div>    
<br/>
Features included in each sample project are listed up as follows.

### Spring Web MVC
<ul>
	<li>MVC basics</li>
	<li>Data access with JDBC</li>
	<li>Data access with Mybatis</li>
	<li>Message source</li>
	<li>Security(Form-login)</li>
	<li>Global exception handler</li>
	<li>An AOP example</li>
</ul>


### Spring Batch
<ul>
	<li>A simple batch process</li>
	<li>Data access with JDBC</li>
	<li>Profiles</li>
</ul>


### Spring REST API with WebFlux
<ul>
	<li>REST APIs</li>
	<li>Data access with JDBC</li>
	<li>Profiles</li>
	<li>A web client</li>
</ul>

