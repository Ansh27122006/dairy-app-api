package com.amardairy.serviceImpl;

import com.amardairy.entity.Order;
import com.amardairy.entity.User;
import com.amardairy.repository.OrderRepository;
import com.amardairy.repository.UserRepository;
import com.amardairy.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final UserRepository userRepository;
    private final OrderRepository orderRepository;

    public List<User> getAllCustomers() {
        return userRepository.findAll();
    }

    public Optional<User> getCustomerById(Long id) {
        return userRepository.findById(id);
    }

    public List<Order> getCustomerOrders(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Customer not found"));
        return orderRepository.findByPhone(user.getPhone());
    }

    public List<User> searchCustomers(String query) {
        return userRepository.findByNameContainingIgnoreCaseOrPhoneContaining(query, query);
    }

    public void deleteCustomer(Long id) {
        userRepository.deleteById(id);
    }

    public User toggleActiveStatus(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found"));
        user.setIsActive(!user.getIsActive());
        return userRepository.save(user);
    }
}