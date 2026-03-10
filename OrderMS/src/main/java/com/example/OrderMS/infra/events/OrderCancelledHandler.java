package com.example.OrderMS.infra.events;


import com.example.OrderMS.core.events.OrderCancelled;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;


@Component
public class OrderCancelledHandler {

    private static final Logger logger = LoggerFactory.getLogger(OrderCancelledHandler.class);

    @EventListener
    public void onOrderCancelled(OrderCancelled event) {
        logger.info("Order cancelled: orderId={}, userId={}, occurredAt={}",
                event.getOrderId(), event.getUserId(), event.occurredAt());
    }
}
