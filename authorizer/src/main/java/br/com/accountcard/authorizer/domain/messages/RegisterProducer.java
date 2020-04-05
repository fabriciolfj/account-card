package br.com.accountcard.authorizer.domain.messages;

import br.com.accountcard.authorizer.domain.exception.JsonConvertException;
import br.com.accountcard.authorizer.infra.util.MessageBuild;
import br.com.accountcard.domain.customer.CustomerMessage;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@EnableBinding(RegisterCustomer.class)
public class RegisterProducer {

    private final RegisterCustomer registerCustomer;

    public void process(CustomerMessage customer) {
        var json = getJson(customer);
        var message = MessageBuild.message(json);
        registerCustomer.output().send(message);
    }

    private String getJson(CustomerMessage customer)  {
        try {
            return new ObjectMapper().writeValueAsString(customer);
        } catch (JsonProcessingException e) {
            throw new JsonConvertException(e.getMessage());
        }
    }

}
