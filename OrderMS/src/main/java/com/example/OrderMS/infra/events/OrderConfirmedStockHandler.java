package com.example.OrderMS.infra.events;

import com.example.OrderMS.core.events.OrderConfirmed;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class OrderConfirmedStockHandler {

    private static final Logger logger = LoggerFactory.getLogger(OrderConfirmedStockHandler.class);

    @EventListener
    public void onOrderConfirmed(OrderConfirmed event) {
        // Placeholder: integrar com serviço real de estoque
        logger.info("Reserving stock: orderId={}, userId={}",
                event.getOrderId(), event.getUserId());
    }
}
