package com.boot.bootie;

import com.boot.bootie.service.Service;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class Controller {
     private final Service service;
     @GetMapping("/main")
     public void mainEndpoint(){
         System.out.println("inside main endpoint");
     }

}
