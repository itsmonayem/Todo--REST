package com.spring.todo.controller;

import com.spring.todo.entities.Todo;
import com.spring.todo.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class TodoController {
    private TodoService todoService;

    @Autowired
    public void setTodoService(TodoService todoService) {
        this.todoService = todoService;
    }


    @GetMapping("/todo")
    public ResponseEntity<List<Todo>> getTodo() {
        List<Todo> todoList = this.todoService.getTodo();
        if (todoList.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.of(Optional.of(todoList));
    }


    @GetMapping("/todo/{id}")
    public ResponseEntity<Optional<Todo>> getTodoById(@PathVariable("id") int id) {
        return this.todoService.getTodoById(id);
    }


    @PostMapping("/todo")
    public Todo addTodo(@RequestBody Todo todo) {
        return this.todoService.addTodo(todo);
    }


    @DeleteMapping("/todo/{id}")
    public ResponseEntity<String> deleteTodo(@PathVariable("id") int id) {
        boolean status = this.todoService.deleteTodo(id);
        if (status) {
            return new ResponseEntity<>("Todo is deleted", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("No such todo", HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/todo/{id}")
    public ResponseEntity<Todo> updateTodo(@RequestBody Todo todo, @PathVariable("id") int id) {
        return this.todoService.updateTodo(todo, id);
    }




}
