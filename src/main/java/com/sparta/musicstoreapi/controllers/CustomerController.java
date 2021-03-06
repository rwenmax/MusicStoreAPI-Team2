package com.sparta.musicstoreapi.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sparta.musicstoreapi.entities.Customer;
import com.sparta.musicstoreapi.entities.Token;
import com.sparta.musicstoreapi.entities.Track;
import com.sparta.musicstoreapi.repositories.CustomerRepository;
import com.sparta.musicstoreapi.repositories.TokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/chinook")
public class CustomerController {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private TokenRepository tokenRepository;

    @Autowired
    private ObjectMapper objectMapper;


    /**
     * Get all customers
     * @return all customers
     */
    @GetMapping(value = "/customers/{token}", produces = { MediaType.APPLICATION_JSON_VALUE , MediaType.APPLICATION_XML_VALUE, })
    public List<Customer> findAllCustomer(@PathVariable String token){
        Optional<Token> tokenResult = tokenRepository.findByToken(token);
        if (tokenResult.isPresent()) {
            if (tokenResult.get().getPermissionLevel() >= 1) {
                return customerRepository.findAll();
            }
        }
        return null;
    }

    /**
     * Return customer by id
     * @param id customer id
     * @return null if non existent
     */
    @GetMapping(value = "/customer/{id}/{token}", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE, })
    public Customer findCustomerById(@PathVariable Integer id, @PathVariable String token){
        Optional<Token> tokenResult = tokenRepository.findByToken(token);
        if (tokenResult.isPresent()) {
            if (tokenResult.get().getPermissionLevel() >= 1) {
                Optional<Customer> result = customerRepository.findById(id);
                if (result.isEmpty()) return null;
                return result.get();
            }
        }
        return null;
    }

    /**
     * Add new customer, json format
     * @param newCustomer json format
     * @return Customer object
     */
    @PostMapping(value = "/customer/add/{token}", produces = { MediaType.APPLICATION_JSON_VALUE , MediaType.APPLICATION_XML_VALUE })
    public Customer addCustomer(@RequestBody Customer newCustomer, @PathVariable String token){
        Optional<Token> tokenResult = tokenRepository.findByToken(token);
        if (tokenResult.isPresent()) {
            if (tokenResult.get().getPermissionLevel() >= 2) {
                return customerRepository.save(newCustomer);
            }
        }
        return null;
    }

    /**
     * Update customer by id. If id doesnt exist return no match found
     * @return no match found if no id
     */
    @PutMapping(value = "/customer/update/{token}", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<String> updateCustomer(@Valid @RequestBody Customer customer, @PathVariable String token){
        Optional<Token> tokenResult = tokenRepository.findByToken(token);
        if (tokenResult.isPresent()) {
            if (tokenResult.get().getPermissionLevel() >= 2) {
                Optional<Customer> customerToUpdate = customerRepository.findById(customer.getId());
                if (customerToUpdate.isPresent()) {
                    customerRepository.save(customer);
                    return new ResponseEntity<String>("Customer updated", HttpStatus.OK);
                } else {
                    return new ResponseEntity<>("Customer doesn't exists.", HttpStatus.NOT_FOUND);
                }
            }
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    @GetMapping(value = "/customer/findbyemail/{email}/{token}", produces = { MediaType.APPLICATION_JSON_VALUE , MediaType.APPLICATION_XML_VALUE })
    public Customer searchCustomerByEmail(@PathVariable String email, @PathVariable String token){
        Optional<Token> tokenResult = tokenRepository.findByToken(token);
        if (tokenResult.isPresent()) {
            if (tokenResult.get().getPermissionLevel() >= 1) {
                return customerRepository.findCustomerByEmail(email);
            }
        }
        return null;
    }
}
