package com.spring.todo.service;

import com.spring.todo.dao.TodoRepository;
import com.spring.todo.entities.Todo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Component
public class TodoService {

    private TodoRepository todoRepository;

    @Autowired
    public void setTodoRepository(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }


    public List<Todo> getTodo() {
        return (List<Todo>) this.todoRepository.findAll();
    }

    public ResponseEntity<Optional<Todo>> getTodoById(int id) {
        if(this.todoRepository.existsById(id)) {
            Optional<Todo> todo = this.todoRepository.findById(id);
            return new ResponseEntity<>(todo,HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        }
    }


    public Todo addTodo (Todo todo) {
        todo.setDate(new Date());
        return this.todoRepository.save(todo);
    }


    public boolean deleteTodo(int id) {
        if(this.todoRepository.existsById(id)){
            this.todoRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    public ResponseEntity<Todo> updateTodo(Todo todo, int id) {
        if(this.todoRepository.existsById(id)) {
            todo.setId(id);
            todo.setDate(this.todoRepository.findById(id).get().getDate());
            this.todoRepository.save(todo);
            return new ResponseEntity<>(todo, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }
}
