package com.example.springsecuritypasswordencoding.service;

import com.example.springsecuritypasswordencoding.data.CustomerDto;
import com.example.springsecuritypasswordencoding.entity.Customer;
import com.example.springsecuritypasswordencoding.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service("customerService")
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    public Customer saveCustomer(final CustomerDto customerData) {
        Customer customerModel = populateCustomerData(customerData);
        return customerRepository.save(customerModel);
    }

    public Customer findUserByUsername(String userName){
        return customerRepository.findByUserName(userName);
    }

    private Customer populateCustomerData(final CustomerDto customerData) {
        Customer customer = new Customer();
        customer.setUserName(customerData.getUserName());
        customer.setEmail(customerData.getEmail());
        customer.setPassword(passwordEncoder.encode(customerData.getPassword()));
        return customer;
    }
}
