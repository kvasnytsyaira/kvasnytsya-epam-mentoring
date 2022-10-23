package com.boot.bootie.service;


import com.boot.bootie.model.Order;
import com.boot.bootie.model.Shoes;
import com.boot.bootie.model.User;
import com.boot.bootie.repository.OrderRepository;
import com.boot.bootie.repository.ShoesRepository;
import com.boot.bootie.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.aspectj.weaver.ast.Or;

import java.time.LocalDate;
import java.util.Optional;

@org.springframework.stereotype.Service
@RequiredArgsConstructor
public class Service {

    private final OrderRepository orderRepository;
    private final ShoesRepository shoeRepository;
    private final UserRepository userRepository;

    public void start() {
        Shoes shoe1 = new Shoes(37, "Sneakers", "Ecco");
        Shoes shoe2 = new Shoes(38, "Sneakers", "Ecco");
        Shoes shoe3 = new Shoes(39, "Sneakers", "Ecco");

        User user1 = new User("Ira", "ira@gmail.com");
        User user2 = new User("Oleh", "oleh@gmail.com");
        User user3 = new User("Kate", "kate@gmail.com");

        Order order1 = new Order(user1, shoe1);
        Order order2 = new Order(user2, shoe2);
        Order order3 = new Order(user3, shoe3);

        System.out.println(order1.toString());
        System.out.println(order2.toString());
        System.out.println(order3.toString());

        shoeRepository.save(shoe1);
        shoeRepository.save(shoe2);
        shoeRepository.save(shoe3);

        userRepository.save(user1);
        userRepository.save(user2);
        userRepository.save(user3);

        orderRepository.save(order1);
        orderRepository.save(order2);
        orderRepository.save(order3);
    }

    public Order createOrder(Order order) {
        shoeRepository.save(order.getShoe());
        userRepository.save(order.getUser());
        return orderRepository.save(order);
    }

    public Order readOrder(Long orderId) throws Exception {
        return orderRepository.findById(orderId)
                .orElseThrow(() -> new Exception("No order with such email"));
    }

    public void updateOrder(Order order) throws Exception {
        Order byId = orderRepository.findById(order.getId())
                .orElseThrow(() -> new Exception("No order with such email"));
        byId.setShoe(order.getShoe());
        byId.setUser(order.getUser());
    }

    public void deleteOrder(Long id){
        orderRepository.deleteById(id);
    }


}
