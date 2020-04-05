package br.com.accountcard.authorizer.api.assembler;

import br.com.accountcard.domain.customer.Customer;
import br.com.accountcard.domain.customer.CustomerDTO;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CustomerDtoDesassembler {

    private final ModelMapper modelMapper;

    public Customer toDomainObject(final CustomerDTO dto) {
        return modelMapper.map(dto, Customer.class);
    }

    public void copyToDomainObject(final CustomerDTO dto, final Customer customer) {
        modelMapper.map(dto, customer);
    }

}
