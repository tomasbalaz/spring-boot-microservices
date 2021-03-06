package sk.balaz.clients.fraud;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

//v1
//@FeignClient(
//        value = "fraud",
//        path = "/api/v1/fraud-check"
//)

//v2
@FeignClient(
        name = "fraud",
        url = "${clients.fraud.url}"
)
public interface FraudClient {

    @GetMapping(path = "/api/v1/fraud-check/{customerId}")
    FraudCheckResponse isFraudster(
            @PathVariable("customerId") Integer customerId);

}
