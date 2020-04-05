package br.com.accountcard.email.domain.facade.change;

import br.com.accountcard.domain.customer.StatusProposal;
import br.com.accountcard.email.domain.dto.CustomerDTO;
import br.com.accountcard.email.domain.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CustomerChange {

    private final CustomerService customerService;

    public void processDecline(final CustomerDTO customerDTO) {
        customerService.update(customerDTO.getCpf(), StatusProposal.DECLINE);
    }
}
