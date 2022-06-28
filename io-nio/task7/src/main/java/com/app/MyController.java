package com.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/app")
public class MyController {

    @Autowired
    private MyService myService;

    @PostMapping
    public void save(@RequestBody FileToShare fileToShare) {
        System.out.println(fileToShare.toString());
        myService.save(fileToShare);
    }

    @GetMapping("/files/id")
    public FileToShare get(@PathVariable Integer id) {
        return myService.retrieve(id);
    }

    @GetMapping("/files")
    public List<FileToShare> getAll() {
        return myService.retrieveAll();
    }


}
