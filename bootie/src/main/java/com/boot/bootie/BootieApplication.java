package com.boot.bootie;

import com.boot.bootie.model.Order;
import com.boot.bootie.model.Shoes;
import com.boot.bootie.model.User;
import com.boot.bootie.service.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class BootieApplication implements CommandLineRunner {


	public static void main(String[] args) throws Exception {
		ConfigurableApplicationContext applicationContext = SpringApplication.run(BootieApplication.class, args);
		Service service = (Service) applicationContext.getBean("service");
		User sanya = new User("Sanya", "sanya@gmail.com");
		Shoes shoes42 = new Shoes(42, "Flats", "Nike");
		Shoes shoes43 = new Shoes(43, "Flats", "Nike");
		Order order = new Order(sanya, shoes42);

		Order order1 = service.createOrder(order);
		System.out.println(service.readOrder(order1.getId()));
		order1.setShoe(shoes43);
		service.updateOrder(order1);
		System.out.println(service.readOrder(order1.getId()));
		service.deleteOrder(order1.getId());
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("run");
	}
}
