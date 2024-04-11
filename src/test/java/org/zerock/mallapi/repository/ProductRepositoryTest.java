package org.zerock.mallapi.repository;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;
import org.zerock.mallapi.domain.Product;
import org.zerock.mallapi.dto.PageRequestDTO;

import java.util.Arrays;
import java.util.Optional;
import java.util.UUID;

@SpringBootTest
@Log4j2
class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @Test
    public void testInsert() {

        for(int i=0; i<10; i++) {
            Product product = Product.builder().pname("test product_" + i).pdesc("test description").price(1000).build();

            product.addImageString(UUID.randomUUID() + "_" + "image1.jpg");
            product.addImageString(UUID.randomUUID() + "_" + "image2.jpg");

            productRepository.save(product);
        }
    }// testInsert

    @Test
    public void testRead() {
        Long pno = 1L;

        Optional<Product> result = productRepository.selectOne(pno);

        Product product = result.orElseThrow();

        log.info(product);
        log.info(product.getImageList());
    }// testRead

    @Test
    @Transactional
    public void testDelete() {
        Long pno = 2L;

        productRepository.updateToDelete(pno, true);
    }// testDelete

    @Test
    public void testUpdate() {
        Product product = productRepository.selectOne(1L).get();

        product.changePrice(3000);

        product.clearList();

        product.addImageString(UUID.randomUUID() + "_" + "image1.jpg");
        product.addImageString(UUID.randomUUID() + "_" + "image2.jpg");
        product.addImageString(UUID.randomUUID() + "_" + "image3.jpg");

        productRepository.save(product);
    }// testUpdate

    @Test
    public void testList() {
        Pageable pageable = PageRequest.of(0, 10, Sort.by("pno").descending());

        Page<Object[]> result = productRepository.selectList(pageable);

        result.getContent().forEach(arr -> {
            log.info(Arrays.toString(arr));
        });
    }// testList

    @Test
    public void testSearch() {
        PageRequestDTO pageRequestDTO = PageRequestDTO.builder().build();

        log.info(productRepository.searchList(pageRequestDTO));
    }// testSearch

}// ProductRepositoryTest
