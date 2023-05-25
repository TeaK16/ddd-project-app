package mk.ukim.finki.emt.product.xport.events;

import lombok.AllArgsConstructor;
import mk.ukim.finki.emt.product.domain.models.ProductId;
import mk.ukim.finki.emt.product.service.ProductService;
import mk.ukim.finki.emt.sharedkernel.domain.config.TopicHolder;
import mk.ukim.finki.emt.sharedkernel.domain.events.DomainEvent;
import mk.ukim.finki.emt.sharedkernel.domain.events.orders.OrderItemCreated;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ProductEventListener {
  private ProductService productService;

    @KafkaListener(topics = TopicHolder.TOPIC_ORDER_ITEM_CREATED,groupId = "product")
    public void consumeOrderItemCreatedEvent(String jsonMessage){
      try {
          OrderItemCreated event = DomainEvent.fromJson(jsonMessage, OrderItemCreated.class);
          productService.orderItemCreated(ProductId.of(event.getProductId()), event.getQuantity());
      }catch (Exception e){

      }
  }

    @KafkaListener(topics = TopicHolder.TOPIC_ORDER_ITEM_REMOVED,groupId = "product")
    public void consumeOrderItemRemovedEvent(String jsonMessage){
        try {
            OrderItemCreated event = DomainEvent.fromJson(jsonMessage, OrderItemCreated.class);
            productService.orderItemRemoved(ProductId.of(event.getProductId()), event.getQuantity());
        }catch (Exception e){

        }
    }
}
