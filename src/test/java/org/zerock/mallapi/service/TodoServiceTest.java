package org.zerock.mallapi.service;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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

}// TodoServiceTest












