# springboot-fileupload
>  使用springboot进行文件上传的2中方式

## 方式1（@RequestPart）

```java
@PostMapping("/upload1")
public String upload1(@RequestPart MultipartFile file) throws IOException {
    // 上传文件输入流
    //InputStream inputStream = file.getInputStream();
    // 上传文件名
    return file.getOriginalFilename();
}
```

## 方式2（@RequestParam）

```java
@PostMapping("/upload2")
public String upload2(@RequestParam MultipartFile file) throws IOException {
    // 上传文件输入流
    //InputStream inputStream = file.getInputStream();
    // 上传文件名
    return file.getOriginalFilename();
}
```

## 方式3（MultipartResolver）

> 这种方式本次测试中并未成功，但是在`华生基因数据分析系统`的`kpi`部分时，因为系统使用了shiro，所以前面两种方式不能成功，使用该方式解决了问题，这种方法先记下来，后续学习完shiro再探讨这个问题

```java
@PostMapping("/upload3")
public String upload3(MultipartHttpServletRequest request) {
    // 获取上传文件
    MultipartResolver resolver = new CommonsMultipartResolver(request.getServletContext());
    System.out.println(resolver.isMultipart(request));
    MultipartHttpServletRequest multipartRequest = resolver.resolveMultipart(request);
    Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
    System.out.println(fileMap.toString());
    MultipartFile file = fileMap.get("file");

    // 上传文件名
    return file.getOriginalFilename();
}
```

## 遗留问题

> shiro如何导致常规文件上传失效？
>
> 什么情况下能让方式3的文件上传成功？