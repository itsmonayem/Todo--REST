package com.spring.todo.service;

import com.spring.todo.dao.TodoRepository;
import com.spring.todo.entities.Todo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

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
}
