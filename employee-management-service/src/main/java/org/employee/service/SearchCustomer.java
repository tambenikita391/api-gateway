package org.employee.service;

import java.util.List;

import org.employee.dto.SearchCustomerResponse;
import org.employee.entity.Customer;
import org.employee.repo.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@Component
public class SearchCustomer {

	@Autowired
	CustomerRepository repo;

	@Autowired
	SearchCustomerResponse response;

	public SearchCustomerResponse searchCustomerById(Long id) {

		List<Customer> customer = repo.findByCustomer_id(id);
		if (customer.isEmpty()) {
			response.setStatus("Fail");
			response.setMessage("Customer not found!!");
			response.setCustomerCode(0);
		} else {
			Customer table = customer.get(0);
			response.getCustomerData().setFirstName(table.getFirst_name());
			response.getCustomerData().setMiddleName(table.getMiddle_name());
			response.getCustomerData().setEmailID(table.getEmail_id());
		}
		return response;

	}

}
