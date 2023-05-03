package com.example.Book_My_Show.Repository;

import com.example.Book_My_Show.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
   // custom built in query for  unique attribute
    User findByEmail(String email);


    //custom built in query for non- unique attribute  ie it returns list

    List<User> findByAge(int age);


    //ex of complex sql query

    @Query(value = "select * from user u where u.age =:age",nativeQuery = true)
    List<User> custom_query_age(int age);
}
