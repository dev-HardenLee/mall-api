package org.zerock.mallapi.repository;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.zerock.mallapi.domain.Todo;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Log4j2
class TodoRepositoryTest {

    @Autowired
    private TodoRepository todoRepository;

    @Test
    void test1() {
        assertNotNull(todoRepository);

        log.info(todoRepository.getClass().getName());
    }// test1

    @Test
    void testInsert() {
        Todo todo = Todo.builder()
                .title("Title..")
                .content("Content")
                .dueDate(LocalDate.of(2024, 12, 31))
                .build();

        Todo result = todoRepository.save(todo);

        log.info(result);
    }// testInsert

    @Test
    void testRead() {
        Long tno = 1L;

        Optional<Todo> byId = todoRepository.findById(tno);

        Todo todo = byId.orElseThrow();

        log.info(todo);
    }// testRead

    @Test
    void testUpdate() {
        Long tno = 1L;

        Optional<Todo> byId = todoRepository.findById(tno);

        Todo todo = byId.orElseThrow();

        todo.changeTitle("Update Title");
        todo.changeContent("Update Content");
        todo.changeComplete(true);

        todoRepository.save(todo);
    }// testUpdate

    @Test
    void testPaging() {
        Pageable pageable = PageRequest.of(0, 10, Sort.by("tno").descending());

        Page<Todo> result = todoRepository.findAll(pageable);

        log.info(result.getTotalElements());
        log.info(result.getContent());
    }// testPaging

    @Test
    void testSearch1() {
        todoRepository.search1();
    }// testSearch1

}










