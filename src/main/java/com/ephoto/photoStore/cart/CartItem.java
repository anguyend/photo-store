package com.ephoto.photoStore.cart;

import com.ephoto.photoStore.photo.PhotoItem;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class CartItem {

    final private PhotoItem item;
    final private int qty;

    public CartItem(PhotoItem item, int qty) {
        this.item = item;
        this.qty = qty;
    }


    BigDecimal getTotal() {

        return this.item.getPrice().multiply(BigDecimal.valueOf(qty));
    };
}
