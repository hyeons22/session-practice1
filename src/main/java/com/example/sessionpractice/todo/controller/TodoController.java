package com.example.sessionpractice.todo.controller;

import com.example.sessionpractice.common.consts.Const;
import com.example.sessionpractice.todo.dto.*;
import com.example.sessionpractice.todo.service.TodoService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class TodoController {

    private final TodoService todoService;

    @PostMapping("/todos")
    public ResponseEntity<TodoSaveResponseDto> save(
            @SessionAttribute(name = Const.LOGIN_MEMBER) Long memberId,
            @RequestBody TodoSaveRequestDto requestDto
    ){
        return ResponseEntity.ok(todoService.save(memberId, requestDto));
    }

    @GetMapping("/todos")
    public ResponseEntity<List<TodoResponseDto>> getAll(){
        return ResponseEntity.ok(todoService.findAll());
    }

    @GetMapping("/todos/{id}")
    public ResponseEntity<TodoResponseDto> getOne(@PathVariable Long id){
        return ResponseEntity.ok(todoService.findById(id));
    }

    @PutMapping("/todos/{id}")
    public ResponseEntity<TodoUpdateResponseDto> update(
            @SessionAttribute(name = Const.LOGIN_MEMBER) Long memberId,
            @PathVariable Long id,
            @RequestBody TodoUpdateRequestDto requestDto
    ){
        return ResponseEntity.ok(todoService.update(memberId, id, requestDto));
    }

    @DeleteMapping("/todos/{id}")
    public void deleteById(
            @SessionAttribute(name = Const.LOGIN_MEMBER) Long memberId,
            @PathVariable Long id
    ){
        todoService.deleteById(memberId, id);
    }
}
