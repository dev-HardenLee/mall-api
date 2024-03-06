package org.zerock.mallapi.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.zerock.mallapi.domain.Todo;
import org.zerock.mallapi.dto.TodoDTO;
import org.zerock.mallapi.repository.TodoRepository;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
@Log4j2
public class TodoServiceImpl implements TodoService {

    private final TodoRepository todoRepository;

    @Override
    public TodoDTO get(Long tno) {
        Optional<Todo> byId = todoRepository.findById(tno);

        Todo todo = byId.orElseThrow();

        return entityToDTO(todo);
    }// get

}// TodoServiceImpl











