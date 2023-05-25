package mk.ukim.finki.emt.order.service.impl;

import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import lombok.AllArgsConstructor;

import mk.ukim.finki.emt.order.domain.exceptions.OrderIdNotExistException;
import mk.ukim.finki.emt.order.domain.exceptions.OrderItemIdNotExistException;
import mk.ukim.finki.emt.order.domain.model.Order;
import mk.ukim.finki.emt.order.domain.model.OrderId;
import mk.ukim.finki.emt.order.domain.model.OrderItemId;
import mk.ukim.finki.emt.order.domain.repository.OrderRepository;
import mk.ukim.finki.emt.order.service.OrderService;
import mk.ukim.finki.emt.order.service.forms.OrderForm;
import mk.ukim.finki.emt.order.service.forms.OrderItemForm;
import mk.ukim.finki.emt.sharedkernel.domain.events.orders.OrderItemCreated;
import mk.ukim.finki.emt.sharedkernel.domain.events.orders.OrderItemRemoved;
import mk.ukim.finki.emt.sharedkernel.infra.DomainEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import javax.websocket.server.ServerEndpoint;
import java.time.Instant;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final DomainEventPublisher domainEventPublisher;
    private final Validator validator;

    @Override
    public OrderId placeOrder(OrderForm orderForm) {
        Objects.requireNonNull(orderForm,"order must not be null.");
        var constraintViolations = validator.validate(orderForm);
        if (constraintViolations.size()>0) {
            throw new ConstraintViolationException("The order form is not valid", constraintViolations);
        }
        var newOrder = orderRepository.saveAndFlush(toDomainObject(orderForm));
        newOrder.getOrderItemList().forEach(item->domainEventPublisher.publish(new OrderItemCreated(item.getProductId().getId(),item.getQuantity())));
        return newOrder.getId();
    }


    @Override
    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    @Override
    public Optional<Order> findById(OrderId id) {
        return orderRepository.findById(id);
    }

    @Override
    public void addItem(OrderId orderId, OrderItemForm orderItemForm) throws OrderIdNotExistException {
        Order order = orderRepository.findById(orderId).orElseThrow(OrderIdNotExistException::new);
        order.addItem(orderItemForm.getProduct(),orderItemForm.getQuantity());
        orderRepository.saveAndFlush(order);
        domainEventPublisher.publish(new OrderItemCreated(orderItemForm.getProduct().getId().getId(),orderItemForm.getQuantity()));
    }

    @Override
    public void deleteItem(OrderId orderId, OrderItemId orderItemId) throws OrderItemIdNotExistException, OrderIdNotExistException {
        Order order = orderRepository.findById(orderId).orElseThrow(OrderIdNotExistException::new);
        order.removeItem(orderItemId);
        orderRepository.saveAndFlush(order);

    }

    private Order toDomainObject(OrderForm orderForm) {
        var order = new Order(Instant.now(),orderForm.getCurrency());
        orderForm.getItem().forEach(item->order.addItem(item.getProduct(),item.getQuantity()));
        return order;
    }
}
