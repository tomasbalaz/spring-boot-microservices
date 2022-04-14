package sk.balaz.customer;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import sk.balaz.clients.fraud.FraudCheckResponse;
import sk.balaz.clients.fraud.FraudClient;
import sk.balaz.clients.notification.NotificationClient;
import sk.balaz.clients.notification.NotificationRequest;

@Service
@AllArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;

    private final FraudClient fraudClient;

    private final NotificationClient notificationClient;

    public void registerCustomer(CustomerRegistrationRequest request) {
        Customer customer = Customer.builder()
                .firstName(request.firstName())
                .lastName(request.lastName())
                .email(request.email())
                .build();

        customerRepository.saveAndFlush(customer);

        // TODO: check if email valid
        // TODO: check if email not taken
        // TODO: check if fraudster
        FraudCheckResponse fraudCheckResponse = fraudClient.isFraudster(customer.getId());

        if(fraudCheckResponse.isFraudster()) {
            throw new IllegalStateException("fraudster");
        }


        // TODO: make it async. i.e add to queue
        notificationClient.sendNotification(
                new NotificationRequest(
                        customer.getId(),
                        customer.getEmail(),
                        String.format("Hi %s, welcome in my microservices world...",
                                customer.getFirstName())

                )
        );

    }
}
