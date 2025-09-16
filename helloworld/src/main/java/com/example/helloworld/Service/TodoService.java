package com.example.helloworld.Service;

import com.example.helloworld.Repository.TodoRepository;
import com.example.helloworld.models.Toedo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoService {

    /*used for simple java
    public TodoService(){
        todorepo=new TodoRepository();
    }
Example
    public void printTodo(){
        System.out.println(todorepo.getAllTodos());
    }
    */
    //crud
    @Autowired
    private TodoRepository todorepo;

    public Toedo createTodo(Toedo todo){
        //save is used to create or update
        //if the id is already there then it update or else create
        return todorepo.save(todo);
    }
    public Toedo getTodoById(Long id){
        return todorepo.findById(id).orElseThrow(() -> new RuntimeException("todo not found"));
    }
    public List<Toedo> getTodos(){
        return todorepo.findAll();
    }
    public Toedo updateTodo(Toedo todo){
        return todorepo.save(todo);
    }
    public void deleteTodoById(Long id){
        todorepo.delete(getTodoById(id));
    }
    public void deleteTodo(Toedo todo){
        todorepo.delete(todo);
    }
    //pagination
    //page-how many pages,size-how many in each page
    public Page<Toedo> getAllPage(int page,int size){
        Pageable pageable= PageRequest.of(page,size);
        return todorepo.findAll(pageable);
    }
}
