# springboot-fileupload
## 使用方法

### 方式1（@RequestPart）

```java
@PostMapping("/upload1")
public String upload1(@RequestPart MultipartFile file) throws IOException {
    // 上传文件输入流
    //InputStream inputStream = file.getInputStream();
    // 上传文件名
    return file.getOriginalFilename();
}
```

### 方式2（@RequestParam）

```java
@PostMapping("/upload2")
public String upload2(@RequestParam MultipartFile file) throws IOException {
    // 上传文件输入流
    //InputStream inputStream = file.getInputStream();
    // 上传文件名
    return file.getOriginalFilename();
}
```

## 采坑记录

### 自定义`ServletRegistrationBean`引发无法上传文件

> 资料参见：
>
> + [Spring setThrowExceptionIfNoHandlerFound 引发的文件上传问题](https://www.jianshu.com/p/ea6cc969acb8) 
> + [SpringMVC工作原理之四：MultipartResolver](https://www.cnblogs.com/tengyunhao/p/7670293.html) 

之前项目中，使用了如下配置

```java
@Bean  
public ServletRegistrationBean dispatcherRegistration(DispatcherServlet dispatcherServlet) {  
    ServletRegistrationBean registration = new ServletRegistrationBean(  
            dispatcherServlet);  
    dispatcherServlet.setThrowExceptionIfNoHandlerFound(true);  
    return registration;  
} 
```

该配置会导致`springboot`没有自动配置`DispatcherServletAutoConfiguration`，所以没有配置文件上传需要的`MultipartResolver`，导致文件上传失效

解决方案是将上述配置在`application.properties`中进行配置

```properties
spring.mvc.throw-exception-if-no-handler-found=true
```

该配置会在`springboot`的自动配置类上进行设置