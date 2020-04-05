package br.com.accountcard.fraud.domain.facade.change;

import br.com.accountcard.domain.customer.StatusProposal;
import br.com.accountcard.domain.service.CustomerService;
import br.com.accountcard.fraud.domain.dto.CustomerDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CustomerChange {

    private final CustomerService customerService;

    public void updateProposal(final CustomerDto customerDto) {
        customerService.update(customerDto.getCpf(), StatusProposal.valueOf(customerDto.getStatusProposal()));
    }
}
