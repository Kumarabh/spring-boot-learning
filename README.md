#### JPA query methods / Custom finder methods / Derived query methods
```
https://docs.spring.io/spring-data/jpa/reference/jpa/query-methods.html
```

#### Difference between @Controller and @RestController
- By default @RequestMapping method returns a view page, example: hello.jsp

```
@Controller
public class TestController {
@RequestMapping("/test", method = RequestMethod.GET)
public String test() {
  return "hello.jsp"
}
}
```

- @ResponseBody decorator is used to return a simple string response "Hello world".

```
@Controller
public class TestController {

@RequestMapping("/test", method = RequestMethod.GET)
@ResponseBody
public String test() {
  return "hello world"
}

}
```

- @RestController decorator is used to avoid using @ResponseBody decorator on request mapping method.

```
@RestController
public class TestController {

@RequestMapping("/test", method = RequestMethod.GET)
public String test() {
  return "hello world"
}

}
```
