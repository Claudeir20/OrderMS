package com.example.OrderMS.infra.events;
import com.example.OrderMS.core.events.OrderConfirmed;
import com.example.OrderMS.core.events.OrderCreated;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;

import org.springframework.stereotype.Component;

@Component
public class OrderCreatedHandler {

    private static final Logger logger = LoggerFactory.getLogger(OrderCreatedHandler.class);

    @EventListener
    public void onOrderCreated(OrderCreated event) {
        logger.info("Order created: orderId={}, userId={}, productId={}, totalValue={}, occurredAt={}",
                event.getOrderId(), event.getUserId(), event.getProductId(),
                event.getTotalValue(), event.occurredAt());
    }
}


