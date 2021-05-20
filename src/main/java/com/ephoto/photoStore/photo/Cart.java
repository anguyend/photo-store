package com.ephoto.photoStore.photo;

import com.ephoto.photoStore.cart.CartItem;

import java.util.Collection;

public interface Cart {

    void add(String sku);

    int getTotalItems();

    Collection<CartItem> getItems();

    void remove(String sku);

    void clear();
}
