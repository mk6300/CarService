package project.carservice.service.shedulers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import project.carservice.service.OrderService;

@Component
public class UpdateOrdersScheduler {
    private final Logger LOGGER = LoggerFactory.getLogger(UpdateOrdersScheduler.class);

    private final OrderService orderService;

    public UpdateOrdersScheduler(OrderService orderService) {
        this.orderService = orderService;
    }

    @Scheduled(cron = "0 0/15 * * * *")
    public void updateOrders() {
        LOGGER.info("Start updating orders...");
        orderService.updateOrderStatusProgress();
        LOGGER.info("Orders updated");
    }

}
