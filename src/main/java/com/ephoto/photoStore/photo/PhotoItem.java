package com.ephoto.photoStore.photo;

import lombok.Data;

import java.math.BigDecimal;
@Data
public class PhotoItem {

    private String sku;
    private String title;
    private BigDecimal price;


    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getPrice() {
        return price;
    }
}
