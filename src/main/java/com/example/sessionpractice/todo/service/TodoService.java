package com.example.sessionpractice.todo.service;

import com.example.sessionpractice.member.entity.Member;
import com.example.sessionpractice.member.repository.MemberRepository;
import com.example.sessionpractice.todo.dto.*;
import com.example.sessionpractice.todo.entity.Todo;
import com.example.sessionpractice.todo.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TodoService {

    private final TodoRepository todoRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public TodoSaveResponseDto save(Long memberId, TodoSaveRequestDto requestDto) {
        Member member = memberRepository.findById(memberId).orElseThrow(
                () -> new IllegalArgumentException("그런 멤버 없습니다.")
        );
        Todo todo = new Todo(requestDto.getContent(), member);
        Todo savedTodo = todoRepository.save(todo);

        return new TodoSaveResponseDto(
                savedTodo.getId(),
                savedTodo.getContent(),
                member.getId(),
                member.getEmail()
        );
    }

    @Transactional(readOnly = true)
    public List<TodoResponseDto> findAll() {
        List<Todo> todos = todoRepository.findAll();
        List<TodoResponseDto> dtoList = new ArrayList<>();
//        return todos.stream()
//                .map(todo -> new TodoResponseDto(todo.getId(),todo.getContent()))
//                .toList();
        for (Todo todo : todos) {
            TodoResponseDto dto = new TodoResponseDto(todo.getId(), todo.getContent());
            dtoList.add(dto);
        }
        return dtoList;
    }

    @Transactional(readOnly = true)
    public TodoResponseDto findById(Long id) {
        Todo todo = todoRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("그런 일정 없어")
        );

        return new TodoResponseDto(todo.getId(), todo.getContent());
    }

    @Transactional
    public TodoUpdateResponseDto update(Long memberId, Long id, TodoUpdateRequestDto requestDto) {
        Member member = memberRepository.findById(memberId).orElseThrow(
                () -> new IllegalArgumentException("그런 멤버 없습니다.")
        );

        Todo todo = todoRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("그런 일정 없어")
        );

        if(!todo.getMember().getId().equals(member.getId())){
            throw new IllegalArgumentException("Todo 작성자가 아닙니다. 누구세요?");
        }

        todo.update(requestDto.getContent());

        return new TodoUpdateResponseDto(todo.getId(), todo.getContent());
    }

    @Transactional
    public void deleteById(Long memberId, Long id) {
        Member member = memberRepository.findById(memberId).orElseThrow(
                () -> new IllegalArgumentException("그런 멤버 없습니다.")
        );

        Todo todo = todoRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("그런 일정 없어")
        );

        if(!todo.getMember().getId().equals(member.getId())){
            throw new IllegalArgumentException("Todo 작성자가 아닙니다. 누구세요?");
        }

        todoRepository.deleteById(id);
    }
}
