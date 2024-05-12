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
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.boot.application.CustomerService;
import com.boot.application.entities.Customer;

@RestController
public class TestController {

	@Autowired
	private CustomerService customerService;
	
	@RequestMapping(path = "/test1", method = RequestMethod.GET)
	public String test1() {
		return "Test-1 works";
	}
	
	@GetMapping("/test2")
	public String test() {
		return "Test-2 works";
		
	}
	
	@GetMapping("/customer/{customerId}")
	public Customer getCustomer(@PathVariable("customerId") int customerId) {
		return this.customerService.getCustomerById(customerId);
		
	}
	
	@GetMapping("/customer")
	public List<Customer> getCustomer() {
		return this.customerService.getAllCustomers();
	}
	
	
}

```
