package academy.digitallab.shopping.client;

import academy.digitallab.shopping.model.Customer;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name = "customer-service", fallback=CustomerHystrixFallbackFactory.class)
public interface CustomerClient {

    @GetMapping(value = "/api/customers/{id}")
    ResponseEntity<Customer> getCustomer(@PathVariable("id") long id);
}
