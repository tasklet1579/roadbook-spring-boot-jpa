package edu.book.roadbookspringbootjpa.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "cart_item")
public class CartItem extends BaseEntity {
    @Id
    @GeneratedValue
    @Column(name = "cart_item_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cart_id")
    private Cart cart; // 하나의 장바구니에는 여러 개의 상품을 담을 수 있다

    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item item; // 하나의 상품은 여러 장바구니의 상품으로 담길 수 있다

    private int count;

    public static CartItem createCartItem(Cart cart, Item item, int count) {
        CartItem cartItem = new CartItem();
        cartItem.setCart(cart);
        cartItem.setItem(item);
        cartItem.setCount(count);
        return cartItem;
    }

    public void addCount(int count) {
        this.count += count;
    }

    public void updateCount(int count) {
        this.count = count;
    }
}
