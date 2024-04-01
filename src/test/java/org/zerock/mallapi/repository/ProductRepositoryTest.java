package org.zerock.mallapi.repository;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.zerock.mallapi.domain.Product;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Log4j2
class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @Test
    public void testInsert() {
        Product product = Product.builder().pname("test product").pdesc("test description").price(1000).build();

        product.addImageString(UUID.randomUUID() + "_" + "image1.jpg");
        product.addImageString(UUID.randomUUID() + "_" + "image2.jpg");

        productRepository.save(product);
    }// testInsert

}// ProductRepositoryTest