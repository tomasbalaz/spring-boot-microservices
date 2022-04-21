package sk.balaz.notification.rabbitmq;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import sk.balaz.clients.notification.NotificationRequest;
import sk.balaz.notification.NotificationService;

@Service
@AllArgsConstructor
@Slf4j
public class NotificationConsumer {

    private final NotificationService notificationService;

    @RabbitListener(queues = "${rabbitmq.queue.notification}")
    public void consumer(NotificationRequest notificationRequest) {

        log.info("Consumed {} from queue", notificationRequest);
        notificationService.send(notificationRequest);
    }
}
