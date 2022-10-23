package com.boot.bootie.repository;

import com.boot.bootie.model.Shoes;
import org.springframework.data.jpa.repository.JpaRepository;

@org.springframework.stereotype.Repository
public interface ShoesRepository extends JpaRepository<Shoes, Long> {
}
