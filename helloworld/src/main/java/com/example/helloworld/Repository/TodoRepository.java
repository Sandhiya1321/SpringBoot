package com.example.helloworld.Repository;

import com.example.helloworld.models.Toedo;
import org.springframework.data.jpa.repository.JpaRepository;

//Example
//@Component
//public class TodoRepository {
//
//    String getAllTodos(){
//        return "todos";
//    }

//CRUD
public interface TodoRepository extends JpaRepository<Toedo,Long> {

}