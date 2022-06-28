package com.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class MyService {
    @Autowired
    private Repo repo;

    void save(FileToShare fileToShare) {
        repo.save(fileToShare);
    }

    FileToShare retrieve(Integer id) {
        Optional<FileToShare> byId = repo.findById(id);
        if (byId.isEmpty()) {
            throw new NoSuchElementException();
        }
        return byId.get();
    }

    void deleteExpired() {
        repo.deleteExpired();
    }

    List<FileToShare> retrieveAll() {
        return repo.findAll();
    }
}
