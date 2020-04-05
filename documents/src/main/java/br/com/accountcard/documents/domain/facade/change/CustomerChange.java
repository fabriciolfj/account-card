package br.com.accountcard.documents.domain.facade.change;

import br.com.accountcard.documents.domain.dto.CustomerDto;
import br.com.accountcard.domain.customer.StatusProposal;
import br.com.accountcard.domain.service.CustomerService;
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
