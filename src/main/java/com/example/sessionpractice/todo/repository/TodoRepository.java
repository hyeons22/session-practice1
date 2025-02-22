package com.example.sessionpractice.todo.repository;

import com.example.sessionpractice.todo.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository<Todo, Long> {
}
