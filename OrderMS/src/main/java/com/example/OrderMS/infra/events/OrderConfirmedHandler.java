package com.example.OrderMS.infra.events;


import com.example.OrderMS.core.events.OrderConfirmed;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class OrderConfirmedHandler {

    private static final Logger logger = LoggerFactory.getLogger(OrderConfirmedHandler.class);

    @EventListener
    public void onOrderConfirmed(OrderConfirmed event) {
        logger.info("Order confirmed: orderId={}, userId={}, occurredAt={}",
                event.getOrderId(), event.getUserId(), event.occurredAt());
    }
}



