package com.example.helloworld.Controller;

import com.example.helloworld.Service.TodoService;
import com.example.helloworld.models.Toedo;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/todo")
@Slf4j

//request mapping
public class TodoController {
    @Autowired
    private TodoService todoService;
//    @GetMapping("/path")
//    String todopath(){
//        return "Todo";
//    }
//    @GetMapping("/get")
//    String getTodo(){
//        return "Todo";
//    }
//
//    @GetMapping("/ids")
//    String getTodoByIdddddd(){
//        return "Todo with id";
//    }

    //putmapping
//    @PutMapping("/update/{ids}")
//    String updateTodoById(@PathVariable long id){
//        return "todo with id: "+id;
//    }

//path variable
//    @GetMapping("/{id}")
//    String getTodoById1(@PathVariable long id){
//        return "Todo with id" +id;
//    }

    //request para
//    @GetMapping("/empty")
//    String getTodoByIdParam(@RequestParam long id){
//        return "Todo with id"+id;
//    }

//    @GetMapping
//    String getTodoByIdParam1(@RequestParam("todoId") long id){
//        return "Todo with id"+id;
//    }

    //request body
    //its wrong becoz of security flaw
//    @GetMapping("/creates")
//    String createUser1(@RequestParam String userId,@RequestParam String pass){
//        return "todo with id: "+userId+" "+"password: "+pass;
//    }



//    @GetMapping("/app")
//    ResponseEntity<Toedo> getTodoById(@RequestParam long id){
//        return new ResponseEntity<>(todoService.getTodoById(id),HttpStatus.OK);
//        //accepted--ok
//    }

//    delete
//    @DeleteMapping("/delete/{idss}")
//    String deleteTodoById(@RequestBody String body){
//        return "todo with id: "+body;
//    }

    //crud
    //this is correct and we run this in platforms like postman
    @PostMapping("/create")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Todo is created successfully"),
            @ApiResponse(responseCode = "404",description = "Todo not found")
    })
    ResponseEntity<Toedo> createUser(@RequestBody Toedo body){
        return new ResponseEntity<>(todoService.createTodo(body), HttpStatus.CREATED);
        //http response 200++ success,400++ fail,500++ server prblm
    }


    @GetMapping("/app")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Todo is created successfully"),
            @ApiResponse(responseCode = "404",description = "Todo not found")
    })
    ResponseEntity<Toedo> getTodoById(@RequestParam long id){
        try{
            Toedo createdTodo=todoService.getTodoById(id);
            return new ResponseEntity<>(createdTodo,HttpStatus.OK);
        }catch(RuntimeException exception){
            //just saying information
            log.info("Error");
            //there is a prblm but doesn't affect
            log.warn("",exception);
            //

            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        }
        //404 -not found error
        //if we need 404 with any msg then instead of null we can give msg
        //accepted--ok
    }

    //if we need all the list of todo

    @GetMapping
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Todo is created successfully"),
            @ApiResponse(responseCode = "404",description = "Todo not found")
    })
    ResponseEntity<List <Toedo>> getTodooo(){
        return new ResponseEntity<List <Toedo>>(todoService.getTodos(),HttpStatus.OK);
    }

    //update
    @PutMapping
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Todo is created successfully"),
            @ApiResponse(responseCode = "404",description = "Todo not found")
    })
    ResponseEntity<Toedo> updateTodo(@RequestBody Toedo todo){
        return new ResponseEntity<>(todoService.updateTodo(todo),HttpStatus.OK);
    }
    //delete
    @DeleteMapping ("/{id}")
    void deleteTodoById(@PathVariable Long id){
        todoService.deleteTodoById(id);
    }

    //pagination endpoint
    @GetMapping("/page")
    ResponseEntity<Page <Toedo>> getTodoPAge(@RequestParam int page,@RequestParam int size){
        return new ResponseEntity<>(todoService.getAllPage(page,size),HttpStatus.OK);
    }
}
