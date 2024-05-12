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

#### Rest Controller

```
package com.boot.application.controllers;

import java.time.LocalDate;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.boot.application.entities.Customer;

@RestController
public class TestController {

	@GetMapping("/test")
	public String test() {
		return "Hello World";
		
	}
	
	@GetMapping("/customer")
	public Customer getCustomer() {
		Customer c1 = new Customer(101, "John Constantino", "Male", LocalDate.parse("1995-01-09"));
		return c1;
	}
	
}
```
