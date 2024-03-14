package org.zerock.mallapi.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.zerock.mallapi.domain.Todo;
import org.zerock.mallapi.dto.PageRequestDTO;
import org.zerock.mallapi.dto.PageResponseDTO;
import org.zerock.mallapi.dto.TodoDTO;
import org.zerock.mallapi.repository.TodoRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    @Override
    public Long register(TodoDTO todoDTO) {
        Todo todo = dtoToEntity(todoDTO);

        todoRepository.save(todo);

        return todo.getTno();
    }

    @Override
    public void modify(TodoDTO todoDTO) {
        Optional<Todo> byId = todoRepository.findById(todoDTO.getTno());

        Todo todo = byId.orElseThrow();

        todo.changeTitle(todoDTO.getTitle());
        todo.changeContent(todoDTO.getContent());
        todo.changeComplete(todoDTO.isComplete());
        todo.changeDueDate(todoDTO.getDueDate());
    }

    @Override
    public void remove(Long tno) {
        todoRepository.deleteById(tno);
    }

    @Override
    public PageResponseDTO<TodoDTO> getList(PageRequestDTO pageRequestDTO) {
        Page<Todo> result = todoRepository.search1(pageRequestDTO);
        List<TodoDTO> dtoList = result.get().map(todo -> entityToDTO(todo)).collect(Collectors.toList());

        PageResponseDTO<TodoDTO> responseDTO = PageResponseDTO.<TodoDTO>withAll()
                .dtoList(dtoList)
                .pageRequestDTO(pageRequestDTO)
                .total(result.getTotalElements())
                .build();

        return responseDTO;
    }// getList


}// TodoServiceImpl











