package org.zerock.mallapi.service;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.zerock.mallapi.domain.Todo;
import org.zerock.mallapi.dto.PageRequestDTO;
import org.zerock.mallapi.dto.TodoDTO;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Log4j2
class TodoServiceTest {

    @Autowired
    private TodoService todoService;

    @Test
    void testGet() {
        Long tno = 50L;

        log.info(todoService.get(tno));
    }// testGet

    @Test
    void testRegister() {
        TodoDTO todo = TodoDTO.builder()
                .title("Test Title...")
                .content("Test Content...")
                .dueDate(LocalDate.of(2024, 03, 14))
                .build();

        Long savedId = todoService.register(todo);

        log.info(savedId);
    }// testRegister

    @Test
    void testGetList() {
        PageRequestDTO pageRequestDTO = PageRequestDTO.builder().page(11).build();

        log.info(todoService.getList(pageRequestDTO));
    }// testGetList

}// TodoServiceTest












