package com.github.mageddo.customer.controller;

import com.github.mageddo.customer.entity.CustomerEntity;
import com.github.mageddo.customer.exception.ValidationException;
import com.github.mageddo.customer.service.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private CustomerService customerService;

	@RequestMapping(method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity create(@RequestBody CustomerEntity customerEntity) {
		customerService.create(customerEntity);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

	@RequestMapping(value = "{customerId}", method = RequestMethod.PUT)
	@ResponseBody
	public ResponseEntity update(
		@PathVariable("customerId") Long customerId, @RequestBody CustomerEntity customerEntity
	) {
		customerEntity.setId(customerId);
		customerService.update(customerEntity);
		return ResponseEntity.ok().build();
	}

	@RequestMapping(value = "{customerId}", method = RequestMethod.DELETE)
	@ResponseBody
	public ResponseEntity delete(@PathVariable("customerId") Long customerId) {
		customerService.delete(customerId);
		return ResponseEntity.ok().build();
	}

	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity findCustomers() {
		return ResponseEntity.ok(customerService.findAll());
	}

	@RequestMapping(value = "{customerId}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity findUniqueCustomer(@PathVariable("customerId") Long customerId) {
		return ResponseEntity.ok(customerService.find(customerId));
	}

	@ExceptionHandler(ValidationException.class)
	@ResponseBody
	public ResponseEntity clientErrorHandler(ValidationException e){
		return ResponseEntity.badRequest().body(Map.of("msg", e.getMessage()));
	}

	@ExceptionHandler(Throwable.class)
	@ResponseBody
	public ResponseEntity serverErrorHandler(Throwable e){
		logger.error("status=internal-error, msg={}", e.getMessage(), e);
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.body(Map.of("msg", "Temporary unavailable, sorry for the inconvenience"));
	}

}
