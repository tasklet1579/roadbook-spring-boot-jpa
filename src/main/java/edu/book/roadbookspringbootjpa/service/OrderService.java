package edu.book.roadbookspringbootjpa.service;

import edu.book.roadbookspringbootjpa.dto.OrderDto;
import edu.book.roadbookspringbootjpa.entity.Item;
import edu.book.roadbookspringbootjpa.entity.Member;
import edu.book.roadbookspringbootjpa.entity.Order;
import edu.book.roadbookspringbootjpa.entity.OrderItem;
import edu.book.roadbookspringbootjpa.repository.ItemRepository;
import edu.book.roadbookspringbootjpa.repository.MemberRepository;
import edu.book.roadbookspringbootjpa.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderService {
    private final ItemRepository itemRepository;
    private final MemberRepository memberRepository;
    private final OrderRepository orderRepository;

    public Long order(OrderDto orderDto, String email) {
        Item item = itemRepository.findById(orderDto.getItemId())
                                  .orElseThrow(EntityNotFoundException::new);
        Member member = memberRepository.findByEmail(email);

        List<OrderItem> orderItemList = new ArrayList<>();
        OrderItem orderItem = OrderItem.createOrderItem(item, orderDto.getCount());
        orderItemList.add(orderItem);

        Order order = Order.createOrder(member, orderItemList);
        orderRepository.save(order);

        return order.getId();
    }
}
