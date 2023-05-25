package mk.ukim.finki.emt.order.service;

import mk.ukim.finki.emt.order.domain.exceptions.OrderIdNotExistException;
import mk.ukim.finki.emt.order.domain.exceptions.OrderItemIdNotExistException;
import mk.ukim.finki.emt.order.domain.model.Order;
import mk.ukim.finki.emt.order.domain.model.OrderId;
import mk.ukim.finki.emt.order.domain.model.OrderItemId;
import mk.ukim.finki.emt.order.service.forms.OrderForm;
import mk.ukim.finki.emt.order.service.forms.OrderItemForm;

import java.util.List;
import java.util.Optional;

public interface OrderService {
    OrderId placeOrder(OrderForm orderForm);//povik na poveke metodi
    List<Order> findAll();
    Optional<Order> findById(OrderId id);
    void addItem(OrderId orderId, OrderItemForm orderItemForm)throws OrderIdNotExistException;

    void deleteItem(OrderId orderId, OrderItemId orderItemId)throws OrderItemIdNotExistException, OrderIdNotExistException;
}
