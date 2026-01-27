package com.amardairy.service;

import com.amardairy.entity.Order;
import com.amardairy.entity.User;

import java.util.List;
import java.util.Optional;

public interface CustomerService {
    public List<User> getAllCustomers();
    public Optional<User> getCustomerById(Long id);
    public List<Order> getCustomerOrders(Long userId);
    public List<User> searchCustomers(String query);
    public void deleteCustomer(Long id) ;
    public User toggleActiveStatus(Long id) ;
}

