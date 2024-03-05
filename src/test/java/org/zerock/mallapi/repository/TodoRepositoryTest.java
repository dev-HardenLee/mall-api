package org.zerock.mallapi.repository;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
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
                .title("Title")
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

}