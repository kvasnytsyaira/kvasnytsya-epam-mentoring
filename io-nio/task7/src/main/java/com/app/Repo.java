package com.app;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface Repo extends JpaRepository<FileToShare, Integer> {

    @Transactional
    @Modifying
    @Query(value = "delete from application_files where local_date < CURRENT_DATE;",nativeQuery = true)
    void deleteExpired();
}
//insert into application_files(id,name,file,local_date) values(1,'name',null,'2017-09-24');
