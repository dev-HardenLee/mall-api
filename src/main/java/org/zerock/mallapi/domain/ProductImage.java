package org.zerock.mallapi.domain;

import jakarta.persistence.Embeddable;
import lombok.*;

@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class ProductImage {

    private String fileName;

    private int ord;

    public void setOrd(int ord) {
        this.ord = ord;
    }

}
