package project.carservice.service.shedulers;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import project.carservice.service.OrderService;

import java.util.logging.Logger;

@Component
public class DeleteOldFinishedOrdersScheduler {

    private final Logger LOGGER = Logger.getLogger(DeleteOldFinishedOrdersScheduler.class.getName());

    private final OrderService orderService;

    public DeleteOldFinishedOrdersScheduler(OrderService orderService) {
        this.orderService = orderService;
    }

    @Scheduled(cron = "0 0 0 * * *")
    public void deleteOldFinishedOrders() {
        LOGGER.info("Start deleting old finished orders...");
        orderService.deleteOldFinishedOrders();
        LOGGER.info("Old finished orders deleted.");
    }



}
