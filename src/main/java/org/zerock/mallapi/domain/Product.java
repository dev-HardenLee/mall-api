package org.zerock.mallapi.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tbl_product")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(exclude = {"imageList"})
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pno;

    private String pname;

    private int price;

    private String pdesc;

    private boolean delFlag;

    @ElementCollection
    @Builder.Default
    private List<ProductImage> imageList = new ArrayList<>();

    public void changeName(String pname) {
        this.pname = pname;
    }

    public void changePrice(int price) {
        this.price = price;
    }

    public void changePdesc(String pdesc) {
        this.pdesc = pdesc;
    }

    public void changeDel(boolean delFlag) {
        this.delFlag = delFlag;
    }

    public void addImage(ProductImage image) {
        image.setOrd(imageList.size());

        imageList.add(image);
    }// addImage

    public void addImageString(String fileName) {
        ProductImage productImage = ProductImage.builder().fileName(fileName).build();

        addImage(productImage);
    }// addImageString

    public void clearList() {
        this.imageList.clear();
    }// clearList

}
