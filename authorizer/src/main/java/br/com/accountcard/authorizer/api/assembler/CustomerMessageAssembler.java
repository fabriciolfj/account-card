package br.com.accountcard.authorizer.api.assembler;

import br.com.accountcard.domain.customer.Customer;
import br.com.accountcard.domain.customer.CustomerMessage;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CustomerMessageAssembler {

    private final ModelMapper modelMapper;

    public CustomerMessage toMessageObject(final Customer customer) {
        return modelMapper.map(customer, CustomerMessage.class);
    }
}
