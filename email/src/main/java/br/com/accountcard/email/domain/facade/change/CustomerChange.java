package br.com.accountcard.email.domain.facade.change;

import br.com.accountcard.domain.customer.StatusProposal;
import br.com.accountcard.domain.service.CustomerService;
import br.com.accountcard.email.domain.dto.CustomerDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CustomerChange {

    private final CustomerService customerService;

    public void updateProposal(final CustomerDTO customerDTO) {
        customerService.update(customerDTO.getCpf(), StatusProposal.valueOf(customerDTO.getStatusProposal()));
    }
}
