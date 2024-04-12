package org.zerock.mallapi.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.zerock.mallapi.dto.PageRequestDTO;
import org.zerock.mallapi.dto.PageResponseDTO;
import org.zerock.mallapi.dto.ProductDTO;
import org.zerock.mallapi.service.ProductService;
import org.zerock.mallapi.util.CustomFileUtil;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/products")
@Log4j2
@RequiredArgsConstructor
public class ProductController {

    private final CustomFileUtil customFileUtil;

    private final ProductService productService;

    @PostMapping("/")
    public Map<String, Long> register(ProductDTO productDTO) {
        log.info("register : " + productDTO);

        List<MultipartFile> files = productDTO.getFiles();

        List<String> uploadedFileNames = customFileUtil.saveFiles(files);

        productDTO.setUploadFileNames(uploadedFileNames);

        log.info(uploadedFileNames);

        Long pno = productService.register(productDTO);

        return Map.of("result", pno);
    }// register

    @GetMapping("/view/{fileName}")
    public ResponseEntity<Resource> viewFileGet(@PathVariable String fileName) {
        return customFileUtil.getFile(fileName);
    }// viewFileGet

    @GetMapping("/list")
    public PageResponseDTO<ProductDTO> list(PageRequestDTO pageRequestDTO) {
        return productService.getList(pageRequestDTO);
    }// list

    @GetMapping("/{pno}")
    public ProductDTO read(@PathVariable("pno") Long pno) {
        return productService.get(pno);
    }// read

    @PutMapping("/{pno}")
    public Map<String, String> modify(@PathVariable("pno") Long pno, ProductDTO productDTO) {
        productDTO.setPno(pno);

        ProductDTO oldProductDTO = productService.get(pno);

        List<MultipartFile> files = productDTO.getFiles();

        List<String> currentUploadFileNames = customFileUtil.saveFiles(files);

        List<String> uploadedFileNames = productDTO.getUploadFileNames();

        if(!currentUploadFileNames.isEmpty()) {
            uploadedFileNames.addAll(currentUploadFileNames);
        }// if

        productService.modify(productDTO);

        List<String> oldFileNames = oldProductDTO.getUploadFileNames();

        if(!oldFileNames.isEmpty()) {
            List<String> removeFiles = oldFileNames.stream().filter(fileName -> uploadedFileNames.indexOf(fileName) == -1).collect(Collectors.toList());

            customFileUtil.deleteFiles(removeFiles);
        }// if

        return Map.of("result", "success");
    }// modify

    @DeleteMapping("/{pno}")
    public Map<String, String> remove(@PathVariable Long pno) {
        List<String> oldFileNames = productService.get(pno).getUploadFileNames();

        productService.remove(pno);

        customFileUtil.deleteFiles(oldFileNames);

        return Map.of("result", "success");
    }// remove


}// ProductController
